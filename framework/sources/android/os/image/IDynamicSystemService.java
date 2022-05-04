package android.os.image;

import android.gsi.AvbPublicKey;
import android.gsi.GsiProgress;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IDynamicSystemService extends IInterface {
    public static final String DESCRIPTOR = "android.os.image.IDynamicSystemService";

    boolean abort() throws RemoteException;

    boolean closePartition() throws RemoteException;

    boolean createPartition(String str, long j, boolean z) throws RemoteException;

    boolean finishInstallation() throws RemoteException;

    boolean getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException;

    GsiProgress getInstallationProgress() throws RemoteException;

    boolean isEnabled() throws RemoteException;

    boolean isInUse() throws RemoteException;

    boolean isInstalled() throws RemoteException;

    boolean remove() throws RemoteException;

    boolean setAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    boolean setEnable(boolean z, boolean z2) throws RemoteException;

    boolean startInstallation(String str) throws RemoteException;

    boolean submitFromAshmem(long j) throws RemoteException;

    long suggestScratchSize() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IDynamicSystemService {
        @Override // android.os.image.IDynamicSystemService
        public boolean startInstallation(String dsuSlot) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean createPartition(String name, long size, boolean readOnly) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean closePartition() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean finishInstallation() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public GsiProgress getInstallationProgress() throws RemoteException {
            return null;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean abort() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean isInUse() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean isInstalled() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean isEnabled() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean remove() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean setEnable(boolean enable, boolean oneShot) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean setAshmem(ParcelFileDescriptor fd, long size) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean submitFromAshmem(long bytes) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean getAvbPublicKey(AvbPublicKey dst) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public long suggestScratchSize() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IDynamicSystemService {
        static final int TRANSACTION_abort = 6;
        static final int TRANSACTION_closePartition = 3;
        static final int TRANSACTION_createPartition = 2;
        static final int TRANSACTION_finishInstallation = 4;
        static final int TRANSACTION_getAvbPublicKey = 14;
        static final int TRANSACTION_getInstallationProgress = 5;
        static final int TRANSACTION_isEnabled = 9;
        static final int TRANSACTION_isInUse = 7;
        static final int TRANSACTION_isInstalled = 8;
        static final int TRANSACTION_remove = 10;
        static final int TRANSACTION_setAshmem = 12;
        static final int TRANSACTION_setEnable = 11;
        static final int TRANSACTION_startInstallation = 1;
        static final int TRANSACTION_submitFromAshmem = 13;
        static final int TRANSACTION_suggestScratchSize = 15;

        public Stub() {
            attachInterface(this, IDynamicSystemService.DESCRIPTOR);
        }

        public static IDynamicSystemService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDynamicSystemService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDynamicSystemService)) {
                return new Proxy(obj);
            }
            return (IDynamicSystemService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startInstallation";
                case 2:
                    return "createPartition";
                case 3:
                    return "closePartition";
                case 4:
                    return "finishInstallation";
                case 5:
                    return "getInstallationProgress";
                case 6:
                    return "abort";
                case 7:
                    return "isInUse";
                case 8:
                    return "isInstalled";
                case 9:
                    return "isEnabled";
                case 10:
                    return "remove";
                case 11:
                    return "setEnable";
                case 12:
                    return "setAshmem";
                case 13:
                    return "submitFromAshmem";
                case 14:
                    return "getAvbPublicKey";
                case 15:
                    return "suggestScratchSize";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0;
            ParcelFileDescriptor _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDynamicSystemService.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            boolean startInstallation = startInstallation(_arg03);
                            reply.writeNoException();
                            reply.writeInt(startInstallation ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            String _arg04 = data.readString();
                            long _arg12 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            boolean createPartition = createPartition(_arg04, _arg12, _arg1);
                            reply.writeNoException();
                            reply.writeInt(createPartition ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean closePartition = closePartition();
                            reply.writeNoException();
                            reply.writeInt(closePartition ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean finishInstallation = finishInstallation();
                            reply.writeNoException();
                            reply.writeInt(finishInstallation ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            GsiProgress _result = getInstallationProgress();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean abort = abort();
                            reply.writeNoException();
                            reply.writeInt(abort ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean isInUse = isInUse();
                            reply.writeNoException();
                            reply.writeInt(isInUse ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean isInstalled = isInstalled();
                            reply.writeNoException();
                            reply.writeInt(isInstalled ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean isEnabled = isEnabled();
                            reply.writeNoException();
                            reply.writeInt(isEnabled ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            boolean remove = remove();
                            reply.writeNoException();
                            reply.writeInt(remove ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            } else {
                                _arg0 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            boolean enable = setEnable(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(enable ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            boolean ashmem = setAshmem(_arg02, data.readLong());
                            reply.writeNoException();
                            reply.writeInt(ashmem ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            long _arg05 = data.readLong();
                            boolean submitFromAshmem = submitFromAshmem(_arg05);
                            reply.writeNoException();
                            reply.writeInt(submitFromAshmem ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            AvbPublicKey _arg06 = new AvbPublicKey();
                            boolean avbPublicKey = getAvbPublicKey(_arg06);
                            reply.writeNoException();
                            reply.writeInt(avbPublicKey ? 1 : 0);
                            reply.writeInt(1);
                            _arg06.writeToParcel(reply, 1);
                            return true;
                        case 15:
                            data.enforceInterface(IDynamicSystemService.DESCRIPTOR);
                            long _result2 = suggestScratchSize();
                            reply.writeNoException();
                            reply.writeLong(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IDynamicSystemService {
            public static IDynamicSystemService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDynamicSystemService.DESCRIPTOR;
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean startInstallation(String dsuSlot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    _data.writeString(dsuSlot);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startInstallation(dsuSlot);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean createPartition(String name, long size, boolean readOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeLong(size);
                    boolean _result = true;
                    _data.writeInt(readOnly ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPartition(name, size, readOnly);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean closePartition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closePartition();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean finishInstallation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finishInstallation();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public GsiProgress getInstallationProgress() throws RemoteException {
                GsiProgress _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallationProgress();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GsiProgress.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean abort() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().abort();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean isInUse() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInUse();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean isInstalled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInstalled();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean isEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnabled();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean remove() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().remove();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean setEnable(boolean enable, boolean oneShot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(oneShot ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnable(enable, oneShot);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean setAshmem(ParcelFileDescriptor fd, long size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = true;
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(size);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAshmem(fd, size);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean submitFromAshmem(long bytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    _data.writeLong(bytes);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().submitFromAshmem(bytes);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean getAvbPublicKey(AvbPublicKey dst) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvbPublicKey(dst);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    if (_reply.readInt() != 0) {
                        dst.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public long suggestScratchSize() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDynamicSystemService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().suggestScratchSize();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDynamicSystemService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDynamicSystemService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
