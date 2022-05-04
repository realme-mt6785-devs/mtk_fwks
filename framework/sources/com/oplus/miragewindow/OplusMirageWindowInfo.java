package com.oplus.miragewindow;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusMirageWindowInfo implements Parcelable {
    public static final Parcelable.Creator<OplusMirageWindowInfo> CREATOR = new Parcelable.Creator<OplusMirageWindowInfo>() { // from class: com.oplus.miragewindow.OplusMirageWindowInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusMirageWindowInfo createFromParcel(Parcel source) {
            return new OplusMirageWindowInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusMirageWindowInfo[] newArray(int size) {
            return new OplusMirageWindowInfo[size];
        }
    };
    public int castFlag;
    public int castMode;
    public ComponentName cpnName;
    public int displayId;
    public Bundle extension;
    public int flag;
    public int height;
    public String pkgName;
    public int stackId;
    public int taskId;
    public int width;
    public boolean windowShown;

    public OplusMirageWindowInfo() {
        this.extension = new Bundle();
    }

    public OplusMirageWindowInfo(Parcel in) {
        this.extension = new Bundle();
        this.pkgName = in.readString();
        if (in.readInt() != 0) {
            this.cpnName = ComponentName.CREATOR.createFromParcel(in);
        }
        this.width = in.readInt();
        this.height = in.readInt();
        this.taskId = in.readInt();
        this.stackId = in.readInt();
        this.flag = in.readInt();
        this.extension = in.readBundle();
        this.castMode = in.readInt();
        this.castFlag = in.readInt();
        this.displayId = in.readInt();
        this.windowShown = in.readBoolean();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pkgName);
        if (this.cpnName != null) {
            dest.writeInt(1);
            this.cpnName.writeToParcel(dest, flags);
        } else {
            dest.writeInt(0);
        }
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.taskId);
        dest.writeInt(this.stackId);
        dest.writeInt(this.flag);
        dest.writeBundle(this.extension);
        dest.writeInt(this.castMode);
        dest.writeInt(this.castFlag);
        dest.writeInt(this.displayId);
        dest.writeBoolean(this.windowShown);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusMirageWindowInfo = { ");
        sb.append(" pkgName = " + this.pkgName);
        sb.append(" windowShown = " + this.windowShown);
        sb.append(" cpnName = " + this.cpnName);
        sb.append(" width = " + this.width);
        sb.append(" height = " + this.height);
        sb.append(" taskId = " + this.taskId);
        sb.append(" stackId = " + this.stackId);
        sb.append(" flag = " + this.flag);
        sb.append(" castMode = " + this.castMode);
        sb.append(" castFlag = " + this.castFlag);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }
}
