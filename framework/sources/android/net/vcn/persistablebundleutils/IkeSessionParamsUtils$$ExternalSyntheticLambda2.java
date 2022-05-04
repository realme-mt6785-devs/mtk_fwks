package android.net.vcn.persistablebundleutils;

import android.net.ipsec.ike.IkeSaProposal;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class IkeSessionParamsUtils$$ExternalSyntheticLambda2 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda2 INSTANCE = new IkeSessionParamsUtils$$ExternalSyntheticLambda2();

    private /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return IkeSaProposalUtils.toPersistableBundle((IkeSaProposal) obj);
    }
}
