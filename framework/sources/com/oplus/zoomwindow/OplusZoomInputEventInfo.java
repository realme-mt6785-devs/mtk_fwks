package com.oplus.zoomwindow;

import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusZoomInputEventInfo implements Parcelable {
    public static final Parcelable.Creator<OplusZoomInputEventInfo> CREATOR = new Parcelable.Creator<OplusZoomInputEventInfo>() { // from class: com.oplus.zoomwindow.OplusZoomInputEventInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomInputEventInfo createFromParcel(Parcel source) {
            return new OplusZoomInputEventInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomInputEventInfo[] newArray(int size) {
            return new OplusZoomInputEventInfo[size];
        }
    };
    public IBinder boundCallback;
    public String callPkg;
    public int evActionFlag;
    public Bundle extension;
    public Point point;
    public IBinder who;

    public OplusZoomInputEventInfo() {
        this.point = new Point();
        this.extension = new Bundle();
    }

    public OplusZoomInputEventInfo(Parcel in) {
        this.point = new Point();
        this.extension = new Bundle();
        this.point = (Point) in.readParcelable(Point.class.getClassLoader());
        this.evActionFlag = in.readInt();
        this.callPkg = in.readString();
        this.extension = in.readBundle();
        this.who = in.readStrongBinder();
        this.boundCallback = in.readStrongBinder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.point, 0);
        dest.writeInt(this.evActionFlag);
        dest.writeString(this.callPkg);
        dest.writeBundle(this.extension);
        dest.writeStrongBinder(this.who);
        dest.writeStrongBinder(this.boundCallback);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusZoomInputEventInfo = { ");
        sb.append(" point = " + this.point);
        sb.append(" evActionFlag = " + this.evActionFlag);
        sb.append(" callPkg = " + this.callPkg);
        sb.append(" extension = " + this.extension);
        sb.append("}");
        return sb.toString();
    }
}
