package android.hardware.security.keymint;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class KeyMintHardwareInfo implements Parcelable {
    public static final Parcelable.Creator<KeyMintHardwareInfo> CREATOR = new Parcelable.Creator<KeyMintHardwareInfo>() { // from class: android.hardware.security.keymint.KeyMintHardwareInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyMintHardwareInfo createFromParcel(Parcel _aidl_source) {
            KeyMintHardwareInfo _aidl_out = new KeyMintHardwareInfo();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyMintHardwareInfo[] newArray(int _aidl_size) {
            return new KeyMintHardwareInfo[_aidl_size];
        }
    };
    public String keyMintAuthorName;
    public String keyMintName;
    public int versionNumber = 0;
    public int securityLevel = 0;
    public boolean timestampTokenRequired = false;

    @Override // android.os.Parcelable
    public final int getStability() {
        return 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.versionNumber);
        _aidl_parcel.writeInt(this.securityLevel);
        _aidl_parcel.writeString(this.keyMintName);
        _aidl_parcel.writeString(this.keyMintAuthorName);
        _aidl_parcel.writeInt(this.timestampTokenRequired ? 1 : 0);
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
                    this.versionNumber = _aidl_parcel.readInt();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.securityLevel = _aidl_parcel.readInt();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.keyMintName = _aidl_parcel.readString();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.keyMintAuthorName = _aidl_parcel.readString();
                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                    this.timestampTokenRequired = _aidl_parcel.readInt() != 0;
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
