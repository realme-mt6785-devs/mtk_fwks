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
public class PhonePowerCalculator extends PowerCalculator {
    private final UsageBasedPowerEstimator mPowerEstimator;

    public PhonePowerCalculator(PowerProfile powerProfile) {
        this.mPowerEstimator = new UsageBasedPowerEstimator(powerProfile.getAveragePower(PowerProfile.POWER_RADIO_ACTIVE));
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        long phoneOnTimeMs = batteryStats.getPhoneOnTime(rawRealtimeUs, 0) / 1000;
        double phoneOnPower = this.mPowerEstimator.calculatePower(phoneOnTimeMs);
        if (phoneOnPower != 0.0d) {
            ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(14, phoneOnPower)).setUsageDurationMillis(14, phoneOnTimeMs);
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        long phoneOnTimeMs = batteryStats.getPhoneOnTime(rawRealtimeUs, statsType) / 1000;
        double phoneOnPower = this.mPowerEstimator.calculatePower(phoneOnTimeMs);
        if (phoneOnPower != 0.0d) {
            BatterySipper bs = new BatterySipper(BatterySipper.DrainType.PHONE, null, 0.0d);
            bs.usagePowerMah = phoneOnPower;
            bs.usageTimeMs = phoneOnTimeMs;
            bs.sumPower();
            sippers.add(bs);
        }
    }
}
