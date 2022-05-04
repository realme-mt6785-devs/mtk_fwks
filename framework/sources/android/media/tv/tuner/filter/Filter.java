package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;
import android.media.tv.tuner.TunerVersionChecker;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes2.dex */
public class Filter implements AutoCloseable {
    public static final int MONITOR_EVENT_IP_CID_CHANGE = 2;
    public static final int MONITOR_EVENT_SCRAMBLING_STATUS = 1;
    public static final int SCRAMBLING_STATUS_NOT_SCRAMBLED = 2;
    public static final int SCRAMBLING_STATUS_SCRAMBLED = 4;
    public static final int SCRAMBLING_STATUS_UNKNOWN = 1;
    public static final int STATUS_DATA_READY = 1;
    public static final int STATUS_HIGH_WATER = 4;
    public static final int STATUS_LOW_WATER = 2;
    public static final int STATUS_OVERFLOW = 8;
    public static final int SUBTYPE_AUDIO = 3;
    public static final int SUBTYPE_DOWNLOAD = 5;
    public static final int SUBTYPE_IP = 13;
    public static final int SUBTYPE_IP_PAYLOAD = 12;
    public static final int SUBTYPE_MMTP = 10;
    public static final int SUBTYPE_NTP = 11;
    public static final int SUBTYPE_PAYLOAD_THROUGH = 14;
    public static final int SUBTYPE_PCR = 8;
    public static final int SUBTYPE_PES = 2;
    public static final int SUBTYPE_PTP = 16;
    public static final int SUBTYPE_RECORD = 6;
    public static final int SUBTYPE_SECTION = 1;
    public static final int SUBTYPE_TEMI = 9;
    public static final int SUBTYPE_TLV = 15;
    public static final int SUBTYPE_TS = 7;
    public static final int SUBTYPE_UNDEFINED = 0;
    public static final int SUBTYPE_VIDEO = 4;
    private static final String TAG = "Filter";
    public static final int TYPE_ALP = 16;
    public static final int TYPE_IP = 4;
    public static final int TYPE_MMTP = 2;
    public static final int TYPE_TLV = 8;
    public static final int TYPE_TS = 1;
    public static final int TYPE_UNDEFINED = 0;
    private FilterCallback mCallback;
    private Executor mExecutor;
    private final long mId;
    private int mMainType;
    private long mNativeContext;
    private Filter mSource;
    private boolean mStarted;
    private int mSubtype;
    private final Object mCallbackLock = new Object();
    private boolean mIsClosed = false;
    private final Object mLock = new Object();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface MonitorEventMask {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ScramblingStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Status {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Subtype {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Type {
    }

    private native int nativeClose();

    private native int nativeConfigureFilter(int i, int i2, FilterConfiguration filterConfiguration);

    private native int nativeConfigureMonitorEvent(int i);

    private native int nativeFlushFilter();

    private native int nativeGetId();

    private native long nativeGetId64Bit();

    private native int nativeRead(byte[] bArr, long j, long j2);

    private native int nativeSetDataSource(Filter filter);

    private native int nativeStartFilter();

    private native int nativeStopFilter();

    private Filter(long id) {
        this.mId = id;
    }

    private void onFilterStatus(final int status) {
        Executor executor;
        synchronized (this.mCallbackLock) {
            if (!(this.mCallback == null || (executor = this.mExecutor) == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.filter.Filter$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Filter.this.lambda$onFilterStatus$0$Filter(status);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onFilterStatus$0$Filter(int status) {
        this.mCallback.onFilterStatusChanged(this, status);
    }

    private void onFilterEvent(final FilterEvent[] events) {
        Executor executor;
        synchronized (this.mCallbackLock) {
            if (!(this.mCallback == null || (executor = this.mExecutor) == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.filter.Filter$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Filter.this.lambda$onFilterEvent$1$Filter(events);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onFilterEvent$1$Filter(FilterEvent[] events) {
        this.mCallback.onFilterEvent(this, events);
    }

    public void setType(int mainType, int subtype) {
        this.mMainType = mainType;
        this.mSubtype = TunerUtils.getFilterSubtype(mainType, subtype);
    }

    public void setCallback(FilterCallback cb, Executor executor) {
        synchronized (this.mCallbackLock) {
            this.mCallback = cb;
            this.mExecutor = executor;
        }
    }

    public FilterCallback getCallback() {
        FilterCallback filterCallback;
        synchronized (this.mCallbackLock) {
            filterCallback = this.mCallback;
        }
        return filterCallback;
    }

    public int configure(FilterConfiguration config) {
        int nativeConfigureFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            Settings s = config.getSettings();
            int subType = s == null ? this.mSubtype : s.getType();
            if (this.mMainType == config.getType() && this.mSubtype == subType) {
                nativeConfigureFilter = nativeConfigureFilter(config.getType(), subType, config);
            } else {
                throw new IllegalArgumentException("Invalid filter config. filter main type=" + this.mMainType + ", filter subtype=" + this.mSubtype + ". config main type=" + config.getType() + ", config subtype=" + subType);
            }
        }
        return nativeConfigureFilter;
    }

    public int getId() {
        int nativeGetId;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            nativeGetId = nativeGetId();
        }
        return nativeGetId;
    }

    public long getIdLong() {
        long nativeGetId64Bit;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            nativeGetId64Bit = nativeGetId64Bit();
        }
        return nativeGetId64Bit;
    }

    public int setMonitorEventMask(int monitorEventMask) {
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            if (!TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "setMonitorEventMask")) {
                return 1;
            }
            return nativeConfigureMonitorEvent(monitorEventMask);
        }
    }

    public int setDataSource(Filter source) {
        int res;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            if (this.mSource == null) {
                res = nativeSetDataSource(source);
                if (res == 0) {
                    this.mSource = source;
                }
            } else {
                throw new IllegalStateException("Data source is existing");
            }
        }
        return res;
    }

    public int start() {
        int nativeStartFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            nativeStartFilter = nativeStartFilter();
        }
        return nativeStartFilter;
    }

    public int stop() {
        int nativeStopFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            nativeStopFilter = nativeStopFilter();
        }
        return nativeStopFilter;
    }

    public int flush() {
        int nativeFlushFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            nativeFlushFilter = nativeFlushFilter();
        }
        return nativeFlushFilter;
    }

    public int read(byte[] buffer, long offset, long size) {
        Throwable th;
        synchronized (this.mLock) {
            try {
                try {
                    TunerUtils.checkResourceState(TAG, this.mIsClosed);
                    return nativeRead(buffer, offset, Math.min(size, buffer.length - offset));
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                int res = nativeClose();
                if (res != 0) {
                    TunerUtils.throwExceptionForResult(res, "Failed to close filter.");
                } else {
                    this.mIsClosed = true;
                }
            }
        }
    }
}
