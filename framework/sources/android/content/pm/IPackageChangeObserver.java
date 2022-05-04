package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPackageChangeObserver extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IPackageChangeObserver";

    void onPackageChanged(PackageChangeEvent packageChangeEvent) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPackageChangeObserver {
        @Override // android.content.pm.IPackageChangeObserver
        public void onPackageChanged(PackageChangeEvent event) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPackageChangeObserver {
        static final int TRANSACTION_onPackageChanged = 1;

        public Stub() {
            attachInterface(this, IPackageChangeObserver.DESCRIPTOR);
        }

        public static IPackageChangeObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPackageChangeObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPackageChangeObserver)) {
                return new Proxy(obj);
            }
            return (IPackageChangeObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onPackageChanged";
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
            PackageChangeEvent _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPackageChangeObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPackageChangeObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PackageChangeEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onPackageChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPackageChangeObserver {
            public static IPackageChangeObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPackageChangeObserver.DESCRIPTOR;
            }

            @Override // android.content.pm.IPackageChangeObserver
            public void onPackageChanged(PackageChangeEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPackageChangeObserver.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPackageChanged(event);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPackageChangeObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPackageChangeObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
