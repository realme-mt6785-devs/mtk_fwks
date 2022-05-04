package android.permission;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import com.android.internal.infra.AndroidFuture;
import java.util.List;
/* loaded from: classes2.dex */
public interface IPermissionController extends IInterface {
    public static final String DESCRIPTOR = "android.permission.IPermissionController";

    void applyStagedRuntimePermissionBackup(String str, UserHandle userHandle, AndroidFuture androidFuture) throws RemoteException;

    void countPermissionApps(List<String> list, int i, AndroidFuture androidFuture) throws RemoteException;

    void getAppPermissions(String str, AndroidFuture androidFuture) throws RemoteException;

    void getGroupOfPlatformPermission(String str, AndroidFuture<String> androidFuture) throws RemoteException;

    void getPermissionUsages(boolean z, long j, AndroidFuture androidFuture) throws RemoteException;

    void getPlatformPermissionsForGroup(String str, AndroidFuture<List<String>> androidFuture) throws RemoteException;

    void getPrivilegesDescriptionStringForProfile(String str, AndroidFuture<String> androidFuture) throws RemoteException;

    void getRuntimePermissionBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void grantOrUpgradeDefaultRuntimePermissions(AndroidFuture androidFuture) throws RemoteException;

    void notifyOneTimePermissionSessionTimeout(String str) throws RemoteException;

    void revokeRuntimePermission(String str, String str2) throws RemoteException;

    void revokeRuntimePermissions(Bundle bundle, boolean z, int i, String str, AndroidFuture androidFuture) throws RemoteException;

    void setRuntimePermissionGrantStateByDeviceAdminFromParams(String str, AdminPermissionControlParams adminPermissionControlParams, AndroidFuture androidFuture) throws RemoteException;

    void stageAndApplyRuntimePermissionsBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void updateUserSensitiveForApp(int i, AndroidFuture androidFuture) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IPermissionController {
        @Override // android.permission.IPermissionController
        public void revokeRuntimePermissions(Bundle request, boolean doDryRun, int reason, String callerPackageName, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getRuntimePermissionBackup(UserHandle user, ParcelFileDescriptor pipe) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void stageAndApplyRuntimePermissionsBackup(UserHandle user, ParcelFileDescriptor pipe) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void applyStagedRuntimePermissionBackup(String packageName, UserHandle user, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getAppPermissions(String packageName, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void revokeRuntimePermission(String packageName, String permissionName) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void countPermissionApps(List<String> permissionNames, int flags, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getPermissionUsages(boolean countSystem, long numMillis, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void setRuntimePermissionGrantStateByDeviceAdminFromParams(String callerPackageName, AdminPermissionControlParams params, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void grantOrUpgradeDefaultRuntimePermissions(AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void notifyOneTimePermissionSessionTimeout(String packageName) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void updateUserSensitiveForApp(int uid, AndroidFuture callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getPrivilegesDescriptionStringForProfile(String deviceProfileName, AndroidFuture<String> callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getPlatformPermissionsForGroup(String permissionGroupName, AndroidFuture<List<String>> callback) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getGroupOfPlatformPermission(String permissionName, AndroidFuture<String> callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPermissionController {
        static final int TRANSACTION_applyStagedRuntimePermissionBackup = 4;
        static final int TRANSACTION_countPermissionApps = 7;
        static final int TRANSACTION_getAppPermissions = 5;
        static final int TRANSACTION_getGroupOfPlatformPermission = 15;
        static final int TRANSACTION_getPermissionUsages = 8;
        static final int TRANSACTION_getPlatformPermissionsForGroup = 14;
        static final int TRANSACTION_getPrivilegesDescriptionStringForProfile = 13;
        static final int TRANSACTION_getRuntimePermissionBackup = 2;
        static final int TRANSACTION_grantOrUpgradeDefaultRuntimePermissions = 10;
        static final int TRANSACTION_notifyOneTimePermissionSessionTimeout = 11;
        static final int TRANSACTION_revokeRuntimePermission = 6;
        static final int TRANSACTION_revokeRuntimePermissions = 1;
        static final int TRANSACTION_setRuntimePermissionGrantStateByDeviceAdminFromParams = 9;
        static final int TRANSACTION_stageAndApplyRuntimePermissionsBackup = 3;
        static final int TRANSACTION_updateUserSensitiveForApp = 12;

        public Stub() {
            attachInterface(this, IPermissionController.DESCRIPTOR);
        }

        public static IPermissionController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPermissionController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPermissionController)) {
                return new Proxy(obj);
            }
            return (IPermissionController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "revokeRuntimePermissions";
                case 2:
                    return "getRuntimePermissionBackup";
                case 3:
                    return "stageAndApplyRuntimePermissionsBackup";
                case 4:
                    return "applyStagedRuntimePermissionBackup";
                case 5:
                    return "getAppPermissions";
                case 6:
                    return "revokeRuntimePermission";
                case 7:
                    return "countPermissionApps";
                case 8:
                    return "getPermissionUsages";
                case 9:
                    return "setRuntimePermissionGrantStateByDeviceAdminFromParams";
                case 10:
                    return "grantOrUpgradeDefaultRuntimePermissions";
                case 11:
                    return "notifyOneTimePermissionSessionTimeout";
                case 12:
                    return "updateUserSensitiveForApp";
                case 13:
                    return "getPrivilegesDescriptionStringForProfile";
                case 14:
                    return "getPlatformPermissionsForGroup";
                case 15:
                    return "getGroupOfPlatformPermission";
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
            Bundle _arg0;
            AndroidFuture _arg4;
            UserHandle _arg02;
            ParcelFileDescriptor _arg1;
            UserHandle _arg03;
            ParcelFileDescriptor _arg12;
            UserHandle _arg13;
            AndroidFuture _arg2;
            AndroidFuture _arg14;
            AndroidFuture _arg22;
            AndroidFuture _arg23;
            AdminPermissionControlParams _arg15;
            AndroidFuture _arg24;
            AndroidFuture _arg04;
            AndroidFuture _arg16;
            AndroidFuture _arg17;
            AndroidFuture _arg18;
            AndroidFuture _arg19;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPermissionController.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg05 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean _arg110 = data.readInt() != 0;
                            int _arg25 = data.readInt();
                            String _arg3 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            revokeRuntimePermissions(_arg0, _arg110, _arg25, _arg3, _arg4);
                            return true;
                        case 2:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            getRuntimePermissionBackup(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            stageAndApplyRuntimePermissionsBackup(_arg03, _arg12);
                            return true;
                        case 4:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            applyStagedRuntimePermissionBackup(_arg06, _arg13, _arg2);
                            return true;
                        case 5:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            getAppPermissions(_arg07, _arg14);
                            return true;
                        case 6:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg08 = data.readString();
                            String _arg111 = data.readString();
                            revokeRuntimePermission(_arg08, _arg111);
                            return true;
                        case 7:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            List<String> _arg09 = data.createStringArrayList();
                            int _arg112 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            countPermissionApps(_arg09, _arg112, _arg22);
                            return true;
                        case 8:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = true;
                            }
                            long _arg113 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg23 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            getPermissionUsages(_arg05, _arg113, _arg23);
                            return true;
                        case 9:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg010 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = AdminPermissionControlParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg24 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            setRuntimePermissionGrantStateByDeviceAdminFromParams(_arg010, _arg15, _arg24);
                            return true;
                        case 10:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            grantOrUpgradeDefaultRuntimePermissions(_arg04);
                            return true;
                        case 11:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg011 = data.readString();
                            notifyOneTimePermissionSessionTimeout(_arg011);
                            return true;
                        case 12:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg16 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            updateUserSensitiveForApp(_arg012, _arg16);
                            return true;
                        case 13:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg013 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            getPrivilegesDescriptionStringForProfile(_arg013, _arg17);
                            return true;
                        case 14:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg014 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            getPlatformPermissionsForGroup(_arg014, _arg18);
                            return true;
                        case 15:
                            data.enforceInterface(IPermissionController.DESCRIPTOR);
                            String _arg015 = data.readString();
                            if (data.readInt() != 0) {
                                _arg19 = AndroidFuture.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            getGroupOfPlatformPermission(_arg015, _arg19);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPermissionController {
            public static IPermissionController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPermissionController.DESCRIPTOR;
            }

            @Override // android.permission.IPermissionController
            public void revokeRuntimePermissions(Bundle request, boolean doDryRun, int reason, String callerPackageName, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(doDryRun ? 1 : 0);
                    _data.writeInt(reason);
                    _data.writeString(callerPackageName);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeRuntimePermissions(request, doDryRun, reason, callerPackageName, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getRuntimePermissionBackup(UserHandle user, ParcelFileDescriptor pipe) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pipe != null) {
                        _data.writeInt(1);
                        pipe.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRuntimePermissionBackup(user, pipe);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void stageAndApplyRuntimePermissionsBackup(UserHandle user, ParcelFileDescriptor pipe) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pipe != null) {
                        _data.writeInt(1);
                        pipe.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stageAndApplyRuntimePermissionsBackup(user, pipe);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void applyStagedRuntimePermissionBackup(String packageName, UserHandle user, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyStagedRuntimePermissionBackup(packageName, user, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getAppPermissions(String packageName, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAppPermissions(packageName, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void revokeRuntimePermission(String packageName, String permissionName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeRuntimePermission(packageName, permissionName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void countPermissionApps(List<String> permissionNames, int flags, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeStringList(permissionNames);
                    _data.writeInt(flags);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().countPermissionApps(permissionNames, flags, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getPermissionUsages(boolean countSystem, long numMillis, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeInt(countSystem ? 1 : 0);
                    _data.writeLong(numMillis);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPermissionUsages(countSystem, numMillis, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void setRuntimePermissionGrantStateByDeviceAdminFromParams(String callerPackageName, AdminPermissionControlParams params, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(callerPackageName);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRuntimePermissionGrantStateByDeviceAdminFromParams(callerPackageName, params, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void grantOrUpgradeDefaultRuntimePermissions(AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantOrUpgradeDefaultRuntimePermissions(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void notifyOneTimePermissionSessionTimeout(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyOneTimePermissionSessionTimeout(packageName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void updateUserSensitiveForApp(int uid, AndroidFuture callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeInt(uid);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateUserSensitiveForApp(uid, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getPrivilegesDescriptionStringForProfile(String deviceProfileName, AndroidFuture<String> callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(deviceProfileName);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPrivilegesDescriptionStringForProfile(deviceProfileName, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getPlatformPermissionsForGroup(String permissionGroupName, AndroidFuture<List<String>> callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(permissionGroupName);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPlatformPermissionsForGroup(permissionGroupName, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getGroupOfPlatformPermission(String permissionName, AndroidFuture<String> callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionController.DESCRIPTOR);
                    _data.writeString(permissionName);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getGroupOfPlatformPermission(permissionName, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPermissionController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPermissionController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
