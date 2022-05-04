package android.content.integrity;

import java.util.function.Predicate;
/* loaded from: classes.dex */
public final /* synthetic */ class CompoundFormula$$ExternalSyntheticLambda3 implements Predicate {
    public static final /* synthetic */ CompoundFormula$$ExternalSyntheticLambda3 INSTANCE = new CompoundFormula$$ExternalSyntheticLambda3();

    private /* synthetic */ CompoundFormula$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean isInstallerFormula;
        isInstallerFormula = ((IntegrityFormula) obj).isInstallerFormula();
        return isInstallerFormula;
    }
}
