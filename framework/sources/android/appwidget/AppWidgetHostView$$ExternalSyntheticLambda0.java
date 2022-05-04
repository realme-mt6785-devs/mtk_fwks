package android.appwidget;

import android.view.LayoutInflater;
import android.widget.RemoteViews;
/* loaded from: classes.dex */
public final /* synthetic */ class AppWidgetHostView$$ExternalSyntheticLambda0 implements LayoutInflater.Filter {
    public static final /* synthetic */ AppWidgetHostView$$ExternalSyntheticLambda0 INSTANCE = new AppWidgetHostView$$ExternalSyntheticLambda0();

    private /* synthetic */ AppWidgetHostView$$ExternalSyntheticLambda0() {
    }

    @Override // android.view.LayoutInflater.Filter
    public final boolean onLoadClass(Class cls) {
        boolean isAnnotationPresent;
        isAnnotationPresent = cls.isAnnotationPresent(RemoteViews.RemoteView.class);
        return isAnnotationPresent;
    }
}
