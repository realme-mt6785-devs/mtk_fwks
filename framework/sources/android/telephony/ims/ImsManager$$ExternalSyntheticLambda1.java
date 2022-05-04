package android.telephony.ims;

import android.telephony.BinderCacheManager;
import com.android.internal.telephony.ITelephony;
/* loaded from: classes3.dex */
public final /* synthetic */ class ImsManager$$ExternalSyntheticLambda1 implements BinderCacheManager.BinderInterfaceFactory {
    public static final /* synthetic */ ImsManager$$ExternalSyntheticLambda1 INSTANCE = new ImsManager$$ExternalSyntheticLambda1();

    private /* synthetic */ ImsManager$$ExternalSyntheticLambda1() {
    }

    @Override // android.telephony.BinderCacheManager.BinderInterfaceFactory
    public final Object create() {
        ITelephony iTelephonyInterface;
        iTelephonyInterface = ImsManager.getITelephonyInterface();
        return iTelephonyInterface;
    }
}
