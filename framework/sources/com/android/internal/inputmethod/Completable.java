package com.android.internal.inputmethod;

import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes4.dex */
public final class Completable {

    /* loaded from: classes4.dex */
    public static final class Boolean extends Values<Boolean> {
    }

    /* loaded from: classes4.dex */
    public static final class CharSequence extends Values<CharSequence> {
    }

    /* loaded from: classes4.dex */
    public static final class ExtractedText extends Values<android.view.inputmethod.ExtractedText> {
    }

    /* loaded from: classes4.dex */
    public static final class IInputContentUriToken extends Values<com.android.internal.inputmethod.IInputContentUriToken> {
    }

    /* loaded from: classes4.dex */
    public static final class InputBindResult extends Values<com.android.internal.view.InputBindResult> {
    }

    /* loaded from: classes4.dex */
    public static final class InputMethodInfoList extends Values<List<InputMethodInfo>> {
    }

    /* loaded from: classes4.dex */
    public static final class InputMethodSubtype extends Values<android.view.inputmethod.InputMethodSubtype> {
    }

    /* loaded from: classes4.dex */
    public static final class InputMethodSubtypeList extends Values<List<android.view.inputmethod.InputMethodSubtype>> {
    }

    /* loaded from: classes4.dex */
    public static final class SurroundingText extends Values<android.view.inputmethod.SurroundingText> {
    }

    private Completable() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class ValueBase {
        private final CountDownLatch mLatch = new CountDownLatch(1);
        protected final Object mStateLock = new Object();
        protected int mState = 0;
        protected String mMessage = null;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes4.dex */
        protected @interface CompletionState {
            public static final int COMPLETED_WITH_ERROR = 2;
            public static final int COMPLETED_WITH_VALUE = 1;
            public static final int NOT_COMPLETED = 0;
        }

        protected ValueBase() {
        }

        protected static String stateToString(int state) {
            switch (state) {
                case 0:
                    return "NOT_COMPLETED";
                case 1:
                    return "COMPLETED_WITH_VALUE";
                case 2:
                    return "COMPLETED_WITH_ERROR";
                default:
                    return "Unknown(value=" + state + ")";
            }
        }

        public boolean hasValue() {
            boolean z;
            synchronized (this.mStateLock) {
                z = true;
                if (this.mState != 1) {
                    z = false;
                }
            }
            return z;
        }

        protected void enforceGetValueLocked() {
            switch (this.mState) {
                case 0:
                    throw new UnsupportedOperationException("getValue() is allowed only if hasValue() returns true");
                case 1:
                    return;
                case 2:
                    throw new RuntimeException(this.mMessage);
                default:
                    throw new UnsupportedOperationException("getValue() is not allowed on state=" + stateToString(this.mState));
            }
        }

        protected void onComplete() {
            this.mLatch.countDown();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onError(ThrowableHolder throwableHolder) {
            synchronized (this.mStateLock) {
                switch (this.mState) {
                    case 0:
                        this.mMessage = throwableHolder.getMessage();
                        this.mState = 2;
                        break;
                    default:
                        throw new UnsupportedOperationException("onError() is not allowed on state=" + stateToString(this.mState));
                }
            }
            onComplete();
        }

        public boolean await(int timeout, TimeUnit timeUnit, CancellationGroup cancellationGroup) {
            if (cancellationGroup == null) {
                return awaitInner(timeout, timeUnit);
            }
            if (!cancellationGroup.registerLatch(this.mLatch)) {
                return false;
            }
            try {
                return awaitInner(timeout, timeUnit);
            } finally {
                cancellationGroup.unregisterLatch(this.mLatch);
            }
        }

        private boolean awaitInner(int timeout, TimeUnit timeUnit) {
            try {
                return this.mLatch.await(timeout, timeUnit);
            } catch (InterruptedException e) {
                return true;
            }
        }

        public void await() {
            boolean interrupted = false;
            while (true) {
                try {
                    this.mLatch.await();
                    break;
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static final class Int extends ValueBase {
        private int mValue = 0;

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ void await() {
            super.await();
        }

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ boolean await(int i, TimeUnit timeUnit, CancellationGroup cancellationGroup) {
            return super.await(i, timeUnit, cancellationGroup);
        }

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ boolean hasValue() {
            return super.hasValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onComplete(int value) {
            synchronized (this.mStateLock) {
                switch (this.mState) {
                    case 0:
                        this.mValue = value;
                        this.mState = 1;
                        break;
                    default:
                        throw new UnsupportedOperationException("onComplete() is not allowed on state=" + stateToString(this.mState));
                }
            }
            onComplete();
        }

        public int getValue() {
            int i;
            synchronized (this.mStateLock) {
                enforceGetValueLocked();
                i = this.mValue;
            }
            return i;
        }
    }

    /* loaded from: classes4.dex */
    public static final class Void extends ValueBase {
        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ void await() {
            super.await();
        }

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ boolean await(int i, TimeUnit timeUnit, CancellationGroup cancellationGroup) {
            return super.await(i, timeUnit, cancellationGroup);
        }

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ boolean hasValue() {
            return super.hasValue();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public void onComplete() {
            synchronized (this.mStateLock) {
                switch (this.mState) {
                    case 0:
                        this.mState = 1;
                        break;
                    default:
                        throw new UnsupportedOperationException("onComplete() is not allowed on state=" + stateToString(this.mState));
                }
            }
            super.onComplete();
        }

        public void getValue() {
            synchronized (this.mStateLock) {
                enforceGetValueLocked();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class Values<T> extends ValueBase {
        private T mValue = null;

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ void await() {
            super.await();
        }

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ boolean await(int i, TimeUnit timeUnit, CancellationGroup cancellationGroup) {
            return super.await(i, timeUnit, cancellationGroup);
        }

        @Override // com.android.internal.inputmethod.Completable.ValueBase
        public /* bridge */ /* synthetic */ boolean hasValue() {
            return super.hasValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onComplete(T value) {
            synchronized (this.mStateLock) {
                switch (this.mState) {
                    case 0:
                        this.mValue = value;
                        this.mState = 1;
                        break;
                    default:
                        throw new UnsupportedOperationException("onComplete() is not allowed on state=" + stateToString(this.mState));
                }
            }
            onComplete();
        }

        public T getValue() {
            T t;
            synchronized (this.mStateLock) {
                enforceGetValueLocked();
                t = this.mValue;
            }
            return t;
        }
    }

    public static Int createInt() {
        return new Int();
    }

    public static Boolean createBoolean() {
        return new Boolean();
    }

    public static CharSequence createCharSequence() {
        return new CharSequence();
    }

    public static ExtractedText createExtractedText() {
        return new ExtractedText();
    }

    public static SurroundingText createSurroundingText() {
        return new SurroundingText();
    }

    public static InputBindResult createInputBindResult() {
        return new InputBindResult();
    }

    public static InputMethodSubtype createInputMethodSubtype() {
        return new InputMethodSubtype();
    }

    public static InputMethodSubtypeList createInputMethodSubtypeList() {
        return new InputMethodSubtypeList();
    }

    public static InputMethodInfoList createInputMethodInfoList() {
        return new InputMethodInfoList();
    }

    public static IInputContentUriToken createIInputContentUriToken() {
        return new IInputContentUriToken();
    }

    public static Void createVoid() {
        return new Void();
    }

    public static <T> T getResult(Values<T> value) {
        value.await();
        return value.getValue();
    }

    public static int getIntResult(Int value) {
        value.await();
        return value.getValue();
    }

    public static void getResult(Void value) {
        value.await();
        value.getValue();
    }

    public static int getResultOrZero(Int value, String tag, String methodName, CancellationGroup cancellationGroup, int maxWaitTime) {
        boolean timedOut = value.await(maxWaitTime, TimeUnit.MILLISECONDS, cancellationGroup);
        if (value.hasValue()) {
            return value.getValue();
        }
        logInternal(tag, methodName, timedOut, maxWaitTime, 0);
        return 0;
    }

    public static <T> T getResultOrNull(Values<T> value, String tag, String methodName, CancellationGroup cancellationGroup, int maxWaitTime) {
        boolean timedOut = value.await(maxWaitTime, TimeUnit.MILLISECONDS, cancellationGroup);
        if (value.hasValue()) {
            return value.getValue();
        }
        logInternal(tag, methodName, timedOut, maxWaitTime, null);
        return null;
    }

    private static void logInternal(String tag, String methodName, boolean timedOut, int maxWaitTime, Object defaultValue) {
        if (timedOut) {
            Log.w(tag, methodName + " didn't respond in " + maxWaitTime + " msec. Returning default: " + defaultValue);
            return;
        }
        Log.w(tag, methodName + " was canceled before complete. Returning default: " + defaultValue);
    }
}
