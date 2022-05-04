package android.app.admin;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public final class FullyManagedDeviceProvisioningParams implements Parcelable {
    private static final String CAN_DEVICE_OWNER_GRANT_SENSOR_PERMISSIONS_PARAM = "CAN_DEVICE_OWNER_GRANT_SENSOR_PERMISSIONS";
    public static final Parcelable.Creator<FullyManagedDeviceProvisioningParams> CREATOR = new Parcelable.Creator<FullyManagedDeviceProvisioningParams>() { // from class: android.app.admin.FullyManagedDeviceProvisioningParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FullyManagedDeviceProvisioningParams createFromParcel(Parcel in) {
            ComponentName componentName = (ComponentName) in.readTypedObject(ComponentName.CREATOR);
            String ownerName = in.readString();
            boolean leaveAllSystemAppsEnabled = in.readBoolean();
            String timeZone = in.readString();
            long localtime = in.readLong();
            String locale = in.readString();
            boolean deviceOwnerCanGrantSensorsPermissions = in.readBoolean();
            return new FullyManagedDeviceProvisioningParams(componentName, ownerName, leaveAllSystemAppsEnabled, timeZone, localtime, locale, deviceOwnerCanGrantSensorsPermissions);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FullyManagedDeviceProvisioningParams[] newArray(int size) {
            return new FullyManagedDeviceProvisioningParams[size];
        }
    };
    private static final String LEAVE_ALL_SYSTEM_APPS_ENABLED_PARAM = "LEAVE_ALL_SYSTEM_APPS_ENABLED";
    private static final String LOCALE_PROVIDED_PARAM = "LOCALE_PROVIDED";
    private static final String TIME_ZONE_PROVIDED_PARAM = "TIME_ZONE_PROVIDED";
    private final ComponentName mDeviceAdminComponentName;
    private final boolean mDeviceOwnerCanGrantSensorsPermissions;
    private final boolean mLeaveAllSystemAppsEnabled;
    private final long mLocalTime;
    private final Locale mLocale;
    private final String mOwnerName;
    private final String mTimeZone;

    private FullyManagedDeviceProvisioningParams(ComponentName deviceAdminComponentName, String ownerName, boolean leaveAllSystemAppsEnabled, String timeZone, long localTime, Locale locale, boolean deviceOwnerCanGrantSensorsPermissions) {
        Objects.requireNonNull(deviceAdminComponentName);
        this.mDeviceAdminComponentName = deviceAdminComponentName;
        Objects.requireNonNull(ownerName);
        this.mOwnerName = ownerName;
        this.mLeaveAllSystemAppsEnabled = leaveAllSystemAppsEnabled;
        this.mTimeZone = timeZone;
        this.mLocalTime = localTime;
        this.mLocale = locale;
        this.mDeviceOwnerCanGrantSensorsPermissions = deviceOwnerCanGrantSensorsPermissions;
    }

    private FullyManagedDeviceProvisioningParams(ComponentName deviceAdminComponentName, String ownerName, boolean leaveAllSystemAppsEnabled, String timeZone, long localTime, String localeStr, boolean deviceOwnerCanGrantSensorsPermissions) {
        this(deviceAdminComponentName, ownerName, leaveAllSystemAppsEnabled, timeZone, localTime, getLocale(localeStr), deviceOwnerCanGrantSensorsPermissions);
    }

    private static Locale getLocale(String localeStr) {
        if (localeStr == null) {
            return null;
        }
        return Locale.forLanguageTag(localeStr);
    }

    public ComponentName getDeviceAdminComponentName() {
        return this.mDeviceAdminComponentName;
    }

    public String getOwnerName() {
        return this.mOwnerName;
    }

    public boolean isLeaveAllSystemAppsEnabled() {
        return this.mLeaveAllSystemAppsEnabled;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public long getLocalTime() {
        return this.mLocalTime;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public boolean canDeviceOwnerGrantSensorsPermissions() {
        return this.mDeviceOwnerCanGrantSensorsPermissions;
    }

    public void logParams(String callerPackage) {
        Objects.requireNonNull(callerPackage);
        logParam(callerPackage, LEAVE_ALL_SYSTEM_APPS_ENABLED_PARAM, this.mLeaveAllSystemAppsEnabled);
        logParam(callerPackage, CAN_DEVICE_OWNER_GRANT_SENSOR_PERMISSIONS_PARAM, this.mDeviceOwnerCanGrantSensorsPermissions);
        boolean z = true;
        logParam(callerPackage, TIME_ZONE_PROVIDED_PARAM, this.mTimeZone != null);
        if (this.mLocale == null) {
            z = false;
        }
        logParam(callerPackage, LOCALE_PROVIDED_PARAM, z);
    }

    private void logParam(String callerPackage, String param, boolean value) {
        DevicePolicyEventLogger.createEvent(197).setStrings(callerPackage).setAdmin(this.mDeviceAdminComponentName).setStrings(param).setBoolean(value).write();
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final ComponentName mDeviceAdminComponentName;
        boolean mDeviceOwnerCanGrantSensorsPermissions = true;
        private boolean mLeaveAllSystemAppsEnabled;
        private long mLocalTime;
        private Locale mLocale;
        private final String mOwnerName;
        private String mTimeZone;

        public Builder(ComponentName deviceAdminComponentName, String ownerName) {
            Objects.requireNonNull(deviceAdminComponentName);
            this.mDeviceAdminComponentName = deviceAdminComponentName;
            Objects.requireNonNull(ownerName);
            this.mOwnerName = ownerName;
        }

        public Builder setLeaveAllSystemAppsEnabled(boolean leaveAllSystemAppsEnabled) {
            this.mLeaveAllSystemAppsEnabled = leaveAllSystemAppsEnabled;
            return this;
        }

        public Builder setTimeZone(String timeZone) {
            this.mTimeZone = timeZone;
            return this;
        }

        public Builder setLocalTime(long localTime) {
            this.mLocalTime = localTime;
            return this;
        }

        public Builder setLocale(Locale locale) {
            this.mLocale = locale;
            return this;
        }

        public Builder setDeviceOwnerCanGrantSensorsPermissions(boolean mayGrant) {
            this.mDeviceOwnerCanGrantSensorsPermissions = mayGrant;
            return this;
        }

        public FullyManagedDeviceProvisioningParams build() {
            return new FullyManagedDeviceProvisioningParams(this.mDeviceAdminComponentName, this.mOwnerName, this.mLeaveAllSystemAppsEnabled, this.mTimeZone, this.mLocalTime, this.mLocale, this.mDeviceOwnerCanGrantSensorsPermissions);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.util.Locale] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "FullyManagedDeviceProvisioningParams{mDeviceAdminComponentName="
            r0.append(r1)
            android.content.ComponentName r1 = r5.mDeviceAdminComponentName
            r0.append(r1)
            java.lang.String r1 = ", mOwnerName="
            r0.append(r1)
            java.lang.String r1 = r5.mOwnerName
            r0.append(r1)
            java.lang.String r1 = ", mLeaveAllSystemAppsEnabled="
            r0.append(r1)
            boolean r1 = r5.mLeaveAllSystemAppsEnabled
            r0.append(r1)
            java.lang.String r1 = ", mTimeZone="
            r0.append(r1)
            java.lang.String r1 = r5.mTimeZone
            java.lang.String r2 = "null"
            if (r1 != 0) goto L_0x0030
            r1 = r2
        L_0x0030:
            r0.append(r1)
            java.lang.String r1 = ", mLocalTime="
            r0.append(r1)
            long r3 = r5.mLocalTime
            r0.append(r3)
            java.lang.String r1 = ", mLocale="
            r0.append(r1)
            java.util.Locale r1 = r5.mLocale
            if (r1 != 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r2 = r1
        L_0x0048:
            r0.append(r2)
            java.lang.String r1 = ", mDeviceOwnerCanGrantSensorsPermissions="
            r0.append(r1)
            boolean r1 = r5.mDeviceOwnerCanGrantSensorsPermissions
            r0.append(r1)
            r1 = 125(0x7d, float:1.75E-43)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.admin.FullyManagedDeviceProvisioningParams.toString():java.lang.String");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedObject(this.mDeviceAdminComponentName, flags);
        dest.writeString(this.mOwnerName);
        dest.writeBoolean(this.mLeaveAllSystemAppsEnabled);
        dest.writeString(this.mTimeZone);
        dest.writeLong(this.mLocalTime);
        Locale locale = this.mLocale;
        dest.writeString(locale == null ? null : locale.toLanguageTag());
        dest.writeBoolean(this.mDeviceOwnerCanGrantSensorsPermissions);
    }
}
