package com.android.internal.os;

import java.util.HashMap;
/* loaded from: classes4.dex */
public interface IPowerProfileExt {
    default boolean readPowerValuesFromXml(HashMap<String, Double[]> powerArrayMap, HashMap<String, Double> powerItemMap) {
        return false;
    }
}
