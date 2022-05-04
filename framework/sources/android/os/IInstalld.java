package android.os;

import android.os.storage.CrateMetadata;
import java.io.FileDescriptor;
/* loaded from: classes2.dex */
public interface IInstalld extends IInterface {
    public static final int FLAG_CLEAR_APP_DATA_KEEP_ART_PROFILES = 131072;
    public static final int FLAG_CLEAR_CACHE_ONLY = 16;
    public static final int FLAG_CLEAR_CODE_CACHE_ONLY = 32;
    public static final int FLAG_FORCE = 8192;
    public static final int FLAG_FREE_CACHE_NOOP = 1024;
    public static final int FLAG_FREE_CACHE_V2 = 256;
    public static final int FLAG_FREE_CACHE_V2_DEFY_QUOTA = 512;
    public static final int FLAG_STORAGE_CE = 2;
    public static final int FLAG_STORAGE_DE = 1;
    public static final int FLAG_STORAGE_EXTERNAL = 4;
    public static final int FLAG_USE_QUOTA = 4096;

    void assertFsverityRootHashMatches(String str, byte[] bArr) throws RemoteException;

    void clearAppData(String str, String str2, int i, int i2, long j) throws RemoteException;

    void clearAppProfiles(String str, String str2) throws RemoteException;

    boolean compileLayouts(String str, String str2, String str3, int i) throws RemoteException;

    boolean copySystemProfile(String str, int i, String str2, String str3) throws RemoteException;

    CreateAppDataResult createAppData(CreateAppDataArgs createAppDataArgs) throws RemoteException;

    CreateAppDataResult[] createAppDataBatched(CreateAppDataArgs[] createAppDataArgsArr) throws RemoteException;

    void createOatDir(String str, String str2) throws RemoteException;

    boolean createProfileSnapshot(int i, String str, String str2, String str3) throws RemoteException;

    void createUserData(String str, int i, int i2, int i3) throws RemoteException;

    long deleteOdex(String str, String str2, String str3) throws RemoteException;

    void destroyAppData(String str, String str2, int i, int i2, long j) throws RemoteException;

    void destroyAppDataSnapshot(String str, String str2, int i, long j, int i2, int i3) throws RemoteException;

    void destroyAppProfiles(String str) throws RemoteException;

    void destroyCeSnapshotsNotSpecified(String str, int i, int[] iArr) throws RemoteException;

    void destroyProfileSnapshot(String str, String str2) throws RemoteException;

    void destroyUserData(String str, int i, int i2) throws RemoteException;

    void dexopt(String str, int i, String str2, String str3, int i2, String str4, int i3, String str5, String str6, String str7, String str8, boolean z, int i4, String str9, String str10, String str11) throws RemoteException;

    boolean dumpProfiles(int i, String str, String str2, String str3) throws RemoteException;

    void fixupAppData(String str, int i) throws RemoteException;

    void freeCache(String str, long j, long j2, int i) throws RemoteException;

    CrateMetadata[] getAppCrates(String str, String[] strArr, int i) throws RemoteException;

    long[] getAppSize(String str, String[] strArr, int i, int i2, int i3, long[] jArr, String[] strArr2) throws RemoteException;

    long[] getExternalSize(String str, int i, int i2, int[] iArr) throws RemoteException;

    CrateMetadata[] getUserCrates(String str, int i) throws RemoteException;

    long[] getUserSize(String str, int i, int i2, int[] iArr) throws RemoteException;

    byte[] hashSecondaryDexFile(String str, String str2, int i, String str3, int i2) throws RemoteException;

    void installApkVerity(String str, FileDescriptor fileDescriptor, int i) throws RemoteException;

    void invalidateMounts() throws RemoteException;

    boolean isQuotaSupported(String str) throws RemoteException;

    void linkFile(String str, String str2, String str3) throws RemoteException;

    void linkNativeLibraryDirectory(String str, String str2, String str3, int i) throws RemoteException;

    int mergeProfiles(int i, String str, String str2) throws RemoteException;

    void migrateAppData(String str, String str2, int i, int i2) throws RemoteException;

    void migrateLegacyObbData() throws RemoteException;

    void moveAb(String str, String str2, String str3) throws RemoteException;

    void moveCompleteApp(String str, String str2, String str3, int i, String str4, int i2, String str5) throws RemoteException;

    void onPrivateVolumeRemoved(String str) throws RemoteException;

    PersistableBundle oplusCommonInterface(String str, PersistableBundle persistableBundle) throws RemoteException;

    boolean prepareAppProfile(String str, int i, int i2, String str2, String str3, String str4) throws RemoteException;

    boolean reconcileSecondaryDexFile(String str, String str2, int i, String[] strArr, String str3, int i2) throws RemoteException;

    void restoreAppDataSnapshot(String str, String str2, int i, String str3, int i2, int i3, int i4) throws RemoteException;

    void restoreconAppData(String str, String str2, int i, int i2, int i3, String str3) throws RemoteException;

    void rmPackageDir(String str) throws RemoteException;

    void rmdex(String str, String str2) throws RemoteException;

    void setAppQuota(String str, int i, int i2, long j) throws RemoteException;

    long snapshotAppData(String str, String str2, int i, int i2, int i3) throws RemoteException;

    void tryMountDataMirror(String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IInstalld {
        @Override // android.os.IInstalld
        public void createUserData(String uuid, int userId, int userSerial, int flags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyUserData(String uuid, int userId, int flags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public CreateAppDataResult createAppData(CreateAppDataArgs args) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public CreateAppDataResult[] createAppDataBatched(CreateAppDataArgs[] args) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public void restoreconAppData(String uuid, String packageName, int userId, int flags, int appId, String seInfo) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void migrateAppData(String uuid, String packageName, int userId, int flags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void clearAppData(String uuid, String packageName, int userId, int flags, long ceDataInode) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyAppData(String uuid, String packageName, int userId, int flags, long ceDataInode) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void fixupAppData(String uuid, int flags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public long[] getAppSize(String uuid, String[] packageNames, int userId, int flags, int appId, long[] ceDataInodes, String[] codePaths) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public long[] getUserSize(String uuid, int userId, int flags, int[] appIds) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public long[] getExternalSize(String uuid, int userId, int flags, int[] appIds) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public CrateMetadata[] getAppCrates(String uuid, String[] packageNames, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public CrateMetadata[] getUserCrates(String uuid, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public void setAppQuota(String uuid, int userId, int appId, long cacheQuota) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void moveCompleteApp(String fromUuid, String toUuid, String packageName, int appId, String seInfo, int targetSdkVersion, String fromCodePath) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void dexopt(String apkPath, int uid, String packageName, String instructionSet, int dexoptNeeded, String outputPath, int dexFlags, String compilerFilter, String uuid, String sharedLibraries, String seInfo, boolean downgrade, int targetSdkVersion, String profileName, String dexMetadataPath, String compilationReason) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean compileLayouts(String apkPath, String packageName, String outDexFile, int uid) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void rmdex(String codePath, String instructionSet) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public int mergeProfiles(int uid, String packageName, String profileName) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInstalld
        public boolean dumpProfiles(int uid, String packageName, String profileName, String codePath) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public boolean copySystemProfile(String systemProfile, int uid, String packageName, String profileName) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void clearAppProfiles(String packageName, String profileName) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyAppProfiles(String packageName) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean createProfileSnapshot(int appId, String packageName, String profileName, String classpath) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void destroyProfileSnapshot(String packageName, String profileName) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void rmPackageDir(String packageDir) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void freeCache(String uuid, long targetFreeBytes, long cacheReservedBytes, int flags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void linkNativeLibraryDirectory(String uuid, String packageName, String nativeLibPath32, int userId) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void createOatDir(String oatDir, String instructionSet) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void linkFile(String relativePath, String fromBase, String toBase) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void moveAb(String apkPath, String instructionSet, String outputPath) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public long deleteOdex(String apkPath, String instructionSet, String outputPath) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInstalld
        public void installApkVerity(String filePath, FileDescriptor verityInput, int contentSize) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void assertFsverityRootHashMatches(String filePath, byte[] expectedHash) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean reconcileSecondaryDexFile(String dexPath, String pkgName, int uid, String[] isas, String volume_uuid, int storage_flag) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public byte[] hashSecondaryDexFile(String dexPath, String pkgName, int uid, String volumeUuid, int storageFlag) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public void invalidateMounts() throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean isQuotaSupported(String uuid) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public boolean prepareAppProfile(String packageName, int userId, int appId, String profileName, String codePath, String dexMetadata) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public long snapshotAppData(String uuid, String packageName, int userId, int snapshotId, int storageFlags) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInstalld
        public void restoreAppDataSnapshot(String uuid, String packageName, int appId, String seInfo, int user, int snapshotId, int storageflags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyAppDataSnapshot(String uuid, String packageName, int userId, long ceSnapshotInode, int snapshotId, int storageFlags) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyCeSnapshotsNotSpecified(String uuid, int userId, int[] retainSnapshotIds) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void tryMountDataMirror(String volumeUuid) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void onPrivateVolumeRemoved(String volumeUuid) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void migrateLegacyObbData() throws RemoteException {
        }

        @Override // android.os.IInstalld
        public PersistableBundle oplusCommonInterface(String funName, PersistableBundle args) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInstalld {
        public static final String DESCRIPTOR = "android.os.IInstalld";
        static final int TRANSACTION_assertFsverityRootHashMatches = 35;
        static final int TRANSACTION_clearAppData = 7;
        static final int TRANSACTION_clearAppProfiles = 23;
        static final int TRANSACTION_compileLayouts = 18;
        static final int TRANSACTION_copySystemProfile = 22;
        static final int TRANSACTION_createAppData = 3;
        static final int TRANSACTION_createAppDataBatched = 4;
        static final int TRANSACTION_createOatDir = 30;
        static final int TRANSACTION_createProfileSnapshot = 25;
        static final int TRANSACTION_createUserData = 1;
        static final int TRANSACTION_deleteOdex = 33;
        static final int TRANSACTION_destroyAppData = 8;
        static final int TRANSACTION_destroyAppDataSnapshot = 43;
        static final int TRANSACTION_destroyAppProfiles = 24;
        static final int TRANSACTION_destroyCeSnapshotsNotSpecified = 44;
        static final int TRANSACTION_destroyProfileSnapshot = 26;
        static final int TRANSACTION_destroyUserData = 2;
        static final int TRANSACTION_dexopt = 17;
        static final int TRANSACTION_dumpProfiles = 21;
        static final int TRANSACTION_fixupAppData = 9;
        static final int TRANSACTION_freeCache = 28;
        static final int TRANSACTION_getAppCrates = 13;
        static final int TRANSACTION_getAppSize = 10;
        static final int TRANSACTION_getExternalSize = 12;
        static final int TRANSACTION_getUserCrates = 14;
        static final int TRANSACTION_getUserSize = 11;
        static final int TRANSACTION_hashSecondaryDexFile = 37;
        static final int TRANSACTION_installApkVerity = 34;
        static final int TRANSACTION_invalidateMounts = 38;
        static final int TRANSACTION_isQuotaSupported = 39;
        static final int TRANSACTION_linkFile = 31;
        static final int TRANSACTION_linkNativeLibraryDirectory = 29;
        static final int TRANSACTION_mergeProfiles = 20;
        static final int TRANSACTION_migrateAppData = 6;
        static final int TRANSACTION_migrateLegacyObbData = 47;
        static final int TRANSACTION_moveAb = 32;
        static final int TRANSACTION_moveCompleteApp = 16;
        static final int TRANSACTION_onPrivateVolumeRemoved = 46;
        static final int TRANSACTION_oplusCommonInterface = 48;
        static final int TRANSACTION_prepareAppProfile = 40;
        static final int TRANSACTION_reconcileSecondaryDexFile = 36;
        static final int TRANSACTION_restoreAppDataSnapshot = 42;
        static final int TRANSACTION_restoreconAppData = 5;
        static final int TRANSACTION_rmPackageDir = 27;
        static final int TRANSACTION_rmdex = 19;
        static final int TRANSACTION_setAppQuota = 15;
        static final int TRANSACTION_snapshotAppData = 41;
        static final int TRANSACTION_tryMountDataMirror = 45;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstalld asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IInstalld)) {
                return new Proxy(obj);
            }
            return (IInstalld) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createUserData";
                case 2:
                    return "destroyUserData";
                case 3:
                    return "createAppData";
                case 4:
                    return "createAppDataBatched";
                case 5:
                    return "restoreconAppData";
                case 6:
                    return "migrateAppData";
                case 7:
                    return "clearAppData";
                case 8:
                    return "destroyAppData";
                case 9:
                    return "fixupAppData";
                case 10:
                    return "getAppSize";
                case 11:
                    return "getUserSize";
                case 12:
                    return "getExternalSize";
                case 13:
                    return "getAppCrates";
                case 14:
                    return "getUserCrates";
                case 15:
                    return "setAppQuota";
                case 16:
                    return "moveCompleteApp";
                case 17:
                    return "dexopt";
                case 18:
                    return "compileLayouts";
                case 19:
                    return "rmdex";
                case 20:
                    return "mergeProfiles";
                case 21:
                    return "dumpProfiles";
                case 22:
                    return "copySystemProfile";
                case 23:
                    return "clearAppProfiles";
                case 24:
                    return "destroyAppProfiles";
                case 25:
                    return "createProfileSnapshot";
                case 26:
                    return "destroyProfileSnapshot";
                case 27:
                    return "rmPackageDir";
                case 28:
                    return "freeCache";
                case 29:
                    return "linkNativeLibraryDirectory";
                case 30:
                    return "createOatDir";
                case 31:
                    return "linkFile";
                case 32:
                    return "moveAb";
                case 33:
                    return "deleteOdex";
                case 34:
                    return "installApkVerity";
                case 35:
                    return "assertFsverityRootHashMatches";
                case 36:
                    return "reconcileSecondaryDexFile";
                case 37:
                    return "hashSecondaryDexFile";
                case 38:
                    return "invalidateMounts";
                case 39:
                    return "isQuotaSupported";
                case 40:
                    return "prepareAppProfile";
                case 41:
                    return "snapshotAppData";
                case 42:
                    return "restoreAppDataSnapshot";
                case 43:
                    return "destroyAppDataSnapshot";
                case 44:
                    return "destroyCeSnapshotsNotSpecified";
                case 45:
                    return "tryMountDataMirror";
                case 46:
                    return "onPrivateVolumeRemoved";
                case 47:
                    return "migrateLegacyObbData";
                case 48:
                    return "oplusCommonInterface";
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
            CreateAppDataArgs _arg0;
            boolean _arg11;
            PersistableBundle _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            createUserData(_arg02, _arg12, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg22 = data.readInt();
                            destroyUserData(_arg03, _arg13, _arg22);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = CreateAppDataArgs.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            CreateAppDataResult _result = createAppData(_arg0);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            CreateAppDataArgs[] _arg04 = (CreateAppDataArgs[]) data.createTypedArray(CreateAppDataArgs.CREATOR);
                            CreateAppDataResult[] _result2 = createAppDataBatched(_arg04);
                            reply.writeNoException();
                            reply.writeTypedArray(_result2, 1);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            String _arg14 = data.readString();
                            int _arg23 = data.readInt();
                            int _arg32 = data.readInt();
                            int _arg4 = data.readInt();
                            String _arg5 = data.readString();
                            restoreconAppData(_arg05, _arg14, _arg23, _arg32, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            String _arg15 = data.readString();
                            int _arg24 = data.readInt();
                            int _arg33 = data.readInt();
                            migrateAppData(_arg06, _arg15, _arg24, _arg33);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _arg16 = data.readString();
                            int _arg25 = data.readInt();
                            int _arg34 = data.readInt();
                            long _arg42 = data.readLong();
                            clearAppData(_arg07, _arg16, _arg25, _arg34, _arg42);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            String _arg17 = data.readString();
                            int _arg26 = data.readInt();
                            int _arg35 = data.readInt();
                            long _arg43 = data.readLong();
                            destroyAppData(_arg08, _arg17, _arg26, _arg35, _arg43);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _arg18 = data.readInt();
                            fixupAppData(_arg09, _arg18);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            String[] _arg19 = data.createStringArray();
                            int _arg27 = data.readInt();
                            int _arg36 = data.readInt();
                            int _arg44 = data.readInt();
                            long[] _arg52 = data.createLongArray();
                            String[] _arg6 = data.createStringArray();
                            long[] _result3 = getAppSize(_arg010, _arg19, _arg27, _arg36, _arg44, _arg52, _arg6);
                            reply.writeNoException();
                            reply.writeLongArray(_result3);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            int _arg110 = data.readInt();
                            int _arg28 = data.readInt();
                            int[] _arg37 = data.createIntArray();
                            long[] _result4 = getUserSize(_arg011, _arg110, _arg28, _arg37);
                            reply.writeNoException();
                            reply.writeLongArray(_result4);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg111 = data.readInt();
                            int _arg29 = data.readInt();
                            int[] _arg38 = data.createIntArray();
                            long[] _result5 = getExternalSize(_arg012, _arg111, _arg29, _arg38);
                            reply.writeNoException();
                            reply.writeLongArray(_result5);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            String[] _arg112 = data.createStringArray();
                            int _arg210 = data.readInt();
                            CrateMetadata[] _result6 = getAppCrates(_arg013, _arg112, _arg210);
                            reply.writeNoException();
                            reply.writeTypedArray(_result6, 1);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            int _arg113 = data.readInt();
                            CrateMetadata[] _result7 = getUserCrates(_arg014, _arg113);
                            reply.writeNoException();
                            reply.writeTypedArray(_result7, 1);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            int _arg114 = data.readInt();
                            int _arg211 = data.readInt();
                            long _arg39 = data.readLong();
                            setAppQuota(_arg015, _arg114, _arg211, _arg39);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            String _arg115 = data.readString();
                            String _arg212 = data.readString();
                            int _arg310 = data.readInt();
                            String _arg45 = data.readString();
                            int _arg53 = data.readInt();
                            String _arg62 = data.readString();
                            moveCompleteApp(_arg016, _arg115, _arg212, _arg310, _arg45, _arg53, _arg62);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            int _arg116 = data.readInt();
                            String _arg213 = data.readString();
                            String _arg311 = data.readString();
                            int _arg46 = data.readInt();
                            String _arg54 = data.readString();
                            int _arg63 = data.readInt();
                            String _arg7 = data.readString();
                            String _arg8 = data.readString();
                            String _arg9 = data.readString();
                            String _arg10 = data.readString();
                            if (data.readInt() != 0) {
                                _arg11 = true;
                            } else {
                                _arg11 = false;
                            }
                            int _arg122 = data.readInt();
                            String _arg132 = data.readString();
                            String _arg142 = data.readString();
                            String _arg152 = data.readString();
                            dexopt(_arg017, _arg116, _arg213, _arg311, _arg46, _arg54, _arg63, _arg7, _arg8, _arg9, _arg10, _arg11, _arg122, _arg132, _arg142, _arg152);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            String _arg117 = data.readString();
                            String _arg214 = data.readString();
                            int _arg312 = data.readInt();
                            boolean compileLayouts = compileLayouts(_arg018, _arg117, _arg214, _arg312);
                            reply.writeNoException();
                            reply.writeInt(compileLayouts ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            String _arg118 = data.readString();
                            rmdex(_arg019, _arg118);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            String _arg119 = data.readString();
                            String _arg215 = data.readString();
                            int _result8 = mergeProfiles(_arg020, _arg119, _arg215);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            String _arg120 = data.readString();
                            String _arg216 = data.readString();
                            String _arg313 = data.readString();
                            boolean dumpProfiles = dumpProfiles(_arg021, _arg120, _arg216, _arg313);
                            reply.writeNoException();
                            reply.writeInt(dumpProfiles ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            int _arg121 = data.readInt();
                            String _arg217 = data.readString();
                            String _arg314 = data.readString();
                            boolean copySystemProfile = copySystemProfile(_arg022, _arg121, _arg217, _arg314);
                            reply.writeNoException();
                            reply.writeInt(copySystemProfile ? 1 : 0);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            String _arg123 = data.readString();
                            clearAppProfiles(_arg023, _arg123);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            destroyAppProfiles(_arg024);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            String _arg124 = data.readString();
                            String _arg218 = data.readString();
                            String _arg315 = data.readString();
                            boolean createProfileSnapshot = createProfileSnapshot(_arg025, _arg124, _arg218, _arg315);
                            reply.writeNoException();
                            reply.writeInt(createProfileSnapshot ? 1 : 0);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            String _arg125 = data.readString();
                            destroyProfileSnapshot(_arg026, _arg125);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg027 = data.readString();
                            rmPackageDir(_arg027);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            long _arg126 = data.readLong();
                            long _arg219 = data.readLong();
                            int _arg316 = data.readInt();
                            freeCache(_arg028, _arg126, _arg219, _arg316);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg029 = data.readString();
                            String _arg127 = data.readString();
                            String _arg220 = data.readString();
                            int _arg317 = data.readInt();
                            linkNativeLibraryDirectory(_arg029, _arg127, _arg220, _arg317);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            String _arg128 = data.readString();
                            createOatDir(_arg030, _arg128);
                            reply.writeNoException();
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            String _arg129 = data.readString();
                            String _arg221 = data.readString();
                            linkFile(_arg031, _arg129, _arg221);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            String _arg130 = data.readString();
                            String _arg222 = data.readString();
                            moveAb(_arg032, _arg130, _arg222);
                            reply.writeNoException();
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            String _arg131 = data.readString();
                            String _arg223 = data.readString();
                            long _result9 = deleteOdex(_arg033, _arg131, _arg223);
                            reply.writeNoException();
                            reply.writeLong(_result9);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            FileDescriptor _arg133 = data.readRawFileDescriptor();
                            int _arg224 = data.readInt();
                            installApkVerity(_arg034, _arg133, _arg224);
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            byte[] _arg134 = data.createByteArray();
                            assertFsverityRootHashMatches(_arg035, _arg134);
                            reply.writeNoException();
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            String _arg135 = data.readString();
                            int _arg225 = data.readInt();
                            String[] _arg318 = data.createStringArray();
                            String _arg47 = data.readString();
                            int _arg55 = data.readInt();
                            boolean reconcileSecondaryDexFile = reconcileSecondaryDexFile(_arg036, _arg135, _arg225, _arg318, _arg47, _arg55);
                            reply.writeNoException();
                            reply.writeInt(reconcileSecondaryDexFile ? 1 : 0);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg037 = data.readString();
                            String _arg136 = data.readString();
                            int _arg226 = data.readInt();
                            String _arg319 = data.readString();
                            int _arg48 = data.readInt();
                            byte[] _result10 = hashSecondaryDexFile(_arg037, _arg136, _arg226, _arg319, _arg48);
                            reply.writeNoException();
                            reply.writeByteArray(_result10);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            invalidateMounts();
                            reply.writeNoException();
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            boolean isQuotaSupported = isQuotaSupported(_arg038);
                            reply.writeNoException();
                            reply.writeInt(isQuotaSupported ? 1 : 0);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            int _arg137 = data.readInt();
                            int _arg227 = data.readInt();
                            String _arg320 = data.readString();
                            String _arg49 = data.readString();
                            String _arg56 = data.readString();
                            boolean prepareAppProfile = prepareAppProfile(_arg039, _arg137, _arg227, _arg320, _arg49, _arg56);
                            reply.writeNoException();
                            reply.writeInt(prepareAppProfile ? 1 : 0);
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            String _arg138 = data.readString();
                            int _arg228 = data.readInt();
                            int _arg321 = data.readInt();
                            int _arg410 = data.readInt();
                            long _result11 = snapshotAppData(_arg040, _arg138, _arg228, _arg321, _arg410);
                            reply.writeNoException();
                            reply.writeLong(_result11);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            String _arg139 = data.readString();
                            int _arg229 = data.readInt();
                            String _arg322 = data.readString();
                            int _arg411 = data.readInt();
                            int _arg57 = data.readInt();
                            int _arg64 = data.readInt();
                            restoreAppDataSnapshot(_arg041, _arg139, _arg229, _arg322, _arg411, _arg57, _arg64);
                            reply.writeNoException();
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            String _arg140 = data.readString();
                            int _arg230 = data.readInt();
                            long _arg323 = data.readLong();
                            int _arg412 = data.readInt();
                            int _arg58 = data.readInt();
                            destroyAppDataSnapshot(_arg042, _arg140, _arg230, _arg323, _arg412, _arg58);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            int _arg141 = data.readInt();
                            int[] _arg231 = data.createIntArray();
                            destroyCeSnapshotsNotSpecified(_arg043, _arg141, _arg231);
                            reply.writeNoException();
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg044 = data.readString();
                            tryMountDataMirror(_arg044);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg045 = data.readString();
                            onPrivateVolumeRemoved(_arg045);
                            reply.writeNoException();
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            migrateLegacyObbData();
                            reply.writeNoException();
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg046 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            PersistableBundle _result12 = oplusCommonInterface(_arg046, _arg1);
                            reply.writeNoException();
                            if (_result12 != null) {
                                reply.writeInt(1);
                                _result12.writeToParcel(reply, 1);
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
        public static class Proxy implements IInstalld {
            public static IInstalld sDefaultImpl;
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

            @Override // android.os.IInstalld
            public void createUserData(String uuid, int userId, int userSerial, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    _data.writeInt(userSerial);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createUserData(uuid, userId, userSerial, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void destroyUserData(String uuid, int userId, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyUserData(uuid, userId, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public CreateAppDataResult createAppData(CreateAppDataArgs args) throws RemoteException {
                CreateAppDataResult _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createAppData(args);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CreateAppDataResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public CreateAppDataResult[] createAppDataBatched(CreateAppDataArgs[] args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedArray(args, 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createAppDataBatched(args);
                    }
                    _reply.readException();
                    CreateAppDataResult[] _result = (CreateAppDataResult[]) _reply.createTypedArray(CreateAppDataResult.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void restoreconAppData(String uuid, String packageName, int userId, int flags, int appId, String seInfo) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeString(packageName);
                            try {
                                _data.writeInt(userId);
                                try {
                                    _data.writeInt(flags);
                                    try {
                                        _data.writeInt(appId);
                                        try {
                                            _data.writeString(seInfo);
                                            boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                                            if (_status || Stub.getDefaultImpl() == null) {
                                                _reply.readException();
                                                _reply.recycle();
                                                _data.recycle();
                                                return;
                                            }
                                            Stub.getDefaultImpl().restoreconAppData(uuid, packageName, userId, flags, appId, seInfo);
                                            _reply.recycle();
                                            _data.recycle();
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
                } catch (Throwable th8) {
                    th = th8;
                }
            }

            @Override // android.os.IInstalld
            public void migrateAppData(String uuid, String packageName, int userId, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().migrateAppData(uuid, packageName, userId, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void clearAppData(String uuid, String packageName, int userId, int flags, long ceDataInode) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeString(packageName);
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
                    _data.writeInt(userId);
                    try {
                        _data.writeInt(flags);
                        try {
                            _data.writeLong(ceDataInode);
                            boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().clearAppData(uuid, packageName, userId, flags, ceDataInode);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
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

            @Override // android.os.IInstalld
            public void destroyAppData(String uuid, String packageName, int userId, int flags, long ceDataInode) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeString(packageName);
                            try {
                                _data.writeInt(userId);
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
                }
                try {
                    _data.writeInt(flags);
                    try {
                        _data.writeLong(ceDataInode);
                        boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().destroyAppData(uuid, packageName, userId, flags, ceDataInode);
                        _reply.recycle();
                        _data.recycle();
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

            @Override // android.os.IInstalld
            public void fixupAppData(String uuid, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().fixupAppData(uuid, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public long[] getAppSize(String uuid, String[] packageNames, int userId, int flags, int appId, long[] ceDataInodes, String[] codePaths) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeStringArray(packageNames);
                            try {
                                _data.writeInt(userId);
                                try {
                                    _data.writeInt(flags);
                                    try {
                                        _data.writeInt(appId);
                                        _data.writeLongArray(ceDataInodes);
                                        _data.writeStringArray(codePaths);
                                        boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            long[] _result = _reply.createLongArray();
                                            _reply.recycle();
                                            _data.recycle();
                                            return _result;
                                        }
                                        long[] appSize = Stub.getDefaultImpl().getAppSize(uuid, packageNames, userId, flags, appId, ceDataInodes, codePaths);
                                        _reply.recycle();
                                        _data.recycle();
                                        return appSize;
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.os.IInstalld
            public long[] getUserSize(String uuid, int userId, int flags, int[] appIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    _data.writeInt(flags);
                    _data.writeIntArray(appIds);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSize(uuid, userId, flags, appIds);
                    }
                    _reply.readException();
                    long[] _result = _reply.createLongArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public long[] getExternalSize(String uuid, int userId, int flags, int[] appIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    _data.writeInt(flags);
                    _data.writeIntArray(appIds);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExternalSize(uuid, userId, flags, appIds);
                    }
                    _reply.readException();
                    long[] _result = _reply.createLongArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public CrateMetadata[] getAppCrates(String uuid, String[] packageNames, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeStringArray(packageNames);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppCrates(uuid, packageNames, userId);
                    }
                    _reply.readException();
                    CrateMetadata[] _result = (CrateMetadata[]) _reply.createTypedArray(CrateMetadata.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public CrateMetadata[] getUserCrates(String uuid, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserCrates(uuid, userId);
                    }
                    _reply.readException();
                    CrateMetadata[] _result = (CrateMetadata[]) _reply.createTypedArray(CrateMetadata.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void setAppQuota(String uuid, int userId, int appId, long cacheQuota) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    _data.writeInt(appId);
                    _data.writeLong(cacheQuota);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAppQuota(uuid, userId, appId, cacheQuota);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void moveCompleteApp(String fromUuid, String toUuid, String packageName, int appId, String seInfo, int targetSdkVersion, String fromCodePath) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(fromUuid);
                        try {
                            _data.writeString(toUuid);
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
                    _data.writeString(packageName);
                    try {
                        _data.writeInt(appId);
                        try {
                            _data.writeString(seInfo);
                            _data.writeInt(targetSdkVersion);
                            _data.writeString(fromCodePath);
                            boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().moveCompleteApp(fromUuid, toUuid, packageName, appId, seInfo, targetSdkVersion, fromCodePath);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
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

            @Override // android.os.IInstalld
            public void dexopt(String apkPath, int uid, String packageName, String instructionSet, int dexoptNeeded, String outputPath, int dexFlags, String compilerFilter, String uuid, String sharedLibraries, String seInfo, boolean downgrade, int targetSdkVersion, String profileName, String dexMetadataPath, String compilationReason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(apkPath);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(instructionSet);
                    _data.writeInt(dexoptNeeded);
                    _data.writeString(outputPath);
                    _data.writeInt(dexFlags);
                    _data.writeString(compilerFilter);
                    _data.writeString(uuid);
                    _data.writeString(sharedLibraries);
                    _data.writeString(seInfo);
                    _data.writeInt(downgrade ? 1 : 0);
                    _data.writeInt(targetSdkVersion);
                    _data.writeString(profileName);
                    _data.writeString(dexMetadataPath);
                    _data.writeString(compilationReason);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().dexopt(apkPath, uid, packageName, instructionSet, dexoptNeeded, outputPath, dexFlags, compilerFilter, uuid, sharedLibraries, seInfo, downgrade, targetSdkVersion, profileName, dexMetadataPath, compilationReason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean compileLayouts(String apkPath, String packageName, String outDexFile, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(apkPath);
                    _data.writeString(packageName);
                    _data.writeString(outDexFile);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().compileLayouts(apkPath, packageName, outDexFile, uid);
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

            @Override // android.os.IInstalld
            public void rmdex(String codePath, String instructionSet) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(codePath);
                    _data.writeString(instructionSet);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().rmdex(codePath, instructionSet);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public int mergeProfiles(int uid, String packageName, String profileName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(profileName);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mergeProfiles(uid, packageName, profileName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean dumpProfiles(int uid, String packageName, String profileName, String codePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(profileName);
                    _data.writeString(codePath);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpProfiles(uid, packageName, profileName, codePath);
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

            @Override // android.os.IInstalld
            public boolean copySystemProfile(String systemProfile, int uid, String packageName, String profileName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(systemProfile);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(profileName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().copySystemProfile(systemProfile, uid, packageName, profileName);
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

            @Override // android.os.IInstalld
            public void clearAppProfiles(String packageName, String profileName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(profileName);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearAppProfiles(packageName, profileName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void destroyAppProfiles(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyAppProfiles(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean createProfileSnapshot(int appId, String packageName, String profileName, String classpath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appId);
                    _data.writeString(packageName);
                    _data.writeString(profileName);
                    _data.writeString(classpath);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProfileSnapshot(appId, packageName, profileName, classpath);
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

            @Override // android.os.IInstalld
            public void destroyProfileSnapshot(String packageName, String profileName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(profileName);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyProfileSnapshot(packageName, profileName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void rmPackageDir(String packageDir) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageDir);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().rmPackageDir(packageDir);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void freeCache(String uuid, long targetFreeBytes, long cacheReservedBytes, int flags) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeLong(targetFreeBytes);
                            try {
                                _data.writeLong(cacheReservedBytes);
                                try {
                                    _data.writeInt(flags);
                                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().freeCache(uuid, targetFreeBytes, cacheReservedBytes, flags);
                                    _reply.recycle();
                                    _data.recycle();
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

            @Override // android.os.IInstalld
            public void linkNativeLibraryDirectory(String uuid, String packageName, String nativeLibPath32, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeString(packageName);
                    _data.writeString(nativeLibPath32);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().linkNativeLibraryDirectory(uuid, packageName, nativeLibPath32, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void createOatDir(String oatDir, String instructionSet) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(oatDir);
                    _data.writeString(instructionSet);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createOatDir(oatDir, instructionSet);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void linkFile(String relativePath, String fromBase, String toBase) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(relativePath);
                    _data.writeString(fromBase);
                    _data.writeString(toBase);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().linkFile(relativePath, fromBase, toBase);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void moveAb(String apkPath, String instructionSet, String outputPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(apkPath);
                    _data.writeString(instructionSet);
                    _data.writeString(outputPath);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().moveAb(apkPath, instructionSet, outputPath);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public long deleteOdex(String apkPath, String instructionSet, String outputPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(apkPath);
                    _data.writeString(instructionSet);
                    _data.writeString(outputPath);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteOdex(apkPath, instructionSet, outputPath);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void installApkVerity(String filePath, FileDescriptor verityInput, int contentSize) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(filePath);
                    _data.writeRawFileDescriptor(verityInput);
                    _data.writeInt(contentSize);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().installApkVerity(filePath, verityInput, contentSize);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void assertFsverityRootHashMatches(String filePath, byte[] expectedHash) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(filePath);
                    _data.writeByteArray(expectedHash);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().assertFsverityRootHashMatches(filePath, expectedHash);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean reconcileSecondaryDexFile(String dexPath, String pkgName, int uid, String[] isas, String volume_uuid, int storage_flag) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(dexPath);
                        try {
                            _data.writeString(pkgName);
                            try {
                                _data.writeInt(uid);
                                try {
                                    _data.writeStringArray(isas);
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
                try {
                    _data.writeString(volume_uuid);
                    try {
                        _data.writeInt(storage_flag);
                        boolean _result = false;
                        boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            if (_reply.readInt() != 0) {
                                _result = true;
                            }
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        boolean reconcileSecondaryDexFile = Stub.getDefaultImpl().reconcileSecondaryDexFile(dexPath, pkgName, uid, isas, volume_uuid, storage_flag);
                        _reply.recycle();
                        _data.recycle();
                        return reconcileSecondaryDexFile;
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public byte[] hashSecondaryDexFile(String dexPath, String pkgName, int uid, String volumeUuid, int storageFlag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(dexPath);
                    _data.writeString(pkgName);
                    _data.writeInt(uid);
                    _data.writeString(volumeUuid);
                    _data.writeInt(storageFlag);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hashSecondaryDexFile(dexPath, pkgName, uid, volumeUuid, storageFlag);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void invalidateMounts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateMounts();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean isQuotaSupported(String uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isQuotaSupported(uuid);
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

            @Override // android.os.IInstalld
            public boolean prepareAppProfile(String packageName, int userId, int appId, String profileName, String codePath, String dexMetadata) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(packageName);
                        try {
                            _data.writeInt(userId);
                            try {
                                _data.writeInt(appId);
                                try {
                                    _data.writeString(profileName);
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
                try {
                    _data.writeString(codePath);
                    try {
                        _data.writeString(dexMetadata);
                        boolean _result = false;
                        boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            if (_reply.readInt() != 0) {
                                _result = true;
                            }
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        boolean prepareAppProfile = Stub.getDefaultImpl().prepareAppProfile(packageName, userId, appId, profileName, codePath, dexMetadata);
                        _reply.recycle();
                        _data.recycle();
                        return prepareAppProfile;
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public long snapshotAppData(String uuid, String packageName, int userId, int snapshotId, int storageFlags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeInt(snapshotId);
                    _data.writeInt(storageFlags);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().snapshotAppData(uuid, packageName, userId, snapshotId, storageFlags);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void restoreAppDataSnapshot(String uuid, String packageName, int appId, String seInfo, int user, int snapshotId, int storageflags) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeString(packageName);
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
                    _data.writeInt(appId);
                    try {
                        _data.writeString(seInfo);
                        try {
                            _data.writeInt(user);
                            _data.writeInt(snapshotId);
                            _data.writeInt(storageflags);
                            boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().restoreAppDataSnapshot(uuid, packageName, appId, seInfo, user, snapshotId, storageflags);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
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

            @Override // android.os.IInstalld
            public void destroyAppDataSnapshot(String uuid, String packageName, int userId, long ceSnapshotInode, int snapshotId, int storageFlags) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(uuid);
                        try {
                            _data.writeString(packageName);
                            try {
                                _data.writeInt(userId);
                                try {
                                    _data.writeLong(ceSnapshotInode);
                                    _data.writeInt(snapshotId);
                                    _data.writeInt(storageFlags);
                                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().destroyAppDataSnapshot(uuid, packageName, userId, ceSnapshotInode, snapshotId, storageFlags);
                                    _reply.recycle();
                                    _data.recycle();
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

            @Override // android.os.IInstalld
            public void destroyCeSnapshotsNotSpecified(String uuid, int userId, int[] retainSnapshotIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(userId);
                    _data.writeIntArray(retainSnapshotIds);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyCeSnapshotsNotSpecified(uuid, userId, retainSnapshotIds);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void tryMountDataMirror(String volumeUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().tryMountDataMirror(volumeUuid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void onPrivateVolumeRemoved(String volumeUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(volumeUuid);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onPrivateVolumeRemoved(volumeUuid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void migrateLegacyObbData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().migrateLegacyObbData();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IInstalld
            public PersistableBundle oplusCommonInterface(String funName, PersistableBundle args) throws RemoteException {
                PersistableBundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funName);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().oplusCommonInterface(funName, args);
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

        public static boolean setDefaultImpl(IInstalld impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInstalld getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
