package android.hardware.camera2.impl;

import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class CameraExtensionUtils {
    public static final int JPEG_DEFAULT_QUALITY = 100;
    public static final int JPEG_DEFAULT_ROTATION = 0;
    public static final int[] SUPPORTED_CAPTURE_OUTPUT_FORMATS = {35, 256};
    private static final String TAG = "CameraExtensionUtils";

    /* loaded from: classes.dex */
    public static class SurfaceInfo {
        public int mWidth = 0;
        public int mHeight = 0;
        public int mFormat = 1;
        public long mUsage = 0;
    }

    /* loaded from: classes.dex */
    public static final class HandlerExecutor implements Executor {
        private final Handler mHandler;

        public HandlerExecutor(Handler handler) {
            this.mHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runCmd) {
            try {
                this.mHandler.post(runCmd);
            } catch (RejectedExecutionException e) {
                Log.w(CameraExtensionUtils.TAG, "Handler thread unavailable, skipping message!");
            }
        }
    }

    public static SurfaceInfo querySurface(Surface s) {
        SurfaceInfo surfaceInfo = new SurfaceInfo();
        int nativeFormat = SurfaceUtils.detectSurfaceFormat(s);
        int dataspace = SurfaceUtils.getSurfaceDataspace(s);
        Size surfaceSize = SurfaceUtils.getSurfaceSize(s);
        surfaceInfo.mFormat = nativeFormat;
        surfaceInfo.mWidth = surfaceSize.getWidth();
        surfaceInfo.mHeight = surfaceSize.getHeight();
        surfaceInfo.mUsage = SurfaceUtils.getSurfaceUsage(s);
        if (nativeFormat != 33 || dataspace != 146931712) {
            return surfaceInfo;
        }
        surfaceInfo.mFormat = 256;
        return surfaceInfo;
    }

    public static Surface getBurstCaptureSurface(List<OutputConfiguration> outputConfigs, HashMap<Integer, List<Size>> supportedCaptureSizes) {
        int[] iArr;
        for (OutputConfiguration config : outputConfigs) {
            SurfaceInfo surfaceInfo = querySurface(config.getSurface());
            for (int supportedFormat : SUPPORTED_CAPTURE_OUTPUT_FORMATS) {
                if (surfaceInfo.mFormat == supportedFormat) {
                    Size captureSize = new Size(surfaceInfo.mWidth, surfaceInfo.mHeight);
                    if (!supportedCaptureSizes.containsKey(Integer.valueOf(supportedFormat))) {
                        return config.getSurface();
                    }
                    if (supportedCaptureSizes.get(Integer.valueOf(surfaceInfo.mFormat)).contains(captureSize)) {
                        return config.getSurface();
                    }
                    throw new IllegalArgumentException("Capture size not supported!");
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.view.Surface getRepeatingRequestSurface(java.util.List<android.hardware.camera2.params.OutputConfiguration> r6, java.util.List<android.util.Size> r7) {
        /*
            java.util.Iterator r0 = r6.iterator()
        L_0x0004:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0057
            java.lang.Object r1 = r0.next()
            android.hardware.camera2.params.OutputConfiguration r1 = (android.hardware.camera2.params.OutputConfiguration) r1
            android.view.Surface r2 = r1.getSurface()
            android.hardware.camera2.impl.CameraExtensionUtils$SurfaceInfo r2 = querySurface(r2)
            int r3 = r2.mFormat
            r4 = 34
            if (r3 == r4) goto L_0x0025
            int r3 = r2.mFormat
            r4 = 1
            if (r3 != r4) goto L_0x0024
            goto L_0x0025
        L_0x0024:
            goto L_0x0004
        L_0x0025:
            android.util.Size r0 = new android.util.Size
            int r3 = r2.mWidth
            int r4 = r2.mHeight
            r0.<init>(r3, r4)
            if (r7 == 0) goto L_0x003b
            boolean r3 = r7.contains(r0)
            if (r3 == 0) goto L_0x003b
            android.view.Surface r3 = r1.getSurface()
            return r3
        L_0x003b:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Repeating request surface size "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r5 = " not supported!"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x0057:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.impl.CameraExtensionUtils.getRepeatingRequestSurface(java.util.List, java.util.List):android.view.Surface");
    }
}
