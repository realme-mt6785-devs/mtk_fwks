package com.mediatek.mmsdk;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class TrasformOptions implements Parcelable {
    public static final Parcelable.Creator<TrasformOptions> CREATOR = new Parcelable.Creator<TrasformOptions>() { // from class: com.mediatek.mmsdk.TrasformOptions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrasformOptions createFromParcel(Parcel in) {
            return new TrasformOptions(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrasformOptions[] newArray(int size) {
            return new TrasformOptions[size];
        }
    };
    private int encQuality;
    private int isDither;
    private Parcelable rect;
    private int sharpnessLevel;
    private int transform;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.rect, flags);
        out.writeInt(this.transform);
        out.writeInt(this.encQuality);
        out.writeInt(this.isDither);
        out.writeInt(this.sharpnessLevel);
    }

    public void readFromParcel(Parcel in) {
        this.rect = in.readParcelable(null);
        this.transform = in.readInt();
        this.encQuality = in.readInt();
        this.isDither = in.readInt();
        this.sharpnessLevel = in.readInt();
    }

    private TrasformOptions(Parcel in) {
        this.rect = in.readParcelable(null);
        this.transform = in.readInt();
        this.encQuality = in.readInt();
        this.isDither = in.readInt();
        this.sharpnessLevel = in.readInt();
    }
}
