package com.oplus.deepthinker.sdk.aidl.proton.deepsleep;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class SleepRecord implements Parcelable {
    public static final Parcelable.Creator<SleepRecord> CREATOR = new Parcelable.Creator<SleepRecord>() { // from class: com.oplus.deepthinker.sdk.aidl.proton.deepsleep.SleepRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepRecord createFromParcel(Parcel source) {
            SleepRecord record = new SleepRecord(0L, 0L);
            record.mSleepTime = source.readLong();
            record.mWakeTime = source.readLong();
            return record;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepRecord[] newArray(int size) {
            return new SleepRecord[size];
        }
    };
    private long mSleepTime;
    private long mWakeTime;

    public SleepRecord(long sleepTime, long wakeTime) {
        this.mSleepTime = 0L;
        this.mWakeTime = 0L;
        this.mSleepTime = sleepTime;
        this.mWakeTime = wakeTime;
    }

    public long getSleepTime() {
        return this.mSleepTime;
    }

    public long getWakeTime() {
        return this.mWakeTime;
    }

    public void setSleepTime(long sleepTime) {
        this.mSleepTime = sleepTime;
    }

    public void setWakeTime(long wakeTime) {
        this.mWakeTime = wakeTime;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mSleepTime);
        dest.writeLong(this.mWakeTime);
    }
}
