package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
/* loaded from: classes.dex */
public class GenericLevelServerModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "GenericLevelServerModel";

    public GenericLevelServerModel(BluetoothMesh meshInst) {
        super(meshInst, 14);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }
}
