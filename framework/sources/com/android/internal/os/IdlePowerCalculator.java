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
public class IdlePowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    private static final String TAG = "IdlePowerCalculator";
    private final double mAveragePowerCpuIdleMahPerUs;
    private final double mAveragePowerCpuSuspendMahPerUs;
    public long mDurationMs;
    public double mPowerMah;

    public IdlePowerCalculator(PowerProfile powerProfile) {
        this.mAveragePowerCpuSuspendMahPerUs = powerProfile.getAveragePower(PowerProfile.POWER_CPU_SUSPEND) / 3.6E9d;
        this.mAveragePowerCpuIdleMahPerUs = powerProfile.getAveragePower(PowerProfile.POWER_CPU_IDLE) / 3.6E9d;
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        calculatePowerAndDuration(batteryStats, rawRealtimeUs, rawUptimeUs, 0);
        if (this.mPowerMah != 0.0d) {
            ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(16, this.mPowerMah)).setUsageDurationMillis(16, this.mDurationMs);
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        calculatePowerAndDuration(batteryStats, rawRealtimeUs, rawUptimeUs, statsType);
        if (this.mPowerMah != 0.0d) {
            BatterySipper bs = new BatterySipper(BatterySipper.DrainType.IDLE, null, 0.0d);
            bs.usagePowerMah = this.mPowerMah;
            bs.usageTimeMs = this.mDurationMs;
            bs.sumPower();
            sippers.add(bs);
        }
    }

    private void calculatePowerAndDuration(BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType) {
        long batteryRealtimeUs = batteryStats.computeBatteryRealtime(rawRealtimeUs, statsType);
        long batteryUptimeUs = batteryStats.computeBatteryUptime(rawUptimeUs, statsType);
        double suspendPowerMah = batteryRealtimeUs * this.mAveragePowerCpuSuspendMahPerUs;
        double idlePowerMah = batteryUptimeUs * this.mAveragePowerCpuIdleMahPerUs;
        this.mPowerMah = suspendPowerMah + idlePowerMah;
        this.mDurationMs = batteryRealtimeUs / 1000;
    }
}
