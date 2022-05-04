package android.inputmethodservice;

import android.inputmethodservice.MultiClientInputMethodClientCallbackAdaptor;
import java.util.function.Consumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda4 implements Consumer {
    public static final /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda4 INSTANCE = new MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda4();

    private /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).finishSession();
    }
}
