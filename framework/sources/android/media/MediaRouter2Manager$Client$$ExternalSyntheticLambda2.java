package android.media;

import com.android.internal.util.function.TriConsumer;
import java.util.List;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2Manager$Client$$ExternalSyntheticLambda2 implements TriConsumer {
    public static final /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda2 INSTANCE = new MediaRouter2Manager$Client$$ExternalSyntheticLambda2();

    private /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((MediaRouter2Manager) obj).updatePreferredFeatures((String) obj2, (List) obj3);
    }
}
