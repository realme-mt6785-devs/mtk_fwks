package com.mediatek.bt;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes4.dex */
public final class BluetoothTbsCall implements Parcelable {
    public static final Parcelable.Creator<BluetoothTbsCall> CREATOR = new Parcelable.Creator<BluetoothTbsCall>() { // from class: com.mediatek.bt.BluetoothTbsCall.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothTbsCall createFromParcel(Parcel in) {
            return new BluetoothTbsCall(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothTbsCall[] newArray(int size) {
            return new BluetoothTbsCall[size];
        }
    };
    public static final int FLAG_OUTGOING_CALL = 1;
    public static final int FLAG_WITHHELD_BY_NETWORK = 4;
    public static final int FLAG_WITHHELD_BY_SERVER = 2;
    public static final int STATE_ACTIVE = 3;
    public static final int STATE_ALERTING = 2;
    public static final int STATE_DIALING = 1;
    public static final int STATE_INCOMING = 0;
    public static final int STATE_LOCALLY_AND_REMOTELY_HELD = 6;
    public static final int STATE_LOCALLY_HELD = 4;
    public static final int STATE_REMOTELY_HELD = 5;
    private int mCallFlags;
    private String mFriendlyName;
    private int mState;
    private String mUri;
    private UUID mUuid;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface State {
    }

    public BluetoothTbsCall(BluetoothTbsCall that) {
        this.mUuid = new UUID(that.getUuid().getMostSignificantBits(), that.getUuid().getLeastSignificantBits());
        this.mUri = that.mUri;
        this.mFriendlyName = that.mFriendlyName;
        this.mState = that.mState;
        this.mCallFlags = that.mCallFlags;
    }

    public BluetoothTbsCall(UUID uuid, String uri, String friendlyName, int state, int callFlags) {
        this.mUuid = uuid;
        this.mUri = uri;
        this.mFriendlyName = friendlyName;
        this.mState = state;
        this.mCallFlags = callFlags;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BluetoothTbsCall that = (BluetoothTbsCall) o;
        if (!this.mUuid.equals(that.mUuid) || !this.mUri.equals(that.mUri) || !this.mFriendlyName.equals(that.mFriendlyName) || this.mState != that.mState || this.mCallFlags != that.mCallFlags) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mUuid, this.mUri, this.mFriendlyName, Integer.valueOf(this.mState), Integer.valueOf(this.mCallFlags));
    }

    public String toString() {
        return this.mUuid.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(new ParcelUuid(this.mUuid), 0);
        out.writeString(this.mUri);
        out.writeString(this.mFriendlyName);
        out.writeInt(this.mState);
        out.writeInt(this.mCallFlags);
    }

    private BluetoothTbsCall(Parcel in) {
        this.mUuid = ((ParcelUuid) in.readParcelable(null)).getUuid();
        this.mUri = in.readString();
        this.mFriendlyName = in.readString();
        this.mState = in.readInt();
        this.mCallFlags = in.readInt();
    }

    public UUID getUuid() {
        return this.mUuid;
    }

    public String getUri() {
        return this.mUri;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public int getState() {
        return this.mState;
    }

    public int getCallFlags() {
        return this.mCallFlags;
    }

    public boolean isIncomingCall() {
        return (this.mCallFlags & 1) == 0;
    }
}
