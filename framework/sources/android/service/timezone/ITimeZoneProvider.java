package android.service.timezone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.timezone.ITimeZoneProviderManager;
/* loaded from: classes3.dex */
public interface ITimeZoneProvider extends IInterface {
    public static final String DESCRIPTOR = "android.service.timezone.ITimeZoneProvider";

    void startUpdates(ITimeZoneProviderManager iTimeZoneProviderManager, long j) throws RemoteException;

    void stopUpdates() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITimeZoneProvider {
        @Override // android.service.timezone.ITimeZoneProvider
        public void startUpdates(ITimeZoneProviderManager manager, long initializationTimeoutMillis) throws RemoteException {
        }

        @Override // android.service.timezone.ITimeZoneProvider
        public void stopUpdates() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITimeZoneProvider {
        static final int TRANSACTION_startUpdates = 1;
        static final int TRANSACTION_stopUpdates = 2;

        public Stub() {
            attachInterface(this, ITimeZoneProvider.DESCRIPTOR);
        }

        public static ITimeZoneProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITimeZoneProvider.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITimeZoneProvider)) {
                return new Proxy(obj);
            }
            return (ITimeZoneProvider) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startUpdates";
                case 2:
                    return "stopUpdates";
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
                    reply.writeString(ITimeZoneProvider.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITimeZoneProvider.DESCRIPTOR);
                            ITimeZoneProviderManager _arg0 = ITimeZoneProviderManager.Stub.asInterface(data.readStrongBinder());
                            long _arg1 = data.readLong();
                            startUpdates(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(ITimeZoneProvider.DESCRIPTOR);
                            stopUpdates();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITimeZoneProvider {
            public static ITimeZoneProvider sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITimeZoneProvider.DESCRIPTOR;
            }

            @Override // android.service.timezone.ITimeZoneProvider
            public void startUpdates(ITimeZoneProviderManager manager, long initializationTimeoutMillis) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneProvider.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeLong(initializationTimeoutMillis);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startUpdates(manager, initializationTimeoutMillis);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.timezone.ITimeZoneProvider
            public void stopUpdates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneProvider.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopUpdates();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeZoneProvider impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITimeZoneProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
