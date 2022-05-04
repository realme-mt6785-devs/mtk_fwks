package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.CallForwardingInfo;
/* loaded from: classes4.dex */
public interface ICallForwardingInfoCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.ICallForwardingInfoCallback";

    void onCallForwardingInfoAvailable(CallForwardingInfo callForwardingInfo) throws RemoteException;

    void onError(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ICallForwardingInfoCallback {
        @Override // com.android.internal.telephony.ICallForwardingInfoCallback
        public void onCallForwardingInfoAvailable(CallForwardingInfo info) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ICallForwardingInfoCallback
        public void onError(int error) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ICallForwardingInfoCallback {
        static final int TRANSACTION_onCallForwardingInfoAvailable = 1;
        static final int TRANSACTION_onError = 2;

        public Stub() {
            attachInterface(this, ICallForwardingInfoCallback.DESCRIPTOR);
        }

        public static ICallForwardingInfoCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICallForwardingInfoCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICallForwardingInfoCallback)) {
                return new Proxy(obj);
            }
            return (ICallForwardingInfoCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCallForwardingInfoAvailable";
                case 2:
                    return "onError";
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
            CallForwardingInfo _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICallForwardingInfoCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICallForwardingInfoCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = CallForwardingInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onCallForwardingInfoAvailable(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ICallForwardingInfoCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onError(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ICallForwardingInfoCallback {
            public static ICallForwardingInfoCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICallForwardingInfoCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.ICallForwardingInfoCallback
            public void onCallForwardingInfoAvailable(CallForwardingInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallForwardingInfoCallback.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallForwardingInfoAvailable(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ICallForwardingInfoCallback
            public void onError(int error) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallForwardingInfoCallback.DESCRIPTOR);
                    _data.writeInt(error);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(error);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallForwardingInfoCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICallForwardingInfoCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
