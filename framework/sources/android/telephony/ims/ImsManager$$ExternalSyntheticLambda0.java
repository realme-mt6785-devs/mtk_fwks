package android.telephony.ims;

import android.telephony.BinderCacheManager;
import android.telephony.ims.aidl.IImsRcsController;
/* loaded from: classes3.dex */
public final /* synthetic */ class ImsManager$$ExternalSyntheticLambda0 implements BinderCacheManager.BinderInterfaceFactory {
    public static final /* synthetic */ ImsManager$$ExternalSyntheticLambda0 INSTANCE = new ImsManager$$ExternalSyntheticLambda0();

    private /* synthetic */ ImsManager$$ExternalSyntheticLambda0() {
    }

    @Override // android.telephony.BinderCacheManager.BinderInterfaceFactory
    public final Object create() {
        IImsRcsController iImsRcsControllerInterface;
        iImsRcsControllerInterface = ImsManager.getIImsRcsControllerInterface();
        return iImsRcsControllerInterface;
    }
}
