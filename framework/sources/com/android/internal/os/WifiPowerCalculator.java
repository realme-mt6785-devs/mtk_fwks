package com.android.internal.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class WifiPowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    private static final String TAG = "WifiPowerCalculator";
    private final UsageBasedPowerEstimator mBatchScanPowerEstimator;
    private final boolean mHasWifiPowerController;
    private final UsageBasedPowerEstimator mIdlePowerEstimator;
    private final UsageBasedPowerEstimator mPowerOnPowerEstimator;
    private final UsageBasedPowerEstimator mRxPowerEstimator;
    private final UsageBasedPowerEstimator mScanPowerEstimator;
    private final UsageBasedPowerEstimator mTxPowerEstimator;
    private final double mWifiPowerPerPacket;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PowerDurationAndTraffic {
        public long durationMs;
        public double powerMah;
        public long wifiRxBytes;
        public long wifiRxPackets;
        public long wifiTxBytes;
        public long wifiTxPackets;

        private PowerDurationAndTraffic() {
        }
    }

    public WifiPowerCalculator(PowerProfile profile) {
        this.mPowerOnPowerEstimator = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_WIFI_ON));
        this.mScanPowerEstimator = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_WIFI_SCAN));
        this.mBatchScanPowerEstimator = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_WIFI_BATCHED_SCAN));
        UsageBasedPowerEstimator usageBasedPowerEstimator = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_WIFI_CONTROLLER_IDLE));
        this.mIdlePowerEstimator = usageBasedPowerEstimator;
        UsageBasedPowerEstimator usageBasedPowerEstimator2 = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_WIFI_CONTROLLER_TX));
        this.mTxPowerEstimator = usageBasedPowerEstimator2;
        UsageBasedPowerEstimator usageBasedPowerEstimator3 = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_WIFI_CONTROLLER_RX));
        this.mRxPowerEstimator = usageBasedPowerEstimator3;
        this.mWifiPowerPerPacket = getWifiPowerPerPacket(profile);
        this.mHasWifiPowerController = usageBasedPowerEstimator.isSupported() && usageBasedPowerEstimator2.isSupported() && usageBasedPowerEstimator3.isSupported();
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        long totalAppDurationMs = 0;
        double totalAppPowerMah = 0.0d;
        PowerDurationAndTraffic powerDurationAndTraffic = new PowerDurationAndTraffic();
        SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
        int i = uidBatteryConsumerBuilders.size() - 1;
        while (i >= 0) {
            UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
            long consumptionUC = app.getBatteryStatsUid().getWifiMeasuredBatteryConsumptionUC();
            int powerModel = getPowerModel(consumptionUC, query);
            calculateApp(powerDurationAndTraffic, app.getBatteryStatsUid(), powerModel, rawRealtimeUs, 0, batteryStats.hasWifiActivityReporting(), consumptionUC);
            totalAppDurationMs += powerDurationAndTraffic.durationMs;
            totalAppPowerMah += powerDurationAndTraffic.powerMah;
            app.setUsageDurationMillis(11, powerDurationAndTraffic.durationMs);
            app.setConsumedPower(11, powerDurationAndTraffic.powerMah, powerModel);
            i--;
            uidBatteryConsumerBuilders = uidBatteryConsumerBuilders;
        }
        long consumptionUC2 = batteryStats.getWifiMeasuredBatteryConsumptionUC();
        int powerModel2 = getPowerModel(consumptionUC2, query);
        calculateRemaining(powerDurationAndTraffic, powerModel2, batteryStats, rawRealtimeUs, 0, batteryStats.hasWifiActivityReporting(), totalAppDurationMs, totalAppPowerMah, consumptionUC2);
        ((AggregateBatteryConsumer.Builder) builder.getAggregateBatteryConsumerBuilder(0).setUsageDurationMillis(11, powerDurationAndTraffic.durationMs)).setConsumedPower(11, powerDurationAndTraffic.powerMah + totalAppPowerMah, powerModel2);
        builder.getAggregateBatteryConsumerBuilder(1).setConsumedPower(11, totalAppPowerMah, powerModel2);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        BatterySipper bs = new BatterySipper(BatterySipper.DrainType.WIFI, null, 0.0d);
        long totalAppDurationMs = 0;
        double totalAppPowerMah = 0.0d;
        PowerDurationAndTraffic powerDurationAndTraffic = new PowerDurationAndTraffic();
        boolean z = true;
        for (int i = sippers.size() - 1; i >= 0; i--) {
            BatterySipper app = sippers.get(i);
            if (app.drainType == BatterySipper.DrainType.APP) {
                long consumptionUC = app.uidObj.getWifiMeasuredBatteryConsumptionUC();
                int powerModel = getPowerModel(consumptionUC);
                z = z;
                calculateApp(powerDurationAndTraffic, app.uidObj, powerModel, rawRealtimeUs, statsType, batteryStats.hasWifiActivityReporting(), consumptionUC);
                totalAppDurationMs += powerDurationAndTraffic.durationMs;
                totalAppPowerMah += powerDurationAndTraffic.powerMah;
                app.wifiPowerMah = powerDurationAndTraffic.powerMah;
                app.wifiRunningTimeMs = powerDurationAndTraffic.durationMs;
                app.wifiRxBytes = powerDurationAndTraffic.wifiRxBytes;
                app.wifiRxPackets = powerDurationAndTraffic.wifiRxPackets;
                app.wifiTxBytes = powerDurationAndTraffic.wifiTxBytes;
                app.wifiTxPackets = powerDurationAndTraffic.wifiTxPackets;
                if (app.getUid() == 1010) {
                    app.isAggregated = z;
                    bs.add(app);
                }
            } else {
                z = z;
            }
        }
        long consumptionUC2 = batteryStats.getWifiMeasuredBatteryConsumptionUC();
        int powerModel2 = getPowerModel(consumptionUC2);
        calculateRemaining(powerDurationAndTraffic, powerModel2, batteryStats, rawRealtimeUs, statsType, batteryStats.hasWifiActivityReporting(), totalAppDurationMs, totalAppPowerMah, consumptionUC2);
        bs.wifiRunningTimeMs += powerDurationAndTraffic.durationMs;
        bs.wifiPowerMah += powerDurationAndTraffic.powerMah;
        if (bs.sumPower() > 0.0d) {
            sippers.add(bs);
        }
    }

    private void calculateApp(PowerDurationAndTraffic powerDurationAndTraffic, BatteryStats.Uid u, int powerModel, long rawRealtimeUs, int statsType, boolean hasWifiActivityReporting, long consumptionUC) {
        powerDurationAndTraffic.wifiRxPackets = u.getNetworkActivityPackets(2, statsType);
        powerDurationAndTraffic.wifiTxPackets = u.getNetworkActivityPackets(3, statsType);
        powerDurationAndTraffic.wifiRxBytes = u.getNetworkActivityBytes(2, statsType);
        powerDurationAndTraffic.wifiTxBytes = u.getNetworkActivityBytes(3, statsType);
        if (powerModel == 2) {
            powerDurationAndTraffic.powerMah = uCtoMah(consumptionUC);
        }
        if (hasWifiActivityReporting && this.mHasWifiPowerController) {
            BatteryStats.ControllerActivityCounter counter = u.getWifiControllerActivity();
            if (counter != null) {
                long idleTime = counter.getIdleTimeCounter().getCountLocked(statsType);
                long txTime = counter.getTxTimeCounters()[0].getCountLocked(statsType);
                long rxTime = counter.getRxTimeCounter().getCountLocked(statsType);
                powerDurationAndTraffic.durationMs = idleTime + rxTime + txTime;
                if (powerModel == 1) {
                    powerDurationAndTraffic.powerMah = calcPowerFromControllerDataMah(rxTime, txTime, idleTime);
                    return;
                }
                return;
            }
            return;
        }
        long wifiRunningTime = u.getWifiRunningTime(rawRealtimeUs, statsType) / 1000;
        powerDurationAndTraffic.durationMs = wifiRunningTime;
        if (powerModel == 1) {
            long wifiScanTimeMs = u.getWifiScanTime(rawRealtimeUs, statsType) / 1000;
            long batchTimeMs = 0;
            for (int bin = 0; bin < 5; bin++) {
                batchTimeMs += u.getWifiBatchedScanTime(bin, rawRealtimeUs, statsType) / 1000;
            }
            powerDurationAndTraffic.powerMah = calcPowerWithoutControllerDataMah(powerDurationAndTraffic.wifiRxPackets, powerDurationAndTraffic.wifiTxPackets, wifiRunningTime, wifiScanTimeMs, batchTimeMs);
        }
    }

    private void calculateRemaining(PowerDurationAndTraffic powerDurationAndTraffic, int powerModel, BatteryStats stats, long rawRealtimeUs, int statsType, boolean hasWifiActivityReporting, long totalAppDurationMs, double totalAppPowerMah, long consumptionUC) {
        long totalDurationMs;
        double totalPowerMah = 0.0d;
        if (powerModel == 2) {
            totalPowerMah = uCtoMah(consumptionUC);
        }
        if (!hasWifiActivityReporting || !this.mHasWifiPowerController) {
            long totalDurationMs2 = stats.getGlobalWifiRunningTime(rawRealtimeUs, statsType) / 1000;
            if (powerModel == 1) {
                totalPowerMah = calcGlobalPowerWithoutControllerDataMah(totalDurationMs2);
                totalDurationMs = totalDurationMs2;
            } else {
                totalDurationMs = totalDurationMs2;
            }
        } else {
            BatteryStats.ControllerActivityCounter counter = stats.getWifiControllerActivity();
            long idleTimeMs = counter.getIdleTimeCounter().getCountLocked(statsType);
            long txTimeMs = counter.getTxTimeCounters()[0].getCountLocked(statsType);
            long rxTimeMs = counter.getRxTimeCounter().getCountLocked(statsType);
            totalDurationMs = idleTimeMs + rxTimeMs + txTimeMs;
            if (powerModel == 1) {
                double totalPowerMah2 = counter.getPowerCounter().getCountLocked(statsType) / 3600000.0d;
                Log.d(TAG, "calculateRemaining WiFi power: " + totalPowerMah2 + " timeMs: idle=" + idleTimeMs + " tx=" + txTimeMs + " rx=" + rxTimeMs);
                if (totalPowerMah2 == 0.0d) {
                    totalPowerMah = calcPowerFromControllerDataMah(rxTimeMs, txTimeMs, idleTimeMs);
                } else {
                    totalPowerMah = totalPowerMah2;
                }
            }
        }
        powerDurationAndTraffic.durationMs = Math.max(0L, totalDurationMs - totalAppDurationMs);
        powerDurationAndTraffic.powerMah = Math.max(0.0d, totalPowerMah - totalAppPowerMah);
    }

    public double calcPowerFromControllerDataMah(long rxTimeMs, long txTimeMs, long idleTimeMs) {
        return this.mRxPowerEstimator.calculatePower(rxTimeMs) + this.mTxPowerEstimator.calculatePower(txTimeMs) + this.mIdlePowerEstimator.calculatePower(idleTimeMs);
    }

    public double calcPowerWithoutControllerDataMah(long rxPackets, long txPackets, long wifiRunningTimeMs, long wifiScanTimeMs, long wifiBatchScanTimeMs) {
        return ((rxPackets + txPackets) * this.mWifiPowerPerPacket) + this.mPowerOnPowerEstimator.calculatePower(wifiRunningTimeMs) + this.mScanPowerEstimator.calculatePower(wifiScanTimeMs) + this.mBatchScanPowerEstimator.calculatePower(wifiBatchScanTimeMs);
    }

    public double calcGlobalPowerWithoutControllerDataMah(long globalWifiRunningTimeMs) {
        return this.mPowerOnPowerEstimator.calculatePower(globalWifiRunningTimeMs);
    }

    private static double getWifiPowerPerPacket(PowerProfile profile) {
        double averageWifiActivePower = profile.getAveragePower(PowerProfile.POWER_WIFI_ACTIVE) / 3600.0d;
        return averageWifiActivePower / 61.03515625d;
    }
}
