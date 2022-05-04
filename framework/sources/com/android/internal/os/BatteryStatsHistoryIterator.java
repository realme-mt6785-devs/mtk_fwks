package com.android.internal.os;

import android.os.BatteryStats;
import android.os.Parcel;
import java.util.List;
/* loaded from: classes4.dex */
public class BatteryStatsHistoryIterator {
    private static final boolean DEBUG = false;
    private static final String TAG = "BatteryStatsHistoryItr";
    private final BatteryStatsHistory mBatteryStatsHistory;
    private final BatteryStats.HistoryStepDetails mReadHistoryStepDetails = new BatteryStats.HistoryStepDetails();
    private final String[] mReadHistoryStrings;
    private final int[] mReadHistoryUids;

    public BatteryStatsHistoryIterator(BatteryStatsHistory history, List<BatteryStats.HistoryTag> historyTagPool) {
        this.mBatteryStatsHistory = history;
        history.startIteratingHistory();
        this.mReadHistoryStrings = new String[historyTagPool.size()];
        this.mReadHistoryUids = new int[historyTagPool.size()];
        for (int i = historyTagPool.size() - 1; i >= 0; i--) {
            BatteryStats.HistoryTag tag = historyTagPool.get(i);
            int idx = tag.poolIdx;
            this.mReadHistoryStrings[idx] = tag.string;
            this.mReadHistoryUids[idx] = tag.uid;
        }
    }

    public boolean next(BatteryStats.HistoryItem out) {
        Parcel p = this.mBatteryStatsHistory.getNextParcel(out);
        if (p == null) {
            this.mBatteryStatsHistory.finishIteratingHistory();
            return false;
        }
        long lastRealtimeMs = out.time;
        long lastWalltimeMs = out.currentTime;
        readHistoryDelta(p, out);
        if (out.cmd == 5 || out.cmd == 7 || lastWalltimeMs == 0) {
            return true;
        }
        out.currentTime = (out.time - lastRealtimeMs) + lastWalltimeMs;
        return true;
    }

    void readHistoryDelta(Parcel src, BatteryStats.HistoryItem cur) {
        int batteryLevelInt;
        int firstToken = src.readInt();
        int deltaTimeToken = 524287 & firstToken;
        cur.cmd = (byte) 0;
        cur.numReadInts = 1;
        if (deltaTimeToken < 524285) {
            cur.time += deltaTimeToken;
        } else if (deltaTimeToken == 524285) {
            cur.readFromParcel(src);
            return;
        } else if (deltaTimeToken == 524286) {
            int delta = src.readInt();
            cur.time += delta;
            cur.numReadInts++;
        } else {
            long delta2 = src.readLong();
            cur.time += delta2;
            cur.numReadInts += 2;
        }
        if ((524288 & firstToken) != 0) {
            batteryLevelInt = src.readInt();
            readBatteryLevelInt(batteryLevelInt, cur);
            cur.numReadInts++;
        } else {
            batteryLevelInt = 0;
        }
        if ((1048576 & firstToken) != 0) {
            int stateInt = src.readInt();
            cur.states = (16777215 & stateInt) | ((-33554432) & firstToken);
            cur.batteryStatus = (byte) ((stateInt >> 29) & 7);
            cur.batteryHealth = (byte) ((stateInt >> 26) & 7);
            cur.batteryPlugType = (byte) ((stateInt >> 24) & 3);
            switch (cur.batteryPlugType) {
                case 1:
                    cur.batteryPlugType = (byte) 1;
                    break;
                case 2:
                    cur.batteryPlugType = (byte) 2;
                    break;
                case 3:
                    cur.batteryPlugType = (byte) 4;
                    break;
            }
            cur.numReadInts++;
        } else {
            cur.states = (firstToken & (-33554432)) | (cur.states & 16777215);
        }
        if ((2097152 & firstToken) != 0) {
            cur.states2 = src.readInt();
        }
        if ((4194304 & firstToken) != 0) {
            int indexes = src.readInt();
            int wakeLockIndex = indexes & 65535;
            int wakeReasonIndex = (indexes >> 16) & 65535;
            if (wakeLockIndex != 65535) {
                cur.wakelockTag = cur.localWakelockTag;
                readHistoryTag(wakeLockIndex, cur.wakelockTag);
            } else {
                cur.wakelockTag = null;
            }
            if (wakeReasonIndex != 65535) {
                cur.wakeReasonTag = cur.localWakeReasonTag;
                readHistoryTag(wakeReasonIndex, cur.wakeReasonTag);
            } else {
                cur.wakeReasonTag = null;
            }
            cur.numReadInts++;
        } else {
            cur.wakelockTag = null;
            cur.wakeReasonTag = null;
        }
        if ((8388608 & firstToken) != 0) {
            cur.eventTag = cur.localEventTag;
            int codeAndIndex = src.readInt();
            cur.eventCode = codeAndIndex & 65535;
            int index = (codeAndIndex >> 16) & 65535;
            readHistoryTag(index, cur.eventTag);
            cur.numReadInts++;
        } else {
            cur.eventCode = 0;
        }
        if ((batteryLevelInt & 1) != 0) {
            cur.stepDetails = this.mReadHistoryStepDetails;
            cur.stepDetails.readFromParcel(src);
        } else {
            cur.stepDetails = null;
        }
        if ((16777216 & firstToken) != 0) {
            cur.batteryChargeUah = src.readInt();
        }
        cur.modemRailChargeMah = src.readDouble();
        cur.wifiRailChargeMah = src.readDouble();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHistoryStringPoolSize() {
        return this.mReadHistoryStrings.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHistoryStringPoolBytes() {
        int totalChars = 0;
        for (int i = this.mReadHistoryStrings.length - 1; i >= 0; i--) {
            String[] strArr = this.mReadHistoryStrings;
            if (strArr[i] != null) {
                totalChars += strArr[i].length() + 1;
            }
        }
        return (this.mReadHistoryStrings.length * 12) + (totalChars * 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getHistoryTagPoolString(int index) {
        return this.mReadHistoryStrings[index];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHistoryTagPoolUid(int index) {
        return this.mReadHistoryUids[index];
    }

    private void readHistoryTag(int index, BatteryStats.HistoryTag tag) {
        String[] strArr = this.mReadHistoryStrings;
        if (index < strArr.length) {
            tag.string = strArr[index];
            tag.uid = this.mReadHistoryUids[index];
        } else {
            tag.string = null;
            tag.uid = 0;
        }
        tag.poolIdx = index;
    }

    private static void readBatteryLevelInt(int batteryLevelInt, BatteryStats.HistoryItem out) {
        out.batteryLevel = (byte) (((-33554432) & batteryLevelInt) >>> 25);
        out.batteryTemperature = (short) ((33521664 & batteryLevelInt) >>> 15);
        out.batteryVoltage = (char) ((batteryLevelInt & 32766) >>> 1);
    }
}
