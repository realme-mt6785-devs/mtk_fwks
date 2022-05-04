package com.mediatek.bt.mesh;

import android.util.Log;
/* loaded from: classes.dex */
public abstract class BluetoothMeshCallback {
    private static final String TAG = BluetoothMeshCallback.class.getSimpleName();

    public void onMeshEnabled() {
        Log.d(TAG, "onMeshEnabled:");
    }

    public void onConfigReset() {
        Log.d(TAG, "onConfigReset:");
    }

    public void onFriendShipStatus(int addr, int status, float timeCost) {
        String str = TAG;
        Log.d(str, "onFriendShipStatus + addr " + addr + " status " + status + " timeCost " + timeCost);
    }

    public void onOtaEvent(int eventId, int errorCode, long serialNumber, long firmwareId, long timeEscaped, int nodesNum, int curr_block, int total_block, int curr_chunk, int chunk_mask, int[] nodesStatus) {
        String str = TAG;
        Log.d(str, "onOtaEvent + eventId " + eventId + " errorCode " + errorCode + " nodesNum " + nodesNum + " curr_block " + curr_block + " total_block " + total_block + " curr_chunk " + curr_chunk + " chunk_mask " + chunk_mask);
    }

    public void onAdvReport(int addrType, int[] addr, int rssi, int reportType, int[] data) {
        String str = TAG;
        Log.d(str, "onAdvReport + addrType " + addrType + " rssi " + rssi + " reportType " + reportType);
    }

    public void onProvScanComplete() {
        Log.d(TAG, "onProvScanComplete:");
    }

    public void onScanUnProvDevice(int[] uuid, int oobInfom, int[] uriHash, int rssi) {
        String str = TAG;
        Log.d(str, "onScanUnProvDevice: uuid=" + uuid + " oobInfom=" + oobInfom + " uriHash=" + uriHash + " rssi=" + rssi);
    }

    public void onProvCapabilities(int numberOfElements, int algorithms, int publicKeyType, int staticOOBType, int outputOobSize, int outputOobAction, int inputOobSize, int inputOobAction) {
        String str = TAG;
        Log.d(str, "onProvCapabilities: numberOfElements=" + numberOfElements + " algorithms=" + algorithms + " publicKeyType=" + publicKeyType);
    }

    public void onRequestOobPublicKey() {
        Log.d(TAG, "onRequestOobPublicKey:");
    }

    public void onRequestOobAuthValue(int method, int action, int size) {
        String str = TAG;
        Log.d(str, "onRequestOobAuthValue: method=" + method + " action=" + action + " size=" + size);
    }

    public void onProvShowOobPublicKey(int[] publicKey) {
        String str = TAG;
        Log.d(str, "onProvShowOobPublicKey: publicKey=" + publicKey);
    }

    public void onProvShowOobAuthValue(int[] authValue) {
        String str = TAG;
        Log.d(str, "onProvShowOobAuthValue: authValue=" + authValue);
    }

    public void onProvDone(int address, int[] deviceKey, boolean success, boolean gattBearer) {
        String str = TAG;
        Log.d(str, "onProvDone: address=" + address + " success=" + success + " gattBearer=" + gattBearer);
    }

    public void onKeyRefresh(int netKeyIndex, int phase) {
        String str = TAG;
        Log.d(str, "onKeyRefresh: netKeyIndex=" + netKeyIndex + " phase=" + phase);
    }

    public void onIvUpdate(int ivIndex, int state) {
        String str = TAG;
        Log.d(str, "onIvUpdate: ivIndex=" + ivIndex + " state=" + state);
    }

    public void onSeqChange(int seqNumber) {
        String str = TAG;
        Log.d(str, "onSeqChange: seqNumber=" + seqNumber);
    }

    public void onProvFactor(int type, int[] buf, int bufLen) {
        String str = TAG;
        Log.d(str, "onProvFactor: type=" + type + " bufLen=" + bufLen);
    }

    public void onHeartbeat(int address, int active) {
        String str = TAG;
        Log.d(str, "onHeartbeat: address=" + address + " active=" + active);
    }

    public void onBearerGattStatus(long handle, int status) {
        String str = TAG;
        Log.d(str, "onBearerGattStatus: handle=" + handle + " status=" + status);
    }

    public void onEvtErrorCode(int type) {
        String str = TAG;
        Log.d(str, "onEvtErrorCode: type=" + type);
    }

    public void onOtaMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) {
        String str = TAG;
        Log.d(str, "onOtaMsgHandler: modelHandle=" + modelHandle);
    }
}
