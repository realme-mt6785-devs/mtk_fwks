package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshConstants;
import com.mediatek.bt.mesh.MeshModel;
/* loaded from: classes.dex */
public class VendorModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "VendorModel";

    public VendorModel(BluetoothMesh meshInst) {
        super(meshInst, MeshConstants.MESH_MODEL_DATA_OP_ADD_MODEL);
    }

    public void setModelParams(int[] opcodes, int companyID, int opcodeCount) {
        super.setVendorMsgOpcodes(opcodes);
        super.setCompanyId(companyID);
        super.setOpcodeCount(opcodeCount);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void vendorModelSendPacket(int[] params) {
        Log.d(TAG, "vendorModelSendPacket");
        super.modelSendPacket(params);
    }
}
