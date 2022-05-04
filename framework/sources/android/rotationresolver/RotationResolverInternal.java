package android.rotationresolver;

import android.os.CancellationSignal;
/* loaded from: classes2.dex */
public abstract class RotationResolverInternal {

    /* loaded from: classes2.dex */
    public interface RotationResolverCallbackInternal {
        void onFailure(int i);

        void onSuccess(int i);
    }

    public abstract boolean isRotationResolverSupported();

    public abstract void resolveRotation(RotationResolverCallbackInternal rotationResolverCallbackInternal, String str, int i, int i2, long j, CancellationSignal cancellationSignal);
}
