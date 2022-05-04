package android.security.keystore2;

import android.system.keystore2.Authorization;
import java.util.function.Predicate;
/* loaded from: classes2.dex */
public final /* synthetic */ class AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda5 implements Predicate {
    public static final /* synthetic */ AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda5 INSTANCE = new AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda5();

    private /* synthetic */ AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return AndroidKeyStoreKeyPairGeneratorSpi.lambda$checkAttestKeyPurpose$0((Authorization) obj);
    }
}
