package com.oplus.content;

import android.os.Debug;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import com.oplus.content.IOplusFeatureObserver;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusFeatureConfigManager extends OplusBaseFeatureConfigManager implements IOplusFeatureConfigList {
    private static final int STACK_SIZE = 6;
    private static final String TAG = "OplusFeatureConfigManager";
    private static volatile OplusFeatureConfigManager sInstance = null;
    private final ArrayMap<OnFeatureObserver, IOplusFeatureObserver> mFeatureObservers = new ArrayMap<>();
    private OplusFeatureCache mCache = new OplusFeatureCache();

    /* loaded from: classes4.dex */
    public interface OnFeatureObserver {
        void onFeatureUpdate(List<String> list);
    }

    private OplusFeatureConfigManager() {
        super("package");
    }

    @Deprecated
    public static OplusFeatureConfigManager getInstacne() {
        if (sInstance == null) {
            synchronized (OplusFeatureConfigManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusFeatureConfigManager();
                }
            }
        } else if (sInstance.mRemote == null) {
            IBinder binder = ServiceManager.getService(sInstance.mName);
            if (binder == null) {
                Log.e(TAG, "remote is still null");
            } else {
                Log.e(TAG, "remote is not null, update remote");
                sInstance.mRemote = binder;
            }
        }
        return sInstance;
    }

    public boolean isRemoteReady() {
        return this.mRemote != null;
    }

    public static OplusFeatureConfigManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusFeatureConfigManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusFeatureConfigManager();
                }
            }
        } else if (sInstance.mRemote == null) {
            IBinder binder = ServiceManager.getService(sInstance.mName);
            if (binder == null) {
                Log.e(TAG, "remote is still null");
            } else {
                Log.e(TAG, "remote is not null, update remote");
                sInstance.mRemote = binder;
            }
        }
        return sInstance;
    }

    public boolean hasFeatureIPC(String featureName) throws RemoteException {
        if (this.mRemote == null) {
            Log.e(TAG, "hasFeatureIPC " + featureName + " failed , pms not initialized." + Debug.getCallers(6));
            return false;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(featureName);
            this.mRemote.transact(20002, data, reply, 0);
            reply.readException();
            boolean hasFeature = Boolean.valueOf(reply.readString()).booleanValue();
            return hasFeature;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // com.oplus.content.IOplusFeatureConfigManager
    public boolean hasFeature(String name) {
        return this.mCache.query(name);
    }

    @Override // com.oplus.content.IOplusFeatureConfigManager
    public boolean enableFeature(String featureName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(featureName);
            this.mRemote.transact(20003, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // com.oplus.content.IOplusFeatureConfigManager
    public boolean disableFeature(String featureName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(featureName);
            this.mRemote.transact(IOplusFeatureConfigManager.DISABLE_FEATURE_CONFIG, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // com.oplus.content.IOplusFeatureConfigManager
    public void notifyFeaturesUpdate(String action, String actionValue) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(action);
            data.writeString(actionValue);
            this.mRemote.transact(IOplusFeatureConfigManager.NOTIFY_FEATURE_CONFIG_UPDATE, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean registerFeatureObserver(List<String> features, OnFeatureObserver observer) {
        if (observer == null) {
            Log.e(TAG, " registerFeatureObserver null observer");
            return false;
        }
        synchronized (this.mFeatureObservers) {
            if (this.mFeatureObservers.get(observer) != null) {
                Log.e(TAG, "already regiter before");
                return false;
            }
            OnFeatureObserverDelegate delegate = new OnFeatureObserverDelegate(observer);
            try {
                boolean result = registerFeatureObserverInner(features, delegate);
                if (result) {
                    this.mFeatureObservers.put(observer, delegate);
                }
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, "registerFeatureObserver failed!!");
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean unregisterFeatureObserver(OnFeatureObserver observer) {
        if (observer == null) {
            Log.i(TAG, "unregisterFeatureObserver null observer");
            return false;
        }
        synchronized (this.mFeatureObservers) {
            IOplusFeatureObserver delegate = this.mFeatureObservers.get(observer);
            if (delegate != null) {
                try {
                    this.mFeatureObservers.remove(observer);
                    return unregisterFeatureObserverInner(delegate);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    private boolean registerFeatureObserverInner(List<String> features, IOplusFeatureObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeStringList(features);
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusFeatureConfigManager.REGISTER_FEATURE_OBSERVER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    private boolean unregisterFeatureObserverInner(IOplusFeatureObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusFeatureConfigManager.UNREGISTER_FEATURE_OBSERVER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    /* loaded from: classes4.dex */
    private class OnFeatureObserverDelegate extends IOplusFeatureObserver.Stub {
        private final OnFeatureObserver mObserver;

        public OnFeatureObserverDelegate(OnFeatureObserver observer) {
            this.mObserver = observer;
        }

        @Override // com.oplus.content.IOplusFeatureObserver
        public void onFeatureUpdate(List<String> featureList) {
            this.mObserver.onFeatureUpdate(featureList);
        }
    }
}
