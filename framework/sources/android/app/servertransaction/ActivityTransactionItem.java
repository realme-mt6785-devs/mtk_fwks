package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
/* loaded from: classes.dex */
public abstract class ActivityTransactionItem extends ClientTransactionItem {
    public abstract void execute(ClientTransactionHandler clientTransactionHandler, ActivityThread.ActivityClientRecord activityClientRecord, PendingTransactionActions pendingTransactionActions);

    @Override // android.app.servertransaction.BaseClientRequest
    public final void execute(ClientTransactionHandler client, IBinder token, PendingTransactionActions pendingActions) {
        ActivityThread.ActivityClientRecord r = getActivityClientRecord(client, token);
        execute(client, r, pendingActions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityThread.ActivityClientRecord getActivityClientRecord(ClientTransactionHandler client, IBinder token) {
        return getActivityClientRecord(client, token, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityThread.ActivityClientRecord getActivityClientRecord(ClientTransactionHandler client, IBinder token, boolean includeLaunching) {
        ActivityThread.ActivityClientRecord r = client.getActivityClient(token);
        if (r == null) {
            if (includeLaunching) {
                r = client.getLaunchingActivity(token);
            }
            if (r != null) {
                return r;
            }
            throw new IllegalArgumentException("Activity client record must not be null to execute transaction item");
        } else if (client.getActivity(token) != null) {
            return r;
        } else {
            throw new IllegalArgumentException("Activity must not be null to execute transaction item");
        }
    }
}
