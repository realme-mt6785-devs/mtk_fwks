package com.android.internal.os;

import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.UidBatteryConsumer;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.internal.os.BatterySipper;
import com.android.internal.util.ArrayUtils;
import java.util.List;
/* loaded from: classes4.dex */
public class UserPowerCalculator extends PowerCalculator {
    @Override // com.android.internal.os.PowerCalculator
    public void calculate(BatteryUsageStats.Builder builder, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, BatteryUsageStatsQuery query) {
        int[] userIds = query.getUserIds();
        if (!ArrayUtils.contains(userIds, -1)) {
            SparseArray<UidBatteryConsumer.Builder> uidBatteryConsumerBuilders = builder.getUidBatteryConsumerBuilders();
            for (int i = uidBatteryConsumerBuilders.size() - 1; i >= 0; i--) {
                UidBatteryConsumer.Builder uidBuilder = uidBatteryConsumerBuilders.valueAt(i);
                int uid = uidBuilder.getUid();
                if (UserHandle.getAppId(uid) >= 10000) {
                    int userId = UserHandle.getUserId(uid);
                    if (!ArrayUtils.contains(userIds, userId)) {
                        uidBuilder.excludeFromBatteryUsageStats();
                        builder.getOrCreateUserBatteryConsumerBuilder(userId).addUidBatteryConsumer(uidBuilder);
                    }
                }
            }
        }
    }

    @Override // com.android.internal.os.PowerCalculator
    public void calculate(List<BatterySipper> sippers, BatteryStats batteryStats, long rawRealtimeUs, long rawUptimeUs, int statsType, SparseArray<UserHandle> asUsers) {
        boolean forAllUsers = asUsers.get(-1) != null;
        if (!forAllUsers) {
            SparseArray<BatterySipper> userSippers = new SparseArray<>();
            for (int i = sippers.size() - 1; i >= 0; i--) {
                BatterySipper sipper = sippers.get(i);
                int uid = sipper.getUid();
                int userId = UserHandle.getUserId(uid);
                if (asUsers.get(userId) == null && UserHandle.getAppId(uid) >= 10000) {
                    BatterySipper userSipper = userSippers.get(userId);
                    if (userSipper == null) {
                        userSipper = new BatterySipper(BatterySipper.DrainType.USER, null, 0.0d);
                        userSipper.userId = userId;
                        userSippers.put(userId, userSipper);
                    }
                    userSipper.add(sipper);
                    sipper.isAggregated = true;
                }
            }
            for (int i2 = 0; i2 < userSippers.size(); i2++) {
                BatterySipper sipper2 = userSippers.valueAt(i2);
                if (sipper2.sumPower() > 0.0d) {
                    sippers.add(sipper2);
                }
            }
        }
    }
}
