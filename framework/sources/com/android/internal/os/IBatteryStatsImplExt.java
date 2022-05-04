package com.android.internal.os;

import android.content.ComponentName;
import android.content.Context;
import android.os.BatteryStats;
import android.os.Handler;
import android.os.Message;
import android.telephony.ModemActivityInfo;
import android.util.AtomicFile;
import com.android.internal.os.BatteryStatsImpl;
import java.io.File;
import java.io.PrintWriter;
/* loaded from: classes4.dex */
public interface IBatteryStatsImplExt {
    default boolean onBatteryStatsMessageHandle(Message msg) {
        return false;
    }

    default void setInBatteryStatsImplInstance(BatteryStats impl) {
    }

    default void onSystemServicesReady(Context context) {
    }

    default void initBatteryStatsImplExtImpl(BatteryStatsImpl battStatsImpl, File systemDir, Handler handler) {
    }

    default void initBatteryStatsImplExtImpl(BatteryStatsImpl battStatsImpl) {
    }

    default void setThermalState(Object thermalState) {
    }

    default void noteScreenBrightnessModeChangedLock(boolean isAuto) {
    }

    default void setThermalConfig() {
    }

    default void dumpThemalLocked(PrintWriter pw, long histStart) {
    }

    default void clearThermalAllHistory() {
    }

    default void toggleThermalDebugSwith(PrintWriter pw, int on) {
    }

    default void updateCpuStatsNow(PrintWriter pw) {
    }

    default void setThermalHeatThreshold(PrintWriter pw, int threshold) {
    }

    default void printThermalHeatThreshold(PrintWriter pw) {
    }

    default void setHeatBetweenTime(PrintWriter pw, int time) {
    }

    default void setMonitorAppLimitTime(PrintWriter pw, int limitTime) {
    }

    default void getMonitorAppLocked(PrintWriter pw) {
    }

    default void dumpThemalRawLocked(PrintWriter pw, long histStart) {
    }

    default void backupThermalStatsFile() {
    }

    default void backupThermalLogFile() {
    }

    default void dumpThemalHeatDetailLocked(PrintWriter pw) {
    }

    default void getPhoneTemp(PrintWriter pw) {
    }

    default void printThermalUploadTemp(PrintWriter pw) {
    }

    default void printChargeMapLocked(PrintWriter pw) {
    }

    default void logSwitch(boolean en) {
    }

    default void dumpThermalConfig(PrintWriter pw) {
    }

    default void dumpThemalRecLocked(Context context, PrintWriter pw, int flags, int reqUid, long histStart) {
    }

    default String addDevicePowerStatsDeltaString(String curStatSubsystemPowerState) {
        return curStatSubsystemPowerState;
    }

    default void recordGpsPowerDrainMaMs(long powerdrains) {
    }

    default void collectCheckinFile(int lowDischargeAmountSinceCharge, AtomicFile checkinFile, BatteryStatsImpl.BatteryCallback callback) {
    }

    default void recordNetworkActivityBytes(int type, long deltaBytes) {
    }

    default void recordWifiPowerDrainMaMs(long powerdrains) {
    }

    default void recordMobilePowerDrainMaMs(long powerdrains) {
    }

    default void updateMobileRadioState(ModemActivityInfo deltaInfo) {
    }

    default void recordBluetoothPowerDrainMaMs(long powerdrains) {
    }

    default void noteActivityPausedLocked(int uid, ComponentName component, boolean isOnBattery, BatteryStats.HistoryItem mHistoryCur, long pausedElapsedRealtime1, Handler handler) {
    }

    default void noteActivityResumedLocked(int uid, ComponentName component, boolean isOnBattery, BatteryStats.HistoryItem historyCur, long pausedElapsedRealtime1, Handler handler, String currentTopActivity) {
    }

    default void noteActivityLocked(int uid, ComponentName component, boolean isOnBattery, BatteryStats.HistoryItem historyCur, long pausedElapsedRealtime1, Handler handler, String currentTopActivity, boolean resumed, Context context) {
    }

    default void setThermalCpuLoading(int load1, int load5, int load15, int cpuLoading, int maxCpu, String cpuProc, String simpleTopProc) {
    }

    default void addThermalForegroundApp(long elapsedRealTime, long uptime, String procName, int uid, int code) {
    }

    default void noteConnectivityChangedLocked(int type, String extra, long elapsedRealtime, long uptime) {
    }

    default void addThermalnetSyncProc(long elapsedRealtime, long uptime, String procName) {
    }

    default void addThermalJobProc(long elapsedRealtime, long uptime, String procName) {
    }

    default void addThermalOnOffEvent(int eventType, long elapsedRealtime, long uptime, boolean on) {
    }

    default void addThermalScreenBrightnessEvent(long elapsedRealtime, long uptime, int backlight, int delayTime) {
    }

    default void setScreenBrightness(int value) {
    }

    default void addThermalNetState(long elapsedRealtime, long uptime, boolean netState) {
    }

    default void addThermalPhoneOnOff(long elapsedRealtime, long uptime, boolean onOff) {
    }

    default void addThermalPhoneSignal(long elapsedRealtime, long uptime, byte signal) {
    }

    default void addThermalPhoneState(long elapsedRealtime, long uptime, byte state) {
    }

    default void notePhoneDataConnectionStateLocked(long elapsedTime, long upTime, int dataType) {
    }

    default void addThermalWifiStatus(long elapsedRealtime, long uptime, int status) {
    }

    default void addThermalWifiRssi(long elapsedRealtime, long uptime, int wifiSignalStrengthBin) {
    }
}
