package android.telephony.ims.stub;

import android.telephony.ims.aidl.IRcsConfigCallback;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ImsConfigImplBase$$ExternalSyntheticLambda5 implements Consumer {
    public static final /* synthetic */ ImsConfigImplBase$$ExternalSyntheticLambda5 INSTANCE = new ImsConfigImplBase$$ExternalSyntheticLambda5();

    private /* synthetic */ ImsConfigImplBase$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ImsConfigImplBase.lambda$onNotifyRcsAutoConfigurationRemoved$3((IRcsConfigCallback) obj);
    }
}
