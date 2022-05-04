package android.telephony;

import android.telephony.data.ApnSetting;
/* loaded from: classes3.dex */
public interface IOplusApnSetting {
    default int oemGetApnTypesBitmaskFromString(String types, String operatorNumeric) {
        return 0;
    }

    default boolean oemMergeApnIgnoreProtocolType(ApnSetting firstApn, ApnSetting secondApn) {
        return false;
    }
}
