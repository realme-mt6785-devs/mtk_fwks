package android.app;

import android.util.Printer;
import android.util.Slog;
/* loaded from: classes.dex */
public final /* synthetic */ class ActivityThread$ApplicationThread$$ExternalSyntheticLambda0 implements Printer {
    public static final /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda0 INSTANCE = new ActivityThread$ApplicationThread$$ExternalSyntheticLambda0();

    private /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda0() {
    }

    @Override // android.util.Printer
    public final void println(String str) {
        Slog.v(ActivityThread.TAG, "main thread looper msg: " + str);
    }
}
