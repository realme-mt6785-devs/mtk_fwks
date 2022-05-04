package android.inputmethodservice;

import android.app.Dialog;
import android.content.Context;
import android.database.ContentObserver;
import android.inputmethodservice.InputMethodService;
import android.net.Uri;
import android.view.View;
/* loaded from: classes2.dex */
public interface IInputMethodServiceExt {
    default boolean hookDebugSwitch() {
        return false;
    }

    default void hookOnComputeRaise(InputMethodService.Insets insets, Dialog dialog) {
    }

    default boolean hookHideImmediately(int flags, SoftInputWindow mWindow) {
        return false;
    }

    default void hookSkipInsetChangeMaybe(int flags) {
    }

    default void hookOnColorChange(Uri uri) {
    }

    default void hookOnCreate(ContentObserver mSettingObserver, Context context) {
    }

    default void hookOnDestroy() {
    }

    default void hookUpdateNavigationGuardColorDelay(SoftInputWindow mWindow) {
    }

    default void hookUpdateNavigationGuardColor(SoftInputWindow mWindow) {
    }

    default void hookUpdateNavigationGuardColorForDialog(Dialog mWindow) {
    }

    default void hookUpdateStartTime() {
    }

    default void hookUploadData() {
    }

    default int hookUpdatedFlag() {
        return 0;
    }

    default boolean hookDebugSwitchUpdated(String[] args, boolean DEBUG) {
        return DEBUG;
    }

    default boolean hookShouldPreventTouch(SoftInputWindow mWindow) {
        return false;
    }

    default View hookRootViewUpdated(View rootView) {
        return rootView;
    }

    default boolean isFoldDisplayOpen() {
        return false;
    }
}
