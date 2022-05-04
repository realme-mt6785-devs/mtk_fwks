package com.mediatek.server;

import android.content.Context;
import android.os.IBinder;
import android.util.Slog;
import com.android.server.SystemService;
import com.mediatek.net.connectivity.MtkIpConnectivityMetrics;
import com.mediatek.net.networkstats.MtkTestSimHandler;
import com.mediatek.net.tethering.TestModeTethering;
/* loaded from: classes.dex */
public class MtkConnectivitySupplementalService extends SystemService {
    private static final String TAG = "MtkConnectivitySupplementalService";
    private IBinder mNetworkConstroller;
    private final MtkTestSimHandler mTestSimHandler;
    private final TestModeTethering mTethering;

    public MtkConnectivitySupplementalService(Context context) {
        super(context);
        this.mTethering = new TestModeTethering(context);
        this.mTestSimHandler = new MtkTestSimHandler(context);
    }

    public void onStart() {
        Slog.d(TAG, "onStart");
    }

    public void onBootPhase(int phase) {
        Slog.d(TAG, "onBootPhase: phase=" + phase);
        if (phase == 500) {
            this.mTethering.initialize();
            Slog.d(TAG, "initialize mtkconnmetrics.");
            MtkIpConnectivityMetrics ipConnectivityMetrics = new MtkIpConnectivityMetrics(getContext());
            IBinder mtkIpConnSrv = ipConnectivityMetrics.getMtkIpConnSrv();
            this.mNetworkConstroller = mtkIpConnSrv;
            if (mtkIpConnSrv != null) {
                publishBinderService("mtkconnmetrics", mtkIpConnSrv);
            } else {
                Slog.e(TAG, "initialize mtkconnmetrics error!");
            }
        }
    }
}
