package android.view;

import java.util.function.Predicate;
/* loaded from: classes3.dex */
public final /* synthetic */ class View$$ExternalSyntheticLambda9 implements Predicate {
    public static final /* synthetic */ View$$ExternalSyntheticLambda9 INSTANCE = new View$$ExternalSyntheticLambda9();

    private /* synthetic */ View$$ExternalSyntheticLambda9() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean startsWith;
        startsWith = ((String) obj).startsWith("*");
        return startsWith;
    }
}
