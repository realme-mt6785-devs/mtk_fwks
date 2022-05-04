package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.os.ICancellationSignal;
import android.os.RemoteException;
import android.os.Trace;
import android.telephony.BinderCacheManager$BinderDeathTracker$$ExternalSyntheticLambda0;
import android.util.CloseGuard;
import android.util.Log;
import android.view.IScrollCaptureConnection;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public class ScrollCaptureConnection extends IScrollCaptureConnection.Stub {
    private static final String TAG = "ScrollCaptureConnection";
    private volatile boolean mActive;
    private CancellationSignal mCancellation;
    private ScrollCaptureCallback mLocal;
    private final Point mPositionInWindow;
    private IScrollCaptureCallbacks mRemote;
    private final Rect mScrollBounds;
    private ScrollCaptureSession mSession;
    private final Executor mUiThread;
    private final Object mLock = new Object();
    private final CloseGuard mCloseGuard = new CloseGuard();

    public ScrollCaptureConnection(Executor uiThread, ScrollCaptureTarget selectedTarget) {
        Objects.requireNonNull(uiThread, "<uiThread> must non-null");
        this.mUiThread = uiThread;
        Objects.requireNonNull(selectedTarget, "<selectedTarget> must non-null");
        Rect copyOrNull = Rect.copyOrNull(selectedTarget.getScrollBounds());
        Objects.requireNonNull(copyOrNull, "target.getScrollBounds() must be non-null to construct a client");
        this.mScrollBounds = copyOrNull;
        this.mLocal = selectedTarget.getCallback();
        this.mPositionInWindow = new Point(selectedTarget.getPositionInWindow());
    }

    @Override // android.view.IScrollCaptureConnection
    public ICancellationSignal startCapture(Surface surface, IScrollCaptureCallbacks remote) throws RemoteException {
        this.mCloseGuard.open("close");
        if (surface.isValid()) {
            Objects.requireNonNull(remote, "<callbacks> must non-null");
            this.mRemote = remote;
            ICancellationSignal cancellation = CancellationSignal.createTransport();
            this.mCancellation = CancellationSignal.fromTransport(cancellation);
            this.mSession = new ScrollCaptureSession(surface, this.mScrollBounds, this.mPositionInWindow);
            final Runnable listener = SafeCallback.create(this.mCancellation, this.mUiThread, new Runnable() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ScrollCaptureConnection.this.onStartCaptureCompleted();
                }
            });
            this.mUiThread.execute(new Runnable() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    ScrollCaptureConnection.this.lambda$startCapture$0$ScrollCaptureConnection(listener);
                }
            });
            return cancellation;
        }
        throw new RemoteException(new IllegalArgumentException("surface must be valid"));
    }

    public /* synthetic */ void lambda$startCapture$0$ScrollCaptureConnection(Runnable listener) {
        this.mLocal.onScrollCaptureStart(this.mSession, this.mCancellation, listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartCaptureCompleted() {
        this.mActive = true;
        try {
            this.mRemote.onCaptureStarted();
        } catch (RemoteException e) {
            Log.w(TAG, "Shutting down due to error: ", e);
            close();
        }
    }

    @Override // android.view.IScrollCaptureConnection
    public ICancellationSignal requestImage(final Rect requestRect) throws RemoteException {
        Trace.beginSection("requestImage");
        checkActive();
        ICancellationSignal cancellation = CancellationSignal.createTransport();
        CancellationSignal fromTransport = CancellationSignal.fromTransport(cancellation);
        this.mCancellation = fromTransport;
        final Consumer<Rect> listener = SafeCallback.create(fromTransport, this.mUiThread, new Consumer() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ScrollCaptureConnection.this.onImageRequestCompleted((Rect) obj);
            }
        });
        this.mUiThread.execute(new Runnable() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ScrollCaptureConnection.this.lambda$requestImage$1$ScrollCaptureConnection(requestRect, listener);
            }
        });
        Trace.endSection();
        return cancellation;
    }

    public /* synthetic */ void lambda$requestImage$1$ScrollCaptureConnection(Rect requestRect, Consumer listener) {
        this.mLocal.onScrollCaptureImageRequest(this.mSession, this.mCancellation, new Rect(requestRect), listener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onImageRequestCompleted(Rect capturedArea) {
        try {
            this.mRemote.onImageRequestCompleted(0, capturedArea);
        } catch (RemoteException e) {
            Log.w(TAG, "Shutting down due to error: ", e);
            close();
        }
    }

    @Override // android.view.IScrollCaptureConnection
    public ICancellationSignal endCapture() throws RemoteException {
        checkActive();
        ICancellationSignal cancellation = CancellationSignal.createTransport();
        CancellationSignal fromTransport = CancellationSignal.fromTransport(cancellation);
        this.mCancellation = fromTransport;
        final Runnable listener = SafeCallback.create(fromTransport, this.mUiThread, new Runnable() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ScrollCaptureConnection.this.onEndCaptureCompleted();
            }
        });
        this.mUiThread.execute(new Runnable() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ScrollCaptureConnection.this.lambda$endCapture$2$ScrollCaptureConnection(listener);
            }
        });
        return cancellation;
    }

    public /* synthetic */ void lambda$endCapture$2$ScrollCaptureConnection(Runnable listener) {
        this.mLocal.onScrollCaptureEnd(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndCaptureCompleted() {
        this.mActive = false;
        try {
            try {
                IScrollCaptureCallbacks iScrollCaptureCallbacks = this.mRemote;
                if (iScrollCaptureCallbacks != null) {
                    iScrollCaptureCallbacks.onCaptureEnded();
                }
            } catch (RemoteException e) {
                Log.w(TAG, "Caught exception confirming capture end!", e);
            }
        } finally {
            close();
        }
    }

    @Override // android.view.IScrollCaptureConnection
    public void close() {
        if (this.mActive) {
            if (this.mCancellation != null) {
                Log.w(TAG, "close(): cancelling pending operation.");
                this.mCancellation.cancel();
                this.mCancellation = null;
            }
            Log.w(TAG, "close(): capture session still active! Ending now.");
            final ScrollCaptureCallback callback = this.mLocal;
            this.mUiThread.execute(new Runnable() { // from class: android.view.ScrollCaptureConnection$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScrollCaptureCallback.this.onScrollCaptureEnd(ScrollCaptureConnection$$ExternalSyntheticLambda6.INSTANCE);
                }
            });
            this.mActive = false;
        }
        this.mActive = false;
        this.mSession = null;
        this.mRemote = null;
        this.mLocal = null;
        this.mCloseGuard.close();
        Reference.reachabilityFence(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$close$3() {
    }

    public boolean isActive() {
        return this.mActive;
    }

    private void checkActive() throws RemoteException {
        synchronized (this.mLock) {
            if (!this.mActive) {
                throw new RemoteException(new IllegalStateException("Not started!"));
            }
        }
    }

    public String toString() {
        return "ScrollCaptureConnection{active=" + this.mActive + ", session=" + this.mSession + ", remote=" + this.mRemote + ", local=" + this.mLocal + "}";
    }

    protected void finalize() throws Throwable {
        try {
            this.mCloseGuard.warnIfOpen();
            close();
        } finally {
            super.finalize();
        }
    }

    /* loaded from: classes3.dex */
    private static class SafeCallback<T> {
        private boolean mExecuted;
        private final Executor mExecutor;
        private final CancellationSignal mSignal;
        private final WeakReference<T> mTargetRef;

        protected SafeCallback(CancellationSignal signal, Executor executor, T target) {
            this.mSignal = signal;
            this.mTargetRef = new WeakReference<>(target);
            this.mExecutor = executor;
        }

        protected final void maybeAccept(final Consumer<T> targetConsumer) {
            final T target;
            if (!this.mExecuted) {
                this.mExecuted = true;
                if (!this.mSignal.isCanceled() && (target = this.mTargetRef.get()) != null) {
                    this.mExecutor.execute(new Runnable() { // from class: android.view.ScrollCaptureConnection$SafeCallback$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            targetConsumer.accept(target);
                        }
                    });
                }
            }
        }

        static Runnable create(CancellationSignal signal, Executor executor, Runnable target) {
            return new RunnableCallback(signal, executor, target);
        }

        static <T> Consumer<T> create(CancellationSignal signal, Executor executor, Consumer<T> target) {
            return new ConsumerCallback(signal, executor, target);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class RunnableCallback extends SafeCallback<Runnable> implements Runnable {
        RunnableCallback(CancellationSignal signal, Executor executor, Runnable target) {
            super(signal, executor, target);
        }

        @Override // java.lang.Runnable
        public void run() {
            maybeAccept(BinderCacheManager$BinderDeathTracker$$ExternalSyntheticLambda0.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ConsumerCallback<T> extends SafeCallback<Consumer<T>> implements Consumer<T> {
        ConsumerCallback(CancellationSignal signal, Executor executor, Consumer<T> target) {
            super(signal, executor, target);
        }

        @Override // java.util.function.Consumer
        public void accept(final T value) {
            maybeAccept(new Consumer() { // from class: android.view.ScrollCaptureConnection$ConsumerCallback$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Consumer) obj).accept(value);
                }
            });
        }
    }
}
