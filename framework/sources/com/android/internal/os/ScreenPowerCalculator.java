package com.android.internal.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseLongArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class ScreenPowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    public static final long MIN_ACTIVE_TIME_FOR_SMEARING = 600000;
    private static final String TAG = "ScreenPowerCalculator";
    private final UsageBasedPowerEstimator mScreenFullPowerEstimator;
    private final UsageBasedPowerEstimator mScreenOnPowerEstimator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PowerAndDuration {
        public long durationMs;
        public double powerMah;

        private PowerAndDuration() {
        }
    }

    public ScreenPowerCalculator(PowerProfile powerProfile) {
        this.mScreenOnPowerEstimator = new UsageBasedPowerEstimator(powerProfile.getAveragePower(PowerProfile.POWER_SCREEN_ON));
        this.mScreenFullPowerEstimator = new UsageBasedPowerEstimator(powerProfile.getAveragePower(PowerProfile.POWER_SCREEN_FULL));
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        ScreenPowerCalculator screenPowerCalculator = this;
        PowerAndDuration totalPowerAndDuration = new PowerAndDuration();
        long consumptionUC = batteryStats.getScreenOnMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC, query);
        calculateTotalDurationAndPower(totalPowerAndDuration, powerModel, batteryStats, rawRealtimeUs, 0, consumptionUC);
        double totalAppPower = 0.0d;
        long totalAppDuration = 0;
        SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
        switch (powerModel) {
            case 2:
                PowerAndDuration appPowerAndDuration = new PowerAndDuration();
                int i = uidBatteryConsumerBuilders.size() - 1;
                while (i >= 0) {
                    UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
                    screenPowerCalculator.calculateAppUsingMeasuredEnergy(appPowerAndDuration, app.getBatteryStatsUid(), rawRealtimeUs);
                    ((UidBatteryConsumer.Builder) app.setUsageDurationMillis(0, appPowerAndDuration.durationMs)).setConsumedPower(0, appPowerAndDuration.powerMah, powerModel);
                    totalAppPower += appPowerAndDuration.powerMah;
                    totalAppDuration += appPowerAndDuration.durationMs;
                    i--;
                    screenPowerCalculator = this;
                    uidBatteryConsumerBuilders = uidBatteryConsumerBuilders;
                }
                break;
            default:
                smearScreenBatteryDrain(uidBatteryConsumerBuilders, totalPowerAndDuration, rawRealtimeUs);
                totalAppPower = totalPowerAndDuration.powerMah;
                totalAppDuration = totalPowerAndDuration.durationMs;
                break;
        }
        ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(0, Math.max(totalPowerAndDuration.powerMah, totalAppPower), powerModel)).setUsageDurationMillis(0, totalPowerAndDuration.durationMs);
        ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(1).setConsumedPower(0, totalAppPower, powerModel)).setUsageDurationMillis(0, totalAppDuration);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        PowerAndDuration totalPowerAndDuration = new PowerAndDuration();
        long consumptionUC = batteryStats.getScreenOnMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC);
        calculateTotalDurationAndPower(totalPowerAndDuration, powerModel, batteryStats, rawRealtimeUs, statsType, consumptionUC);
        if (totalPowerAndDuration.powerMah != 0.0d) {
            BatterySipper bs = new BatterySipper(BatterySipper.DrainType.SCREEN, null, 0.0d);
            bs.usagePowerMah = totalPowerAndDuration.powerMah;
            bs.usageTimeMs = totalPowerAndDuration.durationMs;
            bs.sumPower();
            sippers.add(bs);
            switch (powerModel) {
                case 2:
                    PowerAndDuration appPowerAndDuration = new PowerAndDuration();
                    for (int i = sippers.size() - 1; i >= 0; i--) {
                        BatterySipper app = sippers.get(i);
                        if (app.drainType == BatterySipper.DrainType.APP) {
                            calculateAppUsingMeasuredEnergy(appPowerAndDuration, app.uidObj, rawRealtimeUs);
                            app.screenPowerMah = appPowerAndDuration.powerMah;
                        }
                    }
                    return;
                default:
                    smearScreenBatterySipper(sippers, bs, rawRealtimeUs);
                    return;
            }
        }
    }

    private void calculateTotalDurationAndPower(PowerAndDuration totalPowerAndDuration, int powerModel, BatteryStats batteryStats, long rawRealtimeUs, int statsType, long consumptionUC) {
        totalPowerAndDuration.durationMs = calculateDuration(batteryStats, rawRealtimeUs, statsType);
        switch (powerModel) {
            case 2:
                totalPowerAndDuration.powerMah = uCtoMah(consumptionUC);
                return;
            default:
                totalPowerAndDuration.powerMah = calculateTotalPowerFromBrightness(batteryStats, rawRealtimeUs, statsType, totalPowerAndDuration.durationMs);
                return;
        }
    }

    private void calculateAppUsingMeasuredEnergy(PowerAndDuration appPowerAndDuration, BatteryStats.Uid u, long rawRealtimeUs) {
        appPowerAndDuration.durationMs = getProcessForegroundTimeMs(u, rawRealtimeUs);
        long chargeUC = u.getScreenOnMeasuredBatteryConsumptionUC();
        if (chargeUC < 0) {
            Slog.wtf(TAG, "Screen energy not supported, so calculateApp shouldn't de called");
            appPowerAndDuration.powerMah = 0.0d;
            return;
        }
        appPowerAndDuration.powerMah = uCtoMah(chargeUC);
    }

    private long calculateDuration(BatteryStats batteryStats, long rawRealtimeUs, int statsType) {
        return batteryStats.getScreenOnTime(rawRealtimeUs, statsType) / 1000;
    }

    private double calculateTotalPowerFromBrightness(BatteryStats batteryStats, long rawRealtimeUs, int statsType, long durationMs) {
        double power = this.mScreenOnPowerEstimator.calculatePower(durationMs);
        for (int i = 0; i < 5; i++) {
            long brightnessTime = batteryStats.getScreenBrightnessTime(i, rawRealtimeUs, statsType) / 1000;
            double binPowerMah = (this.mScreenFullPowerEstimator.calculatePower(brightnessTime) * (i + 0.5f)) / 5.0d;
            power += binPowerMah;
        }
        return power;
    }

    public void smearScreenBatterySipper(List<BatterySipper> sippers, BatterySipper screenSipper, long rawRealtimeUs) {
        long totalActivityTimeMs = 0;
        SparseLongArray activityTimeArray = new SparseLongArray();
        for (int i = sippers.size() - 1; i >= 0; i--) {
            BatteryStats.Uid uid = sippers.get(i).uidObj;
            if (uid != null) {
                long timeMs = getProcessForegroundTimeMs(uid, rawRealtimeUs);
                activityTimeArray.put(uid.getUid(), timeMs);
                totalActivityTimeMs += timeMs;
            }
        }
        if (screenSipper != null && totalActivityTimeMs >= MIN_ACTIVE_TIME_FOR_SMEARING) {
            double totalScreenPowerMah = screenSipper.totalPowerMah;
            for (int i2 = sippers.size() - 1; i2 >= 0; i2--) {
                BatterySipper sipper = sippers.get(i2);
                sipper.screenPowerMah = (activityTimeArray.get(sipper.getUid(), 0L) * totalScreenPowerMah) / totalActivityTimeMs;
            }
        }
    }

    private void smearScreenBatteryDrain(SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders, PowerAndDuration totalPowerAndDuration, long rawRealtimeUs) {
        long totalActivityTimeMs = 0;
        SparseLongArray activityTimeArray = new SparseLongArray();
        for (int i = uidBatteryConsumerBuilders.size() - 1; i >= 0; i--) {
            BatteryStats.Uid uid = uidBatteryConsumerBuilders.valueAt(i).getBatteryStatsUid();
            long timeMs = getProcessForegroundTimeMs(uid, rawRealtimeUs);
            activityTimeArray.put(uid.getUid(), timeMs);
            totalActivityTimeMs += timeMs;
        }
        if (totalActivityTimeMs >= MIN_ACTIVE_TIME_FOR_SMEARING) {
            double totalScreenPowerMah = totalPowerAndDuration.powerMah;
            int i2 = uidBatteryConsumerBuilders.size() - 1;
            while (i2 >= 0) {
                UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i2);
                long durationMs = activityTimeArray.get(app.getUid(), 0L);
                double powerMah = (durationMs * totalScreenPowerMah) / totalActivityTimeMs;
                ((UidBatteryConsumer.Builder) app.setUsageDurationMillis(0, durationMs)).setConsumedPower(0, powerMah, 1);
                i2--;
                activityTimeArray = activityTimeArray;
            }
        }
    }

    public long getProcessForegroundTimeMs(BatteryStats.Uid uid, long rawRealTimeUs) {
        int[] foregroundTypes = {0};
        long timeUs = 0;
        for (int type : foregroundTypes) {
            long localTime = uid.getProcessStateTime(type, rawRealTimeUs, 0);
            timeUs += localTime;
        }
        return Math.min(timeUs, getForegroundActivityTotalTimeUs(uid, rawRealTimeUs)) / 1000;
    }

    public long getForegroundActivityTotalTimeUs(BatteryStats.Uid uid, long rawRealtimeUs) {
        BatteryStats.Timer timer = uid.getForegroundActivityTimer();
        if (timer == null) {
            return 0L;
        }
        return timer.getTotalTimeLocked(rawRealtimeUs, 0);
    }
}
