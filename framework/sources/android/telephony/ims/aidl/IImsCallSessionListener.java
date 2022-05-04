package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.CallQuality;
import android.telephony.ims.ImsCallProfile;
import android.telephony.ims.ImsConferenceState;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.ImsStreamMediaProfile;
import android.telephony.ims.ImsSuppServiceNotification;
import android.telephony.ims.RtpHeaderExtension;
import com.android.ims.internal.IImsCallSession;
import java.util.List;
/* loaded from: classes3.dex */
public interface IImsCallSessionListener extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsCallSessionListener";

    void callQualityChanged(CallQuality callQuality) throws RemoteException;

    void callSessionConferenceExtendFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionConferenceExtendReceived(IImsCallSession iImsCallSession, ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionConferenceExtended(IImsCallSession iImsCallSession, ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionConferenceStateUpdated(ImsConferenceState imsConferenceState) throws RemoteException;

    void callSessionDtmfReceived(char c) throws RemoteException;

    void callSessionHandover(int i, int i2, ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionHandoverFailed(int i, int i2, ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionHeld(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionHoldFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionHoldReceived(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionInitiated(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionInitiatedFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionInitiating(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionInitiatingFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionInviteParticipantsRequestDelivered() throws RemoteException;

    void callSessionInviteParticipantsRequestFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionMayHandover(int i, int i2) throws RemoteException;

    void callSessionMergeComplete(IImsCallSession iImsCallSession) throws RemoteException;

    void callSessionMergeFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionMergeStarted(IImsCallSession iImsCallSession, ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionMultipartyStateChanged(boolean z) throws RemoteException;

    void callSessionProgressing(ImsStreamMediaProfile imsStreamMediaProfile) throws RemoteException;

    void callSessionRemoveParticipantsRequestDelivered() throws RemoteException;

    void callSessionRemoveParticipantsRequestFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionResumeFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionResumeReceived(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionResumed(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionRtpHeaderExtensionsReceived(List<RtpHeaderExtension> list) throws RemoteException;

    void callSessionRttAudioIndicatorChanged(ImsStreamMediaProfile imsStreamMediaProfile) throws RemoteException;

    void callSessionRttMessageReceived(String str) throws RemoteException;

    void callSessionRttModifyRequestReceived(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionRttModifyResponseReceived(int i) throws RemoteException;

    void callSessionSuppServiceReceived(ImsSuppServiceNotification imsSuppServiceNotification) throws RemoteException;

    void callSessionTerminated(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionTransferFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionTransferred() throws RemoteException;

    void callSessionTtyModeReceived(int i) throws RemoteException;

    void callSessionUpdateFailed(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void callSessionUpdateReceived(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionUpdated(ImsCallProfile imsCallProfile) throws RemoteException;

    void callSessionUssdMessageReceived(int i, String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsCallSessionListener {
        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionInitiating(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionInitiatingFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionProgressing(ImsStreamMediaProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionInitiated(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionInitiatedFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionTerminated(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionHeld(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionHoldFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionHoldReceived(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionResumed(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionResumeFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionResumeReceived(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionMergeStarted(IImsCallSession newSession, ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionMergeComplete(IImsCallSession session) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionMergeFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionUpdated(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionUpdateFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionUpdateReceived(ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionConferenceExtended(IImsCallSession newSession, ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionConferenceExtendFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionConferenceExtendReceived(IImsCallSession newSession, ImsCallProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionInviteParticipantsRequestDelivered() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionInviteParticipantsRequestFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRemoveParticipantsRequestDelivered() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRemoveParticipantsRequestFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionConferenceStateUpdated(ImsConferenceState state) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionUssdMessageReceived(int mode, String ussdMessage) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionHandover(int srcNetworkType, int targetNetworkType, ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionHandoverFailed(int srcNetworkType, int targetNetworkType, ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionMayHandover(int srcNetworkType, int targetNetworkType) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionTtyModeReceived(int mode) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionMultipartyStateChanged(boolean isMultiParty) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionSuppServiceReceived(ImsSuppServiceNotification suppSrvNotification) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRttModifyRequestReceived(ImsCallProfile callProfile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRttModifyResponseReceived(int status) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRttMessageReceived(String rttMessage) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRttAudioIndicatorChanged(ImsStreamMediaProfile profile) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionTransferred() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionTransferFailed(ImsReasonInfo reasonInfo) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionDtmfReceived(char dtmf) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callQualityChanged(CallQuality callQuality) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCallSessionListener
        public void callSessionRtpHeaderExtensionsReceived(List<RtpHeaderExtension> extensions) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsCallSessionListener {
        static final int TRANSACTION_callQualityChanged = 41;
        static final int TRANSACTION_callSessionConferenceExtendFailed = 20;
        static final int TRANSACTION_callSessionConferenceExtendReceived = 21;
        static final int TRANSACTION_callSessionConferenceExtended = 19;
        static final int TRANSACTION_callSessionConferenceStateUpdated = 26;
        static final int TRANSACTION_callSessionDtmfReceived = 40;
        static final int TRANSACTION_callSessionHandover = 28;
        static final int TRANSACTION_callSessionHandoverFailed = 29;
        static final int TRANSACTION_callSessionHeld = 7;
        static final int TRANSACTION_callSessionHoldFailed = 8;
        static final int TRANSACTION_callSessionHoldReceived = 9;
        static final int TRANSACTION_callSessionInitiated = 4;
        static final int TRANSACTION_callSessionInitiatedFailed = 5;
        static final int TRANSACTION_callSessionInitiating = 1;
        static final int TRANSACTION_callSessionInitiatingFailed = 2;
        static final int TRANSACTION_callSessionInviteParticipantsRequestDelivered = 22;
        static final int TRANSACTION_callSessionInviteParticipantsRequestFailed = 23;
        static final int TRANSACTION_callSessionMayHandover = 30;
        static final int TRANSACTION_callSessionMergeComplete = 14;
        static final int TRANSACTION_callSessionMergeFailed = 15;
        static final int TRANSACTION_callSessionMergeStarted = 13;
        static final int TRANSACTION_callSessionMultipartyStateChanged = 32;
        static final int TRANSACTION_callSessionProgressing = 3;
        static final int TRANSACTION_callSessionRemoveParticipantsRequestDelivered = 24;
        static final int TRANSACTION_callSessionRemoveParticipantsRequestFailed = 25;
        static final int TRANSACTION_callSessionResumeFailed = 11;
        static final int TRANSACTION_callSessionResumeReceived = 12;
        static final int TRANSACTION_callSessionResumed = 10;
        static final int TRANSACTION_callSessionRtpHeaderExtensionsReceived = 42;
        static final int TRANSACTION_callSessionRttAudioIndicatorChanged = 37;
        static final int TRANSACTION_callSessionRttMessageReceived = 36;
        static final int TRANSACTION_callSessionRttModifyRequestReceived = 34;
        static final int TRANSACTION_callSessionRttModifyResponseReceived = 35;
        static final int TRANSACTION_callSessionSuppServiceReceived = 33;
        static final int TRANSACTION_callSessionTerminated = 6;
        static final int TRANSACTION_callSessionTransferFailed = 39;
        static final int TRANSACTION_callSessionTransferred = 38;
        static final int TRANSACTION_callSessionTtyModeReceived = 31;
        static final int TRANSACTION_callSessionUpdateFailed = 17;
        static final int TRANSACTION_callSessionUpdateReceived = 18;
        static final int TRANSACTION_callSessionUpdated = 16;
        static final int TRANSACTION_callSessionUssdMessageReceived = 27;

        public Stub() {
            attachInterface(this, IImsCallSessionListener.DESCRIPTOR);
        }

        public static IImsCallSessionListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImsCallSessionListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsCallSessionListener)) {
                return new Proxy(obj);
            }
            return (IImsCallSessionListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "callSessionInitiating";
                case 2:
                    return "callSessionInitiatingFailed";
                case 3:
                    return "callSessionProgressing";
                case 4:
                    return "callSessionInitiated";
                case 5:
                    return "callSessionInitiatedFailed";
                case 6:
                    return "callSessionTerminated";
                case 7:
                    return "callSessionHeld";
                case 8:
                    return "callSessionHoldFailed";
                case 9:
                    return "callSessionHoldReceived";
                case 10:
                    return "callSessionResumed";
                case 11:
                    return "callSessionResumeFailed";
                case 12:
                    return "callSessionResumeReceived";
                case 13:
                    return "callSessionMergeStarted";
                case 14:
                    return "callSessionMergeComplete";
                case 15:
                    return "callSessionMergeFailed";
                case 16:
                    return "callSessionUpdated";
                case 17:
                    return "callSessionUpdateFailed";
                case 18:
                    return "callSessionUpdateReceived";
                case 19:
                    return "callSessionConferenceExtended";
                case 20:
                    return "callSessionConferenceExtendFailed";
                case 21:
                    return "callSessionConferenceExtendReceived";
                case 22:
                    return "callSessionInviteParticipantsRequestDelivered";
                case 23:
                    return "callSessionInviteParticipantsRequestFailed";
                case 24:
                    return "callSessionRemoveParticipantsRequestDelivered";
                case 25:
                    return "callSessionRemoveParticipantsRequestFailed";
                case 26:
                    return "callSessionConferenceStateUpdated";
                case 27:
                    return "callSessionUssdMessageReceived";
                case 28:
                    return "callSessionHandover";
                case 29:
                    return "callSessionHandoverFailed";
                case 30:
                    return "callSessionMayHandover";
                case 31:
                    return "callSessionTtyModeReceived";
                case 32:
                    return "callSessionMultipartyStateChanged";
                case 33:
                    return "callSessionSuppServiceReceived";
                case 34:
                    return "callSessionRttModifyRequestReceived";
                case 35:
                    return "callSessionRttModifyResponseReceived";
                case 36:
                    return "callSessionRttMessageReceived";
                case 37:
                    return "callSessionRttAudioIndicatorChanged";
                case 38:
                    return "callSessionTransferred";
                case 39:
                    return "callSessionTransferFailed";
                case 40:
                    return "callSessionDtmfReceived";
                case 41:
                    return "callQualityChanged";
                case 42:
                    return "callSessionRtpHeaderExtensionsReceived";
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
            ImsCallProfile _arg0;
            ImsReasonInfo _arg02;
            ImsStreamMediaProfile _arg03;
            ImsCallProfile _arg04;
            ImsReasonInfo _arg05;
            ImsReasonInfo _arg06;
            ImsCallProfile _arg07;
            ImsReasonInfo _arg08;
            ImsCallProfile _arg09;
            ImsCallProfile _arg010;
            ImsReasonInfo _arg011;
            ImsCallProfile _arg012;
            ImsCallProfile _arg1;
            ImsReasonInfo _arg013;
            ImsCallProfile _arg014;
            ImsReasonInfo _arg015;
            ImsCallProfile _arg016;
            ImsCallProfile _arg12;
            ImsReasonInfo _arg017;
            ImsCallProfile _arg13;
            ImsReasonInfo _arg018;
            ImsReasonInfo _arg019;
            ImsConferenceState _arg020;
            ImsReasonInfo _arg2;
            ImsReasonInfo _arg22;
            ImsSuppServiceNotification _arg021;
            ImsCallProfile _arg022;
            ImsStreamMediaProfile _arg023;
            ImsReasonInfo _arg024;
            CallQuality _arg025;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IImsCallSessionListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            callSessionInitiating(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            callSessionInitiatingFailed(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ImsStreamMediaProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            callSessionProgressing(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            callSessionInitiated(_arg04);
                            return true;
                        case 5:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            callSessionInitiatedFailed(_arg05);
                            return true;
                        case 6:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            callSessionTerminated(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            callSessionHeld(_arg07);
                            return true;
                        case 8:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            callSessionHoldFailed(_arg08);
                            return true;
                        case 9:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            callSessionHoldReceived(_arg09);
                            return true;
                        case 10:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            callSessionResumed(_arg010);
                            return true;
                        case 11:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            callSessionResumeFailed(_arg011);
                            return true;
                        case 12:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg012 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            callSessionResumeReceived(_arg012);
                            return true;
                        case 13:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            IImsCallSession _arg026 = IImsCallSession.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            callSessionMergeStarted(_arg026, _arg1);
                            return true;
                        case 14:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            IImsCallSession _arg027 = IImsCallSession.Stub.asInterface(data.readStrongBinder());
                            callSessionMergeComplete(_arg027);
                            return true;
                        case 15:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg013 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            callSessionMergeFailed(_arg013);
                            return true;
                        case 16:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg014 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            callSessionUpdated(_arg014);
                            return true;
                        case 17:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg015 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg015 = null;
                            }
                            callSessionUpdateFailed(_arg015);
                            return true;
                        case 18:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg016 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg016 = null;
                            }
                            callSessionUpdateReceived(_arg016);
                            return true;
                        case 19:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            IImsCallSession _arg028 = IImsCallSession.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            callSessionConferenceExtended(_arg028, _arg12);
                            return true;
                        case 20:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg017 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg017 = null;
                            }
                            callSessionConferenceExtendFailed(_arg017);
                            return true;
                        case 21:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            IImsCallSession _arg029 = IImsCallSession.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg13 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            callSessionConferenceExtendReceived(_arg029, _arg13);
                            return true;
                        case 22:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            callSessionInviteParticipantsRequestDelivered();
                            return true;
                        case 23:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg018 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg018 = null;
                            }
                            callSessionInviteParticipantsRequestFailed(_arg018);
                            return true;
                        case 24:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            callSessionRemoveParticipantsRequestDelivered();
                            return true;
                        case 25:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg019 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg019 = null;
                            }
                            callSessionRemoveParticipantsRequestFailed(_arg019);
                            return true;
                        case 26:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg020 = ImsConferenceState.CREATOR.createFromParcel(data);
                            } else {
                                _arg020 = null;
                            }
                            callSessionConferenceStateUpdated(_arg020);
                            return true;
                        case 27:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            int _arg030 = data.readInt();
                            String _arg14 = data.readString();
                            callSessionUssdMessageReceived(_arg030, _arg14);
                            return true;
                        case 28:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            int _arg031 = data.readInt();
                            int _arg15 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            callSessionHandover(_arg031, _arg15, _arg2);
                            return true;
                        case 29:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            int _arg032 = data.readInt();
                            int _arg16 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            callSessionHandoverFailed(_arg032, _arg16, _arg22);
                            return true;
                        case 30:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            int _arg033 = data.readInt();
                            int _arg17 = data.readInt();
                            callSessionMayHandover(_arg033, _arg17);
                            return true;
                        case 31:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            int _arg034 = data.readInt();
                            callSessionTtyModeReceived(_arg034);
                            return true;
                        case 32:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            boolean _arg035 = data.readInt() != 0;
                            callSessionMultipartyStateChanged(_arg035);
                            return true;
                        case 33:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg021 = ImsSuppServiceNotification.CREATOR.createFromParcel(data);
                            } else {
                                _arg021 = null;
                            }
                            callSessionSuppServiceReceived(_arg021);
                            return true;
                        case 34:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg022 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg022 = null;
                            }
                            callSessionRttModifyRequestReceived(_arg022);
                            return true;
                        case 35:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            int _arg036 = data.readInt();
                            callSessionRttModifyResponseReceived(_arg036);
                            return true;
                        case 36:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            String _arg037 = data.readString();
                            callSessionRttMessageReceived(_arg037);
                            return true;
                        case 37:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg023 = ImsStreamMediaProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg023 = null;
                            }
                            callSessionRttAudioIndicatorChanged(_arg023);
                            return true;
                        case 38:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            callSessionTransferred();
                            return true;
                        case 39:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg024 = ImsReasonInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg024 = null;
                            }
                            callSessionTransferFailed(_arg024);
                            return true;
                        case 40:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            char _arg038 = (char) data.readInt();
                            callSessionDtmfReceived(_arg038);
                            return true;
                        case 41:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg025 = CallQuality.CREATOR.createFromParcel(data);
                            } else {
                                _arg025 = null;
                            }
                            callQualityChanged(_arg025);
                            return true;
                        case 42:
                            data.enforceInterface(IImsCallSessionListener.DESCRIPTOR);
                            List<RtpHeaderExtension> _arg039 = data.createTypedArrayList(RtpHeaderExtension.CREATOR);
                            callSessionRtpHeaderExtensionsReceived(_arg039);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsCallSessionListener {
            public static IImsCallSessionListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImsCallSessionListener.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionInitiating(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionInitiating(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionInitiatingFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionInitiatingFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionProgressing(ImsStreamMediaProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionProgressing(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionInitiated(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionInitiated(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionInitiatedFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionInitiatedFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionTerminated(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionTerminated(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionHeld(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionHeld(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionHoldFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionHoldFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionHoldReceived(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionHoldReceived(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionResumed(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionResumed(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionResumeFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionResumeFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionResumeReceived(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionResumeReceived(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionMergeStarted(IImsCallSession newSession, ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeStrongBinder(newSession != null ? newSession.asBinder() : null);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionMergeStarted(newSession, profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionMergeComplete(IImsCallSession session) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeStrongBinder(session != null ? session.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionMergeComplete(session);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionMergeFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionMergeFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionUpdated(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionUpdated(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionUpdateFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionUpdateFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionUpdateReceived(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionUpdateReceived(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionConferenceExtended(IImsCallSession newSession, ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeStrongBinder(newSession != null ? newSession.asBinder() : null);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionConferenceExtended(newSession, profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionConferenceExtendFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionConferenceExtendFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionConferenceExtendReceived(IImsCallSession newSession, ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeStrongBinder(newSession != null ? newSession.asBinder() : null);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionConferenceExtendReceived(newSession, profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionInviteParticipantsRequestDelivered() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionInviteParticipantsRequestDelivered();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionInviteParticipantsRequestFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionInviteParticipantsRequestFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRemoveParticipantsRequestDelivered() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRemoveParticipantsRequestDelivered();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRemoveParticipantsRequestFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRemoveParticipantsRequestFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionConferenceStateUpdated(ImsConferenceState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionConferenceStateUpdated(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionUssdMessageReceived(int mode, String ussdMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(mode);
                    _data.writeString(ussdMessage);
                    boolean _status = this.mRemote.transact(27, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionUssdMessageReceived(mode, ussdMessage);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionHandover(int srcNetworkType, int targetNetworkType, ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(srcNetworkType);
                    _data.writeInt(targetNetworkType);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionHandover(srcNetworkType, targetNetworkType, reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionHandoverFailed(int srcNetworkType, int targetNetworkType, ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(srcNetworkType);
                    _data.writeInt(targetNetworkType);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(29, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionHandoverFailed(srcNetworkType, targetNetworkType, reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionMayHandover(int srcNetworkType, int targetNetworkType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(srcNetworkType);
                    _data.writeInt(targetNetworkType);
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionMayHandover(srcNetworkType, targetNetworkType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionTtyModeReceived(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(31, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionTtyModeReceived(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionMultipartyStateChanged(boolean isMultiParty) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(isMultiParty ? 1 : 0);
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionMultipartyStateChanged(isMultiParty);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionSuppServiceReceived(ImsSuppServiceNotification suppSrvNotification) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (suppSrvNotification != null) {
                        _data.writeInt(1);
                        suppSrvNotification.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionSuppServiceReceived(suppSrvNotification);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRttModifyRequestReceived(ImsCallProfile callProfile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (callProfile != null) {
                        _data.writeInt(1);
                        callProfile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRttModifyRequestReceived(callProfile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRttModifyResponseReceived(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(35, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRttModifyResponseReceived(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRttMessageReceived(String rttMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeString(rttMessage);
                    boolean _status = this.mRemote.transact(36, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRttMessageReceived(rttMessage);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRttAudioIndicatorChanged(ImsStreamMediaProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRttAudioIndicatorChanged(profile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionTransferred() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionTransferred();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionTransferFailed(ImsReasonInfo reasonInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (reasonInfo != null) {
                        _data.writeInt(1);
                        reasonInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionTransferFailed(reasonInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionDtmfReceived(char dtmf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeInt(dtmf);
                    boolean _status = this.mRemote.transact(40, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionDtmfReceived(dtmf);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callQualityChanged(CallQuality callQuality) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    if (callQuality != null) {
                        _data.writeInt(1);
                        callQuality.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(41, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callQualityChanged(callQuality);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCallSessionListener
            public void callSessionRtpHeaderExtensionsReceived(List<RtpHeaderExtension> extensions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsCallSessionListener.DESCRIPTOR);
                    _data.writeTypedList(extensions);
                    boolean _status = this.mRemote.transact(42, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callSessionRtpHeaderExtensionsReceived(extensions);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsCallSessionListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsCallSessionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
