package com.mediatek.internal.content;

import android.content.Context;
import android.drm.DrmManagerClient;
import android.media.MediaFile;
import android.net.Uri;
import android.os.SystemProperties;
import android.webkit.MimeTypeMap;
import com.mediatek.media.MtkMediaStore;
import java.io.File;
/* loaded from: classes.dex */
public class MtkFileSystemProviderHelper {
    private static final boolean DEBUG = false;
    private static final boolean LOG_INOTIFY = false;
    private static final String MIMETYPE_JPEG = "image/jpeg";
    private static final String MIMETYPE_JPG = "image/jpg";
    private static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
    private static final String TAG = "FileSystemProvider";
    private Context mContext;
    private String[] mDefaultProjection;
    private static final Uri BASE_URI = new Uri.Builder().scheme("content").authority("com.android.externalstorage.documents").build();
    private static final String[] DEFAULT_DOCUMENT_PROJECTION = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size", "_data", "is_drm", MtkMediaStore.MediaColumns.DRM_METHOD};

    public MtkFileSystemProviderHelper(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public static boolean isMtkDrmApp() {
        return SystemProperties.getBoolean("ro.vendor.mtk_oma_drm_support", false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a9, code lost:
        if (r15 != null) goto L_0x00ab;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
        r15.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bb, code lost:
        if (r15 == null) goto L_0x00c2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void supportDRM(java.io.File r19, android.database.MatrixCursor.RowBuilder r20, java.lang.String r21, java.lang.String r22, java.io.File r23) throws java.io.FileNotFoundException {
        /*
            r18 = this;
            r1 = r20
            java.lang.String r2 = r19.getName()
            boolean r0 = isMtkDrmApp()
            java.lang.String r3 = "mime_type"
            if (r0 == 0) goto L_0x00be
            boolean r0 = r19.isDirectory()
            if (r0 != 0) goto L_0x00be
            r0 = 46
            int r4 = r2.lastIndexOf(r0)
            r0 = 0
            if (r4 < 0) goto L_0x0029
            int r5 = r4 + 1
            java.lang.String r5 = r2.substring(r5)
            java.lang.String r0 = r5.toLowerCase()
            r5 = r0
            goto L_0x002a
        L_0x0029:
            r5 = r0
        L_0x002a:
            if (r5 == 0) goto L_0x00be
            java.lang.String r0 = "dcf"
            boolean r0 = r5.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x00be
            java.lang.String r0 = "external"
            android.net.Uri r12 = android.provider.MediaStore.Files.getContentUri(r0)
            java.lang.String r13 = "_data = ?"
            java.lang.String r0 = "is_drm"
            java.lang.String r14 = "drm_method"
            java.lang.String[] r8 = new java.lang.String[]{r0, r14, r3}
            r15 = 0
            r16 = r23
            if (r16 == 0) goto L_0x00a0
            r11 = r18
            android.content.Context r6 = r11.mContext     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            r7 = 1
            java.lang.String[] r10 = new java.lang.String[r7]     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            r7 = 0
            java.lang.String r9 = r16.getAbsolutePath()     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            r10[r7] = r9     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            r17 = 0
            r7 = r12
            r9 = r13
            r11 = r17
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            r15 = r6
            if (r15 == 0) goto L_0x009d
            boolean r6 = r15.moveToFirst()     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            if (r6 == 0) goto L_0x009d
            int r6 = r15.getColumnIndex(r0)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            int r6 = r15.getInt(r6)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            int r7 = r15.getColumnIndex(r14)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            int r7 = r15.getInt(r7)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            int r9 = r15.getColumnIndex(r3)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            java.lang.String r9 = r15.getString(r9)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            java.lang.Integer r10 = java.lang.Integer.valueOf(r6)     // Catch: all -> 0x0099, IllegalStateException -> 0x009b
            r1.add(r0, r10)     // Catch: all -> 0x0099, IllegalStateException -> 0x009b
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)     // Catch: all -> 0x0099, IllegalStateException -> 0x009b
            r1.add(r14, r0)     // Catch: all -> 0x0099, IllegalStateException -> 0x009b
            goto L_0x00a9
        L_0x0099:
            r0 = move-exception
            goto L_0x00b2
        L_0x009b:
            r0 = move-exception
            goto L_0x00bb
        L_0x009d:
            r9 = r22
            goto L_0x00a9
        L_0x00a0:
            java.lang.String r0 = "FileSystemProvider"
            java.lang.String r6 = "VisibleFile is null"
            android.util.Log.d(r0, r6)     // Catch: all -> 0x00af, IllegalStateException -> 0x00b8
            r9 = r22
        L_0x00a9:
            if (r15 == 0) goto L_0x00c2
        L_0x00ab:
            r15.close()
            goto L_0x00c2
        L_0x00af:
            r0 = move-exception
            r9 = r22
        L_0x00b2:
            if (r15 == 0) goto L_0x00b7
            r15.close()
        L_0x00b7:
            throw r0
        L_0x00b8:
            r0 = move-exception
            r9 = r22
        L_0x00bb:
            if (r15 == 0) goto L_0x00c2
            goto L_0x00ab
        L_0x00be:
            r16 = r19
            r9 = r22
        L_0x00c2:
            r1.add(r3, r9)
            java.lang.String r0 = r16.getAbsolutePath()
            java.lang.String r3 = "_data"
            r1.add(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.internal.content.MtkFileSystemProviderHelper.supportDRM(java.io.File, android.database.MatrixCursor$RowBuilder, java.lang.String, java.lang.String, java.io.File):void");
    }

    public String getTypeForNameMtk(File file, String name) {
        int lastDot = name.lastIndexOf(46);
        if (lastDot >= 0) {
            String extension = name.substring(lastDot + 1).toLowerCase();
            if (extension.equalsIgnoreCase("dcf")) {
                return getTypeForDrmFile(file);
            }
            String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            if (mime != null) {
                return mime;
            }
        }
        String mime2 = MediaFile.getMimeTypeForFile(name);
        if (mime2 != null) {
            return mime2;
        }
        return MIMETYPE_OCTET_STREAM;
    }

    private String getTypeForDrmFile(File file) {
        DrmManagerClient client = new DrmManagerClient(this.mContext);
        String rawFile = file.toString();
        if (client.canHandle(rawFile, (String) null)) {
            return client.getOriginalMimeType(rawFile);
        }
        return MIMETYPE_OCTET_STREAM;
    }

    public String[] getDefaultProjection() {
        return DEFAULT_DOCUMENT_PROJECTION;
    }
}
