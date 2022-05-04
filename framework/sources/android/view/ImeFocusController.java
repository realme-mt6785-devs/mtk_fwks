package android.view;

import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.inputmethod.InputMethodDebug;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class ImeFocusController {
    private static boolean DEBUG = false;
    private static final String TAG = "ImeFocusController";
    private InputMethodManagerDelegate mDelegate;
    private boolean mHasImeFocus = false;
    private View mNextServedView;
    private View mServedView;
    private final ViewRootImpl mViewRootImpl;

    /* loaded from: classes3.dex */
    public interface InputMethodManagerDelegate {
        void closeCurrentIme();

        void finishComposingText();

        void finishInput();

        void finishInputAndReportToIme();

        boolean hasActiveConnection(View view);

        boolean isCurrentRootView(ViewRootImpl viewRootImpl);

        boolean isRestartOnNextWindowFocus(boolean z);

        void setCurrentRootView(ViewRootImpl viewRootImpl);

        boolean startInput(int i, View view, int i2, int i3, int i4);

        void startInputAsyncOnWindowFocusGain(View view, int i, int i2, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImeFocusController(ViewRootImpl viewRootImpl) {
        this.mViewRootImpl = viewRootImpl;
    }

    private InputMethodManagerDelegate getImmDelegate() {
        InputMethodManagerDelegate delegate = this.mDelegate;
        if (delegate != null) {
            return delegate;
        }
        InputMethodManagerDelegate delegate2 = ((InputMethodManager) this.mViewRootImpl.mContext.getSystemService(InputMethodManager.class)).getDelegate();
        this.mDelegate = delegate2;
        return delegate2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onMovedToDisplay() {
        this.mDelegate = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onTraversal(boolean hasWindowFocus, WindowManager.LayoutParams windowAttribute) {
        boolean hasImeFocus = updateImeFocusable(windowAttribute, false);
        if (hasWindowFocus && !isInLocalFocusMode(windowAttribute) && hasImeFocus != this.mHasImeFocus) {
            this.mHasImeFocus = hasImeFocus;
            if (DEBUG) {
                Log.v(TAG, "onTraversal hasImeFocus = " + hasImeFocus);
            }
            if (this.mHasImeFocus) {
                onPreWindowFocus(true, windowAttribute);
                onPostWindowFocus(this.mViewRootImpl.mView.findFocus(), true, windowAttribute);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPreWindowFocus(boolean hasWindowFocus, WindowManager.LayoutParams windowAttribute) {
        if (this.mHasImeFocus && !isInLocalFocusMode(windowAttribute)) {
            if (hasWindowFocus) {
                getImmDelegate().setCurrentRootView(this.mViewRootImpl);
            }
            if (DEBUG) {
                Log.v(TAG, "onPreWindowFocus hasWindowFocus = " + hasWindowFocus + " mViewRootImpl = " + this.mViewRootImpl);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean updateImeFocusable(WindowManager.LayoutParams windowAttribute, boolean force) {
        boolean hasImeFocus = WindowManager.LayoutParams.mayUseInputMethod(windowAttribute.flags);
        if (force) {
            this.mHasImeFocus = hasImeFocus;
            if (DEBUG) {
                Log.v(TAG, "updateImeFocusable hasImeFocus = " + hasImeFocus);
            }
        }
        return hasImeFocus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPostWindowFocus(View focusedView, boolean hasWindowFocus, WindowManager.LayoutParams windowAttribute) {
        if (hasWindowFocus && this.mHasImeFocus && !isInLocalFocusMode(windowAttribute)) {
            View viewForWindowFocus = focusedView != null ? focusedView : this.mViewRootImpl.mView;
            if (DEBUG) {
                Log.v(TAG, "onWindowFocus: " + viewForWindowFocus + " softInputMode=" + InputMethodDebug.softInputModeToString(windowAttribute.softInputMode));
            }
            boolean forceFocus = false;
            InputMethodManagerDelegate immDelegate = getImmDelegate();
            boolean nextFocusIsServedView = true;
            if (immDelegate.isRestartOnNextWindowFocus(true)) {
                if (DEBUG) {
                    Log.v(TAG, "Restarting due to isRestartOnNextWindowFocus as true");
                }
                forceFocus = true;
            }
            onViewFocusChanged(viewForWindowFocus, true);
            if (this.mServedView != viewForWindowFocus) {
                nextFocusIsServedView = false;
            }
            if (nextFocusIsServedView && !immDelegate.hasActiveConnection(viewForWindowFocus)) {
                forceFocus = true;
            }
            immDelegate.startInputAsyncOnWindowFocusGain(viewForWindowFocus, windowAttribute.softInputMode, windowAttribute.flags, forceFocus);
        }
    }

    public boolean checkFocus(boolean forceNewFocus, boolean startInput) {
        InputMethodManagerDelegate immDelegate = getImmDelegate();
        if (!immDelegate.isCurrentRootView(this.mViewRootImpl) || (this.mServedView == this.mNextServedView && !forceNewFocus)) {
            return false;
        }
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("checkFocus: view=");
            sb.append(this.mServedView);
            sb.append(" next=");
            sb.append(this.mNextServedView);
            sb.append(" force=");
            sb.append(forceNewFocus);
            sb.append(" package=");
            View view = this.mServedView;
            sb.append(view != null ? view.getContext().getPackageName() : "<none>");
            Log.v(TAG, sb.toString());
        }
        View view2 = this.mNextServedView;
        if (view2 == null) {
            immDelegate.finishInput();
            immDelegate.closeCurrentIme();
            return false;
        }
        this.mServedView = view2;
        immDelegate.finishComposingText();
        if (!startInput) {
            return true;
        }
        immDelegate.startInput(5, null, 0, 0, 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onViewFocusChanged(View view, boolean hasFocus) {
        if (view == null || view.isTemporarilyDetached()) {
            if (DEBUG) {
                Log.v(TAG, "onViewFocusChanged return, view = " + view);
            }
        } else if (!getImmDelegate().isCurrentRootView(view.getViewRootImpl())) {
            if (DEBUG) {
                Log.v(TAG, "onViewFocusChanged return, ViewRootImpl = " + view.getViewRootImpl());
            }
        } else if (view.hasImeFocus() && view.hasWindowFocus()) {
            if (DEBUG) {
                Log.d(TAG, "onViewFocusChanged, view=" + view + ", mServedView=" + this.mServedView);
            }
            if (hasFocus) {
                this.mNextServedView = view;
            }
            this.mViewRootImpl.dispatchCheckFocus();
        } else if (DEBUG) {
            Log.v(TAG, "onViewFocusChanged return, hasImeFocus = " + view.hasImeFocus() + " hasWindowFocus = " + view.hasWindowFocus());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onViewDetachedFromWindow(View view) {
        if (getImmDelegate().isCurrentRootView(view.getViewRootImpl()) && this.mServedView == view) {
            this.mNextServedView = null;
            this.mViewRootImpl.dispatchCheckFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onWindowDismissed() {
        InputMethodManagerDelegate immDelegate = getImmDelegate();
        if (immDelegate.isCurrentRootView(this.mViewRootImpl)) {
            if (this.mServedView != null) {
                immDelegate.finishInput();
            }
            immDelegate.setCurrentRootView(null);
            this.mHasImeFocus = false;
            if (DEBUG) {
                Log.v(TAG, "onWindowDismissed");
            }
        }
    }

    public void onInteractiveChanged(boolean interactive) {
        InputMethodManagerDelegate immDelegate = getImmDelegate();
        if (immDelegate.isCurrentRootView(this.mViewRootImpl)) {
            if (interactive) {
                View focusedView = this.mViewRootImpl.mView.findFocus();
                onViewFocusChanged(focusedView, focusedView != null);
                return;
            }
            this.mDelegate.finishInputAndReportToIme();
        }
    }

    private static boolean isInLocalFocusMode(WindowManager.LayoutParams windowAttribute) {
        return (windowAttribute.flags & 268435456) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onProcessImeInputStage(Object token, InputEvent event, WindowManager.LayoutParams windowAttribute, InputMethodManager.FinishedInputEventCallback callback) {
        InputMethodManager imm;
        if (!this.mHasImeFocus || isInLocalFocusMode(windowAttribute) || (imm = (InputMethodManager) this.mViewRootImpl.mContext.getSystemService(InputMethodManager.class)) == null) {
            return 0;
        }
        return imm.dispatchInputEvent(event, token, callback, this.mViewRootImpl.mHandler);
    }

    public View getServedView() {
        return this.mServedView;
    }

    public View getNextServedView() {
        return this.mNextServedView;
    }

    public void setServedView(View view) {
        this.mServedView = view;
    }

    public void setNextServedView(View view) {
        this.mNextServedView = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasImeFocus() {
        return this.mHasImeFocus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        long token = proto.start(fieldId);
        proto.write(1133871366145L, this.mHasImeFocus);
        proto.write(1138166333442L, Objects.toString(this.mServedView));
        proto.write(1138166333443L, Objects.toString(this.mNextServedView));
        proto.end(token);
    }
}
