package android.hardware.camera2.extension;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import java.util.Collection;
import java.util.List;
/* loaded from: classes.dex */
public class CameraOutputConfig implements Parcelable {
    public static final Parcelable.Creator<CameraOutputConfig> CREATOR = new Parcelable.Creator<CameraOutputConfig>() { // from class: android.hardware.camera2.extension.CameraOutputConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraOutputConfig createFromParcel(Parcel _aidl_source) {
            CameraOutputConfig _aidl_out = new CameraOutputConfig();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraOutputConfig[] newArray(int _aidl_size) {
            return new CameraOutputConfig[_aidl_size];
        }
    };
    public static final int TYPE_IMAGEREADER = 1;
    public static final int TYPE_MULTIRES_IMAGEREADER = 2;
    public static final int TYPE_SURFACE = 0;
    public OutputConfigId outputId;
    public String physicalCameraId;
    public Size size;
    public Surface surface;
    public List<OutputConfigId> surfaceSharingOutputConfigs;
    public int imageFormat = 0;
    public int capacity = 0;
    public int type = 0;
    public int surfaceGroupId = 0;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        if (this.size != null) {
            _aidl_parcel.writeInt(1);
            this.size.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
        if (this.surface != null) {
            _aidl_parcel.writeInt(1);
            this.surface.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
        _aidl_parcel.writeInt(this.imageFormat);
        _aidl_parcel.writeInt(this.capacity);
        _aidl_parcel.writeInt(this.type);
        if (this.outputId != null) {
            _aidl_parcel.writeInt(1);
            this.outputId.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
        _aidl_parcel.writeInt(this.surfaceGroupId);
        _aidl_parcel.writeString(this.physicalCameraId);
        _aidl_parcel.writeTypedList(this.surfaceSharingOutputConfigs);
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
                    if (_aidl_parcel.readInt() != 0) {
                        this.size = Size.CREATOR.createFromParcel(_aidl_parcel);
                    } else {
                        this.size = null;
                    }
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        if (_aidl_parcel.readInt() != 0) {
                            this.surface = Surface.CREATOR.createFromParcel(_aidl_parcel);
                        } else {
                            this.surface = null;
                        }
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.imageFormat = _aidl_parcel.readInt();
                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                this.capacity = _aidl_parcel.readInt();
                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                    this.type = _aidl_parcel.readInt();
                                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                        if (_aidl_parcel.readInt() != 0) {
                                            this.outputId = OutputConfigId.CREATOR.createFromParcel(_aidl_parcel);
                                        } else {
                                            this.outputId = null;
                                        }
                                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                            this.surfaceGroupId = _aidl_parcel.readInt();
                                            if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                this.physicalCameraId = _aidl_parcel.readString();
                                                if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                                                    this.surfaceSharingOutputConfigs = _aidl_parcel.createTypedArrayList(OutputConfigId.CREATOR);
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
        int _mask = 0 | describeContents(this.size);
        return _mask | describeContents(this.surface) | describeContents(this.outputId) | describeContents(this.surfaceSharingOutputConfigs);
    }

    private int describeContents(Object _v) {
        if (_v == null) {
            return 0;
        }
        if (_v instanceof Collection) {
            int _mask = 0;
            for (Object o : (Collection) _v) {
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
