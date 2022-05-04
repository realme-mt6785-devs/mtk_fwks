package com.mediatek.mmsdk;

import android.util.Log;
/* loaded from: classes.dex */
public class CameraEffectStatus {
    private static final boolean DEBUG = true;
    private static final String TAG = "CameraEffectStatus";
    private CameraEffectHalStatus mCurrentStatus = CameraEffectHalStatus.STATUS_UNINITIALIZED;

    /* loaded from: classes.dex */
    public enum CameraEffectHalStatus {
        STATUS_UNINITIALIZED,
        STATUS_INITIALIZED,
        STATUS_CONFINGURED,
        STATUS_RUNNING
    }

    public void setEffectHalStatus(CameraEffectHalStatus status) {
        Log.i(TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "] ,mCurrentStatus = " + this.mCurrentStatus + ",next status = " + status);
        this.mCurrentStatus = status;
    }

    public CameraEffectHalStatus getEffectHalStatus() {
        Log.i(TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "] ,mCurrentStatus = " + this.mCurrentStatus);
        return this.mCurrentStatus;
    }
}
