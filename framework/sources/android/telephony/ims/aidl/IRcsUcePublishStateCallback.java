package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IRcsUcePublishStateCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IRcsUcePublishStateCallback";

    void onPublishStateChanged(int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRcsUcePublishStateCallback {
        @Override // android.telephony.ims.aidl.IRcsUcePublishStateCallback
        public void onPublishStateChanged(int publishState) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRcsUcePublishStateCallback {
        static final int TRANSACTION_onPublishStateChanged = 1;

        public Stub() {
            attachInterface(this, IRcsUcePublishStateCallback.DESCRIPTOR);
        }

        public static IRcsUcePublishStateCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRcsUcePublishStateCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRcsUcePublishStateCallback)) {
                return new Proxy(obj);
            }
            return (IRcsUcePublishStateCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onPublishStateChanged";
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
                    reply.writeString(IRcsUcePublishStateCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRcsUcePublishStateCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onPublishStateChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRcsUcePublishStateCallback {
            public static IRcsUcePublishStateCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRcsUcePublishStateCallback.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IRcsUcePublishStateCallback
            public void onPublishStateChanged(int publishState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRcsUcePublishStateCallback.DESCRIPTOR);
                    _data.writeInt(publishState);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPublishStateChanged(publishState);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRcsUcePublishStateCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRcsUcePublishStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
