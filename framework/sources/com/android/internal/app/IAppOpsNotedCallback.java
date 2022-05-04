package com.android.internal.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IAppOpsNotedCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.app.IAppOpsNotedCallback";

    void opNoted(int i, int i2, String str, String str2, int i3, int i4) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IAppOpsNotedCallback {
        @Override // com.android.internal.app.IAppOpsNotedCallback
        public void opNoted(int op, int uid, String packageName, String attributionTag, int flags, int mode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IAppOpsNotedCallback {
        static final int TRANSACTION_opNoted = 1;

        public Stub() {
            attachInterface(this, IAppOpsNotedCallback.DESCRIPTOR);
        }

        public static IAppOpsNotedCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAppOpsNotedCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppOpsNotedCallback)) {
                return new Proxy(obj);
            }
            return (IAppOpsNotedCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "opNoted";
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
                    reply.writeString(IAppOpsNotedCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAppOpsNotedCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            String _arg2 = data.readString();
                            String _arg3 = data.readString();
                            int _arg4 = data.readInt();
                            int _arg5 = data.readInt();
                            opNoted(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IAppOpsNotedCallback {
            public static IAppOpsNotedCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppOpsNotedCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.app.IAppOpsNotedCallback
            public void opNoted(int op, int uid, String packageName, String attributionTag, int flags, int mode) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppOpsNotedCallback.DESCRIPTOR);
                    try {
                        _data.writeInt(op);
                        try {
                            _data.writeInt(uid);
                            try {
                                _data.writeString(packageName);
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
                    try {
                        _data.writeString(attributionTag);
                        try {
                            _data.writeInt(flags);
                            try {
                                _data.writeInt(mode);
                                try {
                                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().opNoted(op, uid, packageName, attributionTag, flags, mode);
                                    _data.recycle();
                                } catch (Throwable th5) {
                                    th = th5;
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th9) {
                    th = th9;
                }
            }
        }

        public static boolean setDefaultImpl(IAppOpsNotedCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAppOpsNotedCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
