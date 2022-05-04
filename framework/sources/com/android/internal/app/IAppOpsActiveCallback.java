package com.android.internal.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IAppOpsActiveCallback extends IInterface {
    void opActiveChanged(int i, int i2, String str, String str2, boolean z, int i3, int i4) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IAppOpsActiveCallback {
        @Override // com.android.internal.app.IAppOpsActiveCallback
        public void opActiveChanged(int op, int uid, String packageName, String attributionTag, boolean active, int attributionFlags, int attributionChainId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IAppOpsActiveCallback {
        public static final String DESCRIPTOR = "com.android.internal.app.IAppOpsActiveCallback";
        static final int TRANSACTION_opActiveChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppOpsActiveCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppOpsActiveCallback)) {
                return new Proxy(obj);
            }
            return (IAppOpsActiveCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "opActiveChanged";
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
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            String _arg2 = data.readString();
                            String _arg3 = data.readString();
                            boolean _arg4 = data.readInt() != 0;
                            int _arg5 = data.readInt();
                            int _arg6 = data.readInt();
                            opActiveChanged(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IAppOpsActiveCallback {
            public static IAppOpsActiveCallback sDefaultImpl;
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

            @Override // com.android.internal.app.IAppOpsActiveCallback
            public void opActiveChanged(int op, int uid, String packageName, String attributionTag, boolean active, int attributionFlags, int attributionChainId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(op);
                        try {
                            _data.writeInt(uid);
                            try {
                                _data.writeString(packageName);
                                try {
                                    _data.writeString(attributionTag);
                                    _data.writeInt(active ? 1 : 0);
                                    try {
                                        _data.writeInt(attributionFlags);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        _data.recycle();
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
                try {
                    _data.writeInt(attributionChainId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().opActiveChanged(op, uid, packageName, attributionTag, active, attributionFlags, attributionChainId);
                    _data.recycle();
                } catch (Throwable th8) {
                    th = th8;
                    _data.recycle();
                    throw th;
                }
            }
        }

        public static boolean setDefaultImpl(IAppOpsActiveCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAppOpsActiveCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
