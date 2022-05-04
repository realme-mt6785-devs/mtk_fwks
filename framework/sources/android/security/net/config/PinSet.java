package android.security.net.config;

import android.util.ArraySet;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes2.dex */
public final class PinSet {
    public static final PinSet EMPTY_PINSET = new PinSet(Collections.emptySet(), Long.MAX_VALUE);
    public final long expirationTime;
    public final Set<Pin> pins;

    public PinSet(Set<Pin> pins, long expirationTime) {
        if (pins != null) {
            this.pins = pins;
            this.expirationTime = expirationTime;
            return;
        }
        throw new NullPointerException("pins must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getPinAlgorithms() {
        Set<String> algorithms = new ArraySet<>();
        for (Pin pin : this.pins) {
            algorithms.add(pin.digestAlgorithm);
        }
        return algorithms;
    }
}
