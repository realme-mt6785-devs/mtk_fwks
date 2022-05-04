package android.net.vcn.persistablebundleutils;

import android.net.ipsec.ike.ChildSaProposal;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda3 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda3 INSTANCE = new TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda3();

    private /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return ChildSaProposalUtils.toPersistableBundle((ChildSaProposal) obj);
    }
}
