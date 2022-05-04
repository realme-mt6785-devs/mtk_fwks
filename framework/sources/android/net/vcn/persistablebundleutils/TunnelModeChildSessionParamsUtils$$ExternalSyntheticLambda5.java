package android.net.vcn.persistablebundleutils;

import android.net.vcn.persistablebundleutils.TunnelModeChildSessionParamsUtils;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda5 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda5 INSTANCE = new TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda5();

    private /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda5() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return ((TunnelModeChildSessionParamsUtils.ConfigRequest) obj).toPersistableBundle();
    }
}
