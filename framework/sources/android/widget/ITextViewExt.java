package android.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
/* loaded from: classes3.dex */
public interface ITextViewExt {
    default void init(Context context) {
    }

    default boolean getClipboardStatus() {
        return true;
    }

    default int getTypefaceIndex(int originIndex, int oplusIndex) {
        return originIndex;
    }

    default void replaceFakeBoldToMedium(TextView textView, Typeface typeface, int style) {
    }

    default Typeface flipTypeface(Typeface typeface, Paint paint) {
        return typeface;
    }
}
