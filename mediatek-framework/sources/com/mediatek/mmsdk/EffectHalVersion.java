package com.mediatek.mmsdk;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class EffectHalVersion implements Parcelable {
    public static final Parcelable.Creator<EffectHalVersion> CREATOR = new Parcelable.Creator<EffectHalVersion>() { // from class: com.mediatek.mmsdk.EffectHalVersion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EffectHalVersion createFromParcel(Parcel in) {
            return new EffectHalVersion(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EffectHalVersion[] newArray(int size) {
            return new EffectHalVersion[size];
        }
    };
    private int mMajor;
    private int mMinor;
    private String mName;

    public EffectHalVersion() {
        this.mName = "Null";
        this.mMajor = 0;
        this.mMinor = 0;
    }

    public EffectHalVersion(String name, int major, int minor) {
        this.mName = name;
        this.mMajor = major;
        this.mMinor = minor;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mName);
        out.writeInt(this.mMajor);
        out.writeInt(this.mMinor);
    }

    public void readFromParcel(Parcel in) {
        this.mName = in.readString();
        this.mMajor = in.readInt();
        this.mMinor = in.readInt();
    }

    private EffectHalVersion(Parcel in) {
        this.mName = in.readString();
        this.mMajor = in.readInt();
        this.mMinor = in.readInt();
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public void setMajor(int major) {
        this.mMajor = major;
    }

    public int getMajor() {
        return this.mMajor;
    }

    public void setMinor(int minor) {
        this.mMinor = minor;
    }

    public int getMinor() {
        return this.mMinor;
    }
}
