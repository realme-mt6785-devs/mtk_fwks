package android.uwb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
@SystemApi
/* loaded from: classes3.dex */
public final class UwbAddress implements Parcelable {
    public static final Parcelable.Creator<UwbAddress> CREATOR = new Parcelable.Creator<UwbAddress>() { // from class: android.uwb.UwbAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UwbAddress createFromParcel(Parcel in) {
            byte[] address = new byte[in.readInt()];
            in.readByteArray(address);
            return UwbAddress.fromBytes(address);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UwbAddress[] newArray(int size) {
            return new UwbAddress[size];
        }
    };
    public static final int EXTENDED_ADDRESS_BYTE_LENGTH = 8;
    public static final int SHORT_ADDRESS_BYTE_LENGTH = 2;
    private final byte[] mAddressBytes;

    private UwbAddress(byte[] address) {
        this.mAddressBytes = address;
    }

    public static UwbAddress fromBytes(byte[] address) {
        if (address.length == 2 || address.length == 8) {
            return new UwbAddress(address);
        }
        throw new IllegalArgumentException("Invalid UwbAddress length " + address.length);
    }

    public byte[] toBytes() {
        return this.mAddressBytes;
    }

    public int size() {
        return this.mAddressBytes.length;
    }

    public String toString() {
        byte[] bArr;
        StringBuilder builder = new StringBuilder("0x");
        for (byte addressByte : this.mAddressBytes) {
            builder.append(String.format("%02X", Byte.valueOf(addressByte)));
        }
        return builder.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof UwbAddress) {
            return Arrays.equals(this.mAddressBytes, ((UwbAddress) obj).toBytes());
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mAddressBytes);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAddressBytes.length);
        dest.writeByteArray(this.mAddressBytes);
    }
}
