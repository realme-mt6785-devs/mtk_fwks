package com.oplus.miragewindow;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusMirageDisplayCastInfo implements Parcelable {
    public static final Parcelable.Creator<OplusMirageDisplayCastInfo> CREATOR = new Parcelable.Creator<OplusMirageDisplayCastInfo>() { // from class: com.oplus.miragewindow.OplusMirageDisplayCastInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusMirageDisplayCastInfo createFromParcel(Parcel source) {
            return new OplusMirageDisplayCastInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusMirageDisplayCastInfo[] newArray(int size) {
            return new OplusMirageDisplayCastInfo[size];
        }
    };
    public static final int OPLUS_MIRAGE_APP_SHARE_MODE = 3;
    public static final int OPLUS_MIRAGE_BACKGROUND_STREAM_MODE = 4;
    public static final int OPLUS_MIRAGE_BASE_DISPLAY_ID = 2020;
    public static final int OPLUS_MIRAGE_CAR_MODE = 5;
    public static final int OPLUS_MIRAGE_DEFAULT_MODE = 0;
    public static final int OPLUS_MIRAGE_PC_GAME_MODE = 6;
    public static final int OPLUS_MIRAGE_PC_MODE = 2;
    public static final int OPLUS_MIRAGE_TV_MODE = 1;
    public int castFlag;
    public int castMode;
    public int displayId;
    public Bundle extension;
    public int flag;
    public int height;
    public int[] stackIds;
    public int[] taskIds;
    public int width;

    public OplusMirageDisplayCastInfo() {
        this.extension = new Bundle();
    }

    public OplusMirageDisplayCastInfo(Parcel in) {
        this.extension = new Bundle();
        this.width = in.readInt();
        this.height = in.readInt();
        readTaskIdsFromParcel(in);
        readStackIdsFromParcel(in);
        this.flag = in.readInt();
        this.extension = in.readBundle();
        this.castMode = in.readInt();
        this.castFlag = in.readInt();
        this.displayId = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        int tasksize = this.taskIds.length;
        if (tasksize > 0) {
            dest.writeInt(tasksize);
            dest.writeIntArray(this.taskIds);
        } else {
            dest.writeInt(0);
        }
        int stacksize = this.stackIds.length;
        if (stacksize > 0) {
            dest.writeInt(stacksize);
            dest.writeIntArray(this.stackIds);
        } else {
            dest.writeInt(0);
        }
        dest.writeInt(this.flag);
        dest.writeBundle(this.extension);
        dest.writeInt(this.castMode);
        dest.writeInt(this.castFlag);
        dest.writeInt(this.displayId);
    }

    private void readTaskIdsFromParcel(Parcel in) {
        if (in != null) {
            int tasksize = in.readInt();
            if (tasksize > 0) {
                int[] iArr = new int[tasksize];
                this.taskIds = iArr;
                in.readIntArray(iArr);
                return;
            }
            this.taskIds = null;
        }
    }

    private void readStackIdsFromParcel(Parcel in) {
        if (in != null) {
            int stacksize = in.readInt();
            if (stacksize > 0) {
                int[] iArr = new int[stacksize];
                this.stackIds = iArr;
                in.readIntArray(iArr);
                return;
            }
            this.stackIds = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusMirageDisplayCastInfo = { ");
        sb.append(" width = " + this.width);
        sb.append(" height = " + this.height);
        sb.append(" taskIds = " + this.taskIds);
        sb.append(" stackIds = " + this.stackIds);
        sb.append(" flag = " + this.flag);
        sb.append(" castMode = " + this.castMode);
        sb.append(" castFlag = " + this.castFlag);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }
}
