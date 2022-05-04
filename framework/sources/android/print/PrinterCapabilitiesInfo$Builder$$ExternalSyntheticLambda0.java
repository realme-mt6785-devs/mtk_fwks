package android.print;

import java.util.function.IntConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda0 implements IntConsumer {
    public static final /* synthetic */ PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda0 INSTANCE = new PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda0();

    private /* synthetic */ PrinterCapabilitiesInfo$Builder$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        PrintAttributes.enforceValidColorMode(i);
    }
}
