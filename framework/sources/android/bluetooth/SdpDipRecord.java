package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class SdpDipRecord implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: android.bluetooth.SdpDipRecord.1
        @Override // android.os.Parcelable.Creator
        public SdpDipRecord createFromParcel(Parcel in) {
            return new SdpDipRecord(in);
        }

        @Override // android.os.Parcelable.Creator
        public SdpDipRecord[] newArray(int size) {
            return new SdpDipRecord[size];
        }
    };
    private final boolean mPrimaryRecord;
    private final int mProductId;
    private final int mSpecificationId;
    private final int mVendorId;
    private final int mVendorIdSource;
    private final int mVersion;

    public SdpDipRecord(int specificationId, int vendorId, int vendorIdSource, int productId, int version, boolean primaryRecord) {
        this.mSpecificationId = specificationId;
        this.mVendorId = vendorId;
        this.mVendorIdSource = vendorIdSource;
        this.mProductId = productId;
        this.mVersion = version;
        this.mPrimaryRecord = primaryRecord;
    }

    public SdpDipRecord(Parcel in) {
        this.mSpecificationId = in.readInt();
        this.mVendorId = in.readInt();
        this.mVendorIdSource = in.readInt();
        this.mProductId = in.readInt();
        this.mVersion = in.readInt();
        this.mPrimaryRecord = in.readBoolean();
    }

    public int getSpecificationId() {
        return this.mSpecificationId;
    }

    public int getVendorId() {
        return this.mVendorId;
    }

    public int getVendorIdSource() {
        return this.mVendorIdSource;
    }

    public int getProductId() {
        return this.mProductId;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public boolean getPrimaryRecord() {
        return this.mPrimaryRecord;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mSpecificationId);
        dest.writeInt(this.mVendorId);
        dest.writeInt(this.mVendorIdSource);
        dest.writeInt(this.mProductId);
        dest.writeInt(this.mVersion);
        dest.writeBoolean(this.mPrimaryRecord);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
