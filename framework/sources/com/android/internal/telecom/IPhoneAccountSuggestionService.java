package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.telecom.IPhoneAccountSuggestionCallback;
/* loaded from: classes4.dex */
public interface IPhoneAccountSuggestionService extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telecom.IPhoneAccountSuggestionService";

    void onAccountSuggestionRequest(IPhoneAccountSuggestionCallback iPhoneAccountSuggestionCallback, String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IPhoneAccountSuggestionService {
        @Override // com.android.internal.telecom.IPhoneAccountSuggestionService
        public void onAccountSuggestionRequest(IPhoneAccountSuggestionCallback callback, String number) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IPhoneAccountSuggestionService {
        static final int TRANSACTION_onAccountSuggestionRequest = 1;

        public Stub() {
            attachInterface(this, IPhoneAccountSuggestionService.DESCRIPTOR);
        }

        public static IPhoneAccountSuggestionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPhoneAccountSuggestionService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPhoneAccountSuggestionService)) {
                return new Proxy(obj);
            }
            return (IPhoneAccountSuggestionService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAccountSuggestionRequest";
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
                    reply.writeString(IPhoneAccountSuggestionService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPhoneAccountSuggestionService.DESCRIPTOR);
                            IPhoneAccountSuggestionCallback _arg0 = IPhoneAccountSuggestionCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg1 = data.readString();
                            onAccountSuggestionRequest(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IPhoneAccountSuggestionService {
            public static IPhoneAccountSuggestionService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPhoneAccountSuggestionService.DESCRIPTOR;
            }

            @Override // com.android.internal.telecom.IPhoneAccountSuggestionService
            public void onAccountSuggestionRequest(IPhoneAccountSuggestionCallback callback, String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPhoneAccountSuggestionService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAccountSuggestionRequest(callback, number);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPhoneAccountSuggestionService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPhoneAccountSuggestionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
