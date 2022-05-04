package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
/* loaded from: classes.dex */
public class ConfigurationServerModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "ConfigurationServerModel";

    public ConfigurationServerModel(BluetoothMesh meshInst) {
        super(meshInst, 3);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setConfigMessageHeader(int src, int dst, int ttl, int netKeyIndex, int msgOpCode) {
        Log.d(TAG, "setConfigMessageHeader");
        super.setConfigMessageHeader(src, dst, ttl, netKeyIndex, msgOpCode);
    }
}
