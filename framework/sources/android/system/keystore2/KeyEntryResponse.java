package android.system.keystore2;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import android.system.keystore2.IKeystoreSecurityLevel;
/* loaded from: classes3.dex */
public class KeyEntryResponse implements Parcelable {
    public static final Parcelable.Creator<KeyEntryResponse> CREATOR = new Parcelable.Creator<KeyEntryResponse>() { // from class: android.system.keystore2.KeyEntryResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyEntryResponse createFromParcel(Parcel _aidl_source) {
            KeyEntryResponse _aidl_out = new KeyEntryResponse();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyEntryResponse[] newArray(int _aidl_size) {
            return new KeyEntryResponse[_aidl_size];
        }
    };
    public IKeystoreSecurityLevel iSecurityLevel;
    public KeyMetadata metadata;

    @Override // android.os.Parcelable
    public final int getStability() {
        return 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        IKeystoreSecurityLevel iKeystoreSecurityLevel = this.iSecurityLevel;
        _aidl_parcel.writeStrongBinder(iKeystoreSecurityLevel != null ? iKeystoreSecurityLevel.asBinder() : null);
        if (this.metadata != null) {
            _aidl_parcel.writeInt(1);
            this.metadata.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
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
                    this.iSecurityLevel = IKeystoreSecurityLevel.Stub.asInterface(_aidl_parcel.readStrongBinder());
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        if (_aidl_parcel.readInt() != 0) {
                            this.metadata = KeyMetadata.CREATOR.createFromParcel(_aidl_parcel);
                        } else {
                            this.metadata = null;
                        }
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
        int _mask = 0 | describeContents(this.metadata);
        return _mask;
    }

    private int describeContents(Object _v) {
        if (_v != null && (_v instanceof Parcelable)) {
            return ((Parcelable) _v).describeContents();
        }
        return 0;
    }
}
