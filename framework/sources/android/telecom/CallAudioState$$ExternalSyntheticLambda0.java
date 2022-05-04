package android.telecom;

import android.bluetooth.BluetoothDevice;
import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class CallAudioState$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ CallAudioState$$ExternalSyntheticLambda0 INSTANCE = new CallAudioState$$ExternalSyntheticLambda0();

    private /* synthetic */ CallAudioState$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((BluetoothDevice) obj).getAddress();
    }
}
