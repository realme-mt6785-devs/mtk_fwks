package android.net;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.IIpSecService;
import android.net.IpSecManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
/* loaded from: classes2.dex */
public final class IpSecTransform implements AutoCloseable {
    public static final int ENCAP_ESPINUDP = 2;
    public static final int ENCAP_ESPINUDP_NON_IKE = 1;
    public static final int ENCAP_NONE = 0;
    public static final int MODE_TRANSPORT = 0;
    public static final int MODE_TUNNEL = 1;
    private static final String TAG = "IpSecTransform";
    private final IpSecConfig mConfig;
    private final Context mContext;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private int mResourceId = -1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface EncapType {
    }

    public IpSecTransform(Context context, IpSecConfig config) {
        this.mContext = context;
        this.mConfig = new IpSecConfig(config);
    }

    private IIpSecService getIpSecService() {
        IBinder b = ServiceManager.getService(Context.IPSEC_SERVICE);
        if (b != null) {
            return IIpSecService.Stub.asInterface(b);
        }
        throw new RemoteException("Failed to connect to IpSecService").rethrowAsRuntimeException();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void checkResultStatus(int status) throws IOException, IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException {
        switch (status) {
            case 0:
                return;
            case 1:
                throw new IpSecManager.ResourceUnavailableException("Failed to allocate a new IpSecTransform");
            case 2:
                Log.wtf(TAG, "Attempting to use an SPI that was somehow not reserved");
                break;
        }
        throw new IllegalStateException("Failed to Create a Transform with status code " + status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IpSecTransform activate() throws IOException, IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException {
        synchronized (this) {
            try {
                try {
                    try {
                        IIpSecService svc = getIpSecService();
                        IpSecTransformResponse result = svc.createTransform(this.mConfig, new Binder(), this.mContext.getOpPackageName());
                        int status = result.status;
                        checkResultStatus(status);
                        this.mResourceId = result.resourceId;
                        Log.d(TAG, "Added Transform with Id " + this.mResourceId);
                        this.mCloseGuard.open("build");
                    } catch (RemoteException e) {
                        throw e.rethrowAsRuntimeException();
                    }
                } catch (ServiceSpecificException e2) {
                    throw IpSecManager.rethrowUncheckedExceptionFromServiceSpecificException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IpSecTransform)) {
            return false;
        }
        IpSecTransform rhs = (IpSecTransform) other;
        return getConfig().equals(rhs.getConfig()) && this.mResourceId == rhs.mResourceId;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Log.d(TAG, "Removing Transform with Id " + this.mResourceId);
        try {
            if (this.mResourceId == -1) {
                this.mCloseGuard.close();
                return;
            }
            try {
                try {
                    IIpSecService svc = getIpSecService();
                    svc.deleteTransform(this.mResourceId);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to close " + this + ", Exception=" + e);
                }
            } catch (RemoteException e2) {
                throw e2.rethrowAsRuntimeException();
            }
        } finally {
            this.mResourceId = -1;
            this.mCloseGuard.close();
        }
    }

    protected void finalize() throws Throwable {
        CloseGuard closeGuard = this.mCloseGuard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
    }

    IpSecConfig getConfig() {
        return this.mConfig;
    }

    public int getResourceId() {
        return this.mResourceId;
    }

    /* loaded from: classes2.dex */
    public static class NattKeepaliveCallback {
        public static final int ERROR_HARDWARE_ERROR = 3;
        public static final int ERROR_HARDWARE_UNSUPPORTED = 2;
        public static final int ERROR_INVALID_NETWORK = 1;

        public void onStarted() {
        }

        public void onStopped() {
        }

        public void onError(int error) {
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private IpSecConfig mConfig = new IpSecConfig();
        private Context mContext;

        public Builder setEncryption(IpSecAlgorithm algo) {
            Preconditions.checkNotNull(algo);
            this.mConfig.setEncryption(algo);
            return this;
        }

        public Builder setAuthentication(IpSecAlgorithm algo) {
            Preconditions.checkNotNull(algo);
            this.mConfig.setAuthentication(algo);
            return this;
        }

        public Builder setAuthenticatedEncryption(IpSecAlgorithm algo) {
            Preconditions.checkNotNull(algo);
            this.mConfig.setAuthenticatedEncryption(algo);
            return this;
        }

        public Builder setIpv4Encapsulation(IpSecManager.UdpEncapsulationSocket localSocket, int remotePort) {
            Preconditions.checkNotNull(localSocket);
            this.mConfig.setEncapType(2);
            if (localSocket.getResourceId() != -1) {
                this.mConfig.setEncapSocketResourceId(localSocket.getResourceId());
                this.mConfig.setEncapRemotePort(remotePort);
                return this;
            }
            throw new IllegalArgumentException("Invalid UdpEncapsulationSocket");
        }

        public IpSecTransform buildTransportModeTransform(InetAddress sourceAddress, IpSecManager.SecurityParameterIndex spi) throws IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException, IOException {
            Preconditions.checkNotNull(sourceAddress);
            Preconditions.checkNotNull(spi);
            if (spi.getResourceId() != -1) {
                this.mConfig.setMode(0);
                this.mConfig.setSourceAddress(sourceAddress.getHostAddress());
                this.mConfig.setSpiResourceId(spi.getResourceId());
                return new IpSecTransform(this.mContext, this.mConfig).activate();
            }
            throw new IllegalArgumentException("Invalid SecurityParameterIndex");
        }

        @SystemApi
        public IpSecTransform buildTunnelModeTransform(InetAddress sourceAddress, IpSecManager.SecurityParameterIndex spi) throws IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException, IOException {
            Preconditions.checkNotNull(sourceAddress);
            Preconditions.checkNotNull(spi);
            if (spi.getResourceId() != -1) {
                this.mConfig.setMode(1);
                this.mConfig.setSourceAddress(sourceAddress.getHostAddress());
                this.mConfig.setSpiResourceId(spi.getResourceId());
                return new IpSecTransform(this.mContext, this.mConfig).activate();
            }
            throw new IllegalArgumentException("Invalid SecurityParameterIndex");
        }

        public Builder(Context context) {
            Preconditions.checkNotNull(context);
            this.mContext = context;
        }
    }

    public String toString() {
        return "IpSecTransform{resourceId=" + this.mResourceId + "}";
    }
}
