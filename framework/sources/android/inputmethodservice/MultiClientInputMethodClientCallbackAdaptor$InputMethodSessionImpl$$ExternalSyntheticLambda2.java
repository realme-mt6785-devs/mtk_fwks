package android.inputmethodservice;

import android.inputmethodservice.MultiClientInputMethodClientCallbackAdaptor;
import com.android.internal.os.SomeArgs;
import java.util.function.BiConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda2 INSTANCE = new MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda2();

    private /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).updateSelection((SomeArgs) obj2);
    }
}
