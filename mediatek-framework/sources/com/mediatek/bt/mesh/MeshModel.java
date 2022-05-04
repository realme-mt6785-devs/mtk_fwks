package com.mediatek.bt.mesh;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Arrays;
/* loaded from: classes.dex */
public class MeshModel implements Parcelable {
    public static final Parcelable.Creator<MeshModel> CREATOR = new Parcelable.Creator<MeshModel>() { // from class: com.mediatek.bt.mesh.MeshModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MeshModel createFromParcel(Parcel in) {
            return new MeshModel(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MeshModel[] newArray(int size) {
            return new MeshModel[size];
        }
    };
    private static final boolean DBG = true;
    private static final String TAG = "BluetoothMesh_MeshModel";
    private static final boolean VDBG = true;
    private int mCompanyID;
    private ModelConfigMessage mConfigMsg;
    private int mElementIndex;
    private BluetoothMesh mMesh;
    private int mModelHandle;
    private long mModelID;
    private int mModelOpcode;
    private int mOpcodeCount;
    private ModelTxMessage mTxMsg;
    private int[] mVendorMsgOpcodes;

    public MeshModel(BluetoothMesh meshInst) {
        this.mMesh = meshInst;
    }

    public MeshModel(BluetoothMesh meshInst, int modelOpcode) {
        this.mMesh = meshInst;
        this.mModelOpcode = modelOpcode;
    }

    public MeshModel(BluetoothMesh meshInst, int modelOpcode, int index) {
        this.mMesh = meshInst;
        this.mModelOpcode = modelOpcode;
        this.mElementIndex = index;
    }

    MeshModel(Parcel in) {
        this.mModelOpcode = in.readInt();
        this.mModelHandle = in.readInt();
        this.mElementIndex = in.readInt();
        this.mModelID = in.readLong();
        this.mVendorMsgOpcodes = in.createIntArray();
        this.mCompanyID = in.readInt();
        this.mOpcodeCount = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mModelOpcode);
        out.writeInt(this.mModelHandle);
        out.writeInt(this.mElementIndex);
        out.writeLong(this.mModelID);
        out.writeIntArray(this.mVendorMsgOpcodes);
        out.writeInt(this.mCompanyID);
        out.writeInt(this.mOpcodeCount);
    }

    /* loaded from: classes.dex */
    public class ModelTxMessage {
        private int mAppKeyIndex;
        private int mDst;
        private int mDstAddrType;
        private int mMsgOpCode;
        private int mNetKeyIndex;
        private int mSrc;
        private int mTtl;
        private int[] mVirtualUUID;

        public ModelTxMessage() {
        }
    }

    /* loaded from: classes.dex */
    public class ModelConfigMessage {
        private int mDst;
        private int mMsgOpCode;
        private int mNetKeyIndex;
        private int mSrc;
        private int mTtl;

        public ModelConfigMessage() {
        }
    }

    public void setTxMessageHeader(int dstAddrType, int dst, int[] virtualUUID, int src, int ttl, int netKeyIndex, int appKeyIndex, int msgOpCode) {
        if (this.mTxMsg == null) {
            this.mTxMsg = new ModelTxMessage();
        }
        this.mTxMsg.mDstAddrType = dstAddrType;
        this.mTxMsg.mDst = dst;
        this.mTxMsg.mVirtualUUID = virtualUUID;
        this.mTxMsg.mSrc = src;
        this.mTxMsg.mTtl = ttl;
        this.mTxMsg.mNetKeyIndex = netKeyIndex;
        this.mTxMsg.mAppKeyIndex = appKeyIndex;
        this.mTxMsg.mMsgOpCode = msgOpCode;
    }

    public void setConfigMessageHeader(int src, int dst, int ttl, int netKeyIndex, int msgOpCode) {
        if (this.mConfigMsg == null) {
            this.mConfigMsg = new ModelConfigMessage();
        }
        this.mConfigMsg.mSrc = src;
        this.mConfigMsg.mDst = dst;
        this.mConfigMsg.mTtl = ttl;
        this.mConfigMsg.mNetKeyIndex = netKeyIndex;
        this.mConfigMsg.mMsgOpCode = msgOpCode;
    }

    public void modelSendPacket() {
        modelSendPacket((int[]) null);
    }

    public void modelSendPacket(int param1) {
        int[] params = {param1};
        modelSendPacket(params);
    }

    public void modelSendPacket(int param1, int param2) {
        int[] params = {param1, param2};
        modelSendPacket(params);
    }

    public void modelSendPacket(int param1, int param2, int param3) {
        int[] params = {param1, param2, param3};
        modelSendPacket(params);
    }

    public void modelSendPacket(int param1, int param2, int param3, int param4) {
        int[] params = {param1, param2, param3, param4};
        modelSendPacket(params);
    }

    public void modelSendPacket(int param1, int param2, int[] array) {
        int arrayLength = array == null ? 0 : array.length;
        int[] params = new int[2 + arrayLength];
        params[0] = param1;
        params[1] = param2;
        if (array != null) {
            System.arraycopy(array, 0, params, 2, arrayLength);
        }
        modelSendPacket(params);
    }

    public void modelSendPacket(int[] params) {
        int[] payload;
        int opcodeLength;
        Log.d(TAG, "modelSendPacket: params=" + Arrays.toString(params));
        ModelTxMessage modelTxMessage = this.mTxMsg;
        if (modelTxMessage == null) {
            Log.e(TAG, "TxMsg is null, should create header first");
        } else if (this.mMesh == null) {
            Log.e(TAG, "BluetoothMesh is null, cannot send");
        } else {
            int paramsLength = params == null ? 0 : params.length;
            if (modelTxMessage.mMsgOpCode < 127) {
                opcodeLength = 1;
                int[] payload2 = new int[1 + paramsLength];
                payload2[0] = this.mTxMsg.mMsgOpCode & 255;
                Log.d(TAG, "modelSendPacket  1-octet opcode = " + this.mTxMsg.mMsgOpCode);
                payload = payload2;
            } else if (this.mTxMsg.mMsgOpCode > 127 && this.mTxMsg.mMsgOpCode < 49152) {
                opcodeLength = 2;
                int[] payload3 = new int[2 + paramsLength];
                payload3[0] = (65280 & this.mTxMsg.mMsgOpCode) >> 8;
                payload3[1] = this.mTxMsg.mMsgOpCode & 255;
                Log.d(TAG, "modelSendPacket  2-octet opcode = " + this.mTxMsg.mMsgOpCode);
                payload = payload3;
            } else if (this.mTxMsg.mMsgOpCode >= 49152) {
                opcodeLength = 3;
                int[] payload4 = new int[3 + paramsLength];
                payload4[0] = (this.mTxMsg.mMsgOpCode & 16711680) >> 16;
                int i = this.mCompanyID;
                payload4[1] = i & 255;
                payload4[2] = (65280 & i) >> 8;
                Log.d(TAG, "modelSendPacket  3-octet opcode = " + this.mTxMsg.mMsgOpCode);
                payload = payload4;
            } else {
                opcodeLength = 0;
                Log.d(TAG, "modelSendPacket  should never here!! ");
                payload = null;
            }
            if (!(params == null || payload == null)) {
                System.arraycopy(params, 0, payload, opcodeLength, params.length);
            }
            this.mMesh.sendPacket(this.mTxMsg.mDst, this.mTxMsg.mSrc, this.mTxMsg.mTtl, this.mTxMsg.mNetKeyIndex, this.mTxMsg.mAppKeyIndex, payload);
        }
    }

    public void modelSendConfigMessage() {
        ModelConfigMessage modelConfigMessage = this.mConfigMsg;
        if (modelConfigMessage == null) {
            Log.e(TAG, "TxMsg is null, should create header first");
            return;
        }
        BluetoothMesh bluetoothMesh = this.mMesh;
        if (bluetoothMesh == null) {
            Log.e(TAG, "BluetoothMesh is null, cannot send");
        } else {
            bluetoothMesh.sendConfigMessage(modelConfigMessage.mDst, this.mConfigMsg.mSrc, this.mConfigMsg.mTtl, this.mConfigMsg.mNetKeyIndex, this.mConfigMsg.mMsgOpCode, null);
        }
    }

    public void modelSendConfigMessage(ConfigMessageParams param) {
        ModelConfigMessage modelConfigMessage = this.mConfigMsg;
        if (modelConfigMessage == null) {
            Log.e(TAG, "TxMsg is null, should create header first");
            return;
        }
        BluetoothMesh bluetoothMesh = this.mMesh;
        if (bluetoothMesh == null) {
            Log.e(TAG, "BluetoothMesh is null, cannot send");
        } else {
            bluetoothMesh.sendConfigMessage(modelConfigMessage.mDst, this.mConfigMsg.mSrc, this.mConfigMsg.mTtl, this.mConfigMsg.mNetKeyIndex, this.mConfigMsg.mMsgOpCode, param);
        }
    }

    public int[] twoOctetsToArray(int src) {
        if (src > 65535 || src < 0) {
            Log.w(TAG, "Param should be 0x0000~0xFFFF. Wrong param 0x" + Integer.toHexString(src) + ", will keep the last 2 bytes 0x" + Integer.toHexString(65535 & src));
        }
        int[] dstArray = {src & 255, (65280 & src) >> 8};
        return dstArray;
    }

    public void setModelOpcode(int modelOpcode) {
        this.mModelOpcode = modelOpcode;
    }

    public void setElementIndex(int index) {
        this.mElementIndex = index;
    }

    public void setModelHandle(int handle) {
        this.mModelHandle = handle;
    }

    public void setModelId(long modelID) {
        this.mModelID = modelID;
    }

    public void setVendorMsgOpcodes(int[] opcodes) {
        this.mVendorMsgOpcodes = opcodes;
    }

    public void setCompanyId(int companyID) {
        this.mCompanyID = companyID;
    }

    public void setOpcodeCount(int opcodeCount) {
        this.mOpcodeCount = opcodeCount;
    }

    public int getModelOpcode() {
        return this.mModelOpcode;
    }

    public int getElementIndex() {
        return this.mElementIndex;
    }

    public int getModelHandle() {
        return this.mModelHandle;
    }

    public long getModelId() {
        return this.mModelID;
    }

    public int[] getVendorMsgOpcodes() {
        return this.mVendorMsgOpcodes;
    }

    public int getCompanyId() {
        return this.mCompanyID;
    }

    public int getOpcodeCount() {
        return this.mOpcodeCount;
    }

    public void onMsgHandler(int modelHandle, BluetoothMeshAccessRxMessage msg) {
    }

    public void onPublishTimeoutCallback(int modelHandle) {
    }
}
