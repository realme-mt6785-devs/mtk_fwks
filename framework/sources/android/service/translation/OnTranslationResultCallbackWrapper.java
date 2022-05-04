package android.service.translation;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import android.view.translation.TranslationResponse;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
final class OnTranslationResultCallbackWrapper implements Consumer<TranslationResponse> {
    private static final String TAG = "OnTranslationResultCallback";
    private final ITranslationCallback mCallback;
    private final AtomicBoolean mCalled = new AtomicBoolean();

    public OnTranslationResultCallbackWrapper(ITranslationCallback callback) {
        Objects.requireNonNull(callback);
        this.mCallback = callback;
    }

    public void accept(TranslationResponse response) {
        assertNotCalled();
        if (!this.mCalled.getAndSet(response.isFinalResponse())) {
            try {
                this.mCallback.onTranslationResponse(response);
            } catch (RemoteException e) {
                if (e instanceof DeadObjectException) {
                    Log.w(TAG, "Process is dead, ignore.");
                    return;
                }
                throw e.rethrowAsRuntimeException();
            }
        } else {
            throw new IllegalStateException("Already called with complete response");
        }
    }

    private void assertNotCalled() {
        if (this.mCalled.get()) {
            throw new IllegalStateException("Already called");
        }
    }
}
