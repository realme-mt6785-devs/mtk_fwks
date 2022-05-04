package android.net.nsd;

import android.app.compat.CompatChanges;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.AsyncChannel;
import com.android.internal.util.Preconditions;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes2.dex */
public final class NsdManager {
    public static final String ACTION_NSD_STATE_CHANGED = "android.net.nsd.STATE_CHANGED";
    private static final int BASE = 393216;
    public static final int DAEMON_CLEANUP = 393237;
    public static final int DAEMON_STARTUP = 393238;
    private static final boolean DBG = false;
    public static final int DISABLE = 393241;
    public static final int DISCOVER_SERVICES = 393217;
    public static final int DISCOVER_SERVICES_FAILED = 393219;
    public static final int DISCOVER_SERVICES_STARTED = 393218;
    public static final int ENABLE = 393240;
    private static final SparseArray<String> EVENT_NAMES;
    public static final String EXTRA_NSD_STATE = "nsd_state";
    public static final int FAILURE_ALREADY_ACTIVE = 3;
    public static final int FAILURE_INTERNAL_ERROR = 0;
    public static final int FAILURE_MAX_LIMIT = 4;
    private static final int FIRST_LISTENER_KEY = 1;
    public static final int NATIVE_DAEMON_EVENT = 393242;
    public static final int NSD_STATE_DISABLED = 1;
    public static final int NSD_STATE_ENABLED = 2;
    public static final int PROTOCOL_DNS_SD = 1;
    public static final int REGISTER_SERVICE = 393225;
    public static final int REGISTER_SERVICE_FAILED = 393226;
    public static final int REGISTER_SERVICE_SUCCEEDED = 393227;
    public static final int RESOLVE_SERVICE = 393234;
    public static final int RESOLVE_SERVICE_FAILED = 393235;
    public static final int RESOLVE_SERVICE_SUCCEEDED = 393236;
    public static final long RUN_NATIVE_NSD_ONLY_IF_LEGACY_APPS = 191844585;
    public static final int SERVICE_FOUND = 393220;
    public static final int SERVICE_LOST = 393221;
    public static final int STOP_DISCOVERY = 393222;
    public static final int STOP_DISCOVERY_FAILED = 393223;
    public static final int STOP_DISCOVERY_SUCCEEDED = 393224;
    private static final String TAG = NsdManager.class.getSimpleName();
    public static final int UNREGISTER_SERVICE = 393228;
    public static final int UNREGISTER_SERVICE_FAILED = 393229;
    public static final int UNREGISTER_SERVICE_SUCCEEDED = 393230;
    private final Context mContext;
    private ServiceHandler mHandler;
    private final INsdManager mService;
    private int mListenerKey = 1;
    private final SparseArray mListenerMap = new SparseArray();
    private final SparseArray<NsdServiceInfo> mServiceMap = new SparseArray<>();
    private final Object mMapLock = new Object();
    private final AsyncChannel mAsyncChannel = new AsyncChannel();
    private final CountDownLatch mConnected = new CountDownLatch(1);

    /* loaded from: classes2.dex */
    public interface DiscoveryListener {
        void onDiscoveryStarted(String str);

        void onDiscoveryStopped(String str);

        void onServiceFound(NsdServiceInfo nsdServiceInfo);

        void onServiceLost(NsdServiceInfo nsdServiceInfo);

        void onStartDiscoveryFailed(String str, int i);

        void onStopDiscoveryFailed(String str, int i);
    }

    /* loaded from: classes2.dex */
    public interface RegistrationListener {
        void onRegistrationFailed(NsdServiceInfo nsdServiceInfo, int i);

        void onServiceRegistered(NsdServiceInfo nsdServiceInfo);

        void onServiceUnregistered(NsdServiceInfo nsdServiceInfo);

        void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo, int i);
    }

    /* loaded from: classes2.dex */
    public interface ResolveListener {
        void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i);

        void onServiceResolved(NsdServiceInfo nsdServiceInfo);
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        EVENT_NAMES = sparseArray;
        sparseArray.put(DISCOVER_SERVICES, "DISCOVER_SERVICES");
        sparseArray.put(DISCOVER_SERVICES_STARTED, "DISCOVER_SERVICES_STARTED");
        sparseArray.put(DISCOVER_SERVICES_FAILED, "DISCOVER_SERVICES_FAILED");
        sparseArray.put(SERVICE_FOUND, "SERVICE_FOUND");
        sparseArray.put(SERVICE_LOST, "SERVICE_LOST");
        sparseArray.put(STOP_DISCOVERY, "STOP_DISCOVERY");
        sparseArray.put(STOP_DISCOVERY_FAILED, "STOP_DISCOVERY_FAILED");
        sparseArray.put(STOP_DISCOVERY_SUCCEEDED, "STOP_DISCOVERY_SUCCEEDED");
        sparseArray.put(REGISTER_SERVICE, "REGISTER_SERVICE");
        sparseArray.put(REGISTER_SERVICE_FAILED, "REGISTER_SERVICE_FAILED");
        sparseArray.put(REGISTER_SERVICE_SUCCEEDED, "REGISTER_SERVICE_SUCCEEDED");
        sparseArray.put(UNREGISTER_SERVICE, "UNREGISTER_SERVICE");
        sparseArray.put(UNREGISTER_SERVICE_FAILED, "UNREGISTER_SERVICE_FAILED");
        sparseArray.put(UNREGISTER_SERVICE_SUCCEEDED, "UNREGISTER_SERVICE_SUCCEEDED");
        sparseArray.put(RESOLVE_SERVICE, "RESOLVE_SERVICE");
        sparseArray.put(RESOLVE_SERVICE_FAILED, "RESOLVE_SERVICE_FAILED");
        sparseArray.put(RESOLVE_SERVICE_SUCCEEDED, "RESOLVE_SERVICE_SUCCEEDED");
        sparseArray.put(DAEMON_CLEANUP, "DAEMON_CLEANUP");
        sparseArray.put(DAEMON_STARTUP, "DAEMON_STARTUP");
        sparseArray.put(ENABLE, "ENABLE");
        sparseArray.put(DISABLE, "DISABLE");
        sparseArray.put(NATIVE_DAEMON_EVENT, "NATIVE_DAEMON_EVENT");
    }

    public static String nameOf(int event) {
        String name = EVENT_NAMES.get(event);
        if (name == null) {
            return Integer.toString(event);
        }
        return name;
    }

    public NsdManager(Context context, INsdManager service) {
        this.mService = service;
        this.mContext = context;
        init();
    }

    public void disconnect() {
        this.mAsyncChannel.disconnect();
        this.mHandler.getLooper().quitSafely();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ServiceHandler extends Handler {
        ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object listener;
            NsdServiceInfo ns;
            int what = message.what;
            int key = message.arg2;
            switch (what) {
                case 69632:
                    NsdManager.this.mAsyncChannel.sendMessage(AsyncChannel.CMD_CHANNEL_FULL_CONNECTION);
                    return;
                case AsyncChannel.CMD_CHANNEL_FULL_CONNECTION /* 69633 */:
                case AsyncChannel.CMD_CHANNEL_DISCONNECT /* 69635 */:
                default:
                    synchronized (NsdManager.this.mMapLock) {
                        listener = NsdManager.this.mListenerMap.get(key);
                        ns = (NsdServiceInfo) NsdManager.this.mServiceMap.get(key);
                    }
                    if (listener == null) {
                        String str = NsdManager.TAG;
                        Log.d(str, "Stale key " + message.arg2);
                        return;
                    }
                    switch (what) {
                        case NsdManager.DISCOVER_SERVICES_STARTED /* 393218 */:
                            String s = NsdManager.getNsdServiceInfoType((NsdServiceInfo) message.obj);
                            ((DiscoveryListener) listener).onDiscoveryStarted(s);
                            return;
                        case NsdManager.DISCOVER_SERVICES_FAILED /* 393219 */:
                            NsdManager.this.removeListener(key);
                            ((DiscoveryListener) listener).onStartDiscoveryFailed(NsdManager.getNsdServiceInfoType(ns), message.arg1);
                            return;
                        case NsdManager.SERVICE_FOUND /* 393220 */:
                            ((DiscoveryListener) listener).onServiceFound((NsdServiceInfo) message.obj);
                            return;
                        case NsdManager.SERVICE_LOST /* 393221 */:
                            ((DiscoveryListener) listener).onServiceLost((NsdServiceInfo) message.obj);
                            return;
                        case NsdManager.STOP_DISCOVERY /* 393222 */:
                        case NsdManager.REGISTER_SERVICE /* 393225 */:
                        case NsdManager.UNREGISTER_SERVICE /* 393228 */:
                        case 393231:
                        case 393232:
                        case 393233:
                        case NsdManager.RESOLVE_SERVICE /* 393234 */:
                        default:
                            String str2 = NsdManager.TAG;
                            Log.d(str2, "Ignored " + message);
                            return;
                        case NsdManager.STOP_DISCOVERY_FAILED /* 393223 */:
                            NsdManager.this.removeListener(key);
                            ((DiscoveryListener) listener).onStopDiscoveryFailed(NsdManager.getNsdServiceInfoType(ns), message.arg1);
                            return;
                        case NsdManager.STOP_DISCOVERY_SUCCEEDED /* 393224 */:
                            NsdManager.this.removeListener(key);
                            ((DiscoveryListener) listener).onDiscoveryStopped(NsdManager.getNsdServiceInfoType(ns));
                            return;
                        case NsdManager.REGISTER_SERVICE_FAILED /* 393226 */:
                            NsdManager.this.removeListener(key);
                            ((RegistrationListener) listener).onRegistrationFailed(ns, message.arg1);
                            return;
                        case NsdManager.REGISTER_SERVICE_SUCCEEDED /* 393227 */:
                            ((RegistrationListener) listener).onServiceRegistered((NsdServiceInfo) message.obj);
                            return;
                        case NsdManager.UNREGISTER_SERVICE_FAILED /* 393229 */:
                            NsdManager.this.removeListener(key);
                            ((RegistrationListener) listener).onUnregistrationFailed(ns, message.arg1);
                            return;
                        case NsdManager.UNREGISTER_SERVICE_SUCCEEDED /* 393230 */:
                            NsdManager.this.removeListener(message.arg2);
                            ((RegistrationListener) listener).onServiceUnregistered(ns);
                            return;
                        case NsdManager.RESOLVE_SERVICE_FAILED /* 393235 */:
                            NsdManager.this.removeListener(key);
                            ((ResolveListener) listener).onResolveFailed(ns, message.arg1);
                            return;
                        case NsdManager.RESOLVE_SERVICE_SUCCEEDED /* 393236 */:
                            NsdManager.this.removeListener(key);
                            ((ResolveListener) listener).onServiceResolved((NsdServiceInfo) message.obj);
                            return;
                    }
                case AsyncChannel.CMD_CHANNEL_FULLY_CONNECTED /* 69634 */:
                    NsdManager.this.mConnected.countDown();
                    return;
                case AsyncChannel.CMD_CHANNEL_DISCONNECTED /* 69636 */:
                    Log.e(NsdManager.TAG, "Channel lost");
                    return;
            }
        }
    }

    private int nextListenerKey() {
        int max = Math.max(1, this.mListenerKey + 1);
        this.mListenerKey = max;
        return max;
    }

    private int putListener(Object listener, NsdServiceInfo s) {
        int key;
        checkListener(listener);
        synchronized (this.mMapLock) {
            int valueIndex = this.mListenerMap.indexOfValue(listener);
            Preconditions.checkArgument(valueIndex == -1, "listener already in use");
            key = nextListenerKey();
            this.mListenerMap.put(key, listener);
            this.mServiceMap.put(key, s);
        }
        return key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeListener(int key) {
        synchronized (this.mMapLock) {
            this.mListenerMap.remove(key);
            this.mServiceMap.remove(key);
        }
    }

    private int getListenerKey(Object listener) {
        int keyAt;
        checkListener(listener);
        synchronized (this.mMapLock) {
            int valueIndex = this.mListenerMap.indexOfValue(listener);
            Preconditions.checkArgument(valueIndex != -1, "listener not registered");
            keyAt = this.mListenerMap.keyAt(valueIndex);
        }
        return keyAt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getNsdServiceInfoType(NsdServiceInfo s) {
        return s == null ? "?" : s.getServiceType();
    }

    private void init() {
        Messenger messenger = getMessenger();
        if (messenger == null) {
            fatal("Failed to obtain service Messenger");
        }
        HandlerThread t = new HandlerThread("NsdManager");
        t.start();
        ServiceHandler serviceHandler = new ServiceHandler(t.getLooper());
        this.mHandler = serviceHandler;
        this.mAsyncChannel.connect(this.mContext, serviceHandler, messenger);
        try {
            this.mConnected.await();
        } catch (InterruptedException e) {
            fatal("Interrupted wait at init");
        }
        if (!CompatChanges.isChangeEnabled(RUN_NATIVE_NSD_ONLY_IF_LEGACY_APPS)) {
            this.mAsyncChannel.sendMessage(DAEMON_STARTUP);
        }
    }

    private static void fatal(String msg) {
        Log.e(TAG, msg);
        throw new RuntimeException(msg);
    }

    public void registerService(NsdServiceInfo serviceInfo, int protocolType, RegistrationListener listener) {
        Preconditions.checkArgument(serviceInfo.getPort() > 0, "Invalid port number");
        checkServiceInfo(serviceInfo);
        checkProtocol(protocolType);
        int key = putListener(listener, serviceInfo);
        this.mAsyncChannel.sendMessage(REGISTER_SERVICE, 0, key, serviceInfo);
    }

    public void unregisterService(RegistrationListener listener) {
        int id = getListenerKey(listener);
        this.mAsyncChannel.sendMessage(UNREGISTER_SERVICE, 0, id);
    }

    public void discoverServices(String serviceType, int protocolType, DiscoveryListener listener) {
        Preconditions.checkStringNotEmpty(serviceType, "Service type cannot be empty");
        checkProtocol(protocolType);
        NsdServiceInfo s = new NsdServiceInfo();
        s.setServiceType(serviceType);
        int key = putListener(listener, s);
        this.mAsyncChannel.sendMessage(DISCOVER_SERVICES, 0, key, s);
    }

    public void stopServiceDiscovery(DiscoveryListener listener) {
        int id = getListenerKey(listener);
        this.mAsyncChannel.sendMessage(STOP_DISCOVERY, 0, id);
    }

    public void resolveService(NsdServiceInfo serviceInfo, ResolveListener listener) {
        checkServiceInfo(serviceInfo);
        int key = putListener(listener, serviceInfo);
        this.mAsyncChannel.sendMessage(RESOLVE_SERVICE, 0, key, serviceInfo);
    }

    public void setEnabled(boolean enabled) {
        try {
            this.mService.setEnabled(enabled);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private Messenger getMessenger() {
        try {
            return this.mService.getMessenger();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static void checkListener(Object listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
    }

    private static void checkProtocol(int protocolType) {
        boolean z = true;
        if (protocolType != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Unsupported protocol");
    }

    private static void checkServiceInfo(NsdServiceInfo serviceInfo) {
        Preconditions.checkNotNull(serviceInfo, "NsdServiceInfo cannot be null");
        Preconditions.checkStringNotEmpty(serviceInfo.getServiceName(), "Service name cannot be empty");
        Preconditions.checkStringNotEmpty(serviceInfo.getServiceType(), "Service type cannot be empty");
    }
}
