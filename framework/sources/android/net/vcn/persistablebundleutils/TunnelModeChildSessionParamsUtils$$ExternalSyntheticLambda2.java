package android.net.vcn.persistablebundleutils;

import android.net.vcn.persistablebundleutils.TunnelModeChildSessionParamsUtils;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda2 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda2 INSTANCE = new TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda2();

    private /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return new TunnelModeChildSessionParamsUtils.ConfigRequest(persistableBundle);
    }
}
