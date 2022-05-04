package android.view.translation;

import android.icu.util.ULocale;
/* loaded from: classes3.dex */
public interface UiTranslationStateCallback {
    void onFinished();

    void onPaused();

    @Deprecated
    default void onStarted(String sourceLocale, String targetLocale) {
    }

    default void onStarted(ULocale sourceLocale, ULocale targetLocale) {
        onStarted(sourceLocale.getLanguage(), targetLocale.getLanguage());
    }

    default void onResumed(ULocale sourceLocale, ULocale targetLocale) {
    }
}
