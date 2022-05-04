package com.android.internal.os;
/* loaded from: classes4.dex */
public interface IZygoteArgumentsExt {
    default boolean canParseArg(String arg) {
        return false;
    }

    default void doParseArg(String arg) throws IllegalArgumentException {
    }

    default String[] getOplusHiddenApiExemptions() {
        return null;
    }
}
