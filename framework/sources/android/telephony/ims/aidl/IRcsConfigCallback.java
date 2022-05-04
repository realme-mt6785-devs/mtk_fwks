package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IRcsConfigCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IRcsConfigCallback";

    void onAutoConfigurationErrorReceived(int i, String str) throws RemoteException;

    void onConfigurationChanged(byte[] bArr) throws RemoteException;

    void onConfigurationReset() throws RemoteException;

    void onPreProvisioningReceived(byte[] bArr) throws RemoteException;

    void onRemoved() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRcsConfigCallback {
        @Override // android.telephony.ims.aidl.IRcsConfigCallback
        public void onConfigurationChanged(byte[] config) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsConfigCallback
        public void onAutoConfigurationErrorReceived(int errorCode, String errorString) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsConfigCallback
        public void onConfigurationReset() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsConfigCallback
        public void onRemoved() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsConfigCallback
        public void onPreProvisioningReceived(byte[] config) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRcsConfigCallback {
        static final int TRANSACTION_onAutoConfigurationErrorReceived = 2;
        static final int TRANSACTION_onConfigurationChanged = 1;
        static final int TRANSACTION_onConfigurationReset = 3;
        static final int TRANSACTION_onPreProvisioningReceived = 5;
        static final int TRANSACTION_onRemoved = 4;

        public Stub() {
            attachInterface(this, IRcsConfigCallback.DESCRIPTOR);
        }

        public static IRcsConfigCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRcsConfigCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRcsConfigCallback)) {
                return new Proxy(obj);
            }
            return (IRcsConfigCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onConfigurationChanged";
                case 2:
                    return "onAutoConfigurationErrorReceived";
                case 3:
                    return "onConfigurationReset";
                case 4:
                    return "onRemoved";
                case 5:
                    return "onPreProvisioningReceived";
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
                    reply.writeString(IRcsConfigCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRcsConfigCallback.DESCRIPTOR);
                            byte[] _arg0 = data.createByteArray();
                            onConfigurationChanged(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IRcsConfigCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _arg1 = data.readString();
                            onAutoConfigurationErrorReceived(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IRcsConfigCallback.DESCRIPTOR);
                            onConfigurationReset();
                            return true;
                        case 4:
                            data.enforceInterface(IRcsConfigCallback.DESCRIPTOR);
                            onRemoved();
                            return true;
                        case 5:
                            data.enforceInterface(IRcsConfigCallback.DESCRIPTOR);
                            byte[] _arg03 = data.createByteArray();
                            onPreProvisioningReceived(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRcsConfigCallback {
            public static IRcsConfigCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRcsConfigCallback.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IRcsConfigCallback
            public void onConfigurationChanged(byte[] config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRcsConfigCallback.DESCRIPTOR);
                    _data.writeByteArray(config);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigurationChanged(config);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsConfigCallback
            public void onAutoConfigurationErrorReceived(int errorCode, String errorString) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRcsConfigCallback.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    _data.writeString(errorString);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAutoConfigurationErrorReceived(errorCode, errorString);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsConfigCallback
            public void onConfigurationReset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRcsConfigCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigurationReset();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsConfigCallback
            public void onRemoved() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRcsConfigCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoved();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsConfigCallback
            public void onPreProvisioningReceived(byte[] config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRcsConfigCallback.DESCRIPTOR);
                    _data.writeByteArray(config);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPreProvisioningReceived(config);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRcsConfigCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRcsConfigCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
