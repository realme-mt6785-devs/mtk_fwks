package com.oplus.miragewindow;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusCastScreenState implements Parcelable {
    public static final Parcelable.Creator<OplusCastScreenState> CREATOR = new Parcelable.Creator<OplusCastScreenState>() { // from class: com.oplus.miragewindow.OplusCastScreenState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusCastScreenState createFromParcel(Parcel source) {
            return new OplusCastScreenState(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusCastScreenState[] newArray(int size) {
            return new OplusCastScreenState[size];
        }
    };
    public static final int FINISHED = 3;
    public static final int INITIALIZING = 1;
    public static final int RUNNING = 2;
    public String castName;
    public int castState;
    public Bundle extension;

    public OplusCastScreenState() {
        this.extension = new Bundle();
    }

    public OplusCastScreenState(Parcel in) {
        this.extension = new Bundle();
        this.castName = in.readString();
        this.castState = in.readInt();
        this.extension = in.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.castName);
        dest.writeInt(this.castState);
        dest.writeBundle(this.extension);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusCastScreenState = { ");
        sb.append(" castName = " + this.castName);
        sb.append(" castState = " + this.castState);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }
}
