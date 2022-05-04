package android.service.quickaccesswallet;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.quickaccesswallet.IQuickAccessWalletService;
import android.service.quickaccesswallet.QuickAccessWalletService;
import android.util.Log;
/* loaded from: classes3.dex */
public abstract class QuickAccessWalletService extends Service {
    public static final String ACTION_VIEW_WALLET = "android.service.quickaccesswallet.action.VIEW_WALLET";
    public static final String ACTION_VIEW_WALLET_SETTINGS = "android.service.quickaccesswallet.action.VIEW_WALLET_SETTINGS";
    public static final String SERVICE_INTERFACE = "android.service.quickaccesswallet.QuickAccessWalletService";
    public static final String SERVICE_META_DATA = "android.quickaccesswallet";
    private static final String TAG = "QAWalletService";
    public static final String TILE_SERVICE_META_DATA = "android.quickaccesswallet.tile";
    private IQuickAccessWalletServiceCallbacks mEventListener;
    private String mEventListenerId;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final IQuickAccessWalletService mInterface = new AnonymousClass1();

    public abstract void onWalletCardSelected(SelectWalletCardRequest selectWalletCardRequest);

    public abstract void onWalletCardsRequested(GetWalletCardsRequest getWalletCardsRequest, GetWalletCardsCallback getWalletCardsCallback);

    public abstract void onWalletDismissed();

    /* renamed from: android.service.quickaccesswallet.QuickAccessWalletService$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends IQuickAccessWalletService.Stub {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onWalletCardsRequested$0$QuickAccessWalletService$1(GetWalletCardsRequest request, IQuickAccessWalletServiceCallbacks callback) {
            QuickAccessWalletService.this.onWalletCardsRequestedInternal(request, callback);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardsRequested(final GetWalletCardsRequest request, final IQuickAccessWalletServiceCallbacks callback) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletService$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletService.AnonymousClass1.this.lambda$onWalletCardsRequested$0$QuickAccessWalletService$1(request, callback);
                }
            });
        }

        public /* synthetic */ void lambda$onWalletCardSelected$1$QuickAccessWalletService$1(SelectWalletCardRequest request) {
            QuickAccessWalletService.this.onWalletCardSelected(request);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardSelected(final SelectWalletCardRequest request) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletService$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletService.AnonymousClass1.this.lambda$onWalletCardSelected$1$QuickAccessWalletService$1(request);
                }
            });
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletDismissed() {
            Handler handler = QuickAccessWalletService.this.mHandler;
            final QuickAccessWalletService quickAccessWalletService = QuickAccessWalletService.this;
            handler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletService$1$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletService.this.onWalletDismissed();
                }
            });
        }

        public /* synthetic */ void lambda$registerWalletServiceEventListener$2$QuickAccessWalletService$1(WalletServiceEventListenerRequest request, IQuickAccessWalletServiceCallbacks callback) {
            QuickAccessWalletService.this.registerDismissWalletListenerInternal(request, callback);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void registerWalletServiceEventListener(final WalletServiceEventListenerRequest request, final IQuickAccessWalletServiceCallbacks callback) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletService$1$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletService.AnonymousClass1.this.lambda$registerWalletServiceEventListener$2$QuickAccessWalletService$1(request, callback);
                }
            });
        }

        public /* synthetic */ void lambda$unregisterWalletServiceEventListener$3$QuickAccessWalletService$1(WalletServiceEventListenerRequest request) {
            QuickAccessWalletService.this.unregisterDismissWalletListenerInternal(request);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void unregisterWalletServiceEventListener(final WalletServiceEventListenerRequest request) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletService$1$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletService.AnonymousClass1.this.lambda$unregisterWalletServiceEventListener$3$QuickAccessWalletService$1(request);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWalletCardsRequestedInternal(GetWalletCardsRequest request, IQuickAccessWalletServiceCallbacks callback) {
        onWalletCardsRequested(request, new GetWalletCardsCallbackImpl(request, callback, this.mHandler));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (Build.VERSION.SDK_INT < 30) {
            Log.w(TAG, "Warning: binding on pre-R device");
        }
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return this.mInterface.asBinder();
        }
        Log.w(TAG, "Wrong action");
        return null;
    }

    public final void sendWalletServiceEvent(final WalletServiceEvent serviceEvent) {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.QuickAccessWalletService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletService.this.lambda$sendWalletServiceEvent$0$QuickAccessWalletService(serviceEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendWalletServiceEventInternal */
    public void lambda$sendWalletServiceEvent$0$QuickAccessWalletService(WalletServiceEvent serviceEvent) {
        IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks = this.mEventListener;
        if (iQuickAccessWalletServiceCallbacks == null) {
            Log.i(TAG, "No dismiss listener registered");
            return;
        }
        try {
            iQuickAccessWalletServiceCallbacks.onWalletServiceEvent(serviceEvent);
        } catch (RemoteException e) {
            Log.w(TAG, "onWalletServiceEvent error", e);
            this.mEventListenerId = null;
            this.mEventListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerDismissWalletListenerInternal(WalletServiceEventListenerRequest request, IQuickAccessWalletServiceCallbacks callback) {
        this.mEventListenerId = request.getListenerId();
        this.mEventListener = callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterDismissWalletListenerInternal(WalletServiceEventListenerRequest request) {
        String str = this.mEventListenerId;
        if (str == null || !str.equals(request.getListenerId())) {
            Log.w(TAG, "dismiss listener missing or replaced");
            return;
        }
        this.mEventListenerId = null;
        this.mEventListener = null;
    }
}
