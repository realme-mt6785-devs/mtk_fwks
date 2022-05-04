package com.mediatek.dcfdecoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemProperties;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
/* loaded from: classes.dex */
public class DcfDecoder {
    private static final int ACTION_DECODE_FULL_IMAGE = 0;
    private static final int ACTION_JUST_DECODE_BOUND = 1;
    private static final int ACTION_JUST_DECODE_THUMBNAIL = 2;
    private static final int DECODE_THUMBNAIL_FLAG = 256;
    private static final int HEADER_BUFFER_SIZE = 128;
    private static final String TAG = "DRM/DcfDecoder";
    private static final int THUMBNAIL_TARGET_SIZE = 96;
    private static boolean sIsOmaDrmEnabled;

    private static native byte[] nativeDecryptDcfFile(FileDescriptor fileDescriptor, int i, int i2);

    private native byte[] nativeForceDecryptFile(String str, boolean z);

    static {
        sIsOmaDrmEnabled = false;
        sIsOmaDrmEnabled = SystemProperties.getBoolean("ro.vendor.mtk_oma_drm_support", false);
        System.loadLibrary("dcfdecoderjni");
    }

    public byte[] forceDecryptFile(String pathName, boolean consume) {
        if (pathName != null) {
            return nativeForceDecryptFile(pathName, consume);
        }
        Log.e(TAG, "forceDecryptFile: find null file name!");
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0134, code lost:
        if (r6 != null) goto L_0x0136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x014d, code lost:
        if (0 == 0) goto L_0x0150;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap decodeDrmImageIfNeeded(byte[] r11, java.io.InputStream r12, android.graphics.BitmapFactory.Options r13) {
        /*
            Method dump skipped, instructions count: 343
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.dcfdecoder.DcfDecoder.decodeDrmImageIfNeeded(byte[], java.io.InputStream, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    public static Bitmap decodeDrmImageIfNeeded(FileDescriptor fd, BitmapFactory.Options opts) {
        if (opts != null && opts.inJustDecodeBounds && opts.outWidth > 0 && opts.outHeight > 0) {
            return null;
        }
        Log.d(TAG, "decodeDrmImageIfNeeded with fd");
        long offset = -1;
        try {
            try {
                offset = Os.lseek(fd, 0L, OsConstants.SEEK_CUR);
                Bitmap decodeDrmImage = decodeDrmImage(fd, 0, opts);
                if (offset != -1) {
                    try {
                        Os.lseek(fd, offset, OsConstants.SEEK_SET);
                    } catch (ErrnoException errno1) {
                        Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno1);
                    }
                }
                return decodeDrmImage;
            } catch (Throwable th) {
                if (offset != -1) {
                    try {
                        Os.lseek(fd, offset, OsConstants.SEEK_SET);
                    } catch (ErrnoException errno12) {
                        Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno12);
                    }
                }
                throw th;
            }
        } catch (ErrnoException errno) {
            Log.e(TAG, "decodeDrmImageIfNeeded seek fd to beginning with ", errno);
            if (offset != -1) {
                try {
                    Os.lseek(fd, offset, OsConstants.SEEK_SET);
                } catch (ErrnoException errno13) {
                    Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno13);
                }
            }
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
        if (r2 == null) goto L_0x0056;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap decodeDrmImageIfNeeded(byte[] r6, android.graphics.BitmapFactory.Options r7) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x0010
            boolean r1 = r7.inJustDecodeBounds
            if (r1 == 0) goto L_0x0010
            int r1 = r7.outWidth
            if (r1 <= 0) goto L_0x0010
            int r1 = r7.outHeight
            if (r1 <= 0) goto L_0x0010
            return r0
        L_0x0010:
            java.lang.String r1 = "DRM/DcfDecoder"
            java.lang.String r2 = "decodeDrmImageIfNeeded with data"
            android.util.Log.d(r1, r2)
            boolean r2 = isDrmFile(r6)
            if (r2 != 0) goto L_0x001e
            return r0
        L_0x001e:
            r0 = 0
            r2 = 0
            android.os.MemoryFile r3 = new android.os.MemoryFile     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            java.lang.String r4 = "drm_image"
            int r5 = r6.length     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            r3.<init>(r4, r5)     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            r2 = r3
            int r3 = r6.length     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            r4 = 0
            r2.writeBytes(r6, r4, r4, r3)     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            java.io.FileDescriptor r3 = r2.getFileDescriptor()     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            int r4 = r2.length()     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            android.graphics.Bitmap r1 = decodeDrmImage(r3, r4, r7)     // Catch: all -> 0x0040, IllegalArgumentException -> 0x0042, IOException -> 0x004c
            r0 = r1
        L_0x003c:
            r2.close()
            goto L_0x0056
        L_0x0040:
            r1 = move-exception
            goto L_0x0057
        L_0x0042:
            r3 = move-exception
            java.lang.String r4 = "decodeDrmImageIfNeeded IllegalArgumentException ignoring data"
            android.util.Log.e(r1, r4)     // Catch: all -> 0x0040
            if (r2 == 0) goto L_0x0056
            goto L_0x003c
        L_0x004c:
            r3 = move-exception
            java.lang.String r4 = "decodeDrmImageIfNeeded IOException ignoring data "
            android.util.Log.e(r1, r4)     // Catch: all -> 0x0040
            if (r2 == 0) goto L_0x0056
            goto L_0x003c
        L_0x0056:
            return r0
        L_0x0057:
            if (r2 == 0) goto L_0x005c
            r2.close()
        L_0x005c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.dcfdecoder.DcfDecoder.decodeDrmImageIfNeeded(byte[], android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    private static Bitmap decodeDrmImage(FileDescriptor fd, int size, BitmapFactory.Options opts) {
        int action = 0;
        if (opts != null) {
            if (opts.inJustDecodeBounds) {
                action = 1;
            } else if ((opts.inSampleSize & 256) > 0) {
                action = 2;
            }
        }
        byte[] clearData = nativeDecryptDcfFile(fd, size, action);
        if (clearData == null) {
            Log.e(TAG, "decodeDrmImage native decrypt failed ");
            return null;
        }
        if (action == 2) {
            BitmapFactory.Options thumbnailOpts = new BitmapFactory.Options();
            thumbnailOpts.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(clearData, 0, clearData.length, thumbnailOpts);
            int height = thumbnailOpts.outHeight;
            int width = thumbnailOpts.outWidth;
            int sampleSizeWidth = width / THUMBNAIL_TARGET_SIZE;
            int sampleSizeHeight = height / THUMBNAIL_TARGET_SIZE;
            opts.inSampleSize = Math.min(sampleSizeWidth, sampleSizeHeight);
        }
        Bitmap bm = BitmapFactory.decodeByteArray(clearData, 0, clearData.length, opts);
        return bm;
    }

    private static boolean isDrmFile(byte[] header) {
        if (header == null || header.length < 128) {
            return false;
        }
        String magic = new String(header, 0, 8);
        if (magic.startsWith("CTA5")) {
            Log.d(TAG, "isDrmFile: this is a cta5 file: " + magic);
            return true;
        }
        int version = header[0];
        if (version != 1) {
            Log.d(TAG, "isDrmFile: version is not dcf version 1, no oma drm file");
            return false;
        }
        int contentTypeLen = header[1];
        int contentUriLen = header[2];
        if (contentTypeLen <= 0 || contentTypeLen + 3 > 128 || contentUriLen <= 0 || contentUriLen > 128) {
            Log.d(TAG, "isDrmFile: content type or uri len invalid, not oma drm file, contentType[" + contentTypeLen + "] contentUri[" + contentUriLen + "]");
            return false;
        }
        String contentType = new String(header, 3, contentTypeLen);
        if (!contentType.contains("/")) {
            Log.d(TAG, "isDrmFile: content type not right, not oma drm file");
            return false;
        }
        Log.d(TAG, "this is a oma drm file: " + contentType);
        return true;
    }
}
