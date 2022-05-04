package android.bluetooth.le;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class AdvertiseData implements Parcelable {
    public static final Parcelable.Creator<AdvertiseData> CREATOR = new Parcelable.Creator<AdvertiseData>() { // from class: android.bluetooth.le.AdvertiseData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AdvertiseData[] newArray(int size) {
            return new AdvertiseData[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AdvertiseData createFromParcel(Parcel in) {
            Builder builder = new Builder();
            ArrayList<ParcelUuid> uuids = in.createTypedArrayList(ParcelUuid.CREATOR);
            Iterator<ParcelUuid> it = uuids.iterator();
            while (it.hasNext()) {
                ParcelUuid uuid = it.next();
                builder.addServiceUuid(uuid);
            }
            ArrayList<ParcelUuid> solicitationUuids = in.createTypedArrayList(ParcelUuid.CREATOR);
            Iterator<ParcelUuid> it2 = solicitationUuids.iterator();
            while (it2.hasNext()) {
                ParcelUuid uuid2 = it2.next();
                builder.addServiceSolicitationUuid(uuid2);
            }
            int manufacturerSize = in.readInt();
            for (int i = 0; i < manufacturerSize; i++) {
                int manufacturerId = in.readInt();
                byte[] manufacturerData = in.createByteArray();
                builder.addManufacturerData(manufacturerId, manufacturerData);
            }
            int serviceDataSize = in.readInt();
            for (int i2 = 0; i2 < serviceDataSize; i2++) {
                ParcelUuid serviceDataUuid = (ParcelUuid) in.readTypedObject(ParcelUuid.CREATOR);
                byte[] serviceData = in.createByteArray();
                builder.addServiceData(serviceDataUuid, serviceData);
            }
            int i3 = in.readByte();
            boolean z = false;
            builder.setIncludeTxPowerLevel(i3 == 1);
            if (in.readByte() == 1) {
                z = true;
            }
            builder.setIncludeDeviceName(z);
            return builder.build();
        }
    };
    private final boolean mIncludeDeviceName;
    private final boolean mIncludeTxPowerLevel;
    private final SparseArray<byte[]> mManufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> mServiceData;
    private final List<ParcelUuid> mServiceSolicitationUuids;
    private final List<ParcelUuid> mServiceUuids;

    private AdvertiseData(List<ParcelUuid> serviceUuids, List<ParcelUuid> serviceSolicitationUuids, SparseArray<byte[]> manufacturerData, Map<ParcelUuid, byte[]> serviceData, boolean includeTxPowerLevel, boolean includeDeviceName) {
        this.mServiceUuids = serviceUuids;
        this.mServiceSolicitationUuids = serviceSolicitationUuids;
        this.mManufacturerSpecificData = manufacturerData;
        this.mServiceData = serviceData;
        this.mIncludeTxPowerLevel = includeTxPowerLevel;
        this.mIncludeDeviceName = includeDeviceName;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.mServiceUuids;
    }

    public List<ParcelUuid> getServiceSolicitationUuids() {
        return this.mServiceSolicitationUuids;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.mManufacturerSpecificData;
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.mServiceData;
    }

    public boolean getIncludeTxPowerLevel() {
        return this.mIncludeTxPowerLevel;
    }

    public boolean getIncludeDeviceName() {
        return this.mIncludeDeviceName;
    }

    public int hashCode() {
        return Objects.hash(this.mServiceUuids, this.mServiceSolicitationUuids, this.mManufacturerSpecificData, this.mServiceData, Boolean.valueOf(this.mIncludeDeviceName), Boolean.valueOf(this.mIncludeTxPowerLevel));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertiseData other = (AdvertiseData) obj;
        if (!Objects.equals(this.mServiceUuids, other.mServiceUuids) || !Objects.equals(this.mServiceSolicitationUuids, other.mServiceSolicitationUuids) || !BluetoothLeUtils.equals(this.mManufacturerSpecificData, other.mManufacturerSpecificData) || !BluetoothLeUtils.equals(this.mServiceData, other.mServiceData) || this.mIncludeDeviceName != other.mIncludeDeviceName || this.mIncludeTxPowerLevel != other.mIncludeTxPowerLevel) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "AdvertiseData [mServiceUuids=" + this.mServiceUuids + ", mServiceSolicitationUuids=" + this.mServiceSolicitationUuids + ", mManufacturerSpecificData=" + BluetoothLeUtils.toString(this.mManufacturerSpecificData) + ", mServiceData=" + BluetoothLeUtils.toString(this.mServiceData) + ", mIncludeTxPowerLevel=" + this.mIncludeTxPowerLevel + ", mIncludeDeviceName=" + this.mIncludeDeviceName + "]";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        List<ParcelUuid> list = this.mServiceUuids;
        dest.writeTypedArray((ParcelUuid[]) list.toArray(new ParcelUuid[list.size()]), flags);
        List<ParcelUuid> list2 = this.mServiceSolicitationUuids;
        dest.writeTypedArray((ParcelUuid[]) list2.toArray(new ParcelUuid[list2.size()]), flags);
        dest.writeInt(this.mManufacturerSpecificData.size());
        for (int i = 0; i < this.mManufacturerSpecificData.size(); i++) {
            dest.writeInt(this.mManufacturerSpecificData.keyAt(i));
            dest.writeByteArray(this.mManufacturerSpecificData.valueAt(i));
        }
        dest.writeInt(this.mServiceData.size());
        for (ParcelUuid uuid : this.mServiceData.keySet()) {
            dest.writeTypedObject(uuid, flags);
            dest.writeByteArray(this.mServiceData.get(uuid));
        }
        dest.writeByte(getIncludeTxPowerLevel() ? (byte) 1 : (byte) 0);
        dest.writeByte(getIncludeDeviceName() ? (byte) 1 : (byte) 0);
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean mIncludeDeviceName;
        private boolean mIncludeTxPowerLevel;
        private List<ParcelUuid> mServiceUuids = new ArrayList();
        private List<ParcelUuid> mServiceSolicitationUuids = new ArrayList();
        private SparseArray<byte[]> mManufacturerSpecificData = new SparseArray<>();
        private Map<ParcelUuid, byte[]> mServiceData = new ArrayMap();

        public Builder addServiceUuid(ParcelUuid serviceUuid) {
            if (serviceUuid != null) {
                this.mServiceUuids.add(serviceUuid);
                return this;
            }
            throw new IllegalArgumentException("serviceUuid is null");
        }

        public Builder addServiceSolicitationUuid(ParcelUuid serviceSolicitationUuid) {
            if (serviceSolicitationUuid != null) {
                this.mServiceSolicitationUuids.add(serviceSolicitationUuid);
                return this;
            }
            throw new IllegalArgumentException("serviceSolicitationUuid is null");
        }

        public Builder addServiceData(ParcelUuid serviceDataUuid, byte[] serviceData) {
            if (serviceDataUuid == null || serviceData == null) {
                throw new IllegalArgumentException("serviceDataUuid or serviceDataUuid is null");
            }
            this.mServiceData.put(serviceDataUuid, serviceData);
            return this;
        }

        public Builder addManufacturerData(int manufacturerId, byte[] manufacturerSpecificData) {
            if (manufacturerId < 0) {
                throw new IllegalArgumentException("invalid manufacturerId - " + manufacturerId);
            } else if (manufacturerSpecificData != null) {
                this.mManufacturerSpecificData.put(manufacturerId, manufacturerSpecificData);
                return this;
            } else {
                throw new IllegalArgumentException("manufacturerSpecificData is null");
            }
        }

        public Builder setIncludeTxPowerLevel(boolean includeTxPowerLevel) {
            this.mIncludeTxPowerLevel = includeTxPowerLevel;
            return this;
        }

        public Builder setIncludeDeviceName(boolean includeDeviceName) {
            this.mIncludeDeviceName = includeDeviceName;
            return this;
        }

        public AdvertiseData build() {
            return new AdvertiseData(this.mServiceUuids, this.mServiceSolicitationUuids, this.mManufacturerSpecificData, this.mServiceData, this.mIncludeTxPowerLevel, this.mIncludeDeviceName);
        }
    }
}
