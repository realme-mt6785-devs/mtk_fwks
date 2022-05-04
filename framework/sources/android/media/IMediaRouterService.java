package android.media;

import android.media.IMediaRouter2;
import android.media.IMediaRouter2Manager;
import android.media.IMediaRouterClient;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IMediaRouterService extends IInterface {
    void deselectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void deselectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void enforceMediaContentControlPermission() throws RemoteException;

    List<RoutingSessionInfo> getActiveSessions(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    MediaRouterClientState getState(IMediaRouterClient iMediaRouterClient) throws RemoteException;

    List<MediaRoute2Info> getSystemRoutes() throws RemoteException;

    RoutingSessionInfo getSystemSessionInfo() throws RemoteException;

    boolean isPlaybackActive(IMediaRouterClient iMediaRouterClient) throws RemoteException;

    void registerClientAsUser(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException;

    void registerClientGroupId(IMediaRouterClient iMediaRouterClient, String str) throws RemoteException;

    void registerManager(IMediaRouter2Manager iMediaRouter2Manager, String str) throws RemoteException;

    void registerRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException;

    void releaseSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str) throws RemoteException;

    void releaseSessionWithRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException;

    void requestCreateSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void requestCreateSessionWithRouter2(IMediaRouter2 iMediaRouter2, int i, long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info, Bundle bundle) throws RemoteException;

    void requestSetVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException;

    void requestUpdateVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException;

    void selectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void selectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void setDiscoveryRequest(IMediaRouterClient iMediaRouterClient, int i, boolean z) throws RemoteException;

    void setDiscoveryRequestWithRouter2(IMediaRouter2 iMediaRouter2, RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException;

    void setRouteVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, MediaRoute2Info mediaRoute2Info, int i2) throws RemoteException;

    void setRouteVolumeWithRouter2(IMediaRouter2 iMediaRouter2, MediaRoute2Info mediaRoute2Info, int i) throws RemoteException;

    void setSelectedRoute(IMediaRouterClient iMediaRouterClient, String str, boolean z) throws RemoteException;

    void setSessionVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, int i2) throws RemoteException;

    void setSessionVolumeWithRouter2(IMediaRouter2 iMediaRouter2, String str, int i) throws RemoteException;

    void startScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    void stopScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    void transferToRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void transferToRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void unregisterClient(IMediaRouterClient iMediaRouterClient) throws RemoteException;

    void unregisterManager(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    void unregisterRouter2(IMediaRouter2 iMediaRouter2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaRouterService {
        @Override // android.media.IMediaRouterService
        public void registerClientAsUser(IMediaRouterClient client, String packageName, int userId) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void unregisterClient(IMediaRouterClient client) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void registerClientGroupId(IMediaRouterClient client, String groupId) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public MediaRouterClientState getState(IMediaRouterClient client) throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public boolean isPlaybackActive(IMediaRouterClient client) throws RemoteException {
            return false;
        }

        @Override // android.media.IMediaRouterService
        public void setDiscoveryRequest(IMediaRouterClient client, int routeTypes, boolean activeScan) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setSelectedRoute(IMediaRouterClient client, String routeId, boolean explicit) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestSetVolume(IMediaRouterClient client, String routeId, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestUpdateVolume(IMediaRouterClient client, String routeId, int direction) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void enforceMediaContentControlPermission() throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public List<MediaRoute2Info> getSystemRoutes() throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public RoutingSessionInfo getSystemSessionInfo() throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public void registerRouter2(IMediaRouter2 router, String packageName) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void unregisterRouter2(IMediaRouter2 router) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setDiscoveryRequestWithRouter2(IMediaRouter2 router, RouteDiscoveryPreference preference) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setRouteVolumeWithRouter2(IMediaRouter2 router, MediaRoute2Info route, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestCreateSessionWithRouter2(IMediaRouter2 router, int requestId, long managerRequestId, RoutingSessionInfo oldSession, MediaRoute2Info route, Bundle sessionHints) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void selectRouteWithRouter2(IMediaRouter2 router, String sessionId, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void deselectRouteWithRouter2(IMediaRouter2 router, String sessionId, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void transferToRouteWithRouter2(IMediaRouter2 router, String sessionId, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setSessionVolumeWithRouter2(IMediaRouter2 router, String sessionId, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void releaseSessionWithRouter2(IMediaRouter2 router, String sessionId) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public List<RoutingSessionInfo> getActiveSessions(IMediaRouter2Manager manager) throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public void registerManager(IMediaRouter2Manager manager, String packageName) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void unregisterManager(IMediaRouter2Manager manager) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setRouteVolumeWithManager(IMediaRouter2Manager manager, int requestId, MediaRoute2Info route, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void startScan(IMediaRouter2Manager manager) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void stopScan(IMediaRouter2Manager manager) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestCreateSessionWithManager(IMediaRouter2Manager manager, int requestId, RoutingSessionInfo oldSession, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void selectRouteWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void deselectRouteWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void transferToRouteWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setSessionVolumeWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void releaseSessionWithManager(IMediaRouter2Manager manager, int requestId, String sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaRouterService {
        public static final String DESCRIPTOR = "android.media.IMediaRouterService";
        static final int TRANSACTION_deselectRouteWithManager = 31;
        static final int TRANSACTION_deselectRouteWithRouter2 = 19;
        static final int TRANSACTION_enforceMediaContentControlPermission = 10;
        static final int TRANSACTION_getActiveSessions = 23;
        static final int TRANSACTION_getState = 4;
        static final int TRANSACTION_getSystemRoutes = 11;
        static final int TRANSACTION_getSystemSessionInfo = 12;
        static final int TRANSACTION_isPlaybackActive = 5;
        static final int TRANSACTION_registerClientAsUser = 1;
        static final int TRANSACTION_registerClientGroupId = 3;
        static final int TRANSACTION_registerManager = 24;
        static final int TRANSACTION_registerRouter2 = 13;
        static final int TRANSACTION_releaseSessionWithManager = 34;
        static final int TRANSACTION_releaseSessionWithRouter2 = 22;
        static final int TRANSACTION_requestCreateSessionWithManager = 29;
        static final int TRANSACTION_requestCreateSessionWithRouter2 = 17;
        static final int TRANSACTION_requestSetVolume = 8;
        static final int TRANSACTION_requestUpdateVolume = 9;
        static final int TRANSACTION_selectRouteWithManager = 30;
        static final int TRANSACTION_selectRouteWithRouter2 = 18;
        static final int TRANSACTION_setDiscoveryRequest = 6;
        static final int TRANSACTION_setDiscoveryRequestWithRouter2 = 15;
        static final int TRANSACTION_setRouteVolumeWithManager = 26;
        static final int TRANSACTION_setRouteVolumeWithRouter2 = 16;
        static final int TRANSACTION_setSelectedRoute = 7;
        static final int TRANSACTION_setSessionVolumeWithManager = 33;
        static final int TRANSACTION_setSessionVolumeWithRouter2 = 21;
        static final int TRANSACTION_startScan = 27;
        static final int TRANSACTION_stopScan = 28;
        static final int TRANSACTION_transferToRouteWithManager = 32;
        static final int TRANSACTION_transferToRouteWithRouter2 = 20;
        static final int TRANSACTION_unregisterClient = 2;
        static final int TRANSACTION_unregisterManager = 25;
        static final int TRANSACTION_unregisterRouter2 = 14;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaRouterService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaRouterService)) {
                return new Proxy(obj);
            }
            return (IMediaRouterService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerClientAsUser";
                case 2:
                    return "unregisterClient";
                case 3:
                    return "registerClientGroupId";
                case 4:
                    return "getState";
                case 5:
                    return "isPlaybackActive";
                case 6:
                    return "setDiscoveryRequest";
                case 7:
                    return "setSelectedRoute";
                case 8:
                    return "requestSetVolume";
                case 9:
                    return "requestUpdateVolume";
                case 10:
                    return "enforceMediaContentControlPermission";
                case 11:
                    return "getSystemRoutes";
                case 12:
                    return "getSystemSessionInfo";
                case 13:
                    return "registerRouter2";
                case 14:
                    return "unregisterRouter2";
                case 15:
                    return "setDiscoveryRequestWithRouter2";
                case 16:
                    return "setRouteVolumeWithRouter2";
                case 17:
                    return "requestCreateSessionWithRouter2";
                case 18:
                    return "selectRouteWithRouter2";
                case 19:
                    return "deselectRouteWithRouter2";
                case 20:
                    return "transferToRouteWithRouter2";
                case 21:
                    return "setSessionVolumeWithRouter2";
                case 22:
                    return "releaseSessionWithRouter2";
                case 23:
                    return "getActiveSessions";
                case 24:
                    return "registerManager";
                case 25:
                    return "unregisterManager";
                case 26:
                    return "setRouteVolumeWithManager";
                case 27:
                    return "startScan";
                case 28:
                    return "stopScan";
                case 29:
                    return "requestCreateSessionWithManager";
                case 30:
                    return "selectRouteWithManager";
                case 31:
                    return "deselectRouteWithManager";
                case 32:
                    return "transferToRouteWithManager";
                case 33:
                    return "setSessionVolumeWithManager";
                case 34:
                    return "releaseSessionWithManager";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RouteDiscoveryPreference _arg1;
            MediaRoute2Info _arg12;
            RoutingSessionInfo _arg3;
            MediaRoute2Info _arg4;
            Bundle _arg5;
            MediaRoute2Info _arg2;
            MediaRoute2Info _arg22;
            MediaRoute2Info _arg23;
            MediaRoute2Info _arg24;
            RoutingSessionInfo _arg25;
            MediaRoute2Info _arg32;
            MediaRoute2Info _arg33;
            MediaRoute2Info _arg34;
            MediaRoute2Info _arg35;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg26 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg0 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            String _arg13 = data.readString();
                            registerClientAsUser(_arg0, _arg13, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg02 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            unregisterClient(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg03 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            String _arg14 = data.readString();
                            registerClientGroupId(_arg03, _arg14);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg04 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            MediaRouterClientState _result = getState(_arg04);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg05 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            boolean isPlaybackActive = isPlaybackActive(_arg05);
                            reply.writeNoException();
                            reply.writeInt(isPlaybackActive ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg06 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            int _arg15 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg26 = true;
                            }
                            setDiscoveryRequest(_arg06, _arg15, _arg26);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg07 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            String _arg16 = data.readString();
                            if (data.readInt() != 0) {
                                _arg26 = true;
                            }
                            setSelectedRoute(_arg07, _arg16, _arg26);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg08 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            String _arg17 = data.readString();
                            requestSetVolume(_arg08, _arg17, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouterClient _arg09 = IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
                            String _arg18 = data.readString();
                            requestUpdateVolume(_arg09, _arg18, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            enforceMediaContentControlPermission();
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            List<MediaRoute2Info> _result2 = getSystemRoutes();
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            RoutingSessionInfo _result3 = getSystemSessionInfo();
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg010 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            String _arg19 = data.readString();
                            registerRouter2(_arg010, _arg19);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg011 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            unregisterRouter2(_arg011);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg012 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = RouteDiscoveryPreference.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            setDiscoveryRequestWithRouter2(_arg012, _arg1);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg013 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            setRouteVolumeWithRouter2(_arg013, _arg12, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg014 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            int _arg110 = data.readInt();
                            long _arg27 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg3 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            requestCreateSessionWithRouter2(_arg014, _arg110, _arg27, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg015 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            String _arg111 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            selectRouteWithRouter2(_arg015, _arg111, _arg2);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg016 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            String _arg112 = data.readString();
                            if (data.readInt() != 0) {
                                _arg22 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            deselectRouteWithRouter2(_arg016, _arg112, _arg22);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg017 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            String _arg113 = data.readString();
                            if (data.readInt() != 0) {
                                _arg23 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            transferToRouteWithRouter2(_arg017, _arg113, _arg23);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg018 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            String _arg114 = data.readString();
                            setSessionVolumeWithRouter2(_arg018, _arg114, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2 _arg019 = IMediaRouter2.Stub.asInterface(data.readStrongBinder());
                            String _arg115 = data.readString();
                            releaseSessionWithRouter2(_arg019, _arg115);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg020 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            List<RoutingSessionInfo> _result4 = getActiveSessions(_arg020);
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg021 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            String _arg116 = data.readString();
                            registerManager(_arg021, _arg116);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg022 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            unregisterManager(_arg022);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg023 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg117 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg24 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            int _arg36 = data.readInt();
                            setRouteVolumeWithManager(_arg023, _arg117, _arg24, _arg36);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg024 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            startScan(_arg024);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg025 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            stopScan(_arg025);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg026 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg118 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg25 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            requestCreateSessionWithManager(_arg026, _arg118, _arg25, _arg32);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg027 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg119 = data.readInt();
                            String _arg28 = data.readString();
                            if (data.readInt() != 0) {
                                _arg33 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            selectRouteWithManager(_arg027, _arg119, _arg28, _arg33);
                            reply.writeNoException();
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg028 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg120 = data.readInt();
                            String _arg29 = data.readString();
                            if (data.readInt() != 0) {
                                _arg34 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            deselectRouteWithManager(_arg028, _arg120, _arg29, _arg34);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg029 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg121 = data.readInt();
                            String _arg210 = data.readString();
                            if (data.readInt() != 0) {
                                _arg35 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg35 = null;
                            }
                            transferToRouteWithManager(_arg029, _arg121, _arg210, _arg35);
                            reply.writeNoException();
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg030 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg122 = data.readInt();
                            String _arg211 = data.readString();
                            int _arg37 = data.readInt();
                            setSessionVolumeWithManager(_arg030, _arg122, _arg211, _arg37);
                            reply.writeNoException();
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            IMediaRouter2Manager _arg031 = IMediaRouter2Manager.Stub.asInterface(data.readStrongBinder());
                            int _arg123 = data.readInt();
                            releaseSessionWithManager(_arg031, _arg123, data.readString());
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaRouterService {
            public static IMediaRouterService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.media.IMediaRouterService
            public void registerClientAsUser(IMediaRouterClient client, String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerClientAsUser(client, packageName, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void unregisterClient(IMediaRouterClient client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterClient(client);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void registerClientGroupId(IMediaRouterClient client, String groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeString(groupId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerClientGroupId(client, groupId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public MediaRouterClientState getState(IMediaRouterClient client) throws RemoteException {
                MediaRouterClientState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState(client);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = MediaRouterClientState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public boolean isPlaybackActive(IMediaRouterClient client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPlaybackActive(client);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setDiscoveryRequest(IMediaRouterClient client, int routeTypes, boolean activeScan) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeInt(routeTypes);
                    _data.writeInt(activeScan ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDiscoveryRequest(client, routeTypes, activeScan);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setSelectedRoute(IMediaRouterClient client, String routeId, boolean explicit) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeString(routeId);
                    _data.writeInt(explicit ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSelectedRoute(client, routeId, explicit);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestSetVolume(IMediaRouterClient client, String routeId, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeString(routeId);
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestSetVolume(client, routeId, volume);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestUpdateVolume(IMediaRouterClient client, String routeId, int direction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeString(routeId);
                    _data.writeInt(direction);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestUpdateVolume(client, routeId, direction);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void enforceMediaContentControlPermission() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enforceMediaContentControlPermission();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public List<MediaRoute2Info> getSystemRoutes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemRoutes();
                    }
                    _reply.readException();
                    List<MediaRoute2Info> _result = _reply.createTypedArrayList(MediaRoute2Info.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public RoutingSessionInfo getSystemSessionInfo() throws RemoteException {
                RoutingSessionInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemSessionInfo();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RoutingSessionInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void registerRouter2(IMediaRouter2 router, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerRouter2(router, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void unregisterRouter2(IMediaRouter2 router) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterRouter2(router);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setDiscoveryRequestWithRouter2(IMediaRouter2 router, RouteDiscoveryPreference preference) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    if (preference != null) {
                        _data.writeInt(1);
                        preference.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDiscoveryRequestWithRouter2(router, preference);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setRouteVolumeWithRouter2(IMediaRouter2 router, MediaRoute2Info route, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRouteVolumeWithRouter2(router, route, volume);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestCreateSessionWithRouter2(IMediaRouter2 router, int requestId, long managerRequestId, RoutingSessionInfo oldSession, MediaRoute2Info route, Bundle sessionHints) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    try {
                        _data.writeInt(requestId);
                        _data.writeLong(managerRequestId);
                        if (oldSession != null) {
                            _data.writeInt(1);
                            oldSession.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (route != null) {
                            _data.writeInt(1);
                            route.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (sessionHints != null) {
                            _data.writeInt(1);
                            sessionHints.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().requestCreateSessionWithRouter2(router, requestId, managerRequestId, oldSession, route, sessionHints);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.media.IMediaRouterService
            public void selectRouteWithRouter2(IMediaRouter2 router, String sessionId, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    _data.writeString(sessionId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().selectRouteWithRouter2(router, sessionId, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void deselectRouteWithRouter2(IMediaRouter2 router, String sessionId, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    _data.writeString(sessionId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deselectRouteWithRouter2(router, sessionId, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void transferToRouteWithRouter2(IMediaRouter2 router, String sessionId, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    _data.writeString(sessionId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().transferToRouteWithRouter2(router, sessionId, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setSessionVolumeWithRouter2(IMediaRouter2 router, String sessionId, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    _data.writeString(sessionId);
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSessionVolumeWithRouter2(router, sessionId, volume);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void releaseSessionWithRouter2(IMediaRouter2 router, String sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(router != null ? router.asBinder() : null);
                    _data.writeString(sessionId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseSessionWithRouter2(router, sessionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public List<RoutingSessionInfo> getActiveSessions(IMediaRouter2Manager manager) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSessions(manager);
                    }
                    _reply.readException();
                    List<RoutingSessionInfo> _result = _reply.createTypedArrayList(RoutingSessionInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void registerManager(IMediaRouter2Manager manager, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerManager(manager, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void unregisterManager(IMediaRouter2Manager manager) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterManager(manager);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setRouteVolumeWithManager(IMediaRouter2Manager manager, int requestId, MediaRoute2Info route, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRouteVolumeWithManager(manager, requestId, route, volume);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void startScan(IMediaRouter2Manager manager) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startScan(manager);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void stopScan(IMediaRouter2Manager manager) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopScan(manager);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestCreateSessionWithManager(IMediaRouter2Manager manager, int requestId, RoutingSessionInfo oldSession, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    if (oldSession != null) {
                        _data.writeInt(1);
                        oldSession.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestCreateSessionWithManager(manager, requestId, oldSession, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void selectRouteWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    _data.writeString(sessionId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().selectRouteWithManager(manager, requestId, sessionId, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void deselectRouteWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    _data.writeString(sessionId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deselectRouteWithManager(manager, requestId, sessionId, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void transferToRouteWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    _data.writeString(sessionId);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().transferToRouteWithManager(manager, requestId, sessionId, route);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setSessionVolumeWithManager(IMediaRouter2Manager manager, int requestId, String sessionId, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    _data.writeString(sessionId);
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSessionVolumeWithManager(manager, requestId, sessionId, volume);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void releaseSessionWithManager(IMediaRouter2Manager manager, int requestId, String sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(manager != null ? manager.asBinder() : null);
                    _data.writeInt(requestId);
                    _data.writeString(sessionId);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseSessionWithManager(manager, requestId, sessionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouterService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaRouterService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
