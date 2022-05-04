package android.net;

import android.content.IntentFilter;
import android.os.ServiceManager;
import android.provider.SettingsStringUtil;
import android.util.Log;
import com.android.net.IProxyService;
import com.google.android.collect.Lists;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
/* loaded from: classes2.dex */
public class PacProxySelector extends ProxySelector {
    private static final String PROXY = "PROXY ";
    public static final String PROXY_SERVICE = "com.android.net.IProxyService";
    private static final String SOCKS = "SOCKS ";
    private static final String TAG = "PacProxySelector";
    private final List<Proxy> mDefaultList;
    private IProxyService mProxyService;

    public PacProxySelector() {
        IProxyService asInterface = IProxyService.Stub.asInterface(ServiceManager.getService("com.android.net.IProxyService"));
        this.mProxyService = asInterface;
        if (asInterface == null) {
            Log.e(TAG, "PacProxyService: no proxy service");
        }
        this.mDefaultList = Lists.newArrayList(Proxy.NO_PROXY);
    }

    @Override // java.net.ProxySelector
    public List<Proxy> select(URI uri) {
        String urlString;
        if (this.mProxyService == null) {
            this.mProxyService = IProxyService.Stub.asInterface(ServiceManager.getService("com.android.net.IProxyService"));
        }
        if (this.mProxyService == null) {
            Log.e(TAG, "select: no proxy service return NO_PROXY");
            return Lists.newArrayList(Proxy.NO_PROXY);
        }
        String response = null;
        try {
            if (!IntentFilter.SCHEME_HTTP.equalsIgnoreCase(uri.getScheme())) {
                uri = new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), "/", null, null);
            }
            urlString = uri.toURL().toString();
        } catch (MalformedURLException e) {
            urlString = uri.getHost();
        } catch (URISyntaxException e2) {
            urlString = uri.getHost();
        }
        try {
            response = this.mProxyService.resolvePacFile(uri.getHost(), urlString);
        } catch (Exception e3) {
            Log.e(TAG, "Error resolving PAC File", e3);
        }
        if (response == null) {
            return this.mDefaultList;
        }
        return parseResponse(response);
    }

    private static List<Proxy> parseResponse(String response) {
        Proxy proxy;
        String[] split = response.split(";");
        List<Proxy> ret = Lists.newArrayList();
        for (String s : split) {
            String trimmed = s.trim();
            if (trimmed.equals("DIRECT")) {
                ret.add(Proxy.NO_PROXY);
            } else if (trimmed.startsWith(PROXY)) {
                Proxy proxy2 = proxyFromHostPort(Proxy.Type.HTTP, trimmed.substring(PROXY.length()));
                if (proxy2 != null) {
                    ret.add(proxy2);
                }
            } else if (trimmed.startsWith(SOCKS) && (proxy = proxyFromHostPort(Proxy.Type.SOCKS, trimmed.substring(SOCKS.length()))) != null) {
                ret.add(proxy);
            }
        }
        if (ret.size() == 0) {
            ret.add(Proxy.NO_PROXY);
        }
        return ret;
    }

    private static Proxy proxyFromHostPort(Proxy.Type type, String hostPortString) {
        try {
            String[] hostPort = hostPortString.split(SettingsStringUtil.DELIMITER);
            String host = hostPort[0];
            int port = Integer.parseInt(hostPort[1]);
            return new Proxy(type, InetSocketAddress.createUnresolved(host, port));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Log.d(TAG, "Unable to parse proxy " + hostPortString + " " + e);
            return null;
        }
    }

    @Override // java.net.ProxySelector
    public void connectFailed(URI uri, SocketAddress address, IOException failure) {
    }
}
