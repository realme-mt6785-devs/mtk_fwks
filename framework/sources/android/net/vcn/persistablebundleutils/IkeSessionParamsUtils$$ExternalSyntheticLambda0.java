package android.net.vcn.persistablebundleutils;

import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class IkeSessionParamsUtils$$ExternalSyntheticLambda0 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda0 INSTANCE = new IkeSessionParamsUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return IkeSaProposalUtils.fromPersistableBundle(persistableBundle);
    }
}
