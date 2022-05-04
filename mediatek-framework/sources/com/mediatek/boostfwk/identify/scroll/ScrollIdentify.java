package com.mediatek.boostfwk.identify.scroll;

import android.content.Context;
import android.os.Trace;
import android.util.Slog;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.mediatek.boostfwk.policy.scroll.ScrollPolicy;
import com.mediatek.boostfwk.scenario.scroll.ScrollScenario;
import com.mediatek.boostfwk.utils.Config;
import com.mediatek.boostfwk.utils.TasksUtil;
import com.mediatek.boostfwk.utils.Util;
/* loaded from: classes.dex */
public class ScrollIdentify {
    public static final int AOSP_DESIGN = 0;
    private static final float DISPLAY_RATE_60 = 60.0f;
    protected static final float FLING_DISTANCE = 200.0f;
    protected static final int FLING_SPEED = 1000;
    protected static final float MOVE_DISTANCE = 50.0f;
    public static final int NO_CHECKED_STATUS = -1;
    public static final int SPECIAL_DESIGN = 1;
    public static final int SPECIAL_DESIGN_WEB = 2;
    private static final String TAG = "ScrollIdentify";
    private static final boolean sAUTO_SWITCH_FPSGO = true;
    private GestureDetector mGestureDetector;
    private static ScrollIdentify sInstance = null;
    private static Object lock = new Object();
    private static final Object SCROLL_LOCK = new Object();
    private static Object scrollerLock = null;
    private static boolean mIsInput = false;
    private static String mDispatcherPkgName = null;
    private static boolean mIsSystemApp = false;
    private static String mInputPkgName = "";
    private static float mRefreshRate = 0.0f;
    private static long mFrameIntervalMs = 0;
    private static long mLimitVsyncTime = 0;
    private static int mIsSpecialAppDesign = -1;
    private static int mIsGameAppDesign = -1;
    private Context mContext = null;
    private boolean mHaveMoveEvent = false;
    private boolean mIsInputLockAcquired = false;
    private boolean mIsScrollLockAcquired = false;
    private boolean mIsScrolling = false;

    public static ScrollIdentify getInstance() {
        if (sInstance == null) {
            synchronized (lock) {
                if (sInstance == null) {
                    sInstance = new ScrollIdentify();
                }
            }
        }
        return sInstance;
    }

    public void scrollActionsDispatcher(ScrollScenario scenario) {
        if (Config.isBoostFwkScrollIdentify() && scenario != null) {
            int action = scenario.getScenarioAction();
            int status = scenario.getBoostStatus();
            MotionEvent event = scenario.getScenarioInputEvent();
            Object object = scenario.getScenarioObj();
            String packageName = null;
            if (scenario.getScenarioContext() != null) {
                this.mContext = scenario.getScenarioContext();
                packageName = scenario.getScenarioContext().getPackageName();
            }
            if (packageName == null || ((!packageName.contains("android") || !checkSystemAPP(packageName)) && !TasksUtil.isAPPInStrictMode(packageName))) {
                if (Config.isBoostFwkScrollIdentifyOn60hz()) {
                    mRefreshRate = Util.getRefreshRate();
                }
                String str = mDispatcherPkgName;
                if (str == null && str != packageName) {
                    mIsSpecialAppDesign = -1;
                }
                if (mIsGameAppDesign == -1) {
                    checkGameAPP();
                }
                if (mIsGameAppDesign != 1) {
                    if (this.mGestureDetector == null) {
                        try {
                            this.mGestureDetector = new GestureDetector(this.mContext, new ScrollGestureListener());
                        } catch (Exception e) {
                            if (Config.isBoostFwkLogEnable()) {
                                Slog.e(TAG, "layout not inflate, cannot create GestureDetector,try to next time.");
                            }
                            this.mGestureDetector = null;
                            return;
                        }
                    }
                    updateRefreshRate();
                    mDispatcherPkgName = packageName;
                    if (Config.isBoostFwkLogEnable()) {
                        Slog.d(TAG, packageName + ", Scroll action dispatcher to = " + action + " status = " + status + ", mContext = " + this.mContext);
                    }
                    switch (action) {
                        case 0:
                            if (event != null) {
                                inputEventCheck(event);
                                return;
                            }
                            return;
                        case 1:
                            inputDrawCheck(status, packageName);
                            return;
                        case 2:
                            inertialScrollCheck(status, packageName, object);
                            return;
                        case 3:
                            if (this.mHaveMoveEvent) {
                                if (Config.isBoostFwkLogEnable()) {
                                    Slog.d(TAG, "using scroller when VERTICAL scroll");
                                }
                                inertialScrollCheck(-2, packageName, object);
                                return;
                            }
                            return;
                        default:
                            Slog.w(TAG, "Not found dispatcher scroll action.");
                            return;
                    }
                } else if (Config.isBoostFwkLogEnable()) {
                    Slog.d(TAG, "onScroll -- game app");
                }
            }
        }
    }

    private void updateRefreshRate() {
        float refreshRate = Util.getRefreshRate();
        if (refreshRate != mRefreshRate) {
            mRefreshRate = refreshRate;
            mFrameIntervalMs = 1000.0f / refreshRate;
        }
    }

    public boolean disableForSpecialRate() {
        boolean result = Config.isBoostFwkScrollIdentifyOn60hz() && mRefreshRate == DISPLAY_RATE_60;
        if (Config.isBoostFwkLogEnable() && result) {
            Slog.d(TAG, "filter specila rate when scrolling: " + mRefreshRate);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ScrollGestureListener extends GestureDetector.SimpleOnGestureListener {
        ScrollGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e1 == null || e2 == null) {
                return false;
            }
            if (e1.getY() - e2.getY() > ScrollIdentify.MOVE_DISTANCE || e2.getY() - e1.getY() > ScrollIdentify.MOVE_DISTANCE) {
                if (Config.isBoostFwkLogEnable()) {
                    Slog.d(ScrollIdentify.TAG, "onScroll");
                }
                ScrollIdentify.this.mHaveMoveEvent = true;
                ScrollIdentify.this.checkSpecialAPPDesign();
                if (Config.isBoostFwkLogEnable()) {
                    Slog.d(ScrollIdentify.TAG, "mIsSpecialAppDesign = " + ScrollIdentify.mIsSpecialAppDesign);
                }
                ScrollIdentify.this.sbeHint(0, "Boost when move");
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 == null || e2 == null) {
                return false;
            }
            float distance_down = e2.getY() - e1.getY();
            float distance_up = e1.getY() - e2.getY();
            if ((distance_down <= ScrollIdentify.FLING_DISTANCE || Math.abs(velocityY) <= 1000.0f) && (distance_up <= ScrollIdentify.FLING_DISTANCE || Math.abs(velocityY) <= 1000.0f)) {
                return true;
            }
            if (Config.isBoostFwkLogEnable()) {
                Slog.d(ScrollIdentify.TAG, "onFling --> distance_down: " + distance_down + ", distance_up: " + distance_up + ", Math.abs(velocityY): " + Math.abs(velocityY));
            }
            ScrollIdentify.this.sbeHint(2, "Boost when fling");
            return true;
        }
    }

    private void inputEventCheck(MotionEvent event) {
        if (this.mHaveMoveEvent && event.getActionMasked() == 1) {
            if (Config.isBoostFwkLogEnable()) {
                Slog.d(TAG, "touch up");
            }
            this.mHaveMoveEvent = false;
            sbeHint(1, "Boost when up");
            this.mIsInputLockAcquired = false;
        }
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(event);
        }
    }

    private void inputDrawCheck(int action, String pkgName) {
        if (this.mHaveMoveEvent && !this.mIsInputLockAcquired) {
            mLimitVsyncTime = mFrameIntervalMs >> 1;
            mIsInput = boostBeginEndCheck(action);
            mInputPkgName = pkgName;
            if (Config.isBoostFwkLogEnable()) {
                Slog.d(TAG, "Vendor::inputDrawCheck begin, pkgName = " + pkgName + ", refresh rate = " + mRefreshRate + ", mFrameIntervalMs = " + mFrameIntervalMs);
            }
            this.mIsInputLockAcquired = true;
        }
    }

    public void inertialScrollCheck(int action, String pkgName, Object obj) {
        if (action == -2) {
            ScrollPolicy.getInstance().switchToFPSGo(true);
            this.mIsInputLockAcquired = false;
            this.mIsScrollLockAcquired = false;
            return;
        }
        boolean shouldBoost = boostBeginEndCheck(action);
        if (checkScroller(shouldBoost, obj)) {
            boolean z = this.mIsScrollLockAcquired;
            if (!z && shouldBoost) {
                inertialScrollHint(true, pkgName);
                this.mIsScrollLockAcquired = true;
            } else if (z && !shouldBoost) {
                inertialScrollHint(false, pkgName);
                this.mIsScrollLockAcquired = false;
            }
        }
    }

    private void inertialScrollHint(boolean enable, String pkgName) {
        if (mIsInput || !mInputPkgName.equals("") || mInputPkgName.equals(pkgName) || mIsSpecialAppDesign != 0) {
            ScrollPolicy.getInstance().switchToFPSGo(enable);
            ScrollPolicy.getInstance().releaseTargetFPS(enable);
            resetInputFlag(!enable);
            return;
        }
        resetInputFlag(true);
    }

    private void resetInputFlag(boolean reset) {
        if (reset) {
            mIsInput = false;
            mInputPkgName = "";
        }
    }

    private boolean boostBeginEndCheck(int action) {
        switch (action) {
            case 0:
                return true;
            case 1:
                return false;
            default:
                Slog.e(TAG, "Unknown define action inputed, exit now.");
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSpecialAPPDesign() {
        switch (mIsSpecialAppDesign) {
            case -1:
                Context context = this.mContext;
                if (context == null) {
                    mIsSpecialAppDesign = 1;
                    this.mIsInputLockAcquired = true;
                    return;
                } else if (!TasksUtil.isSpeicalAPP(context)) {
                    mIsSpecialAppDesign = 0;
                    return;
                } else {
                    mIsSpecialAppDesign = 1;
                    this.mIsInputLockAcquired = true;
                    if (mRefreshRate == DISPLAY_RATE_60 && !TasksUtil.isSpeicalAPPWOWebView(this.mContext)) {
                        mIsSpecialAppDesign = 2;
                        return;
                    }
                    return;
                }
            case 0:
                return;
            case 1:
            case 2:
                this.mIsInputLockAcquired = true;
                return;
            default:
                Slog.e(TAG, "Unknown special app design status. flag = " + mIsSpecialAppDesign);
                return;
        }
    }

    private void checkGameAPP() {
        Context context = this.mContext;
        if (context == null) {
            mIsGameAppDesign = 1;
        } else if (mIsGameAppDesign != -1) {
        } else {
            if (TasksUtil.isGameAPP(context)) {
                mIsGameAppDesign = 1;
            } else {
                mIsGameAppDesign = 0;
            }
        }
    }

    private boolean checkScroller(boolean shouldBoost, Object obj) {
        if (obj == null) {
            return false;
        }
        if (!shouldBoost) {
            Object obj2 = scrollerLock;
            if (obj2 != null && obj2 == obj) {
                scrollerLock = null;
                return true;
            }
        } else if (scrollerLock == null) {
            scrollerLock = obj;
            return true;
        }
        return false;
    }

    private boolean checkSystemAPP(String pkgName) {
        String str = mDispatcherPkgName;
        if (str == null && str != pkgName) {
            mIsSystemApp = Util.isSystemApp(pkgName);
        }
        boolean isSystemApp = mIsSystemApp;
        return isSystemApp;
    }

    private boolean needSupportAt60() {
        if (mRefreshRate != DISPLAY_RATE_60) {
            return true;
        }
        int i = mIsSpecialAppDesign;
        if (i == 2 || i == 0) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sbeHint(int whichStep, String logStr) {
        if (whichStep == 2 && mIsSpecialAppDesign != -1) {
            ScrollPolicy.getInstance().scrollHint(whichStep, mIsSpecialAppDesign);
        } else if (this.mIsInputLockAcquired && needSupportAt60()) {
            if (Config.isBoostFwkLogEnable() && logStr != null) {
                Slog.d(TAG, logStr);
            }
            ScrollPolicy.getInstance().scrollHint(whichStep, mIsSpecialAppDesign);
        }
    }

    public boolean isScroll() {
        boolean z;
        synchronized (SCROLL_LOCK) {
            z = this.mIsScrolling;
        }
        return z;
    }

    public void setScrolling(boolean scrolling, String msg) {
        synchronized (SCROLL_LOCK) {
            this.mIsScrolling = scrolling;
            if (Config.isBoostFwkLogEnable()) {
                Trace.traceBegin(8L, msg + " curScrollingState=" + String.valueOf(this.mIsScrolling));
                Trace.traceEnd(8L);
            }
        }
    }
}
