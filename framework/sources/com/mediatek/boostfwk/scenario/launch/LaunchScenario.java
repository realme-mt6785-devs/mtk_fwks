package com.mediatek.boostfwk.scenario.launch;

import android.view.WindowManager;
import com.mediatek.boostfwk.scenario.BasicScenario;
/* loaded from: classes4.dex */
public class LaunchScenario extends BasicScenario {
    private String mActivityName;
    private WindowManager.LayoutParams mAttrs;
    private int mBoostStatus;
    private String mHostingType;
    private boolean mIsComeFromIdle;
    private String mPkgName;
    private int mScenarioAction;

    public LaunchScenario(int scenario, int action, String hostingType, int boostStatus, String pkgName) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mHostingType = hostingType;
        this.mBoostStatus = boostStatus;
        this.mPkgName = pkgName;
    }

    public LaunchScenario(int scenario, int action, int boostStatus, String pkgName, String activityName, boolean isComeFromIdle) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mBoostStatus = boostStatus;
        this.mPkgName = pkgName;
        this.mActivityName = activityName;
        this.mIsComeFromIdle = isComeFromIdle;
    }

    public LaunchScenario(int scenario, int action, int boostStatus, String pkgName, WindowManager.LayoutParams attrs, boolean isComeFromIdle) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mBoostStatus = boostStatus;
        this.mPkgName = pkgName;
        this.mAttrs = attrs;
        this.mIsComeFromIdle = isComeFromIdle;
    }

    public int getScenarioAction() {
        return this.mScenarioAction;
    }

    public String getPackageName() {
        return this.mPkgName;
    }

    public int getBoostStatus() {
        return this.mBoostStatus;
    }

    public String getHostingType() {
        return this.mHostingType;
    }

    public WindowManager.LayoutParams getAttrs() {
        return this.mAttrs;
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    public boolean getIsComeFromIdle() {
        return this.mIsComeFromIdle;
    }
}
