package android.hardware.radio;

import android.hardware.radio.ProgramList;
import java.util.function.Consumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class ProgramList$$ExternalSyntheticLambda7 implements Consumer {
    public static final /* synthetic */ ProgramList$$ExternalSyntheticLambda7 INSTANCE = new ProgramList$$ExternalSyntheticLambda7();

    private /* synthetic */ ProgramList$$ExternalSyntheticLambda7() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((ProgramList.OnCompleteListener) obj).onComplete();
    }
}
