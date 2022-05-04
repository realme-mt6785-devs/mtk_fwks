package android.os;

import android.view.KeyEvent;
/* loaded from: classes2.dex */
public interface IOplusKeyEventObserver extends IInterface {
    public static final String DESCRIPTOR = "android.os.IOplusKeyEventObserver";

    void onKeyEvent(KeyEvent keyEvent) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOplusKeyEventObserver {
        @Override // android.os.IOplusKeyEventObserver
        public void onKeyEvent(KeyEvent info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOplusKeyEventObserver {
        static final int TRANSACTION_onKeyEvent = 1;

        public Stub() {
            attachInterface(this, IOplusKeyEventObserver.DESCRIPTOR);
        }

        public static IOplusKeyEventObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusKeyEventObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusKeyEventObserver)) {
                return new Proxy(obj);
            }
            return (IOplusKeyEventObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onKeyEvent";
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
            KeyEvent _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusKeyEventObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusKeyEventObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = KeyEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onKeyEvent(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOplusKeyEventObserver {
            public static IOplusKeyEventObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusKeyEventObserver.DESCRIPTOR;
            }

            @Override // android.os.IOplusKeyEventObserver
            public void onKeyEvent(KeyEvent info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusKeyEventObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyEvent(info);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusKeyEventObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusKeyEventObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
