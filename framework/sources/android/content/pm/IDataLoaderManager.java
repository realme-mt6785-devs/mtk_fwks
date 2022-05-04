package android.content.pm;

import android.content.pm.IDataLoader;
import android.content.pm.IDataLoaderStatusListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDataLoaderManager extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IDataLoaderManager";

    boolean bindToDataLoader(int i, DataLoaderParamsParcel dataLoaderParamsParcel, long j, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException;

    IDataLoader getDataLoader(int i) throws RemoteException;

    void unbindFromDataLoader(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDataLoaderManager {
        @Override // android.content.pm.IDataLoaderManager
        public boolean bindToDataLoader(int id, DataLoaderParamsParcel params, long bindDelayMs, IDataLoaderStatusListener listener) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IDataLoaderManager
        public IDataLoader getDataLoader(int dataLoaderId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IDataLoaderManager
        public void unbindFromDataLoader(int dataLoaderId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDataLoaderManager {
        static final int TRANSACTION_bindToDataLoader = 1;
        static final int TRANSACTION_getDataLoader = 2;
        static final int TRANSACTION_unbindFromDataLoader = 3;

        public Stub() {
            attachInterface(this, IDataLoaderManager.DESCRIPTOR);
        }

        public static IDataLoaderManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDataLoaderManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataLoaderManager)) {
                return new Proxy(obj);
            }
            return (IDataLoaderManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "bindToDataLoader";
                case 2:
                    return "getDataLoader";
                case 3:
                    return "unbindFromDataLoader";
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
            DataLoaderParamsParcel _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDataLoaderManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDataLoaderManager.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = DataLoaderParamsParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            long _arg2 = data.readLong();
                            IDataLoaderStatusListener _arg3 = IDataLoaderStatusListener.Stub.asInterface(data.readStrongBinder());
                            boolean bindToDataLoader = bindToDataLoader(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(bindToDataLoader ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IDataLoaderManager.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            IDataLoader _result = getDataLoader(_arg02);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        case 3:
                            data.enforceInterface(IDataLoaderManager.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            unbindFromDataLoader(_arg03);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDataLoaderManager {
            public static IDataLoaderManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDataLoaderManager.DESCRIPTOR;
            }

            @Override // android.content.pm.IDataLoaderManager
            public boolean bindToDataLoader(int id, DataLoaderParamsParcel params, long bindDelayMs, IDataLoaderStatusListener listener) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoaderManager.DESCRIPTOR);
                    try {
                        _data.writeInt(id);
                        boolean _result = true;
                        if (params != null) {
                            _data.writeInt(1);
                            params.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeLong(bindDelayMs);
                            _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                            try {
                                boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() == 0) {
                                        _result = false;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                boolean bindToDataLoader = Stub.getDefaultImpl().bindToDataLoader(id, params, bindDelayMs, listener);
                                _reply.recycle();
                                _data.recycle();
                                return bindToDataLoader;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.content.pm.IDataLoaderManager
            public IDataLoader getDataLoader(int dataLoaderId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoaderManager.DESCRIPTOR);
                    _data.writeInt(dataLoaderId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataLoader(dataLoaderId);
                    }
                    _reply.readException();
                    IDataLoader _result = IDataLoader.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IDataLoaderManager
            public void unbindFromDataLoader(int dataLoaderId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoaderManager.DESCRIPTOR);
                    _data.writeInt(dataLoaderId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unbindFromDataLoader(dataLoaderId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataLoaderManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataLoaderManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
