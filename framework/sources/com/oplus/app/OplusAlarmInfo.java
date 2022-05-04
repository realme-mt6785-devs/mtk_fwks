package com.oplus.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
/* loaded from: classes4.dex */
public class OplusAlarmInfo implements Parcelable {
    public static final Parcelable.Creator<OplusAlarmInfo> CREATOR = new Parcelable.Creator<OplusAlarmInfo>() { // from class: com.oplus.app.OplusAlarmInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAlarmInfo createFromParcel(Parcel in) {
            return new OplusAlarmInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAlarmInfo[] newArray(int size) {
            return new OplusAlarmInfo[size];
        }
    };
    public int callingPid;
    public int callingUid;
    public String packageName;
    public String statsTag;
    public long timeStamp;
    public int type;

    public OplusAlarmInfo() {
    }

    public OplusAlarmInfo(Parcel in) {
        this.type = in.readInt();
        this.timeStamp = in.readLong();
        this.packageName = in.readString();
        this.callingUid = in.readInt();
        this.callingPid = in.readInt();
        this.statsTag = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeLong(this.timeStamp);
        dest.writeString(this.packageName);
        dest.writeInt(this.callingUid);
        dest.writeInt(this.callingPid);
        dest.writeString(this.statsTag);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "AlarmInfo{type=" + this.type + ", timeStamp=" + this.timeStamp + ", packageName='" + this.packageName + DateFormat.QUOTE + ", callingUid=" + this.callingUid + ", callingPid=" + this.callingPid + ", statsTag='" + this.statsTag + DateFormat.QUOTE + '}';
    }
}
