package android.net.vcn.persistablebundleutils;

import android.net.vcn.persistablebundleutils.SaProposalUtilsBase;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class ChildSaProposalUtils$$ExternalSyntheticLambda0 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ ChildSaProposalUtils$$ExternalSyntheticLambda0 INSTANCE = new ChildSaProposalUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ ChildSaProposalUtils$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return new SaProposalUtilsBase.EncryptionAlgoKeyLenPair(persistableBundle);
    }
}
