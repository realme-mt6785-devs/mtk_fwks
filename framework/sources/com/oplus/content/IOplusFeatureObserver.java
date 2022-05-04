package com.oplus.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes4.dex */
public interface IOplusFeatureObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.content.IOplusFeatureObserver";

    void onFeatureUpdate(List<String> list) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusFeatureObserver {
        @Override // com.oplus.content.IOplusFeatureObserver
        public void onFeatureUpdate(List<String> features) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusFeatureObserver {
        static final int TRANSACTION_onFeatureUpdate = 1;

        public Stub() {
            attachInterface(this, IOplusFeatureObserver.DESCRIPTOR);
        }

        public static IOplusFeatureObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusFeatureObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusFeatureObserver)) {
                return new Proxy(obj);
            }
            return (IOplusFeatureObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onFeatureUpdate";
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
                    reply.writeString(IOplusFeatureObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusFeatureObserver.DESCRIPTOR);
                            List<String> _arg0 = data.createStringArrayList();
                            onFeatureUpdate(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusFeatureObserver {
            public static IOplusFeatureObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusFeatureObserver.DESCRIPTOR;
            }

            @Override // com.oplus.content.IOplusFeatureObserver
            public void onFeatureUpdate(List<String> features) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusFeatureObserver.DESCRIPTOR);
                    _data.writeStringList(features);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFeatureUpdate(features);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusFeatureObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusFeatureObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
