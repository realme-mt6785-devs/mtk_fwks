package com.mediatek.mmsdk;

import android.util.AndroidException;
/* loaded from: classes.dex */
public class CameraEffectHalException extends AndroidException {
    public static final int EFFECT_HAL_CLIENT_ERROR = 105;
    public static final int EFFECT_HAL_ERROR = 104;
    public static final int EFFECT_HAL_FACTORY_ERROR = 103;
    public static final int EFFECT_HAL_FEATUREMANAGER_ERROR = 102;
    public static final int EFFECT_HAL_IN_USE = 107;
    public static final int EFFECT_HAL_LISTENER_ERROR = 106;
    public static final int EFFECT_HAL_SERVICE_ERROR = 101;
    public static final int EFFECT_INITIAL_ERROR = 201;
    private final int mReason;

    /* loaded from: classes.dex */
    public enum EffectHalError {
        EFFECT_HAL_SERVICE_ERROR,
        EFFECT_HAL_FEATUREMANAGER_ERROR,
        EFFECT_HAL_FACTORY_ERROR,
        EFFECT_HAL_ERROR,
        EFFECT_HAL_CLIENT_ERROR,
        EFFECT_HAL_LISTENER_ERROR,
        EFFECT_HAL_IN_USE
    }

    /* loaded from: classes.dex */
    public enum EffectHalStatusError {
        EFFECT_INITIAL_ERROR
    }

    public final int getReason() {
        return this.mReason;
    }

    public CameraEffectHalException(int problem) {
        super(getDefaultMessage(problem));
        this.mReason = problem;
    }

    public CameraEffectHalException(int problem, String msg) {
        super(msg);
        this.mReason = problem;
    }

    public CameraEffectHalException(int problem, String msg, Throwable throwable) {
        super(msg, throwable);
        this.mReason = problem;
    }

    public CameraEffectHalException(int problem, Throwable throwable) {
        super(getDefaultMessage(problem), throwable);
        this.mReason = problem;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getDefaultMessage(int problem) {
        switch (problem) {
            case EFFECT_HAL_SERVICE_ERROR /* 101 */:
                break;
            case EFFECT_HAL_FEATUREMANAGER_ERROR /* 102 */:
                break;
            case EFFECT_HAL_FACTORY_ERROR /* 103 */:
                break;
            case EFFECT_HAL_ERROR /* 104 */:
            case 105:
            case EFFECT_HAL_LISTENER_ERROR /* 106 */:
            default:
                return "the problem type not in the camera hal,please add that in CameraEffectHalException ";
            case EFFECT_HAL_IN_USE /* 107 */:
                break;
        }
        return "the problem type not in the camera hal,please add that in CameraEffectHalException ";
    }
}
