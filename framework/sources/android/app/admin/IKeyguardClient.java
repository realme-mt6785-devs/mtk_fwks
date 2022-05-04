package android.app.admin;

import android.app.admin.IKeyguardCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IKeyguardClient extends IInterface {
    public static final String DESCRIPTOR = "android.app.admin.IKeyguardClient";

    void onCreateKeyguardSurface(IBinder iBinder, IKeyguardCallback iKeyguardCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IKeyguardClient {
        @Override // android.app.admin.IKeyguardClient
        public void onCreateKeyguardSurface(IBinder hostInputToken, IKeyguardCallback keyguardCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IKeyguardClient {
        static final int TRANSACTION_onCreateKeyguardSurface = 1;

        public Stub() {
            attachInterface(this, IKeyguardClient.DESCRIPTOR);
        }

        public static IKeyguardClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IKeyguardClient.DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeyguardClient)) {
                return new Proxy(obj);
            }
            return (IKeyguardClient) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCreateKeyguardSurface";
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
                    reply.writeString(IKeyguardClient.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IKeyguardClient.DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            IKeyguardCallback _arg1 = IKeyguardCallback.Stub.asInterface(data.readStrongBinder());
                            onCreateKeyguardSurface(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IKeyguardClient {
            public static IKeyguardClient sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IKeyguardClient.DESCRIPTOR;
            }

            @Override // android.app.admin.IKeyguardClient
            public void onCreateKeyguardSurface(IBinder hostInputToken, IKeyguardCallback keyguardCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IKeyguardClient.DESCRIPTOR);
                    _data.writeStrongBinder(hostInputToken);
                    _data.writeStrongBinder(keyguardCallback != null ? keyguardCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCreateKeyguardSurface(hostInputToken, keyguardCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyguardClient impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeyguardClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
