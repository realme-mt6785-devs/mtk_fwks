package com.android.internal.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.MathUtils;
import com.android.internal.R;
/* loaded from: classes4.dex */
public class BrightnessSynchronizer {
    private static final Uri BRIGHTNESS_URI = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
    public static final float EPSILON = 0.001f;
    private static final int MSG_UPDATE_BOTH = 3;
    private static final int MSG_UPDATE_FLOAT = 1;
    private static final int MSG_UPDATE_INT = 2;
    private static final String TAG = "BrightnessSynchronizer";
    private final Context mContext;
    private DisplayManager mDisplayManager;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.internal.display.BrightnessSynchronizer.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BrightnessSynchronizer.this.updateBrightnessFloatFromInt(msg.arg1);
                    return;
                case 2:
                    BrightnessSynchronizer.this.updateBrightnessIntFromFloat(Float.intBitsToFloat(msg.arg1));
                    return;
                case 3:
                    BrightnessSynchronizer.this.updateBoth(Float.intBitsToFloat(msg.arg1));
                    return;
                default:
                    super.handleMessage(msg);
                    return;
            }
        }
    };
    private float mPreferredSettingValue;

    public BrightnessSynchronizer(Context context) {
        this.mContext = context;
    }

    public void startSynchronizing() {
        if (this.mDisplayManager == null) {
            this.mDisplayManager = (DisplayManager) this.mContext.getSystemService(DisplayManager.class);
        }
        BrightnessSyncObserver brightnessSyncObserver = new BrightnessSyncObserver();
        brightnessSyncObserver.startObserving();
        float currentFloatBrightness = getScreenBrightnessFloat();
        int currentIntBrightness = getScreenBrightnessInt(this.mContext);
        if (!Float.isNaN(currentFloatBrightness)) {
            updateBrightnessIntFromFloat(currentFloatBrightness);
        } else if (currentIntBrightness != -1) {
            updateBrightnessFloatFromInt(currentIntBrightness);
        } else {
            float defaultBrightness = this.mContext.getResources().getFloat(R.dimen.config_screenBrightnessSettingDefaultFloat);
            this.mDisplayManager.setBrightness(0, defaultBrightness);
        }
    }

    public static float brightnessIntToFloat(int brightnessInt) {
        if (brightnessInt == 0) {
            return -1.0f;
        }
        if (brightnessInt == -1) {
            return Float.NaN;
        }
        return MathUtils.constrainedMap(0.0f, 1.0f, 1.0f, 255.0f, brightnessInt);
    }

    public static int brightnessFloatToInt(float brightnessFloat) {
        return Math.round(brightnessFloatToIntRange(brightnessFloat));
    }

    public static float brightnessFloatToIntRange(float brightnessFloat) {
        if (floatEquals(brightnessFloat, -1.0f)) {
            return 0.0f;
        }
        if (Float.isNaN(brightnessFloat)) {
            return -1.0f;
        }
        return MathUtils.constrainedMap(1.0f, 255.0f, 0.0f, 1.0f, brightnessFloat);
    }

    private float getScreenBrightnessFloat() {
        return this.mDisplayManager.getBrightness(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getScreenBrightnessInt(Context context) {
        return Settings.System.getIntForUser(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBrightnessFloatFromInt(int value) {
        if (brightnessFloatToInt(this.mPreferredSettingValue) != value) {
            float brightnessIntToFloat = brightnessIntToFloat(value);
            this.mPreferredSettingValue = brightnessIntToFloat;
            int newBrightnessAsIntBits = Float.floatToIntBits(brightnessIntToFloat);
            this.mHandler.removeMessages(3);
            this.mHandler.obtainMessage(3, newBrightnessAsIntBits, 0).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBrightnessIntFromFloat(float value) {
        if (!floatEquals(this.mPreferredSettingValue, value)) {
            this.mPreferredSettingValue = value;
            int newBrightnessAsIntBits = Float.floatToIntBits(value);
            this.mHandler.removeMessages(3);
            this.mHandler.obtainMessage(3, newBrightnessAsIntBits, 0).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBoth(float newBrightnessFloat) {
        brightnessFloatToInt(newBrightnessFloat);
        this.mDisplayManager.setBrightness(0, newBrightnessFloat);
        getScreenBrightnessInt(this.mContext);
    }

    public static boolean floatEquals(float a, float b) {
        if (a == b) {
            return true;
        }
        return (Float.isNaN(a) && Float.isNaN(b)) || Math.abs(a - b) < 0.001f;
    }

    /* loaded from: classes4.dex */
    private class BrightnessSyncObserver {
        private final ContentObserver mContentObserver;
        private final DisplayManager.DisplayListener mListener;

        private BrightnessSyncObserver() {
            this.mListener = new DisplayManager.DisplayListener() { // from class: com.android.internal.display.BrightnessSynchronizer.BrightnessSyncObserver.1
                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayAdded(int displayId) {
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayRemoved(int displayId) {
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayChanged(int displayId) {
                }
            };
            this.mContentObserver = new ContentObserver(BrightnessSynchronizer.this.mHandler) { // from class: com.android.internal.display.BrightnessSynchronizer.BrightnessSyncObserver.2
                @Override // android.database.ContentObserver
                public void onChange(boolean selfChange, Uri uri) {
                    if (!selfChange && BrightnessSynchronizer.BRIGHTNESS_URI.equals(uri)) {
                        int currentBrightness = BrightnessSynchronizer.getScreenBrightnessInt(BrightnessSynchronizer.this.mContext);
                        BrightnessSynchronizer.this.mHandler.removeMessages(1);
                        BrightnessSynchronizer.this.mHandler.obtainMessage(1, currentBrightness, 0).sendToTarget();
                    }
                }
            };
        }

        public void startObserving() {
            ContentResolver cr = BrightnessSynchronizer.this.mContext.getContentResolver();
            cr.unregisterContentObserver(this.mContentObserver);
            cr.registerContentObserver(BrightnessSynchronizer.BRIGHTNESS_URI, false, this.mContentObserver, -1);
            BrightnessSynchronizer.this.mDisplayManager.registerDisplayListener(this.mListener, BrightnessSynchronizer.this.mHandler, 12L);
        }

        public void stopObserving() {
            ContentResolver cr = BrightnessSynchronizer.this.mContext.getContentResolver();
            cr.unregisterContentObserver(this.mContentObserver);
            BrightnessSynchronizer.this.mDisplayManager.unregisterDisplayListener(this.mListener);
        }
    }
}
