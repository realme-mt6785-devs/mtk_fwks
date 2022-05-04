package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import android.util.Range;
import java.util.Arrays;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public class FrontendInfo {
    private final int mAcquireRange;
    private final int mExclusiveGroupId;
    private final Range<Integer> mFrequencyRange;
    private final FrontendCapabilities mFrontendCap;
    private final int mId;
    private final int[] mStatusCaps;
    private final Range<Integer> mSymbolRateRange;
    private final int mType;

    private FrontendInfo(int id, int type, int minFrequency, int maxFrequency, int minSymbolRate, int maxSymbolRate, int acquireRange, int exclusiveGroupId, int[] statusCaps, FrontendCapabilities frontendCap) {
        this.mId = id;
        this.mType = type;
        this.mFrequencyRange = new Range<>(Integer.valueOf(minFrequency), Integer.valueOf(maxFrequency < 0 ? Integer.MAX_VALUE : maxFrequency));
        this.mSymbolRateRange = new Range<>(Integer.valueOf(minSymbolRate), Integer.valueOf(maxSymbolRate));
        this.mAcquireRange = acquireRange;
        this.mExclusiveGroupId = exclusiveGroupId;
        this.mStatusCaps = statusCaps;
        this.mFrontendCap = frontendCap;
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public Range<Integer> getFrequencyRange() {
        return this.mFrequencyRange;
    }

    public Range<Integer> getSymbolRateRange() {
        return this.mSymbolRateRange;
    }

    public int getAcquireRange() {
        return this.mAcquireRange;
    }

    public int getExclusiveGroupId() {
        return this.mExclusiveGroupId;
    }

    public int[] getStatusCapabilities() {
        return this.mStatusCaps;
    }

    public FrontendCapabilities getFrontendCapabilities() {
        return this.mFrontendCap;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof FrontendInfo)) {
            return false;
        }
        FrontendInfo info = (FrontendInfo) o;
        if (this.mId == info.getId() && this.mType == info.getType() && Objects.equals(this.mFrequencyRange, info.getFrequencyRange()) && Objects.equals(this.mSymbolRateRange, info.getSymbolRateRange()) && this.mAcquireRange == info.getAcquireRange() && this.mExclusiveGroupId == info.getExclusiveGroupId() && Arrays.equals(this.mStatusCaps, info.getStatusCapabilities())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mId;
    }
}
