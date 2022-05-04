package android.net.vcn.persistablebundleutils;

import android.os.PersistableBundle;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
/* loaded from: classes2.dex */
public final /* synthetic */ class IkeSessionParamsUtils$IkeAuthDigitalSignConfigUtils$$ExternalSyntheticLambda1 implements PersistableBundleUtils.Serializer {
    public static final /* synthetic */ IkeSessionParamsUtils$IkeAuthDigitalSignConfigUtils$$ExternalSyntheticLambda1 INSTANCE = new IkeSessionParamsUtils$IkeAuthDigitalSignConfigUtils$$ExternalSyntheticLambda1();

    private /* synthetic */ IkeSessionParamsUtils$IkeAuthDigitalSignConfigUtils$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.server.vcn.repackaged.util.PersistableBundleUtils.Serializer
    public final PersistableBundle toPersistableBundle(Object obj) {
        return PersistableBundleUtils.fromByteArray((byte[]) obj);
    }
}
