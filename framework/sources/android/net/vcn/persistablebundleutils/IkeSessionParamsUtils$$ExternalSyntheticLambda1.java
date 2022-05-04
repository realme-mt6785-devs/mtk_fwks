package android.net.vcn.persistablebundleutils;

import android.net.vcn.persistablebundleutils.IkeSessionParamsUtils;
import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class IkeSessionParamsUtils$$ExternalSyntheticLambda1 implements PersistableBundleUtils.Deserializer {
    public static final /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda1 INSTANCE = new IkeSessionParamsUtils$$ExternalSyntheticLambda1();

    private /* synthetic */ IkeSessionParamsUtils$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Deserializer
    public final Object fromPersistableBundle(PersistableBundle persistableBundle) {
        return new IkeSessionParamsUtils.ConfigRequest(persistableBundle);
    }
}
