package com.oplus.globaldrag;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusGlobalDragAndDropRUSConfigItem implements Parcelable {
    public static final Parcelable.Creator<OplusGlobalDragAndDropRUSConfigItem> CREATOR = new Parcelable.Creator<OplusGlobalDragAndDropRUSConfigItem>() { // from class: com.oplus.globaldrag.OplusGlobalDragAndDropRUSConfigItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusGlobalDragAndDropRUSConfigItem createFromParcel(Parcel in) {
            return new OplusGlobalDragAndDropRUSConfigItem(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusGlobalDragAndDropRUSConfigItem[] newArray(int size) {
            return new OplusGlobalDragAndDropRUSConfigItem[size];
        }
    };
    private int mAppVersionCode;
    private String mAttrStr;
    private int mFeatureVersionCode;

    public OplusGlobalDragAndDropRUSConfigItem() {
        this.mAppVersionCode = 0;
        this.mFeatureVersionCode = 1;
    }

    protected OplusGlobalDragAndDropRUSConfigItem(Parcel in) {
        this.mAppVersionCode = 0;
        this.mFeatureVersionCode = 1;
        this.mAttrStr = in.readStringNoHelper();
        this.mAppVersionCode = in.readInt();
        this.mFeatureVersionCode = in.readInt();
    }

    public String getAttrStr() {
        return this.mAttrStr;
    }

    public void setAttrStr(String attrStr) {
        this.mAttrStr = attrStr;
    }

    public int getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public void setAppVersionCode(int appVersionCode) {
        this.mAppVersionCode = appVersionCode;
    }

    public int getFeatureVersionCode() {
        return this.mFeatureVersionCode;
    }

    public void setFeatureVersionCode(int featureVersionCode) {
        this.mFeatureVersionCode = featureVersionCode;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringNoHelper(this.mAttrStr);
        dest.writeInt(this.mAppVersionCode);
        dest.writeInt(this.mFeatureVersionCode);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusGlobalDragAndDropRUSConfigItem{");
        sb.append("attr=" + this.mAttrStr);
        sb.append(", AppVersionCode=" + this.mAppVersionCode);
        sb.append(", FeatureVersionCode=" + this.mFeatureVersionCode);
        sb.append("}");
        return sb.toString();
    }
}
