package com.android.internal.jank;

import android.graphics.HardwareRendererObserver;
import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import android.util.SparseArray;
import android.util.TimeUtils;
import android.view.Choreographer;
import android.view.FrameMetrics;
import android.view.SurfaceControl;
import android.view.ThreadedRenderer;
import android.view.ViewRootImpl;
import com.android.internal.jank.FrameTracker;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes4.dex */
public class FrameTracker extends SurfaceControl.OnJankDataListener implements HardwareRendererObserver.OnFrameMetricsAvailableListener {
    private static final boolean DEBUG = false;
    private static final long INVALID_ID = -1;
    public static final int NANOS_IN_MILLISECOND = 1000000;
    static final int REASON_CANCEL_NORMAL = 16;
    static final int REASON_CANCEL_NOT_BEGUN = 17;
    static final int REASON_CANCEL_SAME_VSYNC = 18;
    static final int REASON_END_NORMAL = 0;
    static final int REASON_END_SURFACE_DESTROYED = 1;
    static final int REASON_END_UNKNOWN = -1;
    private static final String TAG = "FrameTracker";
    private final ChoreographerWrapper mChoreographer;
    private final Handler mHandler;
    private FrameTrackerListener mListener;
    private boolean mMetricsFinalized;
    private final FrameMetricsWrapper mMetricsWrapper;
    private final HardwareRendererObserver mObserver;
    private final ThreadedRendererWrapper mRendererWrapper;
    private final InteractionJankMonitor.Session mSession;
    private final ViewRootImpl.SurfaceChangedCallback mSurfaceChangedCallback;
    private SurfaceControl mSurfaceControl;
    private final SurfaceControlWrapper mSurfaceControlWrapper;
    private final int mTraceThresholdFrameTimeMillis;
    private final int mTraceThresholdMissedFrames;
    private final ViewRootWrapper mViewRoot;
    private final SparseArray<JankInfo> mJankInfos = new SparseArray<>();
    private long mBeginVsyncId = -1;
    private long mEndVsyncId = -1;
    private boolean mCancelled = false;
    private boolean mTracingStarted = false;

    /* loaded from: classes4.dex */
    public interface FrameTrackerListener {
        void onCujEvents(InteractionJankMonitor.Session session, String str);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Reasons {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class JankInfo {
        long frameVsyncId;
        boolean hwuiCallbackFired;
        boolean isFirstFrame;
        int jankType;
        boolean surfaceControlCallbackFired;
        long totalDurationNanos;

        static JankInfo createFromHwuiCallback(long frameVsyncId, long totalDurationNanos, boolean isFirstFrame) {
            return new JankInfo(frameVsyncId, true, false, 0, totalDurationNanos, isFirstFrame);
        }

        static JankInfo createFromSurfaceControlCallback(long frameVsyncId, int jankType) {
            return new JankInfo(frameVsyncId, false, true, jankType, 0L, false);
        }

        private JankInfo(long frameVsyncId, boolean hwuiCallbackFired, boolean surfaceControlCallbackFired, int jankType, long totalDurationNanos, boolean isFirstFrame) {
            this.frameVsyncId = frameVsyncId;
            this.hwuiCallbackFired = hwuiCallbackFired;
            this.surfaceControlCallbackFired = surfaceControlCallbackFired;
            this.totalDurationNanos = totalDurationNanos;
            this.jankType = jankType;
            this.isFirstFrame = isFirstFrame;
        }
    }

    public FrameTracker(InteractionJankMonitor.Session session, Handler handler, ThreadedRendererWrapper renderer, ViewRootWrapper viewRootWrapper, SurfaceControlWrapper surfaceControlWrapper, ChoreographerWrapper choreographer, FrameMetricsWrapper metrics, int traceThresholdMissedFrames, int traceThresholdFrameTimeMillis, FrameTrackerListener listener) {
        this.mSession = session;
        this.mRendererWrapper = renderer;
        this.mMetricsWrapper = metrics;
        this.mViewRoot = viewRootWrapper;
        this.mChoreographer = choreographer;
        this.mSurfaceControlWrapper = surfaceControlWrapper;
        this.mHandler = handler;
        this.mObserver = new HardwareRendererObserver(this, metrics.getTiming(), handler, false);
        this.mTraceThresholdMissedFrames = traceThresholdMissedFrames;
        this.mTraceThresholdFrameTimeMillis = traceThresholdFrameTimeMillis;
        this.mListener = listener;
        if (viewRootWrapper.getSurfaceControl().isValid()) {
            this.mSurfaceControl = viewRootWrapper.getSurfaceControl();
        }
        AnonymousClass1 r0 = new AnonymousClass1(viewRootWrapper);
        this.mSurfaceChangedCallback = r0;
        viewRootWrapper.addSurfaceChangedCallback(r0);
    }

    /* renamed from: com.android.internal.jank.FrameTracker$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    class AnonymousClass1 implements ViewRootImpl.SurfaceChangedCallback {
        final /* synthetic */ ViewRootWrapper val$viewRootWrapper;

        AnonymousClass1(ViewRootWrapper viewRootWrapper) {
            this.val$viewRootWrapper = viewRootWrapper;
        }

        @Override // android.view.ViewRootImpl.SurfaceChangedCallback
        public void surfaceCreated(SurfaceControl.Transaction t) {
            synchronized (FrameTracker.this) {
                if (FrameTracker.this.mSurfaceControl == null) {
                    FrameTracker.this.mSurfaceControl = this.val$viewRootWrapper.getSurfaceControl();
                    if (FrameTracker.this.mBeginVsyncId != -1) {
                        SurfaceControlWrapper surfaceControlWrapper = FrameTracker.this.mSurfaceControlWrapper;
                        FrameTracker frameTracker = FrameTracker.this;
                        surfaceControlWrapper.addJankStatsListener(frameTracker, frameTracker.mSurfaceControl);
                        FrameTracker.this.postTraceStartMarker();
                    }
                }
            }
        }

        @Override // android.view.ViewRootImpl.SurfaceChangedCallback
        public void surfaceReplaced(SurfaceControl.Transaction t) {
        }

        @Override // android.view.ViewRootImpl.SurfaceChangedCallback
        public void surfaceDestroyed() {
            if (!FrameTracker.this.mMetricsFinalized) {
                FrameTracker.this.mSurfaceControlWrapper.removeJankStatsListener(FrameTracker.this);
            }
            FrameTracker.this.mHandler.postDelayed(new Runnable() { // from class: com.android.internal.jank.FrameTracker$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FrameTracker.AnonymousClass1.this.lambda$surfaceDestroyed$0$FrameTracker$1();
                }
            }, 50L);
        }

        public /* synthetic */ void lambda$surfaceDestroyed$0$FrameTracker$1() {
            synchronized (FrameTracker.this) {
                if (!FrameTracker.this.mMetricsFinalized) {
                    FrameTracker.this.end(1);
                    FrameTracker frameTracker = FrameTracker.this;
                    frameTracker.finish(frameTracker.mJankInfos.size() - 1);
                }
            }
        }
    }

    public synchronized void begin() {
        this.mBeginVsyncId = this.mChoreographer.getVsyncId() + 1;
        if (this.mSurfaceControl != null) {
            postTraceStartMarker();
        }
        this.mRendererWrapper.addObserver(this.mObserver);
        SurfaceControl surfaceControl = this.mSurfaceControl;
        if (surfaceControl != null) {
            this.mSurfaceControlWrapper.addJankStatsListener(this, surfaceControl);
        }
        FrameTrackerListener frameTrackerListener = this.mListener;
        if (frameTrackerListener != null) {
            frameTrackerListener.onCujEvents(this.mSession, InteractionJankMonitor.ACTION_SESSION_BEGIN);
        }
    }

    public void postTraceStartMarker() {
        this.mChoreographer.mChoreographer.postCallback(0, new Runnable() { // from class: com.android.internal.jank.FrameTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FrameTracker.this.lambda$postTraceStartMarker$0$FrameTracker();
            }
        }, null);
    }

    public /* synthetic */ void lambda$postTraceStartMarker$0$FrameTracker() {
        synchronized (this) {
            if (!this.mCancelled && this.mEndVsyncId == -1) {
                this.mTracingStarted = true;
                Trace.beginAsyncSection(this.mSession.getName(), (int) this.mBeginVsyncId);
            }
        }
    }

    public synchronized void end(int reason) {
        if (this.mEndVsyncId == -1) {
            long vsyncId = this.mChoreographer.getVsyncId();
            this.mEndVsyncId = vsyncId;
            long j = this.mBeginVsyncId;
            if (j == -1) {
                cancel(17);
            } else if (vsyncId <= j) {
                cancel(18);
            } else {
                Trace.endAsyncSection(this.mSession.getName(), (int) this.mBeginVsyncId);
                this.mSession.setReason(reason);
                FrameTrackerListener frameTrackerListener = this.mListener;
                if (frameTrackerListener != null) {
                    frameTrackerListener.onCujEvents(this.mSession, InteractionJankMonitor.ACTION_SESSION_END);
                }
            }
        }
    }

    public synchronized void cancel(int reason) {
        if (this.mTracingStarted) {
            Trace.endAsyncSection(this.mSession.getName(), (int) this.mBeginVsyncId);
        }
        this.mCancelled = true;
        removeObservers();
        this.mSession.setReason(reason);
        FrameTrackerListener frameTrackerListener = this.mListener;
        if (frameTrackerListener != null) {
            frameTrackerListener.onCujEvents(this.mSession, InteractionJankMonitor.ACTION_SESSION_CANCEL);
        }
    }

    @Override // android.view.SurfaceControl.OnJankDataListener
    public synchronized void onJankDataAvailable(SurfaceControl.JankData[] jankData) {
        if (!this.mCancelled) {
            for (SurfaceControl.JankData jankStat : jankData) {
                if (isInRange(jankStat.frameVsyncId)) {
                    JankInfo info = findJankInfo(jankStat.frameVsyncId);
                    if (info != null) {
                        info.surfaceControlCallbackFired = true;
                        info.jankType = jankStat.jankType;
                    } else {
                        this.mJankInfos.put((int) jankStat.frameVsyncId, JankInfo.createFromSurfaceControlCallback(jankStat.frameVsyncId, jankStat.jankType));
                    }
                }
            }
            processJankInfos();
        }
    }

    private JankInfo findJankInfo(long frameVsyncId) {
        return this.mJankInfos.get((int) frameVsyncId);
    }

    private boolean isInRange(long vsyncId) {
        return vsyncId >= this.mBeginVsyncId;
    }

    @Override // android.graphics.HardwareRendererObserver.OnFrameMetricsAvailableListener
    public synchronized void onFrameMetricsAvailable(int dropCountSinceLastInvocation) {
        if (!this.mCancelled) {
            long totalDurationNanos = this.mMetricsWrapper.getMetric(8);
            boolean isFirstFrame = this.mMetricsWrapper.getMetric(9) == 1;
            long frameVsyncId = this.mMetricsWrapper.getTiming()[1];
            if (isInRange(frameVsyncId)) {
                JankInfo info = findJankInfo(frameVsyncId);
                if (info != null) {
                    info.hwuiCallbackFired = true;
                    info.totalDurationNanos = totalDurationNanos;
                    info.isFirstFrame = isFirstFrame;
                } else {
                    this.mJankInfos.put((int) frameVsyncId, JankInfo.createFromHwuiCallback(frameVsyncId, totalDurationNanos, isFirstFrame));
                }
                processJankInfos();
            }
        }
    }

    private int getIndexOnOrAfterEnd() {
        SparseArray<JankInfo> sparseArray;
        if (this.mEndVsyncId == -1 || this.mMetricsFinalized) {
            return -1;
        }
        JankInfo last = this.mJankInfos.size() == 0 ? null : this.mJankInfos.valueAt(sparseArray.size() - 1);
        if (last == null || last.frameVsyncId < this.mEndVsyncId) {
            return -1;
        }
        int lastIndex = -1;
        for (int i = this.mJankInfos.size() - 1; i >= 0; i--) {
            JankInfo info = this.mJankInfos.valueAt(i);
            if (info.frameVsyncId < this.mEndVsyncId) {
                break;
            }
            if (info.hwuiCallbackFired && info.surfaceControlCallbackFired) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    private void processJankInfos() {
        int indexOnOrAfterEnd = getIndexOnOrAfterEnd();
        if (indexOnOrAfterEnd != -1) {
            finish(indexOnOrAfterEnd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(int indexOnOrAfterEnd) {
        int i;
        int i2;
        int totalFramesCount;
        int i3 = 1;
        this.mMetricsFinalized = true;
        removeObservers();
        int totalFramesCount2 = 0;
        int i4 = 0;
        long maxFrameTimeNanos = 0;
        int missedFramesCount = 0;
        int missedAppFramesCount = 0;
        int missedSfFramesCount = 0;
        while (i4 <= indexOnOrAfterEnd) {
            JankInfo info = this.mJankInfos.valueAt(i4);
            if (info.isFirstFrame) {
                i2 = i4;
            } else {
                if (info.surfaceControlCallbackFired) {
                    int totalFramesCount3 = totalFramesCount2 + 1;
                    boolean missedFrame = false;
                    if (!((info.jankType & 16) == 0 && (info.jankType & 8) == 0)) {
                        Log.w(TAG, "Missed App frame:" + info.jankType);
                        missedAppFramesCount++;
                        missedFrame = true;
                    }
                    if (!((info.jankType & i3) == 0 && (info.jankType & 2) == 0 && (info.jankType & 4) == 0 && (info.jankType & 32) == 0)) {
                        Log.w(TAG, "Missed SF frame:" + info.jankType);
                        missedSfFramesCount++;
                        missedFrame = true;
                    }
                    if (missedFrame) {
                        missedFramesCount++;
                    }
                    if (!info.hwuiCallbackFired) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Missing HWUI jank callback for vsyncId: ");
                        totalFramesCount = totalFramesCount3;
                        sb.append(info.frameVsyncId);
                        Log.w(TAG, sb.toString());
                    } else {
                        totalFramesCount = totalFramesCount3;
                    }
                    totalFramesCount2 = totalFramesCount;
                }
                if (info.hwuiCallbackFired) {
                    i2 = i4;
                    long maxFrameTimeNanos2 = Math.max(info.totalDurationNanos, maxFrameTimeNanos);
                    if (!info.surfaceControlCallbackFired) {
                        Log.w(TAG, "Missing SF jank callback for vsyncId: " + info.frameVsyncId);
                    }
                    maxFrameTimeNanos = maxFrameTimeNanos2;
                } else {
                    i2 = i4;
                }
            }
            i4 = i2 + 1;
            i3 = 1;
        }
        Trace.traceCounter(4096L, this.mSession.getName() + "#missedFrames", missedFramesCount);
        Trace.traceCounter(4096L, this.mSession.getName() + "#missedAppFrames", missedAppFramesCount);
        Trace.traceCounter(4096L, this.mSession.getName() + "#missedSfFrames", missedSfFramesCount);
        Trace.traceCounter(4096L, this.mSession.getName() + "#totalFrames", totalFramesCount2);
        Trace.traceCounter(4096L, this.mSession.getName() + "#maxFrameTimeMillis", (int) (maxFrameTimeNanos / TimeUtils.NANOS_PER_MS));
        int i5 = this.mTraceThresholdMissedFrames;
        boolean overFrameTimeThreshold = false;
        boolean overMissedFramesThreshold = i5 != -1 && missedFramesCount >= i5;
        if (this.mTraceThresholdFrameTimeMillis != -1 && maxFrameTimeNanos >= i * 1000000) {
            overFrameTimeThreshold = true;
        }
        if (overMissedFramesThreshold || overFrameTimeThreshold) {
            triggerPerfetto();
        }
        if (this.mSession.logToStatsd()) {
            FrameworkStatsLog.write(305, this.mSession.getStatsdInteractionType(), totalFramesCount2, missedFramesCount, maxFrameTimeNanos, missedSfFramesCount, missedAppFramesCount);
            FrameTrackerListener frameTrackerListener = this.mListener;
            if (frameTrackerListener != null) {
                frameTrackerListener.onCujEvents(this.mSession, InteractionJankMonitor.ACTION_METRICS_LOGGED);
            }
        }
    }

    public void removeObservers() {
        this.mRendererWrapper.removeObserver(this.mObserver);
        this.mSurfaceControlWrapper.removeJankStatsListener(this);
        ViewRootImpl.SurfaceChangedCallback surfaceChangedCallback = this.mSurfaceChangedCallback;
        if (surfaceChangedCallback != null) {
            this.mViewRoot.removeSurfaceChangedCallback(surfaceChangedCallback);
        }
    }

    public void triggerPerfetto() {
        InteractionJankMonitor.getInstance().trigger(this.mSession);
    }

    /* loaded from: classes4.dex */
    public static class FrameMetricsWrapper {
        private final FrameMetrics mFrameMetrics = new FrameMetrics();

        public long[] getTiming() {
            return this.mFrameMetrics.mTimingData;
        }

        public long getMetric(int index) {
            return this.mFrameMetrics.getMetric(index);
        }
    }

    /* loaded from: classes4.dex */
    public static class ThreadedRendererWrapper {
        private final ThreadedRenderer mRenderer;

        public ThreadedRendererWrapper(ThreadedRenderer renderer) {
            this.mRenderer = renderer;
        }

        public void addObserver(HardwareRendererObserver observer) {
            this.mRenderer.addObserver(observer);
        }

        public void removeObserver(HardwareRendererObserver observer) {
            this.mRenderer.removeObserver(observer);
        }
    }

    /* loaded from: classes4.dex */
    public static class ViewRootWrapper {
        private final ViewRootImpl mViewRoot;

        public ViewRootWrapper(ViewRootImpl viewRoot) {
            this.mViewRoot = viewRoot;
        }

        public void addSurfaceChangedCallback(ViewRootImpl.SurfaceChangedCallback callback) {
            this.mViewRoot.addSurfaceChangedCallback(callback);
        }

        public void removeSurfaceChangedCallback(ViewRootImpl.SurfaceChangedCallback callback) {
            this.mViewRoot.removeSurfaceChangedCallback(callback);
        }

        public SurfaceControl getSurfaceControl() {
            return this.mViewRoot.getSurfaceControl();
        }
    }

    /* loaded from: classes4.dex */
    public static class SurfaceControlWrapper {
        public void addJankStatsListener(SurfaceControl.OnJankDataListener listener, SurfaceControl surfaceControl) {
            SurfaceControl.addJankDataListener(listener, surfaceControl);
        }

        public void removeJankStatsListener(SurfaceControl.OnJankDataListener listener) {
            SurfaceControl.removeJankDataListener(listener);
        }
    }

    /* loaded from: classes4.dex */
    public static class ChoreographerWrapper {
        private final Choreographer mChoreographer;

        public ChoreographerWrapper(Choreographer choreographer) {
            this.mChoreographer = choreographer;
        }

        public long getVsyncId() {
            return this.mChoreographer.getVsyncId();
        }
    }
}
