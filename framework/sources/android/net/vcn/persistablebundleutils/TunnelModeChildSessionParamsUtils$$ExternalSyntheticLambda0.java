package android.net.vcn.persistablebundleutils;

import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda0 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda0 INSTANCE = new TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return ChildSaProposalUtils.fromPersistableBundle(persistableBundle);
    }
}
