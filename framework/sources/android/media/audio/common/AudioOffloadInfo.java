package android.media.audio.common;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class AudioOffloadInfo implements Parcelable {
    public static final Parcelable.Creator<AudioOffloadInfo> CREATOR = new Parcelable.Creator<AudioOffloadInfo>() { // from class: android.media.audio.common.AudioOffloadInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioOffloadInfo createFromParcel(Parcel _aidl_source) {
            AudioOffloadInfo _aidl_out = new AudioOffloadInfo();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioOffloadInfo[] newArray(int _aidl_size) {
            return new AudioOffloadInfo[_aidl_size];
        }
    };
    public int format;
    public int streamType;
    public int usage;
    public int sampleRateHz = 0;
    public int channelMask = 0;
    public int bitRatePerSecond = 0;
    public long durationMicroseconds = 0;
    public boolean hasVideo = false;
    public boolean isStreaming = false;
    public int bitWidth = 0;
    public int bufferSize = 0;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.sampleRateHz);
        _aidl_parcel.writeInt(this.channelMask);
        _aidl_parcel.writeInt(this.format);
        _aidl_parcel.writeInt(this.streamType);
        _aidl_parcel.writeInt(this.bitRatePerSecond);
        _aidl_parcel.writeLong(this.durationMicroseconds);
        _aidl_parcel.writeInt(this.hasVideo ? 1 : 0);
        _aidl_parcel.writeInt(this.isStreaming ? 1 : 0);
        _aidl_parcel.writeInt(this.bitWidth);
        _aidl_parcel.writeInt(this.bufferSize);
        _aidl_parcel.writeInt(this.usage);
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
                    this.sampleRateHz = _aidl_parcel.readInt();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.channelMask = _aidl_parcel.readInt();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.format = _aidl_parcel.readInt();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.streamType = _aidl_parcel.readInt();
                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                    this.bitRatePerSecond = _aidl_parcel.readInt();
                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                        this.durationMicroseconds = _aidl_parcel.readLong();
                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                            boolean z = true;
                                            this.hasVideo = _aidl_parcel.readInt() != 0;
                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                if (_aidl_parcel.readInt() == 0) {
                                                    z = false;
                                                }
                                                this.isStreaming = z;
                                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                    this.bitWidth = _aidl_parcel.readInt();
                                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                        this.bufferSize = _aidl_parcel.readInt();
                                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                            this.usage = _aidl_parcel.readInt();
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
