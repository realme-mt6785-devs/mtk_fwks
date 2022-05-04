package android.app.search;

import android.app.search.ISearchCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISearchUiManager extends IInterface {
    public static final String DESCRIPTOR = "android.app.search.ISearchUiManager";

    void createSearchSession(SearchContext searchContext, SearchSessionId searchSessionId, IBinder iBinder) throws RemoteException;

    void destroySearchSession(SearchSessionId searchSessionId) throws RemoteException;

    void notifyEvent(SearchSessionId searchSessionId, Query query, SearchTargetEvent searchTargetEvent) throws RemoteException;

    void query(SearchSessionId searchSessionId, Query query, ISearchCallback iSearchCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISearchUiManager {
        @Override // android.app.search.ISearchUiManager
        public void createSearchSession(SearchContext context, SearchSessionId sessionId, IBinder token) throws RemoteException {
        }

        @Override // android.app.search.ISearchUiManager
        public void query(SearchSessionId sessionId, Query input, ISearchCallback callback) throws RemoteException {
        }

        @Override // android.app.search.ISearchUiManager
        public void notifyEvent(SearchSessionId sessionId, Query input, SearchTargetEvent event) throws RemoteException {
        }

        @Override // android.app.search.ISearchUiManager
        public void destroySearchSession(SearchSessionId sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISearchUiManager {
        static final int TRANSACTION_createSearchSession = 1;
        static final int TRANSACTION_destroySearchSession = 4;
        static final int TRANSACTION_notifyEvent = 3;
        static final int TRANSACTION_query = 2;

        public Stub() {
            attachInterface(this, ISearchUiManager.DESCRIPTOR);
        }

        public static ISearchUiManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISearchUiManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISearchUiManager)) {
                return new Proxy(obj);
            }
            return (ISearchUiManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSearchSession";
                case 2:
                    return "query";
                case 3:
                    return "notifyEvent";
                case 4:
                    return "destroySearchSession";
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
            SearchContext _arg0;
            SearchSessionId _arg1;
            SearchSessionId _arg02;
            Query _arg12;
            SearchSessionId _arg03;
            Query _arg13;
            SearchTargetEvent _arg2;
            SearchSessionId _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISearchUiManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISearchUiManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SearchContext.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = SearchSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IBinder _arg22 = data.readStrongBinder();
                            createSearchSession(_arg0, _arg1, _arg22);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ISearchUiManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SearchSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = Query.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            ISearchCallback _arg23 = ISearchCallback.Stub.asInterface(data.readStrongBinder());
                            query(_arg02, _arg12, _arg23);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ISearchUiManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = SearchSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = Query.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = SearchTargetEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            notifyEvent(_arg03, _arg13, _arg2);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ISearchUiManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = SearchSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            destroySearchSession(_arg04);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISearchUiManager {
            public static ISearchUiManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISearchUiManager.DESCRIPTOR;
            }

            @Override // android.app.search.ISearchUiManager
            public void createSearchSession(SearchContext context, SearchSessionId sessionId, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiManager.DESCRIPTOR);
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
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createSearchSession(context, sessionId, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.search.ISearchUiManager
            public void query(SearchSessionId sessionId, Query input, ISearchCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (input != null) {
                        _data.writeInt(1);
                        input.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().query(sessionId, input, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.search.ISearchUiManager
            public void notifyEvent(SearchSessionId sessionId, Query input, SearchTargetEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (input != null) {
                        _data.writeInt(1);
                        input.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyEvent(sessionId, input, event);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.search.ISearchUiManager
            public void destroySearchSession(SearchSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiManager.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroySearchSession(sessionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchUiManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISearchUiManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
