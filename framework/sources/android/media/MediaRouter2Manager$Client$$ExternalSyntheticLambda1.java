package android.media;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2Manager$Client$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda1 INSTANCE = new MediaRouter2Manager$Client$$ExternalSyntheticLambda1();

    private /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((MediaRouter2Manager) obj).handleFailureOnHandler(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
    }
}
