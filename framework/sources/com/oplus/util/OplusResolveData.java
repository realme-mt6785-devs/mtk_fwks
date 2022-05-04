package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes4.dex */
public final class OplusResolveData implements Parcelable {
    public static final Parcelable.Creator<OplusResolveData> CREATOR = new Parcelable.Creator<OplusResolveData>() { // from class: com.oplus.util.OplusResolveData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusResolveData createFromParcel(Parcel in) {
            return new OplusResolveData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusResolveData[] newArray(int size) {
            return new OplusResolveData[size];
        }
    };
    private HashMap<String, List<String>> mMap1 = new HashMap<>();
    private HashMap<String, List<String>> mMap2 = new HashMap<>();
    private HashMap<String, List<String>> mMap3 = new HashMap<>();
    private HashMap<String, List<String>> mMap4 = new HashMap<>();
    private HashMap<String, List<String>> mMap5 = new HashMap<>();
    private HashMap<String, String> mMap6 = new HashMap<>();
    private HashMap<String, List<String>> mMap7 = new HashMap<>();

    public OplusResolveData() {
    }

    public OplusResolveData(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeMap(this.mMap1);
        out.writeMap(this.mMap2);
        out.writeMap(this.mMap3);
        out.writeMap(this.mMap4);
        out.writeMap(this.mMap5);
        out.writeMap(this.mMap6);
        out.writeMap(this.mMap7);
    }

    public void readFromParcel(Parcel in) {
        this.mMap1 = in.readHashMap(HashMap.class.getClassLoader());
        this.mMap2 = in.readHashMap(HashMap.class.getClassLoader());
        this.mMap3 = in.readHashMap(HashMap.class.getClassLoader());
        this.mMap4 = in.readHashMap(HashMap.class.getClassLoader());
        this.mMap5 = in.readHashMap(HashMap.class.getClassLoader());
        this.mMap6 = in.readHashMap(HashMap.class.getClassLoader());
        this.mMap7 = in.readHashMap(HashMap.class.getClassLoader());
    }

    public HashMap<String, List<String>> getBlackResolveMap() {
        return this.mMap1;
    }

    public HashMap<String, List<String>> getResolveMap() {
        return this.mMap2;
    }

    public HashMap<String, List<String>> getChooseMap() {
        return this.mMap3;
    }

    public HashMap<String, List<String>> getBlackChoosePackageMap() {
        return this.mMap4;
    }

    public HashMap<String, List<String>> getBlackChooseActivityMap() {
        return this.mMap5;
    }

    public HashMap<String, String> getIconName() {
        return this.mMap6;
    }

    public HashMap<String, List<String>> getApkSubContractMap() {
        return this.mMap7;
    }
}
