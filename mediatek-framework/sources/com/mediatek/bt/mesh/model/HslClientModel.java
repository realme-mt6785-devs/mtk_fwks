package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class HslClientModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "HslClientModel";

    public HslClientModel(BluetoothMesh meshInst) {
        super(meshInst, 13);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void lightHslGet() {
        super.modelSendPacket();
    }

    public void lightHslSet(int hslLightness, int hslHue, int hslSaturation, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(hslLightness)[0], super.twoOctetsToArray(hslLightness)[1], super.twoOctetsToArray(hslHue)[0], super.twoOctetsToArray(hslHue)[1], super.twoOctetsToArray(hslSaturation)[0], super.twoOctetsToArray(hslSaturation)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslSetUnacknowledged(int hslLightness, int hslHue, int hslSaturation, int tid, int transitionTime, int delay) {
        lightHslSet(hslLightness, hslHue, hslSaturation, tid, transitionTime, delay);
    }

    public void lightHslTargetGet() {
        super.modelSendPacket();
    }

    public void lightHslDefaultGet() {
        super.modelSendPacket();
    }

    public void lightHslDefaultSet(int lightness, int hue, int saturation) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1], super.twoOctetsToArray(hue)[0], super.twoOctetsToArray(hue)[1], super.twoOctetsToArray(saturation)[0], super.twoOctetsToArray(saturation)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslDefaultSetUnacknowledged(int lightness, int hue, int saturation) {
        lightHslDefaultSet(lightness, hue, saturation);
    }

    public void lightHslRangeGet() {
        super.modelSendPacket();
    }

    public void lightHslRangeSet(int hueRangeMin, int hueRangeMax, int saturationRangeMin, int saturationRangeMax) {
        int[] params = {super.twoOctetsToArray(hueRangeMin)[0], super.twoOctetsToArray(hueRangeMin)[1], super.twoOctetsToArray(hueRangeMax)[0], super.twoOctetsToArray(hueRangeMax)[1], super.twoOctetsToArray(saturationRangeMin)[0], super.twoOctetsToArray(saturationRangeMin)[1], super.twoOctetsToArray(saturationRangeMax)[0], super.twoOctetsToArray(saturationRangeMax)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslRangeSetUnacknowledged(int hueRangeMin, int hueRangeMax, int saturationRangeMin, int saturationRangeMax) {
        lightHslRangeSet(hueRangeMin, hueRangeMax, saturationRangeMin, saturationRangeMax);
    }

    public void lightHslHueGet(int hue, int tid, int transitionTime, int delay) {
        Log.d(TAG, "lightHSLHueGet");
        super.modelSendPacket();
    }

    public void lightHslHueSet(int hue, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(hue)[0], super.twoOctetsToArray(hue)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslHueSetUnacknowledged(int hue, int tid, int transitionTime, int delay) {
        lightHslHueSet(hue, tid, transitionTime, delay);
    }

    public void lightHslSaturationGet() {
        super.modelSendPacket();
    }

    public void lightHslSaturationSet(int saturation, int tid, int transitionTime, int delay) {
        int[] params = {super.twoOctetsToArray(saturation)[0], super.twoOctetsToArray(saturation)[1], tid, transitionTime, delay};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslSaturationSetUnacknowledged(int saturation, int tid, int transitionTime, int delay) {
        lightHslSaturationSet(saturation, tid, transitionTime, delay);
    }
}
