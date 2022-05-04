package android.print;

import java.util.function.IntConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class PrinterCapabilitiesInfo$$ExternalSyntheticLambda0 implements IntConsumer {
    public static final /* synthetic */ PrinterCapabilitiesInfo$$ExternalSyntheticLambda0 INSTANCE = new PrinterCapabilitiesInfo$$ExternalSyntheticLambda0();

    private /* synthetic */ PrinterCapabilitiesInfo$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        PrintAttributes.enforceValidColorMode(i);
    }
}
