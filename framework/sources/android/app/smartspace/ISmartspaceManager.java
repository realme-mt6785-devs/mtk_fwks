package android.app.smartspace;

import android.app.smartspace.ISmartspaceCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISmartspaceManager extends IInterface {
    public static final String DESCRIPTOR = "android.app.smartspace.ISmartspaceManager";

    void createSmartspaceSession(SmartspaceConfig smartspaceConfig, SmartspaceSessionId smartspaceSessionId, IBinder iBinder) throws RemoteException;

    void destroySmartspaceSession(SmartspaceSessionId smartspaceSessionId) throws RemoteException;

    void notifySmartspaceEvent(SmartspaceSessionId smartspaceSessionId, SmartspaceTargetEvent smartspaceTargetEvent) throws RemoteException;

    void registerSmartspaceUpdates(SmartspaceSessionId smartspaceSessionId, ISmartspaceCallback iSmartspaceCallback) throws RemoteException;

    void requestSmartspaceUpdate(SmartspaceSessionId smartspaceSessionId) throws RemoteException;

    void unregisterSmartspaceUpdates(SmartspaceSessionId smartspaceSessionId, ISmartspaceCallback iSmartspaceCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISmartspaceManager {
        @Override // android.app.smartspace.ISmartspaceManager
        public void createSmartspaceSession(SmartspaceConfig config, SmartspaceSessionId sessionId, IBinder token) throws RemoteException {
        }

        @Override // android.app.smartspace.ISmartspaceManager
        public void notifySmartspaceEvent(SmartspaceSessionId sessionId, SmartspaceTargetEvent event) throws RemoteException {
        }

        @Override // android.app.smartspace.ISmartspaceManager
        public void requestSmartspaceUpdate(SmartspaceSessionId sessionId) throws RemoteException {
        }

        @Override // android.app.smartspace.ISmartspaceManager
        public void registerSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
        }

        @Override // android.app.smartspace.ISmartspaceManager
        public void unregisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
        }

        @Override // android.app.smartspace.ISmartspaceManager
        public void destroySmartspaceSession(SmartspaceSessionId sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISmartspaceManager {
        static final int TRANSACTION_createSmartspaceSession = 1;
        static final int TRANSACTION_destroySmartspaceSession = 6;
        static final int TRANSACTION_notifySmartspaceEvent = 2;
        static final int TRANSACTION_registerSmartspaceUpdates = 4;
        static final int TRANSACTION_requestSmartspaceUpdate = 3;
        static final int TRANSACTION_unregisterSmartspaceUpdates = 5;

        public Stub() {
            attachInterface(this, ISmartspaceManager.DESCRIPTOR);
        }

        public static ISmartspaceManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISmartspaceManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISmartspaceManager)) {
                return new Proxy(obj);
            }
            return (ISmartspaceManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSmartspaceSession";
                case 2:
                    return "notifySmartspaceEvent";
                case 3:
                    return "requestSmartspaceUpdate";
                case 4:
                    return "registerSmartspaceUpdates";
                case 5:
                    return "unregisterSmartspaceUpdates";
                case 6:
                    return "destroySmartspaceSession";
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
            SmartspaceConfig _arg0;
            SmartspaceSessionId _arg1;
            SmartspaceSessionId _arg02;
            SmartspaceTargetEvent _arg12;
            SmartspaceSessionId _arg03;
            SmartspaceSessionId _arg04;
            SmartspaceSessionId _arg05;
            SmartspaceSessionId _arg06;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISmartspaceManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISmartspaceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SmartspaceConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IBinder _arg2 = data.readStrongBinder();
                            createSmartspaceSession(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ISmartspaceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = SmartspaceTargetEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            notifySmartspaceEvent(_arg02, _arg12);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ISmartspaceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            requestSmartspaceUpdate(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ISmartspaceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            ISmartspaceCallback _arg13 = ISmartspaceCallback.Stub.asInterface(data.readStrongBinder());
                            registerSmartspaceUpdates(_arg04, _arg13);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ISmartspaceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            ISmartspaceCallback _arg14 = ISmartspaceCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterSmartspaceUpdates(_arg05, _arg14);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(ISmartspaceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            destroySmartspaceSession(_arg06);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISmartspaceManager {
            public static ISmartspaceManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISmartspaceManager.DESCRIPTOR;
            }

            @Override // android.app.smartspace.ISmartspaceManager
            public void createSmartspaceSession(SmartspaceConfig config, SmartspaceSessionId sessionId, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceManager.DESCRIPTOR);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createSmartspaceSession(config, sessionId, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.smartspace.ISmartspaceManager
            public void notifySmartspaceEvent(SmartspaceSessionId sessionId, SmartspaceTargetEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifySmartspaceEvent(sessionId, event);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.smartspace.ISmartspaceManager
            public void requestSmartspaceUpdate(SmartspaceSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestSmartspaceUpdate(sessionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.smartspace.ISmartspaceManager
            public void registerSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerSmartspaceUpdates(sessionId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.smartspace.ISmartspaceManager
            public void unregisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterSmartspaceUpdates(sessionId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.smartspace.ISmartspaceManager
            public void destroySmartspaceSession(SmartspaceSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroySmartspaceSession(sessionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartspaceManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISmartspaceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
