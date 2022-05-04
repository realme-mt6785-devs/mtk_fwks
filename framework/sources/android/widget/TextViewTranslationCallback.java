package android.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.text.method.TranslationTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.translation.UiTranslationManager;
import android.view.translation.ViewTranslationCallback;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
/* loaded from: classes3.dex */
public class TextViewTranslationCallback implements ViewTranslationCallback {
    private static final char COMPAT_PAD_CHARACTER = 8194;
    private static final boolean DEBUG = Log.isLoggable(UiTranslationManager.LOG_TAG, 3);
    private static final String TAG = "TextViewTranslationCb";
    private ValueAnimator mAnimator;
    private CharSequence mContentDescription;
    private CharSequence mPaddedText;
    private TranslationTransformationMethod mTranslationTransformation;
    private boolean mIsShowingTranslation = false;
    private boolean mIsTextPaddingEnabled = false;
    private int mAnimationDurationMillis = 250;

    private void clearTranslationTransformation() {
        if (DEBUG) {
            Log.v(TAG, "clearTranslationTransformation: " + this.mTranslationTransformation);
        }
        this.mTranslationTransformation = null;
    }

    @Override // android.view.translation.ViewTranslationCallback
    public boolean onShowTranslation(final View view) {
        ViewTranslationResponse response = view.getViewTranslationResponse();
        if (response == null) {
            Log.e(TAG, "onShowTranslation() shouldn't be called before onViewTranslationResponse().");
            return false;
        }
        if (this.mTranslationTransformation == null) {
            TransformationMethod originalTranslationMethod = ((TextView) view).getTransformationMethod();
            this.mTranslationTransformation = new TranslationTransformationMethod(response, originalTranslationMethod);
        }
        final TransformationMethod originalTranslationMethod2 = this.mTranslationTransformation;
        runWithAnimation((TextView) view, new Runnable() { // from class: android.widget.TextViewTranslationCallback$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TextViewTranslationCallback.this.lambda$onShowTranslation$0$TextViewTranslationCallback(view, originalTranslationMethod2);
            }
        });
        if (!response.getKeys().contains(ViewTranslationRequest.ID_CONTENT_DESCRIPTION)) {
            return true;
        }
        CharSequence translatedContentDescription = response.getValue(ViewTranslationRequest.ID_CONTENT_DESCRIPTION).getText();
        if (TextUtils.isEmpty(translatedContentDescription)) {
            return true;
        }
        this.mContentDescription = view.getContentDescription();
        view.setContentDescription(translatedContentDescription);
        return true;
    }

    public /* synthetic */ void lambda$onShowTranslation$0$TextViewTranslationCallback(View view, TransformationMethod transformation) {
        this.mIsShowingTranslation = true;
        ((TextView) view).setTransformationMethod(transformation);
    }

    @Override // android.view.translation.ViewTranslationCallback
    public boolean onHideTranslation(final View view) {
        if (view.getViewTranslationResponse() == null) {
            Log.e(TAG, "onHideTranslation() shouldn't be called before onViewTranslationResponse().");
            return false;
        }
        TranslationTransformationMethod translationTransformationMethod = this.mTranslationTransformation;
        if (translationTransformationMethod != null) {
            final TransformationMethod transformation = translationTransformationMethod.getOriginalTransformationMethod();
            runWithAnimation((TextView) view, new Runnable() { // from class: android.widget.TextViewTranslationCallback$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    TextViewTranslationCallback.this.lambda$onHideTranslation$1$TextViewTranslationCallback(view, transformation);
                }
            });
            if (TextUtils.isEmpty(this.mContentDescription)) {
                return true;
            }
            view.setContentDescription(this.mContentDescription);
            return true;
        }
        if (DEBUG) {
            Log.w(TAG, "onHideTranslation(): no translated text.");
        }
        return false;
    }

    public /* synthetic */ void lambda$onHideTranslation$1$TextViewTranslationCallback(View view, TransformationMethod transformation) {
        this.mIsShowingTranslation = false;
        ((TextView) view).setTransformationMethod(transformation);
    }

    @Override // android.view.translation.ViewTranslationCallback
    public boolean onClearTranslation(View view) {
        if (this.mTranslationTransformation != null) {
            onHideTranslation(view);
            clearTranslationTransformation();
            this.mPaddedText = null;
            this.mContentDescription = null;
            return true;
        } else if (!DEBUG) {
            return false;
        } else {
            Log.w(TAG, "onClearTranslation(): no translated text.");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isShowingTranslation() {
        return this.mIsShowingTranslation;
    }

    @Override // android.view.translation.ViewTranslationCallback
    public void enableContentPadding() {
        this.mIsTextPaddingEnabled = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTextPaddingEnabled() {
        return this.mIsTextPaddingEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence getPaddedText(CharSequence text, CharSequence translatedText) {
        if (text == null) {
            return null;
        }
        if (this.mPaddedText == null) {
            this.mPaddedText = computePaddedText(text, translatedText);
        }
        return this.mPaddedText;
    }

    private CharSequence computePaddedText(CharSequence text, CharSequence translatedText) {
        if (translatedText == null) {
            return text;
        }
        int newLength = translatedText.length();
        if (newLength <= text.length()) {
            return text;
        }
        StringBuilder sb = new StringBuilder(newLength);
        sb.append(text);
        for (int i = text.length(); i < newLength; i++) {
            sb.append(COMPAT_PAD_CHARACTER);
        }
        return sb;
    }

    @Override // android.view.translation.ViewTranslationCallback
    public void setAnimationDurationMillis(int durationMillis) {
        this.mAnimationDurationMillis = durationMillis;
    }

    private void runWithAnimation(final TextView view, final Runnable runnable) {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        int fadedOutColor = colorWithAlpha(view.getCurrentTextColor(), 0);
        ValueAnimator ofArgb = ValueAnimator.ofArgb(view.getCurrentTextColor(), fadedOutColor);
        this.mAnimator = ofArgb;
        ofArgb.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.widget.TextViewTranslationCallback$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                TextView.this.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
            }
        });
        this.mAnimator.setRepeatMode(2);
        this.mAnimator.setRepeatCount(1);
        this.mAnimator.setDuration(this.mAnimationDurationMillis);
        final ColorStateList originalColors = view.getTextColors();
        this.mAnimator.addListener(new Animator.AnimatorListener() { // from class: android.widget.TextViewTranslationCallback.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                view.setTextColor(originalColors);
                TextViewTranslationCallback.this.mAnimator = null;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                runnable.run();
            }
        });
        this.mAnimator.start();
    }

    private static int colorWithAlpha(int color, int newAlpha) {
        return Color.argb(newAlpha, Color.red(color), Color.green(color), Color.blue(color));
    }
}
