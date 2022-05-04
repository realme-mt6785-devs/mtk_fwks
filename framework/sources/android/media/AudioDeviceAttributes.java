package android.media;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public final class AudioDeviceAttributes implements Parcelable {
    public static final Parcelable.Creator<AudioDeviceAttributes> CREATOR = new Parcelable.Creator<AudioDeviceAttributes>() { // from class: android.media.AudioDeviceAttributes.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioDeviceAttributes createFromParcel(Parcel p) {
            return new AudioDeviceAttributes(p);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioDeviceAttributes[] newArray(int size) {
            return new AudioDeviceAttributes[size];
        }
    };
    public static final int ROLE_INPUT = 1;
    public static final int ROLE_OUTPUT = 2;
    private final String mAddress;
    private final int mNativeType;
    private final int mRole;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Role {
    }

    @SystemApi
    public AudioDeviceAttributes(AudioDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        this.mRole = deviceInfo.isSink() ? 2 : 1;
        this.mType = deviceInfo.getType();
        this.mAddress = deviceInfo.getAddress();
        this.mNativeType = deviceInfo.getInternalType();
    }

    @SystemApi
    public AudioDeviceAttributes(int role, int type, String address) {
        Objects.requireNonNull(address);
        if (role == 2 || role == 1) {
            if (role == 2) {
                AudioDeviceInfo.enforceValidAudioDeviceTypeOut(type);
                this.mNativeType = AudioDeviceInfo.convertDeviceTypeToInternalDevice(type);
            } else if (role == 1) {
                AudioDeviceInfo.enforceValidAudioDeviceTypeIn(type);
                this.mNativeType = AudioDeviceInfo.convertDeviceTypeToInternalInputDevice(type);
            } else {
                this.mNativeType = 0;
            }
            this.mRole = role;
            this.mType = type;
            this.mAddress = address;
            return;
        }
        throw new IllegalArgumentException("Invalid role " + role);
    }

    public AudioDeviceAttributes(int nativeType, String address) {
        this.mRole = (Integer.MIN_VALUE & nativeType) != 0 ? 1 : 2;
        this.mType = AudioDeviceInfo.convertInternalDeviceToDeviceType(nativeType);
        this.mAddress = address;
        this.mNativeType = nativeType;
    }

    @SystemApi
    public int getRole() {
        return this.mRole;
    }

    @SystemApi
    public int getType() {
        return this.mType;
    }

    @SystemApi
    public String getAddress() {
        return this.mAddress;
    }

    public int getInternalType() {
        return this.mNativeType;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mRole), Integer.valueOf(this.mType), this.mAddress);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AudioDeviceAttributes that = (AudioDeviceAttributes) o;
        if (this.mRole == that.mRole && this.mType == that.mType && this.mAddress.equals(that.mAddress)) {
            return true;
        }
        return false;
    }

    public static String roleToString(int role) {
        return role == 2 ? "output" : "input";
    }

    private String getAnonymizedAddress(String address) {
        if (address == null || address.length() <= 8) {
            return address;
        }
        return "XX:XX:XX" + address.substring(8);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AudioDeviceAttributes: role:");
        sb.append(roleToString(this.mRole));
        sb.append(" type:");
        sb.append(this.mRole == 2 ? AudioSystem.getOutputDeviceName(this.mNativeType) : AudioSystem.getInputDeviceName(this.mNativeType));
        sb.append(" addr:");
        sb.append(getAnonymizedAddress(this.mAddress));
        return new String(sb.toString());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mRole);
        dest.writeInt(this.mType);
        dest.writeString(this.mAddress);
        dest.writeInt(this.mNativeType);
    }

    private AudioDeviceAttributes(Parcel in) {
        this.mRole = in.readInt();
        this.mType = in.readInt();
        this.mAddress = in.readString();
        this.mNativeType = in.readInt();
    }
}
