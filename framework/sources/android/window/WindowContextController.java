package android.window;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
/* loaded from: classes3.dex */
public class WindowContextController {
    public boolean mAttachedToDisplayArea;
    private final WindowTokenClient mToken;
    private final IWindowManager mWms;

    public WindowContextController(WindowTokenClient token) {
        this(token, WindowManagerGlobal.getWindowManagerService());
    }

    public WindowContextController(WindowTokenClient token, IWindowManager mockWms) {
        this.mToken = token;
        this.mWms = mockWms;
    }

    public void attachToDisplayArea(int type, int displayId, Bundle options) {
        if (!this.mAttachedToDisplayArea) {
            try {
                Configuration configuration = this.mWms.attachWindowContextToDisplayArea(this.mToken, type, displayId, options);
                if (configuration != null) {
                    this.mAttachedToDisplayArea = true;
                    this.mToken.onConfigurationChanged(configuration, displayId);
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException("A Window Context can be only attached to a DisplayArea once.");
        }
    }

    public void attachToWindowToken(IBinder windowToken) {
        if (this.mAttachedToDisplayArea) {
            try {
                this.mWms.attachWindowContextToWindowToken(this.mToken, windowToken);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException("The Window Context should have been attached to a DisplayArea.");
        }
    }

    public void detachIfNeeded() {
        if (this.mAttachedToDisplayArea) {
            try {
                this.mWms.detachWindowContextFromWindowContainer(this.mToken);
                this.mAttachedToDisplayArea = false;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }
}
