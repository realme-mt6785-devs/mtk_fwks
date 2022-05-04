package android.os.storage;

import android.app.PendingIntent;
import android.content.pm.IPackageMoveObserver;
import android.content.res.ObbInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IVoldTaskListener;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.storage.IObbActionListener;
import android.os.storage.IStorageEventListener;
import android.os.storage.IStorageShutdownObserver;
import android.provider.Telephony;
import com.android.internal.os.AppFuseMount;
/* loaded from: classes2.dex */
public interface IStorageManager extends IInterface {
    void abortChanges(String str, boolean z) throws RemoteException;

    void abortIdleMaintenance() throws RemoteException;

    void addUserKeyAuth(int i, int i2, byte[] bArr, byte[] bArr2) throws RemoteException;

    void allocateBytes(String str, long j, int i, String str2) throws RemoteException;

    void benchmark(String str, IVoldTaskListener iVoldTaskListener) throws RemoteException;

    int changeEncryptionPassword(int i, String str) throws RemoteException;

    void clearPassword() throws RemoteException;

    void clearUserKeyAuth(int i, int i2, byte[] bArr, byte[] bArr2) throws RemoteException;

    void commitChanges() throws RemoteException;

    void createUserKey(int i, int i2, boolean z) throws RemoteException;

    int decryptStorage(String str) throws RemoteException;

    void destroyUserKey(int i) throws RemoteException;

    void destroyUserStorage(String str, int i, int i2) throws RemoteException;

    void disableAppDataIsolation(String str, int i, int i2) throws RemoteException;

    int encryptStorage(int i, String str) throws RemoteException;

    void fixateNewestUserKeyAuth(int i) throws RemoteException;

    void fixupAppDir(String str) throws RemoteException;

    void forgetAllVolumes() throws RemoteException;

    void forgetVolume(String str) throws RemoteException;

    void format(String str) throws RemoteException;

    void fstrim(int i, IVoldTaskListener iVoldTaskListener) throws RemoteException;

    long getAllocatableBytes(String str, int i, String str2) throws RemoteException;

    long getCacheQuotaBytes(String str, int i) throws RemoteException;

    long getCacheSizeBytes(String str, int i) throws RemoteException;

    DiskInfo[] getDisks() throws RemoteException;

    int getEncryptionState() throws RemoteException;

    int getExternalStorageMountMode(int i, String str) throws RemoteException;

    String getField(String str) throws RemoteException;

    PendingIntent getManageSpaceActivityIntent(String str, int i) throws RemoteException;

    String getMountedObbPath(String str) throws RemoteException;

    String getPassword() throws RemoteException;

    int getPasswordType() throws RemoteException;

    String getPrimaryStorageUuid() throws RemoteException;

    StorageVolume[] getVolumeList(int i, String str, int i2) throws RemoteException;

    VolumeRecord[] getVolumeRecords(int i) throws RemoteException;

    VolumeInfo[] getVolumes(int i) throws RemoteException;

    boolean isAppIoBlocked(String str, int i, int i2, int i3) throws RemoteException;

    boolean isConvertibleToFBE() throws RemoteException;

    boolean isObbMounted(String str) throws RemoteException;

    boolean isUserKeyUnlocked(int i) throws RemoteException;

    long lastMaintenance() throws RemoteException;

    void lockUserKey(int i) throws RemoteException;

    void mkdirs(String str, String str2) throws RemoteException;

    void mount(String str) throws RemoteException;

    void mountObb(String str, String str2, String str3, IObbActionListener iObbActionListener, int i, ObbInfo obbInfo) throws RemoteException;

    AppFuseMount mountProxyFileDescriptorBridge() throws RemoteException;

    boolean needsCheckpoint() throws RemoteException;

    void notifyAppIoBlocked(String str, int i, int i2, int i3) throws RemoteException;

    void notifyAppIoResumed(String str, int i, int i2, int i3) throws RemoteException;

    ParcelFileDescriptor openProxyFileDescriptor(int i, int i2, int i3) throws RemoteException;

    void partitionMixed(String str, int i) throws RemoteException;

    void partitionPrivate(String str) throws RemoteException;

    void partitionPublic(String str) throws RemoteException;

    void prepareUserStorage(String str, int i, int i2, int i3) throws RemoteException;

    void registerListener(IStorageEventListener iStorageEventListener) throws RemoteException;

    void runIdleMaintenance() throws RemoteException;

    void runMaintenance() throws RemoteException;

    void setDebugFlags(int i, int i2) throws RemoteException;

    void setField(String str, String str2) throws RemoteException;

    void setPrimaryStorageUuid(String str, IPackageMoveObserver iPackageMoveObserver) throws RemoteException;

    void setVolumeNickname(String str, String str2) throws RemoteException;

    void setVolumeUserFlags(String str, int i, int i2) throws RemoteException;

    void shutdown(IStorageShutdownObserver iStorageShutdownObserver) throws RemoteException;

    void startCheckpoint(int i) throws RemoteException;

    boolean supportsCheckpoint() throws RemoteException;

    void unlockUserKey(int i, int i2, byte[] bArr, byte[] bArr2) throws RemoteException;

    void unmount(String str) throws RemoteException;

    void unmountObb(String str, boolean z, IObbActionListener iObbActionListener, int i) throws RemoteException;

    void unregisterListener(IStorageEventListener iStorageEventListener) throws RemoteException;

    int verifyEncryptionPassword(String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IStorageManager {
        @Override // android.os.storage.IStorageManager
        public void registerListener(IStorageEventListener listener) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void unregisterListener(IStorageEventListener listener) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void shutdown(IStorageShutdownObserver observer) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void mountObb(String rawPath, String canonicalPath, String key, IObbActionListener token, int nonce, ObbInfo obbInfo) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void unmountObb(String rawPath, boolean force, IObbActionListener token, int nonce) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public boolean isObbMounted(String rawPath) throws RemoteException {
            return false;
        }

        @Override // android.os.storage.IStorageManager
        public String getMountedObbPath(String rawPath) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public int decryptStorage(String password) throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IStorageManager
        public int encryptStorage(int type, String password) throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IStorageManager
        public int changeEncryptionPassword(int type, String password) throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IStorageManager
        public StorageVolume[] getVolumeList(int uid, String packageName, int flags) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public int getEncryptionState() throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IStorageManager
        public int verifyEncryptionPassword(String password) throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IStorageManager
        public void mkdirs(String callingPkg, String path) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public int getPasswordType() throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IStorageManager
        public String getPassword() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public void clearPassword() throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void setField(String field, String contents) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public String getField(String field) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public long lastMaintenance() throws RemoteException {
            return 0L;
        }

        @Override // android.os.storage.IStorageManager
        public void runMaintenance() throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public DiskInfo[] getDisks() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public VolumeInfo[] getVolumes(int flags) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public VolumeRecord[] getVolumeRecords(int flags) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public void mount(String volId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void unmount(String volId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void format(String volId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void partitionPublic(String diskId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void partitionPrivate(String diskId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void partitionMixed(String diskId, int ratio) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void setVolumeNickname(String fsUuid, String nickname) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void setVolumeUserFlags(String fsUuid, int flags, int mask) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void forgetVolume(String fsUuid) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void forgetAllVolumes() throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public String getPrimaryStorageUuid() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public void setPrimaryStorageUuid(String volumeUuid, IPackageMoveObserver callback) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void benchmark(String volId, IVoldTaskListener listener) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void setDebugFlags(int flags, int mask) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void createUserKey(int userId, int serialNumber, boolean ephemeral) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void destroyUserKey(int userId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void unlockUserKey(int userId, int serialNumber, byte[] token, byte[] secret) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void lockUserKey(int userId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public boolean isUserKeyUnlocked(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.storage.IStorageManager
        public void prepareUserStorage(String volumeUuid, int userId, int serialNumber, int flags) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void destroyUserStorage(String volumeUuid, int userId, int flags) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public boolean isConvertibleToFBE() throws RemoteException {
            return false;
        }

        @Override // android.os.storage.IStorageManager
        public void addUserKeyAuth(int userId, int serialNumber, byte[] token, byte[] secret) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void fixateNewestUserKeyAuth(int userId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void fstrim(int flags, IVoldTaskListener listener) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public AppFuseMount mountProxyFileDescriptorBridge() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public ParcelFileDescriptor openProxyFileDescriptor(int mountPointId, int fileId, int mode) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public long getCacheQuotaBytes(String volumeUuid, int uid) throws RemoteException {
            return 0L;
        }

        @Override // android.os.storage.IStorageManager
        public long getCacheSizeBytes(String volumeUuid, int uid) throws RemoteException {
            return 0L;
        }

        @Override // android.os.storage.IStorageManager
        public long getAllocatableBytes(String volumeUuid, int flags, String callingPackage) throws RemoteException {
            return 0L;
        }

        @Override // android.os.storage.IStorageManager
        public void allocateBytes(String volumeUuid, long bytes, int flags, String callingPackage) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void runIdleMaintenance() throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void abortIdleMaintenance() throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void commitChanges() throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public boolean supportsCheckpoint() throws RemoteException {
            return false;
        }

        @Override // android.os.storage.IStorageManager
        public void startCheckpoint(int numTries) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public boolean needsCheckpoint() throws RemoteException {
            return false;
        }

        @Override // android.os.storage.IStorageManager
        public void abortChanges(String message, boolean retry) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void clearUserKeyAuth(int userId, int serialNumber, byte[] token, byte[] secret) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void fixupAppDir(String path) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void disableAppDataIsolation(String pkgName, int pid, int userId) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void notifyAppIoBlocked(String volumeUuid, int uid, int tid, int reason) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public void notifyAppIoResumed(String volumeUuid, int uid, int tid, int reason) throws RemoteException {
        }

        @Override // android.os.storage.IStorageManager
        public PendingIntent getManageSpaceActivityIntent(String packageName, int requestCode) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IStorageManager
        public boolean isAppIoBlocked(String volumeUuid, int uid, int tid, int reason) throws RemoteException {
            return false;
        }

        @Override // android.os.storage.IStorageManager
        public int getExternalStorageMountMode(int uid, String packageName) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IStorageManager {
        public static final String DESCRIPTOR = "android.os.storage.IStorageManager";
        static final int TRANSACTION_abortChanges = 88;
        static final int TRANSACTION_abortIdleMaintenance = 81;
        static final int TRANSACTION_addUserKeyAuth = 71;
        static final int TRANSACTION_allocateBytes = 79;
        static final int TRANSACTION_benchmark = 60;
        static final int TRANSACTION_changeEncryptionPassword = 29;
        static final int TRANSACTION_clearPassword = 38;
        static final int TRANSACTION_clearUserKeyAuth = 89;
        static final int TRANSACTION_commitChanges = 84;
        static final int TRANSACTION_createUserKey = 62;
        static final int TRANSACTION_decryptStorage = 27;
        static final int TRANSACTION_destroyUserKey = 63;
        static final int TRANSACTION_destroyUserStorage = 68;
        static final int TRANSACTION_disableAppDataIsolation = 91;
        static final int TRANSACTION_encryptStorage = 28;
        static final int TRANSACTION_fixateNewestUserKeyAuth = 72;
        static final int TRANSACTION_fixupAppDir = 90;
        static final int TRANSACTION_forgetAllVolumes = 57;
        static final int TRANSACTION_forgetVolume = 56;
        static final int TRANSACTION_format = 50;
        static final int TRANSACTION_fstrim = 73;
        static final int TRANSACTION_getAllocatableBytes = 78;
        static final int TRANSACTION_getCacheQuotaBytes = 76;
        static final int TRANSACTION_getCacheSizeBytes = 77;
        static final int TRANSACTION_getDisks = 45;
        static final int TRANSACTION_getEncryptionState = 32;
        static final int TRANSACTION_getExternalStorageMountMode = 96;
        static final int TRANSACTION_getField = 40;
        static final int TRANSACTION_getManageSpaceActivityIntent = 94;
        static final int TRANSACTION_getMountedObbPath = 25;
        static final int TRANSACTION_getPassword = 37;
        static final int TRANSACTION_getPasswordType = 36;
        static final int TRANSACTION_getPrimaryStorageUuid = 58;
        static final int TRANSACTION_getVolumeList = 30;
        static final int TRANSACTION_getVolumeRecords = 47;
        static final int TRANSACTION_getVolumes = 46;
        static final int TRANSACTION_isAppIoBlocked = 95;
        static final int TRANSACTION_isConvertibleToFBE = 69;
        static final int TRANSACTION_isObbMounted = 24;
        static final int TRANSACTION_isUserKeyUnlocked = 66;
        static final int TRANSACTION_lastMaintenance = 42;
        static final int TRANSACTION_lockUserKey = 65;
        static final int TRANSACTION_mkdirs = 35;
        static final int TRANSACTION_mount = 48;
        static final int TRANSACTION_mountObb = 22;
        static final int TRANSACTION_mountProxyFileDescriptorBridge = 74;
        static final int TRANSACTION_needsCheckpoint = 87;
        static final int TRANSACTION_notifyAppIoBlocked = 92;
        static final int TRANSACTION_notifyAppIoResumed = 93;
        static final int TRANSACTION_openProxyFileDescriptor = 75;
        static final int TRANSACTION_partitionMixed = 53;
        static final int TRANSACTION_partitionPrivate = 52;
        static final int TRANSACTION_partitionPublic = 51;
        static final int TRANSACTION_prepareUserStorage = 67;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_runIdleMaintenance = 80;
        static final int TRANSACTION_runMaintenance = 43;
        static final int TRANSACTION_setDebugFlags = 61;
        static final int TRANSACTION_setField = 39;
        static final int TRANSACTION_setPrimaryStorageUuid = 59;
        static final int TRANSACTION_setVolumeNickname = 54;
        static final int TRANSACTION_setVolumeUserFlags = 55;
        static final int TRANSACTION_shutdown = 20;
        static final int TRANSACTION_startCheckpoint = 86;
        static final int TRANSACTION_supportsCheckpoint = 85;
        static final int TRANSACTION_unlockUserKey = 64;
        static final int TRANSACTION_unmount = 49;
        static final int TRANSACTION_unmountObb = 23;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_verifyEncryptionPassword = 33;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStorageManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IStorageManager)) {
                return new Proxy(obj);
            }
            return (IStorageManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerListener";
                case 2:
                    return "unregisterListener";
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 21:
                case 26:
                case 31:
                case 34:
                case 41:
                case 44:
                case 70:
                case 82:
                case 83:
                default:
                    return null;
                case 20:
                    return "shutdown";
                case 22:
                    return "mountObb";
                case 23:
                    return "unmountObb";
                case 24:
                    return "isObbMounted";
                case 25:
                    return "getMountedObbPath";
                case 27:
                    return "decryptStorage";
                case 28:
                    return "encryptStorage";
                case 29:
                    return "changeEncryptionPassword";
                case 30:
                    return "getVolumeList";
                case 32:
                    return "getEncryptionState";
                case 33:
                    return "verifyEncryptionPassword";
                case 35:
                    return "mkdirs";
                case 36:
                    return "getPasswordType";
                case 37:
                    return "getPassword";
                case 38:
                    return "clearPassword";
                case 39:
                    return "setField";
                case 40:
                    return "getField";
                case 42:
                    return "lastMaintenance";
                case 43:
                    return "runMaintenance";
                case 45:
                    return "getDisks";
                case 46:
                    return "getVolumes";
                case 47:
                    return "getVolumeRecords";
                case 48:
                    return "mount";
                case 49:
                    return "unmount";
                case 50:
                    return Telephony.CellBroadcasts.MESSAGE_FORMAT;
                case 51:
                    return "partitionPublic";
                case 52:
                    return "partitionPrivate";
                case 53:
                    return "partitionMixed";
                case 54:
                    return "setVolumeNickname";
                case 55:
                    return "setVolumeUserFlags";
                case 56:
                    return "forgetVolume";
                case 57:
                    return "forgetAllVolumes";
                case 58:
                    return "getPrimaryStorageUuid";
                case 59:
                    return "setPrimaryStorageUuid";
                case 60:
                    return "benchmark";
                case 61:
                    return "setDebugFlags";
                case 62:
                    return "createUserKey";
                case 63:
                    return "destroyUserKey";
                case 64:
                    return "unlockUserKey";
                case 65:
                    return "lockUserKey";
                case 66:
                    return "isUserKeyUnlocked";
                case 67:
                    return "prepareUserStorage";
                case 68:
                    return "destroyUserStorage";
                case 69:
                    return "isConvertibleToFBE";
                case 71:
                    return "addUserKeyAuth";
                case 72:
                    return "fixateNewestUserKeyAuth";
                case 73:
                    return "fstrim";
                case 74:
                    return "mountProxyFileDescriptorBridge";
                case 75:
                    return "openProxyFileDescriptor";
                case 76:
                    return "getCacheQuotaBytes";
                case 77:
                    return "getCacheSizeBytes";
                case 78:
                    return "getAllocatableBytes";
                case 79:
                    return "allocateBytes";
                case 80:
                    return "runIdleMaintenance";
                case 81:
                    return "abortIdleMaintenance";
                case 84:
                    return "commitChanges";
                case 85:
                    return "supportsCheckpoint";
                case 86:
                    return "startCheckpoint";
                case 87:
                    return "needsCheckpoint";
                case 88:
                    return "abortChanges";
                case 89:
                    return "clearUserKeyAuth";
                case 90:
                    return "fixupAppDir";
                case 91:
                    return "disableAppDataIsolation";
                case 92:
                    return "notifyAppIoBlocked";
                case 93:
                    return "notifyAppIoResumed";
                case 94:
                    return "getManageSpaceActivityIntent";
                case 95:
                    return "isAppIoBlocked";
                case 96:
                    return "getExternalStorageMountMode";
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ObbInfo _arg5;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IStorageEventListener _arg0 = IStorageEventListener.Stub.asInterface(data.readStrongBinder());
                            registerListener(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IStorageEventListener _arg02 = IStorageEventListener.Stub.asInterface(data.readStrongBinder());
                            unregisterListener(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 21:
                        case 26:
                        case 31:
                        case 34:
                        case 41:
                        case 44:
                        case 70:
                        case 82:
                        case 83:
                        default:
                            return super.onTransact(code, data, reply, flags);
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IStorageShutdownObserver _arg03 = IStorageShutdownObserver.Stub.asInterface(data.readStrongBinder());
                            shutdown(_arg03);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _arg12 = data.readString();
                            String _arg2 = data.readString();
                            IObbActionListener _arg3 = IObbActionListener.Stub.asInterface(data.readStrongBinder());
                            int _arg4 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg5 = ObbInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            mountObb(_arg04, _arg12, _arg2, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            IObbActionListener _arg22 = IObbActionListener.Stub.asInterface(data.readStrongBinder());
                            int _arg32 = data.readInt();
                            unmountObb(_arg05, _arg1, _arg22, _arg32);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            boolean isObbMounted = isObbMounted(_arg06);
                            reply.writeNoException();
                            reply.writeInt(isObbMounted ? 1 : 0);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _result = getMountedObbPath(_arg07);
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            int _result2 = decryptStorage(_arg08);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            int _result3 = encryptStorage(_arg09, data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _result4 = changeEncryptionPassword(_arg010, data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            String _arg13 = data.readString();
                            int _arg23 = data.readInt();
                            StorageVolume[] _result5 = getVolumeList(_arg011, _arg13, _arg23);
                            reply.writeNoException();
                            reply.writeTypedArray(_result5, 1);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _result6 = getEncryptionState();
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _result7 = verifyEncryptionPassword(_arg012);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            mkdirs(_arg013, data.readString());
                            reply.writeNoException();
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            int _result8 = getPasswordType();
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _result9 = getPassword();
                            reply.writeNoException();
                            reply.writeString(_result9);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            clearPassword();
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            setField(_arg014, data.readString());
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            String _result10 = getField(_arg015);
                            reply.writeNoException();
                            reply.writeString(_result10);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            long _result11 = lastMaintenance();
                            reply.writeNoException();
                            reply.writeLong(_result11);
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            runMaintenance();
                            reply.writeNoException();
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            DiskInfo[] _result12 = getDisks();
                            reply.writeNoException();
                            reply.writeTypedArray(_result12, 1);
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            VolumeInfo[] _result13 = getVolumes(_arg016);
                            reply.writeNoException();
                            reply.writeTypedArray(_result13, 1);
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            VolumeRecord[] _result14 = getVolumeRecords(_arg017);
                            reply.writeNoException();
                            reply.writeTypedArray(_result14, 1);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            mount(_arg018);
                            reply.writeNoException();
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            unmount(_arg019);
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            format(_arg020);
                            reply.writeNoException();
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            partitionPublic(_arg021);
                            reply.writeNoException();
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            partitionPrivate(_arg022);
                            reply.writeNoException();
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            partitionMixed(_arg023, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            setVolumeNickname(_arg024, data.readString());
                            reply.writeNoException();
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg025 = data.readString();
                            int _arg14 = data.readInt();
                            int _arg24 = data.readInt();
                            setVolumeUserFlags(_arg025, _arg14, _arg24);
                            reply.writeNoException();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            forgetVolume(_arg026);
                            reply.writeNoException();
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            forgetAllVolumes();
                            reply.writeNoException();
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            String _result15 = getPrimaryStorageUuid();
                            reply.writeNoException();
                            reply.writeString(_result15);
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg027 = data.readString();
                            setPrimaryStorageUuid(_arg027, IPackageMoveObserver.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            benchmark(_arg028, IVoldTaskListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            setDebugFlags(_arg029, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 62:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            int _arg15 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            createUserKey(_arg030, _arg15, _arg1);
                            reply.writeNoException();
                            return true;
                        case 63:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg031 = data.readInt();
                            destroyUserKey(_arg031);
                            reply.writeNoException();
                            return true;
                        case 64:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg032 = data.readInt();
                            int _arg16 = data.readInt();
                            byte[] _arg25 = data.createByteArray();
                            byte[] _arg33 = data.createByteArray();
                            unlockUserKey(_arg032, _arg16, _arg25, _arg33);
                            reply.writeNoException();
                            return true;
                        case 65:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg033 = data.readInt();
                            lockUserKey(_arg033);
                            reply.writeNoException();
                            return true;
                        case 66:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg034 = data.readInt();
                            boolean isUserKeyUnlocked = isUserKeyUnlocked(_arg034);
                            reply.writeNoException();
                            reply.writeInt(isUserKeyUnlocked ? 1 : 0);
                            return true;
                        case 67:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            int _arg17 = data.readInt();
                            int _arg26 = data.readInt();
                            int _arg34 = data.readInt();
                            prepareUserStorage(_arg035, _arg17, _arg26, _arg34);
                            reply.writeNoException();
                            return true;
                        case 68:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            int _arg18 = data.readInt();
                            int _arg27 = data.readInt();
                            destroyUserStorage(_arg036, _arg18, _arg27);
                            reply.writeNoException();
                            return true;
                        case 69:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isConvertibleToFBE = isConvertibleToFBE();
                            reply.writeNoException();
                            reply.writeInt(isConvertibleToFBE ? 1 : 0);
                            return true;
                        case 71:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg037 = data.readInt();
                            int _arg19 = data.readInt();
                            byte[] _arg28 = data.createByteArray();
                            byte[] _arg35 = data.createByteArray();
                            addUserKeyAuth(_arg037, _arg19, _arg28, _arg35);
                            reply.writeNoException();
                            return true;
                        case 72:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg038 = data.readInt();
                            fixateNewestUserKeyAuth(_arg038);
                            reply.writeNoException();
                            return true;
                        case 73:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg039 = data.readInt();
                            fstrim(_arg039, IVoldTaskListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 74:
                            data.enforceInterface(DESCRIPTOR);
                            AppFuseMount _result16 = mountProxyFileDescriptorBridge();
                            reply.writeNoException();
                            if (_result16 != null) {
                                reply.writeInt(1);
                                _result16.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 75:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg040 = data.readInt();
                            int _arg110 = data.readInt();
                            int _arg29 = data.readInt();
                            ParcelFileDescriptor _result17 = openProxyFileDescriptor(_arg040, _arg110, _arg29);
                            reply.writeNoException();
                            if (_result17 != null) {
                                reply.writeInt(1);
                                _result17.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 76:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            long _result18 = getCacheQuotaBytes(_arg041, data.readInt());
                            reply.writeNoException();
                            reply.writeLong(_result18);
                            return true;
                        case 77:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            long _result19 = getCacheSizeBytes(_arg042, data.readInt());
                            reply.writeNoException();
                            reply.writeLong(_result19);
                            return true;
                        case 78:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            int _arg111 = data.readInt();
                            String _arg210 = data.readString();
                            long _result20 = getAllocatableBytes(_arg043, _arg111, _arg210);
                            reply.writeNoException();
                            reply.writeLong(_result20);
                            return true;
                        case 79:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg044 = data.readString();
                            long _arg112 = data.readLong();
                            int _arg211 = data.readInt();
                            String _arg36 = data.readString();
                            allocateBytes(_arg044, _arg112, _arg211, _arg36);
                            reply.writeNoException();
                            return true;
                        case 80:
                            data.enforceInterface(DESCRIPTOR);
                            runIdleMaintenance();
                            reply.writeNoException();
                            return true;
                        case 81:
                            data.enforceInterface(DESCRIPTOR);
                            abortIdleMaintenance();
                            reply.writeNoException();
                            return true;
                        case 84:
                            data.enforceInterface(DESCRIPTOR);
                            commitChanges();
                            reply.writeNoException();
                            return true;
                        case 85:
                            data.enforceInterface(DESCRIPTOR);
                            boolean supportsCheckpoint = supportsCheckpoint();
                            reply.writeNoException();
                            reply.writeInt(supportsCheckpoint ? 1 : 0);
                            return true;
                        case 86:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg045 = data.readInt();
                            startCheckpoint(_arg045);
                            reply.writeNoException();
                            return true;
                        case 87:
                            data.enforceInterface(DESCRIPTOR);
                            boolean needsCheckpoint = needsCheckpoint();
                            reply.writeNoException();
                            reply.writeInt(needsCheckpoint ? 1 : 0);
                            return true;
                        case 88:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg046 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            abortChanges(_arg046, _arg1);
                            reply.writeNoException();
                            return true;
                        case 89:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg047 = data.readInt();
                            int _arg113 = data.readInt();
                            byte[] _arg212 = data.createByteArray();
                            byte[] _arg37 = data.createByteArray();
                            clearUserKeyAuth(_arg047, _arg113, _arg212, _arg37);
                            reply.writeNoException();
                            return true;
                        case 90:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg048 = data.readString();
                            fixupAppDir(_arg048);
                            reply.writeNoException();
                            return true;
                        case 91:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg049 = data.readString();
                            int _arg114 = data.readInt();
                            int _arg213 = data.readInt();
                            disableAppDataIsolation(_arg049, _arg114, _arg213);
                            reply.writeNoException();
                            return true;
                        case 92:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg050 = data.readString();
                            int _arg115 = data.readInt();
                            int _arg214 = data.readInt();
                            int _arg38 = data.readInt();
                            notifyAppIoBlocked(_arg050, _arg115, _arg214, _arg38);
                            reply.writeNoException();
                            return true;
                        case 93:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg051 = data.readString();
                            int _arg116 = data.readInt();
                            int _arg215 = data.readInt();
                            int _arg39 = data.readInt();
                            notifyAppIoResumed(_arg051, _arg116, _arg215, _arg39);
                            reply.writeNoException();
                            return true;
                        case 94:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg052 = data.readString();
                            PendingIntent _result21 = getManageSpaceActivityIntent(_arg052, data.readInt());
                            reply.writeNoException();
                            if (_result21 != null) {
                                reply.writeInt(1);
                                _result21.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 95:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg053 = data.readString();
                            int _arg117 = data.readInt();
                            int _arg216 = data.readInt();
                            int _arg310 = data.readInt();
                            boolean isAppIoBlocked = isAppIoBlocked(_arg053, _arg117, _arg216, _arg310);
                            reply.writeNoException();
                            reply.writeInt(isAppIoBlocked ? 1 : 0);
                            return true;
                        case 96:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg054 = data.readInt();
                            int _result22 = getExternalStorageMountMode(_arg054, data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result22);
                            return true;
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IStorageManager {
            public static IStorageManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.os.storage.IStorageManager
            public void registerListener(IStorageEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void unregisterListener(IStorageEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void shutdown(IStorageShutdownObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().shutdown(observer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void mountObb(String rawPath, String canonicalPath, String key, IObbActionListener token, int nonce, ObbInfo obbInfo) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(rawPath);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(canonicalPath);
                    try {
                        _data.writeString(key);
                        _data.writeStrongBinder(token != null ? token.asBinder() : null);
                        try {
                            _data.writeInt(nonce);
                            if (obbInfo != null) {
                                _data.writeInt(1);
                                obbInfo.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            try {
                                boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().mountObb(rawPath, canonicalPath, key, token, nonce, obbInfo);
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.storage.IStorageManager
            public void unmountObb(String rawPath, boolean force, IObbActionListener token, int nonce) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(rawPath);
                    _data.writeInt(force ? 1 : 0);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeInt(nonce);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unmountObb(rawPath, force, token, nonce);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public boolean isObbMounted(String rawPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(rawPath);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isObbMounted(rawPath);
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

            @Override // android.os.storage.IStorageManager
            public String getMountedObbPath(String rawPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(rawPath);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMountedObbPath(rawPath);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public int decryptStorage(String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(password);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().decryptStorage(password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public int encryptStorage(int type, String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeString(password);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().encryptStorage(type, password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public int changeEncryptionPassword(int type, String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeString(password);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().changeEncryptionPassword(type, password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public StorageVolume[] getVolumeList(int uid, String packageName, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeList(uid, packageName, flags);
                    }
                    _reply.readException();
                    StorageVolume[] _result = (StorageVolume[]) _reply.createTypedArray(StorageVolume.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public int getEncryptionState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEncryptionState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public int verifyEncryptionPassword(String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(password);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyEncryptionPassword(password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void mkdirs(String callingPkg, String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().mkdirs(callingPkg, path);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public int getPasswordType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPasswordType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public String getPassword() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPassword();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void clearPassword() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearPassword();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void setField(String field, String contents) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(field);
                    _data.writeString(contents);
                    boolean _status = this.mRemote.transact(39, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setField(field, contents);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public String getField(String field) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(field);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getField(field);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public long lastMaintenance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().lastMaintenance();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void runMaintenance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().runMaintenance();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public DiskInfo[] getDisks() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisks();
                    }
                    _reply.readException();
                    DiskInfo[] _result = (DiskInfo[]) _reply.createTypedArray(DiskInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public VolumeInfo[] getVolumes(int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumes(flags);
                    }
                    _reply.readException();
                    VolumeInfo[] _result = (VolumeInfo[]) _reply.createTypedArray(VolumeInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public VolumeRecord[] getVolumeRecords(int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeRecords(flags);
                    }
                    _reply.readException();
                    VolumeRecord[] _result = (VolumeRecord[]) _reply.createTypedArray(VolumeRecord.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void mount(String volId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volId);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().mount(volId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void unmount(String volId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volId);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unmount(volId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void format(String volId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volId);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().format(volId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void partitionPublic(String diskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(diskId);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().partitionPublic(diskId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void partitionPrivate(String diskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(diskId);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().partitionPrivate(diskId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void partitionMixed(String diskId, int ratio) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(diskId);
                    _data.writeInt(ratio);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().partitionMixed(diskId, ratio);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void setVolumeNickname(String fsUuid, String nickname) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fsUuid);
                    _data.writeString(nickname);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVolumeNickname(fsUuid, nickname);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void setVolumeUserFlags(String fsUuid, int flags, int mask) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fsUuid);
                    _data.writeInt(flags);
                    _data.writeInt(mask);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVolumeUserFlags(fsUuid, flags, mask);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void forgetVolume(String fsUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fsUuid);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().forgetVolume(fsUuid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void forgetAllVolumes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().forgetAllVolumes();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public String getPrimaryStorageUuid() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrimaryStorageUuid();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void setPrimaryStorageUuid(String volumeUuid, IPackageMoveObserver callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPrimaryStorageUuid(volumeUuid, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void benchmark(String volId, IVoldTaskListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().benchmark(volId, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void setDebugFlags(int flags, int mask) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeInt(mask);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDebugFlags(flags, mask);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void createUserKey(int userId, int serialNumber, boolean ephemeral) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(serialNumber);
                    _data.writeInt(ephemeral ? 1 : 0);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createUserKey(userId, serialNumber, ephemeral);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void destroyUserKey(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyUserKey(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void unlockUserKey(int userId, int serialNumber, byte[] token, byte[] secret) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(serialNumber);
                    _data.writeByteArray(token);
                    _data.writeByteArray(secret);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unlockUserKey(userId, serialNumber, token, secret);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void lockUserKey(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().lockUserKey(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public boolean isUserKeyUnlocked(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserKeyUnlocked(userId);
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

            @Override // android.os.storage.IStorageManager
            public void prepareUserStorage(String volumeUuid, int userId, int serialNumber, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(userId);
                    _data.writeInt(serialNumber);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().prepareUserStorage(volumeUuid, userId, serialNumber, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void destroyUserStorage(String volumeUuid, int userId, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(userId);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyUserStorage(volumeUuid, userId, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public boolean isConvertibleToFBE() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConvertibleToFBE();
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

            @Override // android.os.storage.IStorageManager
            public void addUserKeyAuth(int userId, int serialNumber, byte[] token, byte[] secret) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(serialNumber);
                    _data.writeByteArray(token);
                    _data.writeByteArray(secret);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addUserKeyAuth(userId, serialNumber, token, secret);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void fixateNewestUserKeyAuth(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().fixateNewestUserKeyAuth(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void fstrim(int flags, IVoldTaskListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().fstrim(flags, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public AppFuseMount mountProxyFileDescriptorBridge() throws RemoteException {
                AppFuseMount _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mountProxyFileDescriptorBridge();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AppFuseMount.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public ParcelFileDescriptor openProxyFileDescriptor(int mountPointId, int fileId, int mode) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mountPointId);
                    _data.writeInt(fileId);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openProxyFileDescriptor(mountPointId, fileId, mode);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public long getCacheQuotaBytes(String volumeUuid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCacheQuotaBytes(volumeUuid, uid);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public long getCacheSizeBytes(String volumeUuid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCacheSizeBytes(volumeUuid, uid);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public long getAllocatableBytes(String volumeUuid, int flags, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(flags);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllocatableBytes(volumeUuid, flags, callingPackage);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void allocateBytes(String volumeUuid, long bytes, int flags, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeLong(bytes);
                    _data.writeInt(flags);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allocateBytes(volumeUuid, bytes, flags, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void runIdleMaintenance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().runIdleMaintenance();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void abortIdleMaintenance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abortIdleMaintenance();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void commitChanges() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().commitChanges();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public boolean supportsCheckpoint() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportsCheckpoint();
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

            @Override // android.os.storage.IStorageManager
            public void startCheckpoint(int numTries) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(numTries);
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startCheckpoint(numTries);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public boolean needsCheckpoint() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().needsCheckpoint();
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

            @Override // android.os.storage.IStorageManager
            public void abortChanges(String message, boolean retry) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(message);
                    _data.writeInt(retry ? 1 : 0);
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abortChanges(message, retry);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void clearUserKeyAuth(int userId, int serialNumber, byte[] token, byte[] secret) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(serialNumber);
                    _data.writeByteArray(token);
                    _data.writeByteArray(secret);
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearUserKeyAuth(userId, serialNumber, token, secret);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void fixupAppDir(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().fixupAppDir(path);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void disableAppDataIsolation(String pkgName, int pid, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    _data.writeInt(pid);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableAppDataIsolation(pkgName, pid, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void notifyAppIoBlocked(String volumeUuid, int uid, int tid, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(uid);
                    _data.writeInt(tid);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyAppIoBlocked(volumeUuid, uid, tid, reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public void notifyAppIoResumed(String volumeUuid, int uid, int tid, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(uid);
                    _data.writeInt(tid);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyAppIoResumed(volumeUuid, uid, tid, reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public PendingIntent getManageSpaceActivityIntent(String packageName, int requestCode) throws RemoteException {
                PendingIntent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(requestCode);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManageSpaceActivityIntent(packageName, requestCode);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PendingIntent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IStorageManager
            public boolean isAppIoBlocked(String volumeUuid, int uid, int tid, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    _data.writeInt(uid);
                    _data.writeInt(tid);
                    _data.writeInt(reason);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAppIoBlocked(volumeUuid, uid, tid, reason);
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

            @Override // android.os.storage.IStorageManager
            public int getExternalStorageMountMode(int uid, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(96, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExternalStorageMountMode(uid, packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStorageManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IStorageManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
