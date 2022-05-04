package vendor.oplus.hardware.rpmh.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class PowerStateSubsystemSleepState {
    public String name = new String();
    public long version = 0;
    public long residencyInMsecSinceBoot = 0;
    public long totalTransitions = 0;
    public long lastEntryTimestampMs = 0;
    public boolean supportedOnlyInSuspend = false;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != PowerStateSubsystemSleepState.class) {
            return false;
        }
        PowerStateSubsystemSleepState other = (PowerStateSubsystemSleepState) otherObject;
        if (HidlSupport.deepEquals(this.name, other.name) && this.version == other.version && this.residencyInMsecSinceBoot == other.residencyInMsecSinceBoot && this.totalTransitions == other.totalTransitions && this.lastEntryTimestampMs == other.lastEntryTimestampMs && this.supportedOnlyInSuspend == other.supportedOnlyInSuspend) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.version))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.residencyInMsecSinceBoot))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.totalTransitions))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.lastEntryTimestampMs))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.supportedOnlyInSuspend))));
    }

    public final String toString() {
        return "{.name = " + this.name + ", .version = " + this.version + ", .residencyInMsecSinceBoot = " + this.residencyInMsecSinceBoot + ", .totalTransitions = " + this.totalTransitions + ", .lastEntryTimestampMs = " + this.lastEntryTimestampMs + ", .supportedOnlyInSuspend = " + this.supportedOnlyInSuspend + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(56L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<PowerStateSubsystemSleepState> readVectorFromParcel(HwParcel parcel) {
        ArrayList<PowerStateSubsystemSleepState> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 56, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            PowerStateSubsystemSleepState _hidl_vec_element = new PowerStateSubsystemSleepState();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 56);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        String string = _hidl_blob.getString(_hidl_offset + 0);
        this.name = string;
        parcel.readEmbeddedBuffer(string.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.version = _hidl_blob.getInt64(16 + _hidl_offset);
        this.residencyInMsecSinceBoot = _hidl_blob.getInt64(24 + _hidl_offset);
        this.totalTransitions = _hidl_blob.getInt64(32 + _hidl_offset);
        this.lastEntryTimestampMs = _hidl_blob.getInt64(40 + _hidl_offset);
        this.supportedOnlyInSuspend = _hidl_blob.getBool(48 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(56);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<PowerStateSubsystemSleepState> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 56);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 56);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.name);
        _hidl_blob.putInt64(16 + _hidl_offset, this.version);
        _hidl_blob.putInt64(24 + _hidl_offset, this.residencyInMsecSinceBoot);
        _hidl_blob.putInt64(32 + _hidl_offset, this.totalTransitions);
        _hidl_blob.putInt64(40 + _hidl_offset, this.lastEntryTimestampMs);
        _hidl_blob.putBool(48 + _hidl_offset, this.supportedOnlyInSuspend);
    }
}
