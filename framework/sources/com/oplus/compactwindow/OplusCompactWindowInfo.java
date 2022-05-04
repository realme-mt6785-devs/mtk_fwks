package com.oplus.compactwindow;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public final class OplusCompactWindowInfo implements Parcelable {
    public static final Parcelable.Creator<OplusCompactWindowInfo> CREATOR = new Parcelable.Creator<OplusCompactWindowInfo>() { // from class: com.oplus.compactwindow.OplusCompactWindowInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusCompactWindowInfo createFromParcel(Parcel source) {
            return new OplusCompactWindowInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusCompactWindowInfo[] newArray(int size) {
            return new OplusCompactWindowInfo[size];
        }
    };
    public String compactPkg;
    public int compactWindowPosition;
    public Bundle extension;
    public boolean popUpWizard;
    public boolean showButton;
    public int windowType;

    public OplusCompactWindowInfo() {
        this.popUpWizard = false;
        this.showButton = false;
        this.extension = new Bundle();
        this.windowType = -1;
        this.compactWindowPosition = -1;
    }

    public OplusCompactWindowInfo(Parcel in) {
        this.popUpWizard = false;
        this.showButton = false;
        this.extension = new Bundle();
        this.windowType = -1;
        this.compactWindowPosition = -1;
        this.compactPkg = in.readString();
        this.popUpWizard = in.readBoolean();
        this.showButton = in.readBoolean();
        this.extension = in.readBundle();
        this.windowType = in.readInt();
        this.compactWindowPosition = in.readInt();
    }

    public OplusCompactWindowInfo(OplusCompactWindowInfo in) {
        this.popUpWizard = false;
        this.showButton = false;
        this.extension = new Bundle();
        this.windowType = -1;
        this.compactWindowPosition = -1;
        if (in != null) {
            this.compactPkg = in.compactPkg;
            this.popUpWizard = in.popUpWizard;
            this.showButton = in.showButton;
            this.extension = in.extension;
            this.windowType = in.windowType;
            this.compactWindowPosition = in.compactWindowPosition;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.compactPkg);
        dest.writeBoolean(this.popUpWizard);
        dest.writeBoolean(this.showButton);
        dest.writeBundle(this.extension);
        dest.writeInt(this.windowType);
        dest.writeInt(this.compactWindowPosition);
    }

    public String toString() {
        return "OplusCompactWindowInfo = {  pName = " + this.compactPkg + " popUpWizard = " + this.popUpWizard + " showButton = " + this.showButton + " extension = " + this.extension + " windowType = " + this.windowType + " compactWindowPosition = " + this.compactWindowPosition + "}";
    }
}
