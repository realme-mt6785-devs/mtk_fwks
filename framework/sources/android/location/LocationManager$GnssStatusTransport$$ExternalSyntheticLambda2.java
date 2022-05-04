package android.location;

import android.location.GnssStatus;
import java.util.function.Consumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class LocationManager$GnssStatusTransport$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ LocationManager$GnssStatusTransport$$ExternalSyntheticLambda2 INSTANCE = new LocationManager$GnssStatusTransport$$ExternalSyntheticLambda2();

    private /* synthetic */ LocationManager$GnssStatusTransport$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((GnssStatus.Callback) obj).onStarted();
    }
}
