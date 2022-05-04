package com.android.internal.cabc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface ICabcManager extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.cabc.ICabcManager";

    void closeCabc() throws RemoteException;

    void closeCabcForever(boolean z) throws RemoteException;

    int getMode() throws RemoteException;

    void openCabc() throws RemoteException;

    void setMode(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ICabcManager {
        @Override // com.android.internal.cabc.ICabcManager
        public void setMode(int mode) throws RemoteException {
        }

        @Override // com.android.internal.cabc.ICabcManager
        public int getMode() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.cabc.ICabcManager
        public void closeCabc() throws RemoteException {
        }

        @Override // com.android.internal.cabc.ICabcManager
        public void openCabc() throws RemoteException {
        }

        @Override // com.android.internal.cabc.ICabcManager
        public void closeCabcForever(boolean enable) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ICabcManager {
        static final int TRANSACTION_closeCabc = 3;
        static final int TRANSACTION_closeCabcForever = 5;
        static final int TRANSACTION_getMode = 2;
        static final int TRANSACTION_openCabc = 4;
        static final int TRANSACTION_setMode = 1;

        public Stub() {
            attachInterface(this, ICabcManager.DESCRIPTOR);
        }

        public static ICabcManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICabcManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICabcManager)) {
                return new Proxy(obj);
            }
            return (ICabcManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setMode";
                case 2:
                    return "getMode";
                case 3:
                    return "closeCabc";
                case 4:
                    return "openCabc";
                case 5:
                    return "closeCabcForever";
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
                    reply.writeString(ICabcManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICabcManager.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            setMode(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ICabcManager.DESCRIPTOR);
                            int _result = getMode();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 3:
                            data.enforceInterface(ICabcManager.DESCRIPTOR);
                            closeCabc();
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ICabcManager.DESCRIPTOR);
                            openCabc();
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ICabcManager.DESCRIPTOR);
                            boolean _arg02 = data.readInt() != 0;
                            closeCabcForever(_arg02);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ICabcManager {
            public static ICabcManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICabcManager.DESCRIPTOR;
            }

            @Override // com.android.internal.cabc.ICabcManager
            public void setMode(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICabcManager.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setMode(mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.cabc.ICabcManager
            public int getMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICabcManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.cabc.ICabcManager
            public void closeCabc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICabcManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeCabc();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.cabc.ICabcManager
            public void openCabc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICabcManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().openCabc();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.cabc.ICabcManager
            public void closeCabcForever(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICabcManager.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeCabcForever(enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICabcManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICabcManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
