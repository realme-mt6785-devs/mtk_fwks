package com.android.internal.app;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.slice.Slice;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AnalogClock;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.R;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public class PlatLogoActivity extends Activity {
    private static final String S_EGG_UNLOCK_SETTING = "egg_mode_s";
    private static final String TAG = "PlatLogoActivity";
    static final String TOUCH_STATS = "touch.stats";
    private BubblesDrawable mBg;
    private SettableAnalogClock mClock;
    private ImageView mLogo;
    double mPressureMin = 0.0d;
    double mPressureMax = -1.0d;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.hide();
        }
        FrameLayout layout = new FrameLayout(this);
        this.mClock = new SettableAnalogClock(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float dp = dm.density;
        int minSide = Math.min(dm.widthPixels, dm.heightPixels);
        int widgetSize = (int) (minSide * 0.75d);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(widgetSize, widgetSize);
        lp.gravity = 17;
        layout.addView(this.mClock, lp);
        ImageView imageView = new ImageView(this);
        this.mLogo = imageView;
        imageView.setVisibility(8);
        this.mLogo.setImageResource(R.drawable.platlogo);
        layout.addView(this.mLogo, lp);
        BubblesDrawable bubblesDrawable = new BubblesDrawable();
        this.mBg = bubblesDrawable;
        bubblesDrawable.setLevel(0);
        this.mBg.avoid = widgetSize / 2;
        this.mBg.padding = 0.5f * dp;
        this.mBg.minR = 1.0f * dp;
        layout.setBackground(this.mBg);
        setContentView(layout);
    }

    private boolean shouldWriteSettings() {
        return getPackageName().equals("android");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchNextStage(boolean locked) {
        this.mClock.animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).withEndAction(new Runnable() { // from class: com.android.internal.app.PlatLogoActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PlatLogoActivity.this.lambda$launchNextStage$0$PlatLogoActivity();
            }
        }).start();
        this.mLogo.setAlpha(0.0f);
        this.mLogo.setScaleX(0.5f);
        this.mLogo.setScaleY(0.5f);
        this.mLogo.setVisibility(0);
        this.mLogo.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).start();
        this.mLogo.postDelayed(new Runnable() { // from class: com.android.internal.app.PlatLogoActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PlatLogoActivity.this.lambda$launchNextStage$1$PlatLogoActivity();
            }
        }, 500L);
        ContentResolver cr = getContentResolver();
        try {
            if (shouldWriteSettings()) {
                Log.v(TAG, "Saving egg unlock=" + locked);
                syncTouchPressure();
                Settings.System.putLong(cr, S_EGG_UNLOCK_SETTING, locked ? 0L : System.currentTimeMillis());
            }
        } catch (RuntimeException e) {
            Log.e(TAG, "Can't write settings", e);
        }
        try {
            startActivity(new Intent(Intent.ACTION_MAIN).setFlags(268468224).addCategory("com.android.internal.category.PLATLOGO"));
        } catch (ActivityNotFoundException e2) {
            Log.e("com.android.internal.app.PlatLogoActivity", "No more eggs.");
        }
    }

    public /* synthetic */ void lambda$launchNextStage$0$PlatLogoActivity() {
        this.mClock.setVisibility(8);
    }

    public /* synthetic */ void lambda$launchNextStage$1$PlatLogoActivity() {
        ObjectAnimator anim = ObjectAnimator.ofInt(this.mBg, "level", 0, 10000);
        anim.setInterpolator(new DecelerateInterpolator(1.0f));
        anim.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void measureTouchPressure(MotionEvent event) {
        float pressure = event.getPressure();
        switch (event.getActionMasked()) {
            case 0:
                if (this.mPressureMax < 0.0d) {
                    double d = pressure;
                    this.mPressureMax = d;
                    this.mPressureMin = d;
                    return;
                }
                return;
            case 1:
            default:
                return;
            case 2:
                if (pressure < this.mPressureMin) {
                    this.mPressureMin = pressure;
                }
                if (pressure > this.mPressureMax) {
                    this.mPressureMax = pressure;
                    return;
                }
                return;
        }
    }

    private void syncTouchPressure() {
        try {
            String touchDataJson = Settings.System.getString(getContentResolver(), TOUCH_STATS);
            JSONObject touchData = new JSONObject(touchDataJson != null ? touchDataJson : "{}");
            if (touchData.has("min")) {
                this.mPressureMin = Math.min(this.mPressureMin, touchData.getDouble("min"));
            }
            if (touchData.has(Slice.SUBTYPE_MAX)) {
                this.mPressureMax = Math.max(this.mPressureMax, touchData.getDouble(Slice.SUBTYPE_MAX));
            }
            if (this.mPressureMax >= 0.0d) {
                touchData.put("min", this.mPressureMin);
                touchData.put(Slice.SUBTYPE_MAX, this.mPressureMax);
                if (shouldWriteSettings()) {
                    Settings.System.putString(getContentResolver(), TOUCH_STATS, touchData.toString());
                }
            }
        } catch (Exception e) {
            Log.e("com.android.internal.app.PlatLogoActivity", "Can't write touch settings", e);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        syncTouchPressure();
    }

    @Override // android.app.Activity
    public void onStop() {
        syncTouchPressure();
        super.onStop();
    }

    /* loaded from: classes4.dex */
    public class SettableAnalogClock extends AnalogClock {
        private int mOverrideHour = -1;
        private int mOverrideMinute = -1;
        private boolean mOverride = false;

        public SettableAnalogClock(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.widget.AnalogClock
        public Instant now() {
            Instant realNow = super.now();
            ZoneId tz = Clock.systemDefaultZone().getZone();
            ZonedDateTime zdTime = realNow.atZone(tz);
            if (!this.mOverride) {
                return realNow;
            }
            if (this.mOverrideHour < 0) {
                this.mOverrideHour = zdTime.getHour();
            }
            return Clock.fixed(zdTime.withHour(this.mOverrideHour).withMinute(this.mOverrideMinute).withSecond(0).toInstant(), tz).instant();
        }

        double toPositiveDegrees(double rad) {
            return ((Math.toDegrees(rad) + 360.0d) - 90.0d) % 360.0d;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent ev) {
            int i;
            switch (ev.getActionMasked()) {
                case 0:
                    this.mOverride = true;
                    break;
                case 1:
                    if (this.mOverrideMinute == 0 && this.mOverrideHour % 12 == 0) {
                        Log.v(PlatLogoActivity.TAG, "12:00 let's gooooo");
                        performHapticFeedback(0);
                        PlatLogoActivity.this.launchNextStage(false);
                    }
                    return true;
                case 2:
                    break;
                default:
                    return false;
            }
            PlatLogoActivity.this.measureTouchPressure(ev);
            float x = ev.getX();
            float y = ev.getY();
            float cx = getWidth() / 2.0f;
            float cy = getHeight() / 2.0f;
            float angle = (float) toPositiveDegrees(Math.atan2(x - cx, y - cy));
            int minutes = (75 - ((int) (angle / 6.0f))) % 60;
            int minuteDelta = minutes - this.mOverrideMinute;
            if (minuteDelta != 0) {
                if (Math.abs(minuteDelta) > 45 && (i = this.mOverrideHour) >= 0) {
                    int hourDelta = minuteDelta < 0 ? 1 : -1;
                    this.mOverrideHour = ((i + 24) + hourDelta) % 24;
                }
                this.mOverrideMinute = minutes;
                if (minutes == 0) {
                    performHapticFeedback(0);
                    if (getScaleX() == 1.0f) {
                        setScaleX(1.05f);
                        setScaleY(1.05f);
                        animate().scaleX(1.0f).scaleY(1.0f).setDuration(150L).start();
                    }
                } else {
                    performHapticFeedback(4);
                }
                onTimeChanged();
                postInvalidate();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Bubble {
        public int color;
        public float r;
        public float x;
        public float y;

        Bubble() {
        }
    }

    /* loaded from: classes4.dex */
    class BubblesDrawable extends Drawable {
        private static final int MAX_BUBBS = 2000;
        private final int[] mColorIds;
        private int[] mColors;
        private int mNumBubbs;
        private final Bubble[] mBubbs = new Bubble[2000];
        private final Paint mPaint = new Paint(1);
        public float avoid = 0.0f;
        public float padding = 0.0f;
        public float minR = 0.0f;

        BubblesDrawable() {
            int[] iArr = {17170493, 17170494, 17170495, 17170506, 17170507, 17170508};
            this.mColorIds = iArr;
            this.mColors = new int[iArr.length];
            int i = 0;
            while (true) {
                int[] iArr2 = this.mColorIds;
                if (i >= iArr2.length) {
                    break;
                }
                this.mColors[i] = PlatLogoActivity.this.getColor(iArr2[i]);
                i++;
            }
            int j = 0;
            while (true) {
                Bubble[] bubbleArr = this.mBubbs;
                if (j < bubbleArr.length) {
                    bubbleArr[j] = new Bubble();
                    j++;
                } else {
                    return;
                }
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            float f = getLevel() / 10000.0f;
            this.mPaint.setStyle(Paint.Style.FILL);
            int drawn = 0;
            for (int j = 0; j < this.mNumBubbs; j++) {
                if (!(this.mBubbs[j].color == 0 || this.mBubbs[j].r == 0.0f)) {
                    this.mPaint.setColor(this.mBubbs[j].color);
                    canvas.drawCircle(this.mBubbs[j].x, this.mBubbs[j].y, this.mBubbs[j].r * f, this.mPaint);
                    drawn++;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.graphics.drawable.Drawable
        public boolean onLevelChange(int level) {
            invalidateSelf();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            randomize();
        }

        private void randomize() {
            float x;
            float w = getBounds().width();
            float h = getBounds().height();
            float maxR = Math.min(w, h) / 3.0f;
            this.mNumBubbs = 0;
            if (this.avoid > 0.0f) {
                this.mBubbs[0].x = w / 2.0f;
                this.mBubbs[this.mNumBubbs].y = h / 2.0f;
                this.mBubbs[this.mNumBubbs].r = this.avoid;
                this.mBubbs[this.mNumBubbs].color = 0;
                this.mNumBubbs++;
            }
            for (int j = 0; j < 2000; j++) {
                int tries = 5;
                while (true) {
                    int tries2 = tries - 1;
                    if (tries > 0) {
                        float x2 = ((float) Math.random()) * w;
                        float y = ((float) Math.random()) * h;
                        float r = Math.min(Math.min(x2, w - x2), Math.min(y, h - y));
                        int i = 0;
                        while (true) {
                            if (i >= this.mNumBubbs) {
                                x = x2;
                                break;
                            }
                            x = x2;
                            r = (float) Math.min(r, (Math.hypot(x2 - this.mBubbs[i].x, y - this.mBubbs[i].y) - this.mBubbs[i].r) - this.padding);
                            if (r < this.minR) {
                                break;
                            }
                            i++;
                            x2 = x;
                        }
                        if (r >= this.minR) {
                            float r2 = Math.min(maxR, r);
                            this.mBubbs[this.mNumBubbs].x = x;
                            this.mBubbs[this.mNumBubbs].y = y;
                            this.mBubbs[this.mNumBubbs].r = r2;
                            this.mBubbs[this.mNumBubbs].color = this.mColors[(int) (Math.random() * this.mColors.length)];
                            this.mNumBubbs++;
                            break;
                        }
                        tries = tries2;
                    }
                }
            }
            Log.v(PlatLogoActivity.TAG, String.format("successfully placed %d bubbles (%d%%)", Integer.valueOf(this.mNumBubbs), Integer.valueOf((int) ((this.mNumBubbs * 100.0f) / 2000.0f))));
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }
    }
}
