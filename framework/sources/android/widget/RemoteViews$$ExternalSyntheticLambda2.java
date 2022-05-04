package android.widget;

import android.util.SizeF;
import java.util.Map;
import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class RemoteViews$$ExternalSyntheticLambda2 implements Function {
    public static final /* synthetic */ RemoteViews$$ExternalSyntheticLambda2 INSTANCE = new RemoteViews$$ExternalSyntheticLambda2();

    private /* synthetic */ RemoteViews$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((RemoteViews) r1.getValue()).setIdealSize((SizeF) ((Map.Entry) obj).getKey());
    }
}
