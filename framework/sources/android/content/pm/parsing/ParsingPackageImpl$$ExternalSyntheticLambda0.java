package android.content.pm.parsing;

import android.content.pm.parsing.component.ParsedMainComponent;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class ParsingPackageImpl$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ ParsingPackageImpl$$ExternalSyntheticLambda0 INSTANCE = new ParsingPackageImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ ParsingPackageImpl$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(((ParsedMainComponent) obj2).getOrder(), ((ParsedMainComponent) obj).getOrder());
        return compare;
    }
}
