package com.oplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public final class OplusAppEnterInfo implements Parcelable {
    public static final Parcelable.Creator<OplusAppEnterInfo> CREATOR = new Parcelable.Creator<OplusAppEnterInfo>() { // from class: com.oplus.app.OplusAppEnterInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAppEnterInfo createFromParcel(Parcel source) {
            return new OplusAppEnterInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusAppEnterInfo[] newArray(int size) {
            return new OplusAppEnterInfo[size];
        }
    };
    public static final int SWITCH_TYPE_ACTIVITY = 1;
    public static final int SWITCH_TYPE_APP = 2;
    public Bundle extension;
    public boolean firstStart;
    public Intent intent;
    public String launchedFromPackage;
    public boolean multiApp;
    public String targetName;
    public int windowMode;

    public OplusAppEnterInfo() {
        this.extension = new Bundle();
    }

    public OplusAppEnterInfo(Parcel in) {
        this.extension = new Bundle();
        this.intent = (Intent) in.readParcelable(Intent.class.getClassLoader());
        this.windowMode = in.readInt();
        this.targetName = in.readString();
        boolean z = true;
        this.multiApp = in.readByte() != 0;
        this.firstStart = in.readByte() == 0 ? false : z;
        this.launchedFromPackage = in.readString();
        this.extension = in.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.intent, flags);
        dest.writeInt(this.windowMode);
        dest.writeString(this.targetName);
        dest.writeByte(this.multiApp ? (byte) 1 : (byte) 0);
        dest.writeByte(this.firstStart ? (byte) 1 : (byte) 0);
        dest.writeString(this.launchedFromPackage);
        dest.writeBundle(this.extension);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusAppEnterInfo = { ");
        sb.append(" windowMode = " + this.windowMode);
        sb.append(" targetName = " + this.targetName);
        sb.append(" multiApp = " + this.multiApp);
        sb.append(" firstStart = " + this.firstStart);
        sb.append(" launchedFromPackage = " + this.launchedFromPackage);
        sb.append(" intent = " + this.intent);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }
}
