package com.android.internal.telecom;

import android.media.MediaMetrics;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.Logging.Session;
import android.telecom.PhoneAccountHandle;
import android.telephony.ims.ImsCallProfile;
import com.android.internal.telecom.IConnectionServiceAdapter;
import java.util.List;
/* loaded from: classes4.dex */
public interface IConnectionService extends IInterface {
    void abort(String str, Session.Info info) throws RemoteException;

    void addConferenceParticipants(String str, List<Uri> list, Session.Info info) throws RemoteException;

    void addConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException;

    void answer(String str, Session.Info info) throws RemoteException;

    void answerVideo(String str, int i, Session.Info info) throws RemoteException;

    void conference(String str, String str2, Session.Info info) throws RemoteException;

    void connectionServiceFocusGained(Session.Info info) throws RemoteException;

    void connectionServiceFocusLost(Session.Info info) throws RemoteException;

    void consultativeTransfer(String str, String str2, Session.Info info) throws RemoteException;

    void createConference(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException;

    void createConferenceComplete(String str, Session.Info info) throws RemoteException;

    void createConferenceFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException;

    void createConnection(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException;

    void createConnectionComplete(String str, Session.Info info) throws RemoteException;

    void createConnectionFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException;

    void deflect(String str, Uri uri, Session.Info info) throws RemoteException;

    void disconnect(String str, Session.Info info) throws RemoteException;

    void handoverComplete(String str, Session.Info info) throws RemoteException;

    void handoverFailed(String str, ConnectionRequest connectionRequest, int i, Session.Info info) throws RemoteException;

    void hold(String str, Session.Info info) throws RemoteException;

    void mergeConference(String str, Session.Info info) throws RemoteException;

    void onCallAudioStateChanged(String str, CallAudioState callAudioState, Session.Info info) throws RemoteException;

    void onCallFilteringCompleted(String str, Connection.CallFilteringCompletionInfo callFilteringCompletionInfo, Session.Info info) throws RemoteException;

    void onExtrasChanged(String str, Bundle bundle, Session.Info info) throws RemoteException;

    void onPostDialContinue(String str, boolean z, Session.Info info) throws RemoteException;

    void onTrackedByNonUiService(String str, boolean z, Session.Info info) throws RemoteException;

    void onUsingAlternativeUi(String str, boolean z, Session.Info info) throws RemoteException;

    void playDtmfTone(String str, char c, Session.Info info) throws RemoteException;

    void pullExternalCall(String str, Session.Info info) throws RemoteException;

    void reject(String str, Session.Info info) throws RemoteException;

    void rejectWithMessage(String str, String str2, Session.Info info) throws RemoteException;

    void rejectWithReason(String str, int i, Session.Info info) throws RemoteException;

    void removeConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException;

    void respondToRttUpgradeRequest(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException;

    void sendCallEvent(String str, String str2, Bundle bundle, Session.Info info) throws RemoteException;

    void silence(String str, Session.Info info) throws RemoteException;

    void splitFromConference(String str, Session.Info info) throws RemoteException;

    void startRtt(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException;

    void stopDtmfTone(String str, Session.Info info) throws RemoteException;

    void stopRtt(String str, Session.Info info) throws RemoteException;

    void swapConference(String str, Session.Info info) throws RemoteException;

    void transfer(String str, Uri uri, boolean z, Session.Info info) throws RemoteException;

    void unhold(String str, Session.Info info) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IConnectionService {
        @Override // com.android.internal.telecom.IConnectionService
        public void addConnectionServiceAdapter(IConnectionServiceAdapter adapter, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void removeConnectionServiceAdapter(IConnectionServiceAdapter adapter, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConnection(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, boolean isUnknown, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConnectionComplete(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConference(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, boolean isUnknown, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConferenceComplete(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConferenceFailed(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void abort(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void answerVideo(String callId, int videoState, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void answer(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void deflect(String callId, Uri address, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void reject(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void rejectWithReason(String callId, int rejectReason, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void rejectWithMessage(String callId, String message, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void transfer(String callId, Uri number, boolean isConfirmationRequired, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void consultativeTransfer(String callId, String otherCallId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void disconnect(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void silence(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void hold(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void unhold(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onCallAudioStateChanged(String activeCallId, CallAudioState callAudioState, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void playDtmfTone(String callId, char digit, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void stopDtmfTone(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void conference(String conferenceCallId, String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void splitFromConference(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void mergeConference(String conferenceCallId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void swapConference(String conferenceCallId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void addConferenceParticipants(String CallId, List<Uri> participants, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onPostDialContinue(String callId, boolean proceed, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void pullExternalCall(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void sendCallEvent(String callId, String event, Bundle extras, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onCallFilteringCompleted(String callId, Connection.CallFilteringCompletionInfo completionInfo, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onExtrasChanged(String callId, Bundle extras, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void startRtt(String callId, ParcelFileDescriptor fromInCall, ParcelFileDescriptor toInCall, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void stopRtt(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void respondToRttUpgradeRequest(String callId, ParcelFileDescriptor fromInCall, ParcelFileDescriptor toInCall, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void connectionServiceFocusLost(Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void connectionServiceFocusGained(Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void handoverFailed(String callId, ConnectionRequest request, int error, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void handoverComplete(String callId, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onUsingAlternativeUi(String callId, boolean isUsingAlternativeUi, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onTrackedByNonUiService(String callId, boolean isTracked, Session.Info sessionInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IConnectionService {
        public static final String DESCRIPTOR = "com.android.internal.telecom.IConnectionService";
        static final int TRANSACTION_abort = 9;
        static final int TRANSACTION_addConferenceParticipants = 29;
        static final int TRANSACTION_addConnectionServiceAdapter = 1;
        static final int TRANSACTION_answer = 11;
        static final int TRANSACTION_answerVideo = 10;
        static final int TRANSACTION_conference = 25;
        static final int TRANSACTION_connectionServiceFocusGained = 39;
        static final int TRANSACTION_connectionServiceFocusLost = 38;
        static final int TRANSACTION_consultativeTransfer = 17;
        static final int TRANSACTION_createConference = 6;
        static final int TRANSACTION_createConferenceComplete = 7;
        static final int TRANSACTION_createConferenceFailed = 8;
        static final int TRANSACTION_createConnection = 3;
        static final int TRANSACTION_createConnectionComplete = 4;
        static final int TRANSACTION_createConnectionFailed = 5;
        static final int TRANSACTION_deflect = 12;
        static final int TRANSACTION_disconnect = 18;
        static final int TRANSACTION_handoverComplete = 41;
        static final int TRANSACTION_handoverFailed = 40;
        static final int TRANSACTION_hold = 20;
        static final int TRANSACTION_mergeConference = 27;
        static final int TRANSACTION_onCallAudioStateChanged = 22;
        static final int TRANSACTION_onCallFilteringCompleted = 33;
        static final int TRANSACTION_onExtrasChanged = 34;
        static final int TRANSACTION_onPostDialContinue = 30;
        static final int TRANSACTION_onTrackedByNonUiService = 43;
        static final int TRANSACTION_onUsingAlternativeUi = 42;
        static final int TRANSACTION_playDtmfTone = 23;
        static final int TRANSACTION_pullExternalCall = 31;
        static final int TRANSACTION_reject = 13;
        static final int TRANSACTION_rejectWithMessage = 15;
        static final int TRANSACTION_rejectWithReason = 14;
        static final int TRANSACTION_removeConnectionServiceAdapter = 2;
        static final int TRANSACTION_respondToRttUpgradeRequest = 37;
        static final int TRANSACTION_sendCallEvent = 32;
        static final int TRANSACTION_silence = 19;
        static final int TRANSACTION_splitFromConference = 26;
        static final int TRANSACTION_startRtt = 35;
        static final int TRANSACTION_stopDtmfTone = 24;
        static final int TRANSACTION_stopRtt = 36;
        static final int TRANSACTION_swapConference = 28;
        static final int TRANSACTION_transfer = 16;
        static final int TRANSACTION_unhold = 21;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnectionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IConnectionService)) {
                return new Proxy(obj);
            }
            return (IConnectionService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addConnectionServiceAdapter";
                case 2:
                    return "removeConnectionServiceAdapter";
                case 3:
                    return "createConnection";
                case 4:
                    return "createConnectionComplete";
                case 5:
                    return "createConnectionFailed";
                case 6:
                    return "createConference";
                case 7:
                    return "createConferenceComplete";
                case 8:
                    return "createConferenceFailed";
                case 9:
                    return "abort";
                case 10:
                    return "answerVideo";
                case 11:
                    return "answer";
                case 12:
                    return "deflect";
                case 13:
                    return "reject";
                case 14:
                    return "rejectWithReason";
                case 15:
                    return "rejectWithMessage";
                case 16:
                    return "transfer";
                case 17:
                    return "consultativeTransfer";
                case 18:
                    return MediaMetrics.Value.DISCONNECT;
                case 19:
                    return "silence";
                case 20:
                    return "hold";
                case 21:
                    return "unhold";
                case 22:
                    return "onCallAudioStateChanged";
                case 23:
                    return "playDtmfTone";
                case 24:
                    return "stopDtmfTone";
                case 25:
                    return ImsCallProfile.EXTRA_CONFERENCE_DEPRECATED;
                case 26:
                    return "splitFromConference";
                case 27:
                    return "mergeConference";
                case 28:
                    return "swapConference";
                case 29:
                    return "addConferenceParticipants";
                case 30:
                    return "onPostDialContinue";
                case 31:
                    return "pullExternalCall";
                case 32:
                    return "sendCallEvent";
                case 33:
                    return "onCallFilteringCompleted";
                case 34:
                    return "onExtrasChanged";
                case 35:
                    return "startRtt";
                case 36:
                    return "stopRtt";
                case 37:
                    return "respondToRttUpgradeRequest";
                case 38:
                    return "connectionServiceFocusLost";
                case 39:
                    return "connectionServiceFocusGained";
                case 40:
                    return "handoverFailed";
                case 41:
                    return "handoverComplete";
                case 42:
                    return "onUsingAlternativeUi";
                case 43:
                    return "onTrackedByNonUiService";
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
            Session.Info _arg1;
            Session.Info _arg12;
            PhoneAccountHandle _arg0;
            ConnectionRequest _arg2;
            Session.Info _arg5;
            Session.Info _arg13;
            PhoneAccountHandle _arg02;
            ConnectionRequest _arg22;
            Session.Info _arg4;
            PhoneAccountHandle _arg03;
            ConnectionRequest _arg23;
            Session.Info _arg52;
            Session.Info _arg14;
            PhoneAccountHandle _arg04;
            ConnectionRequest _arg24;
            Session.Info _arg42;
            Session.Info _arg15;
            Session.Info _arg25;
            Session.Info _arg16;
            Uri _arg17;
            Session.Info _arg26;
            Session.Info _arg18;
            Session.Info _arg27;
            Session.Info _arg28;
            Uri _arg19;
            Session.Info _arg3;
            Session.Info _arg29;
            Session.Info _arg110;
            Session.Info _arg111;
            Session.Info _arg112;
            Session.Info _arg113;
            CallAudioState _arg114;
            Session.Info _arg210;
            Session.Info _arg211;
            Session.Info _arg115;
            Session.Info _arg212;
            Session.Info _arg116;
            Session.Info _arg117;
            Session.Info _arg118;
            Session.Info _arg213;
            Session.Info _arg214;
            Session.Info _arg119;
            Bundle _arg215;
            Session.Info _arg32;
            Connection.CallFilteringCompletionInfo _arg120;
            Session.Info _arg216;
            Bundle _arg121;
            Session.Info _arg217;
            ParcelFileDescriptor _arg122;
            ParcelFileDescriptor _arg218;
            Session.Info _arg33;
            Session.Info _arg123;
            ParcelFileDescriptor _arg124;
            ParcelFileDescriptor _arg219;
            Session.Info _arg34;
            Session.Info _arg05;
            Session.Info _arg06;
            ConnectionRequest _arg125;
            Session.Info _arg35;
            Session.Info _arg126;
            Session.Info _arg220;
            Session.Info _arg221;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg127 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IConnectionServiceAdapter _arg07 = IConnectionServiceAdapter.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            addConnectionServiceAdapter(_arg07, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IConnectionServiceAdapter _arg08 = IConnectionServiceAdapter.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            removeConnectionServiceAdapter(_arg08, _arg12);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg128 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean _arg36 = data.readInt() != 0;
                            boolean _arg43 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg5 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            createConnection(_arg0, _arg128, _arg2, _arg36, _arg43, _arg5);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            createConnectionComplete(_arg09, _arg13);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg129 = data.readString();
                            if (data.readInt() != 0) {
                                _arg22 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            boolean _arg37 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg4 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            createConnectionFailed(_arg02, _arg129, _arg22, _arg37, _arg4);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            String _arg130 = data.readString();
                            if (data.readInt() != 0) {
                                _arg23 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            boolean _arg38 = data.readInt() != 0;
                            boolean _arg44 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg52 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg52 = null;
                            }
                            createConference(_arg03, _arg130, _arg23, _arg38, _arg44, _arg52);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            createConferenceComplete(_arg010, _arg14);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            String _arg131 = data.readString();
                            if (data.readInt() != 0) {
                                _arg24 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            boolean _arg39 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg42 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            createConferenceFailed(_arg04, _arg131, _arg24, _arg39, _arg42);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            abort(_arg011, _arg15);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg132 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg25 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            answerVideo(_arg012, _arg132, _arg25);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            if (data.readInt() != 0) {
                                _arg16 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            answer(_arg013, _arg16);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg26 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            deflect(_arg014, _arg17, _arg26);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            reject(_arg015, _arg18);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            int _arg133 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg27 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg27 = null;
                            }
                            rejectWithReason(_arg016, _arg133, _arg27);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            String _arg134 = data.readString();
                            if (data.readInt() != 0) {
                                _arg28 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg28 = null;
                            }
                            rejectWithMessage(_arg017, _arg134, _arg28);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            if (data.readInt() != 0) {
                                _arg19 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg127 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            transfer(_arg018, _arg19, _arg127, _arg3);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            String _arg135 = data.readString();
                            if (data.readInt() != 0) {
                                _arg29 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg29 = null;
                            }
                            consultativeTransfer(_arg019, _arg135, _arg29);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            if (data.readInt() != 0) {
                                _arg110 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            disconnect(_arg020, _arg110);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            if (data.readInt() != 0) {
                                _arg111 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            silence(_arg021, _arg111);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            if (data.readInt() != 0) {
                                _arg112 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg112 = null;
                            }
                            hold(_arg022, _arg112);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            if (data.readInt() != 0) {
                                _arg113 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg113 = null;
                            }
                            unhold(_arg023, _arg113);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            if (data.readInt() != 0) {
                                _arg114 = CallAudioState.CREATOR.createFromParcel(data);
                            } else {
                                _arg114 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg210 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg210 = null;
                            }
                            onCallAudioStateChanged(_arg024, _arg114, _arg210);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg025 = data.readString();
                            char _arg136 = (char) data.readInt();
                            if (data.readInt() != 0) {
                                _arg211 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg211 = null;
                            }
                            playDtmfTone(_arg025, _arg136, _arg211);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            if (data.readInt() != 0) {
                                _arg115 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg115 = null;
                            }
                            stopDtmfTone(_arg026, _arg115);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg027 = data.readString();
                            String _arg137 = data.readString();
                            if (data.readInt() != 0) {
                                _arg212 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg212 = null;
                            }
                            conference(_arg027, _arg137, _arg212);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            if (data.readInt() != 0) {
                                _arg116 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg116 = null;
                            }
                            splitFromConference(_arg028, _arg116);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg029 = data.readString();
                            if (data.readInt() != 0) {
                                _arg117 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg117 = null;
                            }
                            mergeConference(_arg029, _arg117);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            if (data.readInt() != 0) {
                                _arg118 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg118 = null;
                            }
                            swapConference(_arg030, _arg118);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            List<Uri> _arg138 = data.createTypedArrayList(Uri.CREATOR);
                            if (data.readInt() != 0) {
                                _arg213 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg213 = null;
                            }
                            addConferenceParticipants(_arg031, _arg138, _arg213);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            if (data.readInt() != 0) {
                                _arg127 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg214 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg214 = null;
                            }
                            onPostDialContinue(_arg032, _arg127, _arg214);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            if (data.readInt() != 0) {
                                _arg119 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg119 = null;
                            }
                            pullExternalCall(_arg033, _arg119);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            String _arg139 = data.readString();
                            if (data.readInt() != 0) {
                                _arg215 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg215 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            sendCallEvent(_arg034, _arg139, _arg215, _arg32);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            if (data.readInt() != 0) {
                                _arg120 = Connection.CallFilteringCompletionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg120 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg216 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg216 = null;
                            }
                            onCallFilteringCompleted(_arg035, _arg120, _arg216);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            if (data.readInt() != 0) {
                                _arg121 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg121 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg217 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg217 = null;
                            }
                            onExtrasChanged(_arg036, _arg121, _arg217);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg037 = data.readString();
                            if (data.readInt() != 0) {
                                _arg122 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg122 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg218 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg218 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg33 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            startRtt(_arg037, _arg122, _arg218, _arg33);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            if (data.readInt() != 0) {
                                _arg123 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg123 = null;
                            }
                            stopRtt(_arg038, _arg123);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            if (data.readInt() != 0) {
                                _arg124 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg124 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg219 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg219 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg34 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            respondToRttUpgradeRequest(_arg039, _arg124, _arg219, _arg34);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            connectionServiceFocusLost(_arg05);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            connectionServiceFocusGained(_arg06);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            if (data.readInt() != 0) {
                                _arg125 = ConnectionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg125 = null;
                            }
                            int _arg222 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg35 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg35 = null;
                            }
                            handoverFailed(_arg040, _arg125, _arg222, _arg35);
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            if (data.readInt() != 0) {
                                _arg126 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg126 = null;
                            }
                            handoverComplete(_arg041, _arg126);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            if (data.readInt() != 0) {
                                _arg127 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg220 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg220 = null;
                            }
                            onUsingAlternativeUi(_arg042, _arg127, _arg220);
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            if (data.readInt() != 0) {
                                _arg127 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg221 = Session.Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg221 = null;
                            }
                            onTrackedByNonUiService(_arg043, _arg127, _arg221);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IConnectionService {
            public static IConnectionService sDefaultImpl;
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

            @Override // com.android.internal.telecom.IConnectionService
            public void addConnectionServiceAdapter(IConnectionServiceAdapter adapter, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(adapter != null ? adapter.asBinder() : null);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addConnectionServiceAdapter(adapter, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void removeConnectionServiceAdapter(IConnectionServiceAdapter adapter, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(adapter != null ? adapter.asBinder() : null);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeConnectionServiceAdapter(adapter, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConnection(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, boolean isUnknown, Session.Info sessionInfo) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectionManagerPhoneAccount != null) {
                        _data.writeInt(1);
                        connectionManagerPhoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeString(callId);
                        if (request != null) {
                            _data.writeInt(1);
                            request.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeInt(isIncoming ? 1 : 0);
                        _data.writeInt(isUnknown ? 1 : 0);
                        if (sessionInfo != null) {
                            _data.writeInt(1);
                            sessionInfo.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(3, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().createConnection(connectionManagerPhoneAccount, callId, request, isIncoming, isUnknown, sessionInfo);
                            _data.recycle();
                        } catch (Throwable th2) {
                            th = th2;
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

            @Override // com.android.internal.telecom.IConnectionService
            public void createConnectionComplete(String callId, Session.Info sessionInfo) throws RemoteException {
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
                        Stub.getDefaultImpl().createConnectionComplete(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectionManagerPhoneAccount != null) {
                        _data.writeInt(1);
                        connectionManagerPhoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(isIncoming ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createConnectionFailed(connectionManagerPhoneAccount, callId, request, isIncoming, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConference(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, boolean isUnknown, Session.Info sessionInfo) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectionManagerPhoneAccount != null) {
                        _data.writeInt(1);
                        connectionManagerPhoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeString(callId);
                        if (request != null) {
                            _data.writeInt(1);
                            request.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeInt(isIncoming ? 1 : 0);
                        _data.writeInt(isUnknown ? 1 : 0);
                        if (sessionInfo != null) {
                            _data.writeInt(1);
                            sessionInfo.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(6, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().createConference(connectionManagerPhoneAccount, callId, request, isIncoming, isUnknown, sessionInfo);
                            _data.recycle();
                        } catch (Throwable th2) {
                            th = th2;
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

            @Override // com.android.internal.telecom.IConnectionService
            public void createConferenceComplete(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createConferenceComplete(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConferenceFailed(PhoneAccountHandle connectionManagerPhoneAccount, String callId, ConnectionRequest request, boolean isIncoming, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectionManagerPhoneAccount != null) {
                        _data.writeInt(1);
                        connectionManagerPhoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(isIncoming ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createConferenceFailed(connectionManagerPhoneAccount, callId, request, isIncoming, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void abort(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abort(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void answerVideo(String callId, int videoState, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().answerVideo(callId, videoState, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void answer(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().answer(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void deflect(String callId, Uri address, Session.Info sessionInfo) throws RemoteException {
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
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deflect(callId, address, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void reject(String callId, Session.Info sessionInfo) throws RemoteException {
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
                        Stub.getDefaultImpl().reject(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void rejectWithReason(String callId, int rejectReason, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(rejectReason);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rejectWithReason(callId, rejectReason, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void rejectWithMessage(String callId, String message, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeString(message);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rejectWithMessage(callId, message, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void transfer(String callId, Uri number, boolean isConfirmationRequired, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (number != null) {
                        _data.writeInt(1);
                        number.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(isConfirmationRequired ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().transfer(callId, number, isConfirmationRequired, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void consultativeTransfer(String callId, String otherCallId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeString(otherCallId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().consultativeTransfer(callId, otherCallId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void disconnect(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnect(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void silence(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().silence(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void hold(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hold(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void unhold(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unhold(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onCallAudioStateChanged(String activeCallId, CallAudioState callAudioState, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(activeCallId);
                    if (callAudioState != null) {
                        _data.writeInt(1);
                        callAudioState.writeToParcel(_data, 0);
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
                        Stub.getDefaultImpl().onCallAudioStateChanged(activeCallId, callAudioState, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void playDtmfTone(String callId, char digit, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(digit);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playDtmfTone(callId, digit, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void stopDtmfTone(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopDtmfTone(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void conference(String conferenceCallId, String callId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(conferenceCallId);
                    _data.writeString(callId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().conference(conferenceCallId, callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void splitFromConference(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().splitFromConference(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void mergeConference(String conferenceCallId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(conferenceCallId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mergeConference(conferenceCallId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void swapConference(String conferenceCallId, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(conferenceCallId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().swapConference(conferenceCallId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void addConferenceParticipants(String CallId, List<Uri> participants, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(CallId);
                    _data.writeTypedList(participants);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(29, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addConferenceParticipants(CallId, participants, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onPostDialContinue(String callId, boolean proceed, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(proceed ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPostDialContinue(callId, proceed, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void pullExternalCall(String callId, Session.Info sessionInfo) throws RemoteException {
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
                        Stub.getDefaultImpl().pullExternalCall(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void sendCallEvent(String callId, String event, Bundle extras, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCallEvent(callId, event, extras, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onCallFilteringCompleted(String callId, Connection.CallFilteringCompletionInfo completionInfo, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (completionInfo != null) {
                        _data.writeInt(1);
                        completionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallFilteringCompleted(callId, completionInfo, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onExtrasChanged(String callId, Bundle extras, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onExtrasChanged(callId, extras, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void startRtt(String callId, ParcelFileDescriptor fromInCall, ParcelFileDescriptor toInCall, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (fromInCall != null) {
                        _data.writeInt(1);
                        fromInCall.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (toInCall != null) {
                        _data.writeInt(1);
                        toInCall.writeToParcel(_data, 0);
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
                        Stub.getDefaultImpl().startRtt(callId, fromInCall, toInCall, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void stopRtt(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(36, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopRtt(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void respondToRttUpgradeRequest(String callId, ParcelFileDescriptor fromInCall, ParcelFileDescriptor toInCall, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    if (fromInCall != null) {
                        _data.writeInt(1);
                        fromInCall.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (toInCall != null) {
                        _data.writeInt(1);
                        toInCall.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().respondToRttUpgradeRequest(callId, fromInCall, toInCall, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void connectionServiceFocusLost(Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectionServiceFocusLost(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void connectionServiceFocusGained(Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectionServiceFocusGained(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void handoverFailed(String callId, ConnectionRequest request, int error, Session.Info sessionInfo) throws RemoteException {
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
                    _data.writeInt(error);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(40, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handoverFailed(callId, request, error, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void handoverComplete(String callId, Session.Info sessionInfo) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(41, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handoverComplete(callId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onUsingAlternativeUi(String callId, boolean isUsingAlternativeUi, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(isUsingAlternativeUi ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(42, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUsingAlternativeUi(callId, isUsingAlternativeUi, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onTrackedByNonUiService(String callId, boolean isTracked, Session.Info sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(isTracked ? 1 : 0);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(43, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTrackedByNonUiService(callId, isTracked, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectionService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IConnectionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
