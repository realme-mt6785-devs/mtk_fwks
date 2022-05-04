package android.tracing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ITracingServiceProxy extends IInterface {
    public static final String DESCRIPTOR = "android.tracing.ITracingServiceProxy";

    void notifyTraceSessionEnded(boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITracingServiceProxy {
        @Override // android.tracing.ITracingServiceProxy
        public void notifyTraceSessionEnded(boolean sessionStolen) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITracingServiceProxy {
        static final int TRANSACTION_notifyTraceSessionEnded = 1;

        public Stub() {
            attachInterface(this, ITracingServiceProxy.DESCRIPTOR);
        }

        public static ITracingServiceProxy asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITracingServiceProxy.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITracingServiceProxy)) {
                return new Proxy(obj);
            }
            return (ITracingServiceProxy) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyTraceSessionEnded";
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
                    reply.writeString(ITracingServiceProxy.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITracingServiceProxy.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            notifyTraceSessionEnded(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITracingServiceProxy {
            public static ITracingServiceProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITracingServiceProxy.DESCRIPTOR;
            }

            @Override // android.tracing.ITracingServiceProxy
            public void notifyTraceSessionEnded(boolean sessionStolen) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITracingServiceProxy.DESCRIPTOR);
                    _data.writeInt(sessionStolen ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyTraceSessionEnded(sessionStolen);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITracingServiceProxy impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITracingServiceProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
