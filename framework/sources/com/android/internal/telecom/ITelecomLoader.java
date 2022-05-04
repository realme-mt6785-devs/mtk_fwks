package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.telecom.IInternalServiceRetriever;
import com.android.internal.telecom.ITelecomService;
/* loaded from: classes4.dex */
public interface ITelecomLoader extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telecom.ITelecomLoader";

    ITelecomService createTelecomService(IInternalServiceRetriever iInternalServiceRetriever) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ITelecomLoader {
        @Override // com.android.internal.telecom.ITelecomLoader
        public ITelecomService createTelecomService(IInternalServiceRetriever retriever) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ITelecomLoader {
        static final int TRANSACTION_createTelecomService = 1;

        public Stub() {
            attachInterface(this, ITelecomLoader.DESCRIPTOR);
        }

        public static ITelecomLoader asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITelecomLoader.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITelecomLoader)) {
                return new Proxy(obj);
            }
            return (ITelecomLoader) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createTelecomService";
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
                    reply.writeString(ITelecomLoader.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITelecomLoader.DESCRIPTOR);
                            IInternalServiceRetriever _arg0 = IInternalServiceRetriever.Stub.asInterface(data.readStrongBinder());
                            ITelecomService _result = createTelecomService(_arg0);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ITelecomLoader {
            public static ITelecomLoader sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITelecomLoader.DESCRIPTOR;
            }

            @Override // com.android.internal.telecom.ITelecomLoader
            public ITelecomService createTelecomService(IInternalServiceRetriever retriever) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITelecomLoader.DESCRIPTOR);
                    _data.writeStrongBinder(retriever != null ? retriever.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTelecomService(retriever);
                    }
                    _reply.readException();
                    ITelecomService _result = ITelecomService.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITelecomLoader impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITelecomLoader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
