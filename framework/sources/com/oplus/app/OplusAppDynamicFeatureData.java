package com.oplus.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusAppDynamicFeatureData implements Parcelable {
    public static final Parcelable.Creator<OplusAppDynamicFeatureData> CREATOR = new Parcelable.Creator<OplusAppDynamicFeatureData>() { // from class: com.oplus.app.OplusAppDynamicFeatureData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAppDynamicFeatureData createFromParcel(Parcel in) {
            return new OplusAppDynamicFeatureData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAppDynamicFeatureData[] newArray(int size) {
            return new OplusAppDynamicFeatureData[size];
        }
    };
    public String mActivityName;
    public String mPackageName;
    public Map<String, Integer> mComponentNames = new ArrayMap();
    public Map<String, Integer> mIdNames = new ArrayMap();
    public List<String> mDatabaseNames = new ArrayList();
    public List<String> mTexts = new ArrayList();

    public OplusAppDynamicFeatureData() {
    }

    public OplusAppDynamicFeatureData(OplusAppDynamicFeatureData data) {
        if (data != null) {
            this.mPackageName = data.mPackageName;
            this.mActivityName = data.mActivityName;
            this.mComponentNames.putAll(data.mComponentNames);
            this.mIdNames.putAll(data.mIdNames);
            this.mDatabaseNames.addAll(data.mDatabaseNames);
            this.mTexts.addAll(data.mTexts);
        }
    }

    protected OplusAppDynamicFeatureData(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.mPackageName = in.readString();
        this.mActivityName = in.readString();
        in.readMap(this.mComponentNames, Map.class.getClassLoader());
        in.readMap(this.mIdNames, Map.class.getClassLoader());
        in.readStringList(this.mDatabaseNames);
        in.readStringList(this.mTexts);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPackageName);
        dest.writeString(this.mActivityName);
        dest.writeMap(this.mComponentNames);
        dest.writeMap(this.mIdNames);
        dest.writeStringList(this.mDatabaseNames);
        dest.writeStringList(this.mTexts);
    }

    public String toString() {
        return "OplusAppDynamicFeatureData{package=" + this.mPackageName + ", activity=" + this.mActivityName + ", componentName=" + this.mComponentNames + ", idName=" + this.mIdNames + ", databaseName=" + this.mDatabaseNames + ", text=" + this.mTexts + "}";
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setActivityName(String activityName) {
        this.mActivityName = activityName;
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    public void setComponentNames(Map<String, Integer> componentNames) {
        this.mComponentNames.clear();
        this.mComponentNames.putAll(componentNames);
    }

    public Map<String, Integer> getComponentNames() {
        return this.mComponentNames;
    }

    public void setIdNames(Map<String, Integer> idNames) {
        this.mIdNames.clear();
        this.mIdNames.putAll(idNames);
    }

    public Map<String, Integer> getIdNames() {
        return this.mIdNames;
    }

    public void setDatabaseNames(List<String> databaseNames) {
        this.mDatabaseNames.clear();
        this.mDatabaseNames.addAll(databaseNames);
    }

    public List<String> getDatabaseNames() {
        return this.mDatabaseNames;
    }

    public void setTexts(List<String> texts) {
        this.mTexts.clear();
        this.mTexts.addAll(texts);
    }

    public List<String> getTexts() {
        return this.mTexts;
    }
}
