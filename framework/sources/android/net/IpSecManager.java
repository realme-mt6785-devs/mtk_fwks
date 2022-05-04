package android.net;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.telephony.ims.RcsContactPresenceTuple;
import android.util.AndroidException;
import android.util.Log;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
/* loaded from: classes2.dex */
public final class IpSecManager {
    public static final int DIRECTION_FWD = 2;
    public static final int DIRECTION_IN = 0;
    public static final int DIRECTION_OUT = 1;
    public static final int INVALID_RESOURCE_ID = -1;
    public static final int INVALID_SECURITY_PARAMETER_INDEX = 0;
    private static final String TAG = "IpSecManager";
    private final Context mContext;
    private final IIpSecService mService;

    /* loaded from: classes2.dex */
    public interface Status {
        public static final int OK = 0;
        public static final int RESOURCE_UNAVAILABLE = 1;
        public static final int SPI_UNAVAILABLE = 2;
    }

    /* loaded from: classes2.dex */
    public static final class SpiUnavailableException extends AndroidException {
        private final int mSpi;

        SpiUnavailableException(String msg, int spi) {
            super(msg + " (spi: " + spi + ")");
            this.mSpi = spi;
        }

        public int getSpi() {
            return this.mSpi;
        }
    }

    /* loaded from: classes2.dex */
    public static final class ResourceUnavailableException extends AndroidException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ResourceUnavailableException(String msg) {
            super(msg);
        }
    }

    /* loaded from: classes2.dex */
    public static final class SecurityParameterIndex implements AutoCloseable {
        private final CloseGuard mCloseGuard;
        private final InetAddress mDestinationAddress;
        private int mResourceId;
        private final IIpSecService mService;
        private int mSpi;

        public int getSpi() {
            return this.mSpi;
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            try {
                try {
                    try {
                        this.mService.releaseSecurityParameterIndex(this.mResourceId);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                } catch (Exception e2) {
                    Log.e(IpSecManager.TAG, "Failed to close " + this + ", Exception=" + e2);
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

        private SecurityParameterIndex(IIpSecService service, InetAddress destinationAddress, int spi) throws ResourceUnavailableException, SpiUnavailableException {
            CloseGuard closeGuard = CloseGuard.get();
            this.mCloseGuard = closeGuard;
            this.mSpi = 0;
            this.mResourceId = -1;
            this.mService = service;
            this.mDestinationAddress = destinationAddress;
            try {
                IpSecSpiResponse result = service.allocateSecurityParameterIndex(destinationAddress.getHostAddress(), spi, new Binder());
                if (result != null) {
                    int status = result.status;
                    switch (status) {
                        case 0:
                            this.mSpi = result.spi;
                            int i = result.resourceId;
                            this.mResourceId = i;
                            if (this.mSpi == 0) {
                                throw new RuntimeException("Invalid SPI returned by IpSecService: " + status);
                            } else if (i != -1) {
                                closeGuard.open(RcsContactPresenceTuple.TUPLE_BASIC_STATUS_OPEN);
                                return;
                            } else {
                                throw new RuntimeException("Invalid Resource ID returned by IpSecService: " + status);
                            }
                        case 1:
                            throw new ResourceUnavailableException("No more SPIs may be allocated by this requester.");
                        case 2:
                            throw new SpiUnavailableException("Requested SPI is unavailable", spi);
                        default:
                            throw new RuntimeException("Unknown status returned by IpSecService: " + status);
                    }
                } else {
                    throw new NullPointerException("Received null response from IpSecService");
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public String toString() {
            return "SecurityParameterIndex{spi=" + this.mSpi + ",resourceId=" + this.mResourceId + "}";
        }
    }

    public SecurityParameterIndex allocateSecurityParameterIndex(InetAddress destinationAddress) throws ResourceUnavailableException {
        try {
            return new SecurityParameterIndex(this.mService, destinationAddress, 0);
        } catch (SpiUnavailableException e) {
            throw new ResourceUnavailableException("No SPIs available");
        } catch (ServiceSpecificException e2) {
            throw rethrowUncheckedExceptionFromServiceSpecificException(e2);
        }
    }

    public SecurityParameterIndex allocateSecurityParameterIndex(InetAddress destinationAddress, int requestedSpi) throws SpiUnavailableException, ResourceUnavailableException {
        if (requestedSpi != 0) {
            try {
                return new SecurityParameterIndex(this.mService, destinationAddress, requestedSpi);
            } catch (ServiceSpecificException e) {
                throw rethrowUncheckedExceptionFromServiceSpecificException(e);
            }
        } else {
            throw new IllegalArgumentException("Requested SPI must be a valid (non-zero) SPI");
        }
    }

    public void applyTransportModeTransform(Socket socket, int direction, IpSecTransform transform) throws IOException {
        socket.getSoLinger();
        applyTransportModeTransform(socket.getFileDescriptor$(), direction, transform);
    }

    public void applyTransportModeTransform(DatagramSocket socket, int direction, IpSecTransform transform) throws IOException {
        applyTransportModeTransform(socket.getFileDescriptor$(), direction, transform);
    }

    public void applyTransportModeTransform(FileDescriptor socket, int direction, IpSecTransform transform) throws IOException {
        try {
            ParcelFileDescriptor pfd = ParcelFileDescriptor.dup(socket);
            try {
                this.mService.applyTransportModeTransform(pfd, direction, transform.getResourceId());
                if (pfd != null) {
                    pfd.close();
                }
            } catch (Throwable th) {
                if (pfd != null) {
                    try {
                        pfd.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw rethrowCheckedExceptionFromServiceSpecificException(e2);
        }
    }

    public void removeTransportModeTransforms(Socket socket) throws IOException {
        socket.getSoLinger();
        removeTransportModeTransforms(socket.getFileDescriptor$());
    }

    public void removeTransportModeTransforms(DatagramSocket socket) throws IOException {
        removeTransportModeTransforms(socket.getFileDescriptor$());
    }

    public void removeTransportModeTransforms(FileDescriptor socket) throws IOException {
        try {
            ParcelFileDescriptor pfd = ParcelFileDescriptor.dup(socket);
            try {
                this.mService.removeTransportModeTransforms(pfd);
                if (pfd != null) {
                    pfd.close();
                }
            } catch (Throwable th) {
                if (pfd != null) {
                    try {
                        pfd.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw rethrowCheckedExceptionFromServiceSpecificException(e2);
        }
    }

    public void removeTunnelModeTransform(Network net, IpSecTransform transform) {
    }

    /* loaded from: classes2.dex */
    public static final class UdpEncapsulationSocket implements AutoCloseable {
        private final CloseGuard mCloseGuard;
        private final ParcelFileDescriptor mPfd;
        private final int mPort;
        private int mResourceId;
        private final IIpSecService mService;

        private UdpEncapsulationSocket(IIpSecService service, int port) throws ResourceUnavailableException, IOException {
            this.mResourceId = -1;
            CloseGuard closeGuard = CloseGuard.get();
            this.mCloseGuard = closeGuard;
            this.mService = service;
            try {
                IpSecUdpEncapResponse result = service.openUdpEncapsulationSocket(port, new Binder());
                switch (result.status) {
                    case 0:
                        this.mResourceId = result.resourceId;
                        this.mPort = result.port;
                        this.mPfd = result.fileDescriptor;
                        closeGuard.open("constructor");
                        return;
                    case 1:
                        throw new ResourceUnavailableException("No more Sockets may be allocated by this requester.");
                    default:
                        throw new RuntimeException("Unknown status returned by IpSecService: " + result.status);
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public FileDescriptor getFileDescriptor() {
            ParcelFileDescriptor parcelFileDescriptor = this.mPfd;
            if (parcelFileDescriptor == null) {
                return null;
            }
            return parcelFileDescriptor.getFileDescriptor();
        }

        public int getPort() {
            return this.mPort;
        }

        @Override // java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                try {
                    try {
                        this.mService.closeUdpEncapsulationSocket(this.mResourceId);
                        this.mResourceId = -1;
                    } catch (Exception e) {
                        Log.e(IpSecManager.TAG, "Failed to close " + this + ", Exception=" + e);
                    }
                    try {
                        this.mPfd.close();
                    } catch (IOException e2) {
                        Log.e(IpSecManager.TAG, "Failed to close UDP Encapsulation Socket with Port= " + this.mPort);
                        throw e2;
                    }
                } catch (RemoteException e3) {
                    throw e3.rethrowFromSystemServer();
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

        @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
        public int getResourceId() {
            return this.mResourceId;
        }

        public String toString() {
            return "UdpEncapsulationSocket{port=" + this.mPort + ",resourceId=" + this.mResourceId + "}";
        }
    }

    public UdpEncapsulationSocket openUdpEncapsulationSocket(int port) throws IOException, ResourceUnavailableException {
        if (port != 0) {
            try {
                return new UdpEncapsulationSocket(this.mService, port);
            } catch (ServiceSpecificException e) {
                throw rethrowCheckedExceptionFromServiceSpecificException(e);
            }
        } else {
            throw new IllegalArgumentException("Specified port must be a valid port number!");
        }
    }

    public UdpEncapsulationSocket openUdpEncapsulationSocket() throws IOException, ResourceUnavailableException {
        try {
            return new UdpEncapsulationSocket(this.mService, 0);
        } catch (ServiceSpecificException e) {
            throw rethrowCheckedExceptionFromServiceSpecificException(e);
        }
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public static final class IpSecTunnelInterface implements AutoCloseable {
        private final CloseGuard mCloseGuard;
        private String mInterfaceName;
        private final InetAddress mLocalAddress;
        private final String mOpPackageName;
        private final InetAddress mRemoteAddress;
        private int mResourceId;
        private final IIpSecService mService;
        private final Network mUnderlyingNetwork;

        public String getInterfaceName() {
            return this.mInterfaceName;
        }

        @SystemApi
        public void addAddress(InetAddress address, int prefixLen) throws IOException {
            try {
                this.mService.addAddressToTunnelInterface(this.mResourceId, new LinkAddress(address, prefixLen), this.mOpPackageName);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (ServiceSpecificException e2) {
                throw IpSecManager.rethrowCheckedExceptionFromServiceSpecificException(e2);
            }
        }

        @SystemApi
        public void removeAddress(InetAddress address, int prefixLen) throws IOException {
            try {
                this.mService.removeAddressFromTunnelInterface(this.mResourceId, new LinkAddress(address, prefixLen), this.mOpPackageName);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (ServiceSpecificException e2) {
                throw IpSecManager.rethrowCheckedExceptionFromServiceSpecificException(e2);
            }
        }

        public void setUnderlyingNetwork(Network underlyingNetwork) throws IOException {
            try {
                this.mService.setNetworkForTunnelInterface(this.mResourceId, underlyingNetwork, this.mOpPackageName);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        private IpSecTunnelInterface(Context ctx, IIpSecService service, InetAddress localAddress, InetAddress remoteAddress, Network underlyingNetwork) throws ResourceUnavailableException, IOException {
            CloseGuard closeGuard = CloseGuard.get();
            this.mCloseGuard = closeGuard;
            this.mResourceId = -1;
            String opPackageName = ctx.getOpPackageName();
            this.mOpPackageName = opPackageName;
            this.mService = service;
            this.mLocalAddress = localAddress;
            this.mRemoteAddress = remoteAddress;
            this.mUnderlyingNetwork = underlyingNetwork;
            try {
                IpSecTunnelInterfaceResponse result = service.createTunnelInterface(localAddress.getHostAddress(), remoteAddress.getHostAddress(), underlyingNetwork, new Binder(), opPackageName);
                switch (result.status) {
                    case 0:
                        this.mResourceId = result.resourceId;
                        this.mInterfaceName = result.interfaceName;
                        closeGuard.open("constructor");
                        return;
                    case 1:
                        throw new ResourceUnavailableException("No more tunnel interfaces may be allocated by this requester.");
                    default:
                        throw new RuntimeException("Unknown status returned by IpSecService: " + result.status);
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            try {
                try {
                    try {
                        this.mService.deleteTunnelInterface(this.mResourceId, this.mOpPackageName);
                    } catch (Exception e) {
                        Log.e(IpSecManager.TAG, "Failed to close " + this + ", Exception=" + e);
                    }
                } catch (RemoteException e2) {
                    throw e2.rethrowFromSystemServer();
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

        public int getResourceId() {
            return this.mResourceId;
        }

        public String toString() {
            return "IpSecTunnelInterface{ifname=" + this.mInterfaceName + ",resourceId=" + this.mResourceId + "}";
        }
    }

    @SystemApi
    public IpSecTunnelInterface createIpSecTunnelInterface(InetAddress localAddress, InetAddress remoteAddress, Network underlyingNetwork) throws ResourceUnavailableException, IOException {
        try {
            return new IpSecTunnelInterface(this.mContext, this.mService, localAddress, remoteAddress, underlyingNetwork);
        } catch (ServiceSpecificException e) {
            throw rethrowCheckedExceptionFromServiceSpecificException(e);
        }
    }

    @SystemApi
    public void applyTunnelModeTransform(IpSecTunnelInterface tunnel, int direction, IpSecTransform transform) throws IOException {
        try {
            this.mService.applyTunnelModeTransform(tunnel.getResourceId(), direction, transform.getResourceId(), this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw rethrowCheckedExceptionFromServiceSpecificException(e2);
        }
    }

    public IpSecManager(Context ctx, IIpSecService service) {
        this.mContext = ctx;
        this.mService = (IIpSecService) Preconditions.checkNotNull(service, "missing service");
    }

    private static void maybeHandleServiceSpecificException(ServiceSpecificException sse) {
        if (sse.errorCode == OsConstants.EINVAL) {
            throw new IllegalArgumentException(sse);
        } else if (sse.errorCode == OsConstants.EAGAIN) {
            throw new IllegalStateException(sse);
        } else if (sse.errorCode == OsConstants.EOPNOTSUPP || sse.errorCode == OsConstants.EPROTONOSUPPORT) {
            throw new UnsupportedOperationException(sse);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RuntimeException rethrowUncheckedExceptionFromServiceSpecificException(ServiceSpecificException sse) {
        maybeHandleServiceSpecificException(sse);
        throw new RuntimeException(sse);
    }

    static IOException rethrowCheckedExceptionFromServiceSpecificException(ServiceSpecificException sse) throws IOException {
        maybeHandleServiceSpecificException(sse);
        throw new ErrnoException("IpSec encountered errno=" + sse.errorCode, sse.errorCode).rethrowAsIOException();
    }
}
