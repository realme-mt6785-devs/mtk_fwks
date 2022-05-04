package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.PhoneAccountSuggestion;
import java.util.List;
/* loaded from: classes4.dex */
public interface IPhoneAccountSuggestionCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telecom.IPhoneAccountSuggestionCallback";

    void suggestPhoneAccounts(String str, List<PhoneAccountSuggestion> list) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IPhoneAccountSuggestionCallback {
        @Override // com.android.internal.telecom.IPhoneAccountSuggestionCallback
        public void suggestPhoneAccounts(String number, List<PhoneAccountSuggestion> suggestions) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IPhoneAccountSuggestionCallback {
        static final int TRANSACTION_suggestPhoneAccounts = 1;

        public Stub() {
            attachInterface(this, IPhoneAccountSuggestionCallback.DESCRIPTOR);
        }

        public static IPhoneAccountSuggestionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPhoneAccountSuggestionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPhoneAccountSuggestionCallback)) {
                return new Proxy(obj);
            }
            return (IPhoneAccountSuggestionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "suggestPhoneAccounts";
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
                    reply.writeString(IPhoneAccountSuggestionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPhoneAccountSuggestionCallback.DESCRIPTOR);
                            String _arg0 = data.readString();
                            List<PhoneAccountSuggestion> _arg1 = data.createTypedArrayList(PhoneAccountSuggestion.CREATOR);
                            suggestPhoneAccounts(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IPhoneAccountSuggestionCallback {
            public static IPhoneAccountSuggestionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPhoneAccountSuggestionCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telecom.IPhoneAccountSuggestionCallback
            public void suggestPhoneAccounts(String number, List<PhoneAccountSuggestion> suggestions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPhoneAccountSuggestionCallback.DESCRIPTOR);
                    _data.writeString(number);
                    _data.writeTypedList(suggestions);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestPhoneAccounts(number, suggestions);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPhoneAccountSuggestionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPhoneAccountSuggestionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
