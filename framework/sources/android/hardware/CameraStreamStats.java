package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
/* loaded from: classes.dex */
public class CameraStreamStats implements Parcelable {
    public static final Parcelable.Creator<CameraStreamStats> CREATOR = new Parcelable.Creator<CameraStreamStats>() { // from class: android.hardware.CameraStreamStats.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraStreamStats createFromParcel(Parcel in) {
            try {
                CameraStreamStats streamStats = new CameraStreamStats(in);
                return streamStats;
            } catch (Exception e) {
                Log.e(CameraStreamStats.TAG, "Exception creating CameraStreamStats from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraStreamStats[] newArray(int size) {
            return new CameraStreamStats[size];
        }
    };
    public static final int HISTOGRAM_TYPE_CAPTURE_LATENCY = 1;
    public static final int HISTOGRAM_TYPE_UNKNOWN = 0;
    private static final String TAG = "CameraStreamStats";
    private int mDataSpace;
    private long mErrorCount;
    private int mFormat;
    private int mHeight;
    private float[] mHistogramBins;
    private long[] mHistogramCounts;
    private int mHistogramType;
    private int mMaxAppBuffers;
    private int mMaxHalBuffers;
    private long mRequestCount;
    private int mStartLatencyMs;
    private long mUsage;
    private int mWidth;

    public CameraStreamStats() {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mFormat = 0;
        this.mDataSpace = 0;
        this.mUsage = 0L;
        this.mRequestCount = 0L;
        this.mErrorCount = 0L;
        this.mStartLatencyMs = 0;
        this.mMaxHalBuffers = 0;
        this.mMaxAppBuffers = 0;
        this.mHistogramType = 0;
    }

    public CameraStreamStats(int width, int height, int format, int dataSpace, long usage, long requestCount, long errorCount, int startLatencyMs, int maxHalBuffers, int maxAppBuffers) {
        this.mWidth = width;
        this.mHeight = height;
        this.mFormat = format;
        this.mDataSpace = dataSpace;
        this.mUsage = usage;
        this.mRequestCount = requestCount;
        this.mErrorCount = errorCount;
        this.mStartLatencyMs = startLatencyMs;
        this.mMaxHalBuffers = maxHalBuffers;
        this.mMaxAppBuffers = maxAppBuffers;
        this.mHistogramType = 0;
    }

    private CameraStreamStats(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mWidth);
        dest.writeInt(this.mHeight);
        dest.writeInt(this.mFormat);
        dest.writeInt(this.mDataSpace);
        dest.writeLong(this.mUsage);
        dest.writeLong(this.mRequestCount);
        dest.writeLong(this.mErrorCount);
        dest.writeInt(this.mStartLatencyMs);
        dest.writeInt(this.mMaxHalBuffers);
        dest.writeInt(this.mMaxAppBuffers);
        dest.writeInt(this.mHistogramType);
        dest.writeFloatArray(this.mHistogramBins);
        dest.writeLongArray(this.mHistogramCounts);
    }

    public void readFromParcel(Parcel in) {
        this.mWidth = in.readInt();
        this.mHeight = in.readInt();
        this.mFormat = in.readInt();
        this.mDataSpace = in.readInt();
        this.mUsage = in.readLong();
        this.mRequestCount = in.readLong();
        this.mErrorCount = in.readLong();
        this.mStartLatencyMs = in.readInt();
        this.mMaxHalBuffers = in.readInt();
        this.mMaxAppBuffers = in.readInt();
        this.mHistogramType = in.readInt();
        this.mHistogramBins = in.createFloatArray();
        this.mHistogramCounts = in.createLongArray();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getFormat() {
        return this.mFormat;
    }

    public int getDataSpace() {
        return this.mDataSpace;
    }

    public long getUsage() {
        return this.mUsage;
    }

    public long getRequestCount() {
        return this.mRequestCount;
    }

    public long getErrorCount() {
        return this.mErrorCount;
    }

    public int getStartLatencyMs() {
        return this.mStartLatencyMs;
    }

    public int getMaxHalBuffers() {
        return this.mMaxHalBuffers;
    }

    public int getMaxAppBuffers() {
        return this.mMaxAppBuffers;
    }

    public int getHistogramType() {
        return this.mHistogramType;
    }

    public float[] getHistogramBins() {
        return this.mHistogramBins;
    }

    public long[] getHistogramCounts() {
        return this.mHistogramCounts;
    }
}
