package android.app;

import com.android.internal.graphics.palette.Palette;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class WallpaperColors$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ WallpaperColors$$ExternalSyntheticLambda0 INSTANCE = new WallpaperColors$$ExternalSyntheticLambda0();

    private /* synthetic */ WallpaperColors$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return WallpaperColors.lambda$fromBitmap$0((Palette.Swatch) obj, (Palette.Swatch) obj2);
    }
}
