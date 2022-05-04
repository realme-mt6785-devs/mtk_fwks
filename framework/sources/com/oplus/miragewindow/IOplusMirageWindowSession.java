package com.oplus.miragewindow;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.miragewindow.IOplusCastScreenStateObserver;
import java.util.List;
/* loaded from: classes4.dex */
public interface IOplusMirageWindowSession extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.miragewindow.IOplusMirageWindowSession";

    void addCastScreenState(OplusCastScreenState oplusCastScreenState) throws RemoteException;

    List<OplusCastScreenState> getCastScreenStateList() throws RemoteException;

    boolean registerCastScreenStateObserver(IOplusCastScreenStateObserver iOplusCastScreenStateObserver) throws RemoteException;

    void removeCastScreenState() throws RemoteException;

    void setMirageDisplayId(int i) throws RemoteException;

    boolean unregisterCastScreenStateObserver(IOplusCastScreenStateObserver iOplusCastScreenStateObserver) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusMirageWindowSession {
        @Override // com.oplus.miragewindow.IOplusMirageWindowSession
        public void addCastScreenState(OplusCastScreenState state) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowSession
        public List<OplusCastScreenState> getCastScreenStateList() throws RemoteException {
            return null;
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowSession
        public void removeCastScreenState() throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowSession
        public boolean registerCastScreenStateObserver(IOplusCastScreenStateObserver observer) throws RemoteException {
            return false;
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowSession
        public boolean unregisterCastScreenStateObserver(IOplusCastScreenStateObserver observer) throws RemoteException {
            return false;
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowSession
        public void setMirageDisplayId(int id) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusMirageWindowSession {
        static final int TRANSACTION_addCastScreenState = 1;
        static final int TRANSACTION_getCastScreenStateList = 2;
        static final int TRANSACTION_registerCastScreenStateObserver = 4;
        static final int TRANSACTION_removeCastScreenState = 3;
        static final int TRANSACTION_setMirageDisplayId = 6;
        static final int TRANSACTION_unregisterCastScreenStateObserver = 5;

        public Stub() {
            attachInterface(this, IOplusMirageWindowSession.DESCRIPTOR);
        }

        public static IOplusMirageWindowSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusMirageWindowSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusMirageWindowSession)) {
                return new Proxy(obj);
            }
            return (IOplusMirageWindowSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addCastScreenState";
                case 2:
                    return "getCastScreenStateList";
                case 3:
                    return "removeCastScreenState";
                case 4:
                    return "registerCastScreenStateObserver";
                case 5:
                    return "unregisterCastScreenStateObserver";
                case 6:
                    return "setMirageDisplayId";
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
            OplusCastScreenState _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusMirageWindowSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusMirageWindowSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusCastScreenState.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            addCastScreenState(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusMirageWindowSession.DESCRIPTOR);
                            List<OplusCastScreenState> _result = getCastScreenStateList();
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusMirageWindowSession.DESCRIPTOR);
                            removeCastScreenState();
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IOplusMirageWindowSession.DESCRIPTOR);
                            IOplusCastScreenStateObserver _arg02 = IOplusCastScreenStateObserver.Stub.asInterface(data.readStrongBinder());
                            boolean registerCastScreenStateObserver = registerCastScreenStateObserver(_arg02);
                            reply.writeNoException();
                            reply.writeInt(registerCastScreenStateObserver ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusMirageWindowSession.DESCRIPTOR);
                            IOplusCastScreenStateObserver _arg03 = IOplusCastScreenStateObserver.Stub.asInterface(data.readStrongBinder());
                            boolean unregisterCastScreenStateObserver = unregisterCastScreenStateObserver(_arg03);
                            reply.writeNoException();
                            reply.writeInt(unregisterCastScreenStateObserver ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusMirageWindowSession.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            setMirageDisplayId(_arg04);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusMirageWindowSession {
            public static IOplusMirageWindowSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusMirageWindowSession.DESCRIPTOR;
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowSession
            public void addCastScreenState(OplusCastScreenState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowSession.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addCastScreenState(state);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowSession
            public List<OplusCastScreenState> getCastScreenStateList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCastScreenStateList();
                    }
                    _reply.readException();
                    List<OplusCastScreenState> _result = _reply.createTypedArrayList(OplusCastScreenState.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowSession
            public void removeCastScreenState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeCastScreenState();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowSession
            public boolean registerCastScreenStateObserver(IOplusCastScreenStateObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowSession.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCastScreenStateObserver(observer);
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

            @Override // com.oplus.miragewindow.IOplusMirageWindowSession
            public boolean unregisterCastScreenStateObserver(IOplusCastScreenStateObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowSession.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterCastScreenStateObserver(observer);
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

            @Override // com.oplus.miragewindow.IOplusMirageWindowSession
            public void setMirageDisplayId(int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowSession.DESCRIPTOR);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setMirageDisplayId(id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusMirageWindowSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusMirageWindowSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
