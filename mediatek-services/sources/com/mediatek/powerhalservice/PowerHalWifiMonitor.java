package com.mediatek.powerhalservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IRemoteCallback;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.UEventObserver;
import android.util.Log;
import com.mediatek.net.connectivity.MtkPacketMessage;
import com.mediatek.powerhalwrapper.PowerHalWrapper;
/* loaded from: classes.dex */
public class PowerHalWifiMonitor {
    private static final String APP_EVENT_BUNDLE_KEY_STATE = "STATE";
    private static final int APP_EVENT_DUPLICATE_PACKET_PREDICTION_BUSY = -1;
    private static final int APP_EVENT_DUPLICATE_PACKET_PREDICTION_OFF = 0;
    private static final int APP_EVENT_DUPLICATE_PACKET_PREDICTION_ON = 1;
    private static final int APP_EVENT_WIFI_UNAVAILABLE = -1;
    private static final int CMD_CALLBACK_APP_EVENT = 0;
    private static final int DRIVER_EVENT_TX_DUP_CERT_CHANGE = 102;
    private static final int DRIVER_EVENT_TX_DUP_OFF = 100;
    private static final int DRIVER_EVENT_TX_DUP_ON = 101;
    private static final int DUPLICATE_PACKET_PREDICTION_BUSY_TIMEOUT = 5;
    private static final String KEY = "code";
    private static final String UEVENT_PATH = "DEVPATH=/devices/virtual/misc/wlan";
    private static MyUEventObserver mUEventObserver;
    private Context mContext;
    private boolean mDppStarted;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mWifiConnected;
    private boolean mWifiEnabled;
    private static PowerHalWifiMonitor sInstance = null;
    private static PowerHalWrapper mPowerHalWrap = null;
    private final String TAG = "PowerHalWifiMonitor";
    private int mDppPowerHdl = 0;
    private final RemoteCallbackList<IRemoteCallback> mListeners = new RemoteCallbackList<>();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.mediatek.powerhalservice.PowerHalWifiMonitor.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PowerHalWifiMonitor powerHalWifiMonitor = PowerHalWifiMonitor.this;
            powerHalWifiMonitor.logd("onReceive action:" + intent.getAction());
            if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                int wifiState = intent.getIntExtra("wifi_state", 4);
                PowerHalWifiMonitor.this.onWifiStateChange(wifiState);
            } else if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                NetworkInfo info = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                PowerHalWifiMonitor.this.onWifiConnectionStateChange(info);
            } else if (intent.getAction().equals("com.mediatek.npp.ev.a")) {
                PowerHalWifiMonitor.this.postStateChange(1);
            } else if (intent.getAction().equals("com.mediatek.npp.ev.b")) {
                PowerHalWifiMonitor.this.postStateChange(0);
            }
        }
    };

    public PowerHalWifiMonitor(Context context) {
        this.mContext = context;
        mPowerHalWrap = PowerHalWrapper.getInstance();
        registerForBroadcast();
        mUEventObserver = new MyUEventObserver();
        initHandlerThread();
        sInstance = this;
    }

    public static PowerHalWifiMonitor getInstance() {
        return sInstance;
    }

    /* loaded from: classes.dex */
    private class MyUEventObserver extends UEventObserver {
        private MyUEventObserver() {
        }

        public void onUEvent(UEventObserver.UEvent event) {
            String value = event.get(PowerHalWifiMonitor.KEY);
            if (value != null) {
                Log.i("PowerHalWifiMonitor", "onUEvent, event value:" + value);
                try {
                    int eventCode = Integer.parseInt(value);
                    Log.d("PowerHalWifiMonitor", "onUevent, event int code:" + eventCode);
                    PowerHalWifiMonitor.this.ueventCallback(eventCode);
                } catch (Exception e) {
                    Log.e("PowerHalWifiMonitor", "DPP event wrong format:" + e.toString());
                }
            } else {
                Log.e("PowerHalWifiMonitor", "onUEvent, value is NULL");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ueventCallback(int event) {
        int dupEvent = event / 100000;
        int timeEvent = event % 100000;
        boolean busy = checkIfDuplicatePacketPredictionBusy(timeEvent);
        Log.i("PowerHalWifiMonitor", "mDppStarted:" + this.mDppStarted + ", mWifiConnected:" + this.mWifiConnected + ", busy:" + busy);
        int i = -1;
        switch (dupEvent) {
            case DRIVER_EVENT_TX_DUP_OFF /* 100 */:
                if (this.mDppStarted && this.mWifiConnected) {
                    if (!busy) {
                        i = 0;
                    }
                    callbackOnStateChanged(i);
                    return;
                }
                return;
            case DRIVER_EVENT_TX_DUP_ON /* 101 */:
                if (this.mDppStarted && this.mWifiConnected) {
                    if (!busy) {
                        i = 1;
                    }
                    callbackOnStateChanged(i);
                    return;
                }
                return;
            case DRIVER_EVENT_TX_DUP_CERT_CHANGE /* 102 */:
                if (!mPowerHalWrap.getRildCap(-1)) {
                    resetDuplicatePacketPrediction();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void startDuplicatePacketPrediction() {
        logd("startDuplicatePacketPrediction() mDppStarted:" + this.mDppStarted + ", mWifiEnabled:" + this.mWifiEnabled);
        int[] rscList = {41959680, 1, 54525952, 2};
        if (!this.mDppStarted) {
            this.mDppStarted = true;
            if (this.mWifiEnabled) {
                int i = this.mDppPowerHdl;
                if (i != 0) {
                    mPowerHalWrap.perfLockRelease(i);
                    this.mDppPowerHdl = 0;
                }
                this.mDppPowerHdl = mPowerHalWrap.perfLockAcquire(this.mDppPowerHdl, 0, rscList);
            }
            if (!this.mWifiEnabled || !this.mWifiConnected) {
                postStateChange(-1);
            }
        }
    }

    public void stopDuplicatePacketPrediction() {
        logd("stopDuplicatePacketPrediction() mDppStarted:" + this.mDppStarted + ", mWifiEnabled:" + this.mWifiEnabled);
        if (this.mDppStarted) {
            this.mDppStarted = false;
            if (this.mWifiEnabled) {
                mPowerHalWrap.perfLockRelease(this.mDppPowerHdl);
                this.mDppPowerHdl = 0;
            }
        }
    }

    public boolean isDupPacketPredictionStarted() {
        logd("isDupPacketPredictionStarted() mDppStarted:" + this.mDppStarted);
        return this.mDppStarted;
    }

    public boolean registerDuplicatePacketPredictionEvent(IRemoteCallback listener) {
        if (listener == null) {
            return false;
        }
        synchronized (this.mListeners) {
            logd("registerDuplicatePacketPredictionEvent() " + listener.getClass().toString());
            this.mListeners.register(listener);
        }
        mUEventObserver.startObserving(UEVENT_PATH);
        return true;
    }

    public boolean unregisterDuplicatePacketPredictionEvent(IRemoteCallback listener) {
        if (listener == null) {
            return false;
        }
        synchronized (this.mListeners) {
            logd("unregisterDuplicatePacketPredictionEvent() " + listener.getClass().toString());
            this.mListeners.unregister(listener);
        }
        mUEventObserver.stopObserving();
        return true;
    }

    private void registerForBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("com.mediatek.npp.ev.a");
        filter.addAction("com.mediatek.npp.ev.b");
        this.mContext.registerReceiver(this.mReceiver, filter);
    }

    private void resetDuplicatePacketPrediction() {
        logd("resetDuplicatePacketPrediction(), mDppStarted:" + this.mDppStarted);
        this.mDppStarted = false;
        mPowerHalWrap.setSysInfo(8, "DELETE_ALL");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postStateChange(int event) {
        logd("postStateChange(), event:" + event);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(0, event, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackOnStateChanged(int event) {
        synchronized (this.mListeners) {
            int i = this.mListeners.beginBroadcast();
            logOut("callbackOnStateChanged() " + i + " event:" + event);
            Bundle bundle = new Bundle();
            bundle.putInt(APP_EVENT_BUNDLE_KEY_STATE, event);
            while (i > 0) {
                i--;
                try {
                    this.mListeners.getBroadcastItem(i).sendResult(bundle);
                } catch (RemoteException e) {
                }
            }
            this.mListeners.finishBroadcast();
        }
    }

    private void reStartWifiDriver() {
        logd("reStartWifiDriver()");
        int[] rscList = {41959680, 1, 54525952, 2};
        int i = this.mDppPowerHdl;
        if (i != 0) {
            mPowerHalWrap.perfLockRelease(i);
            this.mDppPowerHdl = 0;
        }
        this.mDppPowerHdl = mPowerHalWrap.perfLockAcquire(this.mDppPowerHdl, 0, rscList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWifiStateChange(int wifiState) {
        boolean wifiEnable = wifiState == 3;
        if (this.mWifiEnabled != wifiEnable) {
            this.mWifiEnabled = wifiEnable;
            logd("onWifiStateChange(), mWifiEnabled:" + this.mWifiEnabled + ", mDppStarted:" + this.mDppStarted);
            if (this.mWifiEnabled) {
                if (this.mDppStarted) {
                    reStartWifiDriver();
                }
            } else if (this.mDppStarted) {
                postStateChange(-1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWifiConnectionStateChange(NetworkInfo info) {
        boolean wifiConnected = info.isConnected();
        if (this.mWifiConnected != wifiConnected) {
            this.mWifiConnected = wifiConnected;
            logd("onWifiConnectionStateChange(), mWifiConnected:" + this.mWifiConnected + ", mDppStarted:" + this.mDppStarted);
            if (!this.mWifiConnected) {
                if (this.mDppStarted) {
                    postStateChange(-1);
                }
            } else if (this.mDppStarted) {
                reStartWifiDriver();
            }
        }
    }

    private boolean checkIfDuplicatePacketPredictionBusy(int timeEvent) {
        long nowMs = System.currentTimeMillis();
        int now = (int) ((((nowMs / 1000) % 60) * 1000) + (nowMs % 1000));
        if (now - timeEvent <= DUPLICATE_PACKET_PREDICTION_BUSY_TIMEOUT) {
            return false;
        }
        logd("checkIfDuplicatePacketPredictionBusy(), now: " + now + ", drv:" + timeEvent);
        return false;
    }

    public void supplicantHalCallback(int event) {
        int dupEvent = event / 100000;
        int timeEvent = event % 100000;
        boolean busy = checkIfDuplicatePacketPredictionBusy(timeEvent);
        int i = -1;
        switch (dupEvent) {
            case DRIVER_EVENT_TX_DUP_OFF /* 100 */:
                if (this.mDppStarted && this.mWifiConnected) {
                    if (!busy) {
                        i = 0;
                    }
                    callbackOnStateChanged(i);
                    return;
                }
                return;
            case DRIVER_EVENT_TX_DUP_ON /* 101 */:
                if (this.mDppStarted && this.mWifiConnected) {
                    if (!busy) {
                        i = 1;
                    }
                    callbackOnStateChanged(i);
                    return;
                }
                return;
            case DRIVER_EVENT_TX_DUP_CERT_CHANGE /* 102 */:
                if (!mPowerHalWrap.getRildCap(-1)) {
                    resetDuplicatePacketPrediction();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void initHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("PowerHalWifiMonitor");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.mediatek.powerhalservice.PowerHalWifiMonitor.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                PowerHalWifiMonitor powerHalWifiMonitor = PowerHalWifiMonitor.this;
                powerHalWifiMonitor.logd("handleMessage: " + messageToString(msg) + " " + msg.arg1);
                switch (msg.what) {
                    case MtkPacketMessage.NF_DROP /* 0 */:
                        PowerHalWifiMonitor.this.callbackOnStateChanged(msg.arg1);
                        return;
                    default:
                        return;
                }
            }

            private String messageToString(Message msg) {
                switch (msg.what) {
                    case MtkPacketMessage.NF_DROP /* 0 */:
                        return "CMD_CALLBACK_APP_EVENT";
                    default:
                        return Integer.toString(msg.what);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logd(String info) {
        Log.d("PowerHalWifiMonitor", info);
    }

    private void logOut(String info) {
        Log.i("NPP", info);
    }
}
