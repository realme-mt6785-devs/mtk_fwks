package com.android.internal.util;

import android.content.Context;
import android.os.Handler;
import com.android.internal.util.ScreenshotHelper;
/* loaded from: classes4.dex */
public interface IScreenshotHelperExt {
    default boolean takeScreenshotNeedReturn(Context context, int screenshotType, ScreenshotHelper.ScreenshotRequest screenshotRequest, Handler handler) {
        return false;
    }
}
