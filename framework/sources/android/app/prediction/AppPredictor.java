package android.app.prediction;

import android.annotation.SystemApi;
import android.app.prediction.AppPredictor;
import android.app.prediction.IPredictionCallback;
import android.app.prediction.IPredictionManager;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.SettingsStringUtil;
import android.util.ArrayMap;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
@SystemApi
/* loaded from: classes.dex */
public final class AppPredictor {
    private static final String TAG = AppPredictor.class.getSimpleName();
    private final IPredictionManager mPredictionManager;
    private final AppPredictionSessionId mSessionId;
    private final IBinder mToken;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mIsClosed = new AtomicBoolean(false);
    private final ArrayMap<Callback, CallbackWrapper> mRegisteredCallbacks = new ArrayMap<>();

    /* loaded from: classes.dex */
    public interface Callback {
        void onTargetsAvailable(List<AppTarget> list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppPredictor(Context context, AppPredictionContext predictionContext) {
        Binder binder = new Binder();
        this.mToken = binder;
        IBinder b = ServiceManager.getService(Context.APP_PREDICTION_SERVICE);
        IPredictionManager asInterface = IPredictionManager.Stub.asInterface(b);
        this.mPredictionManager = asInterface;
        AppPredictionSessionId appPredictionSessionId = new AppPredictionSessionId(context.getPackageName() + SettingsStringUtil.DELIMITER + UUID.randomUUID().toString(), context.getUserId());
        this.mSessionId = appPredictionSessionId;
        try {
            asInterface.createPredictionSession(predictionContext, appPredictionSessionId, binder);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to create predictor", e);
            e.rethrowAsRuntimeException();
        }
        this.mCloseGuard.open("close");
    }

    public void notifyAppTargetEvent(AppTargetEvent event) {
        if (!this.mIsClosed.get()) {
            try {
                this.mPredictionManager.notifyAppTargetEvent(this.mSessionId, event);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to notify app target event", e);
                e.rethrowAsRuntimeException();
            }
        } else {
            throw new IllegalStateException("This client has already been destroyed.");
        }
    }

    public void notifyLaunchLocationShown(String launchLocation, List<AppTargetId> targetIds) {
        if (!this.mIsClosed.get()) {
            try {
                this.mPredictionManager.notifyLaunchLocationShown(this.mSessionId, launchLocation, new ParceledListSlice(targetIds));
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to notify location shown event", e);
                e.rethrowAsRuntimeException();
            }
        } else {
            throw new IllegalStateException("This client has already been destroyed.");
        }
    }

    public void registerPredictionUpdates(Executor callbackExecutor, final Callback callback) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        } else if (!this.mRegisteredCallbacks.containsKey(callback)) {
            try {
                Objects.requireNonNull(callback);
                CallbackWrapper callbackWrapper = new CallbackWrapper(callbackExecutor, new Consumer() { // from class: android.app.prediction.AppPredictor$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AppPredictor.Callback.this.onTargetsAvailable((List) obj);
                    }
                });
                this.mPredictionManager.registerPredictionUpdates(this.mSessionId, callbackWrapper);
                this.mRegisteredCallbacks.put(callback, callbackWrapper);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to register for prediction updates", e);
                e.rethrowAsRuntimeException();
            }
        }
    }

    public void unregisterPredictionUpdates(Callback callback) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        } else if (this.mRegisteredCallbacks.containsKey(callback)) {
            try {
                CallbackWrapper callbackWrapper = this.mRegisteredCallbacks.remove(callback);
                this.mPredictionManager.unregisterPredictionUpdates(this.mSessionId, callbackWrapper);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to unregister for prediction updates", e);
                e.rethrowAsRuntimeException();
            }
        }
    }

    public void requestPredictionUpdate() {
        if (!this.mIsClosed.get()) {
            try {
                this.mPredictionManager.requestPredictionUpdate(this.mSessionId);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to request prediction update", e);
                e.rethrowAsRuntimeException();
            }
        } else {
            throw new IllegalStateException("This client has already been destroyed.");
        }
    }

    public void sortTargets(List<AppTarget> targets, Executor callbackExecutor, Consumer<List<AppTarget>> callback) {
        if (!this.mIsClosed.get()) {
            try {
                this.mPredictionManager.sortAppTargets(this.mSessionId, new ParceledListSlice(targets), new CallbackWrapper(callbackExecutor, callback));
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to sort targets", e);
                e.rethrowAsRuntimeException();
            }
        } else {
            throw new IllegalStateException("This client has already been destroyed.");
        }
    }

    public void destroy() {
        if (!this.mIsClosed.getAndSet(true)) {
            this.mCloseGuard.close();
            try {
                this.mPredictionManager.onDestroyPredictionSession(this.mSessionId);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to notify app target event", e);
                e.rethrowAsRuntimeException();
            }
            this.mRegisteredCallbacks.clear();
            return;
        }
        throw new IllegalStateException("This client has already been destroyed.");
    }

    protected void finalize() throws Throwable {
        try {
            CloseGuard closeGuard = this.mCloseGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            if (!this.mIsClosed.get()) {
                destroy();
            }
        } finally {
            super.finalize();
        }
    }

    public AppPredictionSessionId getSessionId() {
        return this.mSessionId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CallbackWrapper extends IPredictionCallback.Stub {
        private final Consumer<List<AppTarget>> mCallback;
        private final Executor mExecutor;

        CallbackWrapper(Executor callbackExecutor, Consumer<List<AppTarget>> callback) {
            this.mCallback = callback;
            this.mExecutor = callbackExecutor;
        }

        @Override // android.app.prediction.IPredictionCallback
        public void onResult(final ParceledListSlice result) {
            long identity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.app.prediction.AppPredictor$CallbackWrapper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppPredictor.CallbackWrapper.this.lambda$onResult$0$AppPredictor$CallbackWrapper(result);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        public /* synthetic */ void lambda$onResult$0$AppPredictor$CallbackWrapper(ParceledListSlice result) {
            this.mCallback.accept(result.getList());
        }
    }
}
