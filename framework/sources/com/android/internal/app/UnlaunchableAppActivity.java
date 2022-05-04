package com.android.internal.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.internal.R;
/* loaded from: classes4.dex */
public class UnlaunchableAppActivity extends Activity implements DialogInterface.OnDismissListener, DialogInterface.OnClickListener {
    private static final String EXTRA_UNLAUNCHABLE_REASON = "unlaunchable_reason";
    private static final String TAG = "UnlaunchableAppActivity";
    private static final int UNLAUNCHABLE_REASON_QUIET_MODE = 1;
    private final IUnlaunchableAppActivityExt mExt = UnlaunchableAppActivityExtPlugin.constructor.newInstance();
    private int mReason;
    private IntentSender mTarget;
    private int mUserId;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        Intent intent = getIntent();
        this.mReason = intent.getIntExtra(EXTRA_UNLAUNCHABLE_REASON, -1);
        this.mUserId = intent.getIntExtra(Intent.EXTRA_USER_HANDLE, -10000);
        this.mTarget = (IntentSender) intent.getParcelableExtra(Intent.EXTRA_INTENT);
        if (this.mUserId == -10000) {
            Log.wtf(TAG, "Invalid user id: " + this.mUserId + ". Stopping.");
            finish();
        } else if (this.mReason == 1) {
            String dialogTitle = getResources().getString(R.string.work_mode_off_title);
            String dialogMessage = getResources().getString(R.string.work_mode_off_message);
            AlertDialog.Builder builder = new AlertDialog.Builder(this, this.mExt.adjustThemeResIdForDialog()).setTitle(dialogTitle).setMessage(dialogMessage).setOnDismissListener(this);
            if (this.mReason == 1) {
                builder.setPositiveButton(R.string.work_mode_turn_on, this).setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            } else {
                builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            }
            builder.show();
        } else {
            Log.wtf(TAG, "Invalid unlaunchable type: " + this.mReason);
            finish();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        finish();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (this.mReason == 1 && which == -1) {
            final UserManager userManager = UserManager.get(this);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.android.internal.app.UnlaunchableAppActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UnlaunchableAppActivity.this.lambda$onClick$0$UnlaunchableAppActivity(userManager);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onClick$0$UnlaunchableAppActivity(UserManager userManager) {
        userManager.requestQuietModeEnabled(false, UserHandle.of(this.mUserId), this.mTarget);
    }

    private static final Intent createBaseIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("android", UnlaunchableAppActivity.class.getName()));
        intent.setFlags(276824064);
        return intent;
    }

    public static Intent createInQuietModeDialogIntent(int userId) {
        Intent intent = createBaseIntent();
        intent.putExtra(EXTRA_UNLAUNCHABLE_REASON, 1);
        intent.putExtra(Intent.EXTRA_USER_HANDLE, userId);
        return intent;
    }

    public static Intent createInQuietModeDialogIntent(int userId, IntentSender target) {
        Intent intent = createInQuietModeDialogIntent(userId);
        intent.putExtra(Intent.EXTRA_INTENT, target);
        return intent;
    }
}
