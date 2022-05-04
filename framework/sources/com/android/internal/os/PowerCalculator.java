package com.android.internal.os;

import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
/* loaded from: classes4.dex */
public abstract class PowerCalculator {
    protected static final double MILLIAMPHOUR_PER_MICROCOULOMB = 2.777777777777778E-7d;

    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        for (int i = sippers.size() - 1; i >= 0; i--) {
            BatterySipper app = sippers.get(i);
            if (app.drainType == BatterySipper.DrainType.APP) {
                calculateApp(app, app.uidObj, rawRealtimeUs, rawUptimeUs, statsType);
            }
        }
    }

    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
        for (int i = uidBatteryConsumerBuilders.size() - 1; i >= 0; i--) {
            UidBatteryConsumer.Builder app = uidBatteryConsumerBuilders.valueAt(i);
            calculateApp(app, app.getBatteryStatsUid(), rawRealtimeUs, rawUptimeUs, query);
        }
    }

    protected void calculateApp(BatterySipper app, BatteryStats.Uid u, long rawRealtimeUs, long rawUptimeUs, int statsType) {
    }

    protected void calculateApp(UidBatteryConsumer.Builder app, BatteryStats.Uid u, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
    }

    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getPowerModel(long measuredEnergyUC, BatteryUsageStatsQuery query) {
        if (measuredEnergyUC == -1 || query.shouldForceUsePowerProfileModel()) {
            return 1;
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getPowerModel(long measuredEnergyUC) {
        if (measuredEnergyUC != -1) {
            return 2;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static double getMeasuredOrEstimatedPower(int powerModel, long measuredEnergyUC, UsageBasedPowerEstimator powerEstimator, long durationMs) {
        switch (powerModel) {
            case 2:
                return uCtoMah(measuredEnergyUC);
            default:
                return powerEstimator.calculatePower(durationMs);
        }
    }

    protected static double getMeasuredOrEstimatedPower(long measuredEnergyUC, UsageBasedPowerEstimator powerEstimator, long durationMs) {
        if (measuredEnergyUC != -1) {
            return uCtoMah(measuredEnergyUC);
        }
        return powerEstimator.calculatePower(durationMs);
    }

    public static void printPowerMah(PrintWriter pw, double powerMah) {
        pw.print(formatCharge(powerMah));
    }

    public static String formatCharge(double power) {
        String format;
        if (power == 0.0d) {
            return "0";
        }
        if (power < 1.0E-5d) {
            format = "%.8f";
        } else if (power < 1.0E-4d) {
            format = "%.7f";
        } else if (power < 0.001d) {
            format = "%.6f";
        } else if (power < 0.01d) {
            format = "%.5f";
        } else if (power < 0.1d) {
            format = "%.4f";
        } else if (power < 1.0d) {
            format = "%.3f";
        } else if (power < 10.0d) {
            format = "%.2f";
        } else if (power < 100.0d) {
            format = "%.1f";
        } else {
            format = "%.0f";
        }
        return String.format(Locale.ENGLISH, format, Double.valueOf(power));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double uCtoMah(long chargeUC) {
        return chargeUC * MILLIAMPHOUR_PER_MICROCOULOMB;
    }
}
