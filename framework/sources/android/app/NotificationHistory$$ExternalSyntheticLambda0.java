package android.app;

import android.app.NotificationHistory;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class NotificationHistory$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ NotificationHistory$$ExternalSyntheticLambda0 INSTANCE = new NotificationHistory$$ExternalSyntheticLambda0();

    private /* synthetic */ NotificationHistory$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return NotificationHistory.lambda$addNotificationsToWrite$0((NotificationHistory.HistoricalNotification) obj, (NotificationHistory.HistoricalNotification) obj2);
    }
}
