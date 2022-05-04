package android.app;

import java.util.Comparator;
import java.util.Map;
/* loaded from: classes.dex */
public final /* synthetic */ class WallpaperColors$$ExternalSyntheticLambda1 implements Comparator {
    public static final /* synthetic */ WallpaperColors$$ExternalSyntheticLambda1 INSTANCE = new WallpaperColors$$ExternalSyntheticLambda1();

    private /* synthetic */ WallpaperColors$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((Double) ((Map.Entry) obj2).getValue()).compareTo((Double) ((Map.Entry) obj).getValue());
        return compareTo;
    }
}
