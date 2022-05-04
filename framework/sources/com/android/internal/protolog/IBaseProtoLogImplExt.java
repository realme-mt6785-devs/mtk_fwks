package com.android.internal.protolog;

import java.io.PrintWriter;
/* loaded from: classes4.dex */
public interface IBaseProtoLogImplExt {
    default int setLogging(boolean setTextLogging, boolean value, PrintWriter pw, String... groups) {
        return -1;
    }
}
