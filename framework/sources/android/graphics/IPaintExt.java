package android.graphics;
/* loaded from: classes.dex */
public interface IPaintExt {
    default boolean getZgFlag() {
        return false;
    }

    default Typeface flipTypeface(Typeface typeface) {
        return null;
    }

    default Typeface replaceTypefaceWithVariation(Typeface typeface, Paint paint) {
        return null;
    }

    default void setForceZgFont(long paintPtr) {
    }

    default void injectedByOemOS(Typeface typeface, long paintPtr, Paint paint) {
    }
}
