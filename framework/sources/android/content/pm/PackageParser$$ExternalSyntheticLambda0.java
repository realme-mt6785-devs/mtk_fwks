package android.content.pm;

import android.content.pm.PackageParser;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageParser$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ PackageParser$$ExternalSyntheticLambda0 INSTANCE = new PackageParser$$ExternalSyntheticLambda0();

    private /* synthetic */ PackageParser$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(((PackageParser.Activity) obj2).order, ((PackageParser.Activity) obj).order);
        return compare;
    }
}
