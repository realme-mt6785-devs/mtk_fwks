package com.oplus.darkmode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusDarkModeListener extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.darkmode.IOplusDarkModeListener";

    void onUiModeConfigurationChangeFinish() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusDarkModeListener {
        @Override // com.oplus.darkmode.IOplusDarkModeListener
        public void onUiModeConfigurationChangeFinish() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusDarkModeListener {
        static final int TRANSACTION_onUiModeConfigurationChangeFinish = 1;

        public Stub() {
            attachInterface(this, IOplusDarkModeListener.DESCRIPTOR);
        }

        public static IOplusDarkModeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusDarkModeListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusDarkModeListener)) {
                return new Proxy(obj);
            }
            return (IOplusDarkModeListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onUiModeConfigurationChangeFinish";
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
                    reply.writeString(IOplusDarkModeListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusDarkModeListener.DESCRIPTOR);
                            onUiModeConfigurationChangeFinish();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusDarkModeListener {
            public static IOplusDarkModeListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusDarkModeListener.DESCRIPTOR;
            }

            @Override // com.oplus.darkmode.IOplusDarkModeListener
            public void onUiModeConfigurationChangeFinish() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusDarkModeListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUiModeConfigurationChangeFinish();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusDarkModeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusDarkModeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
