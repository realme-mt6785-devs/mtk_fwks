package android.telephony.ims;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.telephony.BinderCacheManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.ImsRcsManager;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRcsController;
import android.util.Log;
import com.android.internal.telephony.IIntegerConsumer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public class ImsRcsManager {
    public static final String ACTION_SHOW_CAPABILITY_DISCOVERY_OPT_IN = "android.telephony.ims.action.SHOW_CAPABILITY_DISCOVERY_OPT_IN";
    private static final String TAG = "ImsRcsManager";
    private final Map<OnAvailabilityChangedListener, AvailabilityCallbackAdapter> mAvailabilityChangedCallbacks = new HashMap();
    private final BinderCacheManager<IImsRcsController> mBinderCache;
    private final Context mContext;
    private final int mSubId;

    @SystemApi
    /* loaded from: classes3.dex */
    public interface OnAvailabilityChangedListener {
        void onAvailabilityChanged(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class AvailabilityCallbackAdapter {
        private final CapabilityBinder mBinder;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class CapabilityBinder extends IImsCapabilityCallback.Stub {
            private final Executor mExecutor;
            private final OnAvailabilityChangedListener mOnAvailabilityChangedListener;

            CapabilityBinder(OnAvailabilityChangedListener listener, Executor executor) {
                this.mExecutor = executor;
                this.mOnAvailabilityChangedListener = listener;
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onCapabilitiesStatusChanged(final int config) {
                if (this.mOnAvailabilityChangedListener != null) {
                    long callingIdentity = Binder.clearCallingIdentity();
                    try {
                        this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.ImsRcsManager$AvailabilityCallbackAdapter$CapabilityBinder$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                ImsRcsManager.AvailabilityCallbackAdapter.CapabilityBinder.this.lambda$onCapabilitiesStatusChanged$0$ImsRcsManager$AvailabilityCallbackAdapter$CapabilityBinder(config);
                            }
                        });
                    } finally {
                        restoreCallingIdentity(callingIdentity);
                    }
                }
            }

            public /* synthetic */ void lambda$onCapabilitiesStatusChanged$0$ImsRcsManager$AvailabilityCallbackAdapter$CapabilityBinder(int config) {
                this.mOnAvailabilityChangedListener.onAvailabilityChanged(config);
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onQueryCapabilityConfiguration(int capability, int radioTech, boolean isEnabled) {
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onChangeCapabilityConfigurationError(int capability, int radioTech, int reason) {
            }
        }

        AvailabilityCallbackAdapter(Executor executor, OnAvailabilityChangedListener listener) {
            this.mBinder = new CapabilityBinder(listener, executor);
        }

        public final IImsCapabilityCallback getBinder() {
            return this.mBinder;
        }
    }

    public ImsRcsManager(Context context, int subId, BinderCacheManager<IImsRcsController> binderCache) {
        this.mSubId = subId;
        this.mContext = context;
        this.mBinderCache = binderCache;
    }

    public RcsUceAdapter getUceAdapter() {
        return new RcsUceAdapter(this.mContext, this.mSubId);
    }

    public void registerImsRegistrationCallback(Executor executor, RegistrationManager.RegistrationCallback c) throws ImsException {
        if (c == null) {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        } else if (executor != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                c.setExecutor(executor);
                try {
                    imsRcsController.registerImsRegistrationCallback(this.mSubId, c.getBinder());
                } catch (RemoteException | IllegalStateException e) {
                    throw new ImsException(e.getMessage(), 1);
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.toString(), e2.errorCode);
                }
            } else {
                Log.w(TAG, "Register registration callback: IImsRcsController is null");
                throw new ImsException("Cannot find remote IMS service", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    public void unregisterImsRegistrationCallback(RegistrationManager.RegistrationCallback c) {
        if (c != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                try {
                    imsRcsController.unregisterImsRegistrationCallback(this.mSubId, c.getBinder());
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } else {
                Log.w(TAG, "Unregister registration callback: IImsRcsController is null");
                throw new IllegalStateException("Cannot find remote IMS service");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        }
    }

    public void getRegistrationState(Executor executor, final Consumer<Integer> stateCallback) {
        if (stateCallback == null) {
            throw new IllegalArgumentException("Must include a non-null stateCallback.");
        } else if (executor != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                try {
                    imsRcsController.getImsRcsRegistrationState(this.mSubId, new AnonymousClass1(executor, stateCallback));
                } catch (RemoteException | ServiceSpecificException e) {
                    Log.w(TAG, "Get registration state error: " + e);
                    executor.execute(new Runnable() { // from class: android.telephony.ims.ImsRcsManager$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            stateCallback.accept(0);
                        }
                    });
                }
            } else {
                Log.w(TAG, "Get registration state error: IImsRcsController is null");
                throw new IllegalStateException("Cannot find remote IMS service");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    /* renamed from: android.telephony.ims.ImsRcsManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$stateCallback;

        AnonymousClass1(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$stateCallback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$stateCallback;
                executor.execute(new Runnable() { // from class: android.telephony.ims.ImsRcsManager$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(Integer.valueOf(result));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    public void getRegistrationTransportType(Executor executor, final Consumer<Integer> transportTypeCallback) {
        if (transportTypeCallback == null) {
            throw new IllegalArgumentException("Must include a non-null transportTypeCallback.");
        } else if (executor != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                try {
                    imsRcsController.getImsRcsRegistrationTransportType(this.mSubId, new AnonymousClass2(executor, transportTypeCallback));
                } catch (RemoteException | ServiceSpecificException e) {
                    Log.w(TAG, "Get registration transport type error: " + e);
                    executor.execute(new Runnable() { // from class: android.telephony.ims.ImsRcsManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            transportTypeCallback.accept(-1);
                        }
                    });
                }
            } else {
                Log.w(TAG, "Get registration transport type error: IImsRcsController is null");
                throw new IllegalStateException("Cannot find remote IMS service");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    /* renamed from: android.telephony.ims.ImsRcsManager$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass2 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$transportTypeCallback;

        AnonymousClass2(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$transportTypeCallback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$transportTypeCallback;
                executor.execute(new Runnable() { // from class: android.telephony.ims.ImsRcsManager$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(Integer.valueOf(result));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    @SystemApi
    public void addOnAvailabilityChangedListener(Executor executor, OnAvailabilityChangedListener listener) throws ImsException {
        if (listener == null) {
            throw new IllegalArgumentException("Must include a non-nullOnAvailabilityChangedListener.");
        } else if (executor != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                AvailabilityCallbackAdapter adapter = addAvailabilityChangedListenerToCollection(executor, listener);
                try {
                    imsRcsController.registerRcsAvailabilityCallback(this.mSubId, adapter.getBinder());
                } catch (RemoteException e) {
                    Log.w(TAG, "Error calling IImsRcsController#registerRcsAvailabilityCallback", e);
                    throw new ImsException("Remote IMS Service is not available", 1);
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.toString(), e2.errorCode);
                }
            } else {
                Log.w(TAG, "Add availability changed listener: IImsRcsController is null");
                throw new ImsException("Cannot find remote IMS service", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    @SystemApi
    public void removeOnAvailabilityChangedListener(OnAvailabilityChangedListener listener) {
        if (listener != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController == null) {
                Log.w(TAG, "Remove availability changed listener: IImsRcsController is null");
                return;
            }
            AvailabilityCallbackAdapter callback = removeAvailabilityChangedListenerFromCollection(listener);
            if (callback != null) {
                try {
                    imsRcsController.unregisterRcsAvailabilityCallback(this.mSubId, callback.getBinder());
                } catch (RemoteException e) {
                    Log.w(TAG, "Error calling IImsRcsController#unregisterRcsAvailabilityCallback", e);
                }
            }
        } else {
            throw new IllegalArgumentException("Must include a non-nullOnAvailabilityChangedListener.");
        }
    }

    @SystemApi
    public boolean isCapable(int capability, int radioTech) throws ImsException {
        IImsRcsController imsRcsController = getIImsRcsController();
        if (imsRcsController != null) {
            try {
                return imsRcsController.isCapable(this.mSubId, capability, radioTech);
            } catch (RemoteException e) {
                Log.w(TAG, "Error calling IImsRcsController#isCapable", e);
                throw new ImsException("Remote IMS Service is not available", 1);
            }
        } else {
            Log.w(TAG, "isCapable: IImsRcsController is null");
            throw new ImsException("Cannot find remote IMS service", 1);
        }
    }

    @SystemApi
    public boolean isAvailable(int capability, int radioTech) throws ImsException {
        IImsRcsController imsRcsController = getIImsRcsController();
        if (imsRcsController != null) {
            try {
                return imsRcsController.isAvailable(this.mSubId, capability, radioTech);
            } catch (RemoteException e) {
                Log.w(TAG, "Error calling IImsRcsController#isAvailable", e);
                throw new ImsException("Remote IMS Service is not available", 1);
            }
        } else {
            Log.w(TAG, "isAvailable: IImsRcsController is null");
            throw new ImsException("Cannot find remote IMS service", 1);
        }
    }

    private AvailabilityCallbackAdapter addAvailabilityChangedListenerToCollection(Executor executor, OnAvailabilityChangedListener listener) {
        AvailabilityCallbackAdapter adapter = new AvailabilityCallbackAdapter(executor, listener);
        synchronized (this.mAvailabilityChangedCallbacks) {
            this.mAvailabilityChangedCallbacks.put(listener, adapter);
        }
        return adapter;
    }

    private AvailabilityCallbackAdapter removeAvailabilityChangedListenerFromCollection(OnAvailabilityChangedListener listener) {
        AvailabilityCallbackAdapter remove;
        synchronized (this.mAvailabilityChangedCallbacks) {
            remove = this.mAvailabilityChangedCallbacks.remove(listener);
        }
        return remove;
    }

    private IImsRcsController getIImsRcsController() {
        IBinder binder = TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyImsServiceRegisterer().get();
        return IImsRcsController.Stub.asInterface(binder);
    }
}
