package com.android.internal.os;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.Parcel;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class BatteryUsageStatsProvider {
    private static final String TAG = "BatteryUsageStatsProv";
    private final BatteryUsageStatsStore mBatteryUsageStatsStore;
    private final Context mContext;
    private final Object mLock;
    private List<PowerCalculator> mPowerCalculators;
    private final PowerProfile mPowerProfile;
    private final BatteryStats mStats;

    public BatteryUsageStatsProvider(Context context, BatteryStats stats) {
        this(context, stats, null);
    }

    public BatteryUsageStatsProvider(Context context, BatteryStats stats, BatteryUsageStatsStore batteryUsageStatsStore) {
        PowerProfile powerProfile;
        this.mLock = new Object();
        this.mContext = context;
        this.mStats = stats;
        this.mBatteryUsageStatsStore = batteryUsageStatsStore;
        if (stats instanceof BatteryStatsImpl) {
            powerProfile = ((BatteryStatsImpl) stats).getPowerProfile();
        } else {
            powerProfile = new PowerProfile(context);
        }
        this.mPowerProfile = powerProfile;
    }

    private List<PowerCalculator> getPowerCalculators() {
        synchronized (this.mLock) {
            if (this.mPowerCalculators == null) {
                ArrayList arrayList = new ArrayList();
                this.mPowerCalculators = arrayList;
                arrayList.add(new BatteryChargeCalculator());
                this.mPowerCalculators.add(new CpuPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new MemoryPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new WakelockPowerCalculator(this.mPowerProfile));
                if (!BatteryStatsHelper.checkWifiOnly(this.mContext)) {
                    this.mPowerCalculators.add(new MobileRadioPowerCalculator(this.mPowerProfile));
                }
                this.mPowerCalculators.add(new WifiPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new BluetoothPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new SensorPowerCalculator((SensorManager) this.mContext.getSystemService(SensorManager.class)));
                this.mPowerCalculators.add(new GnssPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new CameraPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new FlashlightPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new AudioPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new VideoPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new PhonePowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new ScreenPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new AmbientDisplayPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new IdlePowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new CustomMeasuredPowerCalculator(this.mPowerProfile));
                this.mPowerCalculators.add(new UserPowerCalculator());
                this.mPowerCalculators.add(new SystemServicePowerCalculator(this.mPowerProfile));
            }
        }
        return this.mPowerCalculators;
    }

    public boolean shouldUpdateStats(List<BatteryUsageStatsQuery> queries, long lastUpdateTimeStampMs) {
        long allowableStatsAge = Long.MAX_VALUE;
        for (int i = queries.size() - 1; i >= 0; i--) {
            BatteryUsageStatsQuery query = queries.get(i);
            allowableStatsAge = Math.min(allowableStatsAge, query.getMaxStatsAge());
        }
        return elapsedRealtime() - lastUpdateTimeStampMs > allowableStatsAge;
    }

    public List<BatteryUsageStats> getBatteryUsageStats(List<BatteryUsageStatsQuery> queries) {
        ArrayList<BatteryUsageStats> results = new ArrayList<>(queries.size());
        synchronized (this.mStats) {
            this.mStats.prepareForDumpLocked();
            long currentTimeMillis = currentTimeMillis();
            for (int i = 0; i < queries.size(); i++) {
                results.add(getBatteryUsageStats(queries.get(i), currentTimeMillis));
            }
        }
        return results;
    }

    public BatteryUsageStats getBatteryUsageStats(BatteryUsageStatsQuery query) {
        return getBatteryUsageStats(query, currentTimeMillis());
    }

    private BatteryUsageStats getBatteryUsageStats(BatteryUsageStatsQuery query, long currentTimeMs) {
        if (query.getToTimestamp() == 0) {
            return getCurrentBatteryUsageStats(query, currentTimeMs);
        }
        return getAggregatedBatteryUsageStats(query);
    }

    private BatteryUsageStats getCurrentBatteryUsageStats(BatteryUsageStatsQuery query, long currentTimeMs) {
        long realtimeUs = elapsedRealtime() * 1000;
        long uptimeUs = 1000 * uptimeMillis();
        boolean includePowerModels = (query.getFlags() & 4) != 0;
        BatteryUsageStats.Builder batteryUsageStatsBuilder = new BatteryUsageStats.Builder(this.mStats.getCustomEnergyConsumerNames(), includePowerModels);
        batteryUsageStatsBuilder.setStatsStartTimestamp(this.mStats.getStartClockTime());
        batteryUsageStatsBuilder.setStatsEndTimestamp(currentTimeMs);
        SparseArray<? extends BatteryStats.Uid> uidStats = this.mStats.getUidStats();
        int i = uidStats.size() - 1;
        while (i >= 0) {
            BatteryStats.Uid uid = (BatteryStats.Uid) uidStats.valueAt(i);
            batteryUsageStatsBuilder.getOrCreateUidBatteryConsumerBuilder(uid).setTimeInStateMs(1, getProcessBackgroundTimeMs(uid, realtimeUs)).setTimeInStateMs(0, getProcessForegroundTimeMs(uid, realtimeUs));
            i--;
            includePowerModels = includePowerModels;
        }
        List<PowerCalculator> powerCalculators = getPowerCalculators();
        int count = powerCalculators.size();
        int i2 = 0;
        while (i2 < count) {
            PowerCalculator powerCalculator = powerCalculators.get(i2);
            powerCalculator.calculate(batteryUsageStatsBuilder, this.mStats, realtimeUs, uptimeUs, query);
            i2++;
            uidStats = uidStats;
        }
        if ((query.getFlags() & 2) != 0) {
            BatteryStats batteryStats = this.mStats;
            if (batteryStats instanceof BatteryStatsImpl) {
                BatteryStatsImpl batteryStatsImpl = (BatteryStatsImpl) batteryStats;
                Parcel historyBuffer = Parcel.obtain();
                historyBuffer.appendFrom(batteryStatsImpl.mHistoryBuffer, 0, batteryStatsImpl.mHistoryBuffer.dataSize());
                ArrayList<BatteryStats.HistoryTag> tags = new ArrayList<>(batteryStatsImpl.mHistoryTagPool.size());
                for (Map.Entry<BatteryStats.HistoryTag, Integer> entry : batteryStatsImpl.mHistoryTagPool.entrySet()) {
                    BatteryStats.HistoryTag tag = new BatteryStats.HistoryTag();
                    tag.setTo(entry.getKey());
                    tag.poolIdx = entry.getValue().intValue();
                    tags.add(tag);
                }
                batteryUsageStatsBuilder.setBatteryHistory(historyBuffer, tags);
            } else {
                throw new UnsupportedOperationException("History cannot be included for " + getClass().getName());
            }
        }
        return batteryUsageStatsBuilder.build();
    }

    private long getProcessForegroundTimeMs(BatteryStats.Uid uid, long realtimeUs) {
        long topStateDurationUs = uid.getProcessStateTime(0, realtimeUs, 0);
        long foregroundActivityDurationUs = 0;
        BatteryStats.Timer foregroundActivityTimer = uid.getForegroundActivityTimer();
        if (foregroundActivityTimer != null) {
            foregroundActivityDurationUs = foregroundActivityTimer.getTotalTimeLocked(realtimeUs, 0);
        }
        long totalForegroundDurationUs = Math.min(topStateDurationUs, foregroundActivityDurationUs);
        return ((totalForegroundDurationUs + uid.getProcessStateTime(2, realtimeUs, 0)) + uid.getProcessStateTime(1, realtimeUs, 0)) / 1000;
    }

    private long getProcessBackgroundTimeMs(BatteryStats.Uid uid, long realtimeUs) {
        return uid.getProcessStateTime(3, realtimeUs, 0) / 1000;
    }

    private BatteryUsageStats getAggregatedBatteryUsageStats(BatteryUsageStatsQuery query) {
        BatteryUsageStats snapshot;
        boolean includePowerModels = (query.getFlags() & 4) != 0;
        BatteryUsageStats.Builder builder = new BatteryUsageStats.Builder(this.mStats.getCustomEnergyConsumerNames(), includePowerModels);
        BatteryUsageStatsStore batteryUsageStatsStore = this.mBatteryUsageStatsStore;
        if (batteryUsageStatsStore == null) {
            Log.e(TAG, "BatteryUsageStatsStore is unavailable");
            return builder.build();
        }
        long[] timestamps = batteryUsageStatsStore.listBatteryUsageStatsTimestamps();
        for (long timestamp : timestamps) {
            if (timestamp > query.getFromTimestamp() && timestamp <= query.getToTimestamp() && (snapshot = this.mBatteryUsageStatsStore.loadBatteryUsageStats(timestamp)) != null) {
                builder.add(snapshot);
            }
        }
        return builder.build();
    }

    private long elapsedRealtime() {
        BatteryStats batteryStats = this.mStats;
        if (batteryStats instanceof BatteryStatsImpl) {
            return ((BatteryStatsImpl) batteryStats).mClocks.elapsedRealtime();
        }
        return SystemClock.elapsedRealtime();
    }

    private long uptimeMillis() {
        BatteryStats batteryStats = this.mStats;
        if (batteryStats instanceof BatteryStatsImpl) {
            return ((BatteryStatsImpl) batteryStats).mClocks.uptimeMillis();
        }
        return SystemClock.uptimeMillis();
    }

    private long currentTimeMillis() {
        BatteryStats batteryStats = this.mStats;
        if (batteryStats instanceof BatteryStatsImpl) {
            return ((BatteryStatsImpl) batteryStats).mClocks.currentTimeMillis();
        }
        return System.currentTimeMillis();
    }
}
