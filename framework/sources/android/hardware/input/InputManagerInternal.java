package android.hardware.input;

import android.hardware.display.DisplayViewport;
import android.os.IBinder;
import android.view.InputEvent;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class InputManagerInternal {

    /* loaded from: classes2.dex */
    public interface LidSwitchCallback {
        void notifyLidSwitchChanged(long j, boolean z);
    }

    public abstract boolean injectInputEvent(InputEvent inputEvent, int i);

    public abstract void registerLidSwitchCallback(LidSwitchCallback lidSwitchCallback);

    public abstract void setDisplayViewports(List<DisplayViewport> list);

    public abstract void setInteractive(boolean z);

    public abstract void setPulseGestureEnabled(boolean z);

    public abstract void toggleCapsLock(int i);

    public abstract boolean transferTouchFocus(IBinder iBinder, IBinder iBinder2);

    public abstract void unregisterLidSwitchCallback(LidSwitchCallback lidSwitchCallback);
}
