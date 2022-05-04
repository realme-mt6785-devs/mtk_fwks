package android.view.translation;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SynchronousResultReceiver;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.translation.TranslationManager;
import android.view.translation.Translator;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.SyncResultReceiver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final class TranslationManager {
    public static final String EXTRA_CAPABILITIES = "translation_capabilities";
    public static final int STATUS_SYNC_CALL_FAIL = 2;
    public static final int STATUS_SYNC_CALL_SUCCESS = 1;
    static final int SYNC_CALLS_TIMEOUT_MS = 60000;
    private static final String TAG = "TranslationManager";
    private final Context mContext;
    private final ITranslationManager mService;
    private static final Random ID_GENERATOR = new Random();
    private static final AtomicInteger sAvailableRequestId = new AtomicInteger(1);
    private final ArrayMap<Pair<Integer, Integer>, ArrayList<PendingIntent>> mTranslationCapabilityUpdateListeners = new ArrayMap<>();
    private final Map<Consumer<TranslationCapability>, IRemoteCallback> mCapabilityCallbacks = new ArrayMap();
    private final Object mLock = new Object();
    private final SparseArray<Translator> mTranslators = new SparseArray<>();
    private final ArrayMap<TranslationContext, Integer> mTranslatorIds = new ArrayMap<>();
    private final Handler mHandler = Handler.createAsync(Looper.getMainLooper());

    public TranslationManager(Context context, ITranslationManager service) {
        Objects.requireNonNull(context, "context cannot be null");
        this.mContext = context;
        this.mService = service;
    }

    public void createOnDeviceTranslator(final TranslationContext translationContext, Executor executor, final Consumer<Translator> callback) {
        Objects.requireNonNull(translationContext, "translationContext cannot be null");
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(callback, "callback cannot be null");
        synchronized (this.mLock) {
            try {
                try {
                    if (this.mTranslatorIds.containsKey(translationContext)) {
                        executor.execute(new Runnable() { // from class: android.view.translation.TranslationManager$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                TranslationManager.this.lambda$createOnDeviceTranslator$0$TranslationManager(callback, translationContext);
                            }
                        });
                        return;
                    }
                    while (true) {
                        int translatorId = Math.abs(ID_GENERATOR.nextInt());
                        if (translatorId != 0 && this.mTranslators.indexOfKey(translatorId) < 0) {
                            new Translator(this.mContext, translationContext, translatorId, this, this.mHandler, this.mService, new AnonymousClass1(executor, callback, translatorId, translationContext));
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public /* synthetic */ void lambda$createOnDeviceTranslator$0$TranslationManager(Consumer callback, TranslationContext translationContext) {
        callback.accept(this.mTranslators.get(this.mTranslatorIds.get(translationContext).intValue()));
    }

    /* renamed from: android.view.translation.TranslationManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 implements Consumer<Translator> {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ int val$tId;
        final /* synthetic */ TranslationContext val$translationContext;

        AnonymousClass1(Executor executor, Consumer consumer, int i, TranslationContext translationContext) {
            this.val$executor = executor;
            this.val$callback = consumer;
            this.val$tId = i;
            this.val$translationContext = translationContext;
        }

        public void accept(final Translator translator) {
            long token;
            if (translator == null) {
                token = Binder.clearCallingIdentity();
                try {
                    Executor executor = this.val$executor;
                    final Consumer consumer = this.val$callback;
                    executor.execute(new Runnable() { // from class: android.view.translation.TranslationManager$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            consumer.accept(null);
                        }
                    });
                } finally {
                }
            } else {
                synchronized (TranslationManager.this.mLock) {
                    TranslationManager.this.mTranslators.put(this.val$tId, translator);
                    TranslationManager.this.mTranslatorIds.put(this.val$translationContext, Integer.valueOf(this.val$tId));
                }
                token = Binder.clearCallingIdentity();
                try {
                    Executor executor2 = this.val$executor;
                    final Consumer consumer2 = this.val$callback;
                    executor2.execute(new Runnable() { // from class: android.view.translation.TranslationManager$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            consumer2.accept(translator);
                        }
                    });
                } finally {
                }
            }
        }
    }

    @Deprecated
    public Translator createOnDeviceTranslator(TranslationContext translationContext) {
        int translatorId;
        Objects.requireNonNull(translationContext, "translationContext cannot be null");
        synchronized (this.mLock) {
            if (this.mTranslatorIds.containsKey(translationContext)) {
                return this.mTranslators.get(this.mTranslatorIds.get(translationContext).intValue());
            }
            while (true) {
                translatorId = Math.abs(ID_GENERATOR.nextInt());
                if (translatorId != 0 && this.mTranslators.indexOfKey(translatorId) < 0) {
                    break;
                }
            }
            Translator newTranslator = new Translator(this.mContext, translationContext, translatorId, this, this.mHandler, this.mService);
            newTranslator.start();
            try {
                if (!newTranslator.isSessionCreated()) {
                    return null;
                }
                this.mTranslators.put(translatorId, newTranslator);
                this.mTranslatorIds.put(translationContext, Integer.valueOf(translatorId));
                return newTranslator;
            } catch (Translator.ServiceBinderReceiver.TimeoutException e) {
                Log.e(TAG, "Timed out getting create session: " + e);
                return null;
            }
        }
    }

    @Deprecated
    public Translator createTranslator(TranslationContext translationContext) {
        return createOnDeviceTranslator(translationContext);
    }

    public Set<TranslationCapability> getOnDeviceTranslationCapabilities(int sourceFormat, int targetFormat) {
        try {
            SynchronousResultReceiver receiver = new SynchronousResultReceiver();
            this.mService.onTranslationCapabilitiesRequest(sourceFormat, targetFormat, receiver, this.mContext.getUserId());
            SynchronousResultReceiver.Result result = receiver.awaitResult(60000L);
            if (result.resultCode != 1) {
                return Collections.emptySet();
            }
            Parcelable[] parcelables = result.bundle.getParcelableArray(EXTRA_CAPABILITIES);
            ArraySet<TranslationCapability> capabilities = new ArraySet<>();
            for (Parcelable obj : parcelables) {
                if (obj instanceof TranslationCapability) {
                    capabilities.add((TranslationCapability) obj);
                }
            }
            return capabilities;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (TimeoutException e2) {
            Log.e(TAG, "Timed out getting supported translation capabilities: " + e2);
            return Collections.emptySet();
        }
    }

    @Deprecated
    public Set<TranslationCapability> getTranslationCapabilities(int sourceFormat, int targetFormat) {
        return getOnDeviceTranslationCapabilities(sourceFormat, targetFormat);
    }

    public void addOnDeviceTranslationCapabilityUpdateListener(Executor executor, Consumer<TranslationCapability> capabilityListener) {
        Objects.requireNonNull(executor, "executor should not be null");
        Objects.requireNonNull(capabilityListener, "capability listener should not be null");
        synchronized (this.mLock) {
            if (this.mCapabilityCallbacks.containsKey(capabilityListener)) {
                Log.w(TAG, "addOnDeviceTranslationCapabilityUpdateListener: the listener for " + capabilityListener + " already registered; ignoring.");
                return;
            }
            IRemoteCallback remoteCallback = new TranslationCapabilityRemoteCallback(executor, capabilityListener);
            try {
                this.mService.registerTranslationCapabilityCallback(remoteCallback, this.mContext.getUserId());
                this.mCapabilityCallbacks.put(capabilityListener, remoteCallback);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Deprecated
    public void addOnDeviceTranslationCapabilityUpdateListener(int sourceFormat, int targetFormat, PendingIntent pendingIntent) {
        Objects.requireNonNull(pendingIntent, "pending intent should not be null");
        synchronized (this.mLock) {
            Pair<Integer, Integer> formatPair = new Pair<>(Integer.valueOf(sourceFormat), Integer.valueOf(targetFormat));
            this.mTranslationCapabilityUpdateListeners.computeIfAbsent(formatPair, TranslationManager$$ExternalSyntheticLambda1.INSTANCE).add(pendingIntent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArrayList lambda$addOnDeviceTranslationCapabilityUpdateListener$1(Pair formats) {
        return new ArrayList();
    }

    @Deprecated
    public void addTranslationCapabilityUpdateListener(int sourceFormat, int targetFormat, PendingIntent pendingIntent) {
        addOnDeviceTranslationCapabilityUpdateListener(sourceFormat, targetFormat, pendingIntent);
    }

    public void removeOnDeviceTranslationCapabilityUpdateListener(Consumer<TranslationCapability> capabilityListener) {
        Objects.requireNonNull(capabilityListener, "capability callback should not be null");
        synchronized (this.mLock) {
            IRemoteCallback remoteCallback = this.mCapabilityCallbacks.get(capabilityListener);
            if (remoteCallback == null) {
                Log.w(TAG, "removeOnDeviceTranslationCapabilityUpdateListener: the capability listener not found; ignoring.");
                return;
            }
            try {
                this.mService.unregisterTranslationCapabilityCallback(remoteCallback, this.mContext.getUserId());
                this.mCapabilityCallbacks.remove(capabilityListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Deprecated
    public void removeOnDeviceTranslationCapabilityUpdateListener(int sourceFormat, int targetFormat, PendingIntent pendingIntent) {
        Objects.requireNonNull(pendingIntent, "pending intent should not be null");
        synchronized (this.mLock) {
            Pair<Integer, Integer> formatPair = new Pair<>(Integer.valueOf(sourceFormat), Integer.valueOf(targetFormat));
            if (this.mTranslationCapabilityUpdateListeners.containsKey(formatPair)) {
                ArrayList<PendingIntent> intents = this.mTranslationCapabilityUpdateListeners.get(formatPair);
                if (intents.contains(pendingIntent)) {
                    intents.remove(pendingIntent);
                } else {
                    Log.w(TAG, "pending intent=" + pendingIntent + " does not exist in mTranslationCapabilityUpdateListeners");
                }
            } else {
                Log.w(TAG, "format pair=" + formatPair + " does not exist in mTranslationCapabilityUpdateListeners");
            }
        }
    }

    @Deprecated
    public void removeTranslationCapabilityUpdateListener(int sourceFormat, int targetFormat, PendingIntent pendingIntent) {
        removeOnDeviceTranslationCapabilityUpdateListener(sourceFormat, targetFormat, pendingIntent);
    }

    public PendingIntent getOnDeviceTranslationSettingsActivityIntent() {
        SyncResultReceiver resultReceiver = new SyncResultReceiver(60000);
        try {
            this.mService.getServiceSettingsActivity(resultReceiver, this.mContext.getUserId());
            try {
                return (PendingIntent) resultReceiver.getParcelableResult();
            } catch (SyncResultReceiver.TimeoutException e) {
                Log.e(TAG, "Fail to get translation service settings activity.");
                return null;
            }
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public PendingIntent getTranslationSettingsActivityIntent() {
        return getOnDeviceTranslationSettingsActivityIntent();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeTranslator(int id) {
        synchronized (this.mLock) {
            this.mTranslators.remove(id);
            int i = 0;
            while (true) {
                if (i >= this.mTranslatorIds.size()) {
                    break;
                } else if (this.mTranslatorIds.valueAt(i).intValue() == id) {
                    this.mTranslatorIds.removeAt(i);
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger getAvailableRequestId() {
        AtomicInteger atomicInteger;
        synchronized (this.mLock) {
            atomicInteger = sAvailableRequestId;
        }
        return atomicInteger;
    }

    /* loaded from: classes3.dex */
    private static class TranslationCapabilityRemoteCallback extends IRemoteCallback.Stub {
        private final Executor mExecutor;
        private final Consumer<TranslationCapability> mListener;

        TranslationCapabilityRemoteCallback(Executor executor, Consumer<TranslationCapability> listener) {
            this.mExecutor = executor;
            this.mListener = listener;
        }

        @Override // android.os.IRemoteCallback
        public void sendResult(final Bundle bundle) {
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.view.translation.TranslationManager$TranslationCapabilityRemoteCallback$$ExternalSyntheticLambda0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    TranslationManager.TranslationCapabilityRemoteCallback.this.lambda$sendResult$1$TranslationManager$TranslationCapabilityRemoteCallback(bundle);
                }
            });
        }

        public /* synthetic */ void lambda$sendResult$1$TranslationManager$TranslationCapabilityRemoteCallback(final Bundle bundle) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.view.translation.TranslationManager$TranslationCapabilityRemoteCallback$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    TranslationManager.TranslationCapabilityRemoteCallback.this.lambda$sendResult$0$TranslationManager$TranslationCapabilityRemoteCallback(bundle);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onTranslationCapabilityUpdate */
        public void lambda$sendResult$0$TranslationManager$TranslationCapabilityRemoteCallback(Bundle bundle) {
            TranslationCapability capability = (TranslationCapability) bundle.getParcelable(TranslationManager.EXTRA_CAPABILITIES);
            this.mListener.accept(capability);
        }
    }
}
