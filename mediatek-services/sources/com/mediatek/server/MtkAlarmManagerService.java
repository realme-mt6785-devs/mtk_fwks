package com.mediatek.server;

import android.app.AlarmManager;
import android.app.IAlarmListener;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.display.WifiDisplayStatus;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.WorkSource;
import android.text.format.Time;
import android.util.Slog;
import com.android.server.alarm.Alarm;
import com.android.server.alarm.AlarmManagerService;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MtkAlarmManagerService extends AlarmManagerService {
    private static final String PKGNAME_ENGINEERMODE = "com.oplus.engineermode";
    private static final String PKGNAME_SETTINGS = "com.android.settings";
    private static final long POWER_OFF_ALARMCLOCK_BUFFER_TIME = 60000;
    static final long POWER_OFF_ALARM_BUFFER_TIME = 150000;
    private static final Comparator<Alarm> sIncreasingTimeOrder = Comparator.comparingLong(MtkAlarmManagerService$$ExternalSyntheticLambda0.INSTANCE);
    private long mNativeData;
    WFDStatusChangedReceiver mWFDStatusChangedReceiver;
    private Object mWaitThreadlock = new Object();
    private Object mPowerOffAlarmLock = new Object();
    private final ArrayList<Alarm> mPoweroffAlarms = new ArrayList<>();
    boolean mIsWFDConnected = false;

    public MtkAlarmManagerService(Context context) {
        super(context);
        if (Build.TYPE.equals("eng")) {
            localLOGV = true;
            DEBUG_WAKELOCK = false;
            DEBUG_BATCH = false;
            DEBUG_STANDBY = true;
            DEBUG_ALARM_CLOCK = true;
        } else if (Build.TYPE.equals("userdebug")) {
            localLOGV = false;
            DEBUG_WAKELOCK = false;
            DEBUG_BATCH = false;
            DEBUG_STANDBY = true;
            DEBUG_ALARM_CLOCK = true;
        }
    }

    protected void registerWFDStatusChangeReciever() {
        this.mWFDStatusChangedReceiver = new WFDStatusChangedReceiver();
    }

    protected boolean isWFDConnected() {
        return this.mIsWFDConnected;
    }

    public void onStart() {
        MtkAlarmManagerService.super.onStart();
        if (this.mInjector != null) {
            this.mNativeData = this.mInjector.getNativeData();
        }
    }

    /* loaded from: classes.dex */
    class WFDStatusChangedReceiver extends BroadcastReceiver {
        public WFDStatusChangedReceiver() {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED");
            MtkAlarmManagerService.this.getContext().registerReceiver(this, filter);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED".equals(intent.getAction())) {
                WifiDisplayStatus wfdStatus = intent.getParcelableExtra("android.hardware.display.extra.WIFI_DISPLAY_STATUS");
                MtkAlarmManagerService.this.mIsWFDConnected = 2 == wfdStatus.getActiveDisplayState();
            }
        }
    }

    protected boolean isPowerOffAlarmType(int type) {
        if (type != 7) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class PowerOffAlarmComparator implements Comparator {
        PowerOffAlarmComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Object o1, Object o2) {
            Alarm e1 = (Alarm) o1;
            Alarm e2 = (Alarm) o2;
            if (e1.origWhen > e2.origWhen) {
                return 1;
            }
            if (e1.origWhen == e2.origWhen) {
                return 0;
            }
            return -1;
        }
    }

    protected boolean schedulePoweroffAlarm(int type, long triggerAtTime, long interval, PendingIntent operation, IAlarmListener directReceiver, String listenerTag, WorkSource workSource, AlarmManager.AlarmClockInfo alarmClock, String callingPackage) {
        long bufferTime;
        if (type != 7) {
            return true;
        }
        if (this.mNativeData == -1) {
            Slog.w("AlarmManager", "alarm driver not open ,return!");
            return false;
        }
        if (DEBUG_ALARM_CLOCK) {
            Slog.d("AlarmManager", "alarm set type 7 , package name " + operation.getTargetPackage());
        }
        String packageName = operation.getTargetPackage();
        long nowTime = System.currentTimeMillis();
        if (!PKGNAME_SETTINGS.equals(packageName)) {
            long bufferTime2 = POWER_OFF_ALARM_BUFFER_TIME;
            if (isAlarmClockPkg(packageName)) {
                bufferTime2 = POWER_OFF_ALARMCLOCK_BUFFER_TIME;
            }
            Slog.d("AlarmManager", "schedulePoweroffAlarm: triggerAtTime= " + triggerAtTime + ", nowTime=" + nowTime + ", bufferTime=" + bufferTime2 + ", packageName=" + packageName);
            bufferTime = triggerAtTime - nowTime > bufferTime2 ? triggerAtTime - bufferTime2 : triggerAtTime;
        } else {
            bufferTime = triggerAtTime;
        }
        if (bufferTime < nowTime) {
            if (DEBUG_ALARM_CLOCK) {
                Slog.w("AlarmManager", "PowerOff alarm set time is wrong! nowTime = " + nowTime + " ; triggerAtTime = " + bufferTime);
            }
            return false;
        }
        if (DEBUG_ALARM_CLOCK) {
            Slog.d("AlarmManager", "PowerOff alarm TriggerTime = " + bufferTime + " now = " + nowTime);
        }
        synchronized (this.mPowerOffAlarmLock) {
            try {
                try {
                    removePoweroffAlarmLocked(operation.getTargetPackage());
                    int poweroffAlarmUserId = UserHandle.getCallingUserId();
                    Alarm alarm = new Alarm(type, bufferTime, 0L, 0L, interval, operation, directReceiver, listenerTag, workSource, 0, alarmClock, poweroffAlarmUserId, callingPackage, (Bundle) null, -1);
                    addPoweroffAlarmLocked(alarm);
                    if (this.mPoweroffAlarms.size() > 0) {
                        Slog.i("AlarmManager", "whb mPoweroffAlarms.size=" + this.mPoweroffAlarms.size());
                        Collections.sort(this.mPoweroffAlarms, new PowerOffAlarmComparator());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (int i = 0; i < this.mPoweroffAlarms.size(); i++) {
                            String time = sdf.format(new Date(this.mPoweroffAlarms.get(i).origWhen));
                            Slog.i("AlarmManager", "whb alram.when=" + time);
                        }
                        Slog.w("AlarmManager", "whb resetPoweroffAlarm 1");
                        resetPoweroffAlarm(this.mPoweroffAlarms.get(0));
                    }
                    return true;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    protected void updatePoweroffAlarmtoNowRtc() {
        long nowRTC = System.currentTimeMillis();
        updatePoweroffAlarm(nowRTC);
    }

    private void updatePoweroffAlarm(long nowRTC) {
        synchronized (this.mPowerOffAlarmLock) {
            if (this.mPoweroffAlarms.size() != 0) {
                if (this.mPoweroffAlarms.get(0).origWhen <= nowRTC) {
                    Iterator<Alarm> it = this.mPoweroffAlarms.iterator();
                    while (it.hasNext()) {
                        Alarm alarm = it.next();
                        if (alarm.origWhen > nowRTC) {
                            break;
                        }
                        if (DEBUG_ALARM_CLOCK) {
                            Slog.w("AlarmManager", "power off alarm update deleted");
                        }
                        it.remove();
                    }
                    if (this.mPoweroffAlarms.size() > 0) {
                        Collections.sort(this.mPoweroffAlarms, new PowerOffAlarmComparator());
                        resetPoweroffAlarm(this.mPoweroffAlarms.get(0));
                    }
                }
            }
        }
    }

    private int addPoweroffAlarmLocked(Alarm alarm) {
        ArrayList<Alarm> alarmList = this.mPoweroffAlarms;
        int index = Collections.binarySearch(alarmList, alarm, sIncreasingTimeOrder);
        if (index < 0) {
            index = (0 - index) - 1;
        }
        if (localLOGV) {
            Slog.v("AlarmManager", "Adding alarm " + alarm + " at " + index);
        }
        alarmList.add(index, alarm);
        if (localLOGV) {
            Slog.v("AlarmManager", "alarms: " + alarmList.size() + " type: " + alarm.type);
            int position = 0;
            Iterator<Alarm> it = alarmList.iterator();
            while (it.hasNext()) {
                Alarm a = it.next();
                Time time = new Time();
                time.set(a.origWhen);
                String timeStr = time.format("%b %d %I:%M:%S %p");
                Slog.v("AlarmManager", position + ": " + timeStr + " " + a.operation.getTargetPackage());
                position++;
            }
        }
        return index;
    }

    private void removePoweroffAlarmLocked(String packageName) {
        ArrayList<Alarm> alarmList = this.mPoweroffAlarms;
        if (alarmList.size() > 0) {
            Iterator<Alarm> it = alarmList.iterator();
            while (it.hasNext()) {
                Alarm alarm = it.next();
                if (alarm.operation.getTargetPackage().equals(packageName)) {
                    it.remove();
                }
            }
        }
    }

    private void resetPoweroffAlarm(Alarm alarm) {
        String setPackageName = alarm.operation.getTargetPackage();
        long latestTime = alarm.origWhen;
        long j = this.mNativeData;
        if (j != 0 && j != -1) {
            if (setPackageName.equals("com.android.deskclock")) {
                if (DEBUG_ALARM_CLOCK) {
                    Slog.i("AlarmManager", "mBootPackage = " + setPackageName + " set Prop 2");
                }
                set(this.mNativeData, 7, latestTime / 1000, (latestTime % 1000) * 1000 * 1000);
            } else if (setPackageName.equals("com.mediatek.sqa8.aging")) {
                Slog.i("AlarmManager", "mBootPackage = " + setPackageName + " set Prop 2");
                set(this.mNativeData, 7, latestTime / 1000, (latestTime % 1000) * 1000 * 1000);
            } else if (isAlarmClockPkg(setPackageName)) {
                SystemProperties.set("persist.sys.bootpackage", "2");
                set(this.mNativeData, 7, latestTime / 1000, (latestTime % 1000) * 1000 * 1000);
                SystemProperties.set("sys.power_off_alarm", Long.toString(latestTime / 1000));
            } else if (setPackageName.equals(PKGNAME_SETTINGS) || setPackageName.equals(PKGNAME_ENGINEERMODE)) {
                SystemProperties.set("persist.sys.bootpackage", "2");
                set(this.mNativeData, 7, latestTime / 1000, (latestTime % 1000) * 1000 * 1000);
                SystemProperties.set("sys.power_off_alarm", Long.toString(latestTime / 1000));
            } else if (DEBUG_ALARM_CLOCK) {
                Slog.w("AlarmManager", "unknown package (" + setPackageName + ") to set power off alarm");
            }
            if (DEBUG_ALARM_CLOCK) {
                Slog.i("AlarmManager", "reset power off alarm is " + setPackageName);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(new Date(latestTime));
            Slog.i("AlarmManager", "sys.power_off_alarm is " + time);
        } else if (DEBUG_ALARM_CLOCK) {
            Slog.i("AlarmManager", " do not set alarm to RTC when fd close ");
        }
    }

    public void cancelPoweroffAlarmImpl(String name) {
        if (DEBUG_ALARM_CLOCK) {
            Slog.i("AlarmManager", "remove power off alarm pacakge name " + name);
        }
        synchronized (this.mPowerOffAlarmLock) {
            removePoweroffAlarmLocked(name);
            long j = this.mNativeData;
            if (!(j == 0 || j == -1)) {
                if (name.equals("com.android.deskclock")) {
                    set(this.mNativeData, 7, 0L, 0L);
                } else if (isAlarmClockPkg(name)) {
                    set(this.mNativeData, 7, 0L, 0L);
                    SystemProperties.set("sys.power_off_alarm", Long.toString(0L));
                } else if (name.equals(PKGNAME_SETTINGS) || name.equals(PKGNAME_ENGINEERMODE)) {
                    set(this.mNativeData, 7, 0L, 0L);
                    SystemProperties.set("sys.power_off_alarm", Long.toString(0L));
                }
            }
            if (this.mPoweroffAlarms.size() > 0) {
                Collections.sort(this.mPoweroffAlarms, new PowerOffAlarmComparator());
                resetPoweroffAlarm(this.mPoweroffAlarms.get(0));
            }
        }
    }

    protected void configLogTag(PrintWriter pw, String[] args, int opti) {
        if (opti >= args.length) {
            pw.println("  Invalid argument!");
        } else if ("on".equals(args[opti])) {
            localLOGV = true;
            DEBUG_BATCH = true;
            DEBUG_ALARM_CLOCK = true;
            DEBUG_WAKELOCK = true;
            DEBUG_STANDBY = true;
        } else if ("off".equals(args[opti])) {
            localLOGV = false;
            DEBUG_BATCH = false;
            DEBUG_ALARM_CLOCK = false;
            DEBUG_WAKELOCK = false;
            DEBUG_STANDBY = false;
        } else {
            pw.println("  Invalid argument!");
        }
    }

    protected void dumpWithargs(PrintWriter pw, String[] args) {
        String opt;
        int opti = 0;
        while (opti < args.length && (opt = args[opti]) != null && opt.length() > 0 && opt.charAt(0) == '-') {
            opti++;
            if ("-h".equals(opt)) {
                pw.println("alarm manager dump options:");
                pw.println("  log  [on/off]");
                pw.println("  Example:");
                pw.println("  $adb shell dumpsys alarm log on");
                pw.println("  $adb shell dumpsys alarm log off");
                return;
            }
            pw.println("Unknown argument: " + opt + "; use -h for help");
        }
        if (opti < args.length) {
            String cmd = args[opti];
            int opti2 = opti + 1;
            if ("log".equals(cmd)) {
                configLogTag(pw, args, opti2);
            }
        }
    }

    private boolean isAlarmClockPkg(String pkgName) {
        if (pkgName.contains(".deskclock") || pkgName.contains(".alarmclock")) {
            Slog.d("AlarmManager", "alarmclock pkgName = " + pkgName);
            return true;
        }
        Slog.d("AlarmManager", "222 pkgName = " + pkgName);
        return false;
    }
}
