package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.DelegateRequest;
import android.telephony.ims.aidl.ISipDelegate;
import android.telephony.ims.aidl.ISipDelegateMessageCallback;
import android.telephony.ims.aidl.ISipDelegateStateCallback;
/* loaded from: classes3.dex */
public interface ISipTransport extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.ISipTransport";

    void createSipDelegate(int i, DelegateRequest delegateRequest, ISipDelegateStateCallback iSipDelegateStateCallback, ISipDelegateMessageCallback iSipDelegateMessageCallback) throws RemoteException;

    void destroySipDelegate(ISipDelegate iSipDelegate, int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISipTransport {
        @Override // android.telephony.ims.aidl.ISipTransport
        public void createSipDelegate(int subId, DelegateRequest request, ISipDelegateStateCallback dc, ISipDelegateMessageCallback mc) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipTransport
        public void destroySipDelegate(ISipDelegate delegate, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISipTransport {
        static final int TRANSACTION_createSipDelegate = 1;
        static final int TRANSACTION_destroySipDelegate = 2;

        public Stub() {
            attachInterface(this, ISipTransport.DESCRIPTOR);
        }

        public static ISipTransport asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISipTransport.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISipTransport)) {
                return new Proxy(obj);
            }
            return (ISipTransport) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSipDelegate";
                case 2:
                    return "destroySipDelegate";
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
            DelegateRequest _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISipTransport.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISipTransport.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = DelegateRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            ISipDelegateStateCallback _arg2 = ISipDelegateStateCallback.Stub.asInterface(data.readStrongBinder());
                            ISipDelegateMessageCallback _arg3 = ISipDelegateMessageCallback.Stub.asInterface(data.readStrongBinder());
                            createSipDelegate(_arg0, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(ISipTransport.DESCRIPTOR);
                            ISipDelegate _arg02 = ISipDelegate.Stub.asInterface(data.readStrongBinder());
                            int _arg12 = data.readInt();
                            destroySipDelegate(_arg02, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISipTransport {
            public static ISipTransport sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISipTransport.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.ISipTransport
            public void createSipDelegate(int subId, DelegateRequest request, ISipDelegateStateCallback dc, ISipDelegateMessageCallback mc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipTransport.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(dc != null ? dc.asBinder() : null);
                    _data.writeStrongBinder(mc != null ? mc.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createSipDelegate(subId, request, dc, mc);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipTransport
            public void destroySipDelegate(ISipDelegate delegate, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipTransport.DESCRIPTOR);
                    _data.writeStrongBinder(delegate != null ? delegate.asBinder() : null);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroySipDelegate(delegate, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISipTransport impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISipTransport getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
