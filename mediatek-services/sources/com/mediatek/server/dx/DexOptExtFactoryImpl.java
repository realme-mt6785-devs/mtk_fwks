package com.mediatek.server.dx;

import com.mediatek.dx.DexOptExt;
import com.mediatek.dx.DexOptExtFactory;
/* loaded from: classes.dex */
public class DexOptExtFactoryImpl extends DexOptExtFactory {
    public DexOptExt makeDexOpExt() {
        return DexOptExtImpl.getInstance();
    }
}
