package com.oplus.darkmode;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusDarkModeData implements Parcelable {
    public static final Parcelable.Creator<OplusDarkModeData> CREATOR = new Parcelable.Creator<OplusDarkModeData>() { // from class: com.oplus.darkmode.OplusDarkModeData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusDarkModeData createFromParcel(Parcel in) {
            return new OplusDarkModeData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusDarkModeData[] newArray(int size) {
            return new OplusDarkModeData[size];
        }
    };
    public boolean mAlreadyClickByUser;
    public int mCurType;
    public int mIsRecommend;
    public int mOldType;
    public boolean mOpenByUser;
    public long mVersionCode;

    public OplusDarkModeData() {
        this.mVersionCode = -1L;
        this.mIsRecommend = 0;
        this.mOldType = 0;
        this.mCurType = 0;
        this.mOpenByUser = false;
        this.mAlreadyClickByUser = false;
    }

    private OplusDarkModeData(Parcel in) {
        this.mVersionCode = -1L;
        this.mIsRecommend = 0;
        this.mOldType = 0;
        this.mCurType = 0;
        this.mOpenByUser = false;
        this.mAlreadyClickByUser = false;
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mVersionCode);
        dest.writeInt(this.mIsRecommend);
        dest.writeInt(this.mOldType);
        dest.writeInt(this.mCurType);
        dest.writeBoolean(this.mOpenByUser);
        dest.writeBoolean(this.mAlreadyClickByUser);
    }

    public void readFromParcel(Parcel source) {
        this.mVersionCode = source.readLong();
        this.mIsRecommend = source.readInt();
        this.mOldType = source.readInt();
        this.mCurType = source.readInt();
        this.mOpenByUser = source.readBoolean();
        this.mAlreadyClickByUser = source.readBoolean();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
