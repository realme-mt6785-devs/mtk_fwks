package com.oplus.ota;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusSystemUpdateInfo implements Parcelable {
    public static final Parcelable.Creator<OplusSystemUpdateInfo> CREATOR = new Parcelable.Creator<OplusSystemUpdateInfo>() { // from class: com.oplus.ota.OplusSystemUpdateInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusSystemUpdateInfo createFromParcel(Parcel in) {
            return new OplusSystemUpdateInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusSystemUpdateInfo[] newArray(int size) {
            return new OplusSystemUpdateInfo[size];
        }
    };
    public static final int TYPE_NO_UPDATE = 0;
    public static final int TYPE_OTA_UPDATE = 1;
    public static final int TYPE_RECOVERY_UPDATE = 3;
    public static final int TYPE_SAU_UPDATE = 2;
    private String mFailedMsg;
    private int mFailedType;
    private boolean mUpdateSucc;
    private int mUpdateType;
    private boolean mUpdated;

    public OplusSystemUpdateInfo() {
        this.mUpdated = false;
        this.mUpdateType = 0;
        this.mUpdateSucc = false;
        this.mFailedType = 0;
        this.mFailedMsg = "";
        this.mUpdated = false;
        this.mUpdateType = 0;
        this.mUpdateSucc = false;
    }

    public OplusSystemUpdateInfo(int updateType, boolean updateSucc, int failedType, String failedMsg) {
        this.mUpdated = false;
        this.mUpdateType = 0;
        this.mUpdateSucc = false;
        this.mFailedType = 0;
        this.mFailedMsg = "";
        this.mUpdated = true;
        this.mUpdateType = updateType;
        this.mUpdateSucc = updateSucc;
        this.mFailedType = failedType;
        this.mFailedMsg = failedMsg;
    }

    public OplusSystemUpdateInfo(Parcel in) {
        this.mUpdated = false;
        this.mUpdateType = 0;
        this.mUpdateSucc = false;
        this.mFailedType = 0;
        this.mFailedMsg = "";
        this.mUpdated = in.readBoolean();
        this.mUpdateType = in.readInt();
        this.mUpdateSucc = in.readBoolean();
        this.mFailedType = in.readInt();
        this.mFailedMsg = in.readString();
    }

    public boolean isUpdated() {
        return this.mUpdated;
    }

    public int getUpdateType() {
        return this.mUpdateType;
    }

    public boolean isUpdateSucc() {
        return this.mUpdateSucc;
    }

    public int getFailedType() {
        return this.mFailedType;
    }

    public String getFailedMsg() {
        return this.mFailedMsg;
    }

    public void setUpdateType(int updateType) {
        this.mUpdated = true;
        this.mUpdateType = updateType;
    }

    public void setUpdateSucc(boolean updateSucc) {
        this.mUpdated = true;
        this.mUpdateSucc = updateSucc;
    }

    public void setFailedType(int mFailedType) {
        this.mFailedType = mFailedType;
    }

    public void setFailedMsg(String mFailedMsg) {
        this.mFailedMsg = mFailedMsg;
    }

    public String toString() {
        return "OplusSystemUpdateInfo{ updated:" + this.mUpdated + " updateType:" + this.mUpdateType + " updateSucc:" + this.mUpdateSucc + " failedType:" + this.mFailedType + " failedMsg:" + this.mFailedMsg + "}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBoolean(this.mUpdated);
        dest.writeInt(this.mUpdateType);
        dest.writeBoolean(this.mUpdateSucc);
        dest.writeInt(this.mFailedType);
        dest.writeString(this.mFailedMsg);
    }
}
