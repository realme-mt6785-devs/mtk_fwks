package android.view;

import android.content.ClipData;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.view.IWindow;
import android.view.IWindowId;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.ClientWindowFrames;
import java.util.List;
/* loaded from: classes3.dex */
public interface IWindowSession extends IInterface {
    int addToDisplay(IWindow iWindow, WindowManager.LayoutParams layoutParams, int i, int i2, InsetsState insetsState, InputChannel inputChannel, InsetsState insetsState2, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    int addToDisplayAsUser(IWindow iWindow, WindowManager.LayoutParams layoutParams, int i, int i2, int i3, InsetsState insetsState, InputChannel inputChannel, InsetsState insetsState2, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    int addToDisplayWithoutInputChannel(IWindow iWindow, WindowManager.LayoutParams layoutParams, int i, int i2, InsetsState insetsState) throws RemoteException;

    void cancelDragAndDrop(IBinder iBinder, boolean z) throws RemoteException;

    void dragRecipientEntered(IWindow iWindow) throws RemoteException;

    void dragRecipientExited(IWindow iWindow) throws RemoteException;

    void finishDrawing(IWindow iWindow, SurfaceControl.Transaction transaction) throws RemoteException;

    void finishMovingTask(IWindow iWindow) throws RemoteException;

    void generateDisplayHash(IWindow iWindow, Rect rect, String str, RemoteCallback remoteCallback) throws RemoteException;

    boolean getInTouchMode() throws RemoteException;

    IWindowId getWindowId(IBinder iBinder) throws RemoteException;

    void grantEmbeddedWindowFocus(IWindow iWindow, IBinder iBinder, boolean z) throws RemoteException;

    void grantInputChannel(int i, SurfaceControl surfaceControl, IWindow iWindow, IBinder iBinder, int i2, int i3, int i4, InputChannel inputChannel) throws RemoteException;

    void insetsModified(IWindow iWindow, InsetsState insetsState) throws RemoteException;

    void onRectangleOnScreenRequested(IBinder iBinder, Rect rect) throws RemoteException;

    boolean outOfMemory(IWindow iWindow) throws RemoteException;

    IBinder performDrag(IWindow iWindow, int i, SurfaceControl surfaceControl, int i2, float f, float f2, float f3, float f4, ClipData clipData) throws RemoteException;

    boolean performHapticFeedback(int i, boolean z) throws RemoteException;

    void pokeDrawLock(IBinder iBinder) throws RemoteException;

    void prepareToReplaceWindows(IBinder iBinder, boolean z) throws RemoteException;

    int relayout(IWindow iWindow, WindowManager.LayoutParams layoutParams, int i, int i2, int i3, int i4, long j, ClientWindowFrames clientWindowFrames, MergedConfiguration mergedConfiguration, SurfaceControl surfaceControl, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr, Point point) throws RemoteException;

    void remove(IWindow iWindow) throws RemoteException;

    void reportDropResult(IWindow iWindow, boolean z) throws RemoteException;

    void reportSystemGestureExclusionChanged(IWindow iWindow, List<Rect> list) throws RemoteException;

    Bundle sendWallpaperCommand(IBinder iBinder, String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException;

    void setInTouchMode(boolean z) throws RemoteException;

    void setInsets(IWindow iWindow, int i, Rect rect, Rect rect2, Region region) throws RemoteException;

    void setShouldZoomOutWallpaper(IBinder iBinder, boolean z) throws RemoteException;

    void setWallpaperDisplayOffset(IBinder iBinder, int i, int i2) throws RemoteException;

    void setWallpaperPosition(IBinder iBinder, float f, float f2, float f3, float f4) throws RemoteException;

    void setWallpaperZoomOut(IBinder iBinder, float f) throws RemoteException;

    boolean startMovingTask(IWindow iWindow, float f, float f2) throws RemoteException;

    void updateDisplayContentLocation(IWindow iWindow, int i, int i2, int i3) throws RemoteException;

    void updateInputChannel(IBinder iBinder, int i, SurfaceControl surfaceControl, int i2, int i3, Region region) throws RemoteException;

    void updatePointerIcon(IWindow iWindow) throws RemoteException;

    void updateTapExcludeRegion(IWindow iWindow, Region region) throws RemoteException;

    void wallpaperCommandComplete(IBinder iBinder, Bundle bundle) throws RemoteException;

    void wallpaperOffsetsComplete(IBinder iBinder) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowSession {
        @Override // android.view.IWindowSession
        public int addToDisplay(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, InsetsState requestedVisibility, InputChannel outInputChannel, InsetsState insetsState, InsetsSourceControl[] activeControls) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public int addToDisplayAsUser(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, int userId, InsetsState requestedVisibility, InputChannel outInputChannel, InsetsState insetsState, InsetsSourceControl[] activeControls) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public int addToDisplayWithoutInputChannel(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, InsetsState insetsState) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public void remove(IWindow window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public int relayout(IWindow window, WindowManager.LayoutParams attrs, int requestedWidth, int requestedHeight, int viewVisibility, int flags, long frameNumber, ClientWindowFrames outFrames, MergedConfiguration outMergedConfiguration, SurfaceControl outSurfaceControl, InsetsState insetsState, InsetsSourceControl[] activeControls, Point outSurfaceSize) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public void prepareToReplaceWindows(IBinder appToken, boolean childrenOnly) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public boolean outOfMemory(IWindow window) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public void setInsets(IWindow window, int touchableInsets, Rect contentInsets, Rect visibleInsets, Region touchableRegion) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void finishDrawing(IWindow window, SurfaceControl.Transaction postDrawTransaction) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setInTouchMode(boolean showFocus) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public boolean getInTouchMode() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public boolean performHapticFeedback(int effectId, boolean always) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public IBinder performDrag(IWindow window, int flags, SurfaceControl surface, int touchSource, float touchX, float touchY, float thumbCenterX, float thumbCenterY, ClipData data) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowSession
        public void reportDropResult(IWindow window, boolean consumed) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void cancelDragAndDrop(IBinder dragToken, boolean skipAnimation) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void dragRecipientEntered(IWindow window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void dragRecipientExited(IWindow window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setWallpaperPosition(IBinder windowToken, float x, float y, float xstep, float ystep) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setWallpaperZoomOut(IBinder windowToken, float scale) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setShouldZoomOutWallpaper(IBinder windowToken, boolean shouldZoom) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void wallpaperOffsetsComplete(IBinder window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setWallpaperDisplayOffset(IBinder windowToken, int x, int y) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public Bundle sendWallpaperCommand(IBinder window, String action, int x, int y, int z, Bundle extras, boolean sync) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowSession
        public void wallpaperCommandComplete(IBinder window, Bundle result) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void onRectangleOnScreenRequested(IBinder token, Rect rectangle) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public IWindowId getWindowId(IBinder window) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowSession
        public void pokeDrawLock(IBinder window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public boolean startMovingTask(IWindow window, float startX, float startY) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public void finishMovingTask(IWindow window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updatePointerIcon(IWindow window) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updateDisplayContentLocation(IWindow window, int x, int y, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updateTapExcludeRegion(IWindow window, Region region) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void insetsModified(IWindow window, InsetsState state) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void reportSystemGestureExclusionChanged(IWindow window, List<Rect> exclusionRects) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void grantInputChannel(int displayId, SurfaceControl surface, IWindow window, IBinder hostInputToken, int flags, int privateFlags, int type, InputChannel outInputChannel) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updateInputChannel(IBinder channelToken, int displayId, SurfaceControl surface, int flags, int privateFlags, Region region) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void grantEmbeddedWindowFocus(IWindow window, IBinder inputToken, boolean grantFocus) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void generateDisplayHash(IWindow window, Rect boundsInWindow, String hashAlgorithm, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowSession {
        public static final String DESCRIPTOR = "android.view.IWindowSession";
        static final int TRANSACTION_addToDisplay = 1;
        static final int TRANSACTION_addToDisplayAsUser = 2;
        static final int TRANSACTION_addToDisplayWithoutInputChannel = 3;
        static final int TRANSACTION_cancelDragAndDrop = 15;
        static final int TRANSACTION_dragRecipientEntered = 16;
        static final int TRANSACTION_dragRecipientExited = 17;
        static final int TRANSACTION_finishDrawing = 9;
        static final int TRANSACTION_finishMovingTask = 29;
        static final int TRANSACTION_generateDisplayHash = 38;
        static final int TRANSACTION_getInTouchMode = 11;
        static final int TRANSACTION_getWindowId = 26;
        static final int TRANSACTION_grantEmbeddedWindowFocus = 37;
        static final int TRANSACTION_grantInputChannel = 35;
        static final int TRANSACTION_insetsModified = 33;
        static final int TRANSACTION_onRectangleOnScreenRequested = 25;
        static final int TRANSACTION_outOfMemory = 7;
        static final int TRANSACTION_performDrag = 13;
        static final int TRANSACTION_performHapticFeedback = 12;
        static final int TRANSACTION_pokeDrawLock = 27;
        static final int TRANSACTION_prepareToReplaceWindows = 6;
        static final int TRANSACTION_relayout = 5;
        static final int TRANSACTION_remove = 4;
        static final int TRANSACTION_reportDropResult = 14;
        static final int TRANSACTION_reportSystemGestureExclusionChanged = 34;
        static final int TRANSACTION_sendWallpaperCommand = 23;
        static final int TRANSACTION_setInTouchMode = 10;
        static final int TRANSACTION_setInsets = 8;
        static final int TRANSACTION_setShouldZoomOutWallpaper = 20;
        static final int TRANSACTION_setWallpaperDisplayOffset = 22;
        static final int TRANSACTION_setWallpaperPosition = 18;
        static final int TRANSACTION_setWallpaperZoomOut = 19;
        static final int TRANSACTION_startMovingTask = 28;
        static final int TRANSACTION_updateDisplayContentLocation = 31;
        static final int TRANSACTION_updateInputChannel = 36;
        static final int TRANSACTION_updatePointerIcon = 30;
        static final int TRANSACTION_updateTapExcludeRegion = 32;
        static final int TRANSACTION_wallpaperCommandComplete = 24;
        static final int TRANSACTION_wallpaperOffsetsComplete = 21;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowSession)) {
                return new Proxy(obj);
            }
            return (IWindowSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addToDisplay";
                case 2:
                    return "addToDisplayAsUser";
                case 3:
                    return "addToDisplayWithoutInputChannel";
                case 4:
                    return "remove";
                case 5:
                    return "relayout";
                case 6:
                    return "prepareToReplaceWindows";
                case 7:
                    return "outOfMemory";
                case 8:
                    return "setInsets";
                case 9:
                    return "finishDrawing";
                case 10:
                    return "setInTouchMode";
                case 11:
                    return "getInTouchMode";
                case 12:
                    return "performHapticFeedback";
                case 13:
                    return "performDrag";
                case 14:
                    return "reportDropResult";
                case 15:
                    return "cancelDragAndDrop";
                case 16:
                    return "dragRecipientEntered";
                case 17:
                    return "dragRecipientExited";
                case 18:
                    return "setWallpaperPosition";
                case 19:
                    return "setWallpaperZoomOut";
                case 20:
                    return "setShouldZoomOutWallpaper";
                case 21:
                    return "wallpaperOffsetsComplete";
                case 22:
                    return "setWallpaperDisplayOffset";
                case 23:
                    return "sendWallpaperCommand";
                case 24:
                    return "wallpaperCommandComplete";
                case 25:
                    return "onRectangleOnScreenRequested";
                case 26:
                    return "getWindowId";
                case 27:
                    return "pokeDrawLock";
                case 28:
                    return "startMovingTask";
                case 29:
                    return "finishMovingTask";
                case 30:
                    return "updatePointerIcon";
                case 31:
                    return "updateDisplayContentLocation";
                case 32:
                    return "updateTapExcludeRegion";
                case 33:
                    return "insetsModified";
                case 34:
                    return "reportSystemGestureExclusionChanged";
                case 35:
                    return "grantInputChannel";
                case 36:
                    return "updateInputChannel";
                case 37:
                    return "grantEmbeddedWindowFocus";
                case 38:
                    return "generateDisplayHash";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            WindowManager.LayoutParams _arg1;
            InsetsState _arg4;
            InsetsSourceControl[] _arg7;
            WindowManager.LayoutParams _arg12;
            InsetsState _arg5;
            InsetsSourceControl[] _arg8;
            WindowManager.LayoutParams _arg13;
            WindowManager.LayoutParams _arg14;
            InsetsSourceControl[] _arg11;
            Rect _arg2;
            Rect _arg3;
            Region _arg42;
            SurfaceControl.Transaction _arg15;
            SurfaceControl _arg22;
            ClipData _arg82;
            Bundle _arg52;
            boolean _arg6;
            Bundle _arg16;
            Rect _arg17;
            Region _arg18;
            InsetsState _arg19;
            SurfaceControl _arg110;
            SurfaceControl _arg23;
            Region _arg53;
            Rect _arg111;
            RemoteCallback _arg32;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg24 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg0 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = WindowManager.LayoutParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg25 = data.readInt();
                            int _arg33 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            InputChannel _arg54 = new InputChannel();
                            InsetsState _arg62 = new InsetsState();
                            int _arg7_length = data.readInt();
                            if (_arg7_length < 0) {
                                _arg7 = null;
                            } else {
                                _arg7 = new InsetsSourceControl[_arg7_length];
                            }
                            int _result = addToDisplay(_arg0, _arg1, _arg25, _arg33, _arg4, _arg54, _arg62, _arg7);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            reply.writeInt(1);
                            _arg54.writeToParcel(reply, 1);
                            reply.writeInt(1);
                            _arg62.writeToParcel(reply, 1);
                            reply.writeTypedArray(_arg7, 1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg02 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = WindowManager.LayoutParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            int _arg26 = data.readInt();
                            int _arg34 = data.readInt();
                            int _arg43 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg5 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            InputChannel _arg63 = new InputChannel();
                            InsetsState _arg72 = new InsetsState();
                            int _arg8_length = data.readInt();
                            if (_arg8_length < 0) {
                                _arg8 = null;
                            } else {
                                _arg8 = new InsetsSourceControl[_arg8_length];
                            }
                            int _result2 = addToDisplayAsUser(_arg02, _arg12, _arg26, _arg34, _arg43, _arg5, _arg63, _arg72, _arg8);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            reply.writeInt(1);
                            _arg63.writeToParcel(reply, 1);
                            reply.writeInt(1);
                            _arg72.writeToParcel(reply, 1);
                            reply.writeTypedArray(_arg8, 1);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg03 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg13 = WindowManager.LayoutParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            int _arg27 = data.readInt();
                            int _arg35 = data.readInt();
                            InsetsState _arg44 = new InsetsState();
                            int _result3 = addToDisplayWithoutInputChannel(_arg03, _arg13, _arg27, _arg35, _arg44);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            reply.writeInt(1);
                            _arg44.writeToParcel(reply, 1);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg04 = IWindow.Stub.asInterface(data.readStrongBinder());
                            remove(_arg04);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg05 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg14 = WindowManager.LayoutParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            int _arg28 = data.readInt();
                            int _arg36 = data.readInt();
                            int _arg45 = data.readInt();
                            int _arg55 = data.readInt();
                            long _arg64 = data.readLong();
                            ClientWindowFrames _arg73 = new ClientWindowFrames();
                            MergedConfiguration _arg83 = new MergedConfiguration();
                            SurfaceControl _arg9 = new SurfaceControl();
                            InsetsState _arg10 = new InsetsState();
                            int _arg11_length = data.readInt();
                            if (_arg11_length < 0) {
                                _arg11 = null;
                            } else {
                                _arg11 = new InsetsSourceControl[_arg11_length];
                            }
                            Point _arg122 = new Point();
                            int _result4 = relayout(_arg05, _arg14, _arg28, _arg36, _arg45, _arg55, _arg64, _arg73, _arg83, _arg9, _arg10, _arg11, _arg122);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            reply.writeInt(1);
                            _arg73.writeToParcel(reply, 1);
                            reply.writeInt(1);
                            _arg83.writeToParcel(reply, 1);
                            reply.writeInt(1);
                            _arg9.writeToParcel(reply, 1);
                            reply.writeInt(1);
                            _arg10.writeToParcel(reply, 1);
                            reply.writeTypedArray(_arg11, 1);
                            reply.writeInt(1);
                            _arg122.writeToParcel(reply, 1);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg06 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            prepareToReplaceWindows(_arg06, _arg24);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg07 = IWindow.Stub.asInterface(data.readStrongBinder());
                            boolean outOfMemory = outOfMemory(_arg07);
                            reply.writeNoException();
                            reply.writeInt(outOfMemory ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg08 = IWindow.Stub.asInterface(data.readStrongBinder());
                            int _arg112 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg42 = Region.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            setInsets(_arg08, _arg112, _arg2, _arg3, _arg42);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg09 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg15 = SurfaceControl.Transaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            finishDrawing(_arg09, _arg15);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            setInTouchMode(_arg24);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            boolean inTouchMode = getInTouchMode();
                            reply.writeNoException();
                            reply.writeInt(inTouchMode ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            boolean performHapticFeedback = performHapticFeedback(_arg010, _arg24);
                            reply.writeNoException();
                            reply.writeInt(performHapticFeedback ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg011 = IWindow.Stub.asInterface(data.readStrongBinder());
                            int _arg113 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            int _arg37 = data.readInt();
                            float _arg46 = data.readFloat();
                            float _arg56 = data.readFloat();
                            float _arg65 = data.readFloat();
                            float _arg74 = data.readFloat();
                            if (data.readInt() != 0) {
                                _arg82 = ClipData.CREATOR.createFromParcel(data);
                            } else {
                                _arg82 = null;
                            }
                            IBinder _result5 = performDrag(_arg011, _arg113, _arg22, _arg37, _arg46, _arg56, _arg65, _arg74, _arg82);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result5);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg012 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            reportDropResult(_arg012, _arg24);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg013 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            cancelDragAndDrop(_arg013, _arg24);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg014 = IWindow.Stub.asInterface(data.readStrongBinder());
                            dragRecipientEntered(_arg014);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg015 = IWindow.Stub.asInterface(data.readStrongBinder());
                            dragRecipientExited(_arg015);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg016 = data.readStrongBinder();
                            float _arg114 = data.readFloat();
                            float _arg29 = data.readFloat();
                            float _arg38 = data.readFloat();
                            float _arg47 = data.readFloat();
                            setWallpaperPosition(_arg016, _arg114, _arg29, _arg38, _arg47);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg017 = data.readStrongBinder();
                            float _arg115 = data.readFloat();
                            setWallpaperZoomOut(_arg017, _arg115);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg018 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            setShouldZoomOutWallpaper(_arg018, _arg24);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg019 = data.readStrongBinder();
                            wallpaperOffsetsComplete(_arg019);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg020 = data.readStrongBinder();
                            int _arg116 = data.readInt();
                            setWallpaperDisplayOffset(_arg020, _arg116, data.readInt());
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg021 = data.readStrongBinder();
                            String _arg117 = data.readString();
                            int _arg210 = data.readInt();
                            int _arg39 = data.readInt();
                            int _arg48 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg52 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg52 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg6 = true;
                            } else {
                                _arg6 = false;
                            }
                            Bundle _result6 = sendWallpaperCommand(_arg021, _arg117, _arg210, _arg39, _arg48, _arg52, _arg6);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg022 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg16 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            wallpaperCommandComplete(_arg022, _arg16);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg023 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg17 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            onRectangleOnScreenRequested(_arg023, _arg17);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg024 = data.readStrongBinder();
                            IWindowId _result7 = getWindowId(_arg024);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result7 != null ? _result7.asBinder() : null);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg025 = data.readStrongBinder();
                            pokeDrawLock(_arg025);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg026 = IWindow.Stub.asInterface(data.readStrongBinder());
                            float _arg118 = data.readFloat();
                            boolean startMovingTask = startMovingTask(_arg026, _arg118, data.readFloat());
                            reply.writeNoException();
                            reply.writeInt(startMovingTask ? 1 : 0);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg027 = IWindow.Stub.asInterface(data.readStrongBinder());
                            finishMovingTask(_arg027);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg028 = IWindow.Stub.asInterface(data.readStrongBinder());
                            updatePointerIcon(_arg028);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg029 = IWindow.Stub.asInterface(data.readStrongBinder());
                            int _arg119 = data.readInt();
                            int _arg211 = data.readInt();
                            int _arg310 = data.readInt();
                            updateDisplayContentLocation(_arg029, _arg119, _arg211, _arg310);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg030 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg18 = Region.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            updateTapExcludeRegion(_arg030, _arg18);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg031 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg19 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            insetsModified(_arg031, _arg19);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg032 = IWindow.Stub.asInterface(data.readStrongBinder());
                            List<Rect> _arg120 = data.createTypedArrayList(Rect.CREATOR);
                            reportSystemGestureExclusionChanged(_arg032, _arg120);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg033 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg110 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            IWindow _arg212 = IWindow.Stub.asInterface(data.readStrongBinder());
                            IBinder _arg311 = data.readStrongBinder();
                            int _arg49 = data.readInt();
                            int _arg57 = data.readInt();
                            int _arg66 = data.readInt();
                            InputChannel _arg75 = new InputChannel();
                            grantInputChannel(_arg033, _arg110, _arg212, _arg311, _arg49, _arg57, _arg66, _arg75);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg75.writeToParcel(reply, 1);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg034 = data.readStrongBinder();
                            int _arg121 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg23 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            int _arg312 = data.readInt();
                            int _arg410 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg53 = Region.CREATOR.createFromParcel(data);
                            } else {
                                _arg53 = null;
                            }
                            updateInputChannel(_arg034, _arg121, _arg23, _arg312, _arg410, _arg53);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg035 = IWindow.Stub.asInterface(data.readStrongBinder());
                            IBinder _arg123 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg24 = true;
                            }
                            grantEmbeddedWindowFocus(_arg035, _arg123, _arg24);
                            reply.writeNoException();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            IWindow _arg036 = IWindow.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg111 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            String _arg213 = data.readString();
                            if (data.readInt() != 0) {
                                _arg32 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            generateDisplayHash(_arg036, _arg111, _arg213, _arg32);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowSession {
            public static IWindowSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.view.IWindowSession
            public int addToDisplay(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, InsetsState requestedVisibility, InputChannel outInputChannel, InsetsState insetsState, InsetsSourceControl[] activeControls) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (attrs != null) {
                        _data.writeInt(1);
                        attrs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(viewVisibility);
                        _data.writeInt(layerStackId);
                        if (requestedVisibility != null) {
                            _data.writeInt(1);
                            requestedVisibility.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (activeControls == null) {
                            _data.writeInt(-1);
                        } else {
                            _data.writeInt(activeControls.length);
                        }
                        boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            int _result = _reply.readInt();
                            if (_reply.readInt() != 0) {
                                try {
                                    outInputChannel.readFromParcel(_reply);
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            }
                            if (_reply.readInt() != 0) {
                                try {
                                    insetsState.readFromParcel(_reply);
                                } catch (Throwable th3) {
                                    th = th3;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            }
                            _reply.readTypedArray(activeControls, InsetsSourceControl.CREATOR);
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        int addToDisplay = Stub.getDefaultImpl().addToDisplay(window, attrs, viewVisibility, layerStackId, requestedVisibility, outInputChannel, insetsState, activeControls);
                        _reply.recycle();
                        _data.recycle();
                        return addToDisplay;
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.view.IWindowSession
            public int addToDisplayAsUser(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, int userId, InsetsState requestedVisibility, InputChannel outInputChannel, InsetsState insetsState, InsetsSourceControl[] activeControls) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (attrs != null) {
                        _data.writeInt(1);
                        attrs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(viewVisibility);
                    _data.writeInt(layerStackId);
                    _data.writeInt(userId);
                    if (requestedVisibility != null) {
                        _data.writeInt(1);
                        requestedVisibility.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (activeControls == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(activeControls.length);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        if (_reply.readInt() != 0) {
                            try {
                                outInputChannel.readFromParcel(_reply);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        }
                        if (_reply.readInt() != 0) {
                            try {
                                insetsState.readFromParcel(_reply);
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        }
                        _reply.readTypedArray(activeControls, InsetsSourceControl.CREATOR);
                        _reply.recycle();
                        _data.recycle();
                        return _result;
                    }
                    int addToDisplayAsUser = Stub.getDefaultImpl().addToDisplayAsUser(window, attrs, viewVisibility, layerStackId, userId, requestedVisibility, outInputChannel, insetsState, activeControls);
                    _reply.recycle();
                    _data.recycle();
                    return addToDisplayAsUser;
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.view.IWindowSession
            public int addToDisplayWithoutInputChannel(IWindow window, WindowManager.LayoutParams attrs, int viewVisibility, int layerStackId, InsetsState insetsState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (attrs != null) {
                        _data.writeInt(1);
                        attrs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(viewVisibility);
                    _data.writeInt(layerStackId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addToDisplayWithoutInputChannel(window, attrs, viewVisibility, layerStackId, insetsState);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    if (_reply.readInt() != 0) {
                        insetsState.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void remove(IWindow window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().remove(window);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public int relayout(IWindow window, WindowManager.LayoutParams attrs, int requestedWidth, int requestedHeight, int viewVisibility, int flags, long frameNumber, ClientWindowFrames outFrames, MergedConfiguration outMergedConfiguration, SurfaceControl outSurfaceControl, InsetsState insetsState, InsetsSourceControl[] activeControls, Point outSurfaceSize) throws RemoteException {
                Parcel _data;
                Parcel _reply;
                Throwable th;
                IBinder asBinder;
                Parcel _reply2;
                Parcel _data2 = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data2.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (window != null) {
                        try {
                            asBinder = window.asBinder();
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _data = _data2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        asBinder = null;
                    }
                    _data2.writeStrongBinder(asBinder);
                    if (attrs != null) {
                        _data2.writeInt(1);
                        attrs.writeToParcel(_data2, 0);
                    } else {
                        _data2.writeInt(0);
                    }
                    _data2.writeInt(requestedWidth);
                    _data2.writeInt(requestedHeight);
                    _data2.writeInt(viewVisibility);
                    _data2.writeInt(flags);
                    _data2.writeLong(frameNumber);
                    if (activeControls == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(activeControls.length);
                    }
                    boolean _status = this.mRemote.transact(5, _data2, _reply3, 0);
                    if (!_status) {
                        try {
                            if (Stub.getDefaultImpl() != null) {
                                _data = _data2;
                                try {
                                    int relayout = Stub.getDefaultImpl().relayout(window, attrs, requestedWidth, requestedHeight, viewVisibility, flags, frameNumber, outFrames, outMergedConfiguration, outSurfaceControl, insetsState, activeControls, outSurfaceSize);
                                    _reply3.recycle();
                                    _data.recycle();
                                    return relayout;
                                } catch (Throwable th3) {
                                    th = th3;
                                    _reply = _reply3;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } else {
                                _reply2 = _reply3;
                                _data = _data2;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _data = _data2;
                            _reply = _reply3;
                        }
                    } else {
                        _reply2 = _reply3;
                        _data = _data2;
                    }
                    try {
                        _reply2.readException();
                        int _result = _reply2.readInt();
                        if (_reply2.readInt() != 0) {
                            _reply = _reply2;
                            try {
                                outFrames.readFromParcel(_reply);
                            } catch (Throwable th5) {
                                th = th5;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } else {
                            _reply = _reply2;
                        }
                        if (_reply.readInt() != 0) {
                            try {
                                outMergedConfiguration.readFromParcel(_reply);
                            } catch (Throwable th6) {
                                th = th6;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        }
                        if (_reply.readInt() != 0) {
                            try {
                                outSurfaceControl.readFromParcel(_reply);
                            } catch (Throwable th7) {
                                th = th7;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        }
                        if (_reply.readInt() != 0) {
                            try {
                                insetsState.readFromParcel(_reply);
                            } catch (Throwable th8) {
                                th = th8;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        }
                        try {
                            _reply.readTypedArray(activeControls, InsetsSourceControl.CREATOR);
                            if (_reply.readInt() != 0) {
                                try {
                                    outSurfaceSize.readFromParcel(_reply);
                                } catch (Throwable th9) {
                                    th = th9;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            }
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        } catch (Throwable th10) {
                            th = th10;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th11) {
                        th = th11;
                        _reply = _reply2;
                    }
                } catch (Throwable th12) {
                    th = th12;
                    _reply = _reply3;
                    _data = _data2;
                }
            }

            @Override // android.view.IWindowSession
            public void prepareToReplaceWindows(IBinder appToken, boolean childrenOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(appToken);
                    _data.writeInt(childrenOnly ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareToReplaceWindows(appToken, childrenOnly);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean outOfMemory(IWindow window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().outOfMemory(window);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setInsets(IWindow window, int touchableInsets, Rect contentInsets, Rect visibleInsets, Region touchableRegion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    _data.writeInt(touchableInsets);
                    if (contentInsets != null) {
                        _data.writeInt(1);
                        contentInsets.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (visibleInsets != null) {
                        _data.writeInt(1);
                        visibleInsets.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (touchableRegion != null) {
                        _data.writeInt(1);
                        touchableRegion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInsets(window, touchableInsets, contentInsets, visibleInsets, touchableRegion);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void finishDrawing(IWindow window, SurfaceControl.Transaction postDrawTransaction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (postDrawTransaction != null) {
                        _data.writeInt(1);
                        postDrawTransaction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishDrawing(window, postDrawTransaction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setInTouchMode(boolean showFocus) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(showFocus ? 1 : 0);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInTouchMode(showFocus);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean getInTouchMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInTouchMode();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean performHapticFeedback(int effectId, boolean always) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(effectId);
                    boolean _result = true;
                    _data.writeInt(always ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().performHapticFeedback(effectId, always);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public IBinder performDrag(IWindow window, int flags, SurfaceControl surface, int touchSource, float touchX, float touchY, float thumbCenterX, float thumbCenterY, ClipData data) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    try {
                        _data.writeInt(flags);
                        if (surface != null) {
                            _data.writeInt(1);
                            surface.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeInt(touchSource);
                        _data.writeFloat(touchX);
                        _data.writeFloat(touchY);
                        _data.writeFloat(thumbCenterX);
                        _data.writeFloat(thumbCenterY);
                        if (data != null) {
                            _data.writeInt(1);
                            data.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            IBinder _result = _reply.readStrongBinder();
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        IBinder performDrag = Stub.getDefaultImpl().performDrag(window, flags, surface, touchSource, touchX, touchY, thumbCenterX, thumbCenterY, data);
                        _reply.recycle();
                        _data.recycle();
                        return performDrag;
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // android.view.IWindowSession
            public void reportDropResult(IWindow window, boolean consumed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    _data.writeInt(consumed ? 1 : 0);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportDropResult(window, consumed);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void cancelDragAndDrop(IBinder dragToken, boolean skipAnimation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(dragToken);
                    _data.writeInt(skipAnimation ? 1 : 0);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelDragAndDrop(dragToken, skipAnimation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void dragRecipientEntered(IWindow window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dragRecipientEntered(window);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void dragRecipientExited(IWindow window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dragRecipientExited(window);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setWallpaperPosition(IBinder windowToken, float x, float y, float xstep, float ystep) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(windowToken);
                    _data.writeFloat(x);
                    _data.writeFloat(y);
                    _data.writeFloat(xstep);
                    _data.writeFloat(ystep);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWallpaperPosition(windowToken, x, y, xstep, ystep);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setWallpaperZoomOut(IBinder windowToken, float scale) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(windowToken);
                    _data.writeFloat(scale);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWallpaperZoomOut(windowToken, scale);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setShouldZoomOutWallpaper(IBinder windowToken, boolean shouldZoom) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(windowToken);
                    _data.writeInt(shouldZoom ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShouldZoomOutWallpaper(windowToken, shouldZoom);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void wallpaperOffsetsComplete(IBinder window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wallpaperOffsetsComplete(window);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setWallpaperDisplayOffset(IBinder windowToken, int x, int y) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(windowToken);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWallpaperDisplayOffset(windowToken, x, y);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public Bundle sendWallpaperCommand(IBinder window, String action, int x, int y, int z, Bundle extras, boolean sync) throws RemoteException {
                Throwable th;
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(window);
                        try {
                            _data.writeString(action);
                            try {
                                _data.writeInt(x);
                                try {
                                    _data.writeInt(y);
                                    _data.writeInt(z);
                                    int i = 1;
                                    if (extras != null) {
                                        _data.writeInt(1);
                                        extras.writeToParcel(_data, 0);
                                    } else {
                                        _data.writeInt(0);
                                    }
                                    if (!sync) {
                                        i = 0;
                                    }
                                    _data.writeInt(i);
                                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = Bundle.CREATOR.createFromParcel(_reply);
                                        } else {
                                            _result = null;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    Bundle sendWallpaperCommand = Stub.getDefaultImpl().sendWallpaperCommand(window, action, x, y, z, extras, sync);
                                    _reply.recycle();
                                    _data.recycle();
                                    return sendWallpaperCommand;
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.view.IWindowSession
            public void wallpaperCommandComplete(IBinder window, Bundle result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wallpaperCommandComplete(window, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void onRectangleOnScreenRequested(IBinder token, Rect rectangle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (rectangle != null) {
                        _data.writeInt(1);
                        rectangle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRectangleOnScreenRequested(token, rectangle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public IWindowId getWindowId(IBinder window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowId(window);
                    }
                    _reply.readException();
                    IWindowId _result = IWindowId.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void pokeDrawLock(IBinder window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().pokeDrawLock(window);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean startMovingTask(IWindow window, float startX, float startY) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    _data.writeFloat(startX);
                    _data.writeFloat(startY);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startMovingTask(window, startX, startY);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void finishMovingTask(IWindow window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    boolean _status = this.mRemote.transact(29, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishMovingTask(window);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void updatePointerIcon(IWindow window) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePointerIcon(window);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void updateDisplayContentLocation(IWindow window, int x, int y, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(31, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateDisplayContentLocation(window, x, y, displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void updateTapExcludeRegion(IWindow window, Region region) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (region != null) {
                        _data.writeInt(1);
                        region.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTapExcludeRegion(window, region);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void insetsModified(IWindow window, InsetsState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().insetsModified(window, state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void reportSystemGestureExclusionChanged(IWindow window, List<Rect> exclusionRects) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    _data.writeTypedList(exclusionRects);
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportSystemGestureExclusionChanged(window, exclusionRects);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void grantInputChannel(int displayId, SurfaceControl surface, IWindow window, IBinder hostInputToken, int flags, int privateFlags, int type, InputChannel outInputChannel) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(displayId);
                        if (surface != null) {
                            _data.writeInt(1);
                            surface.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeStrongBinder(hostInputToken);
                    try {
                        _data.writeInt(flags);
                        _data.writeInt(privateFlags);
                        _data.writeInt(type);
                        boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            if (_reply.readInt() != 0) {
                                try {
                                    outInputChannel.readFromParcel(_reply);
                                } catch (Throwable th4) {
                                    th = th4;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            }
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().grantInputChannel(displayId, surface, window, hostInputToken, flags, privateFlags, type, outInputChannel);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindowSession
            public void updateInputChannel(IBinder channelToken, int displayId, SurfaceControl surface, int flags, int privateFlags, Region region) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(channelToken);
                        try {
                            _data.writeInt(displayId);
                            if (surface != null) {
                                _data.writeInt(1);
                                surface.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            try {
                                _data.writeInt(flags);
                                try {
                                    _data.writeInt(privateFlags);
                                    if (region != null) {
                                        _data.writeInt(1);
                                        region.writeToParcel(_data, 0);
                                    } else {
                                        _data.writeInt(0);
                                    }
                                    try {
                                        boolean _status = this.mRemote.transact(36, _data, null, 1);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _data.recycle();
                                            return;
                                        }
                                        Stub.getDefaultImpl().updateInputChannel(channelToken, displayId, surface, flags, privateFlags, region);
                                        _data.recycle();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        _data.recycle();
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.view.IWindowSession
            public void grantEmbeddedWindowFocus(IWindow window, IBinder inputToken, boolean grantFocus) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    _data.writeStrongBinder(inputToken);
                    _data.writeInt(grantFocus ? 1 : 0);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().grantEmbeddedWindowFocus(window, inputToken, grantFocus);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void generateDisplayHash(IWindow window, Rect boundsInWindow, String hashAlgorithm, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(window != null ? window.asBinder() : null);
                    if (boundsInWindow != null) {
                        _data.writeInt(1);
                        boundsInWindow.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(hashAlgorithm);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().generateDisplayHash(window, boundsInWindow, hashAlgorithm, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
