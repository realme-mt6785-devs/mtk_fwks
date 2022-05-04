package com.mediatek.boostfwk.scenario.scroll;

import android.content.Context;
import android.view.MotionEvent;
/* loaded from: classes4.dex */
public class ScrollScenario extends ViewScenario {
    private int mBoostStatus;
    private Context mContext;
    private MotionEvent mEvent;
    private Object mObject;

    public ScrollScenario() {
    }

    public ScrollScenario(int scenario, int action, int boostStatus, Context context) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mBoostStatus = boostStatus;
        this.mContext = context;
    }

    public ScrollScenario(int scenario, int action, int boostStatus, Context context, Object object) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mBoostStatus = boostStatus;
        this.mContext = context;
        this.mObject = object;
    }

    public ScrollScenario(int scenario, int action, MotionEvent event, Context context) {
        this.mScenario = scenario;
        this.mScenarioAction = action;
        this.mEvent = event;
        this.mContext = context;
    }

    public int getBoostStatus() {
        return this.mBoostStatus;
    }

    public Context getScenarioContext() {
        return this.mContext;
    }

    public MotionEvent getScenarioInputEvent() {
        return this.mEvent;
    }

    public Object getScenarioObj() {
        return this.mObject;
    }
}
