package android.hardware.camera2;

import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICameraOfflineSession extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.ICameraOfflineSession";

    void disconnect() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICameraOfflineSession {
        @Override // android.hardware.camera2.ICameraOfflineSession
        public void disconnect() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICameraOfflineSession {
        static final int TRANSACTION_disconnect = 1;

        public Stub() {
            attachInterface(this, ICameraOfflineSession.DESCRIPTOR);
        }

        public static ICameraOfflineSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICameraOfflineSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICameraOfflineSession)) {
                return new Proxy(obj);
            }
            return (ICameraOfflineSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return MediaMetrics.Value.DISCONNECT;
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
                    reply.writeString(ICameraOfflineSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICameraOfflineSession.DESCRIPTOR);
                            disconnect();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICameraOfflineSession {
            public static ICameraOfflineSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICameraOfflineSession.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.ICameraOfflineSession
            public void disconnect() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraOfflineSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disconnect();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraOfflineSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICameraOfflineSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
