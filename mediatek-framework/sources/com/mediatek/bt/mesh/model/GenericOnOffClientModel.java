package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
/* loaded from: classes.dex */
public class GenericOnOffClientModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "GenericOnOffClientModel";

    public GenericOnOffClientModel(BluetoothMesh meshInst) {
        super(meshInst, 16);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void genericOnOffGet() {
        Log.d(TAG, "genericOnOffGet");
        super.modelSendPacket();
    }

    public void genericOnOffSet(int onOff, int tid, int transitionTime, int delay) {
        Log.d(TAG, "genericOnOffSet");
        super.modelSendPacket(onOff, tid, transitionTime, delay);
    }

    public void genericOnOffSetUnacknowledged(int onOff, int tid, int transitionTime, int delay) {
        Log.d(TAG, "genericOnOffSetUnacknowledged");
        super.modelSendPacket(onOff, tid, transitionTime, delay);
    }
}
