package com.android.internal.app.chooser;

import android.content.Intent;
import com.android.internal.app.IResolverActivityExt;
/* loaded from: classes4.dex */
public interface IDisplayResolveInfoExt {
    default void setIsMultiApp(boolean isMultiApp) {
    }

    default int changeUserIdIfNeed(IResolverActivityExt activityext, Intent intent, int userId) {
        return userId;
    }
}
