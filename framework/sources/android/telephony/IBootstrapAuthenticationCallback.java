package android.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IBootstrapAuthenticationCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.IBootstrapAuthenticationCallback";

    void onAuthenticationFailure(int i, int i2) throws RemoteException;

    void onKeysAvailable(int i, byte[] bArr, String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IBootstrapAuthenticationCallback {
        @Override // android.telephony.IBootstrapAuthenticationCallback
        public void onKeysAvailable(int token, byte[] gbaKey, String btId) throws RemoteException {
        }

        @Override // android.telephony.IBootstrapAuthenticationCallback
        public void onAuthenticationFailure(int token, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IBootstrapAuthenticationCallback {
        static final int TRANSACTION_onAuthenticationFailure = 2;
        static final int TRANSACTION_onKeysAvailable = 1;

        public Stub() {
            attachInterface(this, IBootstrapAuthenticationCallback.DESCRIPTOR);
        }

        public static IBootstrapAuthenticationCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBootstrapAuthenticationCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBootstrapAuthenticationCallback)) {
                return new Proxy(obj);
            }
            return (IBootstrapAuthenticationCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onKeysAvailable";
                case 2:
                    return "onAuthenticationFailure";
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
                    reply.writeString(IBootstrapAuthenticationCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBootstrapAuthenticationCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            String _arg2 = data.readString();
                            onKeysAvailable(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IBootstrapAuthenticationCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg12 = data.readInt();
                            onAuthenticationFailure(_arg02, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IBootstrapAuthenticationCallback {
            public static IBootstrapAuthenticationCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBootstrapAuthenticationCallback.DESCRIPTOR;
            }

            @Override // android.telephony.IBootstrapAuthenticationCallback
            public void onKeysAvailable(int token, byte[] gbaKey, String btId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBootstrapAuthenticationCallback.DESCRIPTOR);
                    _data.writeInt(token);
                    _data.writeByteArray(gbaKey);
                    _data.writeString(btId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeysAvailable(token, gbaKey, btId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.IBootstrapAuthenticationCallback
            public void onAuthenticationFailure(int token, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBootstrapAuthenticationCallback.DESCRIPTOR);
                    _data.writeInt(token);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthenticationFailure(token, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBootstrapAuthenticationCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBootstrapAuthenticationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
