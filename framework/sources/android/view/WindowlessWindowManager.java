package android.view;

import android.content.ClipData;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.util.Log;
import android.util.MergedConfiguration;
import android.view.IWindowSession;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.ClientWindowFrames;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public class WindowlessWindowManager implements IWindowSession {
    private static final String TAG = "WindowlessWindowManager";
    private final Configuration mConfiguration;
    private final IBinder mHostInputToken;
    protected final SurfaceControl mRootSurface;
    final HashMap<IBinder, State> mStateForWindow = new HashMap<>();
    final HashMap<IBinder, ResizeCompleteCallback> mResizeCompletionForWindow = new HashMap<>();
    private final SurfaceSession mSurfaceSession = new SurfaceSession();
    private int mForceHeight = -1;
    private int mForceWidth = -1;
    private final IWindowSession mRealWm = WindowManagerGlobal.getWindowSession();

    /* loaded from: classes3.dex */
    public interface ResizeCompleteCallback {
        void finished(SurfaceControl.Transaction transaction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class State {
        int mDisplayId;
        IBinder mInputChannelToken;
        Region mInputRegion;
        WindowManager.LayoutParams mParams;
        SurfaceControl mSurfaceControl;

        State(SurfaceControl sc, WindowManager.LayoutParams p, int displayId, IBinder inputChannelToken) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mParams = layoutParams;
            this.mSurfaceControl = sc;
            layoutParams.copyFrom(p);
            this.mDisplayId = displayId;
            this.mInputChannelToken = inputChannelToken;
        }
    }

    public WindowlessWindowManager(Configuration c, SurfaceControl rootSurface, IBinder hostInputToken) {
        this.mRootSurface = rootSurface;
        this.mConfiguration = new Configuration(c);
        this.mHostInputToken = hostInputToken;
    }

    protected void setConfiguration(Configuration configuration) {
        this.mConfiguration.setTo(configuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCompletionCallback(IBinder window, ResizeCompleteCallback callback) {
        if (this.mResizeCompletionForWindow.get(window) != null) {
            Log.w(TAG, "Unsupported overlapping resizes");
        }
        this.mResizeCompletionForWindow.put(window, callback);
    }

    protected void setTouchRegion(IBinder window, Region region) {
        synchronized (this) {
            State state = this.mStateForWindow.get(window);
            if (state != null) {
                if (!Objects.equals(region, state.mInputRegion)) {
                    state.mInputRegion = region != null ? new Region(region) : null;
                    if (state.mInputChannelToken != null) {
                        try {
                            this.mRealWm.updateInputChannel(state.mInputChannelToken, state.mDisplayId, state.mSurfaceControl, state.mParams.flags, state.mParams.privateFlags, state.mInputRegion);
                        } catch (RemoteException e) {
                            Log.e(TAG, "Failed to update surface input channel: ", e);
                        }
                    }
                }
            }
        }
    }

    protected void attachToParentSurface(IWindow window, SurfaceControl.Builder b) {
        b.setParent(this.mRootSurface);
    }

    @Override // android.view.IWindowSession
    public int addToDisplay(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int displayId, InsetsState requestedVisibility, InputChannel outInputChannel, InsetsState outInsetsState, InsetsSourceControl[] outActiveControls) {
        SurfaceControl.Builder b = new SurfaceControl.Builder(this.mSurfaceSession).setFormat(attrs.format).setBLASTLayer().setName(attrs.getTitle().toString()).setCallsite("WindowlessWindowManager.addToDisplay");
        attachToParentSurface(window, b);
        SurfaceControl sc = b.build();
        if ((attrs.inputFeatures & 2) == 0) {
            try {
                IWindowSession iWindowSession = this.mRealWm;
                if (iWindowSession instanceof IWindowSession.Stub) {
                    iWindowSession.grantInputChannel(displayId, new SurfaceControl(sc, "WindowlessWindowManager.addToDisplay"), window, this.mHostInputToken, attrs.flags, attrs.privateFlags, attrs.type, outInputChannel);
                } else {
                    iWindowSession.grantInputChannel(displayId, sc, window, this.mHostInputToken, attrs.flags, attrs.privateFlags, attrs.type, outInputChannel);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to grant input to surface: ", e);
            }
        }
        State state = new State(sc, attrs, displayId, outInputChannel != null ? outInputChannel.getToken() : null);
        synchronized (this) {
            this.mStateForWindow.put(window.asBinder(), state);
        }
        return isInTouchMode() ? 11 : 10;
    }

    @Override // android.view.IWindowSession
    public int addToDisplayAsUser(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int displayId, int userId, InsetsState requestedVisibility, InputChannel outInputChannel, InsetsState outInsetsState, InsetsSourceControl[] outActiveControls) {
        return addToDisplay(window, attrs, viewVisibility, displayId, requestedVisibility, outInputChannel, outInsetsState, outActiveControls);
    }

    @Override // android.view.IWindowSession
    public int addToDisplayWithoutInputChannel(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, InsetsState insetsState) {
        return 0;
    }

    @Override // android.view.IWindowSession
    public void remove(IWindow window) throws RemoteException {
        State state;
        this.mRealWm.remove(window);
        synchronized (this) {
            state = this.mStateForWindow.remove(window.asBinder());
        }
        if (state != null) {
            SurfaceControl.Transaction t = new SurfaceControl.Transaction();
            try {
                t.remove(state.mSurfaceControl).apply();
                t.close();
            } catch (Throwable th) {
                try {
                    t.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Invalid window token (never added or removed already)");
        }
    }

    private boolean isOpaque(WindowManager.LayoutParams attrs) {
        if ((attrs.surfaceInsets == null || attrs.surfaceInsets.left == 0) && attrs.surfaceInsets.top == 0 && attrs.surfaceInsets.right == 0 && attrs.surfaceInsets.bottom == 0) {
            return !PixelFormat.formatHasAlpha(attrs.format);
        }
        return false;
    }

    private boolean isInTouchMode() {
        try {
            return WindowManagerGlobal.getWindowSession().getInTouchMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to check if the window is in touch mode", e);
            return false;
        }
    }

    protected IBinder getWindowBinder(View rootView) {
        ViewRootImpl root = rootView.getViewRootImpl();
        if (root == null) {
            return null;
        }
        return root.mWindow.asBinder();
    }

    protected SurfaceControl getSurfaceControl(View rootView) {
        ViewRootImpl root = rootView.getViewRootImpl();
        if (root == null) {
            return null;
        }
        return getSurfaceControl(root.mWindow);
    }

    protected SurfaceControl getSurfaceControl(IWindow window) {
        State s = this.mStateForWindow.get(window.asBinder());
        if (s == null) {
            return null;
        }
        return s.mSurfaceControl;
    }

    @Override // android.view.IWindowSession
    public int relayout(IWindow window, WindowManager.LayoutParams inAttrs, int requestedWidth, int requestedHeight, int viewFlags, int flags, long frameNumber, ClientWindowFrames outFrames, MergedConfiguration mergedConfiguration, SurfaceControl outSurfaceControl, InsetsState outInsetsState, InsetsSourceControl[] outActiveControls, Point outSurfaceSize) {
        State state;
        int attrChanges;
        synchronized (this) {
            state = this.mStateForWindow.get(window.asBinder());
        }
        if (state != null) {
            SurfaceControl sc = state.mSurfaceControl;
            SurfaceControl.Transaction t = new SurfaceControl.Transaction();
            if (inAttrs != null) {
                int attrChanges2 = state.mParams.copyFrom(inAttrs);
                attrChanges = attrChanges2;
            } else {
                attrChanges = 0;
            }
            WindowManager.LayoutParams attrs = state.mParams;
            if (viewFlags == 0) {
                outSurfaceSize.set(getSurfaceWidth(attrs), getSurfaceHeight(attrs));
                t.setOpaque(sc, isOpaque(attrs)).show(sc).apply();
                outSurfaceControl.copyFrom(sc, "WindowlessWindowManager.relayout");
            } else {
                t.hide(sc).apply();
                outSurfaceControl.release();
            }
            outFrames.frame.set(0, 0, attrs.width, attrs.height);
            outFrames.displayFrame.set(outFrames.frame);
            Configuration configuration = this.mConfiguration;
            mergedConfiguration.setConfiguration(configuration, configuration);
            if (!((attrChanges & 4) == 0 || state.mInputChannelToken == null)) {
                try {
                    IWindowSession iWindowSession = this.mRealWm;
                    if (iWindowSession instanceof IWindowSession.Stub) {
                        iWindowSession.updateInputChannel(state.mInputChannelToken, state.mDisplayId, new SurfaceControl(sc, "WindowlessWindowManager.relayout"), attrs.flags, attrs.privateFlags, state.mInputRegion);
                    } else {
                        iWindowSession.updateInputChannel(state.mInputChannelToken, state.mDisplayId, sc, attrs.flags, attrs.privateFlags, state.mInputRegion);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to update surface input channel: ", e);
                }
            }
            return isInTouchMode() ? 1 : 0;
        }
        throw new IllegalArgumentException("Invalid window token (never added or removed already)");
    }

    @Override // android.view.IWindowSession
    public void prepareToReplaceWindows(IBinder appToken, boolean childrenOnly) {
    }

    @Override // android.view.IWindowSession
    public boolean outOfMemory(IWindow window) {
        return false;
    }

    @Override // android.view.IWindowSession
    public void setInsets(IWindow window, int touchableInsets, Rect contentInsets, Rect visibleInsets, Region touchableRegion) {
    }

    @Override // android.view.IWindowSession
    public void finishDrawing(IWindow window, SurfaceControl.Transaction postDrawTransaction) {
        synchronized (this) {
            ResizeCompleteCallback c = this.mResizeCompletionForWindow.get(window.asBinder());
            if (c == null) {
                postDrawTransaction.apply();
                return;
            }
            c.finished(postDrawTransaction);
            this.mResizeCompletionForWindow.remove(window.asBinder());
        }
    }

    @Override // android.view.IWindowSession
    public void setInTouchMode(boolean showFocus) {
    }

    @Override // android.view.IWindowSession
    public boolean getInTouchMode() {
        return false;
    }

    @Override // android.view.IWindowSession
    public boolean performHapticFeedback(int effectId, boolean always) {
        return false;
    }

    @Override // android.view.IWindowSession
    public IBinder performDrag(IWindow window, int flags, SurfaceControl surface, int touchSource, float touchX, float touchY, float thumbCenterX, float thumbCenterY, ClipData data) {
        return null;
    }

    @Override // android.view.IWindowSession
    public void reportDropResult(IWindow window, boolean consumed) {
    }

    @Override // android.view.IWindowSession
    public void cancelDragAndDrop(IBinder dragToken, boolean skipAnimation) {
    }

    @Override // android.view.IWindowSession
    public void dragRecipientEntered(IWindow window) {
    }

    @Override // android.view.IWindowSession
    public void dragRecipientExited(IWindow window) {
    }

    @Override // android.view.IWindowSession
    public void setWallpaperPosition(IBinder windowToken, float x, float y, float xstep, float ystep) {
    }

    @Override // android.view.IWindowSession
    public void setWallpaperZoomOut(IBinder windowToken, float zoom) {
    }

    @Override // android.view.IWindowSession
    public void setShouldZoomOutWallpaper(IBinder windowToken, boolean shouldZoom) {
    }

    @Override // android.view.IWindowSession
    public void wallpaperOffsetsComplete(IBinder window) {
    }

    @Override // android.view.IWindowSession
    public void setWallpaperDisplayOffset(IBinder windowToken, int x, int y) {
    }

    @Override // android.view.IWindowSession
    public Bundle sendWallpaperCommand(IBinder window, String action, int x, int y, int z, Bundle extras, boolean sync) {
        return null;
    }

    @Override // android.view.IWindowSession
    public void wallpaperCommandComplete(IBinder window, Bundle result) {
    }

    @Override // android.view.IWindowSession
    public void onRectangleOnScreenRequested(IBinder token, Rect rectangle) {
    }

    @Override // android.view.IWindowSession
    public IWindowId getWindowId(IBinder window) {
        return null;
    }

    @Override // android.view.IWindowSession
    public void pokeDrawLock(IBinder window) {
    }

    @Override // android.view.IWindowSession
    public boolean startMovingTask(IWindow window, float startX, float startY) {
        return false;
    }

    @Override // android.view.IWindowSession
    public void finishMovingTask(IWindow window) {
    }

    @Override // android.view.IWindowSession
    public void updatePointerIcon(IWindow window) {
    }

    @Override // android.view.IWindowSession
    public void updateDisplayContentLocation(IWindow window, int x, int y, int displayId) {
    }

    @Override // android.view.IWindowSession
    public void updateTapExcludeRegion(IWindow window, Region region) {
    }

    @Override // android.view.IWindowSession
    public void insetsModified(IWindow window, InsetsState state) {
    }

    @Override // android.view.IWindowSession
    public void reportSystemGestureExclusionChanged(IWindow window, List<Rect> exclusionRects) {
    }

    @Override // android.view.IWindowSession
    public void grantInputChannel(int displayId, SurfaceControl surface, IWindow window, IBinder hostInputToken, int flags, int privateFlags, int type, InputChannel outInputChannel) {
    }

    @Override // android.view.IWindowSession
    public void updateInputChannel(IBinder channelToken, int displayId, SurfaceControl surface, int flags, int privateFlags, Region region) {
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    private int getSurfaceWidth(WindowManager.LayoutParams attrs) {
        Rect surfaceInsets = attrs.surfaceInsets;
        return surfaceInsets != null ? attrs.width + surfaceInsets.left + surfaceInsets.right : attrs.width;
    }

    private int getSurfaceHeight(WindowManager.LayoutParams attrs) {
        Rect surfaceInsets = attrs.surfaceInsets;
        return surfaceInsets != null ? attrs.height + surfaceInsets.top + surfaceInsets.bottom : attrs.height;
    }

    @Override // android.view.IWindowSession
    public void grantEmbeddedWindowFocus(IWindow callingWindow, IBinder targetInputToken, boolean grantFocus) {
    }

    @Override // android.view.IWindowSession
    public void generateDisplayHash(IWindow window, Rect boundsInWindow, String hashAlgorithm, RemoteCallback callback) {
    }
}
