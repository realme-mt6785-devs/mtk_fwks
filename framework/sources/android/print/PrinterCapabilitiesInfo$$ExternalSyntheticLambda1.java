package android.print;

import java.util.function.IntConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class PrinterCapabilitiesInfo$$ExternalSyntheticLambda1 implements IntConsumer {
    public static final /* synthetic */ PrinterCapabilitiesInfo$$ExternalSyntheticLambda1 INSTANCE = new PrinterCapabilitiesInfo$$ExternalSyntheticLambda1();

    private /* synthetic */ PrinterCapabilitiesInfo$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        PrintAttributes.enforceValidDuplexMode(i);
    }
}
