package com.android.internal.os;

import java.lang.Thread;
/* loaded from: classes4.dex */
public interface IRuntimeInitExt {
    default void uncaughtExceptionExt(Thread t, Throwable e, Thread.UncaughtExceptionHandler eh) {
    }
}
