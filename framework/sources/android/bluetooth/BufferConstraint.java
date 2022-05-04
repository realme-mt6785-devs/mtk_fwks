package android.bluetooth;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
@SystemApi
/* loaded from: classes.dex */
public final class BufferConstraint implements Parcelable {
    public static final Parcelable.Creator<BufferConstraint> CREATOR = new Parcelable.Creator<BufferConstraint>() { // from class: android.bluetooth.BufferConstraint.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BufferConstraint createFromParcel(Parcel in) {
            return new BufferConstraint(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BufferConstraint[] newArray(int size) {
            return new BufferConstraint[size];
        }
    };
    private static final String TAG = "BufferConstraint";
    private int mDefaultMillis;
    private int mMaxMillis;
    private int mMinMillis;

    public BufferConstraint(int defaultMillis, int maxMillis, int minMillis) {
        this.mDefaultMillis = defaultMillis;
        this.mMaxMillis = maxMillis;
        this.mMinMillis = minMillis;
    }

    BufferConstraint(Parcel in) {
        this.mDefaultMillis = in.readInt();
        this.mMaxMillis = in.readInt();
        this.mMinMillis = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mDefaultMillis);
        out.writeInt(this.mMaxMillis);
        out.writeInt(this.mMinMillis);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @SystemApi
    public int getDefaultMillis() {
        return this.mDefaultMillis;
    }

    @SystemApi
    public int getMaxMillis() {
        return this.mMaxMillis;
    }

    @SystemApi
    public int getMinMillis() {
        return this.mMinMillis;
    }
}
