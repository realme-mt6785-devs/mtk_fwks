package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.controls.IControlsSubscription;
/* loaded from: classes3.dex */
public interface IControlsSubscriber extends IInterface {
    public static final String DESCRIPTOR = "android.service.controls.IControlsSubscriber";

    void onComplete(IBinder iBinder) throws RemoteException;

    void onError(IBinder iBinder, String str) throws RemoteException;

    void onNext(IBinder iBinder, Control control) throws RemoteException;

    void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IControlsSubscriber {
        @Override // android.service.controls.IControlsSubscriber
        public void onSubscribe(IBinder token, IControlsSubscription cs) throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onNext(IBinder token, Control c) throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onError(IBinder token, String s) throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onComplete(IBinder token) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IControlsSubscriber {
        static final int TRANSACTION_onComplete = 4;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onNext = 2;
        static final int TRANSACTION_onSubscribe = 1;

        public Stub() {
            attachInterface(this, IControlsSubscriber.DESCRIPTOR);
        }

        public static IControlsSubscriber asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IControlsSubscriber.DESCRIPTOR);
            if (iin == null || !(iin instanceof IControlsSubscriber)) {
                return new Proxy(obj);
            }
            return (IControlsSubscriber) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSubscribe";
                case 2:
                    return "onNext";
                case 3:
                    return "onError";
                case 4:
                    return "onComplete";
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
            Control _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IControlsSubscriber.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IControlsSubscriber.DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            IControlsSubscription _arg12 = IControlsSubscription.Stub.asInterface(data.readStrongBinder());
                            onSubscribe(_arg0, _arg12);
                            return true;
                        case 2:
                            data.enforceInterface(IControlsSubscriber.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg1 = Control.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onNext(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IControlsSubscriber.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            String _arg13 = data.readString();
                            onError(_arg03, _arg13);
                            return true;
                        case 4:
                            data.enforceInterface(IControlsSubscriber.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            onComplete(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IControlsSubscriber {
            public static IControlsSubscriber sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IControlsSubscriber.DESCRIPTOR;
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onSubscribe(IBinder token, IControlsSubscription cs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsSubscriber.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeStrongBinder(cs != null ? cs.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSubscribe(token, cs);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onNext(IBinder token, Control c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsSubscriber.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (c != null) {
                        _data.writeInt(1);
                        c.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNext(token, c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onError(IBinder token, String s) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsSubscriber.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(s);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(token, s);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onComplete(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsSubscriber.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(token);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControlsSubscriber impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IControlsSubscriber getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
