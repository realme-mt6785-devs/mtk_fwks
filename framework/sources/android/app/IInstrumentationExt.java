package android.app;

import android.content.Intent;
/* loaded from: classes.dex */
public interface IInstrumentationExt {
    default boolean beginHookExecStartActivity(Intent intent, int pid, int uid, String access) {
        return true;
    }
}
