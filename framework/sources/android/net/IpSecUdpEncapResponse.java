package android.net;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.FileDescriptor;
import java.io.IOException;
/* loaded from: classes2.dex */
public final class IpSecUdpEncapResponse implements Parcelable {
    public static final Parcelable.Creator<IpSecUdpEncapResponse> CREATOR = new Parcelable.Creator<IpSecUdpEncapResponse>() { // from class: android.net.IpSecUdpEncapResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecUdpEncapResponse createFromParcel(Parcel in) {
            return new IpSecUdpEncapResponse(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecUdpEncapResponse[] newArray(int size) {
            return new IpSecUdpEncapResponse[size];
        }
    };
    private static final String TAG = "IpSecUdpEncapResponse";
    public final ParcelFileDescriptor fileDescriptor;
    public final int port;
    public final int resourceId;
    public final int status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.fileDescriptor != null ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.status);
        out.writeInt(this.resourceId);
        out.writeInt(this.port);
        out.writeParcelable(this.fileDescriptor, 1);
    }

    public IpSecUdpEncapResponse(int inStatus) {
        if (inStatus != 0) {
            this.status = inStatus;
            this.resourceId = -1;
            this.port = -1;
            this.fileDescriptor = null;
            return;
        }
        throw new IllegalArgumentException("Valid status implies other args must be provided");
    }

    public IpSecUdpEncapResponse(int inStatus, int inResourceId, int inPort, FileDescriptor inFd) throws IOException {
        if (inStatus == 0 && inFd == null) {
            throw new IllegalArgumentException("Valid status implies FD must be non-null");
        }
        this.status = inStatus;
        this.resourceId = inResourceId;
        this.port = inPort;
        this.fileDescriptor = inStatus == 0 ? ParcelFileDescriptor.dup(inFd) : null;
    }

    private IpSecUdpEncapResponse(Parcel in) {
        this.status = in.readInt();
        this.resourceId = in.readInt();
        this.port = in.readInt();
        this.fileDescriptor = (ParcelFileDescriptor) in.readParcelable(ParcelFileDescriptor.class.getClassLoader());
    }
}
