package android.text;
/* loaded from: classes3.dex */
public interface ITextLineExt {
    default boolean hookjustify(float justifyWidth, int spaces, int start, int end, boolean charsValid, char[] chars, CharSequence text, int mstart) {
        return false;
    }
}
