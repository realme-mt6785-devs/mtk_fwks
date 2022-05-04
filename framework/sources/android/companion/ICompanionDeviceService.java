package android.companion;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICompanionDeviceService extends IInterface {
    public static final String DESCRIPTOR = "android.companion.ICompanionDeviceService";

    void onDeviceAppeared(String str) throws RemoteException;

    void onDeviceDisappeared(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICompanionDeviceService {
        @Override // android.companion.ICompanionDeviceService
        public void onDeviceAppeared(String address) throws RemoteException {
        }

        @Override // android.companion.ICompanionDeviceService
        public void onDeviceDisappeared(String address) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICompanionDeviceService {
        static final int TRANSACTION_onDeviceAppeared = 1;
        static final int TRANSACTION_onDeviceDisappeared = 2;

        public Stub() {
            attachInterface(this, ICompanionDeviceService.DESCRIPTOR);
        }

        public static ICompanionDeviceService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICompanionDeviceService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICompanionDeviceService)) {
                return new Proxy(obj);
            }
            return (ICompanionDeviceService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDeviceAppeared";
                case 2:
                    return "onDeviceDisappeared";
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
                    reply.writeString(ICompanionDeviceService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICompanionDeviceService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            onDeviceAppeared(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ICompanionDeviceService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            onDeviceDisappeared(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICompanionDeviceService {
            public static ICompanionDeviceService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICompanionDeviceService.DESCRIPTOR;
            }

            @Override // android.companion.ICompanionDeviceService
            public void onDeviceAppeared(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICompanionDeviceService.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceAppeared(address);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceService
            public void onDeviceDisappeared(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICompanionDeviceService.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDisappeared(address);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICompanionDeviceService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICompanionDeviceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
