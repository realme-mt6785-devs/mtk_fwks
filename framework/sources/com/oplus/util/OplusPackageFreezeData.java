package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class OplusPackageFreezeData implements Parcelable {
    public static final Parcelable.Creator<OplusPackageFreezeData> CREATOR = new Parcelable.Creator<OplusPackageFreezeData>() { // from class: com.oplus.util.OplusPackageFreezeData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusPackageFreezeData createFromParcel(Parcel in) {
            return new OplusPackageFreezeData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusPackageFreezeData[] newArray(int size) {
            return new OplusPackageFreezeData[size];
        }
    };
    private int mPid = 0;
    private int mUid = 0;
    private int mCurAdj = 10000;
    private int mUserId = 0;
    private String mProcessName = "";
    private List<String> mPackageList = new ArrayList();

    public OplusPackageFreezeData() {
    }

    public OplusPackageFreezeData(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mPid);
        out.writeInt(this.mUid);
        out.writeInt(this.mCurAdj);
        out.writeInt(this.mUserId);
        out.writeString(this.mProcessName);
        out.writeStringList(this.mPackageList);
    }

    public void readFromParcel(Parcel in) {
        this.mPid = in.readInt();
        this.mUid = in.readInt();
        this.mCurAdj = in.readInt();
        this.mUserId = in.readInt();
        this.mProcessName = in.readString();
        this.mPackageList = in.createStringArrayList();
    }

    public int hashCode() {
        return Objects.hash(this.mProcessName, Integer.valueOf(this.mPid), Integer.valueOf(this.mUid));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        OplusPackageFreezeData info = (OplusPackageFreezeData) obj;
        if (this.mProcessName.equals(info.mProcessName) && this.mUid == info.mUid && this.mPid == info.mPid) {
            return true;
        }
        return false;
    }

    public void setPid(int pid) {
        this.mPid = pid;
    }

    public int getPid() {
        return this.mPid;
    }

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public int getUid() {
        return this.mUid;
    }

    public void setCurAdj(int curAdj) {
        this.mCurAdj = curAdj;
    }

    public int getCurAdj() {
        return this.mCurAdj;
    }

    public void setUserId(int userId) {
        this.mUserId = userId;
    }

    public int getUserId() {
        return this.mUserId;
    }

    public void setProcessName(String processName) {
        this.mProcessName = processName;
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    public void setPackageList(List<String> packageList) {
        this.mPackageList.clear();
        if (packageList != null) {
            this.mPackageList.addAll(packageList);
        }
    }

    public List<String> getPackageList() {
        return this.mPackageList;
    }
}
