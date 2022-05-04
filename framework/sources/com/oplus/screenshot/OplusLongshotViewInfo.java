package com.oplus.screenshot;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public final class OplusLongshotViewInfo implements Parcelable {
    public static final Parcelable.Creator<OplusLongshotViewInfo> CREATOR = new Parcelable.Creator<OplusLongshotViewInfo>() { // from class: com.oplus.screenshot.OplusLongshotViewInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusLongshotViewInfo createFromParcel(Parcel in) {
            return new OplusLongshotViewInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusLongshotViewInfo[] newArray(int size) {
            return new OplusLongshotViewInfo[size];
        }
    };
    private boolean mIsUnsupported = false;

    public OplusLongshotViewInfo() {
    }

    public OplusLongshotViewInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mIsUnsupported ? 1 : 0);
    }

    public void readFromParcel(Parcel in) {
        boolean z = true;
        if (1 != in.readInt()) {
            z = false;
        }
        this.mIsUnsupported = z;
    }

    public void reset() {
        this.mIsUnsupported = false;
    }

    public void setUnsupported() {
        this.mIsUnsupported = true;
    }

    public boolean isUnsupported() {
        return this.mIsUnsupported;
    }
}
