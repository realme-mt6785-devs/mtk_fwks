package android.print;

import java.util.function.IntConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda1 implements IntConsumer {
    public static final /* synthetic */ PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda1 INSTANCE = new PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda1();

    private /* synthetic */ PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        PrintAttributes.enforceValidDuplexMode(i);
    }
}
