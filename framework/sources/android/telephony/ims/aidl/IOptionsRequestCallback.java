package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.RcsContactUceCapability;
/* loaded from: classes3.dex */
public interface IOptionsRequestCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IOptionsRequestCallback";

    void respondToCapabilityRequest(RcsContactUceCapability rcsContactUceCapability, boolean z) throws RemoteException;

    void respondToCapabilityRequestWithError(int i, String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IOptionsRequestCallback {
        @Override // android.telephony.ims.aidl.IOptionsRequestCallback
        public void respondToCapabilityRequest(RcsContactUceCapability ownCapabilities, boolean isBlocked) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IOptionsRequestCallback
        public void respondToCapabilityRequestWithError(int code, String reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IOptionsRequestCallback {
        static final int TRANSACTION_respondToCapabilityRequest = 1;
        static final int TRANSACTION_respondToCapabilityRequestWithError = 2;

        public Stub() {
            attachInterface(this, IOptionsRequestCallback.DESCRIPTOR);
        }

        public static IOptionsRequestCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOptionsRequestCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOptionsRequestCallback)) {
                return new Proxy(obj);
            }
            return (IOptionsRequestCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "respondToCapabilityRequest";
                case 2:
                    return "respondToCapabilityRequestWithError";
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
            RcsContactUceCapability _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOptionsRequestCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOptionsRequestCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RcsContactUceCapability.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean _arg1 = data.readInt() != 0;
                            respondToCapabilityRequest(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IOptionsRequestCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _arg12 = data.readString();
                            respondToCapabilityRequestWithError(_arg02, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IOptionsRequestCallback {
            public static IOptionsRequestCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOptionsRequestCallback.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IOptionsRequestCallback
            public void respondToCapabilityRequest(RcsContactUceCapability ownCapabilities, boolean isBlocked) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOptionsRequestCallback.DESCRIPTOR);
                    int i = 0;
                    if (ownCapabilities != null) {
                        _data.writeInt(1);
                        ownCapabilities.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (isBlocked) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().respondToCapabilityRequest(ownCapabilities, isBlocked);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IOptionsRequestCallback
            public void respondToCapabilityRequestWithError(int code, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOptionsRequestCallback.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().respondToCapabilityRequestWithError(code, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOptionsRequestCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOptionsRequestCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
