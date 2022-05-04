package com.oplus.multiapp;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusMultiAppConfig implements Parcelable {
    public static final int CHOOSE_TYPE_NONE = -1;
    public static final int CHOOSE_TYPE_RECENT_TASK = 0;
    public static final int CHOOSE_TYPE_SKIP = 1;
    public static final Parcelable.Creator<OplusMultiAppConfig> CREATOR = new Parcelable.Creator<OplusMultiAppConfig>() { // from class: com.oplus.multiapp.OplusMultiAppConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusMultiAppConfig createFromParcel(Parcel in) {
            return new OplusMultiAppConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusMultiAppConfig[] newArray(int size) {
            return new OplusMultiAppConfig[size];
        }
    };
    private List<String> mAllowedPkgList;
    private String mAndroidVersionName;
    private int mAndroidVersionNumber;
    private List<ComponentName> mChooseRecentList;
    private ArrayMap<String, List<ComponentName>> mChooseSkipMap;
    private List<String> mCrossAuthorityList;
    private volatile int mMaxCreatedNum;
    private List<String> mOpAllowedPkgList;
    private List<String> mRelatedPkgList;
    private String mVersionName;
    private int mVersionNum;

    public String getVersionName() {
        return this.mVersionName;
    }

    public void setVersionName(String versionName) {
        this.mVersionName = versionName;
    }

    public int getVersionNum() {
        return this.mVersionNum;
    }

    public void setVersionNum(int versionNum) {
        this.mVersionNum = versionNum;
    }

    public String getAndroidVersionName() {
        return this.mAndroidVersionName;
    }

    public void setAndroidVersionName(String androidVersionName) {
        this.mAndroidVersionName = androidVersionName;
    }

    public int getAndroidVersionNumber() {
        return this.mAndroidVersionNumber;
    }

    public void setAndroidVersionNumber(int androidVersionNumber) {
        this.mAndroidVersionNumber = androidVersionNumber;
    }

    public int getMaxCreatedNum() {
        return this.mMaxCreatedNum;
    }

    public void setMaxCreatedNum(int mMaxCreatedNum) {
        this.mMaxCreatedNum = mMaxCreatedNum;
    }

    public List<String> getRelatedPkgList() {
        return this.mRelatedPkgList;
    }

    public void setRelatedPkgList(List<String> relatedPkgList) {
        this.mRelatedPkgList = relatedPkgList;
    }

    public List<String> getAllowedPkgList() {
        return this.mAllowedPkgList;
    }

    public List<String> getOpAllowedPkgList() {
        return this.mOpAllowedPkgList;
    }

    public void setOpAllowedPkgList(List<String> opAllowedPkgList) {
        this.mOpAllowedPkgList = opAllowedPkgList;
    }

    public void setAllowedPkgList(List<String> allowedPkgList) {
        this.mAllowedPkgList = allowedPkgList;
    }

    public List<ComponentName> getChooseRecentList() {
        return this.mChooseRecentList;
    }

    public void setChooseRecentList(List<ComponentName> chooseRecentList) {
        this.mChooseRecentList = chooseRecentList;
    }

    public ArrayMap<String, List<ComponentName>> getChooseSkipMap() {
        return this.mChooseSkipMap;
    }

    public void setChooseSkipMap(ArrayMap<String, List<ComponentName>> filter) {
        this.mChooseSkipMap = filter;
    }

    public List<String> getCrossAuthorityList() {
        return this.mCrossAuthorityList;
    }

    public void setCrossAuthorityList(List<String> crossAuthorityList) {
        this.mCrossAuthorityList = crossAuthorityList;
    }

    public OplusMultiAppConfig() {
        this.mVersionName = "error";
        this.mVersionNum = -1;
        this.mAndroidVersionName = "";
        this.mAndroidVersionNumber = -1;
        this.mMaxCreatedNum = 2;
        this.mRelatedPkgList = new ArrayList();
        this.mAllowedPkgList = new ArrayList();
        this.mCrossAuthorityList = new ArrayList();
        this.mChooseRecentList = new ArrayList();
        this.mOpAllowedPkgList = new ArrayList();
        this.mChooseSkipMap = new ArrayMap<>();
    }

    public OplusMultiAppConfig(Parcel in) {
        this.mVersionName = "error";
        this.mVersionNum = -1;
        this.mAndroidVersionName = "";
        this.mAndroidVersionNumber = -1;
        this.mMaxCreatedNum = 2;
        this.mRelatedPkgList = new ArrayList();
        this.mAllowedPkgList = new ArrayList();
        this.mCrossAuthorityList = new ArrayList();
        this.mChooseRecentList = new ArrayList();
        this.mOpAllowedPkgList = new ArrayList();
        this.mChooseSkipMap = new ArrayMap<>();
        this.mVersionName = in.readString();
        this.mVersionNum = in.readInt();
        this.mMaxCreatedNum = in.readInt();
        in.readStringList(this.mAllowedPkgList);
        in.readStringList(this.mRelatedPkgList);
        this.mChooseRecentList = in.readArrayList(ComponentName.class.getClassLoader());
        in.readMap(this.mChooseSkipMap, ArrayList.class.getClassLoader());
        in.readStringList(this.mCrossAuthorityList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mVersionName);
        dest.writeInt(this.mVersionNum);
        dest.writeInt(this.mMaxCreatedNum);
        dest.writeStringList(this.mAllowedPkgList);
        dest.writeStringList(this.mRelatedPkgList);
        dest.writeList(this.mChooseRecentList);
        dest.writeMap(this.mChooseSkipMap);
        dest.writeStringList(this.mCrossAuthorityList);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MultiAppConfig[versionName: ");
        sb.append(this.mVersionName);
        sb.append(" num: ");
        sb.append(this.mVersionNum);
        sb.append("]\n");
        sb.append("maxCreateNum[");
        sb.append(this.mMaxCreatedNum);
        sb.append(")]\n");
        sb.append("Allowed[\n");
        for (String name : this.mAllowedPkgList) {
            sb.append(name);
            sb.append("\n");
        }
        sb.append("]\n");
        sb.append("Related[\n");
        for (String name2 : this.mRelatedPkgList) {
            sb.append(name2);
            sb.append("\n");
        }
        sb.append("]\n");
        sb.append("Filter[\n");
        sb.append(" RecentTask(\n");
        for (ComponentName name3 : this.mChooseRecentList) {
            sb.append(name3);
        }
        sb.append(" )");
        sb.append("]\n");
        sb.append(" ChooseSkip[\n");
        for (String name4 : this.mChooseSkipMap.keySet()) {
            sb.append(name4);
            sb.append("->[\n");
            List<ComponentName> componentNameList = this.mChooseSkipMap.get(name4);
            if (componentNameList != null) {
                for (ComponentName com2 : componentNameList) {
                    sb.append(com2.toShortString());
                    sb.append("\n");
                }
            } else {
                sb.append("null");
                sb.append("\n");
            }
            sb.append("]");
        }
        sb.append("]\n");
        sb.append("CrossAuthority[\n");
        for (String name5 : this.mCrossAuthorityList) {
            sb.append(name5);
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
