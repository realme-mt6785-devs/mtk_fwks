package com.oplus.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.PrintWriter;
/* loaded from: classes4.dex */
public class OplusRemovableAppInfo implements Parcelable {
    private String baseCodePath;
    private String codePath;
    private long fileSize;
    private String packageName;
    private boolean uninstalled;
    private long versionCode;
    private String versionName;
    private static final String TAG = OplusRemovableAppInfo.class.getSimpleName();
    public static final Parcelable.Creator<OplusRemovableAppInfo> CREATOR = new Parcelable.Creator<OplusRemovableAppInfo>() { // from class: com.oplus.content.OplusRemovableAppInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusRemovableAppInfo createFromParcel(Parcel source) {
            return new OplusRemovableAppInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusRemovableAppInfo[] newArray(int size) {
            return new OplusRemovableAppInfo[size];
        }
    };

    public OplusRemovableAppInfo(String packageName) {
        this.packageName = "";
        this.versionCode = -1L;
        this.versionName = "";
        this.codePath = "";
        this.baseCodePath = "";
        this.uninstalled = false;
        this.fileSize = 0L;
        this.packageName = packageName;
    }

    public OplusRemovableAppInfo(Parcel in) {
        this.packageName = "";
        this.versionCode = -1L;
        this.versionName = "";
        this.codePath = "";
        this.baseCodePath = "";
        this.uninstalled = false;
        this.fileSize = 0L;
        this.packageName = in.readString();
        this.versionCode = in.readLong();
        this.versionName = in.readString();
        this.codePath = in.readString();
        this.baseCodePath = in.readString();
        this.uninstalled = in.readBoolean();
        this.fileSize = in.readLong();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (TextUtils.isEmpty(this.packageName)) {
            dest.writeString("");
        } else {
            dest.writeString(this.packageName);
        }
        dest.writeLong(this.versionCode);
        if (TextUtils.isEmpty(this.versionName)) {
            dest.writeString("");
        } else {
            dest.writeString(this.versionName);
        }
        if (TextUtils.isEmpty(this.codePath)) {
            dest.writeString("");
        } else {
            dest.writeString(this.codePath);
        }
        if (TextUtils.isEmpty(this.baseCodePath)) {
            dest.writeString("");
        } else {
            dest.writeString(this.baseCodePath);
        }
        dest.writeBoolean(this.uninstalled);
        dest.writeLong(this.fileSize);
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getCodePath() {
        return this.codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public String getBaseCodePath() {
        return this.baseCodePath;
    }

    public void setBaseCodePath(String baseCodePath) {
        this.baseCodePath = baseCodePath;
    }

    public boolean isUninstalled() {
        return this.uninstalled;
    }

    public void setUninstalled(boolean uninstalled) {
        this.uninstalled = uninstalled;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void dump(PrintWriter pw) {
        pw.append((CharSequence) ("packageName = " + this.packageName + ", versionCode = " + this.versionCode + ", versionName = " + this.versionName + ", codePath = " + this.codePath + ", baseCodePath = " + this.baseCodePath + ", uninstalled = " + this.uninstalled + ", fileSize = " + this.fileSize));
    }
}
