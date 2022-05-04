package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class CameraStatus implements Parcelable {
    public static final Parcelable.Creator<CameraStatus> CREATOR = new Parcelable.Creator<CameraStatus>() { // from class: android.hardware.CameraStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraStatus createFromParcel(Parcel in) {
            CameraStatus status = new CameraStatus();
            status.readFromParcel(in);
            return status;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraStatus[] newArray(int size) {
            return new CameraStatus[size];
        }
    };
    public String cameraId;
    public int status;
    public String[] unavailablePhysicalCameras;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.cameraId);
        out.writeInt(this.status);
        out.writeStringArray(this.unavailablePhysicalCameras);
    }

    public void readFromParcel(Parcel in) {
        this.cameraId = in.readString();
        this.status = in.readInt();
        this.unavailablePhysicalCameras = in.readStringArray();
    }
}
