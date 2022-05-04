package android.app.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
/* loaded from: classes.dex */
public interface IAlertControllerExt {
    default void init(Context context, DialogInterface di, Window window) {
    }

    default boolean isMessageNeedScroll() {
        return false;
    }

    default void setMessageNeedScroll(boolean messageNeedScroll) {
    }

    default int getDialogType() {
        return 0;
    }

    default void setDialogType(int dialogType) {
    }

    default View getConvertView(View target, int position, int count) {
        return null;
    }

    default int selectContentView() {
        return -1;
    }

    default void setupView() {
    }

    default void setupContent(ViewGroup contentPanel) {
    }

    default void setupButtons(ViewGroup buttonPanel) {
    }

    default boolean isCenterDialog() {
        return false;
    }

    default boolean isOplusStyle(Context context) {
        return false;
    }
}
