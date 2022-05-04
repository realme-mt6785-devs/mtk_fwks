package android.hardware.camera2.extension;

import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class ParcelImage implements Parcelable {
    public static final Parcelable.Creator<ParcelImage> CREATOR = new Parcelable.Creator<ParcelImage>() { // from class: android.hardware.camera2.extension.ParcelImage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelImage createFromParcel(Parcel _aidl_source) {
            ParcelImage _aidl_out = new ParcelImage();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelImage[] newArray(int _aidl_size) {
            return new ParcelImage[_aidl_size];
        }
    };
    public HardwareBuffer buffer;
    public Rect crop;
    public ParcelFileDescriptor fence;
    public int format = 0;
    public int width = 0;
    public int height = 0;
    public int transform = 0;
    public int scalingMode = 0;
    public long timestamp = 0;
    public int planeCount = 0;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.format);
        _aidl_parcel.writeInt(this.width);
        _aidl_parcel.writeInt(this.height);
        _aidl_parcel.writeInt(this.transform);
        _aidl_parcel.writeInt(this.scalingMode);
        _aidl_parcel.writeLong(this.timestamp);
        _aidl_parcel.writeInt(this.planeCount);
        if (this.crop != null) {
            _aidl_parcel.writeInt(1);
            this.crop.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
        if (this.buffer != null) {
            _aidl_parcel.writeInt(1);
            this.buffer.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
        if (this.fence != null) {
            _aidl_parcel.writeInt(1);
            this.fence.writeToParcel(_aidl_parcel, 0);
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
                    this.format = _aidl_parcel.readInt();
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.width = _aidl_parcel.readInt();
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.height = _aidl_parcel.readInt();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.transform = _aidl_parcel.readInt();
                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                    this.scalingMode = _aidl_parcel.readInt();
                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                        this.timestamp = _aidl_parcel.readLong();
                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                            this.planeCount = _aidl_parcel.readInt();
                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                if (_aidl_parcel.readInt() != 0) {
                                                    this.crop = Rect.CREATOR.createFromParcel(_aidl_parcel);
                                                } else {
                                                    this.crop = null;
                                                }
                                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                    if (_aidl_parcel.readInt() != 0) {
                                                        this.buffer = HardwareBuffer.CREATOR.createFromParcel(_aidl_parcel);
                                                    } else {
                                                        this.buffer = null;
                                                    }
                                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                        if (_aidl_parcel.readInt() != 0) {
                                                            this.fence = ParcelFileDescriptor.CREATOR.createFromParcel(_aidl_parcel);
                                                        } else {
                                                            this.fence = null;
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
        int _mask = 0 | describeContents(this.crop);
        return _mask | describeContents(this.buffer) | describeContents(this.fence);
    }

    private int describeContents(Object _v) {
        if (_v != null && (_v instanceof Parcelable)) {
            return ((Parcelable) _v).describeContents();
        }
        return 0;
    }
}
