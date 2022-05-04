package android.widget;

import android.view.LayoutInflater;
import android.widget.RemoteViews;
/* loaded from: classes3.dex */
public final /* synthetic */ class RemoteViews$$ExternalSyntheticLambda0 implements LayoutInflater.Filter {
    public static final /* synthetic */ RemoteViews$$ExternalSyntheticLambda0 INSTANCE = new RemoteViews$$ExternalSyntheticLambda0();

    private /* synthetic */ RemoteViews$$ExternalSyntheticLambda0() {
    }

    @Override // android.view.LayoutInflater.Filter
    public final boolean onLoadClass(Class cls) {
        boolean isAnnotationPresent;
        isAnnotationPresent = cls.isAnnotationPresent(RemoteViews.RemoteView.class);
        return isAnnotationPresent;
    }
}
