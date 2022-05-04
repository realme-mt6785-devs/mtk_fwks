package android.service.rotationresolver;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.service.rotationresolver.IRotationResolverService;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public abstract class RotationResolverService extends Service {
    public static final int ROTATION_RESULT_FAILURE_CANCELLED = 0;
    public static final int ROTATION_RESULT_FAILURE_NOT_SUPPORTED = 4;
    public static final int ROTATION_RESULT_FAILURE_PREEMPTED = 2;
    public static final int ROTATION_RESULT_FAILURE_TIMED_OUT = 1;
    public static final int ROTATION_RESULT_FAILURE_UNKNOWN = 3;
    public static final String SERVICE_INTERFACE = "android.service.rotationresolver.RotationResolverService";
    private CancellationSignal mCancellationSignal;
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper(), null, true);
    private RotationResolverCallbackWrapper mPendingCallback;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface FailureCodes {
    }

    /* loaded from: classes3.dex */
    public interface RotationResolverCallback {
        void onFailure(int i);

        void onSuccess(int i);
    }

    public abstract void onResolveRotation(RotationResolutionRequest rotationResolutionRequest, CancellationSignal cancellationSignal, RotationResolverCallback rotationResolverCallback);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.service.rotationresolver.RotationResolverService$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 extends IRotationResolverService.Stub {
        AnonymousClass1() {
        }

        @Override // android.service.rotationresolver.IRotationResolverService
        public void resolveRotation(IRotationResolverCallback callback, RotationResolutionRequest request) throws RemoteException {
            Objects.requireNonNull(callback);
            Objects.requireNonNull(request);
            ICancellationSignal transport = CancellationSignal.createTransport();
            callback.onCancellable(transport);
            RotationResolverService.this.mMainThreadHandler.sendMessage(PooledLambda.obtainMessage(RotationResolverService$1$$ExternalSyntheticLambda0.INSTANCE, RotationResolverService.this, callback, request, transport));
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return new AnonymousClass1();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveRotation(IRotationResolverCallback callback, RotationResolutionRequest request, ICancellationSignal transport) {
        CancellationSignal cancellationSignal;
        if (this.mPendingCallback == null || (((cancellationSignal = this.mCancellationSignal) != null && cancellationSignal.isCanceled()) || SystemClock.uptimeMillis() >= this.mPendingCallback.mExpirationTime)) {
            this.mPendingCallback = new RotationResolverCallbackWrapper(callback, this, SystemClock.uptimeMillis() + request.getTimeoutMillis(), null);
            CancellationSignal fromTransport = CancellationSignal.fromTransport(transport);
            this.mCancellationSignal = fromTransport;
            onResolveRotation(request, fromTransport, this.mPendingCallback);
            return;
        }
        reportFailures(callback, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRotationResult(IRotationResolverCallback internalCallback, int result) {
        RotationResolverCallbackWrapper rotationResolverCallbackWrapper = this.mPendingCallback;
        if (rotationResolverCallbackWrapper != null && rotationResolverCallbackWrapper.mCallback == internalCallback) {
            this.mPendingCallback = null;
            try {
                internalCallback.onSuccess(result);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFailureResult(IRotationResolverCallback internalCallback, int error) {
        RotationResolverCallbackWrapper rotationResolverCallbackWrapper = this.mPendingCallback;
        if (rotationResolverCallbackWrapper != null && internalCallback == rotationResolverCallbackWrapper.mCallback) {
            reportFailures(internalCallback, error);
            this.mPendingCallback = null;
        }
    }

    private void reportFailures(IRotationResolverCallback callback, int error) {
        try {
            callback.onFailure(error);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    /* loaded from: classes3.dex */
    public static final class RotationResolverCallbackWrapper implements RotationResolverCallback {
        private final IRotationResolverCallback mCallback;
        private final long mExpirationTime;
        private final Handler mHandler;
        private final RotationResolverService mService;

        /* synthetic */ RotationResolverCallbackWrapper(IRotationResolverCallback x0, RotationResolverService x1, long x2, AnonymousClass1 x3) {
            this(x0, x1, x2);
        }

        private RotationResolverCallbackWrapper(IRotationResolverCallback callback, RotationResolverService service, long expirationTime) {
            this.mCallback = callback;
            this.mService = service;
            Handler handler = service.mMainThreadHandler;
            this.mHandler = handler;
            this.mExpirationTime = expirationTime;
            Objects.requireNonNull(handler);
        }

        @Override // android.service.rotationresolver.RotationResolverService.RotationResolverCallback
        public void onSuccess(int result) {
            this.mHandler.sendMessage(PooledLambda.obtainMessage(RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda1.INSTANCE, this.mService, this.mCallback, Integer.valueOf(result)));
        }

        @Override // android.service.rotationresolver.RotationResolverService.RotationResolverCallback
        public void onFailure(int error) {
            this.mHandler.sendMessage(PooledLambda.obtainMessage(RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda0.INSTANCE, this.mService, this.mCallback, Integer.valueOf(error)));
        }
    }
}
