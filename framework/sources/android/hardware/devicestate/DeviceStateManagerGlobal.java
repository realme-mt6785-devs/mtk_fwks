package android.hardware.devicestate;

import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.devicestate.DeviceStateManagerGlobal;
import android.hardware.devicestate.DeviceStateRequest;
import android.hardware.devicestate.IDeviceStateManager;
import android.hardware.devicestate.IDeviceStateManagerCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class DeviceStateManagerGlobal {
    private static DeviceStateManagerGlobal sInstance;
    private DeviceStateManagerCallback mCallback;
    private final IDeviceStateManager mDeviceStateManager;
    private DeviceStateInfo mLastReceivedInfo;
    private final Object mLock = new Object();
    private final ArrayList<DeviceStateCallbackWrapper> mCallbacks = new ArrayList<>();
    private final ArrayMap<IBinder, DeviceStateRequestWrapper> mRequests = new ArrayMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DeviceStateManagerGlobal getInstance() {
        DeviceStateManagerGlobal deviceStateManagerGlobal;
        IBinder b;
        synchronized (DeviceStateManagerGlobal.class) {
            if (sInstance == null && (b = ServiceManager.getService(Context.DEVICE_STATE_SERVICE)) != null) {
                sInstance = new DeviceStateManagerGlobal(IDeviceStateManager.Stub.asInterface(b));
            }
            deviceStateManagerGlobal = sInstance;
        }
        return deviceStateManagerGlobal;
    }

    public DeviceStateManagerGlobal(IDeviceStateManager deviceStateManager) {
        this.mDeviceStateManager = deviceStateManager;
    }

    public int[] getSupportedStates() {
        int[] copyOf;
        synchronized (this.mLock) {
            DeviceStateInfo currentInfo = this.mLastReceivedInfo;
            if (currentInfo == null) {
                try {
                    currentInfo = this.mDeviceStateManager.getDeviceStateInfo();
                } catch (RemoteException ex) {
                    throw ex.rethrowFromSystemServer();
                }
            }
            copyOf = Arrays.copyOf(currentInfo.supportedStates, currentInfo.supportedStates.length);
        }
        return copyOf;
    }

    public void requestState(DeviceStateRequest request, DeviceStateRequest.Callback callback, Executor executor) {
        if (callback == null && executor != null) {
            throw new IllegalArgumentException("Callback must be supplied with executor.");
        } else if (executor != null || callback == null) {
            synchronized (this.mLock) {
                registerCallbackIfNeededLocked();
                if (findRequestTokenLocked(request) == null) {
                    IBinder token = new Binder();
                    this.mRequests.put(token, new DeviceStateRequestWrapper(request, callback, executor));
                    try {
                        this.mDeviceStateManager.requestState(token, request.getState(), request.getFlags());
                    } catch (RemoteException ex) {
                        this.mRequests.remove(token);
                        throw ex.rethrowFromSystemServer();
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Executor must be supplied with callback.");
        }
    }

    public void cancelRequest(DeviceStateRequest request) {
        synchronized (this.mLock) {
            registerCallbackIfNeededLocked();
            IBinder token = findRequestTokenLocked(request);
            if (token != null) {
                try {
                    this.mDeviceStateManager.cancelRequest(token);
                } catch (RemoteException ex) {
                    throw ex.rethrowFromSystemServer();
                }
            }
        }
    }

    public void registerDeviceStateCallback(DeviceStateManager.DeviceStateCallback callback, Executor executor) {
        synchronized (this.mLock) {
            int index = findCallbackLocked(callback);
            if (index == -1) {
                registerCallbackIfNeededLocked();
                DeviceStateCallbackWrapper wrapper = new DeviceStateCallbackWrapper(callback, executor);
                this.mCallbacks.add(wrapper);
                DeviceStateInfo deviceStateInfo = this.mLastReceivedInfo;
                if (deviceStateInfo != null) {
                    int[] supportedStates = Arrays.copyOf(deviceStateInfo.supportedStates, this.mLastReceivedInfo.supportedStates.length);
                    wrapper.notifySupportedStatesChanged(supportedStates);
                    wrapper.notifyBaseStateChanged(this.mLastReceivedInfo.baseState);
                    wrapper.notifyStateChanged(this.mLastReceivedInfo.currentState);
                }
            }
        }
    }

    public void unregisterDeviceStateCallback(DeviceStateManager.DeviceStateCallback callback) {
        synchronized (this.mLock) {
            int indexToRemove = findCallbackLocked(callback);
            if (indexToRemove != -1) {
                this.mCallbacks.remove(indexToRemove);
            }
        }
    }

    private void registerCallbackIfNeededLocked() {
        if (this.mCallback == null) {
            DeviceStateManagerCallback deviceStateManagerCallback = new DeviceStateManagerCallback();
            this.mCallback = deviceStateManagerCallback;
            try {
                this.mDeviceStateManager.registerCallback(deviceStateManagerCallback);
            } catch (RemoteException ex) {
                this.mCallback = null;
                throw ex.rethrowFromSystemServer();
            }
        }
    }

    private int findCallbackLocked(DeviceStateManager.DeviceStateCallback callback) {
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            if (this.mCallbacks.get(i).mDeviceStateCallback.equals(callback)) {
                return i;
            }
        }
        return -1;
    }

    private IBinder findRequestTokenLocked(DeviceStateRequest request) {
        for (int i = 0; i < this.mRequests.size(); i++) {
            if (this.mRequests.valueAt(i).mRequest.equals(request)) {
                return this.mRequests.keyAt(i);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceStateInfoChanged(DeviceStateInfo info) {
        DeviceStateInfo oldInfo;
        ArrayList<DeviceStateCallbackWrapper> callbacks;
        synchronized (this.mLock) {
            oldInfo = this.mLastReceivedInfo;
            this.mLastReceivedInfo = info;
            callbacks = new ArrayList<>(this.mCallbacks);
        }
        int diff = oldInfo == null ? -1 : info.diff(oldInfo);
        if ((diff & 1) > 0) {
            for (int i = 0; i < callbacks.size(); i++) {
                int[] supportedStates = Arrays.copyOf(info.supportedStates, info.supportedStates.length);
                callbacks.get(i).notifySupportedStatesChanged(supportedStates);
            }
        }
        int i2 = diff & 2;
        if (i2 > 0) {
            for (int i3 = 0; i3 < callbacks.size(); i3++) {
                callbacks.get(i3).notifyBaseStateChanged(info.baseState);
            }
        }
        int i4 = diff & 4;
        if (i4 > 0) {
            for (int i5 = 0; i5 < callbacks.size(); i5++) {
                callbacks.get(i5).notifyStateChanged(info.currentState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestActive(IBinder token) {
        DeviceStateRequestWrapper request;
        synchronized (this.mLock) {
            request = this.mRequests.get(token);
        }
        if (request != null) {
            request.notifyRequestActive();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestSuspended(IBinder token) {
        DeviceStateRequestWrapper request;
        synchronized (this.mLock) {
            request = this.mRequests.get(token);
        }
        if (request != null) {
            request.notifyRequestSuspended();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestCanceled(IBinder token) {
        DeviceStateRequestWrapper request;
        synchronized (this.mLock) {
            request = this.mRequests.remove(token);
        }
        if (request != null) {
            request.notifyRequestCanceled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DeviceStateManagerCallback extends IDeviceStateManagerCallback.Stub {
        private DeviceStateManagerCallback() {
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onDeviceStateInfoChanged(DeviceStateInfo info) {
            DeviceStateManagerGlobal.this.handleDeviceStateInfoChanged(info);
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onRequestActive(IBinder token) {
            DeviceStateManagerGlobal.this.handleRequestActive(token);
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onRequestSuspended(IBinder token) {
            DeviceStateManagerGlobal.this.handleRequestSuspended(token);
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onRequestCanceled(IBinder token) {
            DeviceStateManagerGlobal.this.handleRequestCanceled(token);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DeviceStateCallbackWrapper {
        private final DeviceStateManager.DeviceStateCallback mDeviceStateCallback;
        private final Executor mExecutor;

        DeviceStateCallbackWrapper(DeviceStateManager.DeviceStateCallback callback, Executor executor) {
            this.mDeviceStateCallback = callback;
            this.mExecutor = executor;
        }

        void notifySupportedStatesChanged(final int[] newSupportedStates) {
            this.mExecutor.execute(new Runnable() { // from class: android.hardware.devicestate.DeviceStateManagerGlobal$DeviceStateCallbackWrapper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceStateManagerGlobal.DeviceStateCallbackWrapper.this.lambda$notifySupportedStatesChanged$0$DeviceStateManagerGlobal$DeviceStateCallbackWrapper(newSupportedStates);
                }
            });
        }

        public /* synthetic */ void lambda$notifySupportedStatesChanged$0$DeviceStateManagerGlobal$DeviceStateCallbackWrapper(int[] newSupportedStates) {
            this.mDeviceStateCallback.onSupportedStatesChanged(newSupportedStates);
        }

        public /* synthetic */ void lambda$notifyBaseStateChanged$1$DeviceStateManagerGlobal$DeviceStateCallbackWrapper(int newBaseState) {
            this.mDeviceStateCallback.onBaseStateChanged(newBaseState);
        }

        void notifyBaseStateChanged(final int newBaseState) {
            this.mExecutor.execute(new Runnable() { // from class: android.hardware.devicestate.DeviceStateManagerGlobal$DeviceStateCallbackWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceStateManagerGlobal.DeviceStateCallbackWrapper.this.lambda$notifyBaseStateChanged$1$DeviceStateManagerGlobal$DeviceStateCallbackWrapper(newBaseState);
                }
            });
        }

        public /* synthetic */ void lambda$notifyStateChanged$2$DeviceStateManagerGlobal$DeviceStateCallbackWrapper(int newDeviceState) {
            this.mDeviceStateCallback.onStateChanged(newDeviceState);
        }

        void notifyStateChanged(final int newDeviceState) {
            this.mExecutor.execute(new Runnable() { // from class: android.hardware.devicestate.DeviceStateManagerGlobal$DeviceStateCallbackWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceStateManagerGlobal.DeviceStateCallbackWrapper.this.lambda$notifyStateChanged$2$DeviceStateManagerGlobal$DeviceStateCallbackWrapper(newDeviceState);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DeviceStateRequestWrapper {
        private final DeviceStateRequest.Callback mCallback;
        private final Executor mExecutor;
        private final DeviceStateRequest mRequest;

        DeviceStateRequestWrapper(DeviceStateRequest request, DeviceStateRequest.Callback callback, Executor executor) {
            this.mRequest = request;
            this.mCallback = callback;
            this.mExecutor = executor;
        }

        void notifyRequestActive() {
            if (this.mCallback != null) {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.devicestate.DeviceStateManagerGlobal$DeviceStateRequestWrapper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceStateManagerGlobal.DeviceStateRequestWrapper.this.lambda$notifyRequestActive$0$DeviceStateManagerGlobal$DeviceStateRequestWrapper();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$notifyRequestActive$0$DeviceStateManagerGlobal$DeviceStateRequestWrapper() {
            this.mCallback.onRequestActivated(this.mRequest);
        }

        void notifyRequestSuspended() {
            if (this.mCallback != null) {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.devicestate.DeviceStateManagerGlobal$DeviceStateRequestWrapper$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceStateManagerGlobal.DeviceStateRequestWrapper.this.lambda$notifyRequestSuspended$1$DeviceStateManagerGlobal$DeviceStateRequestWrapper();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$notifyRequestSuspended$1$DeviceStateManagerGlobal$DeviceStateRequestWrapper() {
            this.mCallback.onRequestSuspended(this.mRequest);
        }

        void notifyRequestCanceled() {
            if (this.mCallback != null) {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.devicestate.DeviceStateManagerGlobal$DeviceStateRequestWrapper$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceStateManagerGlobal.DeviceStateRequestWrapper.this.lambda$notifyRequestCanceled$2$DeviceStateManagerGlobal$DeviceStateRequestWrapper();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$notifyRequestCanceled$2$DeviceStateManagerGlobal$DeviceStateRequestWrapper() {
            this.mCallback.onRequestSuspended(this.mRequest);
        }
    }
}
