package com.mediatek.boostfwk.scenario.frame;

import com.mediatek.boostfwk.scenario.BasicScenario;
/* loaded from: classes4.dex */
public class FrameScenario extends BasicScenario {
    private int mBoostStatus;
    private long mFrameId;
    private int mFrameStep;
    protected int mScenarioAction;

    public FrameScenario() {
    }

    public FrameScenario(int scenario, int action, int boostStatus, long frameId) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mBoostStatus = boostStatus;
        this.mFrameStep = -1;
        this.mFrameId = frameId;
    }

    public FrameScenario(int scenario, int action, int boostStatus, int frameStep) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mBoostStatus = boostStatus;
        this.mFrameStep = frameStep;
    }

    public int getScenarioAction() {
        return this.mScenarioAction;
    }

    public int getBoostStatus() {
        return this.mBoostStatus;
    }

    public int getFrameStep() {
        return this.mFrameStep;
    }

    public long getFrameId() {
        return this.mFrameId;
    }
}
