package android.gsi;

import android.gsi.IGsiServiceCallback;
import android.gsi.IImageService;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IGsiService extends IInterface {
    public static final String DESCRIPTOR = "android.gsi.IGsiService";
    public static final int INSTALL_ERROR_FILE_SYSTEM_CLUTTERED = 3;
    public static final int INSTALL_ERROR_GENERIC = 1;
    public static final int INSTALL_ERROR_NO_SPACE = 2;
    public static final int INSTALL_OK = 0;
    public static final int STATUS_COMPLETE = 2;
    public static final int STATUS_NO_OPERATION = 0;
    public static final int STATUS_WORKING = 1;

    boolean cancelGsiInstall() throws RemoteException;

    int closeInstall() throws RemoteException;

    int closePartition() throws RemoteException;

    boolean commitGsiChunkFromAshmem(long j) throws RemoteException;

    boolean commitGsiChunkFromStream(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    int createPartition(String str, long j, boolean z) throws RemoteException;

    boolean disableGsi() throws RemoteException;

    String dumpDeviceMapperDevices() throws RemoteException;

    int enableGsi(boolean z, String str) throws RemoteException;

    void enableGsiAsync(boolean z, String str, IGsiServiceCallback iGsiServiceCallback) throws RemoteException;

    String getActiveDsuSlot() throws RemoteException;

    int getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException;

    GsiProgress getInstallProgress() throws RemoteException;

    List<String> getInstalledDsuSlots() throws RemoteException;

    String getInstalledGsiImageDir() throws RemoteException;

    boolean isGsiEnabled() throws RemoteException;

    boolean isGsiInstallInProgress() throws RemoteException;

    boolean isGsiInstalled() throws RemoteException;

    boolean isGsiRunning() throws RemoteException;

    IImageService openImageService(String str) throws RemoteException;

    int openInstall(String str) throws RemoteException;

    boolean removeGsi() throws RemoteException;

    void removeGsiAsync(IGsiServiceCallback iGsiServiceCallback) throws RemoteException;

    boolean setGsiAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    long suggestScratchSize() throws RemoteException;

    int zeroPartition(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IGsiService {
        @Override // android.gsi.IGsiService
        public boolean commitGsiChunkFromStream(ParcelFileDescriptor stream, long bytes) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public GsiProgress getInstallProgress() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public boolean setGsiAshmem(ParcelFileDescriptor stream, long size) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean commitGsiChunkFromAshmem(long bytes) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public int enableGsi(boolean oneShot, String dsuSlot) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public void enableGsiAsync(boolean oneShot, String dsuSlot, IGsiServiceCallback result) throws RemoteException {
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiEnabled() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean cancelGsiInstall() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiInstallInProgress() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean removeGsi() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public void removeGsiAsync(IGsiServiceCallback result) throws RemoteException {
        }

        @Override // android.gsi.IGsiService
        public boolean disableGsi() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiInstalled() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiRunning() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public String getActiveDsuSlot() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public String getInstalledGsiImageDir() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public List<String> getInstalledDsuSlots() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public int openInstall(String installDir) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public int closeInstall() throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public int createPartition(String name, long size, boolean readOnly) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public int closePartition() throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public int zeroPartition(String name) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public IImageService openImageService(String prefix) throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public String dumpDeviceMapperDevices() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public int getAvbPublicKey(AvbPublicKey dst) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public long suggestScratchSize() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IGsiService {
        static final int TRANSACTION_cancelGsiInstall = 8;
        static final int TRANSACTION_closeInstall = 19;
        static final int TRANSACTION_closePartition = 21;
        static final int TRANSACTION_commitGsiChunkFromAshmem = 4;
        static final int TRANSACTION_commitGsiChunkFromStream = 1;
        static final int TRANSACTION_createPartition = 20;
        static final int TRANSACTION_disableGsi = 12;
        static final int TRANSACTION_dumpDeviceMapperDevices = 24;
        static final int TRANSACTION_enableGsi = 5;
        static final int TRANSACTION_enableGsiAsync = 6;
        static final int TRANSACTION_getActiveDsuSlot = 15;
        static final int TRANSACTION_getAvbPublicKey = 25;
        static final int TRANSACTION_getInstallProgress = 2;
        static final int TRANSACTION_getInstalledDsuSlots = 17;
        static final int TRANSACTION_getInstalledGsiImageDir = 16;
        static final int TRANSACTION_isGsiEnabled = 7;
        static final int TRANSACTION_isGsiInstallInProgress = 9;
        static final int TRANSACTION_isGsiInstalled = 13;
        static final int TRANSACTION_isGsiRunning = 14;
        static final int TRANSACTION_openImageService = 23;
        static final int TRANSACTION_openInstall = 18;
        static final int TRANSACTION_removeGsi = 10;
        static final int TRANSACTION_removeGsiAsync = 11;
        static final int TRANSACTION_setGsiAshmem = 3;
        static final int TRANSACTION_suggestScratchSize = 26;
        static final int TRANSACTION_zeroPartition = 22;

        public Stub() {
            attachInterface(this, IGsiService.DESCRIPTOR);
        }

        public static IGsiService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGsiService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGsiService)) {
                return new Proxy(obj);
            }
            return (IGsiService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "commitGsiChunkFromStream";
                case 2:
                    return "getInstallProgress";
                case 3:
                    return "setGsiAshmem";
                case 4:
                    return "commitGsiChunkFromAshmem";
                case 5:
                    return "enableGsi";
                case 6:
                    return "enableGsiAsync";
                case 7:
                    return "isGsiEnabled";
                case 8:
                    return "cancelGsiInstall";
                case 9:
                    return "isGsiInstallInProgress";
                case 10:
                    return "removeGsi";
                case 11:
                    return "removeGsiAsync";
                case 12:
                    return "disableGsi";
                case 13:
                    return "isGsiInstalled";
                case 14:
                    return "isGsiRunning";
                case 15:
                    return "getActiveDsuSlot";
                case 16:
                    return "getInstalledGsiImageDir";
                case 17:
                    return "getInstalledDsuSlots";
                case 18:
                    return "openInstall";
                case 19:
                    return "closeInstall";
                case 20:
                    return "createPartition";
                case 21:
                    return "closePartition";
                case 22:
                    return "zeroPartition";
                case 23:
                    return "openImageService";
                case 24:
                    return "dumpDeviceMapperDevices";
                case 25:
                    return "getAvbPublicKey";
                case 26:
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
            ParcelFileDescriptor _arg0;
            ParcelFileDescriptor _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IGsiService.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg2 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            long _arg1 = data.readLong();
                            boolean commitGsiChunkFromStream = commitGsiChunkFromStream(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(commitGsiChunkFromStream ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            GsiProgress _result = getInstallProgress();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            long _arg12 = data.readLong();
                            boolean gsiAshmem = setGsiAshmem(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeInt(gsiAshmem ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            long _arg03 = data.readLong();
                            boolean commitGsiChunkFromAshmem = commitGsiChunkFromAshmem(_arg03);
                            reply.writeNoException();
                            reply.writeInt(commitGsiChunkFromAshmem ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            String _arg13 = data.readString();
                            int _result2 = enableGsi(_arg2, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 6:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            String _arg14 = data.readString();
                            enableGsiAsync(_arg2, _arg14, IGsiServiceCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 7:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean isGsiEnabled = isGsiEnabled();
                            reply.writeNoException();
                            reply.writeInt(isGsiEnabled ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean cancelGsiInstall = cancelGsiInstall();
                            reply.writeNoException();
                            reply.writeInt(cancelGsiInstall ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean isGsiInstallInProgress = isGsiInstallInProgress();
                            reply.writeNoException();
                            reply.writeInt(isGsiInstallInProgress ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean removeGsi = removeGsi();
                            reply.writeNoException();
                            reply.writeInt(removeGsi ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            IGsiServiceCallback _arg04 = IGsiServiceCallback.Stub.asInterface(data.readStrongBinder());
                            removeGsiAsync(_arg04);
                            return true;
                        case 12:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean disableGsi = disableGsi();
                            reply.writeNoException();
                            reply.writeInt(disableGsi ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean isGsiInstalled = isGsiInstalled();
                            reply.writeNoException();
                            reply.writeInt(isGsiInstalled ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            boolean isGsiRunning = isGsiRunning();
                            reply.writeNoException();
                            reply.writeInt(isGsiRunning ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _result3 = getActiveDsuSlot();
                            reply.writeNoException();
                            reply.writeString(_result3);
                            return true;
                        case 16:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _result4 = getInstalledGsiImageDir();
                            reply.writeNoException();
                            reply.writeString(_result4);
                            return true;
                        case 17:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            List<String> _result5 = getInstalledDsuSlots();
                            reply.writeNoException();
                            reply.writeStringList(_result5);
                            return true;
                        case 18:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _result6 = openInstall(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 19:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            int _result7 = closeInstall();
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 20:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _arg06 = data.readString();
                            long _arg15 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            int _result8 = createPartition(_arg06, _arg15, _arg2);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 21:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            int _result9 = closePartition();
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 22:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _result10 = zeroPartition(_arg07);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 23:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _arg08 = data.readString();
                            IImageService _result11 = openImageService(_arg08);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result11 != null ? _result11.asBinder() : null);
                            return true;
                        case 24:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            String _result12 = dumpDeviceMapperDevices();
                            reply.writeNoException();
                            reply.writeString(_result12);
                            return true;
                        case 25:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            AvbPublicKey _arg09 = new AvbPublicKey();
                            int _result13 = getAvbPublicKey(_arg09);
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            reply.writeInt(1);
                            _arg09.writeToParcel(reply, 1);
                            return true;
                        case 26:
                            data.enforceInterface(IGsiService.DESCRIPTOR);
                            long _result14 = suggestScratchSize();
                            reply.writeNoException();
                            reply.writeLong(_result14);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IGsiService {
            public static IGsiService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGsiService.DESCRIPTOR;
            }

            @Override // android.gsi.IGsiService
            public boolean commitGsiChunkFromStream(ParcelFileDescriptor stream, long bytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = true;
                    if (stream != null) {
                        _data.writeInt(1);
                        stream.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(bytes);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().commitGsiChunkFromStream(stream, bytes);
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

            @Override // android.gsi.IGsiService
            public GsiProgress getInstallProgress() throws RemoteException {
                GsiProgress _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallProgress();
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

            @Override // android.gsi.IGsiService
            public boolean setGsiAshmem(ParcelFileDescriptor stream, long size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = true;
                    if (stream != null) {
                        _data.writeInt(1);
                        stream.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(size);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setGsiAshmem(stream, size);
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

            @Override // android.gsi.IGsiService
            public boolean commitGsiChunkFromAshmem(long bytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeLong(bytes);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().commitGsiChunkFromAshmem(bytes);
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

            @Override // android.gsi.IGsiService
            public int enableGsi(boolean oneShot, String dsuSlot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeInt(oneShot ? 1 : 0);
                    _data.writeString(dsuSlot);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableGsi(oneShot, dsuSlot);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public void enableGsiAsync(boolean oneShot, String dsuSlot, IGsiServiceCallback result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeInt(oneShot ? 1 : 0);
                    _data.writeString(dsuSlot);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableGsiAsync(oneShot, dsuSlot, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean isGsiEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiEnabled();
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

            @Override // android.gsi.IGsiService
            public boolean cancelGsiInstall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelGsiInstall();
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

            @Override // android.gsi.IGsiService
            public boolean isGsiInstallInProgress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiInstallInProgress();
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

            @Override // android.gsi.IGsiService
            public boolean removeGsi() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeGsi();
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

            @Override // android.gsi.IGsiService
            public void removeGsiAsync(IGsiServiceCallback result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeGsiAsync(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean disableGsi() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableGsi();
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

            @Override // android.gsi.IGsiService
            public boolean isGsiInstalled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiInstalled();
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

            @Override // android.gsi.IGsiService
            public boolean isGsiRunning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiRunning();
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

            @Override // android.gsi.IGsiService
            public String getActiveDsuSlot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveDsuSlot();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public String getInstalledGsiImageDir() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledGsiImageDir();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public List<String> getInstalledDsuSlots() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledDsuSlots();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int openInstall(String installDir) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeString(installDir);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openInstall(installDir);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int closeInstall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closeInstall();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int createPartition(String name, long size, boolean readOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeLong(size);
                    _data.writeInt(readOnly ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPartition(name, size, readOnly);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int closePartition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closePartition();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int zeroPartition(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().zeroPartition(name);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public IImageService openImageService(String prefix) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    _data.writeString(prefix);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openImageService(prefix);
                    }
                    _reply.readException();
                    IImageService _result = IImageService.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public String dumpDeviceMapperDevices() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpDeviceMapperDevices();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int getAvbPublicKey(AvbPublicKey dst) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvbPublicKey(dst);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    if (_reply.readInt() != 0) {
                        dst.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public long suggestScratchSize() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGsiService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
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

        public static boolean setDefaultImpl(IGsiService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGsiService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
