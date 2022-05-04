package com.android.internal.app;

import android.Manifest;
import android.app.AppGlobals;
import android.app.KeyguardManager;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.SuspendDialogInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Slog;
import com.android.internal.R;
import com.android.internal.app.AlertController;
import com.android.internal.util.ArrayUtils;
/* loaded from: classes4.dex */
public class SuspendedAppActivity extends AlertActivity implements DialogInterface.OnClickListener {
    public static final String EXTRA_ACTIVITY_OPTIONS = "com.android.internal.app.extra.ACTIVITY_OPTIONS";
    public static final String EXTRA_DIALOG_INFO = "com.android.internal.app.extra.DIALOG_INFO";
    public static final String EXTRA_SUSPENDED_PACKAGE = "com.android.internal.app.extra.SUSPENDED_PACKAGE";
    public static final String EXTRA_SUSPENDING_PACKAGE = "com.android.internal.app.extra.SUSPENDING_PACKAGE";
    public static final String EXTRA_UNSUSPEND_INTENT = "com.android.internal.app.extra.UNSUSPEND_INTENT";
    private static final String PACKAGE_NAME = "com.android.internal.app";
    private static final String TAG = SuspendedAppActivity.class.getSimpleName();
    private Intent mMoreDetailsIntent;
    private int mNeutralButtonAction;
    private IntentSender mOnUnsuspend;
    private Bundle mOptions;
    private PackageManager mPm;
    private SuspendDialogInfo mSuppliedDialogInfo;
    private String mSuspendedPackage;
    private Resources mSuspendingAppResources;
    private String mSuspendingPackage;
    private BroadcastReceiver mUnsuspendReceiver = new BroadcastReceiver() { // from class: com.android.internal.app.SuspendedAppActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_PACKAGES_UNSUSPENDED.equals(intent.getAction())) {
                String[] unsuspended = intent.getStringArrayExtra(Intent.EXTRA_CHANGED_PACKAGE_LIST);
                if (ArrayUtils.contains(unsuspended, SuspendedAppActivity.this.mSuspendedPackage) && !SuspendedAppActivity.this.isFinishing()) {
                    String str = SuspendedAppActivity.TAG;
                    Slog.w(str, "Package " + SuspendedAppActivity.this.mSuspendedPackage + " got unsuspended while the dialog was visible. Finishing.");
                    SuspendedAppActivity.this.finish();
                }
            }
        }
    };
    private int mUserId;
    private UsageStatsManager mUsm;

    private CharSequence getAppLabel(String packageName) {
        try {
            return this.mPm.getApplicationInfoAsUser(packageName, 0, this.mUserId).loadLabel(this.mPm);
        } catch (PackageManager.NameNotFoundException ne) {
            String str = TAG;
            Slog.e(str, "Package " + packageName + " not found", ne);
            return packageName;
        }
    }

    private Intent getMoreDetailsActivity() {
        Intent moreDetailsIntent = new Intent(Intent.ACTION_SHOW_SUSPENDED_APP_DETAILS).setPackage(this.mSuspendingPackage);
        ResolveInfo resolvedInfo = this.mPm.resolveActivityAsUser(moreDetailsIntent, 786432, this.mUserId);
        if (resolvedInfo == null || resolvedInfo.activityInfo == null || !Manifest.permission.SEND_SHOW_SUSPENDED_APP_DETAILS.equals(resolvedInfo.activityInfo.permission)) {
            return null;
        }
        moreDetailsIntent.putExtra("android.intent.extra.PACKAGE_NAME", this.mSuspendedPackage).setFlags(335544320);
        return moreDetailsIntent;
    }

    private Drawable resolveIcon() {
        Resources resources;
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        int iconId = suspendDialogInfo != null ? suspendDialogInfo.getIconResId() : 0;
        if (iconId == 0 || (resources = this.mSuspendingAppResources) == null) {
            return null;
        }
        try {
            return resources.getDrawable(iconId, getTheme());
        } catch (Resources.NotFoundException e) {
            String str = TAG;
            Slog.e(str, "Could not resolve drawable resource id " + iconId);
            return null;
        }
    }

    private String resolveTitle() {
        Resources resources;
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        if (suspendDialogInfo != null) {
            int titleId = suspendDialogInfo.getTitleResId();
            String title = this.mSuppliedDialogInfo.getTitle();
            if (titleId != 0 && (resources = this.mSuspendingAppResources) != null) {
                try {
                    return resources.getString(titleId);
                } catch (Resources.NotFoundException e) {
                    String str = TAG;
                    Slog.e(str, "Could not resolve string resource id " + titleId);
                }
            } else if (title != null) {
                return title;
            }
        }
        return getString(R.string.app_suspended_title);
    }

    private String resolveDialogMessage() {
        Resources resources;
        CharSequence suspendedAppLabel = getAppLabel(this.mSuspendedPackage);
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        if (suspendDialogInfo != null) {
            int messageId = suspendDialogInfo.getDialogMessageResId();
            String message = this.mSuppliedDialogInfo.getDialogMessage();
            if (messageId != 0 && (resources = this.mSuspendingAppResources) != null) {
                try {
                    return resources.getString(messageId, suspendedAppLabel);
                } catch (Resources.NotFoundException e) {
                    String str = TAG;
                    Slog.e(str, "Could not resolve string resource id " + messageId);
                }
            } else if (message != null) {
                return String.format(getResources().getConfiguration().getLocales().get(0), message, suspendedAppLabel);
            }
        }
        return getString(R.string.app_suspended_default_message, suspendedAppLabel, getAppLabel(this.mSuspendingPackage));
    }

    private String resolveNeutralButtonText() {
        int defaultButtonTextId;
        Resources resources;
        switch (this.mNeutralButtonAction) {
            case 0:
                if (this.mMoreDetailsIntent != null) {
                    defaultButtonTextId = R.string.app_suspended_more_details;
                    break;
                } else {
                    return null;
                }
            case 1:
                defaultButtonTextId = R.string.app_suspended_unsuspend_message;
                break;
            default:
                String str = TAG;
                Slog.w(str, "Unknown neutral button action: " + this.mNeutralButtonAction);
                return null;
        }
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        if (suspendDialogInfo != null) {
            int buttonTextId = suspendDialogInfo.getNeutralButtonTextResId();
            String buttonText = this.mSuppliedDialogInfo.getNeutralButtonText();
            if (buttonTextId != 0 && (resources = this.mSuspendingAppResources) != null) {
                try {
                    return resources.getString(buttonTextId);
                } catch (Resources.NotFoundException e) {
                    String str2 = TAG;
                    Slog.e(str2, "Could not resolve string resource id " + buttonTextId);
                }
            } else if (buttonText != null) {
                return buttonText;
            }
        }
        return getString(defaultButtonTextId);
    }

    @Override // com.android.internal.app.AlertActivity, android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.mPm = getPackageManager();
        this.mUsm = (UsageStatsManager) getSystemService(UsageStatsManager.class);
        getWindow().setType(2008);
        Intent intent = getIntent();
        this.mOptions = intent.getBundleExtra(EXTRA_ACTIVITY_OPTIONS);
        int intExtra = intent.getIntExtra(Intent.EXTRA_USER_ID, -1);
        this.mUserId = intExtra;
        if (intExtra < 0) {
            String str = TAG;
            Slog.wtf(str, "Invalid user: " + this.mUserId);
            finish();
            return;
        }
        this.mSuspendedPackage = intent.getStringExtra(EXTRA_SUSPENDED_PACKAGE);
        this.mSuspendingPackage = intent.getStringExtra(EXTRA_SUSPENDING_PACKAGE);
        this.mSuppliedDialogInfo = (SuspendDialogInfo) intent.getParcelableExtra(EXTRA_DIALOG_INFO);
        this.mOnUnsuspend = (IntentSender) intent.getParcelableExtra(EXTRA_UNSUSPEND_INTENT);
        int i = 0;
        if (this.mSuppliedDialogInfo != null) {
            try {
                this.mSuspendingAppResources = createContextAsUser(UserHandle.of(this.mUserId), 0).getPackageManager().getResourcesForApplication(this.mSuspendingPackage);
            } catch (PackageManager.NameNotFoundException ne) {
                String str2 = TAG;
                Slog.e(str2, "Could not find resources for " + this.mSuspendingPackage, ne);
            }
        }
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        if (suspendDialogInfo != null) {
            i = suspendDialogInfo.getNeutralButtonAction();
        }
        this.mNeutralButtonAction = i;
        this.mMoreDetailsIntent = i == 0 ? getMoreDetailsActivity() : null;
        AlertController.AlertParams ap = this.mAlertParams;
        ap.mIcon = resolveIcon();
        ap.mTitle = resolveTitle();
        ap.mMessage = resolveDialogMessage();
        ap.mPositiveButtonText = getString(17039370);
        ap.mNeutralButtonText = resolveNeutralButtonText();
        ap.mNeutralButtonListener = this;
        ap.mPositiveButtonListener = this;
        requestDismissKeyguardIfNeeded(ap.mMessage);
        setupAlert();
        IntentFilter unsuspendFilter = new IntentFilter(Intent.ACTION_PACKAGES_UNSUSPENDED);
        registerReceiverAsUser(this.mUnsuspendReceiver, UserHandle.of(this.mUserId), unsuspendFilter, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mUnsuspendReceiver);
    }

    private void requestDismissKeyguardIfNeeded(CharSequence dismissMessage) {
        KeyguardManager km = (KeyguardManager) getSystemService(KeyguardManager.class);
        if (km.isKeyguardLocked()) {
            km.requestDismissKeyguard(this, dismissMessage, new KeyguardManager.KeyguardDismissCallback() { // from class: com.android.internal.app.SuspendedAppActivity.2
                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissError() {
                    Slog.e(SuspendedAppActivity.TAG, "Error while dismissing keyguard. Keeping the dialog visible.");
                }

                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissCancelled() {
                    Slog.w(SuspendedAppActivity.TAG, "Keyguard dismiss was cancelled. Finishing.");
                    SuspendedAppActivity.this.finish();
                }
            });
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case -3:
                switch (this.mNeutralButtonAction) {
                    case 0:
                        Intent intent = this.mMoreDetailsIntent;
                        if (intent == null) {
                            Slog.wtf(TAG, "Neutral button should not have existed!");
                            break;
                        } else {
                            startActivityAsUser(intent, this.mOptions, UserHandle.of(this.mUserId));
                            break;
                        }
                    case 1:
                        IPackageManager ipm = AppGlobals.getPackageManager();
                        try {
                            String[] errored = ipm.setPackagesSuspendedAsUser(new String[]{this.mSuspendedPackage}, false, null, null, null, this.mSuspendingPackage, this.mUserId);
                            if (!ArrayUtils.contains(errored, this.mSuspendedPackage)) {
                                Intent reportUnsuspend = new Intent().setAction(Intent.ACTION_PACKAGE_UNSUSPENDED_MANUALLY).putExtra("android.intent.extra.PACKAGE_NAME", this.mSuspendedPackage).setPackage(this.mSuspendingPackage).addFlags(16777216);
                                sendBroadcastAsUser(reportUnsuspend, UserHandle.of(this.mUserId));
                                IntentSender intentSender = this.mOnUnsuspend;
                                if (intentSender != null) {
                                    try {
                                        intentSender.sendIntent(this, 0, null, null, null);
                                        break;
                                    } catch (IntentSender.SendIntentException e) {
                                        String str = TAG;
                                        Slog.e(str, "Error while starting intent " + this.mOnUnsuspend, e);
                                        break;
                                    }
                                }
                            } else {
                                String str2 = TAG;
                                Slog.e(str2, "Could not unsuspend " + this.mSuspendedPackage);
                                break;
                            }
                        } catch (RemoteException re) {
                            Slog.e(TAG, "Can't talk to system process", re);
                            break;
                        }
                        break;
                    default:
                        String str3 = TAG;
                        Slog.e(str3, "Unexpected action on neutral button: " + this.mNeutralButtonAction);
                        break;
                }
        }
        this.mUsm.reportUserInteraction(this.mSuspendingPackage, this.mUserId);
        finish();
    }

    public static Intent createSuspendedAppInterceptIntent(String suspendedPackage, String suspendingPackage, SuspendDialogInfo dialogInfo, Bundle options, IntentSender onUnsuspend, int userId) {
        return new Intent().setClassName("android", SuspendedAppActivity.class.getName()).putExtra(EXTRA_SUSPENDED_PACKAGE, suspendedPackage).putExtra(EXTRA_DIALOG_INFO, dialogInfo).putExtra(EXTRA_SUSPENDING_PACKAGE, suspendingPackage).putExtra(EXTRA_UNSUSPEND_INTENT, onUnsuspend).putExtra(EXTRA_ACTIVITY_OPTIONS, options).putExtra(Intent.EXTRA_USER_ID, userId).setFlags(276824064);
    }
}
