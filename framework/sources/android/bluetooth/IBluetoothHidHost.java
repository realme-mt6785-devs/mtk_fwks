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
public interface IBluetoothHidHost extends IInterface {
    boolean connect(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr, AttributionSource attributionSource) throws RemoteException;

    boolean getIdleTime(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean getProtocolMode(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean getReport(BluetoothDevice bluetoothDevice, byte b, byte b2, int i, AttributionSource attributionSource) throws RemoteException;

    boolean sendData(BluetoothDevice bluetoothDevice, String str, AttributionSource attributionSource) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean setIdleTime(BluetoothDevice bluetoothDevice, byte b, AttributionSource attributionSource) throws RemoteException;

    boolean setProtocolMode(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean setReport(BluetoothDevice bluetoothDevice, byte b, String str, AttributionSource attributionSource) throws RemoteException;

    boolean virtualUnplug(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothHidHost {
        @Override // android.bluetooth.IBluetoothHidHost
        public boolean connect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean disconnect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public int getConnectionState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean getProtocolMode(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean virtualUnplug(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean setProtocolMode(BluetoothDevice device, int protocolMode, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean getReport(BluetoothDevice device, byte reportType, byte reportId, int bufferSize, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean setReport(BluetoothDevice device, byte reportType, String report, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean sendData(BluetoothDevice device, String report, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean getIdleTime(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHidHost
        public boolean setIdleTime(BluetoothDevice device, byte idleTime, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothHidHost {
        public static final String DESCRIPTOR = "android.bluetooth.IBluetoothHidHost";
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getConnectedDevices = 3;
        static final int TRANSACTION_getConnectionPolicy = 7;
        static final int TRANSACTION_getConnectionState = 5;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
        static final int TRANSACTION_getIdleTime = 14;
        static final int TRANSACTION_getProtocolMode = 8;
        static final int TRANSACTION_getReport = 11;
        static final int TRANSACTION_sendData = 13;
        static final int TRANSACTION_setConnectionPolicy = 6;
        static final int TRANSACTION_setIdleTime = 15;
        static final int TRANSACTION_setProtocolMode = 10;
        static final int TRANSACTION_setReport = 12;
        static final int TRANSACTION_virtualUnplug = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothHidHost asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothHidHost)) {
                return new Proxy(obj);
            }
            return (IBluetoothHidHost) iin;
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
                    return "getProtocolMode";
                case 9:
                    return "virtualUnplug";
                case 10:
                    return "setProtocolMode";
                case 11:
                    return "getReport";
                case 12:
                    return "setReport";
                case 13:
                    return "sendData";
                case 14:
                    return "getIdleTime";
                case 15:
                    return "setIdleTime";
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
            BluetoothDevice _arg07;
            AttributionSource _arg16;
            BluetoothDevice _arg08;
            AttributionSource _arg17;
            BluetoothDevice _arg09;
            AttributionSource _arg22;
            BluetoothDevice _arg010;
            AttributionSource _arg4;
            BluetoothDevice _arg011;
            AttributionSource _arg3;
            BluetoothDevice _arg012;
            AttributionSource _arg23;
            BluetoothDevice _arg013;
            AttributionSource _arg18;
            BluetoothDevice _arg014;
            AttributionSource _arg24;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
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
                            data.enforceInterface(DESCRIPTOR);
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
                            data.enforceInterface(DESCRIPTOR);
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
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg015 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            List<BluetoothDevice> _result2 = getDevicesMatchingConnectionStates(_arg015, _arg13);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
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
                            data.enforceInterface(DESCRIPTOR);
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
                            data.enforceInterface(DESCRIPTOR);
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
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg16 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            boolean protocolMode = getProtocolMode(_arg07, _arg16);
                            reply.writeNoException();
                            reply.writeInt(protocolMode ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg17 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            boolean virtualUnplug = virtualUnplug(_arg08, _arg17);
                            reply.writeNoException();
                            reply.writeInt(virtualUnplug ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            int _arg110 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            boolean protocolMode2 = setProtocolMode(_arg09, _arg110, _arg22);
                            reply.writeNoException();
                            reply.writeInt(protocolMode2 ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            byte _arg111 = data.readByte();
                            byte _arg25 = data.readByte();
                            int _arg32 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            boolean report = getReport(_arg010, _arg111, _arg25, _arg32, _arg4);
                            reply.writeNoException();
                            reply.writeInt(report ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            byte _arg112 = data.readByte();
                            String _arg26 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            boolean report2 = setReport(_arg011, _arg112, _arg26, _arg3);
                            reply.writeNoException();
                            reply.writeInt(report2 ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg012 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            String _arg113 = data.readString();
                            if (data.readInt() != 0) {
                                _arg23 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            boolean sendData = sendData(_arg012, _arg113, _arg23);
                            reply.writeNoException();
                            reply.writeInt(sendData ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg013 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg18 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            boolean idleTime = getIdleTime(_arg013, _arg18);
                            reply.writeNoException();
                            reply.writeInt(idleTime ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg014 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            byte _arg114 = data.readByte();
                            if (data.readInt() != 0) {
                                _arg24 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            boolean idleTime2 = setIdleTime(_arg014, _arg114, _arg24);
                            reply.writeNoException();
                            reply.writeInt(idleTime2 ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothHidHost {
            public static IBluetoothHidHost sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean connect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean disconnect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public int getConnectionState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean getProtocolMode(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProtocolMode(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean virtualUnplug(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().virtualUnplug(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean setProtocolMode(BluetoothDevice device, int protocolMode, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(protocolMode);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setProtocolMode(device, protocolMode, attributionSource);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean getReport(BluetoothDevice device, byte reportType, byte reportId, int bufferSize, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeByte(reportType);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeByte(reportId);
                    try {
                        _data.writeInt(bufferSize);
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                if (_reply.readInt() == 0) {
                                    _result = false;
                                }
                                _reply.recycle();
                                _data.recycle();
                                return _result;
                            }
                            boolean report = Stub.getDefaultImpl().getReport(device, reportType, reportId, bufferSize, attributionSource);
                            _reply.recycle();
                            _data.recycle();
                            return report;
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean setReport(BluetoothDevice device, byte reportType, String report, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByte(reportType);
                    _data.writeString(report);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setReport(device, reportType, report, attributionSource);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean sendData(BluetoothDevice device, String report, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(report);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendData(device, report, attributionSource);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean getIdleTime(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIdleTime(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothHidHost
            public boolean setIdleTime(BluetoothDevice device, byte idleTime, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByte(idleTime);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIdleTime(device, idleTime, attributionSource);
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
        }

        public static boolean setDefaultImpl(IBluetoothHidHost impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothHidHost getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
