package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes2.dex */
public class IsdbtFrontendSettings extends FrontendSettings {
    public static final int BANDWIDTH_6MHZ = 8;
    public static final int BANDWIDTH_7MHZ = 4;
    public static final int BANDWIDTH_8MHZ = 2;
    public static final int BANDWIDTH_AUTO = 1;
    public static final int BANDWIDTH_UNDEFINED = 0;
    public static final int MODE_1 = 2;
    public static final int MODE_2 = 4;
    public static final int MODE_3 = 8;
    public static final int MODE_AUTO = 1;
    public static final int MODE_UNDEFINED = 0;
    public static final int MODULATION_AUTO = 1;
    public static final int MODULATION_MOD_16QAM = 8;
    public static final int MODULATION_MOD_64QAM = 16;
    public static final int MODULATION_MOD_DQPSK = 2;
    public static final int MODULATION_MOD_QPSK = 4;
    public static final int MODULATION_UNDEFINED = 0;
    private final int mBandwidth;
    private final int mCodeRate;
    private final int mGuardInterval;
    private final int mMode;
    private final int mModulation;
    private final int mServiceAreaId;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Bandwidth {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Mode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Modulation {
    }

    private IsdbtFrontendSettings(int frequency, int modulation, int bandwidth, int mode, int codeRate, int guardInterval, int serviceAreaId) {
        super(frequency);
        this.mModulation = modulation;
        this.mBandwidth = bandwidth;
        this.mMode = mode;
        this.mCodeRate = codeRate;
        this.mGuardInterval = guardInterval;
        this.mServiceAreaId = serviceAreaId;
    }

    public int getModulation() {
        return this.mModulation;
    }

    public int getBandwidth() {
        return this.mBandwidth;
    }

    public int getMode() {
        return this.mMode;
    }

    public int getCodeRate() {
        return this.mCodeRate;
    }

    public int getGuardInterval() {
        return this.mGuardInterval;
    }

    public int getServiceAreaId() {
        return this.mServiceAreaId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mBandwidth;
        private int mCodeRate;
        private int mFrequency;
        private int mGuardInterval;
        private int mMode;
        private int mModulation;
        private int mServiceAreaId;

        private Builder() {
            this.mFrequency = 0;
            this.mModulation = 0;
            this.mBandwidth = 0;
            this.mMode = 0;
            this.mCodeRate = 0;
            this.mGuardInterval = 0;
            this.mServiceAreaId = 0;
        }

        public Builder setFrequency(int frequency) {
            this.mFrequency = frequency;
            return this;
        }

        public Builder setModulation(int modulation) {
            this.mModulation = modulation;
            return this;
        }

        public Builder setBandwidth(int bandwidth) {
            this.mBandwidth = bandwidth;
            return this;
        }

        public Builder setMode(int mode) {
            this.mMode = mode;
            return this;
        }

        public Builder setCodeRate(int codeRate) {
            this.mCodeRate = codeRate;
            return this;
        }

        public Builder setGuardInterval(int guardInterval) {
            this.mGuardInterval = guardInterval;
            return this;
        }

        public Builder setServiceAreaId(int serviceAreaId) {
            this.mServiceAreaId = serviceAreaId;
            return this;
        }

        public IsdbtFrontendSettings build() {
            return new IsdbtFrontendSettings(this.mFrequency, this.mModulation, this.mBandwidth, this.mMode, this.mCodeRate, this.mGuardInterval, this.mServiceAreaId);
        }
    }

    @Override // android.media.tv.tuner.frontend.FrontendSettings
    public int getType() {
        return 9;
    }
}
