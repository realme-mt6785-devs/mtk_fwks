package com.oplus.romupdate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Slog;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes4.dex */
public class RomUpdateObserver {
    private static final String BROADCAST_ACTION_ROM_UPDATE_CONFIG_SUCCESS = "oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS";
    private static final String COMPONENT_SAFE_PERMISSION = "oplus.permission.OPLUS_COMPONENT_SAFE";
    private static final String ROM_UPDATE_CONFIG_LIST = "ROM_UPDATE_CONFIG_LIST";
    private static final String TAG = "RomUpdateObserver";
    private static volatile RomUpdateObserver sObserver;
    private Context mContext;
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final Uri CONTENT_URI_WHITE_LIST = Uri.parse("content://com.oplus.romupdate.provider.db/update_list");
    private final HashMap<String, ArrayList<OnReceiveListener>> mRegisterMap = new HashMap<>(16);
    private boolean mInitDone = false;

    /* loaded from: classes4.dex */
    public interface OnReceiveListener {
        void onReceive(Context context);
    }

    private RomUpdateObserver() {
    }

    public static RomUpdateObserver getInstance() {
        if (sObserver == null) {
            synchronized (RomUpdateObserver.class) {
                if (sObserver == null) {
                    sObserver = new RomUpdateObserver();
                }
            }
        }
        return sObserver;
    }

    public void init(Context context) {
        if (!this.mInitDone) {
            Slog.d(TAG, "init");
            this.mInitDone = true;
            this.mContext = context;
            IntentFilter filter = new IntentFilter();
            filter.addAction("oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS");
            this.mContext.registerReceiver(new BroadcastReceiver() { // from class: com.oplus.romupdate.RomUpdateObserver.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(final Context context2, final Intent intent) {
                    new Thread(new Runnable() { // from class: com.oplus.romupdate.RomUpdateObserver.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RomUpdateObserver.this.dealReceiveEvent(context2, intent);
                        }
                    }).start();
                }
            }, filter, "oplus.permission.OPLUS_COMPONENT_SAFE", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealReceiveEvent(Context context, Intent intent) {
        Slog.d(TAG, "dealReceiveEvent");
        ArrayList<String> list = intent.getStringArrayListExtra("ROM_UPDATE_CONFIG_LIST");
        if (list == null) {
            Slog.e(TAG, "list is null");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            synchronized (this.mRegisterMap) {
                if (this.mRegisterMap.containsKey(name)) {
                    Slog.d(TAG, name + " on receive");
                    ArrayList<OnReceiveListener> listeners = this.mRegisterMap.get(name);
                    for (int j = 0; j < listeners.size(); j++) {
                        listeners.get(j).onReceive(context);
                    }
                }
            }
        }
    }

    public void register(String filterName, OnReceiveListener listener) {
        if (TextUtils.isEmpty(filterName)) {
            Slog.e(TAG, "register failed. filterName is null");
        } else if (listener == null) {
            Slog.d(TAG, "register failed. listener is null");
        } else {
            Slog.d(TAG, filterName + " register.");
            synchronized (this.mRegisterMap) {
                if (this.mRegisterMap.containsKey(filterName)) {
                    ArrayList<OnReceiveListener> list = this.mRegisterMap.get(filterName);
                    if (!list.contains(listener)) {
                        list.add(listener);
                    }
                } else {
                    ArrayList<OnReceiveListener> list2 = new ArrayList<>();
                    list2.add(listener);
                    this.mRegisterMap.put(filterName, list2);
                }
            }
        }
    }

    public void unregister(String filterName, OnReceiveListener listener) {
        if (TextUtils.isEmpty(filterName)) {
            Slog.e(TAG, "unregister failed. filterName is null");
        } else if (listener == null) {
            Slog.e(TAG, "unregister failed. listener is null");
        } else {
            Slog.d(TAG, "try to remove " + filterName);
            synchronized (this.mRegisterMap) {
                if (this.mRegisterMap.containsKey(filterName)) {
                    ArrayList<OnReceiveListener> list = this.mRegisterMap.get(filterName);
                    boolean ret = list.remove(listener);
                    Slog.d(TAG, "remove " + filterName + " ret:" + ret);
                    if (list.isEmpty()) {
                        this.mRegisterMap.remove(filterName);
                    }
                } else {
                    Slog.d(TAG, filterName + " not register.");
                }
            }
        }
    }

    public boolean isRegister(String filterName, OnReceiveListener listener) {
        if (TextUtils.isEmpty(filterName)) {
            Slog.e(TAG, "isRegister failed. filterName is null");
            return false;
        } else if (listener == null) {
            Slog.e(TAG, "unregister failed. listener is null");
            return false;
        } else {
            boolean ret = false;
            synchronized (this.mRegisterMap) {
                if (this.mRegisterMap.containsKey(filterName)) {
                    ret = this.mRegisterMap.get(filterName).contains(listener);
                }
            }
            Slog.d(TAG, filterName + " is register : " + ret);
            return ret;
        }
    }
}
