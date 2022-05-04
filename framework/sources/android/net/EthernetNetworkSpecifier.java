package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.util.Objects;
@SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
/* loaded from: classes2.dex */
public final class EthernetNetworkSpecifier extends NetworkSpecifier implements Parcelable {
    public static final Parcelable.Creator<EthernetNetworkSpecifier> CREATOR = new Parcelable.Creator<EthernetNetworkSpecifier>() { // from class: android.net.EthernetNetworkSpecifier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EthernetNetworkSpecifier createFromParcel(Parcel in) {
            return new EthernetNetworkSpecifier(in.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EthernetNetworkSpecifier[] newArray(int size) {
            return new EthernetNetworkSpecifier[size];
        }
    };
    private final String mInterfaceName;

    public EthernetNetworkSpecifier(String interfaceName) {
        Preconditions.checkStringNotEmpty(interfaceName);
        this.mInterfaceName = interfaceName;
    }

    public String getInterfaceName() {
        return this.mInterfaceName;
    }

    @Override // android.net.NetworkSpecifier
    public boolean canBeSatisfiedBy(NetworkSpecifier other) {
        return equals(other);
    }

    public boolean equals(Object o) {
        if (!(o instanceof EthernetNetworkSpecifier)) {
            return false;
        }
        return TextUtils.equals(this.mInterfaceName, ((EthernetNetworkSpecifier) o).mInterfaceName);
    }

    public int hashCode() {
        return Objects.hashCode(this.mInterfaceName);
    }

    public String toString() {
        return "EthernetNetworkSpecifier (" + this.mInterfaceName + ")";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mInterfaceName);
    }
}
