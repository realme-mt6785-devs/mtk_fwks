package com.oplus.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusSplitScreenObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusSplitScreenObserver";

    void onStateChanged(String str, Bundle bundle) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusSplitScreenObserver {
        @Override // com.oplus.app.IOplusSplitScreenObserver
        public void onStateChanged(String event, Bundle bundle) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusSplitScreenObserver {
        static final int TRANSACTION_onStateChanged = 1;

        public Stub() {
            attachInterface(this, IOplusSplitScreenObserver.DESCRIPTOR);
        }

        public static IOplusSplitScreenObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusSplitScreenObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusSplitScreenObserver)) {
                return new Proxy(obj);
            }
            return (IOplusSplitScreenObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onStateChanged";
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
            Bundle _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusSplitScreenObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusSplitScreenObserver.DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onStateChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusSplitScreenObserver {
            public static IOplusSplitScreenObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusSplitScreenObserver.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusSplitScreenObserver
            public void onStateChanged(String event, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusSplitScreenObserver.DESCRIPTOR);
                    _data.writeString(event);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStateChanged(event, bundle);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusSplitScreenObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusSplitScreenObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
