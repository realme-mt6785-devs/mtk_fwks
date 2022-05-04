package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerVersionChecker;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes2.dex */
public class FrontendStatus {
    public static final int FRONTEND_STATUS_TYPE_AGC = 14;
    public static final int FRONTEND_STATUS_TYPE_ATSC3_PLP_INFO = 21;
    public static final int FRONTEND_STATUS_TYPE_BANDWIDTH = 25;
    public static final int FRONTEND_STATUS_TYPE_BER = 2;
    public static final int FRONTEND_STATUS_TYPE_BERS = 23;
    public static final int FRONTEND_STATUS_TYPE_CODERATES = 24;
    public static final int FRONTEND_STATUS_TYPE_DEMOD_LOCK = 0;
    public static final int FRONTEND_STATUS_TYPE_EWBS = 13;
    public static final int FRONTEND_STATUS_TYPE_FEC = 8;
    public static final int FRONTEND_STATUS_TYPE_FREQ_OFFSET = 18;
    public static final int FRONTEND_STATUS_TYPE_GUARD_INTERVAL = 26;
    public static final int FRONTEND_STATUS_TYPE_HIERARCHY = 19;
    public static final int FRONTEND_STATUS_TYPE_INTERLEAVINGS = 30;
    public static final int FRONTEND_STATUS_TYPE_ISDBT_SEGMENTS = 31;
    public static final int FRONTEND_STATUS_TYPE_IS_LINEAR = 35;
    public static final int FRONTEND_STATUS_TYPE_IS_MISO_ENABLED = 34;
    public static final int FRONTEND_STATUS_TYPE_IS_SHORT_FRAMES_ENABLED = 36;
    public static final int FRONTEND_STATUS_TYPE_LAYER_ERROR = 16;
    public static final int FRONTEND_STATUS_TYPE_LNA = 15;
    public static final int FRONTEND_STATUS_TYPE_LNB_VOLTAGE = 11;
    public static final int FRONTEND_STATUS_TYPE_MER = 17;
    public static final int FRONTEND_STATUS_TYPE_MODULATION = 9;
    public static final int FRONTEND_STATUS_TYPE_MODULATIONS_EXT = 22;
    public static final int FRONTEND_STATUS_TYPE_PER = 3;
    public static final int FRONTEND_STATUS_TYPE_PLP_ID = 12;
    public static final int FRONTEND_STATUS_TYPE_PRE_BER = 4;
    public static final int FRONTEND_STATUS_TYPE_RF_LOCK = 20;
    public static final int FRONTEND_STATUS_TYPE_ROLL_OFF = 33;
    public static final int FRONTEND_STATUS_TYPE_SIGNAL_QUALITY = 5;
    public static final int FRONTEND_STATUS_TYPE_SIGNAL_STRENGTH = 6;
    public static final int FRONTEND_STATUS_TYPE_SNR = 1;
    public static final int FRONTEND_STATUS_TYPE_SPECTRAL = 10;
    public static final int FRONTEND_STATUS_TYPE_SYMBOL_RATE = 7;
    public static final int FRONTEND_STATUS_TYPE_T2_SYSTEM_ID = 29;
    public static final int FRONTEND_STATUS_TYPE_TRANSMISSION_MODE = 27;
    public static final int FRONTEND_STATUS_TYPE_TS_DATA_RATES = 32;
    public static final int FRONTEND_STATUS_TYPE_UEC = 28;
    private Integer mAgc;
    private Integer mBandwidth;
    private Integer mBer;
    private int[] mBers;
    private int[] mCodeRates;
    private Integer mFreqOffset;
    private Integer mGuardInterval;
    private Integer mHierarchy;
    private Long mInnerFec;
    private int[] mInterleaving;
    private Integer mInversion;
    private Boolean mIsDemodLocked;
    private Boolean mIsEwbs;
    private boolean[] mIsLayerErrors;
    private Boolean mIsLinear;
    private Boolean mIsLnaOn;
    private Boolean mIsMisoEnabled;
    private Boolean mIsRfLocked;
    private Boolean mIsShortFrames;
    private int[] mIsdbtSegment;
    private Integer mLnbVoltage;
    private Integer mMer;
    private Integer mModulation;
    private int[] mModulationsExt;
    private Integer mPer;
    private Integer mPerBer;
    private Integer mPlpId;
    private Atsc3PlpTuningInfo[] mPlpInfo;
    private Integer mRollOff;
    private Integer mSignalQuality;
    private Integer mSignalStrength;
    private Integer mSnr;
    private Integer mSymbolRate;
    private Integer mSystemId;
    private Integer mTransmissionMode;
    private int[] mTsDataRate;
    private Integer mUec;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendBandwidth {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendGuardInterval {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendInterleaveMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendModulation {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendRollOff {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendStatusType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrontendTransmissionMode {
    }

    private FrontendStatus() {
    }

    public boolean isDemodLocked() {
        Boolean bool = this.mIsDemodLocked;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("DemodLocked status is empty");
    }

    public int getSnr() {
        Integer num = this.mSnr;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Snr status is empty");
    }

    public int getBer() {
        Integer num = this.mBer;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Ber status is empty");
    }

    public int getPer() {
        Integer num = this.mPer;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Per status is empty");
    }

    public int getPerBer() {
        Integer num = this.mPerBer;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PerBer status is empty");
    }

    public int getSignalQuality() {
        Integer num = this.mSignalQuality;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("SignalQuality status is empty");
    }

    public int getSignalStrength() {
        Integer num = this.mSignalStrength;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("SignalStrength status is empty");
    }

    public int getSymbolRate() {
        Integer num = this.mSymbolRate;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("SymbolRate status is empty");
    }

    public long getInnerFec() {
        Long l = this.mInnerFec;
        if (l != null) {
            return l.longValue();
        }
        throw new IllegalStateException("InnerFec status is empty");
    }

    public int getModulation() {
        Integer num = this.mModulation;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Modulation status is empty");
    }

    public int getSpectralInversion() {
        Integer num = this.mInversion;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("SpectralInversion status is empty");
    }

    public int getLnbVoltage() {
        Integer num = this.mLnbVoltage;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("LnbVoltage status is empty");
    }

    public int getPlpId() {
        Integer num = this.mPlpId;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PlpId status is empty");
    }

    public boolean isEwbs() {
        Boolean bool = this.mIsEwbs;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("Ewbs status is empty");
    }

    public int getAgc() {
        Integer num = this.mAgc;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Agc status is empty");
    }

    public boolean isLnaOn() {
        Boolean bool = this.mIsLnaOn;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("LnaOn status is empty");
    }

    public boolean[] getLayerErrors() {
        boolean[] zArr = this.mIsLayerErrors;
        if (zArr != null) {
            return zArr;
        }
        throw new IllegalStateException("LayerErrors status is empty");
    }

    public int getMer() {
        Integer num = this.mMer;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Mer status is empty");
    }

    public int getFreqOffset() {
        Integer num = this.mFreqOffset;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("FreqOffset status is empty");
    }

    public int getHierarchy() {
        Integer num = this.mHierarchy;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Hierarchy status is empty");
    }

    public boolean isRfLocked() {
        Boolean bool = this.mIsRfLocked;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("isRfLocked status is empty");
    }

    public Atsc3PlpTuningInfo[] getAtsc3PlpTuningInfo() {
        Atsc3PlpTuningInfo[] atsc3PlpTuningInfoArr = this.mPlpInfo;
        if (atsc3PlpTuningInfoArr != null) {
            return atsc3PlpTuningInfoArr;
        }
        throw new IllegalStateException("Atsc3PlpTuningInfo status is empty");
    }

    public int[] getBers() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getBers status");
        int[] iArr = this.mBers;
        if (iArr != null) {
            return iArr;
        }
        throw new IllegalStateException("Bers status is empty");
    }

    public int[] getCodeRates() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getCodeRates status");
        int[] iArr = this.mCodeRates;
        if (iArr != null) {
            return iArr;
        }
        throw new IllegalStateException("CodeRates status is empty");
    }

    public int getBandwidth() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getBandwidth status");
        Integer num = this.mBandwidth;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Bandwidth status is empty");
    }

    public int getGuardInterval() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getGuardInterval status");
        Integer num = this.mGuardInterval;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("GuardInterval status is empty");
    }

    public int getTransmissionMode() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getTransmissionMode status");
        Integer num = this.mTransmissionMode;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("TransmissionMode status is empty");
    }

    public int getUec() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getUec status");
        Integer num = this.mUec;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Uec status is empty");
    }

    public int getSystemId() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getSystemId status");
        Integer num = this.mSystemId;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("SystemId status is empty");
    }

    public int[] getInterleaving() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getInterleaving status");
        int[] iArr = this.mInterleaving;
        if (iArr != null) {
            return iArr;
        }
        throw new IllegalStateException("Interleaving status is empty");
    }

    public int[] getIsdbtSegment() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getIsdbtSegment status");
        int[] iArr = this.mIsdbtSegment;
        if (iArr != null) {
            return iArr;
        }
        throw new IllegalStateException("IsdbtSegment status is empty");
    }

    public int[] getTsDataRate() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getTsDataRate status");
        int[] iArr = this.mTsDataRate;
        if (iArr != null) {
            return iArr;
        }
        throw new IllegalStateException("TsDataRate status is empty");
    }

    public int[] getExtendedModulations() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getExtendedModulations status");
        int[] iArr = this.mModulationsExt;
        if (iArr != null) {
            return iArr;
        }
        throw new IllegalStateException("ExtendedModulations status is empty");
    }

    public int getRollOff() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "getRollOff status");
        Integer num = this.mRollOff;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("RollOff status is empty");
    }

    public boolean isMisoEnabled() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "isMisoEnabled status");
        Boolean bool = this.mIsMisoEnabled;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("isMisoEnabled status is empty");
    }

    public boolean isLinear() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "isLinear status");
        Boolean bool = this.mIsLinear;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("isLinear status is empty");
    }

    public boolean isShortFramesEnabled() {
        TunerVersionChecker.checkHigherOrEqualVersionTo(65537, "isShortFramesEnabled status");
        Boolean bool = this.mIsShortFrames;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("isShortFramesEnabled status is empty");
    }

    /* loaded from: classes2.dex */
    public static class Atsc3PlpTuningInfo {
        private final boolean mIsLocked;
        private final int mPlpId;
        private final int mUec;

        private Atsc3PlpTuningInfo(int plpId, boolean isLocked, int uec) {
            this.mPlpId = plpId;
            this.mIsLocked = isLocked;
            this.mUec = uec;
        }

        public int getPlpId() {
            return this.mPlpId;
        }

        public boolean isLocked() {
            return this.mIsLocked;
        }

        public int getUec() {
            return this.mUec;
        }
    }
}
