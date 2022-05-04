package android.inputmethodservice;

import android.inputmethodservice.MultiClientInputMethodClientCallbackAdaptor;
import android.os.ResultReceiver;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda0 INSTANCE = new MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).hideSoftInput(((Integer) obj2).intValue(), (ResultReceiver) obj3);
    }
}
