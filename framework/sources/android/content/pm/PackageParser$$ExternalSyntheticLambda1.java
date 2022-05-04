package android.content.pm;

import android.content.pm.PackageParser;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageParser$$ExternalSyntheticLambda1 implements Comparator {
    public static final /* synthetic */ PackageParser$$ExternalSyntheticLambda1 INSTANCE = new PackageParser$$ExternalSyntheticLambda1();

    private /* synthetic */ PackageParser$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(((PackageParser.Activity) obj2).order, ((PackageParser.Activity) obj).order);
        return compare;
    }
}
