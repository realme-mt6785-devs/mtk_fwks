package com.mediatek.boostfwk.identify.launch;

import android.util.Slog;
import com.mediatek.boostfwk.scenario.launch.LaunchScenario;
/* loaded from: classes.dex */
public class LaunchDispatcher {
    private static final String TAG = "LaunchDispatcher";
    private static LaunchDispatcher sInstance = null;
    private static Object sLock = new Object();
    private static LaunchIdentify sLaunchScenario = LaunchIdentify.getInstance();

    public static LaunchDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new LaunchDispatcher();
                }
            }
        }
        return sInstance;
    }

    public void scenarioActionDispatcher(LaunchScenario scenario) {
        if (scenario == null) {
            Slog.w(TAG, "No launch scenario to dispatcher.");
        } else if (scenario instanceof LaunchScenario) {
            sLaunchScenario.launchActionsDispatcher(scenario);
        }
    }
}
