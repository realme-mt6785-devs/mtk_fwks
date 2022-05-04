package android.view.inputmethod;

import android.content.Context;
import android.view.View;
/* loaded from: classes3.dex */
public interface IInputMethodManagerExt {
    default void logDebug(String msg) {
    }

    default void printCallPidAndUid(String msg) {
    }

    default int adjustForceFlag(int flags) {
        return flags;
    }

    default void updateOrmsAction() {
    }

    default boolean dynamicallyConfigDebug(String[] args, String key) {
        return false;
    }

    default boolean hookDebug() {
        return false;
    }

    default void updateCursorAnchorInfoToSynergy(CursorAnchorInfo cursorAnchorInfo) {
    }

    default int adjustFlagForWindowingMode(View view, int flags) {
        return flags;
    }

    default void onCallClickBeforeCheckFocus(Context context) {
    }

    default void onCallShowBeforeCheckFocus(Context context) {
    }

    default int adjustStartInputFlags(int flags) {
        return flags;
    }

    default boolean needForceNewFocus() {
        return false;
    }
}
