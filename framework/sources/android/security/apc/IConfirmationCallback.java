package android.security.apc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IConfirmationCallback extends IInterface {
    public static final String DESCRIPTOR = "android$security$apc$IConfirmationCallback".replace('$', '.');

    void onCompleted(int i, byte[] bArr) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IConfirmationCallback {
        @Override // android.security.apc.IConfirmationCallback
        public void onCompleted(int result, byte[] dataConfirmed) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IConfirmationCallback {
        static final int TRANSACTION_onCompleted = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConfirmationCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IConfirmationCallback)) {
                return new Proxy(obj);
            }
            return (IConfirmationCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            onCompleted(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IConfirmationCallback {
            public static IConfirmationCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.security.apc.IConfirmationCallback
            public void onCompleted(int result, byte[] dataConfirmed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(result);
                    _data.writeByteArray(dataConfirmed);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCompleted(result, dataConfirmed);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConfirmationCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IConfirmationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
