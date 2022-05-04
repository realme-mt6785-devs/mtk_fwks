package com.android.internal.telephony;

import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class SipMessageParsingUtils$$ExternalSyntheticLambda4 implements Predicate {
    public static final /* synthetic */ SipMessageParsingUtils$$ExternalSyntheticLambda4 INSTANCE = new SipMessageParsingUtils$$ExternalSyntheticLambda4();

    private /* synthetic */ SipMessageParsingUtils$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean startsWith;
        startsWith = ((String) obj).startsWith("+");
        return startsWith;
    }
}
