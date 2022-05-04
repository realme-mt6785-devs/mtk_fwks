package com.android.internal.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class AmbientDisplayPowerCalculator extends PowerCalculator {
    private final UsageBasedPowerEstimator mPowerEstimator;

    public AmbientDisplayPowerCalculator(PowerProfile powerProfile) {
        this.mPowerEstimator = new UsageBasedPowerEstimator(powerProfile.getAveragePower(PowerProfile.POWER_AMBIENT_DISPLAY));
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        long measuredEnergyUC = batteryStats.getScreenDozeMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(measuredEnergyUC, query);
        long durationMs = calculateDuration(batteryStats, rawRealtimeUs, 0);
        double powerMah = getMeasuredOrEstimatedPower(powerModel, measuredEnergyUC, this.mPowerEstimator, durationMs);
        ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setUsageDurationMillis(15, durationMs)).setConsumedPower(15, powerMah, powerModel);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        long measuredEnergyUC = batteryStats.getScreenDozeMeasuredBatteryConsumptionUC();
        long durationMs = calculateDuration(batteryStats, rawRealtimeUs, statsType);
        int powerModel = getPowerModel(measuredEnergyUC);
        double powerMah = getMeasuredOrEstimatedPower(powerModel, batteryStats.getScreenDozeMeasuredBatteryConsumptionUC(), this.mPowerEstimator, durationMs);
        if (powerMah > 0.0d) {
            BatterySipper bs = new BatterySipper(BatterySipper.DrainType.AMBIENT_DISPLAY, null, 0.0d);
            bs.usagePowerMah = powerMah;
            bs.usageTimeMs = durationMs;
            bs.sumPower();
            sippers.add(bs);
        }
    }

    private long calculateDuration(BatteryStats batteryStats, long rawRealtimeUs, int statsType) {
        return batteryStats.getScreenDozeTime(rawRealtimeUs, statsType) / 1000;
    }
}
