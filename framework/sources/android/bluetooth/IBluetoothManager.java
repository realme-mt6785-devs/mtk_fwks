package android.bluetooth;

import android.bluetooth.IBluetooth;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManagerCallback;
import android.bluetooth.IBluetoothProfileServiceConnection;
import android.bluetooth.IBluetoothStateChangeCallback;
import android.content.AttributionSource;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IBluetoothManager extends IInterface {
    boolean bindBluetoothProfileService(int i, IBluetoothProfileServiceConnection iBluetoothProfileServiceConnection) throws RemoteException;

    boolean disable(AttributionSource attributionSource, boolean z) throws RemoteException;

    boolean disableBle(AttributionSource attributionSource, IBinder iBinder) throws RemoteException;

    boolean enable(AttributionSource attributionSource) throws RemoteException;

    boolean enableBle(AttributionSource attributionSource, IBinder iBinder) throws RemoteException;

    boolean enableNoAutoConnect(AttributionSource attributionSource) throws RemoteException;

    String getAddress(AttributionSource attributionSource) throws RemoteException;

    IBluetoothGatt getBluetoothGatt() throws RemoteException;

    String getName(AttributionSource attributionSource) throws RemoteException;

    int getState() throws RemoteException;

    List<String> getSystemConfigEnabledProfilesForPackage(String str) throws RemoteException;

    boolean isBleAppPresent() throws RemoteException;

    boolean isBleScanAlwaysAvailable() throws RemoteException;

    boolean isHearingAidProfileSupported() throws RemoteException;

    boolean isLeAudioProfileSupported() throws RemoteException;

    boolean onFactoryReset(AttributionSource attributionSource) throws RemoteException;

    IBluetooth registerAdapter(IBluetoothManagerCallback iBluetoothManagerCallback) throws RemoteException;

    void registerStateChangeCallback(IBluetoothStateChangeCallback iBluetoothStateChangeCallback) throws RemoteException;

    void unbindBluetoothProfileService(int i, IBluetoothProfileServiceConnection iBluetoothProfileServiceConnection) throws RemoteException;

    void unregisterAdapter(IBluetoothManagerCallback iBluetoothManagerCallback) throws RemoteException;

    void unregisterStateChangeCallback(IBluetoothStateChangeCallback iBluetoothStateChangeCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothManager {
        @Override // android.bluetooth.IBluetoothManager
        public IBluetooth registerAdapter(IBluetoothManagerCallback callback) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothManager
        public void unregisterAdapter(IBluetoothManagerCallback callback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothManager
        public void registerStateChangeCallback(IBluetoothStateChangeCallback callback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothManager
        public void unregisterStateChangeCallback(IBluetoothStateChangeCallback callback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean enable(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean enableNoAutoConnect(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean disable(AttributionSource attributionSource, boolean persist) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public int getState() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothManager
        public IBluetoothGatt getBluetoothGatt() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean bindBluetoothProfileService(int profile, IBluetoothProfileServiceConnection proxy) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public void unbindBluetoothProfileService(int profile, IBluetoothProfileServiceConnection proxy) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothManager
        public String getAddress(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothManager
        public String getName(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean onFactoryReset(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean isBleScanAlwaysAvailable() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean enableBle(AttributionSource attributionSource, IBinder b) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean disableBle(AttributionSource attributionSource, IBinder b) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean isBleAppPresent() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean isHearingAidProfileSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothManager
        public List<String> getSystemConfigEnabledProfilesForPackage(String packageName) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothManager
        public boolean isLeAudioProfileSupported() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothManager {
        public static final String DESCRIPTOR = "android.bluetooth.IBluetoothManager";
        static final int TRANSACTION_bindBluetoothProfileService = 10;
        static final int TRANSACTION_disable = 7;
        static final int TRANSACTION_disableBle = 17;
        static final int TRANSACTION_enable = 5;
        static final int TRANSACTION_enableBle = 16;
        static final int TRANSACTION_enableNoAutoConnect = 6;
        static final int TRANSACTION_getAddress = 12;
        static final int TRANSACTION_getBluetoothGatt = 9;
        static final int TRANSACTION_getName = 13;
        static final int TRANSACTION_getState = 8;
        static final int TRANSACTION_getSystemConfigEnabledProfilesForPackage = 20;
        static final int TRANSACTION_isBleAppPresent = 18;
        static final int TRANSACTION_isBleScanAlwaysAvailable = 15;
        static final int TRANSACTION_isHearingAidProfileSupported = 19;
        static final int TRANSACTION_isLeAudioProfileSupported = 21;
        static final int TRANSACTION_onFactoryReset = 14;
        static final int TRANSACTION_registerAdapter = 1;
        static final int TRANSACTION_registerStateChangeCallback = 3;
        static final int TRANSACTION_unbindBluetoothProfileService = 11;
        static final int TRANSACTION_unregisterAdapter = 2;
        static final int TRANSACTION_unregisterStateChangeCallback = 4;

        public Stub() {
            attachInterface(this, "android.bluetooth.IBluetoothManager");
        }

        public static IBluetoothManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.bluetooth.IBluetoothManager");
            if (iin == null || !(iin instanceof IBluetoothManager)) {
                return new Proxy(obj);
            }
            return (IBluetoothManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerAdapter";
                case 2:
                    return "unregisterAdapter";
                case 3:
                    return "registerStateChangeCallback";
                case 4:
                    return "unregisterStateChangeCallback";
                case 5:
                    return "enable";
                case 6:
                    return "enableNoAutoConnect";
                case 7:
                    return "disable";
                case 8:
                    return "getState";
                case 9:
                    return "getBluetoothGatt";
                case 10:
                    return "bindBluetoothProfileService";
                case 11:
                    return "unbindBluetoothProfileService";
                case 12:
                    return "getAddress";
                case 13:
                    return "getName";
                case 14:
                    return "onFactoryReset";
                case 15:
                    return "isBleScanAlwaysAvailable";
                case 16:
                    return "enableBle";
                case 17:
                    return "disableBle";
                case 18:
                    return "isBleAppPresent";
                case 19:
                    return "isHearingAidProfileSupported";
                case 20:
                    return "getSystemConfigEnabledProfilesForPackage";
                case 21:
                    return "isLeAudioProfileSupported";
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
            AttributionSource _arg0;
            AttributionSource _arg02;
            AttributionSource _arg03;
            AttributionSource _arg04;
            AttributionSource _arg05;
            AttributionSource _arg06;
            AttributionSource _arg07;
            AttributionSource _arg08;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString("android.bluetooth.IBluetoothManager");
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            IBluetoothManagerCallback _arg09 = IBluetoothManagerCallback.Stub.asInterface(data.readStrongBinder());
                            IBluetooth _result = registerAdapter(_arg09);
                            reply.writeNoException();
                            if (_result != null) {
                                iBinder = _result.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 2:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            IBluetoothManagerCallback _arg010 = IBluetoothManagerCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterAdapter(_arg010);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            IBluetoothStateChangeCallback _arg011 = IBluetoothStateChangeCallback.Stub.asInterface(data.readStrongBinder());
                            registerStateChangeCallback(_arg011);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            IBluetoothStateChangeCallback _arg012 = IBluetoothStateChangeCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterStateChangeCallback(_arg012);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg0 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean enable = enable(_arg0);
                            reply.writeNoException();
                            reply.writeInt(enable ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg02 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            boolean enableNoAutoConnect = enableNoAutoConnect(_arg02);
                            reply.writeNoException();
                            reply.writeInt(enableNoAutoConnect ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg03 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            boolean _arg1 = data.readInt() != 0;
                            boolean disable = disable(_arg03, _arg1);
                            reply.writeNoException();
                            reply.writeInt(disable ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            int _result2 = getState();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 9:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            IBluetoothGatt _result3 = getBluetoothGatt();
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 10:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            int _arg013 = data.readInt();
                            IBluetoothProfileServiceConnection _arg12 = IBluetoothProfileServiceConnection.Stub.asInterface(data.readStrongBinder());
                            boolean bindBluetoothProfileService = bindBluetoothProfileService(_arg013, _arg12);
                            reply.writeNoException();
                            reply.writeInt(bindBluetoothProfileService ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            int _arg014 = data.readInt();
                            IBluetoothProfileServiceConnection _arg13 = IBluetoothProfileServiceConnection.Stub.asInterface(data.readStrongBinder());
                            unbindBluetoothProfileService(_arg014, _arg13);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg04 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            String _result4 = getAddress(_arg04);
                            reply.writeNoException();
                            reply.writeString(_result4);
                            return true;
                        case 13:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg05 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            String _result5 = getName(_arg05);
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 14:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg06 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            boolean onFactoryReset = onFactoryReset(_arg06);
                            reply.writeNoException();
                            reply.writeInt(onFactoryReset ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            boolean isBleScanAlwaysAvailable = isBleScanAlwaysAvailable();
                            reply.writeNoException();
                            reply.writeInt(isBleScanAlwaysAvailable ? 1 : 0);
                            return true;
                        case 16:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg07 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            IBinder _arg14 = data.readStrongBinder();
                            boolean enableBle = enableBle(_arg07, _arg14);
                            reply.writeNoException();
                            reply.writeInt(enableBle ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            if (data.readInt() != 0) {
                                _arg08 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            IBinder _arg15 = data.readStrongBinder();
                            boolean disableBle = disableBle(_arg08, _arg15);
                            reply.writeNoException();
                            reply.writeInt(disableBle ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            boolean isBleAppPresent = isBleAppPresent();
                            reply.writeNoException();
                            reply.writeInt(isBleAppPresent ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            boolean isHearingAidProfileSupported = isHearingAidProfileSupported();
                            reply.writeNoException();
                            reply.writeInt(isHearingAidProfileSupported ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            String _arg015 = data.readString();
                            List<String> _result6 = getSystemConfigEnabledProfilesForPackage(_arg015);
                            reply.writeNoException();
                            reply.writeStringList(_result6);
                            return true;
                        case 21:
                            data.enforceInterface("android.bluetooth.IBluetoothManager");
                            boolean isLeAudioProfileSupported = isLeAudioProfileSupported();
                            reply.writeNoException();
                            reply.writeInt(isLeAudioProfileSupported ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothManager {
            public static IBluetoothManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.bluetooth.IBluetoothManager";
            }

            @Override // android.bluetooth.IBluetoothManager
            public IBluetooth registerAdapter(IBluetoothManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAdapter(callback);
                    }
                    _reply.readException();
                    IBluetooth _result = IBluetooth.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public void unregisterAdapter(IBluetoothManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterAdapter(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public void registerStateChangeCallback(IBluetoothStateChangeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerStateChangeCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public void unregisterStateChangeCallback(IBluetoothStateChangeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterStateChangeCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public boolean enable(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enable(attributionSource);
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean enableNoAutoConnect(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableNoAutoConnect(attributionSource);
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean disable(AttributionSource attributionSource, boolean persist) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(persist ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disable(attributionSource, persist);
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

            @Override // android.bluetooth.IBluetoothManager
            public int getState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public IBluetoothGatt getBluetoothGatt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothGatt();
                    }
                    _reply.readException();
                    IBluetoothGatt _result = IBluetoothGatt.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public boolean bindBluetoothProfileService(int profile, IBluetoothProfileServiceConnection proxy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeInt(profile);
                    _data.writeStrongBinder(proxy != null ? proxy.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bindBluetoothProfileService(profile, proxy);
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

            @Override // android.bluetooth.IBluetoothManager
            public void unbindBluetoothProfileService(int profile, IBluetoothProfileServiceConnection proxy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeInt(profile);
                    _data.writeStrongBinder(proxy != null ? proxy.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unbindBluetoothProfileService(profile, proxy);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public String getAddress(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAddress(attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public String getName(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName(attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public boolean onFactoryReset(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onFactoryReset(attributionSource);
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean isBleScanAlwaysAvailable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBleScanAlwaysAvailable();
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean enableBle(AttributionSource attributionSource, IBinder b) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(b);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableBle(attributionSource, b);
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean disableBle(AttributionSource attributionSource, IBinder b) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(b);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableBle(attributionSource, b);
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean isBleAppPresent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBleAppPresent();
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

            @Override // android.bluetooth.IBluetoothManager
            public boolean isHearingAidProfileSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHearingAidProfileSupported();
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

            @Override // android.bluetooth.IBluetoothManager
            public List<String> getSystemConfigEnabledProfilesForPackage(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemConfigEnabledProfilesForPackage(packageName);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothManager
            public boolean isLeAudioProfileSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLeAudioProfileSupported();
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

        public static boolean setDefaultImpl(IBluetoothManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
