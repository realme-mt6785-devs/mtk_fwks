package android.content.integrity;

import java.util.function.Predicate;
/* loaded from: classes.dex */
public final /* synthetic */ class CompoundFormula$$ExternalSyntheticLambda2 implements Predicate {
    public static final /* synthetic */ CompoundFormula$$ExternalSyntheticLambda2 INSTANCE = new CompoundFormula$$ExternalSyntheticLambda2();

    private /* synthetic */ CompoundFormula$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean isAppCertificateFormula;
        isAppCertificateFormula = ((IntegrityFormula) obj).isAppCertificateFormula();
        return isAppCertificateFormula;
    }
}
