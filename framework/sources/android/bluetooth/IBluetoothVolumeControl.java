package android.bluetooth;

import android.content.AttributionSource;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IBluetoothVolumeControl extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothVolumeControl";

    boolean connect(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr, AttributionSource attributionSource) throws RemoteException;

    void mute(AttributionSource attributionSource) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    void setVolume(int i, AttributionSource attributionSource) throws RemoteException;

    void unmute(AttributionSource attributionSource) throws RemoteException;

    void volumeDown(boolean z, AttributionSource attributionSource) throws RemoteException;

    void volumeUp(boolean z, AttributionSource attributionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothVolumeControl {
        @Override // android.bluetooth.IBluetoothVolumeControl
        public boolean connect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public boolean disconnect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public int getConnectionState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public void setVolume(int volume, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public void volumeUp(boolean unmute, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public void volumeDown(boolean unmute, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public void mute(AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothVolumeControl
        public void unmute(AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothVolumeControl {
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getConnectedDevices = 3;
        static final int TRANSACTION_getConnectionPolicy = 7;
        static final int TRANSACTION_getConnectionState = 5;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
        static final int TRANSACTION_mute = 11;
        static final int TRANSACTION_setConnectionPolicy = 6;
        static final int TRANSACTION_setVolume = 8;
        static final int TRANSACTION_unmute = 12;
        static final int TRANSACTION_volumeDown = 10;
        static final int TRANSACTION_volumeUp = 9;

        public Stub() {
            attachInterface(this, IBluetoothVolumeControl.DESCRIPTOR);
        }

        public static IBluetoothVolumeControl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothVolumeControl.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothVolumeControl)) {
                return new Proxy(obj);
            }
            return (IBluetoothVolumeControl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return MediaMetrics.Value.CONNECT;
                case 2:
                    return MediaMetrics.Value.DISCONNECT;
                case 3:
                    return "getConnectedDevices";
                case 4:
                    return "getDevicesMatchingConnectionStates";
                case 5:
                    return "getConnectionState";
                case 6:
                    return "setConnectionPolicy";
                case 7:
                    return "getConnectionPolicy";
                case 8:
                    return "setVolume";
                case 9:
                    return "volumeUp";
                case 10:
                    return "volumeDown";
                case 11:
                    return MediaMetrics.Value.MUTE;
                case 12:
                    return MediaMetrics.Value.UNMUTE;
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
            AttributionSource _arg1;
            BluetoothDevice _arg02;
            AttributionSource _arg12;
            AttributionSource _arg03;
            AttributionSource _arg13;
            BluetoothDevice _arg04;
            AttributionSource _arg14;
            BluetoothDevice _arg05;
            AttributionSource _arg2;
            BluetoothDevice _arg06;
            AttributionSource _arg15;
            AttributionSource _arg16;
            AttributionSource _arg17;
            AttributionSource _arg18;
            AttributionSource _arg07;
            AttributionSource _arg08;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothVolumeControl.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg09 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            boolean connect = connect(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(connect ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            boolean disconnect = disconnect(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeInt(disconnect ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            List<BluetoothDevice> _result = getConnectedDevices(_arg03);
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            int[] _arg010 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            List<BluetoothDevice> _result2 = getDevicesMatchingConnectionStates(_arg010, _arg13);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg14 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            int _result3 = getConnectionState(_arg04, _arg14);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            int _arg19 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean connectionPolicy = setConnectionPolicy(_arg05, _arg19, _arg2);
                            reply.writeNoException();
                            reply.writeInt(connectionPolicy ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg15 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            int _result4 = getConnectionPolicy(_arg06, _arg15);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 8:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg16 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            setVolume(_arg011, _arg16);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg17 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            volumeUp(_arg09, _arg17);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg18 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            volumeDown(_arg09, _arg18);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            mute(_arg07);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IBluetoothVolumeControl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            unmute(_arg08);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothVolumeControl {
            public static IBluetoothVolumeControl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothVolumeControl.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public boolean connect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public boolean disconnect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnect(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedDevices(attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    _data.writeIntArray(states);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(states, attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public int getConnectionState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(connectionPolicy);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConnectionPolicy(device, connectionPolicy, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionPolicy(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public void setVolume(int volume, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    _data.writeInt(volume);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVolume(volume, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public void volumeUp(boolean unmute, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    _data.writeInt(unmute ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().volumeUp(unmute, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public void volumeDown(boolean unmute, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    _data.writeInt(unmute ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().volumeDown(unmute, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public void mute(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().mute(attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothVolumeControl
            public void unmute(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothVolumeControl.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unmute(attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothVolumeControl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothVolumeControl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
