package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.SipMessage;
/* loaded from: classes3.dex */
public interface ISipDelegateMessageCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.ISipDelegateMessageCallback";

    void onMessageReceived(SipMessage sipMessage) throws RemoteException;

    void onMessageSendFailure(String str, int i) throws RemoteException;

    void onMessageSent(String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISipDelegateMessageCallback {
        @Override // android.telephony.ims.aidl.ISipDelegateMessageCallback
        public void onMessageReceived(SipMessage message) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegateMessageCallback
        public void onMessageSent(String viaTransactionId) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegateMessageCallback
        public void onMessageSendFailure(String viaTransactionId, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISipDelegateMessageCallback {
        static final int TRANSACTION_onMessageReceived = 1;
        static final int TRANSACTION_onMessageSendFailure = 3;
        static final int TRANSACTION_onMessageSent = 2;

        public Stub() {
            attachInterface(this, ISipDelegateMessageCallback.DESCRIPTOR);
        }

        public static ISipDelegateMessageCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISipDelegateMessageCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISipDelegateMessageCallback)) {
                return new Proxy(obj);
            }
            return (ISipDelegateMessageCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMessageReceived";
                case 2:
                    return "onMessageSent";
                case 3:
                    return "onMessageSendFailure";
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
            SipMessage _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISipDelegateMessageCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISipDelegateMessageCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SipMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onMessageReceived(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ISipDelegateMessageCallback.DESCRIPTOR);
                            String _arg02 = data.readString();
                            onMessageSent(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(ISipDelegateMessageCallback.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg1 = data.readInt();
                            onMessageSendFailure(_arg03, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISipDelegateMessageCallback {
            public static ISipDelegateMessageCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISipDelegateMessageCallback.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.ISipDelegateMessageCallback
            public void onMessageReceived(SipMessage message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateMessageCallback.DESCRIPTOR);
                    if (message != null) {
                        _data.writeInt(1);
                        message.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMessageReceived(message);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegateMessageCallback
            public void onMessageSent(String viaTransactionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateMessageCallback.DESCRIPTOR);
                    _data.writeString(viaTransactionId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMessageSent(viaTransactionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegateMessageCallback
            public void onMessageSendFailure(String viaTransactionId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateMessageCallback.DESCRIPTOR);
                    _data.writeString(viaTransactionId);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMessageSendFailure(viaTransactionId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISipDelegateMessageCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISipDelegateMessageCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
