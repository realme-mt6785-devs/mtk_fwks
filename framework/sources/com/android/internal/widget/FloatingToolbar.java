package com.android.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Size;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.util.Preconditions;
import com.android.internal.widget.FloatingToolbar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class FloatingToolbar {
    public static final String FLOATING_TOOLBAR_TAG = "floating_toolbar";
    private static final MenuItem.OnMenuItemClickListener NO_OP_MENUITEM_CLICK_LISTENER = FloatingToolbar$$ExternalSyntheticLambda0.INSTANCE;
    private final Context mContext;
    private Menu mMenu;
    private final FloatingToolbarPopup mPopup;
    private int mSuggestedWidth;
    private final Window mWindow;
    private final Rect mContentRect = new Rect();
    private final Rect mPreviousContentRect = new Rect();
    private MenuItem.OnMenuItemClickListener mMenuItemClickListener = NO_OP_MENUITEM_CLICK_LISTENER;
    private boolean mWidthChanged = true;
    private IFloatingToolbarExt mFloatingToolbarExt = FloatingToolbarExtPlugin.constructor.newInstance();
    private final View.OnLayoutChangeListener mOrientationChangeHandler = new View.OnLayoutChangeListener() { // from class: com.android.internal.widget.FloatingToolbar.1
        private final Rect mNewRect = new Rect();
        private final Rect mOldRect = new Rect();

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int newLeft, int newRight, int newTop, int newBottom, int oldLeft, int oldRight, int oldTop, int oldBottom) {
            this.mNewRect.set(newLeft, newRight, newTop, newBottom);
            this.mOldRect.set(oldLeft, oldRight, oldTop, oldBottom);
            if (FloatingToolbar.this.mPopup.isShowing() && !this.mNewRect.equals(this.mOldRect)) {
                FloatingToolbar.this.mWidthChanged = true;
                FloatingToolbar.this.updateLayout();
            }
        }
    };
    private final Comparator<MenuItem> mMenuItemComparator = FloatingToolbar$$ExternalSyntheticLambda1.INSTANCE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(MenuItem item) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$1(MenuItem menuItem1, MenuItem menuItem2) {
        if (menuItem1.getItemId() == 16908353) {
            return menuItem2.getItemId() == 16908353 ? 0 : -1;
        }
        if (menuItem2.getItemId() == 16908353) {
            return 1;
        }
        if (menuItem1.requiresActionButton()) {
            return menuItem2.requiresActionButton() ? 0 : -1;
        }
        if (menuItem2.requiresActionButton()) {
            return 1;
        }
        if (menuItem1.requiresOverflow()) {
            return !menuItem2.requiresOverflow();
        }
        if (menuItem2.requiresOverflow()) {
            return -1;
        }
        return menuItem1.getOrder() - menuItem2.getOrder();
    }

    public FloatingToolbar(Window window) {
        Context applyDefaultTheme = applyDefaultTheme(window.getContext());
        this.mContext = applyDefaultTheme;
        Objects.requireNonNull(window);
        this.mWindow = window;
        this.mPopup = new FloatingToolbarPopup(applyDefaultTheme, window.getDecorView());
    }

    public FloatingToolbar setMenu(Menu menu) {
        Objects.requireNonNull(menu);
        this.mMenu = menu;
        return this;
    }

    public FloatingToolbar setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener menuItemClickListener) {
        if (menuItemClickListener != null) {
            this.mMenuItemClickListener = menuItemClickListener;
        } else {
            this.mMenuItemClickListener = NO_OP_MENUITEM_CLICK_LISTENER;
        }
        return this;
    }

    public FloatingToolbar setContentRect(Rect rect) {
        Rect rect2 = this.mContentRect;
        Objects.requireNonNull(rect);
        rect2.set(rect);
        return this;
    }

    public FloatingToolbar setSuggestedWidth(int suggestedWidth) {
        int difference = Math.abs(suggestedWidth - this.mSuggestedWidth);
        this.mWidthChanged = ((double) difference) > ((double) this.mSuggestedWidth) * 0.2d;
        this.mSuggestedWidth = suggestedWidth;
        return this;
    }

    public FloatingToolbar show() {
        registerOrientationHandler();
        doShow();
        return this;
    }

    public FloatingToolbar updateLayout() {
        if (this.mPopup.isShowing()) {
            doShow();
        }
        return this;
    }

    public void dismiss() {
        unregisterOrientationHandler();
        this.mPopup.dismiss();
    }

    public void hide() {
        this.mPopup.hide();
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean isHidden() {
        return this.mPopup.isHidden();
    }

    public void setOutsideTouchable(boolean outsideTouchable, PopupWindow.OnDismissListener onDismiss) {
        this.mPopup.setOutsideTouchable(outsideTouchable, onDismiss);
    }

    private void doShow() {
        List<MenuItem> menuItems = getVisibleAndEnabledMenuItems(this.mMenu);
        menuItems.sort(this.mMenuItemComparator);
        if (this.mPopup.isLayoutRequired(menuItems) || this.mWidthChanged) {
            this.mPopup.dismiss();
            this.mPopup.layoutMenuItems(menuItems, this.mMenuItemClickListener, this.mSuggestedWidth);
        } else {
            this.mPopup.updateMenuItems(menuItems, this.mMenuItemClickListener);
        }
        if (!this.mPopup.isShowing()) {
            this.mPopup.show(this.mContentRect);
        } else if (!this.mPreviousContentRect.equals(this.mContentRect)) {
            this.mPopup.updateCoordinates(this.mContentRect);
        }
        this.mWidthChanged = false;
        this.mPreviousContentRect.set(this.mContentRect);
    }

    private static List<MenuItem> getVisibleAndEnabledMenuItems(Menu menu) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; menu != null && i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.isVisible() && menuItem.isEnabled()) {
                Menu subMenu = menuItem.getSubMenu();
                if (subMenu != null) {
                    menuItems.addAll(getVisibleAndEnabledMenuItems(subMenu));
                } else {
                    menuItems.add(menuItem);
                }
            }
        }
        return menuItems;
    }

    private void registerOrientationHandler() {
        unregisterOrientationHandler();
        this.mWindow.getDecorView().addOnLayoutChangeListener(this.mOrientationChangeHandler);
    }

    private void unregisterOrientationHandler() {
        this.mWindow.getDecorView().removeOnLayoutChangeListener(this.mOrientationChangeHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class FloatingToolbarPopup {
        private final Drawable mArrow;
        private final AnimationSet mCloseOverflowAnimation;
        private final ViewGroup mContentContainer;
        private final Context mContext;
        private final AnimatorSet mDismissAnimation;
        private final Interpolator mFastOutLinearInInterpolator;
        private final Interpolator mFastOutSlowInInterpolator;
        private boolean mHidden;
        private final AnimatorSet mHideAnimation;
        private final int mIconTextSpacing;
        private boolean mIsOverflowOpen;
        private final int mLineHeight;
        private final Interpolator mLinearOutSlowInInterpolator;
        private Size mMainPanelSize;
        private final int mMarginHorizontal;
        private final int mMarginVertical;
        private MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;
        private final AnimationSet mOpenOverflowAnimation;
        private boolean mOpenOverflowUpwards;
        private final Drawable mOverflow;
        private final Animation.AnimationListener mOverflowAnimationListener;
        private final ImageButton mOverflowButton;
        private final Size mOverflowButtonSize;
        private Size mOverflowPanelSize;
        private final OverflowPanelViewHelper mOverflowPanelViewHelper;
        private final View mParent;
        private final PopupWindow mPopupWindow;
        private final AnimatorSet mShowAnimation;
        private final AnimatedVectorDrawable mToArrow;
        private final AnimatedVectorDrawable mToOverflow;
        private int mTransitionDurationScale;
        private static final int MIN_OVERFLOW_SIZE = FloatingToolbarPopupExtPlugin.hookMinOverflowSize.callWithDefault(null, 2, new Object[0]).intValue();
        private static final int MAX_OVERFLOW_SIZE = FloatingToolbarPopupExtPlugin.hookMaxOverflowSize.callWithDefault(null, 4, new Object[0]).intValue();
        private IFloatingToolbarPopupExt mFloatingToolbarPopupExt = FloatingToolbarPopupExtPlugin.constructor.newInstance(new Object[0]);
        private final Rect mViewPortOnScreen = new Rect();
        private final Point mCoordsOnWindow = new Point();
        private final int[] mTmpCoords = new int[2];
        private final Region mTouchableRegion = new Region();
        private final ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer = new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: com.android.internal.widget.FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnComputeInternalInsetsListener
            public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
                FloatingToolbar.FloatingToolbarPopup.this.lambda$new$0$FloatingToolbar$FloatingToolbarPopup(internalInsetsInfo);
            }
        };
        private final Runnable mPreparePopupContentRTLHelper = new Runnable() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.1
            @Override // java.lang.Runnable
            public void run() {
                FloatingToolbarPopup.this.setPanelsStatesAtRestingPosition();
                FloatingToolbarPopup.this.setContentAreaAsTouchableSurface();
                FloatingToolbarPopup.this.mContentContainer.setAlpha(1.0f);
            }
        };
        private boolean mDismissed = true;
        private final Map<MenuItemRepr, MenuItem> mMenuItems = new LinkedHashMap();
        private final View.OnClickListener mMenuItemButtonOnClickListener = new View.OnClickListener() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MenuItem menuItem;
                if (FloatingToolbarPopup.this.mOnMenuItemClickListener != null) {
                    Object tag = v.getTag();
                    if ((tag instanceof MenuItemRepr) && (menuItem = (MenuItem) FloatingToolbarPopup.this.mMenuItems.get((MenuItemRepr) tag)) != null) {
                        FloatingToolbarPopup.this.mOnMenuItemClickListener.onMenuItemClick(menuItem);
                    }
                }
            }
        };
        private final Interpolator mLogAccelerateInterpolator = new LogAccelerateInterpolator();
        private final ViewGroup mMainPanel = createMainPanel();
        private final OverflowPanel mOverflowPanel = createOverflowPanel();

        public /* synthetic */ void lambda$new$0$FloatingToolbar$FloatingToolbarPopup(ViewTreeObserver.InternalInsetsInfo info) {
            info.contentInsets.setEmpty();
            info.visibleInsets.setEmpty();
            info.touchableRegion.set(this.mTouchableRegion);
            info.setTouchableInsets(3);
        }

        public FloatingToolbarPopup(Context context, View parent) {
            Objects.requireNonNull(parent);
            this.mParent = parent;
            Objects.requireNonNull(context);
            Context context2 = context;
            this.mContext = context2;
            ViewGroup createContentContainer = FloatingToolbar.createContentContainer(context);
            this.mContentContainer = createContentContainer;
            this.mFloatingToolbarPopupExt.hookSetElevation(createContentContainer);
            this.mPopupWindow = FloatingToolbar.createPopupWindow(createContentContainer);
            this.mMarginHorizontal = parent.getResources().getDimensionPixelSize(R.dimen.floating_toolbar_horizontal_margin);
            this.mMarginVertical = parent.getResources().getDimensionPixelSize(this.mFloatingToolbarPopupExt.hookGetMarginVertical());
            this.mLineHeight = this.mFloatingToolbarPopupExt.hookFloatingToolbarPopupForLineHeight(context, R.dimen.floating_toolbar_height);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.floating_toolbar_icon_text_spacing);
            this.mIconTextSpacing = dimensionPixelSize;
            this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(context2, 17563661);
            this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(context2, 17563662);
            this.mFastOutLinearInInterpolator = AnimationUtils.loadInterpolator(context2, 17563663);
            Drawable hookFloatingToolbarPopupForArrow = this.mFloatingToolbarPopupExt.hookFloatingToolbarPopupForArrow(context2, R.drawable.ft_avd_tooverflow);
            this.mArrow = hookFloatingToolbarPopupForArrow;
            hookFloatingToolbarPopupForArrow.setAutoMirrored(true);
            Drawable hookFloatingToolbarPopupForOverflow = this.mFloatingToolbarPopupExt.hookFloatingToolbarPopupForOverflow(context2, R.drawable.ft_avd_toarrow);
            this.mOverflow = hookFloatingToolbarPopupForOverflow;
            hookFloatingToolbarPopupForOverflow.setAutoMirrored(true);
            AnimatedVectorDrawable hookFloatingToolbarPopupForToArrow = this.mFloatingToolbarPopupExt.hookFloatingToolbarPopupForToArrow(context2, R.drawable.ft_avd_toarrow_animation);
            this.mToArrow = hookFloatingToolbarPopupForToArrow;
            hookFloatingToolbarPopupForToArrow.setAutoMirrored(true);
            AnimatedVectorDrawable hookFloatingToolbarPopupForToOverflow = this.mFloatingToolbarPopupExt.hookFloatingToolbarPopupForToOverflow(context2, R.drawable.ft_avd_tooverflow_animation);
            this.mToOverflow = hookFloatingToolbarPopupForToOverflow;
            hookFloatingToolbarPopupForToOverflow.setAutoMirrored(true);
            ImageButton createOverflowButton = createOverflowButton();
            this.mOverflowButton = createOverflowButton;
            this.mOverflowButtonSize = measure(createOverflowButton);
            OverflowPanelViewHelper overflowPanelViewHelper = new OverflowPanelViewHelper(context2, dimensionPixelSize);
            this.mOverflowPanelViewHelper = overflowPanelViewHelper;
            this.mFloatingToolbarPopupExt.hookFloatingToolbarPopupAfterOverflowPanelViewHelper(overflowPanelViewHelper.mOverflowPanelViewHelperExt);
            Animation.AnimationListener createOverflowAnimationListener = createOverflowAnimationListener();
            this.mOverflowAnimationListener = createOverflowAnimationListener;
            AnimationSet animationSet = new AnimationSet(true);
            this.mOpenOverflowAnimation = animationSet;
            animationSet.setAnimationListener(createOverflowAnimationListener);
            AnimationSet animationSet2 = new AnimationSet(true);
            this.mCloseOverflowAnimation = animationSet2;
            animationSet2.setAnimationListener(createOverflowAnimationListener);
            this.mShowAnimation = FloatingToolbar.createEnterAnimation(createContentContainer);
            this.mDismissAnimation = FloatingToolbar.createExitAnimation(createContentContainer, 150, new AnimatorListenerAdapter() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    FloatingToolbarPopup.this.mPopupWindow.dismiss();
                    FloatingToolbarPopup.this.mContentContainer.removeAllViews();
                }
            });
            this.mHideAnimation = FloatingToolbar.createExitAnimation(createContentContainer, 0, new AnimatorListenerAdapter() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    FloatingToolbarPopup.this.mPopupWindow.dismiss();
                }
            });
        }

        public boolean setOutsideTouchable(boolean outsideTouchable, PopupWindow.OnDismissListener onDismiss) {
            boolean ret = false;
            if (this.mPopupWindow.isOutsideTouchable() ^ outsideTouchable) {
                this.mPopupWindow.setOutsideTouchable(outsideTouchable);
                this.mPopupWindow.setFocusable(!outsideTouchable);
                this.mPopupWindow.update();
                ret = true;
            }
            this.mPopupWindow.setOnDismissListener(onDismiss);
            return ret;
        }

        public void layoutMenuItems(List<MenuItem> menuItems, MenuItem.OnMenuItemClickListener menuItemClickListener, int suggestedWidth) {
            cancelOverflowAnimations();
            clearPanels();
            updateMenuItems(menuItems, menuItemClickListener);
            List<MenuItem> menuItems2 = layoutMainPanelItems(menuItems, getAdjustedToolbarWidth(suggestedWidth));
            if (!menuItems2.isEmpty()) {
                layoutOverflowPanelItems(menuItems2);
            }
            updatePopupSize();
        }

        public void updateMenuItems(List<MenuItem> menuItems, MenuItem.OnMenuItemClickListener menuItemClickListener) {
            this.mMenuItems.clear();
            for (MenuItem menuItem : menuItems) {
                this.mMenuItems.put(MenuItemRepr.of(menuItem), menuItem);
            }
            this.mOnMenuItemClickListener = menuItemClickListener;
        }

        public boolean isLayoutRequired(List<MenuItem> menuItems) {
            return !MenuItemRepr.reprEquals(menuItems, this.mMenuItems.values());
        }

        public void show(Rect contentRectOnScreen) {
            Objects.requireNonNull(contentRectOnScreen);
            if (!isShowing()) {
                this.mHidden = false;
                this.mDismissed = false;
                cancelDismissAndHideAnimations();
                cancelOverflowAnimations();
                refreshCoordinatesAndOverflowDirection(contentRectOnScreen);
                preparePopupContent(true);
                this.mPopupWindow.showAtLocation(this.mParent, 0, this.mCoordsOnWindow.x, this.mCoordsOnWindow.y);
                setTouchableSurfaceInsetsComputer();
                runShowAnimation();
            }
        }

        public void dismiss() {
            if (!this.mDismissed) {
                this.mHidden = false;
                this.mDismissed = true;
                this.mHideAnimation.cancel();
                runDismissAnimation();
                setZeroTouchableSurface();
            }
        }

        public void hide() {
            if (isShowing()) {
                this.mHidden = true;
                runHideAnimation();
                setZeroTouchableSurface();
            }
        }

        public boolean isShowing() {
            return !this.mDismissed && !this.mHidden;
        }

        public boolean isHidden() {
            return this.mHidden;
        }

        public void updateCoordinates(Rect contentRectOnScreen) {
            Objects.requireNonNull(contentRectOnScreen);
            if (isShowing() && this.mPopupWindow.isShowing()) {
                cancelOverflowAnimations();
                refreshCoordinatesAndOverflowDirection(contentRectOnScreen);
                preparePopupContent(false);
                this.mPopupWindow.update(this.mCoordsOnWindow.x, this.mCoordsOnWindow.y, this.mPopupWindow.getWidth(), this.mPopupWindow.getHeight());
            }
        }

        private void refreshCoordinatesAndOverflowDirection(Rect contentRectOnScreen) {
            int minimumOverflowHeightWithMargin;
            refreshViewPort();
            int x = Math.min(contentRectOnScreen.centerX() - (this.mPopupWindow.getWidth() / 2), this.mViewPortOnScreen.right - this.mPopupWindow.getWidth());
            int availableHeightAboveContent = contentRectOnScreen.top - this.mViewPortOnScreen.top;
            int availableHeightBelowContent = this.mViewPortOnScreen.bottom - contentRectOnScreen.bottom;
            int margin = this.mMarginVertical * 2;
            int toolbarHeightWithVerticalMargin = this.mLineHeight + margin;
            if (hasOverflow()) {
                int y = MIN_OVERFLOW_SIZE;
                int minimumOverflowHeightWithMargin2 = calculateOverflowHeight(y) + margin;
                int availableHeightThroughContentDown = (this.mViewPortOnScreen.bottom - contentRectOnScreen.top) + toolbarHeightWithVerticalMargin;
                int availableHeightThroughContentUp = (contentRectOnScreen.bottom - this.mViewPortOnScreen.top) + toolbarHeightWithVerticalMargin;
                if (availableHeightAboveContent >= minimumOverflowHeightWithMargin2) {
                    updateOverflowHeight(availableHeightAboveContent - margin);
                    int y2 = contentRectOnScreen.top - this.mPopupWindow.getHeight();
                    this.mOpenOverflowUpwards = true;
                    minimumOverflowHeightWithMargin = y2;
                } else if (availableHeightAboveContent >= toolbarHeightWithVerticalMargin && availableHeightThroughContentDown >= minimumOverflowHeightWithMargin2) {
                    updateOverflowHeight(availableHeightThroughContentDown - margin);
                    int y3 = contentRectOnScreen.top - toolbarHeightWithVerticalMargin;
                    this.mOpenOverflowUpwards = false;
                    minimumOverflowHeightWithMargin = y3;
                } else if (availableHeightBelowContent >= minimumOverflowHeightWithMargin2) {
                    updateOverflowHeight(availableHeightBelowContent - margin);
                    int y4 = contentRectOnScreen.bottom;
                    this.mOpenOverflowUpwards = false;
                    minimumOverflowHeightWithMargin = y4;
                } else if (availableHeightBelowContent < toolbarHeightWithVerticalMargin || this.mViewPortOnScreen.height() < minimumOverflowHeightWithMargin2) {
                    updateOverflowHeight(this.mViewPortOnScreen.height() - margin);
                    int y5 = this.mViewPortOnScreen.top;
                    this.mOpenOverflowUpwards = false;
                    minimumOverflowHeightWithMargin = y5;
                } else {
                    updateOverflowHeight(availableHeightThroughContentUp - margin);
                    int y6 = (contentRectOnScreen.bottom + toolbarHeightWithVerticalMargin) - this.mPopupWindow.getHeight();
                    this.mOpenOverflowUpwards = true;
                    minimumOverflowHeightWithMargin = y6;
                }
            } else if (availableHeightAboveContent >= toolbarHeightWithVerticalMargin) {
                minimumOverflowHeightWithMargin = contentRectOnScreen.top - toolbarHeightWithVerticalMargin;
            } else if (availableHeightBelowContent >= toolbarHeightWithVerticalMargin) {
                minimumOverflowHeightWithMargin = contentRectOnScreen.bottom;
            } else {
                int y7 = this.mLineHeight;
                if (availableHeightBelowContent >= y7) {
                    minimumOverflowHeightWithMargin = contentRectOnScreen.bottom - this.mMarginVertical;
                } else {
                    minimumOverflowHeightWithMargin = Math.max(this.mViewPortOnScreen.top, contentRectOnScreen.top - toolbarHeightWithVerticalMargin);
                }
            }
            this.mParent.getRootView().getLocationOnScreen(this.mTmpCoords);
            int[] iArr = this.mTmpCoords;
            int rootViewLeftOnScreen = iArr[0];
            int rootViewTopOnScreen = iArr[1];
            this.mParent.getRootView().getLocationInWindow(this.mTmpCoords);
            int[] iArr2 = this.mTmpCoords;
            int rootViewLeftOnWindow = iArr2[0];
            int rootViewTopOnWindow = iArr2[1];
            int windowLeftOnScreen = rootViewLeftOnScreen - rootViewLeftOnWindow;
            int windowTopOnScreen = rootViewTopOnScreen - rootViewTopOnWindow;
            this.mCoordsOnWindow.set(Math.max(0, x - windowLeftOnScreen), Math.max(0, minimumOverflowHeightWithMargin - windowTopOnScreen));
            int floatingToolbarHeight = calculateOverflowHeight(MIN_OVERFLOW_SIZE) + margin + toolbarHeightWithVerticalMargin;
            int floatingToolBarBottom = this.mCoordsOnWindow.y + windowTopOnScreen + floatingToolbarHeight;
            int screenHeight = this.mContentContainer.getContext().getResources().getDisplayMetrics().heightPixels;
            if (floatingToolBarBottom > screenHeight && floatingToolbarHeight < windowTopOnScreen) {
                this.mCoordsOnWindow.set(Math.max(0, x - windowLeftOnScreen), minimumOverflowHeightWithMargin - windowTopOnScreen);
            }
        }

        private void runShowAnimation() {
            this.mShowAnimation.start();
        }

        private void runDismissAnimation() {
            this.mDismissAnimation.start();
        }

        private void runHideAnimation() {
            this.mHideAnimation.start();
        }

        private void cancelDismissAndHideAnimations() {
            this.mDismissAnimation.cancel();
            this.mHideAnimation.cancel();
        }

        private void cancelOverflowAnimations() {
            this.mContentContainer.clearAnimation();
            this.mMainPanel.animate().cancel();
            this.mOverflowPanel.animate().cancel();
            this.mToArrow.stop();
            this.mToOverflow.stop();
        }

        private void openOverflow() {
            final float overflowButtonTargetX;
            final int targetWidth = this.mOverflowPanelSize.getWidth();
            final int targetHeight = this.mOverflowPanelSize.getHeight();
            final int startWidth = this.mContentContainer.getWidth();
            final int startHeight = this.mContentContainer.getHeight();
            final float startY = this.mContentContainer.getY();
            final float left = this.mContentContainer.getX();
            final float right = left + this.mContentContainer.getWidth();
            Animation widthAnimation = new Animation() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.view.animation.Animation
                public void applyTransformation(float interpolatedTime, Transformation t) {
                    int deltaWidth = (int) ((targetWidth - startWidth) * interpolatedTime);
                    FloatingToolbarPopup.setWidth(FloatingToolbarPopup.this.mContentContainer, startWidth + deltaWidth);
                    if (FloatingToolbarPopup.this.isInRTLMode()) {
                        FloatingToolbarPopup.this.mContentContainer.setX(left);
                        FloatingToolbarPopup.this.mMainPanel.setX(0.0f);
                        FloatingToolbarPopup.this.mOverflowPanel.setX(0.0f);
                        return;
                    }
                    FloatingToolbarPopup.this.mContentContainer.setX(right - FloatingToolbarPopup.this.mContentContainer.getWidth());
                    FloatingToolbarPopup.this.mMainPanel.setX(FloatingToolbarPopup.this.mContentContainer.getWidth() - startWidth);
                    FloatingToolbarPopup.this.mOverflowPanel.setX(FloatingToolbarPopup.this.mContentContainer.getWidth() - targetWidth);
                }
            };
            Animation heightAnimation = new Animation() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.6
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.view.animation.Animation
                public void applyTransformation(float interpolatedTime, Transformation t) {
                    int deltaHeight = (int) ((targetHeight - startHeight) * interpolatedTime);
                    FloatingToolbarPopup.setHeight(FloatingToolbarPopup.this.mContentContainer, startHeight + deltaHeight);
                    if (FloatingToolbarPopup.this.mOpenOverflowUpwards) {
                        FloatingToolbarPopup.this.mContentContainer.setY(startY - (FloatingToolbarPopup.this.mContentContainer.getHeight() - startHeight));
                        FloatingToolbarPopup.this.positionContentYCoordinatesIfOpeningOverflowUpwards();
                    }
                }
            };
            final float overflowButtonStartX = this.mOverflowButton.getX();
            if (isInRTLMode()) {
                overflowButtonTargetX = ((targetWidth + overflowButtonStartX) - this.mOverflowButton.getWidth()) - this.mFloatingToolbarPopupExt.hookGetOverflowBtnExtraStartX(this.mOverflowButton);
            } else {
                overflowButtonTargetX = (overflowButtonStartX - targetWidth) + this.mOverflowButton.getWidth() + this.mFloatingToolbarPopupExt.hookGetOverflowBtnExtraStartX(this.mOverflowButton);
            }
            Animation overflowButtonAnimation = new Animation() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.7
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.view.animation.Animation
                public void applyTransformation(float interpolatedTime, Transformation t) {
                    float deltaContainerWidth;
                    float f = overflowButtonStartX;
                    float overflowButtonX = f + ((overflowButtonTargetX - f) * interpolatedTime);
                    if (FloatingToolbarPopup.this.isInRTLMode()) {
                        deltaContainerWidth = 0.0f;
                    } else {
                        deltaContainerWidth = FloatingToolbarPopup.this.mContentContainer.getWidth() - startWidth;
                    }
                    float actualOverflowButtonX = overflowButtonX + deltaContainerWidth;
                    FloatingToolbarPopup.this.mOverflowButton.setX(actualOverflowButtonX);
                }
            };
            widthAnimation.setInterpolator(this.mLogAccelerateInterpolator);
            widthAnimation.setDuration(getAdjustedDuration(250));
            heightAnimation.setInterpolator(this.mFastOutSlowInInterpolator);
            heightAnimation.setDuration(getAdjustedDuration(250));
            overflowButtonAnimation.setInterpolator(this.mFastOutSlowInInterpolator);
            overflowButtonAnimation.setDuration(getAdjustedDuration(250));
            this.mOpenOverflowAnimation.getAnimations().clear();
            this.mOpenOverflowAnimation.getAnimations().clear();
            this.mOpenOverflowAnimation.addAnimation(widthAnimation);
            this.mOpenOverflowAnimation.addAnimation(heightAnimation);
            this.mOpenOverflowAnimation.addAnimation(overflowButtonAnimation);
            this.mContentContainer.startAnimation(this.mOpenOverflowAnimation);
            this.mIsOverflowOpen = true;
            this.mMainPanel.animate().alpha(0.0f).withLayer().setInterpolator(this.mLinearOutSlowInInterpolator).setDuration(250L).start();
            this.mOverflowPanel.setAlpha(1.0f);
        }

        private void closeOverflow() {
            final float overflowButtonTargetX;
            final int targetWidth = this.mMainPanelSize.getWidth();
            final int startWidth = this.mContentContainer.getWidth();
            final float left = this.mContentContainer.getX();
            final float right = left + this.mContentContainer.getWidth();
            Animation widthAnimation = new Animation() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.8
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.view.animation.Animation
                public void applyTransformation(float interpolatedTime, Transformation t) {
                    int deltaWidth = (int) ((targetWidth - startWidth) * interpolatedTime);
                    FloatingToolbarPopup.setWidth(FloatingToolbarPopup.this.mContentContainer, startWidth + deltaWidth);
                    if (FloatingToolbarPopup.this.isInRTLMode()) {
                        FloatingToolbarPopup.this.mContentContainer.setX(left);
                        FloatingToolbarPopup.this.mMainPanel.setX(0.0f);
                        FloatingToolbarPopup.this.mOverflowPanel.setX(0.0f);
                        return;
                    }
                    FloatingToolbarPopup.this.mContentContainer.setX(right - FloatingToolbarPopup.this.mContentContainer.getWidth());
                    FloatingToolbarPopup.this.mMainPanel.setX(FloatingToolbarPopup.this.mContentContainer.getWidth() - targetWidth);
                    FloatingToolbarPopup.this.mOverflowPanel.setX(FloatingToolbarPopup.this.mContentContainer.getWidth() - startWidth);
                }
            };
            final int targetHeight = this.mMainPanelSize.getHeight();
            final int startHeight = this.mContentContainer.getHeight();
            final float bottom = this.mContentContainer.getY() + this.mContentContainer.getHeight();
            Animation heightAnimation = new Animation() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.9
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.view.animation.Animation
                public void applyTransformation(float interpolatedTime, Transformation t) {
                    int deltaHeight = (int) ((targetHeight - startHeight) * interpolatedTime);
                    FloatingToolbarPopup.setHeight(FloatingToolbarPopup.this.mContentContainer, startHeight + deltaHeight);
                    if (FloatingToolbarPopup.this.mOpenOverflowUpwards) {
                        FloatingToolbarPopup.this.mContentContainer.setY(bottom - FloatingToolbarPopup.this.mContentContainer.getHeight());
                        FloatingToolbarPopup.this.positionContentYCoordinatesIfOpeningOverflowUpwards();
                    }
                }
            };
            final float overflowButtonStartX = this.mOverflowButton.getX();
            if (isInRTLMode()) {
                overflowButtonTargetX = (overflowButtonStartX - startWidth) + this.mOverflowButton.getWidth() + this.mFloatingToolbarPopupExt.hookGetOverflowBtnExtraStartX(this.mOverflowButton);
            } else {
                overflowButtonTargetX = ((startWidth + overflowButtonStartX) - this.mOverflowButton.getWidth()) - this.mFloatingToolbarPopupExt.hookGetOverflowBtnExtraStartX(this.mOverflowButton);
            }
            Animation overflowButtonAnimation = new Animation() { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.10
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.view.animation.Animation
                public void applyTransformation(float interpolatedTime, Transformation t) {
                    float deltaContainerWidth;
                    float f = overflowButtonStartX;
                    float overflowButtonX = f + ((overflowButtonTargetX - f) * interpolatedTime);
                    if (FloatingToolbarPopup.this.isInRTLMode()) {
                        deltaContainerWidth = 0.0f;
                    } else {
                        deltaContainerWidth = FloatingToolbarPopup.this.mContentContainer.getWidth() - startWidth;
                    }
                    float actualOverflowButtonX = overflowButtonX + deltaContainerWidth;
                    FloatingToolbarPopup.this.mOverflowButton.setX(actualOverflowButtonX);
                }
            };
            widthAnimation.setInterpolator(this.mFastOutSlowInInterpolator);
            widthAnimation.setDuration(getAdjustedDuration(250));
            heightAnimation.setInterpolator(this.mLogAccelerateInterpolator);
            heightAnimation.setDuration(getAdjustedDuration(250));
            overflowButtonAnimation.setInterpolator(this.mFastOutSlowInInterpolator);
            overflowButtonAnimation.setDuration(getAdjustedDuration(250));
            this.mCloseOverflowAnimation.getAnimations().clear();
            this.mCloseOverflowAnimation.addAnimation(widthAnimation);
            this.mCloseOverflowAnimation.addAnimation(heightAnimation);
            this.mCloseOverflowAnimation.addAnimation(overflowButtonAnimation);
            this.mContentContainer.startAnimation(this.mCloseOverflowAnimation);
            this.mIsOverflowOpen = false;
            this.mMainPanel.animate().alpha(1.0f).withLayer().setInterpolator(this.mFastOutLinearInInterpolator).setDuration(100L).start();
            this.mOverflowPanel.animate().alpha(0.0f).withLayer().setInterpolator(this.mLinearOutSlowInInterpolator).setDuration(150L).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPanelsStatesAtRestingPosition() {
            this.mOverflowButton.setEnabled(true);
            this.mOverflowPanel.awakenScrollBars();
            if (this.mIsOverflowOpen) {
                Size containerSize = this.mOverflowPanelSize;
                setSize(this.mContentContainer, containerSize);
                this.mMainPanel.setAlpha(0.0f);
                this.mMainPanel.setVisibility(4);
                this.mOverflowPanel.setAlpha(1.0f);
                this.mOverflowPanel.setVisibility(0);
                this.mOverflowButton.setImageDrawable(this.mArrow);
                this.mOverflowButton.setContentDescription(this.mContext.getString(R.string.floating_toolbar_close_overflow_description));
                if (isInRTLMode()) {
                    this.mContentContainer.setX(this.mMarginHorizontal);
                    this.mMainPanel.setX(0.0f);
                    this.mOverflowButton.setX((containerSize.getWidth() - this.mOverflowButtonSize.getWidth()) - this.mFloatingToolbarPopupExt.hookGetOverflowBtnExtraStartX(this.mOverflowButton));
                    this.mOverflowPanel.setX(0.0f);
                } else {
                    this.mContentContainer.setX((this.mPopupWindow.getWidth() - containerSize.getWidth()) - this.mMarginHorizontal);
                    this.mMainPanel.setX(-this.mContentContainer.getX());
                    ImageButton imageButton = this.mOverflowButton;
                    imageButton.setX(this.mFloatingToolbarPopupExt.hookGetOverflowBtnExtraStartX(imageButton) + 0);
                    this.mOverflowPanel.setX(0.0f);
                }
                if (this.mOpenOverflowUpwards) {
                    this.mContentContainer.setY(this.mMarginVertical);
                    this.mMainPanel.setY(containerSize.getHeight() - this.mContentContainer.getHeight());
                    this.mOverflowButton.setY(containerSize.getHeight() - this.mOverflowButtonSize.getHeight());
                    this.mOverflowPanel.setY(0.0f);
                    return;
                }
                this.mContentContainer.setY(this.mMarginVertical);
                this.mMainPanel.setY(0.0f);
                this.mOverflowButton.setY(0.0f);
                this.mOverflowPanel.setY(this.mOverflowButtonSize.getHeight());
                return;
            }
            Size containerSize2 = this.mMainPanelSize;
            setSize(this.mContentContainer, containerSize2);
            this.mMainPanel.setAlpha(1.0f);
            this.mMainPanel.setVisibility(0);
            this.mOverflowPanel.setAlpha(0.0f);
            this.mOverflowPanel.setVisibility(4);
            this.mOverflowButton.setImageDrawable(this.mOverflow);
            this.mOverflowButton.setContentDescription(this.mContext.getString(R.string.floating_toolbar_open_overflow_description));
            if (hasOverflow()) {
                if (isInRTLMode()) {
                    this.mContentContainer.setX(this.mMarginHorizontal);
                    this.mMainPanel.setX(0.0f);
                    this.mOverflowButton.setX(0.0f);
                    this.mOverflowPanel.setX(0.0f);
                } else {
                    this.mContentContainer.setX((this.mPopupWindow.getWidth() - containerSize2.getWidth()) - this.mMarginHorizontal);
                    this.mMainPanel.setX(0.0f);
                    this.mOverflowButton.setX(containerSize2.getWidth() - this.mOverflowButtonSize.getWidth());
                    this.mOverflowPanel.setX(containerSize2.getWidth() - this.mOverflowPanelSize.getWidth());
                }
                if (this.mOpenOverflowUpwards) {
                    this.mContentContainer.setY((this.mMarginVertical + this.mOverflowPanelSize.getHeight()) - containerSize2.getHeight());
                    this.mMainPanel.setY(0.0f);
                    this.mOverflowButton.setY(0.0f);
                    this.mOverflowPanel.setY(containerSize2.getHeight() - this.mOverflowPanelSize.getHeight());
                    return;
                }
                this.mContentContainer.setY(this.mMarginVertical);
                this.mMainPanel.setY(0.0f);
                this.mOverflowButton.setY(0.0f);
                this.mOverflowPanel.setY(this.mOverflowButtonSize.getHeight());
                return;
            }
            this.mContentContainer.setX(this.mMarginHorizontal);
            this.mContentContainer.setY(this.mMarginVertical);
            this.mMainPanel.setX(0.0f);
            this.mMainPanel.setY(0.0f);
        }

        private void updateOverflowHeight(int suggestedHeight) {
            if (hasOverflow()) {
                int maxItemSize = (suggestedHeight - this.mOverflowButtonSize.getHeight()) / this.mLineHeight;
                int newHeight = calculateOverflowHeight(maxItemSize);
                if (this.mOverflowPanelSize.getHeight() != newHeight) {
                    this.mOverflowPanelSize = new Size(this.mOverflowPanelSize.getWidth(), newHeight);
                }
                setSize(this.mOverflowPanel, this.mOverflowPanelSize);
                if (this.mIsOverflowOpen) {
                    setSize(this.mContentContainer, this.mOverflowPanelSize);
                    if (this.mOpenOverflowUpwards) {
                        int deltaHeight = this.mOverflowPanelSize.getHeight() - newHeight;
                        ViewGroup viewGroup = this.mContentContainer;
                        viewGroup.setY(viewGroup.getY() + deltaHeight);
                        ImageButton imageButton = this.mOverflowButton;
                        imageButton.setY(imageButton.getY() - deltaHeight);
                    }
                } else {
                    setSize(this.mContentContainer, this.mMainPanelSize);
                }
                updatePopupSize();
            }
        }

        private void updatePopupSize() {
            int width = 0;
            int height = 0;
            Size size = this.mMainPanelSize;
            if (size != null) {
                width = Math.max(0, size.getWidth());
                height = Math.max(0, this.mMainPanelSize.getHeight());
            }
            Size size2 = this.mOverflowPanelSize;
            if (size2 != null) {
                width = Math.max(width, size2.getWidth());
                height = Math.max(height, this.mOverflowPanelSize.getHeight());
            }
            this.mPopupWindow.setWidth((this.mMarginHorizontal * 2) + width);
            this.mPopupWindow.setHeight((this.mMarginVertical * 2) + height);
            maybeComputeTransitionDurationScale();
        }

        private void refreshViewPort() {
            this.mParent.getWindowVisibleDisplayFrame(this.mViewPortOnScreen);
        }

        private int getAdjustedToolbarWidth(int suggestedWidth) {
            int width = suggestedWidth;
            refreshViewPort();
            int maximumWidth = this.mViewPortOnScreen.width() - (this.mParent.getResources().getDimensionPixelSize(R.dimen.floating_toolbar_horizontal_margin) * 2);
            if (width <= 0) {
                width = this.mParent.getResources().getDimensionPixelSize(R.dimen.floating_toolbar_preferred_width);
            }
            return Math.min(width, maximumWidth);
        }

        private void setZeroTouchableSurface() {
            this.mTouchableRegion.setEmpty();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContentAreaAsTouchableSurface() {
            int height;
            int width;
            Objects.requireNonNull(this.mMainPanelSize);
            if (this.mIsOverflowOpen) {
                Objects.requireNonNull(this.mOverflowPanelSize);
                width = this.mOverflowPanelSize.getWidth();
                height = this.mOverflowPanelSize.getHeight();
            } else {
                width = this.mMainPanelSize.getWidth();
                height = this.mMainPanelSize.getHeight();
            }
            this.mTouchableRegion.set((int) this.mContentContainer.getX(), (int) this.mContentContainer.getY(), ((int) this.mContentContainer.getX()) + width, ((int) this.mContentContainer.getY()) + height);
        }

        private void setTouchableSurfaceInsetsComputer() {
            ViewTreeObserver viewTreeObserver = this.mPopupWindow.getContentView().getRootView().getViewTreeObserver();
            viewTreeObserver.removeOnComputeInternalInsetsListener(this.mInsetsComputer);
            viewTreeObserver.addOnComputeInternalInsetsListener(this.mInsetsComputer);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInRTLMode() {
            return this.mContext.getApplicationInfo().hasRtlSupport() && this.mContext.getResources().getConfiguration().getLayoutDirection() == 1;
        }

        private boolean hasOverflow() {
            return this.mOverflowPanelSize != null;
        }

        public List<MenuItem> layoutMainPanelItems(List<MenuItem> menuItems, int toolbarWidth) {
            int menuItemButtonWidth;
            Objects.requireNonNull(menuItems);
            int availableWidth = toolbarWidth;
            LinkedList<MenuItem> remainingMenuItems = new LinkedList<>();
            LinkedList<MenuItem> overflowMenuItems = new LinkedList<>();
            Iterator<MenuItem> it = menuItems.iterator();
            while (true) {
                menuItemButtonWidth = 16908353;
                if (!it.hasNext()) {
                    break;
                }
                MenuItem menuItem = it.next();
                if (menuItem.getItemId() == 16908353 || !menuItem.requiresOverflow()) {
                    remainingMenuItems.add(menuItem);
                } else {
                    overflowMenuItems.add(menuItem);
                }
            }
            remainingMenuItems.addAll(overflowMenuItems);
            this.mMainPanel.removeAllViews();
            this.mMainPanel.setPaddingRelative(0, 0, 0, 0);
            boolean isFirstItem = true;
            while (!remainingMenuItems.isEmpty()) {
                MenuItem menuItem2 = remainingMenuItems.peek();
                if (!isFirstItem && menuItem2.requiresOverflow()) {
                    break;
                }
                boolean showIcon = isFirstItem && menuItem2.getItemId() == menuItemButtonWidth;
                View menuItemButton = FloatingToolbar.createMenuItemButton(this.mContext, menuItem2, this.mIconTextSpacing, showIcon);
                if (!showIcon && (menuItemButton instanceof LinearLayout)) {
                    ((LinearLayout) menuItemButton).setGravity(17);
                }
                if (isFirstItem) {
                    menuItemButton.setPaddingRelative(this.mFloatingToolbarPopupExt.hookGetFirstItemPaddingStart(menuItemButton.getContext(), menuItemButton.getPaddingStart()), menuItemButton.getPaddingTop(), menuItemButton.getPaddingEnd(), menuItemButton.getPaddingBottom());
                }
                boolean isLastItem = remainingMenuItems.size() == 1;
                if (isLastItem) {
                    menuItemButton.setPaddingRelative(menuItemButton.getPaddingStart(), menuItemButton.getPaddingTop(), this.mFloatingToolbarPopupExt.hookGetLastItemPaddingEnd(menuItemButton.getContext(), menuItemButton.getPaddingEnd()), menuItemButton.getPaddingBottom());
                }
                menuItemButton.measure(0, 0);
                int menuItemButtonWidth2 = Math.min(menuItemButton.getMeasuredWidth(), toolbarWidth);
                boolean canFitWithOverflow = menuItemButtonWidth2 <= availableWidth - this.mOverflowButtonSize.getWidth();
                boolean canFitNoOverflow = isLastItem && menuItemButtonWidth2 <= availableWidth;
                if (!canFitWithOverflow && !canFitNoOverflow) {
                    break;
                }
                setButtonTagAndClickListener(menuItemButton, menuItem2);
                menuItemButton.setTooltipText(menuItem2.getTooltipText());
                this.mMainPanel.addView(menuItemButton);
                ViewGroup.LayoutParams params = menuItemButton.getLayoutParams();
                params.width = menuItemButtonWidth2;
                menuItemButton.setLayoutParams(params);
                availableWidth -= menuItemButtonWidth2;
                remainingMenuItems.pop();
                menuItem2.getGroupId();
                isFirstItem = false;
                menuItemButtonWidth = 16908353;
            }
            if (!remainingMenuItems.isEmpty()) {
                this.mMainPanel.setPaddingRelative(0, 0, this.mOverflowButtonSize.getWidth(), 0);
            }
            this.mMainPanelSize = measure(this.mMainPanel);
            return remainingMenuItems;
        }

        private void layoutOverflowPanelItems(List<MenuItem> menuItems) {
            ArrayAdapter<MenuItem> overflowPanelAdapter = (ArrayAdapter) this.mOverflowPanel.getAdapter();
            overflowPanelAdapter.clear();
            int size = menuItems.size();
            for (int i = 0; i < size; i++) {
                overflowPanelAdapter.add(menuItems.get(i));
            }
            this.mOverflowPanel.setAdapter((ListAdapter) overflowPanelAdapter);
            this.mFloatingToolbarPopupExt.hookLayoutOverflowPanelItems(overflowPanelAdapter.getCount());
            if (this.mOpenOverflowUpwards) {
                this.mOverflowPanel.setY(0.0f);
            } else {
                this.mOverflowPanel.setY(this.mOverflowButtonSize.getHeight());
            }
            int width = Math.max(getOverflowWidth(), this.mOverflowButtonSize.getWidth());
            int height = calculateOverflowHeight(MAX_OVERFLOW_SIZE);
            Size size2 = new Size(width, height);
            this.mOverflowPanelSize = size2;
            setSize(this.mOverflowPanel, size2);
        }

        private void preparePopupContent(boolean isFirstRendered) {
            this.mContentContainer.removeAllViews();
            if (hasOverflow()) {
                this.mContentContainer.addView(this.mOverflowPanel);
            }
            this.mContentContainer.addView(this.mMainPanel);
            if (hasOverflow()) {
                this.mContentContainer.addView(this.mOverflowButton);
            }
            setPanelsStatesAtRestingPosition();
            setContentAreaAsTouchableSurface();
            if (isFirstRendered) {
                this.mContentContainer.setAlpha(0.0f);
            }
            this.mContentContainer.post(this.mPreparePopupContentRTLHelper);
        }

        private void clearPanels() {
            this.mOverflowPanelSize = null;
            this.mMainPanelSize = null;
            this.mIsOverflowOpen = false;
            this.mMainPanel.removeAllViews();
            ArrayAdapter<MenuItem> overflowPanelAdapter = (ArrayAdapter) this.mOverflowPanel.getAdapter();
            overflowPanelAdapter.clear();
            this.mOverflowPanel.setAdapter((ListAdapter) overflowPanelAdapter);
            this.mFloatingToolbarPopupExt.hookClearPanels(overflowPanelAdapter.getCount());
            this.mContentContainer.removeAllViews();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void positionContentYCoordinatesIfOpeningOverflowUpwards() {
            if (this.mOpenOverflowUpwards) {
                this.mMainPanel.setY(this.mContentContainer.getHeight() - this.mMainPanelSize.getHeight());
                this.mOverflowButton.setY(this.mContentContainer.getHeight() - this.mOverflowButton.getHeight());
                this.mOverflowPanel.setY(this.mContentContainer.getHeight() - this.mOverflowPanelSize.getHeight());
            }
        }

        private int getOverflowWidth() {
            int overflowWidth = 0;
            int count = this.mOverflowPanel.getAdapter().getCount();
            for (int i = 0; i < count; i++) {
                MenuItem menuItem = (MenuItem) this.mOverflowPanel.getAdapter().getItem(i);
                overflowWidth = Math.max(this.mOverflowPanelViewHelper.calculateWidth(menuItem), overflowWidth);
            }
            return overflowWidth;
        }

        private int calculateOverflowHeight(int maxItemSize) {
            int actualSize = Math.min(MAX_OVERFLOW_SIZE, Math.min(Math.max(MIN_OVERFLOW_SIZE, maxItemSize), this.mOverflowPanel.getCount()));
            int extension = 0;
            if (actualSize < this.mOverflowPanel.getCount()) {
                extension = this.mFloatingToolbarPopupExt.hookCalculateOverflowExtension(this.mContentContainer, this.mLineHeight);
            }
            return this.mFloatingToolbarPopupExt.hookCalOverflowTotalItemHeight(this.mContext, actualSize, this.mLineHeight) + this.mOverflowButtonSize.getHeight() + extension;
        }

        private void setButtonTagAndClickListener(View menuItemButton, MenuItem menuItem) {
            menuItemButton.setTag(MenuItemRepr.of(menuItem));
            menuItemButton.setOnClickListener(this.mMenuItemButtonOnClickListener);
        }

        private int getAdjustedDuration(int originalDuration) {
            int i = this.mTransitionDurationScale;
            if (i < 150) {
                return Math.max(originalDuration - 50, 0);
            }
            if (i > 300) {
                return originalDuration + 50;
            }
            return (int) (originalDuration * ValueAnimator.getDurationScale());
        }

        private void maybeComputeTransitionDurationScale() {
            Size size = this.mMainPanelSize;
            if (size != null && this.mOverflowPanelSize != null) {
                int w = size.getWidth() - this.mOverflowPanelSize.getWidth();
                int h = this.mOverflowPanelSize.getHeight() - this.mMainPanelSize.getHeight();
                this.mTransitionDurationScale = (int) (Math.sqrt((w * w) + (h * h)) / this.mContentContainer.getContext().getResources().getDisplayMetrics().density);
            }
        }

        private ViewGroup createMainPanel() {
            ViewGroup mainPanel = new LinearLayout(this.mContext) { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.11
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.widget.LinearLayout, android.view.View
                public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                    if (FloatingToolbarPopup.this.isOverflowAnimating()) {
                        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(FloatingToolbarPopup.this.mMainPanelSize.getWidth(), 1073741824);
                    }
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }

                @Override // android.view.ViewGroup
                public boolean onInterceptTouchEvent(MotionEvent ev) {
                    return FloatingToolbarPopup.this.isOverflowAnimating();
                }
            };
            return mainPanel;
        }

        private ImageButton createOverflowButton() {
            final ImageButton overflowButton = this.mFloatingToolbarPopupExt.hookCreateOverflowButton(this.mContext, R.layout.floating_popup_overflow_button);
            overflowButton.setImageDrawable(this.mOverflow);
            overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.internal.widget.FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FloatingToolbar.FloatingToolbarPopup.this.lambda$createOverflowButton$1$FloatingToolbar$FloatingToolbarPopup(overflowButton, view);
                }
            });
            return overflowButton;
        }

        public /* synthetic */ void lambda$createOverflowButton$1$FloatingToolbar$FloatingToolbarPopup(ImageButton overflowButton, View v) {
            if (this.mIsOverflowOpen) {
                overflowButton.setImageDrawable(this.mToOverflow);
                this.mToOverflow.start();
                closeOverflow();
                return;
            }
            overflowButton.setImageDrawable(this.mToArrow);
            this.mToArrow.start();
            openOverflow();
        }

        private OverflowPanel createOverflowPanel() {
            final OverflowPanel overflowPanel = new OverflowPanel(this);
            overflowPanel.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            overflowPanel.setDivider(null);
            overflowPanel.setDividerHeight(0);
            this.mFloatingToolbarPopupExt.hookCreateOverflowPanelAfterOverflowPanel(overflowPanel);
            ArrayAdapter adapter = new ArrayAdapter<MenuItem>(this.mContext, 0) { // from class: com.android.internal.widget.FloatingToolbar.FloatingToolbarPopup.12
                @Override // android.widget.ArrayAdapter, android.widget.Adapter
                public View getView(int position, View convertView, ViewGroup parent) {
                    FloatingToolbarPopup.this.mFloatingToolbarPopupExt.hookCreateOverflowPanelAtAdapterGetView(position, FloatingToolbarPopup.this.mOpenOverflowUpwards);
                    return FloatingToolbarPopup.this.mOverflowPanelViewHelper.getView(getItem(position), FloatingToolbarPopup.this.mOverflowPanelSize.getWidth(), convertView);
                }
            };
            overflowPanel.setAdapter((ListAdapter) adapter);
            overflowPanel.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.internal.widget.FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda2
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    FloatingToolbar.FloatingToolbarPopup.this.lambda$createOverflowPanel$2$FloatingToolbar$FloatingToolbarPopup(overflowPanel, adapterView, view, i, j);
                }
            });
            return overflowPanel;
        }

        public /* synthetic */ void lambda$createOverflowPanel$2$FloatingToolbar$FloatingToolbarPopup(OverflowPanel overflowPanel, AdapterView parent, View view, int position, long id) {
            MenuItem menuItem = (MenuItem) overflowPanel.getAdapter().getItem(position);
            MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.mOnMenuItemClickListener;
            if (onMenuItemClickListener != null) {
                onMenuItemClickListener.onMenuItemClick(menuItem);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isOverflowAnimating() {
            boolean overflowOpening = this.mOpenOverflowAnimation.hasStarted() && !this.mOpenOverflowAnimation.hasEnded();
            boolean overflowClosing = this.mCloseOverflowAnimation.hasStarted() && !this.mCloseOverflowAnimation.hasEnded();
            return overflowOpening || overflowClosing;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.android.internal.widget.FloatingToolbar$FloatingToolbarPopup$13  reason: invalid class name */
        /* loaded from: classes4.dex */
        public class AnonymousClass13 implements Animation.AnimationListener {
            AnonymousClass13() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                FloatingToolbarPopup.this.mOverflowButton.setEnabled(false);
                FloatingToolbarPopup.this.mMainPanel.setVisibility(0);
                FloatingToolbarPopup.this.mOverflowPanel.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                FloatingToolbarPopup.this.mContentContainer.post(new Runnable() { // from class: com.android.internal.widget.FloatingToolbar$FloatingToolbarPopup$13$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FloatingToolbar.FloatingToolbarPopup.AnonymousClass13.this.lambda$onAnimationEnd$0$FloatingToolbar$FloatingToolbarPopup$13();
                    }
                });
            }

            public /* synthetic */ void lambda$onAnimationEnd$0$FloatingToolbar$FloatingToolbarPopup$13() {
                FloatingToolbarPopup.this.setPanelsStatesAtRestingPosition();
                FloatingToolbarPopup.this.setContentAreaAsTouchableSurface();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }
        }

        private Animation.AnimationListener createOverflowAnimationListener() {
            Animation.AnimationListener listener = new AnonymousClass13();
            return listener;
        }

        private static Size measure(View view) {
            Preconditions.checkState(view.getParent() == null);
            view.measure(0, 0);
            return new Size(view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        private static void setSize(View view, int width, int height) {
            view.setMinimumWidth(width);
            view.setMinimumHeight(height);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            ViewGroup.LayoutParams params2 = params == null ? new ViewGroup.LayoutParams(0, 0) : params;
            params2.width = width;
            params2.height = height;
            view.setLayoutParams(params2);
        }

        private static void setSize(View view, Size size) {
            setSize(view, size.getWidth(), size.getHeight());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void setWidth(View view, int width) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            setSize(view, width, params.height);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void setHeight(View view, int height) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            setSize(view, params.width, height);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static final class OverflowPanel extends ListView {
            private IOverflowPanelExt mOverflowPanelExt = OverflowPanelExtPlugin.constructor.newInstance();
            private final FloatingToolbarPopup mPopup;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            OverflowPanel(FloatingToolbarPopup popup) {
                super(popup.mContext);
                Objects.requireNonNull(popup);
                this.mPopup = popup;
                setScrollBarDefaultDelayBeforeFade(ViewConfiguration.getScrollDefaultDelay() * 3);
                if (!this.mOverflowPanelExt.hookOverflowPanel()) {
                    setScrollIndicators(3);
                    this.mOverflowPanelExt.hookSetScrollerbar(this);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
            public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int height = this.mPopup.mOverflowPanelSize.getHeight() - this.mPopup.mOverflowButtonSize.getHeight();
                int heightMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent ev) {
                if (this.mPopup.isOverflowAnimating()) {
                    return true;
                }
                return super.dispatchTouchEvent(ev);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.view.View
            public boolean awakenScrollBars() {
                return super.awakenScrollBars();
            }
        }

        /* loaded from: classes4.dex */
        private static final class LogAccelerateInterpolator implements Interpolator {
            private static final int BASE = 100;
            private static final float LOGS_SCALE = 1.0f / computeLog(1.0f, 100);

            private LogAccelerateInterpolator() {
            }

            private static float computeLog(float t, int base) {
                return (float) (1.0d - Math.pow(base, -t));
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float t) {
                return 1.0f - (computeLog(1.0f - t, 100) * LOGS_SCALE);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static final class OverflowPanelViewHelper {
            private final Context mContext;
            private final int mIconTextSpacing;
            private final int mSidePadding;
            private IOverflowPanelViewHelperExt mOverflowPanelViewHelperExt = OverflowPanelViewHelperExtPlugin.constructor.newInstance();
            private final View mCalculator = createMenuButton(null);

            public OverflowPanelViewHelper(Context context, int iconTextSpacing) {
                Objects.requireNonNull(context);
                this.mContext = context;
                this.mIconTextSpacing = iconTextSpacing;
                this.mSidePadding = context.getResources().getDimensionPixelSize(this.mOverflowPanelViewHelperExt.hookGetSidePaddingRes());
            }

            public View getView(MenuItem menuItem, int minimumWidth, View convertView) {
                Objects.requireNonNull(menuItem);
                if (convertView != null) {
                    FloatingToolbar.updateMenuItemButton(convertView, menuItem, this.mIconTextSpacing, shouldShowIcon(menuItem));
                } else {
                    convertView = createMenuButton(menuItem);
                }
                convertView.setMinimumWidth(minimumWidth);
                this.mOverflowPanelViewHelperExt.hookGetView(convertView, this.mSidePadding, minimumWidth);
                return convertView;
            }

            public int calculateWidth(MenuItem menuItem) {
                FloatingToolbar.updateMenuItemButton(this.mCalculator, menuItem, this.mIconTextSpacing, shouldShowIcon(menuItem));
                this.mCalculator.measure(0, 0);
                return this.mCalculator.getMeasuredWidth();
            }

            private View createMenuButton(MenuItem menuItem) {
                View button = FloatingToolbar.createMenuItemButton(this.mContext, menuItem, this.mIconTextSpacing, shouldShowIcon(menuItem));
                int i = this.mSidePadding;
                button.setPadding(i, 0, i, 0);
                return button;
            }

            private boolean shouldShowIcon(MenuItem menuItem) {
                return menuItem != null && menuItem.getGroupId() == 16908353;
            }
        }
    }

    /* loaded from: classes4.dex */
    public static final class MenuItemRepr {
        public final int groupId;
        public final int itemId;
        private final Drawable mIcon;
        private final Intent mIntent;
        public final String title;

        private MenuItemRepr(int itemId, int groupId, CharSequence title, Drawable icon, Intent intent) {
            this.itemId = itemId;
            this.groupId = groupId;
            this.title = title == null ? null : title.toString();
            this.mIcon = icon;
            this.mIntent = intent;
        }

        public static MenuItemRepr of(MenuItem menuItem) {
            return new MenuItemRepr(menuItem.getItemId(), menuItem.getGroupId(), menuItem.getTitle(), menuItem.getIcon(), menuItem.getIntent());
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.itemId), Integer.valueOf(this.groupId), this.title, this.mIcon, this.mIntent);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MenuItemRepr)) {
                return false;
            }
            MenuItemRepr other = (MenuItemRepr) o;
            return this.itemId == other.itemId && this.groupId == other.groupId && TextUtils.equals(this.title, other.title) && Objects.equals(this.mIcon, other.mIcon) && Objects.equals(this.mIntent, other.mIntent);
        }

        public static boolean reprEquals(Collection<MenuItem> menuItems1, Collection<MenuItem> menuItems2) {
            if (menuItems1.size() != menuItems2.size()) {
                return false;
            }
            Iterator<MenuItem> menuItems2Iter = menuItems2.iterator();
            for (MenuItem menuItem1 : menuItems1) {
                MenuItem menuItem2 = menuItems2Iter.next();
                if (!of(menuItem1).equals(of(menuItem2))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static View createMenuItemButton(Context context, MenuItem menuItem, int iconTextSpacing, boolean showIcon) {
        View menuItemButton = FloatingToolbarExtPlugin.hookCreateMenuItemButton.call(context);
        if (menuItemButton == null) {
            menuItemButton = LayoutInflater.from(context).inflate(R.layout.floating_popup_menu_button, (ViewGroup) null);
        }
        if (menuItem != null) {
            updateMenuItemButton(menuItemButton, menuItem, iconTextSpacing, showIcon);
        }
        return menuItemButton;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateMenuItemButton(View menuItemButton, MenuItem menuItem, int iconTextSpacing, boolean showIcon) {
        TextView buttonText = FloatingToolbarExtPlugin.hookUpdateMenuItemButtonForText.call(menuItemButton);
        if (buttonText == null) {
            buttonText = (TextView) menuItemButton.findViewById(R.id.floating_toolbar_menu_item_text);
        }
        buttonText.setEllipsize(null);
        if (TextUtils.isEmpty(menuItem.getTitle())) {
            buttonText.setVisibility(8);
        } else {
            buttonText.setVisibility(0);
            buttonText.setText(menuItem.getTitle());
        }
        ImageView buttonIcon = FloatingToolbarExtPlugin.hookUpdateMenuItemButtonForIcon.call(menuItemButton);
        if (buttonIcon == null) {
            buttonIcon = (ImageView) menuItemButton.findViewById(R.id.floating_toolbar_menu_item_image);
        }
        if (menuItem.getIcon() == null || !showIcon) {
            buttonIcon.setVisibility(8);
            if (buttonText != null) {
                buttonText.setPaddingRelative(0, 0, 0, 0);
            }
        } else {
            buttonIcon.setVisibility(0);
            buttonIcon.setImageDrawable(menuItem.getIcon());
            if (buttonText != null) {
                buttonText.setPaddingRelative(iconTextSpacing, 0, 0, 0);
            }
        }
        CharSequence contentDescription = menuItem.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            menuItemButton.setContentDescription(menuItem.getTitle());
        } else {
            menuItemButton.setContentDescription(contentDescription);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ViewGroup createContentContainer(Context context) {
        ViewGroup contentContainer = FloatingToolbarExtPlugin.hookCreateContentContainer.call(context);
        if (contentContainer == null) {
            contentContainer = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.floating_popup_container, (ViewGroup) null);
        }
        contentContainer.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        contentContainer.setTag(FLOATING_TOOLBAR_TAG);
        contentContainer.setClipToOutline(true);
        return contentContainer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PopupWindow createPopupWindow(ViewGroup content) {
        ViewGroup popupContentHolder = new LinearLayout(content.getContext());
        PopupWindow popupWindow = new PopupWindow(popupContentHolder);
        popupWindow.setClippingEnabled(false);
        popupWindow.setWindowLayoutType(1005);
        popupWindow.setAnimationStyle(0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        content.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        popupContentHolder.addView(content);
        return popupWindow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AnimatorSet createEnterAnimation(View view) {
        AnimatorSet animation = new AnimatorSet();
        animation.playTogether(ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f).setDuration(150L));
        return animation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AnimatorSet createExitAnimation(View view, int startDelay, Animator.AnimatorListener listener) {
        AnimatorSet animation = new AnimatorSet();
        animation.playTogether(ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f).setDuration(100L));
        animation.setStartDelay(startDelay);
        animation.addListener(listener);
        return animation;
    }

    private static Context applyDefaultTheme(Context originalContext) {
        TypedArray a = originalContext.obtainStyledAttributes(new int[]{16844176});
        boolean isLightTheme = a.getBoolean(0, true);
        int themeId = isLightTheme ? 16974123 : 16974120;
        a.recycle();
        return new ContextThemeWrapper(originalContext, themeId);
    }
}
