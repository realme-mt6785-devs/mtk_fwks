package android.telecom.Logging;

import android.os.Process;
import android.telecom.Logging.SessionManager;
/* loaded from: classes3.dex */
public final /* synthetic */ class SessionManager$$ExternalSyntheticLambda0 implements SessionManager.ICurrentThreadId {
    public static final /* synthetic */ SessionManager$$ExternalSyntheticLambda0 INSTANCE = new SessionManager$$ExternalSyntheticLambda0();

    private /* synthetic */ SessionManager$$ExternalSyntheticLambda0() {
    }

    @Override // android.telecom.Logging.SessionManager.ICurrentThreadId
    public final int get() {
        return Process.myTid();
    }
}
