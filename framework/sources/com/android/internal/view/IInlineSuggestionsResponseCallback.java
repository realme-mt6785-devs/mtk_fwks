package com.android.internal.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.autofill.AutofillId;
import android.view.inputmethod.InlineSuggestionsResponse;
/* loaded from: classes4.dex */
public interface IInlineSuggestionsResponseCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.view.IInlineSuggestionsResponseCallback";

    void onInlineSuggestionsResponse(AutofillId autofillId, InlineSuggestionsResponse inlineSuggestionsResponse) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInlineSuggestionsResponseCallback {
        @Override // com.android.internal.view.IInlineSuggestionsResponseCallback
        public void onInlineSuggestionsResponse(AutofillId fieldId, InlineSuggestionsResponse response) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInlineSuggestionsResponseCallback {
        static final int TRANSACTION_onInlineSuggestionsResponse = 1;

        public Stub() {
            attachInterface(this, IInlineSuggestionsResponseCallback.DESCRIPTOR);
        }

        public static IInlineSuggestionsResponseCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineSuggestionsResponseCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineSuggestionsResponseCallback)) {
                return new Proxy(obj);
            }
            return (IInlineSuggestionsResponseCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onInlineSuggestionsResponse";
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
            AutofillId _arg0;
            InlineSuggestionsResponse _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInlineSuggestionsResponseCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineSuggestionsResponseCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = InlineSuggestionsResponse.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onInlineSuggestionsResponse(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInlineSuggestionsResponseCallback {
            public static IInlineSuggestionsResponseCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineSuggestionsResponseCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.view.IInlineSuggestionsResponseCallback
            public void onInlineSuggestionsResponse(AutofillId fieldId, InlineSuggestionsResponse response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsResponseCallback.DESCRIPTOR);
                    if (fieldId != null) {
                        _data.writeInt(1);
                        fieldId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (response != null) {
                        _data.writeInt(1);
                        response.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInlineSuggestionsResponse(fieldId, response);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionsResponseCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineSuggestionsResponseCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
