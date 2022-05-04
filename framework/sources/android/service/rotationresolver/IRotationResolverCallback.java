package android.service.rotationresolver;

import android.os.Binder;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IRotationResolverCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.rotationresolver.IRotationResolverCallback";

    void onCancellable(ICancellationSignal iCancellationSignal) throws RemoteException;

    void onFailure(int i) throws RemoteException;

    void onSuccess(int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRotationResolverCallback {
        @Override // android.service.rotationresolver.IRotationResolverCallback
        public void onCancellable(ICancellationSignal cancellation) throws RemoteException {
        }

        @Override // android.service.rotationresolver.IRotationResolverCallback
        public void onSuccess(int recommendedRotation) throws RemoteException {
        }

        @Override // android.service.rotationresolver.IRotationResolverCallback
        public void onFailure(int error) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRotationResolverCallback {
        static final int TRANSACTION_onCancellable = 1;
        static final int TRANSACTION_onFailure = 3;
        static final int TRANSACTION_onSuccess = 2;

        public Stub() {
            attachInterface(this, IRotationResolverCallback.DESCRIPTOR);
        }

        public static IRotationResolverCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRotationResolverCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRotationResolverCallback)) {
                return new Proxy(obj);
            }
            return (IRotationResolverCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCancellable";
                case 2:
                    return "onSuccess";
                case 3:
                    return "onFailure";
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
                    reply.writeString(IRotationResolverCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRotationResolverCallback.DESCRIPTOR);
                            ICancellationSignal _arg0 = ICancellationSignal.Stub.asInterface(data.readStrongBinder());
                            onCancellable(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IRotationResolverCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onSuccess(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IRotationResolverCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onFailure(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRotationResolverCallback {
            public static IRotationResolverCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRotationResolverCallback.DESCRIPTOR;
            }

            @Override // android.service.rotationresolver.IRotationResolverCallback
            public void onCancellable(ICancellationSignal cancellation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRotationResolverCallback.DESCRIPTOR);
                    _data.writeStrongBinder(cancellation != null ? cancellation.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCancellable(cancellation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.rotationresolver.IRotationResolverCallback
            public void onSuccess(int recommendedRotation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRotationResolverCallback.DESCRIPTOR);
                    _data.writeInt(recommendedRotation);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess(recommendedRotation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.rotationresolver.IRotationResolverCallback
            public void onFailure(int error) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRotationResolverCallback.DESCRIPTOR);
                    _data.writeInt(error);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFailure(error);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRotationResolverCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRotationResolverCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
