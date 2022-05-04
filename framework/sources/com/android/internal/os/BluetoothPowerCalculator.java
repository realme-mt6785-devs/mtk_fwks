package com.android.internal.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class BluetoothPowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    private static final String TAG = "BluetoothPowerCalc";
    private final boolean mHasBluetoothPowerController;
    private final double mIdleMa;
    private final double mRxMa;
    private final double mTxMa;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PowerAndDuration {
        public long durationMs;
        public double powerMah;

        private PowerAndDuration() {
        }
    }

    public BluetoothPowerCalculator(PowerProfile profile) {
        double averagePower = profile.getAveragePower(PowerProfile.POWER_BLUETOOTH_CONTROLLER_IDLE);
        this.mIdleMa = averagePower;
        double averagePower2 = profile.getAveragePower(PowerProfile.POWER_BLUETOOTH_CONTROLLER_RX);
        this.mRxMa = averagePower2;
        double averagePower3 = profile.getAveragePower(PowerProfile.POWER_BLUETOOTH_CONTROLLER_TX);
        this.mTxMa = averagePower3;
        this.mHasBluetoothPowerController = (averagePower == 0.0d || averagePower2 == 0.0d || averagePower3 == 0.0d) ? false : true;
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        if (batteryStats.hasBluetoothActivityReporting()) {
            PowerAndDuration total = new PowerAndDuration();
            SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
            for (int i = uidBatteryConsumerBuilders.size() - 1; i >= 0; i--) {
                UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
                calculateApp(app, total, query);
            }
            long measuredChargeUC = batteryStats.getBluetoothMeasuredBatteryConsumptionUC();
            int powerModel = getPowerModel(measuredChargeUC, query);
            BatteryStats.ControllerActivityCounter activityCounter = batteryStats.getBluetoothControllerActivity();
            long systemDurationMs = calculateDuration(activityCounter);
            double systemPowerMah = calculatePowerMah(powerModel, measuredChargeUC, activityCounter, query.shouldForceUsePowerProfileModel());
            Math.max(0L, systemDurationMs - total.durationMs);
            ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setUsageDurationMillis(2, systemDurationMs)).setConsumedPower(2, Math.max(systemPowerMah, total.powerMah), powerModel);
            ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(1).setUsageDurationMillis(2, total.durationMs)).setConsumedPower(2, total.powerMah, powerModel);
        }
    }

    private void calculateApp(UidBatteryConsumer.Builder app, PowerAndDuration total, BatteryUsageStatsQuery query) {
        long measuredChargeUC = app.getBatteryStatsUid().getBluetoothMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(measuredChargeUC, query);
        BatteryStats.ControllerActivityCounter activityCounter = app.getBatteryStatsUid().getBluetoothControllerActivity();
        long durationMs = calculateDuration(activityCounter);
        double powerMah = calculatePowerMah(powerModel, measuredChargeUC, activityCounter, query.shouldForceUsePowerProfileModel());
        ((UidBatteryConsumer.Builder) app.setUsageDurationMillis(2, durationMs)).setConsumedPower(2, powerMah, powerModel);
        total.durationMs += durationMs;
        total.powerMah += powerMah;
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        boolean z;
        if (this.mHasBluetoothPowerController && batteryStats.hasBluetoothActivityReporting()) {
            PowerAndDuration total = new PowerAndDuration();
            for (int i = sippers.size() - 1; i >= 0; i--) {
                BatterySipper app = sippers.get(i);
                if (app.drainType == BatterySipper.DrainType.APP) {
                    calculateApp(app, app.uidObj, statsType, total);
                }
            }
            BatterySipper bs = new BatterySipper(BatterySipper.DrainType.BLUETOOTH, null, 0.0d);
            long measuredChargeUC = batteryStats.getBluetoothMeasuredBatteryConsumptionUC();
            int powerModel = getPowerModel(measuredChargeUC);
            BatteryStats.ControllerActivityCounter activityCounter = batteryStats.getBluetoothControllerActivity();
            long systemDurationMs = calculateDuration(activityCounter);
            double systemPowerMah = calculatePowerMah(powerModel, measuredChargeUC, activityCounter, false);
            double powerMah = Math.max(0.0d, systemPowerMah - total.powerMah);
            long durationMs = Math.max(0L, systemDurationMs - total.durationMs);
            bs.bluetoothPowerMah = powerMah;
            bs.bluetoothRunningTimeMs = durationMs;
            int i2 = sippers.size() - 1;
            while (i2 >= 0) {
                BatterySipper app2 = sippers.get(i2);
                if (app2.getUid() == 1002) {
                    z = true;
                    app2.isAggregated = true;
                    bs.add(app2);
                } else {
                    z = true;
                }
                i2--;
                systemPowerMah = systemPowerMah;
            }
            if (bs.sumPower() > 0.0d) {
                sippers.add(bs);
            }
        }
    }

    private void calculateApp(BatterySipper app, BatteryStats.Uid u, int statsType, PowerAndDuration total) {
        long measuredChargeUC = u.getBluetoothMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(measuredChargeUC);
        BatteryStats.ControllerActivityCounter activityCounter = u.getBluetoothControllerActivity();
        long durationMs = calculateDuration(activityCounter);
        double powerMah = calculatePowerMah(powerModel, measuredChargeUC, activityCounter, false);
        app.bluetoothRunningTimeMs = durationMs;
        app.bluetoothPowerMah = powerMah;
        app.btRxBytes = u.getNetworkActivityBytes(4, statsType);
        app.btTxBytes = u.getNetworkActivityBytes(5, statsType);
        total.durationMs += durationMs;
        total.powerMah += powerMah;
    }

    private long calculateDuration(BatteryStats.ControllerActivityCounter counter) {
        if (counter == null) {
            return 0L;
        }
        return counter.getIdleTimeCounter().getCountLocked(0) + counter.getRxTimeCounter().getCountLocked(0) + counter.getTxTimeCounters()[0].getCountLocked(0);
    }

    private double calculatePowerMah(int powerModel, long measuredChargeUC, BatteryStats.ControllerActivityCounter counter, boolean ignoreReportedPower) {
        if (powerModel == 2) {
            return uCtoMah(measuredChargeUC);
        }
        if (counter == null) {
            return 0.0d;
        }
        if (!ignoreReportedPower) {
            double powerMah = counter.getPowerCounter().getCountLocked(0) / 3600000.0d;
            if (powerMah != 0.0d) {
                return powerMah;
            }
        }
        if (!this.mHasBluetoothPowerController) {
            return 0.0d;
        }
        long idleTimeMs = counter.getIdleTimeCounter().getCountLocked(0);
        long rxTimeMs = counter.getRxTimeCounter().getCountLocked(0);
        long txTimeMs = counter.getTxTimeCounters()[0].getCountLocked(0);
        return calculatePowerMah(rxTimeMs, txTimeMs, idleTimeMs);
    }

    public double calculatePowerMah(long rxTimeMs, long txTimeMs, long idleTimeMs) {
        return (((idleTimeMs * this.mIdleMa) + (rxTimeMs * this.mRxMa)) + (txTimeMs * this.mTxMa)) / 3600000.0d;
    }
}
