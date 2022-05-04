package com.android.internal.os;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.MemoryFile;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.R;
import com.android.internal.app.IBatteryStats;
import com.android.internal.os.BatterySipper;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Deprecated
/* loaded from: classes4.dex */
public class BatteryStatsHelper {
    static final boolean DEBUG = false;
    private static Intent sBatteryBroadcastXfer;
    private static BatteryStats sStatsXfer;
    private Intent mBatteryBroadcast;
    private IBatteryStats mBatteryInfo;
    long mBatteryRealtimeUs;
    long mBatteryTimeRemainingUs;
    long mBatteryUptimeUs;
    long mChargeTimeRemainingUs;
    private final boolean mCollectBatteryBroadcast;
    private double mComputedPower;
    private final Context mContext;
    private double mMaxDrainedPower;
    private double mMaxPower;
    private double mMaxRealPower;
    private double mMinDrainedPower;
    private final List<BatterySipper> mMobilemsppList;
    private PackageManager mPackageManager;
    private List<PowerCalculator> mPowerCalculators;
    private PowerProfile mPowerProfile;
    long mRawRealtimeUs;
    long mRawUptimeUs;
    private String[] mServicepackageArray;
    private BatteryStats mStats;
    private long mStatsPeriod;
    private int mStatsType;
    private String[] mSystemPackageArray;
    private double mTotalPower;
    private final List<BatterySipper> mUsageList;
    private final boolean mWifiOnly;
    private static final String TAG = BatteryStatsHelper.class.getSimpleName();
    private static ArrayMap<File, BatteryStats> sFileXfer = new ArrayMap<>();

    public static boolean checkWifiOnly(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (tm == null) {
            return false;
        }
        return !tm.isDataCapable();
    }

    public BatteryStatsHelper(Context context) {
        this(context, true);
    }

    public BatteryStatsHelper(Context context, boolean collectBatteryBroadcast) {
        this(context, collectBatteryBroadcast, checkWifiOnly(context));
    }

    public BatteryStatsHelper(Context context, boolean collectBatteryBroadcast, boolean wifiOnly) {
        this.mUsageList = new ArrayList();
        this.mMobilemsppList = new ArrayList();
        this.mStatsType = 0;
        this.mStatsPeriod = 0L;
        this.mMaxPower = 1.0d;
        this.mMaxRealPower = 1.0d;
        this.mContext = context;
        this.mCollectBatteryBroadcast = collectBatteryBroadcast;
        this.mWifiOnly = wifiOnly;
        this.mPackageManager = context.getPackageManager();
        Resources resources = context.getResources();
        this.mSystemPackageArray = resources.getStringArray(R.array.config_batteryPackageTypeSystem);
        this.mServicepackageArray = resources.getStringArray(R.array.config_batteryPackageTypeService);
    }

    public void storeStatsHistoryInFile(String fname) {
        synchronized (sFileXfer) {
            File path = makeFilePath(this.mContext, fname);
            sFileXfer.put(path, getStats());
            FileOutputStream fout = null;
            try {
                try {
                    fout = new FileOutputStream(path);
                    Parcel hist = Parcel.obtain();
                    getStats().writeToParcelWithoutUids(hist, 0);
                    byte[] histData = hist.marshall();
                    fout.write(histData);
                    fout.close();
                } catch (IOException e) {
                    Log.w(TAG, "Unable to write history to file", e);
                    if (fout != null) {
                        fout.close();
                    }
                }
            } catch (IOException e2) {
            }
        }
    }

    public static BatteryStats statsFromFile(Context context, String fname) {
        synchronized (sFileXfer) {
            File path = makeFilePath(context, fname);
            BatteryStats stats = sFileXfer.get(path);
            if (stats != null) {
                return stats;
            }
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(path);
                byte[] data = readFully(fin);
                Parcel parcel = Parcel.obtain();
                parcel.unmarshall(data, 0, data.length);
                parcel.setDataPosition(0);
                BatteryStatsImpl createFromParcel = BatteryStatsImpl.CREATOR.createFromParcel(parcel);
                try {
                    fin.close();
                } catch (IOException e) {
                }
                return createFromParcel;
            } catch (IOException e2) {
                Log.w(TAG, "Unable to read history to file", e2);
                if (fin != null) {
                    try {
                        fin.close();
                    } catch (IOException e3) {
                    }
                }
                return getStats(IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats")), true);
            }
        }
    }

    public static void dropFile(Context context, String fname) {
        makeFilePath(context, fname).delete();
    }

    private static File makeFilePath(Context context, String fname) {
        return new File(context.getFilesDir(), fname);
    }

    public void clearStats() {
        this.mStats = null;
    }

    public BatteryStats getStats() {
        return getStats(true);
    }

    public BatteryStats getStats(boolean updateAll) {
        if (this.mStats == null) {
            load(updateAll);
        }
        return this.mStats;
    }

    public Intent getBatteryBroadcast() {
        if (this.mBatteryBroadcast == null && this.mCollectBatteryBroadcast) {
            load();
        }
        return this.mBatteryBroadcast;
    }

    public PowerProfile getPowerProfile() {
        return this.mPowerProfile;
    }

    public void create(BatteryStats stats) {
        this.mPowerProfile = new PowerProfile(this.mContext);
        this.mStats = stats;
    }

    public void create(Bundle icicle) {
        if (icicle != null) {
            this.mStats = sStatsXfer;
            this.mBatteryBroadcast = sBatteryBroadcastXfer;
        }
        this.mBatteryInfo = IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        this.mPowerProfile = new PowerProfile(this.mContext);
    }

    public void storeState() {
        sStatsXfer = this.mStats;
        sBatteryBroadcastXfer = this.mBatteryBroadcast;
    }

    public static String makemAh(double power) {
        return PowerCalculator.formatCharge(power);
    }

    public void refreshStats(int statsType, int asUser) {
        SparseArray<UserHandle> users = new SparseArray<>(1);
        users.put(asUser, new UserHandle(asUser));
        refreshStats(statsType, users);
    }

    public void refreshStats(int statsType, List<UserHandle> asUsers) {
        int n = asUsers.size();
        SparseArray<UserHandle> users = new SparseArray<>(n);
        for (int i = 0; i < n; i++) {
            UserHandle userHandle = asUsers.get(i);
            users.put(userHandle.getIdentifier(), userHandle);
        }
        refreshStats(statsType, users);
    }

    public void refreshStats(int statsType, SparseArray<UserHandle> asUsers) {
        refreshStats(statsType, asUsers, SystemClock.elapsedRealtime() * 1000, SystemClock.uptimeMillis() * 1000);
    }

    public void refreshStats(int statsType, SparseArray<UserHandle> asUsers, long rawRealtimeUs, long rawUptimeUs) {
        BatteryStatsHelper batteryStatsHelper = this;
        if (statsType != 0) {
            Log.w(TAG, "refreshStats called for statsType " + statsType + " but only STATS_SINCE_CHARGED is supported. Using STATS_SINCE_CHARGED instead.");
        }
        getStats();
        batteryStatsHelper.mMaxPower = 0.0d;
        batteryStatsHelper.mMaxRealPower = 0.0d;
        batteryStatsHelper.mComputedPower = 0.0d;
        batteryStatsHelper.mTotalPower = 0.0d;
        batteryStatsHelper.mUsageList.clear();
        batteryStatsHelper.mMobilemsppList.clear();
        if (batteryStatsHelper.mStats != null) {
            if (batteryStatsHelper.mPowerCalculators == null) {
                ArrayList arrayList = new ArrayList();
                batteryStatsHelper.mPowerCalculators = arrayList;
                arrayList.add(new CpuPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new MemoryPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new WakelockPowerCalculator(batteryStatsHelper.mPowerProfile));
                if (!batteryStatsHelper.mWifiOnly) {
                    batteryStatsHelper.mPowerCalculators.add(new MobileRadioPowerCalculator(batteryStatsHelper.mPowerProfile));
                }
                batteryStatsHelper.mPowerCalculators.add(new WifiPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new BluetoothPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new SensorPowerCalculator((SensorManager) batteryStatsHelper.mContext.getSystemService(SensorManager.class)));
                batteryStatsHelper.mPowerCalculators.add(new GnssPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new CameraPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new FlashlightPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new MediaPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new PhonePowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new ScreenPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new AmbientDisplayPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new SystemServicePowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new IdlePowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new CustomMeasuredPowerCalculator(batteryStatsHelper.mPowerProfile));
                batteryStatsHelper.mPowerCalculators.add(new UserPowerCalculator());
            }
            int size = batteryStatsHelper.mPowerCalculators.size();
            for (int i = 0; i < size; i++) {
                batteryStatsHelper.mPowerCalculators.get(i).reset();
            }
            batteryStatsHelper.mStatsType = statsType;
            batteryStatsHelper.mRawUptimeUs = rawUptimeUs;
            batteryStatsHelper.mRawRealtimeUs = rawRealtimeUs;
            batteryStatsHelper.mBatteryUptimeUs = batteryStatsHelper.mStats.getBatteryUptime(rawUptimeUs);
            batteryStatsHelper.mBatteryRealtimeUs = batteryStatsHelper.mStats.getBatteryRealtime(rawRealtimeUs);
            batteryStatsHelper.mBatteryTimeRemainingUs = batteryStatsHelper.mStats.computeBatteryTimeRemaining(rawRealtimeUs);
            batteryStatsHelper.mChargeTimeRemainingUs = batteryStatsHelper.mStats.computeChargeTimeRemaining(rawRealtimeUs);
            batteryStatsHelper.mStatsPeriod = batteryStatsHelper.mStats.computeBatteryRealtime(rawRealtimeUs, batteryStatsHelper.mStatsType);
            batteryStatsHelper.mMinDrainedPower = (batteryStatsHelper.mStats.getLowDischargeAmountSinceCharge() * batteryStatsHelper.mPowerProfile.getBatteryCapacity()) / 100.0d;
            batteryStatsHelper.mMaxDrainedPower = (batteryStatsHelper.mStats.getHighDischargeAmountSinceCharge() * batteryStatsHelper.mPowerProfile.getBatteryCapacity()) / 100.0d;
            batteryStatsHelper.processAppUsage(asUsers);
            Collections.sort(batteryStatsHelper.mUsageList);
            Collections.sort(batteryStatsHelper.mMobilemsppList, BatteryStatsHelper$$ExternalSyntheticLambda0.INSTANCE);
            if (!batteryStatsHelper.mUsageList.isEmpty()) {
                double d = batteryStatsHelper.mUsageList.get(0).totalPowerMah;
                batteryStatsHelper.mMaxPower = d;
                batteryStatsHelper.mMaxRealPower = d;
                int usageListCount = batteryStatsHelper.mUsageList.size();
                for (int i2 = 0; i2 < usageListCount; i2++) {
                    batteryStatsHelper.mComputedPower += batteryStatsHelper.mUsageList.get(i2).totalPowerMah;
                }
            }
            batteryStatsHelper.mTotalPower = batteryStatsHelper.mComputedPower;
            if (batteryStatsHelper.mStats.getLowDischargeAmountSinceCharge() > 1) {
                double d2 = batteryStatsHelper.mMinDrainedPower;
                double d3 = batteryStatsHelper.mComputedPower;
                if (d2 > d3) {
                    double amount = d2 - d3;
                    batteryStatsHelper.mTotalPower = d2;
                    BatterySipper bs = new BatterySipper(BatterySipper.DrainType.UNACCOUNTED, null, amount);
                    int index = Collections.binarySearch(batteryStatsHelper.mUsageList, bs);
                    if (index < 0) {
                        index = -(index + 1);
                    }
                    batteryStatsHelper.mUsageList.add(index, bs);
                    batteryStatsHelper.mMaxPower = Math.max(batteryStatsHelper.mMaxPower, amount);
                } else {
                    double d4 = batteryStatsHelper.mMaxDrainedPower;
                    if (d4 < d3) {
                        double amount2 = d3 - d4;
                        BatterySipper bs2 = new BatterySipper(BatterySipper.DrainType.OVERCOUNTED, null, amount2);
                        int index2 = Collections.binarySearch(batteryStatsHelper.mUsageList, bs2);
                        if (index2 < 0) {
                            index2 = -(index2 + 1);
                        }
                        batteryStatsHelper.mUsageList.add(index2, bs2);
                        batteryStatsHelper.mMaxPower = Math.max(batteryStatsHelper.mMaxPower, amount2);
                    }
                }
            }
            double hiddenPowerMah = batteryStatsHelper.removeHiddenBatterySippers(batteryStatsHelper.mUsageList);
            double totalRemainingPower = getTotalPower() - hiddenPowerMah;
            if (Math.abs(totalRemainingPower) > 0.001d) {
                int i3 = 0;
                int size2 = batteryStatsHelper.mUsageList.size();
                while (i3 < size2) {
                    BatterySipper sipper = batteryStatsHelper.mUsageList.get(i3);
                    if (!sipper.shouldHide) {
                        sipper.proportionalSmearMah = ((sipper.totalPowerMah + sipper.screenPowerMah) / totalRemainingPower) * hiddenPowerMah;
                        sipper.sumPower();
                    }
                    i3++;
                    batteryStatsHelper = this;
                }
            }
        }
    }

    private void processAppUsage(SparseArray<UserHandle> asUsers) {
        SparseArray<? extends BatteryStats.Uid> uidStats = this.mStats.getUidStats();
        ArrayList<BatterySipper> sippers = new ArrayList<>(uidStats.size());
        int size = uidStats.size();
        for (int iu = 0; iu < size; iu++) {
            BatteryStats.Uid u = (BatteryStats.Uid) uidStats.valueAt(iu);
            sippers.add(new BatterySipper(BatterySipper.DrainType.APP, u, 0.0d));
        }
        int size2 = this.mPowerCalculators.size();
        for (int i = 0; i < size2; i++) {
            PowerCalculator calculator = this.mPowerCalculators.get(i);
            calculator.calculate(sippers, this.mStats, this.mRawRealtimeUs, this.mRawUptimeUs, this.mStatsType, asUsers);
        }
        int i2 = sippers.size();
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            BatterySipper sipper = sippers.get(i3);
            double totalPower = sipper.sumPower();
            if (totalPower != 0.0d || sipper.getUid() == 0) {
                if (sipper.drainType == BatterySipper.DrainType.APP) {
                    sipper.computeMobilemspp();
                    if (sipper.mobilemspp != 0.0d) {
                        this.mMobilemsppList.add(sipper);
                    }
                }
                if (!sipper.isAggregated) {
                    this.mUsageList.add(sipper);
                }
            }
        }
    }

    public List<BatterySipper> getUsageList() {
        return this.mUsageList;
    }

    public List<BatterySipper> getMobilemsppList() {
        return this.mMobilemsppList;
    }

    public long getStatsPeriod() {
        return this.mStatsPeriod;
    }

    public int getStatsType() {
        return this.mStatsType;
    }

    public double getMaxPower() {
        return this.mMaxPower;
    }

    public double getMaxRealPower() {
        return this.mMaxRealPower;
    }

    public double getTotalPower() {
        return this.mTotalPower;
    }

    public double getComputedPower() {
        return this.mComputedPower;
    }

    public double getMinDrainedPower() {
        return this.mMinDrainedPower;
    }

    public double getMaxDrainedPower() {
        return this.mMaxDrainedPower;
    }

    public static byte[] readFully(FileInputStream stream) throws IOException {
        return readFully(stream, stream.available());
    }

    public static byte[] readFully(FileInputStream stream, int avail) throws IOException {
        int pos = 0;
        byte[] data = new byte[avail];
        while (true) {
            int amt = stream.read(data, pos, data.length - pos);
            if (amt <= 0) {
                return data;
            }
            pos += amt;
            int avail2 = stream.available();
            if (avail2 > data.length - pos) {
                byte[] newData = new byte[pos + avail2];
                System.arraycopy(data, 0, newData, 0, pos);
                data = newData;
            }
        }
    }

    public double removeHiddenBatterySippers(List<BatterySipper> sippers) {
        double proportionalSmearPowerMah = 0.0d;
        for (int i = sippers.size() - 1; i >= 0; i--) {
            BatterySipper sipper = sippers.get(i);
            sipper.shouldHide = shouldHideSipper(sipper);
            if (!(!sipper.shouldHide || sipper.drainType == BatterySipper.DrainType.OVERCOUNTED || sipper.drainType == BatterySipper.DrainType.SCREEN || sipper.drainType == BatterySipper.DrainType.AMBIENT_DISPLAY || sipper.drainType == BatterySipper.DrainType.UNACCOUNTED || sipper.drainType == BatterySipper.DrainType.BLUETOOTH || sipper.drainType == BatterySipper.DrainType.WIFI || sipper.drainType == BatterySipper.DrainType.IDLE)) {
                proportionalSmearPowerMah += sipper.totalPowerMah;
            }
        }
        return proportionalSmearPowerMah;
    }

    public boolean shouldHideSipper(BatterySipper sipper) {
        BatterySipper.DrainType drainType = sipper.drainType;
        return drainType == BatterySipper.DrainType.IDLE || drainType == BatterySipper.DrainType.CELL || drainType == BatterySipper.DrainType.SCREEN || drainType == BatterySipper.DrainType.AMBIENT_DISPLAY || drainType == BatterySipper.DrainType.UNACCOUNTED || drainType == BatterySipper.DrainType.OVERCOUNTED || isTypeService(sipper) || isTypeSystem(sipper);
    }

    public boolean isTypeService(BatterySipper sipper) {
        String[] packages = this.mPackageManager.getPackagesForUid(sipper.getUid());
        if (packages == null) {
            return false;
        }
        for (String packageName : packages) {
            if (ArrayUtils.contains(this.mServicepackageArray, packageName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTypeSystem(BatterySipper sipper) {
        String[] strArr;
        int uid = sipper.uidObj == null ? -1 : sipper.getUid();
        sipper.mPackages = this.mPackageManager.getPackagesForUid(uid);
        if (uid >= 0 && uid < 10000) {
            return true;
        }
        if (sipper.mPackages != null) {
            for (String packageName : sipper.mPackages) {
                if (ArrayUtils.contains(this.mSystemPackageArray, packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public long convertUsToMs(long timeUs) {
        return timeUs / 1000;
    }

    public long convertMsToUs(long timeMs) {
        return 1000 * timeMs;
    }

    public void setPackageManager(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    public void setSystemPackageArray(String[] array) {
        this.mSystemPackageArray = array;
    }

    public void setServicePackageArray(String[] array) {
        this.mServicepackageArray = array;
    }

    private void load() {
        load(true);
    }

    private void load(boolean updateAll) {
        IBatteryStats iBatteryStats = this.mBatteryInfo;
        if (iBatteryStats != null) {
            this.mStats = getStats(iBatteryStats, updateAll);
            if (this.mCollectBatteryBroadcast) {
                this.mBatteryBroadcast = this.mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            }
        }
    }

    private static BatteryStatsImpl getStats(IBatteryStats service, boolean updateAll) {
        try {
            ParcelFileDescriptor pfd = service.getStatisticsStream(updateAll);
            if (pfd != null) {
                try {
                    FileInputStream fis = new ParcelFileDescriptor.AutoCloseInputStream(pfd);
                    try {
                        byte[] data = readFully(fis, MemoryFile.getSize(pfd.getFileDescriptor()));
                        Parcel parcel = Parcel.obtain();
                        parcel.unmarshall(data, 0, data.length);
                        parcel.setDataPosition(0);
                        BatteryStatsImpl stats = BatteryStatsImpl.CREATOR.createFromParcel(parcel);
                        fis.close();
                        return stats;
                    } catch (Throwable th) {
                        try {
                            fis.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (IOException e) {
                    Log.w(TAG, "Unable to read statistics stream", e);
                }
            }
        } catch (RemoteException e2) {
            Log.w(TAG, "RemoteException:", e2);
        }
        return new BatteryStatsImpl();
    }
}
