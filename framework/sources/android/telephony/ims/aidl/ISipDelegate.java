package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.SipMessage;
/* loaded from: classes3.dex */
public interface ISipDelegate extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.ISipDelegate";

    void cleanupSession(String str) throws RemoteException;

    void notifyMessageReceiveError(String str, int i) throws RemoteException;

    void notifyMessageReceived(String str) throws RemoteException;

    void sendMessage(SipMessage sipMessage, long j) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISipDelegate {
        @Override // android.telephony.ims.aidl.ISipDelegate
        public void sendMessage(SipMessage sipMessage, long configVersion) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegate
        public void notifyMessageReceived(String viaTransactionId) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegate
        public void notifyMessageReceiveError(String viaTransactionId, int reason) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegate
        public void cleanupSession(String callId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISipDelegate {
        static final int TRANSACTION_cleanupSession = 4;
        static final int TRANSACTION_notifyMessageReceiveError = 3;
        static final int TRANSACTION_notifyMessageReceived = 2;
        static final int TRANSACTION_sendMessage = 1;

        public Stub() {
            attachInterface(this, ISipDelegate.DESCRIPTOR);
        }

        public static ISipDelegate asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISipDelegate.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISipDelegate)) {
                return new Proxy(obj);
            }
            return (ISipDelegate) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "sendMessage";
                case 2:
                    return "notifyMessageReceived";
                case 3:
                    return "notifyMessageReceiveError";
                case 4:
                    return "cleanupSession";
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
                    reply.writeString(ISipDelegate.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISipDelegate.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SipMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            long _arg1 = data.readLong();
                            sendMessage(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(ISipDelegate.DESCRIPTOR);
                            String _arg02 = data.readString();
                            notifyMessageReceived(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(ISipDelegate.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg12 = data.readInt();
                            notifyMessageReceiveError(_arg03, _arg12);
                            return true;
                        case 4:
                            data.enforceInterface(ISipDelegate.DESCRIPTOR);
                            String _arg04 = data.readString();
                            cleanupSession(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISipDelegate {
            public static ISipDelegate sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISipDelegate.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.ISipDelegate
            public void sendMessage(SipMessage sipMessage, long configVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegate.DESCRIPTOR);
                    if (sipMessage != null) {
                        _data.writeInt(1);
                        sipMessage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(configVersion);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendMessage(sipMessage, configVersion);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegate
            public void notifyMessageReceived(String viaTransactionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegate.DESCRIPTOR);
                    _data.writeString(viaTransactionId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMessageReceived(viaTransactionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegate
            public void notifyMessageReceiveError(String viaTransactionId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegate.DESCRIPTOR);
                    _data.writeString(viaTransactionId);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMessageReceiveError(viaTransactionId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegate
            public void cleanupSession(String callId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegate.DESCRIPTOR);
                    _data.writeString(callId);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cleanupSession(callId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISipDelegate impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISipDelegate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
