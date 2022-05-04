package android.telephony;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes3.dex */
public interface IPhoneNumberUtilsExt {
    default String getNumberFromIntent(Intent intent, Context context, String number) {
        return number;
    }

    default String stripSeparators(String phoneNumber) {
        return phoneNumber;
    }
}
