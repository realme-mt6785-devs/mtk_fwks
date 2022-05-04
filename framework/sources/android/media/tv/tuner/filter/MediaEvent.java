package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.MediaCodec;
@SystemApi
/* loaded from: classes2.dex */
public class MediaEvent extends FilterEvent {
    private final long mDataId;
    private final long mDataLength;
    private final AudioDescriptor mExtraMetaData;
    private final boolean mIsPrivateData;
    private final boolean mIsPtsPresent;
    private final boolean mIsSecureMemory;
    private MediaCodec.LinearBlock mLinearBlock;
    private final int mMpuSequenceNumber;
    private long mNativeContext;
    private final long mOffset;
    private final long mPts;
    private final int mStreamId;
    private boolean mReleased = false;
    private final Object mLock = new Object();

    private native void nativeFinalize();

    private native Long nativeGetAudioHandle();

    private native MediaCodec.LinearBlock nativeGetLinearBlock();

    private MediaEvent(int streamId, boolean isPtsPresent, long pts, long dataLength, long offset, MediaCodec.LinearBlock buffer, boolean isSecureMemory, long dataId, int mpuSequenceNumber, boolean isPrivateData, AudioDescriptor extraMetaData) {
        this.mStreamId = streamId;
        this.mIsPtsPresent = isPtsPresent;
        this.mPts = pts;
        this.mDataLength = dataLength;
        this.mOffset = offset;
        this.mLinearBlock = buffer;
        this.mIsSecureMemory = isSecureMemory;
        this.mDataId = dataId;
        this.mMpuSequenceNumber = mpuSequenceNumber;
        this.mIsPrivateData = isPrivateData;
        this.mExtraMetaData = extraMetaData;
    }

    public int getStreamId() {
        return this.mStreamId;
    }

    public boolean isPtsPresent() {
        return this.mIsPtsPresent;
    }

    public long getPts() {
        return this.mPts;
    }

    public long getDataLength() {
        return this.mDataLength;
    }

    public long getOffset() {
        return this.mOffset;
    }

    public MediaCodec.LinearBlock getLinearBlock() {
        MediaCodec.LinearBlock linearBlock;
        synchronized (this.mLock) {
            if (this.mLinearBlock == null) {
                this.mLinearBlock = nativeGetLinearBlock();
            }
            linearBlock = this.mLinearBlock;
        }
        return linearBlock;
    }

    public boolean isSecureMemory() {
        return this.mIsSecureMemory;
    }

    public long getAvDataId() {
        return this.mDataId;
    }

    public long getAudioHandle() {
        nativeGetAudioHandle();
        return this.mDataId;
    }

    public int getMpuSequenceNumber() {
        return this.mMpuSequenceNumber;
    }

    public boolean isPrivateData() {
        return this.mIsPrivateData;
    }

    public AudioDescriptor getExtraMetaData() {
        return this.mExtraMetaData;
    }

    protected void finalize() {
        release();
    }

    public void release() {
        synchronized (this.mLock) {
            if (!this.mReleased) {
                nativeFinalize();
                this.mNativeContext = 0L;
                this.mReleased = true;
            }
        }
    }
}
