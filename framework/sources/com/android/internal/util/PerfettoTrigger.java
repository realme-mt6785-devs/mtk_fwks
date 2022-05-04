package com.android.internal.util;

import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;
/* loaded from: classes4.dex */
public class PerfettoTrigger {
    private static final String TAG = "PerfettoTrigger";
    private static final long THROTTLE_MILLIS = 60000;
    private static final String TRIGGER_COMMAND = "/system/bin/trigger_perfetto";
    private static volatile long sLastTriggerTime = -60000;

    public static void trigger(String triggerName) {
        long sinceLastTrigger = SystemClock.elapsedRealtime() - sLastTriggerTime;
        if (sinceLastTrigger < 60000) {
            Log.v(TAG, "Not triggering " + triggerName + " - not enough time since last trigger");
            return;
        }
        try {
            ProcessBuilder pb = new ProcessBuilder(TRIGGER_COMMAND, triggerName);
            Log.v(TAG, "Triggering " + String.join(" ", pb.command()));
            pb.start();
            sLastTriggerTime = SystemClock.elapsedRealtime();
        } catch (IOException e) {
            Log.w(TAG, "Failed to trigger " + triggerName, e);
        }
    }
}
