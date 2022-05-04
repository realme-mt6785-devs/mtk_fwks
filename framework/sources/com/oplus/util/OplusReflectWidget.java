package com.oplus.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import com.android.internal.logging.nano.MetricsProto;
/* loaded from: classes4.dex */
public class OplusReflectWidget implements Parcelable {
    private String className;
    private String field;
    private int fieldLevel;
    private String packageName;
    private int versionCode;
    public static final OplusReflectWidget DEFAULT_WIDGET = new OplusReflectWidget("com.tencent.mm", 1280, "com.tencent.mm.ui.widget.MMNeatTextView", 1, "mText");
    public static final OplusReflectWidget DEFAULT_WIDGET_WECHAT_1420 = new OplusReflectWidget("com.tencent.mm", MetricsProto.MetricsEvent.FIELD_CHARGING_DURATION_MILLIS, "com.tencent.mm.ui.widget.MMNeat7extView", 1, "mText");
    public static final Parcelable.Creator<OplusReflectWidget> CREATOR = new Parcelable.Creator<OplusReflectWidget>() { // from class: com.oplus.util.OplusReflectWidget.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusReflectWidget createFromParcel(Parcel source) {
            return new OplusReflectWidget(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusReflectWidget[] newArray(int size) {
            return new OplusReflectWidget[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeInt(this.versionCode);
        dest.writeString(this.className);
        dest.writeInt(this.fieldLevel);
        dest.writeString(this.field);
    }

    public OplusReflectWidget() {
    }

    public OplusReflectWidget(String packageName, int versionCode, String className, int fieldLevel, String field) {
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.className = className;
        this.fieldLevel = fieldLevel;
        this.field = field;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OplusReflectWidget that = (OplusReflectWidget) o;
        if (this.versionCode == that.versionCode && this.fieldLevel == that.fieldLevel && this.packageName.equals(that.packageName) && this.className.equals(that.className)) {
            return this.field.equals(that.field);
        }
        return false;
    }

    public String toString() {
        return "OplusReflectWidget{packageName='" + this.packageName + DateFormat.QUOTE + ", versionCode=" + this.versionCode + ", className='" + this.className + DateFormat.QUOTE + ", fieldLevel=" + this.fieldLevel + ", field='" + this.field + DateFormat.QUOTE + '}';
    }

    public int hashCode() {
        int result = this.packageName.hashCode();
        return (((((((result * 31) + this.versionCode) * 31) + this.className.hashCode()) * 31) + this.fieldLevel) * 31) + this.field.hashCode();
    }

    public void setFieldLevel(int fieldLevel) {
        this.fieldLevel = fieldLevel;
    }

    public int getFieldLevel() {
        return this.fieldLevel;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    protected OplusReflectWidget(Parcel in) {
        this.packageName = in.readString();
        this.versionCode = in.readInt();
        this.className = in.readString();
        this.fieldLevel = in.readInt();
        this.field = in.readString();
    }
}
