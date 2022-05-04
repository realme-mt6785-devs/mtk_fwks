package android.view;

import android.os.IBinder;
import android.os.Trace;
import android.util.proto.ProtoOutputStream;
import android.view.SurfaceControl;
import android.view.inputmethod.InputMethodManager;
import java.util.function.Supplier;
/* loaded from: classes3.dex */
public final class ImeInsetsSourceConsumer extends InsetsSourceConsumer {
    private boolean mIsRequestedVisibleAwaitingControl;

    public ImeInsetsSourceConsumer(InsetsState state, Supplier<SurfaceControl.Transaction> transactionSupplier, InsetsController controller) {
        super(19, state, transactionSupplier, controller);
    }

    @Override // android.view.InsetsSourceConsumer
    public void onWindowFocusGained(boolean hasViewFocus) {
        super.onWindowFocusGained(hasViewFocus);
        getImm().registerImeConsumer(this);
    }

    @Override // android.view.InsetsSourceConsumer
    public void onWindowFocusLost() {
        super.onWindowFocusLost();
        getImm().unregisterImeConsumer(this);
        this.mIsRequestedVisibleAwaitingControl = false;
    }

    @Override // android.view.InsetsSourceConsumer
    public void hide() {
        super.hide();
        this.mIsRequestedVisibleAwaitingControl = false;
    }

    @Override // android.view.InsetsSourceConsumer
    void hide(boolean animationFinished, int animationType) {
        hide();
        if (animationFinished) {
            notifyHidden();
            removeSurface();
        }
    }

    @Override // android.view.InsetsSourceConsumer
    public int requestShow(boolean fromIme) {
        if (getControl() == null) {
            this.mIsRequestedVisibleAwaitingControl = true;
        }
        if (fromIme) {
            return 0;
        }
        if (!this.mState.getSource(getType()).isVisible() || getControl() == null) {
            return getImm().requestImeShow(this.mController.getHost().getWindowToken()) ? 1 : 2;
        }
        return 0;
    }

    @Override // android.view.InsetsSourceConsumer
    void notifyHidden() {
        getImm().notifyImeHidden(this.mController.getHost().getWindowToken());
        Trace.asyncTraceEnd(8L, "IC.hideRequestFromApi", 0);
    }

    @Override // android.view.InsetsSourceConsumer
    public void removeSurface() {
        IBinder window = this.mController.getHost().getWindowToken();
        if (window != null) {
            getImm().removeImeSurface(window);
        }
    }

    @Override // android.view.InsetsSourceConsumer
    public void setControl(InsetsSourceControl control, int[] showTypes, int[] hideTypes) {
        super.setControl(control, showTypes, hideTypes);
        if (control == null && !this.mIsRequestedVisibleAwaitingControl) {
            hide();
            removeSurface();
        }
        if (control != null) {
            this.mIsRequestedVisibleAwaitingControl = false;
        }
    }

    @Override // android.view.InsetsSourceConsumer
    protected boolean isRequestedVisibleAwaitingControl() {
        return this.mIsRequestedVisibleAwaitingControl || isRequestedVisible();
    }

    @Override // android.view.InsetsSourceConsumer
    public void onPerceptible(boolean perceptible) {
        super.onPerceptible(perceptible);
        IBinder window = this.mController.getHost().getWindowToken();
        if (window != null) {
            getImm().reportPerceptible(window, perceptible);
        }
    }

    @Override // android.view.InsetsSourceConsumer
    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        long token = proto.start(fieldId);
        super.dumpDebug(proto, 1146756268033L);
        proto.write(1133871366147L, this.mIsRequestedVisibleAwaitingControl);
        proto.end(token);
    }

    private InputMethodManager getImm() {
        return this.mController.getHost().getInputMethodManager();
    }
}
