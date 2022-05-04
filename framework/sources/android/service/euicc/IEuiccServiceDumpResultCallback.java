package android.service.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IEuiccServiceDumpResultCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.euicc.IEuiccServiceDumpResultCallback";

    void onComplete(String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IEuiccServiceDumpResultCallback {
        @Override // android.service.euicc.IEuiccServiceDumpResultCallback
        public void onComplete(String logs) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IEuiccServiceDumpResultCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, IEuiccServiceDumpResultCallback.DESCRIPTOR);
        }

        public static IEuiccServiceDumpResultCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEuiccServiceDumpResultCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEuiccServiceDumpResultCallback)) {
                return new Proxy(obj);
            }
            return (IEuiccServiceDumpResultCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onComplete";
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
                    reply.writeString(IEuiccServiceDumpResultCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEuiccServiceDumpResultCallback.DESCRIPTOR);
                            String _arg0 = data.readString();
                            onComplete(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IEuiccServiceDumpResultCallback {
            public static IEuiccServiceDumpResultCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEuiccServiceDumpResultCallback.DESCRIPTOR;
            }

            @Override // android.service.euicc.IEuiccServiceDumpResultCallback
            public void onComplete(String logs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccServiceDumpResultCallback.DESCRIPTOR);
                    _data.writeString(logs);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(logs);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEuiccServiceDumpResultCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEuiccServiceDumpResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
