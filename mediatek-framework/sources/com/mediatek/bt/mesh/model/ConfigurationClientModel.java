package com.mediatek.bt.mesh.model;

import android.util.Log;
import com.mediatek.bt.mesh.BluetoothMesh;
import com.mediatek.bt.mesh.ConfigMessageParams;
import com.mediatek.bt.mesh.MeshModel;
/* loaded from: classes.dex */
public class ConfigurationClientModel extends MeshModel {
    private static final boolean DBG = true;
    private static final String TAG = "ConfigurationClientModel";

    public ConfigurationClientModel(BluetoothMesh meshInst) {
        super(meshInst, 4);
    }

    @Override // com.mediatek.bt.mesh.MeshModel
    public void setConfigMessageHeader(int src, int dst, int ttl, int netKeyIndex, int msgOpCode) {
        Log.d(TAG, "setConfigMessageHeader");
        super.setConfigMessageHeader(src, dst, ttl, netKeyIndex, msgOpCode);
    }

    public void configBeaconGet() {
        Log.d(TAG, "configBeaconGet");
        super.modelSendConfigMessage();
    }

    public void configBeaconSet(ConfigMessageParams param) {
    }

    public void configCompositionDataGet(ConfigMessageParams param) {
        Log.d(TAG, "configCompositionDataGet");
        super.modelSendConfigMessage(param);
    }

    public void configDefaultTtlGet() {
        Log.d(TAG, "configDefaultTtlGet");
        super.modelSendConfigMessage();
    }

    public void configDefaultTtlSet(ConfigMessageParams param) {
        Log.d(TAG, "configDefaultTTLSet");
        super.modelSendConfigMessage(param);
    }

    public void configGattProxyGet() {
        Log.d(TAG, "configGattProxyGet");
        super.modelSendConfigMessage();
    }

    public void configGattProxySet(ConfigMessageParams param) {
        Log.d(TAG, "configGattProxySet");
        super.modelSendConfigMessage(param);
    }

    public void configFriendGet() {
        Log.d(TAG, "configFriendGet");
        super.modelSendConfigMessage();
    }

    public void configFriendSet(ConfigMessageParams param) {
        Log.d(TAG, "configFriendSet");
        super.modelSendConfigMessage(param);
    }

    public void configRelayGet() {
        Log.d(TAG, "configRelayGet");
        super.modelSendConfigMessage();
    }

    public void configRelaySet(ConfigMessageParams param) {
        Log.d(TAG, "configRelaySet");
        super.modelSendConfigMessage(param);
    }

    public void configModelPubGet(ConfigMessageParams param) {
        Log.d(TAG, "configModelPubGet");
        super.modelSendConfigMessage(param);
    }

    public void configModelPubSet(ConfigMessageParams param) {
        Log.d(TAG, "configModelPubSet");
        super.modelSendConfigMessage(param);
    }

    public void configModelSubAdd(ConfigMessageParams param) {
        Log.d(TAG, "configModelSubAdd");
        super.modelSendConfigMessage(param);
    }

    public void configModelSubDel(ConfigMessageParams param) {
        Log.d(TAG, "configModelSubDel");
        super.modelSendConfigMessage(param);
    }

    public void configModelSubOw(ConfigMessageParams param) {
        Log.d(TAG, "configModelSubOw");
        super.modelSendConfigMessage(param);
    }

    public void configModelSubDelAll(ConfigMessageParams param) {
        Log.d(TAG, "configModelSubDelAll");
        super.modelSendConfigMessage(param);
    }

    public void configSigModelSubGet(ConfigMessageParams param) {
        Log.d(TAG, "configSigModelSubGet");
        super.modelSendConfigMessage(param);
    }

    public void configVendorModelSubGet(ConfigMessageParams param) {
        Log.d(TAG, "configVendorModelSubGet");
        super.modelSendConfigMessage(param);
    }

    public void configNetkeyAdd(ConfigMessageParams param) {
        Log.d(TAG, "configNetkeyAdd");
        super.modelSendConfigMessage(param);
    }

    public void configNetkeyUpdate(ConfigMessageParams param) {
        Log.d(TAG, "configNetkeyUpdate");
        super.modelSendConfigMessage(param);
    }

    public void configNetkeyDel(ConfigMessageParams param) {
        Log.d(TAG, "configNetkeyDel");
        super.modelSendConfigMessage(param);
    }

    public void configNetkeyGet() {
        Log.d(TAG, "configNetkeyGet");
        super.modelSendConfigMessage();
    }

    public void configAppkeyAdd(ConfigMessageParams param) {
        Log.d(TAG, "configAppkeyAdd");
        super.modelSendConfigMessage(param);
    }

    public void configAppkeyUpdate(ConfigMessageParams param) {
        Log.d(TAG, "configAppkeyUpdate");
        super.modelSendConfigMessage(param);
    }

    public void configAppkeyDel(ConfigMessageParams param) {
        Log.d(TAG, "configAppkeyDel");
        super.modelSendConfigMessage(param);
    }

    public void configAppkeyGet(ConfigMessageParams param) {
        Log.d(TAG, "configAppkeyGet");
        super.modelSendConfigMessage(param);
    }

    public void configModelAppBind(ConfigMessageParams param) {
        Log.d(TAG, "configModelAppBind");
        super.modelSendConfigMessage(param);
    }

    public void configModelAppUnbind(ConfigMessageParams param) {
        Log.d(TAG, "configModelAppUnbind");
        super.modelSendConfigMessage(param);
    }

    public void configSigModelAppGet(ConfigMessageParams param) {
        Log.d(TAG, "configSigModelAppGet");
        super.modelSendConfigMessage(param);
    }

    public void configVendorModelAppGet(ConfigMessageParams param) {
        Log.d(TAG, "configVendorModelAppGet");
        super.modelSendConfigMessage(param);
    }

    public void configNodeIdentityGet(ConfigMessageParams param) {
        Log.d(TAG, "configNodeIdentityGet");
        super.modelSendConfigMessage(param);
    }

    public void configNodeIdentitySet(ConfigMessageParams param) {
        Log.d(TAG, "configNodeIdentitySet");
        super.modelSendConfigMessage(param);
    }

    public void configNodeReset() {
        Log.d(TAG, "configNodeReset");
        super.modelSendConfigMessage();
    }

    public void configKeyRefreshPhaseGet(ConfigMessageParams param) {
        Log.d(TAG, "configKeyRefreshPhaseGet");
        super.modelSendConfigMessage(param);
    }

    public void configKeyRefreshPhaseSet(ConfigMessageParams param) {
        Log.d(TAG, "configKeyRefreshPhaseSet");
        super.modelSendConfigMessage(param);
    }

    public void configHbPubGet() {
        Log.d(TAG, "configHbPubGet");
        super.modelSendConfigMessage();
    }

    public void configHbPubSet(ConfigMessageParams param) {
        Log.d(TAG, "configHbPubSet");
        super.modelSendConfigMessage(param);
    }

    public void configHbSubGet() {
        Log.d(TAG, "configHbSubGet");
        super.modelSendConfigMessage();
    }

    public void configHbSubSet(ConfigMessageParams param) {
        Log.d(TAG, "configHbSubSet");
        super.modelSendConfigMessage(param);
    }

    public void configNetworkTransmitGet() {
        Log.d(TAG, "configNetworkTransmitGet");
        super.modelSendConfigMessage();
    }

    public void configNetworkTransmitSet(ConfigMessageParams param) {
        Log.d(TAG, "configNetworkTransmitSet");
        super.modelSendConfigMessage(param);
    }
}
