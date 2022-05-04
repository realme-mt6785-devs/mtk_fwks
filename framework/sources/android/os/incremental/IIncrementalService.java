package android.os.incremental;

import android.content.pm.DataLoaderParamsParcel;
import android.content.pm.IDataLoaderStatusListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.incremental.IStorageHealthListener;
import android.os.incremental.IStorageLoadingProgressListener;
/* loaded from: classes2.dex */
public interface IIncrementalService extends IInterface {
    public static final int BIND_PERMANENT = 1;
    public static final int BIND_TEMPORARY = 0;
    public static final int CREATE_MODE_CREATE = 4;
    public static final int CREATE_MODE_OPEN_EXISTING = 8;
    public static final int CREATE_MODE_PERMANENT_BIND = 2;
    public static final int CREATE_MODE_TEMPORARY_BIND = 1;
    public static final String DESCRIPTOR = "android.os.incremental.IIncrementalService";
    public static final String METRICS_DATA_LOADER_BIND_DELAY_MILLIS = "dataLoaderBindDelayMillis";
    public static final String METRICS_DATA_LOADER_STATUS_CODE = "dataLoaderStatusCode";
    public static final String METRICS_LAST_READ_ERROR_NUMBER = "lastReadErrorNo";
    public static final String METRICS_LAST_READ_ERROR_UID = "lastReadErrorUid";
    public static final String METRICS_MILLIS_SINCE_LAST_DATA_LOADER_BIND = "millisSinceLastDataLoaderBind";
    public static final String METRICS_MILLIS_SINCE_LAST_READ_ERROR = "millisSinceLastReadError";
    public static final String METRICS_MILLIS_SINCE_OLDEST_PENDING_READ = "millisSinceOldestPendingRead";
    public static final String METRICS_READ_LOGS_ENABLED = "readLogsEnabled";
    public static final String METRICS_STORAGE_HEALTH_STATUS_CODE = "storageHealthStatusCode";
    public static final String METRICS_TOTAL_DELAYED_READS = "totalDelayedReads";
    public static final String METRICS_TOTAL_DELAYED_READS_MILLIS = "totalDelayedReadsMillis";
    public static final String METRICS_TOTAL_FAILED_READS = "totalFailedReads";

    boolean configureNativeBinaries(int i, String str, String str2, String str3, boolean z) throws RemoteException;

    int createLinkedStorage(String str, int i, int i2) throws RemoteException;

    int createStorage(String str, DataLoaderParamsParcel dataLoaderParamsParcel, int i) throws RemoteException;

    int deleteBindMount(int i, String str) throws RemoteException;

    void deleteStorage(int i) throws RemoteException;

    void disallowReadLogs(int i) throws RemoteException;

    float getLoadingProgress(int i) throws RemoteException;

    byte[] getMetadataById(int i, byte[] bArr) throws RemoteException;

    byte[] getMetadataByPath(int i, String str) throws RemoteException;

    PersistableBundle getMetrics(int i) throws RemoteException;

    int isFileFullyLoaded(int i, String str) throws RemoteException;

    int isFullyLoaded(int i) throws RemoteException;

    int makeBindMount(int i, String str, String str2, int i2) throws RemoteException;

    int makeDirectories(int i, String str) throws RemoteException;

    int makeDirectory(int i, String str) throws RemoteException;

    int makeFile(int i, String str, IncrementalNewFileParams incrementalNewFileParams, byte[] bArr) throws RemoteException;

    int makeFileFromRange(int i, String str, String str2, long j, long j2) throws RemoteException;

    int makeLink(int i, String str, int i2, String str2) throws RemoteException;

    void onInstallationComplete(int i) throws RemoteException;

    int openStorage(String str) throws RemoteException;

    boolean registerLoadingProgressListener(int i, IStorageLoadingProgressListener iStorageLoadingProgressListener) throws RemoteException;

    boolean startLoading(int i, DataLoaderParamsParcel dataLoaderParamsParcel, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener, PerUidReadTimeouts[] perUidReadTimeoutsArr) throws RemoteException;

    int unlink(int i, String str) throws RemoteException;

    boolean unregisterLoadingProgressListener(int i) throws RemoteException;

    boolean waitForNativeBinariesExtraction(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IIncrementalService {
        @Override // android.os.incremental.IIncrementalService
        public int openStorage(String path) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int createStorage(String path, DataLoaderParamsParcel params, int createMode) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int createLinkedStorage(String path, int otherStorageId, int createMode) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean startLoading(int storageId, DataLoaderParamsParcel params, IDataLoaderStatusListener statusListener, StorageHealthCheckParams healthCheckParams, IStorageHealthListener healthListener, PerUidReadTimeouts[] perUidReadTimeouts) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public void onInstallationComplete(int storageId) throws RemoteException {
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeBindMount(int storageId, String sourcePath, String targetFullPath, int bindType) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int deleteBindMount(int storageId, String targetFullPath) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeDirectory(int storageId, String path) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeDirectories(int storageId, String path) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeFile(int storageId, String path, IncrementalNewFileParams params, byte[] content) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeFileFromRange(int storageId, String targetPath, String sourcePath, long start, long end) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeLink(int sourceStorageId, String sourcePath, int destStorageId, String destPath) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int unlink(int storageId, String path) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int isFileFullyLoaded(int storageId, String path) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int isFullyLoaded(int storageId) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public float getLoadingProgress(int storageId) throws RemoteException {
            return 0.0f;
        }

        @Override // android.os.incremental.IIncrementalService
        public byte[] getMetadataByPath(int storageId, String path) throws RemoteException {
            return null;
        }

        @Override // android.os.incremental.IIncrementalService
        public byte[] getMetadataById(int storageId, byte[] fileId) throws RemoteException {
            return null;
        }

        @Override // android.os.incremental.IIncrementalService
        public void deleteStorage(int storageId) throws RemoteException {
        }

        @Override // android.os.incremental.IIncrementalService
        public void disallowReadLogs(int storageId) throws RemoteException {
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean configureNativeBinaries(int storageId, String apkFullPath, String libDirRelativePath, String abi, boolean extractNativeLibs) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean waitForNativeBinariesExtraction(int storageId) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean registerLoadingProgressListener(int storageId, IStorageLoadingProgressListener listener) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean unregisterLoadingProgressListener(int storageId) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public PersistableBundle getMetrics(int storageId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IIncrementalService {
        static final int TRANSACTION_configureNativeBinaries = 21;
        static final int TRANSACTION_createLinkedStorage = 3;
        static final int TRANSACTION_createStorage = 2;
        static final int TRANSACTION_deleteBindMount = 7;
        static final int TRANSACTION_deleteStorage = 19;
        static final int TRANSACTION_disallowReadLogs = 20;
        static final int TRANSACTION_getLoadingProgress = 16;
        static final int TRANSACTION_getMetadataById = 18;
        static final int TRANSACTION_getMetadataByPath = 17;
        static final int TRANSACTION_getMetrics = 25;
        static final int TRANSACTION_isFileFullyLoaded = 14;
        static final int TRANSACTION_isFullyLoaded = 15;
        static final int TRANSACTION_makeBindMount = 6;
        static final int TRANSACTION_makeDirectories = 9;
        static final int TRANSACTION_makeDirectory = 8;
        static final int TRANSACTION_makeFile = 10;
        static final int TRANSACTION_makeFileFromRange = 11;
        static final int TRANSACTION_makeLink = 12;
        static final int TRANSACTION_onInstallationComplete = 5;
        static final int TRANSACTION_openStorage = 1;
        static final int TRANSACTION_registerLoadingProgressListener = 23;
        static final int TRANSACTION_startLoading = 4;
        static final int TRANSACTION_unlink = 13;
        static final int TRANSACTION_unregisterLoadingProgressListener = 24;
        static final int TRANSACTION_waitForNativeBinariesExtraction = 22;

        public Stub() {
            attachInterface(this, IIncrementalService.DESCRIPTOR);
        }

        public static IIncrementalService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IIncrementalService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IIncrementalService)) {
                return new Proxy(obj);
            }
            return (IIncrementalService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "openStorage";
                case 2:
                    return "createStorage";
                case 3:
                    return "createLinkedStorage";
                case 4:
                    return "startLoading";
                case 5:
                    return "onInstallationComplete";
                case 6:
                    return "makeBindMount";
                case 7:
                    return "deleteBindMount";
                case 8:
                    return "makeDirectory";
                case 9:
                    return "makeDirectories";
                case 10:
                    return "makeFile";
                case 11:
                    return "makeFileFromRange";
                case 12:
                    return "makeLink";
                case 13:
                    return "unlink";
                case 14:
                    return "isFileFullyLoaded";
                case 15:
                    return "isFullyLoaded";
                case 16:
                    return "getLoadingProgress";
                case 17:
                    return "getMetadataByPath";
                case 18:
                    return "getMetadataById";
                case 19:
                    return "deleteStorage";
                case 20:
                    return "disallowReadLogs";
                case 21:
                    return "configureNativeBinaries";
                case 22:
                    return "waitForNativeBinariesExtraction";
                case 23:
                    return "registerLoadingProgressListener";
                case 24:
                    return "unregisterLoadingProgressListener";
                case 25:
                    return "getMetrics";
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
            DataLoaderParamsParcel _arg1;
            DataLoaderParamsParcel _arg12;
            StorageHealthCheckParams _arg3;
            IncrementalNewFileParams _arg2;
            boolean _arg4;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IIncrementalService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _result = openStorage(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = DataLoaderParamsParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg22 = data.readInt();
                            int _result2 = createStorage(_arg02, _arg1, _arg22);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg23 = data.readInt();
                            int _result3 = createLinkedStorage(_arg03, _arg13, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = DataLoaderParamsParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            IDataLoaderStatusListener _arg24 = IDataLoaderStatusListener.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg3 = StorageHealthCheckParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            IStorageHealthListener _arg42 = IStorageHealthListener.Stub.asInterface(data.readStrongBinder());
                            PerUidReadTimeouts[] _arg5 = (PerUidReadTimeouts[]) data.createTypedArray(PerUidReadTimeouts.CREATOR);
                            boolean startLoading = startLoading(_arg04, _arg12, _arg24, _arg3, _arg42, _arg5);
                            reply.writeNoException();
                            reply.writeInt(startLoading ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onInstallationComplete(_arg05);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _arg14 = data.readString();
                            String _arg25 = data.readString();
                            int _arg32 = data.readInt();
                            int _result4 = makeBindMount(_arg06, _arg14, _arg25, _arg32);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            String _arg15 = data.readString();
                            int _result5 = deleteBindMount(_arg07, _arg15);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 8:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            String _arg16 = data.readString();
                            int _result6 = makeDirectory(_arg08, _arg16);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 9:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            String _arg17 = data.readString();
                            int _result7 = makeDirectories(_arg09, _arg17);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 10:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            String _arg18 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = IncrementalNewFileParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            byte[] _arg33 = data.createByteArray();
                            int _result8 = makeFile(_arg010, _arg18, _arg2, _arg33);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 11:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            String _arg19 = data.readString();
                            String _arg26 = data.readString();
                            long _arg34 = data.readLong();
                            long _arg43 = data.readLong();
                            int _result9 = makeFileFromRange(_arg011, _arg19, _arg26, _arg34, _arg43);
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 12:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            String _arg110 = data.readString();
                            int _arg27 = data.readInt();
                            String _arg35 = data.readString();
                            int _result10 = makeLink(_arg012, _arg110, _arg27, _arg35);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 13:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            String _arg111 = data.readString();
                            int _result11 = unlink(_arg013, _arg111);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 14:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            String _arg112 = data.readString();
                            int _result12 = isFileFullyLoaded(_arg014, _arg112);
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 15:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _result13 = isFullyLoaded(_arg015);
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 16:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            float _result14 = getLoadingProgress(_arg016);
                            reply.writeNoException();
                            reply.writeFloat(_result14);
                            return true;
                        case 17:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            String _arg113 = data.readString();
                            byte[] _result15 = getMetadataByPath(_arg017, _arg113);
                            reply.writeNoException();
                            reply.writeByteArray(_result15);
                            return true;
                        case 18:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            byte[] _arg114 = data.createByteArray();
                            byte[] _result16 = getMetadataById(_arg018, _arg114);
                            reply.writeNoException();
                            reply.writeByteArray(_result16);
                            return true;
                        case 19:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg019 = data.readInt();
                            deleteStorage(_arg019);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg020 = data.readInt();
                            disallowReadLogs(_arg020);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg021 = data.readInt();
                            String _arg115 = data.readString();
                            String _arg28 = data.readString();
                            String _arg36 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            boolean configureNativeBinaries = configureNativeBinaries(_arg021, _arg115, _arg28, _arg36, _arg4);
                            reply.writeNoException();
                            reply.writeInt(configureNativeBinaries ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg022 = data.readInt();
                            boolean waitForNativeBinariesExtraction = waitForNativeBinariesExtraction(_arg022);
                            reply.writeNoException();
                            reply.writeInt(waitForNativeBinariesExtraction ? 1 : 0);
                            return true;
                        case 23:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg023 = data.readInt();
                            IStorageLoadingProgressListener _arg116 = IStorageLoadingProgressListener.Stub.asInterface(data.readStrongBinder());
                            boolean registerLoadingProgressListener = registerLoadingProgressListener(_arg023, _arg116);
                            reply.writeNoException();
                            reply.writeInt(registerLoadingProgressListener ? 1 : 0);
                            return true;
                        case 24:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg024 = data.readInt();
                            boolean unregisterLoadingProgressListener = unregisterLoadingProgressListener(_arg024);
                            reply.writeNoException();
                            reply.writeInt(unregisterLoadingProgressListener ? 1 : 0);
                            return true;
                        case 25:
                            data.enforceInterface(IIncrementalService.DESCRIPTOR);
                            int _arg025 = data.readInt();
                            PersistableBundle _result17 = getMetrics(_arg025);
                            reply.writeNoException();
                            if (_result17 != null) {
                                reply.writeInt(1);
                                _result17.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IIncrementalService {
            public static IIncrementalService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IIncrementalService.DESCRIPTOR;
            }

            @Override // android.os.incremental.IIncrementalService
            public int openStorage(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openStorage(path);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int createStorage(String path, DataLoaderParamsParcel params, int createMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeString(path);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(createMode);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createStorage(path, params, createMode);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int createLinkedStorage(String path, int otherStorageId, int createMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeString(path);
                    _data.writeInt(otherStorageId);
                    _data.writeInt(createMode);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createLinkedStorage(path, otherStorageId, createMode);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean startLoading(int storageId, DataLoaderParamsParcel params, IDataLoaderStatusListener statusListener, StorageHealthCheckParams healthCheckParams, IStorageHealthListener healthListener, PerUidReadTimeouts[] perUidReadTimeouts) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    try {
                        _data.writeInt(storageId);
                        boolean _result = true;
                        if (params != null) {
                            _data.writeInt(1);
                            params.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        IBinder iBinder = null;
                        _data.writeStrongBinder(statusListener != null ? statusListener.asBinder() : null);
                        if (healthCheckParams != null) {
                            _data.writeInt(1);
                            healthCheckParams.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (healthListener != null) {
                            iBinder = healthListener.asBinder();
                        }
                        _data.writeStrongBinder(iBinder);
                        try {
                            _data.writeTypedArray(perUidReadTimeouts, 0);
                            try {
                                boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() == 0) {
                                        _result = false;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                boolean startLoading = Stub.getDefaultImpl().startLoading(storageId, params, statusListener, healthCheckParams, healthListener, perUidReadTimeouts);
                                _reply.recycle();
                                _data.recycle();
                                return startLoading;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public void onInstallationComplete(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onInstallationComplete(storageId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeBindMount(int storageId, String sourcePath, String targetFullPath, int bindType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(sourcePath);
                    _data.writeString(targetFullPath);
                    _data.writeInt(bindType);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeBindMount(storageId, sourcePath, targetFullPath, bindType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int deleteBindMount(int storageId, String targetFullPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(targetFullPath);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteBindMount(storageId, targetFullPath);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeDirectory(int storageId, String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeDirectory(storageId, path);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeDirectories(int storageId, String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeDirectories(storageId, path);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeFile(int storageId, String path, IncrementalNewFileParams params, byte[] content) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(path);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(content);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeFile(storageId, path, params, content);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeFileFromRange(int storageId, String targetPath, String sourcePath, long start, long end) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    try {
                        _data.writeInt(storageId);
                        try {
                            _data.writeString(targetPath);
                            try {
                                _data.writeString(sourcePath);
                                try {
                                    _data.writeLong(start);
                                    _data.writeLong(end);
                                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        int _result = _reply.readInt();
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    int makeFileFromRange = Stub.getDefaultImpl().makeFileFromRange(storageId, targetPath, sourcePath, start, end);
                                    _reply.recycle();
                                    _data.recycle();
                                    return makeFileFromRange;
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeLink(int sourceStorageId, String sourcePath, int destStorageId, String destPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(sourceStorageId);
                    _data.writeString(sourcePath);
                    _data.writeInt(destStorageId);
                    _data.writeString(destPath);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeLink(sourceStorageId, sourcePath, destStorageId, destPath);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int unlink(int storageId, String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unlink(storageId, path);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int isFileFullyLoaded(int storageId, String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFileFullyLoaded(storageId, path);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int isFullyLoaded(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFullyLoaded(storageId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public float getLoadingProgress(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLoadingProgress(storageId);
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public byte[] getMetadataByPath(int storageId, String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadataByPath(storageId, path);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public byte[] getMetadataById(int storageId, byte[] fileId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeByteArray(fileId);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadataById(storageId, fileId);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public void deleteStorage(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteStorage(storageId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public void disallowReadLogs(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disallowReadLogs(storageId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean configureNativeBinaries(int storageId, String apkFullPath, String libDirRelativePath, String abi, boolean extractNativeLibs) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    try {
                        _data.writeInt(storageId);
                        try {
                            _data.writeString(apkFullPath);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeString(libDirRelativePath);
                    try {
                        _data.writeString(abi);
                        boolean _result = true;
                        _data.writeInt(extractNativeLibs ? 1 : 0);
                        try {
                            boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                if (_reply.readInt() == 0) {
                                    _result = false;
                                }
                                _reply.recycle();
                                _data.recycle();
                                return _result;
                            }
                            boolean configureNativeBinaries = Stub.getDefaultImpl().configureNativeBinaries(storageId, apkFullPath, libDirRelativePath, abi, extractNativeLibs);
                            _reply.recycle();
                            _data.recycle();
                            return configureNativeBinaries;
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean waitForNativeBinariesExtraction(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().waitForNativeBinariesExtraction(storageId);
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

            @Override // android.os.incremental.IIncrementalService
            public boolean registerLoadingProgressListener(int storageId, IStorageLoadingProgressListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerLoadingProgressListener(storageId, listener);
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

            @Override // android.os.incremental.IIncrementalService
            public boolean unregisterLoadingProgressListener(int storageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterLoadingProgressListener(storageId);
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

            @Override // android.os.incremental.IIncrementalService
            public PersistableBundle getMetrics(int storageId) throws RemoteException {
                PersistableBundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncrementalService.DESCRIPTOR);
                    _data.writeInt(storageId);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetrics(storageId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PersistableBundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncrementalService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IIncrementalService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
