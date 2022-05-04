package android.security.metrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IKeystoreMetrics extends IInterface {
    public static final String DESCRIPTOR = "android$security$metrics$IKeystoreMetrics".replace('$', '.');

    KeystoreAtom[] pullMetrics(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IKeystoreMetrics {
        @Override // android.security.metrics.IKeystoreMetrics
        public KeystoreAtom[] pullMetrics(int atomID) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKeystoreMetrics {
        static final int TRANSACTION_pullMetrics = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreMetrics asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeystoreMetrics)) {
                return new Proxy(obj);
            }
            return (IKeystoreMetrics) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            int _arg0 = data.readInt();
                            KeystoreAtom[] _result = pullMetrics(_arg0);
                            reply.writeNoException();
                            reply.writeTypedArray(_result, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IKeystoreMetrics {
            public static IKeystoreMetrics sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.security.metrics.IKeystoreMetrics
            public KeystoreAtom[] pullMetrics(int atomID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(atomID);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pullMetrics(atomID);
                    }
                    _reply.readException();
                    KeystoreAtom[] _result = (KeystoreAtom[]) _reply.createTypedArray(KeystoreAtom.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreMetrics impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeystoreMetrics getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
