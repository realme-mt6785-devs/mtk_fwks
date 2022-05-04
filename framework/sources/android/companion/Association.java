package android.companion;

import android.annotation.NonNull;
import android.annotation.UserIdInt;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.org.bouncycastle.math.ec.Tnaf;
import com.android.internal.util.AnnotationValidations;
import java.util.Date;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Association implements Parcelable {
    public static final Parcelable.Creator<Association> CREATOR = new Parcelable.Creator<Association>() { // from class: android.companion.Association.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Association[] newArray(int size) {
            return new Association[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Association createFromParcel(Parcel in) {
            return new Association(in);
        }
    };
    private final String mDeviceMacAddress;
    private final String mDeviceProfile;
    private final boolean mNotifyOnDeviceNearby;
    private final String mPackageName;
    private final long mTimeApprovedMs;
    private final int mUserId;

    public int getUserId() {
        return this.mUserId;
    }

    private String timeApprovedMsToString() {
        return new Date(this.mTimeApprovedMs).toString();
    }

    public Association(int userId, String deviceMacAddress, String packageName, String deviceProfile, boolean notifyOnDeviceNearby, long timeApprovedMs) {
        this.mUserId = userId;
        AnnotationValidations.validate((Class<UserIdInt>) UserIdInt.class, (UserIdInt) null, userId);
        this.mDeviceMacAddress = deviceMacAddress;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) deviceMacAddress);
        this.mPackageName = packageName;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) packageName);
        this.mDeviceProfile = deviceProfile;
        this.mNotifyOnDeviceNearby = notifyOnDeviceNearby;
        this.mTimeApprovedMs = timeApprovedMs;
    }

    public String getDeviceMacAddress() {
        return this.mDeviceMacAddress;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getDeviceProfile() {
        return this.mDeviceProfile;
    }

    public boolean isNotifyOnDeviceNearby() {
        return this.mNotifyOnDeviceNearby;
    }

    public long getTimeApprovedMs() {
        return this.mTimeApprovedMs;
    }

    public String toString() {
        return "Association { userId = " + this.mUserId + ", deviceMacAddress = " + this.mDeviceMacAddress + ", packageName = " + this.mPackageName + ", deviceProfile = " + this.mDeviceProfile + ", notifyOnDeviceNearby = " + this.mNotifyOnDeviceNearby + ", timeApprovedMs = " + timeApprovedMsToString() + " }";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Association that = (Association) o;
        if (this.mUserId != that.mUserId || !Objects.equals(this.mDeviceMacAddress, that.mDeviceMacAddress) || !Objects.equals(this.mPackageName, that.mPackageName) || !Objects.equals(this.mDeviceProfile, that.mDeviceProfile) || this.mNotifyOnDeviceNearby != that.mNotifyOnDeviceNearby || this.mTimeApprovedMs != that.mTimeApprovedMs) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int _hash = (1 * 31) + this.mUserId;
        return (((((((((_hash * 31) + Objects.hashCode(this.mDeviceMacAddress)) * 31) + Objects.hashCode(this.mPackageName)) * 31) + Objects.hashCode(this.mDeviceProfile)) * 31) + Boolean.hashCode(this.mNotifyOnDeviceNearby)) * 31) + Long.hashCode(this.mTimeApprovedMs);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte flg = 0;
        if (this.mNotifyOnDeviceNearby) {
            flg = (byte) (0 | 16);
        }
        if (this.mDeviceProfile != null) {
            flg = (byte) (flg | 8);
        }
        dest.writeByte(flg);
        dest.writeInt(this.mUserId);
        dest.writeString(this.mDeviceMacAddress);
        dest.writeString(this.mPackageName);
        String str = this.mDeviceProfile;
        if (str != null) {
            dest.writeString(str);
        }
        dest.writeLong(this.mTimeApprovedMs);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    Association(Parcel in) {
        byte flg = in.readByte();
        boolean notifyOnDeviceNearby = (flg & Tnaf.POW_2_WIDTH) != 0;
        int userId = in.readInt();
        String deviceMacAddress = in.readString();
        String packageName = in.readString();
        String deviceProfile = (flg & 8) == 0 ? null : in.readString();
        long timeApprovedMs = in.readLong();
        this.mUserId = userId;
        AnnotationValidations.validate((Class<UserIdInt>) UserIdInt.class, (UserIdInt) null, userId);
        this.mDeviceMacAddress = deviceMacAddress;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) deviceMacAddress);
        this.mPackageName = packageName;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) packageName);
        this.mDeviceProfile = deviceProfile;
        this.mNotifyOnDeviceNearby = notifyOnDeviceNearby;
        this.mTimeApprovedMs = timeApprovedMs;
    }

    @Deprecated
    private void __metadata() {
    }
}
