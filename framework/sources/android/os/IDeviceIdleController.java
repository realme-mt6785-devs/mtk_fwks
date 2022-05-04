package android.os;

import java.util.List;
/* loaded from: classes2.dex */
public interface IDeviceIdleController extends IInterface {
    void addPowerSaveTempWhitelistApp(String str, long j, int i, int i2, String str2) throws RemoteException;

    long addPowerSaveTempWhitelistAppForMms(String str, int i, int i2, String str2) throws RemoteException;

    long addPowerSaveTempWhitelistAppForSms(String str, int i, int i2, String str2) throws RemoteException;

    void addPowerSaveWhitelistApp(String str) throws RemoteException;

    int addPowerSaveWhitelistApps(List<String> list) throws RemoteException;

    void exitIdle(String str) throws RemoteException;

    int[] getAppIdTempWhitelist() throws RemoteException;

    int[] getAppIdUserWhitelist() throws RemoteException;

    int[] getAppIdWhitelist() throws RemoteException;

    int[] getAppIdWhitelistExceptIdle() throws RemoteException;

    String[] getFullPowerWhitelist() throws RemoteException;

    String[] getFullPowerWhitelistExceptIdle() throws RemoteException;

    String[] getRemovedSystemPowerWhitelistApps() throws RemoteException;

    String[] getSystemPowerWhitelist() throws RemoteException;

    String[] getSystemPowerWhitelistExceptIdle() throws RemoteException;

    String[] getUserPowerWhitelist() throws RemoteException;

    boolean isPowerSaveWhitelistApp(String str) throws RemoteException;

    boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException;

    void removePowerSaveWhitelistApp(String str) throws RemoteException;

    void removeSystemPowerWhitelistApp(String str) throws RemoteException;

    void resetPreIdleTimeoutMode() throws RemoteException;

    void restoreSystemPowerWhitelistApp(String str) throws RemoteException;

    int setPreIdleTimeoutMode(int i) throws RemoteException;

    long whitelistAppTemporarily(String str, int i, int i2, String str2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IDeviceIdleController {
        @Override // android.os.IDeviceIdleController
        public void addPowerSaveWhitelistApp(String name) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public int addPowerSaveWhitelistApps(List<String> packageNames) throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public void removePowerSaveWhitelistApp(String name) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void removeSystemPowerWhitelistApp(String name) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void restoreSystemPowerWhitelistApp(String name) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public String[] getRemovedSystemPowerWhitelistApps() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getSystemPowerWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getSystemPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getUserPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getFullPowerWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getFullPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdUserWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdTempWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public boolean isPowerSaveWhitelistExceptIdleApp(String name) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public boolean isPowerSaveWhitelistApp(String name) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public void addPowerSaveTempWhitelistApp(String name, long duration, int userId, int reasonCode, String reason) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public long addPowerSaveTempWhitelistAppForMms(String name, int userId, int reasonCode, String reason) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IDeviceIdleController
        public long addPowerSaveTempWhitelistAppForSms(String name, int userId, int reasonCode, String reason) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IDeviceIdleController
        public long whitelistAppTemporarily(String name, int userId, int reasonCode, String reason) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IDeviceIdleController
        public void exitIdle(String reason) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public int setPreIdleTimeoutMode(int Mode) throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public void resetPreIdleTimeoutMode() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IDeviceIdleController {
        public static final String DESCRIPTOR = "android.os.IDeviceIdleController";
        static final int TRANSACTION_addPowerSaveTempWhitelistApp = 18;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForMms = 19;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForSms = 20;
        static final int TRANSACTION_addPowerSaveWhitelistApp = 1;
        static final int TRANSACTION_addPowerSaveWhitelistApps = 2;
        static final int TRANSACTION_exitIdle = 22;
        static final int TRANSACTION_getAppIdTempWhitelist = 15;
        static final int TRANSACTION_getAppIdUserWhitelist = 14;
        static final int TRANSACTION_getAppIdWhitelist = 13;
        static final int TRANSACTION_getAppIdWhitelistExceptIdle = 12;
        static final int TRANSACTION_getFullPowerWhitelist = 11;
        static final int TRANSACTION_getFullPowerWhitelistExceptIdle = 10;
        static final int TRANSACTION_getRemovedSystemPowerWhitelistApps = 6;
        static final int TRANSACTION_getSystemPowerWhitelist = 8;
        static final int TRANSACTION_getSystemPowerWhitelistExceptIdle = 7;
        static final int TRANSACTION_getUserPowerWhitelist = 9;
        static final int TRANSACTION_isPowerSaveWhitelistApp = 17;
        static final int TRANSACTION_isPowerSaveWhitelistExceptIdleApp = 16;
        static final int TRANSACTION_removePowerSaveWhitelistApp = 3;
        static final int TRANSACTION_removeSystemPowerWhitelistApp = 4;
        static final int TRANSACTION_resetPreIdleTimeoutMode = 24;
        static final int TRANSACTION_restoreSystemPowerWhitelistApp = 5;
        static final int TRANSACTION_setPreIdleTimeoutMode = 23;
        static final int TRANSACTION_whitelistAppTemporarily = 21;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceIdleController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceIdleController)) {
                return new Proxy(obj);
            }
            return (IDeviceIdleController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addPowerSaveWhitelistApp";
                case 2:
                    return "addPowerSaveWhitelistApps";
                case 3:
                    return "removePowerSaveWhitelistApp";
                case 4:
                    return "removeSystemPowerWhitelistApp";
                case 5:
                    return "restoreSystemPowerWhitelistApp";
                case 6:
                    return "getRemovedSystemPowerWhitelistApps";
                case 7:
                    return "getSystemPowerWhitelistExceptIdle";
                case 8:
                    return "getSystemPowerWhitelist";
                case 9:
                    return "getUserPowerWhitelist";
                case 10:
                    return "getFullPowerWhitelistExceptIdle";
                case 11:
                    return "getFullPowerWhitelist";
                case 12:
                    return "getAppIdWhitelistExceptIdle";
                case 13:
                    return "getAppIdWhitelist";
                case 14:
                    return "getAppIdUserWhitelist";
                case 15:
                    return "getAppIdTempWhitelist";
                case 16:
                    return "isPowerSaveWhitelistExceptIdleApp";
                case 17:
                    return "isPowerSaveWhitelistApp";
                case 18:
                    return "addPowerSaveTempWhitelistApp";
                case 19:
                    return "addPowerSaveTempWhitelistAppForMms";
                case 20:
                    return "addPowerSaveTempWhitelistAppForSms";
                case 21:
                    return "whitelistAppTemporarily";
                case 22:
                    return "exitIdle";
                case 23:
                    return "setPreIdleTimeoutMode";
                case 24:
                    return "resetPreIdleTimeoutMode";
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
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            addPowerSaveWhitelistApp(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _arg02 = data.createStringArrayList();
                            int _result = addPowerSaveWhitelistApps(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            removePowerSaveWhitelistApp(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            removeSystemPowerWhitelistApp(_arg04);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            restoreSystemPowerWhitelistApp(_arg05);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result2 = getRemovedSystemPowerWhitelistApps();
                            reply.writeNoException();
                            reply.writeStringArray(_result2);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result3 = getSystemPowerWhitelistExceptIdle();
                            reply.writeNoException();
                            reply.writeStringArray(_result3);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result4 = getSystemPowerWhitelist();
                            reply.writeNoException();
                            reply.writeStringArray(_result4);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result5 = getUserPowerWhitelist();
                            reply.writeNoException();
                            reply.writeStringArray(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result6 = getFullPowerWhitelistExceptIdle();
                            reply.writeNoException();
                            reply.writeStringArray(_result6);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result7 = getFullPowerWhitelist();
                            reply.writeNoException();
                            reply.writeStringArray(_result7);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result8 = getAppIdWhitelistExceptIdle();
                            reply.writeNoException();
                            reply.writeIntArray(_result8);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result9 = getAppIdWhitelist();
                            reply.writeNoException();
                            reply.writeIntArray(_result9);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result10 = getAppIdUserWhitelist();
                            reply.writeNoException();
                            reply.writeIntArray(_result10);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result11 = getAppIdTempWhitelist();
                            reply.writeNoException();
                            reply.writeIntArray(_result11);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            boolean isPowerSaveWhitelistExceptIdleApp = isPowerSaveWhitelistExceptIdleApp(_arg06);
                            reply.writeNoException();
                            reply.writeInt(isPowerSaveWhitelistExceptIdleApp ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            boolean isPowerSaveWhitelistApp = isPowerSaveWhitelistApp(_arg07);
                            reply.writeNoException();
                            reply.writeInt(isPowerSaveWhitelistApp ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            long _arg1 = data.readLong();
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            String _arg4 = data.readString();
                            addPowerSaveTempWhitelistApp(_arg08, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _arg12 = data.readInt();
                            int _arg22 = data.readInt();
                            String _arg32 = data.readString();
                            long _result12 = addPowerSaveTempWhitelistAppForMms(_arg09, _arg12, _arg22, _arg32);
                            reply.writeNoException();
                            reply.writeLong(_result12);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg23 = data.readInt();
                            String _arg33 = data.readString();
                            long _result13 = addPowerSaveTempWhitelistAppForSms(_arg010, _arg13, _arg23, _arg33);
                            reply.writeNoException();
                            reply.writeLong(_result13);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            int _arg14 = data.readInt();
                            int _arg24 = data.readInt();
                            String _arg34 = data.readString();
                            long _result14 = whitelistAppTemporarily(_arg011, _arg14, _arg24, _arg34);
                            reply.writeNoException();
                            reply.writeLong(_result14);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            exitIdle(_arg012);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _result15 = setPreIdleTimeoutMode(_arg013);
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            resetPreIdleTimeoutMode();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IDeviceIdleController {
            public static IDeviceIdleController sDefaultImpl;
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

            @Override // android.os.IDeviceIdleController
            public void addPowerSaveWhitelistApp(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addPowerSaveWhitelistApp(name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int addPowerSaveWhitelistApps(List<String> packageNames) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringList(packageNames);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveWhitelistApps(packageNames);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void removePowerSaveWhitelistApp(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removePowerSaveWhitelistApp(name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void removeSystemPowerWhitelistApp(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeSystemPowerWhitelistApp(name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void restoreSystemPowerWhitelistApp(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().restoreSystemPowerWhitelistApp(name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getRemovedSystemPowerWhitelistApps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemovedSystemPowerWhitelistApps();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getSystemPowerWhitelistExceptIdle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPowerWhitelistExceptIdle();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getSystemPowerWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPowerWhitelist();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getUserPowerWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserPowerWhitelist();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getFullPowerWhitelistExceptIdle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFullPowerWhitelistExceptIdle();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getFullPowerWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFullPowerWhitelist();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdWhitelistExceptIdle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdWhitelistExceptIdle();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdWhitelist();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdUserWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdUserWhitelist();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdTempWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdTempWhitelist();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public boolean isPowerSaveWhitelistExceptIdleApp(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPowerSaveWhitelistExceptIdleApp(name);
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

            @Override // android.os.IDeviceIdleController
            public boolean isPowerSaveWhitelistApp(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPowerSaveWhitelistApp(name);
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

            @Override // android.os.IDeviceIdleController
            public void addPowerSaveTempWhitelistApp(String name, long duration, int userId, int reasonCode, String reason) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(name);
                        try {
                            _data.writeLong(duration);
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
                    _data.writeInt(reasonCode);
                    try {
                        _data.writeString(reason);
                        boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().addPowerSaveTempWhitelistApp(name, duration, userId, reasonCode, reason);
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

            @Override // android.os.IDeviceIdleController
            public long addPowerSaveTempWhitelistAppForMms(String name, int userId, int reasonCode, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(userId);
                    _data.writeInt(reasonCode);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveTempWhitelistAppForMms(name, userId, reasonCode, reason);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long addPowerSaveTempWhitelistAppForSms(String name, int userId, int reasonCode, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(userId);
                    _data.writeInt(reasonCode);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveTempWhitelistAppForSms(name, userId, reasonCode, reason);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long whitelistAppTemporarily(String name, int userId, int reasonCode, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(userId);
                    _data.writeInt(reasonCode);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().whitelistAppTemporarily(name, userId, reasonCode, reason);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void exitIdle(String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().exitIdle(reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int setPreIdleTimeoutMode(int Mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(Mode);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPreIdleTimeoutMode(Mode);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void resetPreIdleTimeoutMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetPreIdleTimeoutMode();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceIdleController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDeviceIdleController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
