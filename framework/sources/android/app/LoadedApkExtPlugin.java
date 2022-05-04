package android.app;

import android.app.LoadedApk;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.IResourcesImplExt;
import android.os.Handler;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
import java.util.List;
/* loaded from: classes.dex */
public class LoadedApkExtPlugin {
    public static Class<?> TYPE = RefClass.load(LoadedApkExtPlugin.class, "android.app.LoadedApkExtImpl");
    @MethodParams({Handler.class, ComponentName.class, Runnable.class})
    public static RefStaticMethod<Void> activityThreadHandlerOnServiceDispatcherConnected;
    @MethodParams({ActivityThread.class, ApplicationInfo.class, List.class})
    public static RefStaticMethod<Void> addPathInMakePaths;
    @MethodParams({LoadedApk.ReceiverDispatcher.Args.class, boolean.class, Intent.class, boolean.class})
    public static RefStaticMethod<Void> afterOnReceiveInArgsRunnable;
    @MethodParams({LoadedApk.ReceiverDispatcher.Args.class, boolean.class, Intent.class})
    public static RefStaticMethod<Boolean> beforeOnReceiveInArgsRunnable;
    @MethodParams({LoadedApk.ReceiverDispatcher.Args.class, Intent.class, boolean.class})
    public static RefStaticMethod<Void> inExceptionOnReceiveInArgsRunnable;
    @MethodParams({LoadedApk.ReceiverDispatcher.class})
    public static RefStaticMethod<String> innerReceiverToString;
    @MethodParams({IResourcesImplExt.class, String.class})
    public static RefStaticMethod<Void> modifyResourcesInGetResources;
    @MethodParams({LoadedApk.ReceiverDispatcher.Args.class, boolean.class, Intent.class})
    public static RefStaticMethod<Void> onPerformReceiveWithIntentNonNull;
    @MethodParams({ApplicationInfo.class})
    public static RefStaticMethod<Integer> sGetOverrideDisplayId;
    @MethodParams({String.class, String.class})
    public static RefStaticMethod<Void> sLogP;
}
