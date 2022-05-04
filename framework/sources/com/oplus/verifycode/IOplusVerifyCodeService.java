package com.oplus.verifycode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusVerifyCodeService extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.verifycode.IOplusVerifyCodeService";

    void setVerifyCode(String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusVerifyCodeService {
        @Override // com.oplus.verifycode.IOplusVerifyCodeService
        public void setVerifyCode(String code) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusVerifyCodeService {
        static final int TRANSACTION_setVerifyCode = 1;

        public Stub() {
            attachInterface(this, IOplusVerifyCodeService.DESCRIPTOR);
        }

        public static IOplusVerifyCodeService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusVerifyCodeService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusVerifyCodeService)) {
                return new Proxy(obj);
            }
            return (IOplusVerifyCodeService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setVerifyCode";
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
                    reply.writeString(IOplusVerifyCodeService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusVerifyCodeService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            setVerifyCode(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusVerifyCodeService {
            public static IOplusVerifyCodeService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusVerifyCodeService.DESCRIPTOR;
            }

            @Override // com.oplus.verifycode.IOplusVerifyCodeService
            public void setVerifyCode(String code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusVerifyCodeService.DESCRIPTOR);
                    _data.writeString(code);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVerifyCode(code);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusVerifyCodeService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusVerifyCodeService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
