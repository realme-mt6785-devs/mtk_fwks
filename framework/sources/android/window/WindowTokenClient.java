package android.window;

import android.app.ActivityThread;
import android.app.IWindowToken;
import android.app.ResourcesManager;
import android.content.Context;
import android.content.res.Configuration;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public class WindowTokenClient extends IWindowToken.Stub {
    private WeakReference<Context> mContextRef = null;
    private final ResourcesManager mResourcesManager = ResourcesManager.getInstance();

    public void attachContext(Context context) {
        if (this.mContextRef == null) {
            this.mContextRef = new WeakReference<>(context);
            return;
        }
        throw new IllegalStateException("Context is already attached.");
    }

    @Override // android.app.IWindowToken
    public void onConfigurationChanged(final Configuration newConfig, int newDisplayId) {
        final Context context = this.mContextRef.get();
        if (context != null) {
            int currentDisplayId = context.getDisplayId();
            boolean configChanged = true;
            boolean displayChanged = newDisplayId != currentDisplayId;
            Configuration config = context.getResources().getConfiguration();
            if (config.diff(newConfig) == 0) {
                configChanged = false;
            }
            if (displayChanged || configChanged) {
                this.mResourcesManager.updateResourcesForActivity(this, newConfig, newDisplayId);
                if (context instanceof WindowContext) {
                    ActivityThread.currentActivityThread().getHandler().post(new Runnable() { // from class: android.window.WindowTokenClient$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ((WindowContext) Context.this).dispatchConfigurationChanged(newConfig);
                        }
                    });
                }
            }
            if (displayChanged) {
                context.updateDisplay(newDisplayId);
            }
        }
    }

    @Override // android.app.IWindowToken
    public void onWindowTokenRemoved() {
        Context context = this.mContextRef.get();
        if (context != null) {
            context.destroy();
            this.mContextRef.clear();
        }
    }
}
