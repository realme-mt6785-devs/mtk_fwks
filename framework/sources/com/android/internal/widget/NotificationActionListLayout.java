package com.android.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.RemotableViewMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.android.internal.R;
import java.util.ArrayList;
import java.util.Comparator;
@RemoteViews.RemoteView
/* loaded from: classes4.dex */
public class NotificationActionListLayout extends LinearLayout {
    public static final Comparator<TextViewInfo> MEASURE_ORDER_COMPARATOR = NotificationActionListLayout$$ExternalSyntheticLambda0.INSTANCE;
    private int mCollapsibleIndentDimen;
    private int mDefaultPaddingBottom;
    private int mDefaultPaddingTop;
    private int mEmphasizedHeight;
    private boolean mEmphasizedMode;
    private int mExtraStartPadding;
    private final int mGravity;
    private ArrayList<View> mMeasureOrderOther;
    private ArrayList<TextViewInfo> mMeasureOrderTextViews;
    private int mRegularHeight;
    private int mTotalWidth;

    public NotificationActionListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotificationActionListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NotificationActionListLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mTotalWidth = 0;
        this.mExtraStartPadding = 0;
        this.mMeasureOrderTextViews = new ArrayList<>();
        this.mMeasureOrderOther = new ArrayList<>();
        this.mCollapsibleIndentDimen = 0;
        int[] attrIds = {16842927};
        TypedArray ta = context.obtainStyledAttributes(attrs, attrIds, defStyleAttr, defStyleRes);
        this.mGravity = ta.getInt(0, 0);
        ta.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPriority(View actionView) {
        return (actionView instanceof EmphasizedNotificationButton) && ((EmphasizedNotificationButton) actionView).isPriority();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        boolean needRebuild;
        boolean isPriority;
        View c;
        int otherSize;
        int i2;
        int maxWidthForChild;
        int N = getChildCount();
        int i3 = 0;
        int textViews = 0;
        int otherViews = 0;
        int notGoneChildren = 0;
        int priorityChildren = 0;
        while (true) {
            i = 8;
            if (i3 >= N) {
                break;
            }
            View c2 = getChildAt(i3);
            if (c2 instanceof TextView) {
                textViews++;
            } else {
                otherViews++;
            }
            if (c2.getVisibility() != 8) {
                notGoneChildren++;
                if (isPriority(c2)) {
                    priorityChildren++;
                }
            }
            i3++;
        }
        boolean needRebuild2 = false;
        if (!(textViews == this.mMeasureOrderTextViews.size() && otherViews == this.mMeasureOrderOther.size())) {
            needRebuild2 = true;
        }
        if (!needRebuild2) {
            int size = this.mMeasureOrderTextViews.size();
            for (int i4 = 0; i4 < size; i4++) {
                if (this.mMeasureOrderTextViews.get(i4).needsRebuild()) {
                    needRebuild = true;
                    break;
                }
            }
        }
        needRebuild = needRebuild2;
        if (needRebuild) {
            rebuildMeasureOrder(textViews, otherViews);
        }
        boolean constrained = View.MeasureSpec.getMode(widthMeasureSpec) != 0;
        int innerWidth = (View.MeasureSpec.getSize(widthMeasureSpec) - this.mPaddingLeft) - this.mPaddingRight;
        int otherSize2 = this.mMeasureOrderOther.size();
        int usedWidth = 0;
        int measuredChildren = 0;
        int measuredPriorityChildren = 0;
        int i5 = 0;
        while (i5 < N) {
            if (i5 < otherSize2) {
                c = this.mMeasureOrderOther.get(i5);
                isPriority = false;
            } else {
                TextViewInfo info = this.mMeasureOrderTextViews.get(i5 - otherSize2);
                c = info.mTextView;
                isPriority = info.mIsPriority;
            }
            if (c.getVisibility() == i) {
                i2 = i5;
                otherSize = otherSize2;
            } else {
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) c.getLayoutParams();
                if (constrained) {
                    int availableWidth = innerWidth - usedWidth;
                    int unmeasuredChildren = notGoneChildren - measuredChildren;
                    int maxWidthForChild2 = availableWidth / unmeasuredChildren;
                    if (isPriority) {
                        int unmeasuredPriorityChildren = priorityChildren - measuredPriorityChildren;
                        int unmeasuredOtherChildren = unmeasuredChildren - unmeasuredPriorityChildren;
                        int widthReservedForOtherChildren = (innerWidth * unmeasuredOtherChildren) / 4;
                        int widthAvailableForPriority = availableWidth - widthReservedForOtherChildren;
                        maxWidthForChild2 = widthAvailableForPriority / unmeasuredPriorityChildren;
                    }
                    int usedWidthForChild = innerWidth - maxWidthForChild2;
                    maxWidthForChild = usedWidthForChild;
                } else {
                    maxWidthForChild = usedWidth;
                }
                i2 = i5;
                otherSize = otherSize2;
                measureChildWithMargins(c, widthMeasureSpec, maxWidthForChild, heightMeasureSpec, 0);
                usedWidth += c.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                measuredChildren++;
                if (isPriority) {
                    measuredPriorityChildren++;
                }
            }
            i5 = i2 + 1;
            otherSize2 = otherSize;
            i = 8;
        }
        int collapsibleIndent = this.mCollapsibleIndentDimen == 0 ? 0 : getResources().getDimensionPixelOffset(this.mCollapsibleIndentDimen);
        if (innerWidth - usedWidth > collapsibleIndent) {
            this.mExtraStartPadding = collapsibleIndent;
        } else {
            this.mExtraStartPadding = 0;
        }
        this.mTotalWidth = usedWidth + this.mPaddingRight + this.mPaddingLeft + this.mExtraStartPadding;
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec), resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    private void rebuildMeasureOrder(int capacityText, int capacityOther) {
        clearMeasureOrder();
        this.mMeasureOrderTextViews.ensureCapacity(capacityText);
        this.mMeasureOrderOther.ensureCapacity(capacityOther);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View c = getChildAt(i);
            if (!(c instanceof TextView) || ((TextView) c).getText().length() <= 0) {
                this.mMeasureOrderOther.add(c);
            } else {
                this.mMeasureOrderTextViews.add(new TextViewInfo((TextView) c));
            }
        }
        this.mMeasureOrderTextViews.sort(MEASURE_ORDER_COMPARATOR);
    }

    private void clearMeasureOrder() {
        this.mMeasureOrderOther.clear();
        this.mMeasureOrderTextViews.clear();
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        clearMeasureOrder();
        if (child.getBackground() instanceof RippleDrawable) {
            ((RippleDrawable) child.getBackground()).setForceSoftware(true);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        clearMeasureOrder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childLeft;
        int paddingTop;
        boolean isLayoutRtl;
        NotificationActionListLayout notificationActionListLayout = this;
        boolean isLayoutRtl2 = isLayoutRtl();
        int paddingTop2 = notificationActionListLayout.mPaddingTop;
        boolean centerAligned = true;
        if ((notificationActionListLayout.mGravity & 1) == 0) {
            centerAligned = false;
        }
        if (centerAligned) {
            childLeft = ((notificationActionListLayout.mPaddingLeft + left) + ((right - left) / 2)) - (notificationActionListLayout.mTotalWidth / 2);
        } else {
            int childLeft2 = notificationActionListLayout.mPaddingLeft;
            int absoluteGravity = Gravity.getAbsoluteGravity(Gravity.START, getLayoutDirection());
            if (absoluteGravity == 5) {
                childLeft = childLeft2 + ((right - left) - notificationActionListLayout.mTotalWidth);
            } else {
                childLeft = childLeft2 + notificationActionListLayout.mExtraStartPadding;
            }
        }
        int absoluteGravity2 = bottom - top;
        int i = (absoluteGravity2 - paddingTop2) - notificationActionListLayout.mPaddingBottom;
        int count = getChildCount();
        int start = 0;
        int dir = 1;
        if (isLayoutRtl2) {
            start = count - 1;
            dir = -1;
        }
        int i2 = 0;
        while (i2 < count) {
            int childIndex = (dir * i2) + start;
            View child = notificationActionListLayout.getChildAt(childIndex);
            if (child.getVisibility() != 8) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                int childTop = lp.topMargin + paddingTop2;
                isLayoutRtl = isLayoutRtl2;
                int childLeft3 = childLeft + lp.leftMargin;
                paddingTop = paddingTop2;
                child.layout(childLeft3, childTop, childLeft3 + childWidth, childTop + childHeight);
                childLeft = childLeft3 + lp.rightMargin + childWidth;
            } else {
                isLayoutRtl = isLayoutRtl2;
                paddingTop = paddingTop2;
            }
            i2++;
            notificationActionListLayout = this;
            isLayoutRtl2 = isLayoutRtl;
            paddingTop2 = paddingTop;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDefaultPaddingBottom = getPaddingBottom();
        this.mDefaultPaddingTop = getPaddingTop();
        updateHeights();
    }

    private void updateHeights() {
        int paddingTop = getResources().getDimensionPixelSize(R.dimen.notification_content_margin);
        int paddingBottom = getResources().getDimensionPixelSize(R.dimen.notification_content_margin_end);
        this.mEmphasizedHeight = paddingBottom + paddingTop + getResources().getDimensionPixelSize(R.dimen.notification_action_emphasized_height);
        this.mRegularHeight = getResources().getDimensionPixelSize(R.dimen.notification_action_list_height);
    }

    @RemotableViewMethod
    public void setCollapsibleIndentDimen(int collapsibleIndentDimen) {
        if (this.mCollapsibleIndentDimen != collapsibleIndentDimen) {
            this.mCollapsibleIndentDimen = collapsibleIndentDimen;
            requestLayout();
        }
    }

    @RemotableViewMethod
    public void setEmphasizedMode(boolean emphasizedMode) {
        int height;
        this.mEmphasizedMode = emphasizedMode;
        if (emphasizedMode) {
            int paddingTop = getResources().getDimensionPixelSize(R.dimen.notification_content_margin);
            int paddingBottom = getResources().getDimensionPixelSize(R.dimen.notification_content_margin_end);
            height = this.mEmphasizedHeight;
            int buttonPaddingInternal = getResources().getDimensionPixelSize(R.dimen.button_inset_vertical_material);
            setPaddingRelative(getPaddingStart(), paddingTop - buttonPaddingInternal, getPaddingEnd(), paddingBottom - buttonPaddingInternal);
        } else {
            setPaddingRelative(getPaddingStart(), this.mDefaultPaddingTop, getPaddingEnd(), this.mDefaultPaddingBottom);
            height = this.mRegularHeight;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
    }

    public int getExtraMeasureHeight() {
        if (this.mEmphasizedMode) {
            return this.mEmphasizedHeight - this.mRegularHeight;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(TextViewInfo a, TextViewInfo b) {
        int priorityComparison = -Boolean.compare(a.mIsPriority, b.mIsPriority);
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return Integer.compare(a.mTextLength, b.mTextLength);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class TextViewInfo {
        final boolean mIsPriority;
        final int mTextLength;
        final TextView mTextView;

        TextViewInfo(TextView textView) {
            this.mIsPriority = NotificationActionListLayout.isPriority(textView);
            this.mTextLength = textView.getText().length();
            this.mTextView = textView;
        }

        boolean needsRebuild() {
            return (this.mTextView.getText().length() == this.mTextLength && NotificationActionListLayout.isPriority(this.mTextView) == this.mIsPriority) ? false : true;
        }
    }
}
