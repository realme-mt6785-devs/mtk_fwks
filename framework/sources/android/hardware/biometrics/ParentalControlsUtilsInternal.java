package android.hardware.biometrics;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.UserHandle;
import android.provider.Settings;
/* loaded from: classes.dex */
public class ParentalControlsUtilsInternal {
    private static final String TEST_ALWAYS_REQUIRE_CONSENT = "android.hardware.biometrics.ParentalControlsUtilsInternal.always_require_consent";

    public static boolean isTestModeEnabled(Context context) {
        return (Build.IS_USERDEBUG || Build.IS_ENG) && Settings.Secure.getInt(context.getContentResolver(), TEST_ALWAYS_REQUIRE_CONSENT, 0) != 0;
    }

    public static boolean parentConsentRequired(Context context, DevicePolicyManager dpm, int modality, UserHandle userHandle) {
        if (isTestModeEnabled(context)) {
            return true;
        }
        return parentConsentRequired(dpm, modality, userHandle);
    }

    public static boolean parentConsentRequired(DevicePolicyManager dpm, int modality, UserHandle userHandle) {
        ComponentName cn = getSupervisionComponentName(dpm, userHandle);
        if (cn == null) {
            return false;
        }
        int keyguardDisabledFeatures = dpm.getKeyguardDisabledFeatures(cn);
        boolean dpmFpDisabled = containsFlag(keyguardDisabledFeatures, 32);
        boolean dpmFaceDisabled = containsFlag(keyguardDisabledFeatures, 128);
        boolean dpmIrisDisabled = containsFlag(keyguardDisabledFeatures, 256);
        if (containsFlag(modality, 2) && dpmFpDisabled) {
            return true;
        }
        if (containsFlag(modality, 8) && dpmFaceDisabled) {
            return true;
        }
        if (!containsFlag(modality, 4) || !dpmIrisDisabled) {
            return false;
        }
        return true;
    }

    public static ComponentName getSupervisionComponentName(DevicePolicyManager dpm, UserHandle userHandle) {
        return dpm.getProfileOwnerOrDeviceOwnerSupervisionComponent(userHandle);
    }

    private static boolean containsFlag(int haystack, int needle) {
        return (haystack & needle) != 0;
    }
}
