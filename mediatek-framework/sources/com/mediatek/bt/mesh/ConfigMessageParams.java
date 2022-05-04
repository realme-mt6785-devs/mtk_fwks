package com.mediatek.bt.mesh;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class ConfigMessageParams implements Parcelable {
    public static final Parcelable.Creator<ConfigMessageParams> CREATOR = new Parcelable.Creator<ConfigMessageParams>() { // from class: com.mediatek.bt.mesh.ConfigMessageParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConfigMessageParams createFromParcel(Parcel in) {
            return new ConfigMessageParams(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConfigMessageParams[] newArray(int size) {
            return new ConfigMessageParams[size];
        }
    };
    private int TTL;
    private int addressType;
    private int addressValue;
    private int[] appkey;
    private int appkeyIndex;
    private int beacon;
    private int count;
    private int countLog;
    private int destination;
    private int elementAddress;
    private int features;
    private boolean friendshipCredentialFlag;
    private int gattProxy;
    private int identity;
    private int intervalSteps;
    private int meshFriend;
    private long modelId;
    private int[] netkey;
    private int netkeyIndex;
    private int page;
    private int periodLog;
    private int publishPeriod;
    private int publishTTL;
    private int relay;
    private int retransmitCount;
    private int retransmitIntervalSteps;
    private int source;
    private int transition;
    private int[] virtualUUID;

    public ConfigMessageParams() {
    }

    private ConfigMessageParams(Parcel in) {
        this.beacon = in.readInt();
        this.page = in.readInt();
        this.TTL = in.readInt();
        this.gattProxy = in.readInt();
        this.meshFriend = in.readInt();
        this.relay = in.readInt();
        this.retransmitCount = in.readInt();
        this.retransmitIntervalSteps = in.readInt();
        this.elementAddress = in.readInt();
        this.modelId = in.readLong();
        this.friendshipCredentialFlag = in.readInt() != 0;
        this.publishTTL = in.readInt();
        this.publishPeriod = in.readInt();
        this.addressType = in.readInt();
        this.addressValue = in.readInt();
        this.virtualUUID = in.createIntArray();
        this.appkeyIndex = in.readInt();
        this.netkeyIndex = in.readInt();
        this.appkey = in.createIntArray();
        this.netkey = in.createIntArray();
        this.identity = in.readInt();
        this.transition = in.readInt();
        this.destination = in.readInt();
        this.countLog = in.readInt();
        this.periodLog = in.readInt();
        this.features = in.readInt();
        this.source = in.readInt();
        this.count = in.readInt();
        this.intervalSteps = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.beacon);
        out.writeInt(this.page);
        out.writeInt(this.TTL);
        out.writeInt(this.gattProxy);
        out.writeInt(this.meshFriend);
        out.writeInt(this.relay);
        out.writeInt(this.retransmitCount);
        out.writeInt(this.retransmitIntervalSteps);
        out.writeInt(this.elementAddress);
        out.writeLong(this.modelId);
        out.writeInt(this.friendshipCredentialFlag ? 1 : 0);
        out.writeInt(this.publishTTL);
        out.writeInt(this.publishPeriod);
        out.writeInt(this.addressType);
        out.writeInt(this.addressValue);
        out.writeIntArray(this.virtualUUID);
        out.writeInt(this.appkeyIndex);
        out.writeInt(this.netkeyIndex);
        out.writeIntArray(this.appkey);
        out.writeIntArray(this.netkey);
        out.writeInt(this.identity);
        out.writeInt(this.transition);
        out.writeInt(this.destination);
        out.writeInt(this.countLog);
        out.writeInt(this.periodLog);
        out.writeInt(this.features);
        out.writeInt(this.source);
        out.writeInt(this.count);
        out.writeInt(this.intervalSteps);
    }

    public void setConfigBeaconGetParam() {
    }

    public void setConfigBeaconSetParam(int beacon) {
        this.beacon = beacon;
    }

    public void setConfigCompositionDataGetParam(int page) {
        this.page = page;
    }

    public void setConfigDefaultTtlGetParam() {
    }

    public void setConfigDefaultTtlSetParam(int TTL) {
        this.TTL = TTL;
    }

    public void setConfigGattProxyGetParam() {
    }

    public void setConfigGattProxySetParam(int gattProxy) {
        this.gattProxy = gattProxy;
    }

    public void setConfigFriendGetParam() {
    }

    public void setConfigFriendSetParam(int meshFriend) {
        this.meshFriend = meshFriend;
    }

    public void setConfigRelayGetParam() {
    }

    public void setConfigRelaySetParam(int relay, int retransmitCount, int retransmitIntervalSteps) {
        this.relay = relay;
        this.retransmitCount = retransmitCount;
        this.retransmitIntervalSteps = retransmitIntervalSteps;
    }

    public void setConfigModelPubGetParam(int elementAddress, long modelId) {
        this.elementAddress = elementAddress;
        this.modelId = modelId;
    }

    public void setConfigModelPubSetParam(int elementAddress, int addressType, int addressValue, int[] virtualUUID, int appkeyIndex, boolean friendshipCredentialFlag, int publishTTL, int publishPeriod, int retransmitCount, int retransmitIntervalSteps, long modelId) {
        this.elementAddress = elementAddress;
        this.addressType = addressType;
        this.addressValue = addressValue;
        this.virtualUUID = virtualUUID;
        this.appkeyIndex = appkeyIndex;
        this.friendshipCredentialFlag = friendshipCredentialFlag;
        this.publishTTL = publishTTL;
        this.publishPeriod = publishPeriod;
        this.retransmitCount = retransmitCount;
        this.retransmitIntervalSteps = retransmitIntervalSteps;
        this.modelId = modelId;
    }

    public void setConfigModelSubAddParam(int elementAddress, int addressType, int addressValue, int[] virtualUUID, long modelId) {
        this.elementAddress = elementAddress;
        this.addressType = addressType;
        this.addressValue = addressValue;
        this.virtualUUID = virtualUUID;
        this.modelId = modelId;
    }

    public void setConfigModelSubDelParam(int elementAddress, int addressType, int addressValue, int[] virtualUUID, long modelId) {
        this.elementAddress = elementAddress;
        this.addressType = addressType;
        this.addressValue = addressValue;
        this.virtualUUID = virtualUUID;
        this.modelId = modelId;
    }

    public void setConfigModelSubOwParam(int elementAddress, int addressType, int addressValue, int[] virtualUUID, long modelId) {
        this.elementAddress = elementAddress;
        this.addressType = addressType;
        this.addressValue = addressValue;
        this.virtualUUID = virtualUUID;
        this.modelId = modelId;
    }

    public void setConfigModelSubDelAllParam(int elementAddress, long modelId) {
        this.elementAddress = elementAddress;
        this.modelId = modelId;
    }

    public void setConfigSigModelSubGetParam(int elementAddress, long modelId) {
        this.elementAddress = elementAddress;
        this.modelId = modelId;
    }

    public void setConfigVendorModelSubGetParam(int elementAddress, long modelId) {
        this.elementAddress = elementAddress;
        this.modelId = modelId;
    }

    public void setConfigNetkeyAddParam(int netkeyIndex, int[] netkey) {
        this.netkeyIndex = netkeyIndex;
        this.netkey = netkey;
    }

    public void setConfigNetkeyUpdateParam(int netkeyIndex, int[] netkey) {
        this.netkeyIndex = netkeyIndex;
        this.netkey = netkey;
    }

    public void setConfigNetkeyDelParam(int netkeyIndex) {
        this.netkeyIndex = netkeyIndex;
    }

    public void setConfigNetkeyGetParam() {
    }

    public void setConfigAppkeyAddParam(int netkeyIndex, int appkeyIndex, int[] appkey) {
        this.netkeyIndex = netkeyIndex;
        this.appkeyIndex = appkeyIndex;
        this.appkey = appkey;
    }

    public void setConfigAppkeyUpdateParam(int netkeyIndex, int appkeyIndex, int[] appkey) {
        this.netkeyIndex = netkeyIndex;
        this.appkeyIndex = appkeyIndex;
        this.appkey = appkey;
    }

    public void setConfigAppkeyDelParam(int netkeyIndex, int appkeyIndex) {
        this.netkeyIndex = netkeyIndex;
        this.appkeyIndex = appkeyIndex;
    }

    public void setConfigAppkeyGetParam(int netkeyIndex) {
        this.netkeyIndex = netkeyIndex;
    }

    public void setConfigModelAppBindParam(int elementAddress, int appkeyIndex, long modelId) {
        this.elementAddress = elementAddress;
        this.appkeyIndex = appkeyIndex;
        this.modelId = modelId;
    }

    public void setConfigModelAppUnbindParam(int elementAddress, int appkeyIndex, long modelId) {
        this.elementAddress = elementAddress;
        this.appkeyIndex = appkeyIndex;
        this.modelId = modelId;
    }

    public void setConfigSigModelAppGetParam(int elementAddress, long modelId) {
        this.elementAddress = elementAddress;
        this.modelId = modelId;
    }

    public void setConfigVendorModelAppGetParam(int elementAddress, long modelId) {
        this.elementAddress = elementAddress;
        this.modelId = modelId;
    }

    public void setConfigNodeIdentityGetParam(int netkeyIndex) {
        this.netkeyIndex = netkeyIndex;
    }

    public void setConfigNodeIdentitySetParam(int netkeyIndex, int identity) {
        this.netkeyIndex = netkeyIndex;
        this.identity = identity;
    }

    public void setConfigNodeResetParam() {
    }

    public void setConfigKeyRefreshPhaseGetParam(int netkeyIndex) {
        this.netkeyIndex = netkeyIndex;
    }

    public void setConfigKeyRefreshPhaseSetParam(int netkeyIndex, int transition) {
        this.netkeyIndex = netkeyIndex;
        this.transition = transition;
    }

    public void setConfigHbPubGetParam() {
    }

    public void setConfigHbPubSetParam(int destination, int countLog, int periodLog, int TTL, int features, int netkeyIndex) {
        this.destination = destination;
        this.countLog = countLog;
        this.periodLog = periodLog;
        this.TTL = TTL;
        this.features = features;
        this.netkeyIndex = netkeyIndex;
    }

    public void setConfigHbSubGetParam() {
    }

    public void setConfigHbSubSetParam(int source, int destination, int periodLog) {
        this.source = source;
        this.destination = destination;
        this.periodLog = periodLog;
    }

    public void setConfigNetworkTransmitGetParam() {
    }

    public void setConfigNetworkTransmitSetParam(int count, int intervalSteps) {
        this.count = count;
        this.intervalSteps = intervalSteps;
    }

    public int getBeacon() {
        return this.beacon;
    }

    public int getPage() {
        return this.page;
    }

    public int getTtl() {
        return this.TTL;
    }

    public int getGattProxy() {
        return this.gattProxy;
    }

    public int getMeshFriend() {
        return this.meshFriend;
    }

    public int getRelay() {
        return this.relay;
    }

    public int getRetransmitCount() {
        return this.retransmitCount;
    }

    public int getRetransmitIntervalSteps() {
        return this.retransmitIntervalSteps;
    }

    public int getElementAddress() {
        return this.elementAddress;
    }

    public long getModelId() {
        return this.modelId;
    }

    public boolean getFriendshipCredentialFlag() {
        return this.friendshipCredentialFlag;
    }

    public int getPublishTtl() {
        return this.publishTTL;
    }

    public int getPublishPeriod() {
        return this.publishPeriod;
    }

    public int getAddressType() {
        return this.addressType;
    }

    public int getAddressValue() {
        return this.addressValue;
    }

    public int[] getVirtualUuid() {
        return this.virtualUUID;
    }

    public int getAppkeyIndex() {
        return this.appkeyIndex;
    }

    public int getNetkeyIndex() {
        return this.netkeyIndex;
    }

    public int[] getAppkey() {
        return this.appkey;
    }

    public int[] getNetkey() {
        return this.netkey;
    }

    public int getIdentity() {
        return this.identity;
    }

    public int getTransition() {
        return this.transition;
    }

    public int getDestination() {
        return this.destination;
    }

    public int getCountLog() {
        return this.countLog;
    }

    public int getPeriodLog() {
        return this.periodLog;
    }

    public int getFeatures() {
        return this.features;
    }

    public int getSource() {
        return this.source;
    }

    public int getCount() {
        return this.count;
    }

    public int getIntervalSteps() {
        return this.intervalSteps;
    }
}
