package com.oplus.lockscreen;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusLockScreenCallback extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.lockscreen.IOplusLockScreenCallback";

    void showDialogForIntercpet(String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusLockScreenCallback {
        @Override // com.oplus.lockscreen.IOplusLockScreenCallback
        public void showDialogForIntercpet(String packageName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusLockScreenCallback {
        static final int TRANSACTION_showDialogForIntercpet = 1;

        public Stub() {
            attachInterface(this, IOplusLockScreenCallback.DESCRIPTOR);
        }

        public static IOplusLockScreenCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusLockScreenCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusLockScreenCallback)) {
                return new Proxy(obj);
            }
            return (IOplusLockScreenCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "showDialogForIntercpet";
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
                    reply.writeString(IOplusLockScreenCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusLockScreenCallback.DESCRIPTOR);
                            String _arg0 = data.readString();
                            showDialogForIntercpet(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusLockScreenCallback {
            public static IOplusLockScreenCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusLockScreenCallback.DESCRIPTOR;
            }

            @Override // com.oplus.lockscreen.IOplusLockScreenCallback
            public void showDialogForIntercpet(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusLockScreenCallback.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showDialogForIntercpet(packageName);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusLockScreenCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusLockScreenCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
