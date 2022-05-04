package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;
/* loaded from: classes.dex */
public interface IAdbManager extends IInterface {
    public static final String DESCRIPTOR = "android.debug.IAdbManager";

    void allowDebugging(boolean z, String str) throws RemoteException;

    void allowWirelessDebugging(boolean z, String str) throws RemoteException;

    void clearDebuggingKeys() throws RemoteException;

    void denyDebugging() throws RemoteException;

    void denyWirelessDebugging() throws RemoteException;

    void disablePairing() throws RemoteException;

    void enablePairingByPairingCode() throws RemoteException;

    void enablePairingByQrCode(String str, String str2) throws RemoteException;

    int getAdbWirelessPort() throws RemoteException;

    Map getPairedDevices() throws RemoteException;

    boolean isAdbWifiQrSupported() throws RemoteException;

    boolean isAdbWifiSupported() throws RemoteException;

    void unpairDevice(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAdbManager {
        @Override // android.debug.IAdbManager
        public void allowDebugging(boolean alwaysAllow, String publicKey) throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void denyDebugging() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void clearDebuggingKeys() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void allowWirelessDebugging(boolean alwaysAllow, String bssid) throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void denyWirelessDebugging() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public Map getPairedDevices() throws RemoteException {
            return null;
        }

        @Override // android.debug.IAdbManager
        public void unpairDevice(String fingerprint) throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void enablePairingByPairingCode() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void enablePairingByQrCode(String serviceName, String password) throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public int getAdbWirelessPort() throws RemoteException {
            return 0;
        }

        @Override // android.debug.IAdbManager
        public void disablePairing() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public boolean isAdbWifiSupported() throws RemoteException {
            return false;
        }

        @Override // android.debug.IAdbManager
        public boolean isAdbWifiQrSupported() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAdbManager {
        static final int TRANSACTION_allowDebugging = 1;
        static final int TRANSACTION_allowWirelessDebugging = 4;
        static final int TRANSACTION_clearDebuggingKeys = 3;
        static final int TRANSACTION_denyDebugging = 2;
        static final int TRANSACTION_denyWirelessDebugging = 5;
        static final int TRANSACTION_disablePairing = 11;
        static final int TRANSACTION_enablePairingByPairingCode = 8;
        static final int TRANSACTION_enablePairingByQrCode = 9;
        static final int TRANSACTION_getAdbWirelessPort = 10;
        static final int TRANSACTION_getPairedDevices = 6;
        static final int TRANSACTION_isAdbWifiQrSupported = 13;
        static final int TRANSACTION_isAdbWifiSupported = 12;
        static final int TRANSACTION_unpairDevice = 7;

        public Stub() {
            attachInterface(this, IAdbManager.DESCRIPTOR);
        }

        public static IAdbManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAdbManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAdbManager)) {
                return new Proxy(obj);
            }
            return (IAdbManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "allowDebugging";
                case 2:
                    return "denyDebugging";
                case 3:
                    return "clearDebuggingKeys";
                case 4:
                    return "allowWirelessDebugging";
                case 5:
                    return "denyWirelessDebugging";
                case 6:
                    return "getPairedDevices";
                case 7:
                    return "unpairDevice";
                case 8:
                    return "enablePairingByPairingCode";
                case 9:
                    return "enablePairingByQrCode";
                case 10:
                    return "getAdbWirelessPort";
                case 11:
                    return "disablePairing";
                case 12:
                    return "isAdbWifiSupported";
                case 13:
                    return "isAdbWifiQrSupported";
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
                    reply.writeString(IAdbManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            String _arg1 = data.readString();
                            allowDebugging(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            denyDebugging();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            clearDebuggingKeys();
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            String _arg12 = data.readString();
                            allowWirelessDebugging(_arg0, _arg12);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            denyWirelessDebugging();
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            Map _result = getPairedDevices();
                            reply.writeNoException();
                            reply.writeMap(_result);
                            return true;
                        case 7:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            unpairDevice(data.readString());
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            enablePairingByPairingCode();
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            String _arg02 = data.readString();
                            String _arg13 = data.readString();
                            enablePairingByQrCode(_arg02, _arg13);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            int _result2 = getAdbWirelessPort();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 11:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            disablePairing();
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            boolean isAdbWifiSupported = isAdbWifiSupported();
                            reply.writeNoException();
                            reply.writeInt(isAdbWifiSupported ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IAdbManager.DESCRIPTOR);
                            boolean isAdbWifiQrSupported = isAdbWifiQrSupported();
                            reply.writeNoException();
                            reply.writeInt(isAdbWifiQrSupported ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAdbManager {
            public static IAdbManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAdbManager.DESCRIPTOR;
            }

            @Override // android.debug.IAdbManager
            public void allowDebugging(boolean alwaysAllow, String publicKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    _data.writeInt(alwaysAllow ? 1 : 0);
                    _data.writeString(publicKey);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allowDebugging(alwaysAllow, publicKey);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void denyDebugging() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().denyDebugging();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void clearDebuggingKeys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearDebuggingKeys();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void allowWirelessDebugging(boolean alwaysAllow, String bssid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    _data.writeInt(alwaysAllow ? 1 : 0);
                    _data.writeString(bssid);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allowWirelessDebugging(alwaysAllow, bssid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void denyWirelessDebugging() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().denyWirelessDebugging();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public Map getPairedDevices() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPairedDevices();
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void unpairDevice(String fingerprint) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    _data.writeString(fingerprint);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unpairDevice(fingerprint);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void enablePairingByPairingCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enablePairingByPairingCode();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void enablePairingByQrCode(String serviceName, String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    _data.writeString(serviceName);
                    _data.writeString(password);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enablePairingByQrCode(serviceName, password);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public int getAdbWirelessPort() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdbWirelessPort();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void disablePairing() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disablePairing();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public boolean isAdbWifiSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdbWifiSupported();
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

            @Override // android.debug.IAdbManager
            public boolean isAdbWifiQrSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdbWifiQrSupported();
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
        }

        public static boolean setDefaultImpl(IAdbManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAdbManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
