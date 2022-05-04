package com.android.internal.os;

import android.os.BatteryStats;
@Deprecated
/* loaded from: classes4.dex */
public class BatterySipper implements Comparable<BatterySipper> {
    public double audioPowerMah;
    public long audioTimeMs;
    public double bluetoothPowerMah;
    public long bluetoothRunningTimeMs;
    public long btRxBytes;
    public long btTxBytes;
    public double cameraPowerMah;
    public long cameraTimeMs;
    public long cpuFgTimeMs;
    public double cpuPowerMah;
    public long cpuTimeMs;
    public double[] customMeasuredPowerMah;
    public DrainType drainType;
    public double flashlightPowerMah;
    public long flashlightTimeMs;
    public double gpsPowerMah;
    public long gpsTimeMs;
    public boolean isAggregated;
    public String[] mPackages;
    public long mobileActive;
    public int mobileActiveCount;
    public double mobileRadioPowerMah;
    public long mobileRxBytes;
    public long mobileRxPackets;
    public long mobileTxBytes;
    public long mobileTxPackets;
    public double mobilemspp;
    public double noCoveragePercent;
    public String packageWithHighestDrain;
    public double percent;
    public double powerReattributedToOtherSippersMah;
    public double proportionalSmearMah;
    public double screenPowerMah;
    public double sensorPowerMah;
    public boolean shouldHide;
    public double systemServiceCpuPowerMah;
    public double totalPowerMah;
    public double totalSmearedPowerMah;
    public BatteryStats.Uid uidObj;
    public double usagePowerMah;
    public long usageTimeMs;
    public int userId;
    public double videoPowerMah;
    public long videoTimeMs;
    public double wakeLockPowerMah;
    public long wakeLockTimeMs;
    public double wifiPowerMah;
    public long wifiRunningTimeMs;
    public long wifiRxBytes;
    public long wifiRxPackets;
    public long wifiTxBytes;
    public long wifiTxPackets;

    /* loaded from: classes4.dex */
    public enum DrainType {
        AMBIENT_DISPLAY,
        APP,
        BLUETOOTH,
        CAMERA,
        CELL,
        FLASHLIGHT,
        IDLE,
        MEMORY,
        OVERCOUNTED,
        PHONE,
        SCREEN,
        UNACCOUNTED,
        USER,
        WIFI
    }

    public BatterySipper(DrainType drainType, BatteryStats.Uid uid, double value) {
        this.totalPowerMah = value;
        this.drainType = drainType;
        this.uidObj = uid;
    }

    public void computeMobilemspp() {
        long packets = this.mobileRxPackets + this.mobileTxPackets;
        this.mobilemspp = packets > 0 ? this.mobileActive / packets : 0.0d;
    }

    public int compareTo(BatterySipper other) {
        DrainType drainType = this.drainType;
        if (drainType != other.drainType) {
            if (drainType == DrainType.OVERCOUNTED) {
                return 1;
            }
            if (other.drainType == DrainType.OVERCOUNTED) {
                return -1;
            }
        }
        return Double.compare(other.totalPowerMah, this.totalPowerMah);
    }

    public String[] getPackages() {
        return this.mPackages;
    }

    public int getUid() {
        BatteryStats.Uid uid = this.uidObj;
        if (uid == null) {
            return 0;
        }
        return uid.getUid();
    }

    public void add(BatterySipper other) {
        this.totalPowerMah += other.totalPowerMah;
        this.usageTimeMs += other.usageTimeMs;
        this.usagePowerMah += other.usagePowerMah;
        this.audioTimeMs += other.audioTimeMs;
        this.cpuTimeMs += other.cpuTimeMs;
        this.gpsTimeMs += other.gpsTimeMs;
        this.wifiRunningTimeMs += other.wifiRunningTimeMs;
        this.cpuFgTimeMs += other.cpuFgTimeMs;
        this.videoTimeMs += other.videoTimeMs;
        this.wakeLockTimeMs += other.wakeLockTimeMs;
        this.cameraTimeMs += other.cameraTimeMs;
        this.flashlightTimeMs += other.flashlightTimeMs;
        this.bluetoothRunningTimeMs += other.bluetoothRunningTimeMs;
        this.mobileRxPackets += other.mobileRxPackets;
        this.mobileTxPackets += other.mobileTxPackets;
        this.mobileActive += other.mobileActive;
        this.mobileActiveCount += other.mobileActiveCount;
        this.wifiRxPackets += other.wifiRxPackets;
        this.wifiTxPackets += other.wifiTxPackets;
        this.mobileRxBytes += other.mobileRxBytes;
        this.mobileTxBytes += other.mobileTxBytes;
        this.wifiRxBytes += other.wifiRxBytes;
        this.wifiTxBytes += other.wifiTxBytes;
        this.btRxBytes += other.btRxBytes;
        this.btTxBytes += other.btTxBytes;
        this.audioPowerMah += other.audioPowerMah;
        this.wifiPowerMah += other.wifiPowerMah;
        this.gpsPowerMah += other.gpsPowerMah;
        this.cpuPowerMah += other.cpuPowerMah;
        this.sensorPowerMah += other.sensorPowerMah;
        this.mobileRadioPowerMah += other.mobileRadioPowerMah;
        this.wakeLockPowerMah += other.wakeLockPowerMah;
        this.cameraPowerMah += other.cameraPowerMah;
        this.flashlightPowerMah += other.flashlightPowerMah;
        this.bluetoothPowerMah += other.bluetoothPowerMah;
        this.screenPowerMah += other.screenPowerMah;
        this.videoPowerMah += other.videoPowerMah;
        this.proportionalSmearMah += other.proportionalSmearMah;
        this.totalSmearedPowerMah += other.totalSmearedPowerMah;
        this.systemServiceCpuPowerMah += other.systemServiceCpuPowerMah;
        double[] dArr = other.customMeasuredPowerMah;
        if (dArr != null) {
            if (this.customMeasuredPowerMah == null) {
                this.customMeasuredPowerMah = new double[dArr.length];
            }
            if (this.customMeasuredPowerMah.length == other.customMeasuredPowerMah.length) {
                int idx = 0;
                while (true) {
                    double[] dArr2 = other.customMeasuredPowerMah;
                    if (idx >= dArr2.length) {
                        break;
                    }
                    double[] dArr3 = this.customMeasuredPowerMah;
                    dArr3[idx] = dArr3[idx] + dArr2[idx];
                    idx++;
                }
            }
        }
        this.powerReattributedToOtherSippersMah += other.powerReattributedToOtherSippersMah;
    }

    public double sumPower() {
        this.totalPowerMah = this.usagePowerMah + this.wifiPowerMah + this.gpsPowerMah + this.cpuPowerMah + this.sensorPowerMah + this.mobileRadioPowerMah + this.wakeLockPowerMah + this.cameraPowerMah + this.flashlightPowerMah + this.bluetoothPowerMah + this.audioPowerMah + this.videoPowerMah + this.systemServiceCpuPowerMah;
        if (this.customMeasuredPowerMah != null) {
            int idx = 0;
            while (true) {
                double[] dArr = this.customMeasuredPowerMah;
                if (idx >= dArr.length) {
                    break;
                }
                this.totalPowerMah += dArr[idx];
                idx++;
            }
        }
        double d = this.totalPowerMah + this.powerReattributedToOtherSippersMah;
        this.totalPowerMah = d;
        this.totalSmearedPowerMah = this.screenPowerMah + d + this.proportionalSmearMah;
        return d;
    }
}
