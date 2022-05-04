package android.app;

import android.content.pm.SharedLibraryInfo;
import android.os.Build;
import android.os.GraphicsEnvironment;
import android.os.OplusJankMonitorExtPlugin;
import android.os.SystemClock;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.os.ClassLoaderFactory;
import dalvik.system.PathClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class ApplicationLoaders {
    private static final String TAG = "ApplicationLoaders";
    private static final ApplicationLoaders gApplicationLoaders = new ApplicationLoaders();
    private final ArrayMap<String, ClassLoader> mLoaders = new ArrayMap<>();
    private Map<String, CachedClassLoader> mSystemLibsCacheMap = null;

    public static ApplicationLoaders getDefault() {
        return gApplicationLoaders;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassLoader getClassLoader(String zip, int targetSdkVersion, boolean isBundled, String librarySearchPath, String libraryPermittedPath, ClassLoader parent, String classLoaderName) {
        return getClassLoaderWithSharedLibraries(zip, targetSdkVersion, isBundled, librarySearchPath, libraryPermittedPath, parent, classLoaderName, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassLoader getClassLoaderWithSharedLibraries(String zip, int targetSdkVersion, boolean isBundled, String librarySearchPath, String libraryPermittedPath, ClassLoader parent, String classLoaderName, List<ClassLoader> sharedLibraries, List<String> nativeSharedLibraries) {
        return getClassLoader(zip, targetSdkVersion, isBundled, librarySearchPath, libraryPermittedPath, parent, zip, classLoaderName, sharedLibraries, nativeSharedLibraries);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassLoader getSharedLibraryClassLoaderWithSharedLibraries(String zip, int targetSdkVersion, boolean isBundled, String librarySearchPath, String libraryPermittedPath, ClassLoader parent, String classLoaderName, List<ClassLoader> sharedLibraries) {
        ClassLoader loader = getCachedNonBootclasspathSystemLib(zip, parent, classLoaderName, sharedLibraries);
        if (loader != null) {
            return loader;
        }
        List<String> nativeSharedLibraries = new ArrayList<>();
        nativeSharedLibraries.add("ALL");
        return getClassLoaderWithSharedLibraries(zip, targetSdkVersion, isBundled, librarySearchPath, libraryPermittedPath, parent, classLoaderName, sharedLibraries, nativeSharedLibraries);
    }

    private ClassLoader getClassLoader(String zip, int targetSdkVersion, boolean isBundled, String librarySearchPath, String libraryPermittedPath, ClassLoader parent, String cacheKey, String classLoaderName, List<ClassLoader> sharedLibraries, List<String> nativeSharedLibraries) {
        ClassLoader parent2;
        ClassLoader baseParent = ClassLoader.getSystemClassLoader().getParent();
        synchronized (this.mLoaders) {
            if (parent == null) {
                parent2 = baseParent;
            } else {
                parent2 = parent;
            }
            try {
                try {
                    if (parent2 == baseParent) {
                        try {
                            ClassLoader loader = this.mLoaders.get(cacheKey);
                            if (loader != null) {
                                return loader;
                            }
                            Trace.traceBegin(64L, zip);
                            long startTime = SystemClock.uptimeMillis();
                            ClassLoader classloader = ClassLoaderFactory.createClassLoader(zip, librarySearchPath, libraryPermittedPath, parent2, targetSdkVersion, isBundled, classLoaderName, sharedLibraries, nativeSharedLibraries);
                            OplusJankMonitorExtPlugin.setLaunchStageTime.call(zip, "createClassLoader", Long.valueOf(startTime));
                            Trace.traceEnd(64L);
                            Trace.traceBegin(64L, "setLayerPaths");
                            GraphicsEnvironment.getInstance().setLayerPaths(classloader, librarySearchPath, libraryPermittedPath);
                            Trace.traceEnd(64L);
                            if (cacheKey != null) {
                                this.mLoaders.put(cacheKey, classloader);
                            }
                            return classloader;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } else {
                        Trace.traceBegin(64L, zip);
                        long startTime2 = SystemClock.uptimeMillis();
                        ClassLoader loader2 = ClassLoaderFactory.createClassLoader(zip, null, parent2, classLoaderName, sharedLibraries);
                        OplusJankMonitorExtPlugin.setLaunchStageTime.call(zip, "createClassLoader", Long.valueOf(startTime2));
                        Trace.traceEnd(64L);
                        return loader2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public void createAndCacheNonBootclasspathSystemClassLoaders(SharedLibraryInfo[] libs) {
        if (this.mSystemLibsCacheMap == null) {
            this.mSystemLibsCacheMap = new HashMap();
            for (SharedLibraryInfo lib : libs) {
                createAndCacheNonBootclasspathSystemClassLoader(lib);
            }
            return;
        }
        throw new IllegalStateException("Already cached.");
    }

    private void createAndCacheNonBootclasspathSystemClassLoader(SharedLibraryInfo lib) {
        ArrayList<ClassLoader> sharedLibraries;
        String path = lib.getPath();
        List<SharedLibraryInfo> dependencies = lib.getDependencies();
        if (dependencies != null) {
            ArrayList<ClassLoader> sharedLibraries2 = new ArrayList<>(dependencies.size());
            for (SharedLibraryInfo dependency : dependencies) {
                String dependencyPath = dependency.getPath();
                CachedClassLoader cached = this.mSystemLibsCacheMap.get(dependencyPath);
                if (cached != null) {
                    sharedLibraries2.add(cached.loader);
                } else {
                    throw new IllegalStateException("Failed to find dependency " + dependencyPath + " of cachedlibrary " + path);
                }
            }
            sharedLibraries = sharedLibraries2;
        } else {
            sharedLibraries = null;
        }
        ClassLoader classLoader = getClassLoader(path, Build.VERSION.SDK_INT, true, null, null, null, null, null, sharedLibraries, null);
        if (classLoader != null) {
            CachedClassLoader cached2 = new CachedClassLoader();
            cached2.loader = classLoader;
            cached2.sharedLibraries = sharedLibraries;
            Log.d(TAG, "Created zygote-cached class loader: " + path);
            this.mSystemLibsCacheMap.put(path, cached2);
            return;
        }
        throw new IllegalStateException("Failed to cache " + path);
    }

    private static boolean sharedLibrariesEquals(List<ClassLoader> lhs, List<ClassLoader> rhs) {
        if (lhs == null) {
            return rhs == null;
        }
        return lhs.equals(rhs);
    }

    public ClassLoader getCachedNonBootclasspathSystemLib(String zip, ClassLoader parent, String classLoaderName, List<ClassLoader> sharedLibraries) {
        CachedClassLoader cached;
        Map<String, CachedClassLoader> map = this.mSystemLibsCacheMap;
        if (map == null || parent != null || classLoaderName != null || (cached = map.get(zip)) == null) {
            return null;
        }
        if (!sharedLibrariesEquals(sharedLibraries, cached.sharedLibraries)) {
            Log.w(TAG, "Unexpected environment loading cached library " + zip + " (real|cached): (" + sharedLibraries + "|" + cached.sharedLibraries + ")");
            return null;
        }
        Log.d(TAG, "Returning zygote-cached class loader: " + zip);
        return cached.loader;
    }

    public ClassLoader createAndCacheWebViewClassLoader(String packagePath, String libsPath, String cacheKey) {
        return getClassLoader(packagePath, Build.VERSION.SDK_INT, false, libsPath, null, null, cacheKey, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPath(ClassLoader classLoader, String dexPath) {
        if (classLoader instanceof PathClassLoader) {
            PathClassLoader baseDexClassLoader = (PathClassLoader) classLoader;
            baseDexClassLoader.addDexPath(dexPath);
            return;
        }
        throw new IllegalStateException("class loader is not a PathClassLoader");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addNative(ClassLoader classLoader, Collection<String> libPaths) {
        if (classLoader instanceof PathClassLoader) {
            PathClassLoader baseDexClassLoader = (PathClassLoader) classLoader;
            baseDexClassLoader.addNativePath(libPaths);
            return;
        }
        throw new IllegalStateException("class loader is not a PathClassLoader");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CachedClassLoader {
        ClassLoader loader;
        List<ClassLoader> sharedLibraries;

        private CachedClassLoader() {
        }
    }
}
