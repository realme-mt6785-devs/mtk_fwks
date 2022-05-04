package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import com.android.internal.R;
/* loaded from: classes4.dex */
public interface IFloatingToolbarPopupExt {
    public static final float NUM_0_5 = 0.5f;
    public static final float NUM_1_5 = 1.5f;

    default int hookFloatingToolbarPopupForLineHeight(Context context, int defaultFloatingToolBarHeightRes) {
        return context.getResources().getDimensionPixelSize(defaultFloatingToolBarHeightRes);
    }

    default Drawable hookFloatingToolbarPopupForArrow(Context context, int defaultArrowDrawableId) {
        return context.getResources().getDrawable(defaultArrowDrawableId, context.getTheme());
    }

    default Drawable hookFloatingToolbarPopupForOverflow(Context context, int defaultOverflowDrawableId) {
        return context.getResources().getDrawable(defaultOverflowDrawableId, context.getTheme());
    }

    default AnimatedVectorDrawable hookFloatingToolbarPopupForToArrow(Context context, int defaultToArrowAnimId) {
        return (AnimatedVectorDrawable) context.getResources().getDrawable(defaultToArrowAnimId, context.getTheme());
    }

    default AnimatedVectorDrawable hookFloatingToolbarPopupForToOverflow(Context context, int defaultToOverflowAnimId) {
        return (AnimatedVectorDrawable) context.getResources().getDrawable(defaultToOverflowAnimId, context.getTheme());
    }

    default void hookFloatingToolbarPopupAfterOverflowPanelViewHelper(IOverflowPanelViewHelperExt overflowPanelViewHelperExt) {
    }

    default int hookGetFirstItemPaddingStart(Context context, int paddingStart) {
        return (int) (paddingStart * 1.5f);
    }

    default int hookGetLastItemPaddingEnd(Context context, int paddingEnd) {
        return (int) (paddingEnd * 1.5f);
    }

    default void hookLayoutOverflowPanelItems(int overflowMenuCount) {
    }

    default void hookClearPanels(int overflowMenuCount) {
    }

    default int hookCalculateOverflowExtension(ViewGroup contentContainerView, int lineHeight) {
        return (int) (lineHeight * 0.5f);
    }

    default ImageButton hookCreateOverflowButton(Context context, int defaultOverflowButtonRes) {
        return (ImageButton) LayoutInflater.from(context).inflate(defaultOverflowButtonRes, (ViewGroup) null);
    }

    default void hookCreateOverflowPanelAfterOverflowPanel(ListView listview) {
    }

    default void hookCreateOverflowPanelAtAdapterGetView(int position, boolean upward) {
    }

    default int hookGetMarginVertical() {
        return R.dimen.floating_toolbar_vertical_margin;
    }

    default int hookGetOverflowBtnExtraStartX(View overflowButton) {
        return 0;
    }

    default int hookCalOverflowTotalItemHeight(Context context, int itemCount, int itemHeight) {
        return itemCount * itemHeight;
    }

    default void hookSetElevation(View view) {
    }
}
