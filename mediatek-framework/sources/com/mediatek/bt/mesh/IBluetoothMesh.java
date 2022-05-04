package com.mediatek.bt.mesh;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.bt.mesh.IBluetoothMeshCallback;
/* loaded from: classes.dex */
public interface IBluetoothMesh extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.bt.mesh.IBluetoothMesh";

    int accessModelReply(int i, BluetoothMeshAccessRxMessage bluetoothMeshAccessRxMessage, BluetoothMeshAccessTxMessage bluetoothMeshAccessTxMessage) throws RemoteException;

    int addDevKey(int i, int[] iArr, int[] iArr2) throws RemoteException;

    int addElement(int i) throws RemoteException;

    int addModel(MeshModel meshModel) throws RemoteException;

    int bearerAdvSetParams(long j, int i, int i2, int i3, long j2, int i4, int i5) throws RemoteException;

    int delDevKey(int i) throws RemoteException;

    int disable() throws RemoteException;

    int disableMeshFilter() throws RemoteException;

    int enable(MeshInitParams meshInitParams) throws RemoteException;

    int enableMeshFilter(boolean z, boolean z2, boolean z3, boolean z4) throws RemoteException;

    int enableMeshFilterWithNetwork(boolean z, boolean z2, int[] iArr, boolean z3, boolean z4, int[] iArr2) throws RemoteException;

    int gattConnect(String str, int i, int i2) throws RemoteException;

    int gattDisconnect() throws RemoteException;

    int getDefaultTtl() throws RemoteException;

    int[] getDevKey(int i) throws RemoteException;

    int getElementAddr(int i) throws RemoteException;

    int getMeshRole() throws RemoteException;

    boolean getMeshState() throws RemoteException;

    int getModelHandle(long j, int i) throws RemoteException;

    String getVersion() throws RemoteException;

    int inviteProvisioning(int[] iArr, int i) throws RemoteException;

    int modelAppBind(int i, int i2) throws RemoteException;

    int[] otaGetClientModelHandle() throws RemoteException;

    int otaInitiatorOperation(OtaOperationParams otaOperationParams) throws RemoteException;

    int publishModel(int i, int i2, int i3, int[] iArr) throws RemoteException;

    void registerCallback(IBluetoothMeshCallback iBluetoothMeshCallback) throws RemoteException;

    boolean resetData(int i) throws RemoteException;

    boolean saveData() throws RemoteException;

    int sendConfigMessage(int i, int i2, int i3, int i4, int i5, ConfigMessageParams configMessageParams) throws RemoteException;

    int sendPacket(int i, int i2, int i3, int i4, int i5, int[] iArr) throws RemoteException;

    int setAppkey(int i, int[] iArr, int i2, int i3) throws RemoteException;

    boolean setCompositionDataHeader(int[] iArr) throws RemoteException;

    void setData() throws RemoteException;

    void setDefaultTtl(int i) throws RemoteException;

    boolean setElementAddr(int i) throws RemoteException;

    int setHeartbeatPeriod(int i, long j) throws RemoteException;

    int setIv(long j, int i) throws RemoteException;

    void setLogLevel(long j) throws RemoteException;

    int setMeshMode(int i) throws RemoteException;

    int setNetkey(int i, int[] iArr, int i2) throws RemoteException;

    int setProvisionFactor(int i, int[] iArr) throws RemoteException;

    int setScanParams(int i, int i2) throws RemoteException;

    int setSpecialPktParams(boolean z, int i, int i2, int i3) throws RemoteException;

    int startProvisioning(int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, long j, int i7, int i8, int i9) throws RemoteException;

    void unProvisionScan(boolean z, int i) throws RemoteException;

    void unregisterCallback() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothMesh {
        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public void registerCallback(IBluetoothMeshCallback callback) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public void unregisterCallback() throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int getMeshRole() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public boolean getMeshState() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int enable(MeshInitParams params) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int disable() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public boolean setCompositionDataHeader(int[] data) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int addElement(int location) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public boolean setElementAddr(int addr) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int addModel(MeshModel model) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setNetkey(int op, int[] key, int netIndex) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setAppkey(int op, int[] key, int netIndex, int appIndex) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public void unProvisionScan(boolean start, int duration) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int inviteProvisioning(int[] UUID, int attentionDuration) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int startProvisioning(int algorithm, int publicKey, int authMethod, int authAction, int authSize, int[] netkey, int netkeyIndex, long ivIndex, int addr, int flags, int mode) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setProvisionFactor(int type, int[] buf) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int sendConfigMessage(int dst, int src, int ttl, int netKeyIndex, int opcode, ConfigMessageParams param) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int sendPacket(int dst, int src, int ttl, int netKeyIndex, int appKeyIndex, int[] payload) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int publishModel(int modelHandle, int opCode, int companyId, int[] buffer) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setMeshMode(int on) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public boolean resetData(int sector) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public boolean saveData() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public void setData() throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public String getVersion() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int getElementAddr(int elementIndex) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public void setDefaultTtl(int value) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int getDefaultTtl() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setIv(long ivIndex, int ivPhase) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int addDevKey(int unicastAddr, int[] devicekey, int[] uuid) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int[] getDevKey(int unicastAddr) throws RemoteException {
            return null;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int delDevKey(int unicastAddr) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int modelAppBind(int handle, int appIndex) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int accessModelReply(int modelHandle, BluetoothMeshAccessRxMessage msg, BluetoothMeshAccessTxMessage reply) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public void setLogLevel(long level) throws RemoteException {
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int getModelHandle(long modelId, int elementIndex) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int gattConnect(String address, int addressType, int serviceType) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int gattDisconnect() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setHeartbeatPeriod(int num, long hbTimeout) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int[] otaGetClientModelHandle() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int otaInitiatorOperation(OtaOperationParams params) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int bearerAdvSetParams(long advPeriod, int minInterval, int maxInterval, int resend, long scanPeriod, int scanInterval, int scanWindow) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setScanParams(int scanInterval, int scanWindow) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int setSpecialPktParams(boolean isSnIncrease, int snIncreaseInterval, int advInterval, int advPeriod) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int enableMeshFilter(boolean pbAdv, boolean meshMessage, boolean unprovBeacon, boolean secureBeacon) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int enableMeshFilterWithNetwork(boolean pbAdv, boolean meshMessageWithNID, int[] meshMessageNetIndex, boolean unprovBeacon, boolean secureBeaconWithNetworkID, int[] secureBeaconNetIndex) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMesh
        public int disableMeshFilter() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothMesh {
        static final int TRANSACTION_accessModelReply = 33;
        static final int TRANSACTION_addDevKey = 29;
        static final int TRANSACTION_addElement = 8;
        static final int TRANSACTION_addModel = 10;
        static final int TRANSACTION_bearerAdvSetParams = 41;
        static final int TRANSACTION_delDevKey = 31;
        static final int TRANSACTION_disable = 6;
        static final int TRANSACTION_disableMeshFilter = 46;
        static final int TRANSACTION_enable = 5;
        static final int TRANSACTION_enableMeshFilter = 44;
        static final int TRANSACTION_enableMeshFilterWithNetwork = 45;
        static final int TRANSACTION_gattConnect = 36;
        static final int TRANSACTION_gattDisconnect = 37;
        static final int TRANSACTION_getDefaultTtl = 27;
        static final int TRANSACTION_getDevKey = 30;
        static final int TRANSACTION_getElementAddr = 25;
        static final int TRANSACTION_getMeshRole = 3;
        static final int TRANSACTION_getMeshState = 4;
        static final int TRANSACTION_getModelHandle = 35;
        static final int TRANSACTION_getVersion = 24;
        static final int TRANSACTION_inviteProvisioning = 14;
        static final int TRANSACTION_modelAppBind = 32;
        static final int TRANSACTION_otaGetClientModelHandle = 39;
        static final int TRANSACTION_otaInitiatorOperation = 40;
        static final int TRANSACTION_publishModel = 19;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_resetData = 21;
        static final int TRANSACTION_saveData = 22;
        static final int TRANSACTION_sendConfigMessage = 17;
        static final int TRANSACTION_sendPacket = 18;
        static final int TRANSACTION_setAppkey = 12;
        static final int TRANSACTION_setCompositionDataHeader = 7;
        static final int TRANSACTION_setData = 23;
        static final int TRANSACTION_setDefaultTtl = 26;
        static final int TRANSACTION_setElementAddr = 9;
        static final int TRANSACTION_setHeartbeatPeriod = 38;
        static final int TRANSACTION_setIv = 28;
        static final int TRANSACTION_setLogLevel = 34;
        static final int TRANSACTION_setMeshMode = 20;
        static final int TRANSACTION_setNetkey = 11;
        static final int TRANSACTION_setProvisionFactor = 16;
        static final int TRANSACTION_setScanParams = 42;
        static final int TRANSACTION_setSpecialPktParams = 43;
        static final int TRANSACTION_startProvisioning = 15;
        static final int TRANSACTION_unProvisionScan = 13;
        static final int TRANSACTION_unregisterCallback = 2;

        public Stub() {
            attachInterface(this, IBluetoothMesh.DESCRIPTOR);
        }

        public static IBluetoothMesh asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothMesh.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothMesh)) {
                return new Proxy(obj);
            }
            return (IBluetoothMesh) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            MeshInitParams _arg0;
            MeshModel _arg02;
            ConfigMessageParams _arg5;
            BluetoothMeshAccessRxMessage _arg1;
            BluetoothMeshAccessTxMessage _arg2;
            OtaOperationParams _arg03;
            boolean _arg04;
            boolean _arg12;
            boolean _arg22;
            boolean _arg05;
            boolean _arg13;
            boolean _arg3;
            boolean _arg4;
            switch (code) {
                case 1598968902:
                    reply.writeString(IBluetoothMesh.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg32 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            IBluetoothMeshCallback _arg06 = IBluetoothMeshCallback.Stub.asInterface(data.readStrongBinder());
                            registerCallback(_arg06);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            unregisterCallback();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _result = getMeshRole();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            boolean meshState = getMeshState();
                            reply.writeNoException();
                            reply.writeInt(meshState ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = MeshInitParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _result2 = enable(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _result3 = disable();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int[] _arg07 = data.createIntArray();
                            boolean compositionDataHeader = setCompositionDataHeader(_arg07);
                            reply.writeNoException();
                            reply.writeInt(compositionDataHeader ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _result4 = addElement(_arg08);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 9:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            boolean elementAddr = setElementAddr(_arg09);
                            reply.writeNoException();
                            reply.writeInt(elementAddr ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = MeshModel.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _result5 = addModel(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 11:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int[] _arg14 = data.createIntArray();
                            int _arg23 = data.readInt();
                            int _result6 = setNetkey(_arg010, _arg14, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 12:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int[] _arg15 = data.createIntArray();
                            int _arg24 = data.readInt();
                            int _result7 = setAppkey(_arg011, _arg15, _arg24, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 13:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg32 = true;
                            }
                            int _arg16 = data.readInt();
                            unProvisionScan(_arg32, _arg16);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int[] _arg012 = data.createIntArray();
                            int _arg17 = data.readInt();
                            int _result8 = inviteProvisioning(_arg012, _arg17);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 15:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _arg18 = data.readInt();
                            int _arg25 = data.readInt();
                            int _arg33 = data.readInt();
                            int _arg42 = data.readInt();
                            int[] _arg52 = data.createIntArray();
                            int _arg6 = data.readInt();
                            long _arg7 = data.readLong();
                            int _arg8 = data.readInt();
                            int _arg9 = data.readInt();
                            int _arg10 = data.readInt();
                            int _result9 = startProvisioning(_arg013, _arg18, _arg25, _arg33, _arg42, _arg52, _arg6, _arg7, _arg8, _arg9, _arg10);
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 16:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int[] _arg19 = data.createIntArray();
                            int _result10 = setProvisionFactor(_arg014, _arg19);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 17:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _arg110 = data.readInt();
                            int _arg26 = data.readInt();
                            int _arg34 = data.readInt();
                            int _arg43 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg5 = ConfigMessageParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            int _result11 = sendConfigMessage(_arg015, _arg110, _arg26, _arg34, _arg43, _arg5);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case TRANSACTION_sendPacket /* 18 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            int _arg111 = data.readInt();
                            int _arg27 = data.readInt();
                            int _arg35 = data.readInt();
                            int _arg44 = data.readInt();
                            int[] _arg53 = data.createIntArray();
                            int _result12 = sendPacket(_arg016, _arg111, _arg27, _arg35, _arg44, _arg53);
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case TRANSACTION_publishModel /* 19 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            int _arg112 = data.readInt();
                            int _arg28 = data.readInt();
                            int _result13 = publishModel(_arg017, _arg112, _arg28, data.createIntArray());
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 20:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            int _result14 = setMeshMode(_arg018);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case TRANSACTION_resetData /* 21 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg019 = data.readInt();
                            boolean resetData = resetData(_arg019);
                            reply.writeNoException();
                            reply.writeInt(resetData ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            boolean saveData = saveData();
                            reply.writeNoException();
                            reply.writeInt(saveData ? 1 : 0);
                            return true;
                        case 23:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            setData();
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            String _result15 = getVersion();
                            reply.writeNoException();
                            reply.writeString(_result15);
                            return true;
                        case 25:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg020 = data.readInt();
                            int _result16 = getElementAddr(_arg020);
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 26:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg021 = data.readInt();
                            setDefaultTtl(_arg021);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getDefaultTtl /* 27 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _result17 = getDefaultTtl();
                            reply.writeNoException();
                            reply.writeInt(_result17);
                            return true;
                        case TRANSACTION_setIv /* 28 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            long _arg022 = data.readLong();
                            int _arg113 = data.readInt();
                            int _result18 = setIv(_arg022, _arg113);
                            reply.writeNoException();
                            reply.writeInt(_result18);
                            return true;
                        case TRANSACTION_addDevKey /* 29 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg023 = data.readInt();
                            int[] _arg114 = data.createIntArray();
                            int[] _arg29 = data.createIntArray();
                            int _result19 = addDevKey(_arg023, _arg114, _arg29);
                            reply.writeNoException();
                            reply.writeInt(_result19);
                            return true;
                        case TRANSACTION_getDevKey /* 30 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg024 = data.readInt();
                            int[] _result20 = getDevKey(_arg024);
                            reply.writeNoException();
                            reply.writeIntArray(_result20);
                            return true;
                        case TRANSACTION_delDevKey /* 31 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg025 = data.readInt();
                            int _result21 = delDevKey(_arg025);
                            reply.writeNoException();
                            reply.writeInt(_result21);
                            return true;
                        case 32:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg026 = data.readInt();
                            int _arg115 = data.readInt();
                            int _result22 = modelAppBind(_arg026, _arg115);
                            reply.writeNoException();
                            reply.writeInt(_result22);
                            return true;
                        case TRANSACTION_accessModelReply /* 33 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg027 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = BluetoothMeshAccessRxMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = BluetoothMeshAccessTxMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _result23 = accessModelReply(_arg027, _arg1, _arg2);
                            reply.writeNoException();
                            reply.writeInt(_result23);
                            return true;
                        case TRANSACTION_setLogLevel /* 34 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            long _arg028 = data.readLong();
                            setLogLevel(_arg028);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getModelHandle /* 35 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            long _arg029 = data.readLong();
                            int _arg116 = data.readInt();
                            int _result24 = getModelHandle(_arg029, _arg116);
                            reply.writeNoException();
                            reply.writeInt(_result24);
                            return true;
                        case TRANSACTION_gattConnect /* 36 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            String _arg030 = data.readString();
                            int _arg117 = data.readInt();
                            int _arg210 = data.readInt();
                            int _result25 = gattConnect(_arg030, _arg117, _arg210);
                            reply.writeNoException();
                            reply.writeInt(_result25);
                            return true;
                        case TRANSACTION_gattDisconnect /* 37 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _result26 = gattDisconnect();
                            reply.writeNoException();
                            reply.writeInt(_result26);
                            return true;
                        case TRANSACTION_setHeartbeatPeriod /* 38 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg031 = data.readInt();
                            long _arg118 = data.readLong();
                            int _result27 = setHeartbeatPeriod(_arg031, _arg118);
                            reply.writeNoException();
                            reply.writeInt(_result27);
                            return true;
                        case TRANSACTION_otaGetClientModelHandle /* 39 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int[] _result28 = otaGetClientModelHandle();
                            reply.writeNoException();
                            reply.writeIntArray(_result28);
                            return true;
                        case 40:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = OtaOperationParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _result29 = otaInitiatorOperation(_arg03);
                            reply.writeNoException();
                            reply.writeInt(_result29);
                            return true;
                        case TRANSACTION_bearerAdvSetParams /* 41 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            long _arg032 = data.readLong();
                            int _arg119 = data.readInt();
                            int _arg211 = data.readInt();
                            int _arg36 = data.readInt();
                            long _arg45 = data.readLong();
                            int _arg54 = data.readInt();
                            int _arg62 = data.readInt();
                            int _result30 = bearerAdvSetParams(_arg032, _arg119, _arg211, _arg36, _arg45, _arg54, _arg62);
                            reply.writeNoException();
                            reply.writeInt(_result30);
                            return true;
                        case TRANSACTION_setScanParams /* 42 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _arg033 = data.readInt();
                            int _arg120 = data.readInt();
                            int _result31 = setScanParams(_arg033, _arg120);
                            reply.writeNoException();
                            reply.writeInt(_result31);
                            return true;
                        case TRANSACTION_setSpecialPktParams /* 43 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg32 = true;
                            }
                            int _arg121 = data.readInt();
                            int _arg212 = data.readInt();
                            int _result32 = setSpecialPktParams(_arg32, _arg121, _arg212, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result32);
                            return true;
                        case TRANSACTION_enableMeshFilter /* 44 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            } else {
                                _arg04 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            } else {
                                _arg12 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = true;
                            } else {
                                _arg22 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = true;
                            }
                            int _result33 = enableMeshFilter(_arg04, _arg12, _arg22, _arg32);
                            reply.writeNoException();
                            reply.writeInt(_result33);
                            return true;
                        case TRANSACTION_enableMeshFilterWithNetwork /* 45 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = true;
                            } else {
                                _arg05 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = true;
                            } else {
                                _arg13 = false;
                            }
                            int[] _arg213 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            int[] _arg55 = data.createIntArray();
                            int _result34 = enableMeshFilterWithNetwork(_arg05, _arg13, _arg213, _arg3, _arg4, _arg55);
                            reply.writeNoException();
                            reply.writeInt(_result34);
                            return true;
                        case TRANSACTION_disableMeshFilter /* 46 */:
                            data.enforceInterface(IBluetoothMesh.DESCRIPTOR);
                            int _result35 = disableMeshFilter();
                            reply.writeNoException();
                            reply.writeInt(_result35);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothMesh {
            public static IBluetoothMesh sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothMesh.DESCRIPTOR;
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public void registerCallback(IBluetoothMeshCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public void unregisterCallback() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int getMeshRole() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMeshRole();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public boolean getMeshState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMeshState();
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

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int enable(MeshInitParams params) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enable(params);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int disable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disable();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public boolean setCompositionDataHeader(int[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeIntArray(data);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCompositionDataHeader(data);
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

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int addElement(int location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(location);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addElement(location);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public boolean setElementAddr(int addr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(addr);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setElementAddr(addr);
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

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int addModel(MeshModel model) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    if (model != null) {
                        _data.writeInt(1);
                        model.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addModel(model);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setNetkey(int op, int[] key, int netIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeIntArray(key);
                    _data.writeInt(netIndex);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNetkey(op, key, netIndex);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setAppkey(int op, int[] key, int netIndex, int appIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeIntArray(key);
                    _data.writeInt(netIndex);
                    _data.writeInt(appIndex);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAppkey(op, key, netIndex, appIndex);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public void unProvisionScan(boolean start, int duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(start ? 1 : 0);
                    _data.writeInt(duration);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unProvisionScan(start, duration);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int inviteProvisioning(int[] UUID, int attentionDuration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeIntArray(UUID);
                    _data.writeInt(attentionDuration);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().inviteProvisioning(UUID, attentionDuration);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int startProvisioning(int algorithm, int publicKey, int authMethod, int authAction, int authSize, int[] netkey, int netkeyIndex, long ivIndex, int addr, int flags, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(algorithm);
                    _data.writeInt(publicKey);
                    _data.writeInt(authMethod);
                    _data.writeInt(authAction);
                    _data.writeInt(authSize);
                    _data.writeIntArray(netkey);
                    _data.writeInt(netkeyIndex);
                    _data.writeLong(ivIndex);
                    _data.writeInt(addr);
                    _data.writeInt(flags);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startProvisioning(algorithm, publicKey, authMethod, authAction, authSize, netkey, netkeyIndex, ivIndex, addr, flags, mode);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setProvisionFactor(int type, int[] buf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeIntArray(buf);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setProvisionFactor(type, buf);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int sendConfigMessage(int dst, int src, int ttl, int netKeyIndex, int opcode, ConfigMessageParams param) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    try {
                        _data.writeInt(dst);
                        try {
                            _data.writeInt(src);
                            try {
                                _data.writeInt(ttl);
                                try {
                                    _data.writeInt(netKeyIndex);
                                    try {
                                        _data.writeInt(opcode);
                                        if (param != null) {
                                            _data.writeInt(1);
                                            param.writeToParcel(_data, 0);
                                        } else {
                                            _data.writeInt(0);
                                        }
                                        boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            int _result = _reply.readInt();
                                            _reply.recycle();
                                            _data.recycle();
                                            return _result;
                                        }
                                        int sendConfigMessage = Stub.getDefaultImpl().sendConfigMessage(dst, src, ttl, netKeyIndex, opcode, param);
                                        _reply.recycle();
                                        _data.recycle();
                                        return sendConfigMessage;
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int sendPacket(int dst, int src, int ttl, int netKeyIndex, int appKeyIndex, int[] payload) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    try {
                        _data.writeInt(dst);
                        try {
                            _data.writeInt(src);
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
                }
                try {
                    _data.writeInt(ttl);
                    try {
                        _data.writeInt(netKeyIndex);
                        try {
                            _data.writeInt(appKeyIndex);
                            try {
                                _data.writeIntArray(payload);
                                boolean _status = this.mRemote.transact(Stub.TRANSACTION_sendPacket, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    int _result = _reply.readInt();
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                int sendPacket = Stub.getDefaultImpl().sendPacket(dst, src, ttl, netKeyIndex, appKeyIndex, payload);
                                _reply.recycle();
                                _data.recycle();
                                return sendPacket;
                            } catch (Throwable th5) {
                                th = th5;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int publishModel(int modelHandle, int opCode, int companyId, int[] buffer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    _data.writeInt(opCode);
                    _data.writeInt(companyId);
                    _data.writeIntArray(buffer);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_publishModel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().publishModel(modelHandle, opCode, companyId, buffer);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setMeshMode(int on) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(on);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMeshMode(on);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public boolean resetData(int sector) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(sector);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_resetData, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetData(sector);
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

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public boolean saveData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().saveData();
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

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public void setData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setData();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public String getVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVersion();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int getElementAddr(int elementIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(elementIndex);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElementAddr(elementIndex);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public void setDefaultTtl(int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDefaultTtl(value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int getDefaultTtl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDefaultTtl, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultTtl();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setIv(long ivIndex, int ivPhase) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeLong(ivIndex);
                    _data.writeInt(ivPhase);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setIv, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIv(ivIndex, ivPhase);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int addDevKey(int unicastAddr, int[] devicekey, int[] uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(unicastAddr);
                    _data.writeIntArray(devicekey);
                    _data.writeIntArray(uuid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_addDevKey, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addDevKey(unicastAddr, devicekey, uuid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int[] getDevKey(int unicastAddr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(unicastAddr);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDevKey, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevKey(unicastAddr);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int delDevKey(int unicastAddr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(unicastAddr);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_delDevKey, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().delDevKey(unicastAddr);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int modelAppBind(int handle, int appIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(handle);
                    _data.writeInt(appIndex);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().modelAppBind(handle, appIndex);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int accessModelReply(int modelHandle, BluetoothMeshAccessRxMessage msg, BluetoothMeshAccessTxMessage reply) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    if (msg != null) {
                        _data.writeInt(1);
                        msg.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (reply != null) {
                        _data.writeInt(1);
                        reply.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_accessModelReply, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().accessModelReply(modelHandle, msg, reply);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public void setLogLevel(long level) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeLong(level);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setLogLevel, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLogLevel(level);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int getModelHandle(long modelId, int elementIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeLong(modelId);
                    _data.writeInt(elementIndex);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getModelHandle, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModelHandle(modelId, elementIndex);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int gattConnect(String address, int addressType, int serviceType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(addressType);
                    _data.writeInt(serviceType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_gattConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().gattConnect(address, addressType, serviceType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int gattDisconnect() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_gattDisconnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().gattDisconnect();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setHeartbeatPeriod(int num, long hbTimeout) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(num);
                    _data.writeLong(hbTimeout);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setHeartbeatPeriod, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setHeartbeatPeriod(num, hbTimeout);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int[] otaGetClientModelHandle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_otaGetClientModelHandle, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().otaGetClientModelHandle();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int otaInitiatorOperation(OtaOperationParams params) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().otaInitiatorOperation(params);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int bearerAdvSetParams(long advPeriod, int minInterval, int maxInterval, int resend, long scanPeriod, int scanInterval, int scanWindow) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    try {
                        _data.writeLong(advPeriod);
                        try {
                            _data.writeInt(minInterval);
                            _data.writeInt(maxInterval);
                            _data.writeInt(resend);
                            _data.writeLong(scanPeriod);
                            _data.writeInt(scanInterval);
                            _data.writeInt(scanWindow);
                            boolean _status = this.mRemote.transact(Stub.TRANSACTION_bearerAdvSetParams, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                int _result = _reply.readInt();
                                _reply.recycle();
                                _data.recycle();
                                return _result;
                            }
                            int bearerAdvSetParams = Stub.getDefaultImpl().bearerAdvSetParams(advPeriod, minInterval, maxInterval, resend, scanPeriod, scanInterval, scanWindow);
                            _reply.recycle();
                            _data.recycle();
                            return bearerAdvSetParams;
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
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setScanParams(int scanInterval, int scanWindow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(scanInterval);
                    _data.writeInt(scanWindow);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setScanParams, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setScanParams(scanInterval, scanWindow);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int setSpecialPktParams(boolean isSnIncrease, int snIncreaseInterval, int advInterval, int advPeriod) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    _data.writeInt(isSnIncrease ? 1 : 0);
                    _data.writeInt(snIncreaseInterval);
                    _data.writeInt(advInterval);
                    _data.writeInt(advPeriod);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setSpecialPktParams, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSpecialPktParams(isSnIncrease, snIncreaseInterval, advInterval, advPeriod);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int enableMeshFilter(boolean pbAdv, boolean meshMessage, boolean unprovBeacon, boolean secureBeacon) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(pbAdv ? 1 : 0);
                    _data.writeInt(meshMessage ? 1 : 0);
                    _data.writeInt(unprovBeacon ? 1 : 0);
                    if (!secureBeacon) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_enableMeshFilter, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableMeshFilter(pbAdv, meshMessage, unprovBeacon, secureBeacon);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int enableMeshFilterWithNetwork(boolean pbAdv, boolean meshMessageWithNID, int[] meshMessageNetIndex, boolean unprovBeacon, boolean secureBeaconWithNetworkID, int[] secureBeaconNetIndex) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(pbAdv ? 1 : 0);
                    _data.writeInt(meshMessageWithNID ? 1 : 0);
                    try {
                        _data.writeIntArray(meshMessageNetIndex);
                        _data.writeInt(unprovBeacon ? 1 : 0);
                        if (!secureBeaconWithNetworkID) {
                            i = 0;
                        }
                        _data.writeInt(i);
                        try {
                            _data.writeIntArray(secureBeaconNetIndex);
                            try {
                                boolean _status = this.mRemote.transact(Stub.TRANSACTION_enableMeshFilterWithNetwork, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    int _result = _reply.readInt();
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                int enableMeshFilterWithNetwork = Stub.getDefaultImpl().enableMeshFilterWithNetwork(pbAdv, meshMessageWithNID, meshMessageNetIndex, unprovBeacon, secureBeaconWithNetworkID, secureBeaconNetIndex);
                                _reply.recycle();
                                _data.recycle();
                                return enableMeshFilterWithNetwork;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // com.mediatek.bt.mesh.IBluetoothMesh
            public int disableMeshFilter() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMesh.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_disableMeshFilter, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableMeshFilter();
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

        public static boolean setDefaultImpl(IBluetoothMesh impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothMesh getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
