package com.oplus.zoomwindow;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusZoomFloatHandleViewInfo implements Parcelable {
    public static final Parcelable.Creator<OplusZoomFloatHandleViewInfo> CREATOR = new Parcelable.Creator<OplusZoomFloatHandleViewInfo>() { // from class: com.oplus.zoomwindow.OplusZoomFloatHandleViewInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomFloatHandleViewInfo createFromParcel(Parcel source) {
            return new OplusZoomFloatHandleViewInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomFloatHandleViewInfo[] newArray(int size) {
            return new OplusZoomFloatHandleViewInfo[size];
        }
    };
    public int actionFlag;
    public String cpnName;
    public Bundle extension;
    public int floatHandleState;
    public Rect iConRect;
    public String lockPkg;
    public int lockUserId;
    public float scaleLeftY;
    public float scaleRightY;
    public int side;

    public OplusZoomFloatHandleViewInfo() {
        this.actionFlag = 1;
        this.floatHandleState = 8;
        this.side = 0;
        this.scaleLeftY = -1.0f;
        this.scaleRightY = -1.0f;
        this.iConRect = new Rect();
        this.extension = new Bundle();
    }

    public OplusZoomFloatHandleViewInfo(OplusZoomFloatHandleViewInfo info) {
        this.actionFlag = 1;
        this.floatHandleState = 8;
        this.side = 0;
        this.scaleLeftY = -1.0f;
        this.scaleRightY = -1.0f;
        this.iConRect = new Rect();
        this.extension = new Bundle();
        if (info != null) {
            this.lockPkg = info.lockPkg;
            this.lockUserId = info.lockUserId;
            this.cpnName = info.cpnName;
            this.floatHandleState = info.floatHandleState;
            this.actionFlag = info.actionFlag;
            this.side = info.side;
            this.scaleLeftY = info.scaleLeftY;
            this.scaleRightY = info.scaleRightY;
            this.iConRect = info.iConRect;
            this.extension = info.extension;
        }
    }

    public OplusZoomFloatHandleViewInfo(Parcel in) {
        this.actionFlag = 1;
        this.floatHandleState = 8;
        this.side = 0;
        this.scaleLeftY = -1.0f;
        this.scaleRightY = -1.0f;
        this.iConRect = new Rect();
        this.extension = new Bundle();
        this.lockPkg = in.readString();
        this.lockUserId = in.readInt();
        this.cpnName = in.readString();
        this.floatHandleState = in.readInt();
        this.actionFlag = in.readInt();
        this.side = in.readInt();
        this.scaleLeftY = in.readFloat();
        this.scaleRightY = in.readFloat();
        this.iConRect = (Rect) in.readParcelable(null);
        this.extension = in.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lockPkg);
        dest.writeInt(this.lockUserId);
        dest.writeString(this.cpnName);
        dest.writeInt(this.floatHandleState);
        dest.writeInt(this.actionFlag);
        dest.writeInt(this.side);
        dest.writeFloat(this.scaleLeftY);
        dest.writeFloat(this.scaleRightY);
        dest.writeParcelable(this.iConRect, 0);
        dest.writeBundle(this.extension);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusZoomFloatHandleViewInfo = { ");
        sb.append(" lockPkg = " + this.lockPkg);
        sb.append(" lockUserId = " + this.lockUserId);
        sb.append(" cpnName = " + this.cpnName);
        sb.append(" floatHandleState = " + this.floatHandleState);
        sb.append(" actionFlag = " + this.actionFlag);
        sb.append(" side = " + this.side);
        sb.append(" scaleLeftY = " + this.scaleLeftY);
        sb.append(" scaleRightY = " + this.scaleRightY);
        sb.append(" iConRect = " + this.iConRect);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }
}
