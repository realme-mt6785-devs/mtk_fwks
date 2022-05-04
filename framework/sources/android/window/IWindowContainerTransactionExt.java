package android.window;

import android.os.Parcel;
/* loaded from: classes3.dex */
public final class IWindowContainerTransactionExt {
    private boolean mIsEnterSplit;

    public IWindowContainerTransactionExt() {
        this.mIsEnterSplit = false;
    }

    public IWindowContainerTransactionExt(Parcel in) {
        this.mIsEnterSplit = false;
        this.mIsEnterSplit = in.readBoolean();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBoolean(this.mIsEnterSplit);
    }

    public void setIsEnterSplit(boolean enter) {
        this.mIsEnterSplit = enter;
    }

    public boolean isEnterSplit() {
        return this.mIsEnterSplit;
    }
}
