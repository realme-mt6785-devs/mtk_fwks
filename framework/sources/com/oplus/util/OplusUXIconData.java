package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public final class OplusUXIconData implements Parcelable {
    public static final Parcelable.Creator<OplusUXIconData> CREATOR = new Parcelable.Creator<OplusUXIconData>() { // from class: com.oplus.util.OplusUXIconData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusUXIconData createFromParcel(Parcel in) {
            return new OplusUXIconData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusUXIconData[] newArray(int size) {
            return new OplusUXIconData[size];
        }
    };
    private List<String> mList = new ArrayList();

    public OplusUXIconData() {
    }

    public OplusUXIconData(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringList(this.mList);
    }

    public void readFromParcel(Parcel in) {
        in.readStringList(this.mList);
    }

    public List<String> getPresetAppList() {
        return this.mList;
    }
}
