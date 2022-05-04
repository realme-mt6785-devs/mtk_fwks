package com.android.internal.inputmethod;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public final class ThrowableHolder implements Parcelable {
    public static final Parcelable.Creator<ThrowableHolder> CREATOR = new Parcelable.Creator<ThrowableHolder>() { // from class: com.android.internal.inputmethod.ThrowableHolder.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ThrowableHolder createFromParcel(Parcel source) {
            return new ThrowableHolder(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ThrowableHolder[] newArray(int size) {
            return new ThrowableHolder[size];
        }
    };
    private final String mMessage;

    ThrowableHolder(Throwable throwable) {
        this.mMessage = throwable.getMessage();
    }

    ThrowableHolder(Parcel source) {
        this.mMessage = source.readString();
    }

    public static ThrowableHolder of(Throwable throwable) {
        return new ThrowableHolder(throwable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMessage() {
        return this.mMessage;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
    }
}
