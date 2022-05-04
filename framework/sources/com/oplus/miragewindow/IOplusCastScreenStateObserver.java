package com.oplus.miragewindow;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusCastScreenStateObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.miragewindow.IOplusCastScreenStateObserver";

    void onCastScreenStateChanged(OplusCastScreenState oplusCastScreenState) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusCastScreenStateObserver {
        @Override // com.oplus.miragewindow.IOplusCastScreenStateObserver
        public void onCastScreenStateChanged(OplusCastScreenState state) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusCastScreenStateObserver {
        static final int TRANSACTION_onCastScreenStateChanged = 1;

        public Stub() {
            attachInterface(this, IOplusCastScreenStateObserver.DESCRIPTOR);
        }

        public static IOplusCastScreenStateObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusCastScreenStateObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusCastScreenStateObserver)) {
                return new Proxy(obj);
            }
            return (IOplusCastScreenStateObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCastScreenStateChanged";
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
            OplusCastScreenState _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusCastScreenStateObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusCastScreenStateObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusCastScreenState.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onCastScreenStateChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusCastScreenStateObserver {
            public static IOplusCastScreenStateObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusCastScreenStateObserver.DESCRIPTOR;
            }

            @Override // com.oplus.miragewindow.IOplusCastScreenStateObserver
            public void onCastScreenStateChanged(OplusCastScreenState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCastScreenStateObserver.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCastScreenStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusCastScreenStateObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusCastScreenStateObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
