package com.mediatek.net.tethering;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.TetheringManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Slog;
import java.util.concurrent.Executor;
import vendor.mediatek.hardware.netdagent.V1_0.INetdagent;
/* loaded from: classes.dex */
public class TestModeTethering {
    private static final String ACTION_DISABLE_DATA_ALERT = "com.mediatek.intent.action.IGNORE_DATA_USAGE_ALERT";
    private static final String AUTO_TETHERING_INTENT = "com.mediatek.intent.action.TETHERING_CHANGED";
    private static final long DISABLE_DATA_ALERT_BYTES = 0;
    private static final String TAG = "MCSS-Tethering";
    private static boolean sIsAutoTethering = SystemProperties.getBoolean("persist.vendor.net.tethering", false);
    private final Context mContext;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    TetheringManager mTethering;

    public TestModeTethering(Context context) {
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
        this.mTethering = (TetheringManager) context.getSystemService("tethering");
    }

    public void initialize() {
        Slog.d(TAG, "initialize");
        this.mHandler.post(new Runnable() { // from class: com.mediatek.net.tethering.TestModeTethering.1
            @Override // java.lang.Runnable
            public void run() {
                TestModeTethering.this.checkEngineerModeSettings();
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction(AUTO_TETHERING_INTENT);
        if (sIsAutoTethering) {
            filter.addAction("android.intent.action.BOOT_COMPLETED");
        }
        filter.addAction(ACTION_DISABLE_DATA_ALERT);
        this.mContext.registerReceiver(new TetheringReceiver(), filter);
    }

    private void setUsbTethering(boolean enable) {
        TetheringManager tm = (TetheringManager) this.mContext.getSystemService("tethering");
        tm.setUsbTethering(enable);
    }

    /* loaded from: classes.dex */
    private class TetheringReceiver extends BroadcastReceiver {
        private TetheringReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                Slog.d(TestModeTethering.TAG, "onReceive: action=" + action);
                if (TestModeTethering.AUTO_TETHERING_INTENT.equals(action)) {
                    boolean enable = intent.getBooleanExtra("tethering_isconnected", true);
                    if (enable) {
                        TestModeTethering.this.enableUsbTethering();
                    } else {
                        TestModeTethering.this.disableUsbTethering();
                    }
                } else if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
                    if (TestModeTethering.sIsAutoTethering) {
                        TestModeTethering.this.enableUsbTethering();
                    }
                } else if (TestModeTethering.ACTION_DISABLE_DATA_ALERT.equals(action)) {
                    TestModeTethering.this.mHandler.post(new Runnable() { // from class: com.mediatek.net.tethering.TestModeTethering.TetheringReceiver.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Slog.d(TestModeTethering.TAG, "Disable data usage alert");
                            Settings.Global.putLong(context.getContentResolver(), "netstats_global_alert_bytes", TestModeTethering.DISABLE_DATA_ALERT_BYTES);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableUsbTethering() {
        Slog.d(TAG, "enableUsbTethering");
        TetheringManager.TetheringRequest tr = new TetheringManager.TetheringRequest.Builder(1).setShouldShowEntitlementUi(false).build();
        this.mTethering.startTethering(tr, new Executor() { // from class: com.mediatek.net.tethering.TestModeTethering.2
            @Override // java.util.concurrent.Executor
            public void execute(Runnable r) {
                TestModeTethering.this.mHandler.post(r);
            }
        }, new TetheringManager.StartTetheringCallback() { // from class: com.mediatek.net.tethering.TestModeTethering.3
            public void onTetheringStarted() {
                Slog.d(TestModeTethering.TAG, "Auto USB tethering started");
            }

            public void onTetheringFailed(int error) {
                Slog.e(TestModeTethering.TAG, "Auto USB tethering start failed, error = " + error);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableUsbTethering() {
        Slog.d(TAG, "disableUsbTethering");
        this.mTethering.stopTethering(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEngineerModeSettings() {
        boolean isBgdataDisabled = SystemProperties.getBoolean("persist.vendor.radio.bgdata.disabled", false);
        if (isBgdataDisabled) {
            try {
                INetdagent netagent = INetdagent.getService();
                if (netagent == null) {
                    Slog.e(TAG, "netagent is null");
                    return;
                }
                Slog.d(TAG, "setIotFirewall");
                netagent.dispatchNetdagentCmd("netdagent firewall set_nsiot_firewall");
            } catch (Exception e) {
                Slog.d(TAG, "Exception:" + e);
            }
        }
    }
}
