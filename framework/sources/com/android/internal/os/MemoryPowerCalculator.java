package com.android.internal.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UserHandle;
import android.util.LongSparseArray;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class MemoryPowerCalculator extends PowerCalculator {
    public static final String TAG = "MemoryPowerCalculator";
    private final UsageBasedPowerEstimator[] mPowerEstimators;

    public MemoryPowerCalculator(PowerProfile profile) {
        int numBuckets = profile.getNumElements(PowerProfile.POWER_MEMORY);
        this.mPowerEstimators = new UsageBasedPowerEstimator[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            this.mPowerEstimators[i] = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_MEMORY, i));
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        long durationMs = calculateDuration(batteryStats, rawRealtimeUs, 0);
        double powerMah = calculatePower(batteryStats, rawRealtimeUs, 0);
        ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setUsageDurationMillis(13, durationMs)).setConsumedPower(13, powerMah);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        long durationMs = calculateDuration(batteryStats, rawRealtimeUs, statsType);
        double powerMah = calculatePower(batteryStats, rawRealtimeUs, statsType);
        BatterySipper memory = new BatterySipper(BatterySipper.DrainType.MEMORY, null, 0.0d);
        memory.usageTimeMs = durationMs;
        memory.usagePowerMah = powerMah;
        memory.sumPower();
        if (memory.totalPowerMah > 0.0d) {
            sippers.add(memory);
        }
    }

    private long calculateDuration(BatteryStats batteryStats, long rawRealtimeUs, int statsType) {
        long usageDurationMs = 0;
        LongSparseArray<? extends BatteryStats.Timer> timers = batteryStats.getKernelMemoryStats();
        for (int i = 0; i < timers.size(); i++) {
            UsageBasedPowerEstimator[] usageBasedPowerEstimatorArr = this.mPowerEstimators;
            if (i >= usageBasedPowerEstimatorArr.length) {
                break;
            }
            usageDurationMs += usageBasedPowerEstimatorArr[i].calculateDuration((BatteryStats.Timer) timers.valueAt(i), rawRealtimeUs, statsType);
        }
        return usageDurationMs;
    }

    private double calculatePower(BatteryStats batteryStats, long rawRealtimeUs, int statsType) {
        double powerMah = 0.0d;
        LongSparseArray<? extends BatteryStats.Timer> timers = batteryStats.getKernelMemoryStats();
        for (int i = 0; i < timers.size(); i++) {
            UsageBasedPowerEstimator[] usageBasedPowerEstimatorArr = this.mPowerEstimators;
            if (i >= usageBasedPowerEstimatorArr.length) {
                break;
            }
            UsageBasedPowerEstimator estimator = usageBasedPowerEstimatorArr[(int) timers.keyAt(i)];
            long usageDurationMs = estimator.calculateDuration((BatteryStats.Timer) timers.valueAt(i), rawRealtimeUs, statsType);
            powerMah += estimator.calculatePower(usageDurationMs);
        }
        return powerMah;
    }
}
