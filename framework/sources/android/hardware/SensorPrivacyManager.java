package android.hardware;

import android.annotation.SystemApi;
import android.content.Context;
import android.hardware.ISensorPrivacyListener;
import android.hardware.ISensorPrivacyManager;
import android.hardware.SensorPrivacyManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class SensorPrivacyManager {
    private static SensorPrivacyManager sInstance;
    private final Context mContext;
    private final ISensorPrivacyManager mService;
    private static final String LOG_TAG = SensorPrivacyManager.class.getSimpleName();
    public static final String EXTRA_SENSOR = SensorPrivacyManager.class.getName() + ".extra.sensor";
    public static final String EXTRA_ALL_SENSORS = SensorPrivacyManager.class.getName() + ".extra.all_sensors";
    private static final Object sInstanceLock = new Object();
    private IBinder token = new Binder();
    private final SparseArray<Boolean> mToggleSupportCache = new SparseArray<>();
    private final ArrayMap<OnAllSensorPrivacyChangedListener, ISensorPrivacyListener> mListeners = new ArrayMap<>();
    private final ArrayMap<Pair<OnSensorPrivacyChangedListener, Integer>, ISensorPrivacyListener> mIndividualListeners = new ArrayMap<>();

    /* loaded from: classes.dex */
    public interface OnAllSensorPrivacyChangedListener {
        void onAllSensorPrivacyChanged(boolean z);
    }

    @SystemApi
    /* loaded from: classes.dex */
    public interface OnSensorPrivacyChangedListener {
        void onSensorPrivacyChanged(int i, boolean z);
    }

    /* loaded from: classes.dex */
    public static class Sensors {
        public static final int CAMERA = 2;
        public static final int MICROPHONE = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface Sensor {
        }

        private Sensors() {
        }
    }

    /* loaded from: classes.dex */
    public static class Sources {
        public static final int DIALOG = 3;
        public static final int OTHER = 5;
        public static final int QS_TILE = 1;
        public static final int SETTINGS = 2;
        public static final int SHELL = 4;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface Source {
        }

        private Sources() {
        }
    }

    private SensorPrivacyManager(Context context, ISensorPrivacyManager service) {
        this.mContext = context;
        this.mService = service;
    }

    public static SensorPrivacyManager getInstance(Context context) {
        SensorPrivacyManager sensorPrivacyManager;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                try {
                    IBinder b = ServiceManager.getServiceOrThrow(Context.SENSOR_PRIVACY_SERVICE);
                    ISensorPrivacyManager service = ISensorPrivacyManager.Stub.asInterface(b);
                    sInstance = new SensorPrivacyManager(context, service);
                } catch (ServiceManager.ServiceNotFoundException e) {
                    throw new IllegalStateException(e);
                }
            }
            sensorPrivacyManager = sInstance;
        }
        return sensorPrivacyManager;
    }

    public boolean supportsSensorToggle(int sensor) {
        try {
            Boolean val = this.mToggleSupportCache.get(sensor);
            if (val == null) {
                val = Boolean.valueOf(this.mService.supportsSensorToggle(sensor));
                this.mToggleSupportCache.put(sensor, val);
            }
            return val.booleanValue();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void addSensorPrivacyListener(int sensor, OnSensorPrivacyChangedListener listener) {
        addSensorPrivacyListener(sensor, this.mContext.getMainExecutor(), listener);
    }

    public void addSensorPrivacyListener(int sensor, int userId, OnSensorPrivacyChangedListener listener) {
        addSensorPrivacyListener(sensor, userId, this.mContext.getMainExecutor(), listener);
    }

    @SystemApi
    public void addSensorPrivacyListener(int sensor, Executor executor, OnSensorPrivacyChangedListener listener) {
        Pair<OnSensorPrivacyChangedListener, Integer> key = new Pair<>(listener, Integer.valueOf(sensor));
        synchronized (this.mIndividualListeners) {
            ISensorPrivacyListener iListener = this.mIndividualListeners.get(key);
            if (iListener == null) {
                iListener = new AnonymousClass1(executor, listener, sensor);
                this.mIndividualListeners.put(key, iListener);
            }
            try {
                this.mService.addUserGlobalIndividualSensorPrivacyListener(sensor, iListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.SensorPrivacyManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends ISensorPrivacyListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ OnSensorPrivacyChangedListener val$listener;
        final /* synthetic */ int val$sensor;

        AnonymousClass1(Executor executor, OnSensorPrivacyChangedListener onSensorPrivacyChangedListener, int i) {
            this.val$executor = executor;
            this.val$listener = onSensorPrivacyChangedListener;
            this.val$sensor = i;
        }

        @Override // android.hardware.ISensorPrivacyListener
        public void onSensorPrivacyChanged(final boolean enabled) {
            Executor executor = this.val$executor;
            final OnSensorPrivacyChangedListener onSensorPrivacyChangedListener = this.val$listener;
            final int i = this.val$sensor;
            executor.execute(new Runnable() { // from class: android.hardware.SensorPrivacyManager$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SensorPrivacyManager.OnSensorPrivacyChangedListener.this.onSensorPrivacyChanged(i, enabled);
                }
            });
        }
    }

    public void addSensorPrivacyListener(int sensor, int userId, Executor executor, OnSensorPrivacyChangedListener listener) {
        synchronized (this.mIndividualListeners) {
            ISensorPrivacyListener iListener = this.mIndividualListeners.get(listener);
            if (iListener == null) {
                iListener = new AnonymousClass2(executor, listener, sensor);
                this.mIndividualListeners.put(new Pair<>(listener, Integer.valueOf(sensor)), iListener);
            }
            try {
                this.mService.addIndividualSensorPrivacyListener(userId, sensor, iListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.SensorPrivacyManager$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends ISensorPrivacyListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ OnSensorPrivacyChangedListener val$listener;
        final /* synthetic */ int val$sensor;

        AnonymousClass2(Executor executor, OnSensorPrivacyChangedListener onSensorPrivacyChangedListener, int i) {
            this.val$executor = executor;
            this.val$listener = onSensorPrivacyChangedListener;
            this.val$sensor = i;
        }

        @Override // android.hardware.ISensorPrivacyListener
        public void onSensorPrivacyChanged(final boolean enabled) {
            Executor executor = this.val$executor;
            final OnSensorPrivacyChangedListener onSensorPrivacyChangedListener = this.val$listener;
            final int i = this.val$sensor;
            executor.execute(new Runnable() { // from class: android.hardware.SensorPrivacyManager$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SensorPrivacyManager.OnSensorPrivacyChangedListener.this.onSensorPrivacyChanged(i, enabled);
                }
            });
        }
    }

    @SystemApi
    public void removeSensorPrivacyListener(int sensor, OnSensorPrivacyChangedListener listener) {
        synchronized (this.mListeners) {
            int i = 0;
            while (i < this.mIndividualListeners.size()) {
                Pair<OnSensorPrivacyChangedListener, Integer> pair = this.mIndividualListeners.keyAt(i);
                if (pair.second.intValue() == sensor && pair.first.equals(listener)) {
                    try {
                        this.mService.removeIndividualSensorPrivacyListener(sensor, this.mIndividualListeners.valueAt(i));
                        this.mIndividualListeners.removeAt(i);
                        i--;
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
                i++;
            }
        }
    }

    @SystemApi
    public boolean isSensorPrivacyEnabled(int sensor) {
        return isSensorPrivacyEnabled(sensor, -2);
    }

    public boolean isSensorPrivacyEnabled(int sensor, int userId) {
        try {
            return this.mService.isIndividualSensorPrivacyEnabled(userId, sensor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSensorPrivacy(int source, int sensor, boolean enable) {
        setSensorPrivacy(source, sensor, enable, -2);
    }

    public void setSensorPrivacy(int source, int sensor, boolean enable, int userId) {
        try {
            this.mService.setIndividualSensorPrivacy(userId, source, sensor, enable);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSensorPrivacyForProfileGroup(int source, int sensor, boolean enable) {
        setSensorPrivacyForProfileGroup(source, sensor, enable, -2);
    }

    public void setSensorPrivacyForProfileGroup(int source, int sensor, boolean enable, int userId) {
        try {
            this.mService.setIndividualSensorPrivacyForProfileGroup(userId, source, sensor, enable);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void suppressSensorPrivacyReminders(int sensor, boolean suppress) {
        suppressSensorPrivacyReminders(sensor, suppress, -2);
    }

    public void suppressSensorPrivacyReminders(int sensor, boolean suppress, int userId) {
        try {
            this.mService.suppressIndividualSensorPrivacyReminders(userId, sensor, this.token, suppress);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void showSensorUseDialog(int sensor) {
        try {
            this.mService.showSensorUseDialog(sensor);
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "Received exception while trying to show sensor use dialog", e);
        }
    }

    public void setAllSensorPrivacy(boolean enable) {
        try {
            this.mService.setSensorPrivacy(enable);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addAllSensorPrivacyListener(final OnAllSensorPrivacyChangedListener listener) {
        synchronized (this.mListeners) {
            ISensorPrivacyListener iListener = this.mListeners.get(listener);
            if (iListener == null) {
                iListener = new ISensorPrivacyListener.Stub() { // from class: android.hardware.SensorPrivacyManager.3
                    @Override // android.hardware.ISensorPrivacyListener
                    public void onSensorPrivacyChanged(boolean enabled) {
                        listener.onAllSensorPrivacyChanged(enabled);
                    }
                };
                this.mListeners.put(listener, iListener);
            }
            try {
                this.mService.addSensorPrivacyListener(iListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void removeAllSensorPrivacyListener(OnAllSensorPrivacyChangedListener listener) {
        synchronized (this.mListeners) {
            ISensorPrivacyListener iListener = this.mListeners.get(listener);
            if (iListener != null) {
                this.mListeners.remove(iListener);
                try {
                    this.mService.removeSensorPrivacyListener(iListener);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    public boolean isAllSensorPrivacyEnabled() {
        try {
            return this.mService.isSensorPrivacyEnabled();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
