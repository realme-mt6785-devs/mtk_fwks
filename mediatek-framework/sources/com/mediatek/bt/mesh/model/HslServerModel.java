package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshConstants;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class HslServerModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "HslServerModel";
    private int genericLevelState;
    private int lightHSLHueDefaultState;
    private int lightHSLHueRangeState;
    private int lightHSLHueState;
    private int lightHSLSaturationDefaultState;
    private int lightHSLSaturationRangeState;
    private int lightHSLSaturationState;
    private int lightHSLState;

    public HslServerModel(BluetoothMesh meshInst) {
        super(meshInst, MeshConstants.MESH_MODEL_DATA_OP_ADD_MODEL);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void lightHslStatus(int hslLightness, int hslHue, int hslSaturation, int remainingTime) {
        int[] params = {super.twoOctetsToArray(hslLightness)[0], super.twoOctetsToArray(hslLightness)[1], super.twoOctetsToArray(hslHue)[0], super.twoOctetsToArray(hslHue)[1], super.twoOctetsToArray(hslSaturation)[0], super.twoOctetsToArray(hslSaturation)[1], remainingTime};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslTargetStatus(int hslLightnessTarget, int hslHueTarget, int hslSaturationTarget, int remainingTime) {
        int[] params = {super.twoOctetsToArray(hslLightnessTarget)[0], super.twoOctetsToArray(hslLightnessTarget)[1], super.twoOctetsToArray(hslHueTarget)[0], super.twoOctetsToArray(hslHueTarget)[1], super.twoOctetsToArray(hslSaturationTarget)[0], super.twoOctetsToArray(hslSaturationTarget)[1], remainingTime};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightHslDefaultStatus(int lightness, int hue, int saturation) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1], super.twoOctetsToArray(hue)[0], super.twoOctetsToArray(hue)[1], super.twoOctetsToArray(saturation)[0], super.twoOctetsToArray(saturation)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void setLightHslState(int lightHSLState) {
        this.lightHSLState = lightHSLState;
    }

    public int getLightHslState() {
        return this.lightHSLState;
    }

    public void setLightHslHueRangeState(int lightHSLHueRangeState) {
        this.lightHSLHueRangeState = lightHSLHueRangeState;
    }

    public int getLightHslHueRangeState() {
        return this.lightHSLHueRangeState;
    }

    public void setLightHslHueDefaultState(int lightHSLHueDefaultState) {
        this.lightHSLHueDefaultState = lightHSLHueDefaultState;
    }

    public int getLightHslHueDefaultState() {
        return this.lightHSLHueDefaultState;
    }

    public void setLightHslSaturationRangeState(int lightHSLSaturationRangeState) {
        this.lightHSLSaturationRangeState = lightHSLSaturationRangeState;
    }

    public int getLightHslSaturationRangeState() {
        return this.lightHSLSaturationRangeState;
    }

    public void setLightHslSaturationDefaultState(int lightHSLSaturationDefaultState) {
        this.lightHSLSaturationDefaultState = lightHSLSaturationDefaultState;
    }

    public int getLightHslSaturationDefaultState() {
        return this.lightHSLSaturationDefaultState;
    }

    public void setLightHslHueState(int lightHSLHueState) {
        this.lightHSLHueState = lightHSLHueState;
    }

    public int getLightHslHueState() {
        return this.lightHSLHueState;
    }

    public void setLightHslSaturationState(int lightHSLSaturationState) {
        this.lightHSLSaturationState = lightHSLSaturationState;
    }

    public int getLightHslSaturationState() {
        return this.lightHSLSaturationState;
    }

    public void setGenericLevelState(int genericLevelState) {
        this.genericLevelState = genericLevelState;
    }

    public int getGenericLevelState() {
        return this.genericLevelState;
    }
}
