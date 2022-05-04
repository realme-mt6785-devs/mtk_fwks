package com.mediatek.view.impl;

import android.content.pm.IPackageManager;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.Log;
import com.mediatek.appresolutiontuner.ResolutionTunerAppList;
import com.mediatek.view.SurfaceExt;
/* loaded from: classes.dex */
public class SurfaceExtimpl extends SurfaceExt {
    private static final String TAG = "SurfaceExt";
    private String mPackageName;
    private static final boolean ENABLE_WHITE_LIST = SystemProperties.getBoolean("debug.enable.whitelist", (boolean) ENABLE_WHITE_LIST);
    private static final boolean ENABLE_WHITE_LIST = SystemProperties.getBoolean("debug.enable.whitelist", (boolean) ENABLE_WHITE_LIST);
    private static final int ENABLE_RESOLUTION_TUNING = SystemProperties.getInt("ro.vendor.app_resolution_tuner", 0);
    private static final String[] WHITE_LIST = {"com.tencent.qqlive"};
    private static ResolutionTunerAppList mApplist = null;
    private boolean mIsContainPackageName = ENABLE_WHITE_LIST;
    private float mXScaleValue = 1.0f;
    private float mYScaleValue = 1.0f;

    public boolean isInWhiteList() {
        if (ENABLE_WHITE_LIST) {
            return true;
        }
        String packageName = getCallerProcessName();
        String[] strArr = WHITE_LIST;
        if (strArr == null || packageName == null) {
            return ENABLE_WHITE_LIST;
        }
        for (String item : strArr) {
            if (item.equals(packageName)) {
                return true;
            }
        }
        return ENABLE_WHITE_LIST;
    }

    public void initResolutionTunner() {
        if (ENABLE_RESOLUTION_TUNING == 1 && mApplist == null) {
            this.mPackageName = getCallerProcessName();
            ResolutionTunerAppList tunerList = getTunerList();
            mApplist = tunerList;
            this.mIsContainPackageName = tunerList.isScaledBySurfaceView(this.mPackageName);
            if (mApplist.getScaleWidth(this.mPackageName) == 0.0f || mApplist.getScaleHeight(this.mPackageName) == 0.0f) {
                float scaleValue = mApplist.getScaleValue(this.mPackageName);
                this.mXScaleValue = scaleValue;
                this.mYScaleValue = scaleValue;
            } else {
                this.mXScaleValue = mApplist.getScaleWidth(this.mPackageName);
                this.mYScaleValue = mApplist.getScaleHeight(this.mPackageName);
            }
            Log.d(TAG, "initResolutionTunner, mPackageName:" + this.mPackageName + ",mContainPackageName:" + this.mIsContainPackageName + "mXScaleValue:" + this.mXScaleValue + ",mYScaleValue:" + this.mYScaleValue);
        }
    }

    public boolean isResolutionTuningPackage() {
        return this.mIsContainPackageName;
    }

    public float getXScale() {
        return this.mXScaleValue;
    }

    public float getYScale() {
        return this.mYScaleValue;
    }

    private ResolutionTunerAppList getTunerList() {
        ResolutionTunerAppList applist = ResolutionTunerAppList.getInstance();
        applist.loadTunerAppList();
        return applist;
    }

    private String getCallerProcessName() {
        int binderuid = Binder.getCallingUid();
        IPackageManager pm = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        if (pm == null) {
            return null;
        }
        try {
            String callingApp = pm.getNameForUid(binderuid);
            return callingApp;
        } catch (RemoteException e) {
            Log.e(TAG, "getCallerProcessName exception :" + e);
            return null;
        }
    }
}
