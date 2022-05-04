package android.service.search;

import android.annotation.SystemApi;
import android.app.Service;
import android.app.search.ISearchCallback;
import android.app.search.Query;
import android.app.search.SearchContext;
import android.app.search.SearchSessionId;
import android.app.search.SearchTarget;
import android.app.search.SearchTargetEvent;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.search.ISearchUiService;
import android.util.Slog;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.List;
import java.util.function.Consumer;
@SystemApi
/* loaded from: classes3.dex */
public abstract class SearchUiService extends Service {
    private static final boolean DEBUG = false;
    public static final String SERVICE_INTERFACE = "android.service.search.SearchUiService";
    private static final String TAG = "SearchUiService";
    private Handler mHandler;
    private final ISearchUiService mInterface = new AnonymousClass1();

    public abstract void onDestroy(SearchSessionId searchSessionId);

    public abstract void onNotifyEvent(SearchSessionId searchSessionId, Query query, SearchTargetEvent searchTargetEvent);

    public abstract void onQuery(SearchSessionId searchSessionId, Query query, Consumer<List<SearchTarget>> consumer);

    /* renamed from: android.service.search.SearchUiService$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends ISearchUiService.Stub {
        AnonymousClass1() {
        }

        @Override // android.service.search.ISearchUiService
        public void onCreateSearchSession(SearchContext context, SearchSessionId sessionId) {
            SearchUiService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SearchUiService$1$$ExternalSyntheticLambda3.INSTANCE, SearchUiService.this, context, sessionId));
            SearchUiService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SearchUiService$1$$ExternalSyntheticLambda2.INSTANCE, SearchUiService.this, context, sessionId));
        }

        @Override // android.service.search.ISearchUiService
        public void onQuery(SearchSessionId sessionId, Query input, ISearchCallback callback) {
            SearchUiService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SearchUiService$1$$ExternalSyntheticLambda1.INSTANCE, SearchUiService.this, sessionId, input, new CallbackWrapper(callback, null)));
        }

        @Override // android.service.search.ISearchUiService
        public void onNotifyEvent(SearchSessionId sessionId, Query query, SearchTargetEvent event) {
            SearchUiService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SearchUiService$1$$ExternalSyntheticLambda0.INSTANCE, SearchUiService.this, sessionId, query, event));
        }

        @Override // android.service.search.ISearchUiService
        public void onDestroy(SearchSessionId sessionId) {
            SearchUiService.this.mHandler.sendMessage(PooledLambda.obtainMessage(SearchUiService$1$$ExternalSyntheticLambda4.INSTANCE, SearchUiService.this, sessionId));
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
        Slog.w(TAG, "Tried to bind to wrong intent (should be android.service.search.SearchUiService: " + intent);
        return null;
    }

    @Deprecated
    public void onCreateSearchSession(SearchContext context, SearchSessionId sessionId) {
    }

    public void onSearchSessionCreated(SearchContext context, SearchSessionId sessionId) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDestroy(SearchSessionId sessionId) {
        super.onDestroy();
        onDestroy(sessionId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class CallbackWrapper implements Consumer<List<SearchTarget>>, IBinder.DeathRecipient {
        private ISearchCallback mCallback;
        private final Consumer<CallbackWrapper> mOnBinderDied;

        CallbackWrapper(ISearchCallback callback, Consumer<CallbackWrapper> onBinderDied) {
            this.mCallback = callback;
            this.mOnBinderDied = onBinderDied;
            try {
                callback.asBinder().linkToDeath(this, 0);
            } catch (RemoteException e) {
                Slog.e(SearchUiService.TAG, "Failed to link to death: " + e);
            }
        }

        public void accept(List<SearchTarget> searchTargets) {
            try {
                ISearchCallback iSearchCallback = this.mCallback;
                if (iSearchCallback != null) {
                    iSearchCallback.onResult(new ParceledListSlice(searchTargets));
                }
            } catch (RemoteException e) {
                Slog.e(SearchUiService.TAG, "Error sending result:" + e);
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
