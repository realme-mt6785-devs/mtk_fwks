package android.security.identity;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class GetEntriesResultParcel implements Parcelable {
    public static final Parcelable.Creator<GetEntriesResultParcel> CREATOR = new Parcelable.Creator<GetEntriesResultParcel>() { // from class: android.security.identity.GetEntriesResultParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetEntriesResultParcel createFromParcel(Parcel _aidl_source) {
            GetEntriesResultParcel _aidl_out = new GetEntriesResultParcel();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetEntriesResultParcel[] newArray(int _aidl_size) {
            return new GetEntriesResultParcel[_aidl_size];
        }
    };
    public byte[] deviceNameSpaces;
    public byte[] mac;
    public ResultNamespaceParcel[] resultNamespaces;
    public byte[] staticAuthenticationData;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeTypedArray(this.resultNamespaces, 0);
        _aidl_parcel.writeByteArray(this.deviceNameSpaces);
        _aidl_parcel.writeByteArray(this.mac);
        _aidl_parcel.writeByteArray(this.staticAuthenticationData);
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
                    this.resultNamespaces = (ResultNamespaceParcel[]) _aidl_parcel.createTypedArray(ResultNamespaceParcel.CREATOR);
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.deviceNameSpaces = _aidl_parcel.createByteArray();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.mac = _aidl_parcel.createByteArray();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.staticAuthenticationData = _aidl_parcel.createByteArray();
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
        int _mask = 0 | describeContents(this.resultNamespaces);
        return _mask;
    }

    private int describeContents(Object _v) {
        Object[] objArr;
        if (_v == null) {
            return 0;
        }
        Class<?> _clazz = _v.getClass();
        if (_clazz.isArray() && _clazz.getComponentType() == Object.class) {
            int _mask = 0;
            for (Object o : (Object[]) _v) {
                _mask |= describeContents(o);
            }
            return _mask;
        } else if (_v instanceof Parcelable) {
            return ((Parcelable) _v).describeContents();
        } else {
            return 0;
        }
    }
}
