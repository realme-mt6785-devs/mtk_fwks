package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.pm.parsing.ApkLiteParseUtils;
import android.content.pm.parsing.PackageLite;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import libcore.io.IoUtils;
/* loaded from: classes.dex */
public class SplitAssetDependencyLoader extends SplitDependencyLoader<PackageParser.PackageParserException> implements SplitAssetLoader {
    private final AssetManager[] mCachedAssetManagers;
    private final ApkAssets[][] mCachedSplitApks;
    private final int mFlags;
    private final String[] mSplitPaths;

    public SplitAssetDependencyLoader(PackageLite pkg, SparseArray<int[]> dependencies, int flags) {
        super(dependencies);
        String[] strArr = new String[pkg.getSplitApkPaths().length + 1];
        this.mSplitPaths = strArr;
        strArr[0] = pkg.getBaseApkPath();
        System.arraycopy(pkg.getSplitApkPaths(), 0, strArr, 1, pkg.getSplitApkPaths().length);
        this.mFlags = flags;
        this.mCachedSplitApks = new ApkAssets[strArr.length];
        this.mCachedAssetManagers = new AssetManager[strArr.length];
    }

    @Override // android.content.pm.split.SplitDependencyLoader
    protected boolean isSplitCached(int splitIdx) {
        return this.mCachedAssetManagers[splitIdx] != null;
    }

    private static ApkAssets loadApkAssets(String path, int flags) throws PackageParser.PackageParserException {
        if ((flags & 1) == 0 || ApkLiteParseUtils.isApkPath(path)) {
            try {
                return ApkAssets.loadFromPath(path);
            } catch (IOException e) {
                throw new PackageParser.PackageParserException(-2, "Failed to load APK at path " + path, e);
            }
        } else {
            throw new PackageParser.PackageParserException(-100, "Invalid package file: " + path);
        }
    }

    private static AssetManager createAssetManagerWithAssets(ApkAssets[] apkAssets) {
        AssetManager assets = new AssetManager();
        assets.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
        assets.setApkAssets(apkAssets, false);
        return assets;
    }

    @Override // android.content.pm.split.SplitDependencyLoader
    protected void constructSplit(int splitIdx, int[] configSplitIndices, int parentSplitIdx) throws PackageParser.PackageParserException {
        ArrayList<ApkAssets> assets = new ArrayList<>();
        if (parentSplitIdx >= 0) {
            Collections.addAll(assets, this.mCachedSplitApks[parentSplitIdx]);
        }
        assets.add(loadApkAssets(this.mSplitPaths[splitIdx], this.mFlags));
        for (int configSplitIdx : configSplitIndices) {
            assets.add(loadApkAssets(this.mSplitPaths[configSplitIdx], this.mFlags));
        }
        this.mCachedSplitApks[splitIdx] = (ApkAssets[]) assets.toArray(new ApkAssets[assets.size()]);
        this.mCachedAssetManagers[splitIdx] = createAssetManagerWithAssets(this.mCachedSplitApks[splitIdx]);
    }

    @Override // android.content.pm.split.SplitAssetLoader
    public AssetManager getBaseAssetManager() throws PackageParser.PackageParserException {
        loadDependenciesForSplit(0);
        return this.mCachedAssetManagers[0];
    }

    @Override // android.content.pm.split.SplitAssetLoader
    public AssetManager getSplitAssetManager(int idx) throws PackageParser.PackageParserException {
        loadDependenciesForSplit(idx + 1);
        return this.mCachedAssetManagers[idx + 1];
    }

    @Override // android.content.pm.split.SplitAssetLoader
    public ApkAssets getBaseApkAssets() {
        return this.mCachedSplitApks[0][0];
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Exception {
        AssetManager[] assetManagerArr;
        for (AssetManager assets : this.mCachedAssetManagers) {
            IoUtils.closeQuietly(assets);
        }
    }
}
