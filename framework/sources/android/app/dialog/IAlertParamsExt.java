package android.app.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
/* loaded from: classes.dex */
public interface IAlertParamsExt {
    default void setDialogType(int dialogType) {
    }

    default boolean needHookAdapter(boolean isSingleChoice, boolean isMultiChoice) {
        return !isSingleChoice && !isMultiChoice;
    }

    default ListAdapter getHookAdapter(Context context, CharSequence title, CharSequence message, CharSequence[] items) {
        return null;
    }

    default void hookAlertParaApply(IAlertControllerExt alertController) {
    }

    default void setItems(CharSequence[] items) {
    }

    default void setMessageNeedScroll(boolean messageNeedScroll) {
    }

    default void setSummaries(CharSequence[] summaryItems) {
    }

    default void setOnClickListener(DialogInterface.OnClickListener listener) {
    }

    default void setListStyle(ListView listView, boolean isSingleChoice, boolean isMultiChoice) {
    }

    default View getConvertView(View target, int position, int count) {
        return target;
    }
}
