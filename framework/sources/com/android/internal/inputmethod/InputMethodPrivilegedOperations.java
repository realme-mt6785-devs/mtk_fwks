package com.android.internal.inputmethod;

import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.inputmethod.Completable;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class InputMethodPrivilegedOperations {
    private static final String TAG = "InputMethodPrivilegedOperations";
    private final OpsHolder mOps = new OpsHolder();

    /* loaded from: classes4.dex */
    private static final class OpsHolder {
        private IInputMethodPrivilegedOperations mPrivOps;

        private OpsHolder() {
        }

        public synchronized void set(IInputMethodPrivilegedOperations privOps) {
            if (this.mPrivOps == null) {
                this.mPrivOps = privOps;
            } else {
                throw new IllegalStateException("IInputMethodPrivilegedOperations must be set at most once. privOps=" + privOps);
            }
        }

        private static String getCallerMethodName() {
            StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
            if (callStack.length <= 4) {
                return "<bottom of call stack>";
            }
            return callStack[4].getMethodName();
        }

        public synchronized IInputMethodPrivilegedOperations getAndWarnIfNull() {
            if (this.mPrivOps == null) {
                Log.e(InputMethodPrivilegedOperations.TAG, getCallerMethodName() + " is ignored. Call it within attachToken() and InputMethodService.onDestroy()");
            }
            return this.mPrivOps;
        }
    }

    public void set(IInputMethodPrivilegedOperations privOps) {
        Objects.requireNonNull(privOps, "privOps must not be null");
        this.mOps.set(privOps);
    }

    public void setImeWindowStatusAsync(int vis, int backDisposition) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                ops.setImeWindowStatusAsync(vis, backDisposition);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void reportStartInputAsync(IBinder startInputToken) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                ops.reportStartInputAsync(startInputToken);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public IInputContentUriToken createInputContentUriToken(Uri contentUri, String packageName) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops == null) {
            return null;
        }
        try {
            Completable.IInputContentUriToken value = Completable.createIInputContentUriToken();
            ops.createInputContentUriToken(contentUri, packageName, ResultCallbacks.of(value));
            return (IInputContentUriToken) Completable.getResult(value);
        } catch (RemoteException e) {
            return null;
        }
    }

    public void reportFullscreenModeAsync(boolean fullscreen) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                ops.reportFullscreenModeAsync(fullscreen);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void updateStatusIconAsync(String packageName, int iconResId) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                ops.updateStatusIconAsync(packageName, iconResId);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void setInputMethod(String id) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                Completable.Void value = Completable.createVoid();
                ops.setInputMethod(id, ResultCallbacks.of(value));
                Completable.getResult(value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void setInputMethodAndSubtype(String id, InputMethodSubtype subtype) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                Completable.Void value = Completable.createVoid();
                ops.setInputMethodAndSubtype(id, subtype, ResultCallbacks.of(value));
                Completable.getResult(value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void hideMySoftInput(int flags) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                Completable.Void value = Completable.createVoid();
                ops.hideMySoftInput(flags, ResultCallbacks.of(value));
                Completable.getResult(value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void showMySoftInput(int flags) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                Completable.Void value = Completable.createVoid();
                ops.showMySoftInput(flags, ResultCallbacks.of(value));
                Completable.getResult(value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public boolean switchToPreviousInputMethod() {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops == null) {
            return false;
        }
        try {
            Completable.Boolean value = Completable.createBoolean();
            ops.switchToPreviousInputMethod(ResultCallbacks.of(value));
            return ((Boolean) Completable.getResult(value)).booleanValue();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean switchToNextInputMethod(boolean onlyCurrentIme) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops == null) {
            return false;
        }
        try {
            Completable.Boolean value = Completable.createBoolean();
            ops.switchToNextInputMethod(onlyCurrentIme, ResultCallbacks.of(value));
            return ((Boolean) Completable.getResult(value)).booleanValue();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean shouldOfferSwitchingToNextInputMethod() {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops == null) {
            return false;
        }
        try {
            Completable.Boolean value = Completable.createBoolean();
            ops.shouldOfferSwitchingToNextInputMethod(ResultCallbacks.of(value));
            return ((Boolean) Completable.getResult(value)).booleanValue();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void notifyUserActionAsync() {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                ops.notifyUserActionAsync();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void applyImeVisibilityAsync(IBinder showOrHideInputToken, boolean setVisible) {
        IInputMethodPrivilegedOperations ops = this.mOps.getAndWarnIfNull();
        if (ops != null) {
            try {
                ops.applyImeVisibilityAsync(showOrHideInputToken, setVisible);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }
}
