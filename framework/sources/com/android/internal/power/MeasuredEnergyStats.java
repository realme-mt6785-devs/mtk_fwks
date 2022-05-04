package com.android.internal.power;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.DebugUtils;
import android.util.Slog;
import android.view.Display;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
/* loaded from: classes4.dex */
public class MeasuredEnergyStats {
    public static final int NUMBER_STANDARD_POWER_BUCKETS = 8;
    public static final int POWER_BUCKET_BLUETOOTH = 5;
    public static final int POWER_BUCKET_CPU = 3;
    public static final int POWER_BUCKET_GNSS = 6;
    public static final int POWER_BUCKET_MOBILE_RADIO = 7;
    public static final int POWER_BUCKET_SCREEN_DOZE = 1;
    public static final int POWER_BUCKET_SCREEN_ON = 0;
    public static final int POWER_BUCKET_SCREEN_OTHER = 2;
    public static final int POWER_BUCKET_UNKNOWN = -1;
    public static final int POWER_BUCKET_WIFI = 4;
    private static final String TAG = "MeasuredEnergyStats";
    private final long[] mAccumulatedChargeMicroCoulomb;
    private final String[] mCustomBucketNames;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface StandardPowerBucket {
    }

    public MeasuredEnergyStats(boolean[] supportedStandardBuckets, String[] customBucketNames) {
        String[] strArr = customBucketNames == null ? new String[0] : customBucketNames;
        this.mCustomBucketNames = strArr;
        int numTotalBuckets = strArr.length + 8;
        this.mAccumulatedChargeMicroCoulomb = new long[numTotalBuckets];
        for (int stdBucket = 0; stdBucket < 8; stdBucket++) {
            if (!supportedStandardBuckets[stdBucket]) {
                this.mAccumulatedChargeMicroCoulomb[stdBucket] = -1;
            }
        }
    }

    private MeasuredEnergyStats(MeasuredEnergyStats template) {
        int numIndices = template.getNumberOfIndices();
        this.mAccumulatedChargeMicroCoulomb = new long[numIndices];
        for (int stdBucket = 0; stdBucket < 8; stdBucket++) {
            if (!template.isIndexSupported(stdBucket)) {
                this.mAccumulatedChargeMicroCoulomb[stdBucket] = -1;
            }
        }
        this.mCustomBucketNames = template.getCustomBucketNames();
    }

    public static MeasuredEnergyStats createFromTemplate(MeasuredEnergyStats template) {
        return new MeasuredEnergyStats(template);
    }

    private MeasuredEnergyStats(int numIndices) {
        this.mAccumulatedChargeMicroCoulomb = new long[numIndices];
        this.mCustomBucketNames = new String[numIndices - 8];
    }

    public MeasuredEnergyStats(Parcel in) {
        int size = in.readInt();
        long[] jArr = new long[size];
        this.mAccumulatedChargeMicroCoulomb = jArr;
        in.readLongArray(jArr);
        this.mCustomBucketNames = in.readStringArray();
    }

    public void writeToParcel(Parcel out) {
        out.writeInt(this.mAccumulatedChargeMicroCoulomb.length);
        out.writeLongArray(this.mAccumulatedChargeMicroCoulomb);
        out.writeStringArray(this.mCustomBucketNames);
    }

    private void readSummaryFromParcel(Parcel in, boolean overwriteAvailability) {
        int numWrittenEntries = in.readInt();
        for (int entry = 0; entry < numWrittenEntries; entry++) {
            int index = in.readInt();
            long chargeUC = in.readLong();
            if (overwriteAvailability) {
                this.mAccumulatedChargeMicroCoulomb[index] = chargeUC;
            } else {
                setValueIfSupported(index, chargeUC);
            }
        }
    }

    private void writeSummaryToParcel(Parcel out, boolean skipZero) {
        int posOfNumWrittenEntries = out.dataPosition();
        out.writeInt(0);
        int numWrittenEntries = 0;
        int index = 0;
        while (true) {
            long[] jArr = this.mAccumulatedChargeMicroCoulomb;
            if (index < jArr.length) {
                long charge = jArr[index];
                if (charge >= 0 && (charge != 0 || !skipZero)) {
                    out.writeInt(index);
                    out.writeLong(charge);
                    numWrittenEntries++;
                }
                index++;
            } else {
                int currPos = out.dataPosition();
                out.setDataPosition(posOfNumWrittenEntries);
                out.writeInt(numWrittenEntries);
                out.setDataPosition(currPos);
                return;
            }
        }
    }

    private int getNumberOfIndices() {
        return this.mAccumulatedChargeMicroCoulomb.length;
    }

    public void updateStandardBucket(int bucket, long chargeDeltaUC) {
        checkValidStandardBucket(bucket);
        updateEntry(bucket, chargeDeltaUC);
    }

    public void updateCustomBucket(int customBucket, long chargeDeltaUC) {
        if (!isValidCustomBucket(customBucket)) {
            Slog.e(TAG, "Attempted to update invalid custom bucket " + customBucket);
            return;
        }
        int index = customBucketToIndex(customBucket);
        updateEntry(index, chargeDeltaUC);
    }

    private void updateEntry(int index, long chargeDeltaUC) {
        long[] jArr = this.mAccumulatedChargeMicroCoulomb;
        if (jArr[index] >= 0) {
            jArr[index] = jArr[index] + chargeDeltaUC;
            return;
        }
        Slog.wtf(TAG, "Attempting to add " + chargeDeltaUC + " to unavailable bucket " + getBucketName(index) + " whose value was " + this.mAccumulatedChargeMicroCoulomb[index]);
    }

    public long getAccumulatedStandardBucketCharge(int bucket) {
        checkValidStandardBucket(bucket);
        return this.mAccumulatedChargeMicroCoulomb[bucket];
    }

    public long getAccumulatedCustomBucketCharge(int customBucket) {
        if (!isValidCustomBucket(customBucket)) {
            return -1L;
        }
        return this.mAccumulatedChargeMicroCoulomb[customBucketToIndex(customBucket)];
    }

    public long[] getAccumulatedCustomBucketCharges() {
        long[] charges = new long[getNumberCustomPowerBuckets()];
        for (int bucket = 0; bucket < charges.length; bucket++) {
            charges[bucket] = this.mAccumulatedChargeMicroCoulomb[customBucketToIndex(bucket)];
        }
        return charges;
    }

    public static int getDisplayPowerBucket(int screenState) {
        if (Display.isOnState(screenState)) {
            return 0;
        }
        if (Display.isDozeState(screenState)) {
            return 1;
        }
        return 2;
    }

    public static MeasuredEnergyStats createAndReadSummaryFromParcel(Parcel in) {
        String[] customBucketNames;
        int arraySize = in.readInt();
        if (arraySize == 0) {
            return null;
        }
        if (in.readBoolean()) {
            customBucketNames = in.readStringArray();
        } else {
            customBucketNames = new String[0];
        }
        MeasuredEnergyStats stats = new MeasuredEnergyStats(new boolean[8], customBucketNames);
        stats.readSummaryFromParcel(in, true);
        return stats;
    }

    public static MeasuredEnergyStats createAndReadSummaryFromParcel(Parcel in, MeasuredEnergyStats template) {
        int arraySize = in.readInt();
        if (arraySize == 0) {
            return null;
        }
        boolean includesCustomBucketNames = in.readBoolean();
        if (includesCustomBucketNames) {
            in.readStringArray();
        }
        if (template == null) {
            MeasuredEnergyStats mes = new MeasuredEnergyStats(arraySize);
            mes.readSummaryFromParcel(in, false);
            return null;
        } else if (arraySize != template.getNumberOfIndices()) {
            Slog.wtf(TAG, "Size of MeasuredEnergyStats parcel (" + arraySize + ") does not match template (" + template.getNumberOfIndices() + ").");
            MeasuredEnergyStats mes2 = new MeasuredEnergyStats(arraySize);
            mes2.readSummaryFromParcel(in, false);
            return null;
        } else {
            MeasuredEnergyStats stats = createFromTemplate(template);
            stats.readSummaryFromParcel(in, false);
            if (stats.containsInterestingData()) {
                return stats;
            }
            return null;
        }
    }

    private boolean containsInterestingData() {
        int index = 0;
        while (true) {
            long[] jArr = this.mAccumulatedChargeMicroCoulomb;
            if (index >= jArr.length) {
                return false;
            }
            if (jArr[index] > 0) {
                return true;
            }
            index++;
        }
    }

    public static void writeSummaryToParcel(MeasuredEnergyStats stats, Parcel dest, boolean skipZero, boolean skipCustomBucketNames) {
        if (stats == null) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(stats.getNumberOfIndices());
        if (!skipCustomBucketNames) {
            dest.writeBoolean(true);
            dest.writeStringArray(stats.getCustomBucketNames());
        } else {
            dest.writeBoolean(false);
        }
        stats.writeSummaryToParcel(dest, skipZero);
    }

    private void reset() {
        int numIndices = getNumberOfIndices();
        for (int index = 0; index < numIndices; index++) {
            setValueIfSupported(index, 0L);
        }
    }

    public static void resetIfNotNull(MeasuredEnergyStats stats) {
        if (stats != null) {
            stats.reset();
        }
    }

    private void setValueIfSupported(int index, long value) {
        long[] jArr = this.mAccumulatedChargeMicroCoulomb;
        if (jArr[index] != -1) {
            jArr[index] = value;
        }
    }

    public boolean isStandardBucketSupported(int bucket) {
        checkValidStandardBucket(bucket);
        return isIndexSupported(bucket);
    }

    private boolean isIndexSupported(int index) {
        return this.mAccumulatedChargeMicroCoulomb[index] != -1;
    }

    public boolean isSupportEqualTo(boolean[] queriedStandardBuckets, String[] customBucketNames) {
        if (customBucketNames == null) {
            customBucketNames = new String[0];
        }
        int numBuckets = getNumberOfIndices();
        int numCustomBuckets = customBucketNames == null ? 0 : customBucketNames.length;
        if (!(numBuckets == numCustomBuckets + 8 && Arrays.equals(this.mCustomBucketNames, customBucketNames))) {
            return false;
        }
        for (int stdBucket = 0; stdBucket < 8; stdBucket++) {
            if (isStandardBucketSupported(stdBucket) != queriedStandardBuckets[stdBucket]) {
                return false;
            }
        }
        return true;
    }

    public String[] getCustomBucketNames() {
        return this.mCustomBucketNames;
    }

    public void dump(PrintWriter pw) {
        pw.print("   ");
        for (int index = 0; index < this.mAccumulatedChargeMicroCoulomb.length; index++) {
            pw.print(getBucketName(index));
            pw.print(" : ");
            pw.print(this.mAccumulatedChargeMicroCoulomb[index]);
            if (!isIndexSupported(index)) {
                pw.print(" (unsupported)");
            }
            if (index != this.mAccumulatedChargeMicroCoulomb.length - 1) {
                pw.print(", ");
            }
        }
        pw.println();
    }

    private String getBucketName(int index) {
        if (isValidStandardBucket(index)) {
            return DebugUtils.valueToString(MeasuredEnergyStats.class, "POWER_BUCKET_", index);
        }
        int customBucket = indexToCustomBucket(index);
        StringBuilder sb = new StringBuilder();
        sb.append("CUSTOM_");
        StringBuilder name = sb.append(customBucket);
        String[] strArr = this.mCustomBucketNames;
        if (strArr != null && !TextUtils.isEmpty(strArr[customBucket])) {
            name.append('(');
            name.append(this.mCustomBucketNames[customBucket]);
            name.append(')');
        }
        return name.toString();
    }

    public int getNumberCustomPowerBuckets() {
        return this.mAccumulatedChargeMicroCoulomb.length - 8;
    }

    private static int customBucketToIndex(int customBucket) {
        return customBucket + 8;
    }

    private static int indexToCustomBucket(int index) {
        return index - 8;
    }

    private static void checkValidStandardBucket(int bucket) {
        if (!isValidStandardBucket(bucket)) {
            throw new IllegalArgumentException("Illegal StandardPowerBucket " + bucket);
        }
    }

    private static boolean isValidStandardBucket(int bucket) {
        return bucket >= 0 && bucket < 8;
    }

    public boolean isValidCustomBucket(int customBucket) {
        return customBucket >= 0 && customBucketToIndex(customBucket) < this.mAccumulatedChargeMicroCoulomb.length;
    }
}
