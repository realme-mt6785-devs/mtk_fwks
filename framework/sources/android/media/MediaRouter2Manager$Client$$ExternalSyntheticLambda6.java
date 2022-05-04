package android.media;

import java.util.List;
import java.util.function.BiConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2Manager$Client$$ExternalSyntheticLambda6 implements BiConsumer {
    public static final /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda6 INSTANCE = new MediaRouter2Manager$Client$$ExternalSyntheticLambda6();

    private /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((MediaRouter2Manager) obj).changeRoutesOnHandler((List) obj2);
    }
}
