package android.content.pm;

import android.content.pm.IPackageInstallerSessionFileSystemConnector;
import android.os.BadParcelableException;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.incremental.IIncrementalServiceConnector;
import android.os.incremental.IncrementalFileSystemControlParcel;
/* loaded from: classes.dex */
public class FileSystemControlParcel implements Parcelable {
    public static final Parcelable.Creator<FileSystemControlParcel> CREATOR = new Parcelable.Creator<FileSystemControlParcel>() { // from class: android.content.pm.FileSystemControlParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileSystemControlParcel createFromParcel(Parcel _aidl_source) {
            FileSystemControlParcel _aidl_out = new FileSystemControlParcel();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileSystemControlParcel[] newArray(int _aidl_size) {
            return new FileSystemControlParcel[_aidl_size];
        }
    };
    public IPackageInstallerSessionFileSystemConnector callback;
    public IncrementalFileSystemControlParcel incremental;
    public IIncrementalServiceConnector service;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        if (this.incremental != null) {
            _aidl_parcel.writeInt(1);
            this.incremental.writeToParcel(_aidl_parcel, 0);
        } else {
            _aidl_parcel.writeInt(0);
        }
        IIncrementalServiceConnector iIncrementalServiceConnector = this.service;
        IBinder iBinder = null;
        _aidl_parcel.writeStrongBinder(iIncrementalServiceConnector != null ? iIncrementalServiceConnector.asBinder() : null);
        IPackageInstallerSessionFileSystemConnector iPackageInstallerSessionFileSystemConnector = this.callback;
        if (iPackageInstallerSessionFileSystemConnector != null) {
            iBinder = iPackageInstallerSessionFileSystemConnector.asBinder();
        }
        _aidl_parcel.writeStrongBinder(iBinder);
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
                        this.incremental = IncrementalFileSystemControlParcel.CREATOR.createFromParcel(_aidl_parcel);
                    } else {
                        this.incremental = null;
                    }
                    if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                        this.service = IIncrementalServiceConnector.Stub.asInterface(_aidl_parcel.readStrongBinder());
                        if (_aidl_parcel.dataPosition() - _aidl_start_pos < _aidl_parcelable_size) {
                            this.callback = IPackageInstallerSessionFileSystemConnector.Stub.asInterface(_aidl_parcel.readStrongBinder());
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
        int _mask = 0 | describeContents(this.incremental);
        return _mask;
    }

    private int describeContents(Object _v) {
        if (_v != null && (_v instanceof Parcelable)) {
            return ((Parcelable) _v).describeContents();
        }
        return 0;
    }
}
