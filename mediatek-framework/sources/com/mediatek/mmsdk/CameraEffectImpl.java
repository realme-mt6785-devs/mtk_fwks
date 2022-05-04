package com.mediatek.mmsdk;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import com.mediatek.mmsdk.CameraEffect;
import com.mediatek.mmsdk.CameraEffectSession;
import com.mediatek.mmsdk.CameraEffectStatus;
import com.mediatek.mmsdk.IEffectListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class CameraEffectImpl extends CameraEffect {
    private static final boolean DEBUG = true;
    private static final int SUCCESS_VALUE = 0;
    private static final String TAG = "CameraEffectImpl";
    private BaseParameters mBaseParameters;
    private CameraEffectSessionImpl mCurrentSession;
    private Handler mEffectHalHandler;
    private CameraEffect.StateCallback mEffectStateCallback;
    private IEffectHalClient mIEffectHalClient;
    private DeviceStateCallback mSessionStateCallback;
    private boolean mInError = false;
    private final Object mInterfaceLock = new Object();
    private final Runnable mCallOnActive = new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraEffectImpl.this.mInterfaceLock) {
                if (CameraEffectImpl.this.mIEffectHalClient != null) {
                    DeviceStateCallback stateCallback2 = CameraEffectImpl.this.mSessionStateCallback;
                    if (stateCallback2 != null) {
                        stateCallback2.onActive(CameraEffectImpl.this);
                    }
                }
            }
        }
    };
    private final Runnable mCallOnBusy = new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.3
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraEffectImpl.this.mInterfaceLock) {
                if (CameraEffectImpl.this.mIEffectHalClient != null) {
                    DeviceStateCallback sessionCallback = CameraEffectImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onBusy(CameraEffectImpl.this);
                    }
                }
            }
        }
    };
    private final Runnable mCallOnClosed = new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.4
        private boolean isClosedOnce = false;

        @Override // java.lang.Runnable
        public void run() {
            DeviceStateCallback sessionCallback;
            if (!this.isClosedOnce) {
                synchronized (CameraEffectImpl.this.mInterfaceLock) {
                    sessionCallback = CameraEffectImpl.this.mSessionStateCallback;
                }
                if (sessionCallback != null) {
                    sessionCallback.onClosed(CameraEffectImpl.this);
                }
                CameraEffectImpl.this.mEffectStateCallback.onClosed(CameraEffectImpl.this);
                this.isClosedOnce = true;
                return;
            }
            throw new AssertionError("Don't post #onClosed more than once");
        }
    };
    private final Runnable mCallOnIdle = new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.5
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraEffectImpl.this.mInterfaceLock) {
                if (CameraEffectImpl.this.mIEffectHalClient != null) {
                    DeviceStateCallback sessionCallback = CameraEffectImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onIdle(CameraEffectImpl.this);
                    }
                }
            }
        }
    };
    private SparseArray<CaptureCallbackHolder> mCaptureCallbackHolderMap = new SparseArray<>();
    private long mCurrentStartId = -1;
    private CameraEffectStatus mEffectHalStatus = new CameraEffectStatus();

    public CameraEffectImpl(CameraEffect.StateCallback callback, Handler handler) {
        this.mEffectStateCallback = callback;
        this.mEffectHalHandler = handler;
    }

    @Override // com.mediatek.mmsdk.CameraEffect
    public CameraEffectSession createCaptureSession(List<Surface> outputs, List<BaseParameters> surfaceParameters, CameraEffectSession.SessionStateCallback callback, Handler handler) throws CameraEffectHalException {
        CameraEffectHalException pendingException;
        boolean configureSuccess;
        Log.i(TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]");
        checkIfCameraClosedOrInError();
        if (outputs != null) {
            Handler handler2 = checkHandler(handler);
            CameraEffectSessionImpl cameraEffectSessionImpl = this.mCurrentSession;
            if (cameraEffectSessionImpl != null) {
                cameraEffectSessionImpl.replaceSessionClose();
            }
            try {
                configureSuccess = configureOutputs(outputs, surfaceParameters);
                pendingException = null;
            } catch (CameraEffectHalException e) {
                configureSuccess = false;
                Log.v(TAG, "createCaptureSession- failed with exception ", e);
                pendingException = e;
            }
            CameraEffectSessionImpl newSessionImpl = new CameraEffectSessionImpl(callback, handler2, this, this.mEffectHalHandler, configureSuccess);
            this.mCurrentSession = newSessionImpl;
            if (pendingException == null) {
                this.mSessionStateCallback = newSessionImpl.getDeviceStateCallback();
                this.mEffectHalHandler.post(this.mCallOnIdle);
                return this.mCurrentSession;
            }
            throw pendingException;
        }
        throw new IllegalArgumentException("createEffectSession: the outputSurface must not be null");
    }

    @Override // com.mediatek.mmsdk.CameraEffect
    public void setParamters(BaseParameters baseParameters) {
        try {
            this.mIEffectHalClient.setParameters(baseParameters);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during setParameters [BaseParameters]", e);
        }
    }

    @Override // com.mediatek.mmsdk.CameraEffect
    public List<Surface> getInputSurface() {
        Log.d(TAG, "[getInputSurface],current status = " + this.mEffectHalStatus.getEffectHalStatus());
        List<Surface> surface = new ArrayList<>();
        try {
            this.mIEffectHalClient.configure();
            this.mIEffectHalClient.prepare();
            this.mIEffectHalClient.getInputSurfaces(surface);
            this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during configure or prepare or getInputSurfaces", e);
        }
        return surface;
    }

    @Override // com.mediatek.mmsdk.CameraEffect
    public List<BaseParameters> getCaputreRequirement(BaseParameters parameters) {
        int getRequirementValue = -1;
        List<BaseParameters> requireParameters = new ArrayList<>();
        CameraEffectStatus.CameraEffectHalStatus currentStatus = this.mEffectHalStatus.getEffectHalStatus();
        Log.i(TAG, "[getCaputreRequirement] currentStatus = " + currentStatus);
        try {
            if (CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED != currentStatus) {
                this.mIEffectHalClient.configure();
                this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED);
            }
            getRequirementValue = this.mIEffectHalClient.getCaptureRequirement(parameters, requireParameters);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during getCaptureRequirement", e);
        }
        Log.i(TAG, "[getCaputreRequirement] return value from native : " + getRequirementValue + ",parameters = " + requireParameters.toString());
        return requireParameters;
    }

    @Override // com.mediatek.mmsdk.CameraEffect
    public void closeEffect() {
        Log.i(TAG, "[closeEffect] +++,mIEffectHalClient = " + this.mIEffectHalClient);
        abortCapture(null);
        unConfigureEffectHal();
        unInitEffectHal();
        Log.i(TAG, "[closeEffect] ---");
    }

    public EffectHalClientListener getEffectHalListener() {
        return new EffectHalClientListener();
    }

    public void setRemoteCameraEffect(IEffectHalClient client) {
        synchronized (this.mInterfaceLock) {
            if (!this.mInError) {
                this.mIEffectHalClient = client;
                this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_INITIALIZED);
            }
        }
    }

    public void setRemoteCameraEffectFail(CameraEffectHalRuntimeException exception) {
        final int failureCode = 4;
        final boolean failureIsError = true;
        switch (exception.getReason()) {
            case CameraEffectHalException.EFFECT_HAL_SERVICE_ERROR /* 101 */:
                failureCode = 3;
                break;
            case CameraEffectHalException.EFFECT_HAL_FEATUREMANAGER_ERROR /* 102 */:
                failureIsError = false;
                break;
            case CameraEffectHalException.EFFECT_HAL_FACTORY_ERROR /* 103 */:
                failureCode = 4;
                break;
            case CameraEffectHalException.EFFECT_HAL_ERROR /* 104 */:
            case 105:
            default:
                Log.wtf(TAG, "Unknown failure in opening camera device: " + exception.getReason());
                break;
            case CameraEffectHalException.EFFECT_HAL_LISTENER_ERROR /* 106 */:
                failureCode = 6;
                break;
            case CameraEffectHalException.EFFECT_HAL_IN_USE /* 107 */:
                failureCode = 1;
                break;
        }
        synchronized (this.mInterfaceLock) {
            this.mInError = true;
            this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_INITIALIZED);
            this.mEffectHalHandler.post(new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (failureIsError) {
                        CameraEffectImpl.this.mEffectStateCallback.onError(CameraEffectImpl.this, failureCode);
                    } else {
                        CameraEffectImpl.this.mEffectStateCallback.onDisconnected(CameraEffectImpl.this);
                    }
                }
            });
        }
    }

    public boolean configureOutputs(List<Surface> outputs, List<BaseParameters> surfaceParameters) throws CameraEffectHalException {
        Log.i(TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]++++,current status = " + this.mEffectHalStatus.getEffectHalStatus());
        if (outputs == null) {
            outputs = new ArrayList();
        }
        boolean success = false;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            this.mEffectHalHandler.post(this.mCallOnBusy);
            try {
                success = this.mIEffectHalClient.setOutputSurfaces(outputs, surfaceParameters) == 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException during setOutputSurfaces", e);
            }
        }
        Log.i(TAG, "[configureOutputs]----, success = " + success);
        return success;
    }

    public void startEffectHal(Handler handler, CaptureCallback callback) {
        Log.i(TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]++++,status = " + this.mEffectHalStatus.getEffectHalStatus());
        Handler handler2 = checkHandler(handler, callback);
        try {
            if (CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED != this.mEffectHalStatus.getEffectHalStatus()) {
                this.mIEffectHalClient.configure();
                this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED);
            }
            this.mIEffectHalClient.prepare();
            this.mCurrentStartId = this.mIEffectHalClient.start();
            this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_RUNNING);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during prepare or start", e);
        }
        this.mCaptureCallbackHolderMap.put((int) this.mCurrentStartId, new CaptureCallbackHolder(callback, handler2));
        this.mEffectHalHandler.post(this.mCallOnActive);
        Log.i(TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]----, mCurrentStartId = " + this.mCurrentStartId + ",callback = " + callback + ",get the map's callback = " + this.mCaptureCallbackHolderMap.get((int) this.mCurrentStartId));
    }

    public void setFrameParameters(boolean isInput, int index, BaseParameters baseParameters, long timestamp, boolean repeating) {
        try {
            if (isInput) {
                this.mIEffectHalClient.addInputParameter(index, baseParameters, timestamp, repeating);
            } else {
                this.mIEffectHalClient.addOutputParameter(index, baseParameters, timestamp, repeating);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during addInputParameter or addOutputParameter", e);
        }
    }

    public void addOutputParameter(int index, BaseParameters parameter, long timestamp, boolean repeat) {
        try {
            this.mIEffectHalClient.addOutputParameter(index, parameter, timestamp, repeat);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during addOutputParameter", e);
        }
    }

    public void abortCapture(BaseParameters baseParameters) {
        try {
            if (CameraEffectStatus.CameraEffectHalStatus.STATUS_RUNNING == this.mEffectHalStatus.getEffectHalStatus()) {
                this.mIEffectHalClient.abort(this.mBaseParameters);
                this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException during abort", e);
        }
        this.mBaseParameters = baseParameters;
    }

    /* loaded from: classes.dex */
    public class EffectHalClientListener extends IEffectListener.Stub {
        public EffectHalClientListener() {
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onPrepared(IEffectHalClient effect, BaseParameters result) throws RemoteException {
            Log.i(CameraEffectImpl.TAG, "[onPrepared] effect = " + effect + ",result = " + result.flatten());
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onInputFrameProcessed(IEffectHalClient effect, BaseParameters parameter, final BaseParameters partialResult) throws RemoteException {
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]++++");
            final CaptureCallbackHolder callbackHolder = CameraEffectImpl.this.mCurrentStartId > 0 ? (CaptureCallbackHolder) CameraEffectImpl.this.mCaptureCallbackHolderMap.valueAt((int) CameraEffectImpl.this.mCurrentStartId) : null;
            if (!(parameter == null || partialResult == null)) {
                Log.i(CameraEffectImpl.TAG, "[onInputFrameProcessed] effect = " + effect + ",parameter = " + parameter.flatten() + ",partialResult = " + partialResult.flatten() + ",callbackHolder = " + callbackHolder);
            }
            if (callbackHolder != null) {
                callbackHolder.getHandler().post(new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.EffectHalClientListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        callbackHolder.getCaptureCallback().onInputFrameProcessed(CameraEffectImpl.this.mCurrentSession, partialResult, partialResult);
                    }
                });
            }
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]----");
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onOutputFrameProcessed(IEffectHalClient effect, BaseParameters parameter, final BaseParameters partialResult) throws RemoteException {
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2] + "]++++");
            final CaptureCallbackHolder callbackHolder = CameraEffectImpl.this.mCurrentStartId > 0 ? (CaptureCallbackHolder) CameraEffectImpl.this.mCaptureCallbackHolderMap.get((int) CameraEffectImpl.this.mCurrentStartId) : null;
            if (!(parameter == null || partialResult == null)) {
                Log.i(CameraEffectImpl.TAG, "[onOutputFrameProcessed]++++, effect = " + effect + ",parameter = " + parameter.flatten() + ",partialResult = " + partialResult.flatten() + ",mCurrentStartId = " + CameraEffectImpl.this.mCurrentStartId + ",callbackHolder = " + callbackHolder);
            }
            if (callbackHolder != null) {
                callbackHolder.getHandler().post(new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.EffectHalClientListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        callbackHolder.getCaptureCallback().onOutputFrameProcessed(CameraEffectImpl.this.mCurrentSession, partialResult, partialResult);
                    }
                });
            }
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2] + "]----");
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onCompleted(IEffectHalClient effect, final BaseParameters partialResult, long uid) throws RemoteException {
            final CaptureCallbackHolder callbackHolder;
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]++++");
            final int compleateId = (int) uid;
            if (compleateId > 0) {
                callbackHolder = (CaptureCallbackHolder) CameraEffectImpl.this.mCaptureCallbackHolderMap.get(compleateId);
            } else {
                callbackHolder = null;
            }
            if (partialResult != null) {
                Log.i(CameraEffectImpl.TAG, "[onCompleted]++++, effect = ,partialResult = " + partialResult.flatten() + ",uid = " + uid + ",compleateId = " + compleateId + ",mCurrentStartId = " + CameraEffectImpl.this.mCurrentStartId + ",callbackHolder = " + callbackHolder);
            }
            if (callbackHolder != null) {
                callbackHolder.getHandler().post(new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.EffectHalClientListener.3
                    @Override // java.lang.Runnable
                    public void run() {
                        callbackHolder.getCaptureCallback().onCaptureSequenceCompleted(CameraEffectImpl.this.mCurrentSession, partialResult, compleateId);
                    }
                });
            }
            CameraEffectImpl.this.mIEffectHalClient.abort(null);
            CameraEffectImpl.this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED);
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "]----");
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onAborted(IEffectHalClient effect, final BaseParameters result) throws RemoteException {
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2] + "]++++");
            final CaptureCallbackHolder callbackHolder = CameraEffectImpl.this.mCurrentStartId > 0 ? (CaptureCallbackHolder) CameraEffectImpl.this.mCaptureCallbackHolderMap.get((int) CameraEffectImpl.this.mCurrentStartId) : null;
            if (result != null) {
                Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "] ++++,effect = " + effect + ",result = " + result.flatten());
            }
            if (callbackHolder != null) {
                callbackHolder.getHandler().post(new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.EffectHalClientListener.4
                    @Override // java.lang.Runnable
                    public void run() {
                        callbackHolder.getCaptureCallback().onCaptureSequenceAborted(CameraEffectImpl.this.mCurrentSession, result);
                    }
                });
            }
            CameraEffectImpl.this.mCaptureCallbackHolderMap.removeAt((int) CameraEffectImpl.this.mCurrentStartId);
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "] ----");
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onFailed(IEffectHalClient effect, final BaseParameters result) throws RemoteException {
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2] + "]++++");
            final CaptureCallbackHolder callbackHolder = CameraEffectImpl.this.mCurrentStartId > 0 ? (CaptureCallbackHolder) CameraEffectImpl.this.mCaptureCallbackHolderMap.get((int) CameraEffectImpl.this.mCurrentStartId) : null;
            if (result != null) {
                Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "] ++++,effect = " + effect + ",result = " + result.flatten());
            }
            if (callbackHolder != null) {
                callbackHolder.getHandler().post(new Runnable() { // from class: com.mediatek.mmsdk.CameraEffectImpl.EffectHalClientListener.5
                    @Override // java.lang.Runnable
                    public void run() {
                        callbackHolder.getCaptureCallback().onCaptureFailed(CameraEffectImpl.this.mCurrentSession, result);
                    }
                });
            }
            CameraEffectImpl.this.mCaptureCallbackHolderMap.removeAt((int) CameraEffectImpl.this.mCurrentStartId);
            Log.i(CameraEffectImpl.TAG, "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "] ----");
        }
    }

    @Override // com.mediatek.mmsdk.CameraEffect, java.lang.AutoCloseable
    public void close() {
        Log.i(TAG, "[close]");
        if (this.mIEffectHalClient != null || this.mInError) {
            this.mEffectHalHandler.post(this.mCallOnClosed);
        }
        this.mIEffectHalClient = null;
        this.mInError = false;
    }

    public int setFrameSyncMode(boolean isInput, int index, boolean sync) {
        int status_t = -1;
        try {
            if (isInput) {
                status_t = this.mIEffectHalClient.setInputsyncMode(index, sync);
            } else {
                status_t = this.mIEffectHalClient.setOutputsyncMode(index, sync);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "[setFrameSyncMode] status_t = " + status_t + ",isInput = " + isInput);
        return status_t;
    }

    public int setOutputsyncMode(int index, boolean sync) {
        int status_t = -1;
        try {
            status_t = this.mIEffectHalClient.setOutputsyncMode(index, sync);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "[setOutputsyncMode] status_t = " + status_t);
        return status_t;
    }

    public boolean getFrameSyncMode(boolean isInput, int index) {
        boolean value = false;
        try {
            value = this.mIEffectHalClient.getInputsyncMode(index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "[getInputsyncMode] value = " + value);
        return value;
    }

    public boolean getOutputsyncMode(int index) {
        boolean value = false;
        try {
            value = this.mIEffectHalClient.getOutputsyncMode(index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "[getOutputsyncMode] value = " + value);
        return value;
    }

    /* loaded from: classes.dex */
    public static abstract class CaptureCallback {
        public void onInputFrameProcessed(CameraEffectSession session, BaseParameters parameter, BaseParameters partialResult) {
        }

        public void onOutputFrameProcessed(CameraEffectSession session, BaseParameters parameter, BaseParameters partialResult) {
        }

        public void onCaptureSequenceCompleted(CameraEffectSession session, BaseParameters result, long uid) {
        }

        public void onCaptureSequenceAborted(CameraEffectSession session, BaseParameters result) {
        }

        public void onCaptureFailed(CameraEffectSession session, BaseParameters result) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class DeviceStateCallback extends CameraEffect.StateCallback {
        public void onUnconfigured(CameraEffect effect) {
        }

        public void onActive(CameraEffect effect) {
        }

        public void onBusy(CameraEffect effect) {
        }

        public void onIdle(CameraEffect effect) {
        }
    }

    private void checkIfCameraClosedOrInError() throws CameraEffectHalException {
        if (this.mInError) {
            throw new CameraEffectHalRuntimeException((int) CameraEffectHalException.EFFECT_HAL_FACTORY_ERROR, "The camera device has encountered a serious error");
        } else if (this.mIEffectHalClient == null) {
            throw new IllegalStateException("effect hal client have closed");
        }
    }

    private void unConfigureEffectHal() {
        Log.i(TAG, "[unConfigureEffectHal]");
        if (CameraEffectStatus.CameraEffectHalStatus.STATUS_CONFINGURED == this.mEffectHalStatus.getEffectHalStatus()) {
            try {
                this.mIEffectHalClient.unconfigure();
                this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_INITIALIZED);
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException during unconfigure", e);
            }
        }
    }

    private void unInitEffectHal() {
        Log.i(TAG, "[unInitEffectHal]");
        if (CameraEffectStatus.CameraEffectHalStatus.STATUS_INITIALIZED == this.mEffectHalStatus.getEffectHalStatus()) {
            try {
                this.mIEffectHalClient.uninit();
                this.mEffectHalStatus.setEffectHalStatus(CameraEffectStatus.CameraEffectHalStatus.STATUS_UNINITIALIZED);
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException during uninit", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CaptureCallbackHolder {
        private final CaptureCallback mCaptureCallback;
        private final Handler mHandler;

        CaptureCallbackHolder(CaptureCallback callback, Handler handler) {
            this.mCaptureCallback = callback;
            this.mHandler = handler;
        }

        public CaptureCallback getCaptureCallback() {
            return this.mCaptureCallback;
        }

        public Handler getHandler() {
            return this.mHandler;
        }
    }

    private Handler checkHandler(Handler handler) {
        if (handler != null) {
            return handler;
        }
        Looper looper = Looper.myLooper();
        if (looper != null) {
            return new Handler(looper);
        }
        throw new IllegalArgumentException("No handler given, and current thread has no looper!");
    }

    private <T> Handler checkHandler(Handler handler, T callback) {
        if (callback != null) {
            return checkHandler(handler);
        }
        return handler;
    }
}
