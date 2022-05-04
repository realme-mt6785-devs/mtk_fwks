package com.oplus.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
/* loaded from: classes4.dex */
public class OplusWakeLockInfo implements Parcelable {
    public static final Parcelable.Creator<OplusWakeLockInfo> CREATOR = new Parcelable.Creator<OplusWakeLockInfo>() { // from class: com.oplus.app.OplusWakeLockInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusWakeLockInfo createFromParcel(Parcel in) {
            return new OplusWakeLockInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusWakeLockInfo[] newArray(int size) {
            return new OplusWakeLockInfo[size];
        }
    };
    public int binderhash;
    public long duration;
    public int flags;
    public String packageName;
    public int pid;
    public long starttime;
    public String tag;
    public int uid;

    public OplusWakeLockInfo() {
    }

    public OplusWakeLockInfo(Parcel in) {
        this.binderhash = in.readInt();
        this.flags = in.readInt();
        this.tag = in.readString();
        this.duration = in.readLong();
        this.starttime = in.readLong();
        this.packageName = in.readString();
        this.uid = in.readInt();
        this.pid = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(this.binderhash);
        out.writeInt(this.flags);
        out.writeString(this.tag);
        out.writeLong(this.duration);
        out.writeLong(this.starttime);
        out.writeString(this.packageName);
        out.writeInt(this.uid);
        out.writeInt(this.pid);
    }

    public String toString() {
        return "OplusWakeLockInfo{binderhash=" + this.binderhash + "flags=" + this.flags + ", tag=" + this.tag + ", duration=" + this.duration + ", starttime=" + this.starttime + ", packageName='" + this.packageName + DateFormat.QUOTE + ", uid=" + this.uid + ", pid=" + this.pid + '}';
    }
}
