package com.oplus.view;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class OplusLayoutParams implements Parcelable {
    public static final Parcelable.Creator<OplusLayoutParams> CREATOR = new Parcelable.Creator<OplusLayoutParams>() { // from class: com.oplus.view.OplusLayoutParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusLayoutParams createFromParcel(Parcel in) {
            return new OplusLayoutParams(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusLayoutParams[] newArray(int size) {
            return new OplusLayoutParams[size];
        }
    };
    private static final int FLAG_CUSTOM_SYSTEM_BAR = 8;
    private static final int FLAG_FULL_SCREEN_WINDOW = 32;
    private static final int FLAG_HAS_NAVIGATION_BAR = 2;
    private static final int FLAG_HAS_STATUS_BAR = 1;
    private static final int FLAG_SKIP_SYSTEM_UI_VISIBILITY = 64;
    private static final int FLAG_SYSTEM_APP_WINDOW = 16;
    private static final int FLAG_UPDATE_NAVIGATION_BAR = 4;
    private static final int FLAG_USE_LAST_STATUS_BAR_TINT = 128;
    private int mSystemBarFlags = 0;
    private int mNavigationBarColor = 0;

    public OplusLayoutParams() {
    }

    public OplusLayoutParams(Parcel in) {
        readFromParcel(in);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CLP[");
        int i = this.mSystemBarFlags;
        if (i != 0) {
            sb.append(formatHex(i, "sysBarFlg"));
        }
        int i2 = this.mNavigationBarColor;
        if (i2 != 0) {
            sb.append(formatHex(i2, "navColor"));
        }
        sb.append(" ]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OplusLayoutParams other = (OplusLayoutParams) obj;
        if (this.mSystemBarFlags == other.mSystemBarFlags && this.mNavigationBarColor == other.mNavigationBarColor) {
            return true;
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mSystemBarFlags);
        out.writeInt(this.mNavigationBarColor);
    }

    public void readFromParcel(Parcel in) {
        this.mSystemBarFlags = in.readInt();
        this.mNavigationBarColor = in.readInt();
    }

    public void set(OplusLayoutParams src) {
        this.mSystemBarFlags = src.mSystemBarFlags;
        this.mNavigationBarColor = src.mNavigationBarColor;
    }

    public void setHasStatusBar(boolean value) {
        setFlag(value, 1);
    }

    public boolean hasStatusBar() {
        return hasFlag(1);
    }

    public void setHasNavigationBar(boolean value) {
        setFlag(value, 2);
    }

    public boolean hasNavigationBar() {
        return hasFlag(2);
    }

    public void setUpdateNavigationBar(boolean value) {
        setFlag(value, 4);
    }

    public boolean isUpdateNavigationBar() {
        return hasFlag(4);
    }

    public void setCustomSystemBar(boolean value) {
        setFlag(value, 8);
    }

    public boolean isCustomSystemBar() {
        return hasFlag(8);
    }

    public void setSystemAppWindow(boolean value) {
        setFlag(value, 16);
    }

    public boolean isSystemAppWindow() {
        return hasFlag(16);
    }

    public void setFullScreenWindow(boolean value) {
        setFlag(value, 32);
    }

    public boolean isFullScreenWindow() {
        return hasFlag(32);
    }

    public void setSkipSystemUiVisibility(boolean value) {
        setFlag(value, 64);
    }

    public boolean getSkipSystemUiVisibility() {
        return hasFlag(64);
    }

    public void setUseLastStatusBarTint(boolean value) {
        setFlag(value, 128);
    }

    public boolean isUseLastStatusBarTint() {
        return hasFlag(128);
    }

    public void setNavigationBarColor(int value) {
        this.mNavigationBarColor = value;
    }

    public int getNavigationBarColor() {
        return this.mNavigationBarColor;
    }

    private String formatHex(int value, String name) {
        return String.format(" %s=#%08x", name, Integer.valueOf(value));
    }

    private void setFlag(boolean value, int flag) {
        if (value) {
            this.mSystemBarFlags |= flag;
        } else {
            this.mSystemBarFlags &= ~flag;
        }
    }

    private boolean hasFlag(int flag) {
        return (this.mSystemBarFlags & flag) == flag;
    }
}
