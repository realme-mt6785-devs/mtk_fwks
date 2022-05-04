package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class CtlClientModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "CtlClientModel";

    public CtlClientModel(BluetoothMesh meshInst) {
        super(meshInst, 11);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void lightCtlGet() {
        super.modelSendPacket();
    }

    public void lightCtlSet(int ctlLightness, int ctlTemperature, int ctlDeltaUV, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(ctlLightness)[0], super.twoOctetsToArray(ctlLightness)[1], super.twoOctetsToArray(ctlTemperature)[0], super.twoOctetsToArray(ctlTemperature)[1], super.twoOctetsToArray(ctlDeltaUV)[0], super.twoOctetsToArray(ctlDeltaUV)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightCtlSetUnacknowledged(int ctlLightness, int ctlTemperature, int ctlDeltaUV, int tid, int transitionTime, int delay) {
        lightCtlSet(ctlLightness, ctlTemperature, ctlDeltaUV, tid, transitionTime, delay);
    }

    public void lightCtlTemperatureGet() {
        super.modelSendPacket();
    }

    public void lightCtlTemperatureSet(int ctlTemperature, int ctlDeltaUV, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(ctlTemperature)[0], super.twoOctetsToArray(ctlTemperature)[1], super.twoOctetsToArray(ctlDeltaUV)[0], super.twoOctetsToArray(ctlDeltaUV)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightCtlTemperatureSetUnacknowledged(int ctlTemperature, int ctlDeltaUV, int tid, int transitionTime, int delay) {
        lightCtlTemperatureSet(ctlTemperature, ctlDeltaUV, tid, transitionTime, delay);
    }

    public void lightCtlDefaultGet() {
        super.modelSendPacket();
    }

    public void lightCtlDefaultSet(int lightness, int temperature, int deltaUV) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1], super.twoOctetsToArray(temperature)[0], super.twoOctetsToArray(temperature)[1]};
        params[2] = super.twoOctetsToArray(deltaUV)[0];
        params[3] = super.twoOctetsToArray(deltaUV)[1];
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightCtlDefaultSetUnacknowledged(int lightness, int temperature, int deltaUV) {
        lightCtlDefaultSet(lightness, temperature, deltaUV);
    }

    public void lightCtlTemperatureRangeGet() {
        super.modelSendPacket();
    }

    public void lightCtlTemperatureRangeSet(int rangeMin, int rangeMax) {
        int[] params = {super.twoOctetsToArray(rangeMin)[0], super.twoOctetsToArray(rangeMin)[1], super.twoOctetsToArray(rangeMax)[0], super.twoOctetsToArray(rangeMax)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightCtlTemperatureRangeSetUnacknowledged(int rangeMin, int rangeMax) {
        lightCtlTemperatureRangeSet(rangeMin, rangeMax);
    }
}
