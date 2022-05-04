package com.android.internal.telecom;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.ConnectionRequest;
import android.telecom.DisconnectCause;
import android.telecom.Logging.Session;
import android.telecom.ParcelableConference;
import android.telecom.ParcelableConnection;
import android.telecom.PhoneAccountHandle;
import android.telecom.StatusHints;
import com.android.internal.telecom.IVideoProvider;
import com.android.internal.telecom.RemoteServiceCallback;
import java.util.List;
/* loaded from: classes4.dex */
public interface IConnectionServiceAdapter extends IInterface {
    void addConferenceCall(String str, ParcelableConference parcelableConference, Session.Info info) throws RemoteException;

    void addExistingConnection(String str, ParcelableConnection parcelableConnection, Session.Info info) throws RemoteException;

    void handleCreateConferenceComplete(String str, ConnectionRequest connectionRequest, ParcelableConference parcelableConference, Session.Info info) throws RemoteException;

    void handleCreateConnectionComplete(String str, ConnectionRequest connectionRequest, ParcelableConnection parcelableConnection, Session.Info info) throws RemoteException;

    void onConnectionEvent(String str, String str2, Bundle bundle, Session.Info info) throws RemoteException;

    void onConnectionServiceFocusReleased(Session.Info info) throws RemoteException;

    void onPhoneAccountChanged(String str, PhoneAccountHandle phoneAccountHandle, Session.Info info) throws RemoteException;

    void onPostDialChar(String str, char c, Session.Info info) throws RemoteException;

    void onPostDialWait(String str, String str2, Session.Info info) throws RemoteException;

    void onRemoteRttRequest(String str, Session.Info info) throws RemoteException;

    void onRttInitiationFailure(String str, int i, Session.Info info) throws RemoteException;

    void onRttInitiationSuccess(String str, Session.Info info) throws RemoteException;

    void onRttSessionRemotelyTerminated(String str, Session.Info info) throws RemoteException;

    void putExtras(String str, Bundle bundle, Session.Info info) throws RemoteException;

    void queryRemoteConnectionServices(RemoteServiceCallback remoteServiceCallback, String str, Session.Info info) throws RemoteException;

    void removeCall(String str, Session.Info info) throws RemoteException;

    void removeExtras(String str, List<String> list, Session.Info info) throws RemoteException;

    void resetConnectionTime(String str, Session.Info info) throws RemoteException;

    void setActive(String str, Session.Info info) throws RemoteException;

    void setAddress(String str, Uri uri, int i, Session.Info info) throws RemoteException;

    void setAudioRoute(String str, int i, String str2, Session.Info info) throws RemoteException;

    void setCallDirection(String str, int i, Session.Info info) throws RemoteException;

    void setCallerDisplayName(String str, String str2, int i, Session.Info info) throws RemoteException;

    void setConferenceMergeFailed(String str, Session.Info info) throws RemoteException;

    void setConferenceState(String str, boolean z, Session.Info info) throws RemoteException;

    void setConferenceableConnections(String str, List<String> list, Session.Info info) throws RemoteException;

    void setConnectionCapabilities(String str, int i, Session.Info info) throws RemoteException;

    void setConnectionProperties(String str, int i, Session.Info info) throws RemoteException;

    void setDialing(String str, Session.Info info) throws RemoteException;

    void setDisconnected(String str, DisconnectCause disconnectCause, Session.Info info) throws RemoteException;

    void setIsConferenced(String str, String str2, Session.Info info) throws RemoteException;

    void setIsVoipAudioMode(String str, boolean z, Session.Info info) throws RemoteException;

    void setOnHold(String str, Session.Info info) throws RemoteException;

    void setPulling(String str, Session.Info info) throws RemoteException;

    void setRingbackRequested(String str, boolean z, Session.Info info) throws RemoteException;

    void setRinging(String str, Session.Info info) throws RemoteException;

    void setStatusHints(String str, StatusHints statusHints, Session.Info info) throws RemoteException;

    void setVideoProvider(String str, IVideoProvider iVideoProvider, Session.Info info) throws RemoteException;

    void setVideoState(String str, int i, Session.Info info) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IConnectionServiceAdapter {
        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void handleCreateConnectionComplete(String callId, ConnectionRequest request, ParcelableConnection connection, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void handleCreateConferenceComplete(String callId, ConnectionRequest request, ParcelableConference connection, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setActive(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setRinging(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setDialing(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setPulling(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setDisconnected(String callId, DisconnectCause disconnectCause, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setOnHold(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setRingbackRequested(String callId, boolean ringing, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setConnectionCapabilities(String callId, int connectionCapabilities, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setConnectionProperties(String callId, int connectionProperties, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setIsConferenced(String callId, String conferenceCallId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setConferenceMergeFailed(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void addConferenceCall(String callId, ParcelableConference conference, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void removeCall(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onPostDialWait(String callId, String remaining, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onPostDialChar(String callId, char nextChar, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void queryRemoteConnectionServices(RemoteServiceCallback callback, String callingPackage, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setVideoProvider(String callId, IVideoProvider videoProvider, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setVideoState(String callId, int videoState, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setIsVoipAudioMode(String callId, boolean isVoip, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setStatusHints(String callId, StatusHints statusHints, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setAddress(String callId, Uri address, int presentation, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setCallerDisplayName(String callId, String callerDisplayName, int presentation, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setConferenceableConnections(String callId, List<String> conferenceableCallIds, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void addExistingConnection(String callId, ParcelableConnection connection, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void putExtras(String callId, Bundle extras, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void removeExtras(String callId, List<String> keys, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setAudioRoute(String callId, int audioRoute, String bluetoothAddress, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onConnectionEvent(String callId, String event, Bundle extras, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onRttInitiationSuccess(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onRttInitiationFailure(String callId, int reason, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onRttSessionRemotelyTerminated(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onRemoteRttRequest(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onPhoneAccountChanged(String callId, PhoneAccountHandle pHandle, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void onConnectionServiceFocusReleased(Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void resetConnectionTime(String callIdi, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setConferenceState(String callId, boolean isConference, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionServiceAdapter
        public void setCallDirection(String callId, int direction, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IConnectionServiceAdapter {
        public static final String DESCRIPTOR = "com.android.internal.telecom.IConnectionServiceAdapter";
        static final int TRANSACTION_addConferenceCall = 14;
        static final int TRANSACTION_addExistingConnection = 26;
        static final int TRANSACTION_handleCreateConferenceComplete = 2;
        static final int TRANSACTION_handleCreateConnectionComplete = 1;
        static final int TRANSACTION_onConnectionEvent = 30;
        static final int TRANSACTION_onConnectionServiceFocusReleased = 36;
        static final int TRANSACTION_onPhoneAccountChanged = 35;
        static final int TRANSACTION_onPostDialChar = 17;
        static final int TRANSACTION_onPostDialWait = 16;
        static final int TRANSACTION_onRemoteRttRequest = 34;
        static final int TRANSACTION_onRttInitiationFailure = 32;
        static final int TRANSACTION_onRttInitiationSuccess = 31;
        static final int TRANSACTION_onRttSessionRemotelyTerminated = 33;
        static final int TRANSACTION_putExtras = 27;
        static final int TRANSACTION_queryRemoteConnectionServices = 18;
        static final int TRANSACTION_removeCall = 15;
        static final int TRANSACTION_removeExtras = 28;
        static final int TRANSACTION_resetConnectionTime = 37;
        static final int TRANSACTION_setActive = 3;
        static final int TRANSACTION_setAddress = 23;
        static final int TRANSACTION_setAudioRoute = 29;
        static final int TRANSACTION_setCallDirection = 39;
        static final int TRANSACTION_setCallerDisplayName = 24;
        static final int TRANSACTION_setConferenceMergeFailed = 13;
        static final int TRANSACTION_setConferenceState = 38;
        static final int TRANSACTION_setConferenceableConnections = 25;
        static final int TRANSACTION_setConnectionCapabilities = 10;
        static final int TRANSACTION_setConnectionProperties = 11;
        static final int TRANSACTION_setDialing = 5;
        static final int TRANSACTION_setDisconnected = 7;
        static final int TRANSACTION_setIsConferenced = 12;
        static final int TRANSACTION_setIsVoipAudioMode = 21;
        static final int TRANSACTION_setOnHold = 8;
        static final int TRANSACTION_setPulling = 6;
        static final int TRANSACTION_setRingbackRequested = 9;
        static final int TRANSACTION_setRinging = 4;
        static final int TRANSACTION_setStatusHints = 22;
        static final int TRANSACTION_setVideoProvider = 19;
        static final int TRANSACTION_setVideoState = 20;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnectionServiceAdapter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IConnectionServiceAdapter)) {
                return new Proxy(obj);
            }
            return (IConnectionServiceAdapter) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "handleCreateConnectionComplete";
                case 2:
                    return "handleCreateConferenceComplete";
                case 3:
                    return "setActive";
                case 4:
                    return "setRinging";
                case 5:
                    return "setDialing";
                case 6:
                    return "setPulling";
                case 7:
                    return "setDisconnected";
                case 8:
                    return "setOnHold";
                case 9:
                    return "setRingbackRequested";
                case 10:
                    return "setConnectionCapabilities";
                case 11:
                    return "setConnectionProperties";
                case 12:
                    return "setIsConferenced";
                case 13:
                    return "setConferenceMergeFailed";
                case 14:
                    return "addConferenceCall";
                case 15:
                    return "removeCall";
                case 16:
                    return "onPostDialWait";
                case 17:
                    return "onPostDialChar";
                case 18:
                    return "queryRemoteConnectionServices";
                case 19:
                    return "setVideoProvider";
                case 20:
                    return "setVideoState";
                case 21:
                    return "setIsVoipAudioMode";
                case 22:
                    return "setStatusHints";
                case 23:
                    return "setAddress";
                case 24:
                    return "setCallerDisplayName";
                case 25:
                    return "setConferenceableConnections";
                case 26:
                    return "addExistingConnection";
                case 27:
                    return "putExtras";
                case 28:
                    return "removeExtras";
                case 29:
                    return "setAudioRoute";
                case 30:
                    return "onConnectionEvent";
                case 31:
                    return "onRttInitiationSuccess";
                case 32:
                    return "onRttInitiationFailure";
                case 33:
                    return "onRttSessionRemotelyTerminated";
                case 34:
                    return "onRemoteRttRequest";
                case 35:
                    return "onPhoneAccountChanged";
                case 36:
                    return "onConnectionServiceFocusReleased";
                case 37:
                    return "resetConnectionTime";
                case 38:
                    return "setConferenceState";
                case 39:
                    return "setCallDirection";
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
            ConnectionRequest _arg1;
            ParcelableConnection _arg2;
            Session.Info _arg3;
            ConnectionRequest _arg12;
            ParcelableConference _arg22;
            Session.Info _arg32;
            Session.Info _arg13;
            Session.Info _arg14;
            Session.Info _arg15;
            Session.Info _arg16;
            DisconnectCause _arg17;
            Session.Info _arg23;
            Session.Info _arg18;
            Session.Info _arg24;
            Session.Info _arg25;
            Session.Info _arg26;
            Session.Info _arg27;
            Session.Info _arg19;
            ParcelableConference _arg110;
            Session.Info _arg28;
            Session.Info _arg111;
            Session.Info _arg29;
            Session.Info _arg210;
            Session.Info _arg211;
            Session.Info _arg212;
            Session.Info _arg213;
            Session.Info _arg214;
            StatusHints _arg112;
            Session.Info _arg215;
            Uri _arg113;
            Session.Info _arg33;
            Session.Info _arg34;
            Session.Info _arg216;
            ParcelableConnection _arg114;
            Session.Info _arg217;
            Bundle _arg115;
            Session.Info _arg218;
            Session.Info _arg219;
            Session.Info _arg35;
            Bundle _arg220;
            Session.Info _arg36;
            Session.Info _arg116;
            Session.Info _arg221;
            Session.Info _arg117;
            Session.Info _arg118;
            PhoneAccountHandle _arg119;
            Session.Info _arg222;
            Session.Info _arg0;
            Session.Info _arg120;
            Session.Info _arg223;
            Session.Info _arg224;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg121 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = ParcelableConnection.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            handleCreateConnectionComplete(_arg02, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = ParcelableConference.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            handleCreateConferenceComplete(_arg03, _arg12, _arg22, _arg32);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            setActive(_arg04, _arg13);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            setRinging(_arg05, _arg14);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            setDialing(_arg06, _arg15);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg16 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            setPulling(_arg07, _arg16);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = DisconnectCause.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            setDisconnected(_arg08, _arg17, _arg23);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            setOnHold(_arg09, _arg18);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            if (data.readInt() != 0) {
                                _arg121 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg24 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            setRingbackRequested(_arg010, _arg121, _arg24);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            int _arg122 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg25 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            setConnectionCapabilities(_arg011, _arg122, _arg25);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg123 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg26 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            setConnectionProperties(_arg012, _arg123, _arg26);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            String _arg124 = data.readString();
                            if (data.readInt() != 0) {
                                _arg27 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg27 = null;
                            }
                            setIsConferenced(_arg013, _arg124, _arg27);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            if (data.readInt() != 0) {
                                _arg19 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            setConferenceMergeFailed(_arg014, _arg19);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            if (data.readInt() != 0) {
                                _arg110 = ParcelableConference.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg28 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg28 = null;
                            }
                            addConferenceCall(_arg015, _arg110, _arg28);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            if (data.readInt() != 0) {
                                _arg111 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            removeCall(_arg016, _arg111);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            String _arg125 = data.readString();
                            if (data.readInt() != 0) {
                                _arg29 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg29 = null;
                            }
                            onPostDialWait(_arg017, _arg125, _arg29);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            char _arg126 = (char) data.readInt();
                            if (data.readInt() != 0) {
                                _arg210 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg210 = null;
                            }
                            onPostDialChar(_arg018, _arg126, _arg210);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            RemoteServiceCallback _arg019 = RemoteServiceCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg127 = data.readString();
                            if (data.readInt() != 0) {
                                _arg211 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg211 = null;
                            }
                            queryRemoteConnectionServices(_arg019, _arg127, _arg211);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            IVideoProvider _arg128 = IVideoProvider.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg212 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg212 = null;
                            }
                            setVideoProvider(_arg020, _arg128, _arg212);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            int _arg129 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg213 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg213 = null;
                            }
                            setVideoState(_arg021, _arg129, _arg213);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            if (data.readInt() != 0) {
                                _arg121 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg214 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg214 = null;
                            }
                            setIsVoipAudioMode(_arg022, _arg121, _arg214);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            if (data.readInt() != 0) {
                                _arg112 = StatusHints.CREATOR.createFromParcel(data);
                            } else {
                                _arg112 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg215 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg215 = null;
                            }
                            setStatusHints(_arg023, _arg112, _arg215);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            if (data.readInt() != 0) {
                                _arg113 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg113 = null;
                            }
                            int _arg225 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg33 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            setAddress(_arg024, _arg113, _arg225, _arg33);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg025 = data.readString();
                            String _arg130 = data.readString();
                            int _arg226 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg34 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            setCallerDisplayName(_arg025, _arg130, _arg226, _arg34);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            List<String> _arg131 = data.createStringArrayList();
                            if (data.readInt() != 0) {
                                _arg216 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg216 = null;
                            }
                            setConferenceableConnections(_arg026, _arg131, _arg216);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg027 = data.readString();
                            if (data.readInt() != 0) {
                                _arg114 = ParcelableConnection.CREATOR.createFromParcel(data);
                            } else {
                                _arg114 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg217 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg217 = null;
                            }
                            addExistingConnection(_arg027, _arg114, _arg217);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            if (data.readInt() != 0) {
                                _arg115 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg115 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg218 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg218 = null;
                            }
                            putExtras(_arg028, _arg115, _arg218);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg029 = data.readString();
                            List<String> _arg132 = data.createStringArrayList();
                            if (data.readInt() != 0) {
                                _arg219 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg219 = null;
                            }
                            removeExtras(_arg029, _arg132, _arg219);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            int _arg133 = data.readInt();
                            String _arg227 = data.readString();
                            if (data.readInt() != 0) {
                                _arg35 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg35 = null;
                            }
                            setAudioRoute(_arg030, _arg133, _arg227, _arg35);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            String _arg134 = data.readString();
                            if (data.readInt() != 0) {
                                _arg220 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg220 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg36 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg36 = null;
                            }
                            onConnectionEvent(_arg031, _arg134, _arg220, _arg36);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            if (data.readInt() != 0) {
                                _arg116 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg116 = null;
                            }
                            onRttInitiationSuccess(_arg032, _arg116);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            int _arg135 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg221 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg221 = null;
                            }
                            onRttInitiationFailure(_arg033, _arg135, _arg221);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            if (data.readInt() != 0) {
                                _arg117 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg117 = null;
                            }
                            onRttSessionRemotelyTerminated(_arg034, _arg117);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            if (data.readInt() != 0) {
                                _arg118 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg118 = null;
                            }
                            onRemoteRttRequest(_arg035, _arg118);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            if (data.readInt() != 0) {
                                _arg119 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg119 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg222 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg222 = null;
                            }
                            onPhoneAccountChanged(_arg036, _arg119, _arg222);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onConnectionServiceFocusReleased(_arg0);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg037 = data.readString();
                            if (data.readInt() != 0) {
                                _arg120 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg120 = null;
                            }
                            resetConnectionTime(_arg037, _arg120);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            if (data.readInt() != 0) {
                                _arg121 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg223 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg223 = null;
                            }
                            setConferenceState(_arg038, _arg121, _arg223);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            int _arg136 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg224 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg224 = null;
                            }
                            setCallDirection(_arg039, _arg136, _arg224);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IConnectionServiceAdapter {
            public static IConnectionServiceAdapter sDefaultImpl;
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

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void handleCreateConnectionComplete(String callId, ConnectionRequest request, ParcelableConnection connection, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (connection != null) {
                        _data.writeInt(1);
                        connection.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleCreateConnectionComplete(callId, request, connection, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void handleCreateConferenceComplete(String callId, ConnectionRequest request, ParcelableConference connection, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (connection != null) {
                        _data.writeInt(1);
                        connection.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleCreateConferenceComplete(callId, request, connection, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setActive(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setActive(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setRinging(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRinging(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setDialing(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDialing(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setPulling(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPulling(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setDisconnected(String callId, DisconnectCause disconnectCause, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (disconnectCause != null) {
                        _data.writeInt(1);
                        disconnectCause.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisconnected(callId, disconnectCause, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setOnHold(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOnHold(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setRingbackRequested(String callId, boolean ringing, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(ringing ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRingbackRequested(callId, ringing, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setConnectionCapabilities(String callId, int connectionCapabilities, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(connectionCapabilities);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConnectionCapabilities(callId, connectionCapabilities, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setConnectionProperties(String callId, int connectionProperties, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(connectionProperties);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConnectionProperties(callId, connectionProperties, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setIsConferenced(String callId, String conferenceCallId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeString(conferenceCallId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIsConferenced(callId, conferenceCallId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setConferenceMergeFailed(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConferenceMergeFailed(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void addConferenceCall(String callId, ParcelableConference conference, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (conference != null) {
                        _data.writeInt(1);
                        conference.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addConferenceCall(callId, conference, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void removeCall(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCall(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onPostDialWait(String callId, String remaining, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeString(remaining);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPostDialWait(callId, remaining, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onPostDialChar(String callId, char nextChar, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(nextChar);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPostDialChar(callId, nextChar, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void queryRemoteConnectionServices(RemoteServiceCallback callback, String callingPackage, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(callingPackage);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryRemoteConnectionServices(callback, callingPackage, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setVideoProvider(String callId, IVideoProvider videoProvider, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeStrongBinder(videoProvider != null ? videoProvider.asBinder() : null);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVideoProvider(callId, videoProvider, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setVideoState(String callId, int videoState, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(videoState);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVideoState(callId, videoState, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setIsVoipAudioMode(String callId, boolean isVoip, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(isVoip ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIsVoipAudioMode(callId, isVoip, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setStatusHints(String callId, StatusHints statusHints, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (statusHints != null) {
                        _data.writeInt(1);
                        statusHints.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStatusHints(callId, statusHints, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setAddress(String callId, Uri address, int presentation, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (address != null) {
                        _data.writeInt(1);
                        address.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(presentation);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAddress(callId, address, presentation, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setCallerDisplayName(String callId, String callerDisplayName, int presentation, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeString(callerDisplayName);
                    _data.writeInt(presentation);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallerDisplayName(callId, callerDisplayName, presentation, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setConferenceableConnections(String callId, List<String> conferenceableCallIds, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeStringList(conferenceableCallIds);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConferenceableConnections(callId, conferenceableCallIds, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void addExistingConnection(String callId, ParcelableConnection connection, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (connection != null) {
                        _data.writeInt(1);
                        connection.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addExistingConnection(callId, connection, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void putExtras(String callId, Bundle extras, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().putExtras(callId, extras, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void removeExtras(String callId, List<String> keys, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeStringList(keys);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeExtras(callId, keys, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setAudioRoute(String callId, int audioRoute, String bluetoothAddress, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(audioRoute);
                    _data.writeString(bluetoothAddress);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(29, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAudioRoute(callId, audioRoute, bluetoothAddress, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onConnectionEvent(String callId, String event, Bundle extras, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeString(event);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnectionEvent(callId, event, extras, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onRttInitiationSuccess(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(31, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRttInitiationSuccess(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onRttInitiationFailure(String callId, int reason, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(reason);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRttInitiationFailure(callId, reason, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onRttSessionRemotelyTerminated(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRttSessionRemotelyTerminated(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onRemoteRttRequest(String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoteRttRequest(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onPhoneAccountChanged(String callId, PhoneAccountHandle pHandle, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (pHandle != null) {
                        _data.writeInt(1);
                        pHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(35, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPhoneAccountChanged(callId, pHandle, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void onConnectionServiceFocusReleased(Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(36, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnectionServiceFocusReleased(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void resetConnectionTime(String callIdi, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callIdi);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetConnectionTime(callIdi, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setConferenceState(String callId, boolean isConference, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(isConference ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConferenceState(callId, isConference, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionServiceAdapter
            public void setCallDirection(String callId, int direction, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(direction);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallDirection(callId, direction, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectionServiceAdapter impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IConnectionServiceAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
