package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public class HdmiDeviceInfo implements Parcelable {
    public static final int ADDR_INTERNAL = 0;
    public static final int DEVICE_AUDIO_SYSTEM = 5;
    public static final int DEVICE_INACTIVE = -1;
    public static final int DEVICE_PLAYBACK = 4;
    public static final int DEVICE_PURE_CEC_SWITCH = 6;
    public static final int DEVICE_RECORDER = 1;
    public static final int DEVICE_RESERVED = 2;
    public static final int DEVICE_TUNER = 3;
    public static final int DEVICE_TV = 0;
    public static final int DEVICE_VIDEO_PROCESSOR = 7;
    private static final int HDMI_DEVICE_TYPE_CEC = 0;
    private static final int HDMI_DEVICE_TYPE_HARDWARE = 2;
    private static final int HDMI_DEVICE_TYPE_INACTIVE = 100;
    private static final int HDMI_DEVICE_TYPE_MHL = 1;
    public static final int ID_INVALID = 65535;
    private static final int ID_OFFSET_CEC = 0;
    private static final int ID_OFFSET_HARDWARE = 192;
    private static final int ID_OFFSET_MHL = 128;
    public static final int PATH_INTERNAL = 0;
    public static final int PATH_INVALID = 65535;
    public static final int PORT_INVALID = -1;
    private final int mAdopterId;
    private final int mDeviceId;
    private final int mDevicePowerStatus;
    private final int mDeviceType;
    private final String mDisplayName;
    private final int mHdmiCecVersion;
    private final int mHdmiDeviceType;
    private final int mId;
    private final int mLogicalAddress;
    private final int mPhysicalAddress;
    private final int mPortId;
    private final int mVendorId;
    public static final HdmiDeviceInfo INACTIVE_DEVICE = new HdmiDeviceInfo();
    public static final Parcelable.Creator<HdmiDeviceInfo> CREATOR = new Parcelable.Creator<HdmiDeviceInfo>() { // from class: android.hardware.hdmi.HdmiDeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HdmiDeviceInfo createFromParcel(Parcel source) {
            int hdmiDeviceType = source.readInt();
            int physicalAddress = source.readInt();
            int portId = source.readInt();
            switch (hdmiDeviceType) {
                case 0:
                    int logicalAddress = source.readInt();
                    int deviceType = source.readInt();
                    int vendorId = source.readInt();
                    int powerStatus = source.readInt();
                    String displayName = source.readString();
                    int cecVersion = source.readInt();
                    return new HdmiDeviceInfo(logicalAddress, physicalAddress, portId, deviceType, vendorId, displayName, powerStatus, cecVersion);
                case 1:
                    int deviceId = source.readInt();
                    int adopterId = source.readInt();
                    return new HdmiDeviceInfo(physicalAddress, portId, adopterId, deviceId);
                case 2:
                    return new HdmiDeviceInfo(physicalAddress, portId);
                case 100:
                    return HdmiDeviceInfo.INACTIVE_DEVICE;
                default:
                    return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HdmiDeviceInfo[] newArray(int size) {
            return new HdmiDeviceInfo[size];
        }
    };

    public HdmiDeviceInfo(int logicalAddress, int physicalAddress, int portId, int deviceType, int vendorId, String displayName, int powerStatus, int hdmiCecVersion) {
        this.mHdmiDeviceType = 0;
        this.mPhysicalAddress = physicalAddress;
        this.mPortId = portId;
        this.mId = idForCecDevice(logicalAddress);
        this.mLogicalAddress = logicalAddress;
        this.mDeviceType = deviceType;
        this.mHdmiCecVersion = hdmiCecVersion;
        this.mVendorId = vendorId;
        this.mDevicePowerStatus = powerStatus;
        this.mDisplayName = displayName;
        this.mDeviceId = -1;
        this.mAdopterId = -1;
    }

    public HdmiDeviceInfo(int logicalAddress, int physicalAddress, int portId, int deviceType, int vendorId, String displayName, int powerStatus) {
        this(logicalAddress, physicalAddress, portId, deviceType, vendorId, displayName, powerStatus, 5);
    }

    public HdmiDeviceInfo(int logicalAddress, int physicalAddress, int portId, int deviceType, int vendorId, String displayName) {
        this(logicalAddress, physicalAddress, portId, deviceType, vendorId, displayName, -1, 5);
    }

    public HdmiDeviceInfo(int physicalAddress, int portId) {
        this.mHdmiDeviceType = 2;
        this.mPhysicalAddress = physicalAddress;
        this.mPortId = portId;
        this.mId = idForHardware(portId);
        this.mLogicalAddress = -1;
        this.mDeviceType = 2;
        this.mHdmiCecVersion = 5;
        this.mVendorId = 0;
        this.mDevicePowerStatus = -1;
        this.mDisplayName = "HDMI" + portId;
        this.mDeviceId = -1;
        this.mAdopterId = -1;
    }

    public HdmiDeviceInfo(int physicalAddress, int portId, int adopterId, int deviceId) {
        this.mHdmiDeviceType = 1;
        this.mPhysicalAddress = physicalAddress;
        this.mPortId = portId;
        this.mId = idForMhlDevice(portId);
        this.mLogicalAddress = -1;
        this.mDeviceType = 2;
        this.mHdmiCecVersion = 5;
        this.mVendorId = 0;
        this.mDevicePowerStatus = -1;
        this.mDisplayName = "Mobile";
        this.mDeviceId = adopterId;
        this.mAdopterId = deviceId;
    }

    public HdmiDeviceInfo() {
        this.mHdmiDeviceType = 100;
        this.mPhysicalAddress = 65535;
        this.mId = 65535;
        this.mLogicalAddress = -1;
        this.mDeviceType = -1;
        this.mHdmiCecVersion = 5;
        this.mPortId = -1;
        this.mDevicePowerStatus = -1;
        this.mDisplayName = "Inactive";
        this.mVendorId = 0;
        this.mDeviceId = -1;
        this.mAdopterId = -1;
    }

    public int getId() {
        return this.mId;
    }

    public static int idForCecDevice(int address) {
        return address + 0;
    }

    public static int idForMhlDevice(int portId) {
        return portId + 128;
    }

    public static int idForHardware(int portId) {
        return portId + 192;
    }

    public int getLogicalAddress() {
        return this.mLogicalAddress;
    }

    public int getPhysicalAddress() {
        return this.mPhysicalAddress;
    }

    public int getPortId() {
        return this.mPortId;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public int getCecVersion() {
        return this.mHdmiCecVersion;
    }

    public int getDevicePowerStatus() {
        return this.mDevicePowerStatus;
    }

    public int getDeviceId() {
        return this.mDeviceId;
    }

    public int getAdopterId() {
        return this.mAdopterId;
    }

    public boolean isSourceType() {
        if (!isCecDevice()) {
            return isMhlDevice();
        }
        int i = this.mDeviceType;
        return i == 4 || i == 1 || i == 3;
    }

    public boolean isCecDevice() {
        return this.mHdmiDeviceType == 0;
    }

    public boolean isMhlDevice() {
        return this.mHdmiDeviceType == 1;
    }

    public boolean isInactivated() {
        return this.mHdmiDeviceType == 100;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public int getVendorId() {
        return this.mVendorId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mHdmiDeviceType);
        dest.writeInt(this.mPhysicalAddress);
        dest.writeInt(this.mPortId);
        switch (this.mHdmiDeviceType) {
            case 0:
                dest.writeInt(this.mLogicalAddress);
                dest.writeInt(this.mDeviceType);
                dest.writeInt(this.mVendorId);
                dest.writeInt(this.mDevicePowerStatus);
                dest.writeString(this.mDisplayName);
                dest.writeInt(this.mHdmiCecVersion);
                return;
            case 1:
                dest.writeInt(this.mDeviceId);
                dest.writeInt(this.mAdopterId);
                return;
            default:
                return;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        switch (this.mHdmiDeviceType) {
            case 0:
                s.append("CEC: ");
                s.append("logical_address: ");
                s.append(String.format("0x%02X", Integer.valueOf(this.mLogicalAddress)));
                s.append(" ");
                s.append("device_type: ");
                s.append(this.mDeviceType);
                s.append(" ");
                s.append("cec_version: ");
                s.append(this.mHdmiCecVersion);
                s.append(" ");
                s.append("vendor_id: ");
                s.append(this.mVendorId);
                s.append(" ");
                s.append("display_name: ");
                s.append(this.mDisplayName);
                s.append(" ");
                s.append("power_status: ");
                s.append(this.mDevicePowerStatus);
                s.append(" ");
                break;
            case 1:
                s.append("MHL: ");
                s.append("device_id: ");
                s.append(String.format("0x%04X", Integer.valueOf(this.mDeviceId)));
                s.append(" ");
                s.append("adopter_id: ");
                s.append(String.format("0x%04X", Integer.valueOf(this.mAdopterId)));
                s.append(" ");
                break;
            case 2:
                s.append("Hardware: ");
                break;
            case 100:
                s.append("Inactivated: ");
                break;
            default:
                return "";
        }
        s.append("physical_address: ");
        s.append(String.format("0x%04X", Integer.valueOf(this.mPhysicalAddress)));
        s.append(" ");
        s.append("port_id: ");
        s.append(this.mPortId);
        return s.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HdmiDeviceInfo)) {
            return false;
        }
        HdmiDeviceInfo other = (HdmiDeviceInfo) obj;
        return this.mHdmiDeviceType == other.mHdmiDeviceType && this.mPhysicalAddress == other.mPhysicalAddress && this.mPortId == other.mPortId && this.mLogicalAddress == other.mLogicalAddress && this.mDeviceType == other.mDeviceType && this.mHdmiCecVersion == other.mHdmiCecVersion && this.mVendorId == other.mVendorId && this.mDevicePowerStatus == other.mDevicePowerStatus && this.mDisplayName.equals(other.mDisplayName) && this.mDeviceId == other.mDeviceId && this.mAdopterId == other.mAdopterId;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mHdmiDeviceType), Integer.valueOf(this.mPhysicalAddress), Integer.valueOf(this.mPortId), Integer.valueOf(this.mLogicalAddress), Integer.valueOf(this.mDeviceType), Integer.valueOf(this.mHdmiCecVersion), Integer.valueOf(this.mVendorId), Integer.valueOf(this.mDevicePowerStatus), this.mDisplayName, Integer.valueOf(this.mDeviceId), Integer.valueOf(this.mAdopterId));
    }
}
