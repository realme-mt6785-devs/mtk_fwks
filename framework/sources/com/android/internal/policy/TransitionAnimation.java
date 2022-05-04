package com.android.internal.policy;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ResourceId;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.os.SystemProperties;
import android.util.Slog;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ClipRectAnimation;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.android.internal.R;
import com.android.internal.policy.AttributeCache;
import java.util.List;
/* loaded from: classes4.dex */
public class TransitionAnimation {
    private static final int CLIP_REVEAL_TRANSLATION_Y_DP = 8;
    public static final int DEFAULT_APP_TRANSITION_DURATION = 336;
    private static final String DEFAULT_PACKAGE = "android";
    private static final int MAX_CLIP_REVEAL_TRANSITION_DURATION = 420;
    private static final float RECENTS_THUMBNAIL_FADEOUT_FRACTION = 0.5f;
    private static final int THUMBNAIL_APP_TRANSITION_DURATION = 336;
    public static final int THUMBNAIL_TRANSITION_ENTER_SCALE_DOWN = 2;
    public static final int THUMBNAIL_TRANSITION_ENTER_SCALE_UP = 0;
    public static final int THUMBNAIL_TRANSITION_EXIT_SCALE_DOWN = 3;
    public static final int THUMBNAIL_TRANSITION_EXIT_SCALE_UP = 1;
    private final int mClipRevealTranslationY;
    private final int mConfigShortAnimTime;
    private final Context mContext;
    private final boolean mDebug;
    private final Interpolator mDecelerateInterpolator;
    private final int mDefaultWindowAnimationStyleResId;
    private final Interpolator mLinearOutSlowInInterpolator;
    private final String mTag;
    private final LogDecelerateInterpolator mInterpolator = new LogDecelerateInterpolator(100, 0);
    private final Interpolator mTouchResponseInterpolator = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    private final Interpolator mClipHorizontalInterpolator = new PathInterpolator(0.0f, 0.0f, 0.4f, 1.0f);
    private final Rect mTmpFromClipRect = new Rect();
    private final Rect mTmpToClipRect = new Rect();
    private final Rect mTmpRect = new Rect();
    private final Interpolator mThumbnailFadeOutInterpolator = new Interpolator() { // from class: com.android.internal.policy.TransitionAnimation$$ExternalSyntheticLambda0
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return TransitionAnimation.this.lambda$new$0$TransitionAnimation(f);
        }
    };
    private final boolean mGridLayoutRecentsEnabled = SystemProperties.getBoolean("ro.recents.grid", false);
    private final boolean mLowRamRecentsEnabled = ActivityManager.isLowRamDeviceStatic();

    public TransitionAnimation(Context context, boolean debug, String tag) {
        this.mContext = context;
        this.mDebug = debug;
        this.mTag = tag;
        this.mDecelerateInterpolator = AnimationUtils.loadInterpolator(context, 17563651);
        this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, 17563662);
        this.mClipRevealTranslationY = (int) (context.getResources().getDisplayMetrics().density * 8.0f);
        this.mConfigShortAnimTime = context.getResources().getInteger(17694720);
        TypedArray windowStyle = context.getTheme().obtainStyledAttributes(R.styleable.Window);
        this.mDefaultWindowAnimationStyleResId = windowStyle.getResourceId(8, 0);
        windowStyle.recycle();
    }

    public /* synthetic */ float lambda$new$0$TransitionAnimation(float input) {
        if (input >= 0.5f) {
            return 1.0f;
        }
        float t = input / 0.5f;
        return this.mLinearOutSlowInInterpolator.getInterpolation(t);
    }

    public Animation loadKeyguardExitAnimation(int transitionFlags, boolean onWallpaper) {
        if ((transitionFlags & 2) != 0) {
            return null;
        }
        boolean subtle = false;
        boolean toShade = (transitionFlags & 1) != 0;
        if ((transitionFlags & 8) != 0) {
            subtle = true;
        }
        return createHiddenByKeyguardExit(this.mContext, this.mInterpolator, onWallpaper, toShade, subtle);
    }

    public Animation loadKeyguardUnoccludeAnimation() {
        return loadDefaultAnimationRes(R.anim.wallpaper_open_exit);
    }

    public Animation loadVoiceActivityOpenAnimation(boolean enter) {
        int i;
        if (enter) {
            i = R.anim.voice_activity_open_enter;
        } else {
            i = R.anim.voice_activity_open_exit;
        }
        return loadDefaultAnimationRes(i);
    }

    public Animation loadVoiceActivityExitAnimation(boolean enter) {
        int i;
        if (enter) {
            i = R.anim.voice_activity_close_enter;
        } else {
            i = R.anim.voice_activity_close_exit;
        }
        return loadDefaultAnimationRes(i);
    }

    public Animation loadAppTransitionAnimation(String packageName, int resId) {
        return loadAnimationRes(packageName, resId);
    }

    public Animation loadCrossProfileAppEnterAnimation() {
        return loadAnimationRes("android", R.anim.task_open_enter_cross_profile_apps);
    }

    public Animation loadCrossProfileAppThumbnailEnterAnimation() {
        return loadAnimationRes("android", R.anim.cross_profile_apps_thumbnail_enter);
    }

    public Animation loadAnimationRes(String packageName, int resId) {
        AttributeCache.Entry ent;
        if (!ResourceId.isValid(resId) || (ent = getCachedAnimations(packageName, resId)) == null) {
            return null;
        }
        return loadAnimationSafely(ent.context, resId, this.mTag);
    }

    public Animation loadDefaultAnimationRes(int resId) {
        return loadAnimationRes("android", resId);
    }

    public Animation loadAnimationAttr(WindowManager.LayoutParams lp, int animAttr, int transit) {
        AttributeCache.Entry ent;
        int resId = 0;
        Context context = this.mContext;
        if (animAttr >= 0 && (ent = getCachedAnimations(lp)) != null) {
            context = ent.context;
            resId = ent.array.getResourceId(animAttr, 0);
        }
        int resId2 = updateToTranslucentAnimIfNeeded(resId, transit);
        if (ResourceId.isValid(resId2)) {
            return loadAnimationSafely(context, resId2, this.mTag);
        }
        return null;
    }

    public Animation loadDefaultAnimationAttr(int animAttr) {
        AttributeCache.Entry ent;
        int resId = 0;
        Context context = this.mContext;
        if (animAttr >= 0 && (ent = getCachedAnimations("android", this.mDefaultWindowAnimationStyleResId)) != null) {
            context = ent.context;
            resId = ent.array.getResourceId(animAttr, 0);
        }
        if (ResourceId.isValid(resId)) {
            return loadAnimationSafely(context, resId, this.mTag);
        }
        return null;
    }

    private AttributeCache.Entry getCachedAnimations(WindowManager.LayoutParams lp) {
        if (this.mDebug) {
            String str = this.mTag;
            StringBuilder sb = new StringBuilder();
            sb.append("Loading animations: layout params pkg=");
            sb.append(lp != null ? lp.packageName : null);
            sb.append(" resId=0x");
            sb.append(lp != null ? Integer.toHexString(lp.windowAnimations) : null);
            Slog.v(str, sb.toString());
        }
        if (lp == null || lp.windowAnimations == 0) {
            return null;
        }
        String packageName = lp.packageName != null ? lp.packageName : "android";
        int resId = getAnimationStyleResId(lp);
        if (((-16777216) & resId) == 16777216) {
            packageName = "android";
        }
        if (this.mDebug) {
            String str2 = this.mTag;
            Slog.v(str2, "Loading animations: picked package=" + packageName);
        }
        return AttributeCache.instance().get(packageName, resId, R.styleable.WindowAnimation);
    }

    private AttributeCache.Entry getCachedAnimations(String packageName, int resId) {
        if (this.mDebug) {
            String str = this.mTag;
            Slog.v(str, "Loading animations: package=" + packageName + " resId=0x" + Integer.toHexString(resId));
        }
        if (packageName == null) {
            return null;
        }
        if (((-16777216) & resId) == 16777216) {
            packageName = "android";
        }
        if (this.mDebug) {
            String str2 = this.mTag;
            Slog.v(str2, "Loading animations: picked package=" + packageName);
        }
        return AttributeCache.instance().get(packageName, resId, R.styleable.WindowAnimation);
    }

    public int getAnimationStyleResId(WindowManager.LayoutParams lp) {
        int resId = lp.windowAnimations;
        if (lp.type != 3) {
            return resId;
        }
        int resId2 = this.mDefaultWindowAnimationStyleResId;
        return resId2;
    }

    public Animation createRelaunchAnimation(Rect containingFrame, Rect contentInsets, Rect startRect) {
        setupDefaultNextAppTransitionStartRect(startRect, this.mTmpFromClipRect);
        int left = this.mTmpFromClipRect.left;
        int top = this.mTmpFromClipRect.top;
        this.mTmpFromClipRect.offset(-left, -top);
        this.mTmpToClipRect.set(0, 0, containingFrame.width(), containingFrame.height());
        AnimationSet set = new AnimationSet(true);
        float fromWidth = this.mTmpFromClipRect.width();
        float toWidth = this.mTmpToClipRect.width();
        float fromHeight = this.mTmpFromClipRect.height();
        float toHeight = (this.mTmpToClipRect.height() - contentInsets.top) - contentInsets.bottom;
        int translateAdjustment = 0;
        if (fromWidth > toWidth || fromHeight > toHeight) {
            set.addAnimation(new ScaleAnimation(fromWidth / toWidth, 1.0f, fromHeight / toHeight, 1.0f));
            translateAdjustment = (int) ((contentInsets.top * fromHeight) / toHeight);
        } else {
            set.addAnimation(new ClipRectAnimation(this.mTmpFromClipRect, this.mTmpToClipRect));
        }
        TranslateAnimation translate = new TranslateAnimation(left - containingFrame.left, 0.0f, (top - containingFrame.top) - translateAdjustment, 0.0f);
        set.addAnimation(translate);
        set.setDuration(336L);
        set.setZAdjustment(1);
        return set;
    }

    private void setupDefaultNextAppTransitionStartRect(Rect startRect, Rect rect) {
        if (startRect == null) {
            Slog.e(this.mTag, "Starting rect for app requested, but none available", new Throwable());
            rect.setEmpty();
            return;
        }
        rect.set(startRect);
    }

    public Animation createClipRevealAnimationLocked(int transit, boolean enter, Rect appFrame, Rect displayFrame, Rect startRect) {
        long duration;
        boolean z;
        Animation anim;
        float f;
        float t;
        int clipStartY;
        int translationYCorrection;
        int translationY;
        int translationX;
        int clipStartX;
        boolean cutOff;
        Interpolator interpolator;
        if (enter) {
            int appWidth = appFrame.width();
            int appHeight = appFrame.height();
            setupDefaultNextAppTransitionStartRect(startRect, this.mTmpRect);
            if (appHeight > 0) {
                float t2 = this.mTmpRect.top / displayFrame.height();
                t = t2;
            } else {
                t = 0.0f;
            }
            int translationY2 = this.mClipRevealTranslationY + ((int) ((displayFrame.height() / 7.0f) * t));
            int translationX2 = 0;
            int centerX = this.mTmpRect.centerX();
            int centerY = this.mTmpRect.centerY();
            int halfWidth = this.mTmpRect.width() / 2;
            int halfHeight = this.mTmpRect.height() / 2;
            int clipStartX2 = (centerX - halfWidth) - appFrame.left;
            int clipStartY2 = (centerY - halfHeight) - appFrame.top;
            boolean cutOff2 = false;
            if (appFrame.top > centerY - halfHeight) {
                cutOff2 = true;
                translationY = (centerY - halfHeight) - appFrame.top;
                translationYCorrection = 0;
                clipStartY = 0;
            } else {
                translationY = translationY2;
                translationYCorrection = translationY2;
                clipStartY = clipStartY2;
            }
            if (appFrame.left > centerX - halfWidth) {
                translationX2 = (centerX - halfWidth) - appFrame.left;
                clipStartX2 = 0;
                cutOff2 = true;
            }
            if (appFrame.right < centerX + halfWidth) {
                int translationX3 = (centerX + halfWidth) - appFrame.right;
                int clipStartX3 = appWidth - this.mTmpRect.width();
                translationX = translationX3;
                cutOff = true;
                clipStartX = clipStartX3;
            } else {
                translationX = translationX2;
                cutOff = cutOff2;
                clipStartX = clipStartX2;
            }
            long duration2 = calculateClipRevealTransitionDuration(cutOff, translationX, translationY, displayFrame);
            Animation clipAnimLR = new ClipRectLRAnimation(clipStartX, this.mTmpRect.width() + clipStartX, 0, appWidth);
            clipAnimLR.setInterpolator(this.mClipHorizontalInterpolator);
            clipAnimLR.setDuration(((float) duration2) / 2.5f);
            TranslateAnimation translate = new TranslateAnimation(translationX, 0.0f, translationY, 0.0f);
            if (cutOff) {
                interpolator = this.mTouchResponseInterpolator;
            } else {
                interpolator = this.mLinearOutSlowInInterpolator;
            }
            translate.setInterpolator(interpolator);
            translate.setDuration(duration2);
            Animation clipAnimTB = new ClipRectTBAnimation(clipStartY, clipStartY + this.mTmpRect.height(), 0, appHeight, translationYCorrection, 0, this.mLinearOutSlowInInterpolator);
            clipAnimTB.setInterpolator(this.mTouchResponseInterpolator);
            clipAnimTB.setDuration(duration2);
            long alphaDuration = duration2 / 4;
            AlphaAnimation alpha = new AlphaAnimation(0.5f, 1.0f);
            alpha.setDuration(alphaDuration);
            alpha.setInterpolator(this.mLinearOutSlowInInterpolator);
            AnimationSet set = new AnimationSet(false);
            set.addAnimation(clipAnimLR);
            set.addAnimation(clipAnimTB);
            set.addAnimation(translate);
            set.addAnimation(alpha);
            set.setZAdjustment(1);
            set.initialize(appWidth, appHeight, appWidth, appHeight);
            return set;
        }
        switch (transit) {
            case 6:
            case 7:
                duration = this.mConfigShortAnimTime;
                break;
            default:
                duration = 336;
                break;
        }
        if (transit == 14) {
            f = 1.0f;
        } else if (transit == 15) {
            f = 1.0f;
        } else {
            anim = new AlphaAnimation(1.0f, 1.0f);
            z = true;
            anim.setInterpolator(this.mDecelerateInterpolator);
            anim.setDuration(duration);
            anim.setFillAfter(z);
            return anim;
        }
        anim = new AlphaAnimation(f, 0.0f);
        z = true;
        anim.setDetachWallpaper(true);
        anim.setInterpolator(this.mDecelerateInterpolator);
        anim.setDuration(duration);
        anim.setFillAfter(z);
        return anim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Animation createScaleUpAnimationLocked(int transit, boolean enter, Rect containingFrame, Rect startRect) {
        Animation alpha;
        long duration;
        setupDefaultNextAppTransitionStartRect(startRect, this.mTmpRect);
        int appWidth = containingFrame.width();
        int appHeight = containingFrame.height();
        if (enter) {
            float scaleW = this.mTmpRect.width() / appWidth;
            float scaleH = this.mTmpRect.height() / appHeight;
            Animation scale = new ScaleAnimation(scaleW, 1.0f, scaleH, 1.0f, computePivot(this.mTmpRect.left, scaleW), computePivot(this.mTmpRect.top, scaleH));
            scale.setInterpolator(this.mDecelerateInterpolator);
            Animation alpha2 = new AlphaAnimation(0.0f, 1.0f);
            alpha2.setInterpolator(this.mThumbnailFadeOutInterpolator);
            AnimationSet set = new AnimationSet(false);
            set.addAnimation(scale);
            set.addAnimation(alpha2);
            set.setDetachWallpaper(true);
            alpha = set;
        } else if (transit == 14 || transit == 15) {
            alpha = new AlphaAnimation(1.0f, 0.0f);
            alpha.setDetachWallpaper(true);
        } else {
            alpha = new AlphaAnimation(1.0f, 1.0f);
        }
        switch (transit) {
            case 6:
            case 7:
                duration = this.mConfigShortAnimTime;
                break;
            default:
                duration = 336;
                break;
        }
        alpha.setDuration(duration);
        alpha.setFillAfter(true);
        alpha.setInterpolator(this.mDecelerateInterpolator);
        alpha.initialize(appWidth, appHeight, appWidth, appHeight);
        return alpha;
    }

    public Animation createThumbnailEnterExitAnimationLocked(int thumbTransitState, Rect containingFrame, int transit, HardwareBuffer thumbnailHeader, Rect startRect) {
        Animation a;
        int appWidth = containingFrame.width();
        int appHeight = containingFrame.height();
        setupDefaultNextAppTransitionStartRect(startRect, this.mTmpRect);
        int thumbWidthI = thumbnailHeader != null ? thumbnailHeader.getWidth() : appWidth;
        float thumbWidth = thumbWidthI > 0 ? thumbWidthI : 1.0f;
        int thumbHeightI = thumbnailHeader != null ? thumbnailHeader.getHeight() : appHeight;
        float thumbHeight = thumbHeightI > 0 ? thumbHeightI : 1.0f;
        switch (thumbTransitState) {
            case 0:
                float scaleW = thumbWidth / appWidth;
                float scaleH = thumbHeight / appHeight;
                a = new ScaleAnimation(scaleW, 1.0f, scaleH, 1.0f, computePivot(this.mTmpRect.left, scaleW), computePivot(this.mTmpRect.top, scaleH));
                break;
            case 1:
                if (transit != 14) {
                    a = new AlphaAnimation(1.0f, 1.0f);
                    break;
                } else {
                    a = new AlphaAnimation(1.0f, 0.0f);
                    break;
                }
            case 2:
                a = new AlphaAnimation(1.0f, 1.0f);
                break;
            case 3:
                float scaleW2 = thumbWidth / appWidth;
                float scaleH2 = thumbHeight / appHeight;
                Animation scale = new ScaleAnimation(1.0f, scaleW2, 1.0f, scaleH2, computePivot(this.mTmpRect.left, scaleW2), computePivot(this.mTmpRect.top, scaleH2));
                Animation alpha = new AlphaAnimation(1.0f, 0.0f);
                AnimationSet set = new AnimationSet(true);
                set.addAnimation(scale);
                set.addAnimation(alpha);
                set.setZAdjustment(1);
                a = set;
                break;
            default:
                throw new RuntimeException("Invalid thumbnail transition state");
        }
        return prepareThumbnailAnimation(a, appWidth, appHeight, transit);
    }

    public Animation createAspectScaledThumbnailEnterExitAnimationLocked(int thumbTransitState, int orientation, int transit, Rect containingFrame, Rect contentInsets, Rect surfaceInsets, Rect stableInsets, boolean freeform, Rect startRect, Rect defaultStartRect) {
        int appHeight;
        Animation a;
        Animation clipAnim;
        Animation translateAnim;
        Animation clipAnim2;
        Animation translateAnim2;
        int appWidth = containingFrame.width();
        int appHeight2 = containingFrame.height();
        setupDefaultNextAppTransitionStartRect(defaultStartRect, this.mTmpRect);
        int thumbWidthI = this.mTmpRect.width();
        float thumbWidth = thumbWidthI > 0 ? thumbWidthI : 1.0f;
        int thumbHeightI = this.mTmpRect.height();
        float thumbHeight = thumbHeightI > 0 ? thumbHeightI : 1.0f;
        int thumbStartX = (this.mTmpRect.left - containingFrame.left) - contentInsets.left;
        int thumbStartY = this.mTmpRect.top - containingFrame.top;
        switch (thumbTransitState) {
            case 0:
            case 3:
                boolean scaleUp = thumbTransitState == 0;
                if (!freeform || !scaleUp) {
                    if (!freeform) {
                        AnimationSet set = new AnimationSet(true);
                        this.mTmpFromClipRect.set(containingFrame);
                        this.mTmpToClipRect.set(containingFrame);
                        this.mTmpFromClipRect.offsetTo(0, 0);
                        this.mTmpToClipRect.offsetTo(0, 0);
                        this.mTmpFromClipRect.inset(contentInsets);
                        if (shouldScaleDownThumbnailTransition(orientation)) {
                            float scale = thumbWidth / ((appWidth - contentInsets.left) - contentInsets.right);
                            if (!this.mGridLayoutRecentsEnabled) {
                                int unscaledThumbHeight = (int) (thumbHeight / scale);
                                Rect rect = this.mTmpFromClipRect;
                                rect.bottom = rect.top + unscaledThumbHeight;
                            }
                            Animation scaleAnim = new ScaleAnimation(scaleUp ? scale : 1.0f, scaleUp ? 1.0f : scale, scaleUp ? scale : 1.0f, scaleUp ? 1.0f : scale, containingFrame.width() / 2.0f, (containingFrame.height() / 2.0f) + contentInsets.top);
                            float targetX = this.mTmpRect.left - containingFrame.left;
                            float x = (containingFrame.width() / 2.0f) - ((containingFrame.width() / 2.0f) * scale);
                            float targetY = this.mTmpRect.top - containingFrame.top;
                            float y = (containingFrame.height() / 2.0f) - ((containingFrame.height() / 2.0f) * scale);
                            if (!this.mLowRamRecentsEnabled || contentInsets.top != 0 || !scaleUp) {
                                appHeight = appHeight2;
                            } else {
                                appHeight = appHeight2;
                                this.mTmpFromClipRect.top += stableInsets.top;
                                y += stableInsets.top;
                            }
                            float scale2 = targetX - x;
                            float startY = targetY - y;
                            if (scaleUp) {
                                clipAnim2 = new ClipRectAnimation(this.mTmpFromClipRect, this.mTmpToClipRect);
                            } else {
                                clipAnim2 = new ClipRectAnimation(this.mTmpToClipRect, this.mTmpFromClipRect);
                            }
                            if (scaleUp) {
                                translateAnim2 = createCurvedMotion(scale2, 0.0f, startY - contentInsets.top, 0.0f);
                            } else {
                                translateAnim2 = createCurvedMotion(0.0f, scale2, 0.0f, startY - contentInsets.top);
                            }
                            set.addAnimation(clipAnim2);
                            set.addAnimation(scaleAnim);
                            set.addAnimation(translateAnim2);
                        } else {
                            appHeight = appHeight2;
                            Rect rect2 = this.mTmpFromClipRect;
                            rect2.bottom = rect2.top + thumbHeightI;
                            Rect rect3 = this.mTmpFromClipRect;
                            rect3.right = rect3.left + thumbWidthI;
                            if (scaleUp) {
                                clipAnim = new ClipRectAnimation(this.mTmpFromClipRect, this.mTmpToClipRect);
                            } else {
                                clipAnim = new ClipRectAnimation(this.mTmpToClipRect, this.mTmpFromClipRect);
                            }
                            if (scaleUp) {
                                translateAnim = createCurvedMotion(thumbStartX, 0.0f, thumbStartY - contentInsets.top, 0.0f);
                            } else {
                                translateAnim = createCurvedMotion(0.0f, thumbStartX, 0.0f, thumbStartY - contentInsets.top);
                            }
                            set.addAnimation(clipAnim);
                            set.addAnimation(translateAnim);
                        }
                        set.setZAdjustment(1);
                        a = set;
                        break;
                    } else {
                        a = createAspectScaledThumbnailExitFreeformAnimationLocked(containingFrame, surfaceInsets, startRect, defaultStartRect);
                        appHeight = appHeight2;
                        break;
                    }
                } else {
                    a = createAspectScaledThumbnailEnterFreeformAnimationLocked(containingFrame, surfaceInsets, startRect, defaultStartRect);
                    appHeight = appHeight2;
                    break;
                }
                break;
            case 1:
                if (transit != 14) {
                    a = new AlphaAnimation(1.0f, 1.0f);
                    appHeight = appHeight2;
                    break;
                } else {
                    a = new AlphaAnimation(1.0f, 0.0f);
                    appHeight = appHeight2;
                    break;
                }
            case 2:
                if (transit != 14) {
                    a = new AlphaAnimation(1.0f, 1.0f);
                    appHeight = appHeight2;
                    break;
                } else {
                    a = new AlphaAnimation(0.0f, 1.0f);
                    appHeight = appHeight2;
                    break;
                }
            default:
                throw new RuntimeException("Invalid thumbnail transition state");
        }
        return prepareThumbnailAnimationWithDuration(a, appWidth, appHeight, 336L, this.mTouchResponseInterpolator);
    }

    private Animation prepareThumbnailAnimation(Animation a, int appWidth, int appHeight, int transit) {
        int duration;
        switch (transit) {
            case 6:
            case 7:
                duration = this.mConfigShortAnimTime;
                break;
            default:
                duration = 336;
                break;
        }
        return prepareThumbnailAnimationWithDuration(a, appWidth, appHeight, duration, this.mDecelerateInterpolator);
    }

    private Animation createAspectScaledThumbnailEnterFreeformAnimationLocked(Rect frame, Rect surfaceInsets, Rect startRect, Rect defaultStartRect) {
        getNextAppTransitionStartRect(startRect, defaultStartRect, this.mTmpRect);
        return createAspectScaledThumbnailFreeformAnimationLocked(this.mTmpRect, frame, surfaceInsets, true);
    }

    private Animation createAspectScaledThumbnailExitFreeformAnimationLocked(Rect frame, Rect surfaceInsets, Rect startRect, Rect defaultStartRect) {
        getNextAppTransitionStartRect(startRect, defaultStartRect, this.mTmpRect);
        return createAspectScaledThumbnailFreeformAnimationLocked(frame, this.mTmpRect, surfaceInsets, false);
    }

    private void getNextAppTransitionStartRect(Rect startRect, Rect defaultStartRect, Rect rect) {
        if (startRect == null && defaultStartRect == null) {
            Slog.e(this.mTag, "Starting rect for container not available", new Throwable());
            rect.setEmpty();
            return;
        }
        rect.set(startRect != null ? startRect : defaultStartRect);
    }

    private AnimationSet createAspectScaledThumbnailFreeformAnimationLocked(Rect sourceFrame, Rect destFrame, Rect surfaceInsets, boolean enter) {
        ScaleAnimation scale;
        TranslateAnimation translation;
        float sourceWidth = sourceFrame.width();
        float sourceHeight = sourceFrame.height();
        float destWidth = destFrame.width();
        float destHeight = destFrame.height();
        float scaleH = enter ? sourceWidth / destWidth : destWidth / sourceWidth;
        float scaleV = enter ? sourceHeight / destHeight : destHeight / sourceHeight;
        AnimationSet set = new AnimationSet(true);
        int surfaceInsetsV = 0;
        int surfaceInsetsH = surfaceInsets == null ? 0 : surfaceInsets.left + surfaceInsets.right;
        if (surfaceInsets != null) {
            surfaceInsetsV = surfaceInsets.top + surfaceInsets.bottom;
        }
        float scaleHCenter = ((enter ? destWidth : sourceWidth) + surfaceInsetsH) / 2.0f;
        float scaleVCenter = ((enter ? destHeight : sourceHeight) + surfaceInsetsV) / 2.0f;
        if (enter) {
            scale = new ScaleAnimation(scaleH, 1.0f, scaleV, 1.0f, scaleHCenter, scaleVCenter);
        } else {
            scale = new ScaleAnimation(1.0f, scaleH, 1.0f, scaleV, scaleHCenter, scaleVCenter);
        }
        int sourceHCenter = sourceFrame.left + (sourceFrame.width() / 2);
        int sourceVCenter = sourceFrame.top + (sourceFrame.height() / 2);
        int destHCenter = destFrame.left + (destFrame.width() / 2);
        int destVCenter = destFrame.top + (destFrame.height() / 2);
        int fromX = enter ? sourceHCenter - destHCenter : destHCenter - sourceHCenter;
        int fromY = enter ? sourceVCenter - destVCenter : destVCenter - sourceVCenter;
        if (enter) {
            translation = new TranslateAnimation(fromX, 0.0f, fromY, 0.0f);
        } else {
            translation = new TranslateAnimation(0.0f, fromX, 0.0f, fromY);
        }
        set.addAnimation(scale);
        set.addAnimation(translation);
        return set;
    }

    private boolean shouldScaleDownThumbnailTransition(int orientation) {
        return this.mGridLayoutRecentsEnabled || orientation == 1;
    }

    private static int updateToTranslucentAnimIfNeeded(int anim, int transit) {
        if (transit == 24 && anim == 17432591) {
            return R.anim.activity_translucent_open_enter;
        }
        if (transit == 25 && anim == 17432590) {
            return R.anim.activity_translucent_close_exit;
        }
        return anim;
    }

    private static long calculateClipRevealTransitionDuration(boolean cutOff, float translationX, float translationY, Rect displayFrame) {
        if (!cutOff) {
            return 336L;
        }
        float fraction = Math.max(Math.abs(translationX) / displayFrame.width(), Math.abs(translationY) / displayFrame.height());
        return (84.0f * fraction) + 336.0f;
    }

    private static Animation prepareThumbnailAnimationWithDuration(Animation a, int appWidth, int appHeight, long duration, Interpolator interpolator) {
        if (duration > 0) {
            a.setDuration(duration);
        }
        a.setFillAfter(true);
        if (interpolator != null) {
            a.setInterpolator(interpolator);
        }
        a.initialize(appWidth, appHeight, appWidth, appHeight);
        return a;
    }

    private static Animation createCurvedMotion(float fromX, float toX, float fromY, float toY) {
        return new TranslateAnimation(fromX, toX, fromY, toY);
    }

    public static float computePivot(int startPos, float finalScale) {
        float denom = finalScale - 1.0f;
        if (Math.abs(denom) < 1.0E-4f) {
            return startPos;
        }
        return (-startPos) / denom;
    }

    public static Animation loadAnimationSafely(Context context, int resId, String tag) {
        try {
            return TransitionAnimationExtPlugin.hookLoadAnimationSafely.call(context, context.getPackageName(), Integer.valueOf(resId), AnimationUtils.loadAnimation(context, resId));
        } catch (Exception e) {
            try {
                Slog.w(tag, "fail to add corners to animation ", e);
                return AnimationUtils.loadAnimation(context, resId);
            } catch (Resources.NotFoundException e2) {
                Slog.w(tag, "Unable to load animation resource", e2);
                return null;
            } catch (Exception re) {
                Slog.w(tag, "Unable to load animation resource", re);
                return null;
            }
        }
    }

    public static Animation createHiddenByKeyguardExit(Context context, LogDecelerateInterpolator interpolator, boolean onWallpaper, boolean goingToNotificationShade, boolean subtleAnimation) {
        int resource;
        if (goingToNotificationShade) {
            return AnimationUtils.loadAnimation(context, R.anim.lock_screen_behind_enter_fade_in);
        }
        if (subtleAnimation) {
            resource = R.anim.lock_screen_behind_enter_subtle;
        } else if (onWallpaper) {
            resource = R.anim.lock_screen_behind_enter_wallpaper;
        } else {
            resource = R.anim.lock_screen_behind_enter;
        }
        AnimationSet set = (AnimationSet) AnimationUtils.loadAnimation(context, resource);
        List<Animation> animations = set.getAnimations();
        for (int i = animations.size() - 1; i >= 0; i--) {
            animations.get(i).setInterpolator(interpolator);
        }
        return set;
    }
}
