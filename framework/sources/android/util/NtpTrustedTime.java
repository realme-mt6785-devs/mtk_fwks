package android.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.SntpClient;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.R;
import java.util.Objects;
import java.util.function.Supplier;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes3.dex */
public class NtpTrustedTime implements TrustedTime {
    private static final boolean LOGD = false;
    private static final String TAG = "NtpTrustedTime";
    private static NtpTrustedTime sSingleton;
    private final Context mContext;
    private volatile TimeResult mTimeResult;
    public INtpTrustedTimeExt mNtpTrustedTimeExt = (INtpTrustedTimeExt) ExtLoader.type(INtpTrustedTimeExt.class).base(this).create();
    private final Supplier<ConnectivityManager> mConnectivityManagerSupplier = new Supplier<ConnectivityManager>() { // from class: android.util.NtpTrustedTime.1
        private ConnectivityManager mConnectivityManager;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.function.Supplier
        public synchronized ConnectivityManager get() {
            if (this.mConnectivityManager == null) {
                this.mConnectivityManager = (ConnectivityManager) NtpTrustedTime.this.mContext.getSystemService(ConnectivityManager.class);
            }
            return this.mConnectivityManager;
        }
    };

    /* loaded from: classes3.dex */
    public static class TimeResult {
        private final long mCertaintyMillis;
        private final long mElapsedRealtimeMillis;
        private final long mTimeMillis;

        public TimeResult(long timeMillis, long elapsedRealtimeMillis, long certaintyMillis) {
            this.mTimeMillis = timeMillis;
            this.mElapsedRealtimeMillis = elapsedRealtimeMillis;
            this.mCertaintyMillis = certaintyMillis;
        }

        public long getTimeMillis() {
            return this.mTimeMillis;
        }

        public long getElapsedRealtimeMillis() {
            return this.mElapsedRealtimeMillis;
        }

        public long getCertaintyMillis() {
            return this.mCertaintyMillis;
        }

        public long currentTimeMillis() {
            return this.mTimeMillis + getAgeMillis();
        }

        public long getAgeMillis() {
            return SystemClock.elapsedRealtime() - this.mElapsedRealtimeMillis;
        }

        public String toString() {
            return "TimeResult{mTimeMillis=" + this.mTimeMillis + ", mElapsedRealtimeMillis=" + this.mElapsedRealtimeMillis + ", mCertaintyMillis=" + this.mCertaintyMillis + '}';
        }
    }

    private NtpTrustedTime(Context context) {
        Objects.requireNonNull(context);
        Context context2 = context;
        this.mContext = context2;
        this.mNtpTrustedTimeExt.init(context2);
    }

    public static synchronized NtpTrustedTime getInstance(Context context) {
        NtpTrustedTime ntpTrustedTime;
        synchronized (NtpTrustedTime.class) {
            if (sSingleton == null) {
                Context appContext = context.getApplicationContext();
                sSingleton = new NtpTrustedTime(appContext);
            }
            ntpTrustedTime = sSingleton;
        }
        return ntpTrustedTime;
    }

    @Override // android.util.TrustedTime
    public boolean forceRefresh() {
        synchronized (this) {
            NtpConnectionInfo connectionInfo = getNtpConnectionInfo();
            if (connectionInfo == null) {
                return false;
            }
            ConnectivityManager connectivityManager = this.mConnectivityManagerSupplier.get();
            if (connectivityManager == null) {
                return false;
            }
            Network network = connectivityManager.getActiveNetwork();
            NetworkInfo ni = connectivityManager.getNetworkInfo(network);
            if (ni != null && ni.isConnected()) {
                SntpClient client = new SntpClient();
                String serverName = connectionInfo.getServer();
                int timeoutMillis = connectionInfo.getTimeoutMillis();
                boolean refreshResult = this.mNtpTrustedTimeExt.refreshOplusNtpTrustedTime(network, serverName, timeoutMillis);
                if (this.mNtpTrustedTimeExt.isRefreshNtpNeedReturn()) {
                    return refreshResult;
                }
                if (!client.requestTime(serverName, timeoutMillis, network)) {
                    return false;
                }
                long ntpCertainty = client.getRoundTripTime() / 2;
                this.mTimeResult = new TimeResult(client.getNtpTime(), client.getNtpTimeReference(), ntpCertainty);
                return true;
            }
            return false;
        }
    }

    @Override // android.util.TrustedTime
    @Deprecated
    public boolean hasCache() {
        return this.mTimeResult != null;
    }

    @Override // android.util.TrustedTime
    @Deprecated
    public long getCacheAge() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult != null) {
            return SystemClock.elapsedRealtime() - timeResult.getElapsedRealtimeMillis();
        }
        return Long.MAX_VALUE;
    }

    @Override // android.util.TrustedTime
    @Deprecated
    public long currentTimeMillis() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult != null) {
            return timeResult.currentTimeMillis();
        }
        throw new IllegalStateException("Missing authoritative time source");
    }

    @Deprecated
    public long getCachedNtpTime() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult == null) {
            return 0L;
        }
        return timeResult.getTimeMillis();
    }

    @Deprecated
    public long getCachedNtpTimeReference() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult == null) {
            return 0L;
        }
        return timeResult.getElapsedRealtimeMillis();
    }

    public TimeResult getCachedTimeResult() {
        return this.mTimeResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class NtpConnectionInfo {
        private final String mServer;
        private final int mTimeoutMillis;

        NtpConnectionInfo(String server, int timeoutMillis) {
            Objects.requireNonNull(server);
            this.mServer = server;
            this.mTimeoutMillis = timeoutMillis;
        }

        public String getServer() {
            return this.mServer;
        }

        int getTimeoutMillis() {
            return this.mTimeoutMillis;
        }
    }

    private NtpConnectionInfo getNtpConnectionInfo() {
        ContentResolver resolver = this.mContext.getContentResolver();
        Resources res = this.mContext.getResources();
        String defaultServer = res.getString(R.string.config_ntpServer);
        int defaultTimeoutMillis = res.getInteger(R.integer.config_ntpTimeout);
        String secureServer = Settings.Global.getString(resolver, Settings.Global.NTP_SERVER);
        int timeoutMillis = Settings.Global.getInt(resolver, Settings.Global.NTP_TIMEOUT, defaultTimeoutMillis);
        String server = secureServer != null ? secureServer : defaultServer;
        if (TextUtils.isEmpty(server)) {
            return null;
        }
        return new NtpConnectionInfo(server, timeoutMillis);
    }
}
