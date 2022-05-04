package android.bluetooth;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class OobData implements Parcelable {
    @SystemApi
    public static final int CLASS_OF_DEVICE_OCTETS = 3;
    @SystemApi
    public static final int CONFIRMATION_OCTETS = 16;
    public static final Parcelable.Creator<OobData> CREATOR = new Parcelable.Creator<OobData>() { // from class: android.bluetooth.OobData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OobData createFromParcel(Parcel in) {
            return new OobData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OobData[] newArray(int size) {
            return new OobData[size];
        }
    };
    @SystemApi
    public static final int DEVICE_ADDRESS_OCTETS = 7;
    @SystemApi
    public static final int LE_APPEARANCE_OCTETS = 2;
    @SystemApi
    public static final int LE_DEVICE_FLAG_OCTETS = 1;
    @SystemApi
    public static final int LE_DEVICE_ROLE_BOTH_PREFER_CENTRAL = 3;
    @SystemApi
    public static final int LE_DEVICE_ROLE_BOTH_PREFER_PERIPHERAL = 2;
    @SystemApi
    public static final int LE_DEVICE_ROLE_CENTRAL_ONLY = 1;
    @SystemApi
    public static final int LE_DEVICE_ROLE_OCTETS = 1;
    @SystemApi
    public static final int LE_DEVICE_ROLE_PERIPHERAL_ONLY = 0;
    @SystemApi
    public static final int LE_FLAG_BREDR_NOT_SUPPORTED = 2;
    @SystemApi
    public static final int LE_FLAG_GENERAL_DISCOVERY_MODE = 1;
    @SystemApi
    public static final int LE_FLAG_LIMITED_DISCOVERY_MODE = 0;
    @SystemApi
    public static final int LE_FLAG_SIMULTANEOUS_CONTROLLER = 3;
    @SystemApi
    public static final int LE_FLAG_SIMULTANEOUS_HOST = 4;
    @SystemApi
    public static final int LE_TK_OCTETS = 16;
    @SystemApi
    public static final int OOB_LENGTH_OCTETS = 2;
    @SystemApi
    public static final int RANDOMIZER_OCTETS = 16;
    private static final String TAG = "OobData";
    private byte[] mClassOfDevice;
    private final byte[] mClassicLength;
    private final byte[] mConfirmationHash;
    private final byte[] mDeviceAddressWithType;
    private byte[] mDeviceName;
    private byte[] mLeAppearance;
    private final int mLeDeviceRole;
    private int mLeFlags;
    private byte[] mLeTemporaryKey;
    private byte[] mRandomizerHash;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface LeFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface LeRole {
    }

    @SystemApi
    /* loaded from: classes.dex */
    public static final class LeBuilder {
        private byte[] mConfirmationHash;
        private final byte[] mDeviceAddressWithType;
        private final int mLeDeviceRole;
        private byte[] mRandomizerHash = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        private byte[] mDeviceName = null;
        private byte[] mLeTemporaryKey = null;
        private byte[] mLeAppearance = null;
        private int mLeFlags = 1;

        @SystemApi
        public LeBuilder setDeviceName(byte[] deviceName) {
            Objects.requireNonNull(deviceName);
            this.mDeviceName = deviceName;
            return this;
        }

        @SystemApi
        public LeBuilder(byte[] confirmationHash, byte[] deviceAddressWithType, int leDeviceRole) {
            this.mConfirmationHash = null;
            Objects.requireNonNull(confirmationHash);
            Objects.requireNonNull(deviceAddressWithType);
            if (confirmationHash.length == 16) {
                this.mConfirmationHash = confirmationHash;
                if (deviceAddressWithType.length == 7) {
                    this.mDeviceAddressWithType = deviceAddressWithType;
                    if (leDeviceRole < 0 || leDeviceRole > 3) {
                        throw new IllegalArgumentException("leDeviceRole must be a valid value.");
                    }
                    this.mLeDeviceRole = leDeviceRole;
                    return;
                }
                throw new IllegalArgumentException("confirmationHash must be 7 octets in length.");
            }
            throw new IllegalArgumentException("confirmationHash must be 16 octets in length.");
        }

        @SystemApi
        public LeBuilder setLeTemporaryKey(byte[] leTemporaryKey) {
            Objects.requireNonNull(leTemporaryKey);
            if (leTemporaryKey.length == 16) {
                this.mLeTemporaryKey = leTemporaryKey;
                return this;
            }
            throw new IllegalArgumentException("leTemporaryKey must be 16 octets in length.");
        }

        @SystemApi
        public LeBuilder setRandomizerHash(byte[] randomizerHash) {
            Objects.requireNonNull(randomizerHash);
            if (randomizerHash.length == 16) {
                this.mRandomizerHash = randomizerHash;
                return this;
            }
            throw new IllegalArgumentException("randomizerHash must be 16 octets in length.");
        }

        @SystemApi
        public LeBuilder setLeFlags(int leFlags) {
            if (leFlags < 0 || leFlags > 4) {
                throw new IllegalArgumentException("leFlags must be a valid value.");
            }
            this.mLeFlags = leFlags;
            return this;
        }

        @SystemApi
        public OobData build() {
            OobData oob = new OobData(this.mDeviceAddressWithType, this.mLeDeviceRole, this.mConfirmationHash);
            byte[] bArr = this.mLeTemporaryKey;
            if (bArr == null) {
                bArr = oob.mLeTemporaryKey;
            }
            oob.mLeTemporaryKey = bArr;
            byte[] bArr2 = this.mLeAppearance;
            if (bArr2 == null) {
                bArr2 = oob.mLeAppearance;
            }
            oob.mLeAppearance = bArr2;
            int i = this.mLeFlags;
            if (i == 15) {
                i = oob.mLeFlags;
            }
            oob.mLeFlags = i;
            byte[] bArr3 = this.mDeviceName;
            if (bArr3 == null) {
                bArr3 = oob.mDeviceName;
            }
            oob.mDeviceName = bArr3;
            oob.mRandomizerHash = this.mRandomizerHash;
            return oob;
        }
    }

    @SystemApi
    /* loaded from: classes.dex */
    public static final class ClassicBuilder {
        private final byte[] mClassicLength;
        private byte[] mConfirmationHash;
        private final byte[] mDeviceAddressWithType;
        private byte[] mRandomizerHash = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        private byte[] mDeviceName = null;
        private byte[] mClassOfDevice = null;

        @SystemApi
        public ClassicBuilder(byte[] confirmationHash, byte[] classicLength, byte[] deviceAddressWithType) {
            this.mConfirmationHash = null;
            Objects.requireNonNull(confirmationHash);
            Objects.requireNonNull(classicLength);
            Objects.requireNonNull(deviceAddressWithType);
            if (confirmationHash.length == 16) {
                this.mConfirmationHash = confirmationHash;
                if (classicLength.length == 2) {
                    this.mClassicLength = classicLength;
                    if (deviceAddressWithType.length == 7) {
                        this.mDeviceAddressWithType = deviceAddressWithType;
                        return;
                    }
                    throw new IllegalArgumentException("deviceAddressWithType must be 7 octets in length.");
                }
                throw new IllegalArgumentException("classicLength must be 2 octets in length.");
            }
            throw new IllegalArgumentException("confirmationHash must be 16 octets in length.");
        }

        @SystemApi
        public ClassicBuilder setRandomizerHash(byte[] randomizerHash) {
            Objects.requireNonNull(randomizerHash);
            if (randomizerHash.length == 16) {
                this.mRandomizerHash = randomizerHash;
                return this;
            }
            throw new IllegalArgumentException("randomizerHash must be 16 octets in length.");
        }

        @SystemApi
        public ClassicBuilder setDeviceName(byte[] deviceName) {
            Objects.requireNonNull(deviceName);
            this.mDeviceName = deviceName;
            return this;
        }

        @SystemApi
        public ClassicBuilder setClassOfDevice(byte[] classOfDevice) {
            Objects.requireNonNull(classOfDevice);
            if (classOfDevice.length == 3) {
                this.mClassOfDevice = classOfDevice;
                return this;
            }
            throw new IllegalArgumentException("classOfDevice must be 3 octets in length.");
        }

        @SystemApi
        public OobData build() {
            OobData oob = new OobData(this.mClassicLength, this.mDeviceAddressWithType, this.mConfirmationHash);
            byte[] bArr = this.mDeviceName;
            if (bArr == null) {
                bArr = oob.mDeviceName;
            }
            oob.mDeviceName = bArr;
            byte[] bArr2 = this.mClassOfDevice;
            if (bArr2 == null) {
                bArr2 = oob.mClassOfDevice;
            }
            oob.mClassOfDevice = bArr2;
            oob.mRandomizerHash = this.mRandomizerHash;
            return oob;
        }
    }

    @SystemApi
    public byte[] getDeviceAddressWithType() {
        return this.mDeviceAddressWithType;
    }

    @SystemApi
    public byte[] getConfirmationHash() {
        return this.mConfirmationHash;
    }

    @SystemApi
    public byte[] getRandomizerHash() {
        return this.mRandomizerHash;
    }

    @SystemApi
    public byte[] getDeviceName() {
        return this.mDeviceName;
    }

    @SystemApi
    public byte[] getClassicLength() {
        return this.mClassicLength;
    }

    @SystemApi
    public byte[] getClassOfDevice() {
        return this.mClassOfDevice;
    }

    @SystemApi
    public byte[] getLeTemporaryKey() {
        return this.mLeTemporaryKey;
    }

    @SystemApi
    public byte[] getLeAppearance() {
        return this.mLeAppearance;
    }

    @SystemApi
    public int getLeFlags() {
        return this.mLeFlags;
    }

    @SystemApi
    public int getLeDeviceRole() {
        return this.mLeDeviceRole;
    }

    private OobData(byte[] classicLength, byte[] deviceAddressWithType, byte[] confirmationHash) {
        this.mRandomizerHash = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mDeviceName = new byte[]{66, 108, 117, 101, 116, 111, 111, 116, 104, 32, 68, 101, 118, 105, 99, 101};
        this.mClassOfDevice = new byte[3];
        this.mLeTemporaryKey = new byte[16];
        this.mLeAppearance = new byte[2];
        this.mLeFlags = 0;
        this.mClassicLength = classicLength;
        this.mDeviceAddressWithType = deviceAddressWithType;
        this.mConfirmationHash = confirmationHash;
        this.mLeDeviceRole = -1;
    }

    private OobData(byte[] deviceAddressWithType, int leDeviceRole, byte[] confirmationHash) {
        this.mRandomizerHash = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mDeviceName = new byte[]{66, 108, 117, 101, 116, 111, 111, 116, 104, 32, 68, 101, 118, 105, 99, 101};
        this.mClassOfDevice = new byte[3];
        this.mLeTemporaryKey = new byte[16];
        this.mLeAppearance = new byte[2];
        this.mLeFlags = 0;
        this.mDeviceAddressWithType = deviceAddressWithType;
        this.mLeDeviceRole = leDeviceRole;
        this.mConfirmationHash = confirmationHash;
        this.mClassicLength = new byte[2];
    }

    private OobData(Parcel in) {
        this.mRandomizerHash = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mDeviceName = new byte[]{66, 108, 117, 101, 116, 111, 111, 116, 104, 32, 68, 101, 118, 105, 99, 101};
        this.mClassOfDevice = new byte[3];
        this.mLeTemporaryKey = new byte[16];
        this.mLeAppearance = new byte[2];
        this.mLeFlags = 0;
        this.mDeviceAddressWithType = in.createByteArray();
        this.mConfirmationHash = in.createByteArray();
        this.mRandomizerHash = in.createByteArray();
        this.mDeviceName = in.createByteArray();
        this.mClassicLength = in.createByteArray();
        this.mClassOfDevice = in.createByteArray();
        this.mLeDeviceRole = in.readInt();
        this.mLeTemporaryKey = in.createByteArray();
        this.mLeAppearance = in.createByteArray();
        this.mLeFlags = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeByteArray(this.mDeviceAddressWithType);
        out.writeByteArray(this.mConfirmationHash);
        out.writeByteArray(this.mRandomizerHash);
        out.writeByteArray(this.mDeviceName);
        out.writeByteArray(this.mClassicLength);
        out.writeByteArray(this.mClassOfDevice);
        out.writeInt(this.mLeDeviceRole);
        out.writeByteArray(this.mLeTemporaryKey);
        out.writeByteArray(this.mLeAppearance);
        out.writeInt(this.mLeFlags);
    }

    public String toString() {
        return "OobData: \n\tDevice Address With Type: " + toHexString(this.mDeviceAddressWithType) + "\n\tConfirmation: " + toHexString(this.mConfirmationHash) + "\n\tRandomizer: " + toHexString(this.mRandomizerHash) + "\n\tDevice Name: " + toHexString(this.mDeviceName) + "\n\tOobData Length: " + toHexString(this.mClassicLength) + "\n\tClass of Device: " + toHexString(this.mClassOfDevice) + "\n\tLE Device Role: " + toHexString(this.mLeDeviceRole) + "\n\tLE Temporary Key: " + toHexString(this.mLeTemporaryKey) + "\n\tLE Appearance: " + toHexString(this.mLeAppearance) + "\n\tLE Flags: " + toHexString(this.mLeFlags) + "\n\t";
    }

    private String toHexString(int b) {
        return toHexString(new byte[]{(byte) b});
    }

    private String toHexString(byte b) {
        return toHexString(new byte[]{b});
    }

    private String toHexString(byte[] array) {
        StringBuilder builder = new StringBuilder(array.length * 2);
        for (byte b : array) {
            builder.append(String.format("%02x", Byte.valueOf(b)));
        }
        return builder.toString();
    }
}
