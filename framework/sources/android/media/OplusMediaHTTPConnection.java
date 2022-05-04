package android.media;

import android.app.ActivityThread;
import android.os.SystemProperties;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes2.dex */
public class OplusMediaHTTPConnection {
    public static final int CONNECT_TIMEOUT_MS_OPLUS = 8000;
    private static final int MEDIA_ERROR_HTTP_PROTOCOL_ERROR = -214741;
    public static final int READ_TIMEOUT_MS_OPLUS = 8000;
    private static final String TAG = "MediaHTTPConnection";
    private static final boolean VERBOSE = false;
    public static final int WAIT_TIMEOUT_MS_OPLUS = 8000;
    MediaHTTPConnection mMediaHTTPConnection;
    private boolean mNeedSetTimeout;

    public OplusMediaHTTPConnection(MediaHTTPConnection mMediaHTTPConnection) {
        String packageName;
        this.mNeedSetTimeout = false;
        this.mMediaHTTPConnection = mMediaHTTPConnection;
        boolean isMTKPlatform = SystemProperties.get("ro.boot.hardware", "unknow").toLowerCase().startsWith("mt");
        if (isMTKPlatform && (packageName = ActivityThread.currentPackageName()) != null && packageName.length() > 0) {
            Log.d(TAG, "app " + packageName);
            if (packageName.equals("com.oplus.autotest.modem.test:remote") || packageName.equals("com.oplus.autotest.modem.test")) {
                this.mNeedSetTimeout = true;
            }
        }
    }

    public synchronized int readAt(long offset, byte[] data, int size) {
        int ret;
        ret = readAt_internal(offset, data, size, false);
        if (ret == MEDIA_ERROR_HTTP_PROTOCOL_ERROR) {
            Log.w(TAG, "readAt " + offset + " / " + size + " => protocol error, retry");
            ret = readAt_internal(offset, data, size, true);
        }
        if (ret == MEDIA_ERROR_HTTP_PROTOCOL_ERROR) {
            Log.w(TAG, "readAt " + offset + " / " + size + " => error, convert error");
            ret = MediaPlayer.MEDIA_ERROR_UNSUPPORTED;
        }
        return ret;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
        if (r15 != false) goto L_0x002c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized int readAt_internal(long r11, byte[] r13, int r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.OplusMediaHTTPConnection.readAt_internal(long, byte[], int, boolean):int");
    }

    public boolean isNeedsetTimeout() {
        return this.mNeedSetTimeout;
    }

    public boolean asyncSeekTo(final long offset) {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        boolean ret = false;
        Callable<Boolean> call = new Callable<Boolean>() { // from class: android.media.OplusMediaHTTPConnection.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                if (OplusMirrorMediaHTTPConnection.seekTo != null) {
                    OplusMirrorMediaHTTPConnection.seekTo.call(OplusMediaHTTPConnection.this.mMediaHTTPConnection, Long.valueOf(offset));
                }
                return true;
            }
        };
        try {
            Future<Boolean> future = exec.submit(call);
            ret = future.get(8000L, TimeUnit.MILLISECONDS).booleanValue();
            Log.i(TAG, "asyncSeekTo " + ret);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exec.shutdown();
        return ret;
    }
}
