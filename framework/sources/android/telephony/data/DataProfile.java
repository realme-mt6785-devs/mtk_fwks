package android.telephony.data;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.telephony.util.TelephonyUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class DataProfile implements Parcelable {
    public static final Parcelable.Creator<DataProfile> CREATOR = new Parcelable.Creator<DataProfile>() { // from class: android.telephony.data.DataProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataProfile createFromParcel(Parcel source) {
            return new DataProfile(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataProfile[] newArray(int size) {
            return new DataProfile[size];
        }
    };
    public static final int TYPE_3GPP = 1;
    public static final int TYPE_3GPP2 = 2;
    public static final int TYPE_COMMON = 0;
    private final String mApn;
    private final int mAuthType;
    private final int mBearerBitmask;
    private final boolean mEnabled;
    private final int mMaxConnections;
    private final int mMaxConnectionsTime;
    private final int mMtuV4;
    private final int mMtuV6;
    private final String mPassword;
    private final boolean mPersistent;
    private final boolean mPreferred;
    private final int mProfileId;
    private final int mProtocolType;
    private final int mRoamingProtocolType;
    private final int mSupportedApnTypesBitmask;
    private final int mType;
    private final String mUserName;
    private final int mWaitTime;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Type {
    }

    private DataProfile(int profileId, String apn, int protocolType, int authType, String userName, String password, int type, int maxConnectionsTime, int maxConnections, int waitTime, boolean enabled, int supportedApnTypesBitmask, int roamingProtocolType, int bearerBitmask, int mtuV4, int mtuV6, boolean persistent, boolean preferred) {
        int authType2;
        this.mProfileId = profileId;
        this.mApn = apn;
        this.mProtocolType = protocolType;
        if (authType == -1) {
            authType2 = TextUtils.isEmpty(userName) ? 0 : 3;
        } else {
            authType2 = authType;
        }
        this.mAuthType = authType2;
        this.mUserName = userName;
        this.mPassword = password;
        this.mType = type;
        this.mMaxConnectionsTime = maxConnectionsTime;
        this.mMaxConnections = maxConnections;
        this.mWaitTime = waitTime;
        this.mEnabled = enabled;
        this.mSupportedApnTypesBitmask = supportedApnTypesBitmask;
        this.mRoamingProtocolType = roamingProtocolType;
        this.mBearerBitmask = bearerBitmask;
        this.mMtuV4 = mtuV4;
        this.mMtuV6 = mtuV6;
        this.mPersistent = persistent;
        this.mPreferred = preferred;
    }

    private DataProfile(Parcel source) {
        this.mProfileId = source.readInt();
        this.mApn = source.readString();
        this.mProtocolType = source.readInt();
        this.mAuthType = source.readInt();
        this.mUserName = source.readString();
        this.mPassword = source.readString();
        this.mType = source.readInt();
        this.mMaxConnectionsTime = source.readInt();
        this.mMaxConnections = source.readInt();
        this.mWaitTime = source.readInt();
        this.mEnabled = source.readBoolean();
        this.mSupportedApnTypesBitmask = source.readInt();
        this.mRoamingProtocolType = source.readInt();
        this.mBearerBitmask = source.readInt();
        this.mMtuV4 = source.readInt();
        this.mMtuV6 = source.readInt();
        this.mPersistent = source.readBoolean();
        this.mPreferred = source.readBoolean();
    }

    public int getProfileId() {
        return this.mProfileId;
    }

    public String getApn() {
        return this.mApn;
    }

    public int getProtocolType() {
        return this.mProtocolType;
    }

    public int getAuthType() {
        return this.mAuthType;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public int getType() {
        return this.mType;
    }

    public int getMaxConnectionsTime() {
        return this.mMaxConnectionsTime;
    }

    public int getMaxConnections() {
        return this.mMaxConnections;
    }

    public int getWaitTime() {
        return this.mWaitTime;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public int getSupportedApnTypesBitmask() {
        return this.mSupportedApnTypesBitmask;
    }

    public int getRoamingProtocolType() {
        return this.mRoamingProtocolType;
    }

    public int getBearerBitmask() {
        return this.mBearerBitmask;
    }

    @Deprecated
    public int getMtu() {
        return this.mMtuV4;
    }

    public int getMtuV4() {
        return this.mMtuV4;
    }

    public int getMtuV6() {
        return this.mMtuV6;
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    public boolean isPreferred() {
        return this.mPreferred;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("DataProfile=");
        sb.append(this.mProfileId);
        sb.append("/");
        sb.append(this.mProtocolType);
        sb.append("/");
        sb.append(this.mAuthType);
        sb.append("/");
        if (TelephonyUtils.IS_USER) {
            str = "***/***/***";
        } else {
            str = this.mApn + "/" + this.mUserName + "/" + this.mPassword;
        }
        sb.append(str);
        sb.append("/");
        sb.append(this.mType);
        sb.append("/");
        sb.append(this.mMaxConnectionsTime);
        sb.append("/");
        sb.append(this.mMaxConnections);
        sb.append("/");
        sb.append(this.mWaitTime);
        sb.append("/");
        sb.append(this.mEnabled);
        sb.append("/");
        sb.append(this.mSupportedApnTypesBitmask);
        sb.append("/");
        sb.append(this.mRoamingProtocolType);
        sb.append("/");
        sb.append(this.mBearerBitmask);
        sb.append("/");
        sb.append(this.mMtuV4);
        sb.append("/");
        sb.append(this.mMtuV6);
        sb.append("/");
        sb.append(this.mPersistent);
        sb.append("/");
        sb.append(this.mPreferred);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mProfileId);
        dest.writeString(this.mApn);
        dest.writeInt(this.mProtocolType);
        dest.writeInt(this.mAuthType);
        dest.writeString(this.mUserName);
        dest.writeString(this.mPassword);
        dest.writeInt(this.mType);
        dest.writeInt(this.mMaxConnectionsTime);
        dest.writeInt(this.mMaxConnections);
        dest.writeInt(this.mWaitTime);
        dest.writeBoolean(this.mEnabled);
        dest.writeInt(this.mSupportedApnTypesBitmask);
        dest.writeInt(this.mRoamingProtocolType);
        dest.writeInt(this.mBearerBitmask);
        dest.writeInt(this.mMtuV4);
        dest.writeInt(this.mMtuV6);
        dest.writeBoolean(this.mPersistent);
        dest.writeBoolean(this.mPreferred);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataProfile that = (DataProfile) o;
        if (this.mProfileId == that.mProfileId && this.mProtocolType == that.mProtocolType && this.mAuthType == that.mAuthType && this.mType == that.mType && this.mMaxConnectionsTime == that.mMaxConnectionsTime && this.mMaxConnections == that.mMaxConnections && this.mWaitTime == that.mWaitTime && this.mEnabled == that.mEnabled && this.mSupportedApnTypesBitmask == that.mSupportedApnTypesBitmask && this.mRoamingProtocolType == that.mRoamingProtocolType && this.mBearerBitmask == that.mBearerBitmask && this.mMtuV4 == that.mMtuV4 && this.mMtuV6 == that.mMtuV6 && this.mPersistent == that.mPersistent && this.mPreferred == that.mPreferred && Objects.equals(this.mApn, that.mApn) && Objects.equals(this.mUserName, that.mUserName) && Objects.equals(this.mPassword, that.mPassword)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mProfileId), this.mApn, Integer.valueOf(this.mProtocolType), Integer.valueOf(this.mAuthType), this.mUserName, this.mPassword, Integer.valueOf(this.mType), Integer.valueOf(this.mMaxConnectionsTime), Integer.valueOf(this.mMaxConnections), Integer.valueOf(this.mWaitTime), Boolean.valueOf(this.mEnabled), Integer.valueOf(this.mSupportedApnTypesBitmask), Integer.valueOf(this.mRoamingProtocolType), Integer.valueOf(this.mBearerBitmask), Integer.valueOf(this.mMtuV4), Integer.valueOf(this.mMtuV6), Boolean.valueOf(this.mPersistent), Boolean.valueOf(this.mPreferred));
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private String mApn;
        private int mAuthType;
        private int mBearerBitmask;
        private boolean mEnabled;
        private int mMaxConnections;
        private int mMaxConnectionsTime;
        private int mMtuV4;
        private int mMtuV6;
        private String mPassword;
        private boolean mPersistent;
        private boolean mPreferred;
        private int mProfileId;
        private int mProtocolType;
        private int mRoamingProtocolType;
        private int mSupportedApnTypesBitmask;
        private int mType;
        private String mUserName;
        private int mWaitTime;

        public Builder setProfileId(int profileId) {
            this.mProfileId = profileId;
            return this;
        }

        public Builder setApn(String apn) {
            this.mApn = apn;
            return this;
        }

        public Builder setProtocolType(int protocolType) {
            this.mProtocolType = protocolType;
            return this;
        }

        public Builder setAuthType(int authType) {
            this.mAuthType = authType;
            return this;
        }

        public Builder setUserName(String userName) {
            this.mUserName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            this.mPassword = password;
            return this;
        }

        public Builder setType(int type) {
            this.mType = type;
            return this;
        }

        public Builder setMaxConnectionsTime(int maxConnectionsTime) {
            this.mMaxConnectionsTime = maxConnectionsTime;
            return this;
        }

        public Builder setMaxConnections(int maxConnections) {
            this.mMaxConnections = maxConnections;
            return this;
        }

        public Builder setWaitTime(int waitTime) {
            this.mWaitTime = waitTime;
            return this;
        }

        public Builder enable(boolean isEnabled) {
            this.mEnabled = isEnabled;
            return this;
        }

        public Builder setSupportedApnTypesBitmask(int supportedApnTypesBitmask) {
            this.mSupportedApnTypesBitmask = supportedApnTypesBitmask;
            return this;
        }

        public Builder setRoamingProtocolType(int protocolType) {
            this.mRoamingProtocolType = protocolType;
            return this;
        }

        public Builder setBearerBitmask(int bearerBitmask) {
            this.mBearerBitmask = bearerBitmask;
            return this;
        }

        public Builder setMtu(int mtu) {
            this.mMtuV6 = mtu;
            this.mMtuV4 = mtu;
            return this;
        }

        public Builder setMtuV4(int mtu) {
            this.mMtuV4 = mtu;
            return this;
        }

        public Builder setMtuV6(int mtu) {
            this.mMtuV6 = mtu;
            return this;
        }

        public Builder setPreferred(boolean isPreferred) {
            this.mPreferred = isPreferred;
            return this;
        }

        public Builder setPersistent(boolean isPersistent) {
            this.mPersistent = isPersistent;
            return this;
        }

        public DataProfile build() {
            return new DataProfile(this.mProfileId, this.mApn, this.mProtocolType, this.mAuthType, this.mUserName, this.mPassword, this.mType, this.mMaxConnectionsTime, this.mMaxConnections, this.mWaitTime, this.mEnabled, this.mSupportedApnTypesBitmask, this.mRoamingProtocolType, this.mBearerBitmask, this.mMtuV4, this.mMtuV6, this.mPersistent, this.mPreferred);
        }
    }
}
