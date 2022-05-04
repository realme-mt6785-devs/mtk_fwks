package com.mediatek.gnssdebugreport;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class DebugDataReport implements Parcelable {
    public static final Parcelable.Creator<DebugDataReport> CREATOR = new Parcelable.Creator<DebugDataReport>() { // from class: com.mediatek.gnssdebugreport.DebugDataReport.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DebugDataReport createFromParcel(Parcel in) {
            return new DebugDataReport(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DebugDataReport[] newArray(int size) {
            return new DebugDataReport[size];
        }
    };
    public static final String DATA_KEY = "DebugDataReport";
    public static final String DATA_KEY_TYPE1 = "data_type1";
    public static final String JSON_TYPE = "type";
    private double mCB;
    private double mClkTemp;
    private double mCompCB;
    private int mEPOage;
    private int mHaveEPO;
    private double mInitLlhHeight;
    private double mInitLlhLati;
    private double mInitLlhLongi;
    private float mInitPacc;
    private int mInitSrc;
    private int mLsvalid;
    private int mMPEvalid;
    private int mPga;
    private int mSaturation;
    private float mSensorHACC;
    private int mSvnum;
    private long mTT4SV;
    private float mTop4CNR;
    private long mTtff;

    /* loaded from: classes.dex */
    public static class DebugData840 {
        public static final String KEY_AID_HEIGHT = "aiding_height";
        public static final String KEY_AID_LAT = "aiding_lat";
        public static final String KEY_AID_LON = "aiding_lon";
        public static final String KEY_AID_SUMMARY = "aiding_summary";
        public static final String KEY_EPO = "epo";
        public static final String KEY_EPO_AGE = "epo_age";
        public static final String KEY_NLP = "nlp";
        public static final String KEY_NV = "nv";
        public static final String KEY_QEPO = "qepo";
        public static final String KEY_SUPL_INJECT = "supl_inject";
        public static final String KEY_VER = "ver";
    }

    /* loaded from: classes.dex */
    public static class DebugData841 {
        public static final String KEY_BLANKING = "blanking";
        public static final String KEY_CHIP_SUMMARY = "chip_summary";
        public static final String KEY_CLKD = "clk_d";
        public static final String KEY_CLKD_RATE = "clk_d_rate";
        public static final String KEY_DCXO_TEMPER = "dcxo_temper";
        public static final String KEY_DCXO_TEMPER_RATE = "dcxo_temper_rate";
        public static final String KEY_DIGI_I = "digi_i";
        public static final String KEY_DIGI_Q = "digi_q";
        public static final String KEY_NOISE_FLOOR = "noise_floor";
        public static final String KEY_PGA_GAIN = "pga_gain";
        public static final String KEY_SENSOR = "sensor";
        public static final String KEY_VER = "ver";
        public static final String KEY_XO_TEMPER = "xo_temper";
        public static final String KEY_XO_TEMPER_RATE = "xo_temper_rate";
    }

    /* loaded from: classes.dex */
    public static class DebugData842 {
        public static final String KEY_FAIL_EVENT = "fail_event";
        public static final String KEY_FAIL_PROCESS = "fail_process";
        public static final String KEY_TIME = "time";
        public static final String KEY_VER = "ver";
    }

    /* loaded from: classes.dex */
    public static class DebugData843 {
        public static final String KEY_DOWNLOAD_STATUS = "download_status";
        public static final String KEY_NETWORK_CAPABILITY = "nw_capa";
        public static final String KEY_NETWORK_CONNECTION = "nw_conn";
        public static final String KEY_TYPE = "type";
        public static final String KEY_VER = "ver";
    }

    /* loaded from: classes.dex */
    public static class DebugDataAgpsSessionInfo {
        public static final int JSON_TYPE_ID = 9001;
        public static final String KEY_HAS_ACQUISION = "has_acquision";
        public static final String KEY_HAS_ALMANAC = "has_almanac";
        public static final String KEY_HAS_AUX_INFO = "has_aux_info";
        public static final String KEY_HAS_DATA_BIT_ASSIST = "has_data_bit_assist";
        public static final String KEY_HAS_DGPS = "has_dgps";
        public static final String KEY_HAS_EOP = "has_eop";
        public static final String KEY_HAS_EPHEMERIS = "has_ephemeris";
        public static final String KEY_HAS_IONOSPHERE = "has_ionosphere";
        public static final String KEY_HAS_LOC_EST = "has_loc_est";
        public static final String KEY_HAS_REF_LOCATION = "has_ref_location";
        public static final String KEY_HAS_REF_TIME = "has_ref_time";
        public static final String KEY_HAS_RTI = "has_rti";
        public static final String KEY_HAS_SAT_MEAS = "has_sat_meas";
        public static final String KEY_HAS_TIME_MODEL = "has_time_model";
        public static final String KEY_HAS_TOW_ASSIST = "has_tow_assist";
        public static final String KEY_HAS_UTC = "has_utc";
        public static final String KEY_INITIATOR = "initiator";
        public static final String KEY_PLANE = "plane";
        public static final String KEY_PROTOCOL = "protocol";
        public static final String KEY_RESULT = "result";
        public static final String KEY_SESSION_ID = "session_id";
        public static final String KEY_SESSION_PERIOD = "session_period";
        public static final String KEY_VER = "ver";
    }

    public DebugDataReport(double CB, double CompCB, double ClkTemp, int Saturation, int Pga, long Ttff, int Svnum, long TT4SV, float Top4CNR, double InitLlhLongi, double InitLlhLati, double InitLlhHeight, int InitSrc, float InitPacc, int HaveEPO, int EPOage, float SensorHACC, int MPEvalid, int Lsvalid) {
        this.mCB = CB;
        this.mCompCB = CompCB;
        this.mClkTemp = ClkTemp;
        this.mSaturation = Saturation;
        this.mPga = Pga;
        this.mTtff = Ttff;
        this.mSvnum = Svnum;
        this.mTT4SV = TT4SV;
        this.mTop4CNR = Top4CNR;
        this.mInitLlhLongi = InitLlhLongi;
        this.mInitLlhLati = InitLlhLati;
        this.mInitLlhHeight = InitLlhHeight;
        this.mInitSrc = InitSrc;
        this.mInitPacc = InitPacc;
        this.mHaveEPO = HaveEPO;
        this.mEPOage = EPOage;
        this.mSensorHACC = SensorHACC;
        this.mMPEvalid = MPEvalid;
        this.mLsvalid = Lsvalid;
    }

    public DebugDataReport(Parcel source) {
        this.mCB = source.readDouble();
        this.mCompCB = source.readDouble();
        this.mClkTemp = source.readDouble();
        this.mSaturation = source.readInt();
        this.mPga = source.readInt();
        this.mTtff = source.readLong();
        this.mSvnum = source.readInt();
        this.mTT4SV = source.readLong();
        this.mTop4CNR = source.readFloat();
        this.mInitLlhLongi = source.readDouble();
        this.mInitLlhLati = source.readDouble();
        this.mInitLlhHeight = source.readDouble();
        this.mInitSrc = source.readInt();
        this.mInitPacc = source.readFloat();
        this.mHaveEPO = source.readInt();
        this.mEPOage = source.readInt();
        this.mSensorHACC = source.readFloat();
        this.mMPEvalid = source.readInt();
        this.mLsvalid = source.readInt();
    }

    public double getCB() {
        return this.mCB;
    }

    public double getmCompCB() {
        return this.mCompCB;
    }

    public double getClkTemp() {
        return this.mClkTemp;
    }

    public int getSaturation() {
        return this.mSaturation;
    }

    public int getPga() {
        return this.mPga;
    }

    public long getTtff() {
        return this.mTtff;
    }

    public int getSvnum() {
        return this.mSvnum;
    }

    public long getTT4SV() {
        return this.mTT4SV;
    }

    public float getTop4CNR() {
        return this.mTop4CNR;
    }

    public double getInitLlhLongi() {
        return this.mInitLlhLongi;
    }

    public double getInitLlhLati() {
        return this.mInitLlhLati;
    }

    public double getInitLlhHeight() {
        return this.mInitLlhHeight;
    }

    public int getInitSrc() {
        return this.mInitSrc;
    }

    public float getInitPacc() {
        return this.mInitPacc;
    }

    public int getHaveEPO() {
        return this.mHaveEPO;
    }

    public int getEPOage() {
        return this.mEPOage;
    }

    public float getSensorHACC() {
        return this.mSensorHACC;
    }

    public int getMPEvalid() {
        return this.mMPEvalid;
    }

    public int getLsvalid() {
        return this.mLsvalid;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mCB);
        dest.writeDouble(this.mCompCB);
        dest.writeDouble(this.mClkTemp);
        dest.writeInt(this.mSaturation);
        dest.writeInt(this.mPga);
        dest.writeLong(this.mTtff);
        dest.writeInt(this.mSvnum);
        dest.writeLong(this.mTT4SV);
        dest.writeFloat(this.mTop4CNR);
        dest.writeDouble(this.mInitLlhLongi);
        dest.writeDouble(this.mInitLlhLati);
        dest.writeDouble(this.mInitLlhHeight);
        dest.writeInt(this.mInitSrc);
        dest.writeFloat(this.mInitPacc);
        dest.writeInt(this.mHaveEPO);
        dest.writeInt(this.mEPOage);
        dest.writeFloat(this.mSensorHACC);
        dest.writeInt(this.mMPEvalid);
        dest.writeInt(this.mLsvalid);
    }

    public String toString() {
        return "[" + this.mCB + ", " + this.mCompCB + ", " + this.mClkTemp + ", " + this.mSaturation + ", " + this.mPga + ", " + this.mTtff + ", " + this.mSvnum + ", " + this.mTT4SV + ", " + this.mTop4CNR + ", " + this.mInitLlhLongi + ", " + this.mInitLlhLati + ", " + this.mInitLlhHeight + ", " + this.mInitSrc + ", " + this.mInitPacc + ", " + this.mHaveEPO + ", " + this.mEPOage + ", " + this.mSensorHACC + ", " + this.mMPEvalid + ", " + this.mLsvalid + "]";
    }
}
