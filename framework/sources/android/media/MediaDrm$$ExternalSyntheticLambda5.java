package android.media;

import android.media.MediaDrm;
import java.util.function.Consumer;
import java.util.function.Function;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaDrm$$ExternalSyntheticLambda5 implements Function {
    public final /* synthetic */ MediaDrm f$0;

    public /* synthetic */ MediaDrm$$ExternalSyntheticLambda5(MediaDrm mediaDrm) {
        this.f$0 = mediaDrm;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Consumer createOnEventListener;
        createOnEventListener = this.f$0.createOnEventListener((MediaDrm.OnEventListener) obj);
        return createOnEventListener;
    }
}
