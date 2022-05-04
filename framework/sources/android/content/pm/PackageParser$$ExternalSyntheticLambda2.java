package android.content.pm;

import android.content.pm.PackageParser;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageParser$$ExternalSyntheticLambda2 implements Comparator {
    public static final /* synthetic */ PackageParser$$ExternalSyntheticLambda2 INSTANCE = new PackageParser$$ExternalSyntheticLambda2();

    private /* synthetic */ PackageParser$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(((PackageParser.Service) obj2).order, ((PackageParser.Service) obj).order);
        return compare;
    }
}
