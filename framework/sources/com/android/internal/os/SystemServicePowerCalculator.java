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
public class SystemServicePowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    private static final String TAG = "SystemServicePowerCalc";
    private final CpuPowerCalculator mCpuPowerCalculator;
    private final UsageBasedPowerEstimator[] mPowerEstimators;

    public SystemServicePowerCalculator(PowerProfile powerProfile) {
        this.mCpuPowerCalculator = new CpuPowerCalculator(powerProfile);
        int numFreqs = 0;
        int numCpuClusters = powerProfile.getNumCpuClusters();
        for (int cluster = 0; cluster < numCpuClusters; cluster++) {
            numFreqs += powerProfile.getNumSpeedStepsInCpuCluster(cluster);
        }
        this.mPowerEstimators = new UsageBasedPowerEstimator[numFreqs];
        int index = 0;
        for (int cluster2 = 0; cluster2 < numCpuClusters; cluster2++) {
            int numSpeeds = powerProfile.getNumSpeedStepsInCpuCluster(cluster2);
            int speed = 0;
            while (speed < numSpeeds) {
                this.mPowerEstimators[index] = new UsageBasedPowerEstimator(powerProfile.getAveragePowerForCpuCore(cluster2, speed));
                speed++;
                index++;
            }
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        double systemServicePowerMah;
        BatteryStats.Uid systemUid;
        BatteryStats.Uid systemUid2 = (BatteryStats.Uid) batteryStats.getUidStats().get(1000);
        if (systemUid2 != null) {
            long consumptionUC = systemUid2.getCpuMeasuredBatteryConsumptionUC();
            int powerModel = getPowerModel(consumptionUC, query);
            if (powerModel == 2) {
                systemServicePowerMah = calculatePowerUsingMeasuredConsumption(batteryStats, systemUid2, consumptionUC);
            } else {
                systemServicePowerMah = calculatePowerUsingPowerProfile(batteryStats);
            }
            SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
            UidBatteryConsumer.Builder systemServerConsumer = uidBatteryConsumerBuilders.get(1000);
            if (systemServerConsumer != null) {
                systemServicePowerMah = Math.min(systemServicePowerMah, systemServerConsumer.getTotalPower());
                systemServerConsumer.setConsumedPower(17, -systemServicePowerMah, powerModel);
            }
            int i = uidBatteryConsumerBuilders.size() - 1;
            while (i >= 0) {
                UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
                if (app != systemServerConsumer) {
                    BatteryStats.Uid uid = app.getBatteryStatsUid();
                    systemUid = systemUid2;
                    app.setConsumedPower(7, systemServicePowerMah * uid.getProportionalSystemServiceUsage(), powerModel);
                } else {
                    systemUid = systemUid2;
                }
                i--;
                systemUid2 = systemUid;
            }
            builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(7, systemServicePowerMah);
            builder.getAggregateBatteryConsumerBuilder(1).setConsumedPower(7, systemServicePowerMah);
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        double systemServicePowerMah;
        BatteryStats.Uid systemUid = (BatteryStats.Uid) batteryStats.getUidStats().get(1000);
        if (systemUid != null) {
            long consumptionUC = systemUid.getCpuMeasuredBatteryConsumptionUC();
            if (getPowerModel(consumptionUC) == 2) {
                systemServicePowerMah = calculatePowerUsingMeasuredConsumption(batteryStats, systemUid, consumptionUC);
            } else {
                systemServicePowerMah = calculatePowerUsingPowerProfile(batteryStats);
            }
            BatterySipper systemServerSipper = null;
            int i = sippers.size() - 1;
            while (true) {
                if (i < 0) {
                    break;
                }
                BatterySipper app = sippers.get(i);
                if (app.drainType == BatterySipper.DrainType.APP && app.getUid() == 1000) {
                    systemServerSipper = app;
                    break;
                }
                i--;
            }
            if (systemServerSipper != null) {
                systemServicePowerMah = Math.min(systemServicePowerMah, systemServerSipper.sumPower());
                systemServerSipper.powerReattributedToOtherSippersMah = -systemServicePowerMah;
            }
            for (int i2 = sippers.size() - 1; i2 >= 0; i2--) {
                BatterySipper app2 = sippers.get(i2);
                if (app2.drainType == BatterySipper.DrainType.APP && app2 != systemServerSipper) {
                    BatteryStats.Uid uid = app2.uidObj;
                    app2.systemServiceCpuPowerMah = uid.getProportionalSystemServiceUsage() * systemServicePowerMah;
                }
            }
        }
    }

    private double calculatePowerUsingMeasuredConsumption(BatteryStats batteryStats, BatteryStats.Uid systemUid, long consumptionUC) {
        double systemServiceModeledPowerMah = calculatePowerUsingPowerProfile(batteryStats);
        double systemUidModeledPowerMah = this.mCpuPowerCalculator.calculateUidModeledPowerMah(systemUid, 0);
        return (uCtoMah(consumptionUC) * systemServiceModeledPowerMah) / systemUidModeledPowerMah;
    }

    private double calculatePowerUsingPowerProfile(BatteryStats batteryStats) {
        long[] systemServiceTimeAtCpuSpeeds = batteryStats.getSystemServiceTimeAtCpuSpeeds();
        if (systemServiceTimeAtCpuSpeeds == null) {
            return 0.0d;
        }
        double powerMah = 0.0d;
        int size = Math.min(this.mPowerEstimators.length, systemServiceTimeAtCpuSpeeds.length);
        for (int i = 0; i < size; i++) {
            powerMah += this.mPowerEstimators[i].calculatePower(systemServiceTimeAtCpuSpeeds[i] / 1000);
        }
        return powerMah;
    }
}
