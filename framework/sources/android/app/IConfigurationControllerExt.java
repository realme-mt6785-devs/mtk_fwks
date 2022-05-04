package android.app;

import android.content.res.Configuration;
/* loaded from: classes.dex */
public interface IConfigurationControllerExt {
    default boolean hookHandleConfigurationChanged(Application application, Configuration config, int diff, Configuration configuration) {
        return false;
    }
}
