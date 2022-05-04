package com.oplus.zoomwindow;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusZoomWindowSupportAppInfo implements Parcelable {
    public static final Parcelable.Creator<OplusZoomWindowSupportAppInfo> CREATOR = new Parcelable.Creator<OplusZoomWindowSupportAppInfo>() { // from class: com.oplus.zoomwindow.OplusZoomWindowSupportAppInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowSupportAppInfo createFromParcel(Parcel in) {
            return new OplusZoomWindowSupportAppInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowSupportAppInfo[] newArray(int size) {
            return new OplusZoomWindowSupportAppInfo[size];
        }
    };
    private int mAppVersionCode;
    private int mFeatureVersionCode;
    private String mPkg;

    public OplusZoomWindowSupportAppInfo() {
    }

    public String getPkg() {
        return this.mPkg;
    }

    public int getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public int getFeatureVersionCode() {
        return this.mFeatureVersionCode;
    }

    public void setPkg(String pkg) {
        this.mPkg = pkg;
    }

    public void setAppVersionCode(int appVersionCode) {
        this.mAppVersionCode = appVersionCode;
    }

    public void setFeatureVersionCode(int featureVersionCode) {
        this.mFeatureVersionCode = featureVersionCode;
    }

    public OplusZoomWindowSupportAppInfo(Parcel in) {
        this.mPkg = in.readString();
        this.mAppVersionCode = in.readInt();
        this.mFeatureVersionCode = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPkg);
        dest.writeInt(this.mAppVersionCode);
        dest.writeInt(this.mFeatureVersionCode);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusZoomWindowSupportAppInfo{");
        sb.append("pkgName=" + this.mPkg);
        sb.append(", AppVersionCode=" + this.mAppVersionCode);
        sb.append(", FeatureVersionCode=" + this.mFeatureVersionCode);
        sb.append("}");
        return sb.toString();
    }
}
