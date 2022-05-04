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
public interface IBluetoothA2dp extends IInterface {
    boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean connectWithAttribution(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    void disableOptionalCodecs(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean disconnectWithAttribution(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    void enableOptionalCodecs(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    BluetoothDevice getActiveDevice(AttributionSource attributionSource) throws RemoteException;

    BufferConstraints getBufferConstraints(AttributionSource attributionSource) throws RemoteException;

    BluetoothCodecStatus getCodecStatus(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices() throws RemoteException;

    List<BluetoothDevice> getConnectedDevicesWithAttribution(AttributionSource attributionSource) throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getConnectionStateWithAttribution(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStatesWithAttribution(int[] iArr, AttributionSource attributionSource) throws RemoteException;

    int getDynamicBufferSupport(AttributionSource attributionSource) throws RemoteException;

    int getOptionalCodecsEnabled(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getPriority(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean isA2dpPlaying(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException;

    boolean setActiveDevice(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    void setAvrcpAbsoluteVolume(int i, AttributionSource attributionSource) throws RemoteException;

    boolean setBufferLengthMillis(int i, int i2, AttributionSource attributionSource) throws RemoteException;

    void setCodecConfigPreference(BluetoothDevice bluetoothDevice, BluetoothCodecConfig bluetoothCodecConfig, AttributionSource attributionSource) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    void setOptionalCodecsEnabled(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    int supportsOptionalCodecs(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothA2dp {
        @Override // android.bluetooth.IBluetoothA2dp
        public boolean connect(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean connectWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean disconnect(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean disconnectWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public List<BluetoothDevice> getConnectedDevicesWithAttribution(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public List<BluetoothDevice> getDevicesMatchingConnectionStatesWithAttribution(int[] states, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int getConnectionState(BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int getConnectionStateWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean setActiveDevice(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public BluetoothDevice getActiveDevice(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int getConnectionPolicy(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public void setAvrcpAbsoluteVolume(int volume, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean isA2dpPlaying(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public BluetoothCodecStatus getCodecStatus(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public void setCodecConfigPreference(BluetoothDevice device, BluetoothCodecConfig codecConfig, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public void enableOptionalCodecs(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public void disableOptionalCodecs(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int supportsOptionalCodecs(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int getOptionalCodecsEnabled(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public void setOptionalCodecsEnabled(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int getDynamicBufferSupport(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public BufferConstraints getBufferConstraints(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public boolean setBufferLengthMillis(int codec, int size, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothA2dp
        public int getPriority(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothA2dp {
        public static final String DESCRIPTOR = "android.bluetooth.IBluetoothA2dp";
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_connectWithAttribution = 2;
        static final int TRANSACTION_disableOptionalCodecs = 21;
        static final int TRANSACTION_disconnect = 3;
        static final int TRANSACTION_disconnectWithAttribution = 4;
        static final int TRANSACTION_enableOptionalCodecs = 20;
        static final int TRANSACTION_getActiveDevice = 12;
        static final int TRANSACTION_getBufferConstraints = 26;
        static final int TRANSACTION_getCodecStatus = 18;
        static final int TRANSACTION_getConnectedDevices = 5;
        static final int TRANSACTION_getConnectedDevicesWithAttribution = 6;
        static final int TRANSACTION_getConnectionPolicy = 14;
        static final int TRANSACTION_getConnectionState = 9;
        static final int TRANSACTION_getConnectionStateWithAttribution = 10;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 7;
        static final int TRANSACTION_getDevicesMatchingConnectionStatesWithAttribution = 8;
        static final int TRANSACTION_getDynamicBufferSupport = 25;
        static final int TRANSACTION_getOptionalCodecsEnabled = 23;
        static final int TRANSACTION_getPriority = 28;
        static final int TRANSACTION_isA2dpPlaying = 17;
        static final int TRANSACTION_isAvrcpAbsoluteVolumeSupported = 15;
        static final int TRANSACTION_setActiveDevice = 11;
        static final int TRANSACTION_setAvrcpAbsoluteVolume = 16;
        static final int TRANSACTION_setBufferLengthMillis = 27;
        static final int TRANSACTION_setCodecConfigPreference = 19;
        static final int TRANSACTION_setConnectionPolicy = 13;
        static final int TRANSACTION_setOptionalCodecsEnabled = 24;
        static final int TRANSACTION_supportsOptionalCodecs = 22;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothA2dp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothA2dp)) {
                return new Proxy(obj);
            }
            return (IBluetoothA2dp) iin;
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
                    return "connectWithAttribution";
                case 3:
                    return MediaMetrics.Value.DISCONNECT;
                case 4:
                    return "disconnectWithAttribution";
                case 5:
                    return "getConnectedDevices";
                case 6:
                    return "getConnectedDevicesWithAttribution";
                case 7:
                    return "getDevicesMatchingConnectionStates";
                case 8:
                    return "getDevicesMatchingConnectionStatesWithAttribution";
                case 9:
                    return "getConnectionState";
                case 10:
                    return "getConnectionStateWithAttribution";
                case 11:
                    return "setActiveDevice";
                case 12:
                    return "getActiveDevice";
                case 13:
                    return "setConnectionPolicy";
                case 14:
                    return "getConnectionPolicy";
                case 15:
                    return "isAvrcpAbsoluteVolumeSupported";
                case 16:
                    return "setAvrcpAbsoluteVolume";
                case 17:
                    return "isA2dpPlaying";
                case 18:
                    return "getCodecStatus";
                case 19:
                    return "setCodecConfigPreference";
                case 20:
                    return "enableOptionalCodecs";
                case 21:
                    return "disableOptionalCodecs";
                case 22:
                    return "supportsOptionalCodecs";
                case 23:
                    return "getOptionalCodecsEnabled";
                case 24:
                    return "setOptionalCodecsEnabled";
                case 25:
                    return "getDynamicBufferSupport";
                case 26:
                    return "getBufferConstraints";
                case 27:
                    return "setBufferLengthMillis";
                case 28:
                    return "getPriority";
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
            AttributionSource _arg1;
            BluetoothDevice _arg03;
            BluetoothDevice _arg04;
            AttributionSource _arg12;
            AttributionSource _arg05;
            AttributionSource _arg13;
            BluetoothDevice _arg06;
            BluetoothDevice _arg07;
            AttributionSource _arg14;
            BluetoothDevice _arg08;
            AttributionSource _arg15;
            AttributionSource _arg09;
            BluetoothDevice _arg010;
            AttributionSource _arg2;
            BluetoothDevice _arg011;
            AttributionSource _arg16;
            AttributionSource _arg17;
            BluetoothDevice _arg012;
            AttributionSource _arg18;
            BluetoothDevice _arg013;
            AttributionSource _arg19;
            BluetoothDevice _arg014;
            BluetoothCodecConfig _arg110;
            AttributionSource _arg22;
            BluetoothDevice _arg015;
            AttributionSource _arg111;
            BluetoothDevice _arg016;
            AttributionSource _arg112;
            BluetoothDevice _arg017;
            AttributionSource _arg113;
            BluetoothDevice _arg018;
            AttributionSource _arg114;
            BluetoothDevice _arg019;
            AttributionSource _arg23;
            AttributionSource _arg020;
            AttributionSource _arg021;
            AttributionSource _arg24;
            BluetoothDevice _arg022;
            AttributionSource _arg115;
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
                            boolean connect = connect(_arg0);
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
                                _arg1 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            boolean connectWithAttribution = connectWithAttribution(_arg02, _arg1);
                            reply.writeNoException();
                            reply.writeInt(connectWithAttribution ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            boolean disconnect = disconnect(_arg03);
                            reply.writeNoException();
                            reply.writeInt(disconnect ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            boolean disconnectWithAttribution = disconnectWithAttribution(_arg04, _arg12);
                            reply.writeNoException();
                            reply.writeInt(disconnectWithAttribution ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            List<BluetoothDevice> _result = getConnectedDevices();
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            List<BluetoothDevice> _result2 = getConnectedDevicesWithAttribution(_arg05);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg023 = data.createIntArray();
                            List<BluetoothDevice> _result3 = getDevicesMatchingConnectionStates(_arg023);
                            reply.writeNoException();
                            reply.writeTypedList(_result3);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg024 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            List<BluetoothDevice> _result4 = getDevicesMatchingConnectionStatesWithAttribution(_arg024, _arg13);
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            int _result5 = getConnectionState(_arg06);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg14 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            int _result6 = getConnectionStateWithAttribution(_arg07, _arg14);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg15 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            boolean activeDevice = setActiveDevice(_arg08, _arg15);
                            reply.writeNoException();
                            reply.writeInt(activeDevice ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            BluetoothDevice _result7 = getActiveDevice(_arg09);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            int _arg116 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean connectionPolicy = setConnectionPolicy(_arg010, _arg116, _arg2);
                            reply.writeNoException();
                            reply.writeInt(connectionPolicy ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg16 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            int _result8 = getConnectionPolicy(_arg011, _arg16);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isAvrcpAbsoluteVolumeSupported = isAvrcpAbsoluteVolumeSupported();
                            reply.writeNoException();
                            reply.writeInt(isAvrcpAbsoluteVolumeSupported ? 1 : 0);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg17 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            setAvrcpAbsoluteVolume(_arg025, _arg17);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg012 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg18 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            boolean isA2dpPlaying = isA2dpPlaying(_arg012, _arg18);
                            reply.writeNoException();
                            reply.writeInt(isA2dpPlaying ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg013 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg19 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            BluetoothCodecStatus _result9 = getCodecStatus(_arg013, _arg19);
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg014 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg110 = BluetoothCodecConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            setCodecConfigPreference(_arg014, _arg110, _arg22);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg015 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg015 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg111 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            enableOptionalCodecs(_arg015, _arg111);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg016 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg016 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg112 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg112 = null;
                            }
                            disableOptionalCodecs(_arg016, _arg112);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg017 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg017 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg113 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg113 = null;
                            }
                            int _result10 = supportsOptionalCodecs(_arg017, _arg113);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg018 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg018 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg114 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg114 = null;
                            }
                            int _result11 = getOptionalCodecsEnabled(_arg018, _arg114);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg019 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg019 = null;
                            }
                            int _arg117 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg23 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            setOptionalCodecsEnabled(_arg019, _arg117, _arg23);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg020 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg020 = null;
                            }
                            int _result12 = getDynamicBufferSupport(_arg020);
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg021 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg021 = null;
                            }
                            BufferConstraints _result13 = getBufferConstraints(_arg021);
                            reply.writeNoException();
                            if (_result13 != null) {
                                reply.writeInt(1);
                                _result13.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            int _arg118 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg24 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            boolean bufferLengthMillis = setBufferLengthMillis(_arg026, _arg118, _arg24);
                            reply.writeNoException();
                            reply.writeInt(bufferLengthMillis ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg022 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg022 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg115 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg115 = null;
                            }
                            int _result14 = getPriority(_arg022, _arg115);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothA2dp {
            public static IBluetoothA2dp sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean connect(BluetoothDevice device) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(device);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean connectWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                        return Stub.getDefaultImpl().connectWithAttribution(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean disconnect(BluetoothDevice device) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnect(device);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean disconnectWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectWithAttribution(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedDevices();
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public List<BluetoothDevice> getConnectedDevicesWithAttribution(AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedDevicesWithAttribution(attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(states);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(states);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public List<BluetoothDevice> getDevicesMatchingConnectionStatesWithAttribution(int[] states, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStatesWithAttribution(states, attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public int getConnectionState(BluetoothDevice device) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(device);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public int getConnectionStateWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionStateWithAttribution(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean setActiveDevice(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public BluetoothDevice getActiveDevice(AttributionSource attributionSource) throws RemoteException {
                BluetoothDevice _result;
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
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveDevice(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = BluetoothDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
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
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
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

            @Override // android.bluetooth.IBluetoothA2dp
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
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAvrcpAbsoluteVolumeSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public void setAvrcpAbsoluteVolume(int volume, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(volume);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAvrcpAbsoluteVolume(volume, attributionSource);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean isA2dpPlaying(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isA2dpPlaying(device, attributionSource);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public BluetoothCodecStatus getCodecStatus(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                BluetoothCodecStatus _result;
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
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCodecStatus(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = BluetoothCodecStatus.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public void setCodecConfigPreference(BluetoothDevice device, BluetoothCodecConfig codecConfig, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (codecConfig != null) {
                        _data.writeInt(1);
                        codecConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCodecConfigPreference(device, codecConfig, attributionSource);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public void enableOptionalCodecs(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
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
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableOptionalCodecs(device, attributionSource);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public void disableOptionalCodecs(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
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
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableOptionalCodecs(device, attributionSource);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public int supportsOptionalCodecs(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportsOptionalCodecs(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public int getOptionalCodecsEnabled(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOptionalCodecsEnabled(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public void setOptionalCodecsEnabled(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(value);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOptionalCodecsEnabled(device, value, attributionSource);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public int getDynamicBufferSupport(AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDynamicBufferSupport(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public BufferConstraints getBufferConstraints(AttributionSource attributionSource) throws RemoteException {
                BufferConstraints _result;
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
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBufferConstraints(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = BufferConstraints.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothA2dp
            public boolean setBufferLengthMillis(int codec, int size, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(codec);
                    _data.writeInt(size);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBufferLengthMillis(codec, size, attributionSource);
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

            @Override // android.bluetooth.IBluetoothA2dp
            public int getPriority(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPriority(device, attributionSource);
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

        public static boolean setDefaultImpl(IBluetoothA2dp impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothA2dp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
