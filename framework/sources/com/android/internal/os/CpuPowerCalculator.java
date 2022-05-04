package com.android.internal.os;

import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.util.List;
/* loaded from: classes4.dex */
public class CpuPowerCalculator extends PowerCalculator {
    private static final boolean DEBUG = false;
    private static final String TAG = "CpuPowerCalculator";
    private final UsageBasedPowerEstimator mCpuActivePowerEstimator;
    private final int mNumCpuClusters;
    private final UsageBasedPowerEstimator[] mPerClusterPowerEstimators;
    private final UsageBasedPowerEstimator[][] mPerCpuFreqPowerEstimators;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Result {
        public long durationFgMs;
        public long durationMs;
        public String packageWithHighestDrain;
        public double powerMah;

        private Result() {
        }
    }

    public CpuPowerCalculator(PowerProfile profile) {
        int i;
        int numCpuClusters = profile.getNumCpuClusters();
        this.mNumCpuClusters = numCpuClusters;
        this.mCpuActivePowerEstimator = new UsageBasedPowerEstimator(profile.getAveragePower(PowerProfile.POWER_CPU_ACTIVE));
        this.mPerClusterPowerEstimators = new UsageBasedPowerEstimator[numCpuClusters];
        int cluster = 0;
        while (true) {
            i = this.mNumCpuClusters;
            if (cluster >= i) {
                break;
            }
            this.mPerClusterPowerEstimators[cluster] = new UsageBasedPowerEstimator(profile.getAveragePowerForCpuCluster(cluster));
            cluster++;
        }
        this.mPerCpuFreqPowerEstimators = new UsageBasedPowerEstimator[i];
        for (int cluster2 = 0; cluster2 < this.mNumCpuClusters; cluster2++) {
            int speedsForCluster = profile.getNumSpeedStepsInCpuCluster(cluster2);
            this.mPerCpuFreqPowerEstimators[cluster2] = new UsageBasedPowerEstimator[speedsForCluster];
            for (int speed = 0; speed < speedsForCluster; speed++) {
                this.mPerCpuFreqPowerEstimators[cluster2][speed] = new UsageBasedPowerEstimator(profile.getAveragePowerForCpuCore(cluster2, speed));
            }
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        double totalPowerMah = 0.0d;
        Result result = new Result();
        SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
        for (int i = uidBatteryConsumerBuilders.size() - 1; i >= 0; i--) {
            UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
            calculateApp(app, app.getBatteryStatsUid(), query, result);
            totalPowerMah += result.powerMah;
        }
        long consumptionUC = batteryStats.getCpuMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC, query);
        builder.getAggregateBatteryConsumerBuilder(1).setConsumedPower(1, totalPowerMah);
        builder.getAggregateBatteryConsumerBuilder(0).setConsumedPower(1, powerModel == 2 ? uCtoMah(consumptionUC) : totalPowerMah, powerModel);
    }

    private void calculateApp(UidBatteryConsumer.Builder app, BatteryStats.Uid u, BatteryUsageStatsQuery query, Result result) {
        long consumptionUC = u.getCpuMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC, query);
        calculatePowerAndDuration(u, powerModel, consumptionUC, 0, result);
        ((UidBatteryConsumer.Builder) ((UidBatteryConsumer.Builder) app.setConsumedPower(1, result.powerMah, powerModel)).setUsageDurationMillis(1, result.durationMs)).setPackageWithHighestDrain(result.packageWithHighestDrain);
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        Result result = new Result();
        for (int i = sippers.size() - 1; i >= 0; i--) {
            BatterySipper app = sippers.get(i);
            if (app.drainType == BatterySipper.DrainType.APP) {
                calculateApp(app, app.uidObj, statsType, result);
            }
        }
    }

    private void calculateApp(BatterySipper app, BatteryStats.Uid u, int statsType, Result result) {
        long consumptionUC = u.getCpuMeasuredBatteryConsumptionUC();
        int powerModel = getPowerModel(consumptionUC);
        calculatePowerAndDuration(u, powerModel, consumptionUC, statsType, result);
        app.cpuPowerMah = result.powerMah;
        app.cpuTimeMs = result.durationMs;
        app.cpuFgTimeMs = result.durationFgMs;
        app.packageWithHighestDrain = result.packageWithHighestDrain;
    }

    private void calculatePowerAndDuration(BatteryStats.Uid u, int powerModel, long consumptionUC, int statsType, Result result) {
        double powerMah;
        ArrayMap<String, ? extends BatteryStats.Uid.Proc> processStats;
        long durationFgMs;
        int i = statsType;
        long durationMs = (u.getUserCpuTimeUs(i) + u.getSystemCpuTimeUs(i)) / 1000;
        switch (powerModel) {
            case 2:
                double powerMah2 = uCtoMah(consumptionUC);
                powerMah = powerMah2;
                break;
            default:
                powerMah = calculateUidModeledPowerMah(u, i);
                break;
        }
        double highestDrain = 0.0d;
        String packageWithHighestDrain = null;
        long durationFgMs2 = 0;
        ArrayMap<String, ? extends BatteryStats.Uid.Proc> processStats2 = u.getProcessStats();
        int processStatsCount = processStats2.size();
        int i2 = 0;
        while (i2 < processStatsCount) {
            BatteryStats.Uid.Proc ps = (BatteryStats.Uid.Proc) processStats2.valueAt(i2);
            String processName = processStats2.keyAt(i2);
            long durationFgMs3 = durationFgMs2 + ps.getForegroundTime(i);
            long costValue = ps.getUserTime(i) + ps.getSystemTime(i) + ps.getForegroundTime(i);
            if (packageWithHighestDrain != null) {
                durationFgMs = durationFgMs3;
                if (packageWithHighestDrain.startsWith("*")) {
                    processStats = processStats2;
                } else {
                    processStats = processStats2;
                    if (highestDrain < costValue && !processName.startsWith("*")) {
                        highestDrain = costValue;
                        packageWithHighestDrain = processName;
                    }
                    i2++;
                    i = statsType;
                    durationFgMs2 = durationFgMs;
                    processStats2 = processStats;
                }
            } else {
                durationFgMs = durationFgMs3;
                processStats = processStats2;
            }
            highestDrain = costValue;
            packageWithHighestDrain = processName;
            i2++;
            i = statsType;
            durationFgMs2 = durationFgMs;
            processStats2 = processStats;
        }
        if (durationFgMs2 > durationMs) {
            durationMs = durationFgMs2;
        }
        result.durationMs = durationMs;
        result.durationFgMs = durationFgMs2;
        result.powerMah = powerMah;
        result.packageWithHighestDrain = packageWithHighestDrain;
    }

    public double calculateUidModeledPowerMah(BatteryStats.Uid u, int statsType) {
        double powerMah = calculateActiveCpuPowerMah(u.getCpuActiveTime());
        long[] cpuClusterTimes = u.getCpuClusterTimes();
        if (cpuClusterTimes != null) {
            if (cpuClusterTimes.length == this.mNumCpuClusters) {
                for (int cluster = 0; cluster < this.mNumCpuClusters; cluster++) {
                    double power = calculatePerCpuClusterPowerMah(cluster, cpuClusterTimes[cluster]);
                    powerMah += power;
                }
            } else {
                Log.w(TAG, "UID " + u.getUid() + " CPU cluster # mismatch: Power Profile # " + this.mNumCpuClusters + " actual # " + cpuClusterTimes.length);
            }
        }
        for (int cluster2 = 0; cluster2 < this.mNumCpuClusters; cluster2++) {
            int speedsForCluster = this.mPerCpuFreqPowerEstimators[cluster2].length;
            for (int speed = 0; speed < speedsForCluster; speed++) {
                long timeUs = u.getTimeAtCpuSpeed(cluster2, speed, statsType);
                double power2 = calculatePerCpuFreqPowerMah(cluster2, speed, timeUs / 1000);
                powerMah += power2;
            }
        }
        return powerMah;
    }

    public double calculateActiveCpuPowerMah(long durationsMs) {
        return this.mCpuActivePowerEstimator.calculatePower(durationsMs);
    }

    public double calculatePerCpuClusterPowerMah(int cluster, long clusterDurationMs) {
        return this.mPerClusterPowerEstimators[cluster].calculatePower(clusterDurationMs);
    }

    public double calculatePerCpuFreqPowerMah(int cluster, int speedStep, long clusterSpeedDurationsMs) {
        return this.mPerCpuFreqPowerEstimators[cluster][speedStep].calculatePower(clusterSpeedDurationsMs);
    }
}
