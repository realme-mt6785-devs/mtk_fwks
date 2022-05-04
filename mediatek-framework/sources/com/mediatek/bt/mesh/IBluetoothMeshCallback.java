package com.mediatek.bt.mesh;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBluetoothMeshCallback extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.bt.mesh.IBluetoothMeshCallback";

    void onAdvReport(int i, int[] iArr, int i2, int i3, int[] iArr2) throws RemoteException;

    void onBearerGattStatus(long j, int i) throws RemoteException;

    void onConfigReset() throws RemoteException;

    void onEvtErrorCode(int i) throws RemoteException;

    void onFriendShipStatus(int i, int i2, float f) throws RemoteException;

    void onHeartbeat(int i, int i2) throws RemoteException;

    void onIvUpdate(int i, int i2) throws RemoteException;

    void onKeyRefresh(int i, int i2) throws RemoteException;

    void onMeshEnabled() throws RemoteException;

    void onMsgHandler(int i, BluetoothMeshAccessRxMessage bluetoothMeshAccessRxMessage) throws RemoteException;

    void onOtaEvent(int i, int i2, long j, long j2, long j3, int i3, int i4, int i5, int i6, int i7, int[] iArr) throws RemoteException;

    void onOtaMsgHandler(int i, BluetoothMeshAccessRxMessage bluetoothMeshAccessRxMessage) throws RemoteException;

    void onProvCapabilities(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws RemoteException;

    void onProvDone(int i, int[] iArr, boolean z, boolean z2) throws RemoteException;

    void onProvFactor(int i, int[] iArr, int i2) throws RemoteException;

    void onProvScanComplete() throws RemoteException;

    void onProvShowOobAuthValue(int[] iArr) throws RemoteException;

    void onProvShowOobPublicKey(int[] iArr) throws RemoteException;

    void onPublishTimeoutCallback(int i) throws RemoteException;

    void onRequestOobAuthValue(int i, int i2, int i3) throws RemoteException;

    void onRequestOobPublicKey() throws RemoteException;

    void onScanUnProvDevice(int[] iArr, int i, int[] iArr2, int i2) throws RemoteException;

    void onSeqChange(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothMeshCallback {
        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onMeshEnabled() throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onConfigReset() throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onFriendShipStatus(int addr, int status, float timeCost) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onOtaEvent(int event_id, int error_code, long serial_number, long firmware_id, long time_escaped, int nodes_num, int curr_block, int total_block, int curr_chunk, int chunk_mask, int[] nodes_status) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onAdvReport(int addr_type, int[] addr, int rssi, int report_type, int[] data) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvScanComplete() throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onScanUnProvDevice(int[] uuid, int oobInfom, int[] uriHash, int rssi) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvCapabilities(int numberOfElements, int algorithms, int publicKeyType, int staticOOBType, int outputOobSize, int outputOobAction, int inputOobSize, int inputOobAction) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onRequestOobPublicKey() throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onRequestOobAuthValue(int method, int action, int size) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvShowOobPublicKey(int[] publicKey) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvShowOobAuthValue(int[] authValue) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvDone(int address, int[] deviceKey, boolean success, boolean gatt_bearer) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onKeyRefresh(int netKeyIndex, int phase) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onIvUpdate(int ivIndex, int state) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onSeqChange(int seqNumber) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvFactor(int type, int[] buf, int bufLen) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onHeartbeat(int address, int active) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onBearerGattStatus(long handle, int status) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onEvtErrorCode(int type) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onOtaMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onPublishTimeoutCallback(int modelHandle) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothMeshCallback {
        static final int TRANSACTION_onAdvReport = 5;
        static final int TRANSACTION_onBearerGattStatus = 19;
        static final int TRANSACTION_onConfigReset = 2;
        static final int TRANSACTION_onEvtErrorCode = 20;
        static final int TRANSACTION_onFriendShipStatus = 3;
        static final int TRANSACTION_onHeartbeat = 18;
        static final int TRANSACTION_onIvUpdate = 15;
        static final int TRANSACTION_onKeyRefresh = 14;
        static final int TRANSACTION_onMeshEnabled = 1;
        static final int TRANSACTION_onMsgHandler = 22;
        static final int TRANSACTION_onOtaEvent = 4;
        static final int TRANSACTION_onOtaMsgHandler = 21;
        static final int TRANSACTION_onProvCapabilities = 8;
        static final int TRANSACTION_onProvDone = 13;
        static final int TRANSACTION_onProvFactor = 17;
        static final int TRANSACTION_onProvScanComplete = 6;
        static final int TRANSACTION_onProvShowOobAuthValue = 12;
        static final int TRANSACTION_onProvShowOobPublicKey = 11;
        static final int TRANSACTION_onPublishTimeoutCallback = 23;
        static final int TRANSACTION_onRequestOobAuthValue = 10;
        static final int TRANSACTION_onRequestOobPublicKey = 9;
        static final int TRANSACTION_onScanUnProvDevice = 7;
        static final int TRANSACTION_onSeqChange = 16;

        public Stub() {
            attachInterface(this, IBluetoothMeshCallback.DESCRIPTOR);
        }

        public static IBluetoothMeshCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothMeshCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothMeshCallback)) {
                return new Proxy(obj);
            }
            return (IBluetoothMeshCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BluetoothMeshAccessRxMessage _arg1;
            BluetoothMeshAccessRxMessage _arg12;
            switch (code) {
                case 1598968902:
                    reply.writeString(IBluetoothMeshCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            onMeshEnabled();
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            onConfigReset();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg13 = data.readInt();
                            float _arg2 = data.readFloat();
                            onFriendShipStatus(_arg0, _arg13, _arg2);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg14 = data.readInt();
                            long _arg22 = data.readLong();
                            long _arg3 = data.readLong();
                            long _arg4 = data.readLong();
                            int _arg5 = data.readInt();
                            int _arg6 = data.readInt();
                            int _arg7 = data.readInt();
                            int _arg8 = data.readInt();
                            int _arg9 = data.readInt();
                            int[] _arg10 = data.createIntArray();
                            onOtaEvent(_arg02, _arg14, _arg22, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int[] _arg15 = data.createIntArray();
                            int _arg23 = data.readInt();
                            int _arg32 = data.readInt();
                            int[] _arg42 = data.createIntArray();
                            onAdvReport(_arg03, _arg15, _arg23, _arg32, _arg42);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            onProvScanComplete();
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int[] _arg04 = data.createIntArray();
                            int _arg16 = data.readInt();
                            int[] _arg24 = data.createIntArray();
                            int _arg33 = data.readInt();
                            onScanUnProvDevice(_arg04, _arg16, _arg24, _arg33);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg17 = data.readInt();
                            int _arg25 = data.readInt();
                            int _arg34 = data.readInt();
                            int _arg43 = data.readInt();
                            int _arg52 = data.readInt();
                            int _arg62 = data.readInt();
                            int _arg72 = data.readInt();
                            onProvCapabilities(_arg05, _arg17, _arg25, _arg34, _arg43, _arg52, _arg62, _arg72);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            onRequestOobPublicKey();
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg18 = data.readInt();
                            int _arg26 = data.readInt();
                            onRequestOobAuthValue(_arg06, _arg18, _arg26);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int[] _arg07 = data.createIntArray();
                            onProvShowOobPublicKey(_arg07);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int[] _arg08 = data.createIntArray();
                            onProvShowOobAuthValue(_arg08);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            int[] _arg19 = data.createIntArray();
                            boolean _arg35 = false;
                            boolean _arg27 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg35 = true;
                            }
                            onProvDone(_arg09, _arg19, _arg27, _arg35);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg110 = data.readInt();
                            onKeyRefresh(_arg010, _arg110);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg111 = data.readInt();
                            onIvUpdate(_arg011, _arg111);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            onSeqChange(_arg012);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int[] _arg112 = data.createIntArray();
                            int _arg28 = data.readInt();
                            onProvFactor(_arg013, _arg112, _arg28);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onHeartbeat /* 18 */:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int _arg113 = data.readInt();
                            onHeartbeat(_arg014, _arg113);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onBearerGattStatus /* 19 */:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            long _arg015 = data.readLong();
                            int _arg114 = data.readInt();
                            onBearerGattStatus(_arg015, _arg114);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            onEvtErrorCode(_arg016);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onOtaMsgHandler /* 21 */:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = BluetoothMeshAccessRxMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onOtaMsgHandler(_arg017, _arg1);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = BluetoothMeshAccessRxMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onMsgHandler(_arg018, _arg12);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(IBluetoothMeshCallback.DESCRIPTOR);
                            int _arg019 = data.readInt();
                            onPublishTimeoutCallback(_arg019);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothMeshCallback {
            public static IBluetoothMeshCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothMeshCallback.DESCRIPTOR;
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onMeshEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onMeshEnabled();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onConfigReset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onConfigReset();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onFriendShipStatus(int addr, int status, float timeCost) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(addr);
                    _data.writeInt(status);
                    _data.writeFloat(timeCost);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onFriendShipStatus(addr, status, timeCost);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onOtaEvent(int event_id, int error_code, long serial_number, long firmware_id, long time_escaped, int nodes_num, int curr_block, int total_block, int curr_chunk, int chunk_mask, int[] nodes_status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(event_id);
                    _data.writeInt(error_code);
                    _data.writeLong(serial_number);
                    _data.writeLong(firmware_id);
                    _data.writeLong(time_escaped);
                    _data.writeInt(nodes_num);
                    _data.writeInt(curr_block);
                    _data.writeInt(total_block);
                    _data.writeInt(curr_chunk);
                    _data.writeInt(chunk_mask);
                    _data.writeIntArray(nodes_status);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onOtaEvent(event_id, error_code, serial_number, firmware_id, time_escaped, nodes_num, curr_block, total_block, curr_chunk, chunk_mask, nodes_status);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onAdvReport(int addr_type, int[] addr, int rssi, int report_type, int[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(addr_type);
                    _data.writeIntArray(addr);
                    _data.writeInt(rssi);
                    _data.writeInt(report_type);
                    _data.writeIntArray(data);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onAdvReport(addr_type, addr, rssi, report_type, data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onProvScanComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onProvScanComplete();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onScanUnProvDevice(int[] uuid, int oobInfom, int[] uriHash, int rssi) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeIntArray(uuid);
                    _data.writeInt(oobInfom);
                    _data.writeIntArray(uriHash);
                    _data.writeInt(rssi);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onScanUnProvDevice(uuid, oobInfom, uriHash, rssi);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onProvCapabilities(int numberOfElements, int algorithms, int publicKeyType, int staticOOBType, int outputOobSize, int outputOobAction, int inputOobSize, int inputOobAction) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    try {
                        _data.writeInt(numberOfElements);
                        try {
                            _data.writeInt(algorithms);
                            try {
                                _data.writeInt(publicKeyType);
                                try {
                                    _data.writeInt(staticOOBType);
                                    _data.writeInt(outputOobSize);
                                    _data.writeInt(outputOobAction);
                                    _data.writeInt(inputOobSize);
                                    _data.writeInt(inputOobAction);
                                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().onProvCapabilities(numberOfElements, algorithms, publicKeyType, staticOOBType, outputOobSize, outputOobAction, inputOobSize, inputOobAction);
                                    _reply.recycle();
                                    _data.recycle();
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onRequestOobPublicKey() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onRequestOobPublicKey();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onRequestOobAuthValue(int method, int action, int size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(method);
                    _data.writeInt(action);
                    _data.writeInt(size);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onRequestOobAuthValue(method, action, size);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onProvShowOobPublicKey(int[] publicKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeIntArray(publicKey);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onProvShowOobPublicKey(publicKey);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onProvShowOobAuthValue(int[] authValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeIntArray(authValue);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onProvShowOobAuthValue(authValue);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onProvDone(int address, int[] deviceKey, boolean success, boolean gatt_bearer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(address);
                    _data.writeIntArray(deviceKey);
                    int i = 1;
                    _data.writeInt(success ? 1 : 0);
                    if (!gatt_bearer) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onProvDone(address, deviceKey, success, gatt_bearer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onKeyRefresh(int netKeyIndex, int phase) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(netKeyIndex);
                    _data.writeInt(phase);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onKeyRefresh(netKeyIndex, phase);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onIvUpdate(int ivIndex, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(ivIndex);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onIvUpdate(ivIndex, state);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onSeqChange(int seqNumber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(seqNumber);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onSeqChange(seqNumber);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onProvFactor(int type, int[] buf, int bufLen) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeIntArray(buf);
                    _data.writeInt(bufLen);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onProvFactor(type, buf, bufLen);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onHeartbeat(int address, int active) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(address);
                    _data.writeInt(active);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onHeartbeat, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onHeartbeat(address, active);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onBearerGattStatus(long handle, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onBearerGattStatus, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onBearerGattStatus(handle, status);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onEvtErrorCode(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onEvtErrorCode(type);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onOtaMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    if (msg != null) {
                        _data.writeInt(1);
                        msg.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onOtaMsgHandler, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onOtaMsgHandler(modelHandle, msg);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    if (msg != null) {
                        _data.writeInt(1);
                        msg.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onMsgHandler(modelHandle, msg);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
            public void onPublishTimeoutCallback(int modelHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMeshCallback.DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onPublishTimeoutCallback(modelHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMeshCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothMeshCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
