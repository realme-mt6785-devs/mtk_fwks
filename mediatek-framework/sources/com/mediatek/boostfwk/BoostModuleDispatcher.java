package com.mediatek.boostfwk;

import android.util.Slog;
import com.mediatek.boostfwk.identify.frame.FrameDispatcher;
import com.mediatek.boostfwk.identify.launch.LaunchDispatcher;
import com.mediatek.boostfwk.identify.scroll.ScrollDispatcher;
import com.mediatek.boostfwk.scenario.BasicScenario;
import com.mediatek.boostfwk.scenario.frame.FrameScenario;
import com.mediatek.boostfwk.scenario.launch.LaunchScenario;
import com.mediatek.boostfwk.scenario.scroll.ScrollScenario;
import com.mediatek.boostfwk.utils.Util;
/* loaded from: classes.dex */
public final class BoostModuleDispatcher {
    private static final String TAG = "BoostModuleDispatcher";
    private static BoostModuleDispatcher sInstance = null;
    private static Object sLock = new Object();
    private static ScrollDispatcher sScrollDispatcher = ScrollDispatcher.getInstance();
    private static LaunchDispatcher sLaunchDispatcher = LaunchDispatcher.getInstance();
    private static FrameDispatcher sFrameDispatcher = FrameDispatcher.getInstance();

    public static BoostModuleDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new BoostModuleDispatcher();
                }
            }
        }
        return sInstance;
    }

    public void scenarioActionDispatcher(BasicScenario scenario) {
        if (scenario == null) {
            Slog.w(TAG, "No scenario to dispatcher.");
            return;
        }
        int scenarioId = scenario.getScenario();
        switch (scenarioId) {
            case 1:
                if (Util.isMainThread()) {
                    sScrollDispatcher.scenarioActionDispatcher((ScrollScenario) scenario);
                    return;
                }
                return;
            case 2:
                if (Util.isMainThread()) {
                    sFrameDispatcher.scenarioActionDispatcher((FrameScenario) scenario);
                    return;
                }
                return;
            case 3:
                sLaunchDispatcher.scenarioActionDispatcher((LaunchScenario) scenario);
                return;
            default:
                Slog.w(TAG, "Not found dispatcher scenario.");
                return;
        }
    }
}
