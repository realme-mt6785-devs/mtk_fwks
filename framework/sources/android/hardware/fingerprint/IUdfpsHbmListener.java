package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IUdfpsHbmListener extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.fingerprint.IUdfpsHbmListener";
    public static final int GLOBAL_HBM = 0;
    public static final int LOCAL_HBM = 1;

    void onHbmDisabled(int i, int i2) throws RemoteException;

    void onHbmEnabled(int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IUdfpsHbmListener {
        @Override // android.hardware.fingerprint.IUdfpsHbmListener
        public void onHbmEnabled(int hbmType, int displayId) throws RemoteException {
        }

        @Override // android.hardware.fingerprint.IUdfpsHbmListener
        public void onHbmDisabled(int hbmType, int displayId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IUdfpsHbmListener {
        static final int TRANSACTION_onHbmDisabled = 2;
        static final int TRANSACTION_onHbmEnabled = 1;

        public Stub() {
            attachInterface(this, IUdfpsHbmListener.DESCRIPTOR);
        }

        public static IUdfpsHbmListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IUdfpsHbmListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IUdfpsHbmListener)) {
                return new Proxy(obj);
            }
            return (IUdfpsHbmListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onHbmEnabled";
                case 2:
                    return "onHbmDisabled";
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
                    reply.writeString(IUdfpsHbmListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IUdfpsHbmListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            onHbmEnabled(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IUdfpsHbmListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg12 = data.readInt();
                            onHbmDisabled(_arg02, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IUdfpsHbmListener {
            public static IUdfpsHbmListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUdfpsHbmListener.DESCRIPTOR;
            }

            @Override // android.hardware.fingerprint.IUdfpsHbmListener
            public void onHbmEnabled(int hbmType, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsHbmListener.DESCRIPTOR);
                    _data.writeInt(hbmType);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHbmEnabled(hbmType, displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.IUdfpsHbmListener
            public void onHbmDisabled(int hbmType, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsHbmListener.DESCRIPTOR);
                    _data.writeInt(hbmType);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHbmDisabled(hbmType, displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUdfpsHbmListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUdfpsHbmListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
