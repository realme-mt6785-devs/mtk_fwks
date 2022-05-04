package com.mediatek.bt.mesh;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class OtaOperationParams implements Parcelable {
    public static final Parcelable.Creator<OtaOperationParams> CREATOR = new Parcelable.Creator<OtaOperationParams>() { // from class: com.mediatek.bt.mesh.OtaOperationParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaOperationParams createFromParcel(Parcel in) {
            return new OtaOperationParams(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaOperationParams[] newArray(int size) {
            return new OtaOperationParams[size];
        }
    };
    private static final boolean DBG = true;
    private static final String TAG = "BluetoothMesh_OtaOperationParams";
    private int mAppkeyIndex;
    private int mDistributorAddr;
    private long mFwId;
    private int mGroupAddr;
    private boolean mManualApply;
    private int mNodeAddr;
    private byte[] mObjFile;
    private int[] mObjId;
    private int mObjSize;
    private int mOpcode;
    private int[] mUpdaters;
    private int mUpdatersNum;

    public OtaOperationParams() {
    }

    OtaOperationParams(Parcel in) {
        this.mOpcode = in.readInt();
        this.mNodeAddr = in.readInt();
        this.mObjFile = in.createByteArray();
        this.mObjSize = in.readInt();
        this.mObjId = in.createIntArray();
        this.mFwId = in.readLong();
        this.mAppkeyIndex = in.readInt();
        this.mDistributorAddr = in.readInt();
        this.mGroupAddr = in.readInt();
        this.mUpdatersNum = in.readInt();
        this.mUpdaters = in.createIntArray();
        this.mManualApply = in.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mOpcode);
        out.writeInt(this.mNodeAddr);
        out.writeByteArray(this.mObjFile);
        out.writeInt(this.mObjSize);
        out.writeIntArray(this.mObjId);
        out.writeLong(this.mFwId);
        out.writeInt(this.mAppkeyIndex);
        out.writeInt(this.mDistributorAddr);
        out.writeInt(this.mGroupAddr);
        out.writeInt(this.mUpdatersNum);
        out.writeIntArray(this.mUpdaters);
        out.writeInt(this.mManualApply ? 1 : 0);
    }

    public void setOtaInitiatorMsgHandler(int appKeyIndex) {
        this.mOpcode = 0;
        this.mAppkeyIndex = appKeyIndex;
    }

    public void setOtaInitiatorFwInfoGet(int nodeAddr) {
        this.mOpcode = 1;
        this.mNodeAddr = nodeAddr;
    }

    public void setOtaInitiatorStopParams(long fwID, int distributorAddr) {
        this.mOpcode = 3;
        this.mFwId = fwID;
        this.mDistributorAddr = distributorAddr;
    }

    public void setOtaInitiatorStartParams(byte[] objFile, int objSize, int[] objId, long fwID, int appkeyIndex, int distributorAddr, int groupAddr, int updatersNum, int[] updaters, boolean manualApply) {
        this.mOpcode = 2;
        this.mObjFile = objFile;
        this.mObjSize = objSize;
        this.mObjId = objId;
        this.mFwId = fwID;
        this.mAppkeyIndex = appkeyIndex;
        this.mDistributorAddr = distributorAddr;
        this.mGroupAddr = groupAddr;
        this.mUpdatersNum = updatersNum;
        this.mUpdaters = updaters;
        this.mManualApply = manualApply;
    }

    public void setOtaInitiatorApplyDistribution() {
        this.mOpcode = 4;
    }

    public int getOpcode() {
        return this.mOpcode;
    }

    public int getNodeAddr() {
        return this.mNodeAddr;
    }

    public byte[] getObjFile() {
        return this.mObjFile;
    }

    public int getObjSize() {
        return this.mObjSize;
    }

    public int[] getObjId() {
        return this.mObjId;
    }

    public long getFwId() {
        return this.mFwId;
    }

    public int getAppkeyIndex() {
        return this.mAppkeyIndex;
    }

    public int getDistributorAddr() {
        return this.mDistributorAddr;
    }

    public int getGroupAddr() {
        return this.mGroupAddr;
    }

    public int getUpdatersNum() {
        return this.mUpdatersNum;
    }

    public int[] getUpdaters() {
        return this.mUpdaters;
    }

    public boolean getManualApply() {
        return this.mManualApply;
    }
}
