package com.android.internal.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IDragAndDropPermissions extends IInterface {
    void release() throws RemoteException;

    void take(IBinder iBinder) throws RemoteException;

    void takeTransient() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IDragAndDropPermissions {
        @Override // com.android.internal.view.IDragAndDropPermissions
        public void take(IBinder activityToken) throws RemoteException {
        }

        @Override // com.android.internal.view.IDragAndDropPermissions
        public void takeTransient() throws RemoteException {
        }

        @Override // com.android.internal.view.IDragAndDropPermissions
        public void release() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IDragAndDropPermissions {
        public static final String DESCRIPTOR = "com.android.internal.view.IDragAndDropPermissions";
        static final int TRANSACTION_release = 3;
        static final int TRANSACTION_take = 1;
        static final int TRANSACTION_takeTransient = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDragAndDropPermissions asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDragAndDropPermissions)) {
                return new Proxy(obj);
            }
            return (IDragAndDropPermissions) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "take";
                case 2:
                    return "takeTransient";
                case 3:
                    return "release";
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
                            IBinder _arg0 = data.readStrongBinder();
                            take(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            takeTransient();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            release();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IDragAndDropPermissions {
            public static IDragAndDropPermissions sDefaultImpl;
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

            @Override // com.android.internal.view.IDragAndDropPermissions
            public void take(IBinder activityToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(activityToken);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().take(activityToken);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IDragAndDropPermissions
            public void takeTransient() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().takeTransient();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IDragAndDropPermissions
            public void release() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().release();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDragAndDropPermissions impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDragAndDropPermissions getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
