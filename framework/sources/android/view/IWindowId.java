package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IWindowFocusObserver;
/* loaded from: classes3.dex */
public interface IWindowId extends IInterface {
    boolean isFocused() throws RemoteException;

    void registerFocusObserver(IWindowFocusObserver iWindowFocusObserver) throws RemoteException;

    void unregisterFocusObserver(IWindowFocusObserver iWindowFocusObserver) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowId {
        @Override // android.view.IWindowId
        public void registerFocusObserver(IWindowFocusObserver observer) throws RemoteException {
        }

        @Override // android.view.IWindowId
        public void unregisterFocusObserver(IWindowFocusObserver observer) throws RemoteException {
        }

        @Override // android.view.IWindowId
        public boolean isFocused() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowId {
        public static final String DESCRIPTOR = "android.view.IWindowId";
        static final int TRANSACTION_isFocused = 3;
        static final int TRANSACTION_registerFocusObserver = 1;
        static final int TRANSACTION_unregisterFocusObserver = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowId asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowId)) {
                return new Proxy(obj);
            }
            return (IWindowId) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerFocusObserver";
                case 2:
                    return "unregisterFocusObserver";
                case 3:
                    return "isFocused";
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
                            IWindowFocusObserver _arg0 = IWindowFocusObserver.Stub.asInterface(data.readStrongBinder());
                            registerFocusObserver(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IWindowFocusObserver _arg02 = IWindowFocusObserver.Stub.asInterface(data.readStrongBinder());
                            unregisterFocusObserver(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isFocused = isFocused();
                            reply.writeNoException();
                            reply.writeInt(isFocused ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowId {
            public static IWindowId sDefaultImpl;
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

            @Override // android.view.IWindowId
            public void registerFocusObserver(IWindowFocusObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerFocusObserver(observer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowId
            public void unregisterFocusObserver(IWindowFocusObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterFocusObserver(observer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowId
            public boolean isFocused() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFocused();
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

        public static boolean setDefaultImpl(IWindowId impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowId getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
