package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBluetoothConnectionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothConnectionCallback";

    void onDeviceConnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    void onDeviceDisconnected(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothConnectionCallback {
        @Override // android.bluetooth.IBluetoothConnectionCallback
        public void onDeviceConnected(BluetoothDevice device) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothConnectionCallback
        public void onDeviceDisconnected(BluetoothDevice device, int hciReason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothConnectionCallback {
        static final int TRANSACTION_onDeviceConnected = 1;
        static final int TRANSACTION_onDeviceDisconnected = 2;

        public Stub() {
            attachInterface(this, IBluetoothConnectionCallback.DESCRIPTOR);
        }

        public static IBluetoothConnectionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothConnectionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothConnectionCallback)) {
                return new Proxy(obj);
            }
            return (IBluetoothConnectionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDeviceConnected";
                case 2:
                    return "onDeviceDisconnected";
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
            BluetoothDevice _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothConnectionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothConnectionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onDeviceConnected(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothConnectionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg1 = data.readInt();
                            onDeviceDisconnected(_arg02, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothConnectionCallback {
            public static IBluetoothConnectionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothConnectionCallback.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothConnectionCallback
            public void onDeviceConnected(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothConnectionCallback.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceConnected(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothConnectionCallback
            public void onDeviceDisconnected(BluetoothDevice device, int hciReason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothConnectionCallback.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(hciReason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDisconnected(device, hciReason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothConnectionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothConnectionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
