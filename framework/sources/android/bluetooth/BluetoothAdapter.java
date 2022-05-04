package android.bluetooth;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.PropertyInvalidatedCache;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothConnectionCallback;
import android.bluetooth.IBluetoothManager;
import android.bluetooth.IBluetoothManagerCallback;
import android.bluetooth.IBluetoothMetadataListener;
import android.bluetooth.IBluetoothOobDataCallback;
import android.bluetooth.IBluetoothStateChangeCallback;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.PeriodicAdvertisingManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.SynchronousResultReceiver;
import android.os.SystemProperties;
import android.provider.SettingsStringUtil;
import android.util.Log;
import android.util.Pair;
import com.mediatek.bt.BluetoothLeAudioFactory;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public final class BluetoothAdapter {
    public static final String ACTION_BLE_ACL_CONNECTED = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED";
    public static final String ACTION_BLE_ACL_DISCONNECTED = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED";
    @SystemApi
    public static final String ACTION_BLE_STATE_CHANGED = "android.bluetooth.adapter.action.BLE_STATE_CHANGED";
    public static final String ACTION_BLUETOOTH_ADDRESS_CHANGED = "android.bluetooth.adapter.action.BLUETOOTH_ADDRESS_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";
    public static final String ACTION_DISCOVERY_STARTED = "android.bluetooth.adapter.action.DISCOVERY_STARTED";
    public static final String ACTION_LOCAL_NAME_CHANGED = "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED";
    @SystemApi
    public static final String ACTION_REQUEST_BLE_SCAN_ALWAYS_AVAILABLE = "android.bluetooth.adapter.action.REQUEST_BLE_SCAN_ALWAYS_AVAILABLE";
    public static final String ACTION_REQUEST_DISABLE = "android.bluetooth.adapter.action.REQUEST_DISABLE";
    public static final String ACTION_REQUEST_DISCOVERABLE = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";
    public static final String ACTION_SCAN_MODE_CHANGED = "android.bluetooth.adapter.action.SCAN_MODE_CHANGED";
    public static final String ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";
    @SystemApi
    public static final int ACTIVE_DEVICE_ALL = 2;
    @SystemApi
    public static final int ACTIVE_DEVICE_AUDIO = 0;
    @SystemApi
    public static final int ACTIVE_DEVICE_PHONE_CALL = 1;
    private static final int ADDRESS_LENGTH = 17;
    private static final String BLUETOOTH_FILTERING_CACHE_PROPERTY = "cache_key.bluetooth.is_offloaded_filtering_supported";
    private static final String BLUETOOTH_GET_ADAPTER_CONNECTION_STATE_CACHE_PROPERTY = "cache_key.bluetooth.get_adapter_connection_state";
    private static final String BLUETOOTH_GET_STATE_CACHE_PROPERTY = "cache_key.bluetooth.get_state";
    public static final String BLUETOOTH_MANAGER_SERVICE = "bluetooth_manager";
    private static final String BLUETOOTH_PROFILE_CACHE_PROPERTY = "cache_key.bluetooth.get_profile_connection_state";
    private static final boolean DBG = true;
    public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    private static final String DESCRIPTOR = "android.bluetooth.BluetoothAdapter";
    public static final int ERROR = Integer.MIN_VALUE;
    public static final String EXTRA_BLUETOOTH_ADDRESS = "android.bluetooth.adapter.extra.BLUETOOTH_ADDRESS";
    public static final String EXTRA_CONNECTION_STATE = "android.bluetooth.adapter.extra.CONNECTION_STATE";
    public static final String EXTRA_DISCOVERABLE_DURATION = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION";
    public static final String EXTRA_LOCAL_NAME = "android.bluetooth.adapter.extra.LOCAL_NAME";
    public static final String EXTRA_PREVIOUS_CONNECTION_STATE = "android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE";
    public static final String EXTRA_PREVIOUS_SCAN_MODE = "android.bluetooth.adapter.extra.PREVIOUS_SCAN_MODE";
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.adapter.extra.PREVIOUS_STATE";
    public static final String EXTRA_SCAN_MODE = "android.bluetooth.adapter.extra.SCAN_MODE";
    public static final String EXTRA_STATE = "android.bluetooth.adapter.extra.STATE";
    public static final int IO_CAPABILITY_IN = 2;
    public static final int IO_CAPABILITY_IO = 1;
    public static final int IO_CAPABILITY_KBDISP = 4;
    public static final int IO_CAPABILITY_MAX = 5;
    public static final int IO_CAPABILITY_NONE = 3;
    public static final int IO_CAPABILITY_OUT = 0;
    public static final int IO_CAPABILITY_UNKNOWN = 255;
    public static final int SCAN_MODE_CONNECTABLE = 21;
    public static final int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;
    public static final int SCAN_MODE_NONE = 20;
    public static final int SOCKET_CHANNEL_AUTO_STATIC_NO_SDP = -2;
    public static final int STATE_BLE_ON = 15;
    public static final int STATE_BLE_TURNING_OFF = 16;
    public static final int STATE_BLE_TURNING_ON = 14;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
    public static final int STATE_OFF = 10;
    public static final int STATE_ON = 12;
    public static final int STATE_TURNING_OFF = 13;
    public static final int STATE_TURNING_ON = 11;
    private static final String TAG = "BluetoothAdapter";
    private static BluetoothAdapter sAdapter;
    private static IBluetooth sService;
    private static boolean sServiceRegistered;
    private final AttributionSource mAttributionSource;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private BluetoothLeScanner mBluetoothLeScanner;
    private final Map<LeScanCallback, ScanCallback> mLeScanClients;
    private final IBluetoothManagerCallback mManagerCallback;
    private final IBluetoothManager mManagerService;
    private PeriodicAdvertisingManager mPeriodicAdvertisingManager;
    private IBluetooth mService;
    private final ReentrantReadWriteLock mServiceLock;
    private final IBinder mToken;
    private static final boolean VDBG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final UUID LE_PSM_CHARACTERISTIC_UUID = UUID.fromString("2d410339-82b6-42aa-b34e-e2e01df8cc1a");
    private static final Object sServiceLock = new Object();
    private static final IBluetoothManagerCallback sManagerCallback = new IBluetoothManagerCallback.Stub() { // from class: android.bluetooth.BluetoothAdapter.6
        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceUp(IBluetooth bluetoothService) {
            Log.d(BluetoothAdapter.TAG, "onBluetoothServiceUp: " + bluetoothService);
            synchronized (BluetoothAdapter.sServiceLock) {
                IBluetooth unused = BluetoothAdapter.sService = bluetoothService;
                for (IBluetoothManagerCallback cb : BluetoothAdapter.sProxyServiceStateCallbacks.keySet()) {
                    if (cb != null) {
                        try {
                            cb.onBluetoothServiceUp(bluetoothService);
                        } catch (Exception e) {
                            Log.e(BluetoothAdapter.TAG, "", e);
                        }
                    } else {
                        Log.d(BluetoothAdapter.TAG, "onBluetoothServiceUp: cb is null!");
                    }
                }
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceDown() {
            Log.d(BluetoothAdapter.TAG, "onBluetoothServiceDown");
            synchronized (BluetoothAdapter.sServiceLock) {
                IBluetooth unused = BluetoothAdapter.sService = null;
                for (IBluetoothManagerCallback cb : BluetoothAdapter.sProxyServiceStateCallbacks.keySet()) {
                    if (cb != null) {
                        try {
                            cb.onBluetoothServiceDown();
                        } catch (Exception e) {
                            Log.e(BluetoothAdapter.TAG, "", e);
                        }
                    } else {
                        Log.d(BluetoothAdapter.TAG, "onBluetoothServiceDown: cb is null!");
                    }
                }
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBrEdrDown() {
            if (BluetoothAdapter.VDBG) {
                Log.i(BluetoothAdapter.TAG, "onBrEdrDown");
            }
            synchronized (BluetoothAdapter.sServiceLock) {
                for (IBluetoothManagerCallback cb : BluetoothAdapter.sProxyServiceStateCallbacks.keySet()) {
                    if (cb != null) {
                        try {
                            cb.onBrEdrDown();
                        } catch (Exception e) {
                            Log.e(BluetoothAdapter.TAG, "", e);
                        }
                    } else {
                        Log.d(BluetoothAdapter.TAG, "onBrEdrDown: cb is null!");
                    }
                }
            }
        }
    };
    private static final WeakHashMap<IBluetoothManagerCallback, Void> sProxyServiceStateCallbacks = new WeakHashMap<>();
    private final Object mLock = new Object();
    private final Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> mMetadataListeners = new HashMap();
    private final Map<BluetoothConnectionCallback, Executor> mBluetoothConnectionCallbackExecutorMap = new HashMap();
    public IBluetoothAdapterExt mBluetoothAdapterExt = BluetoothAdapterExtPlugin.constructor.newInstance();
    private final IBluetoothMetadataListener mBluetoothMetadataListener = new AnonymousClass1();
    private final PropertyInvalidatedCache<Void, Integer> mBluetoothGetStateCache = new PropertyInvalidatedCache<Void, Integer>(8, BLUETOOTH_GET_STATE_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.2
        /* JADX INFO: Access modifiers changed from: protected */
        public Integer recompute(Void query) {
            try {
                if (BluetoothAdapter.this.mService != null) {
                    return Integer.valueOf(BluetoothAdapter.this.mService.getState());
                }
                return 10;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private final PropertyInvalidatedCache<Void, Boolean> mBluetoothFilteringCache = new PropertyInvalidatedCache<Void, Boolean>(8, BLUETOOTH_FILTERING_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.3
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(Void query) {
            try {
                try {
                    BluetoothAdapter.this.mServiceLock.readLock().lock();
                    if (BluetoothAdapter.this.mService != null) {
                        return Boolean.valueOf(BluetoothAdapter.this.mService.isOffloadedFilteringSupported());
                    }
                } catch (RemoteException e) {
                    Log.e(BluetoothAdapter.TAG, "failed to get isOffloadedFilteringSupported, error: ", e);
                }
                BluetoothAdapter.this.mServiceLock.readLock().unlock();
                return false;
            } finally {
                BluetoothAdapter.this.mServiceLock.readLock().unlock();
            }
        }
    };
    private final PropertyInvalidatedCache<Void, Integer> mBluetoothGetAdapterConnectionStateCache = new PropertyInvalidatedCache<Void, Integer>(8, BLUETOOTH_GET_ADAPTER_CONNECTION_STATE_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.4
        /* JADX INFO: Access modifiers changed from: protected */
        public Integer recompute(Void query) {
            try {
                return Integer.valueOf(BluetoothAdapter.this.mService.getAdapterConnectionState());
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            }
        }
    };
    private final PropertyInvalidatedCache<Integer, Integer> mGetProfileConnectionStateCache = new PropertyInvalidatedCache<Integer, Integer>(8, BLUETOOTH_PROFILE_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothAdapter.5
        /* JADX INFO: Access modifiers changed from: protected */
        public Integer recompute(Integer query) {
            try {
                try {
                    BluetoothAdapter.this.mServiceLock.readLock().lock();
                    if (BluetoothAdapter.this.mService != null) {
                        return Integer.valueOf(BluetoothAdapter.this.mService.getProfileConnectionState(query.intValue()));
                    }
                } catch (RemoteException e) {
                    Log.e(BluetoothAdapter.TAG, "getProfileConnectionState:", e);
                }
                BluetoothAdapter.this.mServiceLock.readLock().unlock();
                return 0;
            } finally {
                BluetoothAdapter.this.mServiceLock.readLock().unlock();
            }
        }

        public String queryToString(Integer query) {
            return String.format("getProfileConnectionState(profile=\"%d\")", query);
        }
    };
    private final IBluetoothConnectionCallback mConnectionCallback = new AnonymousClass9();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActiveDeviceUse {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AdapterState {
    }

    /* loaded from: classes.dex */
    public interface BluetoothStateChangeCallback {
        void onBluetoothStateChange(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IoCapability {
    }

    /* loaded from: classes.dex */
    public interface LeScanCallback {
        void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
    }

    @SystemApi
    /* loaded from: classes.dex */
    public interface OnMetadataChangedListener {
        void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
    }

    @SystemApi
    /* loaded from: classes.dex */
    public interface OobDataCallback {
        void onError(int i);

        void onOobData(int i, OobData oobData);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OobError {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ScanMode {
    }

    /* loaded from: classes.dex */
    public static abstract class ServiceLifecycleCallback {
        IBluetoothManagerCallback mRemote = new IBluetoothManagerCallback.Stub() { // from class: android.bluetooth.BluetoothAdapter.ServiceLifecycleCallback.1
            @Override // android.bluetooth.IBluetoothManagerCallback
            public void onBluetoothServiceUp(IBluetooth bluetoothService) {
                ServiceLifecycleCallback.this.onBluetoothServiceUp();
            }

            @Override // android.bluetooth.IBluetoothManagerCallback
            public void onBluetoothServiceDown() {
                ServiceLifecycleCallback.this.onBluetoothServiceDown();
            }

            @Override // android.bluetooth.IBluetoothManagerCallback
            public void onBrEdrDown() {
            }
        };

        public abstract void onBluetoothServiceDown();

        public abstract void onBluetoothServiceUp();
    }

    public static String nameForState(int state) {
        switch (state) {
            case 10:
                return "OFF";
            case 11:
                return "TURNING_ON";
            case 12:
                return "ON";
            case 13:
                return "TURNING_OFF";
            case 14:
                return "BLE_TURNING_ON";
            case 15:
                return "BLE_ON";
            case 16:
                return "BLE_TURNING_OFF";
            default:
                return "?!?!? (" + state + ")";
        }
    }

    /* renamed from: android.bluetooth.BluetoothAdapter$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends IBluetoothMetadataListener.Stub {
        AnonymousClass1() {
        }

        @Override // android.bluetooth.IBluetoothMetadataListener
        public void onMetadataChanged(final BluetoothDevice device, final int key, final byte[] value) {
            Attributable.setAttributionSource(device, BluetoothAdapter.this.mAttributionSource);
            synchronized (BluetoothAdapter.this.mMetadataListeners) {
                if (BluetoothAdapter.this.mMetadataListeners.containsKey(device)) {
                    List<Pair<OnMetadataChangedListener, Executor>> list = (List) BluetoothAdapter.this.mMetadataListeners.get(device);
                    for (Pair<OnMetadataChangedListener, Executor> pair : list) {
                        final OnMetadataChangedListener listener = (OnMetadataChangedListener) pair.first;
                        Executor executor = (Executor) pair.second;
                        executor.execute(new Runnable() { // from class: android.bluetooth.BluetoothAdapter$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                BluetoothAdapter.OnMetadataChangedListener.this.onMetadataChanged(device, key, value);
                            }
                        });
                    }
                }
            }
        }
    }

    @Deprecated
    public static synchronized BluetoothAdapter getDefaultAdapter() {
        BluetoothAdapter bluetoothAdapter;
        synchronized (BluetoothAdapter.class) {
            if (sAdapter == null) {
                sAdapter = createAdapter(BluetoothManager.resolveAttributionSource(null));
            }
            bluetoothAdapter = sAdapter;
        }
        return bluetoothAdapter;
    }

    public static BluetoothAdapter createAdapter(AttributionSource attributionSource) {
        IBinder binder = ServiceManager.getService("bluetooth_manager");
        if (binder != null) {
            return new BluetoothAdapter(IBluetoothManager.Stub.asInterface(binder), attributionSource);
        }
        Log.e(TAG, "Bluetooth binder is null");
        return null;
    }

    BluetoothAdapter(IBluetoothManager managerService, AttributionSource attributionSource) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.mServiceLock = reentrantReadWriteLock;
        AnonymousClass7 r1 = new AnonymousClass7();
        this.mManagerCallback = r1;
        Objects.requireNonNull(managerService);
        this.mManagerService = managerService;
        Objects.requireNonNull(attributionSource);
        this.mAttributionSource = attributionSource;
        synchronized (reentrantReadWriteLock.writeLock()) {
            this.mService = getBluetoothService(r1);
        }
        this.mLeScanClients = new HashMap();
        this.mToken = new Binder(DESCRIPTOR);
    }

    public BluetoothDevice getRemoteDevice(String address) {
        BluetoothDevice res = new BluetoothDevice(address);
        res.setAttributionSource(this.mAttributionSource);
        return res;
    }

    public BluetoothDevice getRemoteDevice(byte[] address) {
        if (address == null || address.length != 6) {
            throw new IllegalArgumentException("Bluetooth address must have 6 bytes");
        }
        BluetoothDevice res = new BluetoothDevice(String.format(Locale.US, "%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(address[0]), Byte.valueOf(address[1]), Byte.valueOf(address[2]), Byte.valueOf(address[3]), Byte.valueOf(address[4]), Byte.valueOf(address[5])));
        res.setAttributionSource(this.mAttributionSource);
        return res;
    }

    public BluetoothLeAdvertiser getBluetoothLeAdvertiser() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        if (!getLeAccess()) {
            return null;
        }
        synchronized (this.mLock) {
            if (this.mBluetoothLeAdvertiser == null) {
                this.mBluetoothLeAdvertiser = new BluetoothLeAdvertiser(this);
            }
            bluetoothLeAdvertiser = this.mBluetoothLeAdvertiser;
        }
        return bluetoothLeAdvertiser;
    }

    public PeriodicAdvertisingManager getPeriodicAdvertisingManager() {
        PeriodicAdvertisingManager periodicAdvertisingManager;
        if (!getLeAccess() || !isLePeriodicAdvertisingSupported()) {
            return null;
        }
        synchronized (this.mLock) {
            if (this.mPeriodicAdvertisingManager == null) {
                this.mPeriodicAdvertisingManager = new PeriodicAdvertisingManager(this);
            }
            periodicAdvertisingManager = this.mPeriodicAdvertisingManager;
        }
        return periodicAdvertisingManager;
    }

    public BluetoothLeScanner getBluetoothLeScanner() {
        BluetoothLeScanner bluetoothLeScanner;
        if (!getLeAccess()) {
            return null;
        }
        synchronized (this.mLock) {
            if (this.mBluetoothLeScanner == null) {
                this.mBluetoothLeScanner = new BluetoothLeScanner(this);
            }
            bluetoothLeScanner = this.mBluetoothLeScanner;
        }
        return bluetoothLeScanner;
    }

    public boolean isEnabled() {
        return getState() == 12;
    }

    @SystemApi
    public boolean isLeEnabled() {
        int state = getLeState();
        Log.d(TAG, "isLeEnabled(): " + nameForState(state));
        return state == 12 || state == 15 || state == 11 || state == 13;
    }

    @SystemApi
    public boolean disableBLE() {
        if (!isBleScanAlwaysAvailable()) {
            return false;
        }
        ActivityThread.currentPackageName();
        try {
            return this.mManagerService.disableBle(this.mAttributionSource, this.mToken);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public boolean enableBLE() {
        Log.d(TAG, "enableBLE(): called by: " + ActivityThread.currentPackageName());
        if (!isBleScanAlwaysAvailable()) {
            return false;
        }
        ActivityThread.currentPackageName();
        try {
            return this.mManagerService.enableBle(this.mAttributionSource, this.mToken);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public void disableBluetoothGetStateCache() {
        this.mBluetoothGetStateCache.disableLocal();
    }

    public static void invalidateBluetoothGetStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_GET_STATE_CACHE_PROPERTY);
    }

    private int getStateInternal() {
        int state = 10;
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    state = this.mBluetoothGetStateCache.query(null).intValue();
                }
            } catch (RuntimeException e) {
                if (e.getCause() instanceof RemoteException) {
                    Log.e(TAG, "", e.getCause());
                } else {
                    throw e;
                }
            }
            return state;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getState() {
        int state = getStateInternal();
        if (state == 15 || state == 14 || state == 16) {
            if (VDBG) {
                Log.d(TAG, "Consider " + nameForState(state) + " state as OFF");
            }
            state = 10;
        }
        if (VDBG) {
            Log.d(TAG, "" + hashCode() + ": getState(). Returning " + nameForState(state));
        }
        return state;
    }

    public int getLeState() {
        int state = getStateInternal();
        if (VDBG) {
            Log.d(TAG, "getLeState() returning " + nameForState(state));
        }
        return state;
    }

    boolean getLeAccess() {
        return getLeState() == 12 || getLeState() == 15;
    }

    public boolean enable() {
        Log.d(TAG, "enable(): called by: " + ActivityThread.currentPackageName());
        if (isEnabled()) {
            Log.d(TAG, "enable(): BT already enabled!");
            return true;
        } else if (!this.mBluetoothAdapterExt.hookEnable(Manifest.permission.BLUETOOTH_ADMIN, Binder.getCallingPid(), Binder.getCallingUid())) {
            return false;
        } else {
            try {
                return this.mManagerService.enable(this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                return false;
            }
        }
    }

    public boolean disable() {
        Log.d(TAG, "disable(): called by: " + ActivityThread.currentPackageName());
        if (!this.mBluetoothAdapterExt.hookDisable(Manifest.permission.BLUETOOTH_ADMIN, Binder.getCallingPid(), Binder.getCallingUid())) {
            return false;
        }
        try {
            return this.mManagerService.disable(this.mAttributionSource, true);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean disable(boolean persist) {
        Log.d(TAG, "disable(): called by: " + ActivityThread.currentPackageName());
        if (!this.mBluetoothAdapterExt.hookDisable(Manifest.permission.BLUETOOTH_ADMIN, Binder.getCallingPid(), Binder.getCallingUid())) {
            return false;
        }
        try {
            return this.mManagerService.disable(this.mAttributionSource, persist);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public String getAddress() {
        try {
            return this.mManagerService.getAddress(this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public String getName() {
        try {
            return this.mManagerService.getName(this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public int getNameLengthForAdvertise() {
        try {
            return this.mService.getNameLengthForAdvertise(this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return -1;
        }
    }

    public boolean factoryReset() {
        IBluetooth iBluetooth;
        IBluetoothManager iBluetoothManager;
        try {
            try {
                this.mServiceLock.readLock().lock();
                iBluetooth = this.mService;
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            if (iBluetooth == null || !iBluetooth.factoryReset(this.mAttributionSource) || (iBluetoothManager = this.mManagerService) == null || !iBluetoothManager.onFactoryReset(this.mAttributionSource)) {
                Log.e(TAG, "factoryReset(): Setting persist.bluetooth.factoryreset to retry later");
                SystemProperties.set("persist.bluetooth.factoryreset", "true");
                this.mServiceLock.readLock().unlock();
                return false;
            }
            return true;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public ParcelUuid[] getUuids() {
        try {
            if (getState() != 12) {
                return null;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getUuids(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return null;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setName(String name) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.setName(name, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public BluetoothClass getBluetoothClass() {
        try {
            if (getState() != 12) {
                return null;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getBluetoothClass(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return null;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setBluetoothClass(BluetoothClass bluetoothClass) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.setBluetoothClass(bluetoothClass, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getIoCapability() {
        try {
            if (getState() != 12) {
                return 255;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getIoCapability(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return 255;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setIoCapability(int capability) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.setIoCapability(capability, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getLeIoCapability() {
        try {
            if (getState() != 12) {
                return 255;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getLeIoCapability(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return 255;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setLeIoCapability(int capability) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.setLeIoCapability(capability, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getScanMode() {
        try {
            if (getState() != 12) {
                return 20;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getScanMode(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return 20;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setScanMode(int mode, long durationMillis) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    Log.i(TAG, "setScanMode(), mode: " + mode + ", duration: " + durationMillis + ", called by: " + ActivityThread.currentPackageName());
                    int durationSeconds = Math.toIntExact(durationMillis / 1000);
                    return this.mService.setScanMode(mode, durationSeconds, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            } catch (ArithmeticException e2) {
                Log.e(TAG, "setScanMode: Duration in seconds outside of the bounds of an int");
                throw new IllegalArgumentException("Duration not in bounds. In seconds, the durationMillis must be in the range of an int");
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean setScanMode(int mode) {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    Log.i(TAG, "setScanMode(), mode: " + mode + ", called by: " + ActivityThread.currentPackageName());
                    return this.mService.setScanMode(mode, getDiscoverableTimeout(), this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getDiscoverableTimeout() {
        try {
            if (getState() != 12) {
                return -1;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getDiscoverableTimeout(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return -1;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public void setDiscoverableTimeout(int timeout) {
        if (getState() == 12) {
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    IBluetooth iBluetooth = this.mService;
                    if (iBluetooth != null) {
                        iBluetooth.setDiscoverableTimeout(timeout, this.mAttributionSource);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
            } finally {
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    @SystemApi
    public long getDiscoveryEndMillis() {
        try {
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getDiscoveryEndMillis(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return -1L;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean startDiscovery() {
        Log.d(TAG, "startDiscovery(): called by: " + ActivityThread.currentPackageName());
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.startDiscovery(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean cancelDiscovery() {
        Log.d(TAG, "cancelDiscovery(): called by: " + ActivityThread.currentPackageName());
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.cancelDiscovery(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isDiscovering() {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isDiscovering(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    @SystemApi
    public boolean removeActiveDevice(int profiles) {
        if (profiles == 0 || profiles == 1 || profiles == 2) {
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        Log.d(TAG, "removeActiveDevice, profiles: " + profiles);
                        return this.mService.removeActiveDevice(profiles, this.mAttributionSource);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
                this.mServiceLock.readLock().unlock();
                return false;
            } finally {
                this.mServiceLock.readLock().unlock();
            }
        } else {
            Log.e(TAG, "Invalid profiles param value in removeActiveDevice");
            throw new IllegalArgumentException("Profiles must be one of BluetoothAdapter.ACTIVE_DEVICE_AUDIO, BluetoothAdapter.ACTIVE_DEVICE_PHONE_CALL, or BluetoothAdapter.ACTIVE_DEVICE_ALL");
        }
    }

    @SystemApi
    public boolean setActiveDevice(BluetoothDevice device, int profiles) {
        if (device == null) {
            Log.e(TAG, "setActiveDevice: Null device passed as parameter");
            throw new IllegalArgumentException("device cannot be null");
        } else if (profiles == 0 || profiles == 1 || profiles == 2) {
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        Log.d(TAG, "setActiveDevice, device: " + device + ", profiles: " + profiles);
                        return this.mService.setActiveDevice(device, profiles, this.mAttributionSource);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
                this.mServiceLock.readLock().unlock();
                return false;
            } finally {
                this.mServiceLock.readLock().unlock();
            }
        } else {
            Log.e(TAG, "Invalid profiles param value in setActiveDevice");
            throw new IllegalArgumentException("Profiles must be one of BluetoothAdapter.ACTIVE_DEVICE_AUDIO, BluetoothAdapter.ACTIVE_DEVICE_PHONE_CALL, or BluetoothAdapter.ACTIVE_DEVICE_ALL");
        }
    }

    public boolean connectAllEnabledProfiles(BluetoothDevice device) {
        try {
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.connectAllEnabledProfiles(device, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean disconnectAllEnabledProfiles(BluetoothDevice device) {
        try {
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.disconnectAllEnabledProfiles(device, this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isMultipleAdvertisementSupported() {
        try {
            if (getState() != 12) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isMultiAdvertisementSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isMultipleAdvertisementSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    @SystemApi
    public boolean isBleScanAlwaysAvailable() {
        try {
            return this.mManagerService.isBleScanAlwaysAvailable();
        } catch (RemoteException e) {
            Log.e(TAG, "remote expection when calling isBleScanAlwaysAvailable", e);
            return false;
        }
    }

    public void disableIsOffloadedFilteringSupportedCache() {
        this.mBluetoothFilteringCache.disableLocal();
    }

    public static void invalidateIsOffloadedFilteringSupportedCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_FILTERING_CACHE_PROPERTY);
    }

    public boolean isOffloadedFilteringSupported() {
        if (!getLeAccess()) {
            return false;
        }
        return this.mBluetoothFilteringCache.query(null).booleanValue();
    }

    public boolean isOffloadedScanBatchingSupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isOffloadedScanBatchingSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isOffloadedScanBatchingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLe2MPhySupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isLe2MPhySupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isExtendedAdvertisingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLeCodedPhySupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isLeCodedPhySupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isLeCodedPhySupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLeExtendedAdvertisingSupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isLeExtendedAdvertisingSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isLeExtendedAdvertisingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isLePeriodicAdvertisingSupported() {
        try {
            if (!getLeAccess()) {
                return false;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.isLePeriodicAdvertisingSupported();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get isLePeriodicAdvertisingSupported, error: ", e);
            }
            return false;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getLeMaximumAdvertisingDataLength() {
        try {
            if (!getLeAccess()) {
                return 0;
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getLeMaximumAdvertisingDataLength();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get getLeMaximumAdvertisingDataLength, error: ", e);
            }
            return 0;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    private boolean isHearingAidProfileSupported() {
        try {
            return this.mManagerService.isHearingAidProfileSupported();
        } catch (RemoteException e) {
            Log.e(TAG, "remote expection when calling isHearingAidProfileSupported", e);
            return false;
        }
    }

    public int getMaxConnectedAudioDevices() {
        try {
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return iBluetooth.getMaxConnectedAudioDevices(this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "failed to get getMaxConnectedAudioDevices, error: ", e);
            }
            this.mServiceLock.readLock().unlock();
            return 1;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isHardwareTrackingFiltersAvailable() {
        if (!getLeAccess()) {
            return false;
        }
        try {
            IBluetoothGatt iGatt = this.mManagerService.getBluetoothGatt();
            if (iGatt == null) {
                return false;
            }
            return iGatt.numHwTrackFiltersAvailable(this.mAttributionSource) != 0;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @Deprecated
    public BluetoothActivityEnergyInfo getControllerActivityEnergyInfo(int updateType) {
        SynchronousResultReceiver receiver = new SynchronousResultReceiver();
        requestControllerActivityEnergyInfo(receiver);
        try {
            SynchronousResultReceiver.Result result = receiver.awaitResult(1000L);
            if (result.bundle != null) {
                return (BluetoothActivityEnergyInfo) result.bundle.getParcelable("controller_activity");
            }
            return null;
        } catch (TimeoutException e) {
            Log.e(TAG, "getControllerActivityEnergyInfo timed out");
            return null;
        }
    }

    public void requestControllerActivityEnergyInfo(ResultReceiver result) {
        try {
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    iBluetooth.requestActivityInfo(result, this.mAttributionSource);
                    result = null;
                }
                this.mServiceLock.readLock().unlock();
                if (result == null) {
                    return;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "getControllerActivityEnergyInfoCallback: " + e);
                this.mServiceLock.readLock().unlock();
                if (result == null) {
                    return;
                }
            }
            result.send(0, null);
        } catch (Throwable th) {
            this.mServiceLock.readLock().unlock();
            if (result != null) {
                result.send(0, null);
            }
            throw th;
        }
    }

    public List<BluetoothDevice> getMostRecentlyConnectedDevices() {
        try {
            if (getState() != 12) {
                return new ArrayList();
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    return Attributable.setAttributionSource(iBluetooth.getMostRecentlyConnectedDevices(this.mAttributionSource), this.mAttributionSource);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
            this.mServiceLock.readLock().unlock();
            return new ArrayList();
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    /* JADX WARN: Finally extract failed */
    public Set<BluetoothDevice> getBondedDevices() {
        try {
            if (getState() != 12) {
                return toDeviceSet(Arrays.asList(new BluetoothDevice[0]));
            }
            try {
                this.mServiceLock.readLock().lock();
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    Set<BluetoothDevice> deviceSet = toDeviceSet(Attributable.setAttributionSource(Arrays.asList(iBluetooth.getBondedDevices(this.mAttributionSource)), this.mAttributionSource));
                    this.mServiceLock.readLock().unlock();
                    return deviceSet;
                }
                Set<BluetoothDevice> deviceSet2 = toDeviceSet(Arrays.asList(new BluetoothDevice[0]));
                this.mServiceLock.readLock().unlock();
                return deviceSet2;
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                this.mServiceLock.readLock().unlock();
                return null;
            }
        } catch (Throwable th) {
            this.mServiceLock.readLock().unlock();
            throw th;
        }
    }

    public List<Integer> getSupportedProfiles() {
        ArrayList<Integer> supportedProfiles = new ArrayList<>();
        try {
            synchronized (this.mManagerCallback) {
                IBluetooth iBluetooth = this.mService;
                if (iBluetooth != null) {
                    long supportedProfilesBitMask = iBluetooth.getSupportedProfiles();
                    for (int i = 0; i <= BluetoothLeAudioFactory.getInstance().getProfileCount(); i++) {
                        if (((1 << i) & supportedProfilesBitMask) != 0) {
                            supportedProfiles.add(Integer.valueOf(i));
                        }
                    }
                } else {
                    if (isHearingAidProfileSupported()) {
                        supportedProfiles.add(21);
                    }
                    BluetoothLeAudioFactory.getInstance().getSupportedProfiles(this.mManagerService, supportedProfiles);
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getSupportedProfiles:", e);
        }
        return supportedProfiles;
    }

    public void disableGetAdapterConnectionStateCache() {
        this.mBluetoothGetAdapterConnectionStateCache.disableLocal();
    }

    public static void invalidateGetAdapterConnectionStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_GET_ADAPTER_CONNECTION_STATE_CACHE_PROPERTY);
    }

    public int getConnectionState() {
        try {
            if (getState() != 12) {
                return 0;
            }
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    return this.mBluetoothGetAdapterConnectionStateCache.query(null).intValue();
                }
            } catch (RuntimeException e) {
                if (e.getCause() instanceof RemoteException) {
                    Log.e(TAG, "getConnectionState:", e.getCause());
                } else {
                    throw e;
                }
            }
            return 0;
        } finally {
            this.mServiceLock.readLock().unlock();
        }
    }

    public void disableGetProfileConnectionStateCache() {
        this.mGetProfileConnectionStateCache.disableLocal();
    }

    public static void invalidateGetProfileConnectionStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_PROFILE_CACHE_PROPERTY);
    }

    public int getProfileConnectionState(int profile) {
        if (getState() != 12) {
            return 0;
        }
        return this.mGetProfileConnectionStateCache.query(new Integer(profile)).intValue();
    }

    public BluetoothServerSocket listenUsingRfcommOn(int channel) throws IOException {
        return listenUsingRfcommOn(channel, false, false);
    }

    public BluetoothServerSocket listenUsingRfcommOn(int channel, boolean mitm, boolean min16DigitPin) throws IOException {
        BluetoothServerSocket socket = new BluetoothServerSocket(1, true, true, channel, mitm, min16DigitPin);
        int errno = socket.mSocket.bindListen();
        if (channel == -2) {
            socket.setChannel(socket.mSocket.getPort());
        }
        if (errno == 0) {
            return socket;
        }
        throw new IOException("Error: " + errno);
    }

    public BluetoothServerSocket listenUsingRfcommWithServiceRecord(String name, UUID uuid) throws IOException {
        return createNewRfcommSocketAndRecord(name, uuid, true, true);
    }

    public BluetoothServerSocket listenUsingInsecureRfcommWithServiceRecord(String name, UUID uuid) throws IOException {
        return createNewRfcommSocketAndRecord(name, uuid, false, false);
    }

    public BluetoothServerSocket listenUsingEncryptedRfcommWithServiceRecord(String name, UUID uuid) throws IOException {
        return createNewRfcommSocketAndRecord(name, uuid, false, true);
    }

    private BluetoothServerSocket createNewRfcommSocketAndRecord(String name, UUID uuid, boolean auth, boolean encrypt) throws IOException {
        BluetoothServerSocket socket = new BluetoothServerSocket(1, auth, encrypt, new ParcelUuid(uuid));
        socket.setServiceName(name);
        int errno = socket.mSocket.bindListen();
        if (errno == 0) {
            return socket;
        }
        throw new IOException("Error: " + errno);
    }

    public BluetoothServerSocket listenUsingInsecureRfcommOn(int port) throws IOException {
        BluetoothServerSocket socket = new BluetoothServerSocket(1, false, false, port);
        int errno = socket.mSocket.bindListen();
        if (port == -2) {
            socket.setChannel(socket.mSocket.getPort());
        }
        if (errno == 0) {
            return socket;
        }
        throw new IOException("Error: " + errno);
    }

    public BluetoothServerSocket listenUsingL2capOn(int port, boolean mitm, boolean min16DigitPin) throws IOException {
        BluetoothServerSocket socket = new BluetoothServerSocket(3, true, true, port, mitm, min16DigitPin);
        int errno = socket.mSocket.bindListen();
        if (port == -2) {
            int assignedChannel = socket.mSocket.getPort();
            Log.d(TAG, "listenUsingL2capOn: set assigned channel to " + assignedChannel);
            socket.setChannel(assignedChannel);
        }
        if (errno == 0) {
            return socket;
        }
        throw new IOException("Error: " + errno);
    }

    public BluetoothServerSocket listenUsingL2capOn(int port) throws IOException {
        return listenUsingL2capOn(port, false, false);
    }

    public BluetoothServerSocket listenUsingInsecureL2capOn(int port) throws IOException {
        Log.d(TAG, "listenUsingInsecureL2capOn: port=" + port);
        BluetoothServerSocket socket = new BluetoothServerSocket(3, false, false, port, false, false);
        int errno = socket.mSocket.bindListen();
        if (port == -2) {
            int assignedChannel = socket.mSocket.getPort();
            Log.d(TAG, "listenUsingInsecureL2capOn: set assigned channel to " + assignedChannel);
            socket.setChannel(assignedChannel);
        }
        if (errno == 0) {
            return socket;
        }
        throw new IOException("Error: " + errno);
    }

    public Pair<byte[], byte[]> readOutOfBandData() {
        return null;
    }

    public boolean getProfileProxy(Context context, BluetoothProfile.ServiceListener listener, int profile) {
        if (context == null || listener == null) {
            return false;
        }
        if (profile == 1) {
            new BluetoothHeadset(context, listener, this);
            return true;
        } else if (profile == 2) {
            new BluetoothA2dp(context, listener, this);
            return true;
        } else if (profile == 11) {
            new BluetoothA2dpSink(context, listener, this);
            return true;
        } else if (profile == 12) {
            new BluetoothAvrcpController(context, listener, this);
            return true;
        } else if (profile == 4) {
            new BluetoothHidHost(context, listener, this);
            return true;
        } else if (profile == 5) {
            new BluetoothPan(context, listener, this);
            return true;
        } else if (profile == 6) {
            new BluetoothPbap(context, listener, this);
            return true;
        } else if (profile == 3) {
            Log.e(TAG, "getProfileProxy(): BluetoothHealth is deprecated");
            return false;
        } else if (profile == 9) {
            new BluetoothMap(context, listener, this);
            return true;
        } else if (profile == 16) {
            new BluetoothHeadsetClient(context, listener, this);
            return true;
        } else if (profile == 10) {
            new BluetoothSap(context, listener, this);
            return true;
        } else if (profile == 17) {
            new BluetoothPbapClient(context, listener, this);
            return true;
        } else if (profile == 18) {
            new BluetoothMapClient(context, listener, this);
            return true;
        } else if (profile == 19) {
            new BluetoothHidDevice(context, listener, this);
            return true;
        } else if (profile != 21) {
            return BluetoothLeAudioFactory.getInstance().getProfileProxy(context, listener, profile);
        } else {
            if (!isHearingAidProfileSupported()) {
                return false;
            }
            new BluetoothHearingAid(context, listener, this);
            return true;
        }
    }

    public void closeProfileProxy(int profile, BluetoothProfile proxy) {
        if (proxy != null) {
            switch (profile) {
                case 1:
                    BluetoothHeadset headset = (BluetoothHeadset) proxy;
                    headset.close();
                    return;
                case 2:
                    BluetoothA2dp a2dp = (BluetoothA2dp) proxy;
                    a2dp.close();
                    return;
                case 3:
                case 13:
                case 14:
                case 15:
                case 20:
                default:
                    BluetoothLeAudioFactory.getInstance().closeProfileProxy(profile, proxy);
                    return;
                case 4:
                    BluetoothHidHost iDev = (BluetoothHidHost) proxy;
                    iDev.close();
                    return;
                case 5:
                    BluetoothPan pan = (BluetoothPan) proxy;
                    pan.close();
                    return;
                case 6:
                    BluetoothPbap pbap = (BluetoothPbap) proxy;
                    pbap.close();
                    return;
                case 7:
                    BluetoothGatt gatt = (BluetoothGatt) proxy;
                    gatt.close();
                    return;
                case 8:
                    BluetoothGattServer gattServer = (BluetoothGattServer) proxy;
                    gattServer.close();
                    return;
                case 9:
                    BluetoothMap map = (BluetoothMap) proxy;
                    map.close();
                    return;
                case 10:
                    BluetoothSap sap = (BluetoothSap) proxy;
                    sap.close();
                    return;
                case 11:
                    BluetoothA2dpSink a2dpSink = (BluetoothA2dpSink) proxy;
                    a2dpSink.close();
                    return;
                case 12:
                    BluetoothAvrcpController avrcp = (BluetoothAvrcpController) proxy;
                    avrcp.close();
                    return;
                case 16:
                    BluetoothHeadsetClient headsetClient = (BluetoothHeadsetClient) proxy;
                    headsetClient.close();
                    return;
                case 17:
                    BluetoothPbapClient pbapClient = (BluetoothPbapClient) proxy;
                    pbapClient.close();
                    return;
                case 18:
                    BluetoothMapClient mapClient = (BluetoothMapClient) proxy;
                    mapClient.close();
                    return;
                case 19:
                    BluetoothHidDevice hidDevice = (BluetoothHidDevice) proxy;
                    hidDevice.close();
                    return;
                case 21:
                    BluetoothHearingAid hearingAid = (BluetoothHearingAid) proxy;
                    hearingAid.close();
                    return;
            }
        }
    }

    /* renamed from: android.bluetooth.BluetoothAdapter$7  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass7 extends IBluetoothManagerCallback.Stub {
        AnonymousClass7() {
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceUp(IBluetooth bluetoothService) {
            synchronized (BluetoothAdapter.this.mServiceLock.writeLock()) {
                BluetoothAdapter.this.mService = bluetoothService;
            }
            synchronized (BluetoothAdapter.this.mMetadataListeners) {
                BluetoothAdapter.this.mMetadataListeners.forEach(new BiConsumer() { // from class: android.bluetooth.BluetoothAdapter$7$$ExternalSyntheticLambda0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        BluetoothAdapter.AnonymousClass7.this.lambda$onBluetoothServiceUp$0$BluetoothAdapter$7((BluetoothDevice) obj, (List) obj2);
                    }
                });
            }
            synchronized (BluetoothAdapter.this.mBluetoothConnectionCallbackExecutorMap) {
                if (!BluetoothAdapter.this.mBluetoothConnectionCallbackExecutorMap.isEmpty()) {
                    try {
                        BluetoothAdapter.this.mService.registerBluetoothConnectionCallback(BluetoothAdapter.this.mConnectionCallback, BluetoothAdapter.this.mAttributionSource);
                    } catch (RemoteException e) {
                        Log.e(BluetoothAdapter.TAG, "onBluetoothServiceUp: Failed to register bluetoothconnection callback", e);
                    }
                }
            }
        }

        public /* synthetic */ void lambda$onBluetoothServiceUp$0$BluetoothAdapter$7(BluetoothDevice device, List pair) {
            try {
                BluetoothAdapter.this.mService.registerMetadataListener(BluetoothAdapter.this.mBluetoothMetadataListener, device, BluetoothAdapter.this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(BluetoothAdapter.TAG, "Failed to register metadata listener", e);
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceDown() {
            synchronized (BluetoothAdapter.this.mServiceLock.writeLock()) {
                BluetoothAdapter.this.mService = null;
                if (BluetoothAdapter.this.mLeScanClients != null) {
                    BluetoothAdapter.this.mLeScanClients.clear();
                }
                if (BluetoothAdapter.this.mBluetoothLeAdvertiser != null) {
                    BluetoothAdapter.this.mBluetoothLeAdvertiser.cleanup();
                }
                if (BluetoothAdapter.this.mBluetoothLeScanner != null) {
                    BluetoothAdapter.this.mBluetoothLeScanner.cleanup();
                }
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBrEdrDown() {
        }
    }

    @SystemApi
    public boolean enableNoAutoConnect() {
        Log.d(TAG, "enableNoAutoConnect(): called by: " + ActivityThread.currentPackageName());
        if (isEnabled()) {
            Log.d(TAG, "enableNoAutoConnect(): BT already enabled!");
            return true;
        }
        try {
            return this.mManagerService.enableNoAutoConnect(this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class WrappedOobDataCallback extends IBluetoothOobDataCallback.Stub {
        private final OobDataCallback mCallback;
        private final Executor mExecutor;

        WrappedOobDataCallback(OobDataCallback callback, Executor executor) {
            Objects.requireNonNull(callback);
            Objects.requireNonNull(executor);
            this.mCallback = callback;
            this.mExecutor = executor;
        }

        @Override // android.bluetooth.IBluetoothOobDataCallback
        public void onOobData(final int transport, final OobData oobData) {
            this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothAdapter.WrappedOobDataCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    WrappedOobDataCallback.this.mCallback.onOobData(transport, oobData);
                }
            });
        }

        @Override // android.bluetooth.IBluetoothOobDataCallback
        public void onError(final int errorCode) {
            this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothAdapter.WrappedOobDataCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    WrappedOobDataCallback.this.mCallback.onError(errorCode);
                }
            });
        }
    }

    @SystemApi
    public void generateLocalOobData(int transport, Executor executor, OobDataCallback callback) {
        if (transport == 1 || transport == 2) {
            Objects.requireNonNull(callback);
            if (!isEnabled()) {
                Log.w(TAG, "generateLocalOobData(): Adapter isn't enabled!");
                callback.onError(1);
                return;
            }
            try {
                this.mService.generateLocalOobData(transport, new WrappedOobDataCallback(callback, executor), this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        } else {
            throw new IllegalArgumentException("Invalid transport '" + transport + "'!");
        }
    }

    public boolean changeApplicationBluetoothState(boolean on, BluetoothStateChangeCallback callback) {
        return false;
    }

    /* loaded from: classes.dex */
    public class StateChangeCallbackWrapper extends IBluetoothStateChangeCallback.Stub {
        private BluetoothStateChangeCallback mCallback;

        StateChangeCallbackWrapper(BluetoothStateChangeCallback callback) {
            this.mCallback = callback;
        }

        @Override // android.bluetooth.IBluetoothStateChangeCallback
        public void onBluetoothStateChange(boolean on) {
            this.mCallback.onBluetoothStateChange(on);
        }
    }

    private Set<BluetoothDevice> toDeviceSet(List<BluetoothDevice> devices) {
        Set<BluetoothDevice> deviceSet = new HashSet<>(devices);
        return Collections.unmodifiableSet(deviceSet);
    }

    protected void finalize() throws Throwable {
        try {
            removeServiceStateCallback(this.mManagerCallback);
            if (VDBG) {
                Log.d(TAG, "" + hashCode() + " finalize().");
            }
            sAdapter = null;
        } finally {
            super.finalize();
        }
    }

    public static boolean checkBluetoothAddress(String address) {
        if (address == null || address.length() != 17) {
            return false;
        }
        for (int i = 0; i < 17; i++) {
            char c = address.charAt(i);
            switch (i % 3) {
                case 0:
                case 1:
                    if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                        return false;
                    }
                    break;
                case 2:
                    if (c == ':') {
                        break;
                    } else {
                        return false;
                    }
            }
        }
        return true;
    }

    public static boolean isAddressRandomStatic(String address) {
        Objects.requireNonNull(address);
        return checkBluetoothAddress(address) && (Integer.parseInt(address.split(SettingsStringUtil.DELIMITER)[0], 16) & 192) == 192;
    }

    public IBluetoothManager getBluetoothManager() {
        return this.mManagerService;
    }

    public AttributionSource getAttributionSource() {
        return this.mAttributionSource;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IBluetooth getBluetoothService() {
        IBluetooth iBluetooth;
        synchronized (sServiceLock) {
            if (!sProxyServiceStateCallbacks.isEmpty()) {
                iBluetooth = sService;
            } else {
                throw new IllegalStateException("Anonymous service access requires at least one lifecycle in process");
            }
        }
        return iBluetooth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IBluetooth getBluetoothService(IBluetoothManagerCallback cb) {
        IBluetooth iBluetooth;
        Objects.requireNonNull(cb);
        synchronized (sServiceLock) {
            sProxyServiceStateCallbacks.put(cb, null);
            registerOrUnregisterAdapterLocked();
            iBluetooth = sService;
        }
        return iBluetooth;
    }

    void removeServiceStateCallback(IBluetoothManagerCallback cb) {
        Objects.requireNonNull(cb);
        synchronized (sServiceLock) {
            sProxyServiceStateCallbacks.remove(cb);
            registerOrUnregisterAdapterLocked();
        }
    }

    private void registerOrUnregisterAdapterLocked() {
        boolean isRegistered = sServiceRegistered;
        boolean wantRegistered = !sProxyServiceStateCallbacks.isEmpty();
        if (isRegistered != wantRegistered) {
            if (wantRegistered) {
                try {
                    sService = this.mManagerService.registerAdapter(sManagerCallback);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } else {
                try {
                    this.mManagerService.unregisterAdapter(sManagerCallback);
                    sService = null;
                } catch (RemoteException e2) {
                    throw e2.rethrowFromSystemServer();
                }
            }
            sServiceRegistered = wantRegistered;
        }
    }

    public boolean registerServiceLifecycleCallback(ServiceLifecycleCallback callback) {
        return getBluetoothService(callback.mRemote) != null;
    }

    public void unregisterServiceLifecycleCallback(ServiceLifecycleCallback callback) {
        removeServiceStateCallback(callback.mRemote);
    }

    @Deprecated
    public boolean startLeScan(LeScanCallback callback) {
        return startLeScan(null, callback);
    }

    @Deprecated
    public boolean startLeScan(final UUID[] serviceUuids, final LeScanCallback callback) {
        Log.d(TAG, "startLeScan(): " + Arrays.toString(serviceUuids));
        Log.d(TAG, "startLeScan(): called by: " + ActivityThread.currentPackageName());
        if (callback == null) {
            Log.e(TAG, "startLeScan: null callback");
            return false;
        }
        BluetoothLeScanner scanner = getBluetoothLeScanner();
        if (scanner == null) {
            Log.e(TAG, "startLeScan: cannot get BluetoothLeScanner");
            return false;
        }
        synchronized (this.mLeScanClients) {
            if (this.mLeScanClients.containsKey(callback)) {
                Log.e(TAG, "LE Scan has already started");
                return false;
            }
            try {
                IBluetoothGatt iGatt = this.mManagerService.getBluetoothGatt();
                if (iGatt == null) {
                    return false;
                }
                ScanCallback scanCallback = new ScanCallback() { // from class: android.bluetooth.BluetoothAdapter.8
                    @Override // android.bluetooth.le.ScanCallback
                    public void onScanResult(int callbackType, ScanResult result) {
                        UUID[] uuidArr;
                        if (callbackType != 1) {
                            Log.e(BluetoothAdapter.TAG, "LE Scan has already started");
                            return;
                        }
                        ScanRecord scanRecord = result.getScanRecord();
                        if (scanRecord != null) {
                            if (serviceUuids != null) {
                                List<ParcelUuid> uuids = new ArrayList<>();
                                for (UUID uuid : serviceUuids) {
                                    uuids.add(new ParcelUuid(uuid));
                                }
                                List<ParcelUuid> scanServiceUuids = scanRecord.getServiceUuids();
                                if (scanServiceUuids == null || !scanServiceUuids.containsAll(uuids)) {
                                    Log.d(BluetoothAdapter.TAG, "uuids does not match");
                                    return;
                                }
                            }
                            callback.onLeScan(result.getDevice(), result.getRssi(), scanRecord.getBytes());
                        }
                    }
                };
                ScanSettings settings = new ScanSettings.Builder().setCallbackType(1).setScanMode(2).build();
                List<ScanFilter> filters = new ArrayList<>();
                if (serviceUuids != null && serviceUuids.length > 0) {
                    ScanFilter filter = new ScanFilter.Builder().setServiceUuid(new ParcelUuid(serviceUuids[0])).build();
                    filters.add(filter);
                }
                scanner.startScan(filters, settings, scanCallback);
                this.mLeScanClients.put(callback, scanCallback);
                return true;
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                return false;
            }
        }
    }

    @Deprecated
    public void stopLeScan(LeScanCallback callback) {
        Log.d(TAG, "stopLeScan()");
        Log.d(TAG, "stopLeScan(): called by: " + ActivityThread.currentPackageName());
        BluetoothLeScanner scanner = getBluetoothLeScanner();
        if (scanner != null) {
            synchronized (this.mLeScanClients) {
                ScanCallback scanCallback = this.mLeScanClients.remove(callback);
                if (scanCallback == null) {
                    Log.d(TAG, "scan not started yet");
                } else {
                    scanner.stopScan(scanCallback);
                }
            }
        }
    }

    public BluetoothServerSocket listenUsingL2capChannel() throws IOException {
        BluetoothServerSocket socket = new BluetoothServerSocket(4, true, true, -2, false, false);
        int errno = socket.mSocket.bindListen();
        if (errno == 0) {
            int assignedPsm = socket.mSocket.getPort();
            if (assignedPsm != 0) {
                Log.d(TAG, "listenUsingL2capChannel: set assigned PSM to " + assignedPsm);
                socket.setChannel(assignedPsm);
                return socket;
            }
            throw new IOException("Error: Unable to assign PSM value");
        }
        throw new IOException("Error: " + errno);
    }

    public BluetoothServerSocket listenUsingInsecureL2capChannel() throws IOException {
        BluetoothServerSocket socket = new BluetoothServerSocket(4, false, false, -2, false, false);
        int errno = socket.mSocket.bindListen();
        if (errno == 0) {
            int assignedPsm = socket.mSocket.getPort();
            if (assignedPsm != 0) {
                Log.d(TAG, "listenUsingInsecureL2capChannel: set assigned PSM to " + assignedPsm);
                socket.setChannel(assignedPsm);
                return socket;
            }
            throw new IOException("Error: Unable to assign PSM value");
        }
        throw new IOException("Error: " + errno);
    }

    @SystemApi
    public boolean addOnMetadataChangedListener(BluetoothDevice device, Executor executor, final OnMetadataChangedListener listener) {
        boolean ret;
        Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> map;
        Log.d(TAG, "addOnMetadataChangedListener()");
        IBluetooth service = this.mService;
        if (service == null) {
            Log.e(TAG, "Bluetooth is not enabled. Cannot register metadata listener");
            return false;
        } else if (listener == null) {
            throw new NullPointerException("listener is null");
        } else if (device == null) {
            throw new NullPointerException("device is null");
        } else if (executor != null) {
            synchronized (this.mMetadataListeners) {
                List<Pair<OnMetadataChangedListener, Executor>> listenerList = this.mMetadataListeners.get(device);
                if (listenerList == null) {
                    listenerList = new ArrayList();
                    this.mMetadataListeners.put(device, listenerList);
                } else if (listenerList.stream().anyMatch(new Predicate() { // from class: android.bluetooth.BluetoothAdapter$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean equals;
                        equals = ((BluetoothAdapter.OnMetadataChangedListener) ((Pair) obj).first).equals(BluetoothAdapter.OnMetadataChangedListener.this);
                        return equals;
                    }
                })) {
                    throw new IllegalArgumentException("listener was already regestered for the device");
                }
                Pair<OnMetadataChangedListener, Executor> listenerPair = new Pair<>(listener, executor);
                listenerList.add(listenerPair);
                ret = false;
                try {
                    ret = service.registerMetadataListener(this.mBluetoothMetadataListener, device, this.mAttributionSource);
                } catch (RemoteException e) {
                    Log.e(TAG, "registerMetadataListener fail", e);
                    if (0 == 0) {
                        listenerList.remove(listenerPair);
                        if (listenerList.isEmpty()) {
                            map = this.mMetadataListeners;
                        }
                    }
                }
                if (!ret) {
                    listenerList.remove(listenerPair);
                    if (listenerList.isEmpty()) {
                        map = this.mMetadataListeners;
                        map.remove(device);
                    }
                }
            }
            return ret;
        } else {
            throw new NullPointerException("executor is null");
        }
    }

    @SystemApi
    public boolean removeOnMetadataChangedListener(BluetoothDevice device, final OnMetadataChangedListener listener) {
        Log.d(TAG, "removeOnMetadataChangedListener()");
        if (device == null) {
            throw new NullPointerException("device is null");
        } else if (listener != null) {
            synchronized (this.mMetadataListeners) {
                if (this.mMetadataListeners.containsKey(device)) {
                    this.mMetadataListeners.get(device).removeIf(new Predicate() { // from class: android.bluetooth.BluetoothAdapter$$ExternalSyntheticLambda1
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            boolean equals;
                            equals = ((BluetoothAdapter.OnMetadataChangedListener) ((Pair) obj).first).equals(BluetoothAdapter.OnMetadataChangedListener.this);
                            return equals;
                        }
                    });
                    if (!this.mMetadataListeners.get(device).isEmpty()) {
                        return true;
                    }
                    this.mMetadataListeners.remove(device);
                    IBluetooth service = this.mService;
                    if (service == null) {
                        return true;
                    }
                    try {
                        return service.unregisterMetadataListener(device, this.mAttributionSource);
                    } catch (RemoteException e) {
                        Log.e(TAG, "unregisterMetadataListener fail", e);
                        return false;
                    }
                } else {
                    throw new IllegalArgumentException("device was not registered");
                }
            }
        } else {
            throw new NullPointerException("listener is null");
        }
    }

    /* renamed from: android.bluetooth.BluetoothAdapter$9  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass9 extends IBluetoothConnectionCallback.Stub {
        AnonymousClass9() {
        }

        @Override // android.bluetooth.IBluetoothConnectionCallback
        public void onDeviceConnected(final BluetoothDevice device) {
            Attributable.setAttributionSource(device, BluetoothAdapter.this.mAttributionSource);
            for (Map.Entry<BluetoothConnectionCallback, Executor> callbackExecutorEntry : BluetoothAdapter.this.mBluetoothConnectionCallbackExecutorMap.entrySet()) {
                final BluetoothConnectionCallback callback = callbackExecutorEntry.getKey();
                Executor executor = callbackExecutorEntry.getValue();
                executor.execute(new Runnable() { // from class: android.bluetooth.BluetoothAdapter$9$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothAdapter.BluetoothConnectionCallback.this.onDeviceConnected(device);
                    }
                });
            }
        }

        @Override // android.bluetooth.IBluetoothConnectionCallback
        public void onDeviceDisconnected(final BluetoothDevice device, final int hciReason) {
            Attributable.setAttributionSource(device, BluetoothAdapter.this.mAttributionSource);
            for (Map.Entry<BluetoothConnectionCallback, Executor> callbackExecutorEntry : BluetoothAdapter.this.mBluetoothConnectionCallbackExecutorMap.entrySet()) {
                final BluetoothConnectionCallback callback = callbackExecutorEntry.getKey();
                Executor executor = callbackExecutorEntry.getValue();
                executor.execute(new Runnable() { // from class: android.bluetooth.BluetoothAdapter$9$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothAdapter.BluetoothConnectionCallback.this.onDeviceDisconnected(device, hciReason);
                    }
                });
            }
        }
    }

    public boolean registerBluetoothConnectionCallback(Executor executor, BluetoothConnectionCallback callback) {
        ReentrantReadWriteLock.ReadLock readLock;
        Log.d(TAG, "registerBluetoothConnectionCallback()");
        if (callback == null) {
            return false;
        }
        synchronized (this.mBluetoothConnectionCallbackExecutorMap) {
            if (this.mBluetoothConnectionCallbackExecutorMap.isEmpty()) {
                try {
                    this.mServiceLock.readLock().lock();
                    IBluetooth iBluetooth = this.mService;
                    if (iBluetooth != null) {
                        if (!iBluetooth.registerBluetoothConnectionCallback(this.mConnectionCallback, this.mAttributionSource)) {
                            this.mServiceLock.readLock().unlock();
                            return false;
                        }
                    }
                    readLock = this.mServiceLock.readLock();
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                    this.mBluetoothConnectionCallbackExecutorMap.remove(callback);
                    readLock = this.mServiceLock.readLock();
                }
                readLock.unlock();
            }
            if (!this.mBluetoothConnectionCallbackExecutorMap.containsKey(callback)) {
                this.mBluetoothConnectionCallbackExecutorMap.put(callback, executor);
                return true;
            }
            throw new IllegalArgumentException("This callback has already been registered");
        }
    }

    public boolean unregisterBluetoothConnectionCallback(BluetoothConnectionCallback callback) {
        Log.d(TAG, "unregisterBluetoothConnectionCallback()");
        if (callback == null) {
            return false;
        }
        synchronized (this.mBluetoothConnectionCallbackExecutorMap) {
            if (this.mBluetoothConnectionCallbackExecutorMap.remove(callback) != null) {
                return false;
            }
            try {
                if (!this.mBluetoothConnectionCallbackExecutorMap.isEmpty()) {
                    return true;
                }
                try {
                    this.mServiceLock.readLock().lock();
                    IBluetooth iBluetooth = this.mService;
                    if (iBluetooth != null) {
                        return iBluetooth.unregisterBluetoothConnectionCallback(this.mConnectionCallback, this.mAttributionSource);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
                return false;
            } finally {
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class BluetoothConnectionCallback {

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface DisconnectReason {
        }

        public void onDeviceConnected(BluetoothDevice device) {
        }

        public void onDeviceDisconnected(BluetoothDevice device, int reason) {
        }

        public static String disconnectReasonText(int reason) {
            switch (reason) {
                case 1100:
                    return "Local request";
                case 1101:
                    return "Remote request";
                case 1102:
                    return "Local error";
                case 1103:
                    return "Remote error";
                case 1104:
                    return "Timeout";
                case 1105:
                    return "Security";
                case 1106:
                    return "System policy";
                case 1107:
                    return "Resource constrained";
                case 1108:
                    return "Connection already exists";
                case 1109:
                    return "Bad parameters";
                case Integer.MAX_VALUE:
                    return "Reason unknown";
                default:
                    return "Unrecognized disconnect reason: " + reason;
            }
        }
    }

    public static int priorityToConnectionPolicy(int priority) {
        switch (priority) {
            case -1:
                return -1;
            case 0:
                return 0;
            case 100:
                return 100;
            case 1000:
                return 100;
            default:
                Log.e(TAG, "setPriority: Invalid priority: " + priority);
                return -1;
        }
    }

    public static int connectionPolicyToPriority(int connectionPolicy) {
        switch (connectionPolicy) {
            case -1:
                return -1;
            case 0:
                return 0;
            case 100:
                return 100;
            default:
                return -1;
        }
    }
}
