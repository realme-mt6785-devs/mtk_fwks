package android.net.vcn.persistablebundleutils;

import android.net.vcn.persistablebundleutils.SaProposalUtilsBase;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class SaProposalUtilsBase$$ExternalSyntheticLambda0 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ SaProposalUtilsBase$$ExternalSyntheticLambda0 INSTANCE = new SaProposalUtilsBase$$ExternalSyntheticLambda0();

    private /* synthetic */ SaProposalUtilsBase$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return ((SaProposalUtilsBase.EncryptionAlgoKeyLenPair) obj).toPersistableBundle();
    }
}
