package android.net.vcn.persistablebundleutils;

import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda1 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda1 INSTANCE = new TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda1();

    private /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return IkeTrafficSelectorUtils.fromPersistableBundle(persistableBundle);
    }
}
