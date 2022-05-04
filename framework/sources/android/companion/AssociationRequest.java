package android.companion;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import com.android.internal.org.bouncycastle.math.ec.Tnaf;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class AssociationRequest implements Parcelable {
    public static final String DEVICE_PROFILE_WATCH = "android.app.role.COMPANION_DEVICE_WATCH";
    private String mCallingPackage;
    private long mCreationTime;
    private List<DeviceFilter<?>> mDeviceFilters;
    private String mDeviceProfile;
    private String mDeviceProfilePrivilegesDescription;
    private boolean mSingleDevice;
    private boolean mSkipPrompt;
    private static final String LOG_TAG = AssociationRequest.class.getSimpleName();
    public static final Parcelable.Creator<AssociationRequest> CREATOR = new Parcelable.Creator<AssociationRequest>() { // from class: android.companion.AssociationRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssociationRequest[] newArray(int size) {
            return new AssociationRequest[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssociationRequest createFromParcel(Parcel in) {
            return new AssociationRequest(in);
        }
    };

    /* loaded from: classes.dex */
    public @interface DeviceProfile {
    }

    private void onConstructed() {
        this.mCreationTime = System.currentTimeMillis();
    }

    public void setCallingPackage(String pkg) {
        this.mCallingPackage = pkg;
    }

    public void setDeviceProfilePrivilegesDescription(String desc) {
        this.mDeviceProfilePrivilegesDescription = desc;
    }

    public void setSkipPrompt(boolean value) {
        this.mSkipPrompt = true;
    }

    public boolean isSingleDevice() {
        return this.mSingleDevice;
    }

    public List<DeviceFilter<?>> getDeviceFilters() {
        return this.mDeviceFilters;
    }

    /* loaded from: classes.dex */
    public static final class Builder extends OneTimeUseBuilder<AssociationRequest> {
        private boolean mSingleDevice = false;
        private ArrayList<DeviceFilter<?>> mDeviceFilters = null;
        private String mDeviceProfile = null;

        public Builder setSingleDevice(boolean singleDevice) {
            checkNotUsed();
            this.mSingleDevice = singleDevice;
            return this;
        }

        public Builder addDeviceFilter(DeviceFilter<?> deviceFilter) {
            checkNotUsed();
            if (deviceFilter != null) {
                this.mDeviceFilters = ArrayUtils.add(this.mDeviceFilters, deviceFilter);
            }
            return this;
        }

        public Builder setDeviceProfile(String deviceProfile) {
            checkNotUsed();
            this.mDeviceProfile = deviceProfile;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.provider.OneTimeUseBuilder
        public AssociationRequest build() {
            markUsed();
            return new AssociationRequest(this.mSingleDevice, CollectionUtils.emptyIfNull(this.mDeviceFilters), this.mDeviceProfile, null, null, -1L, false);
        }
    }

    public AssociationRequest(boolean singleDevice, List<DeviceFilter<?>> deviceFilters, String deviceProfile, String callingPackage, String deviceProfilePrivilegesDescription, long creationTime, boolean skipPrompt) {
        this.mSingleDevice = false;
        this.mDeviceFilters = new ArrayList();
        this.mDeviceProfile = null;
        this.mCallingPackage = null;
        this.mDeviceProfilePrivilegesDescription = null;
        this.mSkipPrompt = false;
        this.mSingleDevice = singleDevice;
        this.mDeviceFilters = deviceFilters;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) deviceFilters);
        this.mDeviceProfile = deviceProfile;
        AnnotationValidations.validate(DeviceProfile.class, (Annotation) null, deviceProfile);
        this.mCallingPackage = callingPackage;
        this.mDeviceProfilePrivilegesDescription = deviceProfilePrivilegesDescription;
        this.mCreationTime = creationTime;
        this.mSkipPrompt = skipPrompt;
        onConstructed();
    }

    public String getDeviceProfile() {
        return this.mDeviceProfile;
    }

    public String getCallingPackage() {
        return this.mCallingPackage;
    }

    public String getDeviceProfilePrivilegesDescription() {
        return this.mDeviceProfilePrivilegesDescription;
    }

    public long getCreationTime() {
        return this.mCreationTime;
    }

    public boolean isSkipPrompt() {
        return this.mSkipPrompt;
    }

    public String toString() {
        return "AssociationRequest { singleDevice = " + this.mSingleDevice + ", deviceFilters = " + this.mDeviceFilters + ", deviceProfile = " + this.mDeviceProfile + ", callingPackage = " + this.mCallingPackage + ", deviceProfilePrivilegesDescription = " + this.mDeviceProfilePrivilegesDescription + ", creationTime = " + this.mCreationTime + ", skipPrompt = " + this.mSkipPrompt + " }";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssociationRequest that = (AssociationRequest) o;
        if (this.mSingleDevice != that.mSingleDevice || !Objects.equals(this.mDeviceFilters, that.mDeviceFilters) || !Objects.equals(this.mDeviceProfile, that.mDeviceProfile) || !Objects.equals(this.mCallingPackage, that.mCallingPackage) || !Objects.equals(this.mDeviceProfilePrivilegesDescription, that.mDeviceProfilePrivilegesDescription) || this.mCreationTime != that.mCreationTime || this.mSkipPrompt != that.mSkipPrompt) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int _hash = (1 * 31) + Boolean.hashCode(this.mSingleDevice);
        return (((((((((((_hash * 31) + Objects.hashCode(this.mDeviceFilters)) * 31) + Objects.hashCode(this.mDeviceProfile)) * 31) + Objects.hashCode(this.mCallingPackage)) * 31) + Objects.hashCode(this.mDeviceProfilePrivilegesDescription)) * 31) + Long.hashCode(this.mCreationTime)) * 31) + Boolean.hashCode(this.mSkipPrompt);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte flg = 0;
        if (this.mSingleDevice) {
            flg = (byte) (0 | 1);
        }
        if (this.mSkipPrompt) {
            flg = (byte) (flg | 64);
        }
        if (this.mDeviceProfile != null) {
            flg = (byte) (flg | 4);
        }
        if (this.mCallingPackage != null) {
            flg = (byte) (flg | 8);
        }
        if (this.mDeviceProfilePrivilegesDescription != null) {
            flg = (byte) (flg | Tnaf.POW_2_WIDTH);
        }
        dest.writeByte(flg);
        dest.writeParcelableList(this.mDeviceFilters, flags);
        String str = this.mDeviceProfile;
        if (str != null) {
            dest.writeString(str);
        }
        String str2 = this.mCallingPackage;
        if (str2 != null) {
            dest.writeString(str2);
        }
        String str3 = this.mDeviceProfilePrivilegesDescription;
        if (str3 != null) {
            dest.writeString(str3);
        }
        dest.writeLong(this.mCreationTime);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    AssociationRequest(Parcel in) {
        boolean skipPrompt = false;
        this.mSingleDevice = false;
        this.mDeviceFilters = new ArrayList();
        this.mDeviceProfile = null;
        this.mCallingPackage = null;
        this.mDeviceProfilePrivilegesDescription = null;
        this.mSkipPrompt = false;
        byte flg = in.readByte();
        boolean singleDevice = (flg & 1) != 0;
        skipPrompt = (flg & 64) != 0 ? true : skipPrompt;
        ArrayList arrayList = new ArrayList();
        in.readParcelableList(arrayList, DeviceFilter.class.getClassLoader());
        String deviceProfile = (flg & 4) == 0 ? null : in.readString();
        String callingPackage = (flg & 8) == 0 ? null : in.readString();
        String deviceProfilePrivilegesDescription = (flg & Tnaf.POW_2_WIDTH) == 0 ? null : in.readString();
        long creationTime = in.readLong();
        this.mSingleDevice = singleDevice;
        this.mDeviceFilters = arrayList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) arrayList);
        this.mDeviceProfile = deviceProfile;
        AnnotationValidations.validate(DeviceProfile.class, (Annotation) null, deviceProfile);
        this.mCallingPackage = callingPackage;
        this.mDeviceProfilePrivilegesDescription = deviceProfilePrivilegesDescription;
        this.mCreationTime = creationTime;
        this.mSkipPrompt = skipPrompt;
        onConstructed();
    }

    @Deprecated
    private void __metadata() {
    }
}
