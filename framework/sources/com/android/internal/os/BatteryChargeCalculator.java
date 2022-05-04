package com.android.internal.os;

import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UserHandle;
import android.util.SparseArray;
import java.util.List;
/* loaded from: classes4.dex */
public class BatteryChargeCalculator extends PowerCalculator {
    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        long dischargeMah;
        builder.setDischargePercentage(batteryStats.getDischargeAmount(0));
        int batteryCapacityMah = batteryStats.getLearnedBatteryCapacity() / 1000;
        if (batteryCapacityMah <= 0 && (batteryCapacityMah = batteryStats.getMinLearnedBatteryCapacity() / 1000) <= 0) {
            batteryCapacityMah = batteryStats.getEstimatedBatteryCapacity();
        }
        builder.setBatteryCapacity(batteryCapacityMah);
        double dischargedPowerLowerBoundMah = (batteryStats.getLowDischargeAmountSinceCharge() * batteryCapacityMah) / 100.0d;
        double dischargedPowerUpperBoundMah = (batteryStats.getHighDischargeAmountSinceCharge() * batteryCapacityMah) / 100.0d;
        builder.setDischargePercentage(batteryStats.getDischargeAmount(0)).setDischargedPowerRange(dischargedPowerLowerBoundMah, dischargedPowerUpperBoundMah);
        long batteryTimeRemainingMs = batteryStats.computeBatteryTimeRemaining(rawRealtimeUs);
        if (batteryTimeRemainingMs != -1) {
            builder.setBatteryTimeRemainingMs(batteryTimeRemainingMs / 1000);
        }
        long chargeTimeRemainingMs = batteryStats.computeChargeTimeRemaining(rawRealtimeUs);
        if (chargeTimeRemainingMs != -1) {
            builder.setChargeTimeRemainingMs(chargeTimeRemainingMs / 1000);
        }
        long dischargeMah2 = batteryStats.getUahDischarge(0) / 1000;
        if (dischargeMah2 == 0) {
            dischargeMah = (long) (((dischargedPowerLowerBoundMah + dischargedPowerUpperBoundMah) / 2.0d) + 0.5d);
        } else {
            dischargeMah = dischargeMah2;
        }
        builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(dischargeMah);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
    }
}
