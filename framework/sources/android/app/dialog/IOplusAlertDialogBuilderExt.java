package android.app.dialog;

import android.app.AlertDialog;
import android.content.Context;
/* loaded from: classes.dex */
public interface IOplusAlertDialogBuilderExt {
    default AlertDialog.Builder getCenterBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }

    default AlertDialog.Builder getBottomBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }
}
