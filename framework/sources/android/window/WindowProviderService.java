package android.window;

import android.app.ActivityThread;
import android.app.LoadedApk;
import android.app.Service;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
/* loaded from: classes3.dex */
public abstract class WindowProviderService extends Service {
    private final WindowContextController mController;
    private WindowManager mWindowManager;
    private final WindowTokenClient mWindowToken;

    public abstract int getWindowType();

    public WindowProviderService() {
        WindowTokenClient windowTokenClient = new WindowTokenClient();
        this.mWindowToken = windowTokenClient;
        this.mController = new WindowContextController(windowTokenClient);
    }

    public Bundle getWindowContextOptions() {
        return null;
    }

    public final void attachToWindowToken(IBinder windowToken) {
        this.mController.attachToWindowToken(windowToken);
    }

    @Override // android.app.Service
    public final Context createServiceBaseContext(ActivityThread mainThread, LoadedApk packageInfo) {
        Context context = super.createServiceBaseContext(mainThread, packageInfo);
        Display defaultDisplay = ((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplay(0);
        return context.createTokenContext(this.mWindowToken, defaultDisplay);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mWindowToken.attachContext(this);
        this.mController.attachToDisplayArea(getWindowType(), getDisplayId(), getWindowContextOptions());
        this.mWindowManager = WindowManagerImpl.createWindowContextWindowManager(this);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        if (Context.WINDOW_SERVICE.equals(name)) {
            return this.mWindowManager;
        }
        return super.getSystemService(name);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mController.detachIfNeeded();
    }
}
