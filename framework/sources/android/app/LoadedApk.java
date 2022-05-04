package android.app;

import android.app.IServiceConnection;
import android.app.LoadedApk;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.dex.ArtManager;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.AssetManager;
import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.os.IBinder;
import android.os.OplusJankMonitorExtPlugin;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.security.net.config.NetworkSecurityConfigProvider;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.DisplayAdjustments;
import com.android.internal.util.ArrayUtils;
import com.mediatek.res.ResOptExtFactory;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class LoadedApk {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final boolean DEBUG = false;
    static final String TAG = "LoadedApk";
    private final ActivityThread mActivityThread;
    private AppComponentFactory mAppComponentFactory;
    private String mAppDir;
    private Application mApplication;
    private ApplicationInfo mApplicationInfo;
    private final ClassLoader mBaseClassLoader;
    private ClassLoader mClassLoader;
    private File mCredentialProtectedDataDirFile;
    private String mDataDir;
    private File mDataDirFile;
    private ClassLoader mDefaultClassLoader;
    private File mDeviceProtectedDataDirFile;
    private final DisplayAdjustments mDisplayAdjustments;
    private final boolean mIncludeCode;
    private String[] mLegacyOverlayDirs;
    private String mLibDir;
    private final Object mLock;
    private String[] mOverlayPaths;
    final String mPackageName;
    private final ArrayMap<Context, ArrayMap<BroadcastReceiver, ReceiverDispatcher>> mReceivers;
    private final boolean mRegisterPackage;
    private String mResDir;
    Resources mResources;
    private final boolean mSecurityViolation;
    private final ArrayMap<Context, ArrayMap<ServiceConnection, ServiceDispatcher>> mServices;
    private String[] mSplitAppDirs;
    private String[] mSplitClassLoaderNames;
    private SplitDependencyLoaderImpl mSplitLoader;
    private String[] mSplitNames;
    private String[] mSplitResDirs;
    private final ArrayMap<Context, ArrayMap<ServiceConnection, ServiceDispatcher>> mUnboundServices;
    private final ArrayMap<Context, ArrayMap<BroadcastReceiver, ReceiverDispatcher>> mUnregisteredReceivers;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Application getApplication() {
        return this.mApplication;
    }

    public LoadedApk(ActivityThread activityThread, ApplicationInfo aInfo, CompatibilityInfo compatInfo, ClassLoader baseLoader, boolean securityViolation, boolean includeCode, boolean registerPackage) {
        DisplayAdjustments displayAdjustments = new DisplayAdjustments();
        this.mDisplayAdjustments = displayAdjustments;
        this.mReceivers = new ArrayMap<>();
        this.mUnregisteredReceivers = new ArrayMap<>();
        this.mServices = new ArrayMap<>();
        this.mUnboundServices = new ArrayMap<>();
        this.mLock = new Object();
        this.mActivityThread = activityThread;
        setApplicationInfo(aInfo);
        this.mPackageName = aInfo.packageName;
        this.mBaseClassLoader = baseLoader;
        this.mSecurityViolation = securityViolation;
        this.mIncludeCode = includeCode;
        this.mRegisterPackage = registerPackage;
        displayAdjustments.setCompatibilityInfo(compatInfo);
        this.mAppComponentFactory = createAppFactory(this.mApplicationInfo, baseLoader);
    }

    private static ApplicationInfo adjustNativeLibraryPaths(ApplicationInfo info) {
        if (!(info.primaryCpuAbi == null || info.secondaryCpuAbi == null)) {
            String runtimeIsa = VMRuntime.getRuntime().vmInstructionSet();
            String secondaryIsa = VMRuntime.getInstructionSet(info.secondaryCpuAbi);
            String secondaryDexCodeIsa = SystemProperties.get("ro.dalvik.vm.isa." + secondaryIsa);
            if (runtimeIsa.equals(secondaryDexCodeIsa.isEmpty() ? secondaryIsa : secondaryDexCodeIsa)) {
                ApplicationInfo modified = new ApplicationInfo(info);
                modified.nativeLibraryDir = modified.secondaryNativeLibraryDir;
                modified.primaryCpuAbi = modified.secondaryCpuAbi;
                return modified;
            }
        }
        return info;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoadedApk(ActivityThread activityThread) {
        this.mDisplayAdjustments = new DisplayAdjustments();
        this.mReceivers = new ArrayMap<>();
        this.mUnregisteredReceivers = new ArrayMap<>();
        this.mServices = new ArrayMap<>();
        this.mUnboundServices = new ArrayMap<>();
        this.mLock = new Object();
        this.mActivityThread = activityThread;
        ApplicationInfo applicationInfo = new ApplicationInfo();
        this.mApplicationInfo = applicationInfo;
        applicationInfo.packageName = "android";
        this.mPackageName = "android";
        this.mAppDir = null;
        this.mResDir = null;
        this.mSplitAppDirs = null;
        this.mSplitResDirs = null;
        this.mSplitClassLoaderNames = null;
        this.mLegacyOverlayDirs = null;
        this.mOverlayPaths = null;
        this.mDataDir = null;
        this.mDataDirFile = null;
        this.mDeviceProtectedDataDirFile = null;
        this.mCredentialProtectedDataDirFile = null;
        this.mLibDir = null;
        this.mBaseClassLoader = null;
        this.mSecurityViolation = false;
        this.mIncludeCode = true;
        this.mRegisterPackage = false;
        this.mResources = Resources.getSystem();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        this.mDefaultClassLoader = systemClassLoader;
        AppComponentFactory createAppFactory = createAppFactory(this.mApplicationInfo, systemClassLoader);
        this.mAppComponentFactory = createAppFactory;
        this.mClassLoader = createAppFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void installSystemApplicationInfo(ApplicationInfo info, ClassLoader classLoader) {
        this.mApplicationInfo = info;
        this.mDefaultClassLoader = classLoader;
        AppComponentFactory createAppFactory = createAppFactory(info, classLoader);
        this.mAppComponentFactory = createAppFactory;
        this.mClassLoader = createAppFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo));
    }

    private AppComponentFactory createAppFactory(ApplicationInfo appInfo, ClassLoader cl) {
        if (!(!this.mIncludeCode || appInfo.appComponentFactory == null || cl == null)) {
            try {
                return (AppComponentFactory) cl.loadClass(appInfo.appComponentFactory).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                Slog.e(TAG, "Unable to instantiate appComponentFactory", e);
            }
        }
        return AppComponentFactory.DEFAULT;
    }

    public AppComponentFactory getAppFactory() {
        return this.mAppComponentFactory;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public ApplicationInfo getApplicationInfo() {
        return this.mApplicationInfo;
    }

    public int getTargetSdkVersion() {
        return this.mApplicationInfo.targetSdkVersion;
    }

    public boolean isSecurityViolation() {
        return this.mSecurityViolation;
    }

    public CompatibilityInfo getCompatibilityInfo() {
        return this.mDisplayAdjustments.getCompatibilityInfo();
    }

    public void setCompatibilityInfo(CompatibilityInfo compatInfo) {
        this.mDisplayAdjustments.setCompatibilityInfo(compatInfo);
    }

    private static String[] getLibrariesFor(String packageName) {
        try {
            ApplicationInfo ai = ActivityThread.getPackageManager().getApplicationInfo(packageName, 1024, UserHandle.myUserId());
            if (ai == null) {
                return null;
            }
            return ai.sharedLibraryFiles;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateApplicationInfo(ApplicationInfo aInfo, List<String> oldPaths) {
        setApplicationInfo(aInfo);
        List<String> newPaths = new ArrayList<>();
        makePaths(this.mActivityThread, aInfo, newPaths);
        List<String> addedPaths = new ArrayList<>(newPaths.size());
        if (oldPaths != null) {
            for (String path : newPaths) {
                String apkName = path.substring(path.lastIndexOf(File.separator));
                boolean match = false;
                Iterator<String> it = oldPaths.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String oldPath = it.next();
                    String oldApkName = oldPath.substring(oldPath.lastIndexOf(File.separator));
                    if (apkName.equals(oldApkName)) {
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    addedPaths.add(path);
                }
            }
        } else {
            addedPaths.addAll(newPaths);
        }
        synchronized (this.mLock) {
            createOrUpdateClassLoaderLocked(addedPaths);
            if (this.mResources != null) {
                try {
                    String[] splitPaths = getSplitPaths(null);
                    ResourcesManager instance = ResourcesManager.getInstance();
                    String str = this.mResDir;
                    String[] strArr = this.mLegacyOverlayDirs;
                    String[] strArr2 = this.mOverlayPaths;
                    String[] strArr3 = this.mApplicationInfo.sharedLibraryFiles;
                    CompatibilityInfo compatibilityInfo = getCompatibilityInfo();
                    ClassLoader classLoader = getClassLoader();
                    Application application = this.mApplication;
                    Resources resources = instance.getResources(null, str, splitPaths, strArr, strArr2, strArr3, null, null, compatibilityInfo, classLoader, application == null ? null : application.getResources().getLoaders());
                    this.mResources = resources;
                    if (!(resources == null || resources.getImpl() == null)) {
                        LoadedApkExtPlugin.modifyResourcesInGetResources.call(this.mResources.getImpl().mResourcesImplExt, aInfo.packageName);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    throw new AssertionError("null split not found");
                }
            }
        }
        this.mAppComponentFactory = createAppFactory(aInfo, this.mDefaultClassLoader);
    }

    private void setApplicationInfo(ApplicationInfo aInfo) {
        int myUid = Process.myUid();
        ApplicationInfo aInfo2 = adjustNativeLibraryPaths(aInfo);
        this.mApplicationInfo = aInfo2;
        this.mAppDir = aInfo2.sourceDir;
        this.mResDir = aInfo2.uid == myUid ? aInfo2.sourceDir : aInfo2.publicSourceDir;
        this.mLegacyOverlayDirs = aInfo2.resourceDirs;
        this.mOverlayPaths = aInfo2.overlayPaths;
        this.mDataDir = aInfo2.dataDir;
        this.mLibDir = aInfo2.nativeLibraryDir;
        this.mDataDirFile = FileUtils.newFileOrNull(aInfo2.dataDir);
        this.mDeviceProtectedDataDirFile = FileUtils.newFileOrNull(aInfo2.deviceProtectedDataDir);
        this.mCredentialProtectedDataDirFile = FileUtils.newFileOrNull(aInfo2.credentialProtectedDataDir);
        this.mSplitNames = aInfo2.splitNames;
        this.mSplitAppDirs = aInfo2.splitSourceDirs;
        this.mSplitResDirs = aInfo2.uid == myUid ? aInfo2.splitSourceDirs : aInfo2.splitPublicSourceDirs;
        this.mSplitClassLoaderNames = aInfo2.splitClassLoaderNames;
        if (aInfo2.requestsIsolatedSplitLoading() && !ArrayUtils.isEmpty(this.mSplitNames)) {
            this.mSplitLoader = new SplitDependencyLoaderImpl(aInfo2.splitDependencies);
        }
    }

    public static void makePaths(ActivityThread activityThread, ApplicationInfo aInfo, List<String> outZipPaths) {
        makePaths(activityThread, false, aInfo, outZipPaths, null);
    }

    private static void appendSharedLibrariesLibPathsIfNeeded(List<SharedLibraryInfo> sharedLibraries, ApplicationInfo aInfo, Set<String> outSeenPaths, List<String> outLibPaths) {
        if (sharedLibraries != null) {
            for (SharedLibraryInfo lib : sharedLibraries) {
                if (!lib.isNative()) {
                    List<String> paths = lib.getAllCodePaths();
                    outSeenPaths.addAll(paths);
                    for (String path : paths) {
                        appendApkLibPathIfNeeded(path, aInfo, outLibPaths);
                    }
                    appendSharedLibrariesLibPathsIfNeeded(lib.getDependencies(), aInfo, outSeenPaths, outLibPaths);
                }
            }
        }
    }

    public static void makePaths(ActivityThread activityThread, boolean isBundledApp, ApplicationInfo aInfo, List<String> outZipPaths, List<String> outLibPaths) {
        String[] strArr;
        String appDir = aInfo.sourceDir;
        String libDir = aInfo.nativeLibraryDir;
        outZipPaths.clear();
        outZipPaths.add(appDir);
        if (aInfo.splitSourceDirs != null && !aInfo.requestsIsolatedSplitLoading()) {
            Collections.addAll(outZipPaths, aInfo.splitSourceDirs);
        }
        if (outLibPaths != null) {
            outLibPaths.clear();
        }
        String[] instrumentationLibs = null;
        if (activityThread != null) {
            String instrumentationPackageName = activityThread.mInstrumentationPackageName;
            String instrumentationAppDir = activityThread.mInstrumentationAppDir;
            String[] instrumentationSplitAppDirs = activityThread.mInstrumentationSplitAppDirs;
            String instrumentationLibDir = activityThread.mInstrumentationLibDir;
            String instrumentedAppDir = activityThread.mInstrumentedAppDir;
            String[] instrumentedSplitAppDirs = activityThread.mInstrumentedSplitAppDirs;
            String instrumentedLibDir = activityThread.mInstrumentedLibDir;
            if (appDir.equals(instrumentationAppDir) || appDir.equals(instrumentedAppDir)) {
                outZipPaths.clear();
                outZipPaths.add(instrumentationAppDir);
                if (!instrumentationAppDir.equals(instrumentedAppDir)) {
                    outZipPaths.add(instrumentedAppDir);
                }
                if (!aInfo.requestsIsolatedSplitLoading()) {
                    if (instrumentationSplitAppDirs != null) {
                        Collections.addAll(outZipPaths, instrumentationSplitAppDirs);
                    }
                    if (!instrumentationAppDir.equals(instrumentedAppDir) && instrumentedSplitAppDirs != null) {
                        Collections.addAll(outZipPaths, instrumentedSplitAppDirs);
                    }
                }
                if (outLibPaths != null) {
                    outLibPaths.add(instrumentationLibDir);
                    if (!instrumentationLibDir.equals(instrumentedLibDir)) {
                        outLibPaths.add(instrumentedLibDir);
                    }
                }
                if (!instrumentedAppDir.equals(instrumentationAppDir)) {
                    instrumentationLibs = getLibrariesFor(instrumentationPackageName);
                }
            }
        }
        LoadedApkExtPlugin.addPathInMakePaths.call(activityThread, aInfo, outZipPaths);
        if (outLibPaths != null) {
            if (outLibPaths.isEmpty()) {
                outLibPaths.add(libDir);
            }
            if (aInfo.primaryCpuAbi != null) {
                if (aInfo.targetSdkVersion < 24) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("/system/fake-libs");
                    sb.append(VMRuntime.is64BitAbi(aInfo.primaryCpuAbi) ? "64" : "");
                    outLibPaths.add(sb.toString());
                }
                for (String apk : outZipPaths) {
                    outLibPaths.add(apk + "!/lib/" + aInfo.primaryCpuAbi);
                }
            }
            if (isBundledApp) {
                outLibPaths.add(System.getProperty("java.library.path"));
            }
        }
        Set<String> outSeenPaths = new LinkedHashSet<>();
        appendSharedLibrariesLibPathsIfNeeded(aInfo.sharedLibraryInfos, aInfo, outSeenPaths, outLibPaths);
        if (aInfo.sharedLibraryFiles != null) {
            int index = 0;
            for (String lib : aInfo.sharedLibraryFiles) {
                if (lib.endsWith(".apk") && !outSeenPaths.contains(lib) && !outZipPaths.contains(lib)) {
                    outZipPaths.add(index, lib);
                    index++;
                    appendApkLibPathIfNeeded(lib, aInfo, outLibPaths);
                }
            }
        }
        if (instrumentationLibs != null) {
            for (String lib2 : instrumentationLibs) {
                if (!outZipPaths.contains(lib2)) {
                    outZipPaths.add(0, lib2);
                    appendApkLibPathIfNeeded(lib2, aInfo, outLibPaths);
                }
            }
        }
    }

    private static void appendApkLibPathIfNeeded(String path, ApplicationInfo applicationInfo, List<String> outLibPaths) {
        if (outLibPaths != null && applicationInfo.primaryCpuAbi != null && path.endsWith(".apk") && applicationInfo.targetSdkVersion >= 26) {
            outLibPaths.add(path + "!/lib/" + applicationInfo.primaryCpuAbi);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SplitDependencyLoaderImpl extends SplitDependencyLoader<PackageManager.NameNotFoundException> {
        private final ClassLoader[] mCachedClassLoaders;
        private final String[][] mCachedResourcePaths;

        SplitDependencyLoaderImpl(SparseArray<int[]> dependencies) {
            super(dependencies);
            this.mCachedResourcePaths = new String[LoadedApk.this.mSplitNames.length + 1];
            this.mCachedClassLoaders = new ClassLoader[LoadedApk.this.mSplitNames.length + 1];
        }

        @Override // android.content.pm.split.SplitDependencyLoader
        protected boolean isSplitCached(int splitIdx) {
            boolean z;
            synchronized (LoadedApk.this.mLock) {
                z = this.mCachedClassLoaders[splitIdx] != null;
            }
            return z;
        }

        @Override // android.content.pm.split.SplitDependencyLoader
        protected void constructSplit(int splitIdx, int[] configSplitIndices, int parentSplitIdx) throws PackageManager.NameNotFoundException {
            synchronized (LoadedApk.this.mLock) {
                ArrayList<String> splitPaths = new ArrayList<>();
                if (splitIdx == 0) {
                    LoadedApk.this.createOrUpdateClassLoaderLocked(null);
                    this.mCachedClassLoaders[0] = LoadedApk.this.mClassLoader;
                    for (int configSplitIdx : configSplitIndices) {
                        splitPaths.add(LoadedApk.this.mSplitResDirs[configSplitIdx - 1]);
                    }
                    this.mCachedResourcePaths[0] = (String[]) splitPaths.toArray(new String[splitPaths.size()]);
                    return;
                }
                ClassLoader[] classLoaderArr = this.mCachedClassLoaders;
                ClassLoader parent = classLoaderArr[parentSplitIdx];
                classLoaderArr[splitIdx] = ApplicationLoaders.getDefault().getClassLoader(LoadedApk.this.mSplitAppDirs[splitIdx - 1], LoadedApk.this.getTargetSdkVersion(), false, null, null, parent, LoadedApk.this.mSplitClassLoaderNames[splitIdx - 1]);
                Collections.addAll(splitPaths, this.mCachedResourcePaths[parentSplitIdx]);
                splitPaths.add(LoadedApk.this.mSplitResDirs[splitIdx - 1]);
                for (int configSplitIdx2 : configSplitIndices) {
                    splitPaths.add(LoadedApk.this.mSplitResDirs[configSplitIdx2 - 1]);
                }
                this.mCachedResourcePaths[splitIdx] = (String[]) splitPaths.toArray(new String[splitPaths.size()]);
            }
        }

        private int ensureSplitLoaded(String splitName) throws PackageManager.NameNotFoundException {
            int idx = 0;
            if (splitName != null) {
                int idx2 = Arrays.binarySearch(LoadedApk.this.mSplitNames, splitName);
                if (idx2 >= 0) {
                    idx = idx2 + 1;
                } else {
                    throw new PackageManager.NameNotFoundException("Split name '" + splitName + "' is not installed");
                }
            }
            loadDependenciesForSplit(idx);
            return idx;
        }

        ClassLoader getClassLoaderForSplit(String splitName) throws PackageManager.NameNotFoundException {
            ClassLoader classLoader;
            int idx = ensureSplitLoaded(splitName);
            synchronized (LoadedApk.this.mLock) {
                classLoader = this.mCachedClassLoaders[idx];
            }
            return classLoader;
        }

        String[] getSplitPathsForSplit(String splitName) throws PackageManager.NameNotFoundException {
            String[] strArr;
            int idx = ensureSplitLoaded(splitName);
            synchronized (LoadedApk.this.mLock) {
                strArr = this.mCachedResourcePaths[idx];
            }
            return strArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassLoader getSplitClassLoader(String splitName) throws PackageManager.NameNotFoundException {
        SplitDependencyLoaderImpl splitDependencyLoaderImpl = this.mSplitLoader;
        if (splitDependencyLoaderImpl == null) {
            return this.mClassLoader;
        }
        return splitDependencyLoaderImpl.getClassLoaderForSplit(splitName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getSplitPaths(String splitName) throws PackageManager.NameNotFoundException {
        SplitDependencyLoaderImpl splitDependencyLoaderImpl = this.mSplitLoader;
        if (splitDependencyLoaderImpl == null) {
            return this.mSplitResDirs;
        }
        return splitDependencyLoaderImpl.getSplitPathsForSplit(splitName);
    }

    ClassLoader createSharedLibraryLoader(SharedLibraryInfo sharedLibrary, boolean isBundledApp, String librarySearchPath, String libraryPermittedPath) {
        List<String> paths = sharedLibrary.getAllCodePaths();
        List<ClassLoader> sharedLibraries = createSharedLibrariesLoaders(sharedLibrary.getDependencies(), isBundledApp, librarySearchPath, libraryPermittedPath);
        String jars = paths.size() == 1 ? paths.get(0) : TextUtils.join(File.pathSeparator, paths);
        return ApplicationLoaders.getDefault().getSharedLibraryClassLoaderWithSharedLibraries(jars, this.mApplicationInfo.targetSdkVersion, isBundledApp, librarySearchPath, libraryPermittedPath, null, null, sharedLibraries);
    }

    private List<ClassLoader> createSharedLibrariesLoaders(List<SharedLibraryInfo> sharedLibraries, boolean isBundledApp, String librarySearchPath, String libraryPermittedPath) {
        if (sharedLibraries == null) {
            return null;
        }
        List<ClassLoader> loaders = new ArrayList<>();
        for (SharedLibraryInfo info : sharedLibraries) {
            if (!info.isNative()) {
                loaders.add(createSharedLibraryLoader(info, isBundledApp, librarySearchPath, libraryPermittedPath));
            }
        }
        return loaders;
    }

    private StrictMode.ThreadPolicy allowThreadDiskReads() {
        if (this.mActivityThread == null) {
            return null;
        }
        return StrictMode.allowThreadDiskReads();
    }

    private void setThreadPolicy(StrictMode.ThreadPolicy policy) {
        if (this.mActivityThread != null && policy != null) {
            StrictMode.setThreadPolicy(policy);
        }
    }

    private StrictMode.VmPolicy allowVmViolations() {
        if (this.mActivityThread == null) {
            return null;
        }
        return StrictMode.allowVmViolations();
    }

    private void setVmPolicy(StrictMode.VmPolicy policy) {
        if (this.mActivityThread != null && policy != null) {
            StrictMode.setVmPolicy(policy);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void createOrUpdateClassLoaderLocked(java.util.List<java.lang.String> r24) {
        /*
            Method dump skipped, instructions count: 737
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.LoadedApk.createOrUpdateClassLoaderLocked(java.util.List):void");
    }

    private boolean canAccessDataDir() {
        if (this.mActivityThread == null) {
            return false;
        }
        if (Objects.equals(this.mPackageName, ActivityThread.currentPackageName())) {
            return true;
        }
        StrictMode.ThreadPolicy oldThreadPolicy = allowThreadDiskReads();
        StrictMode.VmPolicy oldVmPolicy = allowVmViolations();
        try {
            return new File(this.mDataDir).canExecute();
        } finally {
            setThreadPolicy(oldThreadPolicy);
            setVmPolicy(oldVmPolicy);
        }
    }

    public ClassLoader getClassLoader() {
        ClassLoader classLoader;
        synchronized (this.mLock) {
            if (this.mClassLoader == null) {
                long startTime = SystemClock.uptimeMillis();
                createOrUpdateClassLoaderLocked(null);
                OplusJankMonitorExtPlugin.setLaunchStageTime.call(this.mApplicationInfo.processName, "createOrUpdateClassLoaderLocked", Long.valueOf(startTime));
            }
            classLoader = this.mClassLoader;
        }
        return classLoader;
    }

    private void registerAppInfoToArt() {
        int codePathType;
        if (this.mApplicationInfo.uid == Process.myUid()) {
            List<String> codePaths = new ArrayList<>();
            if ((this.mApplicationInfo.flags & 4) != 0) {
                codePaths.add(this.mApplicationInfo.sourceDir);
            }
            if (this.mApplicationInfo.splitSourceDirs != null) {
                Collections.addAll(codePaths, this.mApplicationInfo.splitSourceDirs);
            }
            if (!codePaths.isEmpty()) {
                int i = codePaths.size() - 1;
                while (i >= 0) {
                    String splitName = i == 0 ? null : this.mApplicationInfo.splitNames[i - 1];
                    String curProfileFile = ArtManager.getCurrentProfilePath(this.mPackageName, UserHandle.myUserId(), splitName);
                    String refProfileFile = ArtManager.getReferenceProfilePath(this.mPackageName, UserHandle.myUserId(), splitName);
                    if (codePaths.get(i).equals(this.mApplicationInfo.sourceDir)) {
                        codePathType = 1;
                    } else {
                        codePathType = 2;
                    }
                    VMRuntime.registerAppInfo(this.mPackageName, curProfileFile, refProfileFile, new String[]{codePaths.get(i)}, codePathType);
                    i--;
                }
                DexLoadReporter.getInstance().registerAppDataDir(this.mPackageName, this.mDataDir);
            }
        }
    }

    private void initializeJavaContextClassLoader() {
        ClassLoader contextClassLoader;
        ActivityThread.getPackageManager();
        PackageInfo pi = PackageManager.getPackageInfoAsUserCached(this.mPackageName, 268435456, UserHandle.myUserId());
        if (pi != null) {
            boolean sharable = true;
            boolean sharedUserIdSet = pi.sharedUserId != null;
            boolean processNameNotDefault = pi.applicationInfo != null && !this.mPackageName.equals(pi.applicationInfo.processName);
            if (!sharedUserIdSet && !processNameNotDefault) {
                sharable = false;
            }
            if (sharable) {
                contextClassLoader = new WarningContextClassLoader();
            } else {
                contextClassLoader = this.mClassLoader;
            }
            Thread.currentThread().setContextClassLoader(contextClassLoader);
            return;
        }
        throw new IllegalStateException("Unable to get package info for " + this.mPackageName + "; is package not installed?");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class WarningContextClassLoader extends ClassLoader {
        private static boolean warned = false;

        private WarningContextClassLoader() {
        }

        private void warn(String methodName) {
            if (!warned) {
                warned = true;
                Thread.currentThread().setContextClassLoader(getParent());
                Slog.w(ActivityThread.TAG, "ClassLoader." + methodName + ": The class loader returned by Thread.getContextClassLoader() may fail for processes that host multiple applications. You should explicitly specify a context class loader. For example: Thread.setContextClassLoader(getClass().getClassLoader());");
            }
        }

        @Override // java.lang.ClassLoader
        public URL getResource(String resName) {
            warn("getResource");
            return getParent().getResource(resName);
        }

        @Override // java.lang.ClassLoader
        public Enumeration<URL> getResources(String resName) throws IOException {
            warn("getResources");
            return getParent().getResources(resName);
        }

        @Override // java.lang.ClassLoader
        public InputStream getResourceAsStream(String resName) {
            warn("getResourceAsStream");
            return getParent().getResourceAsStream(resName);
        }

        @Override // java.lang.ClassLoader
        public Class<?> loadClass(String className) throws ClassNotFoundException {
            warn("loadClass");
            return getParent().loadClass(className);
        }

        @Override // java.lang.ClassLoader
        public void setClassAssertionStatus(String cname, boolean enable) {
            warn("setClassAssertionStatus");
            getParent().setClassAssertionStatus(cname, enable);
        }

        @Override // java.lang.ClassLoader
        public void setPackageAssertionStatus(String pname, boolean enable) {
            warn("setPackageAssertionStatus");
            getParent().setPackageAssertionStatus(pname, enable);
        }

        @Override // java.lang.ClassLoader
        public void setDefaultAssertionStatus(boolean enable) {
            warn("setDefaultAssertionStatus");
            getParent().setDefaultAssertionStatus(enable);
        }

        @Override // java.lang.ClassLoader
        public void clearAssertionStatus() {
            warn("clearAssertionStatus");
            getParent().clearAssertionStatus();
        }
    }

    public String getAppDir() {
        return this.mAppDir;
    }

    public String getLibDir() {
        return this.mLibDir;
    }

    public String getResDir() {
        return this.mResDir;
    }

    public String[] getSplitAppDirs() {
        return this.mSplitAppDirs;
    }

    public String[] getSplitResDirs() {
        return this.mSplitResDirs;
    }

    public String[] getOverlayDirs() {
        return this.mLegacyOverlayDirs;
    }

    public String[] getOverlayPaths() {
        return this.mOverlayPaths;
    }

    public String getDataDir() {
        return this.mDataDir;
    }

    public File getDataDirFile() {
        return this.mDataDirFile;
    }

    public File getDeviceProtectedDataDirFile() {
        return this.mDeviceProtectedDataDirFile;
    }

    public File getCredentialProtectedDataDirFile() {
        return this.mCredentialProtectedDataDirFile;
    }

    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    public Resources getResources() {
        if (this.mResources == null) {
            try {
                String[] splitPaths = getSplitPaths(null);
                if (Process.myUid() == this.mApplicationInfo.uid) {
                    ResourcesManager.getInstance().initializeApplicationPaths(this.mResDir, splitPaths);
                }
                Integer overrideDisplayId = LoadedApkExtPlugin.sGetOverrideDisplayId.call(this.mApplicationInfo);
                Log.d(TAG, "mApplicationInfo overrideDisplayId:" + overrideDisplayId);
                Resources resources = ResourcesManager.getInstance().getResources(null, this.mResDir, splitPaths, this.mLegacyOverlayDirs, this.mOverlayPaths, this.mApplicationInfo.sharedLibraryFiles, overrideDisplayId, null, getCompatibilityInfo(), getClassLoader(), null);
                this.mResources = resources;
                if (!(resources == null || resources.getImpl() == null)) {
                    LoadedApkExtPlugin.modifyResourcesInGetResources.call(this.mResources.getImpl().mResourcesImplExt, this.mPackageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new AssertionError("null split not found");
            }
        }
        return this.mResources;
    }

    public Application makeApplication(boolean forceDefaultAppClass, Instrumentation instrumentation) {
        Application application = this.mApplication;
        if (application != null) {
            return application;
        }
        Trace.traceBegin(64L, "makeApplication");
        Application app = null;
        String appClass = this.mApplicationInfo.className;
        if (forceDefaultAppClass || appClass == null) {
            appClass = "android.app.Application";
        }
        ContextImpl appContext = null;
        try {
            ClassLoader cl = getClassLoader();
            if (!this.mPackageName.equals("android")) {
                Trace.traceBegin(64L, "initializeJavaContextClassLoader");
                initializeJavaContextClassLoader();
                Trace.traceEnd(64L);
            }
            SparseArray<String> packageIdentifiers = getAssets().getAssignedPackageIdentifiers(false, false);
            int n = packageIdentifiers.size();
            for (int i = 0; i < n; i++) {
                int id = packageIdentifiers.keyAt(i);
                if (!(id == 1 || id == 127)) {
                    rewriteRValues(cl, packageIdentifiers.valueAt(i), id);
                }
            }
            appContext = ContextImpl.createAppContext(this.mActivityThread, this);
            NetworkSecurityConfigProvider.handleNewApplication(appContext);
            app = this.mActivityThread.mInstrumentation.newApplication(cl, appClass, appContext);
            appContext.setOuterContext(app);
        } catch (Exception e) {
            if (!this.mActivityThread.mInstrumentation.onException(app, e)) {
                Trace.traceEnd(64L);
                throw new RuntimeException("Unable to instantiate application " + appClass + " package " + this.mPackageName + ": " + e.toString(), e);
            }
        }
        this.mActivityThread.mAllApplications.add(app);
        this.mApplication = app;
        if (!this.mApplicationInfo.isSystemApp() && !this.mApplicationInfo.isInstantApp() && !this.mPackageName.equals("android")) {
            String procName = Application.getProcessName();
            if (procName.equals(this.mPackageName) && appContext != null) {
                ResOptExtFactory.getInstance().makeResOptExt().preloadRes(appContext, getResources());
            }
        }
        if (instrumentation != null) {
            try {
                instrumentation.callApplicationOnCreate(app);
            } catch (Exception e2) {
                if (!instrumentation.onException(app, e2)) {
                    Trace.traceEnd(64L);
                    throw new RuntimeException("Unable to create application " + app.getClass().getName() + ": " + e2.toString(), e2);
                }
            }
        }
        Trace.traceEnd(64L);
        return app;
    }

    private void rewriteRValues(ClassLoader cl, String packageName, int id) {
        Throwable cause;
        try {
            Class<?> rClazz = cl.loadClass(packageName + ".R");
            try {
                Method callback = rClazz.getMethod("onResourcesLoaded", Integer.TYPE);
                try {
                    callback.invoke(null, Integer.valueOf(id));
                } catch (IllegalAccessException e) {
                    cause = e;
                    throw new RuntimeException("Failed to rewrite resource references for " + packageName, cause);
                } catch (InvocationTargetException e2) {
                    cause = e2.getCause();
                    throw new RuntimeException("Failed to rewrite resource references for " + packageName, cause);
                }
            } catch (NoSuchMethodException e3) {
            }
        } catch (ClassNotFoundException e4) {
            Log.i(TAG, "No resource references to update in package " + packageName);
        }
    }

    public void removeContextRegistrations(Context context, String who, String what) {
        boolean reportRegistrationLeaks = StrictMode.vmRegistrationLeaksEnabled();
        synchronized (this.mReceivers) {
            ArrayMap<BroadcastReceiver, ReceiverDispatcher> rmap = this.mReceivers.remove(context);
            if (rmap != null) {
                for (int i = 0; i < rmap.size(); i++) {
                    ReceiverDispatcher rd = rmap.valueAt(i);
                    IntentReceiverLeaked leak = new IntentReceiverLeaked(what + " " + who + " has leaked IntentReceiver " + rd.getIntentReceiver() + " that was originally registered here. Are you missing a call to unregisterReceiver()?");
                    leak.setStackTrace(rd.getLocation().getStackTrace());
                    Slog.e(ActivityThread.TAG, leak.getMessage(), leak);
                    if (reportRegistrationLeaks) {
                        StrictMode.onIntentReceiverLeaked(leak);
                    }
                    try {
                        ActivityManager.getService().unregisterReceiver(rd.getIIntentReceiver());
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
            this.mUnregisteredReceivers.remove(context);
        }
        synchronized (this.mServices) {
            ArrayMap<ServiceConnection, ServiceDispatcher> smap = this.mServices.remove(context);
            if (smap != null) {
                for (int i2 = 0; i2 < smap.size(); i2++) {
                    ServiceDispatcher sd = smap.valueAt(i2);
                    ServiceConnectionLeaked leak2 = new ServiceConnectionLeaked(what + " " + who + " has leaked ServiceConnection " + sd.getServiceConnection() + " that was originally bound here");
                    leak2.setStackTrace(sd.getLocation().getStackTrace());
                    Slog.e(ActivityThread.TAG, leak2.getMessage(), leak2);
                    if (reportRegistrationLeaks) {
                        StrictMode.onServiceConnectionLeaked(leak2);
                    }
                    try {
                        ActivityManager.getService().unbindService(sd.getIServiceConnection());
                        sd.doForget();
                    } catch (RemoteException e2) {
                        throw e2.rethrowFromSystemServer();
                    }
                }
            }
            this.mUnboundServices.remove(context);
        }
    }

    public IIntentReceiver getReceiverDispatcher(BroadcastReceiver r, Context context, Handler handler, Instrumentation instrumentation, boolean registered) {
        IIntentReceiver iIntentReceiver;
        synchronized (this.mReceivers) {
            ReceiverDispatcher rd = null;
            ArrayMap<BroadcastReceiver, ReceiverDispatcher> map = null;
            if (registered) {
                try {
                    map = this.mReceivers.get(context);
                    if (map != null) {
                        rd = map.get(r);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (rd == null) {
                rd = new ReceiverDispatcher(r, context, handler, instrumentation, registered);
                if (registered) {
                    if (map == null) {
                        map = new ArrayMap<>();
                        this.mReceivers.put(context, map);
                    }
                    map.put(r, rd);
                }
            } else {
                rd.validate(context, handler);
            }
            rd.mForgotten = false;
            iIntentReceiver = rd.getIIntentReceiver();
        }
        return iIntentReceiver;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ad, code lost:
        throw new java.lang.IllegalStateException("Unbinding Receiver " + r10 + " from Context that is no longer in use: " + r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.content.IIntentReceiver forgetReceiverDispatcher(android.content.Context r9, android.content.BroadcastReceiver r10) {
        /*
            r8 = this;
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.BroadcastReceiver, android.app.LoadedApk$ReceiverDispatcher>> r0 = r8.mReceivers
            monitor-enter(r0)
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.BroadcastReceiver, android.app.LoadedApk$ReceiverDispatcher>> r1 = r8.mReceivers     // Catch: all -> 0x00c5
            java.lang.Object r1 = r1.get(r9)     // Catch: all -> 0x00c5
            android.util.ArrayMap r1 = (android.util.ArrayMap) r1     // Catch: all -> 0x00c5
            r2 = 0
            if (r1 == 0) goto L_0x0059
            java.lang.Object r3 = r1.get(r10)     // Catch: all -> 0x00c5
            android.app.LoadedApk$ReceiverDispatcher r3 = (android.app.LoadedApk.ReceiverDispatcher) r3     // Catch: all -> 0x00c5
            r2 = r3
            if (r2 == 0) goto L_0x0059
            r1.remove(r10)     // Catch: all -> 0x00c5
            int r3 = r1.size()     // Catch: all -> 0x00c5
            if (r3 != 0) goto L_0x0025
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.BroadcastReceiver, android.app.LoadedApk$ReceiverDispatcher>> r3 = r8.mReceivers     // Catch: all -> 0x00c5
            r3.remove(r9)     // Catch: all -> 0x00c5
        L_0x0025:
            boolean r3 = r10.getDebugUnregister()     // Catch: all -> 0x00c5
            if (r3 == 0) goto L_0x0050
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.BroadcastReceiver, android.app.LoadedApk$ReceiverDispatcher>> r3 = r8.mUnregisteredReceivers     // Catch: all -> 0x00c5
            java.lang.Object r3 = r3.get(r9)     // Catch: all -> 0x00c5
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch: all -> 0x00c5
            if (r3 != 0) goto L_0x0040
            android.util.ArrayMap r4 = new android.util.ArrayMap     // Catch: all -> 0x00c5
            r4.<init>()     // Catch: all -> 0x00c5
            r3 = r4
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.BroadcastReceiver, android.app.LoadedApk$ReceiverDispatcher>> r4 = r8.mUnregisteredReceivers     // Catch: all -> 0x00c5
            r4.put(r9, r3)     // Catch: all -> 0x00c5
        L_0x0040:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: all -> 0x00c5
            java.lang.String r5 = "Originally unregistered here:"
            r4.<init>(r5)     // Catch: all -> 0x00c5
            r4.fillInStackTrace()     // Catch: all -> 0x00c5
            r2.setUnregisterLocation(r4)     // Catch: all -> 0x00c5
            r3.put(r10, r2)     // Catch: all -> 0x00c5
        L_0x0050:
            r3 = 1
            r2.mForgotten = r3     // Catch: all -> 0x00c5
            android.content.IIntentReceiver r3 = r2.getIIntentReceiver()     // Catch: all -> 0x00c5
            monitor-exit(r0)     // Catch: all -> 0x00c5
            return r3
        L_0x0059:
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.BroadcastReceiver, android.app.LoadedApk$ReceiverDispatcher>> r3 = r8.mUnregisteredReceivers     // Catch: all -> 0x00c5
            java.lang.Object r3 = r3.get(r9)     // Catch: all -> 0x00c5
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch: all -> 0x00c5
            if (r3 == 0) goto L_0x008d
            java.lang.Object r4 = r3.get(r10)     // Catch: all -> 0x00c5
            android.app.LoadedApk$ReceiverDispatcher r4 = (android.app.LoadedApk.ReceiverDispatcher) r4     // Catch: all -> 0x00c5
            r2 = r4
            if (r2 != 0) goto L_0x006d
            goto L_0x008d
        L_0x006d:
            java.lang.RuntimeException r4 = r2.getUnregisterLocation()     // Catch: all -> 0x00c5
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: all -> 0x00c5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00c5
            r6.<init>()     // Catch: all -> 0x00c5
            java.lang.String r7 = "Unregistering Receiver "
            r6.append(r7)     // Catch: all -> 0x00c5
            r6.append(r10)     // Catch: all -> 0x00c5
            java.lang.String r7 = " that was already unregistered"
            r6.append(r7)     // Catch: all -> 0x00c5
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00c5
            r5.<init>(r6, r4)     // Catch: all -> 0x00c5
            throw r5     // Catch: all -> 0x00c5
        L_0x008d:
            if (r9 != 0) goto L_0x00ae
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: all -> 0x00c5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x00c5
            r5.<init>()     // Catch: all -> 0x00c5
            java.lang.String r6 = "Unbinding Receiver "
            r5.append(r6)     // Catch: all -> 0x00c5
            r5.append(r10)     // Catch: all -> 0x00c5
            java.lang.String r6 = " from Context that is no longer in use: "
            r5.append(r6)     // Catch: all -> 0x00c5
            r5.append(r9)     // Catch: all -> 0x00c5
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x00c5
            r4.<init>(r5)     // Catch: all -> 0x00c5
            throw r4     // Catch: all -> 0x00c5
        L_0x00ae:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: all -> 0x00c5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x00c5
            r5.<init>()     // Catch: all -> 0x00c5
            java.lang.String r6 = "Receiver not registered: "
            r5.append(r6)     // Catch: all -> 0x00c5
            r5.append(r10)     // Catch: all -> 0x00c5
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x00c5
            r4.<init>(r5)     // Catch: all -> 0x00c5
            throw r4     // Catch: all -> 0x00c5
        L_0x00c5:
            r1 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00c5
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.LoadedApk.forgetReceiverDispatcher(android.content.Context, android.content.BroadcastReceiver):android.content.IIntentReceiver");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ReceiverDispatcher {
        final Handler mActivityThread;
        final Context mContext;
        boolean mForgotten;
        final IIntentReceiver.Stub mIIntentReceiver;
        final Instrumentation mInstrumentation;
        final IntentReceiverLeaked mLocation;
        final BroadcastReceiver mReceiver;
        final boolean mRegistered;
        RuntimeException mUnregisterLocation;

        /* loaded from: classes.dex */
        static final class InnerReceiver extends IIntentReceiver.Stub {
            final WeakReference<ReceiverDispatcher> mDispatcher;
            final ReceiverDispatcher mStrongRef;

            InnerReceiver(ReceiverDispatcher rd, boolean strong) {
                this.mDispatcher = new WeakReference<>(rd);
                this.mStrongRef = strong ? rd : null;
            }

            @Override // android.content.IIntentReceiver
            public void performReceive(Intent intent, int resultCode, String data, Bundle extras, boolean ordered, boolean sticky, int sendingUser) {
                ReceiverDispatcher rd;
                if (intent == null) {
                    Log.wtf(LoadedApk.TAG, "Null intent received");
                    rd = null;
                } else {
                    rd = this.mDispatcher.get();
                }
                if (ActivityThread.DEBUG_BROADCAST) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Receiving broadcast ");
                    sb.append(intent.getAction());
                    sb.append(" to ");
                    sb.append(rd != null ? rd.mReceiver : null);
                    Slog.i(ActivityThread.TAG, sb.toString());
                }
                if (rd != null) {
                    rd.performReceive(intent, resultCode, data, extras, ordered, sticky, sendingUser);
                    return;
                }
                if (ActivityThread.DEBUG_BROADCAST) {
                    Slog.i(ActivityThread.TAG, "Finishing broadcast to unregistered receiver");
                }
                IActivityManager mgr = ActivityManager.getService();
                if (extras != null) {
                    try {
                        extras.setAllowFds(false);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
                mgr.finishReceiver(this, resultCode, data, extras, false, intent.getFlags());
            }

            public String toString() {
                return LoadedApkExtPlugin.innerReceiverToString.call(this.mDispatcher.get());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public final class Args extends BroadcastReceiver.PendingResult {
            private Intent mCurIntent;
            private boolean mDispatched;
            private final boolean mOrdered;
            private boolean mRunCalled;

            public Args(Intent intent, int resultCode, String resultData, Bundle resultExtras, boolean ordered, boolean sticky, int sendingUser) {
                super(resultCode, resultData, resultExtras, ReceiverDispatcher.this.mRegistered ? 1 : 2, ordered, sticky, ReceiverDispatcher.this.mIIntentReceiver.asBinder(), sendingUser, intent.getFlags());
                this.mCurIntent = intent;
                this.mOrdered = ordered;
            }

            public final Runnable getRunnable() {
                return new Runnable() { // from class: android.app.LoadedApk$ReceiverDispatcher$Args$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LoadedApk.ReceiverDispatcher.Args.this.lambda$getRunnable$0$LoadedApk$ReceiverDispatcher$Args();
                    }
                };
            }

            public /* synthetic */ void lambda$getRunnable$0$LoadedApk$ReceiverDispatcher$Args() {
                BroadcastReceiver receiver = ReceiverDispatcher.this.mReceiver;
                boolean ordered = this.mOrdered;
                if (ActivityThread.DEBUG_BROADCAST) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Dispatching broadcast ");
                    Intent intent = this.mCurIntent;
                    sb.append(intent != null ? intent.getAction() : "null");
                    sb.append(" to ");
                    sb.append(ReceiverDispatcher.this.mReceiver);
                    Slog.i(ActivityThread.TAG, sb.toString());
                    Slog.i(ActivityThread.TAG, "  mRegistered=" + ReceiverDispatcher.this.mRegistered + " mOrderedHint=" + ordered);
                }
                IActivityManager mgr = ActivityManager.getService();
                Intent intent2 = this.mCurIntent;
                if (intent2 == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Null intent being dispatched, mDispatched=");
                    sb2.append(this.mDispatched);
                    sb2.append(this.mRunCalled ? ", run() has already been called" : "");
                    Log.wtf(LoadedApk.TAG, sb2.toString());
                }
                this.mCurIntent = null;
                this.mDispatched = true;
                this.mRunCalled = true;
                if (receiver != null && intent2 != null && !ReceiverDispatcher.this.mForgotten) {
                    boolean hasOplusQueueFlag = false;
                    Trace.traceBegin(64L, "broadcastReceiveReg");
                    try {
                        hasOplusQueueFlag = LoadedApkExtPlugin.beforeOnReceiveInArgsRunnable.call(this, Boolean.valueOf(ordered), intent2).booleanValue();
                        ClassLoader cl = ReceiverDispatcher.this.mReceiver.getClass().getClassLoader();
                        intent2.setExtrasClassLoader(cl);
                        intent2.prepareToEnterProcess(ActivityThread.isProtectedBroadcast(intent2), ReceiverDispatcher.this.mContext.getAttributionSource());
                        setExtrasClassLoader(cl);
                        receiver.setPendingResult(this);
                        Trace.traceBegin(64L, "onReceive " + ReceiverDispatcher.this.mReceiver.getClass());
                        receiver.onReceive(ReceiverDispatcher.this.mContext, intent2);
                        Trace.traceEnd(64L);
                        LoadedApkExtPlugin.afterOnReceiveInArgsRunnable.call(this, Boolean.valueOf(ordered), intent2, Boolean.valueOf(hasOplusQueueFlag));
                    } catch (Exception e) {
                        LoadedApkExtPlugin.inExceptionOnReceiveInArgsRunnable.call(this, intent2, Boolean.valueOf(hasOplusQueueFlag));
                        if (ReceiverDispatcher.this.mRegistered && ordered) {
                            if (ActivityThread.DEBUG_BROADCAST) {
                                Slog.i(ActivityThread.TAG, "Finishing failed broadcast to " + ReceiverDispatcher.this.mReceiver);
                            }
                            sendFinished(mgr);
                        }
                        if (ReceiverDispatcher.this.mInstrumentation == null || !ReceiverDispatcher.this.mInstrumentation.onException(ReceiverDispatcher.this.mReceiver, e)) {
                            Trace.traceEnd(64L);
                            throw new RuntimeException("Error receiving broadcast " + intent2 + " in " + ReceiverDispatcher.this.mReceiver, e);
                        }
                    }
                    if (receiver.getPendingResult() != null) {
                        finish();
                    }
                    Trace.traceEnd(64L);
                } else if (ReceiverDispatcher.this.mRegistered && ordered) {
                    if (ActivityThread.DEBUG_BROADCAST) {
                        Slog.i(ActivityThread.TAG, "Finishing null broadcast to " + ReceiverDispatcher.this.mReceiver);
                    }
                    sendFinished(mgr);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ReceiverDispatcher(BroadcastReceiver receiver, Context context, Handler activityThread, Instrumentation instrumentation, boolean registered) {
            if (activityThread != null) {
                this.mIIntentReceiver = new InnerReceiver(this, !registered);
                this.mReceiver = receiver;
                this.mContext = context;
                this.mActivityThread = activityThread;
                this.mInstrumentation = instrumentation;
                this.mRegistered = registered;
                IntentReceiverLeaked intentReceiverLeaked = new IntentReceiverLeaked(null);
                this.mLocation = intentReceiverLeaked;
                intentReceiverLeaked.fillInStackTrace();
                return;
            }
            throw new NullPointerException("Handler must not be null");
        }

        void validate(Context context, Handler activityThread) {
            if (this.mContext != context) {
                throw new IllegalStateException("Receiver " + this.mReceiver + " registered with differing Context (was " + this.mContext + " now " + context + ")");
            } else if (this.mActivityThread != activityThread) {
                throw new IllegalStateException("Receiver " + this.mReceiver + " registered with differing handler (was " + this.mActivityThread + " now " + activityThread + ")");
            }
        }

        IntentReceiverLeaked getLocation() {
            return this.mLocation;
        }

        BroadcastReceiver getIntentReceiver() {
            return this.mReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public IIntentReceiver getIIntentReceiver() {
            return this.mIIntentReceiver;
        }

        void setUnregisterLocation(RuntimeException ex) {
            this.mUnregisterLocation = ex;
        }

        RuntimeException getUnregisterLocation() {
            return this.mUnregisterLocation;
        }

        public void performReceive(Intent intent, int resultCode, String data, Bundle extras, boolean ordered, boolean sticky, int sendingUser) {
            Args args = new Args(intent, resultCode, data, extras, ordered, sticky, sendingUser);
            if (intent == null) {
                Log.wtf(LoadedApk.TAG, "Null intent received");
            } else {
                if (ActivityThread.DEBUG_BROADCAST) {
                    Slog.i(ActivityThread.TAG, "Enqueueing broadcast " + intent.getAction() + " to " + this.mReceiver);
                }
                LoadedApkExtPlugin.onPerformReceiveWithIntentNonNull.call(args, Boolean.valueOf(ordered), intent);
            }
            if ((intent == null || !this.mActivityThread.post(args.getRunnable())) && this.mRegistered && ordered) {
                IActivityManager mgr = ActivityManager.getService();
                if (ActivityThread.DEBUG_BROADCAST) {
                    Slog.i(ActivityThread.TAG, "Finishing sync broadcast to " + this.mReceiver);
                }
                args.sendFinished(mgr);
            }
        }
    }

    public final IServiceConnection getServiceDispatcher(ServiceConnection c, Context context, Handler handler, int flags) {
        return getServiceDispatcherCommon(c, context, handler, null, flags);
    }

    public final IServiceConnection getServiceDispatcher(ServiceConnection c, Context context, Executor executor, int flags) {
        return getServiceDispatcherCommon(c, context, null, executor, flags);
    }

    private IServiceConnection getServiceDispatcherCommon(ServiceConnection c, Context context, Handler handler, Executor executor, int flags) {
        IServiceConnection iServiceConnection;
        synchronized (this.mServices) {
            ServiceDispatcher sd = null;
            ArrayMap<ServiceConnection, ServiceDispatcher> map = this.mServices.get(context);
            if (map != null) {
                sd = map.get(c);
            }
            if (sd == null) {
                if (executor != null) {
                    sd = new ServiceDispatcher(c, context, executor, flags);
                } else {
                    sd = new ServiceDispatcher(c, context, handler, flags);
                }
                if (map == null) {
                    map = new ArrayMap<>();
                    this.mServices.put(context, map);
                }
                map.put(c, sd);
            } else {
                sd.validate(context, handler, executor);
            }
            iServiceConnection = sd.getIServiceConnection();
        }
        return iServiceConnection;
    }

    public IServiceConnection lookupServiceDispatcher(ServiceConnection c, Context context) {
        IServiceConnection iServiceConnection;
        synchronized (this.mServices) {
            ServiceDispatcher sd = null;
            ArrayMap<ServiceConnection, ServiceDispatcher> map = this.mServices.get(context);
            if (map != null) {
                sd = map.get(c);
            }
            iServiceConnection = sd != null ? sd.getIServiceConnection() : null;
        }
        return iServiceConnection;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00af, code lost:
        throw new java.lang.IllegalStateException("Unbinding Service " + r10 + " from Context that is no longer in use: " + r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.app.IServiceConnection forgetServiceDispatcher(android.content.Context r9, android.content.ServiceConnection r10) {
        /*
            r8 = this;
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.ServiceConnection, android.app.LoadedApk$ServiceDispatcher>> r0 = r8.mServices
            monitor-enter(r0)
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.ServiceConnection, android.app.LoadedApk$ServiceDispatcher>> r1 = r8.mServices     // Catch: all -> 0x00c7
            java.lang.Object r1 = r1.get(r9)     // Catch: all -> 0x00c7
            android.util.ArrayMap r1 = (android.util.ArrayMap) r1     // Catch: all -> 0x00c7
            r2 = 0
            if (r1 == 0) goto L_0x005b
            java.lang.Object r3 = r1.get(r10)     // Catch: all -> 0x00c7
            android.app.LoadedApk$ServiceDispatcher r3 = (android.app.LoadedApk.ServiceDispatcher) r3     // Catch: all -> 0x00c7
            r2 = r3
            if (r2 == 0) goto L_0x005b
            r1.remove(r10)     // Catch: all -> 0x00c7
            r2.doForget()     // Catch: all -> 0x00c7
            int r3 = r1.size()     // Catch: all -> 0x00c7
            if (r3 != 0) goto L_0x0028
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.ServiceConnection, android.app.LoadedApk$ServiceDispatcher>> r3 = r8.mServices     // Catch: all -> 0x00c7
            r3.remove(r9)     // Catch: all -> 0x00c7
        L_0x0028:
            int r3 = r2.getFlags()     // Catch: all -> 0x00c7
            r3 = r3 & 2
            if (r3 == 0) goto L_0x0055
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.ServiceConnection, android.app.LoadedApk$ServiceDispatcher>> r3 = r8.mUnboundServices     // Catch: all -> 0x00c7
            java.lang.Object r3 = r3.get(r9)     // Catch: all -> 0x00c7
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch: all -> 0x00c7
            if (r3 != 0) goto L_0x0045
            android.util.ArrayMap r4 = new android.util.ArrayMap     // Catch: all -> 0x00c7
            r4.<init>()     // Catch: all -> 0x00c7
            r3 = r4
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.ServiceConnection, android.app.LoadedApk$ServiceDispatcher>> r4 = r8.mUnboundServices     // Catch: all -> 0x00c7
            r4.put(r9, r3)     // Catch: all -> 0x00c7
        L_0x0045:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: all -> 0x00c7
            java.lang.String r5 = "Originally unbound here:"
            r4.<init>(r5)     // Catch: all -> 0x00c7
            r4.fillInStackTrace()     // Catch: all -> 0x00c7
            r2.setUnbindLocation(r4)     // Catch: all -> 0x00c7
            r3.put(r10, r2)     // Catch: all -> 0x00c7
        L_0x0055:
            android.app.IServiceConnection r3 = r2.getIServiceConnection()     // Catch: all -> 0x00c7
            monitor-exit(r0)     // Catch: all -> 0x00c7
            return r3
        L_0x005b:
            android.util.ArrayMap<android.content.Context, android.util.ArrayMap<android.content.ServiceConnection, android.app.LoadedApk$ServiceDispatcher>> r3 = r8.mUnboundServices     // Catch: all -> 0x00c7
            java.lang.Object r3 = r3.get(r9)     // Catch: all -> 0x00c7
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch: all -> 0x00c7
            if (r3 == 0) goto L_0x008f
            java.lang.Object r4 = r3.get(r10)     // Catch: all -> 0x00c7
            android.app.LoadedApk$ServiceDispatcher r4 = (android.app.LoadedApk.ServiceDispatcher) r4     // Catch: all -> 0x00c7
            r2 = r4
            if (r2 != 0) goto L_0x006f
            goto L_0x008f
        L_0x006f:
            java.lang.RuntimeException r4 = r2.getUnbindLocation()     // Catch: all -> 0x00c7
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: all -> 0x00c7
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00c7
            r6.<init>()     // Catch: all -> 0x00c7
            java.lang.String r7 = "Unbinding Service "
            r6.append(r7)     // Catch: all -> 0x00c7
            r6.append(r10)     // Catch: all -> 0x00c7
            java.lang.String r7 = " that was already unbound"
            r6.append(r7)     // Catch: all -> 0x00c7
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00c7
            r5.<init>(r6, r4)     // Catch: all -> 0x00c7
            throw r5     // Catch: all -> 0x00c7
        L_0x008f:
            if (r9 != 0) goto L_0x00b0
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: all -> 0x00c7
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x00c7
            r5.<init>()     // Catch: all -> 0x00c7
            java.lang.String r6 = "Unbinding Service "
            r5.append(r6)     // Catch: all -> 0x00c7
            r5.append(r10)     // Catch: all -> 0x00c7
            java.lang.String r6 = " from Context that is no longer in use: "
            r5.append(r6)     // Catch: all -> 0x00c7
            r5.append(r9)     // Catch: all -> 0x00c7
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x00c7
            r4.<init>(r5)     // Catch: all -> 0x00c7
            throw r4     // Catch: all -> 0x00c7
        L_0x00b0:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: all -> 0x00c7
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x00c7
            r5.<init>()     // Catch: all -> 0x00c7
            java.lang.String r6 = "Service not registered: "
            r5.append(r6)     // Catch: all -> 0x00c7
            r5.append(r10)     // Catch: all -> 0x00c7
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x00c7
            r4.<init>(r5)     // Catch: all -> 0x00c7
            throw r4     // Catch: all -> 0x00c7
        L_0x00c7:
            r1 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00c7
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.LoadedApk.forgetServiceDispatcher(android.content.Context, android.content.ServiceConnection):android.app.IServiceConnection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ServiceDispatcher {
        private final ArrayMap<ComponentName, ConnectionInfo> mActiveConnections;
        private final Executor mActivityExecutor;
        private final Handler mActivityThread;
        private final ServiceConnection mConnection;
        private final Context mContext;
        private final int mFlags;
        private boolean mForgotten;
        private final InnerConnection mIServiceConnection;
        private final ServiceConnectionLeaked mLocation;
        private RuntimeException mUnbindLocation;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class ConnectionInfo {
            IBinder binder;
            IBinder.DeathRecipient deathMonitor;

            private ConnectionInfo() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class InnerConnection extends IServiceConnection.Stub {
            final WeakReference<ServiceDispatcher> mDispatcher;

            InnerConnection(ServiceDispatcher sd) {
                this.mDispatcher = new WeakReference<>(sd);
            }

            @Override // android.app.IServiceConnection
            public void connected(ComponentName name, IBinder service, boolean dead) throws RemoteException {
                ServiceDispatcher sd = this.mDispatcher.get();
                if (sd != null) {
                    sd.connected(name, service, dead);
                }
            }
        }

        ServiceDispatcher(ServiceConnection conn, Context context, Handler activityThread, int flags) {
            this.mActiveConnections = new ArrayMap<>();
            this.mIServiceConnection = new InnerConnection(this);
            this.mConnection = conn;
            this.mContext = context;
            this.mActivityThread = activityThread;
            this.mActivityExecutor = null;
            ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked(null);
            this.mLocation = serviceConnectionLeaked;
            serviceConnectionLeaked.fillInStackTrace();
            this.mFlags = flags;
        }

        ServiceDispatcher(ServiceConnection conn, Context context, Executor activityExecutor, int flags) {
            this.mActiveConnections = new ArrayMap<>();
            this.mIServiceConnection = new InnerConnection(this);
            this.mConnection = conn;
            this.mContext = context;
            this.mActivityThread = null;
            this.mActivityExecutor = activityExecutor;
            ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked(null);
            this.mLocation = serviceConnectionLeaked;
            serviceConnectionLeaked.fillInStackTrace();
            this.mFlags = flags;
        }

        void validate(Context context, Handler activityThread, Executor activityExecutor) {
            if (this.mContext != context) {
                throw new RuntimeException("ServiceConnection " + this.mConnection + " registered with differing Context (was " + this.mContext + " now " + context + ")");
            } else if (this.mActivityThread != activityThread) {
                throw new RuntimeException("ServiceConnection " + this.mConnection + " registered with differing handler (was " + this.mActivityThread + " now " + activityThread + ")");
            } else if (this.mActivityExecutor != activityExecutor) {
                throw new RuntimeException("ServiceConnection " + this.mConnection + " registered with differing executor (was " + this.mActivityExecutor + " now " + activityExecutor + ")");
            }
        }

        void doForget() {
            synchronized (this) {
                for (int i = 0; i < this.mActiveConnections.size(); i++) {
                    ConnectionInfo ci = this.mActiveConnections.valueAt(i);
                    ci.binder.unlinkToDeath(ci.deathMonitor, 0);
                }
                this.mActiveConnections.clear();
                this.mForgotten = true;
            }
        }

        ServiceConnectionLeaked getLocation() {
            return this.mLocation;
        }

        ServiceConnection getServiceConnection() {
            return this.mConnection;
        }

        IServiceConnection getIServiceConnection() {
            return this.mIServiceConnection;
        }

        int getFlags() {
            return this.mFlags;
        }

        void setUnbindLocation(RuntimeException ex) {
            this.mUnbindLocation = ex;
        }

        RuntimeException getUnbindLocation() {
            return this.mUnbindLocation;
        }

        public void connected(ComponentName name, IBinder service, boolean dead) {
            Executor executor = this.mActivityExecutor;
            if (executor != null) {
                executor.execute(new RunConnection(name, service, 0, dead));
            } else if (this.mActivityThread != null) {
                LoadedApkExtPlugin.activityThreadHandlerOnServiceDispatcherConnected.call(this.mActivityThread, name, new RunConnection(name, service, 0, dead));
            } else {
                doConnected(name, service, dead);
            }
        }

        public void death(ComponentName name, IBinder service) {
            Executor executor = this.mActivityExecutor;
            if (executor != null) {
                executor.execute(new RunConnection(name, service, 1, false));
                return;
            }
            Handler handler = this.mActivityThread;
            if (handler != null) {
                handler.post(new RunConnection(name, service, 1, false));
            } else {
                doDeath(name, service);
            }
        }

        public void doConnected(ComponentName name, IBinder service, boolean dead) {
            synchronized (this) {
                if (!this.mForgotten) {
                    ConnectionInfo old = this.mActiveConnections.get(name);
                    if (old == null || old.binder != service) {
                        if (service != null) {
                            ConnectionInfo info = new ConnectionInfo();
                            info.binder = service;
                            info.deathMonitor = new DeathMonitor(name, service);
                            try {
                                service.linkToDeath(info.deathMonitor, 0);
                                this.mActiveConnections.put(name, info);
                            } catch (RemoteException e) {
                                this.mActiveConnections.remove(name);
                                return;
                            }
                        } else {
                            this.mActiveConnections.remove(name);
                        }
                        if (old != null) {
                            old.binder.unlinkToDeath(old.deathMonitor, 0);
                        }
                        if (old != null) {
                            this.mConnection.onServiceDisconnected(name);
                        }
                        if (dead) {
                            this.mConnection.onBindingDied(name);
                        } else if (service != null) {
                            this.mConnection.onServiceConnected(name, service);
                        } else {
                            this.mConnection.onNullBinding(name);
                        }
                    }
                }
            }
        }

        public void doDeath(ComponentName name, IBinder service) {
            synchronized (this) {
                ConnectionInfo old = this.mActiveConnections.get(name);
                if (old != null && old.binder == service) {
                    this.mActiveConnections.remove(name);
                    old.binder.unlinkToDeath(old.deathMonitor, 0);
                    this.mConnection.onServiceDisconnected(name);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public final class RunConnection implements Runnable {
            final int mCommand;
            final boolean mDead;
            final ComponentName mName;
            final IBinder mService;

            RunConnection(ComponentName name, IBinder service, int command, boolean dead) {
                this.mName = name;
                this.mService = service;
                this.mCommand = command;
                this.mDead = dead;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i = this.mCommand;
                if (i == 0) {
                    ServiceDispatcher.this.doConnected(this.mName, this.mService, this.mDead);
                } else if (i == 1) {
                    ServiceDispatcher.this.doDeath(this.mName, this.mService);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public final class DeathMonitor implements IBinder.DeathRecipient {
            final ComponentName mName;
            final IBinder mService;

            DeathMonitor(ComponentName name, IBinder service) {
                this.mName = name;
                this.mService = service;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                ServiceDispatcher.this.death(this.mName, this.mService);
            }
        }
    }
}
