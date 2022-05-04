package android.service.timezone;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.timezone.ITimeZoneProvider;
import android.service.timezone.TimeZoneProviderService;
import android.util.Log;
import com.android.internal.os.BackgroundThread;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public abstract class TimeZoneProviderService extends Service {
    public static final String PRIMARY_LOCATION_TIME_ZONE_PROVIDER_SERVICE_INTERFACE = "android.service.timezone.PrimaryLocationTimeZoneProviderService";
    public static final String SECONDARY_LOCATION_TIME_ZONE_PROVIDER_SERVICE_INTERFACE = "android.service.timezone.SecondaryLocationTimeZoneProviderService";
    private static final String TAG = "TimeZoneProviderService";
    public static final String TEST_COMMAND_RESULT_ERROR_KEY = "ERROR";
    public static final String TEST_COMMAND_RESULT_SUCCESS_KEY = "SUCCESS";
    private ITimeZoneProviderManager mManager;
    private final TimeZoneProviderServiceWrapper mWrapper = new TimeZoneProviderServiceWrapper();
    private final Handler mHandler = BackgroundThread.getHandler();

    public abstract void onStartUpdates(long j);

    public abstract void onStopUpdates();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.mWrapper;
    }

    public final void reportSuggestion(final TimeZoneProviderSuggestion suggestion) {
        Objects.requireNonNull(suggestion);
        this.mHandler.post(new Runnable() { // from class: android.service.timezone.TimeZoneProviderService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TimeZoneProviderService.this.lambda$reportSuggestion$0$TimeZoneProviderService(suggestion);
            }
        });
    }

    public /* synthetic */ void lambda$reportSuggestion$0$TimeZoneProviderService(TimeZoneProviderSuggestion suggestion) {
        ITimeZoneProviderManager manager = this.mManager;
        if (manager != null) {
            try {
                manager.onTimeZoneProviderSuggestion(suggestion);
            } catch (RemoteException | RuntimeException e) {
                Log.w(TAG, e);
            }
        }
    }

    public final void reportUncertain() {
        this.mHandler.post(new Runnable() { // from class: android.service.timezone.TimeZoneProviderService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TimeZoneProviderService.this.lambda$reportUncertain$1$TimeZoneProviderService();
            }
        });
    }

    public /* synthetic */ void lambda$reportUncertain$1$TimeZoneProviderService() {
        ITimeZoneProviderManager manager = this.mManager;
        if (manager != null) {
            try {
                manager.onTimeZoneProviderUncertain();
            } catch (RemoteException | RuntimeException e) {
                Log.w(TAG, e);
            }
        }
    }

    public final void reportPermanentFailure(final Throwable cause) {
        Objects.requireNonNull(cause);
        this.mHandler.post(new Runnable() { // from class: android.service.timezone.TimeZoneProviderService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TimeZoneProviderService.this.lambda$reportPermanentFailure$2$TimeZoneProviderService(cause);
            }
        });
    }

    public /* synthetic */ void lambda$reportPermanentFailure$2$TimeZoneProviderService(Throwable cause) {
        ITimeZoneProviderManager manager = this.mManager;
        if (manager != null) {
            try {
                manager.onTimeZoneProviderPermanentFailure(cause.getMessage());
            } catch (RemoteException | RuntimeException e) {
                Log.w(TAG, e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartUpdatesInternal(ITimeZoneProviderManager manager, long initializationTimeoutMillis) {
        this.mManager = manager;
        onStartUpdates(initializationTimeoutMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStopUpdatesInternal() {
        onStopUpdates();
        this.mManager = null;
    }

    /* loaded from: classes3.dex */
    private class TimeZoneProviderServiceWrapper extends ITimeZoneProvider.Stub {
        private TimeZoneProviderServiceWrapper() {
        }

        @Override // android.service.timezone.ITimeZoneProvider
        public void startUpdates(final ITimeZoneProviderManager manager, final long initializationTimeoutMillis) {
            Objects.requireNonNull(manager);
            TimeZoneProviderService.this.mHandler.post(new Runnable() { // from class: android.service.timezone.TimeZoneProviderService$TimeZoneProviderServiceWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TimeZoneProviderService.TimeZoneProviderServiceWrapper.this.lambda$startUpdates$0$TimeZoneProviderService$TimeZoneProviderServiceWrapper(manager, initializationTimeoutMillis);
                }
            });
        }

        public /* synthetic */ void lambda$startUpdates$0$TimeZoneProviderService$TimeZoneProviderServiceWrapper(ITimeZoneProviderManager manager, long initializationTimeoutMillis) {
            TimeZoneProviderService.this.onStartUpdatesInternal(manager, initializationTimeoutMillis);
        }

        @Override // android.service.timezone.ITimeZoneProvider
        public void stopUpdates() {
            Handler handler = TimeZoneProviderService.this.mHandler;
            final TimeZoneProviderService timeZoneProviderService = TimeZoneProviderService.this;
            handler.post(new Runnable() { // from class: android.service.timezone.TimeZoneProviderService$TimeZoneProviderServiceWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    TimeZoneProviderService.this.onStopUpdatesInternal();
                }
            });
        }
    }
}
