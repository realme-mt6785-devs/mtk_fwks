package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.telephony.Rlog;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes3.dex */
public abstract class CellIdentity implements Parcelable {
    public static final Parcelable.Creator<CellIdentity> CREATOR = new Parcelable.Creator<CellIdentity>() { // from class: android.telephony.CellIdentity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellIdentity createFromParcel(Parcel in) {
            int type = in.readInt();
            switch (type) {
                case 1:
                    return CellIdentityGsm.createFromParcelBody(in);
                case 2:
                    return CellIdentityCdma.createFromParcelBody(in);
                case 3:
                    return CellIdentityLte.createFromParcelBody(in);
                case 4:
                    return CellIdentityWcdma.createFromParcelBody(in);
                case 5:
                    return CellIdentityTdscdma.createFromParcelBody(in);
                case 6:
                    return CellIdentityNr.createFromParcelBody(in);
                default:
                    throw new IllegalArgumentException("Bad Cell identity Parcel");
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellIdentity[] newArray(int size) {
            return new CellIdentity[size];
        }
    };
    public static final int INVALID_CHANNEL_NUMBER = Integer.MAX_VALUE;
    public static final int MCC_LENGTH = 3;
    public static final int MNC_MAX_LENGTH = 3;
    public static final int MNC_MIN_LENGTH = 2;
    protected String mAlphaLong;
    protected String mAlphaShort;
    protected String mGlobalCellId;
    protected final String mMccStr;
    protected final String mMncStr;
    protected final String mTag;
    protected final int mType;

    @SystemApi
    public abstract CellLocation asCellLocation();

    @SystemApi
    public abstract CellIdentity sanitizeLocationInfo();

    protected abstract void updateGlobalCellId();

    /* JADX INFO: Access modifiers changed from: protected */
    public CellIdentity(String tag, int type, String mcc, String mnc, String alphal, String alphas) {
        this.mTag = tag;
        this.mType = type;
        if (mcc == null || isMcc(mcc)) {
            this.mMccStr = mcc;
        } else if (mcc.isEmpty() || mcc.equals(String.valueOf(Integer.MAX_VALUE))) {
            this.mMccStr = null;
        } else {
            this.mMccStr = null;
            log("invalid MCC format: " + mcc);
        }
        if (mnc == null || isMnc(mnc)) {
            this.mMncStr = mnc;
        } else if (mnc.isEmpty() || mnc.equals(String.valueOf(Integer.MAX_VALUE))) {
            this.mMncStr = null;
        } else {
            this.mMncStr = null;
            log("invalid MNC format: " + mnc);
        }
        String str = this.mMccStr;
        if ((str != null && this.mMncStr == null) || (str == null && this.mMncStr != null)) {
            AnomalyReporter.reportAnomaly(UUID.fromString("a3ab0b9d-f2aa-4baf-911d-7096c0d4645a"), "CellIdentity Missing Half of PLMN ID");
        }
        this.mAlphaLong = alphal;
        this.mAlphaShort = alphas;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.mType;
    }

    public String getMccString() {
        return this.mMccStr;
    }

    public String getMncString() {
        return this.mMncStr;
    }

    public int getChannelNumber() {
        return Integer.MAX_VALUE;
    }

    public CharSequence getOperatorAlphaLong() {
        return this.mAlphaLong;
    }

    public void setOperatorAlphaLong(String alphaLong) {
        this.mAlphaLong = alphaLong;
    }

    public CharSequence getOperatorAlphaShort() {
        return this.mAlphaShort;
    }

    public void setOperatorAlphaShort(String alphaShort) {
        this.mAlphaShort = alphaShort;
    }

    public String getGlobalCellId() {
        return this.mGlobalCellId;
    }

    public boolean isSameCell(CellIdentity ci) {
        if (ci != null && getClass() == ci.getClass()) {
            return TextUtils.equals(getGlobalCellId(), ci.getGlobalCellId());
        }
        return false;
    }

    public String getPlmn() {
        if (this.mMccStr == null || this.mMncStr == null) {
            return null;
        }
        return this.mMccStr + this.mMncStr;
    }

    public boolean equals(Object other) {
        if (!(other instanceof CellIdentity)) {
            return false;
        }
        CellIdentity o = (CellIdentity) other;
        return this.mType == o.mType && TextUtils.equals(this.mMccStr, o.mMccStr) && TextUtils.equals(this.mMncStr, o.mMncStr) && TextUtils.equals(this.mAlphaLong, o.mAlphaLong) && TextUtils.equals(this.mAlphaShort, o.mAlphaShort);
    }

    public int hashCode() {
        return Objects.hash(this.mAlphaLong, this.mAlphaShort, this.mMccStr, this.mMncStr, Integer.valueOf(this.mType));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int type) {
        dest.writeInt(type);
        dest.writeString(this.mMccStr);
        dest.writeString(this.mMncStr);
        dest.writeString(this.mAlphaLong);
        dest.writeString(this.mAlphaShort);
    }

    public static boolean isValidPlmn(String plmn) {
        return plmn.length() >= 5 && plmn.length() <= 6 && isMcc(plmn.substring(0, 3)) && isMnc(plmn.substring(3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CellIdentity(String tag, int type, Parcel source) {
        this(tag, type, source.readString(), source.readString(), source.readString(), source.readString());
    }

    protected void log(String s) {
        Rlog.w(this.mTag, s);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final int inRangeOrUnavailable(int value, int rangeMin, int rangeMax) {
        if (value < rangeMin || value > rangeMax) {
            return Integer.MAX_VALUE;
        }
        return value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final long inRangeOrUnavailable(long value, long rangeMin, long rangeMax) {
        if (value < rangeMin || value > rangeMax) {
            return Long.MAX_VALUE;
        }
        return value;
    }

    protected static final int inRangeOrUnavailable(int value, int rangeMin, int rangeMax, int special) {
        if ((value < rangeMin || value > rangeMax) && value != special) {
            return Integer.MAX_VALUE;
        }
        return value;
    }

    private static boolean isMcc(String mcc) {
        if (mcc.length() != 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (mcc.charAt(i) < '0' || mcc.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    private static boolean isMnc(String mnc) {
        if (mnc.length() < 2 || mnc.length() > 3) {
            return false;
        }
        for (int i = 0; i < mnc.length(); i++) {
            if (mnc.charAt(i) < '0' || mnc.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static CellIdentity create(android.hardware.radio.V1_0.CellIdentity cellIdentity) {
        if (cellIdentity == null) {
            return null;
        }
        switch (cellIdentity.cellInfoType) {
            case 1:
                if (cellIdentity.cellIdentityGsm.size() == 1) {
                    return new CellIdentityGsm(cellIdentity.cellIdentityGsm.get(0));
                }
                break;
            case 2:
                if (cellIdentity.cellIdentityCdma.size() == 1) {
                    return new CellIdentityCdma(cellIdentity.cellIdentityCdma.get(0));
                }
                break;
            case 3:
                if (cellIdentity.cellIdentityLte.size() == 1) {
                    return new CellIdentityLte(cellIdentity.cellIdentityLte.get(0));
                }
                break;
            case 4:
                if (cellIdentity.cellIdentityWcdma.size() == 1) {
                    return new CellIdentityWcdma(cellIdentity.cellIdentityWcdma.get(0));
                }
                break;
            case 5:
                if (cellIdentity.cellIdentityTdscdma.size() == 1) {
                    return new CellIdentityTdscdma(cellIdentity.cellIdentityTdscdma.get(0));
                }
                break;
        }
        return null;
    }

    public static CellIdentity create(android.hardware.radio.V1_2.CellIdentity cellIdentity) {
        if (cellIdentity == null) {
            return null;
        }
        switch (cellIdentity.cellInfoType) {
            case 1:
                if (cellIdentity.cellIdentityGsm.size() == 1) {
                    return new CellIdentityGsm(cellIdentity.cellIdentityGsm.get(0));
                }
                break;
            case 2:
                if (cellIdentity.cellIdentityCdma.size() == 1) {
                    return new CellIdentityCdma(cellIdentity.cellIdentityCdma.get(0));
                }
                break;
            case 3:
                if (cellIdentity.cellIdentityLte.size() == 1) {
                    return new CellIdentityLte(cellIdentity.cellIdentityLte.get(0));
                }
                break;
            case 4:
                if (cellIdentity.cellIdentityWcdma.size() == 1) {
                    return new CellIdentityWcdma(cellIdentity.cellIdentityWcdma.get(0));
                }
                break;
            case 5:
                if (cellIdentity.cellIdentityTdscdma.size() == 1) {
                    return new CellIdentityTdscdma(cellIdentity.cellIdentityTdscdma.get(0));
                }
                break;
        }
        return null;
    }

    public static CellIdentity create(android.hardware.radio.V1_5.CellIdentity ci) {
        if (ci == null) {
            return null;
        }
        switch (ci.getDiscriminator()) {
            case 1:
                return new CellIdentityGsm(ci.gsm());
            case 2:
                return new CellIdentityWcdma(ci.wcdma());
            case 3:
                return new CellIdentityTdscdma(ci.tdscdma());
            case 4:
                return new CellIdentityCdma(ci.cdma());
            case 5:
                return new CellIdentityLte(ci.lte());
            case 6:
                return new CellIdentityNr(ci.nr());
            default:
                return null;
        }
    }
}
