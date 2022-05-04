package com.oplus.zoomwindow;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusZoomWindowRegion implements Parcelable {
    public static final Parcelable.Creator<OplusZoomWindowRegion> CREATOR = new Parcelable.Creator<OplusZoomWindowRegion>() { // from class: com.oplus.zoomwindow.OplusZoomWindowRegion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowRegion createFromParcel(Parcel in) {
            return new OplusZoomWindowRegion(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowRegion[] newArray(int size) {
            return new OplusZoomWindowRegion[size];
        }
    };
    private List<Rect> mRectList;

    public Region getRegion() {
        Region region = new Region();
        for (int i = 0; i < this.mRectList.size(); i++) {
            Rect rect = this.mRectList.get(i);
            region.op(rect, Region.Op.UNION);
        }
        return region;
    }

    public List<Rect> getRectList() {
        return this.mRectList;
    }

    public OplusZoomWindowRegion() {
        this.mRectList = new ArrayList();
    }

    public OplusZoomWindowRegion(Parcel in) {
        this.mRectList = new ArrayList();
        this.mRectList = in.createTypedArrayList(Rect.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mRectList);
    }

    public String toString() {
        Region region = getRegion();
        return region.toString();
    }
}
