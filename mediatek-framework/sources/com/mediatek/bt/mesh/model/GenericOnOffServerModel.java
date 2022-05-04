package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
/* loaded from: classes.dex */
public class GenericOnOffServerModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "GenericOnOffServerModel";
    private int genericOnOffState;

    public GenericOnOffServerModel(BluetoothMesh meshInst) {
        super(meshInst, 7);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void genericOnOffStatus(int presentOnOff, int targetOnOff, int remainingTime) {
        Log.d(TAG, "genericOnOffStatus");
        super.modelSendPacket(presentOnOff, targetOnOff, remainingTime);
    }

    public void setGenericOnOffState(int state) {
        Log.d(TAG, "setGenericOnOffState state " + state);
        this.genericOnOffState = state;
    }

    public int getGenericOnOffState() {
        Log.d(TAG, "getGenericOnOffState state " + this.genericOnOffState);
        return this.genericOnOffState;
    }
}
