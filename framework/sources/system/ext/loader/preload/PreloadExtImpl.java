package system.ext.loader.preload;

import android.media.MediaMetrics;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public abstract class PreloadExtImpl implements IPreloadExt {
    private final String mTag = getClass().getSimpleName();
    private final List<String> mClasses = new ArrayList();

    protected void register(String className) {
        if (className == null) {
            Log.w(this.mTag, "register className is null");
            return;
        }
        synchronized (this.mClasses) {
            this.mClasses.add(className);
        }
    }

    @Override // system.ext.loader.preload.IPreloadExt
    public void preload(ClassLoader classLoader) {
        synchronized (this.mClasses) {
            for (String className : this.mClasses) {
                try {
                    Class.forName(className, true, classLoader);
                    String str = this.mTag;
                    Log.i(str, "Success preloading: " + className);
                } catch (ClassNotFoundException e) {
                    String str2 = this.mTag;
                    Log.w(str2, "Class not found for preloading: " + className);
                } catch (UnsatisfiedLinkError e2) {
                    String str3 = this.mTag;
                    Log.w(str3, "Problem preloading " + className + ": " + e2);
                } catch (Throwable t) {
                    String str4 = this.mTag;
                    Log.e(str4, "Error preloading " + className + MediaMetrics.SEPARATOR, t);
                    if (t instanceof Error) {
                        throw ((Error) t);
                    } else if (t instanceof RuntimeException) {
                        throw ((RuntimeException) t);
                    } else {
                        throw new RuntimeException(t);
                    }
                }
            }
        }
    }
}
