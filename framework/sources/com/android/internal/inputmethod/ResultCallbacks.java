package com.android.internal.inputmethod;

import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import android.view.inputmethod.SurroundingText;
import com.android.internal.inputmethod.Completable;
import com.android.internal.inputmethod.IBooleanResultCallback;
import com.android.internal.inputmethod.ICharSequenceResultCallback;
import com.android.internal.inputmethod.IExtractedTextResultCallback;
import com.android.internal.inputmethod.IIInputContentUriTokenResultCallback;
import com.android.internal.inputmethod.IInputBindResultResultCallback;
import com.android.internal.inputmethod.IInputMethodInfoListResultCallback;
import com.android.internal.inputmethod.IInputMethodSubtypeListResultCallback;
import com.android.internal.inputmethod.IInputMethodSubtypeResultCallback;
import com.android.internal.inputmethod.IIntResultCallback;
import com.android.internal.inputmethod.ISurroundingTextResultCallback;
import com.android.internal.inputmethod.IVoidResultCallback;
import com.android.internal.view.InputBindResult;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes4.dex */
public final class ResultCallbacks {
    private ResultCallbacks() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T unwrap(AtomicReference<WeakReference<T>> atomicRef) {
        WeakReference<T> ref = atomicRef.getAndSet(null);
        if (ref == null) {
            return null;
        }
        T value = ref.get();
        ref.clear();
        return value;
    }

    public static IIntResultCallback.Stub of(Completable.Int value) {
        final AtomicReference<WeakReference<Completable.Int>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IIntResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.1
            @Override // com.android.internal.inputmethod.IIntResultCallback
            public void onResult(int result) {
                Completable.Int value2 = (Completable.Int) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }

            @Override // com.android.internal.inputmethod.IIntResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.Int value2 = (Completable.Int) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static ICharSequenceResultCallback.Stub of(Completable.CharSequence value) {
        final AtomicReference<WeakReference<Completable.CharSequence>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new ICharSequenceResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.2
            @Override // com.android.internal.inputmethod.ICharSequenceResultCallback
            public void onResult(CharSequence result) {
                Completable.CharSequence value2 = (Completable.CharSequence) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }
        };
    }

    public static IExtractedTextResultCallback.Stub of(Completable.ExtractedText value) {
        final AtomicReference<WeakReference<Completable.ExtractedText>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IExtractedTextResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.3
            @Override // com.android.internal.inputmethod.IExtractedTextResultCallback
            public void onResult(ExtractedText result) {
                Completable.ExtractedText value2 = (Completable.ExtractedText) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }
        };
    }

    public static ISurroundingTextResultCallback.Stub of(Completable.SurroundingText value) {
        final AtomicReference<WeakReference<Completable.SurroundingText>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new ISurroundingTextResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.4
            @Override // com.android.internal.inputmethod.ISurroundingTextResultCallback
            public void onResult(SurroundingText result) {
                Completable.SurroundingText value2 = (Completable.SurroundingText) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }
        };
    }

    public static IInputBindResultResultCallback.Stub of(Completable.InputBindResult value) {
        final AtomicReference<WeakReference<Completable.InputBindResult>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IInputBindResultResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.5
            @Override // com.android.internal.inputmethod.IInputBindResultResultCallback
            public void onResult(InputBindResult result) {
                Completable.InputBindResult value2 = (Completable.InputBindResult) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }

            @Override // com.android.internal.inputmethod.IInputBindResultResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.InputBindResult value2 = (Completable.InputBindResult) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static IBooleanResultCallback.Stub of(Completable.Boolean value) {
        final AtomicReference<WeakReference<Completable.Boolean>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IBooleanResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.6
            @Override // com.android.internal.inputmethod.IBooleanResultCallback
            public void onResult(boolean result) {
                Completable.Boolean value2 = (Completable.Boolean) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(Boolean.valueOf(result));
                }
            }

            @Override // com.android.internal.inputmethod.IBooleanResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.Boolean value2 = (Completable.Boolean) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static IInputMethodSubtypeResultCallback.Stub of(Completable.InputMethodSubtype value) {
        final AtomicReference<WeakReference<Completable.InputMethodSubtype>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IInputMethodSubtypeResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.7
            @Override // com.android.internal.inputmethod.IInputMethodSubtypeResultCallback
            public void onResult(InputMethodSubtype result) {
                Completable.InputMethodSubtype value2 = (Completable.InputMethodSubtype) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodSubtypeResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.InputMethodSubtype value2 = (Completable.InputMethodSubtype) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static IInputMethodSubtypeListResultCallback.Stub of(Completable.InputMethodSubtypeList value) {
        final AtomicReference<WeakReference<Completable.InputMethodSubtypeList>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IInputMethodSubtypeListResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.8
            @Override // com.android.internal.inputmethod.IInputMethodSubtypeListResultCallback
            public void onResult(List<InputMethodSubtype> result) {
                Completable.InputMethodSubtypeList value2 = (Completable.InputMethodSubtypeList) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodSubtypeListResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.InputMethodSubtypeList value2 = (Completable.InputMethodSubtypeList) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static IInputMethodInfoListResultCallback.Stub of(Completable.InputMethodInfoList value) {
        final AtomicReference<WeakReference<Completable.InputMethodInfoList>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IInputMethodInfoListResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.9
            @Override // com.android.internal.inputmethod.IInputMethodInfoListResultCallback
            public void onResult(List<InputMethodInfo> result) {
                Completable.InputMethodInfoList value2 = (Completable.InputMethodInfoList) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodInfoListResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.InputMethodInfoList value2 = (Completable.InputMethodInfoList) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static IVoidResultCallback.Stub of(Completable.Void value) {
        final AtomicReference<WeakReference<Completable.Void>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IVoidResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.10
            @Override // com.android.internal.inputmethod.IVoidResultCallback
            public void onResult() {
                Completable.Void value2 = (Completable.Void) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete();
                }
            }

            @Override // com.android.internal.inputmethod.IVoidResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.Void value2 = (Completable.Void) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }

    public static IIInputContentUriTokenResultCallback.Stub of(Completable.IInputContentUriToken value) {
        final AtomicReference<WeakReference<Completable.IInputContentUriToken>> atomicRef = new AtomicReference<>(new WeakReference(value));
        return new IIInputContentUriTokenResultCallback.Stub() { // from class: com.android.internal.inputmethod.ResultCallbacks.11
            @Override // com.android.internal.inputmethod.IIInputContentUriTokenResultCallback
            public void onResult(IInputContentUriToken result) {
                Completable.IInputContentUriToken value2 = (Completable.IInputContentUriToken) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onComplete(result);
                }
            }

            @Override // com.android.internal.inputmethod.IIInputContentUriTokenResultCallback
            public void onError(ThrowableHolder throwableHolder) {
                Completable.IInputContentUriToken value2 = (Completable.IInputContentUriToken) ResultCallbacks.unwrap(atomicRef);
                if (value2 != null) {
                    value2.onError(throwableHolder);
                }
            }
        };
    }
}
