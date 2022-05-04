package com.mediatek.boostfwk.policy.scroll;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.Trace;
import android.util.Slog;
import com.mediatek.boostfwk.identify.scroll.ScrollIdentify;
import com.mediatek.boostfwk.utils.Config;
import com.mediatek.boostfwk.utils.TasksUtil;
import com.mediatek.powerhalmgr.PowerHalMgr;
import com.mediatek.powerhalmgr.PowerHalMgrFactory;
import com.mediatek.util.MtkPatterns;
/* loaded from: classes.dex */
public class ScrollPolicy {
    private static final int PERF_RES_FPS_FPSGO_CTL = 33555200;
    private static final int PERF_RES_FPS_FPSGO_NOCTL = 33555456;
    private static final int PERF_RES_FPS_FPSGO_UBOOST = 33851136;
    private static final int PERF_RES_FPS_FSTB_TARGET_FPS_PID = 33554944;
    private static final String TAG = "ScrollPolicy";
    public static final int sFINGER_MOVE = 0;
    public static final int sFINGER_UP = 1;
    public static final int sFLING = 2;
    private static final String sTHREAD_NAME = "ScrollPolicy";
    private static ScrollPolicy sInstance = null;
    private static Object lock = new Object();
    private static PowerHalMgr mPowerHalService = PowerHalMgrFactory.getInstance().makePowerHalMgr();
    private static int mPowerHandle = 0;
    private static int mBoostHandle = 0;
    private static int mReleaseFPSDuration = Config.sSCROLLING_HINT_DURATION;
    private static int NON_RENDER_THREAD_TID = -1;
    private static int mRenderThreadTid = -1;
    private static int MTKPOWER_HINT_UX_SCROLLING = 43;
    private static int MTKPOWER_HINT_UX_MOVE_SCROLLING = 45;
    private static long mCheckFPSTime = 100;
    private static boolean isCorrectFPS = false;
    private static int mPolicyExeCount = 0;
    private static int mFlingPolicyExeCount = 0;
    private static int mSpecialAppDesign = -1;
    public static boolean useFPSGo = false;
    private HandlerThread mWorkerThread = null;
    private WorkerHandler mWorkerHandler = null;
    private boolean waitingForReleaseFpsgo = false;
    private int waitingForReleaseFpsgoStep = -1;
    private boolean fpsgoUnderCtrlWhenFling = false;
    private boolean uboostEnable = false;

    public static ScrollPolicy getInstance() {
        if (sInstance == null) {
            synchronized (lock) {
                if (sInstance == null) {
                    sInstance = new ScrollPolicy();
                }
            }
        }
        return sInstance;
    }

    public ScrollPolicy() {
        initThread();
    }

    private void initThread() {
        HandlerThread handlerThread = this.mWorkerThread;
        if (handlerThread == null || !handlerThread.isAlive() || this.mWorkerHandler == null) {
            HandlerThread handlerThread2 = new HandlerThread("ScrollPolicy");
            this.mWorkerThread = handlerThread2;
            handlerThread2.start();
            Looper looper = this.mWorkerThread.getLooper();
            if (looper != null) {
                this.mWorkerHandler = new WorkerHandler(looper);
            } else if (Config.isBoostFwkLogEnable()) {
                Slog.i("ScrollPolicy", "Thread looper is null");
            }
        } else if (Config.isBoostFwkLogEnable()) {
            Slog.i("ScrollPolicy", "re-init");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WorkerHandler extends Handler {
        public static final int MSG_RELEASE_BEGIN = 1;
        public static final int MSG_RELEASE_END = 2;
        public static final int MSG_RELEASE_FPS_CHECK = 3;
        public static final int MSG_RELEASE_FPS_TIMEOUT = 4;
        public static final int MSG_SBE_DELAY_RELEASE_FPSGO = 11;
        public static final int MSG_SBE_FLING_POLICY_BEGIN = 8;
        public static final int MSG_SBE_FLING_POLICY_END = 9;
        public static final int MSG_SBE_FLING_POLICY_FLAG_END = 10;
        public static final int MSG_SBE_POLICY_BEGIN = 5;
        public static final int MSG_SBE_POLICY_END = 6;
        public static final int MSG_SBE_POLICY_FLAG_END = 7;

        WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ScrollPolicy.this.releaseTargetFPSInternel(true);
                    return;
                case 2:
                    ScrollPolicy.this.releaseTargetFPSInternel(false);
                    return;
                case 3:
                    boolean unused = ScrollPolicy.isCorrectFPS = true;
                    return;
                case 4:
                    ScrollPolicy.this.releaseTargetFPSInternel(false);
                    return;
                case 5:
                    ScrollIdentify.getInstance().setScrolling(true, "MSG_SBE_POLICY_BEGIN");
                    ScrollPolicy.this.mtkScrollingPolicy(true);
                    return;
                case 6:
                    ScrollPolicy.this.mtkScrollingPolicy(false);
                    return;
                case 7:
                    if (ScrollPolicy.mPolicyExeCount > 0) {
                        int unused2 = ScrollPolicy.mPolicyExeCount = 0;
                        ScrollPolicy.this.releaseFPSGOControl(false, 0);
                        ScrollIdentify.getInstance().setScrolling(false, "MSG_SBE_POLICY_FLAG_END");
                        return;
                    }
                    return;
                case 8:
                    ScrollIdentify.getInstance().setScrolling(true, "MSG_SBE_FLING_POLICY_BEGIN");
                    ScrollPolicy.this.mtkScrollingFlingPolicy(true);
                    return;
                case 9:
                    ScrollPolicy.this.mtkScrollingFlingPolicy(false);
                    return;
                case 10:
                    if (ScrollPolicy.mFlingPolicyExeCount > 0) {
                        int unused3 = ScrollPolicy.mFlingPolicyExeCount = 0;
                        ScrollPolicy.this.releaseFPSGOControl(false, 2);
                        ScrollIdentify.getInstance().setScrolling(false, "MSG_SBE_FLING_POLICY_FLAG_END");
                        return;
                    }
                    return;
                case 11:
                    ScrollPolicy.this.waitingForReleaseFpsgo = false;
                    ScrollPolicy scrollPolicy = ScrollPolicy.this;
                    scrollPolicy.releaseFPSGOControl(false, scrollPolicy.waitingForReleaseFpsgoStep);
                    ScrollPolicy.this.waitingForReleaseFpsgoStep = -1;
                    return;
                default:
                    return;
            }
        }
    }

    public void scrollHint(int step, int specialAppDesign) {
        switch (step) {
            case 0:
                if (mSpecialAppDesign == -1) {
                    mSpecialAppDesign = specialAppDesign;
                }
                if (mPolicyExeCount == 0) {
                    WorkerHandler workerHandler = this.mWorkerHandler;
                    workerHandler.sendMessage(workerHandler.obtainMessage(5, null));
                    return;
                }
                return;
            case 1:
                this.mWorkerHandler.removeMessages(5, null);
                WorkerHandler workerHandler2 = this.mWorkerHandler;
                workerHandler2.sendMessage(workerHandler2.obtainMessage(6, null));
                return;
            case 2:
                if (mFlingPolicyExeCount == 0) {
                    WorkerHandler workerHandler3 = this.mWorkerHandler;
                    workerHandler3.sendMessage(workerHandler3.obtainMessage(8, null));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void switchToFPSGo(boolean enableFPSGo) {
        useFPSGo = enableFPSGo;
        if (enableFPSGo) {
            disableMTKScrollingPolicy(false);
        }
    }

    public void disableMTKScrollingPolicy(boolean needCheckBoostNow) {
        if (!needCheckBoostNow || mPolicyExeCount != 0) {
            WorkerHandler workerHandler = this.mWorkerHandler;
            workerHandler.sendMessage(workerHandler.obtainMessage(9, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mtkScrollingPolicy(boolean enable) {
        if (mPowerHalService == null) {
            Slog.w("ScrollPolicy", "mPowerHalService is null");
            return;
        }
        if (Config.isBoostFwkLogEnable()) {
            StringBuilder sb = new StringBuilder();
            sb.append("mtkScrollingPolicy ");
            sb.append(enable ? MtkPatterns.KEY_URLDATA_START : "stop");
            Trace.traceBegin(8L, sb.toString());
        }
        if (!enable) {
            mPolicyExeCount = 0;
            this.mWorkerHandler.removeMessages(7, null);
            mPowerHalService.mtkPowerHint(MTKPOWER_HINT_UX_MOVE_SCROLLING, 0);
            delayControlFpsgo(0, true);
            if (Config.isBoostFwkLogEnable()) {
                Slog.d("ScrollPolicy", "mtkScrollingPolicy end");
            }
        } else if (mPolicyExeCount == 0) {
            if (mFlingPolicyExeCount != 0) {
                this.mWorkerHandler.removeMessages(10, null);
                mPowerHalService.mtkPowerHint(MTKPOWER_HINT_UX_SCROLLING, 0);
                mFlingPolicyExeCount = 0;
            }
            int i = mSpecialAppDesign;
            if (!(i == -1 || i == 0)) {
                mPowerHalService.mtkPowerHint(MTKPOWER_HINT_UX_MOVE_SCROLLING, mReleaseFPSDuration);
            }
            releaseFPSGOControl(true, 0);
            mPolicyExeCount++;
            WorkerHandler workerHandler = this.mWorkerHandler;
            workerHandler.sendMessageDelayed(workerHandler.obtainMessage(7, null), mReleaseFPSDuration - mCheckFPSTime);
            if (Config.isBoostFwkLogEnable()) {
                Slog.d("ScrollPolicy", "mtkScrollingPolicy begin");
            }
        }
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceEnd(8L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mtkScrollingFlingPolicy(boolean enable) {
        if (mPowerHalService == null) {
            Slog.w("ScrollPolicy", "mPowerHalService is null");
            return;
        }
        if (Config.isBoostFwkLogEnable()) {
            StringBuilder sb = new StringBuilder();
            sb.append("mtkScrollingFlingPolicy ");
            sb.append(enable ? MtkPatterns.KEY_URLDATA_START : "stop");
            Trace.traceBegin(8L, sb.toString());
        }
        if (!enable) {
            mFlingPolicyExeCount = 0;
            this.mWorkerHandler.removeMessages(10, null);
            mPowerHalService.mtkPowerHint(MTKPOWER_HINT_UX_SCROLLING, 0);
            delayControlFpsgo(2, true);
            if (Config.isBoostFwkLogEnable()) {
                Slog.d("ScrollPolicy", "mtkScrolling fling policy end");
            }
        } else if (mFlingPolicyExeCount == 0) {
            mPowerHalService.mtkPowerHint(MTKPOWER_HINT_UX_SCROLLING, mReleaseFPSDuration);
            releaseFPSGOControl(true, 2);
            mFlingPolicyExeCount++;
            WorkerHandler workerHandler = this.mWorkerHandler;
            workerHandler.sendMessageDelayed(workerHandler.obtainMessage(10, null), mReleaseFPSDuration - mCheckFPSTime);
            if (Config.isBoostFwkLogEnable()) {
                Slog.d("ScrollPolicy", "mtkScrolling fling policy end");
            }
        }
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceEnd(8L);
        }
    }

    private void delayControlFpsgo(int step, boolean enable) {
        if (!enable) {
            this.mWorkerHandler.removeMessages(11, null);
            this.waitingForReleaseFpsgoStep = -1;
        } else if (!this.waitingForReleaseFpsgo) {
            WorkerHandler workerHandler = this.mWorkerHandler;
            workerHandler.sendMessageDelayed(workerHandler.obtainMessage(11, null), 30L);
            this.waitingForReleaseFpsgoStep = step;
        }
        this.waitingForReleaseFpsgo = enable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseFPSGOControl(boolean isBegin, int step) {
        releaseFPSGOControl(isBegin, step, null);
    }

    private void releaseFPSGOControl(boolean isBegin, int step, int[] commands) {
        int renderThreadTid = getRenderThreadTid();
        int[] perf_lock_rsc = (commands == null || commands.length <= 2) ? new int[4] : commands;
        if (isBegin) {
            delayControlFpsgo(2, false);
            perf_lock_rsc[0] = PERF_RES_FPS_FPSGO_CTL;
            perf_lock_rsc[1] = renderThreadTid;
            if (step == 2) {
                if (!this.fpsgoUnderCtrlWhenFling) {
                    perf_lock_rsc[2] = PERF_RES_FPS_FPSGO_NOCTL;
                    perf_lock_rsc[3] = -renderThreadTid;
                } else {
                    perf_lock_rsc[1] = 0;
                    perf_lock_rsc[0] = 0;
                }
                this.fpsgoUnderCtrlWhenFling = true;
                uBoostAcquire();
            }
            controlFpsgoInternal(perf_lock_rsc, "start " + step);
            return;
        }
        perf_lock_rsc[0] = PERF_RES_FPS_FPSGO_CTL;
        perf_lock_rsc[1] = -renderThreadTid;
        if (step == 2) {
            perf_lock_rsc[2] = PERF_RES_FPS_FPSGO_NOCTL;
            perf_lock_rsc[3] = renderThreadTid;
            this.fpsgoUnderCtrlWhenFling = false;
            uBoostRelease();
        }
        controlFpsgoInternal(perf_lock_rsc, "end " + step);
        mSpecialAppDesign = -1;
    }

    private void controlFpsgoInternal(int[] perf_lock_rsc, String logStr) {
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceBegin(8L, logStr + " control Fpsgo" + commands2String(perf_lock_rsc));
            StringBuilder sb = new StringBuilder();
            sb.append(logStr);
            sb.append(" control Fpsgo");
            Slog.d("ScrollPolicy", sb.toString());
        }
        perfLockAcquire(perf_lock_rsc);
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceEnd(8L);
        }
    }

    private String commands2String(int[] commands) {
        if (commands == null || commands.length == 0) {
            return "";
        }
        String cStr = "";
        int l = commands.length;
        for (int i = 0; i < l; i++) {
            switch (commands[i]) {
                case PERF_RES_FPS_FSTB_TARGET_FPS_PID /* 33554944 */:
                    cStr = cStr + " PERF_RES_FPS_FSTB_TARGET_FPS_PID ";
                    break;
                case PERF_RES_FPS_FPSGO_CTL /* 33555200 */:
                    cStr = cStr + " PERF_RES_FPS_FPSGO_CTL ";
                    break;
                case PERF_RES_FPS_FPSGO_NOCTL /* 33555456 */:
                    cStr = cStr + " PERF_RES_FPS_FPSGO_NOCTL ";
                    break;
                default:
                    cStr = cStr + String.valueOf(commands[i]);
                    break;
            }
        }
        return cStr;
    }

    public void releaseTargetFPS(boolean release) {
        int renderThreadTid = getRenderThreadTid();
        if (renderThreadTid == NON_RENDER_THREAD_TID) {
            Slog.w("ScrollPolicy", "cannot found render thread");
        } else if (release) {
            isCorrectFPS = false;
            WorkerHandler workerHandler = this.mWorkerHandler;
            workerHandler.sendMessage(workerHandler.obtainMessage(1, null));
            this.mWorkerHandler.removeMessages(4, null);
            WorkerHandler workerHandler2 = this.mWorkerHandler;
            workerHandler2.sendMessageDelayed(workerHandler2.obtainMessage(3, null), mCheckFPSTime);
        } else if (isCorrectFPS) {
            WorkerHandler workerHandler3 = this.mWorkerHandler;
            workerHandler3.sendMessage(workerHandler3.obtainMessage(2, null));
        } else {
            WorkerHandler workerHandler4 = this.mWorkerHandler;
            workerHandler4.sendMessageDelayed(workerHandler4.obtainMessage(4, null), mReleaseFPSDuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseTargetFPSInternel(boolean isBegin) {
        ScrollIdentify.getInstance().setScrolling(isBegin, "release Target FPS");
        int renderThreadTid = getRenderThreadTid();
        int[] perf_lock_rsc = new int[6];
        perf_lock_rsc[4] = PERF_RES_FPS_FSTB_TARGET_FPS_PID;
        perf_lock_rsc[5] = isBegin ? renderThreadTid : -renderThreadTid;
        if (Config.isBoostFwkLogEnable()) {
            StringBuilder sb = new StringBuilder();
            sb.append("release Target FPS");
            sb.append(isBegin ? MtkPatterns.KEY_URLDATA_START : "stop");
            Trace.traceBegin(8L, sb.toString());
            Slog.d("ScrollPolicy", "release Target FPS");
        }
        releaseFPSGOControl(isBegin, 2, perf_lock_rsc);
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceEnd(8L);
        }
    }

    private int getRenderThreadTid() {
        if (mRenderThreadTid == NON_RENDER_THREAD_TID) {
            mRenderThreadTid = TasksUtil.findRenderTheadTid(Process.myPid());
        }
        return mRenderThreadTid;
    }

    private void perfLockAcquire(int[] resList) {
        PowerHalMgr powerHalMgr = mPowerHalService;
        if (powerHalMgr != null) {
            int perfLockAcquire = powerHalMgr.perfLockAcquire(mPowerHandle, mReleaseFPSDuration, resList);
            mPowerHandle = perfLockAcquire;
            mPowerHalService.perfLockRelease(perfLockAcquire);
        }
    }

    private void uBoostAcquire() {
        PowerHalMgr powerHalMgr = mPowerHalService;
        if (powerHalMgr != null && !this.uboostEnable) {
            this.uboostEnable = true;
            int[] perf_lock_rsc = {PERF_RES_FPS_FPSGO_UBOOST, 1};
            mBoostHandle = powerHalMgr.perfLockAcquire(mBoostHandle, mReleaseFPSDuration, perf_lock_rsc);
        }
    }

    private void uBoostRelease() {
        PowerHalMgr powerHalMgr = mPowerHalService;
        if (powerHalMgr != null && this.uboostEnable) {
            this.uboostEnable = false;
            powerHalMgr.perfLockRelease(mBoostHandle);
        }
    }
}
