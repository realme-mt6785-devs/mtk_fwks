package android.widget;

import android.app.PendingIntent;
import android.view.View;
import android.widget.RemoteViews;
/* loaded from: classes3.dex */
public final /* synthetic */ class RemoteViews$$ExternalSyntheticLambda1 implements RemoteViews.InteractionHandler {
    public static final /* synthetic */ RemoteViews$$ExternalSyntheticLambda1 INSTANCE = new RemoteViews$$ExternalSyntheticLambda1();

    private /* synthetic */ RemoteViews$$ExternalSyntheticLambda1() {
    }

    @Override // android.widget.RemoteViews.InteractionHandler
    public final boolean onInteraction(View view, PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
        boolean startPendingIntent;
        startPendingIntent = RemoteViews.startPendingIntent(view, pendingIntent, remoteResponse.getLaunchOptions(view));
        return startPendingIntent;
    }
}
