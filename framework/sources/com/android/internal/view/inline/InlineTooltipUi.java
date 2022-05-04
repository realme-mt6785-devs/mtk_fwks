package com.android.internal.view.inline;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.util.Slog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.autofill.Helper;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.inline.InlineContentView;
import java.io.PrintWriter;
/* loaded from: classes4.dex */
public final class InlineTooltipUi extends PopupWindow implements AutoCloseable {
    private static final String TAG = "InlineTooltipUi";
    private final View.OnAttachStateChangeListener mAnchorOnAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.android.internal.view.inline.InlineTooltipUi.1
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View v) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View v) {
            InlineTooltipUi.this.dismiss();
        }
    };
    private final View.OnLayoutChangeListener mAnchoredOnLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.android.internal.view.inline.InlineTooltipUi.2
        int mHeight;

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (this.mHeight != bottom - top) {
                this.mHeight = bottom - top;
                InlineTooltipUi.this.adjustPosition();
            }
        }
    };
    private final ViewGroup mContentContainer;
    private boolean mShowing;
    private WindowManager.LayoutParams mWindowLayoutParams;
    private final WindowManager mWm;

    public InlineTooltipUi(Context context) {
        this.mContentContainer = new LinearLayout(new ContextWrapper(context));
        this.mWm = (WindowManager) context.getSystemService(WindowManager.class);
        setTouchModal(false);
        setOutsideTouchable(true);
        setInputMethodMode(2);
        setFocusable(false);
    }

    public void setTooltipView(InlineContentView v) {
        this.mContentContainer.removeAllViews();
        this.mContentContainer.addView(v);
        this.mContentContainer.setVisibility(0);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        hide();
    }

    @Override // android.widget.PopupWindow
    protected boolean hasContentView() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.PopupWindow
    public boolean hasDecorView() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.PopupWindow
    public WindowManager.LayoutParams getDecorViewLayoutParams() {
        return this.mWindowLayoutParams;
    }

    public void update(View anchor) {
        setWindowLayoutType(1005);
        int achoredHeight = this.mContentContainer.getHeight();
        int offsetY = achoredHeight == 0 ? (-anchor.getHeight()) << 1 : (-anchor.getHeight()) - achoredHeight;
        if (!isShowing()) {
            setWidth(-2);
            setHeight(-2);
            showAsDropDown(anchor, 0, offsetY, 49);
            return;
        }
        update(anchor, 0, offsetY, -2, -2);
    }

    @Override // android.widget.PopupWindow
    protected void update(View anchor, WindowManager.LayoutParams params) {
        if (anchor.isVisibleToUser()) {
            show(params);
        } else {
            hide();
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (!isShowing()) {
            setShowing(true);
            setDropDown(true);
            attachToAnchor(anchor, xoff, yoff, gravity);
            WindowManager.LayoutParams p = createPopupLayoutParams(anchor.getWindowToken());
            this.mWindowLayoutParams = p;
            boolean aboveAnchor = findDropDownPosition(anchor, p, xoff, yoff, p.width, p.height, gravity, getAllowScrollingAnchorParent());
            updateAboveAnchor(aboveAnchor);
            p.accessibilityIdOfAnchor = anchor.getAccessibilityViewId();
            p.packageName = anchor.getContext().getPackageName();
            show(p);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.PopupWindow
    public void attachToAnchor(View anchor, int xoff, int yoff, int gravity) {
        super.attachToAnchor(anchor, xoff, yoff, gravity);
        anchor.addOnAttachStateChangeListener(this.mAnchorOnAttachStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.PopupWindow
    public void detachFromAnchor() {
        View anchor = getAnchor();
        if (anchor != null) {
            anchor.removeOnAttachStateChangeListener(this.mAnchorOnAttachStateChangeListener);
        }
        super.detachFromAnchor();
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        if (isShowing() && !isTransitioningToDismiss()) {
            setShowing(false);
            setTransitioningToDismiss(true);
            hide();
            detachFromAnchor();
            if (getOnDismissListener() != null) {
                getOnDismissListener().onDismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustPosition() {
        View anchor = getAnchor();
        if (anchor != null) {
            update(anchor);
        }
    }

    private void show(WindowManager.LayoutParams params) {
        if (Helper.sVerbose) {
            Slog.v(TAG, "show()");
        }
        this.mWindowLayoutParams = params;
        try {
            params.packageName = "android";
            params.setTitle("Autofill Inline Tooltip");
            if (!this.mShowing) {
                params.flags = 40;
                params.privateFlags |= 4194304;
                this.mContentContainer.addOnLayoutChangeListener(this.mAnchoredOnLayoutChangeListener);
                this.mWm.addView(this.mContentContainer, params);
                this.mShowing = true;
            } else {
                this.mWm.updateViewLayout(this.mContentContainer, params);
            }
        } catch (WindowManager.BadTokenException e) {
            Slog.d(TAG, "Failed with token " + params.token + " gone.");
        } catch (IllegalStateException e2) {
            Slog.wtf(TAG, "Exception showing window " + params, e2);
        }
    }

    private void hide() {
        if (Helper.sVerbose) {
            Slog.v(TAG, "hide()");
        }
        try {
            if (this.mShowing) {
                this.mContentContainer.removeOnLayoutChangeListener(this.mAnchoredOnLayoutChangeListener);
                this.mWm.removeView(this.mContentContainer);
                this.mShowing = false;
            }
        } catch (IllegalStateException e) {
            Slog.e(TAG, "Exception hiding window ", e);
        }
    }

    @Override // android.widget.PopupWindow
    public int getAnimationStyle() {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public Drawable getBackground() {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public View getContentView() {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public float getElevation() {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public Transition getEnterTransition() {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public Transition getExitTransition() {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public void setBackgroundDrawable(Drawable background) {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public void setContentView(View contentView) {
        if (contentView != null) {
            throw new IllegalStateException("You can't call this!");
        }
    }

    @Override // android.widget.PopupWindow
    public void setElevation(float elevation) {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public void setEnterTransition(Transition enterTransition) {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public void setExitTransition(Transition exitTransition) {
        throw new IllegalStateException("You can't call this!");
    }

    @Override // android.widget.PopupWindow
    public void setTouchInterceptor(View.OnTouchListener l) {
        throw new IllegalStateException("You can't call this!");
    }

    public void dump(PrintWriter pw, String prefix) {
        pw.print(prefix);
        if (this.mContentContainer != null) {
            pw.print(prefix);
            pw.print("Window: ");
            String prefix2 = prefix + "  ";
            pw.println();
            pw.print(prefix2);
            pw.print("showing: ");
            pw.println(this.mShowing);
            pw.print(prefix2);
            pw.print("view: ");
            pw.println(this.mContentContainer);
            if (this.mWindowLayoutParams != null) {
                pw.print(prefix2);
                pw.print("params: ");
                pw.println(this.mWindowLayoutParams);
            }
            pw.print(prefix2);
            pw.print("screen coordinates: ");
            ViewGroup viewGroup = this.mContentContainer;
            if (viewGroup == null) {
                pw.println("N/A");
                return;
            }
            int[] coordinates = viewGroup.getLocationOnScreen();
            pw.print(coordinates[0]);
            pw.print("x");
            pw.println(coordinates[1]);
        }
    }
}
