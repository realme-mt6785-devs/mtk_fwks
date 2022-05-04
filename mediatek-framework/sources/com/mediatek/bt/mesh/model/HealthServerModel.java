package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class HealthServerModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "HealthServerModel";
    private int attentionTimerState;
    private int currentFaultState;
    private int healthPeriodState;
    private int registeredFaultState;

    public HealthServerModel(BluetoothMesh meshInst) {
        super(meshInst, 5);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void healthCurrentStatus(int testId, int companyId, int[] faultArray) {
        int[] params = new int[faultArray.length + 2];
        params[0] = super.twoOctetsToArray(companyId)[0];
        params[1] = super.twoOctetsToArray(companyId)[1];
        for (int i = 0; i < faultArray.length; i++) {
            params[i + 2] = faultArray[i];
        }
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void healthFaultStatus(int testId, int companyId, int[] faultArray) {
        int[] params = new int[faultArray.length + 2];
        params[0] = super.twoOctetsToArray(companyId)[0];
        params[1] = super.twoOctetsToArray(companyId)[1];
        for (int i = 0; i < faultArray.length; i++) {
            params[i + 2] = faultArray[i];
        }
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void healthPeriodStatus(int fastPeriodDevisor) {
        super.modelSendPacket(fastPeriodDevisor);
    }

    public void healthAttentionStatus(int attention) {
        super.modelSendPacket(attention);
    }

    public void setCurrentFaultState(int state) {
        this.currentFaultState = state;
    }

    public void setRegisteredFaultState(int state) {
        this.registeredFaultState = state;
    }

    public void setHealthPeriodState(int state) {
        this.healthPeriodState = state;
    }

    public void setAttentionTimerState(int state) {
        this.attentionTimerState = state;
    }

    public int getCurrentFaultState() {
        return this.currentFaultState;
    }

    public int getRegisteredFaultState() {
        return this.registeredFaultState;
    }

    public int getHealthPeriodState() {
        return this.healthPeriodState;
    }

    public int getAttentionTimerState() {
        return this.attentionTimerState;
    }
}
