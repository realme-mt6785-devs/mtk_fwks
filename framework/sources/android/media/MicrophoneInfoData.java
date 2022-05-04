package android.media;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class MicrophoneInfoData implements Parcelable {
    public static final Parcelable.Creator<MicrophoneInfoData> CREATOR = new Parcelable.Creator<MicrophoneInfoData>() { // from class: android.media.MicrophoneInfoData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MicrophoneInfoData createFromParcel(Parcel _aidl_source) {
            MicrophoneInfoData _aidl_out = new MicrophoneInfoData();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MicrophoneInfoData[] newArray(int _aidl_size) {
            return new MicrophoneInfoData[_aidl_size];
        }
    };
    public String address;
    public int[] channelMapping;
    public String deviceId;
    public float[] frequencies;
    public float[] frequencyResponses;
    public float[] geometricLocation;
    public float[] orientation;
    public int portId = 0;
    public int type = 0;
    public int deviceLocation = 0;
    public int deviceGroup = 0;
    public int indexInTheGroup = 0;
    public float sensitivity = 0.0f;
    public float maxSpl = 0.0f;
    public float minSpl = 0.0f;
    public int directionality = 0;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeString(this.deviceId);
        _aidl_parcel.writeInt(this.portId);
        _aidl_parcel.writeInt(this.type);
        _aidl_parcel.writeString(this.address);
        _aidl_parcel.writeInt(this.deviceLocation);
        _aidl_parcel.writeInt(this.deviceGroup);
        _aidl_parcel.writeInt(this.indexInTheGroup);
        _aidl_parcel.writeFloatArray(this.geometricLocation);
        _aidl_parcel.writeFloatArray(this.orientation);
        _aidl_parcel.writeFloatArray(this.frequencies);
        _aidl_parcel.writeFloatArray(this.frequencyResponses);
        _aidl_parcel.writeIntArray(this.channelMapping);
        _aidl_parcel.writeFloat(this.sensitivity);
        _aidl_parcel.writeFloat(this.maxSpl);
        _aidl_parcel.writeFloat(this.minSpl);
        _aidl_parcel.writeInt(this.directionality);
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
                    this.deviceId = _aidl_parcel.readString();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.portId = _aidl_parcel.readInt();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.type = _aidl_parcel.readInt();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.address = _aidl_parcel.readString();
                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                    this.deviceLocation = _aidl_parcel.readInt();
                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                        this.deviceGroup = _aidl_parcel.readInt();
                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                            this.indexInTheGroup = _aidl_parcel.readInt();
                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                this.geometricLocation = _aidl_parcel.createFloatArray();
                                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                    this.orientation = _aidl_parcel.createFloatArray();
                                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                        this.frequencies = _aidl_parcel.createFloatArray();
                                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                            this.frequencyResponses = _aidl_parcel.createFloatArray();
                                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                                this.channelMapping = _aidl_parcel.createIntArray();
                                                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                                    this.sensitivity = _aidl_parcel.readFloat();
                                                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                                        this.maxSpl = _aidl_parcel.readFloat();
                                                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                                            this.minSpl = _aidl_parcel.readFloat();
                                                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                                                this.directionality = _aidl_parcel.readInt();
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
