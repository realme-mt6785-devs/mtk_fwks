package com.oplus.vrr;

import android.os.Bundle;
import java.util.List;
/* loaded from: classes4.dex */
public interface IOPlusRefreshRateManager {
    int findDisplayModeIdByPolicy(int i, int i2, int i3);

    List<String> getList(int i);

    int getModeType(int i);

    float getPreferredFrameRate(String str, String str2);

    int getRefreshRatePolicy(float f);

    boolean hasFlickerRisk();

    boolean isGameAccelerationScene();

    boolean isOAMode();

    boolean isWhiteListGame(String str);

    void notifyBrightnessChange(float f);

    void notifyNitsChange(float f);

    void requestRefreshRate(DisplayRefreshRateRequest displayRefreshRateRequest);

    void screenStateChange(int i);

    void setExternalRefreshRateStatus(int i);

    boolean setFrameRate(float f, String str, String str2, int i);

    void setLowFreqVideo(boolean z);

    void setRefreshRatePolicy(int i, float f, int i2, boolean z);

    void setTgpaGameData(Bundle bundle);

    void updateAccelerationPkgName(String str, int i, int i2);

    void updateDisplayModes(long j);

    /* loaded from: classes4.dex */
    public static class VRRPolicy {
        public static final int POLICY_DEFAULT = 0;
        public static final int POLICY_FACTORY = 5;
        public static final int POLICY_FOD = 4;
        public static final int POLICY_INVALID = -1;
        public static final int POLICY_MEMC = 3;
        public static final int POLICY_OSYNC = 2;
        public static final int POLICY_SYSTEM = 1;

        public static String toString(int value) {
            return value == 0 ? "POLICY_DEFAULT" : value == 1 ? "POLICY_SYSTEM" : value == 2 ? "POLICY_OSYNC" : value == 3 ? "POLICY_MEMC" : value == 5 ? "POLICY_FACTORY" : value == 4 ? "POLICY_FOD" : "POLICY_INVALID";
        }
    }

    /* loaded from: classes4.dex */
    public static final class DisplayRefreshRateRequest {
        public float requestRefreshRate;
        public boolean statusOn = false;
        public int displayId = 0;
        public int policy = 0;

        public void copyFrom(DisplayRefreshRateRequest other) {
            this.displayId = other.displayId;
            this.policy = other.policy;
            this.requestRefreshRate = other.requestRefreshRate;
            this.statusOn = other.statusOn;
        }

        public boolean equals(Object o) {
            return (o instanceof DisplayRefreshRateRequest) && equals((DisplayRefreshRateRequest) o);
        }

        public boolean equals(DisplayRefreshRateRequest other) {
            return other != null && this.displayId == other.displayId && this.policy == other.policy && this.requestRefreshRate == other.requestRefreshRate && this.statusOn == other.statusOn;
        }

        private boolean floatEquals(float f1, float f2) {
            return f1 == f2 || (Float.isNaN(f1) && Float.isNaN(f2));
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "policy=" + VRRPolicy.toString(this.policy) + ", displayId=" + this.displayId + ", requestRefreshRate=" + this.requestRefreshRate + ", statusOn=" + this.statusOn;
        }
    }
}
