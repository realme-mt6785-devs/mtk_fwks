package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusFreeformConfigChangedListener extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusFreeformConfigChangedListener";

    void onConfigSwitchChanged(boolean z) throws RemoteException;

    void onConfigTypeChanged(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusFreeformConfigChangedListener {
        @Override // com.oplus.app.IOplusFreeformConfigChangedListener
        public void onConfigTypeChanged(int type) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusFreeformConfigChangedListener
        public void onConfigSwitchChanged(boolean enable) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusFreeformConfigChangedListener {
        static final int TRANSACTION_onConfigSwitchChanged = 2;
        static final int TRANSACTION_onConfigTypeChanged = 1;

        public Stub() {
            attachInterface(this, IOplusFreeformConfigChangedListener.DESCRIPTOR);
        }

        public static IOplusFreeformConfigChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusFreeformConfigChangedListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusFreeformConfigChangedListener)) {
                return new Proxy(obj);
            }
            return (IOplusFreeformConfigChangedListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onConfigTypeChanged";
                case 2:
                    return "onConfigSwitchChanged";
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
                    reply.writeString(IOplusFreeformConfigChangedListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusFreeformConfigChangedListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onConfigTypeChanged(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusFreeformConfigChangedListener.DESCRIPTOR);
                            boolean _arg02 = data.readInt() != 0;
                            onConfigSwitchChanged(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusFreeformConfigChangedListener {
            public static IOplusFreeformConfigChangedListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusFreeformConfigChangedListener.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusFreeformConfigChangedListener
            public void onConfigTypeChanged(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusFreeformConfigChangedListener.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigTypeChanged(type);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusFreeformConfigChangedListener
            public void onConfigSwitchChanged(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusFreeformConfigChangedListener.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigSwitchChanged(enable);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusFreeformConfigChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusFreeformConfigChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
