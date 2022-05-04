package com.mediatek.bt.mesh;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfileConnector;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.mediatek.bt.mesh.IBluetoothMesh;
import com.mediatek.bt.mesh.IBluetoothMeshCallback;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class BluetoothMesh implements BluetoothProfile {
    private static final boolean DBG = true;
    private static final String TAG = "BluetoothMesh";
    private static final boolean VDBG = true;
    private static volatile BluetoothMesh sInstance;
    private BluetoothMeshCallback mCallback;
    private final BluetoothProfileConnector<IBluetoothMesh> mProfileConnector;
    private Map<Integer, MeshModel> mModelMap = new HashMap();
    private final IBluetoothMeshCallback mBluetoothMeshCallback = new IBluetoothMeshCallback.Stub() { // from class: com.mediatek.bt.mesh.BluetoothMesh.2
        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onMeshEnabled() {
            Log.d(BluetoothMesh.TAG, "onMeshEnabled");
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onMeshEnabled();
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onConfigReset() {
            Log.d(BluetoothMesh.TAG, "onConfigReset");
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onConfigReset();
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onFriendShipStatus(int addr, int status, float timeCost) {
            Log.d(BluetoothMesh.TAG, "onFriendShipStatus addr=" + addr + ", status=" + status + " , timeCost=" + timeCost);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onFriendShipStatus(addr, status, timeCost);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onOtaEvent(int eventId, int errorCode, long serialNumber, long firmwareId, long timeEscaped, int nodesNum, int curr_block, int total_block, int curr_chunk, int chunk_mask, int[] nodesStatus) {
            Log.d(BluetoothMesh.TAG, "onOtaEvent eventId=" + eventId + ", errorCode=" + errorCode + ", nodesNum=" + nodesNum + ",curr_block=" + curr_block + ",total_block=" + total_block + ",curr_chunk=" + curr_chunk + ",chunk_mask =" + chunk_mask);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onOtaEvent(eventId, errorCode, serialNumber, firmwareId, timeEscaped, nodesNum, curr_block, total_block, curr_chunk, chunk_mask, nodesStatus);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onAdvReport(int addrType, int[] addr, int rssi, int reportType, int[] data) {
            Log.d(BluetoothMesh.TAG, "onAdvReport addrType=" + addrType + ", addr=" + Arrays.toString(addr) + ", rssi=" + rssi + ", reportType=" + reportType + ", data=" + Arrays.toString(addr));
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onAdvReport(addrType, addr, rssi, reportType, data);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvScanComplete() {
            Log.d(BluetoothMesh.TAG, "onProvScanComplete");
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onProvScanComplete();
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onScanUnProvDevice(int[] uuid, int oobInfom, int[] uriHash, int rssi) {
            Log.d(BluetoothMesh.TAG, "onScanUnProvDevice uuid=" + Arrays.toString(uuid) + ", oobInfom=" + oobInfom + ", uriHash=" + Arrays.toString(uriHash));
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onScanUnProvDevice(uuid, oobInfom, uriHash, rssi);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvCapabilities(int numberOfElements, int algorithms, int publicKeyType, int staticOOBType, int outputOobSize, int outputOobAction, int inputOobSize, int inputOobAction) {
            Log.d(BluetoothMesh.TAG, "onProvCapabilities numberOfElements=" + numberOfElements + ", algorithms=" + algorithms + ", publicKeyType=" + publicKeyType + ", staticOOBType=" + staticOOBType + ", outputOobSize=" + outputOobSize + ", outputOobAction=" + outputOobAction + ", inputOobSize=" + inputOobSize + ", inputOobAction=" + inputOobAction);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onProvCapabilities(numberOfElements, algorithms, publicKeyType, staticOOBType, outputOobSize, outputOobAction, inputOobSize, inputOobAction);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onRequestOobPublicKey() {
            Log.d(BluetoothMesh.TAG, "onRequestOobPublicKey");
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onRequestOobPublicKey();
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onRequestOobAuthValue(int method, int action, int size) {
            Log.d(BluetoothMesh.TAG, "onRequestOobAuthValue method=" + method + ", action=" + action + ", size=" + size);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onRequestOobAuthValue(method, action, size);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvShowOobPublicKey(int[] publicKey) {
            Log.d(BluetoothMesh.TAG, "onProvShowOobPublicKey publicKey" + Arrays.toString(publicKey));
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onProvShowOobPublicKey(publicKey);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvShowOobAuthValue(int[] authValue) {
            Log.d(BluetoothMesh.TAG, "onProvShowOobAuthValue authValue =" + Arrays.toString(authValue));
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onProvShowOobAuthValue(authValue);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvDone(int address, int[] deviceKey, boolean success, boolean gattBearer) {
            Log.d(BluetoothMesh.TAG, "onProvDone address=" + address + ", deviceKey=" + Arrays.toString(deviceKey) + ", success=" + success + ", gattBearer=" + gattBearer);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onProvDone(address, deviceKey, success, gattBearer);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onKeyRefresh(int netKeyIndex, int phase) {
            Log.d(BluetoothMesh.TAG, "onKeyRefresh netKeyIndex=" + netKeyIndex + ", phase=" + phase);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onKeyRefresh(netKeyIndex, phase);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onIvUpdate(int ivIndex, int state) {
            Log.d(BluetoothMesh.TAG, "onIvUpdate ivIndex=" + ivIndex + ", state=" + state);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onIvUpdate(ivIndex, state);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onSeqChange(int seqNumber) {
            Log.d(BluetoothMesh.TAG, "onSeqChange seqNumber=" + seqNumber);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onSeqChange(seqNumber);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onProvFactor(int type, int[] buf, int bufLen) {
            Log.d(BluetoothMesh.TAG, "onProvFactor type=" + type + ", buf[0]=" + buf[0]);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onProvFactor(type, buf, bufLen);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onHeartbeat(int address, int active) {
            Log.d(BluetoothMesh.TAG, "onHeartbeat address=" + address + ", active=" + active);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onHeartbeat(address, active);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onBearerGattStatus(long handle, int status) {
            Log.d(BluetoothMesh.TAG, "onBearerGattStatus: handle=" + handle + " status=" + status);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onBearerGattStatus(handle, status);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onEvtErrorCode(int type) {
            Log.d(BluetoothMesh.TAG, "onEvtErrorCode: type=" + type);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onEvtErrorCode(type);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onOtaMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) {
            Log.d(BluetoothMesh.TAG, "onOtaMsgHandler" + modelHandle);
            if (BluetoothMesh.this.mCallback != null) {
                BluetoothMesh.this.mCallback.onOtaMsgHandler(modelHandle, msg);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) {
            Log.d(BluetoothMesh.TAG, "onMsgHandler" + modelHandle);
            MeshModel mModel = (MeshModel) BluetoothMesh.this.mModelMap.get(Integer.valueOf(modelHandle));
            if (mModel != null) {
                mModel.onMsgHandler(modelHandle, msg);
            }
        }

        @Override // com.mediatek.bt.mesh.IBluetoothMeshCallback
        public void onPublishTimeoutCallback(int modelHandle) {
            Log.d(BluetoothMesh.TAG, "onPublishTimeoutCallback" + modelHandle);
            MeshModel mModel = (MeshModel) BluetoothMesh.this.mModelMap.get(Integer.valueOf(modelHandle));
            if (mModel != null) {
                mModel.onPublishTimeoutCallback(modelHandle);
            }
        }
    };
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    BluetoothMesh(Context context, BluetoothProfile.ServiceListener listener) {
        BluetoothProfileConnector<IBluetoothMesh> bluetoothProfileConnector = new BluetoothProfileConnector(this, 25, TAG, IBluetoothMesh.class.getName()) { // from class: com.mediatek.bt.mesh.BluetoothMesh.1
            public IBluetoothMesh getServiceInterface(IBinder service) {
                return IBluetoothMesh.Stub.asInterface(Binder.allowBlocking(service));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        bluetoothProfileConnector.connect(context, listener);
    }

    public static BluetoothMesh getDefaultMesh(Context context, BluetoothProfile.ServiceListener listener) {
        if (context == null || listener == null) {
            return null;
        }
        if (sInstance == null) {
            sInstance = new BluetoothMesh(context, listener);
        }
        return sInstance;
    }

    public void close() {
        Log.d(TAG, "close()");
        this.mProfileConnector.disconnect();
        sInstance = null;
    }

    private IBluetoothMesh getService() {
        return (IBluetoothMesh) this.mProfileConnector.getService();
    }

    public boolean registerCallback(BluetoothMeshCallback callback) {
        Log.d(TAG, "registerCallback()");
        IBluetoothMesh service = getService();
        if (service == null) {
            return false;
        }
        this.mCallback = callback;
        try {
            service.registerCallback(this.mBluetoothMeshCallback);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean unregisterCallback() {
        Log.d(TAG, "unregisterCallback()");
        IBluetoothMesh service = getService();
        if (service == null) {
            return false;
        }
        this.mCallback = null;
        try {
            service.unregisterCallback();
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        return null;
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        return null;
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice device) {
        return 0;
    }

    public int getMeshRole() {
        Log.d(TAG, "getMeshRole");
        try {
            IBluetoothMesh service = getService();
            if (service == null || !isBluetoothEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return -1;
            }
            int role = service.getMeshRole();
            Log.d(TAG, "getMeshRole role=" + role);
            return role;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public boolean getMeshState() {
        Log.d(TAG, "getMeshState");
        try {
            IBluetoothMesh service = getService();
            if (service == null || !isBluetoothEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return false;
            }
            boolean state = service.getMeshState();
            Log.d(TAG, "getMeshState state=" + state);
            return state;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public int enable(MeshInitParams params) {
        Log.d(TAG, "enable, role=" + params.getRole());
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.enable(params);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int disable() {
        Log.d(TAG, "disable");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.disable();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public boolean setCompositionDataHeader(int[] data) {
        Log.d(TAG, "setCompositionDataHeader");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setCompositionDataHeader(data);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public int addElement(int location) {
        Log.d(TAG, "addElement");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.addElement(location);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public boolean setElementAddr(int addr) {
        Log.d(TAG, "setElementAddr");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setElementAddr(addr);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public int addModel(MeshModel model) {
        Log.d(TAG, "addModel modelopcode=0x" + Integer.toHexString(model.getModelOpcode()) + ",elementIndex=0x" + Integer.toHexString(model.getElementIndex()));
        try {
            IBluetoothMesh service = getService();
            if (service == null || !isBluetoothEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return -1;
            }
            int modelHandle = service.addModel(model);
            if (modelHandle > -1) {
                this.mModelMap.put(Integer.valueOf(modelHandle), model);
            }
            Log.d(TAG, "addModel modelHandle=0x" + Integer.toHexString(modelHandle));
            return modelHandle;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setNetkey(int op, int[] key, int netIndex) {
        Log.d(TAG, "setNetkey");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setNetkey(op, key, netIndex);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setAppkey(int op, int[] key, int netIndex, int appIndex) {
        Log.d(TAG, "setAppkey");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setAppkey(op, key, netIndex, appIndex);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public void unProvisionScan(boolean start, int duration) {
        Log.d(TAG, "unProvisionScan start=" + start);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                service.unProvisionScan(start, duration);
            } else if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    public int inviteProvisioning(int[] UUID, int attentionDuration) {
        Log.d(TAG, "inviteProvisioning with UUID " + Arrays.toString(UUID));
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.inviteProvisioning(UUID, attentionDuration);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int startProvisioning(int algorithm, int publicKey, int authMethod, int authAction, int authSize, int[] netkey, int netkeyIndex, long ivIndex, int addr, int flags, int mode) {
        Log.d(TAG, "startProvisioning");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.startProvisioning(algorithm, publicKey, authMethod, authAction, authSize, netkey, netkeyIndex, ivIndex, addr, flags, mode);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setProvisionFactor(int type, int[] buf) {
        Log.d(TAG, "setProvisionFactor type=" + type);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setProvisionFactor(type, buf);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int sendConfigMessage(int dst, int src, int ttl, int netKeyIndex, int opcode, ConfigMessageParams param) {
        Log.d(TAG, "sendConfigMessage");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.sendConfigMessage(dst, src, ttl, netKeyIndex, opcode, param);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int sendPacket(int dst, int src, int ttl, int netKeyIndex, int appKeyIndex, int[] payload) {
        Log.d(TAG, "sendPacket");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.sendPacket(dst, src, ttl, netKeyIndex, appKeyIndex, payload);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int publishModel(int modelHandle, int opCode, int companyId, int[] buffer) {
        Log.d(TAG, "publishModel modelHandle=0x" + Integer.toHexString(modelHandle));
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.publishModel(modelHandle, opCode, companyId, buffer);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setMeshMode(int mode) {
        Log.d(TAG, "setMeshMode mode=" + mode);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setMeshMode(mode);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public boolean resetData(int sector) {
        Log.d(TAG, "resetData, sector=0x" + Integer.toHexString(sector));
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.resetData(sector);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean saveData() {
        Log.d(TAG, "saveData");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.saveData();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public void setData() {
    }

    public String getVersion() {
        Log.d(TAG, "getVersion");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.getVersion();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return null;
        }
    }

    public int getElementAddr(int elementIndex) {
        Log.d(TAG, "getElementAddr by elementIndex " + elementIndex);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.getElementAddr(elementIndex);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public void setDefaultTtl(int ttl) {
        Log.d(TAG, "setDefaultTtl ttl=" + ttl);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                service.setDefaultTtl(ttl);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    public int getDefaultTtl() {
        Log.d(TAG, "getDefaultTtl");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.getDefaultTtl();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setIv(long ivIndex, int ivPhase) {
        Log.d(TAG, "setIv");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setIv(ivIndex, ivPhase);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int addDevKey(int unicastAddr, int[] devicekey, int[] uuid) {
        Log.d(TAG, "addDevKey");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.addDevKey(unicastAddr, devicekey, uuid);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int[] getDevKey(int unicastAddr) {
        Log.d(TAG, "getDevKey");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.getDevKey(unicastAddr);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return null;
        }
    }

    public int delDevKey(int unicastAddr) {
        Log.d(TAG, "delDevKey");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.delDevKey(unicastAddr);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int modelAppBind(int handle, int appIndex) {
        Log.d(TAG, "setModelAppBind");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.modelAppBind(handle, appIndex);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int accessModelReply(int handle, BluetoothMeshAccessRxMessage msg, BluetoothMeshAccessTxMessage reply) {
        Log.d(TAG, "accessModelReply");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.accessModelReply(handle, msg, reply);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public void setLogLevel(long level) {
        Log.d(TAG, "setLogLevel");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                service.setLogLevel(level);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    public int getModelHandle(long modelId, int elementIndex) {
        Log.d(TAG, "getModelHandle");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.getModelHandle(modelId, elementIndex);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int gattConnect(String address, int addressType, int serviceType) {
        Log.d(TAG, "gattConnect");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.gattConnect(address, addressType, serviceType);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int gattDisconnect() {
        Log.d(TAG, "gattDisconnect");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.gattDisconnect();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setHeartbeatPeriod(int num, long hbTimeout) {
        Log.d(TAG, "setHeartbeatPeriod");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setHeartbeatPeriod(num, hbTimeout);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int[] otaGetClientModelHandle() {
        Log.d(TAG, "otaGetClientModelHandle");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.otaGetClientModelHandle();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return null;
        }
    }

    public int otaInitiatorOperation(OtaOperationParams params) {
        Log.d(TAG, "otaInitiatorOperation");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.otaInitiatorOperation(params);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    public int bearerAdvSetParams(long advPeriod, int minInterval, int maxInterval, int resend, long scanPeriod, int scanInterval, int scanWindow) {
        StringBuilder sb = new StringBuilder();
        sb.append("bearerAdvSetParams:advPeriod=");
        sb.append(advPeriod);
        sb.append(", minInterval=");
        String str = minInterval;
        sb.append((int) str);
        sb.append(", maxInterval=");
        sb.append(maxInterval);
        sb.append(", resend=");
        sb.append(resend);
        sb.append(", scanPeriod=");
        sb.append(scanPeriod);
        sb.append(", scanInterval=");
        sb.append(scanInterval);
        sb.append(", scanWindow=");
        sb.append(scanWindow);
        Log.d(TAG, sb.toString());
        try {
            IBluetoothMesh service = getService();
            try {
                if (service != null) {
                    if (isBluetoothEnabled()) {
                        return service.bearerAdvSetParams(advPeriod, minInterval, maxInterval, resend, scanPeriod, scanInterval, scanWindow);
                    }
                }
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return -1;
            } catch (RemoteException e) {
                Log.e(str, "Stack:" + Log.getStackTraceString(new Throwable()));
                return -1;
            }
        } catch (RemoteException e2) {
            str = TAG;
        }
    }

    public int setScanParams(int scanInterval, int scanWindow) {
        Log.d(TAG, "setScanParams: scanInterval=" + scanInterval + ", scanWindow=" + scanWindow);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setScanParams(scanInterval, scanWindow);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int setSpecialPktParams(boolean isSnIncrease, int snIncreaseInterval, int advInterval, int advPeriod) {
        Log.d(TAG, "setSpecialPktParams: isSnIncrease=" + isSnIncrease + ", snIncreaseInterval=" + snIncreaseInterval + ", advInterval=" + advInterval + ",advPeriod=" + advPeriod);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.setSpecialPktParams(isSnIncrease, snIncreaseInterval, advInterval, advPeriod);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int enableMeshFilter(boolean pbAdv, boolean meshMessage, boolean unprovBeacon, boolean secureBeacon) {
        Log.d(TAG, "enableMeshFilter: pbAdv=" + pbAdv + ", meshMessage=" + meshMessage + ", unprovBeacon=" + unprovBeacon + ", secureBeacon=" + secureBeacon);
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.enableMeshFilter(pbAdv, meshMessage, unprovBeacon, secureBeacon);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public int enableMeshFilterWithNetwork(boolean pbAdv, boolean meshMessageWithNID, int[] meshMessageNetIndex, boolean unprovBeacon, boolean secureBeaconWithNetworkID, int[] secureBeaconNetIndex) {
        Log.d(TAG, "enableMeshFilterWithNetwork: pbAdv=" + pbAdv + ", meshMessageWithNID=" + meshMessageWithNID + ", meshMessageNetIndex=" + Arrays.toString(meshMessageNetIndex) + ", unprovBeacon=" + unprovBeacon + ", secureBeaconWithNetworkID=" + secureBeaconWithNetworkID + ", secureBeaconNetIndex=" + Arrays.toString(secureBeaconNetIndex));
        if ((meshMessageNetIndex == null || meshMessageNetIndex.length <= 4) && (secureBeaconNetIndex == null || secureBeaconNetIndex.length <= 4)) {
            try {
                IBluetoothMesh service = getService();
                if (service != null && isBluetoothEnabled()) {
                    return service.enableMeshFilterWithNetwork(pbAdv, meshMessageWithNID, meshMessageNetIndex, unprovBeacon, secureBeaconWithNetworkID, secureBeaconNetIndex);
                }
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return -1;
            } catch (RemoteException e) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return -1;
            }
        } else {
            Log.e(TAG, "enableMeshFilterWithNetwork exceed the supported max network number 4");
            return -2;
        }
    }

    public int disableMeshFilter() {
        Log.d(TAG, "disableMeshFilter");
        try {
            IBluetoothMesh service = getService();
            if (service != null && isBluetoothEnabled()) {
                return service.disableMeshFilter();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    private boolean isBluetoothEnabled() {
        return this.mAdapter.getState() == 12;
    }
}
