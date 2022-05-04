package android.content.pm.dex;

import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.parsing.ApkLiteParseUtils;
import android.content.pm.parsing.PackageLite;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.JsonReader;
import android.util.Log;
import android.util.jar.StrictJarFile;
import com.android.internal.security.VerityUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
/* loaded from: classes.dex */
public class DexMetadataHelper {
    private static final String DEX_METADATA_FILE_EXTENSION = ".dm";
    private static final String PROPERTY_DM_FSVERITY_REQUIRED = "pm.dexopt.dm.require_fsverity";
    private static final String PROPERTY_DM_JSON_MANIFEST_REQUIRED = "pm.dexopt.dm.require_manifest";
    public static final String TAG = "DexMetadataHelper";
    public static final boolean DEBUG = Log.isLoggable(TAG, 3);

    private DexMetadataHelper() {
    }

    public static boolean isDexMetadataFile(File file) {
        return isDexMetadataPath(file.getName());
    }

    private static boolean isDexMetadataPath(String path) {
        return path.endsWith(".dm");
    }

    public static boolean isFsVerityRequired() {
        return VerityUtils.isFsVeritySupported() && SystemProperties.getBoolean(PROPERTY_DM_FSVERITY_REQUIRED, false);
    }

    public static long getPackageDexMetadataSize(PackageLite pkg) {
        long sizeBytes = 0;
        Collection<String> dexMetadataList = getPackageDexMetadata(pkg).values();
        for (String dexMetadata : dexMetadataList) {
            sizeBytes += new File(dexMetadata).length();
        }
        return sizeBytes;
    }

    public static File findDexMetadataForFile(File targetFile) {
        String dexMetadataPath = buildDexMetadataPathForFile(targetFile);
        File dexMetadataFile = new File(dexMetadataPath);
        if (dexMetadataFile.exists()) {
            return dexMetadataFile;
        }
        return null;
    }

    private static Map<String, String> getPackageDexMetadata(PackageLite pkg) {
        return buildPackageApkToDexMetadataMap(pkg.getAllApkPaths());
    }

    public static Map<String, String> buildPackageApkToDexMetadataMap(List<String> codePaths) {
        ArrayMap<String, String> result = new ArrayMap<>();
        for (int i = codePaths.size() - 1; i >= 0; i--) {
            String codePath = codePaths.get(i);
            String dexMetadataPath = buildDexMetadataPathForFile(new File(codePath));
            if (Files.exists(Paths.get(dexMetadataPath, new String[0]), new LinkOption[0])) {
                result.put(codePath, dexMetadataPath);
            }
        }
        return result;
    }

    public static String buildDexMetadataPathForApk(String codePath) {
        if (ApkLiteParseUtils.isApkPath(codePath)) {
            return codePath.substring(0, codePath.length() - ".apk".length()) + ".dm";
        }
        throw new IllegalStateException("Corrupted package. Code path is not an apk " + codePath);
    }

    private static String buildDexMetadataPathForFile(File targetFile) {
        if (ApkLiteParseUtils.isApkFile(targetFile)) {
            return buildDexMetadataPathForApk(targetFile.getPath());
        }
        return targetFile.getPath() + ".dm";
    }

    public static void validateDexMetadataFile(String dmaPath, String packageName, long versionCode) throws PackageParser.PackageParserException {
        validateDexMetadataFile(dmaPath, packageName, versionCode, SystemProperties.getBoolean(PROPERTY_DM_JSON_MANIFEST_REQUIRED, false));
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void validateDexMetadataFile(java.lang.String r9, java.lang.String r10, long r11, boolean r13) throws android.content.pm.PackageParser.PackageParserException {
        /*
            r0 = 0
            boolean r1 = android.content.pm.dex.DexMetadataHelper.DEBUG
            if (r1 == 0) goto L_0x002a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "validateDexMetadataFile: "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r10)
            r1.append(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "DexMetadataHelper"
            android.util.Log.v(r2, r1)
        L_0x002a:
            android.util.jar.StrictJarFile r4 = new android.util.jar.StrictJarFile     // Catch: all -> 0x0041, IOException -> 0x0045
            r1 = 0
            r4.<init>(r9, r1, r1)     // Catch: all -> 0x0041, IOException -> 0x0045
            r3 = r9
            r5 = r10
            r6 = r11
            r8 = r13
            validateDexMetadataManifest(r3, r4, r5, r6, r8)     // Catch: IOException -> 0x003f, all -> 0x0061
            r4.close()     // Catch: IOException -> 0x003c
        L_0x003b:
            goto L_0x003e
        L_0x003c:
            r0 = move-exception
            goto L_0x003b
        L_0x003e:
            return
        L_0x003f:
            r0 = move-exception
            goto L_0x0048
        L_0x0041:
            r1 = move-exception
            r4 = r0
            r0 = r1
            goto L_0x0062
        L_0x0045:
            r1 = move-exception
            r4 = r0
            r0 = r1
        L_0x0048:
            android.content.pm.PackageParser$PackageParserException r1 = new android.content.pm.PackageParser$PackageParserException     // Catch: all -> 0x0061
            r2 = -117(0xffffffffffffff8b, float:NaN)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0061
            r3.<init>()     // Catch: all -> 0x0061
            java.lang.String r5 = "Error opening "
            r3.append(r5)     // Catch: all -> 0x0061
            r3.append(r9)     // Catch: all -> 0x0061
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x0061
            r1.<init>(r2, r3, r0)     // Catch: all -> 0x0061
            throw r1     // Catch: all -> 0x0061
        L_0x0061:
            r0 = move-exception
        L_0x0062:
            if (r4 == 0) goto L_0x0069
            r4.close()     // Catch: IOException -> 0x0068
            goto L_0x0069
        L_0x0068:
            r1 = move-exception
        L_0x0069:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.dex.DexMetadataHelper.validateDexMetadataFile(java.lang.String, java.lang.String, long, boolean):void");
    }

    private static void validateDexMetadataManifest(String dmaPath, StrictJarFile jarFile, String packageName, long versionCode, boolean requireManifest) throws IOException, PackageParser.PackageParserException {
        if (requireManifest) {
            ZipEntry zipEntry = jarFile.findEntry("manifest.json");
            if (zipEntry != null) {
                InputStream inputStream = jarFile.getInputStream(zipEntry);
                try {
                    JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
                    String jsonPackageName = null;
                    long jsonVersionCode = -1;
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        if (name.equals("packageName")) {
                            jsonPackageName = reader.nextString();
                        } else if (name.equals("versionCode")) {
                            jsonVersionCode = reader.nextLong();
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    if (jsonPackageName == null || jsonVersionCode == -1) {
                        throw new PackageParser.PackageParserException(PackageManager.INSTALL_FAILED_BAD_DEX_METADATA, "manifest.json in " + dmaPath + " is missing 'packageName' and/or 'versionCode'");
                    } else if (!jsonPackageName.equals(packageName)) {
                        throw new PackageParser.PackageParserException(PackageManager.INSTALL_FAILED_BAD_DEX_METADATA, "manifest.json in " + dmaPath + " has invalid packageName: " + jsonPackageName + ", expected: " + packageName);
                    } else if (versionCode != jsonVersionCode) {
                        throw new PackageParser.PackageParserException(PackageManager.INSTALL_FAILED_BAD_DEX_METADATA, "manifest.json in " + dmaPath + " has invalid versionCode: " + jsonVersionCode + ", expected: " + versionCode);
                    } else if (DEBUG) {
                        Log.v(TAG, "validateDexMetadataManifest: " + dmaPath + ", " + packageName + ", " + versionCode + ": successful");
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new PackageParser.PackageParserException(PackageManager.INSTALL_FAILED_BAD_DEX_METADATA, "Error opening manifest.json in " + dmaPath, e);
                }
            } else {
                throw new PackageParser.PackageParserException(PackageManager.INSTALL_FAILED_BAD_DEX_METADATA, "Missing manifest.json in " + dmaPath);
            }
        } else if (DEBUG) {
            Log.v(TAG, "validateDexMetadataManifest: " + dmaPath + " manifest.json check skipped");
        }
    }

    public static void validateDexPaths(String[] paths) {
        ArrayList<String> apks = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            if (ApkLiteParseUtils.isApkPath(paths[i])) {
                apks.add(paths[i]);
            }
        }
        ArrayList<String> unmatchedDmFiles = new ArrayList<>();
        for (String dmPath : paths) {
            if (isDexMetadataPath(dmPath)) {
                boolean valid = false;
                int j = apks.size() - 1;
                while (true) {
                    if (j < 0) {
                        break;
                    } else if (dmPath.equals(buildDexMetadataPathForFile(new File(apks.get(j))))) {
                        valid = true;
                        break;
                    } else {
                        j--;
                    }
                }
                if (!valid) {
                    unmatchedDmFiles.add(dmPath);
                }
            }
        }
        if (!unmatchedDmFiles.isEmpty()) {
            throw new IllegalStateException("Unmatched .dm files: " + unmatchedDmFiles);
        }
    }
}
