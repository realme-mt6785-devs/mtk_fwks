package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public final class OplusDisplayOptimizationData implements Parcelable {
    public static final Parcelable.Creator<OplusDisplayOptimizationData> CREATOR = new Parcelable.Creator<OplusDisplayOptimizationData>() { // from class: com.oplus.util.OplusDisplayOptimizationData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusDisplayOptimizationData createFromParcel(Parcel in) {
            return new OplusDisplayOptimizationData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusDisplayOptimizationData[] newArray(int size) {
            return new OplusDisplayOptimizationData[size];
        }
    };
    private static final boolean DBG = false;
    private static final String TAG = "OplusDisplayOptimizationData";
    private boolean mEnableDisplatOpt = true;
    private boolean mEnableGraphicAccelerationSwitch = true;
    private List<String> mExcludeProcessList = new ArrayList();
    private List<String> mWhiteList = new ArrayList();
    private List<String> mBlackList = new ArrayList();
    private List<String> mSpecialList = new ArrayList();
    private List<String> mExcludeWindowList = new ArrayList();
    private int mEnablePolicy = 0;

    public OplusDisplayOptimizationData() {
    }

    public OplusDisplayOptimizationData(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringList(this.mExcludeProcessList);
        out.writeStringList(this.mWhiteList);
        out.writeStringList(this.mBlackList);
        out.writeStringList(this.mSpecialList);
        out.writeStringList(this.mExcludeWindowList);
        out.writeByte(this.mEnableDisplatOpt ? (byte) 1 : (byte) 0);
        out.writeByte(this.mEnableGraphicAccelerationSwitch ? (byte) 1 : (byte) 0);
        out.writeInt(this.mEnablePolicy);
    }

    public void readFromParcel(Parcel in) {
        this.mExcludeProcessList = in.createStringArrayList();
        this.mWhiteList = in.createStringArrayList();
        this.mBlackList = in.createStringArrayList();
        this.mSpecialList = in.createStringArrayList();
        this.mExcludeWindowList = in.createStringArrayList();
        boolean z = true;
        this.mEnableDisplatOpt = in.readByte() != 0;
        if (in.readByte() == 0) {
            z = false;
        }
        this.mEnableGraphicAccelerationSwitch = z;
        this.mEnablePolicy = in.readInt();
    }

    public List<String> getExcludeProcessList() {
        return this.mExcludeProcessList;
    }

    public void setExcludeProcessList(List<String> excludeProcessList) {
        this.mExcludeProcessList = excludeProcessList;
    }

    public boolean getDisplatOptEnabled() {
        return this.mEnableDisplatOpt;
    }

    public void setDisplatOptEnabled(boolean enabled) {
        this.mEnableDisplatOpt = enabled;
    }

    public List<String> getWhiteList() {
        return this.mWhiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.mWhiteList = whiteList;
    }

    public List<String> getBlackList() {
        return this.mBlackList;
    }

    public void setBlackList(List<String> blackList) {
        this.mBlackList = blackList;
    }

    public List<String> getSpecialList() {
        return this.mSpecialList;
    }

    public void setSpecialList(List<String> specialList) {
        this.mSpecialList = specialList;
    }

    public List<String> getExcludeWindowList() {
        return this.mExcludeWindowList;
    }

    public void setExcludeWindowList(List<String> excludeWindowList) {
        this.mExcludeWindowList = excludeWindowList;
    }

    public boolean getGraphicAccelerationSwitchEnabled() {
        return this.mEnableGraphicAccelerationSwitch;
    }

    public void setGraphicAccelerationSwitchEnabled(boolean enabled) {
        this.mEnableGraphicAccelerationSwitch = enabled;
    }

    public int getEnablePolicy() {
        return this.mEnablePolicy;
    }

    public void setEnablePolicy(int policy) {
        this.mEnablePolicy = policy;
    }
}
