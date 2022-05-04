package com.oplus.zoomwindow;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusZoomControlViewInfo implements Parcelable {
    public static final Parcelable.Creator<OplusZoomControlViewInfo> CREATOR = new Parcelable.Creator<OplusZoomControlViewInfo>() { // from class: com.oplus.zoomwindow.OplusZoomControlViewInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomControlViewInfo createFromParcel(Parcel source) {
            return new OplusZoomControlViewInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomControlViewInfo[] newArray(int size) {
            return new OplusZoomControlViewInfo[size];
        }
    };
    public int cvActionFlag;
    public Map<String, Rect> cvRectMap;
    public Bundle extension;

    public OplusZoomControlViewInfo() {
        this.cvRectMap = new HashMap();
        this.extension = new Bundle();
    }

    public OplusZoomControlViewInfo(Parcel in) {
        this.cvRectMap = new HashMap();
        this.extension = new Bundle();
        this.cvActionFlag = in.readInt();
        in.readMap(this.cvRectMap, HashMap.class.getClassLoader());
        this.extension = in.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cvActionFlag);
        dest.writeMap(this.cvRectMap);
        dest.writeBundle(this.extension);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusZoomControlViewInfo = { ");
        sb.append(" cvActionFlag = " + this.cvActionFlag);
        sb.append(" cvRectMap = " + this.cvRectMap);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }

    public void copyFrom(OplusZoomControlViewInfo other) {
        this.cvActionFlag = other.cvActionFlag;
        this.cvRectMap.putAll(other.cvRectMap);
        this.extension.putAll(other.extension);
    }

    public void clear() {
        this.cvActionFlag = 0;
        this.cvRectMap.clear();
        this.extension.clear();
    }

    public boolean isEmpty() {
        Map<String, Rect> map = this.cvRectMap;
        return map == null || map.isEmpty();
    }
}
