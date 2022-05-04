package android.net.vcn.persistablebundleutils;

import android.net.vcn.persistablebundleutils.IkeSessionParamsUtils;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class IkeSessionParamsUtils$$ExternalSyntheticLambda3 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda3 INSTANCE = new IkeSessionParamsUtils$$ExternalSyntheticLambda3();

    private /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return ((IkeSessionParamsUtils.ConfigRequest) obj).toPersistableBundle();
    }
}
