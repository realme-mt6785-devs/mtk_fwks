package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public final class OplusProcDependData implements Parcelable {
    public static final Parcelable.Creator<OplusProcDependData> CREATOR = new Parcelable.Creator<OplusProcDependData>() { // from class: com.oplus.util.OplusProcDependData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusProcDependData createFromParcel(Parcel in) {
            return new OplusProcDependData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusProcDependData[] newArray(int size) {
            return new OplusProcDependData[size];
        }
    };
    public int mPid = -1;
    public int mUid = -1;
    public String mProcessName = null;
    public String mPackageName = null;
    public List<ProcItem> mServices = new ArrayList();
    public List<ProcItem> mClients = new ArrayList();

    public OplusProcDependData() {
    }

    public OplusProcDependData(Parcel in) {
        readFromParcel(in);
    }

    public String toString() {
        return "pid=" + this.mPid + ",uid=" + this.mUid + ",pkg=" + this.mPackageName + ",proc=" + this.mProcessName + ",depend ON=" + this.mServices + ",depend BY=" + this.mClients + ",";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mPid);
        out.writeInt(this.mUid);
        out.writeString(this.mProcessName);
        out.writeString(this.mPackageName);
        out.writeTypedList(this.mServices);
        out.writeTypedList(this.mClients);
    }

    public void readFromParcel(Parcel in) {
        this.mPid = in.readInt();
        this.mUid = in.readInt();
        this.mProcessName = in.readString();
        this.mPackageName = in.readString();
        ArrayList arrayList = new ArrayList();
        this.mServices = arrayList;
        in.readTypedList(arrayList, ProcItem.CREATOR);
        ArrayList arrayList2 = new ArrayList();
        this.mClients = arrayList2;
        in.readTypedList(arrayList2, ProcItem.CREATOR);
    }

    /* loaded from: classes4.dex */
    public static final class ProcItem implements Parcelable {
        public static final Parcelable.Creator<ProcItem> CREATOR = new Parcelable.Creator<ProcItem>() { // from class: com.oplus.util.OplusProcDependData.ProcItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ProcItem createFromParcel(Parcel in) {
                return new ProcItem(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ProcItem[] newArray(int size) {
                return new ProcItem[size];
            }
        };
        public String packageName;
        public int pid;
        public String processName;
        public int uid;

        public ProcItem() {
            this.pid = -1;
            this.uid = -1;
            this.processName = null;
            this.packageName = null;
        }

        public ProcItem(int uid, int pid, String packageName, String processName) {
            this.pid = -1;
            this.uid = -1;
            this.processName = null;
            this.packageName = null;
            this.pid = pid;
            this.uid = uid;
            this.processName = processName;
            this.packageName = packageName;
        }

        public ProcItem(Parcel in) {
            this.pid = -1;
            this.uid = -1;
            this.processName = null;
            this.packageName = null;
            readFromParcel(in);
        }

        public String toString() {
            return this.pid + "+" + this.uid + "+" + this.packageName + "+" + this.processName;
        }

        public boolean equals(Object obj) {
            String str;
            if (obj == null || !(obj instanceof ProcItem)) {
                return false;
            }
            if (this.pid == ((ProcItem) obj).pid && this.uid == ((ProcItem) obj).uid) {
                return true;
            }
            String str2 = this.packageName;
            return str2 != null && str2.equals(((ProcItem) obj).packageName) && (str = this.processName) != null && str.equals(((ProcItem) obj).processName);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.pid);
            out.writeInt(this.uid);
            out.writeString(this.processName);
            out.writeString(this.packageName);
        }

        public void readFromParcel(Parcel in) {
            this.pid = in.readInt();
            this.uid = in.readInt();
            this.processName = in.readString();
            this.packageName = in.readString();
        }
    }
}
