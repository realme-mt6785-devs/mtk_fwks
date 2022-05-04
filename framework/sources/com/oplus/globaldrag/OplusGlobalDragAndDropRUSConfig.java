package com.oplus.globaldrag;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusGlobalDragAndDropRUSConfig implements Parcelable {
    public static final Parcelable.Creator<OplusGlobalDragAndDropRUSConfig> CREATOR = new Parcelable.Creator<OplusGlobalDragAndDropRUSConfig>() { // from class: com.oplus.globaldrag.OplusGlobalDragAndDropRUSConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusGlobalDragAndDropRUSConfig createFromParcel(Parcel in) {
            return new OplusGlobalDragAndDropRUSConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusGlobalDragAndDropRUSConfig[] newArray(int size) {
            return new OplusGlobalDragAndDropRUSConfig[size];
        }
    };
    private String mFilterName;
    private boolean mGlobalDragShareWindowSwitch;
    private List<OplusGlobalDragAndDropRUSConfigItem> mSupportDragPkgList;
    private List<OplusGlobalDragAndDropRUSConfigItem> mSupportDropPkgList;
    private List<OplusGlobalDragAndDropRUSConfigItem> mUnSupportCpnList;
    private int mVersion;

    public OplusGlobalDragAndDropRUSConfig() {
        this.mSupportDragPkgList = new ArrayList();
        this.mSupportDropPkgList = new ArrayList();
        this.mUnSupportCpnList = new ArrayList();
    }

    protected OplusGlobalDragAndDropRUSConfig(Parcel in) {
        this.mSupportDragPkgList = new ArrayList();
        this.mSupportDropPkgList = new ArrayList();
        this.mUnSupportCpnList = new ArrayList();
        this.mVersion = in.readInt();
        this.mGlobalDragShareWindowSwitch = in.readByte() != 0;
        this.mFilterName = in.readStringNoHelper();
        this.mSupportDragPkgList = in.createTypedArrayList(OplusGlobalDragAndDropRUSConfigItem.CREATOR);
        this.mSupportDropPkgList = in.createTypedArrayList(OplusGlobalDragAndDropRUSConfigItem.CREATOR);
        this.mUnSupportCpnList = in.createTypedArrayList(OplusGlobalDragAndDropRUSConfigItem.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mVersion);
        dest.writeByte(this.mGlobalDragShareWindowSwitch ? (byte) 1 : (byte) 0);
        dest.writeStringNoHelper(this.mFilterName);
        dest.writeTypedList(this.mSupportDragPkgList);
        dest.writeTypedList(this.mSupportDropPkgList);
        dest.writeTypedList(this.mUnSupportCpnList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public void setVersion(int version) {
        this.mVersion = version;
    }

    public boolean getGlobalDragShareSwitch() {
        return this.mGlobalDragShareWindowSwitch;
    }

    public void setGlobalDragShareSwitch(boolean globalDragShareSwitch) {
        this.mGlobalDragShareWindowSwitch = globalDragShareSwitch;
    }

    public List<OplusGlobalDragAndDropRUSConfigItem> getSupportDragPkgList() {
        return this.mSupportDragPkgList;
    }

    public void setSupportDragPkgList(List<OplusGlobalDragAndDropRUSConfigItem> supportDragPkgList) {
        this.mSupportDragPkgList = supportDragPkgList;
    }

    public List<OplusGlobalDragAndDropRUSConfigItem> getSupportDropPkgList() {
        return this.mSupportDropPkgList;
    }

    public void setSupportDropPkgList(List<OplusGlobalDragAndDropRUSConfigItem> supportDropPkgList) {
        this.mSupportDropPkgList = supportDropPkgList;
    }

    public List<OplusGlobalDragAndDropRUSConfigItem> getUnSupportCpnList() {
        return this.mUnSupportCpnList;
    }

    public void setUnSupportCpnList(List<OplusGlobalDragAndDropRUSConfigItem> unSupportCpnList) {
        this.mUnSupportCpnList = unSupportCpnList;
    }

    public String getFilterName() {
        return this.mFilterName;
    }

    public void setFilterName(String filterName) {
        this.mFilterName = filterName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusGlobalDragAndDropRUSConfig{");
        sb.append("version = " + this.mVersion);
        sb.append("\nfilter-name = " + this.mFilterName);
        sb.append("\nmGlobalDragShareWindowSwitch = " + this.mGlobalDragShareWindowSwitch);
        sb.append("\nmSupportDragPkgList = " + this.mSupportDragPkgList);
        sb.append("\nmSupportDropPkgList = " + this.mSupportDropPkgList);
        sb.append("}");
        return sb.toString();
    }
}
