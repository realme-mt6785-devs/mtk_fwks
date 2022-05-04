package android.graphics;

import java.util.List;
/* loaded from: classes.dex */
public interface IShaderExt {
    default void setColors(long[] color) {
    }

    default long[] getColorLongs() {
        return null;
    }

    default List<long[]> getComposeShaderColor() {
        return null;
    }

    default void setComposeShaderColor(List<long[]> color) {
    }
}
