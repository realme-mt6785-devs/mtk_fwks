package com.android.internal.app;

import android.provider.DeviceConfig;
import com.android.internal.config.sysui.SystemUiDeviceConfigFlags;
/* loaded from: classes4.dex */
public class ChooserFlags {
    static final boolean USE_PREDICTION_MANAGER_FOR_DIRECT_TARGETS = true;
    public static final boolean USE_SERVICE_TARGETS_FOR_DIRECT_TARGETS = DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_SYSTEMUI, SystemUiDeviceConfigFlags.SHARE_USE_SERVICE_TARGETS, false);
}
