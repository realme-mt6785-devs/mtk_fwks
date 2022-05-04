package android.net.vcn;

import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class VcnConfig$$ExternalSyntheticLambda0 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ VcnConfig$$ExternalSyntheticLambda0 INSTANCE = new VcnConfig$$ExternalSyntheticLambda0();

    private /* synthetic */ VcnConfig$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return new VcnGatewayConnectionConfig(persistableBundle);
    }
}
