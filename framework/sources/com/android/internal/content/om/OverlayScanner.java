package com.android.internal.content.om;

import android.content.pm.parsing.ApkLite;
import android.content.pm.parsing.ApkLiteParseUtils;
import android.content.pm.parsing.result.ParseResult;
import android.content.pm.parsing.result.ParseTypeImpl;
import android.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.util.Collection;
/* loaded from: classes4.dex */
public class OverlayScanner {
    private final ArrayMap<String, ParsedOverlayInfo> mParsedOverlayInfos = new ArrayMap<>();

    /* loaded from: classes4.dex */
    public static class ParsedOverlayInfo {
        public final boolean isStatic;
        public final String packageName;
        public final File path;
        public final int priority;
        public final String targetPackageName;
        public final int targetSdkVersion;

        public ParsedOverlayInfo(String packageName, String targetPackageName, int targetSdkVersion, boolean isStatic, int priority, File path) {
            this.packageName = packageName;
            this.targetPackageName = targetPackageName;
            this.targetSdkVersion = targetSdkVersion;
            this.isStatic = isStatic;
            this.priority = priority;
            this.path = path;
        }

        public String toString() {
            return getClass().getSimpleName() + String.format("{packageName=%s, targetPackageName=%s, targetSdkVersion=%s, isStatic=%s, priority=%s, path=%s}", this.packageName, this.targetPackageName, Integer.valueOf(this.targetSdkVersion), Boolean.valueOf(this.isStatic), Integer.valueOf(this.priority), this.path);
        }
    }

    public final ParsedOverlayInfo getParsedInfo(String packageName) {
        return this.mParsedOverlayInfos.get(packageName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Collection<ParsedOverlayInfo> getAllParsedInfos() {
        return this.mParsedOverlayInfos.values();
    }

    public void scanDir(File partitionOverlayDir) {
        ParsedOverlayInfo info;
        if (partitionOverlayDir.exists() && partitionOverlayDir.isDirectory()) {
            if (!partitionOverlayDir.canRead()) {
                Log.w("OverlayConfig", "Directory " + partitionOverlayDir + " cannot be read");
                return;
            }
            File[] files = partitionOverlayDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        scanDir(f);
                    }
                    if (f.isFile() && f.getPath().endsWith(".apk") && (info = parseOverlayManifest(f)) != null) {
                        this.mParsedOverlayInfos.put(info.packageName, info);
                    }
                }
            }
        }
    }

    public ParsedOverlayInfo parseOverlayManifest(File overlayApk) {
        ParseTypeImpl input = ParseTypeImpl.forParsingWithoutPlatformCompat();
        ParseResult<ApkLite> ret = ApkLiteParseUtils.parseApkLite(input.reset(), overlayApk, 0);
        if (ret.isError()) {
            Log.w("OverlayConfig", "Got exception loading overlay.", ret.getException());
            return null;
        }
        ApkLite apkLite = ret.getResult();
        if (apkLite.getTargetPackageName() == null) {
            return null;
        }
        return new ParsedOverlayInfo(apkLite.getPackageName(), apkLite.getTargetPackageName(), apkLite.getTargetSdkVersion(), apkLite.isOverlayIsStatic(), apkLite.getOverlayPriority(), new File(apkLite.getPath()));
    }
}
