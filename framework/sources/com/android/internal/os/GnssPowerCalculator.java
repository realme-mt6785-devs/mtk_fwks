package com.android.internal.os;

import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class GnssPowerCalculator extends PowerCalculator {
    private final double mAveragePowerGnssOn;
    private final double[] mAveragePowerPerSignalQuality = new double[2];

    public GnssPowerCalculator(PowerProfile profile) {
        this.mAveragePowerGnssOn = profile.getAveragePowerOrDefault(PowerProfile.POWER_GPS_ON, -1.0d);
        for (int i = 0; i < 2; i++) {
            this.mAveragePowerPerSignalQuality[i] = profile.getAveragePower(PowerProfile.POWER_GPS_SIGNAL_QUALITY_BASED, i);
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        double powerMah;
        double appsPowerMah = 0.0d;
        double averageGnssPowerMa = getAverageGnssPower(batteryStats, rawRealtimeUs, 0);
        SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
        int i = uidBatteryConsumerBuilders.size() - 1;
        while (i >= 0) {
            UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
            long consumptionUC = app.getBatteryStatsUid().getGnssMeasuredBatteryConsumptionUC();
            appsPowerMah += calculateApp(app, app.getBatteryStatsUid(), getPowerModel(consumptionUC, query), rawRealtimeUs, averageGnssPowerMa, consumptionUC);
            i--;
            uidBatteryConsumerBuilders = uidBatteryConsumerBuilders;
        }
        long consumptionUC2 = batteryStats.getGnssMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC2, query);
        if (powerModel == 2) {
            powerMah = uCtoMah(consumptionUC2);
        } else {
            powerMah = appsPowerMah;
        }
        builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(10, powerMah, powerModel);
        builder.getAggregateBatteryConsumerBuilder(1).setConsumedPower(10, appsPowerMah, powerModel);
    }

    private double calculateApp(UidBatteryConsumer.Builder app, BatteryStats.Uid u, int powerModel, long rawRealtimeUs, double averageGnssPowerMa, long measuredChargeUC) {
        double powerMah;
        long durationMs = computeDuration(u, rawRealtimeUs, 0);
        switch (powerModel) {
            case 2:
                powerMah = uCtoMah(measuredChargeUC);
                break;
            default:
                powerMah = computePower(durationMs, averageGnssPowerMa);
                break;
        }
        ((UidBatteryConsumer.Builder) app.setUsageDurationMillis(10, durationMs)).setConsumedPower(10, powerMah, powerModel);
        return powerMah;
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        int i;
        double averageGnssPowerMa = getAverageGnssPower(batteryStats, rawRealtimeUs, statsType);
        int i2 = sippers.size() - 1;
        while (i2 >= 0) {
            BatterySipper app = sippers.get(i2);
            if (app.drainType == BatterySipper.DrainType.APP) {
                long consumptionUC = app.uidObj.getGnssMeasuredBatteryConsumptionUC();
                int powerModel = getPowerModel(consumptionUC);
                i = i2;
                calculateApp(app, app.uidObj, powerModel, rawRealtimeUs, averageGnssPowerMa, consumptionUC);
            } else {
                i = i2;
            }
            i2 = i - 1;
        }
    }

    private void calculateApp(BatterySipper app, BatteryStats.Uid u, int powerModel, long rawRealtimeUs, double averageGnssPowerMa, long measuredChargeUC) {
        double powerMah;
        long durationMs = computeDuration(u, rawRealtimeUs, 0);
        switch (powerModel) {
            case 2:
                powerMah = uCtoMah(measuredChargeUC);
                break;
            default:
                powerMah = computePower(durationMs, averageGnssPowerMa);
                break;
        }
        app.gpsTimeMs = durationMs;
        app.gpsPowerMah = powerMah;
    }

    private long computeDuration(BatteryStats.Uid u, long rawRealtimeUs, int statsType) {
        SparseArray<? extends BatteryStats.Uid.Sensor> sensorStats = u.getSensorStats();
        BatteryStats.Uid.Sensor sensor = (BatteryStats.Uid.Sensor) sensorStats.get(-10000);
        if (sensor == null) {
            return 0L;
        }
        BatteryStats.Timer timer = sensor.getSensorTime();
        return timer.getTotalTimeLocked(rawRealtimeUs, statsType) / 1000;
    }

    private double computePower(long sensorTime, double averageGnssPowerMa) {
        return (sensorTime * averageGnssPowerMa) / 3600000.0d;
    }

    private double getAverageGnssPower(BatteryStats stats, long rawRealtimeUs, int statsType) {
        GnssPowerCalculator gnssPowerCalculator = this;
        double averagePower = gnssPowerCalculator.mAveragePowerGnssOn;
        if (averagePower != -1.0d) {
            return averagePower;
        }
        double averagePower2 = 0.0d;
        long totalTime = 0;
        double totalPower = 0.0d;
        int i = 0;
        while (i < 2) {
            long timePerLevel = stats.getGpsSignalQualityTime(i, rawRealtimeUs, statsType);
            totalTime += timePerLevel;
            totalPower += gnssPowerCalculator.mAveragePowerPerSignalQuality[i] * timePerLevel;
            i++;
            gnssPowerCalculator = this;
            averagePower2 = averagePower2;
        }
        if (totalTime != 0) {
            return totalPower / totalTime;
        }
        return averagePower2;
    }
}
