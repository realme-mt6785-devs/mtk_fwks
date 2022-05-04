package com.oplus.favorite;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusFavoriteQueryResult implements Parcelable {
    public static final Parcelable.Creator<OplusFavoriteQueryResult> CREATOR = new Parcelable.Creator<OplusFavoriteQueryResult>() { // from class: com.oplus.favorite.OplusFavoriteQueryResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusFavoriteQueryResult createFromParcel(Parcel in) {
            return new OplusFavoriteQueryResult(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusFavoriteQueryResult[] newArray(int size) {
            return new OplusFavoriteQueryResult[size];
        }
    };
    public static final String EXTRA_DATA = "data";
    public static final String EXTRA_ERROR = "error";
    private static final String TAG = "OplusFavoriteQueryResult";
    private final Bundle mBundle = new Bundle();

    public OplusFavoriteQueryResult() {
    }

    public OplusFavoriteQueryResult(Parcel in) {
        readFromParcel(in);
    }

    public String toString() {
        return "Result=" + this.mBundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        this.mBundle.writeToParcel(out, flags);
    }

    public void readFromParcel(Parcel in) {
        this.mBundle.readFromParcel(in);
    }

    public Bundle getBundle() {
        return this.mBundle;
    }
}
