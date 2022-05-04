package android.debug;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
/* loaded from: classes.dex */
public class PairDevice implements Parcelable {
    public static final Parcelable.Creator<PairDevice> CREATOR = new Parcelable.Creator<PairDevice>() { // from class: android.debug.PairDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PairDevice createFromParcel(Parcel source) {
            return new PairDevice(source.readString(), source.readString(), source.readBoolean());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PairDevice[] newArray(int size) {
            return new PairDevice[size];
        }
    };
    private final boolean mConnected;
    private final String mGuid;
    private final String mName;

    public PairDevice(String name, String guid, boolean connected) {
        Preconditions.checkStringNotEmpty(name);
        Preconditions.checkStringNotEmpty(guid);
        this.mName = name;
        this.mGuid = guid;
        this.mConnected = connected;
    }

    public String getDeviceName() {
        return this.mName;
    }

    public String getGuid() {
        return this.mGuid;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mGuid);
        dest.writeBoolean(this.mConnected);
    }

    public String toString() {
        return "\n" + this.mName + "\n" + this.mGuid + "\n" + this.mConnected;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
