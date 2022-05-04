package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBluetoothMetadataListener extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothMetadataListener";

    void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothMetadataListener {
        @Override // android.bluetooth.IBluetoothMetadataListener
        public void onMetadataChanged(BluetoothDevice devices, int key, byte[] value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothMetadataListener {
        static final int TRANSACTION_onMetadataChanged = 1;

        public Stub() {
            attachInterface(this, IBluetoothMetadataListener.DESCRIPTOR);
        }

        public static IBluetoothMetadataListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothMetadataListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothMetadataListener)) {
                return new Proxy(obj);
            }
            return (IBluetoothMetadataListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMetadataChanged";
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
            BluetoothDevice _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothMetadataListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothMetadataListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            byte[] _arg2 = data.createByteArray();
                            onMetadataChanged(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothMetadataListener {
            public static IBluetoothMetadataListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothMetadataListener.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothMetadataListener
            public void onMetadataChanged(BluetoothDevice devices, int key, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMetadataListener.DESCRIPTOR);
                    if (devices != null) {
                        _data.writeInt(1);
                        devices.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(key);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMetadataChanged(devices, key, value);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMetadataListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothMetadataListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
