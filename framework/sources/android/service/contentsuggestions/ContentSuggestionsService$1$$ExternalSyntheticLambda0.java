package android.service.contentsuggestions;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentSuggestionsService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda0 INSTANCE = new ContentSuggestionsService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((ContentSuggestionsService) obj).onProcessContextImage(((Integer) obj2).intValue(), (Bitmap) obj3, (Bundle) obj4);
    }
}
