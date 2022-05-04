package android.service.smartspace;

import android.app.smartspace.ISmartspaceCallback;
import android.app.smartspace.SmartspaceConfig;
import android.app.smartspace.SmartspaceSessionId;
import android.app.smartspace.SmartspaceTargetEvent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ISmartspaceService extends IInterface {
    public static final String DESCRIPTOR = "android.service.smartspace.ISmartspaceService";

    void notifySmartspaceEvent(SmartspaceSessionId smartspaceSessionId, SmartspaceTargetEvent smartspaceTargetEvent) throws RemoteException;

    void onCreateSmartspaceSession(SmartspaceConfig smartspaceConfig, SmartspaceSessionId smartspaceSessionId) throws RemoteException;

    void onDestroySmartspaceSession(SmartspaceSessionId smartspaceSessionId) throws RemoteException;

    void registerSmartspaceUpdates(SmartspaceSessionId smartspaceSessionId, ISmartspaceCallback iSmartspaceCallback) throws RemoteException;

    void requestSmartspaceUpdate(SmartspaceSessionId smartspaceSessionId) throws RemoteException;

    void unregisterSmartspaceUpdates(SmartspaceSessionId smartspaceSessionId, ISmartspaceCallback iSmartspaceCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISmartspaceService {
        @Override // android.service.smartspace.ISmartspaceService
        public void onCreateSmartspaceSession(SmartspaceConfig context, SmartspaceSessionId sessionId) throws RemoteException {
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void notifySmartspaceEvent(SmartspaceSessionId sessionId, SmartspaceTargetEvent event) throws RemoteException {
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void requestSmartspaceUpdate(SmartspaceSessionId sessionId) throws RemoteException {
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void registerSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void unregisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void onDestroySmartspaceSession(SmartspaceSessionId sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISmartspaceService {
        static final int TRANSACTION_notifySmartspaceEvent = 2;
        static final int TRANSACTION_onCreateSmartspaceSession = 1;
        static final int TRANSACTION_onDestroySmartspaceSession = 6;
        static final int TRANSACTION_registerSmartspaceUpdates = 4;
        static final int TRANSACTION_requestSmartspaceUpdate = 3;
        static final int TRANSACTION_unregisterSmartspaceUpdates = 5;

        public Stub() {
            attachInterface(this, ISmartspaceService.DESCRIPTOR);
        }

        public static ISmartspaceService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISmartspaceService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISmartspaceService)) {
                return new Proxy(obj);
            }
            return (ISmartspaceService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCreateSmartspaceSession";
                case 2:
                    return "notifySmartspaceEvent";
                case 3:
                    return "requestSmartspaceUpdate";
                case 4:
                    return "registerSmartspaceUpdates";
                case 5:
                    return "unregisterSmartspaceUpdates";
                case 6:
                    return "onDestroySmartspaceSession";
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
                    reply.writeString(ISmartspaceService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISmartspaceService.DESCRIPTOR);
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
                            onCreateSmartspaceSession(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(ISmartspaceService.DESCRIPTOR);
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
                            return true;
                        case 3:
                            data.enforceInterface(ISmartspaceService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            requestSmartspaceUpdate(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(ISmartspaceService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            ISmartspaceCallback _arg13 = ISmartspaceCallback.Stub.asInterface(data.readStrongBinder());
                            registerSmartspaceUpdates(_arg04, _arg13);
                            return true;
                        case 5:
                            data.enforceInterface(ISmartspaceService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            ISmartspaceCallback _arg14 = ISmartspaceCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterSmartspaceUpdates(_arg05, _arg14);
                            return true;
                        case 6:
                            data.enforceInterface(ISmartspaceService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = SmartspaceSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            onDestroySmartspaceSession(_arg06);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISmartspaceService {
            public static ISmartspaceService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISmartspaceService.DESCRIPTOR;
            }

            @Override // android.service.smartspace.ISmartspaceService
            public void onCreateSmartspaceSession(SmartspaceConfig context, SmartspaceSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceService.DESCRIPTOR);
                    if (context != null) {
                        _data.writeInt(1);
                        context.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCreateSmartspaceSession(context, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.smartspace.ISmartspaceService
            public void notifySmartspaceEvent(SmartspaceSessionId sessionId, SmartspaceTargetEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceService.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySmartspaceEvent(sessionId, event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.smartspace.ISmartspaceService
            public void requestSmartspaceUpdate(SmartspaceSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceService.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSmartspaceUpdate(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.smartspace.ISmartspaceService
            public void registerSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceService.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSmartspaceUpdates(sessionId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.smartspace.ISmartspaceService
            public void unregisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceService.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSmartspaceUpdates(sessionId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.smartspace.ISmartspaceService
            public void onDestroySmartspaceSession(SmartspaceSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISmartspaceService.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDestroySmartspaceSession(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartspaceService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISmartspaceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
