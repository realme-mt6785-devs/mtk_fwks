package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IGetEuiccChallengeCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IGetEuiccChallengeCallback";

    void onComplete(int i, byte[] bArr) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IGetEuiccChallengeCallback {
        @Override // com.android.internal.telephony.euicc.IGetEuiccChallengeCallback
        public void onComplete(int resultCode, byte[] challenge) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IGetEuiccChallengeCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, IGetEuiccChallengeCallback.DESCRIPTOR);
        }

        public static IGetEuiccChallengeCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGetEuiccChallengeCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGetEuiccChallengeCallback)) {
                return new Proxy(obj);
            }
            return (IGetEuiccChallengeCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onComplete";
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
                    reply.writeString(IGetEuiccChallengeCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGetEuiccChallengeCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            onComplete(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IGetEuiccChallengeCallback {
            public static IGetEuiccChallengeCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGetEuiccChallengeCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.euicc.IGetEuiccChallengeCallback
            public void onComplete(int resultCode, byte[] challenge) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGetEuiccChallengeCallback.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    _data.writeByteArray(challenge);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(resultCode, challenge);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetEuiccChallengeCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGetEuiccChallengeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
