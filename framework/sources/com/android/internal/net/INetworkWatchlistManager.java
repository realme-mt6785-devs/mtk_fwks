package com.android.internal.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface INetworkWatchlistManager extends IInterface {
    byte[] getWatchlistConfigHash() throws RemoteException;

    void reloadWatchlist() throws RemoteException;

    void reportWatchlistIfNecessary() throws RemoteException;

    boolean startWatchlistLogging() throws RemoteException;

    boolean stopWatchlistLogging() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements INetworkWatchlistManager {
        @Override // com.android.internal.net.INetworkWatchlistManager
        public boolean startWatchlistLogging() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.net.INetworkWatchlistManager
        public boolean stopWatchlistLogging() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.net.INetworkWatchlistManager
        public void reloadWatchlist() throws RemoteException {
        }

        @Override // com.android.internal.net.INetworkWatchlistManager
        public void reportWatchlistIfNecessary() throws RemoteException {
        }

        @Override // com.android.internal.net.INetworkWatchlistManager
        public byte[] getWatchlistConfigHash() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements INetworkWatchlistManager {
        public static final String DESCRIPTOR = "com.android.internal.net.INetworkWatchlistManager";
        static final int TRANSACTION_getWatchlistConfigHash = 5;
        static final int TRANSACTION_reloadWatchlist = 3;
        static final int TRANSACTION_reportWatchlistIfNecessary = 4;
        static final int TRANSACTION_startWatchlistLogging = 1;
        static final int TRANSACTION_stopWatchlistLogging = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkWatchlistManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INetworkWatchlistManager)) {
                return new Proxy(obj);
            }
            return (INetworkWatchlistManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startWatchlistLogging";
                case 2:
                    return "stopWatchlistLogging";
                case 3:
                    return "reloadWatchlist";
                case 4:
                    return "reportWatchlistIfNecessary";
                case 5:
                    return "getWatchlistConfigHash";
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
                            boolean startWatchlistLogging = startWatchlistLogging();
                            reply.writeNoException();
                            reply.writeInt(startWatchlistLogging ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            boolean stopWatchlistLogging = stopWatchlistLogging();
                            reply.writeNoException();
                            reply.writeInt(stopWatchlistLogging ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            reloadWatchlist();
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            reportWatchlistIfNecessary();
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _result = getWatchlistConfigHash();
                            reply.writeNoException();
                            reply.writeByteArray(_result);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements INetworkWatchlistManager {
            public static INetworkWatchlistManager sDefaultImpl;
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

            @Override // com.android.internal.net.INetworkWatchlistManager
            public boolean startWatchlistLogging() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startWatchlistLogging();
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

            @Override // com.android.internal.net.INetworkWatchlistManager
            public boolean stopWatchlistLogging() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopWatchlistLogging();
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

            @Override // com.android.internal.net.INetworkWatchlistManager
            public void reloadWatchlist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reloadWatchlist();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.net.INetworkWatchlistManager
            public void reportWatchlistIfNecessary() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportWatchlistIfNecessary();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.net.INetworkWatchlistManager
            public byte[] getWatchlistConfigHash() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWatchlistConfigHash();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkWatchlistManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INetworkWatchlistManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
