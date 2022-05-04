package com.android.internal.telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.UserHandle;
/* loaded from: classes4.dex */
public abstract class PackageChangeReceiver extends BroadcastReceiver {
    private static HandlerThread sHandlerThread;
    static final IntentFilter sPackageIntentFilter;
    Context mRegisteredContext;

    static {
        IntentFilter intentFilter = new IntentFilter();
        sPackageIntentFilter = intentFilter;
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        intentFilter.addAction(Intent.ACTION_QUERY_PACKAGE_RESTART);
        intentFilter.addAction(Intent.ACTION_PACKAGE_RESTARTED);
        intentFilter.addDataScheme("package");
    }

    public void register(Context context, Looper thread, UserHandle user) {
        if (this.mRegisteredContext == null) {
            Handler handler = new Handler(thread == null ? getStaticLooper() : thread);
            Context createContextAsUser = user == null ? context : context.createContextAsUser(user, 0);
            this.mRegisteredContext = createContextAsUser;
            createContextAsUser.registerReceiver(this, sPackageIntentFilter, null, handler);
            return;
        }
        throw new IllegalStateException("Already registered");
    }

    public void unregister() {
        Context context = this.mRegisteredContext;
        if (context != null) {
            context.unregisterReceiver(this);
            this.mRegisteredContext = null;
            return;
        }
        throw new IllegalStateException("Not registered");
    }

    private static synchronized Looper getStaticLooper() {
        Looper looper;
        synchronized (PackageChangeReceiver.class) {
            if (sHandlerThread == null) {
                HandlerThread handlerThread = new HandlerThread(PackageChangeReceiver.class.getSimpleName());
                sHandlerThread = handlerThread;
                handlerThread.start();
            }
            looper = sHandlerThread.getLooper();
        }
        return looper;
    }

    public void onPackageAdded(String packageName) {
    }

    public void onPackageRemoved(String packageName) {
    }

    public void onPackageUpdateFinished(String packageName) {
    }

    public void onPackageModified(String packageName) {
    }

    public void onHandleForceStop(String[] packages, boolean doit) {
    }

    public void onPackageDisappeared() {
    }

    public void onPackageAppeared() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            String pkg = getPackageName(intent);
            if (pkg != null) {
                if (intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)) {
                    onPackageUpdateFinished(pkg);
                    onPackageModified(pkg);
                } else {
                    onPackageAdded(pkg);
                }
                onPackageAppeared();
            }
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            String pkg2 = getPackageName(intent);
            if (pkg2 != null) {
                if (!intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)) {
                    onPackageRemoved(pkg2);
                }
                onPackageDisappeared();
            }
        } else if (Intent.ACTION_PACKAGE_CHANGED.equals(action)) {
            String pkg3 = getPackageName(intent);
            if (pkg3 != null) {
                onPackageModified(pkg3);
            }
        } else if (Intent.ACTION_QUERY_PACKAGE_RESTART.equals(action)) {
            onHandleForceStop(intent.getStringArrayExtra(Intent.EXTRA_PACKAGES), false);
        } else if (Intent.ACTION_PACKAGE_RESTARTED.equals(action)) {
            String[] disappearingPackages = {getPackageName(intent)};
            onHandleForceStop(disappearingPackages, true);
        }
    }

    String getPackageName(Intent intent) {
        Uri uri = intent.getData();
        if (uri == null) {
            return null;
        }
        String pkg = uri.getSchemeSpecificPart();
        return pkg;
    }
}
