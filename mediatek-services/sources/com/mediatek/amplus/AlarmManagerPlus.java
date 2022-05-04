package com.mediatek.amplus;

import android.app.PendingIntent;
import android.content.Context;
/* loaded from: classes.dex */
public class AlarmManagerPlus {
    private static final String TAG = "AlarmManager";
    private PowerSavingUtils mPowerSavingUtils;

    public AlarmManagerPlus(Context context) {
        this.mPowerSavingUtils = null;
        this.mPowerSavingUtils = new PowerSavingUtils(context);
    }

    public long getMaxTriggerTime(int type, long triggerElapsed, long windowLength, long interval, PendingIntent operation, int mAlarmMode, boolean needGrouping) {
        return this.mPowerSavingUtils.getMaxTriggerTime(type, triggerElapsed, windowLength, interval, operation, mAlarmMode, needGrouping);
    }

    public boolean isPowerSavingStart() {
        return this.mPowerSavingUtils.isPowerSavingStart();
    }
}
