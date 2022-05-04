package android.apex;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class ApexSessionInfo implements Parcelable {
    public static final Parcelable.Creator<ApexSessionInfo> CREATOR = new Parcelable.Creator<ApexSessionInfo>() { // from class: android.apex.ApexSessionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexSessionInfo createFromParcel(Parcel _aidl_source) {
            ApexSessionInfo _aidl_out = new ApexSessionInfo();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexSessionInfo[] newArray(int _aidl_size) {
            return new ApexSessionInfo[_aidl_size];
        }
    };
    public String crashingNativeProcess;
    public String errorMessage;
    public int sessionId = 0;
    public boolean isUnknown = false;
    public boolean isVerified = false;
    public boolean isStaged = false;
    public boolean isActivated = false;
    public boolean isRevertInProgress = false;
    public boolean isActivationFailed = false;
    public boolean isSuccess = false;
    public boolean isReverted = false;
    public boolean isRevertFailed = false;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.sessionId);
        _aidl_parcel.writeInt(this.isUnknown ? 1 : 0);
        _aidl_parcel.writeInt(this.isVerified ? 1 : 0);
        _aidl_parcel.writeInt(this.isStaged ? 1 : 0);
        _aidl_parcel.writeInt(this.isActivated ? 1 : 0);
        _aidl_parcel.writeInt(this.isRevertInProgress ? 1 : 0);
        _aidl_parcel.writeInt(this.isActivationFailed ? 1 : 0);
        _aidl_parcel.writeInt(this.isSuccess ? 1 : 0);
        _aidl_parcel.writeInt(this.isReverted ? 1 : 0);
        _aidl_parcel.writeInt(this.isRevertFailed ? 1 : 0);
        _aidl_parcel.writeString(this.crashingNativeProcess);
        _aidl_parcel.writeString(this.errorMessage);
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
                    this.sessionId = _aidl_parcel.readInt();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        boolean z = true;
                        this.isUnknown = _aidl_parcel.readInt() != 0;
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.isVerified = _aidl_parcel.readInt() != 0;
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.isStaged = _aidl_parcel.readInt() != 0;
                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                    this.isActivated = _aidl_parcel.readInt() != 0;
                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                        this.isRevertInProgress = _aidl_parcel.readInt() != 0;
                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                            this.isActivationFailed = _aidl_parcel.readInt() != 0;
                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                this.isSuccess = _aidl_parcel.readInt() != 0;
                                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                    this.isReverted = _aidl_parcel.readInt() != 0;
                                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                        if (_aidl_parcel.readInt() == 0) {
                                                            z = false;
                                                        }
                                                        this.isRevertFailed = z;
                                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                            this.crashingNativeProcess = _aidl_parcel.readString();
                                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                                this.errorMessage = _aidl_parcel.readString();
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
