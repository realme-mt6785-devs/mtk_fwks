package android.nfc;
/* loaded from: classes2.dex */
public interface INfcAdapterExt {
    default boolean hookEnable() {
        return false;
    }

    default boolean hookDisable() {
        return false;
    }
}
