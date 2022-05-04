package com.mediatek.boostfwk.policy.frame;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.Trace;
import android.util.Slog;
import com.mediatek.boostfwk.identify.scroll.ScrollIdentify;
import com.mediatek.boostfwk.policy.scroll.ScrollPolicy;
import com.mediatek.boostfwk.utils.Config;
import com.mediatek.boostfwk.utils.TasksUtil;
import com.mediatek.boostfwk.utils.Util;
import com.mediatek.powerhalmgr.PowerHalMgr;
import com.mediatek.powerhalmgr.PowerHalMgrFactory;
import com.mediatek.powerhalwrapper.PowerHalWrapper;
/* loaded from: classes.dex */
public class FramePolicy {
    private static final double CHECK_POINT = 0.5d;
    private static final int FRAME_STEP_BASIC = -1000;
    private static final int NON_FRAME_STEP = -1000;
    private static final int NON_RENDER_THREAD_TID = -1;
    private static final double NO_DRAW_FRAME_VSYNC_RATIO = 0.1d;
    private static final int PERF_RES_FPS_FBT_RESCUE_SBE_RESCUE = 33802752;
    private static final int PERF_RES_FPS_FPSGO_STOP_BOOST = 33571328;
    private static final int RECEIVE_VSYNC_TO_INPUT = -999;
    private static final String TAG = "FramePolicy";
    private static final int mReleaseFPSDuration = 3000;
    private static final String sTHREAD_NAME = "FramePolicy";
    private PowerHalMgr mPowerHalService;
    private PowerHalWrapper mPowerHalWrap;
    private static FramePolicy sInstance = null;
    private static Object sLock = new Object();
    private static float mFrameIntervalTime = 0.0f;
    private static float mLimitVsyncTime = 0.0f;
    private static float mRefreshrate = 0.0f;
    private static int mFrameStep = -1000;
    private static boolean isNoDraw = true;
    private static boolean mIsDoframeCheck = false;
    private static boolean mAnimAcquiredLock = false;
    private static boolean mTranversalAcquiredLock = false;
    private static boolean isTranversalDraw = false;
    private static boolean isAnimationStepEnd = false;
    private static boolean isBoosting = false;
    private HandlerThread mWorkerThread = null;
    private WorkerHandler mWorkerHandler = null;
    private final int SBE_RESUCE_MODE_END = 0;
    private final int SBE_RESUCE_MODE_START = 1;
    private final int SBE_RESUCE_MODE_TO_QUEUE_END = 2;
    private int curFrameRescueMode = 2;
    private long mFrameId = -1;
    private int mRenderThreadTid = -1;
    private int mPowerHandle = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WorkerHandler extends Handler {
        public static final int MSG_FRAME_BEGIN = 1;
        public static final int MSG_FRAME_END = 2;
        public static final int MSG_NO_DRAW = 4;
        public static final int MSG_STEP_CHECK = 3;

        WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    FramePolicy.this.doFrameHintInternel(true, ((Long) msg.obj).longValue());
                    return;
                case 2:
                    FramePolicy.this.doFrameHintInternel(false, ((Long) msg.obj).longValue());
                    return;
                case 3:
                    FramePolicy.this.doFrameStepHintInternel(FramePolicy.mFrameStep);
                    return;
                case 4:
                    FramePolicy.this.frameDraw(false);
                    return;
                default:
                    return;
            }
        }
    }

    public static FramePolicy getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new FramePolicy();
                }
            }
        }
        return sInstance;
    }

    public FramePolicy() {
        this.mPowerHalService = null;
        this.mPowerHalWrap = null;
        initThread();
        this.mPowerHalService = PowerHalMgrFactory.getInstance().makePowerHalMgr();
        this.mPowerHalWrap = PowerHalWrapper.getInstance();
    }

    private void initThread() {
        HandlerThread handlerThread = this.mWorkerThread;
        if (handlerThread == null || !handlerThread.isAlive() || this.mWorkerHandler == null) {
            HandlerThread handlerThread2 = new HandlerThread("FramePolicy");
            this.mWorkerThread = handlerThread2;
            handlerThread2.start();
            Looper looper = this.mWorkerThread.getLooper();
            if (looper != null) {
                this.mWorkerHandler = new WorkerHandler(looper);
            } else if (Config.isBoostFwkLogEnable()) {
                Slog.i("FramePolicy", "Thread looper is null");
            }
        } else if (Config.isBoostFwkLogEnable()) {
            Slog.i("FramePolicy", "re-init");
        }
    }

    public boolean initLimitTime(float frameIntervalTime, float refresh) {
        if (frameIntervalTime > 0.0f && frameIntervalTime != mFrameIntervalTime) {
            mFrameIntervalTime = frameIntervalTime;
            mLimitVsyncTime = (float) ((frameIntervalTime * CHECK_POINT) - CHECK_POINT);
            mRefreshrate = refresh;
        }
        return ScrollIdentify.getInstance().isScroll() && mFrameIntervalTime != 0.0f;
    }

    public void doFrameHint(boolean isBegin, long frameId) {
        if (Config.isBoostFwkLogEnable()) {
            Slog.d("FramePolicy", "vsync is begin = " + isBegin);
        }
        mIsDoframeCheck = isBegin;
        if (isBegin) {
            setFrameStep(RECEIVE_VSYNC_TO_INPUT);
            WorkerHandler workerHandler = this.mWorkerHandler;
            workerHandler.sendMessage(workerHandler.obtainMessage(1, Long.valueOf(frameId)));
            return;
        }
        if (!isNoDraw && isTranversalDraw) {
            WorkerHandler workerHandler2 = this.mWorkerHandler;
            workerHandler2.sendMessageDelayed(workerHandler2.obtainMessage(4, null), (long) drawFrameDelayTime());
        }
        WorkerHandler workerHandler3 = this.mWorkerHandler;
        workerHandler3.sendMessage(workerHandler3.obtainMessage(2, Long.valueOf(frameId)));
    }

    public void doFrameStepHint(boolean isBegin, int step) {
        if (isBegin) {
            setFrameStep(step);
            if (step == 3) {
                mTranversalAcquiredLock = true;
                WorkerHandler workerHandler = this.mWorkerHandler;
                workerHandler.sendMessage(workerHandler.obtainMessage(3, null));
            }
        } else if (step == 1) {
            isAnimationStepEnd = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doFrameHintInternel(boolean isBegin, long frameId) {
        if (isBegin) {
            this.mFrameId = frameId;
            if (mLimitVsyncTime != 0.0f) {
                if (Config.isBoostFwkLogEnable()) {
                    Slog.d("FramePolicy", "scrolling!! try check animation and draw state.");
                }
                WorkerHandler workerHandler = this.mWorkerHandler;
                workerHandler.sendMessageDelayed(workerHandler.obtainMessage(3), mLimitVsyncTime);
                return;
            }
            return;
        }
        this.mWorkerHandler.removeMessages(3, null);
        if (isBoosting) {
            isBoosting = false;
            this.curFrameRescueMode = 0;
            powerHintForRender(PERF_RES_FPS_FBT_RESCUE_SBE_RESCUE, "frame end");
        }
        mAnimAcquiredLock = false;
        mTranversalAcquiredLock = false;
        isTranversalDraw = false;
        isAnimationStepEnd = false;
        mFrameStep = -1000;
        this.mFrameId = -1L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void doFrameStepHintInternel(int step) {
        if (mIsDoframeCheck || step != -1000) {
            switch (step) {
                case RECEIVE_VSYNC_TO_INPUT /* -999 */:
                case 0:
                case 1:
                    if (!isAnimationStepEnd) {
                        isBoosting = true;
                        this.curFrameRescueMode = 1;
                        powerHintForRender(PERF_RES_FPS_FBT_RESCUE_SBE_RESCUE, "animation end, curStep=" + step);
                    }
                    if (!mAnimAcquiredLock && step != RECEIVE_VSYNC_TO_INPUT) {
                        if (Config.isBoostFwkLogEnable()) {
                            Slog.d("FramePolicy", "input/anim hint drop, enable rescue!");
                        }
                        frameDraw(true);
                        mAnimAcquiredLock = true;
                        return;
                    }
                    break;
                case 3:
                    break;
                default:
                    return;
            }
            if (mTranversalAcquiredLock && !mAnimAcquiredLock) {
                if (Config.isBoostFwkLogEnable()) {
                    Slog.d("FramePolicy", "traversal step, enable rescue!");
                }
                frameDraw(true);
                mTranversalAcquiredLock = false;
                mAnimAcquiredLock = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void frameDraw(boolean isDraw) {
        if (!isDraw || this.mFrameId != -1) {
            if (isDraw) {
                if (isNoDraw) {
                    if (Config.isBoostFwkLogEnable()) {
                        Trace.traceBegin(8L, "Draw, notify FPSGO draw" + this.mFrameId);
                    }
                    if (Config.isBoostFwkLogEnable()) {
                        Trace.traceEnd(8L);
                    }
                }
                this.mWorkerHandler.removeMessages(4, null);
                isNoDraw = false;
                isTranversalDraw = true;
                return;
            }
            powerHintForRender(PERF_RES_FPS_FPSGO_STOP_BOOST, "STOP: No draw");
            isNoDraw = true;
        } else if (Config.isBoostFwkLogEnable()) {
            Slog.d("FramePolicy", "frame clear when rescue. mFrameId = " + this.mFrameId);
        }
    }

    private int getRescueStrength(float refreshRate) {
        if (refreshRate < 35.0f) {
            return 25;
        }
        if (refreshRate <= 65.0f) {
            return 48;
        }
        if (refreshRate < 95.0f) {
            return 56;
        }
        return 60;
    }

    private void powerHintForRender(int cmd, String tagMsg) {
        int renderThreadTid = getRenderThreadTid();
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceBegin(8L, "hint for [" + tagMsg + "] render id = " + renderThreadTid);
        }
        switch (cmd) {
            case PERF_RES_FPS_FPSGO_STOP_BOOST /* 33571328 */:
                int[] perf_lock_rsc = {cmd, renderThreadTid};
                perfLockAcquire(perf_lock_rsc);
                ScrollPolicy.getInstance().disableMTKScrollingPolicy(true);
                break;
            case PERF_RES_FPS_FBT_RESCUE_SBE_RESCUE /* 33802752 */:
                int strength = getRescueStrength(mRefreshrate);
                this.mPowerHalWrap.mtkNotifySbeRescue(renderThreadTid, this.curFrameRescueMode, strength);
                break;
            default:
                if (Config.isBoostFwkLogEnable()) {
                    Slog.d("FramePolicy", "not surpport for cmd = " + cmd);
                    break;
                }
                break;
        }
        if (Config.isBoostFwkLogEnable()) {
            Trace.traceEnd(8L);
        }
    }

    private void setFrameStep(int step) {
        if (step > mFrameStep) {
            mFrameStep = step;
        }
    }

    private double drawFrameDelayTime() {
        if (mFrameIntervalTime == 0.0f) {
            return -1.0d;
        }
        float refreshRate = Util.getRefreshRate();
        double delayCheckTime = mFrameIntervalTime * refreshRate * NO_DRAW_FRAME_VSYNC_RATIO;
        return delayCheckTime;
    }

    private int getRenderThreadTid() {
        if (this.mRenderThreadTid == -1) {
            this.mRenderThreadTid = TasksUtil.findRenderTheadTid(Process.myPid());
        }
        return this.mRenderThreadTid;
    }

    private void perfLockAcquire(int[] resList) {
        PowerHalMgr powerHalMgr = this.mPowerHalService;
        if (powerHalMgr != null) {
            int perfLockAcquire = powerHalMgr.perfLockAcquire(this.mPowerHandle, 0, resList);
            this.mPowerHandle = perfLockAcquire;
            this.mPowerHalService.perfLockRelease(perfLockAcquire);
        }
    }
}
