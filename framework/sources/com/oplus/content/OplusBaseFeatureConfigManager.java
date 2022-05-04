package com.oplus.content;

import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;
/* loaded from: classes4.dex */
public abstract class OplusBaseFeatureConfigManager implements IOplusFeatureConfigManager {
    protected String mName;
    protected IBinder mRemote;

    public OplusBaseFeatureConfigManager(String name) {
        this(ServiceManager.getService(name), name);
    }

    private OplusBaseFeatureConfigManager(IBinder remote, String name) {
        this.mRemote = remote;
        this.mName = name;
        if (remote == null) {
            Log.e(OplusBaseFeatureConfigManager.class.getSimpleName(), "remote not published yet!!!", new Throwable());
        }
    }
}
