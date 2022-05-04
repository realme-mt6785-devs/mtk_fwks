package com.oplus.zoomwindow;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusZoomWindowSize implements Parcelable {
    public static final Parcelable.Creator<OplusZoomWindowSize> CREATOR = new Parcelable.Creator<OplusZoomWindowSize>() { // from class: com.oplus.zoomwindow.OplusZoomWindowSize.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowSize createFromParcel(Parcel in) {
            return new OplusZoomWindowSize(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowSize[] newArray(int size) {
            return new OplusZoomWindowSize[size];
        }
    };
    private int mLandScapeHeight;
    private int mLandScapeWidth;
    private int mPortraitHeight;
    private int mPortraitWidth;

    public OplusZoomWindowSize() {
    }

    protected OplusZoomWindowSize(Parcel in) {
        this.mPortraitWidth = in.readInt();
        this.mPortraitHeight = in.readInt();
        this.mLandScapeWidth = in.readInt();
        this.mLandScapeHeight = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPortraitWidth);
        dest.writeInt(this.mPortraitHeight);
        dest.writeInt(this.mLandScapeWidth);
        dest.writeInt(this.mLandScapeHeight);
    }

    public int getPortraitWidth() {
        return this.mPortraitWidth;
    }

    public int getPortraitHeight() {
        return this.mPortraitHeight;
    }

    public int getLandScapeWidth() {
        return this.mLandScapeWidth;
    }

    public int getLandScapeHeight() {
        return this.mLandScapeHeight;
    }

    public void setZoomWindowPortraitWidth(int portraitWidth) {
        this.mPortraitWidth = portraitWidth;
    }

    public void setZoomWindowPortraitHeight(int portraitHeight) {
        this.mPortraitHeight = portraitHeight;
    }

    public void setZoomWindowLandScapeWidth(int landScapeWidth) {
        this.mLandScapeWidth = landScapeWidth;
    }

    public void setZoomWindowlandScapeHeight(int landScapeHeight) {
        this.mLandScapeHeight = landScapeHeight;
    }

    public void setZoomWindowSize(int portraitWidth, int portraitHeight, int landScapeWidth, int landScapeHeight) {
        this.mPortraitWidth = portraitWidth;
        this.mPortraitHeight = portraitHeight;
        this.mLandScapeWidth = landScapeWidth;
        this.mLandScapeHeight = landScapeHeight;
    }

    public String toString() {
        return "PortraitWidth = " + this.mPortraitWidth + ",PortraitHeight = " + this.mPortraitHeight + ",LandScapeWidth = " + this.mLandScapeWidth + ",LandScapeHeight = " + this.mLandScapeHeight;
    }
}
