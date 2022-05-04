package com.mediatek.server.display;

import android.os.SystemProperties;
import android.view.DisplayInfo;
/* loaded from: classes.dex */
public class MtkDisplayManagerServiceImpl {
    private static final boolean DEBUG = false;
    private static final String TAG = "MtkDisplayManagerServiceImpl";
    private boolean mSupportFullscreenSwitch = "1".equals(SystemProperties.get("ro.vendor.fullscreen_switch"));
    private DisplayInfo mDisplayInfo = new DisplayInfo();

    public void setDisplayInfoForFullscreenSwitch(DisplayInfo displayInfo) {
    }

    public DisplayInfo getDisplayInfoForFullscreenSwitch(DisplayInfo displayInfo, int callingUid) {
        return null;
    }
}
