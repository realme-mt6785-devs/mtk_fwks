package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class LightnessClientModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "LightnessClientModel";

    public LightnessClientModel(BluetoothMesh meshInst) {
        super(meshInst, 10);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void lightLightnessGet() {
        super.modelSendPacket();
    }

    public void lightLightnessSet(int lightness, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightLightnessSetUnacknowledged(int lightness, int tid, int transitionTime, int delay) {
        lightLightnessSet(lightness, tid, transitionTime, delay);
    }

    public void lightLightnessLinearGet() {
        super.modelSendPacket();
    }

    public void lightLightnessLinearSet(int lightness, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightLightnessLinearSetUnacknowledged(int lightness, int tid, int transitionTime, int delay) {
        lightLightnessLinearSet(lightness, tid, transitionTime, delay);
    }

    public void lightLightnessLastGet() {
        super.modelSendPacket();
    }

    public void lightLightnessDefaultGet() {
        super.modelSendPacket();
    }

    public void lightLightnessDefaultSet(int lightness) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightLightnessDefaultSetUnacknowledged(int lightness) {
        lightLightnessDefaultSet(lightness);
    }

    public void lightLightnessRangeGet() {
        super.modelSendPacket();
    }

    public void lightLightnessRangeSet(int rangeMin, int rangeMax) {
        int[] params = {super.twoOctetsToArray(rangeMin)[0], super.twoOctetsToArray(rangeMin)[1], super.twoOctetsToArray(rangeMax)[0], super.twoOctetsToArray(rangeMax)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightLightnessRangeSetUnacknowledged(int rangeMin, int rangeMax) {
        lightLightnessRangeSet(rangeMin, rangeMax);
    }
}
