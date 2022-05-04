package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IRemoteTransitionFinishedCallback extends IInterface {
    public static final String DESCRIPTOR = "android.window.IRemoteTransitionFinishedCallback";

    void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRemoteTransitionFinishedCallback {
        @Override // android.window.IRemoteTransitionFinishedCallback
        public void onTransitionFinished(WindowContainerTransaction wct) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRemoteTransitionFinishedCallback {
        static final int TRANSACTION_onTransitionFinished = 1;

        public Stub() {
            attachInterface(this, IRemoteTransitionFinishedCallback.DESCRIPTOR);
        }

        public static IRemoteTransitionFinishedCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRemoteTransitionFinishedCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteTransitionFinishedCallback)) {
                return new Proxy(obj);
            }
            return (IRemoteTransitionFinishedCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTransitionFinished";
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
            WindowContainerTransaction _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRemoteTransitionFinishedCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRemoteTransitionFinishedCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = WindowContainerTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onTransitionFinished(_arg0);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRemoteTransitionFinishedCallback {
            public static IRemoteTransitionFinishedCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoteTransitionFinishedCallback.DESCRIPTOR;
            }

            @Override // android.window.IRemoteTransitionFinishedCallback
            public void onTransitionFinished(WindowContainerTransaction wct) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoteTransitionFinishedCallback.DESCRIPTOR);
                    if (wct != null) {
                        _data.writeInt(1);
                        wct.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onTransitionFinished(wct);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteTransitionFinishedCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRemoteTransitionFinishedCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
