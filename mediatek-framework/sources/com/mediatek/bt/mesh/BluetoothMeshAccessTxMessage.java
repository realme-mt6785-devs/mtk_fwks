package com.mediatek.bt.mesh;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class BluetoothMeshAccessTxMessage implements Parcelable {
    public static final Parcelable.Creator<BluetoothMeshAccessTxMessage> CREATOR = new Parcelable.Creator<BluetoothMeshAccessTxMessage>() { // from class: com.mediatek.bt.mesh.BluetoothMeshAccessTxMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMeshAccessTxMessage createFromParcel(Parcel in) {
            return new BluetoothMeshAccessTxMessage(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMeshAccessTxMessage[] newArray(int size) {
            return new BluetoothMeshAccessTxMessage[size];
        }
    };
    private static int[] mBuffer;
    private static int mBufferLen;
    private static int mCompanyId;
    private static int mOpCode;

    public BluetoothMeshAccessTxMessage() {
    }

    public BluetoothMeshAccessTxMessage(int opCode, int companyId, int[] buffer) {
        mOpCode = opCode;
        mCompanyId = companyId;
        mBuffer = buffer;
        mBufferLen = buffer.length;
    }

    public BluetoothMeshAccessTxMessage(int opCode, int companyId, int[] buffer, int bufferLen) {
        mOpCode = opCode;
        mCompanyId = companyId;
        mBuffer = buffer;
        mBufferLen = bufferLen;
    }

    BluetoothMeshAccessTxMessage(Parcel in) {
        mOpCode = in.readInt();
        mCompanyId = in.readInt();
        mBuffer = in.createIntArray();
        mBufferLen = in.readInt();
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
    }

    public void setAccessOpCode(int opCode, int companyId) {
        mOpCode = opCode;
        mCompanyId = companyId;
    }

    public void setBuffer(int[] buffer, int bufferLen) {
        mBuffer = buffer;
        mBufferLen = bufferLen;
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
}
