package com.oplus.splitscreen;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
import com.oplus.app.IOplusSplitScreenObserver;
import com.oplus.splitscreen.IOplusStackDividerConnection;
/* loaded from: classes4.dex */
public interface IOplusSplitScreenSession extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.splitscreen.IOplusSplitScreenSession";

    void finishDividerRemoteAnimation() throws RemoteException;

    void notifyFoldUpdatingComplete() throws RemoteException;

    void registerStackDivider(IOplusStackDividerConnection iOplusStackDividerConnection) throws RemoteException;

    void startDividerRemoteAnimation(SurfaceControl surfaceControl) throws RemoteException;

    void waitWindowDrawnAfterMinimized(IOplusSplitScreenObserver iOplusSplitScreenObserver) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusSplitScreenSession {
        @Override // com.oplus.splitscreen.IOplusSplitScreenSession
        public void notifyFoldUpdatingComplete() throws RemoteException {
        }

        @Override // com.oplus.splitscreen.IOplusSplitScreenSession
        public void registerStackDivider(IOplusStackDividerConnection conn) throws RemoteException {
        }

        @Override // com.oplus.splitscreen.IOplusSplitScreenSession
        public void waitWindowDrawnAfterMinimized(IOplusSplitScreenObserver observer) throws RemoteException {
        }

        @Override // com.oplus.splitscreen.IOplusSplitScreenSession
        public void startDividerRemoteAnimation(SurfaceControl leash) throws RemoteException {
        }

        @Override // com.oplus.splitscreen.IOplusSplitScreenSession
        public void finishDividerRemoteAnimation() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusSplitScreenSession {
        static final int TRANSACTION_finishDividerRemoteAnimation = 5;
        static final int TRANSACTION_notifyFoldUpdatingComplete = 1;
        static final int TRANSACTION_registerStackDivider = 2;
        static final int TRANSACTION_startDividerRemoteAnimation = 4;
        static final int TRANSACTION_waitWindowDrawnAfterMinimized = 3;

        public Stub() {
            attachInterface(this, IOplusSplitScreenSession.DESCRIPTOR);
        }

        public static IOplusSplitScreenSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusSplitScreenSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusSplitScreenSession)) {
                return new Proxy(obj);
            }
            return (IOplusSplitScreenSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyFoldUpdatingComplete";
                case 2:
                    return "registerStackDivider";
                case 3:
                    return "waitWindowDrawnAfterMinimized";
                case 4:
                    return "startDividerRemoteAnimation";
                case 5:
                    return "finishDividerRemoteAnimation";
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
                    reply.writeString(IOplusSplitScreenSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusSplitScreenSession.DESCRIPTOR);
                            notifyFoldUpdatingComplete();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusSplitScreenSession.DESCRIPTOR);
                            IOplusStackDividerConnection _arg0 = IOplusStackDividerConnection.Stub.asInterface(data.readStrongBinder());
                            registerStackDivider(_arg0);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IOplusSplitScreenSession.DESCRIPTOR);
                            IOplusSplitScreenObserver _arg02 = IOplusSplitScreenObserver.Stub.asInterface(data.readStrongBinder());
                            waitWindowDrawnAfterMinimized(_arg02);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IOplusSplitScreenSession.DESCRIPTOR);
                            SurfaceControl _arg03 = new SurfaceControl();
                            startDividerRemoteAnimation(_arg03);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg03.writeToParcel(reply, 1);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusSplitScreenSession.DESCRIPTOR);
                            finishDividerRemoteAnimation();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusSplitScreenSession {
            public static IOplusSplitScreenSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusSplitScreenSession.DESCRIPTOR;
            }

            @Override // com.oplus.splitscreen.IOplusSplitScreenSession
            public void notifyFoldUpdatingComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusSplitScreenSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyFoldUpdatingComplete();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.splitscreen.IOplusSplitScreenSession
            public void registerStackDivider(IOplusStackDividerConnection conn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusSplitScreenSession.DESCRIPTOR);
                    _data.writeStrongBinder(conn != null ? conn.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerStackDivider(conn);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.splitscreen.IOplusSplitScreenSession
            public void waitWindowDrawnAfterMinimized(IOplusSplitScreenObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusSplitScreenSession.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().waitWindowDrawnAfterMinimized(observer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.splitscreen.IOplusSplitScreenSession
            public void startDividerRemoteAnimation(SurfaceControl leash) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusSplitScreenSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            leash.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().startDividerRemoteAnimation(leash);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.splitscreen.IOplusSplitScreenSession
            public void finishDividerRemoteAnimation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusSplitScreenSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishDividerRemoteAnimation();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusSplitScreenSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusSplitScreenSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
