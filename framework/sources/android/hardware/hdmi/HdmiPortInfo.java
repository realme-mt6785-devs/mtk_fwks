package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public final class HdmiPortInfo implements Parcelable {
    public static final Parcelable.Creator<HdmiPortInfo> CREATOR = new Parcelable.Creator<HdmiPortInfo>() { // from class: android.hardware.hdmi.HdmiPortInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HdmiPortInfo createFromParcel(Parcel source) {
            int id = source.readInt();
            int type = source.readInt();
            int address = source.readInt();
            boolean cec = source.readInt() == 1;
            boolean arc = source.readInt() == 1;
            boolean mhl = source.readInt() == 1;
            return new HdmiPortInfo(id, type, address, cec, mhl, arc);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HdmiPortInfo[] newArray(int size) {
            return new HdmiPortInfo[size];
        }
    };
    public static final int PORT_INPUT = 0;
    public static final int PORT_OUTPUT = 1;
    private final int mAddress;
    private final boolean mArcSupported;
    private final boolean mCecSupported;
    private final int mId;
    private final boolean mMhlSupported;
    private final int mType;

    public HdmiPortInfo(int id, int type, int address, boolean cec, boolean mhl, boolean arc) {
        this.mId = id;
        this.mType = type;
        this.mAddress = address;
        this.mCecSupported = cec;
        this.mArcSupported = arc;
        this.mMhlSupported = mhl;
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public int getAddress() {
        return this.mAddress;
    }

    public boolean isCecSupported() {
        return this.mCecSupported;
    }

    public boolean isMhlSupported() {
        return this.mMhlSupported;
    }

    public boolean isArcSupported() {
        return this.mArcSupported;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    @SystemApi
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeInt(this.mType);
        dest.writeInt(this.mAddress);
        dest.writeInt(this.mCecSupported ? 1 : 0);
        dest.writeInt(this.mArcSupported ? 1 : 0);
        dest.writeInt(this.mMhlSupported ? 1 : 0);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("port_id: ");
        s.append(this.mId);
        s.append(", ");
        s.append("type: ");
        s.append(this.mType == 0 ? "HDMI_IN" : "HDMI_OUT");
        s.append(", ");
        s.append("address: ");
        s.append(String.format("0x%04x", Integer.valueOf(this.mAddress)));
        s.append(", ");
        s.append("cec: ");
        s.append(this.mCecSupported);
        s.append(", ");
        s.append("arc: ");
        s.append(this.mArcSupported);
        s.append(", ");
        s.append("mhl: ");
        s.append(this.mMhlSupported);
        return s.toString();
    }

    public boolean equals(Object o) {
        if (!(o instanceof HdmiPortInfo)) {
            return false;
        }
        HdmiPortInfo other = (HdmiPortInfo) o;
        return this.mId == other.mId && this.mType == other.mType && this.mAddress == other.mAddress && this.mCecSupported == other.mCecSupported && this.mArcSupported == other.mArcSupported && this.mMhlSupported == other.mMhlSupported;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mId), Integer.valueOf(this.mType), Integer.valueOf(this.mAddress), Boolean.valueOf(this.mCecSupported), Boolean.valueOf(this.mArcSupported), Boolean.valueOf(this.mMhlSupported));
    }
}
