package com.mediatek.net.networkstats;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.util.Slog;
import com.android.server.LocalServices;
import com.android.server.net.NetworkStatsManagerInternal;
import com.mediatek.net.connectivity.MtkPacketMessage;
/* loaded from: classes.dex */
public class MtkTestSimHandler {
    private static final int SUBSCRIPTION_OR_SIM_CHANGED = 0;
    private static final String TAG = MtkTestSimHandler.class.getSimpleName();
    private Context mContext;
    private InternalHandler mHandler;
    private HandlerThread mHandlerThread;
    private NetworkStatsManagerInternal mNetworkStatsInternal;
    private long mEmGlobalAlert = 2097152;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.mediatek.net.networkstats.MtkTestSimHandler.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            MtkTestSimHandler.this.mHandler.sendEmptyMessage(0);
        }
    };

    public MtkTestSimHandler(Context context) {
        String str = TAG;
        Slog.d(str, "MtkTestSimHandler setting up");
        this.mContext = context;
        NetworkStatsManagerInternal networkStatsManagerInternal = (NetworkStatsManagerInternal) LocalServices.getService(NetworkStatsManagerInternal.class);
        this.mNetworkStatsInternal = networkStatsManagerInternal;
        if (networkStatsManagerInternal == null) {
            Slog.e(str, "No NetworkStatsManagerInternal, MtkNetworkStatsService set up fail !!!");
        } else {
            initDataUsageIntent(context);
        }
    }

    private void initDataUsageIntent(Context context) {
        HandlerThread handlerThread = new HandlerThread("MtkTestSimInternalHandler");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new InternalHandler(this.mHandlerThread.getLooper());
        SubscriptionManager.from(context).addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MtkPacketMessage.NF_DROP /* 0 */:
                    MtkTestSimHandler.this.handleSimChange();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSimChange() {
        boolean isTestSim = isTestSim();
        long j = Settings.Global.getLong(this.mContext.getContentResolver(), "netstats_global_alert_bytes", 0L);
        this.mEmGlobalAlert = j;
        if (isTestSim) {
            if (j != 2251799813685248L) {
                Settings.Global.putLong(this.mContext.getContentResolver(), "netstats_global_alert_bytes", 2251799813685248L);
                this.mNetworkStatsInternal.advisePersistThreshold(9223372036854775L);
                Slog.d(TAG, "Configure for test sim with 2TB");
            }
        } else if (j == 2251799813685248L) {
            Settings.Global.putLong(this.mContext.getContentResolver(), "netstats_global_alert_bytes", 2097152L);
            this.mNetworkStatsInternal.advisePersistThreshold(9223372036854775L);
            Slog.d(TAG, "Restore for test sim with 2MB");
        }
    }

    public static boolean isTestSim() {
        boolean isTestSim = SystemProperties.get("vendor.gsm.sim.ril.testsim").equals("1") || SystemProperties.get("vendor.gsm.sim.ril.testsim.2").equals("1") || SystemProperties.get("vendor.gsm.sim.ril.testsim.3").equals("1") || SystemProperties.get("vendor.gsm.sim.ril.testsim.4").equals("1");
        return isTestSim;
    }
}
