package android.net.vcn;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IVcnStatusCallback extends IInterface {
    public static final String DESCRIPTOR = "android.net.vcn.IVcnStatusCallback";

    void onGatewayConnectionError(String str, int i, String str2, String str3) throws RemoteException;

    void onVcnStatusChanged(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVcnStatusCallback {
        @Override // android.net.vcn.IVcnStatusCallback
        public void onVcnStatusChanged(int statusCode) throws RemoteException {
        }

        @Override // android.net.vcn.IVcnStatusCallback
        public void onGatewayConnectionError(String gatewayConnectionName, int errorCode, String exceptionClass, String exceptionMessage) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVcnStatusCallback {
        static final int TRANSACTION_onGatewayConnectionError = 2;
        static final int TRANSACTION_onVcnStatusChanged = 1;

        public Stub() {
            attachInterface(this, IVcnStatusCallback.DESCRIPTOR);
        }

        public static IVcnStatusCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVcnStatusCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVcnStatusCallback)) {
                return new Proxy(obj);
            }
            return (IVcnStatusCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onVcnStatusChanged";
                case 2:
                    return "onGatewayConnectionError";
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
                    reply.writeString(IVcnStatusCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVcnStatusCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onVcnStatusChanged(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IVcnStatusCallback.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg1 = data.readInt();
                            String _arg2 = data.readString();
                            String _arg3 = data.readString();
                            onGatewayConnectionError(_arg02, _arg1, _arg2, _arg3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVcnStatusCallback {
            public static IVcnStatusCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVcnStatusCallback.DESCRIPTOR;
            }

            @Override // android.net.vcn.IVcnStatusCallback
            public void onVcnStatusChanged(int statusCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnStatusCallback.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVcnStatusChanged(statusCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnStatusCallback
            public void onGatewayConnectionError(String gatewayConnectionName, int errorCode, String exceptionClass, String exceptionMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnStatusCallback.DESCRIPTOR);
                    _data.writeString(gatewayConnectionName);
                    _data.writeInt(errorCode);
                    _data.writeString(exceptionClass);
                    _data.writeString(exceptionMessage);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGatewayConnectionError(gatewayConnectionName, errorCode, exceptionClass, exceptionMessage);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVcnStatusCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVcnStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
