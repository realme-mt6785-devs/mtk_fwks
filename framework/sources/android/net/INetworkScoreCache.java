package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface INetworkScoreCache extends IInterface {
    void clearScores() throws RemoteException;

    void updateScores(List<ScoredNetwork> list) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements INetworkScoreCache {
        @Override // android.net.INetworkScoreCache
        public void updateScores(List<ScoredNetwork> networks) throws RemoteException {
        }

        @Override // android.net.INetworkScoreCache
        public void clearScores() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements INetworkScoreCache {
        public static final String DESCRIPTOR = "android.net.INetworkScoreCache";
        static final int TRANSACTION_clearScores = 2;
        static final int TRANSACTION_updateScores = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkScoreCache asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INetworkScoreCache)) {
                return new Proxy(obj);
            }
            return (INetworkScoreCache) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "updateScores";
                case 2:
                    return "clearScores";
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
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            List<ScoredNetwork> _arg0 = data.createTypedArrayList(ScoredNetwork.CREATOR);
                            updateScores(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            clearScores();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements INetworkScoreCache {
            public static INetworkScoreCache sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.net.INetworkScoreCache
            public void updateScores(List<ScoredNetwork> networks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(networks);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateScores(networks);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkScoreCache
            public void clearScores() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearScores();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkScoreCache impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INetworkScoreCache getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
