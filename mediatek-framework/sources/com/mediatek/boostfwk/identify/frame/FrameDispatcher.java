package com.mediatek.boostfwk.identify.frame;

import android.util.Slog;
import com.mediatek.boostfwk.scenario.frame.FrameScenario;
/* loaded from: classes.dex */
public class FrameDispatcher {
    private static final String TAG = "FrameDispatcher";
    private static FrameDispatcher sInstance = null;
    private static Object sLock = new Object();
    private static FrameIdentify sFrameScenario = FrameIdentify.getInstance();

    public static FrameDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new FrameDispatcher();
                }
            }
        }
        return sInstance;
    }

    public void scenarioActionDispatcher(FrameScenario scenario) {
        if (scenario == null) {
            Slog.w(TAG, "No view scenario to dispatcher.");
        } else {
            sFrameScenario.frameActionsDispatcher(scenario);
        }
    }
}
