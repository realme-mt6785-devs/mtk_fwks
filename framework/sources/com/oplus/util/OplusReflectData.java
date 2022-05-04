package com.oplus.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class OplusReflectData implements Parcelable {
    public static final Parcelable.Creator<OplusReflectData> CREATOR = new Parcelable.Creator<OplusReflectData>() { // from class: com.oplus.util.OplusReflectData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusReflectData createFromParcel(Parcel source) {
            return new OplusReflectData(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusReflectData[] newArray(int size) {
            return new OplusReflectData[size];
        }
    };
    private static final ArrayList<OplusReflectWidget> mLocalReflectList;
    private ArrayList<OplusReflectWidget> mReflectAppList;
    private boolean mReflectEnable;
    private ArrayList<OplusReflectWidget> mReflectList;

    static {
        ArrayList<OplusReflectWidget> arrayList = new ArrayList<>();
        mLocalReflectList = arrayList;
        arrayList.add(OplusReflectWidget.DEFAULT_WIDGET);
        arrayList.add(OplusReflectWidget.DEFAULT_WIDGET_WECHAT_1420);
    }

    public OplusReflectData() {
        this.mReflectEnable = true;
        this.mReflectAppList = new ArrayList<>();
        this.mReflectList = new ArrayList<>();
    }

    protected OplusReflectData(Parcel in) {
        boolean z = true;
        this.mReflectEnable = true;
        this.mReflectAppList = new ArrayList<>();
        this.mReflectList = new ArrayList<>();
        this.mReflectEnable = in.readByte() == 0 ? false : z;
        this.mReflectList = in.createTypedArrayList(OplusReflectWidget.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.mReflectEnable ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.mReflectList);
    }

    public boolean isReflectEnable() {
        return this.mReflectEnable;
    }

    public void setReflectEnable(boolean enable) {
        this.mReflectEnable = enable;
    }

    public ArrayList<OplusReflectWidget> getReflectList() {
        return this.mReflectList;
    }

    public void setReflectList(ArrayList<OplusReflectWidget> reflectList) {
        this.mReflectList = reflectList;
    }

    public void clearList() {
        ArrayList<OplusReflectWidget> arrayList = this.mReflectList;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void addReflectWidget(OplusReflectWidget widget) {
        if (this.mReflectList == null) {
            this.mReflectList = new ArrayList<>();
        }
        this.mReflectList.add(widget);
    }

    public String toString() {
        return "OplusReflectData{mReflectEnable=" + this.mReflectEnable + ", mReflectList=" + this.mReflectList + '}';
    }

    private OplusReflectWidget findWidgetImpl(ArrayList<OplusReflectWidget> list, Context context, String appName, String className) {
        for (int i = 0; i < list.size(); i++) {
            OplusReflectWidget widget = list.get(i);
            if (className.equals(widget.getClassName())) {
                return widget;
            }
        }
        return null;
    }

    public OplusReflectWidget findWidget(Context context, String appName, String className) {
        ArrayList<OplusReflectWidget> arrayList = this.mReflectAppList;
        if (arrayList == null || arrayList.size() < 1) {
            return null;
        }
        return findWidgetImpl(this.mReflectAppList, context, appName, className);
    }

    public void initList(String appName, int version) {
        this.mReflectAppList.clear();
        ArrayList<OplusReflectWidget> arrayList = this.mReflectList;
        if (arrayList == null || arrayList.size() < 1) {
            initAppWidgetImpl(mLocalReflectList, this.mReflectAppList, appName, version);
        } else {
            initAppWidgetImpl(this.mReflectList, this.mReflectAppList, appName, version);
        }
    }

    private void initAppWidgetImpl(ArrayList<OplusReflectWidget> totalList, ArrayList<OplusReflectWidget> appList, String appName, int version) {
        for (int i = 0; i < totalList.size(); i++) {
            OplusReflectWidget widget = totalList.get(i);
            if (appName.equals(widget.getPackageName()) && version >= widget.getVersionCode()) {
                appList.add(widget);
            }
        }
        int i2 = appList.size();
        if (i2 < 1) {
            setReflectEnable(false);
        }
    }
}
