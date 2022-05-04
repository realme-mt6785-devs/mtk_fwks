package android.security;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IGenerateRkpKeyService extends IInterface {
    public static final String DESCRIPTOR = "android.security.IGenerateRkpKeyService";

    void generateKey(int i) throws RemoteException;

    void notifyKeyGenerated(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IGenerateRkpKeyService {
        @Override // android.security.IGenerateRkpKeyService
        public void notifyKeyGenerated(int securityLevel) throws RemoteException {
        }

        @Override // android.security.IGenerateRkpKeyService
        public void generateKey(int securityLevel) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IGenerateRkpKeyService {
        static final int TRANSACTION_generateKey = 2;
        static final int TRANSACTION_notifyKeyGenerated = 1;

        public Stub() {
            attachInterface(this, IGenerateRkpKeyService.DESCRIPTOR);
        }

        public static IGenerateRkpKeyService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGenerateRkpKeyService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGenerateRkpKeyService)) {
                return new Proxy(obj);
            }
            return (IGenerateRkpKeyService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyKeyGenerated";
                case 2:
                    return "generateKey";
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
                    reply.writeString(IGenerateRkpKeyService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGenerateRkpKeyService.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            notifyKeyGenerated(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IGenerateRkpKeyService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            generateKey(_arg02);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IGenerateRkpKeyService {
            public static IGenerateRkpKeyService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGenerateRkpKeyService.DESCRIPTOR;
            }

            @Override // android.security.IGenerateRkpKeyService
            public void notifyKeyGenerated(int securityLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGenerateRkpKeyService.DESCRIPTOR);
                    _data.writeInt(securityLevel);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyKeyGenerated(securityLevel);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.security.IGenerateRkpKeyService
            public void generateKey(int securityLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGenerateRkpKeyService.DESCRIPTOR);
                    _data.writeInt(securityLevel);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().generateKey(securityLevel);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGenerateRkpKeyService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGenerateRkpKeyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
