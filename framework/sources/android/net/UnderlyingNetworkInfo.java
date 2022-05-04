package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
/* loaded from: classes2.dex */
public final class UnderlyingNetworkInfo implements Parcelable {
    public static final Parcelable.Creator<UnderlyingNetworkInfo> CREATOR = new Parcelable.Creator<UnderlyingNetworkInfo>() { // from class: android.net.UnderlyingNetworkInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnderlyingNetworkInfo createFromParcel(Parcel in) {
            return new UnderlyingNetworkInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnderlyingNetworkInfo[] newArray(int size) {
            return new UnderlyingNetworkInfo[size];
        }
    };
    private final String mIface;
    private final int mOwnerUid;
    private final List<String> mUnderlyingIfaces;

    public UnderlyingNetworkInfo(int ownerUid, String iface, List<String> underlyingIfaces) {
        Objects.requireNonNull(iface);
        Objects.requireNonNull(underlyingIfaces);
        this.mOwnerUid = ownerUid;
        this.mIface = iface;
        this.mUnderlyingIfaces = Collections.unmodifiableList(new ArrayList(underlyingIfaces));
    }

    private UnderlyingNetworkInfo(Parcel in) {
        this.mOwnerUid = in.readInt();
        this.mIface = in.readString();
        List<String> underlyingIfaces = new ArrayList<>();
        in.readList(underlyingIfaces, null);
        this.mUnderlyingIfaces = Collections.unmodifiableList(underlyingIfaces);
    }

    public int getOwnerUid() {
        return this.mOwnerUid;
    }

    public String getInterface() {
        return this.mIface;
    }

    public List<String> getUnderlyingInterfaces() {
        return this.mUnderlyingIfaces;
    }

    public String toString() {
        return "UnderlyingNetworkInfo{ownerUid=" + this.mOwnerUid + ", iface='" + this.mIface + DateFormat.QUOTE + ", underlyingIfaces='" + this.mUnderlyingIfaces.toString() + DateFormat.QUOTE + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mOwnerUid);
        dest.writeString(this.mIface);
        dest.writeList(this.mUnderlyingIfaces);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnderlyingNetworkInfo)) {
            return false;
        }
        UnderlyingNetworkInfo that = (UnderlyingNetworkInfo) o;
        return this.mOwnerUid == that.getOwnerUid() && Objects.equals(this.mIface, that.getInterface()) && Objects.equals(this.mUnderlyingIfaces, that.getUnderlyingInterfaces());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mOwnerUid), this.mIface, this.mUnderlyingIfaces);
    }
}
