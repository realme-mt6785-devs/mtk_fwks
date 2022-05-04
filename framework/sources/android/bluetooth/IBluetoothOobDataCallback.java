package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBluetoothOobDataCallback extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothOobDataCallback";

    void onError(int i) throws RemoteException;

    void onOobData(int i, OobData oobData) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothOobDataCallback {
        @Override // android.bluetooth.IBluetoothOobDataCallback
        public void onOobData(int transport, OobData oobData) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothOobDataCallback
        public void onError(int errorCode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothOobDataCallback {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onOobData = 1;

        public Stub() {
            attachInterface(this, IBluetoothOobDataCallback.DESCRIPTOR);
        }

        public static IBluetoothOobDataCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothOobDataCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothOobDataCallback)) {
                return new Proxy(obj);
            }
            return (IBluetoothOobDataCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onOobData";
                case 2:
                    return "onError";
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
            OobData _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothOobDataCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothOobDataCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = OobData.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onOobData(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothOobDataCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onError(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothOobDataCallback {
            public static IBluetoothOobDataCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothOobDataCallback.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothOobDataCallback
            public void onOobData(int transport, OobData oobData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothOobDataCallback.DESCRIPTOR);
                    _data.writeInt(transport);
                    if (oobData != null) {
                        _data.writeInt(1);
                        oobData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOobData(transport, oobData);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothOobDataCallback
            public void onError(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothOobDataCallback.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothOobDataCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothOobDataCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
