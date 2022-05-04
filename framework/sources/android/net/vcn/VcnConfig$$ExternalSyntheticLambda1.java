package android.net.vcn;

import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class VcnConfig$$ExternalSyntheticLambda1 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ VcnConfig$$ExternalSyntheticLambda1 INSTANCE = new VcnConfig$$ExternalSyntheticLambda1();

    private /* synthetic */ VcnConfig$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return ((VcnGatewayConnectionConfig) obj).toPersistableBundle();
    }
}
