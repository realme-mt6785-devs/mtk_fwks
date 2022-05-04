package android.net;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public final class IpSecSpiResponse implements Parcelable {
    public static final Parcelable.Creator<IpSecSpiResponse> CREATOR = new Parcelable.Creator<IpSecSpiResponse>() { // from class: android.net.IpSecSpiResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecSpiResponse createFromParcel(Parcel in) {
            return new IpSecSpiResponse(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecSpiResponse[] newArray(int size) {
            return new IpSecSpiResponse[size];
        }
    };
    private static final String TAG = "IpSecSpiResponse";
    public final int resourceId;
    public final int spi;
    public final int status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.status);
        out.writeInt(this.resourceId);
        out.writeInt(this.spi);
    }

    public IpSecSpiResponse(int inStatus, int inResourceId, int inSpi) {
        this.status = inStatus;
        this.resourceId = inResourceId;
        this.spi = inSpi;
    }

    public IpSecSpiResponse(int inStatus) {
        if (inStatus != 0) {
            this.status = inStatus;
            this.resourceId = -1;
            this.spi = 0;
            return;
        }
        throw new IllegalArgumentException("Valid status implies other args must be provided");
    }

    private IpSecSpiResponse(Parcel in) {
        this.status = in.readInt();
        this.resourceId = in.readInt();
        this.spi = in.readInt();
    }
}
