package android.permission;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ILegacyPermissionManager extends IInterface {
    public static final String DESCRIPTOR = "android.permission.ILegacyPermissionManager";

    int checkDeviceIdentifierAccess(String str, String str2, String str3, int i, int i2) throws RemoteException;

    int checkPhoneNumberAccess(String str, String str2, String str3, int i, int i2) throws RemoteException;

    void grantDefaultPermissionsToActiveLuiApp(String str, int i) throws RemoteException;

    void grantDefaultPermissionsToEnabledCarrierApps(String[] strArr, int i) throws RemoteException;

    void grantDefaultPermissionsToEnabledImsServices(String[] strArr, int i) throws RemoteException;

    void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] strArr, int i) throws RemoteException;

    void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] strArr, int i) throws RemoteException;

    void revokeDefaultPermissionsFromLuiApps(String[] strArr, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ILegacyPermissionManager {
        @Override // android.permission.ILegacyPermissionManager
        public int checkDeviceIdentifierAccess(String packageName, String message, String callingFeatureId, int pid, int uid) throws RemoteException {
            return 0;
        }

        @Override // android.permission.ILegacyPermissionManager
        public int checkPhoneNumberAccess(String packageName, String message, String callingFeatureId, int pid, int uid) throws RemoteException {
            return 0;
        }

        @Override // android.permission.ILegacyPermissionManager
        public void grantDefaultPermissionsToEnabledCarrierApps(String[] packageNames, int userId) throws RemoteException {
        }

        @Override // android.permission.ILegacyPermissionManager
        public void grantDefaultPermissionsToEnabledImsServices(String[] packageNames, int userId) throws RemoteException {
        }

        @Override // android.permission.ILegacyPermissionManager
        public void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] packageNames, int userId) throws RemoteException {
        }

        @Override // android.permission.ILegacyPermissionManager
        public void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] packageNames, int userId) throws RemoteException {
        }

        @Override // android.permission.ILegacyPermissionManager
        public void grantDefaultPermissionsToActiveLuiApp(String packageName, int userId) throws RemoteException {
        }

        @Override // android.permission.ILegacyPermissionManager
        public void revokeDefaultPermissionsFromLuiApps(String[] packageNames, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ILegacyPermissionManager {
        static final int TRANSACTION_checkDeviceIdentifierAccess = 1;
        static final int TRANSACTION_checkPhoneNumberAccess = 2;
        static final int TRANSACTION_grantDefaultPermissionsToActiveLuiApp = 7;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledCarrierApps = 3;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledImsServices = 4;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledTelephonyDataServices = 5;
        static final int TRANSACTION_revokeDefaultPermissionsFromDisabledTelephonyDataServices = 6;
        static final int TRANSACTION_revokeDefaultPermissionsFromLuiApps = 8;

        public Stub() {
            attachInterface(this, ILegacyPermissionManager.DESCRIPTOR);
        }

        public static ILegacyPermissionManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILegacyPermissionManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ILegacyPermissionManager)) {
                return new Proxy(obj);
            }
            return (ILegacyPermissionManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "checkDeviceIdentifierAccess";
                case 2:
                    return "checkPhoneNumberAccess";
                case 3:
                    return "grantDefaultPermissionsToEnabledCarrierApps";
                case 4:
                    return "grantDefaultPermissionsToEnabledImsServices";
                case 5:
                    return "grantDefaultPermissionsToEnabledTelephonyDataServices";
                case 6:
                    return "revokeDefaultPermissionsFromDisabledTelephonyDataServices";
                case 7:
                    return "grantDefaultPermissionsToActiveLuiApp";
                case 8:
                    return "revokeDefaultPermissionsFromLuiApps";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ILegacyPermissionManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            String _arg2 = data.readString();
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            int _result = checkDeviceIdentifierAccess(_arg0, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String _arg02 = data.readString();
                            String _arg12 = data.readString();
                            String _arg22 = data.readString();
                            int _arg32 = data.readInt();
                            int _arg42 = data.readInt();
                            int _result2 = checkPhoneNumberAccess(_arg02, _arg12, _arg22, _arg32, _arg42);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String[] _arg03 = data.createStringArray();
                            int _arg13 = data.readInt();
                            grantDefaultPermissionsToEnabledCarrierApps(_arg03, _arg13);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String[] _arg04 = data.createStringArray();
                            int _arg14 = data.readInt();
                            grantDefaultPermissionsToEnabledImsServices(_arg04, _arg14);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String[] _arg05 = data.createStringArray();
                            int _arg15 = data.readInt();
                            grantDefaultPermissionsToEnabledTelephonyDataServices(_arg05, _arg15);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String[] _arg06 = data.createStringArray();
                            int _arg16 = data.readInt();
                            revokeDefaultPermissionsFromDisabledTelephonyDataServices(_arg06, _arg16);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg17 = data.readInt();
                            grantDefaultPermissionsToActiveLuiApp(_arg07, _arg17);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(ILegacyPermissionManager.DESCRIPTOR);
                            String[] _arg08 = data.createStringArray();
                            int _arg18 = data.readInt();
                            revokeDefaultPermissionsFromLuiApps(_arg08, _arg18);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ILegacyPermissionManager {
            public static ILegacyPermissionManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILegacyPermissionManager.DESCRIPTOR;
            }

            @Override // android.permission.ILegacyPermissionManager
            public int checkDeviceIdentifierAccess(String packageName, String message, String callingFeatureId, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(message);
                    _data.writeString(callingFeatureId);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkDeviceIdentifierAccess(packageName, message, callingFeatureId, pid, uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public int checkPhoneNumberAccess(String packageName, String message, String callingFeatureId, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(message);
                    _data.writeString(callingFeatureId);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkPhoneNumberAccess(packageName, message, callingFeatureId, pid, uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public void grantDefaultPermissionsToEnabledCarrierApps(String[] packageNames, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeStringArray(packageNames);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().grantDefaultPermissionsToEnabledCarrierApps(packageNames, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public void grantDefaultPermissionsToEnabledImsServices(String[] packageNames, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeStringArray(packageNames);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().grantDefaultPermissionsToEnabledImsServices(packageNames, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] packageNames, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeStringArray(packageNames);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().grantDefaultPermissionsToEnabledTelephonyDataServices(packageNames, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] packageNames, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeStringArray(packageNames);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().revokeDefaultPermissionsFromDisabledTelephonyDataServices(packageNames, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public void grantDefaultPermissionsToActiveLuiApp(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().grantDefaultPermissionsToActiveLuiApp(packageName, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.ILegacyPermissionManager
            public void revokeDefaultPermissionsFromLuiApps(String[] packageNames, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILegacyPermissionManager.DESCRIPTOR);
                    _data.writeStringArray(packageNames);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().revokeDefaultPermissionsFromLuiApps(packageNames, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILegacyPermissionManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILegacyPermissionManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
