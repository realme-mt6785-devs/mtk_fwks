package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.window.IDisplayAreaOrganizerController;
import android.window.ITaskOrganizerController;
import android.window.ITransitionPlayer;
import android.window.IWindowContainerTransactionCallback;
/* loaded from: classes3.dex */
public interface IWindowOrganizerController extends IInterface {
    public static final String DESCRIPTOR = "android.window.IWindowOrganizerController";

    void abortSync(int i) throws RemoteException;

    int applySyncTransaction(WindowContainerTransaction windowContainerTransaction, IWindowContainerTransactionCallback iWindowContainerTransactionCallback) throws RemoteException;

    void applyTransaction(WindowContainerTransaction windowContainerTransaction) throws RemoteException;

    int finishTransition(IBinder iBinder, WindowContainerTransaction windowContainerTransaction, IWindowContainerTransactionCallback iWindowContainerTransactionCallback) throws RemoteException;

    IDisplayAreaOrganizerController getDisplayAreaOrganizerController() throws RemoteException;

    ITaskOrganizerController getTaskOrganizerController() throws RemoteException;

    void registerTransitionPlayer(ITransitionPlayer iTransitionPlayer) throws RemoteException;

    IBinder startTransition(int i, IBinder iBinder, WindowContainerTransaction windowContainerTransaction) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowOrganizerController {
        @Override // android.window.IWindowOrganizerController
        public void applyTransaction(WindowContainerTransaction t) throws RemoteException {
        }

        @Override // android.window.IWindowOrganizerController
        public int applySyncTransaction(WindowContainerTransaction t, IWindowContainerTransactionCallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.window.IWindowOrganizerController
        public IBinder startTransition(int type, IBinder transitionToken, WindowContainerTransaction t) throws RemoteException {
            return null;
        }

        @Override // android.window.IWindowOrganizerController
        public int finishTransition(IBinder transitionToken, WindowContainerTransaction t, IWindowContainerTransactionCallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.window.IWindowOrganizerController
        public ITaskOrganizerController getTaskOrganizerController() throws RemoteException {
            return null;
        }

        @Override // android.window.IWindowOrganizerController
        public IDisplayAreaOrganizerController getDisplayAreaOrganizerController() throws RemoteException {
            return null;
        }

        @Override // android.window.IWindowOrganizerController
        public void registerTransitionPlayer(ITransitionPlayer player) throws RemoteException {
        }

        @Override // android.window.IWindowOrganizerController
        public void abortSync(int syncId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowOrganizerController {
        static final int TRANSACTION_abortSync = 8;
        static final int TRANSACTION_applySyncTransaction = 2;
        static final int TRANSACTION_applyTransaction = 1;
        static final int TRANSACTION_finishTransition = 4;
        static final int TRANSACTION_getDisplayAreaOrganizerController = 6;
        static final int TRANSACTION_getTaskOrganizerController = 5;
        static final int TRANSACTION_registerTransitionPlayer = 7;
        static final int TRANSACTION_startTransition = 3;

        public Stub() {
            attachInterface(this, IWindowOrganizerController.DESCRIPTOR);
        }

        public static IWindowOrganizerController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IWindowOrganizerController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowOrganizerController)) {
                return new Proxy(obj);
            }
            return (IWindowOrganizerController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "applyTransaction";
                case 2:
                    return "applySyncTransaction";
                case 3:
                    return "startTransition";
                case 4:
                    return "finishTransition";
                case 5:
                    return "getTaskOrganizerController";
                case 6:
                    return "getDisplayAreaOrganizerController";
                case 7:
                    return "registerTransitionPlayer";
                case 8:
                    return "abortSync";
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
            WindowContainerTransaction _arg02;
            WindowContainerTransaction _arg2;
            WindowContainerTransaction _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IWindowOrganizerController.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = WindowContainerTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            applyTransaction(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = WindowContainerTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            IWindowContainerTransactionCallback _arg12 = IWindowContainerTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            int _result = applySyncTransaction(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 3:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IBinder _arg13 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg2 = WindowContainerTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IBinder _result2 = startTransition(_arg03, _arg13, _arg2);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result2);
                            return true;
                        case 4:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg1 = WindowContainerTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IWindowContainerTransactionCallback _arg22 = IWindowContainerTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            int _result3 = finishTransition(_arg04, _arg1, _arg22);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 5:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            ITaskOrganizerController _result4 = getTaskOrganizerController();
                            reply.writeNoException();
                            if (_result4 != null) {
                                iBinder = _result4.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 6:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            IDisplayAreaOrganizerController _result5 = getDisplayAreaOrganizerController();
                            reply.writeNoException();
                            if (_result5 != null) {
                                iBinder = _result5.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 7:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            ITransitionPlayer _arg05 = ITransitionPlayer.Stub.asInterface(data.readStrongBinder());
                            registerTransitionPlayer(_arg05);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IWindowOrganizerController.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            abortSync(_arg06);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowOrganizerController {
            public static IWindowOrganizerController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IWindowOrganizerController.DESCRIPTOR;
            }

            @Override // android.window.IWindowOrganizerController
            public void applyTransaction(WindowContainerTransaction t) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    if (t != null) {
                        _data.writeInt(1);
                        t.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyTransaction(t);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public int applySyncTransaction(WindowContainerTransaction t, IWindowContainerTransactionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    if (t != null) {
                        _data.writeInt(1);
                        t.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().applySyncTransaction(t, callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public IBinder startTransition(int type, IBinder transitionToken, WindowContainerTransaction t) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeStrongBinder(transitionToken);
                    if (t != null) {
                        _data.writeInt(1);
                        t.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startTransition(type, transitionToken, t);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public int finishTransition(IBinder transitionToken, WindowContainerTransaction t, IWindowContainerTransactionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    _data.writeStrongBinder(transitionToken);
                    if (t != null) {
                        _data.writeInt(1);
                        t.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finishTransition(transitionToken, t, callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public ITaskOrganizerController getTaskOrganizerController() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTaskOrganizerController();
                    }
                    _reply.readException();
                    ITaskOrganizerController _result = ITaskOrganizerController.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public IDisplayAreaOrganizerController getDisplayAreaOrganizerController() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayAreaOrganizerController();
                    }
                    _reply.readException();
                    IDisplayAreaOrganizerController _result = IDisplayAreaOrganizerController.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public void registerTransitionPlayer(ITransitionPlayer player) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    _data.writeStrongBinder(player != null ? player.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerTransitionPlayer(player);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.IWindowOrganizerController
            public void abortSync(int syncId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowOrganizerController.DESCRIPTOR);
                    _data.writeInt(syncId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abortSync(syncId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowOrganizerController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowOrganizerController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
