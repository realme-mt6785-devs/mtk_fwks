package com.mediatek.bt.mesh;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class BluetoothMeshAccessRxMessage implements Parcelable {
    public static final Parcelable.Creator<BluetoothMeshAccessRxMessage> CREATOR = new Parcelable.Creator<BluetoothMeshAccessRxMessage>() { // from class: com.mediatek.bt.mesh.BluetoothMeshAccessRxMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMeshAccessRxMessage createFromParcel(Parcel in) {
            return new BluetoothMeshAccessRxMessage(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMeshAccessRxMessage[] newArray(int size) {
            return new BluetoothMeshAccessRxMessage[size];
        }
    };
    private static int mAppKeyIndex;
    private static int[] mBuffer;
    private static int mBufferLen;
    private static int mCompanyId;
    private static int mDstAddr;
    private static int mNetKeyIndex;
    private static int mOpCode;
    private static int mRssi;
    private static int mSrcAddr;
    private static int mTtl;

    public BluetoothMeshAccessRxMessage() {
    }

    public BluetoothMeshAccessRxMessage(int opCode, int companyId, int[] buffer, int bufferLen, int srcAddr, int dstAddr, int appKeyIndex, int netKeyIndex, int rssi, int ttl) {
        mOpCode = opCode;
        mCompanyId = companyId;
        mBuffer = buffer;
        mBufferLen = bufferLen;
        mSrcAddr = srcAddr;
        mDstAddr = dstAddr;
        mAppKeyIndex = appKeyIndex;
        mNetKeyIndex = netKeyIndex;
        mRssi = rssi;
        mTtl = ttl;
    }

    BluetoothMeshAccessRxMessage(Parcel in) {
        mOpCode = in.readInt();
        mCompanyId = in.readInt();
        mBuffer = in.createIntArray();
        mBufferLen = in.readInt();
        mSrcAddr = in.readInt();
        mDstAddr = in.readInt();
        mAppKeyIndex = in.readInt();
        mNetKeyIndex = in.readInt();
        mRssi = in.readInt();
        mTtl = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mOpCode);
        out.writeInt(mCompanyId);
        out.writeIntArray(mBuffer);
        out.writeInt(mBufferLen);
        out.writeInt(mSrcAddr);
        out.writeInt(mDstAddr);
        out.writeInt(mAppKeyIndex);
        out.writeInt(mNetKeyIndex);
        out.writeInt(mRssi);
        out.writeInt(mTtl);
    }

    public void setAccessOpCode(int opCode, int companyId) {
        mOpCode = opCode;
        mCompanyId = companyId;
    }

    public void setMetaData(int srcAddr, int dstAddr, int appKeyIndex, int netKeyIndex, int rssi, int ttl) {
        mSrcAddr = srcAddr;
        mDstAddr = dstAddr;
        mAppKeyIndex = appKeyIndex;
        mNetKeyIndex = netKeyIndex;
        mRssi = rssi;
        mTtl = ttl;
    }

    public void setBuffer(int[] buffer) {
        mBuffer = buffer;
        mBufferLen = buffer.length;
    }

    public int getOpCode() {
        return mOpCode;
    }

    public int getCompanyId() {
        return mCompanyId;
    }

    public int[] getBuffer() {
        return mBuffer;
    }

    public int getSrcAddr() {
        return mSrcAddr;
    }

    public int getDstAddr() {
        return mDstAddr;
    }

    public int getAppKeyIndex() {
        return mAppKeyIndex;
    }

    public int getNetKeyIndex() {
        return mNetKeyIndex;
    }

    public int getRssi() {
        return mRssi;
    }

    public int getTtl() {
        return mTtl;
    }
}
