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
public interface IBluetoothLeAudio extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothLeAudio";
    public static final int GROUP_NODE_ADDED = 1;
    public static final int GROUP_NODE_REMOVED = 2;
    public static final int GROUP_STATUS_CONFIGURED = 5;
    public static final int GROUP_STATUS_DESTROYED = 4;
    public static final int GROUP_STATUS_IDLE = 0;
    public static final int GROUP_STATUS_RECONFIGURED = 3;
    public static final int GROUP_STATUS_RELEASING = 6;
    public static final int GROUP_STATUS_STREAMING = 1;
    public static final int GROUP_STATUS_SUSPENDED = 2;
    public static final int LE_AUDIO_GROUP_ID_INVALID = -1;

    boolean connect(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getActiveDevices(AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr, AttributionSource attributionSource) throws RemoteException;

    int getGroupId(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean groupAddNode(int i, BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    void groupDestroy(int i, AttributionSource attributionSource) throws RemoteException;

    void groupLockSet(int i, boolean z, AttributionSource attributionSource) throws RemoteException;

    boolean groupRemoveNode(int i, BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    void groupStart(int i, AttributionSource attributionSource) throws RemoteException;

    void groupStop(int i, AttributionSource attributionSource) throws RemoteException;

    void groupStream(int i, AttributionSource attributionSource) throws RemoteException;

    void groupSuspend(int i, AttributionSource attributionSource) throws RemoteException;

    int nodeGetGroupId(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean setActiveDevice(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    void setVcAbsoluteVolume(int i, AttributionSource attributionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothLeAudio {
        @Override // android.bluetooth.IBluetoothLeAudio
        public boolean connect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public boolean disconnect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public int getConnectionState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public boolean setActiveDevice(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public List<BluetoothDevice> getActiveDevices(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void setVcAbsoluteVolume(int volume, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public boolean groupAddNode(int group_id, BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public boolean groupRemoveNode(int group_id, BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void groupStream(int group_id, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void groupSuspend(int group_id, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void groupStart(int group_id, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void groupStop(int group_id, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void groupDestroy(int group_id, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public void groupLockSet(int group_id, boolean lock, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public int nodeGetGroupId(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothLeAudio
        public int getGroupId(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothLeAudio {
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getActiveDevices = 7;
        static final int TRANSACTION_getConnectedDevices = 3;
        static final int TRANSACTION_getConnectionPolicy = 9;
        static final int TRANSACTION_getConnectionState = 5;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
        static final int TRANSACTION_getGroupId = 20;
        static final int TRANSACTION_groupAddNode = 11;
        static final int TRANSACTION_groupDestroy = 17;
        static final int TRANSACTION_groupLockSet = 18;
        static final int TRANSACTION_groupRemoveNode = 12;
        static final int TRANSACTION_groupStart = 15;
        static final int TRANSACTION_groupStop = 16;
        static final int TRANSACTION_groupStream = 13;
        static final int TRANSACTION_groupSuspend = 14;
        static final int TRANSACTION_nodeGetGroupId = 19;
        static final int TRANSACTION_setActiveDevice = 6;
        static final int TRANSACTION_setConnectionPolicy = 8;
        static final int TRANSACTION_setVcAbsoluteVolume = 10;

        public Stub() {
            attachInterface(this, IBluetoothLeAudio.DESCRIPTOR);
        }

        public static IBluetoothLeAudio asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothLeAudio.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothLeAudio)) {
                return new Proxy(obj);
            }
            return (IBluetoothLeAudio) iin;
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
                    return "setActiveDevice";
                case 7:
                    return "getActiveDevices";
                case 8:
                    return "setConnectionPolicy";
                case 9:
                    return "getConnectionPolicy";
                case 10:
                    return "setVcAbsoluteVolume";
                case 11:
                    return "groupAddNode";
                case 12:
                    return "groupRemoveNode";
                case 13:
                    return "groupStream";
                case 14:
                    return "groupSuspend";
                case 15:
                    return "groupStart";
                case 16:
                    return "groupStop";
                case 17:
                    return "groupDestroy";
                case 18:
                    return "groupLockSet";
                case 19:
                    return "nodeGetGroupId";
                case 20:
                    return "getGroupId";
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
            AttributionSource _arg15;
            AttributionSource _arg06;
            BluetoothDevice _arg07;
            AttributionSource _arg2;
            BluetoothDevice _arg08;
            AttributionSource _arg16;
            AttributionSource _arg17;
            BluetoothDevice _arg18;
            AttributionSource _arg22;
            BluetoothDevice _arg19;
            AttributionSource _arg23;
            AttributionSource _arg110;
            AttributionSource _arg111;
            AttributionSource _arg112;
            AttributionSource _arg113;
            AttributionSource _arg114;
            AttributionSource _arg24;
            BluetoothDevice _arg09;
            AttributionSource _arg115;
            BluetoothDevice _arg010;
            AttributionSource _arg116;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothLeAudio.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
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
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
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
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
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
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int[] _arg011 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            List<BluetoothDevice> _result2 = getDevicesMatchingConnectionStates(_arg011, _arg13);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
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
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg15 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            boolean activeDevice = setActiveDevice(_arg05, _arg15);
                            reply.writeNoException();
                            reply.writeInt(activeDevice ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            List<BluetoothDevice> _result4 = getActiveDevices(_arg06);
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        case 8:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _arg117 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean connectionPolicy = setConnectionPolicy(_arg07, _arg117, _arg2);
                            reply.writeNoException();
                            reply.writeInt(connectionPolicy ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg16 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            int _result5 = getConnectionPolicy(_arg08, _arg16);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg17 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            setVcAbsoluteVolume(_arg012, _arg17);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg18 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            boolean groupAddNode = groupAddNode(_arg013, _arg18, _arg22);
                            reply.writeNoException();
                            reply.writeInt(groupAddNode ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg19 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            boolean groupRemoveNode = groupRemoveNode(_arg014, _arg19, _arg23);
                            reply.writeNoException();
                            reply.writeInt(groupRemoveNode ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg110 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            groupStream(_arg015, _arg110);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg111 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            groupSuspend(_arg016, _arg111);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg112 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg112 = null;
                            }
                            groupStart(_arg017, _arg112);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg113 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg113 = null;
                            }
                            groupStop(_arg018, _arg113);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg019 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg114 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg114 = null;
                            }
                            groupDestroy(_arg019, _arg114);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            int _arg020 = data.readInt();
                            boolean _arg118 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg24 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            groupLockSet(_arg020, _arg118, _arg24);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg115 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg115 = null;
                            }
                            int _result6 = nodeGetGroupId(_arg09, _arg115);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 20:
                            data.enforceInterface(IBluetoothLeAudio.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg116 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg116 = null;
                            }
                            int _result7 = getGroupId(_arg010, _arg116);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothLeAudio {
            public static IBluetoothLeAudio sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothLeAudio.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public boolean connect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public boolean disconnect(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public List<BluetoothDevice> getConnectedDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public int getConnectionState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public boolean setActiveDevice(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setActiveDevice(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public List<BluetoothDevice> getActiveDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveDevices(attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public void setVcAbsoluteVolume(int volume, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(volume);
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
                        Stub.getDefaultImpl().setVcAbsoluteVolume(volume, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public boolean groupAddNode(int group_id, BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
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
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().groupAddNode(group_id, device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public boolean groupRemoveNode(int group_id, BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
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
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().groupRemoveNode(group_id, device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothLeAudio
            public void groupStream(int group_id, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().groupStream(group_id, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public void groupSuspend(int group_id, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().groupSuspend(group_id, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public void groupStart(int group_id, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().groupStart(group_id, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public void groupStop(int group_id, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().groupStop(group_id, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public void groupDestroy(int group_id, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().groupDestroy(group_id, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public void groupLockSet(int group_id, boolean lock, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
                    _data.writeInt(group_id);
                    _data.writeInt(lock ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().groupLockSet(group_id, lock, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public int nodeGetGroupId(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nodeGetGroupId(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothLeAudio
            public int getGroupId(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothLeAudio.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGroupId(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothLeAudio impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothLeAudio getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
