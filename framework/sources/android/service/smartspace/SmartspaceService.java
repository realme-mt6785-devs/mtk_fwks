package android.service.smartspace;

import android.annotation.SystemApi;
import android.app.Service;
import android.app.smartspace.ISmartspaceCallback;
import android.app.smartspace.SmartspaceConfig;
import android.app.smartspace.SmartspaceSessionId;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceTargetEvent;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.smartspace.ISmartspaceService;
import android.service.smartspace.SmartspaceService;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
@SystemApi
/* loaded from: classes3.dex */
public abstract class SmartspaceService extends Service {
    private static final boolean DEBUG = false;
    public static final String SERVICE_INTERFACE = "android.service.smartspace.SmartspaceService";
    private static final String TAG = "SmartspaceService";
    private Handler mHandler;
    private final ArrayMap<SmartspaceSessionId, ArrayList<CallbackWrapper>> mSessionCallbacks = new ArrayMap<>();
    private final ISmartspaceService mInterface = new AnonymousClass1();

    public abstract void notifySmartspaceEvent(SmartspaceSessionId smartspaceSessionId, SmartspaceTargetEvent smartspaceTargetEvent);

    public abstract void onCreateSmartspaceSession(SmartspaceConfig smartspaceConfig, SmartspaceSessionId smartspaceSessionId);

    public abstract void onDestroy(SmartspaceSessionId smartspaceSessionId);

    public abstract void onDestroySmartspaceSession(SmartspaceSessionId smartspaceSessionId);

    public abstract void onRequestSmartspaceUpdate(SmartspaceSessionId smartspaceSessionId);

    /* renamed from: android.service.smartspace.SmartspaceService$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends ISmartspaceService.Stub {
        AnonymousClass1() {
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void onCreateSmartspaceSession(SmartspaceConfig smartspaceConfig, SmartspaceSessionId sessionId) {
            SmartspaceService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SmartspaceService$1$$ExternalSyntheticLambda0.INSTANCE, SmartspaceService.this, smartspaceConfig, sessionId));
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void notifySmartspaceEvent(SmartspaceSessionId sessionId, SmartspaceTargetEvent event) {
            SmartspaceService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SmartspaceService$1$$ExternalSyntheticLambda3.INSTANCE, SmartspaceService.this, sessionId, event));
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void requestSmartspaceUpdate(SmartspaceSessionId sessionId) {
            SmartspaceService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SmartspaceService$1$$ExternalSyntheticLambda5.INSTANCE, SmartspaceService.this, sessionId));
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void registerSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) {
            SmartspaceService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SmartspaceService$1$$ExternalSyntheticLambda1.INSTANCE, SmartspaceService.this, sessionId, callback));
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void unregisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) {
            SmartspaceService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SmartspaceService$1$$ExternalSyntheticLambda2.INSTANCE, SmartspaceService.this, sessionId, callback));
        }

        @Override // android.service.smartspace.ISmartspaceService
        public void onDestroySmartspaceSession(SmartspaceSessionId sessionId) {
            SmartspaceService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SmartspaceService$1$$ExternalSyntheticLambda4.INSTANCE, SmartspaceService.this, sessionId));
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mHandler = new Handler(Looper.getMainLooper(), null, true);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return this.mInterface.asBinder();
        }
        Slog.w(TAG, "Tried to bind to wrong intent (should be android.service.smartspace.SmartspaceService: " + intent);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCreateSmartspaceSession(SmartspaceConfig config, SmartspaceSessionId sessionId) {
        this.mSessionCallbacks.put(sessionId, new ArrayList<>());
        onCreateSmartspaceSession(config, sessionId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRegisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) {
        final ArrayList<CallbackWrapper> callbacks = this.mSessionCallbacks.get(sessionId);
        if (callbacks == null) {
            Slog.e(TAG, "Failed to register for updates for unknown session: " + sessionId);
            return;
        }
        CallbackWrapper wrapper = findCallbackWrapper(callbacks, callback);
        if (wrapper == null) {
            callbacks.add(new CallbackWrapper(callback, new Consumer() { // from class: android.service.smartspace.SmartspaceService$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SmartspaceService.this.lambda$doRegisterSmartspaceUpdates$1$SmartspaceService(callbacks, (SmartspaceService.CallbackWrapper) obj);
                }
            }));
        }
    }

    public /* synthetic */ void lambda$doRegisterSmartspaceUpdates$1$SmartspaceService(final ArrayList callbacks, final CallbackWrapper callbackWrapper) {
        this.mHandler.post(new Runnable() { // from class: android.service.smartspace.SmartspaceService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SmartspaceService.this.lambda$doRegisterSmartspaceUpdates$0$SmartspaceService(callbacks, callbackWrapper);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUnregisterSmartspaceUpdates(SmartspaceSessionId sessionId, ISmartspaceCallback callback) {
        ArrayList<CallbackWrapper> callbacks = this.mSessionCallbacks.get(sessionId);
        if (callbacks == null) {
            Slog.e(TAG, "Failed to unregister for updates for unknown session: " + sessionId);
            return;
        }
        CallbackWrapper wrapper = findCallbackWrapper(callbacks, callback);
        if (wrapper != null) {
            lambda$doRegisterSmartspaceUpdates$0$SmartspaceService(callbacks, wrapper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRequestPredictionUpdate(SmartspaceSessionId sessionId) {
        ArrayList<CallbackWrapper> callbacks = this.mSessionCallbacks.get(sessionId);
        if (callbacks != null && !callbacks.isEmpty()) {
            onRequestSmartspaceUpdate(sessionId);
        }
    }

    private CallbackWrapper findCallbackWrapper(ArrayList<CallbackWrapper> callbacks, ISmartspaceCallback callback) {
        for (int i = callbacks.size() - 1; i >= 0; i--) {
            if (callbacks.get(i).isCallback(callback)) {
                return callbacks.get(i);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: removeCallbackWrapper */
    public void lambda$doRegisterSmartspaceUpdates$0$SmartspaceService(ArrayList<CallbackWrapper> callbacks, CallbackWrapper wrapper) {
        if (callbacks != null) {
            callbacks.remove(wrapper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDestroy(SmartspaceSessionId sessionId) {
        Log.d(TAG, "doDestroy mSessionCallbacks: " + this.mSessionCallbacks);
        super.onDestroy();
        this.mSessionCallbacks.remove(sessionId);
        onDestroySmartspaceSession(sessionId);
    }

    public final void updateSmartspaceTargets(SmartspaceSessionId sessionId, List<SmartspaceTarget> targets) {
        List<CallbackWrapper> callbacks = this.mSessionCallbacks.get(sessionId);
        if (callbacks != null) {
            for (CallbackWrapper callback : callbacks) {
                callback.accept(targets);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class CallbackWrapper implements Consumer<List<SmartspaceTarget>>, IBinder.DeathRecipient {
        private ISmartspaceCallback mCallback;
        private final Consumer<CallbackWrapper> mOnBinderDied;

        CallbackWrapper(ISmartspaceCallback callback, Consumer<CallbackWrapper> onBinderDied) {
            this.mCallback = callback;
            this.mOnBinderDied = onBinderDied;
            try {
                callback.asBinder().linkToDeath(this, 0);
            } catch (RemoteException e) {
                Slog.e(SmartspaceService.TAG, "Failed to link to death: " + e);
            }
        }

        public boolean isCallback(ISmartspaceCallback callback) {
            ISmartspaceCallback iSmartspaceCallback = this.mCallback;
            if (iSmartspaceCallback != null) {
                return iSmartspaceCallback.equals(callback);
            }
            Slog.e(SmartspaceService.TAG, "Callback is null, likely the binder has died.");
            return false;
        }

        public void accept(List<SmartspaceTarget> smartspaceTargets) {
            try {
                ISmartspaceCallback iSmartspaceCallback = this.mCallback;
                if (iSmartspaceCallback != null) {
                    iSmartspaceCallback.onResult(new ParceledListSlice(smartspaceTargets));
                }
            } catch (RemoteException e) {
                Slog.e(SmartspaceService.TAG, "Error sending result:" + e);
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mCallback.asBinder().unlinkToDeath(this, 0);
            this.mCallback = null;
            Consumer<CallbackWrapper> consumer = this.mOnBinderDied;
            if (consumer != null) {
                consumer.accept(this);
            }
        }
    }
}
