package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SdpRecord implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: android.bluetooth.SdpRecord.1
        @Override // android.os.Parcelable.Creator
        public SdpRecord createFromParcel(Parcel in) {
            return new SdpRecord(in);
        }

        @Override // android.os.Parcelable.Creator
        public SdpRecord[] newArray(int size) {
            return new SdpRecord[size];
        }
    };
    private final byte[] mRawData;
    private final int mRawSize;

    public String toString() {
        return "BluetoothSdpRecord [rawData=" + Arrays.toString(this.mRawData) + ", rawSize=" + this.mRawSize + "]";
    }

    public SdpRecord(int sizeRecord, byte[] record) {
        this.mRawData = record;
        this.mRawSize = sizeRecord;
    }

    public SdpRecord(Parcel in) {
        int readInt = in.readInt();
        this.mRawSize = readInt;
        byte[] bArr = new byte[readInt];
        this.mRawData = bArr;
        in.readByteArray(bArr);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mRawSize);
        dest.writeByteArray(this.mRawData);
    }

    public byte[] getRawData() {
        return this.mRawData;
    }

    public int getRawSize() {
        return this.mRawSize;
    }
}
