package com.oplus.confinemode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusConfineModeObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.confinemode.IOplusConfineModeObserver";

    void onChange(int i, int i2, int i3) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusConfineModeObserver {
        @Override // com.oplus.confinemode.IOplusConfineModeObserver
        public void onChange(int oldMode, int newMode, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusConfineModeObserver {
        static final int TRANSACTION_onChange = 1;

        public Stub() {
            attachInterface(this, IOplusConfineModeObserver.DESCRIPTOR);
        }

        public static IOplusConfineModeObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusConfineModeObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusConfineModeObserver)) {
                return new Proxy(obj);
            }
            return (IOplusConfineModeObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onChange";
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
                    reply.writeString(IOplusConfineModeObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusConfineModeObserver.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            onChange(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusConfineModeObserver {
            public static IOplusConfineModeObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusConfineModeObserver.DESCRIPTOR;
            }

            @Override // com.oplus.confinemode.IOplusConfineModeObserver
            public void onChange(int oldMode, int newMode, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusConfineModeObserver.DESCRIPTOR);
                    _data.writeInt(oldMode);
                    _data.writeInt(newMode);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChange(oldMode, newMode, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusConfineModeObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusConfineModeObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
