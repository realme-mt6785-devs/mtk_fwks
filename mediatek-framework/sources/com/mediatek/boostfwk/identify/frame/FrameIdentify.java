package com.mediatek.boostfwk.identify.frame;

import android.os.Process;
import android.util.Slog;
import com.mediatek.boostfwk.policy.frame.FramePolicy;
import com.mediatek.boostfwk.scenario.frame.FrameScenario;
import com.mediatek.boostfwk.utils.Config;
import com.mediatek.boostfwk.utils.Util;
/* loaded from: classes.dex */
public class FrameIdentify {
    private static final String TAG = "FrameIdentify";
    private static FrameIdentify sInstance = null;
    private static Object slock = new Object();
    private static boolean mIsDoframeCheck = false;
    private static float mRefreshRate = 0.0f;
    private static long mFrameIntervalMs = 0;
    private static long mLimitVsyncTime = 0;

    public static FrameIdentify getInstance() {
        if (sInstance == null) {
            synchronized (slock) {
                if (sInstance == null) {
                    sInstance = new FrameIdentify();
                }
            }
        }
        return sInstance;
    }

    public void frameActionsDispatcher(FrameScenario scenario) {
        if (scenario == null) {
            Slog.w(TAG, "No frame scenario to dispatcher.");
        } else if (!Config.disableFrameIdentify()) {
            int action = scenario.getScenarioAction();
            int status = scenario.getBoostStatus();
            int frameStep = scenario.getFrameStep();
            if (Config.isBoostFwkLogEnable()) {
                Slog.d(TAG, "Frame action dispatcher to = " + action + " status = " + status + ", frame step = " + frameStep);
            }
            switch (action) {
                case 0:
                    frameCheck(status, scenario.getFrameId());
                    return;
                case 1:
                    frameStepCheck(status, frameStep);
                    return;
                default:
                    Slog.w(TAG, "Not found dispatcher frame action.");
                    return;
            }
        }
    }

    private void frameCheck(int status, long frameId) {
        if (Process.myPid() == Process.myTid() && doFrameInit()) {
            mIsDoframeCheck = boostBeginEndCheck(status);
            FramePolicy.getInstance().doFrameHint(mIsDoframeCheck, frameId);
        }
    }

    private void frameStepCheck(int status, int step) {
        if (isBeginFrameAction()) {
            FramePolicy.getInstance().doFrameStepHint(boostBeginEndCheck(status), step);
        }
    }

    private boolean doFrameInit() {
        float refreshRate = Util.getRefreshRate();
        mRefreshRate = refreshRate;
        mFrameIntervalMs = 1000.0f / refreshRate;
        return FramePolicy.getInstance().initLimitTime((float) mFrameIntervalMs, mRefreshRate);
    }

    private boolean isBeginFrameAction() {
        if (!mIsDoframeCheck) {
            return false;
        }
        return true;
    }

    private boolean boostBeginEndCheck(int status) {
        switch (status) {
            case 0:
                return true;
            case 1:
                return false;
            default:
                Slog.e(TAG, "Unknown define action inputed, exit now.");
                return false;
        }
    }
}
