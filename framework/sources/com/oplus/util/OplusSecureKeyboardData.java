package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public final class OplusSecureKeyboardData implements Parcelable {
    public static final Parcelable.Creator<OplusSecureKeyboardData> CREATOR = new Parcelable.Creator<OplusSecureKeyboardData>() { // from class: com.oplus.util.OplusSecureKeyboardData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusSecureKeyboardData createFromParcel(Parcel in) {
            return new OplusSecureKeyboardData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusSecureKeyboardData[] newArray(int size) {
            return new OplusSecureKeyboardData[size];
        }
    };
    private ArrayList<String> mList1 = new ArrayList<>();
    private ArrayList<String> mList2 = new ArrayList<>();
    private String mEnable = "true";

    public OplusSecureKeyboardData() {
    }

    public OplusSecureKeyboardData(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mEnable);
        out.writeStringList(this.mList1);
        out.writeStringList(this.mList2);
    }

    public void readFromParcel(Parcel in) {
        this.mEnable = in.readString();
        this.mList1 = in.createStringArrayList();
        this.mList2 = in.createStringArrayList();
    }

    public void setEnable(String value) {
        this.mEnable = value;
    }

    public String getEnable() {
        return this.mEnable;
    }

    public ArrayList<String> getNormalAppList() {
        return this.mList1;
    }

    public ArrayList<String> getInputMethodAppList() {
        return this.mList2;
    }
}
