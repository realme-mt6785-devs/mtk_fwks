package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.pm.parsing.ApkLiteParseUtils;
import android.content.pm.parsing.PackageLite;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.os.Build;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import libcore.io.IoUtils;
/* loaded from: classes.dex */
public class DefaultSplitAssetLoader implements SplitAssetLoader {
    private ApkAssets mBaseApkAssets;
    private final String mBaseApkPath;
    private AssetManager mCachedAssetManager;
    private final int mFlags;
    private final String[] mSplitApkPaths;

    public DefaultSplitAssetLoader(PackageLite pkg, int flags) {
        this.mBaseApkPath = pkg.getBaseApkPath();
        this.mSplitApkPaths = pkg.getSplitApkPaths();
        this.mFlags = flags;
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

    @Override // android.content.pm.split.SplitAssetLoader
    public AssetManager getBaseAssetManager() throws PackageParser.PackageParserException {
        AssetManager assetManager = this.mCachedAssetManager;
        if (assetManager != null) {
            return assetManager;
        }
        String[] strArr = this.mSplitApkPaths;
        ApkAssets[] apkAssets = new ApkAssets[(strArr != null ? strArr.length : 0) + 1];
        int splitIdx = 0 + 1;
        ApkAssets loadApkAssets = loadApkAssets(this.mBaseApkPath, this.mFlags);
        this.mBaseApkAssets = loadApkAssets;
        apkAssets[0] = loadApkAssets;
        if (!ArrayUtils.isEmpty(this.mSplitApkPaths)) {
            String[] strArr2 = this.mSplitApkPaths;
            int length = strArr2.length;
            int i = 0;
            while (i < length) {
                String apkPath = strArr2[i];
                apkAssets[splitIdx] = loadApkAssets(apkPath, this.mFlags);
                i++;
                splitIdx++;
            }
        }
        AssetManager assets = new AssetManager();
        assets.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
        assets.setApkAssets(apkAssets, false);
        this.mCachedAssetManager = assets;
        return assets;
    }

    @Override // android.content.pm.split.SplitAssetLoader
    public AssetManager getSplitAssetManager(int splitIdx) throws PackageParser.PackageParserException {
        return getBaseAssetManager();
    }

    @Override // android.content.pm.split.SplitAssetLoader
    public ApkAssets getBaseApkAssets() {
        return this.mBaseApkAssets;
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Exception {
        IoUtils.closeQuietly(this.mCachedAssetManager);
    }
}
