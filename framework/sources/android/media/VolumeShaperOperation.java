package android.media;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class VolumeShaperOperation implements Parcelable {
    public static final Parcelable.Creator<VolumeShaperOperation> CREATOR = new Parcelable.Creator<VolumeShaperOperation>() { // from class: android.media.VolumeShaperOperation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VolumeShaperOperation createFromParcel(Parcel _aidl_source) {
            VolumeShaperOperation _aidl_out = new VolumeShaperOperation();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VolumeShaperOperation[] newArray(int _aidl_size) {
            return new VolumeShaperOperation[_aidl_size];
        }
    };
    public int flags = 0;
    public int replaceId = 0;
    public float xOffset = 0.0f;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.flags);
        _aidl_parcel.writeInt(this.replaceId);
        _aidl_parcel.writeFloat(this.xOffset);
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
                    this.flags = _aidl_parcel.readInt();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.replaceId = _aidl_parcel.readInt();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.xOffset = _aidl_parcel.readFloat();
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
