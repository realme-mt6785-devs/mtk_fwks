package com.oplus.app;

import android.media.MediaMetrics;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes4.dex */
public final class OplusAppSwitchConfig implements Parcelable {
    public static final Parcelable.Creator<OplusAppSwitchConfig> CREATOR = new Parcelable.Creator<OplusAppSwitchConfig>() { // from class: com.oplus.app.OplusAppSwitchConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAppSwitchConfig createFromParcel(Parcel source) {
            return new OplusAppSwitchConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAppSwitchConfig[] newArray(int size) {
            return new OplusAppSwitchConfig[size];
        }
    };
    public static final int TYPE_ACTIVITY = 1;
    public static final int TYPE_PACKAGE = 2;
    public HashSet<String> mActivitySet;
    private SparseArray<List<String>> mConfigs;
    public HashSet<String> mPackageSet;
    public int observerFingerPrint;

    public OplusAppSwitchConfig() {
        this.mConfigs = new SparseArray<>();
        this.mActivitySet = new HashSet<>();
        this.mPackageSet = new HashSet<>();
    }

    public OplusAppSwitchConfig(Parcel source) {
        this.mConfigs = new SparseArray<>();
        this.mActivitySet = new HashSet<>();
        this.mPackageSet = new HashSet<>();
        SparseArray<List<String>> readSparseArray = source.readSparseArray(null);
        this.mConfigs = readSparseArray;
        if (readSparseArray == null) {
            this.mConfigs = new SparseArray<>();
        }
        initSearchSet(1);
        initSearchSet(2);
        this.observerFingerPrint = source.readInt();
    }

    private void initSearchSet(int type) {
        List<String> configList = this.mConfigs.get(type);
        switch (type) {
            case 1:
                this.mActivitySet.clear();
                if (configList != null && configList.size() != 0) {
                    this.mActivitySet.addAll(configList);
                    return;
                }
                return;
            case 2:
                this.mPackageSet.clear();
                if (configList != null && configList.size() != 0) {
                    this.mPackageSet.addAll(configList);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public List<String> getConfigs(int type) {
        return this.mConfigs.get(type);
    }

    public void addAppConfig(int type, List<String> list) {
        this.mConfigs.put(type, list);
        initSearchSet(type);
    }

    public void removeAppConfig(int type) {
        this.mConfigs.remove(type);
        initSearchSet(type);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSparseArray(this.mConfigs);
        dest.writeInt(this.observerFingerPrint);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("OplusAppSwitchConfig = { ");
        String pkg = "" + this.mConfigs;
        sb.append(" mConfigs = " + pkg.replace(MediaMetrics.SEPARATOR, "@@").replace("com", "TOM").replace("oplusos", "CO").replace("oplus", "NM").replace("oplus", "OP"));
        sb.append(" observerFingerPrint = " + this.observerFingerPrint);
        sb.append("}");
        return sb.toString();
    }
}
