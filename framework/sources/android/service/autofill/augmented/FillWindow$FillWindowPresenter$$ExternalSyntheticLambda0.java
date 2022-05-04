package android.service.autofill.augmented;

import android.view.WindowManager;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class FillWindow$FillWindowPresenter$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ FillWindow$FillWindowPresenter$$ExternalSyntheticLambda0 INSTANCE = new FillWindow$FillWindowPresenter$$ExternalSyntheticLambda0();

    private /* synthetic */ FillWindow$FillWindowPresenter$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((FillWindow) obj).handleShow((WindowManager.LayoutParams) obj2);
    }
}
