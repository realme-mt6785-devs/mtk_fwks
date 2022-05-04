package android.app;

import android.provider.DeviceConfig;
/* loaded from: classes.dex */
public final /* synthetic */ class AppOpsManager$$ExternalSyntheticLambda2 implements DeviceConfig.OnPropertiesChangedListener {
    public static final /* synthetic */ AppOpsManager$$ExternalSyntheticLambda2 INSTANCE = new AppOpsManager$$ExternalSyntheticLambda2();

    private /* synthetic */ AppOpsManager$$ExternalSyntheticLambda2() {
    }

    @Override // android.provider.DeviceConfig.OnPropertiesChangedListener
    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
        AppOpsManager.lambda$new$0(properties);
    }
}
