package android.os.incremental;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class PerUidReadTimeouts implements Parcelable {
    public static final Parcelable.Creator<PerUidReadTimeouts> CREATOR = new Parcelable.Creator<PerUidReadTimeouts>() { // from class: android.os.incremental.PerUidReadTimeouts.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PerUidReadTimeouts createFromParcel(Parcel _aidl_source) {
            PerUidReadTimeouts _aidl_out = new PerUidReadTimeouts();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PerUidReadTimeouts[] newArray(int _aidl_size) {
            return new PerUidReadTimeouts[_aidl_size];
        }
    };
    public int uid = 0;
    public long minTimeUs = 0;
    public long minPendingTimeUs = 0;
    public long maxPendingTimeUs = 0;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.uid);
        _aidl_parcel.writeLong(this.minTimeUs);
        _aidl_parcel.writeLong(this.minPendingTimeUs);
        _aidl_parcel.writeLong(this.maxPendingTimeUs);
        int _aidl_end_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.setDataPosition(_aidl_start_pos);
        _aidl_parcel.writeInt(_aidl_end_pos - _aidl_start_pos);
        _aidl_parcel.setDataPosition(_aidl_end_pos);
    }

    public final void readFromParcel(Parcel _aidl_parcel) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        int _aidl_parcelable_size = _aidl_parcel.readInt();
        if (_aidl_parcelable_size >= 0) {
            try {
                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                    this.uid = _aidl_parcel.readInt();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.minTimeUs = _aidl_parcel.readLong();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.minPendingTimeUs = _aidl_parcel.readLong();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.maxPendingTimeUs = _aidl_parcel.readLong();
                                if (_aidl_start_pos <= Integer.MAX_VALUE - _aidl_parcelable_size) {
                                    _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
                                    return;
                                }
                                throw new BadParcelableException("Overflow in the size of parcelable");
                            } else if (_aidl_start_pos <= Integer.MAX_VALUE - _aidl_parcelable_size) {
                                _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
                            } else {
                                throw new BadParcelableException("Overflow in the size of parcelable");
                            }
                        } else if (_aidl_start_pos <= Integer.MAX_VALUE - _aidl_parcelable_size) {
                            _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
                        } else {
                            throw new BadParcelableException("Overflow in the size of parcelable");
                        }
                    } else if (_aidl_start_pos <= Integer.MAX_VALUE - _aidl_parcelable_size) {
                        _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
                    } else {
                        throw new BadParcelableException("Overflow in the size of parcelable");
                    }
                } else if (_aidl_start_pos <= Integer.MAX_VALUE - _aidl_parcelable_size) {
                    _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
                } else {
                    throw new BadParcelableException("Overflow in the size of parcelable");
                }
            } catch (Throwable th) {
                if (_aidl_start_pos > Integer.MAX_VALUE - _aidl_parcelable_size) {
                    throw new BadParcelableException("Overflow in the size of parcelable");
                }
                _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
                throw th;
            }
        } else if (_aidl_start_pos <= Integer.MAX_VALUE - _aidl_parcelable_size) {
            _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
        } else {
            throw new BadParcelableException("Overflow in the size of parcelable");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
