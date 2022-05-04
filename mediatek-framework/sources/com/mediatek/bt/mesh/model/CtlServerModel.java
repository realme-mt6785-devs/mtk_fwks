package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.MeshConstants;
import com.mediatek.bt.mesh.MeshModel;
import java.util.Arrays;
/* loaded from: classes.dex */
public class CtlServerModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "CtlServerModel";
    private int genericLevelState;
    private int lightCTLDeltaUVDefaultState;
    private int lightCTLDeltaUVState;
    private int lightCTLState;
    private int lightCTLTemperatureDefaultState;
    private int lightCTLTemperatureRangeState;
    private int lightCTLTemperatureState;

    public CtlServerModel(BluetoothMesh meshInst) {
        super(meshInst, MeshConstants.MESH_MODEL_DATA_OP_ADD_MODEL);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        Log.d(TAG, "setTxMessageHeader");
        super.setTxMessageHeader(dstAddrType, dst, virtualUUID, src, ttl, netKeyIndex, appKeyIndex, msgOpCode);
    }

    public void lightCtlStatus(int presentCTLLightness, int presentCTLTemperature, int targetCTLLightness, int targetCTLTemperature, int remainingTime) {
        int[] params = {super.twoOctetsToArray(presentCTLLightness)[0], super.twoOctetsToArray(presentCTLLightness)[1], super.twoOctetsToArray(presentCTLTemperature)[0], super.twoOctetsToArray(presentCTLTemperature)[1], super.twoOctetsToArray(targetCTLLightness)[0], super.twoOctetsToArray(targetCTLLightness)[1], super.twoOctetsToArray(targetCTLTemperature)[0], super.twoOctetsToArray(targetCTLTemperature)[1], remainingTime};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightCtlTemperatureRangeStatus(int statusCode, int RangeMin, int RangeMax) {
        int[] params = {statusCode, super.twoOctetsToArray(RangeMin)[0], super.twoOctetsToArray(RangeMin)[1], super.twoOctetsToArray(RangeMax)[0], super.twoOctetsToArray(RangeMax)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void lightCtlDefaultStatus(int lightness, int temperature, int deltaUV) {
        int[] params = {super.twoOctetsToArray(lightness)[0], super.twoOctetsToArray(lightness)[1], super.twoOctetsToArray(temperature)[0], super.twoOctetsToArray(temperature)[1], super.twoOctetsToArray(deltaUV)[0], super.twoOctetsToArray(deltaUV)[1]};
        Log.d(TAG, "params:" + Arrays.toString(params));
        super.modelSendPacket(params);
    }

    public void setLightCtlState(int lightCTLState) {
        this.lightCTLState = lightCTLState;
    }

    public int getLightCtlState() {
        return this.lightCTLState;
    }

    public void setLightCtlDeltaUvState(int lightCTLDeltaUVState) {
        this.lightCTLDeltaUVState = lightCTLDeltaUVState;
    }

    public int getLightCtlDeltaUvState() {
        return this.lightCTLDeltaUVState;
    }

    public void setLightCtlTemperatureRangeState(int lightCTLTemperatureRangeState) {
        this.lightCTLTemperatureRangeState = lightCTLTemperatureRangeState;
    }

    public int getLightCtlTemperatureRangeState() {
        return this.lightCTLTemperatureRangeState;
    }

    public void setLightCtlTemperatureDefaultState(int lightCTLTemperatureDefaultState) {
        this.lightCTLTemperatureDefaultState = lightCTLTemperatureDefaultState;
    }

    public int getLightCtlTemperatureDefaultState() {
        return this.lightCTLTemperatureDefaultState;
    }

    public void setLightCtlDeltaUvDefaultState(int lightCTLDeltaUVDefaultState) {
        this.lightCTLDeltaUVDefaultState = lightCTLDeltaUVDefaultState;
    }

    public int getLightCtlDeltaUvDefaultState() {
        return this.lightCTLDeltaUVDefaultState;
    }

    public void setLightCtlTemperatureState(int lightCTLTemperatureState) {
        this.lightCTLTemperatureState = lightCTLTemperatureState;
    }

    public int getLightCtlTemperatureState() {
        return this.lightCTLTemperatureState;
    }

    public void setGenericLevelState(int genericLevelState) {
        this.genericLevelState = genericLevelState;
    }

    public int getGenericLevelState() {
        return this.genericLevelState;
    }
}
