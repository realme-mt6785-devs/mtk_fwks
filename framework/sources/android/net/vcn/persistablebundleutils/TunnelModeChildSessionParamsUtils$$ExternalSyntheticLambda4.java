package android.net.vcn.persistablebundleutils;

import android.net.ipsec.ike.IkeTrafficSelector;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda4 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda4 INSTANCE = new TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda4();

    private /* synthetic */ TunnelModeChildSessionParamsUtils$$ExternalSyntheticLambda4() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return IkeTrafficSelectorUtils.toPersistableBundle((IkeTrafficSelector) obj);
    }
}
