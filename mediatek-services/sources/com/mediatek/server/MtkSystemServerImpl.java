package com.mediatek.server;

import android.content.Context;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.os.SystemProperties;
import android.util.Slog;
import com.android.server.SystemService;
import com.android.server.SystemServiceManager;
import com.android.server.utils.TimingsTraceAndSlog;
import com.mediatek.search.SearchEngineManagerService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class MtkSystemServerImpl extends MtkSystemServer {
    private static TimingsTraceAndSlog BOOT_TIMINGS_TRACE_LOG = null;
    private static final String HDMI_LOCAL_SERVICE_CLASS = "com.mediatek.hdmilocalservice.HdmiLocalService";
    private static final String MTK_ALARM_MANAGER_SERVICE_CLASS = "com.mediatek.server.MtkAlarmManagerService";
    private static final String MTK_CONNECTIVITY_SUPPLEMENTAL_SERVICE_CLASS = "com.mediatek.server.MtkConnectivitySupplementalService";
    private static final String MTK_FM_RADIO_SERVICE_CLASS = "com.mediatek.fmradioservice.FmRadioService";
    private static final String MTK_OMADM_SERVICE_CLASS = "com.mediatek.omadm.OmadmService";
    private static final String MTK_STORAGE_MANAGER_SERVICE_CLASS = "com.mediatek.server.MtkStorageManagerService$MtkStorageManagerServiceLifecycle";
    private static final String MTK_VOW_BRIDGE_SERVICE_CLASS = "com.mediatek.server.vow.VoiceWakeupBridgeService";
    private static final String POWER_HAL_SERVICE_CLASS = "com.mediatek.powerhalservice.PowerHalMgrService";
    private static final String SEARCH_ENGINE_SERVICE_CLASS = "com.mediatek.search.SearchEngineManagerService";
    private static final String TAG = "MtkSystemServerImpl";
    private boolean mMTPROF_disable = false;
    private Context mSystemContext;
    private SystemServiceManager mSystemServiceManager;

    public void setPrameters(TimingsTraceAndSlog btt, SystemServiceManager ssm, Context context) {
        BOOT_TIMINGS_TRACE_LOG = btt;
        this.mSystemServiceManager = ssm;
        this.mSystemContext = context;
    }

    public void startMtkBootstrapServices() {
        Slog.i(TAG, "startMtkBootstrapServices");
    }

    public void startMtkCoreServices() {
        Slog.i(TAG, "startMtkCoreServices ");
    }

    /* JADX WARN: Type inference failed for: r9v5, types: [com.mediatek.search.SearchEngineManagerService, android.os.IBinder] */
    public void startMtkOtherServices() {
        Context context = this.mSystemContext;
        Slog.i(TAG, "startOtherMtkService ");
        boolean isValidVowConfig = false;
        boolean disableSearchManager = SystemProperties.getBoolean("config.disable_searchmanager", false);
        boolean disableNonCoreServices = SystemProperties.getBoolean("config.disable_noncore", false);
        boolean enableHdmiServices = !"".equals(SystemProperties.get("ro.vendor.mtk_tb_hdmi"));
        if (!disableNonCoreServices && !disableSearchManager) {
            traceBeginAndSlog("StartSearchEngineManagerService");
            try {
                ServiceManager.addService("search_engine_service", (IBinder) new SearchEngineManagerService(context));
            } catch (Throwable e) {
                Slog.e(TAG, "StartSearchEngineManagerService " + e.toString());
            }
        }
        try {
            if (Class.forName("com.mediatek.fmradio.FmRadioPackageManager") != null) {
                traceBeginAndSlog("addService FmRadioService");
                startService(MTK_FM_RADIO_SERVICE_CLASS);
                traceEnd();
            }
        } catch (Exception e2) {
            Slog.e(TAG, "com.mediatek.fmradio.FmRadioPackageManager not found ");
        }
        if (enableHdmiServices) {
            traceBeginAndSlog("StartHdmiLocalService");
            try {
                startService(HDMI_LOCAL_SERVICE_CLASS);
            } catch (Throwable e3) {
                reportWtf("starting HdmiLocalService", e3);
            }
            traceEnd();
        }
        traceBeginAndSlog("StartPowerHalMgrService");
        try {
            startService(POWER_HAL_SERVICE_CLASS);
        } catch (Throwable e4) {
            reportWtf("starting PowerHalMgrService", e4);
        }
        traceEnd();
        if (isWakeupSupport(this.mSystemContext) && (isTriggerSupport(this.mSystemContext) || isVowPDKSupported(this.mSystemContext))) {
            isValidVowConfig = true;
        }
        traceBeginAndSlog("V_W_B_S isValidVowConfig " + isValidVowConfig);
        if (isValidVowConfig) {
            traceBeginAndSlog("addService VoiceWakeupBridgeService");
            try {
                startService(MTK_VOW_BRIDGE_SERVICE_CLASS);
            } catch (Throwable e5) {
                reportWtf("starting VoiceWakeupBridgeService", e5);
            }
            traceEnd();
        } else {
            traceBeginAndSlog("not addService VoiceWakeupBridgeService");
        }
        try {
            if (Class.forName(MTK_CONNECTIVITY_SUPPLEMENTAL_SERVICE_CLASS) != null) {
                traceBeginAndSlog("StartConnectivitySupplementalService");
                startService(MTK_CONNECTIVITY_SUPPLEMENTAL_SERVICE_CLASS);
                traceEnd();
                return;
            }
            Slog.e(TAG, "com.mediatek.server.MtkConnectivitySupplementalServiceis null");
        } catch (Exception e6) {
            Slog.e(TAG, "com.mediatek.server.MtkConnectivitySupplementalServicenot found");
        }
    }

    public boolean startMtkAlarmManagerService() {
        traceBeginAndSlog("startMtkAlarmManagerService");
        try {
            startService(MTK_ALARM_MANAGER_SERVICE_CLASS);
            traceEnd();
            return true;
        } catch (Throwable e) {
            Slog.e(TAG, "Exception while starting MtkAlarmManagerService" + e.toString());
            return false;
        }
    }

    public boolean startMtkStorageManagerService() {
        boolean mIsPrivacyProtectionLockSupport = SystemProperties.get("ro.vendor.mtk_privacy_protection_lock").equals("1");
        if (!mIsPrivacyProtectionLockSupport) {
            Slog.i(TAG, "PPL not supported, retruning, will start AOSP StorageManagerService");
            return false;
        }
        traceBeginAndSlog("StartMtkStorageManagerService");
        try {
            startService(MTK_STORAGE_MANAGER_SERVICE_CLASS);
            traceEnd();
            return true;
        } catch (Throwable e) {
            Slog.e(TAG, "Exception while starting MtkStorageManagerService" + e.toString());
            return false;
        }
    }

    private SystemService startService(String className) {
        try {
            return this.mSystemServiceManager.startService(Class.forName(className));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Failed to create service " + className + ": service class not found, usually indicates that the caller should have called PackageManager.hasSystemFeature() to check whether the feature is available on this device before trying to start the services that implement it", ex);
        }
    }

    private static void traceBeginAndSlog(String name) {
        Slog.i(TAG, name);
    }

    private static void traceEnd() {
    }

    private void reportWtf(String msg, Throwable e) {
        Slog.w(TAG, "***********************************************");
        Slog.wtf(TAG, "BOOT FAILURE " + msg, e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    public void addBootEvent(String bootevent) {
        String str = "Failure close /proc/bootprof entry";
        if (!this.mMTPROF_disable) {
            StrictMode.ThreadPolicy oldPolicy = StrictMode.getThreadPolicy();
            if (bootevent.contains("AP_Init")) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(oldPolicy).permitDiskWrites().build());
            }
            FileOutputStream fbp = null;
            try {
                try {
                    try {
                        fbp = new FileOutputStream("/proc/bootprof");
                        fbp.write(bootevent.getBytes());
                        fbp.flush();
                        fbp.close();
                    } catch (Throwable th) {
                        if (fbp != null) {
                            try {
                                fbp.close();
                            } catch (IOException e) {
                                Slog.e("BOOTPROF", str, e);
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e2) {
                    Slog.e("BOOTPROF", "Failure open /proc/bootprof, not found!", e2);
                    if (fbp != null) {
                        fbp.close();
                    }
                } catch (IOException e3) {
                    Slog.e("BOOTPROF", "Failure open /proc/bootprof entry", e3);
                    if (fbp != null) {
                        fbp.close();
                    }
                }
            } catch (IOException e4) {
                Slog.e("BOOTPROF", (String) str, e4);
            }
            str = bootevent.contains("AP_Init");
            if (str != 0) {
                StrictMode.setThreadPolicy(oldPolicy);
            }
        }
    }

    private boolean isWakeupSupport(Context context) {
        AudioManager am = (AudioManager) context.getSystemService("audio");
        if (am == null) {
            traceBeginAndSlog("isWakeupSupport get audio service is null");
            return false;
        }
        String state = am.getParameters("MTK_VOW_SUPPORT");
        traceBeginAndSlog("[isWakeupSupport] " + state);
        if (state != null) {
            return state.equalsIgnoreCase("MTK_VOW_SUPPORT=true");
        }
        return false;
    }

    private boolean isTriggerSupport(Context mContext) {
        boolean isAlexa = false;
        AudioManager am = (AudioManager) mContext.getSystemService("audio");
        if (am == null) {
            traceBeginAndSlog("[isTriggerSupport] get audio service is null");
            return false;
        }
        String AlexaSupport = am.getParameters("MTK_VOW_AMAZON_SUPPORT");
        traceBeginAndSlog("[isTriggerSupport] AlexaSupport " + AlexaSupport);
        if (AlexaSupport.equals("MTK_VOW_AMAZON_SUPPORT=true")) {
            isAlexa = true;
        }
        traceBeginAndSlog("[isTriggerSupport] isAlexa " + isAlexa);
        return isAlexa;
    }

    private boolean isVowPDKSupported(Context mContext) {
        AudioManager am = (AudioManager) mContext.getSystemService("audio");
        if (am == null) {
            traceBeginAndSlog("[isVowPDKSupported] get audio service is null");
            return false;
        }
        int size = 0;
        String[] result = am.getParameters("MTK_VOW_MAX_PDK_NUMBER").split("=");
        if (result != null && result.length >= 2) {
            size = Integer.parseInt(result[1]);
        }
        traceBeginAndSlog("[isVowPDKSupported] DSP Engine Size " + size);
        if (size > 0) {
            return true;
        }
        return false;
    }
}
