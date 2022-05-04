package com.android.internal.org.bouncycastle.jcajce.util;

import com.android.internal.org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Provider;
import java.security.Security;
/* loaded from: classes4.dex */
public class BCJcaJceHelper extends ProviderJcaJceHelper {
    private static volatile Provider bcProvider;

    private static synchronized Provider getBouncyCastleProvider() {
        synchronized (BCJcaJceHelper.class) {
            Provider system2 = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
            if (system2 instanceof BouncyCastleProvider) {
                return system2;
            }
            if (bcProvider != null) {
                return bcProvider;
            }
            bcProvider = new BouncyCastleProvider();
            return bcProvider;
        }
    }

    public BCJcaJceHelper() {
        super(getBouncyCastleProvider());
    }
}
