package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.android.internal.R;
import java.util.Locale;
@RemoteViews.RemoteView
/* loaded from: classes4.dex */
public class NotificationExpandButton extends FrameLayout {
    private int mDefaultPillColor;
    private int mDefaultTextColor;
    private boolean mExpanded;
    private int mHighlightPillColor;
    private int mHighlightTextColor;
    private ImageView mIconView;
    private int mNumber;
    private TextView mNumberView;
    private View mPillView;

    public NotificationExpandButton(Context context) {
        this(context, null, 0, 0);
    }

    public NotificationExpandButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public NotificationExpandButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NotificationExpandButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mPillView = findViewById(R.id.expand_button_pill);
        this.mNumberView = (TextView) findViewById(R.id.expand_button_number);
        this.mIconView = (ImageView) findViewById(R.id.expand_button_icon);
    }

    @Override // android.view.View
    public void getBoundsOnScreen(Rect outRect, boolean clipToParent) {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent == null || parent.getId() != 16908962) {
            super.getBoundsOnScreen(outRect, clipToParent);
        } else {
            parent.getBoundsOnScreen(outRect, clipToParent);
        }
    }

    @Override // android.view.View
    public boolean pointInView(float localX, float localY, float slop) {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent == null || parent.getId() != 16908962) {
            return super.pointInView(localX, localY, slop);
        }
        return true;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(Button.class.getName());
    }

    @RemotableViewMethod
    public void setExpanded(boolean expanded) {
        this.mExpanded = expanded;
        updateExpandedState();
    }

    private void updateExpandedState() {
        int contentDescriptionId;
        int drawableId;
        if (this.mExpanded) {
            drawableId = R.drawable.ic_collapse_notification;
            contentDescriptionId = R.string.expand_button_content_description_expanded;
        } else {
            drawableId = R.drawable.ic_expand_notification;
            contentDescriptionId = R.string.expand_button_content_description_collapsed;
        }
        setContentDescription(this.mContext.getText(contentDescriptionId));
        this.mIconView.setImageDrawable(getContext().getDrawable(drawableId));
        updateNumber();
    }

    private void updateNumber() {
        CharSequence text;
        if (shouldShowNumber()) {
            if (this.mNumber >= 100) {
                text = getResources().getString(R.string.unread_convo_overflow, 99);
            } else {
                text = String.format(Locale.getDefault(), "%d", Integer.valueOf(this.mNumber));
            }
            this.mNumberView.setText(text);
            this.mNumberView.setVisibility(0);
            this.mIconView.setVisibility(8);
        } else {
            this.mNumberView.setVisibility(8);
            this.mIconView.setVisibility(0);
        }
        updateColors();
    }

    private void updateColors() {
        if (shouldShowNumber()) {
            this.mIconView.setColorFilter(this.mHighlightTextColor);
            int i = this.mHighlightTextColor;
            if (i != 0) {
                this.mNumberView.setTextColor(i);
                return;
            }
            return;
        }
        this.mIconView.setColorFilter(this.mDefaultTextColor);
        int i2 = this.mDefaultTextColor;
        if (i2 != 0) {
            this.mNumberView.setTextColor(i2);
        }
    }

    private boolean shouldShowNumber() {
        return !this.mExpanded && this.mNumber > 1;
    }

    @RemotableViewMethod
    public void setDefaultTextColor(int color) {
        this.mDefaultTextColor = color;
        updateColors();
    }

    @RemotableViewMethod
    public void setDefaultPillColor(int color) {
        this.mDefaultPillColor = color;
        updateColors();
    }

    @RemotableViewMethod
    public void setHighlightTextColor(int color) {
        this.mHighlightTextColor = color;
        updateColors();
    }

    @RemotableViewMethod
    public void setHighlightPillColor(int color) {
        this.mHighlightPillColor = color;
        updateColors();
    }

    @RemotableViewMethod
    public void setNumber(int number) {
        if (this.mNumber != number) {
            this.mNumber = number;
            updateNumber();
        }
    }
}
