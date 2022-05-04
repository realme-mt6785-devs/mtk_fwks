package com.android.internal.widget;

import android.content.ContentResolver;
/* loaded from: classes4.dex */
public interface ILockPatternUtilsExt {
    default boolean getTimeoutFlag(int userId, LockPatternUtils utils) {
        return false;
    }

    default void setTimeoutFlag(boolean flag, int userId, LockPatternUtils utils) {
    }

    default long getLockoutAttemptDeadline(int userId, LockPatternUtils utils) {
        return 0L;
    }

    default void setLockoutAttemptDeadline(int userId, long deadline, int timeoutMs, LockPatternUtils utils) {
    }

    default void savePasswordLenght(ContentResolver resover, String key, int value, int userId) {
    }
}
