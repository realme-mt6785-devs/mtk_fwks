package android.telephony;

import android.hardware.radio.V1_0.CellInfo;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.telephony.Rlog;
/* loaded from: classes3.dex */
public final class CellInfoGsm extends CellInfo implements Parcelable {
    public static final Parcelable.Creator<CellInfoGsm> CREATOR = new Parcelable.Creator<CellInfoGsm>() { // from class: android.telephony.CellInfoGsm.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoGsm createFromParcel(Parcel in) {
            in.readInt();
            return CellInfoGsm.createFromParcelBody(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoGsm[] newArray(int size) {
            return new CellInfoGsm[size];
        }
    };
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoGsm";
    private CellIdentityGsm mCellIdentityGsm;
    private CellSignalStrengthGsm mCellSignalStrengthGsm;

    public CellInfoGsm() {
        this.mCellIdentityGsm = new CellIdentityGsm();
        this.mCellSignalStrengthGsm = new CellSignalStrengthGsm();
    }

    public CellInfoGsm(CellInfoGsm ci) {
        super(ci);
        this.mCellIdentityGsm = ci.mCellIdentityGsm.copy();
        this.mCellSignalStrengthGsm = ci.mCellSignalStrengthGsm.copy();
    }

    public CellInfoGsm(CellInfo ci) {
        super(ci);
        android.hardware.radio.V1_0.CellInfoGsm cig = ci.gsm.get(0);
        this.mCellIdentityGsm = new CellIdentityGsm(cig.cellIdentityGsm);
        this.mCellSignalStrengthGsm = new CellSignalStrengthGsm(cig.signalStrengthGsm);
    }

    public CellInfoGsm(android.hardware.radio.V1_2.CellInfo ci) {
        super(ci);
        android.hardware.radio.V1_2.CellInfoGsm cig = ci.gsm.get(0);
        this.mCellIdentityGsm = new CellIdentityGsm(cig.cellIdentityGsm);
        this.mCellSignalStrengthGsm = new CellSignalStrengthGsm(cig.signalStrengthGsm);
    }

    public CellInfoGsm(android.hardware.radio.V1_4.CellInfo ci, long timeStamp) {
        super(ci, timeStamp);
        android.hardware.radio.V1_2.CellInfoGsm cig = ci.info.gsm();
        this.mCellIdentityGsm = new CellIdentityGsm(cig.cellIdentityGsm);
        this.mCellSignalStrengthGsm = new CellSignalStrengthGsm(cig.signalStrengthGsm);
    }

    public CellInfoGsm(android.hardware.radio.V1_5.CellInfo ci, long timeStamp) {
        super(ci, timeStamp);
        android.hardware.radio.V1_5.CellInfoGsm cig = ci.ratSpecificInfo.gsm();
        this.mCellIdentityGsm = new CellIdentityGsm(cig.cellIdentityGsm);
        this.mCellSignalStrengthGsm = new CellSignalStrengthGsm(cig.signalStrengthGsm);
    }

    public CellInfoGsm(android.hardware.radio.V1_6.CellInfo ci, long timeStamp) {
        super(ci, timeStamp);
        android.hardware.radio.V1_5.CellInfoGsm cig = ci.ratSpecificInfo.gsm();
        this.mCellIdentityGsm = new CellIdentityGsm(cig.cellIdentityGsm);
        this.mCellSignalStrengthGsm = new CellSignalStrengthGsm(cig.signalStrengthGsm);
    }

    @Override // android.telephony.CellInfo
    public CellIdentityGsm getCellIdentity() {
        return this.mCellIdentityGsm;
    }

    public void setCellIdentity(CellIdentityGsm cid) {
        this.mCellIdentityGsm = cid;
    }

    @Override // android.telephony.CellInfo
    public CellSignalStrengthGsm getCellSignalStrength() {
        return this.mCellSignalStrengthGsm;
    }

    @Override // android.telephony.CellInfo
    public CellInfo sanitizeLocationInfo() {
        CellInfoGsm result = new CellInfoGsm(this);
        result.mCellIdentityGsm = this.mCellIdentityGsm.sanitizeLocationInfo();
        return result;
    }

    public void setCellSignalStrength(CellSignalStrengthGsm css) {
        this.mCellSignalStrengthGsm = css;
    }

    @Override // android.telephony.CellInfo
    public int hashCode() {
        return super.hashCode() + this.mCellIdentityGsm.hashCode() + this.mCellSignalStrengthGsm.hashCode();
    }

    @Override // android.telephony.CellInfo
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        try {
            CellInfoGsm o = (CellInfoGsm) other;
            if (this.mCellIdentityGsm.equals(o.mCellIdentityGsm)) {
                return this.mCellSignalStrengthGsm.equals(o.mCellSignalStrengthGsm);
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override // android.telephony.CellInfo
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CellInfoGsm:{");
        sb.append(super.toString());
        sb.append(" ");
        sb.append(this.mCellIdentityGsm);
        sb.append(" ");
        sb.append(this.mCellSignalStrengthGsm);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags, 1);
        this.mCellIdentityGsm.writeToParcel(dest, flags);
        this.mCellSignalStrengthGsm.writeToParcel(dest, flags);
    }

    private CellInfoGsm(Parcel in) {
        super(in);
        this.mCellIdentityGsm = CellIdentityGsm.CREATOR.createFromParcel(in);
        this.mCellSignalStrengthGsm = CellSignalStrengthGsm.CREATOR.createFromParcel(in);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CellInfoGsm createFromParcelBody(Parcel in) {
        return new CellInfoGsm(in);
    }

    private static void log(String s) {
        Rlog.w(LOG_TAG, s);
    }
}
