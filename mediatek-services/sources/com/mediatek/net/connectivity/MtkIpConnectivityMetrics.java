package com.mediatek.net.connectivity;

import android.content.Context;
import android.net.INetdEventCallback;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseBooleanArray;
import com.mediatek.net.connectivity.IMtkIpConnectivityMetrics;
import com.mediatek.net.connectivity.MtkNetPacketMonitor;
/* loaded from: classes.dex */
public final class MtkIpConnectivityMetrics {
    private static final boolean DBG = true;
    private static final boolean FEATURE_SUPPORTED = true;
    private static final String TAG = "[MDC]MtkIpConnectivityMetrics";
    private Context mContext;
    public Impl mImpl;
    private MtkNetPacketMonitor mMtkPacketMonitor = new MtkNetPacketMonitor();

    public MtkIpConnectivityMetrics(Context ctx) {
        Log.d(TAG, "MtkIpConnectivityMetrics is created:true");
        this.mContext = ctx;
        this.mImpl = new Impl(this.mContext);
        MtkNetPacketMonitor.PacketCallback packetCallback = new MtkNetPacketMonitor.PacketCallback() { // from class: com.mediatek.net.connectivity.MtkIpConnectivityMetrics.1
            @Override // com.mediatek.net.connectivity.MtkNetPacketMonitor.PacketCallback
            public void onPacketEvent(int uid) {
                Log.i(MtkIpConnectivityMetrics.TAG, "onPacketEvent " + uid);
                MtkIpConnectivityMetrics.this.mImpl.onCtaConnectEvent(uid);
            }
        };
        this.mMtkPacketMonitor.setPacketCallback(packetCallback);
    }

    public IBinder getMtkIpConnSrv() {
        return this.mImpl;
    }

    /* loaded from: classes.dex */
    public final class Impl extends IMtkIpConnectivityMetrics.Stub {
        private Context mContext;
        private INetdEventCallback mNetdEventCallback;
        private SparseBooleanArray mUidSocketRules = new SparseBooleanArray();
        final Object mUidSockeRulestLock = new Object();

        public Impl(Context ctx) {
            this.mContext = ctx;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean registerMtkNetdEventCallback(INetdEventCallback callback) {
            if (!isPermissionAllowed()) {
                return false;
            }
            Log.d(MtkIpConnectivityMetrics.TAG, "registerMtkNetdEventCallback");
            this.mNetdEventCallback = callback;
            return true;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean unregisterMtkNetdEventCallback() {
            if (!isPermissionAllowed()) {
                return false;
            }
            Log.d(MtkIpConnectivityMetrics.TAG, "unregisterMtkNetdEventCallback");
            this.mNetdEventCallback = null;
            return true;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean registerMtkSocketEventCallback(INetdEventCallback callback) {
            Log.e(MtkIpConnectivityMetrics.TAG, "registerMtkSocketEventCallback is deprecated!!!");
            return false;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean unregisterMtkSocketEventCallback() {
            Log.e(MtkIpConnectivityMetrics.TAG, "unregisterMtkSocketEventCallback is deprecated!!!");
            return false;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void updateCtaAppStatus(int uid, boolean needNotify) {
            if (isPermissionAllowed()) {
                synchronized (this.mUidSockeRulestLock) {
                    Log.d(MtkIpConnectivityMetrics.TAG, "updateCtaAppStatus:" + uid + ":" + needNotify);
                    this.mUidSocketRules.put(uid, needNotify);
                }
            }
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void setSpeedDownload(int timeoutMs) {
            Log.e(MtkIpConnectivityMetrics.TAG, "setSpeedDownload is deprecated!!!");
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void startMonitorProcessWithUidArray(int[] uidArray) {
            if (uidArray == null || uidArray.length == 0) {
                Log.e(MtkIpConnectivityMetrics.TAG, "startMonitorProcessWithUidArray invalid uid array");
                return;
            }
            for (int uid : uidArray) {
                startMonitorProcessWithUid(uid);
            }
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void startMonitorProcessWithUid(int uid) {
            if (isValidUid(uid) && MtkIpConnectivityMetrics.this.mMtkPacketMonitor != null) {
                MtkIpConnectivityMetrics.this.mMtkPacketMonitor.startMonitorProcessWithUid(uid);
            }
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void stopMonitorProcessWithUidArray(int[] uidArray) {
            if (uidArray == null || uidArray.length == 0) {
                Log.e(MtkIpConnectivityMetrics.TAG, "stopMonitorProcessWithUidArray invalid uid array");
                return;
            }
            for (int uid : uidArray) {
                stopMonitorProcessWithUid(uid);
            }
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void stopMonitorProcessWithUid(int uid) {
            if (isValidUid(uid) && MtkIpConnectivityMetrics.this.mMtkPacketMonitor != null) {
                MtkIpConnectivityMetrics.this.mMtkPacketMonitor.stopMonitorProcessWithUid(uid);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onCtaConnectEvent(int uid) {
            if (this.mNetdEventCallback != null) {
                synchronized (this.mUidSockeRulestLock) {
                    boolean needNotify = this.mUidSocketRules.get(uid, true);
                    Log.d(MtkIpConnectivityMetrics.TAG, "onCtaConnectEvent:" + uid + ":" + needNotify);
                    if (needNotify) {
                        try {
                            this.mNetdEventCallback.onConnectEvent("", 0, 0L, uid);
                        } catch (Exception e) {
                            Log.d(MtkIpConnectivityMetrics.TAG, "onCtaConnectEvent:" + e);
                        }
                    }
                }
            }
        }

        private boolean isValidUid(int uid) {
            return uid > 0;
        }

        private boolean isPermissionAllowed() {
            enforceNetworkMonitorPermission();
            if (Binder.getCallingUid() == 1000) {
                return true;
            }
            Log.d(MtkIpConnectivityMetrics.TAG, "No permission:" + Binder.getCallingUid());
            return false;
        }

        private void enforceNetworkMonitorPermission() {
            int uid = Binder.getCallingUid();
            if (uid != 1000) {
                throw new SecurityException(String.format("Uid %d has no permission to change watchlist setting.", Integer.valueOf(uid)));
            }
        }
    }
}
