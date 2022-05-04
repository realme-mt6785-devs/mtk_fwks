package com.mediatek.mmsdk;
/* loaded from: classes.dex */
public class CameraEffectHalRuntimeException extends RuntimeException {
    private Throwable mCause;
    private String mMessage;
    private final int mReason;

    public final int getReason() {
        return this.mReason;
    }

    public CameraEffectHalRuntimeException(int problem) {
        this.mReason = problem;
    }

    public CameraEffectHalRuntimeException(int problem, String msg) {
        super(msg);
        this.mReason = problem;
        this.mMessage = msg;
    }

    public CameraEffectHalRuntimeException(int problem, String msg, Throwable throwable) {
        super(msg, throwable);
        this.mReason = problem;
        this.mMessage = msg;
        this.mCause = throwable;
    }

    public CameraEffectHalRuntimeException(int problem, Throwable cause) {
        super(cause);
        this.mReason = problem;
        this.mCause = cause;
    }

    public CameraEffectHalException asChecked() {
        CameraEffectHalException e;
        String str = this.mMessage;
        if (str != null && this.mCause != null) {
            e = new CameraEffectHalException(this.mReason, this.mMessage, this.mCause);
        } else if (str != null) {
            e = new CameraEffectHalException(this.mReason, this.mMessage);
        } else if (this.mCause != null) {
            e = new CameraEffectHalException(this.mReason, this.mCause);
        } else {
            e = new CameraEffectHalException(this.mReason);
        }
        e.setStackTrace(getStackTrace());
        return e;
    }
}
