package android.media;

import java.util.function.IntFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class AudioProfile$$ExternalSyntheticLambda0 implements IntFunction {
    public static final /* synthetic */ AudioProfile$$ExternalSyntheticLambda0 INSTANCE = new AudioProfile$$ExternalSyntheticLambda0();

    private /* synthetic */ AudioProfile$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        String format;
        format = String.format("0x%02X", Integer.valueOf(i));
        return format;
    }
}
