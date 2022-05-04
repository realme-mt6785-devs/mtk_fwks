package android.media;

import android.media.IMediaHTTPConnection;
import android.media.MediaMetrics;
import android.net.InetAddresses;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.SettingsStringUtil;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes2.dex */
public class MediaHTTPConnection extends IMediaHTTPConnection.Stub {
    private static final int CONNECT_TIMEOUT_MS = 30000;
    private static final int HTTP_TEMP_REDIRECT = 307;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "MediaHTTPConnection";
    private static final boolean VERBOSE = false;
    private long mNativeContext;
    private long mCurrentOffset = -1;
    private URL mURL = null;
    private Map<String, String> mHeaders = null;
    private volatile HttpURLConnection mConnection = null;
    private long mTotalSize = -1;
    private InputStream mInputStream = null;
    private boolean mAllowCrossDomainRedirect = true;
    private boolean mAllowCrossProtocolRedirect = true;
    public OplusMediaHTTPConnection mOplusMediaHTTPConnection = new OplusMediaHTTPConnection(this);
    private final AtomicInteger mNumDisconnectingThreads = new AtomicInteger(0);

    private final native void native_finalize();

    private final native IBinder native_getIMemory();

    private static final native void native_init();

    private final native int native_readAt(long j, int i);

    private final native void native_setup();

    public MediaHTTPConnection() {
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (cookieHandler == null) {
            Log.w(TAG, "MediaHTTPConnection: Unexpected. No CookieHandler found.");
        }
        native_setup();
    }

    @Override // android.media.IMediaHTTPConnection
    public synchronized IBinder connect(String uri, String headers) {
        try {
            disconnect();
            try {
                this.mAllowCrossDomainRedirect = true;
                this.mURL = new URL(uri);
                this.mHeaders = convertHeaderStringToMap(headers);
                return native_getIMemory();
            } catch (MalformedURLException e) {
                return null;
            }
        } catch (MalformedURLException e2) {
        }
    }

    private static boolean parseBoolean(String val) {
        try {
            return Long.parseLong(val) != 0;
        } catch (NumberFormatException e) {
            return "true".equalsIgnoreCase(val) || MediaMetrics.Value.YES.equalsIgnoreCase(val);
        }
    }

    private synchronized boolean filterOutInternalHeaders(String key, String val) {
        if (!"android-allow-cross-domain-redirect".equalsIgnoreCase(key)) {
            return false;
        }
        boolean parseBoolean = parseBoolean(val);
        this.mAllowCrossDomainRedirect = parseBoolean;
        this.mAllowCrossProtocolRedirect = parseBoolean;
        return true;
    }

    private synchronized Map<String, String> convertHeaderStringToMap(String headers) {
        HashMap<String, String> map;
        map = new HashMap<>();
        String[] pairs = headers.split("\r\n");
        for (String pair : pairs) {
            int colonPos = pair.indexOf(SettingsStringUtil.DELIMITER);
            if (colonPos >= 0) {
                String key = pair.substring(0, colonPos);
                String val = pair.substring(colonPos + 1);
                if (!filterOutInternalHeaders(key, val)) {
                    map.put(key, val);
                }
            }
        }
        return map;
    }

    @Override // android.media.IMediaHTTPConnection
    public void disconnect() {
        this.mNumDisconnectingThreads.incrementAndGet();
        try {
            HttpURLConnection connectionToDisconnect = this.mConnection;
            if (connectionToDisconnect != null) {
                connectionToDisconnect.disconnect();
            }
            synchronized (this) {
                teardownConnection();
                this.mHeaders = null;
                this.mURL = null;
            }
        } finally {
            this.mNumDisconnectingThreads.decrementAndGet();
        }
    }

    private synchronized void teardownConnection() {
        if (this.mConnection != null) {
            InputStream inputStream = this.mInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
                this.mInputStream = null;
            }
            this.mConnection.disconnect();
            this.mConnection = null;
            this.mCurrentOffset = -1L;
        }
    }

    private static final boolean isLocalHost(URL url) {
        String host;
        if (url == null || (host = url.getHost()) == null) {
            return false;
        }
        if (host.equalsIgnoreCase("localhost")) {
            return true;
        }
        return InetAddresses.parseNumericAddress(host).isLoopbackAddress();
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x01cb, code lost:
        r16.mURL = r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized void seekTo(long r17) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.MediaHTTPConnection.seekTo(long):void");
    }

    @Override // android.media.IMediaHTTPConnection
    public synchronized int readAt(long offset, int size) {
        return native_readAt(offset, size);
    }

    private synchronized int readAt(long offset, byte[] data, int size) {
        int n;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            try {
                try {
                    try {
                        if (offset != this.mCurrentOffset) {
                            seekTo(offset);
                        }
                        n = this.mInputStream.read(data, 0, size);
                        if (n == -1) {
                            n = 0;
                        }
                        this.mCurrentOffset += n;
                    } catch (ProtocolException e) {
                        Log.w(TAG, "readAt " + offset + " / " + size + " => " + e);
                        e.printStackTrace();
                        return MediaPlayer.MEDIA_ERROR_UNSUPPORTED;
                    }
                } catch (NoRouteToHostException e2) {
                    Log.w(TAG, "readAt " + offset + " / " + size + " => " + e2);
                    e2.printStackTrace();
                    return MediaPlayer.MEDIA_ERROR_UNSUPPORTED;
                }
            } catch (UnknownServiceException e3) {
                Log.w(TAG, "readAt " + offset + " / " + size + " => " + e3);
                e3.printStackTrace();
                return MediaPlayer.MEDIA_ERROR_UNSUPPORTED;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            return -1;
        } catch (Exception e5) {
            e5.printStackTrace();
            return -1;
        }
        return n;
    }

    @Override // android.media.IMediaHTTPConnection
    public long getSize() {
        Log.d(TAG, "getSize");
        if (this.mConnection == null) {
            try {
                OplusMediaHTTPConnection oplusMediaHTTPConnection = this.mOplusMediaHTTPConnection;
                if (oplusMediaHTTPConnection == null || !oplusMediaHTTPConnection.isNeedsetTimeout()) {
                    seekTo(0L);
                } else {
                    this.mOplusMediaHTTPConnection.asyncSeekTo(0L);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return -1L;
            }
        }
        return this.mTotalSize;
    }

    @Override // android.media.IMediaHTTPConnection
    public synchronized String getMIMEType() {
        if (this.mConnection == null) {
            try {
                seekTo(0L);
            } catch (IOException e) {
                return "application/octet-stream";
            }
        }
        return this.mConnection.getContentType();
    }

    @Override // android.media.IMediaHTTPConnection
    public synchronized String getUri() {
        return this.mURL.toString();
    }

    protected void finalize() {
        native_finalize();
    }

    static {
        System.loadLibrary("media_jni");
        native_init();
    }
}
