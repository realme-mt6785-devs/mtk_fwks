package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Range;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class ModemActivityInfo implements Parcelable {
    private static final int TX_POWER_LEVELS = 5;
    public static final int TX_POWER_LEVEL_0 = 0;
    public static final int TX_POWER_LEVEL_1 = 1;
    public static final int TX_POWER_LEVEL_2 = 2;
    public static final int TX_POWER_LEVEL_3 = 3;
    public static final int TX_POWER_LEVEL_4 = 4;
    private int mIdleTimeMs;
    private int mRxTimeMs;
    private int mSleepTimeMs;
    private long mTimestamp;
    private int[] mTxTimeMs;
    private static final Range<Integer>[] TX_POWER_RANGES = {new Range<>(Integer.MIN_VALUE, 0), new Range<>(0, 5), new Range<>(5, 15), new Range<>(15, 20), new Range<>(20, Integer.MAX_VALUE)};
    public static final Parcelable.Creator<ModemActivityInfo> CREATOR = new Parcelable.Creator<ModemActivityInfo>() { // from class: android.telephony.ModemActivityInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModemActivityInfo createFromParcel(Parcel in) {
            long timestamp = in.readLong();
            int sleepTimeMs = in.readInt();
            int idleTimeMs = in.readInt();
            int[] txTimeMs = new int[5];
            in.readIntArray(txTimeMs);
            int rxTimeMs = in.readInt();
            return new ModemActivityInfo(timestamp, sleepTimeMs, idleTimeMs, txTimeMs, rxTimeMs);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModemActivityInfo[] newArray(int size) {
            return new ModemActivityInfo[size];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface TxPowerLevel {
    }

    public static int getNumTxPowerLevels() {
        return 5;
    }

    public ModemActivityInfo(long timestamp, int sleepTimeMs, int idleTimeMs, int[] txTimeMs, int rxTimeMs) {
        Objects.requireNonNull(txTimeMs);
        if (txTimeMs.length == 5) {
            this.mTimestamp = timestamp;
            this.mSleepTimeMs = sleepTimeMs;
            this.mIdleTimeMs = idleTimeMs;
            this.mTxTimeMs = txTimeMs;
            this.mRxTimeMs = rxTimeMs;
            return;
        }
        throw new IllegalArgumentException("txTimeMs must have length == TX_POWER_LEVELS");
    }

    public ModemActivityInfo(long timestamp, long sleepTimeMs, long idleTimeMs, int[] txTimeMs, long rxTimeMs) {
        this(timestamp, (int) sleepTimeMs, (int) idleTimeMs, txTimeMs, (int) rxTimeMs);
    }

    public String toString() {
        return "ModemActivityInfo{ mTimestamp=" + this.mTimestamp + " mSleepTimeMs=" + this.mSleepTimeMs + " mIdleTimeMs=" + this.mIdleTimeMs + " mTxTimeMs[]=" + Arrays.toString(this.mTxTimeMs) + " mRxTimeMs=" + this.mRxTimeMs + "}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mTimestamp);
        dest.writeInt(this.mSleepTimeMs);
        dest.writeInt(this.mIdleTimeMs);
        dest.writeIntArray(this.mTxTimeMs);
        dest.writeInt(this.mRxTimeMs);
    }

    public long getTimestampMillis() {
        return this.mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
    }

    public long getTransmitDurationMillisAtPowerLevel(int powerLevel) {
        return this.mTxTimeMs[powerLevel];
    }

    public Range<Integer> getTransmitPowerRange(int powerLevel) {
        return TX_POWER_RANGES[powerLevel];
    }

    public void setTransmitTimeMillis(int[] txTimeMs) {
        this.mTxTimeMs = Arrays.copyOf(txTimeMs, 5);
    }

    public int[] getTransmitTimeMillis() {
        return this.mTxTimeMs;
    }

    public long getSleepTimeMillis() {
        return this.mSleepTimeMs;
    }

    public void setSleepTimeMillis(int sleepTimeMillis) {
        this.mSleepTimeMs = sleepTimeMillis;
    }

    public void setSleepTimeMillis(long sleepTimeMillis) {
        this.mSleepTimeMs = (int) sleepTimeMillis;
    }

    public ModemActivityInfo getDelta(ModemActivityInfo other) {
        int[] txTimeMs = new int[5];
        for (int i = 0; i < 5; i++) {
            txTimeMs[i] = other.mTxTimeMs[i] - this.mTxTimeMs[i];
        }
        return new ModemActivityInfo(other.getTimestampMillis(), other.getSleepTimeMillis() - getSleepTimeMillis(), other.getIdleTimeMillis() - getIdleTimeMillis(), txTimeMs, other.getReceiveTimeMillis() - getReceiveTimeMillis());
    }

    public long getIdleTimeMillis() {
        return this.mIdleTimeMs;
    }

    public void setIdleTimeMillis(int idleTimeMillis) {
        this.mIdleTimeMs = idleTimeMillis;
    }

    public void setIdleTimeMillis(long idleTimeMillis) {
        this.mIdleTimeMs = (int) idleTimeMillis;
    }

    public long getReceiveTimeMillis() {
        return this.mRxTimeMs;
    }

    public void setReceiveTimeMillis(int rxTimeMillis) {
        this.mRxTimeMs = rxTimeMillis;
    }

    public void setReceiveTimeMillis(long receiveTimeMillis) {
        this.mRxTimeMs = (int) receiveTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$isValid$0(int i) {
        return i >= 0;
    }

    public boolean isValid() {
        boolean isTxPowerValid = Arrays.stream(this.mTxTimeMs).allMatch(ModemActivityInfo$$ExternalSyntheticLambda1.INSTANCE);
        return isTxPowerValid && getIdleTimeMillis() >= 0 && getSleepTimeMillis() >= 0 && getReceiveTimeMillis() >= 0 && !isEmpty();
    }

    public boolean isEmpty() {
        int[] iArr = this.mTxTimeMs;
        boolean isTxPowerEmpty = iArr == null || iArr.length == 0 || Arrays.stream(iArr).allMatch(ModemActivityInfo$$ExternalSyntheticLambda0.INSTANCE);
        return isTxPowerEmpty && getIdleTimeMillis() == 0 && getSleepTimeMillis() == 0 && getReceiveTimeMillis() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$isEmpty$1(int i) {
        return i == 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModemActivityInfo that = (ModemActivityInfo) o;
        if (this.mTimestamp == that.mTimestamp && this.mSleepTimeMs == that.mSleepTimeMs && this.mIdleTimeMs == that.mIdleTimeMs && this.mRxTimeMs == that.mRxTimeMs && Arrays.equals(this.mTxTimeMs, that.mTxTimeMs)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Objects.hash(Long.valueOf(this.mTimestamp), Integer.valueOf(this.mSleepTimeMs), Integer.valueOf(this.mIdleTimeMs), Integer.valueOf(this.mRxTimeMs));
        return (result * 31) + Arrays.hashCode(this.mTxTimeMs);
    }
}
