package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface ILocationListener extends IInterface {
    void onFlushComplete(int i) throws RemoteException;

    void onLocationChanged(List<Location> list, IRemoteCallback iRemoteCallback) throws RemoteException;

    void onProviderEnabledChanged(String str, boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ILocationListener {
        @Override // android.location.ILocationListener
        public void onLocationChanged(List<Location> locations, IRemoteCallback onCompleteCallback) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onProviderEnabledChanged(String provider, boolean enabled) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onFlushComplete(int requestCode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ILocationListener {
        public static final String DESCRIPTOR = "android.location.ILocationListener";
        static final int TRANSACTION_onFlushComplete = 3;
        static final int TRANSACTION_onLocationChanged = 1;
        static final int TRANSACTION_onProviderEnabledChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILocationListener)) {
                return new Proxy(obj);
            }
            return (ILocationListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onLocationChanged";
                case 2:
                    return "onProviderEnabledChanged";
                case 3:
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            List<Location> _arg0 = data.createTypedArrayList(Location.CREATOR);
                            IRemoteCallback _arg1 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            onLocationChanged(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            boolean _arg12 = data.readInt() != 0;
                            onProviderEnabledChanged(_arg02, _arg12);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onFlushComplete(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ILocationListener {
            public static ILocationListener sDefaultImpl;
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

            @Override // android.location.ILocationListener
            public void onLocationChanged(List<Location> locations, IRemoteCallback onCompleteCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(locations);
                    _data.writeStrongBinder(onCompleteCallback != null ? onCompleteCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLocationChanged(locations, onCompleteCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onProviderEnabledChanged(String provider, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProviderEnabledChanged(provider, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onFlushComplete(int requestCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(requestCode);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFlushComplete(requestCode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILocationListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
