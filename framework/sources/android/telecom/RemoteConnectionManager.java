package android.telecom;

import android.content.ComponentName;
import android.os.RemoteException;
import com.android.internal.telecom.IConnectionService;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public class RemoteConnectionManager {
    private final ConnectionService mOurConnectionServiceImpl;
    private final Map<ComponentName, RemoteConnectionService> mRemoteConnectionServices = new HashMap();

    public RemoteConnectionManager(ConnectionService ourConnectionServiceImpl) {
        this.mOurConnectionServiceImpl = ourConnectionServiceImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addConnectionService(ComponentName componentName, IConnectionService outgoingConnectionServiceRpc) {
        if (!this.mRemoteConnectionServices.containsKey(componentName)) {
            try {
                RemoteConnectionService remoteConnectionService = new RemoteConnectionService(outgoingConnectionServiceRpc, this.mOurConnectionServiceImpl);
                this.mRemoteConnectionServices.put(componentName, remoteConnectionService);
            } catch (RemoteException e) {
                Log.w(this, "error when addConnectionService of %s: %s", componentName, e.toString());
            }
        }
    }

    public RemoteConnection createRemoteConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request, boolean isIncoming) {
        PhoneAccountHandle accountHandle = request.getAccountHandle();
        if (accountHandle != null) {
            ComponentName componentName = request.getAccountHandle().getComponentName();
            if (this.mRemoteConnectionServices.containsKey(componentName)) {
                RemoteConnectionService remoteService = this.mRemoteConnectionServices.get(componentName);
                if (remoteService != null) {
                    return remoteService.createRemoteConnection(connectionManagerPhoneAccount, request, isIncoming);
                }
                return null;
            }
            throw new UnsupportedOperationException("accountHandle not supported: " + componentName);
        }
        throw new IllegalArgumentException("accountHandle must be specified.");
    }

    public RemoteConference createRemoteConference(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request, boolean isIncoming) {
        PhoneAccountHandle accountHandle = request.getAccountHandle();
        if (accountHandle != null) {
            ComponentName componentName = request.getAccountHandle().getComponentName();
            if (this.mRemoteConnectionServices.containsKey(componentName)) {
                RemoteConnectionService remoteService = this.mRemoteConnectionServices.get(componentName);
                if (remoteService != null) {
                    return remoteService.createRemoteConference(connectionManagerPhoneAccount, request, isIncoming);
                }
                return null;
            }
            throw new UnsupportedOperationException("accountHandle not supported: " + componentName);
        }
        throw new IllegalArgumentException("accountHandle must be specified.");
    }

    public void conferenceRemoteConnections(RemoteConnection a, RemoteConnection b) {
        if (a.getConnectionService() == b.getConnectionService()) {
            try {
                a.getConnectionService().conference(a.getId(), b.getId(), null);
            } catch (RemoteException e) {
            }
        } else {
            Log.w(this, "Request to conference incompatible remote connections (%s,%s) (%s,%s)", a.getConnectionService(), a.getId(), b.getConnectionService(), b.getId());
        }
    }
}
