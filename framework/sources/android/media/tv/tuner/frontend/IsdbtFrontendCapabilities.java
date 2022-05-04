package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
@SystemApi
/* loaded from: classes2.dex */
public class IsdbtFrontendCapabilities extends FrontendCapabilities {
    private final int mBandwidthCap;
    private final int mCodeRateCap;
    private final int mGuardIntervalCap;
    private final int mModeCap;
    private final int mModulationCap;

    private IsdbtFrontendCapabilities(int modeCap, int bandwidthCap, int modulationCap, int codeRateCap, int guardIntervalCap) {
        this.mModeCap = modeCap;
        this.mBandwidthCap = bandwidthCap;
        this.mModulationCap = modulationCap;
        this.mCodeRateCap = codeRateCap;
        this.mGuardIntervalCap = guardIntervalCap;
    }

    public int getModeCapability() {
        return this.mModeCap;
    }

    public int getBandwidthCapability() {
        return this.mBandwidthCap;
    }

    public int getModulationCapability() {
        return this.mModulationCap;
    }

    public int getCodeRateCapability() {
        return this.mCodeRateCap;
    }

    public int getGuardIntervalCapability() {
        return this.mGuardIntervalCap;
    }
}
