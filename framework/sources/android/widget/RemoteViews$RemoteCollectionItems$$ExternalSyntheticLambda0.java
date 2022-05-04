package android.widget;

import java.util.function.ToIntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class RemoteViews$RemoteCollectionItems$$ExternalSyntheticLambda0 implements ToIntFunction {
    public static final /* synthetic */ RemoteViews$RemoteCollectionItems$$ExternalSyntheticLambda0 INSTANCE = new RemoteViews$RemoteCollectionItems$$ExternalSyntheticLambda0();

    private /* synthetic */ RemoteViews$RemoteCollectionItems$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((RemoteViews) obj).getLayoutId();
    }
}
