package android.net.wifi.nl80211;

import android.net.wifi.nl80211.IApInterface;
import android.net.wifi.nl80211.IClientInterface;
import android.net.wifi.nl80211.IInterfaceEventCallback;
import android.net.wifi.nl80211.IWificondEventCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IWificond extends IInterface {
    public static final String DESCRIPTOR = "android.net.wifi.nl80211.IWificond";

    List<IBinder> GetApInterfaces() throws RemoteException;

    List<IBinder> GetClientInterfaces() throws RemoteException;

    void RegisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException;

    void UnregisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException;

    IApInterface createApInterface(String str) throws RemoteException;

    IClientInterface createClientInterface(String str) throws RemoteException;

    int[] getAvailable2gChannels() throws RemoteException;

    int[] getAvailable5gNonDFSChannels() throws RemoteException;

    int[] getAvailable60gChannels() throws RemoteException;

    int[] getAvailable6gChannels() throws RemoteException;

    int[] getAvailableDFSChannels() throws RemoteException;

    DeviceWiphyCapabilities getDeviceWiphyCapabilities(String str) throws RemoteException;

    void registerWificondEventCallback(IWificondEventCallback iWificondEventCallback) throws RemoteException;

    boolean tearDownApInterface(String str) throws RemoteException;

    boolean tearDownClientInterface(String str) throws RemoteException;

    void tearDownInterfaces() throws RemoteException;

    void unregisterWificondEventCallback(IWificondEventCallback iWificondEventCallback) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IWificond {
        @Override // android.net.wifi.nl80211.IWificond
        public IApInterface createApInterface(String iface_name) throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public IClientInterface createClientInterface(String iface_name) throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public boolean tearDownApInterface(String iface_name) throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public boolean tearDownClientInterface(String iface_name) throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void tearDownInterfaces() throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWificond
        public List<IBinder> GetClientInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public List<IBinder> GetApInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable2gChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable5gNonDFSChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailableDFSChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable6gChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable60gChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void RegisterCallback(IInterfaceEventCallback callback) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void UnregisterCallback(IInterfaceEventCallback callback) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void registerWificondEventCallback(IWificondEventCallback callback) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void unregisterWificondEventCallback(IWificondEventCallback callback) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWificond
        public DeviceWiphyCapabilities getDeviceWiphyCapabilities(String iface_name) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IWificond {
        static final int TRANSACTION_GetApInterfaces = 7;
        static final int TRANSACTION_GetClientInterfaces = 6;
        static final int TRANSACTION_RegisterCallback = 13;
        static final int TRANSACTION_UnregisterCallback = 14;
        static final int TRANSACTION_createApInterface = 1;
        static final int TRANSACTION_createClientInterface = 2;
        static final int TRANSACTION_getAvailable2gChannels = 8;
        static final int TRANSACTION_getAvailable5gNonDFSChannels = 9;
        static final int TRANSACTION_getAvailable60gChannels = 12;
        static final int TRANSACTION_getAvailable6gChannels = 11;
        static final int TRANSACTION_getAvailableDFSChannels = 10;
        static final int TRANSACTION_getDeviceWiphyCapabilities = 17;
        static final int TRANSACTION_registerWificondEventCallback = 15;
        static final int TRANSACTION_tearDownApInterface = 3;
        static final int TRANSACTION_tearDownClientInterface = 4;
        static final int TRANSACTION_tearDownInterfaces = 5;
        static final int TRANSACTION_unregisterWificondEventCallback = 16;

        public Stub() {
            attachInterface(this, IWificond.DESCRIPTOR);
        }

        public static IWificond asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IWificond.DESCRIPTOR);
            if (iin == null || !(iin instanceof IWificond)) {
                return new Proxy(obj);
            }
            return (IWificond) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createApInterface";
                case 2:
                    return "createClientInterface";
                case 3:
                    return "tearDownApInterface";
                case 4:
                    return "tearDownClientInterface";
                case 5:
                    return "tearDownInterfaces";
                case 6:
                    return "GetClientInterfaces";
                case 7:
                    return "GetApInterfaces";
                case 8:
                    return "getAvailable2gChannels";
                case 9:
                    return "getAvailable5gNonDFSChannels";
                case 10:
                    return "getAvailableDFSChannels";
                case 11:
                    return "getAvailable6gChannels";
                case 12:
                    return "getAvailable60gChannels";
                case 13:
                    return "RegisterCallback";
                case 14:
                    return "UnregisterCallback";
                case 15:
                    return "registerWificondEventCallback";
                case 16:
                    return "unregisterWificondEventCallback";
                case 17:
                    return "getDeviceWiphyCapabilities";
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
                    reply.writeString(IWificond.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            String _arg0 = data.readString();
                            IApInterface _result = createApInterface(_arg0);
                            reply.writeNoException();
                            if (_result != null) {
                                iBinder = _result.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 2:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            String _arg02 = data.readString();
                            IClientInterface _result2 = createClientInterface(_arg02);
                            reply.writeNoException();
                            if (_result2 != null) {
                                iBinder = _result2.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 3:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            String _arg03 = data.readString();
                            boolean tearDownApInterface = tearDownApInterface(_arg03);
                            reply.writeNoException();
                            reply.writeInt(tearDownApInterface ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            String _arg04 = data.readString();
                            boolean tearDownClientInterface = tearDownClientInterface(_arg04);
                            reply.writeNoException();
                            reply.writeInt(tearDownClientInterface ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            tearDownInterfaces();
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            List<IBinder> _result3 = GetClientInterfaces();
                            reply.writeNoException();
                            reply.writeBinderList(_result3);
                            return true;
                        case 7:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            List<IBinder> _result4 = GetApInterfaces();
                            reply.writeNoException();
                            reply.writeBinderList(_result4);
                            return true;
                        case 8:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            int[] _result5 = getAvailable2gChannels();
                            reply.writeNoException();
                            reply.writeIntArray(_result5);
                            return true;
                        case 9:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            int[] _result6 = getAvailable5gNonDFSChannels();
                            reply.writeNoException();
                            reply.writeIntArray(_result6);
                            return true;
                        case 10:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            int[] _result7 = getAvailableDFSChannels();
                            reply.writeNoException();
                            reply.writeIntArray(_result7);
                            return true;
                        case 11:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            int[] _result8 = getAvailable6gChannels();
                            reply.writeNoException();
                            reply.writeIntArray(_result8);
                            return true;
                        case 12:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            int[] _result9 = getAvailable60gChannels();
                            reply.writeNoException();
                            reply.writeIntArray(_result9);
                            return true;
                        case 13:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            IInterfaceEventCallback _arg05 = IInterfaceEventCallback.Stub.asInterface(data.readStrongBinder());
                            RegisterCallback(_arg05);
                            return true;
                        case 14:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            IInterfaceEventCallback _arg06 = IInterfaceEventCallback.Stub.asInterface(data.readStrongBinder());
                            UnregisterCallback(_arg06);
                            return true;
                        case 15:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            IWificondEventCallback _arg07 = IWificondEventCallback.Stub.asInterface(data.readStrongBinder());
                            registerWificondEventCallback(_arg07);
                            return true;
                        case 16:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            IWificondEventCallback _arg08 = IWificondEventCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterWificondEventCallback(_arg08);
                            return true;
                        case 17:
                            data.enforceInterface(IWificond.DESCRIPTOR);
                            String _arg09 = data.readString();
                            DeviceWiphyCapabilities _result10 = getDeviceWiphyCapabilities(_arg09);
                            reply.writeNoException();
                            if (_result10 != null) {
                                reply.writeInt(1);
                                _result10.writeToParcel(reply, 1);
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
        public static class Proxy implements IWificond {
            public static IWificond sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IWificond.DESCRIPTOR;
            }

            @Override // android.net.wifi.nl80211.IWificond
            public IApInterface createApInterface(String iface_name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeString(iface_name);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createApInterface(iface_name);
                    }
                    _reply.readException();
                    IApInterface _result = IApInterface.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public IClientInterface createClientInterface(String iface_name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeString(iface_name);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createClientInterface(iface_name);
                    }
                    _reply.readException();
                    IClientInterface _result = IClientInterface.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public boolean tearDownApInterface(String iface_name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeString(iface_name);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tearDownApInterface(iface_name);
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

            @Override // android.net.wifi.nl80211.IWificond
            public boolean tearDownClientInterface(String iface_name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeString(iface_name);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tearDownClientInterface(iface_name);
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

            @Override // android.net.wifi.nl80211.IWificond
            public void tearDownInterfaces() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().tearDownInterfaces();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public List<IBinder> GetClientInterfaces() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetClientInterfaces();
                    }
                    _reply.readException();
                    List<IBinder> _result = _reply.createBinderArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public List<IBinder> GetApInterfaces() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetApInterfaces();
                    }
                    _reply.readException();
                    List<IBinder> _result = _reply.createBinderArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable2gChannels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable2gChannels();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable5gNonDFSChannels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable5gNonDFSChannels();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailableDFSChannels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableDFSChannels();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable6gChannels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable6gChannels();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable60gChannels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable60gChannels();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void RegisterCallback(IInterfaceEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().RegisterCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void UnregisterCallback(IInterfaceEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().UnregisterCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void registerWificondEventCallback(IWificondEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerWificondEventCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void unregisterWificondEventCallback(IWificondEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterWificondEventCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public DeviceWiphyCapabilities getDeviceWiphyCapabilities(String iface_name) throws RemoteException {
                DeviceWiphyCapabilities _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWificond.DESCRIPTOR);
                    _data.writeString(iface_name);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceWiphyCapabilities(iface_name);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DeviceWiphyCapabilities.CREATOR.createFromParcel(_reply);
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

        public static boolean setDefaultImpl(IWificond impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWificond getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
