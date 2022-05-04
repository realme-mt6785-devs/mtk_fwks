package com.android.server;
/* loaded from: classes4.dex */
public interface AppStateTracker {
    public static final String TAG = "AppStateTracker";

    /* loaded from: classes4.dex */
    public interface ServiceStateListener {
        void stopForegroundServicesForUidPackage(int i, String str);
    }

    void addServiceStateListener(ServiceStateListener serviceStateListener);
}
