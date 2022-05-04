package android.service.search;

import android.app.search.ISearchCallback;
import android.app.search.Query;
import android.app.search.SearchContext;
import android.app.search.SearchSessionId;
import android.app.search.SearchTargetEvent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ISearchUiService extends IInterface {
    public static final String DESCRIPTOR = "android.service.search.ISearchUiService";

    void onCreateSearchSession(SearchContext searchContext, SearchSessionId searchSessionId) throws RemoteException;

    void onDestroy(SearchSessionId searchSessionId) throws RemoteException;

    void onNotifyEvent(SearchSessionId searchSessionId, Query query, SearchTargetEvent searchTargetEvent) throws RemoteException;

    void onQuery(SearchSessionId searchSessionId, Query query, ISearchCallback iSearchCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISearchUiService {
        @Override // android.service.search.ISearchUiService
        public void onCreateSearchSession(SearchContext context, SearchSessionId sessionId) throws RemoteException {
        }

        @Override // android.service.search.ISearchUiService
        public void onQuery(SearchSessionId sessionId, Query input, ISearchCallback callback) throws RemoteException {
        }

        @Override // android.service.search.ISearchUiService
        public void onNotifyEvent(SearchSessionId sessionId, Query input, SearchTargetEvent event) throws RemoteException {
        }

        @Override // android.service.search.ISearchUiService
        public void onDestroy(SearchSessionId sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISearchUiService {
        static final int TRANSACTION_onCreateSearchSession = 1;
        static final int TRANSACTION_onDestroy = 4;
        static final int TRANSACTION_onNotifyEvent = 3;
        static final int TRANSACTION_onQuery = 2;

        public Stub() {
            attachInterface(this, ISearchUiService.DESCRIPTOR);
        }

        public static ISearchUiService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISearchUiService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISearchUiService)) {
                return new Proxy(obj);
            }
            return (ISearchUiService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCreateSearchSession";
                case 2:
                    return "onQuery";
                case 3:
                    return "onNotifyEvent";
                case 4:
                    return "onDestroy";
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
                    reply.writeString(ISearchUiService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISearchUiService.DESCRIPTOR);
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
                            onCreateSearchSession(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(ISearchUiService.DESCRIPTOR);
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
                            ISearchCallback _arg22 = ISearchCallback.Stub.asInterface(data.readStrongBinder());
                            onQuery(_arg02, _arg12, _arg22);
                            return true;
                        case 3:
                            data.enforceInterface(ISearchUiService.DESCRIPTOR);
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
                            onNotifyEvent(_arg03, _arg13, _arg2);
                            return true;
                        case 4:
                            data.enforceInterface(ISearchUiService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = SearchSessionId.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            onDestroy(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISearchUiService {
            public static ISearchUiService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISearchUiService.DESCRIPTOR;
            }

            @Override // android.service.search.ISearchUiService
            public void onCreateSearchSession(SearchContext context, SearchSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiService.DESCRIPTOR);
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
                        Stub.getDefaultImpl().onCreateSearchSession(context, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.search.ISearchUiService
            public void onQuery(SearchSessionId sessionId, Query input, ISearchCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiService.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQuery(sessionId, input, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.search.ISearchUiService
            public void onNotifyEvent(SearchSessionId sessionId, Query input, SearchTargetEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiService.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyEvent(sessionId, input, event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.search.ISearchUiService
            public void onDestroy(SearchSessionId sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchUiService.DESCRIPTOR);
                    if (sessionId != null) {
                        _data.writeInt(1);
                        sessionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDestroy(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchUiService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISearchUiService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
