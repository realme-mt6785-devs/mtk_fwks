package com.android.internal.inputmethod;

import android.os.RemoteException;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.view.InputBindResult;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
/* loaded from: classes4.dex */
public final class CallbackUtils {
    private CallbackUtils() {
    }

    public static void onResult(IInputBindResultResultCallback callback, Supplier<InputBindResult> resultSupplier) {
        InputBindResult result = null;
        Throwable exception = null;
        try {
            result = resultSupplier.get();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IBooleanResultCallback callback, BooleanSupplier resultSupplier) {
        boolean result = false;
        Throwable exception = null;
        try {
            result = resultSupplier.getAsBoolean();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IInputMethodSubtypeResultCallback callback, Supplier<InputMethodSubtype> resultSupplier) {
        InputMethodSubtype result = null;
        Throwable exception = null;
        try {
            result = resultSupplier.get();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IInputMethodSubtypeListResultCallback callback, Supplier<List<InputMethodSubtype>> resultSupplier) {
        List<InputMethodSubtype> result = null;
        Throwable exception = null;
        try {
            result = resultSupplier.get();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IInputMethodInfoListResultCallback callback, Supplier<List<InputMethodInfo>> resultSupplier) {
        List<InputMethodInfo> result = null;
        Throwable exception = null;
        try {
            result = resultSupplier.get();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IIntResultCallback callback, IntSupplier resultSupplier) {
        int result = 0;
        Throwable exception = null;
        try {
            result = resultSupplier.getAsInt();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IVoidResultCallback callback, Runnable runnable) {
        Throwable exception = null;
        try {
            runnable.run();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult();
            }
        } catch (RemoteException e) {
        }
    }

    public static void onResult(IIInputContentUriTokenResultCallback callback, Supplier<IInputContentUriToken> resultSupplier) {
        IInputContentUriToken result = null;
        Throwable exception = null;
        try {
            result = resultSupplier.get();
        } catch (Throwable throwable) {
            exception = throwable;
        }
        try {
            if (exception != null) {
                callback.onError(ThrowableHolder.of(exception));
            } else {
                callback.onResult(result);
            }
        } catch (RemoteException e) {
        }
    }
}
