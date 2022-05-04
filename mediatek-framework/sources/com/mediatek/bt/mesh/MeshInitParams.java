package com.mediatek.bt.mesh;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class MeshInitParams implements Parcelable {
    public static final Parcelable.Creator<MeshInitParams> CREATOR = new Parcelable.Creator<MeshInitParams>() { // from class: com.mediatek.bt.mesh.MeshInitParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MeshInitParams createFromParcel(Parcel in) {
            return new MeshInitParams(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MeshInitParams[] newArray(int size) {
            return new MeshInitParams[size];
        }
    };
    private int[] mCustomizeParams;
    private int mDefaultTtl;
    private int[] mDeviceUuid;
    private long mFeatureMask;
    private int[] mFriendInitParams;
    private int mOobInfo;
    private int[] mProvisioneeParams;
    private int mRole;
    private byte[] mUri;

    public MeshInitParams() {
    }

    MeshInitParams(Parcel in) {
        this.mRole = in.readInt();
        this.mProvisioneeParams = in.createIntArray();
        this.mDeviceUuid = in.createIntArray();
        this.mOobInfo = in.readInt();
        this.mDefaultTtl = in.readInt();
        this.mUri = in.createByteArray();
        this.mFeatureMask = in.readLong();
        this.mFriendInitParams = in.createIntArray();
        this.mCustomizeParams = in.createIntArray();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mRole);
        out.writeIntArray(this.mProvisioneeParams);
        out.writeIntArray(this.mDeviceUuid);
        out.writeInt(this.mOobInfo);
        out.writeInt(this.mDefaultTtl);
        out.writeByteArray(this.mUri);
        out.writeLong(this.mFeatureMask);
        out.writeIntArray(this.mFriendInitParams);
        out.writeIntArray(this.mCustomizeParams);
    }

    /* loaded from: classes.dex */
    public class ProvisioneeParams {
        private int mAlgorithms;
        private int mInputOobAction;
        private int mInputOobSize;
        private int mNumberOfElements;
        private int mOutputOobAction;
        private int mOutputOobSize;
        private int mPublicKeyType;
        private int mStaticOobType;

        public ProvisioneeParams(int numberOfElements, int algorithms, int publicKeyType, int staticOobType, int outputOobSize, int outputOobAction, int inputOobSize, int inputOobAction) {
            this.mNumberOfElements = numberOfElements;
            this.mAlgorithms = algorithms;
            this.mPublicKeyType = publicKeyType;
            this.mStaticOobType = staticOobType;
            this.mOutputOobSize = outputOobSize;
            this.mOutputOobAction = outputOobAction;
            this.mInputOobSize = inputOobSize;
            this.mInputOobAction = inputOobAction;
        }

        public int getNumberOfElements() {
            return this.mNumberOfElements;
        }

        public int getAlgorithms() {
            return this.mAlgorithms;
        }

        public int getPublicKeyType() {
            return this.mPublicKeyType;
        }

        public int getStaticOobType() {
            return this.mStaticOobType;
        }

        public int getOutputOobSize() {
            return this.mOutputOobSize;
        }

        public int getOutputOobAction() {
            return this.mOutputOobAction;
        }

        public int getInputOobSize() {
            return this.mInputOobSize;
        }

        public int getInputOobAction() {
            return this.mInputOobAction;
        }
    }

    /* loaded from: classes.dex */
    public class FriendInitParams {
        private int mLpnNumber;
        private int mQueueSize;
        private int mSubscriptionListSize;

        public FriendInitParams(int lpnNumber, int queueSize, int subscriptionListSize) {
            this.mLpnNumber = lpnNumber;
            this.mQueueSize = queueSize;
            this.mSubscriptionListSize = subscriptionListSize;
        }

        public int getLpnNumber() {
            return this.mLpnNumber;
        }

        public int getQueueSize() {
            return this.mQueueSize;
        }

        public int getSubscriptionListSize() {
            return this.mSubscriptionListSize;
        }
    }

    /* loaded from: classes.dex */
    public class CustomizeParams {
        private int mMaxRemoteNodeCnt;
        private int mSave2flash;

        public CustomizeParams(int maxRemoteNodeCnt, int save2flash) {
            this.mMaxRemoteNodeCnt = maxRemoteNodeCnt;
            this.mSave2flash = save2flash;
        }

        public int getMaxRemoteNodeCnt() {
            return this.mMaxRemoteNodeCnt;
        }

        public int getSave2flash() {
            return this.mSave2flash;
        }
    }

    public void setRole(int role) {
        this.mRole = role;
    }

    public void setProvisioneeParams(ProvisioneeParams provisionee) {
        int[] param = {provisionee.getNumberOfElements(), provisionee.getAlgorithms(), provisionee.getPublicKeyType(), provisionee.getStaticOobType(), provisionee.getOutputOobSize(), provisionee.getOutputOobAction(), provisionee.getInputOobSize(), provisionee.getInputOobAction()};
        this.mProvisioneeParams = param;
    }

    public void setDeviceUuid(int[] deviceUuid) {
        this.mDeviceUuid = deviceUuid;
    }

    public void setOobInfo(int oobInfo) {
        this.mOobInfo = oobInfo;
    }

    public void setDefaultTtl(int defaultTtl) {
        this.mDefaultTtl = defaultTtl;
    }

    public void setUri(byte[] uri) {
        this.mUri = uri;
    }

    public void setFeatureMask(long featureMask) {
        this.mFeatureMask = featureMask;
    }

    public void setFriendInitParams(FriendInitParams friend) {
        int[] param = {friend.getLpnNumber(), friend.getQueueSize(), friend.getSubscriptionListSize()};
        this.mFriendInitParams = param;
    }

    public void setCustomizeParams(CustomizeParams customize) {
        int[] param = {customize.getMaxRemoteNodeCnt(), customize.getSave2flash()};
        this.mCustomizeParams = param;
    }

    public int getRole() {
        return this.mRole;
    }

    public int[] getProvisioneeParams() {
        return this.mProvisioneeParams;
    }

    public int[] getDeviceUuid() {
        return this.mDeviceUuid;
    }

    public int getOobInfo() {
        return this.mOobInfo;
    }

    public int getDefaultTtl() {
        return this.mDefaultTtl;
    }

    public byte[] getUri() {
        return this.mUri;
    }

    public long getFeatureMask() {
        return this.mFeatureMask;
    }

    public int[] getFriendInitParams() {
        return this.mFriendInitParams;
    }

    public int[] getCustomizeParams() {
        return this.mCustomizeParams;
    }
}
