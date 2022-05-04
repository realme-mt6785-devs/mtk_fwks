package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAdbTransport extends IInterface {
    public static final String DESCRIPTOR = "android.debug.IAdbTransport";

    void onAdbEnabled(boolean z, byte b) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAdbTransport {
        @Override // android.debug.IAdbTransport
        public void onAdbEnabled(boolean enabled, byte type) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAdbTransport {
        static final int TRANSACTION_onAdbEnabled = 1;

        public Stub() {
            attachInterface(this, IAdbTransport.DESCRIPTOR);
        }

        public static IAdbTransport asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAdbTransport.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAdbTransport)) {
                return new Proxy(obj);
            }
            return (IAdbTransport) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAdbEnabled";
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
                    reply.writeString(IAdbTransport.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAdbTransport.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            byte _arg1 = data.readByte();
                            onAdbEnabled(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAdbTransport {
            public static IAdbTransport sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAdbTransport.DESCRIPTOR;
            }

            @Override // android.debug.IAdbTransport
            public void onAdbEnabled(boolean enabled, byte type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdbTransport.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeByte(type);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onAdbEnabled(enabled, type);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAdbTransport impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAdbTransport getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
