package android.location.provider;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface ILocationProviderManager extends IInterface {
    public static final String DESCRIPTOR = "android.location.provider.ILocationProviderManager";

    void onFlushComplete() throws RemoteException;

    void onInitialize(boolean z, ProviderProperties providerProperties, String str) throws RemoteException;

    void onReportLocation(Location location) throws RemoteException;

    void onReportLocations(List<Location> list) throws RemoteException;

    void onSetAllowed(boolean z) throws RemoteException;

    void onSetProperties(ProviderProperties providerProperties) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ILocationProviderManager {
        @Override // android.location.provider.ILocationProviderManager
        public void onInitialize(boolean allowed, ProviderProperties properties, String attributionTag) throws RemoteException {
        }

        @Override // android.location.provider.ILocationProviderManager
        public void onSetAllowed(boolean allowed) throws RemoteException {
        }

        @Override // android.location.provider.ILocationProviderManager
        public void onSetProperties(ProviderProperties properties) throws RemoteException {
        }

        @Override // android.location.provider.ILocationProviderManager
        public void onReportLocation(Location location) throws RemoteException {
        }

        @Override // android.location.provider.ILocationProviderManager
        public void onReportLocations(List<Location> locations) throws RemoteException {
        }

        @Override // android.location.provider.ILocationProviderManager
        public void onFlushComplete() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ILocationProviderManager {
        static final int TRANSACTION_onFlushComplete = 6;
        static final int TRANSACTION_onInitialize = 1;
        static final int TRANSACTION_onReportLocation = 4;
        static final int TRANSACTION_onReportLocations = 5;
        static final int TRANSACTION_onSetAllowed = 2;
        static final int TRANSACTION_onSetProperties = 3;

        public Stub() {
            attachInterface(this, ILocationProviderManager.DESCRIPTOR);
        }

        public static ILocationProviderManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILocationProviderManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ILocationProviderManager)) {
                return new Proxy(obj);
            }
            return (ILocationProviderManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onInitialize";
                case 2:
                    return "onSetAllowed";
                case 3:
                    return "onSetProperties";
                case 4:
                    return "onReportLocation";
                case 5:
                    return "onReportLocations";
                case 6:
                    return "onFlushComplete";
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
            ProviderProperties _arg1;
            ProviderProperties _arg0;
            Location _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ILocationProviderManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg03 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ILocationProviderManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = ProviderProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg2 = data.readString();
                            onInitialize(_arg03, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ILocationProviderManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            onSetAllowed(_arg03);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ILocationProviderManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ProviderProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onSetProperties(_arg0);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ILocationProviderManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Location.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onReportLocation(_arg02);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ILocationProviderManager.DESCRIPTOR);
                            onReportLocations(data.createTypedArrayList(Location.CREATOR));
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(ILocationProviderManager.DESCRIPTOR);
                            onFlushComplete();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ILocationProviderManager {
            public static ILocationProviderManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILocationProviderManager.DESCRIPTOR;
            }

            @Override // android.location.provider.ILocationProviderManager
            public void onInitialize(boolean allowed, ProviderProperties properties, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocationProviderManager.DESCRIPTOR);
                    _data.writeInt(allowed ? 1 : 0);
                    if (properties != null) {
                        _data.writeInt(1);
                        properties.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onInitialize(allowed, properties, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.provider.ILocationProviderManager
            public void onSetAllowed(boolean allowed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocationProviderManager.DESCRIPTOR);
                    _data.writeInt(allowed ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onSetAllowed(allowed);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.provider.ILocationProviderManager
            public void onSetProperties(ProviderProperties properties) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocationProviderManager.DESCRIPTOR);
                    if (properties != null) {
                        _data.writeInt(1);
                        properties.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onSetProperties(properties);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.provider.ILocationProviderManager
            public void onReportLocation(Location location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocationProviderManager.DESCRIPTOR);
                    if (location != null) {
                        _data.writeInt(1);
                        location.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onReportLocation(location);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.provider.ILocationProviderManager
            public void onReportLocations(List<Location> locations) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocationProviderManager.DESCRIPTOR);
                    _data.writeTypedList(locations);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onReportLocations(locations);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.provider.ILocationProviderManager
            public void onFlushComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocationProviderManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onFlushComplete();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationProviderManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILocationProviderManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
