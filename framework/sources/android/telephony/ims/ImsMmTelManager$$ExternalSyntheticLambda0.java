package android.telephony.ims;

import android.telephony.BinderCacheManager;
import com.android.internal.telephony.ITelephony;
/* loaded from: classes3.dex */
public final /* synthetic */ class ImsMmTelManager$$ExternalSyntheticLambda0 implements BinderCacheManager.BinderInterfaceFactory {
    public static final /* synthetic */ ImsMmTelManager$$ExternalSyntheticLambda0 INSTANCE = new ImsMmTelManager$$ExternalSyntheticLambda0();

    private /* synthetic */ ImsMmTelManager$$ExternalSyntheticLambda0() {
    }

    @Override // android.telephony.BinderCacheManager.BinderInterfaceFactory
    public final Object create() {
        ITelephony iTelephonyInterface;
        iTelephonyInterface = ImsMmTelManager.getITelephonyInterface();
        return iTelephonyInterface;
    }
}
