package android.service.quickaccesswallet;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.service.quickaccesswallet.IQuickAccessWalletService;
import android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.widget.LockPatternUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class QuickAccessWalletClientImpl implements QuickAccessWalletClient, ServiceConnection {
    private static final int MSG_TIMEOUT_SERVICE = 5;
    private static final long SERVICE_CONNECTION_TIMEOUT_MS = 60000;
    public static final String SETTING_KEY = "lockscreen_show_wallet";
    private static final String TAG = "QAWalletSClient";
    private final Context mContext;
    private boolean mIsConnected;
    private IQuickAccessWalletService mService;
    private final QuickAccessWalletServiceInfo mServiceInfo;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Queue<ApiCaller> mRequestQueue = new LinkedList();
    private final Map<QuickAccessWalletClient.WalletServiceEventListener, String> mEventListeners = new HashMap(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public QuickAccessWalletClientImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.mServiceInfo = QuickAccessWalletServiceInfo.tryCreate(context);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public boolean isWalletServiceAvailable() {
        return this.mServiceInfo != null;
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public boolean isWalletFeatureAvailable() {
        int currentUser = ActivityManager.getCurrentUser();
        return currentUser == 0 && checkUserSetupComplete() && !new LockPatternUtils(this.mContext).isUserInLockdown(currentUser);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public boolean isWalletFeatureAvailableWhenDeviceLocked() {
        return checkSecureSetting("lockscreen_show_wallet");
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void getWalletCards(GetWalletCardsRequest request, QuickAccessWalletClient.OnWalletCardsRetrievedCallback callback) {
        getWalletCards(this.mContext.getMainExecutor(), request, callback);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void getWalletCards(Executor executor, final GetWalletCardsRequest request, final QuickAccessWalletClient.OnWalletCardsRetrievedCallback callback) {
        if (!isWalletServiceAvailable()) {
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletClient.OnWalletCardsRetrievedCallback.this.onWalletCardRetrievalError(new GetWalletCardsError(null, null));
                }
            });
            return;
        }
        final BaseCallbacks serviceCallback = new AnonymousClass1(executor, callback);
        executeApiCall(new ApiCaller("onWalletCardsRequested") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.2
            @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
            public void performApiCall(IQuickAccessWalletService service) throws RemoteException {
                service.onWalletCardsRequested(request, serviceCallback);
            }

            @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
            public void onApiError() {
                serviceCallback.onGetWalletCardsFailure(new GetWalletCardsError(null, null));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.service.quickaccesswallet.QuickAccessWalletClientImpl$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 extends BaseCallbacks {
        final /* synthetic */ QuickAccessWalletClient.OnWalletCardsRetrievedCallback val$callback;
        final /* synthetic */ Executor val$executor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Executor executor, QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback) {
            super(null);
            this.val$executor = executor;
            this.val$callback = onWalletCardsRetrievedCallback;
        }

        @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.BaseCallbacks, android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsSuccess(final GetWalletCardsResponse response) {
            Executor executor = this.val$executor;
            final QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletClient.OnWalletCardsRetrievedCallback.this.onWalletCardsRetrieved(response);
                }
            });
        }

        @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.BaseCallbacks, android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsFailure(final GetWalletCardsError error) {
            Executor executor = this.val$executor;
            final QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletClient.OnWalletCardsRetrievedCallback.this.onWalletCardRetrievalError(error);
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void selectWalletCard(final SelectWalletCardRequest request) {
        if (isWalletServiceAvailable()) {
            executeApiCall(new ApiCaller("onWalletCardSelected") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.3
                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService service) throws RemoteException {
                    service.onWalletCardSelected(request);
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void notifyWalletDismissed() {
        if (isWalletServiceAvailable()) {
            executeApiCall(new ApiCaller("onWalletDismissed") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.4
                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService service) throws RemoteException {
                    service.onWalletDismissed();
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void addWalletServiceEventListener(QuickAccessWalletClient.WalletServiceEventListener listener) {
        addWalletServiceEventListener(this.mContext.getMainExecutor(), listener);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void addWalletServiceEventListener(Executor executor, final QuickAccessWalletClient.WalletServiceEventListener listener) {
        if (isWalletServiceAvailable()) {
            final BaseCallbacks callback = new AnonymousClass5(executor, listener);
            executeApiCall(new ApiCaller("registerListener") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.6
                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService service) throws RemoteException {
                    String listenerId = UUID.randomUUID().toString();
                    WalletServiceEventListenerRequest request = new WalletServiceEventListenerRequest(listenerId);
                    QuickAccessWalletClientImpl.this.mEventListeners.put(listener, listenerId);
                    service.registerWalletServiceEventListener(request, callback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.service.quickaccesswallet.QuickAccessWalletClientImpl$5  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass5 extends BaseCallbacks {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ QuickAccessWalletClient.WalletServiceEventListener val$listener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Executor executor, QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener) {
            super(null);
            this.val$executor = executor;
            this.val$listener = walletServiceEventListener;
        }

        @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.BaseCallbacks, android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onWalletServiceEvent(final WalletServiceEvent event) {
            Executor executor = this.val$executor;
            final QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener = this.val$listener;
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletClient.WalletServiceEventListener.this.onWalletServiceEvent(event);
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void removeWalletServiceEventListener(final QuickAccessWalletClient.WalletServiceEventListener listener) {
        if (isWalletServiceAvailable()) {
            executeApiCall(new ApiCaller("unregisterListener") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.7
                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService service) throws RemoteException {
                    String listenerId = (String) QuickAccessWalletClientImpl.this.mEventListeners.remove(listener);
                    if (listenerId != null) {
                        WalletServiceEventListenerRequest request = new WalletServiceEventListenerRequest(listenerId);
                        service.unregisterWalletServiceEventListener(request);
                    }
                }
            });
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        disconnect();
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void disconnect() {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletClientImpl.this.lambda$disconnect$1$QuickAccessWalletClientImpl();
            }
        });
    }

    public /* synthetic */ void lambda$disconnect$1$QuickAccessWalletClientImpl() {
        disconnectInternal(true);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Intent createWalletIntent() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        String packageName = quickAccessWalletServiceInfo.getComponentName().getPackageName();
        String walletActivity = this.mServiceInfo.getWalletActivity();
        return createIntent(walletActivity, packageName, QuickAccessWalletService.ACTION_VIEW_WALLET);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Intent createWalletSettingsIntent() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        String packageName = quickAccessWalletServiceInfo.getComponentName().getPackageName();
        String settingsActivity = this.mServiceInfo.getSettingsActivity();
        return createIntent(settingsActivity, packageName, QuickAccessWalletService.ACTION_VIEW_WALLET_SETTINGS);
    }

    private Intent createIntent(String activityName, String packageName, String action) {
        PackageManager pm = this.mContext.getPackageManager();
        if (TextUtils.isEmpty(activityName)) {
            activityName = queryActivityForAction(pm, packageName, action);
        }
        if (TextUtils.isEmpty(activityName)) {
            return null;
        }
        ComponentName component = new ComponentName(packageName, activityName);
        if (!isActivityEnabled(pm, component)) {
            return null;
        }
        return new Intent(action).setComponent(component);
    }

    private static String queryActivityForAction(PackageManager pm, String packageName, String action) {
        Intent intent = new Intent(action).setPackage(packageName);
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
        if (resolveInfo == null || resolveInfo.activityInfo == null || !resolveInfo.activityInfo.exported) {
            return null;
        }
        return resolveInfo.activityInfo.name;
    }

    private static boolean isActivityEnabled(PackageManager pm, ComponentName component) {
        int setting = pm.getComponentEnabledSetting(component);
        if (setting == 1) {
            return true;
        }
        if (setting != 0) {
            return false;
        }
        try {
            return pm.getActivityInfo(component, 0).isEnabled();
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Drawable getLogo() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getWalletLogo(this.mContext);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Drawable getTileIcon() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getTileIcon();
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public CharSequence getServiceLabel() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getServiceLabel(this.mContext);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public CharSequence getShortcutShortLabel() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getShortcutShortLabel(this.mContext);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public CharSequence getShortcutLongLabel() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getShortcutLongLabel(this.mContext);
    }

    private void connect() {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletClientImpl.this.connectInternal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectInternal() {
        if (this.mServiceInfo == null) {
            Log.w(TAG, "Wallet service unavailable");
        } else if (!this.mIsConnected) {
            this.mIsConnected = true;
            Intent intent = new Intent(QuickAccessWalletService.SERVICE_INTERFACE);
            intent.setComponent(this.mServiceInfo.getComponentName());
            this.mContext.bindService(intent, this, 33);
            resetServiceConnectionTimeout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onConnectedInternal */
    public void lambda$onServiceConnected$5$QuickAccessWalletClientImpl(IQuickAccessWalletService service) {
        if (!this.mIsConnected) {
            Log.w(TAG, "onConnectInternal but connection closed");
            this.mService = null;
            return;
        }
        this.mService = service;
        Iterator it = new ArrayList(this.mRequestQueue).iterator();
        while (it.hasNext()) {
            ApiCaller apiCaller = (ApiCaller) it.next();
            performApiCallInternal(apiCaller, this.mService);
            this.mRequestQueue.remove(apiCaller);
        }
    }

    private void resetServiceConnectionTimeout() {
        this.mHandler.removeMessages(5);
        this.mHandler.postDelayed(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletClientImpl.this.lambda$resetServiceConnectionTimeout$2$QuickAccessWalletClientImpl();
            }
        }, 5, 60000L);
    }

    public /* synthetic */ void lambda$resetServiceConnectionTimeout$2$QuickAccessWalletClientImpl() {
        disconnectInternal(true);
    }

    private void disconnectInternal(boolean clearEventListeners) {
        if (!this.mIsConnected) {
            Log.w(TAG, "already disconnected");
        } else if (!clearEventListeners || this.mEventListeners.isEmpty()) {
            this.mIsConnected = false;
            this.mContext.unbindService(this);
            this.mService = null;
            this.mEventListeners.clear();
            this.mRequestQueue.clear();
        } else {
            for (QuickAccessWalletClient.WalletServiceEventListener listener : this.mEventListeners.keySet()) {
                removeWalletServiceEventListener(listener);
            }
            this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletClientImpl.this.lambda$disconnectInternal$3$QuickAccessWalletClientImpl();
                }
            });
        }
    }

    public /* synthetic */ void lambda$disconnectInternal$3$QuickAccessWalletClientImpl() {
        disconnectInternal(false);
    }

    private void executeApiCall(final ApiCaller apiCaller) {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletClientImpl.this.lambda$executeApiCall$4$QuickAccessWalletClientImpl(apiCaller);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: executeInternal */
    public void lambda$executeApiCall$4$QuickAccessWalletClientImpl(ApiCaller apiCaller) {
        IQuickAccessWalletService iQuickAccessWalletService;
        if (!this.mIsConnected || (iQuickAccessWalletService = this.mService) == null) {
            this.mRequestQueue.add(apiCaller);
            connect();
            return;
        }
        performApiCallInternal(apiCaller, iQuickAccessWalletService);
    }

    private void performApiCallInternal(ApiCaller apiCaller, IQuickAccessWalletService service) {
        if (service == null) {
            apiCaller.onApiError();
            return;
        }
        try {
            apiCaller.performApiCall(service);
            resetServiceConnectionTimeout();
        } catch (RemoteException e) {
            Log.w(TAG, "executeInternal error: " + apiCaller.mDesc, e);
            apiCaller.onApiError();
            disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class ApiCaller {
        private final String mDesc;

        abstract void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException;

        /* synthetic */ ApiCaller(String x0, AnonymousClass1 x1) {
            this(x0);
        }

        private ApiCaller(String desc) {
            this.mDesc = desc;
        }

        void onApiError() {
            Log.w(QuickAccessWalletClientImpl.TAG, "api error: " + this.mDesc);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder binder) {
        final IQuickAccessWalletService service = IQuickAccessWalletService.Stub.asInterface(binder);
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletClientImpl.this.lambda$onServiceConnected$5$QuickAccessWalletClientImpl(service);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName name) {
        disconnect();
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName name) {
        disconnect();
    }

    private boolean checkSecureSetting(String name) {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), name, 0) == 1;
    }

    private boolean checkUserSetupComplete() {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), Settings.Secure.USER_SETUP_COMPLETE, 0, -2) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class BaseCallbacks extends IQuickAccessWalletServiceCallbacks.Stub {
        private BaseCallbacks() {
        }

        /* synthetic */ BaseCallbacks(AnonymousClass1 x0) {
            this();
        }

        public void onGetWalletCardsSuccess(GetWalletCardsResponse response) {
            throw new IllegalStateException();
        }

        public void onGetWalletCardsFailure(GetWalletCardsError error) {
            throw new IllegalStateException();
        }

        public void onWalletServiceEvent(WalletServiceEvent event) {
            throw new IllegalStateException();
        }
    }
}
