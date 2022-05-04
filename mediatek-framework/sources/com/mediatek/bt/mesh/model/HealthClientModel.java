package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class HealthClientModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "HealthClientModel";

    public HealthClientModel(BluetoothMesh meshInst) {
        super(meshInst, 6);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void healthFaultGet(int companyId) {
        int[] params = {super.twoOctetsToArray(companyId)[0], super.twoOctetsToArray(companyId)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void healthFaultClear(int companyId) {
        int[] params = {super.twoOctetsToArray(companyId)[0], super.twoOctetsToArray(companyId)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void healthFaultClearUnacknowledged(int companyId) {
        healthFaultClear(companyId);
    }

    public void healthFaultTest(int testId, int companyId) {
        int[] params = {testId, super.twoOctetsToArray(companyId)[0], super.twoOctetsToArray(companyId)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void healthFaultTestUnacknowledged(int testId, int companyId) {
        healthFaultTest(testId, companyId);
    }

    public void healthPeriodGet() {
        super.modelSendPacket();
    }

    public void healthPeriodSet(int fastPeriodDivisor) {
        super.modelSendPacket(fastPeriodDivisor);
    }

    public void healthPeriodSetUnacknowledged(int fastPeriodDivisor) {
        healthPeriodSet(fastPeriodDivisor);
    }

    public void healthAttentionGet() {
        super.modelSendPacket();
    }

    public void healthAttentionSet(int attention) {
        super.modelSendPacket(attention);
    }

    public void healthAttentionSetUnacknowledged(int attention) {
        healthAttentionSet(attention);
    }
}
