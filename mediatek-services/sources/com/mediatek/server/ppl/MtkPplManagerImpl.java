package com.mediatek.server.ppl;

import android.app.StatusBarManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
/* loaded from: classes.dex */
public class MtkPplManagerImpl extends MtkPplManager {
    private static final String TAG = "MtkPplManager";
    private StatusBarManager mStatusBarManager;
    private boolean mPplStatus = false;
    private final BroadcastReceiver mPPLReceiver = new BroadcastReceiver() { // from class: com.mediatek.server.ppl.MtkPplManagerImpl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MtkPplManagerImpl mtkPplManagerImpl = MtkPplManagerImpl.this;
            mtkPplManagerImpl.pplEnable(context, mtkPplManagerImpl.filterPplAction(intent.getAction()));
        }
    };

    public IntentFilter registerPplIntents() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mediatek.ppl.NOTIFY_LOCK");
        filter.addAction("com.mediatek.ppl.NOTIFY_UNLOCK");
        return filter;
    }

    public int calculateStatusBarStatus(boolean pplStatus) {
        if (pplStatus) {
            return 983040;
        }
        return 0;
    }

    public boolean getPplLockStatus() {
        return this.mPplStatus;
    }

    public boolean filterPplAction(String action) {
        if (action.equals("com.mediatek.ppl.NOTIFY_LOCK")) {
            Log.d(TAG, "filterPplAction, recevier action = " + action);
            this.mPplStatus = true;
        } else if (action.equals("com.mediatek.ppl.NOTIFY_UNLOCK")) {
            Log.d(TAG, "filterPplAction, recevier action = " + action);
            this.mPplStatus = false;
        }
        return this.mPplStatus;
    }

    public void registerPplReceiver(Context context) {
        context.registerReceiver(this.mPPLReceiver, registerPplIntents());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pplEnable(Context context, boolean enable) {
        int what = calculateStatusBarStatus(enable);
        if (this.mStatusBarManager == null) {
            this.mStatusBarManager = (StatusBarManager) context.getSystemService("statusbar");
        }
        this.mStatusBarManager.disable(what);
    }
}
