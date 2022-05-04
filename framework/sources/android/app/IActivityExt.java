package android.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.InputEvent;
import android.view.MotionEvent;
/* loaded from: classes.dex */
public interface IActivityExt {
    default void onCreateForActivity(Activity activity, Bundle savedInstanceState) {
    }

    default boolean isLoggable() {
        return false;
    }

    default boolean isAppDebuggable(boolean isAppDebuggable) {
        return isAppDebuggable;
    }

    default void hookForInputLogIsLevelVerbose(String msg) {
    }

    default void hookForInputLogV(String msg) {
    }

    default void hookForInputLogOnTouchEvent(String localClassName, MotionEvent event) {
    }

    default void debugEventHandled(InputEvent event, String detail) {
    }

    default void ormsSendFling(MotionEvent ev, int duration) {
    }

    default void ormsSetSceneActionIfNeeded(Intent intent, String scene, String action, int timeout) {
    }

    default void ormsSetSceneActionForFinish(String scene, String action, int timeout) {
    }

    default void setIsFinishBoost(String name, boolean isFinishBoost) {
    }
}
