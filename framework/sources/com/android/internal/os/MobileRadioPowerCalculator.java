package com.android.internal.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.telephony.CellSignalStrength;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class MobileRadioPowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    private static final int NUM_SIGNAL_STRENGTH_LEVELS = CellSignalStrength.getNumSignalStrengthLevels();
    private static final String TAG = "MobRadioPowerCalculator";
    private final UsageBasedPowerEstimator mActivePowerEstimator;
    private final UsageBasedPowerEstimator[] mIdlePowerEstimators = new UsageBasedPowerEstimator[NUM_SIGNAL_STRENGTH_LEVELS];
    private final UsageBasedPowerEstimator mScanPowerEstimator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PowerAndDuration {
        public long durationMs;
        public long noCoverageDurationMs;
        public double remainingPowerMah;
        public long signalDurationMs;
        public long totalAppDurationMs;
        public double totalAppPowerMah;

        private PowerAndDuration() {
        }
    }

    public MobileRadioPowerCalculator(PowerProfile profile) {
        int i;
        double powerRadioActiveMa = profile.getAveragePowerOrDefault(PowerProfile.POWER_RADIO_ACTIVE, -1.0d);
        if (powerRadioActiveMa == -1.0d) {
            double sum = 0.0d + profile.getAveragePower(PowerProfile.POWER_MODEM_CONTROLLER_RX);
            int i2 = 0;
            while (true) {
                i = NUM_SIGNAL_STRENGTH_LEVELS;
                if (i2 >= i) {
                    break;
                }
                sum += profile.getAveragePower(PowerProfile.POWER_MODEM_CONTROLLER_TX, i2);
                i2++;
            }
            powerRadioActiveMa = sum / (i + 1);
        }
        this.mActivePowerEstimator = new UsageBasedPowerEstimator(powerRadioActiveMa);
        if (profile.getAveragePowerOrDefault(PowerProfile.POWER_RADIO_ON, -1.0d) != -1.0d) {
            for (int i3 = 0; i3 < NUM_SIGNAL_STRENGTH_LEVELS; i3++) {
                this.mIdlePowerEstimators[i3] = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_RADIO_ON, i3));
            }
        } else {
            double idle = profile.getAveragePower(PowerProfile.POWER_MODEM_CONTROLLER_IDLE);
            this.mIdlePowerEstimators[0] = new UsageBasedPowerEstimator((25.0d * idle) / 180.0d);
            for (int i4 = 1; i4 < NUM_SIGNAL_STRENGTH_LEVELS; i4++) {
                this.mIdlePowerEstimators[i4] = new UsageBasedPowerEstimator(Math.max(1.0d, idle / 256.0d));
            }
        }
        this.mScanPowerEstimator = new UsageBasedPowerEstimator(profile.getAveragePowerOrDefault(PowerProfile.POWER_RADIO_SCANNING, 0.0d));
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        PowerAndDuration total = new PowerAndDuration();
        double powerPerPacketMah = getMobilePowerPerPacket(batteryStats, rawRealtimeUs, 0);
        SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
        for (int i = uidBatteryConsumerBuilders.size() - 1; i >= 0; i--) {
            UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
            BatteryStats.Uid uid = app.getBatteryStatsUid();
            calculateApp(app, uid, powerPerPacketMah, total, query);
        }
        long consumptionUC = batteryStats.getMobileRadioMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC, query);
        calculateRemaining(total, powerModel, batteryStats, rawRealtimeUs, consumptionUC);
        if (!(total.remainingPowerMah == 0.0d && total.totalAppPowerMah == 0.0d)) {
            ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setUsageDurationMillis(8, total.durationMs)).setConsumedPower(8, total.remainingPowerMah + total.totalAppPowerMah, powerModel);
            ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(1).setUsageDurationMillis(8, total.durationMs)).setConsumedPower(8, total.totalAppPowerMah, powerModel);
        }
    }

    private void calculateApp(UidBatteryConsumer.Builder app, BatteryStats.Uid u, double powerPerPacketMah, PowerAndDuration total, BatteryUsageStatsQuery query) {
        long radioActiveDurationMs = calculateDuration(u, 0);
        total.totalAppDurationMs += radioActiveDurationMs;
        long consumptionUC = u.getMobileRadioMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC, query);
        double powerMah = calculatePower(u, powerModel, powerPerPacketMah, radioActiveDurationMs, consumptionUC);
        total.totalAppPowerMah += powerMah;
        ((UidBatteryConsumer.Builder) app.setUsageDurationMillis(8, radioActiveDurationMs)).setConsumedPower(8, powerMah, powerModel);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        PowerAndDuration total;
        int i;
        double mobilePowerPerPacket = getMobilePowerPerPacket(batteryStats, rawRealtimeUs, statsType);
        PowerAndDuration total2 = new PowerAndDuration();
        int i2 = sippers.size() - 1;
        while (i2 >= 0) {
            BatterySipper app = sippers.get(i2);
            if (app.drainType == BatterySipper.DrainType.APP) {
                BatteryStats.Uid u = app.uidObj;
                i = i2;
                total = total2;
                calculateApp(app, u, statsType, mobilePowerPerPacket, total2);
            } else {
                i = i2;
                total = total2;
            }
            i2 = i - 1;
            total2 = total;
        }
        BatterySipper radio = new BatterySipper(BatterySipper.DrainType.CELL, null, 0.0d);
        long consumptionUC = batteryStats.getMobileRadioMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC);
        calculateRemaining(total2, powerModel, batteryStats, rawRealtimeUs, consumptionUC);
        if (total2.remainingPowerMah != 0.0d) {
            if (total2.signalDurationMs != 0) {
                radio.noCoveragePercent = (total2.noCoverageDurationMs * 100.0d) / total2.signalDurationMs;
            }
            radio.mobileActive = total2.durationMs;
            radio.mobileActiveCount = batteryStats.getMobileRadioActiveUnknownCount(statsType);
            radio.mobileRadioPowerMah = total2.remainingPowerMah;
            radio.sumPower();
        }
        if (radio.totalPowerMah > 0.0d) {
            sippers.add(radio);
        }
    }

    private void calculateApp(BatterySipper app, BatteryStats.Uid u, int statsType, double powerPerPacketMah, PowerAndDuration total) {
        app.mobileActive = calculateDuration(u, statsType);
        long consumptionUC = u.getMobileRadioMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC);
        app.mobileRadioPowerMah = calculatePower(u, powerModel, powerPerPacketMah, app.mobileActive, consumptionUC);
        total.totalAppDurationMs += app.mobileActive;
        app.mobileRxPackets = u.getNetworkActivityPackets(0, statsType);
        app.mobileTxPackets = u.getNetworkActivityPackets(1, statsType);
        app.mobileActiveCount = u.getMobileRadioActiveCount(statsType);
        app.mobileRxBytes = u.getNetworkActivityBytes(0, statsType);
        app.mobileTxBytes = u.getNetworkActivityBytes(1, statsType);
    }

    private long calculateDuration(BatteryStats.Uid u, int statsType) {
        return u.getMobileRadioActiveTime(statsType) / 1000;
    }

    private double calculatePower(BatteryStats.Uid u, int powerModel, double powerPerPacketMah, long radioActiveDurationMs, long measuredChargeUC) {
        if (powerModel == 2) {
            return uCtoMah(measuredChargeUC);
        }
        if (radioActiveDurationMs > 0) {
            return calcPowerFromRadioActiveDurationMah(radioActiveDurationMs);
        }
        long mobileRxPackets = u.getNetworkActivityPackets(0, 0);
        long mobileTxPackets = u.getNetworkActivityPackets(1, 0);
        return (mobileRxPackets + mobileTxPackets) * powerPerPacketMah;
    }

    private void calculateRemaining(PowerAndDuration total, int powerModel, BatteryStats batteryStats, long rawRealtimeUs, long consumptionUC) {
        long signalTimeMs = 0;
        double powerMah = 0.0d;
        if (powerModel == 2) {
            powerMah = uCtoMah(consumptionUC);
        }
        for (int i = 0; i < NUM_SIGNAL_STRENGTH_LEVELS; i++) {
            long strengthTimeMs = batteryStats.getPhoneSignalStrengthTime(i, rawRealtimeUs, 0) / 1000;
            if (powerModel == 1) {
                double p = calcIdlePowerAtSignalStrengthMah(strengthTimeMs, i);
                powerMah += p;
            }
            signalTimeMs += strengthTimeMs;
            if (i == 0) {
                total.noCoverageDurationMs = strengthTimeMs;
            }
        }
        long scanningTimeMs = batteryStats.getPhoneSignalScanningTime(rawRealtimeUs, 0) / 1000;
        long radioActiveTimeMs = batteryStats.getMobileRadioActiveTime(rawRealtimeUs, 0) / 1000;
        long remainingActiveTimeMs = radioActiveTimeMs - total.totalAppDurationMs;
        if (powerModel == 1) {
            double p2 = calcScanTimePowerMah(scanningTimeMs);
            powerMah += p2;
            if (remainingActiveTimeMs > 0) {
                powerMah += calcPowerFromRadioActiveDurationMah(remainingActiveTimeMs);
            }
        }
        total.durationMs = radioActiveTimeMs;
        total.remainingPowerMah = powerMah;
        total.signalDurationMs = signalTimeMs;
    }

    public double calcPowerFromRadioActiveDurationMah(long radioActiveDurationMs) {
        return this.mActivePowerEstimator.calculatePower(radioActiveDurationMs);
    }

    public double calcIdlePowerAtSignalStrengthMah(long strengthTimeMs, int strengthLevel) {
        return this.mIdlePowerEstimators[strengthLevel].calculatePower(strengthTimeMs);
    }

    public double calcScanTimePowerMah(long scanningTimeMs) {
        return this.mScanPowerEstimator.calculatePower(scanningTimeMs);
    }

    private double getMobilePowerPerPacket(BatteryStats stats, long rawRealtimeUs, int statsType) {
        long radioDataUptimeMs = stats.getMobileRadioActiveTime(rawRealtimeUs, statsType) / 1000;
        double mobilePower = calcPowerFromRadioActiveDurationMah(radioDataUptimeMs);
        long mobileRx = stats.getNetworkActivityPackets(0, statsType);
        long mobileTx = stats.getNetworkActivityPackets(1, statsType);
        long mobilePackets = mobileRx + mobileTx;
        if (mobilePackets != 0) {
            return mobilePower / mobilePackets;
        }
        return 0.0d;
    }
}
