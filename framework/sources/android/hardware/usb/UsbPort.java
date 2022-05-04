package android.hardware.usb;

import android.annotation.SystemApi;
import android.app.slice.Slice;
import com.android.internal.accessibility.common.ShortcutConstants;
import com.android.internal.util.Preconditions;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public final class UsbPort {
    private static final int NUM_DATA_ROLES = 3;
    private static final int POWER_ROLE_OFFSET = 0;
    private final String mId;
    private final int mSupportedContaminantProtectionModes;
    private final int mSupportedModes;
    private final boolean mSupportsEnableContaminantPresenceDetection;
    private final boolean mSupportsEnableContaminantPresenceProtection;
    private final UsbManager mUsbManager;

    public UsbPort(UsbManager usbManager, String id, int supportedModes, int supportedContaminantProtectionModes, boolean supportsEnableContaminantPresenceProtection, boolean supportsEnableContaminantPresenceDetection) {
        Objects.requireNonNull(id);
        Preconditions.checkFlagsArgument(supportedModes, 15);
        this.mUsbManager = usbManager;
        this.mId = id;
        this.mSupportedModes = supportedModes;
        this.mSupportedContaminantProtectionModes = supportedContaminantProtectionModes;
        this.mSupportsEnableContaminantPresenceProtection = supportsEnableContaminantPresenceProtection;
        this.mSupportsEnableContaminantPresenceDetection = supportsEnableContaminantPresenceDetection;
    }

    public String getId() {
        return this.mId;
    }

    public int getSupportedModes() {
        return this.mSupportedModes;
    }

    public int getSupportedContaminantProtectionModes() {
        return this.mSupportedContaminantProtectionModes;
    }

    public boolean supportsEnableContaminantPresenceProtection() {
        return this.mSupportsEnableContaminantPresenceProtection;
    }

    public boolean supportsEnableContaminantPresenceDetection() {
        return this.mSupportsEnableContaminantPresenceDetection;
    }

    public UsbPortStatus getStatus() {
        return this.mUsbManager.getPortStatus(this);
    }

    public void setRoles(int powerRole, int dataRole) {
        checkRoles(powerRole, dataRole);
        this.mUsbManager.setPortRoles(this, powerRole, dataRole);
    }

    public void enableContaminantDetection(boolean enable) {
        this.mUsbManager.enableContaminantDetection(this, enable);
    }

    public static int combineRolesAsBit(int powerRole, int dataRole) {
        checkRoles(powerRole, dataRole);
        int index = ((powerRole + 0) * 3) + dataRole;
        return 1 << index;
    }

    public static String modeToString(int mode) {
        StringBuilder modeString = new StringBuilder();
        if (mode == 0) {
            return "none";
        }
        if ((mode & 3) == 3) {
            modeString.append("dual, ");
        } else if ((mode & 2) == 2) {
            modeString.append("dfp, ");
        } else if ((mode & 1) == 1) {
            modeString.append("ufp, ");
        }
        if ((mode & 4) == 4) {
            modeString.append("audio_acc, ");
        }
        if ((mode & 8) == 8) {
            modeString.append("debug_acc, ");
        }
        if (modeString.length() == 0) {
            return Integer.toString(mode);
        }
        return modeString.substring(0, modeString.length() - 2);
    }

    public static String powerRoleToString(int role) {
        switch (role) {
            case 0:
                return "no-power";
            case 1:
                return Slice.SUBTYPE_SOURCE;
            case 2:
                return "sink";
            default:
                return Integer.toString(role);
        }
    }

    public static String dataRoleToString(int role) {
        switch (role) {
            case 0:
                return "no-data";
            case 1:
                return "host";
            case 2:
                return UsbManager.EXTRA_DEVICE;
            default:
                return Integer.toString(role);
        }
    }

    public static String contaminantPresenceStatusToString(int contaminantPresenceStatus) {
        switch (contaminantPresenceStatus) {
            case 0:
                return "not-supported";
            case 1:
                return "disabled";
            case 2:
                return "not detected";
            case 3:
                return "detected";
            default:
                return Integer.toString(contaminantPresenceStatus);
        }
    }

    public static String roleCombinationsToString(int combo) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        boolean first = true;
        while (combo != 0) {
            int index = Integer.numberOfTrailingZeros(combo);
            combo &= ~(1 << index);
            int powerRole = (index / 3) + 0;
            int dataRole = index % 3;
            if (first) {
                first = false;
            } else {
                result.append(", ");
            }
            result.append(powerRoleToString(powerRole));
            result.append(ShortcutConstants.SERVICES_SEPARATOR);
            result.append(dataRoleToString(dataRole));
        }
        result.append("]");
        return result.toString();
    }

    public static void checkMode(int powerRole) {
        Preconditions.checkArgumentInRange(powerRole, 0, 3, "portMode");
    }

    public static void checkPowerRole(int dataRole) {
        Preconditions.checkArgumentInRange(dataRole, 0, 2, "powerRole");
    }

    public static void checkDataRole(int mode) {
        Preconditions.checkArgumentInRange(mode, 0, 2, "powerRole");
    }

    public static void checkRoles(int powerRole, int dataRole) {
        Preconditions.checkArgumentInRange(powerRole, 0, 2, "powerRole");
        Preconditions.checkArgumentInRange(dataRole, 0, 2, "dataRole");
    }

    public boolean isModeSupported(int mode) {
        return (this.mSupportedModes & mode) == mode;
    }

    public String toString() {
        return "UsbPort{id=" + this.mId + ", supportedModes=" + modeToString(this.mSupportedModes) + "supportedContaminantProtectionModes=" + this.mSupportedContaminantProtectionModes + "supportsEnableContaminantPresenceProtection=" + this.mSupportsEnableContaminantPresenceProtection + "supportsEnableContaminantPresenceDetection=" + this.mSupportsEnableContaminantPresenceDetection;
    }
}
