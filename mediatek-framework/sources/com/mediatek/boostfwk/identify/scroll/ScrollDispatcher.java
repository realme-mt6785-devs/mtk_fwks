package com.mediatek.boostfwk.identify.scroll;

import android.util.Slog;
import com.mediatek.boostfwk.scenario.scroll.ScrollScenario;
/* loaded from: classes.dex */
public class ScrollDispatcher {
    private static final String TAG = "ScrollDispatcher";
    private static ScrollDispatcher sInstance = null;
    private static Object sLock = new Object();
    private static ScrollIdentify sScrollScenario = ScrollIdentify.getInstance();

    public static ScrollDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new ScrollDispatcher();
                }
            }
        }
        return sInstance;
    }

    public void scenarioActionDispatcher(ScrollScenario scenario) {
        if (scenario == null) {
            Slog.w(TAG, "No view scenario to dispatcher.");
        } else if (scenario instanceof ScrollScenario) {
            sScrollScenario.scrollActionsDispatcher(scenario);
        }
    }
}
