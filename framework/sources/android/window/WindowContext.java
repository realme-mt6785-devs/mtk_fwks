package android.window;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacksController;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import java.lang.ref.Reference;
/* loaded from: classes3.dex */
public class WindowContext extends ContextWrapper {
    private final WindowContextController mController;
    private final Bundle mOptions;
    private final int mType;
    private final ComponentCallbacksController mCallbacksController = new ComponentCallbacksController();
    private final WindowManager mWindowManager = WindowManagerImpl.createWindowContextWindowManager(this);

    public WindowContext(Context base, int type, Bundle options) {
        super(base);
        this.mType = type;
        this.mOptions = options;
        WindowTokenClient token = (WindowTokenClient) getWindowContextToken();
        this.mController = new WindowContextController(token);
        Reference.reachabilityFence(this);
    }

    public void attachToDisplayArea() {
        this.mController.attachToDisplayArea(this.mType, getDisplayId(), this.mOptions);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        if (Context.WINDOW_SERVICE.equals(name)) {
            return this.mWindowManager;
        }
        return super.getSystemService(name);
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public void release() {
        this.mController.detachIfNeeded();
        destroy();
    }

    @Override // android.content.Context
    public void destroy() {
        this.mCallbacksController.clearCallbacks();
        getBaseContext().destroy();
        Reference.reachabilityFence(this);
    }

    @Override // android.content.Context
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        this.mCallbacksController.registerCallbacks(callback);
    }

    @Override // android.content.Context
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        this.mCallbacksController.unregisterCallbacks(callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchConfigurationChanged(Configuration newConfig) {
        this.mCallbacksController.dispatchConfigurationChanged(newConfig);
    }
}
