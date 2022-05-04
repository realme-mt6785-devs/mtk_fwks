package android.app;

import android.view.KeyEvent;
import android.view.View;
/* loaded from: classes.dex */
public interface IDialogExt {
    public static final int LOG_LEVEL_DEBUG = 1;
    public static final int LOG_LEVEL_VERBOSE = 2;

    default void changeDarkAlgorithmType(View view, int type) {
    }

    default void logEvent(int level, String tag, KeyEvent event, String info) {
    }
}
