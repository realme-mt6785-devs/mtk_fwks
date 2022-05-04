package android.bluetooth;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes.dex */
public class BluetoothGattCharacteristic implements Parcelable {
    public static final Parcelable.Creator<BluetoothGattCharacteristic> CREATOR = new Parcelable.Creator<BluetoothGattCharacteristic>() { // from class: android.bluetooth.BluetoothGattCharacteristic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothGattCharacteristic createFromParcel(Parcel in) {
            return new BluetoothGattCharacteristic(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothGattCharacteristic[] newArray(int size) {
            return new BluetoothGattCharacteristic[size];
        }
    };
    public static final int FORMAT_FLOAT = 52;
    public static final int FORMAT_SFLOAT = 50;
    public static final int FORMAT_SINT16 = 34;
    public static final int FORMAT_SINT32 = 36;
    public static final int FORMAT_SINT8 = 33;
    public static final int FORMAT_UINT16 = 18;
    public static final int FORMAT_UINT32 = 20;
    public static final int FORMAT_UINT8 = 17;
    public static final int PERMISSION_READ = 1;
    public static final int PERMISSION_READ_ENCRYPTED = 2;
    public static final int PERMISSION_READ_ENCRYPTED_MITM = 4;
    public static final int PERMISSION_WRITE = 16;
    public static final int PERMISSION_WRITE_ENCRYPTED = 32;
    public static final int PERMISSION_WRITE_ENCRYPTED_MITM = 64;
    public static final int PERMISSION_WRITE_SIGNED = 128;
    public static final int PERMISSION_WRITE_SIGNED_MITM = 256;
    public static final int PROPERTY_BROADCAST = 1;
    public static final int PROPERTY_EXTENDED_PROPS = 128;
    public static final int PROPERTY_INDICATE = 32;
    public static final int PROPERTY_NOTIFY = 16;
    public static final int PROPERTY_READ = 2;
    public static final int PROPERTY_SIGNED_WRITE = 64;
    public static final int PROPERTY_WRITE = 8;
    public static final int PROPERTY_WRITE_NO_RESPONSE = 4;
    public static final int WRITE_TYPE_DEFAULT = 2;
    public static final int WRITE_TYPE_NO_RESPONSE = 1;
    public static final int WRITE_TYPE_SIGNED = 4;
    protected List<BluetoothGattDescriptor> mDescriptors;
    protected int mInstance;
    protected int mKeySize;
    protected int mPermissions;
    protected int mProperties;
    protected BluetoothGattService mService;
    protected UUID mUuid;
    protected byte[] mValue;
    protected int mWriteType;

    public BluetoothGattCharacteristic(UUID uuid, int properties, int permissions) {
        this.mKeySize = 16;
        initCharacteristic(null, uuid, 0, properties, permissions);
    }

    BluetoothGattCharacteristic(BluetoothGattService service, UUID uuid, int instanceId, int properties, int permissions) {
        this.mKeySize = 16;
        initCharacteristic(service, uuid, instanceId, properties, permissions);
    }

    public BluetoothGattCharacteristic(UUID uuid, int instanceId, int properties, int permissions) {
        this.mKeySize = 16;
        initCharacteristic(null, uuid, instanceId, properties, permissions);
    }

    private void initCharacteristic(BluetoothGattService service, UUID uuid, int instanceId, int properties, int permissions) {
        this.mUuid = uuid;
        this.mInstance = instanceId;
        this.mProperties = properties;
        this.mPermissions = permissions;
        this.mService = service;
        this.mValue = null;
        this.mDescriptors = new ArrayList();
        if ((this.mProperties & 4) != 0) {
            this.mWriteType = 1;
        } else {
            this.mWriteType = 2;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(new ParcelUuid(this.mUuid), 0);
        out.writeInt(this.mInstance);
        out.writeInt(this.mProperties);
        out.writeInt(this.mPermissions);
        out.writeInt(this.mKeySize);
        out.writeInt(this.mWriteType);
        out.writeTypedList(this.mDescriptors);
    }

    private BluetoothGattCharacteristic(Parcel in) {
        this.mKeySize = 16;
        this.mUuid = ((ParcelUuid) in.readParcelable(null)).getUuid();
        this.mInstance = in.readInt();
        this.mProperties = in.readInt();
        this.mPermissions = in.readInt();
        this.mKeySize = in.readInt();
        this.mWriteType = in.readInt();
        this.mDescriptors = new ArrayList();
        ArrayList<BluetoothGattDescriptor> descs = in.createTypedArrayList(BluetoothGattDescriptor.CREATOR);
        if (descs != null) {
            Iterator<BluetoothGattDescriptor> it = descs.iterator();
            while (it.hasNext()) {
                BluetoothGattDescriptor desc = it.next();
                desc.setCharacteristic(this);
                this.mDescriptors.add(desc);
            }
        }
    }

    public int getKeySize() {
        return this.mKeySize;
    }

    public boolean addDescriptor(BluetoothGattDescriptor descriptor) {
        this.mDescriptors.add(descriptor);
        descriptor.setCharacteristic(this);
        return true;
    }

    BluetoothGattDescriptor getDescriptor(UUID uuid, int instanceId) {
        for (BluetoothGattDescriptor descriptor : this.mDescriptors) {
            if (descriptor.getUuid().equals(uuid) && descriptor.getInstanceId() == instanceId) {
                return descriptor;
            }
        }
        return null;
    }

    public BluetoothGattService getService() {
        return this.mService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setService(BluetoothGattService service) {
        this.mService = service;
    }

    public UUID getUuid() {
        return this.mUuid;
    }

    public int getInstanceId() {
        return this.mInstance;
    }

    public void setInstanceId(int instanceId) {
        this.mInstance = instanceId;
    }

    public int getProperties() {
        return this.mProperties;
    }

    public int getPermissions() {
        return this.mPermissions;
    }

    public int getWriteType() {
        return this.mWriteType;
    }

    public void setWriteType(int writeType) {
        this.mWriteType = writeType;
    }

    public void setKeySize(int keySize) {
        this.mKeySize = keySize;
    }

    public List<BluetoothGattDescriptor> getDescriptors() {
        return this.mDescriptors;
    }

    public BluetoothGattDescriptor getDescriptor(UUID uuid) {
        for (BluetoothGattDescriptor descriptor : this.mDescriptors) {
            if (descriptor.getUuid().equals(uuid)) {
                return descriptor;
            }
        }
        return null;
    }

    public byte[] getValue() {
        return this.mValue;
    }

    public Integer getIntValue(int formatType, int offset) {
        int typeLen = getTypeLen(formatType) + offset;
        byte[] bArr = this.mValue;
        if (typeLen > bArr.length) {
            return null;
        }
        switch (formatType) {
            case 17:
                return Integer.valueOf(unsignedByteToInt(bArr[offset]));
            case 18:
                return Integer.valueOf(unsignedBytesToInt(bArr[offset], bArr[offset + 1]));
            case 20:
                return Integer.valueOf(unsignedBytesToInt(bArr[offset], bArr[offset + 1], bArr[offset + 2], bArr[offset + 3]));
            case 33:
                return Integer.valueOf(unsignedToSigned(unsignedByteToInt(bArr[offset]), 8));
            case 34:
                return Integer.valueOf(unsignedToSigned(unsignedBytesToInt(bArr[offset], bArr[offset + 1]), 16));
            case 36:
                return Integer.valueOf(unsignedToSigned(unsignedBytesToInt(bArr[offset], bArr[offset + 1], bArr[offset + 2], bArr[offset + 3]), 32));
            default:
                return null;
        }
    }

    public Float getFloatValue(int formatType, int offset) {
        int typeLen = getTypeLen(formatType) + offset;
        byte[] bArr = this.mValue;
        if (typeLen > bArr.length) {
            return null;
        }
        switch (formatType) {
            case 50:
                return Float.valueOf(bytesToFloat(bArr[offset], bArr[offset + 1]));
            case 51:
            default:
                return null;
            case 52:
                return Float.valueOf(bytesToFloat(bArr[offset], bArr[offset + 1], bArr[offset + 2], bArr[offset + 3]));
        }
    }

    public String getStringValue(int offset) {
        byte[] bArr = this.mValue;
        if (bArr == null || offset > bArr.length) {
            return null;
        }
        byte[] strBytes = new byte[bArr.length - offset];
        int i = 0;
        while (true) {
            byte[] bArr2 = this.mValue;
            if (i == bArr2.length - offset) {
                return new String(strBytes);
            }
            strBytes[i] = bArr2[offset + i];
            i++;
        }
    }

    public boolean setValue(byte[] value) {
        this.mValue = value;
        return true;
    }

    public boolean setValue(int value, int formatType, int offset) {
        int len = getTypeLen(formatType) + offset;
        if (this.mValue == null) {
            this.mValue = new byte[len];
        }
        if (len > this.mValue.length) {
            return false;
        }
        switch (formatType) {
            case 17:
                this.mValue[offset] = (byte) (value & 255);
                return true;
            case 18:
                byte[] bArr = this.mValue;
                bArr[offset] = (byte) (value & 255);
                bArr[offset + 1] = (byte) ((value >> 8) & 255);
                return true;
            case 20:
                byte[] bArr2 = this.mValue;
                int offset2 = offset + 1;
                bArr2[offset] = (byte) (value & 255);
                int offset3 = offset2 + 1;
                bArr2[offset2] = (byte) ((value >> 8) & 255);
                bArr2[offset3] = (byte) ((value >> 16) & 255);
                bArr2[offset3 + 1] = (byte) ((value >> 24) & 255);
                return true;
            case 33:
                value = intToSignedBits(value, 8);
                this.mValue[offset] = (byte) (value & 255);
                return true;
            case 34:
                value = intToSignedBits(value, 16);
                byte[] bArr3 = this.mValue;
                bArr3[offset] = (byte) (value & 255);
                bArr3[offset + 1] = (byte) ((value >> 8) & 255);
                return true;
            case 36:
                value = intToSignedBits(value, 32);
                byte[] bArr22 = this.mValue;
                int offset22 = offset + 1;
                bArr22[offset] = (byte) (value & 255);
                int offset32 = offset22 + 1;
                bArr22[offset22] = (byte) ((value >> 8) & 255);
                bArr22[offset32] = (byte) ((value >> 16) & 255);
                bArr22[offset32 + 1] = (byte) ((value >> 24) & 255);
                return true;
            default:
                return false;
        }
    }

    public boolean setValue(int mantissa, int exponent, int formatType, int offset) {
        int len = getTypeLen(formatType) + offset;
        if (this.mValue == null) {
            this.mValue = new byte[len];
        }
        if (len > this.mValue.length) {
            return false;
        }
        switch (formatType) {
            case 50:
                int mantissa2 = intToSignedBits(mantissa, 12);
                int exponent2 = intToSignedBits(exponent, 4);
                byte[] bArr = this.mValue;
                int offset2 = offset + 1;
                bArr[offset] = (byte) (mantissa2 & 255);
                bArr[offset2] = (byte) ((mantissa2 >> 8) & 15);
                bArr[offset2] = (byte) (bArr[offset2] + ((byte) ((exponent2 & 15) << 4)));
                return true;
            case 51:
            default:
                return false;
            case 52:
                int mantissa3 = intToSignedBits(mantissa, 24);
                int exponent3 = intToSignedBits(exponent, 8);
                byte[] bArr2 = this.mValue;
                int offset3 = offset + 1;
                bArr2[offset] = (byte) (mantissa3 & 255);
                int offset4 = offset3 + 1;
                bArr2[offset3] = (byte) ((mantissa3 >> 8) & 255);
                int offset5 = offset4 + 1;
                bArr2[offset4] = (byte) ((mantissa3 >> 16) & 255);
                bArr2[offset5] = (byte) (bArr2[offset5] + ((byte) (exponent3 & 255)));
                return true;
        }
    }

    public boolean setValue(String value) {
        this.mValue = value.getBytes();
        return true;
    }

    private int getTypeLen(int formatType) {
        return formatType & 15;
    }

    private int unsignedByteToInt(byte b) {
        return b & 255;
    }

    private int unsignedBytesToInt(byte b0, byte b1) {
        return unsignedByteToInt(b0) + (unsignedByteToInt(b1) << 8);
    }

    private int unsignedBytesToInt(byte b0, byte b1, byte b2, byte b3) {
        return unsignedByteToInt(b0) + (unsignedByteToInt(b1) << 8) + (unsignedByteToInt(b2) << 16) + (unsignedByteToInt(b3) << 24);
    }

    private float bytesToFloat(byte b0, byte b1) {
        int mantissa = unsignedToSigned(unsignedByteToInt(b0) + ((unsignedByteToInt(b1) & 15) << 8), 12);
        int exponent = unsignedToSigned(unsignedByteToInt(b1) >> 4, 4);
        return (float) (mantissa * Math.pow(10.0d, exponent));
    }

    private float bytesToFloat(byte b0, byte b1, byte b2, byte b3) {
        int mantissa = unsignedToSigned(unsignedByteToInt(b0) + (unsignedByteToInt(b1) << 8) + (unsignedByteToInt(b2) << 16), 24);
        return (float) (mantissa * Math.pow(10.0d, b3));
    }

    private int unsignedToSigned(int unsigned, int size) {
        if (((1 << (size - 1)) & unsigned) != 0) {
            return ((1 << (size - 1)) - (unsigned & ((1 << (size - 1)) - 1))) * (-1);
        }
        return unsigned;
    }

    private int intToSignedBits(int i, int size) {
        if (i < 0) {
            return (1 << (size - 1)) + (i & ((1 << (size - 1)) - 1));
        }
        return i;
    }
}
