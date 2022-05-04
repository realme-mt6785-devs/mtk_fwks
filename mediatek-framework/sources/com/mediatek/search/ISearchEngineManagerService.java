package com.mediatek.search;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.common.search.SearchEngine;
import java.util.List;
/* loaded from: classes.dex */
public interface ISearchEngineManagerService extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.search.ISearchEngineManagerService";

    List<SearchEngine> getAvailables() throws RemoteException;

    SearchEngine getBestMatch(String str, String str2) throws RemoteException;

    SearchEngine getDefault() throws RemoteException;

    SearchEngine getSearchEngine(int i, String str) throws RemoteException;

    boolean setDefault(SearchEngine searchEngine) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISearchEngineManagerService {
        @Override // com.mediatek.search.ISearchEngineManagerService
        public List<SearchEngine> getAvailables() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.search.ISearchEngineManagerService
        public SearchEngine getDefault() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.search.ISearchEngineManagerService
        public SearchEngine getBestMatch(String name, String favicon) throws RemoteException {
            return null;
        }

        @Override // com.mediatek.search.ISearchEngineManagerService
        public SearchEngine getSearchEngine(int field, String value) throws RemoteException {
            return null;
        }

        @Override // com.mediatek.search.ISearchEngineManagerService
        public boolean setDefault(SearchEngine engine) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISearchEngineManagerService {
        static final int TRANSACTION_getAvailables = 1;
        static final int TRANSACTION_getBestMatch = 3;
        static final int TRANSACTION_getDefault = 2;
        static final int TRANSACTION_getSearchEngine = 4;
        static final int TRANSACTION_setDefault = 5;

        public Stub() {
            attachInterface(this, ISearchEngineManagerService.DESCRIPTOR);
        }

        public static ISearchEngineManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISearchEngineManagerService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISearchEngineManagerService)) {
                return new Proxy(obj);
            }
            return (ISearchEngineManagerService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            SearchEngine _arg0;
            switch (code) {
                case 1598968902:
                    reply.writeString(ISearchEngineManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISearchEngineManagerService.DESCRIPTOR);
                            List<SearchEngine> _result = getAvailables();
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(ISearchEngineManagerService.DESCRIPTOR);
                            SearchEngine _result2 = getDefault();
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(ISearchEngineManagerService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            String _arg1 = data.readString();
                            SearchEngine _result3 = getBestMatch(_arg02, _arg1);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(ISearchEngineManagerService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            String _arg12 = data.readString();
                            SearchEngine _result4 = getSearchEngine(_arg03, _arg12);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(ISearchEngineManagerService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = (SearchEngine) SearchEngine.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean z = setDefault(_arg0);
                            reply.writeNoException();
                            reply.writeInt(z ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISearchEngineManagerService {
            public static ISearchEngineManagerService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISearchEngineManagerService.DESCRIPTOR;
            }

            @Override // com.mediatek.search.ISearchEngineManagerService
            public List<SearchEngine> getAvailables() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchEngineManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailables();
                    }
                    _reply.readException();
                    List<SearchEngine> _result = _reply.createTypedArrayList(SearchEngine.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.search.ISearchEngineManagerService
            public SearchEngine getDefault() throws RemoteException {
                SearchEngine _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchEngineManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefault();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (SearchEngine) SearchEngine.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.search.ISearchEngineManagerService
            public SearchEngine getBestMatch(String name, String favicon) throws RemoteException {
                SearchEngine _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchEngineManagerService.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(favicon);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBestMatch(name, favicon);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (SearchEngine) SearchEngine.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.search.ISearchEngineManagerService
            public SearchEngine getSearchEngine(int field, String value) throws RemoteException {
                SearchEngine _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchEngineManagerService.DESCRIPTOR);
                    _data.writeInt(field);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSearchEngine(field, value);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (SearchEngine) SearchEngine.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.search.ISearchEngineManagerService
            public boolean setDefault(SearchEngine engine) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISearchEngineManagerService.DESCRIPTOR);
                    boolean _result = true;
                    if (engine != null) {
                        _data.writeInt(1);
                        engine.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefault(engine);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchEngineManagerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISearchEngineManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
