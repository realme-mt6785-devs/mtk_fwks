package android.inputmethodservice;

import android.inputmethodservice.MultiClientInputMethodClientCallbackAdaptor;
import android.os.Bundle;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda0 INSTANCE = new MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).appPrivateCommand((String) obj2, (Bundle) obj3);
    }
}
