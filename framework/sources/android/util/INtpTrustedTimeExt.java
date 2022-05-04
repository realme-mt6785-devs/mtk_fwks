package android.util;

import android.content.Context;
import android.net.Network;
/* loaded from: classes3.dex */
public interface INtpTrustedTimeExt {
    default void init(Context context) {
    }

    default boolean refreshOplusNtpTrustedTime(Network network, String server, int localTimeout) {
        return false;
    }

    default boolean isRefreshNtpNeedReturn() {
        return false;
    }
}
