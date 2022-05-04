package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.RcsContactTerminatedReason;
import java.util.List;
/* loaded from: classes3.dex */
public interface ISubscribeResponseCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.ISubscribeResponseCallback";

    void onCommandError(int i) throws RemoteException;

    void onNetworkRespHeader(int i, String str, int i2, String str2) throws RemoteException;

    void onNetworkResponse(int i, String str) throws RemoteException;

    void onNotifyCapabilitiesUpdate(List<String> list) throws RemoteException;

    void onResourceTerminated(List<RcsContactTerminatedReason> list) throws RemoteException;

    void onTerminated(String str, long j) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISubscribeResponseCallback {
        @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
        public void onCommandError(int code) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
        public void onNetworkResponse(int code, String reason) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
        public void onNetworkRespHeader(int code, String reasonPhrase, int reasonHeaderCause, String reasonHeaderText) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
        public void onNotifyCapabilitiesUpdate(List<String> pidfXmls) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
        public void onResourceTerminated(List<RcsContactTerminatedReason> uriTerminatedReason) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
        public void onTerminated(String reason, long retryAfterMilliseconds) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISubscribeResponseCallback {
        static final int TRANSACTION_onCommandError = 1;
        static final int TRANSACTION_onNetworkRespHeader = 3;
        static final int TRANSACTION_onNetworkResponse = 2;
        static final int TRANSACTION_onNotifyCapabilitiesUpdate = 4;
        static final int TRANSACTION_onResourceTerminated = 5;
        static final int TRANSACTION_onTerminated = 6;

        public Stub() {
            attachInterface(this, ISubscribeResponseCallback.DESCRIPTOR);
        }

        public static ISubscribeResponseCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISubscribeResponseCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISubscribeResponseCallback)) {
                return new Proxy(obj);
            }
            return (ISubscribeResponseCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCommandError";
                case 2:
                    return "onNetworkResponse";
                case 3:
                    return "onNetworkRespHeader";
                case 4:
                    return "onNotifyCapabilitiesUpdate";
                case 5:
                    return "onResourceTerminated";
                case 6:
                    return "onTerminated";
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
                    reply.writeString(ISubscribeResponseCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISubscribeResponseCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onCommandError(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ISubscribeResponseCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _arg1 = data.readString();
                            onNetworkResponse(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(ISubscribeResponseCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            String _arg12 = data.readString();
                            int _arg2 = data.readInt();
                            String _arg3 = data.readString();
                            onNetworkRespHeader(_arg03, _arg12, _arg2, _arg3);
                            return true;
                        case 4:
                            data.enforceInterface(ISubscribeResponseCallback.DESCRIPTOR);
                            List<String> _arg04 = data.createStringArrayList();
                            onNotifyCapabilitiesUpdate(_arg04);
                            return true;
                        case 5:
                            data.enforceInterface(ISubscribeResponseCallback.DESCRIPTOR);
                            List<RcsContactTerminatedReason> _arg05 = data.createTypedArrayList(RcsContactTerminatedReason.CREATOR);
                            onResourceTerminated(_arg05);
                            return true;
                        case 6:
                            data.enforceInterface(ISubscribeResponseCallback.DESCRIPTOR);
                            String _arg06 = data.readString();
                            long _arg13 = data.readLong();
                            onTerminated(_arg06, _arg13);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISubscribeResponseCallback {
            public static ISubscribeResponseCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISubscribeResponseCallback.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
            public void onCommandError(int code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISubscribeResponseCallback.DESCRIPTOR);
                    _data.writeInt(code);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCommandError(code);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
            public void onNetworkResponse(int code, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISubscribeResponseCallback.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNetworkResponse(code, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
            public void onNetworkRespHeader(int code, String reasonPhrase, int reasonHeaderCause, String reasonHeaderText) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISubscribeResponseCallback.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeString(reasonPhrase);
                    _data.writeInt(reasonHeaderCause);
                    _data.writeString(reasonHeaderText);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNetworkRespHeader(code, reasonPhrase, reasonHeaderCause, reasonHeaderText);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
            public void onNotifyCapabilitiesUpdate(List<String> pidfXmls) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISubscribeResponseCallback.DESCRIPTOR);
                    _data.writeStringList(pidfXmls);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyCapabilitiesUpdate(pidfXmls);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
            public void onResourceTerminated(List<RcsContactTerminatedReason> uriTerminatedReason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISubscribeResponseCallback.DESCRIPTOR);
                    _data.writeTypedList(uriTerminatedReason);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResourceTerminated(uriTerminatedReason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISubscribeResponseCallback
            public void onTerminated(String reason, long retryAfterMilliseconds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISubscribeResponseCallback.DESCRIPTOR);
                    _data.writeString(reason);
                    _data.writeLong(retryAfterMilliseconds);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTerminated(reason, retryAfterMilliseconds);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISubscribeResponseCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISubscribeResponseCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
