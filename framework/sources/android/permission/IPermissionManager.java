package android.permission;

import android.content.AttributionSourceState;
import android.content.pm.ParceledListSlice;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.permission.IOnPermissionsChangeListener;
import java.util.List;
/* loaded from: classes2.dex */
public interface IPermissionManager extends IInterface {
    public static final String DESCRIPTOR = "android.permission.IPermissionManager";

    boolean addAllowlistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException;

    void addOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException;

    boolean addPermission(PermissionInfo permissionInfo, boolean z) throws RemoteException;

    ParceledListSlice getAllPermissionGroups(int i) throws RemoteException;

    List<String> getAllowlistedRestrictedPermissions(String str, int i, int i2) throws RemoteException;

    List<String> getAutoRevokeExemptionGrantedPackages(int i) throws RemoteException;

    List<String> getAutoRevokeExemptionRequestedPackages(int i) throws RemoteException;

    int getPermissionFlags(String str, String str2, int i) throws RemoteException;

    PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException;

    PermissionInfo getPermissionInfo(String str, String str2, int i) throws RemoteException;

    List<SplitPermissionInfoParcelable> getSplitPermissions() throws RemoteException;

    void grantRuntimePermission(String str, String str2, int i) throws RemoteException;

    boolean isAutoRevokeExempted(String str, int i) throws RemoteException;

    boolean isPermissionRevokedByPolicy(String str, String str2, int i) throws RemoteException;

    boolean isRegisteredAttributionSource(AttributionSourceState attributionSourceState) throws RemoteException;

    ParceledListSlice queryPermissionsByGroup(String str, int i) throws RemoteException;

    void registerAttributionSource(AttributionSourceState attributionSourceState) throws RemoteException;

    boolean removeAllowlistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException;

    void removeOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException;

    void removePermission(String str) throws RemoteException;

    void revokeRuntimePermission(String str, String str2, int i, String str3) throws RemoteException;

    boolean setAutoRevokeExempted(String str, boolean z, int i) throws RemoteException;

    boolean shouldShowRequestPermissionRationale(String str, String str2, int i) throws RemoteException;

    void startOneTimePermissionSession(String str, int i, long j, int i2, int i3) throws RemoteException;

    void stopOneTimePermissionSession(String str, int i) throws RemoteException;

    void updatePermissionFlags(String str, String str2, int i, int i2, boolean z, int i3) throws RemoteException;

    void updatePermissionFlagsForAllApps(int i, int i2, int i3) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IPermissionManager {
        @Override // android.permission.IPermissionManager
        public ParceledListSlice getAllPermissionGroups(int flags) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public PermissionGroupInfo getPermissionGroupInfo(String groupName, int flags) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public PermissionInfo getPermissionInfo(String permissionName, String packageName, int flags) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public ParceledListSlice queryPermissionsByGroup(String groupName, int flags) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public boolean addPermission(PermissionInfo permissionInfo, boolean async) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public void removePermission(String permissionName) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public int getPermissionFlags(String packageName, String permissionName, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.permission.IPermissionManager
        public void updatePermissionFlags(String packageName, String permissionName, int flagMask, int flagValues, boolean checkAdjustPolicyFlagPermission, int userId) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void updatePermissionFlagsForAllApps(int flagMask, int flagValues, int userId) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void addOnPermissionsChangeListener(IOnPermissionsChangeListener listener) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void removeOnPermissionsChangeListener(IOnPermissionsChangeListener listener) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public List<String> getAllowlistedRestrictedPermissions(String packageName, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public boolean addAllowlistedRestrictedPermission(String packageName, String permissionName, int flags, int userId) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean removeAllowlistedRestrictedPermission(String packageName, String permissionName, int flags, int userId) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public void grantRuntimePermission(String packageName, String permissionName, int userId) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void revokeRuntimePermission(String packageName, String permissionName, int userId, String reason) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean shouldShowRequestPermissionRationale(String packageName, String permissionName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean isPermissionRevokedByPolicy(String packageName, String permissionName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public List<SplitPermissionInfoParcelable> getSplitPermissions() throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public void startOneTimePermissionSession(String packageName, int userId, long timeout, int importanceToResetTimer, int importanceToKeepSessionAlive) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void stopOneTimePermissionSession(String packageName, int userId) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public List<String> getAutoRevokeExemptionRequestedPackages(int userId) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public List<String> getAutoRevokeExemptionGrantedPackages(int userId) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public boolean setAutoRevokeExempted(String packageName, boolean exempted, int userId) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean isAutoRevokeExempted(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public void registerAttributionSource(AttributionSourceState source) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean isRegisteredAttributionSource(AttributionSourceState source) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPermissionManager {
        static final int TRANSACTION_addAllowlistedRestrictedPermission = 13;
        static final int TRANSACTION_addOnPermissionsChangeListener = 10;
        static final int TRANSACTION_addPermission = 5;
        static final int TRANSACTION_getAllPermissionGroups = 1;
        static final int TRANSACTION_getAllowlistedRestrictedPermissions = 12;
        static final int TRANSACTION_getAutoRevokeExemptionGrantedPackages = 23;
        static final int TRANSACTION_getAutoRevokeExemptionRequestedPackages = 22;
        static final int TRANSACTION_getPermissionFlags = 7;
        static final int TRANSACTION_getPermissionGroupInfo = 2;
        static final int TRANSACTION_getPermissionInfo = 3;
        static final int TRANSACTION_getSplitPermissions = 19;
        static final int TRANSACTION_grantRuntimePermission = 15;
        static final int TRANSACTION_isAutoRevokeExempted = 25;
        static final int TRANSACTION_isPermissionRevokedByPolicy = 18;
        static final int TRANSACTION_isRegisteredAttributionSource = 27;
        static final int TRANSACTION_queryPermissionsByGroup = 4;
        static final int TRANSACTION_registerAttributionSource = 26;
        static final int TRANSACTION_removeAllowlistedRestrictedPermission = 14;
        static final int TRANSACTION_removeOnPermissionsChangeListener = 11;
        static final int TRANSACTION_removePermission = 6;
        static final int TRANSACTION_revokeRuntimePermission = 16;
        static final int TRANSACTION_setAutoRevokeExempted = 24;
        static final int TRANSACTION_shouldShowRequestPermissionRationale = 17;
        static final int TRANSACTION_startOneTimePermissionSession = 20;
        static final int TRANSACTION_stopOneTimePermissionSession = 21;
        static final int TRANSACTION_updatePermissionFlags = 8;
        static final int TRANSACTION_updatePermissionFlagsForAllApps = 9;

        public Stub() {
            attachInterface(this, IPermissionManager.DESCRIPTOR);
        }

        public static IPermissionManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPermissionManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPermissionManager)) {
                return new Proxy(obj);
            }
            return (IPermissionManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getAllPermissionGroups";
                case 2:
                    return "getPermissionGroupInfo";
                case 3:
                    return "getPermissionInfo";
                case 4:
                    return "queryPermissionsByGroup";
                case 5:
                    return "addPermission";
                case 6:
                    return "removePermission";
                case 7:
                    return "getPermissionFlags";
                case 8:
                    return "updatePermissionFlags";
                case 9:
                    return "updatePermissionFlagsForAllApps";
                case 10:
                    return "addOnPermissionsChangeListener";
                case 11:
                    return "removeOnPermissionsChangeListener";
                case 12:
                    return "getAllowlistedRestrictedPermissions";
                case 13:
                    return "addAllowlistedRestrictedPermission";
                case 14:
                    return "removeAllowlistedRestrictedPermission";
                case 15:
                    return "grantRuntimePermission";
                case 16:
                    return "revokeRuntimePermission";
                case 17:
                    return "shouldShowRequestPermissionRationale";
                case 18:
                    return "isPermissionRevokedByPolicy";
                case 19:
                    return "getSplitPermissions";
                case 20:
                    return "startOneTimePermissionSession";
                case 21:
                    return "stopOneTimePermissionSession";
                case 22:
                    return "getAutoRevokeExemptionRequestedPackages";
                case 23:
                    return "getAutoRevokeExemptionGrantedPackages";
                case 24:
                    return "setAutoRevokeExempted";
                case 25:
                    return "isAutoRevokeExempted";
                case 26:
                    return "registerAttributionSource";
                case 27:
                    return "isRegisteredAttributionSource";
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
            PermissionInfo _arg0;
            boolean _arg4;
            AttributionSourceState _arg02;
            AttributionSourceState _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPermissionManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            ParceledListSlice _result = getAllPermissionGroups(_arg04);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            PermissionGroupInfo _result2 = getPermissionGroupInfo(_arg05, data.readInt());
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            String _arg12 = data.readString();
                            int _arg2 = data.readInt();
                            PermissionInfo _result3 = getPermissionInfo(_arg06, _arg12, _arg2);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg07 = data.readString();
                            ParceledListSlice _result4 = queryPermissionsByGroup(_arg07, data.readInt());
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PermissionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            boolean addPermission = addPermission(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(addPermission ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg08 = data.readString();
                            removePermission(_arg08);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg13 = data.readString();
                            int _arg22 = data.readInt();
                            int _result5 = getPermissionFlags(_arg09, _arg13, _arg22);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 8:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg010 = data.readString();
                            String _arg14 = data.readString();
                            int _arg23 = data.readInt();
                            int _arg3 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            int _arg5 = data.readInt();
                            updatePermissionFlags(_arg010, _arg14, _arg23, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg15 = data.readInt();
                            int _arg24 = data.readInt();
                            updatePermissionFlagsForAllApps(_arg011, _arg15, _arg24);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            IOnPermissionsChangeListener _arg012 = IOnPermissionsChangeListener.Stub.asInterface(data.readStrongBinder());
                            addOnPermissionsChangeListener(_arg012);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            IOnPermissionsChangeListener _arg013 = IOnPermissionsChangeListener.Stub.asInterface(data.readStrongBinder());
                            removeOnPermissionsChangeListener(_arg013);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg014 = data.readString();
                            int _arg16 = data.readInt();
                            int _arg25 = data.readInt();
                            List<String> _result6 = getAllowlistedRestrictedPermissions(_arg014, _arg16, _arg25);
                            reply.writeNoException();
                            reply.writeStringList(_result6);
                            return true;
                        case 13:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg015 = data.readString();
                            String _arg17 = data.readString();
                            int _arg26 = data.readInt();
                            int _arg32 = data.readInt();
                            boolean addAllowlistedRestrictedPermission = addAllowlistedRestrictedPermission(_arg015, _arg17, _arg26, _arg32);
                            reply.writeNoException();
                            reply.writeInt(addAllowlistedRestrictedPermission ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg016 = data.readString();
                            String _arg18 = data.readString();
                            int _arg27 = data.readInt();
                            int _arg33 = data.readInt();
                            boolean removeAllowlistedRestrictedPermission = removeAllowlistedRestrictedPermission(_arg016, _arg18, _arg27, _arg33);
                            reply.writeNoException();
                            reply.writeInt(removeAllowlistedRestrictedPermission ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg017 = data.readString();
                            String _arg19 = data.readString();
                            int _arg28 = data.readInt();
                            grantRuntimePermission(_arg017, _arg19, _arg28);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg018 = data.readString();
                            String _arg110 = data.readString();
                            int _arg29 = data.readInt();
                            String _arg34 = data.readString();
                            revokeRuntimePermission(_arg018, _arg110, _arg29, _arg34);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg019 = data.readString();
                            String _arg111 = data.readString();
                            int _arg210 = data.readInt();
                            boolean shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale(_arg019, _arg111, _arg210);
                            reply.writeNoException();
                            reply.writeInt(shouldShowRequestPermissionRationale ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg020 = data.readString();
                            String _arg112 = data.readString();
                            int _arg211 = data.readInt();
                            boolean isPermissionRevokedByPolicy = isPermissionRevokedByPolicy(_arg020, _arg112, _arg211);
                            reply.writeNoException();
                            reply.writeInt(isPermissionRevokedByPolicy ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            List<SplitPermissionInfoParcelable> _result7 = getSplitPermissions();
                            reply.writeNoException();
                            reply.writeTypedList(_result7);
                            return true;
                        case 20:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg021 = data.readString();
                            int _arg113 = data.readInt();
                            long _arg212 = data.readLong();
                            int _arg35 = data.readInt();
                            int _arg42 = data.readInt();
                            startOneTimePermissionSession(_arg021, _arg113, _arg212, _arg35, _arg42);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg022 = data.readString();
                            stopOneTimePermissionSession(_arg022, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            int _arg023 = data.readInt();
                            List<String> _result8 = getAutoRevokeExemptionRequestedPackages(_arg023);
                            reply.writeNoException();
                            reply.writeStringList(_result8);
                            return true;
                        case 23:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            int _arg024 = data.readInt();
                            List<String> _result9 = getAutoRevokeExemptionGrantedPackages(_arg024);
                            reply.writeNoException();
                            reply.writeStringList(_result9);
                            return true;
                        case 24:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg025 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            int _arg213 = data.readInt();
                            boolean autoRevokeExempted = setAutoRevokeExempted(_arg025, _arg1, _arg213);
                            reply.writeNoException();
                            reply.writeInt(autoRevokeExempted ? 1 : 0);
                            return true;
                        case 25:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            String _arg026 = data.readString();
                            boolean isAutoRevokeExempted = isAutoRevokeExempted(_arg026, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isAutoRevokeExempted ? 1 : 0);
                            return true;
                        case 26:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = AttributionSourceState.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            registerAttributionSource(_arg02);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(IPermissionManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = AttributionSourceState.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            boolean isRegisteredAttributionSource = isRegisteredAttributionSource(_arg03);
                            reply.writeNoException();
                            reply.writeInt(isRegisteredAttributionSource ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPermissionManager {
            public static IPermissionManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPermissionManager.DESCRIPTOR;
            }

            @Override // android.permission.IPermissionManager
            public ParceledListSlice getAllPermissionGroups(int flags) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPermissionGroups(flags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public PermissionGroupInfo getPermissionGroupInfo(String groupName, int flags) throws RemoteException {
                PermissionGroupInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(groupName);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionGroupInfo(groupName, flags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PermissionGroupInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public PermissionInfo getPermissionInfo(String permissionName, String packageName, int flags) throws RemoteException {
                PermissionInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(permissionName);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionInfo(permissionName, packageName, flags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PermissionInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public ParceledListSlice queryPermissionsByGroup(String groupName, int flags) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(groupName);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryPermissionsByGroup(groupName, flags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean addPermission(PermissionInfo permissionInfo, boolean async) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    boolean _result = true;
                    if (permissionInfo != null) {
                        _data.writeInt(1);
                        permissionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(async ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPermission(permissionInfo, async);
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

            @Override // android.permission.IPermissionManager
            public void removePermission(String permissionName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(permissionName);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removePermission(permissionName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public int getPermissionFlags(String packageName, String permissionName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionFlags(packageName, permissionName, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void updatePermissionFlags(String packageName, String permissionName, int flagMask, int flagValues, boolean checkAdjustPolicyFlagPermission, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    try {
                        _data.writeString(packageName);
                        try {
                            _data.writeString(permissionName);
                            try {
                                _data.writeInt(flagMask);
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
                    try {
                        _data.writeInt(flagValues);
                        _data.writeInt(checkAdjustPolicyFlagPermission ? 1 : 0);
                        try {
                            _data.writeInt(userId);
                            try {
                                boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().updatePermissionFlags(packageName, permissionName, flagMask, flagValues, checkAdjustPolicyFlagPermission, userId);
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

            @Override // android.permission.IPermissionManager
            public void updatePermissionFlagsForAllApps(int flagMask, int flagValues, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeInt(flagMask);
                    _data.writeInt(flagValues);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updatePermissionFlagsForAllApps(flagMask, flagValues, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void addOnPermissionsChangeListener(IOnPermissionsChangeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addOnPermissionsChangeListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void removeOnPermissionsChangeListener(IOnPermissionsChangeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeOnPermissionsChangeListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<String> getAllowlistedRestrictedPermissions(String packageName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowlistedRestrictedPermissions(packageName, flags, userId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean addAllowlistedRestrictedPermission(String packageName, String permissionName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAllowlistedRestrictedPermission(packageName, permissionName, flags, userId);
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

            @Override // android.permission.IPermissionManager
            public boolean removeAllowlistedRestrictedPermission(String packageName, String permissionName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAllowlistedRestrictedPermission(packageName, permissionName, flags, userId);
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

            @Override // android.permission.IPermissionManager
            public void grantRuntimePermission(String packageName, String permissionName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().grantRuntimePermission(packageName, permissionName, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void revokeRuntimePermission(String packageName, String permissionName, int userId, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(userId);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().revokeRuntimePermission(packageName, permissionName, userId, reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean shouldShowRequestPermissionRationale(String packageName, String permissionName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowRequestPermissionRationale(packageName, permissionName, userId);
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

            @Override // android.permission.IPermissionManager
            public boolean isPermissionRevokedByPolicy(String packageName, String permissionName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(permissionName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPermissionRevokedByPolicy(packageName, permissionName, userId);
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

            @Override // android.permission.IPermissionManager
            public List<SplitPermissionInfoParcelable> getSplitPermissions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSplitPermissions();
                    }
                    _reply.readException();
                    List<SplitPermissionInfoParcelable> _result = _reply.createTypedArrayList(SplitPermissionInfoParcelable.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void startOneTimePermissionSession(String packageName, int userId, long timeout, int importanceToResetTimer, int importanceToKeepSessionAlive) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    try {
                        _data.writeString(packageName);
                        try {
                            _data.writeInt(userId);
                            try {
                                _data.writeLong(timeout);
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
                    _data.writeInt(importanceToResetTimer);
                    try {
                        _data.writeInt(importanceToKeepSessionAlive);
                        boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().startOneTimePermissionSession(packageName, userId, timeout, importanceToResetTimer, importanceToKeepSessionAlive);
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

            @Override // android.permission.IPermissionManager
            public void stopOneTimePermissionSession(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopOneTimePermissionSession(packageName, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<String> getAutoRevokeExemptionRequestedPackages(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoRevokeExemptionRequestedPackages(userId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<String> getAutoRevokeExemptionGrantedPackages(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoRevokeExemptionGrantedPackages(userId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean setAutoRevokeExempted(String packageName, boolean exempted, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = true;
                    _data.writeInt(exempted ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAutoRevokeExempted(packageName, exempted, userId);
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

            @Override // android.permission.IPermissionManager
            public boolean isAutoRevokeExempted(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutoRevokeExempted(packageName, userId);
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

            @Override // android.permission.IPermissionManager
            public void registerAttributionSource(AttributionSourceState source) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    if (source != null) {
                        _data.writeInt(1);
                        source.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerAttributionSource(source);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean isRegisteredAttributionSource(AttributionSourceState source) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPermissionManager.DESCRIPTOR);
                    boolean _result = true;
                    if (source != null) {
                        _data.writeInt(1);
                        source.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRegisteredAttributionSource(source);
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
        }

        public static boolean setDefaultImpl(IPermissionManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPermissionManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
