package android.text;
/* loaded from: classes3.dex */
public interface IStaticLayoutExt {
    default void setBuilderToTextJustificationHooks() {
    }

    default void setLayoutParaSpacingAdded(Object object, float paraSpacing) {
    }

    default void setTextJustificationHooks() {
    }

    default float getLayoutParaSpacingAdded(StaticLayout layout, Object builder, boolean moreChars, CharSequence source, int endPos) {
        return -1.0f;
    }

    default boolean lineShouldIncludeFontPad(boolean firstLine, StaticLayout layout) {
        return false;
    }

    default boolean lineNeedMultiply(boolean needMultiply, boolean addLastLineLineSpacing, boolean lastLine, StaticLayout layout) {
        return false;
    }
}
