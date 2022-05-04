package android.media.tv.tuner;

import android.annotation.SystemApi;
import android.content.Context;
import android.media.MediaMetrics;
import android.media.tv.tuner.Tuner;
import android.media.tv.tuner.dvr.DvrPlayback;
import android.media.tv.tuner.dvr.DvrRecorder;
import android.media.tv.tuner.dvr.OnPlaybackStatusChangedListener;
import android.media.tv.tuner.dvr.OnRecordStatusChangedListener;
import android.media.tv.tuner.filter.Filter;
import android.media.tv.tuner.filter.FilterCallback;
import android.media.tv.tuner.filter.TimeFilter;
import android.media.tv.tuner.frontend.Atsc3PlpInfo;
import android.media.tv.tuner.frontend.FrontendInfo;
import android.media.tv.tuner.frontend.FrontendSettings;
import android.media.tv.tuner.frontend.FrontendStatus;
import android.media.tv.tuner.frontend.OnTuneEventListener;
import android.media.tv.tuner.frontend.ScanCallback;
import android.media.tv.tunerresourcemanager.ResourceClientProfile;
import android.media.tv.tunerresourcemanager.TunerCiCamRequest;
import android.media.tv.tunerresourcemanager.TunerDemuxRequest;
import android.media.tv.tunerresourcemanager.TunerDescramblerRequest;
import android.media.tv.tunerresourcemanager.TunerFrontendRequest;
import android.media.tv.tunerresourcemanager.TunerLnbRequest;
import android.media.tv.tunerresourcemanager.TunerResourceManager;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes2.dex */
public class Tuner implements AutoCloseable {
    private static final boolean DEBUG = false;
    public static final int DVR_TYPE_PLAYBACK = 1;
    public static final int DVR_TYPE_RECORD = 0;
    private static final int FILTER_CLEANUP_THRESHOLD = 256;
    public static final int INVALID_AV_SYNC_ID = -1;
    public static final int INVALID_FILTER_ID = -1;
    public static final long INVALID_FILTER_ID_LONG = -1;
    public static final int INVALID_FIRST_MACROBLOCK_IN_SLICE = -1;
    public static final int INVALID_FRONTEND_ID = -1;
    public static final int INVALID_FRONTEND_SETTING_FREQUENCY = -1;
    public static final int INVALID_LNB_ID = -1;
    public static final int INVALID_LTS_ID = -1;
    public static final int INVALID_MMTP_RECORD_EVENT_MPT_SEQUENCE_NUM = -1;
    public static final int INVALID_STREAM_ID = 65535;
    public static final long INVALID_TIMESTAMP = -1;
    public static final int INVALID_TS_PID = 65535;
    private static final int MSG_ON_FILTER_EVENT = 2;
    private static final int MSG_ON_FILTER_STATUS = 3;
    private static final int MSG_ON_LNB_EVENT = 4;
    private static final int MSG_RESOURCE_LOST = 1;
    public static final int RESULT_INVALID_ARGUMENT = 4;
    public static final int RESULT_INVALID_STATE = 3;
    public static final int RESULT_NOT_INITIALIZED = 2;
    public static final int RESULT_OUT_OF_MEMORY = 5;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_UNAVAILABLE = 1;
    public static final int RESULT_UNKNOWN_ERROR = 6;
    public static final int SCAN_TYPE_AUTO = 1;
    public static final int SCAN_TYPE_BLIND = 2;
    public static final int SCAN_TYPE_UNDEFINED = 0;
    private static final String TAG = "MediaTvTuner";
    public static final byte[] VOID_KEYTOKEN = {0};
    private static int sTunerVersion;
    private final int mClientId;
    private final Context mContext;
    private Integer mDemuxHandle;
    private Frontend mFrontend;
    private Integer mFrontendCiCamHandle;
    private Integer mFrontendCiCamId;
    private Integer mFrontendHandle;
    private FrontendInfo mFrontendInfo;
    private EventHandler mHandler;
    private Lnb mLnb;
    private Integer mLnbHandle;
    private long mNativeContext;
    private OnResourceLostListener mOnResourceLostListener;
    private Executor mOnResourceLostListenerExecutor;
    private Executor mOnTuneEventExecutor;
    private OnTuneEventListener mOnTuneEventListener;
    private final TunerResourceManager.ResourcesReclaimListener mResourceListener;
    private ScanCallback mScanCallback;
    private Executor mScanCallbackExecutor;
    private final TunerResourceManager mTunerResourceManager;
    private int mUserId;
    private Boolean mIsSharedFrontend = false;
    private int mFrontendType = 0;
    private final Object mOnTuneEventLock = new Object();
    private final Object mScanCallbackLock = new Object();
    private final Object mOnResourceLostListenerLock = new Object();
    private Map<Integer, WeakReference<Descrambler>> mDescramblers = new HashMap();
    private List<WeakReference<Filter>> mFilters = new ArrayList();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DvrType {
    }

    /* loaded from: classes2.dex */
    public interface OnResourceLostListener {
        void onResourceLost(Tuner tuner);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Result {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ScanType {
    }

    private native int nativeClose();

    private native int nativeCloseDemux(int i);

    private native int nativeCloseFrontend(int i);

    private native int nativeConnectCiCam(int i);

    private native int nativeDisconnectCiCam();

    private native Integer nativeGetAvSyncHwId(Filter filter);

    private native Long nativeGetAvSyncTime(int i);

    private native DemuxCapabilities nativeGetDemuxCapabilities();

    private native List<Integer> nativeGetFrontendIds();

    private native FrontendInfo nativeGetFrontendInfo(int i);

    private native FrontendStatus nativeGetFrontendStatus(int[] iArr);

    private native int nativeGetTunerVersion();

    private static native void nativeInit();

    private native int nativeLinkCiCam(int i);

    private native int nativeOpenDemuxByhandle(int i);

    private native Descrambler nativeOpenDescramblerByHandle(int i);

    private native DvrPlayback nativeOpenDvrPlayback(long j);

    private native DvrRecorder nativeOpenDvrRecorder(long j);

    private native Filter nativeOpenFilter(int i, int i2, long j);

    private native Frontend nativeOpenFrontendByHandle(int i);

    private native Lnb nativeOpenLnbByHandle(int i);

    private native Lnb nativeOpenLnbByName(String str);

    private native TimeFilter nativeOpenTimeFilter();

    private native int nativeScan(int i, FrontendSettings frontendSettings, int i2);

    private native int nativeSetLna(boolean z);

    private native int nativeSetLnb(Lnb lnb);

    private native void nativeSetup();

    private native int nativeStopScan();

    private native int nativeStopTune();

    private native int nativeTune(int i, FrontendSettings frontendSettings);

    private native int nativeUnlinkCiCam(int i);

    static {
        try {
            System.loadLibrary("media_tv_tuner");
            nativeInit();
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "tuner JNI library not found!");
        }
        sTunerVersion = 0;
    }

    public Tuner(Context context, String tvInputSessionId, int useCase) {
        TunerResourceManager.ResourcesReclaimListener resourcesReclaimListener = new TunerResourceManager.ResourcesReclaimListener() { // from class: android.media.tv.tuner.Tuner.1
            @Override // android.media.tv.tunerresourcemanager.TunerResourceManager.ResourcesReclaimListener
            public void onReclaimResources() {
                if (Tuner.this.mFrontend != null) {
                    FrameworkStatsLog.write(276, Tuner.this.mUserId, 0);
                }
                Tuner.this.releaseAll();
                Tuner.this.mHandler.sendMessage(Tuner.this.mHandler.obtainMessage(1));
            }
        };
        this.mResourceListener = resourcesReclaimListener;
        nativeSetup();
        int nativeGetTunerVersion = nativeGetTunerVersion();
        sTunerVersion = nativeGetTunerVersion;
        if (nativeGetTunerVersion == 0) {
            Log.e(TAG, "Unknown Tuner version!");
        } else {
            Log.d(TAG, "Current Tuner version is " + TunerVersionChecker.getMajorVersion(sTunerVersion) + MediaMetrics.SEPARATOR + TunerVersionChecker.getMinorVersion(sTunerVersion) + MediaMetrics.SEPARATOR);
        }
        this.mContext = context;
        TunerResourceManager tunerResourceManager = (TunerResourceManager) context.getSystemService(Context.TV_TUNER_RESOURCE_MGR_SERVICE);
        this.mTunerResourceManager = tunerResourceManager;
        if (this.mHandler == null) {
            this.mHandler = createEventHandler();
        }
        int[] clientId = new int[1];
        ResourceClientProfile profile = new ResourceClientProfile();
        profile.tvInputSessionId = tvInputSessionId;
        profile.useCase = useCase;
        tunerResourceManager.registerClientProfile(profile, new HandlerExecutor(this.mHandler), resourcesReclaimListener, clientId);
        this.mClientId = clientId[0];
        this.mUserId = Process.myUid();
    }

    private FrontendInfo[] getFrontendInfoListInternal() {
        List<Integer> ids = getFrontendIds();
        if (ids == null) {
            return null;
        }
        FrontendInfo[] infos = new FrontendInfo[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i).intValue();
            FrontendInfo frontendInfo = getFrontendInfoById(id);
            if (frontendInfo == null) {
                Log.e(TAG, "Failed to get a FrontendInfo on frontend id:" + id + "!");
            } else {
                infos[i] = frontendInfo;
            }
        }
        return (FrontendInfo[]) Arrays.stream(infos).filter(Tuner$$ExternalSyntheticLambda19.INSTANCE).toArray(Tuner$$ExternalSyntheticLambda18.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FrontendInfo[] lambda$getFrontendInfoListInternal$0(int x$0) {
        return new FrontendInfo[x$0];
    }

    public static int getTunerVersion() {
        return sTunerVersion;
    }

    public List<Integer> getFrontendIds() {
        return nativeGetFrontendIds();
    }

    public void setResourceLostListener(Executor executor, OnResourceLostListener listener) {
        synchronized (this.mOnResourceLostListenerLock) {
            Objects.requireNonNull(executor, "OnResourceLostListener must not be null");
            Objects.requireNonNull(listener, "executor must not be null");
            this.mOnResourceLostListener = listener;
            this.mOnResourceLostListenerExecutor = executor;
        }
    }

    public void clearResourceLostListener() {
        synchronized (this.mOnResourceLostListenerLock) {
            this.mOnResourceLostListener = null;
            this.mOnResourceLostListenerExecutor = null;
        }
    }

    public void shareFrontendFromTuner(Tuner tuner) {
        this.mTunerResourceManager.shareFrontend(this.mClientId, tuner.mClientId);
        synchronized (this.mIsSharedFrontend) {
            this.mFrontendHandle = tuner.mFrontendHandle;
            this.mFrontend = tuner.mFrontend;
            this.mIsSharedFrontend = true;
        }
    }

    public void updateResourcePriority(int priority, int niceValue) {
        this.mTunerResourceManager.updateClientPriority(this.mClientId, priority, niceValue);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        releaseAll();
        TunerUtils.throwExceptionForResult(nativeClose(), "failed to close tuner");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAll() {
        int res;
        if (this.mFrontendHandle != null) {
            synchronized (this.mIsSharedFrontend) {
                if (!this.mIsSharedFrontend.booleanValue() && (res = nativeCloseFrontend(this.mFrontendHandle.intValue())) != 0) {
                    TunerUtils.throwExceptionForResult(res, "failed to close frontend");
                }
                this.mIsSharedFrontend = false;
            }
            this.mTunerResourceManager.releaseFrontend(this.mFrontendHandle.intValue(), this.mClientId);
            FrameworkStatsLog.write(276, this.mUserId, 0);
            this.mFrontendHandle = null;
            this.mFrontend = null;
        }
        Lnb lnb = this.mLnb;
        if (lnb != null) {
            lnb.close();
        }
        if (this.mFrontendCiCamHandle != null) {
            int result = nativeUnlinkCiCam(this.mFrontendCiCamId.intValue());
            if (result == 0) {
                this.mTunerResourceManager.releaseCiCam(this.mFrontendCiCamHandle.intValue(), this.mClientId);
                this.mFrontendCiCamId = null;
                this.mFrontendCiCamHandle = null;
            }
        }
        synchronized (this.mDescramblers) {
            if (!this.mDescramblers.isEmpty()) {
                for (Map.Entry<Integer, WeakReference<Descrambler>> d : this.mDescramblers.entrySet()) {
                    Descrambler descrambler = d.getValue().get();
                    if (descrambler != null) {
                        descrambler.close();
                    }
                    this.mTunerResourceManager.releaseDescrambler(d.getKey().intValue(), this.mClientId);
                }
                this.mDescramblers.clear();
            }
        }
        synchronized (this.mFilters) {
            if (!this.mFilters.isEmpty()) {
                for (WeakReference<Filter> weakFilter : this.mFilters) {
                    Filter filter = weakFilter.get();
                    if (filter != null) {
                        filter.close();
                    }
                }
                this.mFilters.clear();
            }
        }
        Integer num = this.mDemuxHandle;
        if (num != null) {
            int res2 = nativeCloseDemux(num.intValue());
            if (res2 != 0) {
                TunerUtils.throwExceptionForResult(res2, "failed to close demux");
            }
            this.mTunerResourceManager.releaseDemux(this.mDemuxHandle.intValue(), this.mClientId);
            this.mDemuxHandle = null;
        }
        this.mTunerResourceManager.unregisterClientProfile(this.mClientId);
    }

    private EventHandler createEventHandler() {
        Looper looper = Looper.myLooper();
        if (looper != null) {
            return new EventHandler(looper);
        }
        Looper looper2 = Looper.getMainLooper();
        if (looper2 != null) {
            return new EventHandler(looper2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class EventHandler extends Handler {
        private EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    synchronized (Tuner.this.mOnResourceLostListenerLock) {
                        if (!(Tuner.this.mOnResourceLostListener == null || Tuner.this.mOnResourceLostListenerExecutor == null)) {
                            Tuner.this.mOnResourceLostListenerExecutor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$EventHandler$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    Tuner.EventHandler.this.lambda$handleMessage$0$Tuner$EventHandler();
                                }
                            });
                        }
                    }
                    return;
                case 2:
                default:
                    return;
                case 3:
                    Filter filter = (Filter) msg.obj;
                    if (filter.getCallback() != null) {
                        filter.getCallback().onFilterStatusChanged(filter, msg.arg1);
                        return;
                    }
                    return;
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$Tuner$EventHandler() {
            Tuner.this.mOnResourceLostListener.onResourceLost(Tuner.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Frontend {
        private int mId;

        private Frontend(int id) {
            this.mId = id;
        }
    }

    public void setOnTuneEventListener(Executor executor, OnTuneEventListener eventListener) {
        synchronized (this.mOnTuneEventLock) {
            this.mOnTuneEventListener = eventListener;
            this.mOnTuneEventExecutor = executor;
        }
    }

    public void clearOnTuneEventListener() {
        synchronized (this.mOnTuneEventLock) {
            this.mOnTuneEventListener = null;
            this.mOnTuneEventExecutor = null;
        }
    }

    public int tune(FrontendSettings settings) {
        Log.d(TAG, "Tune to " + settings.getFrequency());
        int type = settings.getType();
        this.mFrontendType = type;
        if ((type == 10 && !TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "Tuner with DTMB Frontend")) || !checkResource(0)) {
            return 1;
        }
        this.mFrontendInfo = null;
        Log.d(TAG, "Write Stats Log for tuning.");
        FrameworkStatsLog.write(276, this.mUserId, 1);
        return nativeTune(settings.getType(), settings);
    }

    public int cancelTuning() {
        return nativeStopTune();
    }

    public int scan(FrontendSettings settings, int scanType, Executor executor, ScanCallback scanCallback) {
        Executor executor2;
        synchronized (this.mScanCallbackLock) {
            ScanCallback scanCallback2 = this.mScanCallback;
            if (!((scanCallback2 == null || scanCallback2 == scanCallback) && ((executor2 = this.mScanCallbackExecutor) == null || executor2 == executor))) {
                throw new IllegalStateException("Different Scan session already in progress.  stopScan must be called before a new scan session can be started.");
            }
            int type = settings.getType();
            this.mFrontendType = type;
            if (type == 10 && !TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "Scan with DTMB Frontend")) {
                return 1;
            }
            if (!checkResource(0)) {
                return 1;
            }
            this.mScanCallback = scanCallback;
            this.mScanCallbackExecutor = executor;
            this.mFrontendInfo = null;
            FrameworkStatsLog.write(276, this.mUserId, 5);
            return nativeScan(settings.getType(), settings, scanType);
        }
    }

    public int cancelScanning() {
        int retVal;
        synchronized (this.mScanCallbackLock) {
            FrameworkStatsLog.write(276, this.mUserId, 6);
            retVal = nativeStopScan();
            this.mScanCallback = null;
            this.mScanCallbackExecutor = null;
        }
        return retVal;
    }

    private boolean requestFrontend() {
        int[] feHandle = new int[1];
        TunerFrontendRequest request = new TunerFrontendRequest();
        request.clientId = this.mClientId;
        request.frontendType = this.mFrontendType;
        boolean granted = this.mTunerResourceManager.requestFrontend(request, feHandle);
        if (granted) {
            Integer valueOf = Integer.valueOf(feHandle[0]);
            this.mFrontendHandle = valueOf;
            this.mFrontend = nativeOpenFrontendByHandle(valueOf.intValue());
        }
        return granted;
    }

    private int setLnb(Lnb lnb) {
        return nativeSetLnb(lnb);
    }

    public int setLnaEnabled(boolean enable) {
        return nativeSetLna(enable);
    }

    public FrontendStatus getFrontendStatus(int[] statusTypes) {
        if (this.mFrontend != null) {
            return nativeGetFrontendStatus(statusTypes);
        }
        throw new IllegalStateException("frontend is not initialized");
    }

    public int getAvSyncHwId(Filter filter) {
        Integer id;
        if (checkResource(1) && (id = nativeGetAvSyncHwId(filter)) != null) {
            return id.intValue();
        }
        return -1;
    }

    public long getAvSyncTime(int avSyncHwId) {
        Long time;
        if (checkResource(1) && (time = nativeGetAvSyncTime(avSyncHwId)) != null) {
            return time.longValue();
        }
        return -1L;
    }

    public int connectCiCam(int ciCamId) {
        if (checkResource(1)) {
            return nativeConnectCiCam(ciCamId);
        }
        return 1;
    }

    public int connectFrontendToCiCam(int ciCamId) {
        if (!TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "linkFrontendToCiCam") || !checkCiCamResource(ciCamId) || !checkResource(0)) {
            return -1;
        }
        return nativeLinkCiCam(ciCamId);
    }

    public int disconnectCiCam() {
        if (this.mDemuxHandle != null) {
            return nativeDisconnectCiCam();
        }
        return 1;
    }

    public int disconnectFrontendToCiCam(int ciCamId) {
        Integer num;
        if (!TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "unlinkFrontendToCiCam") || this.mFrontendCiCamHandle == null || (num = this.mFrontendCiCamId) == null || num.intValue() != ciCamId) {
            return 1;
        }
        int result = nativeUnlinkCiCam(ciCamId);
        if (result == 0) {
            this.mTunerResourceManager.releaseCiCam(this.mFrontendCiCamHandle.intValue(), this.mClientId);
            this.mFrontendCiCamId = null;
            this.mFrontendCiCamHandle = null;
        }
        return result;
    }

    public FrontendInfo getFrontendInfo() {
        if (!checkResource(0)) {
            return null;
        }
        Frontend frontend = this.mFrontend;
        if (frontend != null) {
            if (this.mFrontendInfo == null) {
                this.mFrontendInfo = getFrontendInfoById(frontend.mId);
            }
            return this.mFrontendInfo;
        }
        throw new IllegalStateException("frontend is not initialized");
    }

    public List<FrontendInfo> getAvailableFrontendInfos() {
        FrontendInfo[] feInfoList = getFrontendInfoListInternal();
        if (feInfoList == null) {
            return null;
        }
        return Arrays.asList(feInfoList);
    }

    public FrontendInfo getFrontendInfoById(int id) {
        return nativeGetFrontendInfo(id);
    }

    public DemuxCapabilities getDemuxCapabilities() {
        return nativeGetDemuxCapabilities();
    }

    private void onFrontendEvent(final int eventType) {
        Log.d(TAG, "Got event from tuning. Event type: " + eventType);
        synchronized (this.mOnTuneEventLock) {
            Executor executor = this.mOnTuneEventExecutor;
            if (!(executor == null || this.mOnTuneEventListener == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onFrontendEvent$1$Tuner(eventType);
                    }
                });
            }
        }
        Log.d(TAG, "Wrote Stats Log for the events from tuning.");
        if (eventType == 0) {
            FrameworkStatsLog.write(276, this.mUserId, 2);
        } else if (eventType == 1) {
            FrameworkStatsLog.write(276, this.mUserId, 3);
        } else if (eventType == 2) {
            FrameworkStatsLog.write(276, this.mUserId, 4);
        }
    }

    public /* synthetic */ void lambda$onFrontendEvent$1$Tuner(int eventType) {
        this.mOnTuneEventListener.onTuneEvent(eventType);
    }

    private void onLocked() {
        Log.d(TAG, "Wrote Stats Log for locked event from scanning.");
        FrameworkStatsLog.write(276, this.mUserId, 2);
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onLocked$2$Tuner();
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onLocked$2$Tuner() {
        this.mScanCallback.onLocked();
    }

    private void onScanStopped() {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onScanStopped$3$Tuner();
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onScanStopped$3$Tuner() {
        this.mScanCallback.onScanStopped();
    }

    private void onProgress(final int percent) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onProgress$4$Tuner(percent);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onProgress$4$Tuner(int percent) {
        this.mScanCallback.onProgress(percent);
    }

    private void onFrequenciesReport(final int[] frequency) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda12
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onFrequenciesReport$5$Tuner(frequency);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onFrequenciesReport$5$Tuner(int[] frequency) {
        this.mScanCallback.onFrequenciesReported(frequency);
    }

    private void onSymbolRates(final int[] rate) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda16
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onSymbolRates$6$Tuner(rate);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onSymbolRates$6$Tuner(int[] rate) {
        this.mScanCallback.onSymbolRatesReported(rate);
    }

    private void onHierarchy(final int hierarchy) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onHierarchy$7$Tuner(hierarchy);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onHierarchy$7$Tuner(int hierarchy) {
        this.mScanCallback.onHierarchyReported(hierarchy);
    }

    private void onSignalType(final int signalType) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda10
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onSignalType$8$Tuner(signalType);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onSignalType$8$Tuner(int signalType) {
        this.mScanCallback.onSignalTypeReported(signalType);
    }

    private void onPlpIds(final int[] plpIds) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda15
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onPlpIds$9$Tuner(plpIds);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onPlpIds$9$Tuner(int[] plpIds) {
        this.mScanCallback.onPlpIdsReported(plpIds);
    }

    private void onGroupIds(final int[] groupIds) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda13
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onGroupIds$10$Tuner(groupIds);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onGroupIds$10$Tuner(int[] groupIds) {
        this.mScanCallback.onGroupIdsReported(groupIds);
    }

    private void onInputStreamIds(final int[] inputStreamIds) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda14
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onInputStreamIds$11$Tuner(inputStreamIds);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onInputStreamIds$11$Tuner(int[] inputStreamIds) {
        this.mScanCallback.onInputStreamIdsReported(inputStreamIds);
    }

    private void onDvbsStandard(final int dvbsStandandard) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onDvbsStandard$12$Tuner(dvbsStandandard);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onDvbsStandard$12$Tuner(int dvbsStandandard) {
        this.mScanCallback.onDvbsStandardReported(dvbsStandandard);
    }

    private void onDvbtStandard(final int dvbtStandard) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onDvbtStandard$13$Tuner(dvbtStandard);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onDvbtStandard$13$Tuner(int dvbtStandard) {
        this.mScanCallback.onDvbtStandardReported(dvbtStandard);
    }

    private void onAnalogSifStandard(final int sif) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onAnalogSifStandard$14$Tuner(sif);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onAnalogSifStandard$14$Tuner(int sif) {
        this.mScanCallback.onAnalogSifStandardReported(sif);
    }

    private void onAtsc3PlpInfos(final Atsc3PlpInfo[] atsc3PlpInfos) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda17
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onAtsc3PlpInfos$15$Tuner(atsc3PlpInfos);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onAtsc3PlpInfos$15$Tuner(Atsc3PlpInfo[] atsc3PlpInfos) {
        this.mScanCallback.onAtsc3PlpInfosReported(atsc3PlpInfos);
    }

    private void onModulationReported(final int modulation) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onModulationReported$16$Tuner(modulation);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onModulationReported$16$Tuner(int modulation) {
        this.mScanCallback.onModulationReported(modulation);
    }

    private void onPriorityReported(final boolean isHighPriority) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onPriorityReported$17$Tuner(isHighPriority);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onPriorityReported$17$Tuner(boolean isHighPriority) {
        this.mScanCallback.onPriorityReported(isHighPriority);
    }

    private void onDvbcAnnexReported(final int dvbcAnnex) {
        synchronized (this.mScanCallbackLock) {
            Executor executor = this.mScanCallbackExecutor;
            if (!(executor == null || this.mScanCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Tuner$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Tuner.this.lambda$onDvbcAnnexReported$18$Tuner(dvbcAnnex);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onDvbcAnnexReported$18$Tuner(int dvbcAnnex) {
        this.mScanCallback.onDvbcAnnexReported(dvbcAnnex);
    }

    public Filter openFilter(int mainType, int subType, long bufferSize, Executor executor, FilterCallback cb) {
        if (!checkResource(1)) {
            return null;
        }
        Filter filter = nativeOpenFilter(mainType, TunerUtils.getFilterSubtype(mainType, subType), bufferSize);
        if (filter != null) {
            filter.setType(mainType, subType);
            filter.setCallback(cb, executor);
            if (this.mHandler == null) {
                this.mHandler = createEventHandler();
            }
            synchronized (this.mFilters) {
                WeakReference<Filter> weakFilter = new WeakReference<>(filter);
                this.mFilters.add(weakFilter);
                if (this.mFilters.size() > 256) {
                    Iterator<WeakReference<Filter>> iterator = this.mFilters.iterator();
                    while (iterator.hasNext()) {
                        WeakReference<Filter> wFilter = iterator.next();
                        if (wFilter.get() == null) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        return filter;
    }

    public Lnb openLnb(Executor executor, LnbCallback cb) {
        Lnb lnb;
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(cb, "LnbCallback must not be null");
        Lnb lnb2 = this.mLnb;
        if (lnb2 != null) {
            lnb2.setCallback(executor, cb, this);
            return this.mLnb;
        } else if (!checkResource(3) || (lnb = this.mLnb) == null) {
            return null;
        } else {
            lnb.setCallback(executor, cb, this);
            setLnb(this.mLnb);
            return this.mLnb;
        }
    }

    public Lnb openLnbByName(String name, Executor executor, LnbCallback cb) {
        Objects.requireNonNull(name, "LNB name must not be null");
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(cb, "LnbCallback must not be null");
        Lnb newLnb = nativeOpenLnbByName(name);
        if (newLnb != null) {
            Lnb lnb = this.mLnb;
            if (lnb != null) {
                lnb.close();
                this.mLnbHandle = null;
            }
            this.mLnb = newLnb;
            newLnb.setCallback(executor, cb, this);
            setLnb(this.mLnb);
        }
        return this.mLnb;
    }

    private boolean requestLnb() {
        int[] lnbHandle = new int[1];
        TunerLnbRequest request = new TunerLnbRequest();
        request.clientId = this.mClientId;
        boolean granted = this.mTunerResourceManager.requestLnb(request, lnbHandle);
        if (granted) {
            Integer valueOf = Integer.valueOf(lnbHandle[0]);
            this.mLnbHandle = valueOf;
            this.mLnb = nativeOpenLnbByHandle(valueOf.intValue());
        }
        return granted;
    }

    public TimeFilter openTimeFilter() {
        if (!checkResource(1)) {
            return null;
        }
        return nativeOpenTimeFilter();
    }

    public Descrambler openDescrambler() {
        if (!checkResource(1)) {
            return null;
        }
        return requestDescrambler();
    }

    public DvrRecorder openDvrRecorder(long bufferSize, Executor executor, OnRecordStatusChangedListener l) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(l, "OnRecordStatusChangedListener must not be null");
        if (!checkResource(1)) {
            return null;
        }
        DvrRecorder dvr = nativeOpenDvrRecorder(bufferSize);
        dvr.setListener(executor, l);
        return dvr;
    }

    public DvrPlayback openDvrPlayback(long bufferSize, Executor executor, OnPlaybackStatusChangedListener l) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(l, "OnPlaybackStatusChangedListener must not be null");
        if (!checkResource(1)) {
            return null;
        }
        DvrPlayback dvr = nativeOpenDvrPlayback(bufferSize);
        dvr.setListener(executor, l);
        return dvr;
    }

    private boolean requestDemux() {
        int[] demuxHandle = new int[1];
        TunerDemuxRequest request = new TunerDemuxRequest();
        request.clientId = this.mClientId;
        boolean granted = this.mTunerResourceManager.requestDemux(request, demuxHandle);
        if (granted) {
            Integer valueOf = Integer.valueOf(demuxHandle[0]);
            this.mDemuxHandle = valueOf;
            nativeOpenDemuxByhandle(valueOf.intValue());
        }
        return granted;
    }

    private Descrambler requestDescrambler() {
        int[] descramblerHandle = new int[1];
        TunerDescramblerRequest request = new TunerDescramblerRequest();
        request.clientId = this.mClientId;
        boolean granted = this.mTunerResourceManager.requestDescrambler(request, descramblerHandle);
        if (!granted) {
            return null;
        }
        int handle = descramblerHandle[0];
        Descrambler descrambler = nativeOpenDescramblerByHandle(handle);
        if (descrambler != null) {
            synchronized (this.mDescramblers) {
                WeakReference weakDescrambler = new WeakReference(descrambler);
                this.mDescramblers.put(Integer.valueOf(handle), weakDescrambler);
            }
        } else {
            this.mTunerResourceManager.releaseDescrambler(handle, this.mClientId);
        }
        return descrambler;
    }

    private boolean requestFrontendCiCam(int ciCamId) {
        int[] ciCamHandle = new int[1];
        TunerCiCamRequest request = new TunerCiCamRequest();
        request.clientId = this.mClientId;
        request.ciCamId = ciCamId;
        boolean granted = this.mTunerResourceManager.requestCiCam(request, ciCamHandle);
        if (granted) {
            this.mFrontendCiCamHandle = Integer.valueOf(ciCamHandle[0]);
            this.mFrontendCiCamId = Integer.valueOf(ciCamId);
        }
        return granted;
    }

    private boolean checkResource(int resourceType) {
        switch (resourceType) {
            case 0:
                if (this.mFrontendHandle != null || requestFrontend()) {
                    return true;
                }
                return false;
            case 1:
                if (this.mDemuxHandle != null || requestDemux()) {
                    return true;
                }
                return false;
            case 2:
            default:
                return false;
            case 3:
                if (this.mLnb != null || requestLnb()) {
                    return true;
                }
                return false;
        }
    }

    private boolean checkCiCamResource(int ciCamId) {
        if (this.mFrontendCiCamHandle != null || requestFrontendCiCam(ciCamId)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseLnb() {
        Integer num = this.mLnbHandle;
        if (num != null) {
            this.mTunerResourceManager.releaseLnb(num.intValue(), this.mClientId);
            this.mLnbHandle = null;
        }
        this.mLnb = null;
    }
}
