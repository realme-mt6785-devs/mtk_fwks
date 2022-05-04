package com.android.internal.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Slog;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;
import com.android.internal.R;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.accessibility.dialog.AccessibilityTarget;
import com.android.internal.accessibility.dialog.AccessibilityTargetHelper;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.function.pooled.PooledLambda;
import com.oplus.uifirst.IOplusUIFirstManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes4.dex */
public class AccessibilityShortcutController {
    public static final String MAGNIFICATION_CONTROLLER_NAME = "com.android.server.accessibility.MagnificationController";
    private static final String TAG = "AccessibilityShortcutController";
    private static Map<ComponentName, ToggleableFrameworkFeatureInfo> sFrameworkShortcutFeaturesMap;
    private AlertDialog mAlertDialog;
    private final Context mContext;
    private boolean mEnabledOnLockScreen;
    public FrameworkObjectProvider mFrameworkObjectProvider = new FrameworkObjectProvider();
    private final Handler mHandler;
    private boolean mIsShortcutEnabled;
    private int mUserId;
    public static final ComponentName COLOR_INVERSION_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "ColorInversion");
    public static final ComponentName DALTONIZER_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "Daltonizer");
    public static final ComponentName MAGNIFICATION_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "Magnification");
    public static final ComponentName ONE_HANDED_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "OneHandedMode");
    public static final ComponentName REDUCE_BRIGHT_COLORS_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "ReduceBrightColors");
    public static final ComponentName ACCESSIBILITY_BUTTON_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "AccessibilityButton");
    private static final AudioAttributes VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(11).build();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    private @interface DialogStaus {
        public static final int NOT_SHOWN = 0;
        public static final int SHOWN = 1;
    }

    public static Map<ComponentName, ToggleableFrameworkFeatureInfo> getFrameworkShortcutFeaturesMap() {
        if (sFrameworkShortcutFeaturesMap == null) {
            Map<ComponentName, ToggleableFrameworkFeatureInfo> featuresMap = new ArrayMap<>(4);
            featuresMap.put(COLOR_INVERSION_COMPONENT_NAME, new ToggleableFrameworkFeatureInfo(Settings.Secure.ACCESSIBILITY_DISPLAY_INVERSION_ENABLED, IOplusUIFirstManager.APP_START_ANIMATION, "0", R.string.color_inversion_feature_name));
            featuresMap.put(DALTONIZER_COMPONENT_NAME, new ToggleableFrameworkFeatureInfo(Settings.Secure.ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED, IOplusUIFirstManager.APP_START_ANIMATION, "0", R.string.color_correction_feature_name));
            featuresMap.put(ONE_HANDED_COMPONENT_NAME, new ToggleableFrameworkFeatureInfo(Settings.Secure.ONE_HANDED_MODE_ACTIVATED, IOplusUIFirstManager.APP_START_ANIMATION, "0", R.string.one_handed_mode_feature_name));
            featuresMap.put(REDUCE_BRIGHT_COLORS_COMPONENT_NAME, new ToggleableFrameworkFeatureInfo(Settings.Secure.REDUCE_BRIGHT_COLORS_ACTIVATED, IOplusUIFirstManager.APP_START_ANIMATION, "0", R.string.reduce_bright_colors_feature_name));
            sFrameworkShortcutFeaturesMap = Collections.unmodifiableMap(featuresMap);
        }
        return sFrameworkShortcutFeaturesMap;
    }

    public AccessibilityShortcutController(Context context, Handler handler, int initialUserId) {
        this.mContext = context;
        this.mHandler = handler;
        this.mUserId = initialUserId;
        ContentObserver co = new ContentObserver(handler) { // from class: com.android.internal.accessibility.AccessibilityShortcutController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Collection<Uri> uris, int flags, int userId) {
                if (userId == AccessibilityShortcutController.this.mUserId) {
                    AccessibilityShortcutController.this.onSettingsChanged();
                }
            }
        };
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE), false, co, -1);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.ACCESSIBILITY_SHORTCUT_ON_LOCK_SCREEN), false, co, -1);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN), false, co, -1);
        setCurrentUser(this.mUserId);
    }

    public void setCurrentUser(int currentUserId) {
        this.mUserId = currentUserId;
        onSettingsChanged();
    }

    public boolean isAccessibilityShortcutAvailable(boolean phoneLocked) {
        return this.mIsShortcutEnabled && (!phoneLocked || this.mEnabledOnLockScreen);
    }

    public void onSettingsChanged() {
        boolean hasShortcutTarget = hasShortcutTarget();
        ContentResolver cr = this.mContext.getContentResolver();
        boolean z = false;
        int dialogAlreadyShown = Settings.Secure.getIntForUser(cr, Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, this.mUserId);
        if (Settings.Secure.getIntForUser(cr, Settings.Secure.ACCESSIBILITY_SHORTCUT_ON_LOCK_SCREEN, dialogAlreadyShown, this.mUserId) == 1) {
            z = true;
        }
        this.mEnabledOnLockScreen = z;
        this.mIsShortcutEnabled = hasShortcutTarget;
    }

    public void performAccessibilityShortcut() {
        Slog.d(TAG, "Accessibility shortcut activated");
        ContentResolver cr = this.mContext.getContentResolver();
        int userId = ActivityManager.getCurrentUser();
        int dialogAlreadyShown = Settings.Secure.getIntForUser(cr, Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, userId);
        Vibrator vibrator = (Vibrator) this.mContext.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            long[] vibePattern = ArrayUtils.convertToLongArray(this.mContext.getResources().getIntArray(R.array.config_longPressVibePattern));
            vibrator.vibrate(vibePattern, -1, VIBRATION_ATTRIBUTES);
        }
        if (dialogAlreadyShown == 0) {
            AlertDialog createShortcutWarningDialog = createShortcutWarningDialog(userId);
            this.mAlertDialog = createShortcutWarningDialog;
            if (createShortcutWarningDialog != null) {
                if (!performTtsPrompt(createShortcutWarningDialog)) {
                    playNotificationTone();
                }
                Window w = this.mAlertDialog.getWindow();
                WindowManager.LayoutParams attr = w.getAttributes();
                attr.type = 2009;
                w.setAttributes(attr);
                this.mAlertDialog.show();
                Settings.Secure.putIntForUser(cr, Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 1, userId);
                return;
            }
            return;
        }
        playNotificationTone();
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mAlertDialog = null;
        }
        showToast();
        this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).performAccessibilityShortcut();
    }

    private void showToast() {
        String serviceName;
        int i;
        AccessibilityServiceInfo serviceInfo = getInfoForTargetService();
        if (serviceInfo != null && (serviceName = getShortcutFeatureDescription(false)) != null) {
            boolean requestA11yButton = (serviceInfo.flags & 256) != 0;
            boolean isServiceEnabled = isServiceEnabled(serviceInfo);
            if (serviceInfo.getResolveInfo().serviceInfo.applicationInfo.targetSdkVersion <= 29 || !requestA11yButton || !isServiceEnabled) {
                Context context = this.mContext;
                if (isServiceEnabled) {
                    i = R.string.accessibility_shortcut_disabling_service;
                } else {
                    i = R.string.accessibility_shortcut_enabling_service;
                }
                String toastMessageFormatString = context.getString(i);
                String toastMessage = String.format(toastMessageFormatString, serviceName);
                Toast warningToast = this.mFrameworkObjectProvider.makeToastFromText(this.mContext, toastMessage, 1);
                warningToast.show();
            }
        }
    }

    private AlertDialog createShortcutWarningDialog(final int userId) {
        List<AccessibilityTarget> targets = AccessibilityTargetHelper.getTargets(this.mContext, 1);
        if (targets.size() == 0) {
            return null;
        }
        FrameworkObjectProvider frameworkObjectProvider = this.mFrameworkObjectProvider;
        AlertDialog alertDialog = frameworkObjectProvider.getAlertDialogBuilder(frameworkObjectProvider.getSystemUiContext()).setTitle(getShortcutWarningTitle(targets)).setMessage(getShortcutWarningMessage(targets)).setCancelable(false).setNegativeButton(R.string.accessibility_shortcut_on, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.accessibility_shortcut_off, new DialogInterface.OnClickListener() { // from class: com.android.internal.accessibility.AccessibilityShortcutController$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AccessibilityShortcutController.this.lambda$createShortcutWarningDialog$0$AccessibilityShortcutController(userId, dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.internal.accessibility.AccessibilityShortcutController$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                AccessibilityShortcutController.this.lambda$createShortcutWarningDialog$1$AccessibilityShortcutController(userId, dialogInterface);
            }
        }).create();
        return alertDialog;
    }

    public /* synthetic */ void lambda$createShortcutWarningDialog$0$AccessibilityShortcutController(int userId, DialogInterface d, int which) {
        Settings.Secure.putStringForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE, "", userId);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, userId);
    }

    public /* synthetic */ void lambda$createShortcutWarningDialog$1$AccessibilityShortcutController(int userId, DialogInterface d) {
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, userId);
    }

    private String getShortcutWarningTitle(List<AccessibilityTarget> targets) {
        if (targets.size() == 1) {
            return this.mContext.getString(R.string.accessibility_shortcut_single_service_warning_title, targets.get(0).getLabel());
        }
        return this.mContext.getString(R.string.accessibility_shortcut_multiple_service_warning_title);
    }

    private String getShortcutWarningMessage(List<AccessibilityTarget> targets) {
        if (targets.size() == 1) {
            return this.mContext.getString(R.string.accessibility_shortcut_single_service_warning, targets.get(0).getLabel());
        }
        StringBuilder sb = new StringBuilder();
        for (AccessibilityTarget target : targets) {
            sb.append(this.mContext.getString(R.string.accessibility_shortcut_multiple_service_list, target.getLabel()));
        }
        return this.mContext.getString(R.string.accessibility_shortcut_multiple_service_warning, sb.toString());
    }

    private AccessibilityServiceInfo getInfoForTargetService() {
        ComponentName targetComponentName = getShortcutTargetComponentName();
        if (targetComponentName == null) {
            return null;
        }
        AccessibilityManager accessibilityManager = this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext);
        return accessibilityManager.getInstalledServiceInfoWithComponentName(targetComponentName);
    }

    private String getShortcutFeatureDescription(boolean includeSummary) {
        ComponentName targetComponentName = getShortcutTargetComponentName();
        if (targetComponentName == null) {
            return null;
        }
        ToggleableFrameworkFeatureInfo frameworkFeatureInfo = getFrameworkShortcutFeaturesMap().get(targetComponentName);
        if (frameworkFeatureInfo != null) {
            return frameworkFeatureInfo.getLabel(this.mContext);
        }
        AccessibilityServiceInfo serviceInfo = this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).getInstalledServiceInfoWithComponentName(targetComponentName);
        if (serviceInfo == null) {
            return null;
        }
        PackageManager pm = this.mContext.getPackageManager();
        String label = serviceInfo.getResolveInfo().loadLabel(pm).toString();
        CharSequence summary = serviceInfo.loadSummary(pm);
        return (!includeSummary || TextUtils.isEmpty(summary)) ? label : String.format("%s\n%s", label, summary);
    }

    private boolean isServiceEnabled(AccessibilityServiceInfo serviceInfo) {
        AccessibilityManager accessibilityManager = this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext);
        return accessibilityManager.getEnabledAccessibilityServiceList(-1).contains(serviceInfo);
    }

    private boolean hasFeatureLeanback() {
        return this.mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playNotificationTone() {
        int audioAttributesUsage;
        if (hasFeatureLeanback()) {
            audioAttributesUsage = 11;
        } else {
            audioAttributesUsage = 10;
        }
        Ringtone tone = this.mFrameworkObjectProvider.getRingtone(this.mContext, Settings.System.DEFAULT_NOTIFICATION_URI);
        if (tone != null) {
            tone.setAudioAttributes(new AudioAttributes.Builder().setUsage(audioAttributesUsage).build());
            tone.play();
        }
    }

    private boolean performTtsPrompt(AlertDialog alertDialog) {
        String serviceName = getShortcutFeatureDescription(false);
        AccessibilityServiceInfo serviceInfo = getInfoForTargetService();
        if (TextUtils.isEmpty(serviceName) || serviceInfo == null || (serviceInfo.flags & 1024) == 0) {
            return false;
        }
        final TtsPrompt tts = new TtsPrompt(serviceName);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.internal.accessibility.AccessibilityShortcutController$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                AccessibilityShortcutController.TtsPrompt.this.dismiss();
            }
        });
        return true;
    }

    private boolean hasShortcutTarget() {
        String shortcutTargets = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE, this.mUserId);
        if (shortcutTargets == null) {
            shortcutTargets = this.mContext.getString(R.string.config_defaultAccessibilityService);
        }
        return !TextUtils.isEmpty(shortcutTargets);
    }

    private ComponentName getShortcutTargetComponentName() {
        List<String> shortcutTargets = this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).getAccessibilityShortcutTargets(1);
        if (shortcutTargets.size() != 1) {
            return null;
        }
        return ComponentName.unflattenFromString(shortcutTargets.get(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class TtsPrompt implements TextToSpeech.OnInitListener {
        private static final int RETRY_MILLIS = 1000;
        private boolean mDismiss;
        private final CharSequence mText;
        private TextToSpeech mTts;
        private int mRetryCount = 3;
        private boolean mLanguageReady = false;

        TtsPrompt(String serviceName) {
            this.mText = AccessibilityShortcutController.this.mContext.getString(R.string.accessibility_shortcut_spoken_feedback, serviceName);
            this.mTts = AccessibilityShortcutController.this.mFrameworkObjectProvider.getTextToSpeech(AccessibilityShortcutController.this.mContext, this);
        }

        public void dismiss() {
            this.mDismiss = true;
            AccessibilityShortcutController.this.mHandler.sendMessage(PooledLambda.obtainMessage(AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda0.INSTANCE, this.mTts));
        }

        @Override // android.speech.tts.TextToSpeech.OnInitListener
        public void onInit(int status) {
            if (status != 0) {
                Slog.d(AccessibilityShortcutController.TAG, "Tts init fail, status=" + Integer.toString(status));
                AccessibilityShortcutController.this.playNotificationTone();
                return;
            }
            AccessibilityShortcutController.this.mHandler.sendMessage(PooledLambda.obtainMessage(AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda2.INSTANCE, this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void play() {
            if (!this.mDismiss) {
                int status = this.mTts.speak(this.mText, 0, null, null);
                if (status != 0) {
                    Slog.d(AccessibilityShortcutController.TAG, "Tts play fail");
                    AccessibilityShortcutController.this.playNotificationTone();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void waitForTtsReady() {
            if (!this.mDismiss) {
                boolean voiceDataInstalled = false;
                if (!this.mLanguageReady) {
                    int status = this.mTts.setLanguage(Locale.getDefault());
                    this.mLanguageReady = (status == -1 || status == -2) ? false : true;
                }
                if (this.mLanguageReady) {
                    Voice voice = this.mTts.getVoice();
                    if (!(voice == null || voice.getFeatures() == null || voice.getFeatures().contains(TextToSpeech.Engine.KEY_FEATURE_NOT_INSTALLED))) {
                        voiceDataInstalled = true;
                    }
                    if (voiceDataInstalled) {
                        AccessibilityShortcutController.this.mHandler.sendMessage(PooledLambda.obtainMessage(AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda1.INSTANCE, this));
                        return;
                    }
                }
                int i = this.mRetryCount;
                if (i == 0) {
                    Slog.d(AccessibilityShortcutController.TAG, "Tts not ready to speak.");
                    AccessibilityShortcutController.this.playNotificationTone();
                    return;
                }
                this.mRetryCount = i - 1;
                AccessibilityShortcutController.this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda2.INSTANCE, this), 1000L);
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class ToggleableFrameworkFeatureInfo {
        private int mIconDrawableId;
        private final int mLabelStringResourceId;
        private final String mSettingKey;
        private final String mSettingOffValue;
        private final String mSettingOnValue;

        ToggleableFrameworkFeatureInfo(String settingKey, String settingOnValue, String settingOffValue, int labelStringResourceId) {
            this.mSettingKey = settingKey;
            this.mSettingOnValue = settingOnValue;
            this.mSettingOffValue = settingOffValue;
            this.mLabelStringResourceId = labelStringResourceId;
        }

        public String getSettingKey() {
            return this.mSettingKey;
        }

        public String getSettingOnValue() {
            return this.mSettingOnValue;
        }

        public String getSettingOffValue() {
            return this.mSettingOffValue;
        }

        public String getLabel(Context context) {
            return context.getString(this.mLabelStringResourceId);
        }
    }

    /* loaded from: classes4.dex */
    public static class FrameworkObjectProvider {
        public AccessibilityManager getAccessibilityManagerInstance(Context context) {
            return AccessibilityManager.getInstance(context);
        }

        public AlertDialog.Builder getAlertDialogBuilder(Context context) {
            boolean inNightMode = (context.getResources().getConfiguration().uiMode & 48) == 32;
            int themeId = inNightMode ? 16974545 : 16974546;
            return new AlertDialog.Builder(context, themeId);
        }

        public Toast makeToastFromText(Context context, CharSequence charSequence, int duration) {
            return Toast.makeText(context, charSequence, duration);
        }

        public Context getSystemUiContext() {
            return ActivityThread.currentActivityThread().getSystemUiContext();
        }

        public TextToSpeech getTextToSpeech(Context ctx, TextToSpeech.OnInitListener listener) {
            return new TextToSpeech(ctx, listener);
        }

        public Ringtone getRingtone(Context ctx, Uri uri) {
            return RingtoneManager.getRingtone(ctx, uri);
        }
    }
}
