package android.hardware.face;

import android.hardware.biometrics.IBiometricSensorReceiver;
import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IInvalidationCallback;
import android.hardware.biometrics.ITestSession;
import android.hardware.biometrics.ITestSessionCallback;
import android.hardware.face.IFaceServiceReceiver;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import java.util.List;
/* loaded from: classes.dex */
public interface IFaceService extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.face.IFaceService";

    void addLockoutResetCallback(IBiometricServiceLockoutResetCallback iBiometricServiceLockoutResetCallback, String str) throws RemoteException;

    void authenticate(IBinder iBinder, long j, int i, IFaceServiceReceiver iFaceServiceReceiver, String str, boolean z) throws RemoteException;

    void cancelAuthentication(IBinder iBinder, String str) throws RemoteException;

    void cancelAuthenticationFromService(int i, IBinder iBinder, String str) throws RemoteException;

    void cancelEnrollment(IBinder iBinder) throws RemoteException;

    void cancelFaceDetect(IBinder iBinder, String str) throws RemoteException;

    ITestSession createTestSession(int i, ITestSessionCallback iTestSessionCallback, String str) throws RemoteException;

    void detectFace(IBinder iBinder, int i, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    byte[] dumpSensorServiceStateProto(int i, boolean z) throws RemoteException;

    void enroll(int i, IBinder iBinder, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str, int[] iArr, Surface surface, boolean z) throws RemoteException;

    void enrollRemotely(int i, IBinder iBinder, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str, int[] iArr) throws RemoteException;

    void generateChallenge(IBinder iBinder, int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    long getAuthenticatorId(int i, int i2) throws RemoteException;

    List<Face> getEnrolledFaces(int i, int i2, String str) throws RemoteException;

    void getFeature(IBinder iBinder, int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    int getLockoutModeForUser(int i, int i2) throws RemoteException;

    FaceSensorPropertiesInternal getSensorProperties(int i, String str) throws RemoteException;

    List<FaceSensorPropertiesInternal> getSensorPropertiesInternal(String str) throws RemoteException;

    boolean hasEnrolledFaces(int i, int i2, String str) throws RemoteException;

    void invalidateAuthenticatorId(int i, int i2, IInvalidationCallback iInvalidationCallback) throws RemoteException;

    boolean isHardwareDetected(int i, String str) throws RemoteException;

    void prepareForAuthentication(int i, boolean z, IBinder iBinder, long j, int i2, IBiometricSensorReceiver iBiometricSensorReceiver, String str, int i3, boolean z2) throws RemoteException;

    void registerAuthenticators(List<FaceSensorPropertiesInternal> list) throws RemoteException;

    void remove(IBinder iBinder, int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    void removeAll(IBinder iBinder, int i, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    void resetLockout(IBinder iBinder, int i, int i2, byte[] bArr, String str) throws RemoteException;

    void revokeChallenge(IBinder iBinder, int i, int i2, String str, long j) throws RemoteException;

    void setFeature(IBinder iBinder, int i, int i2, boolean z, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    void startPreparedClient(int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IFaceService {
        @Override // android.hardware.face.IFaceService
        public ITestSession createTestSession(int sensorId, ITestSessionCallback callback, String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public byte[] dumpSensorServiceStateProto(int sensorId, boolean clearSchedulerBuffer) throws RemoteException {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public List<FaceSensorPropertiesInternal> getSensorPropertiesInternal(String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public FaceSensorPropertiesInternal getSensorProperties(int sensorId, String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public void authenticate(IBinder token, long operationId, int userId, IFaceServiceReceiver receiver, String opPackageName, boolean isKeyguardBypassEnabled) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void detectFace(IBinder token, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void prepareForAuthentication(int sensorId, boolean requireConfirmation, IBinder token, long operationId, int userId, IBiometricSensorReceiver sensorReceiver, String opPackageName, int cookie, boolean allowBackgroundAuthentication) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void startPreparedClient(int sensorId, int cookie) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelAuthentication(IBinder token, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelFaceDetect(IBinder token, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelAuthenticationFromService(int sensorId, IBinder token, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void enroll(int userId, IBinder token, byte[] hardwareAuthToken, IFaceServiceReceiver receiver, String opPackageName, int[] disabledFeatures, Surface previewSurface, boolean debugConsent) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void enrollRemotely(int userId, IBinder token, byte[] hardwareAuthToken, IFaceServiceReceiver receiver, String opPackageName, int[] disabledFeatures) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelEnrollment(IBinder token) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void remove(IBinder token, int faceId, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void removeAll(IBinder token, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public List<Face> getEnrolledFaces(int sensorId, int userId, String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public boolean isHardwareDetected(int sensorId, String opPackageName) throws RemoteException {
            return false;
        }

        @Override // android.hardware.face.IFaceService
        public void generateChallenge(IBinder token, int sensorId, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void revokeChallenge(IBinder token, int sensorId, int userId, String opPackageName, long challenge) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public boolean hasEnrolledFaces(int sensorId, int userId, String opPackageName) throws RemoteException {
            return false;
        }

        @Override // android.hardware.face.IFaceService
        public int getLockoutModeForUser(int sensorId, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.face.IFaceService
        public void invalidateAuthenticatorId(int sensorId, int userId, IInvalidationCallback callback) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public long getAuthenticatorId(int sensorId, int callingUserId) throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.face.IFaceService
        public void resetLockout(IBinder token, int sensorId, int userId, byte[] hardwareAuthToken, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback callback, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void setFeature(IBinder token, int userId, int feature, boolean enabled, byte[] hardwareAuthToken, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void getFeature(IBinder token, int userId, int feature, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void registerAuthenticators(List<FaceSensorPropertiesInternal> hidlSensors) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IFaceService {
        static final int TRANSACTION_addLockoutResetCallback = 26;
        static final int TRANSACTION_authenticate = 5;
        static final int TRANSACTION_cancelAuthentication = 9;
        static final int TRANSACTION_cancelAuthenticationFromService = 11;
        static final int TRANSACTION_cancelEnrollment = 14;
        static final int TRANSACTION_cancelFaceDetect = 10;
        static final int TRANSACTION_createTestSession = 1;
        static final int TRANSACTION_detectFace = 6;
        static final int TRANSACTION_dumpSensorServiceStateProto = 2;
        static final int TRANSACTION_enroll = 12;
        static final int TRANSACTION_enrollRemotely = 13;
        static final int TRANSACTION_generateChallenge = 19;
        static final int TRANSACTION_getAuthenticatorId = 24;
        static final int TRANSACTION_getEnrolledFaces = 17;
        static final int TRANSACTION_getFeature = 28;
        static final int TRANSACTION_getLockoutModeForUser = 22;
        static final int TRANSACTION_getSensorProperties = 4;
        static final int TRANSACTION_getSensorPropertiesInternal = 3;
        static final int TRANSACTION_hasEnrolledFaces = 21;
        static final int TRANSACTION_invalidateAuthenticatorId = 23;
        static final int TRANSACTION_isHardwareDetected = 18;
        static final int TRANSACTION_prepareForAuthentication = 7;
        static final int TRANSACTION_registerAuthenticators = 29;
        static final int TRANSACTION_remove = 15;
        static final int TRANSACTION_removeAll = 16;
        static final int TRANSACTION_resetLockout = 25;
        static final int TRANSACTION_revokeChallenge = 20;
        static final int TRANSACTION_setFeature = 27;
        static final int TRANSACTION_startPreparedClient = 8;

        public Stub() {
            attachInterface(this, IFaceService.DESCRIPTOR);
        }

        public static IFaceService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IFaceService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IFaceService)) {
                return new Proxy(obj);
            }
            return (IFaceService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createTestSession";
                case 2:
                    return "dumpSensorServiceStateProto";
                case 3:
                    return "getSensorPropertiesInternal";
                case 4:
                    return "getSensorProperties";
                case 5:
                    return "authenticate";
                case 6:
                    return "detectFace";
                case 7:
                    return "prepareForAuthentication";
                case 8:
                    return "startPreparedClient";
                case 9:
                    return "cancelAuthentication";
                case 10:
                    return "cancelFaceDetect";
                case 11:
                    return "cancelAuthenticationFromService";
                case 12:
                    return "enroll";
                case 13:
                    return "enrollRemotely";
                case 14:
                    return "cancelEnrollment";
                case 15:
                    return "remove";
                case 16:
                    return "removeAll";
                case 17:
                    return "getEnrolledFaces";
                case 18:
                    return "isHardwareDetected";
                case 19:
                    return "generateChallenge";
                case 20:
                    return "revokeChallenge";
                case 21:
                    return "hasEnrolledFaces";
                case 22:
                    return "getLockoutModeForUser";
                case 23:
                    return "invalidateAuthenticatorId";
                case 24:
                    return "getAuthenticatorId";
                case 25:
                    return "resetLockout";
                case 26:
                    return "addLockoutResetCallback";
                case 27:
                    return "setFeature";
                case 28:
                    return "getFeature";
                case 29:
                    return "registerAuthenticators";
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
            boolean _arg5;
            boolean _arg1;
            boolean _arg8;
            Surface _arg6;
            boolean _arg7;
            boolean _arg3;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IFaceService.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg12 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            ITestSessionCallback _arg13 = ITestSessionCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg2 = data.readString();
                            ITestSession _result = createTestSession(_arg0, _arg13, _arg2);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        case 2:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            }
                            byte[] _result2 = dumpSensorServiceStateProto(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeByteArray(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            List<FaceSensorPropertiesInternal> _result3 = getSensorPropertiesInternal(_arg03);
                            reply.writeNoException();
                            reply.writeTypedList(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            String _arg14 = data.readString();
                            FaceSensorPropertiesInternal _result4 = getSensorProperties(_arg04, _arg14);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg05 = data.readStrongBinder();
                            long _arg15 = data.readLong();
                            int _arg22 = data.readInt();
                            IFaceServiceReceiver _arg32 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg4 = data.readString();
                            if (data.readInt() != 0) {
                                _arg5 = true;
                            } else {
                                _arg5 = false;
                            }
                            authenticate(_arg05, _arg15, _arg22, _arg32, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg06 = data.readStrongBinder();
                            int _arg16 = data.readInt();
                            IFaceServiceReceiver _arg23 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg33 = data.readString();
                            detectFace(_arg06, _arg16, _arg23, _arg33);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            } else {
                                _arg1 = false;
                            }
                            IBinder _arg24 = data.readStrongBinder();
                            long _arg34 = data.readLong();
                            int _arg42 = data.readInt();
                            IBiometricSensorReceiver _arg52 = IBiometricSensorReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg62 = data.readString();
                            int _arg72 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg8 = true;
                            } else {
                                _arg8 = false;
                            }
                            prepareForAuthentication(_arg07, _arg1, _arg24, _arg34, _arg42, _arg52, _arg62, _arg72, _arg8);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg17 = data.readInt();
                            startPreparedClient(_arg08, _arg17);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg09 = data.readStrongBinder();
                            String _arg18 = data.readString();
                            cancelAuthentication(_arg09, _arg18);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg010 = data.readStrongBinder();
                            String _arg19 = data.readString();
                            cancelFaceDetect(_arg010, _arg19);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            IBinder _arg110 = data.readStrongBinder();
                            String _arg25 = data.readString();
                            cancelAuthenticationFromService(_arg011, _arg110, _arg25);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            IBinder _arg111 = data.readStrongBinder();
                            byte[] _arg26 = data.createByteArray();
                            IFaceServiceReceiver _arg35 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg43 = data.readString();
                            int[] _arg53 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg6 = Surface.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg7 = true;
                            } else {
                                _arg7 = false;
                            }
                            enroll(_arg012, _arg111, _arg26, _arg35, _arg43, _arg53, _arg6, _arg7);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            IBinder _arg112 = data.readStrongBinder();
                            byte[] _arg27 = data.createByteArray();
                            IFaceServiceReceiver _arg36 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg44 = data.readString();
                            int[] _arg54 = data.createIntArray();
                            enrollRemotely(_arg013, _arg112, _arg27, _arg36, _arg44, _arg54);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg014 = data.readStrongBinder();
                            cancelEnrollment(_arg014);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg015 = data.readStrongBinder();
                            int _arg113 = data.readInt();
                            int _arg28 = data.readInt();
                            IFaceServiceReceiver _arg37 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg45 = data.readString();
                            remove(_arg015, _arg113, _arg28, _arg37, _arg45);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg016 = data.readStrongBinder();
                            int _arg114 = data.readInt();
                            IFaceServiceReceiver _arg29 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg38 = data.readString();
                            removeAll(_arg016, _arg114, _arg29, _arg38);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            int _arg115 = data.readInt();
                            String _arg210 = data.readString();
                            List<Face> _result5 = getEnrolledFaces(_arg017, _arg115, _arg210);
                            reply.writeNoException();
                            reply.writeTypedList(_result5);
                            return true;
                        case 18:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            String _arg116 = data.readString();
                            boolean isHardwareDetected = isHardwareDetected(_arg018, _arg116);
                            reply.writeNoException();
                            reply.writeInt(isHardwareDetected ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg019 = data.readStrongBinder();
                            int _arg117 = data.readInt();
                            int _arg211 = data.readInt();
                            IFaceServiceReceiver _arg39 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg46 = data.readString();
                            generateChallenge(_arg019, _arg117, _arg211, _arg39, _arg46);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg020 = data.readStrongBinder();
                            int _arg118 = data.readInt();
                            int _arg212 = data.readInt();
                            String _arg310 = data.readString();
                            long _arg47 = data.readLong();
                            revokeChallenge(_arg020, _arg118, _arg212, _arg310, _arg47);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg021 = data.readInt();
                            int _arg119 = data.readInt();
                            String _arg213 = data.readString();
                            boolean hasEnrolledFaces = hasEnrolledFaces(_arg021, _arg119, _arg213);
                            reply.writeNoException();
                            reply.writeInt(hasEnrolledFaces ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg022 = data.readInt();
                            int _arg120 = data.readInt();
                            int _result6 = getLockoutModeForUser(_arg022, _arg120);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 23:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg023 = data.readInt();
                            int _arg121 = data.readInt();
                            IInvalidationCallback _arg214 = IInvalidationCallback.Stub.asInterface(data.readStrongBinder());
                            invalidateAuthenticatorId(_arg023, _arg121, _arg214);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            int _arg024 = data.readInt();
                            int _arg122 = data.readInt();
                            long _result7 = getAuthenticatorId(_arg024, _arg122);
                            reply.writeNoException();
                            reply.writeLong(_result7);
                            return true;
                        case 25:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg025 = data.readStrongBinder();
                            int _arg123 = data.readInt();
                            int _arg215 = data.readInt();
                            byte[] _arg311 = data.createByteArray();
                            String _arg48 = data.readString();
                            resetLockout(_arg025, _arg123, _arg215, _arg311, _arg48);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBiometricServiceLockoutResetCallback _arg026 = IBiometricServiceLockoutResetCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg124 = data.readString();
                            addLockoutResetCallback(_arg026, _arg124);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg027 = data.readStrongBinder();
                            int _arg125 = data.readInt();
                            int _arg216 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            byte[] _arg49 = data.createByteArray();
                            IFaceServiceReceiver _arg55 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg63 = data.readString();
                            setFeature(_arg027, _arg125, _arg216, _arg3, _arg49, _arg55, _arg63);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            IBinder _arg028 = data.readStrongBinder();
                            int _arg126 = data.readInt();
                            int _arg217 = data.readInt();
                            IFaceServiceReceiver _arg312 = IFaceServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg410 = data.readString();
                            getFeature(_arg028, _arg126, _arg217, _arg312, _arg410);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(IFaceService.DESCRIPTOR);
                            List<FaceSensorPropertiesInternal> _arg029 = data.createTypedArrayList(FaceSensorPropertiesInternal.CREATOR);
                            registerAuthenticators(_arg029);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IFaceService {
            public static IFaceService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IFaceService.DESCRIPTOR;
            }

            @Override // android.hardware.face.IFaceService
            public ITestSession createTestSession(int sensorId, ITestSessionCallback callback, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTestSession(sensorId, callback, opPackageName);
                    }
                    _reply.readException();
                    ITestSession _result = ITestSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public byte[] dumpSensorServiceStateProto(int sensorId, boolean clearSchedulerBuffer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(clearSchedulerBuffer ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpSensorServiceStateProto(sensorId, clearSchedulerBuffer);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public List<FaceSensorPropertiesInternal> getSensorPropertiesInternal(String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorPropertiesInternal(opPackageName);
                    }
                    _reply.readException();
                    List<FaceSensorPropertiesInternal> _result = _reply.createTypedArrayList(FaceSensorPropertiesInternal.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public FaceSensorPropertiesInternal getSensorProperties(int sensorId, String opPackageName) throws RemoteException {
                FaceSensorPropertiesInternal _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorProperties(sensorId, opPackageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = FaceSensorPropertiesInternal.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void authenticate(IBinder token, long operationId, int userId, IFaceServiceReceiver receiver, String opPackageName, boolean isKeyguardBypassEnabled) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(token);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeLong(operationId);
                    try {
                        _data.writeInt(userId);
                        _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                        try {
                            _data.writeString(opPackageName);
                            _data.writeInt(isKeyguardBypassEnabled ? 1 : 0);
                            boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().authenticate(token, operationId, userId, receiver, opPackageName, isKeyguardBypassEnabled);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void detectFace(IBinder token, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().detectFace(token, userId, receiver, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void prepareForAuthentication(int sensorId, boolean requireConfirmation, IBinder token, long operationId, int userId, IBiometricSensorReceiver sensorReceiver, String opPackageName, int cookie, boolean allowBackgroundAuthentication) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    try {
                        _data.writeInt(sensorId);
                        int i = 1;
                        _data.writeInt(requireConfirmation ? 1 : 0);
                        try {
                            _data.writeStrongBinder(token);
                            _data.writeLong(operationId);
                            _data.writeInt(userId);
                            _data.writeStrongBinder(sensorReceiver != null ? sensorReceiver.asBinder() : null);
                            _data.writeString(opPackageName);
                            _data.writeInt(cookie);
                            if (!allowBackgroundAuthentication) {
                                i = 0;
                            }
                            _data.writeInt(i);
                            boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().prepareForAuthentication(sensorId, requireConfirmation, token, operationId, userId, sensorReceiver, opPackageName, cookie, allowBackgroundAuthentication);
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void startPreparedClient(int sensorId, int cookie) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(cookie);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startPreparedClient(sensorId, cookie);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelAuthentication(IBinder token, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelAuthentication(token, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelFaceDetect(IBinder token, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelFaceDetect(token, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelAuthenticationFromService(int sensorId, IBinder token, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelAuthenticationFromService(sensorId, token, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void enroll(int userId, IBinder token, byte[] hardwareAuthToken, IFaceServiceReceiver receiver, String opPackageName, int[] disabledFeatures, Surface previewSurface, boolean debugConsent) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    try {
                        _data.writeInt(userId);
                        try {
                            _data.writeStrongBinder(token);
                            try {
                                _data.writeByteArray(hardwareAuthToken);
                                _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                                _data.writeString(opPackageName);
                                _data.writeIntArray(disabledFeatures);
                                int i = 1;
                                if (previewSurface != null) {
                                    _data.writeInt(1);
                                    previewSurface.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                if (!debugConsent) {
                                    i = 0;
                                }
                                _data.writeInt(i);
                                boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().enroll(userId, token, hardwareAuthToken, receiver, opPackageName, disabledFeatures, previewSurface, debugConsent);
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
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void enrollRemotely(int userId, IBinder token, byte[] hardwareAuthToken, IFaceServiceReceiver receiver, String opPackageName, int[] disabledFeatures) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    try {
                        _data.writeInt(userId);
                        try {
                            _data.writeStrongBinder(token);
                            try {
                                _data.writeByteArray(hardwareAuthToken);
                                _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(opPackageName);
                        try {
                            _data.writeIntArray(disabledFeatures);
                            try {
                                boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().enrollRemotely(userId, token, hardwareAuthToken, receiver, opPackageName, disabledFeatures);
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th5) {
                                th = th5;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelEnrollment(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelEnrollment(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void remove(IBinder token, int faceId, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(faceId);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().remove(token, faceId, userId, receiver, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void removeAll(IBinder token, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeAll(token, userId, receiver, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public List<Face> getEnrolledFaces(int sensorId, int userId, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnrolledFaces(sensorId, userId, opPackageName);
                    }
                    _reply.readException();
                    List<Face> _result = _reply.createTypedArrayList(Face.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public boolean isHardwareDetected(int sensorId, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeString(opPackageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHardwareDetected(sensorId, opPackageName);
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

            @Override // android.hardware.face.IFaceService
            public void generateChallenge(IBinder token, int sensorId, int userId, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().generateChallenge(token, sensorId, userId, receiver, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void revokeChallenge(IBinder token, int sensorId, int userId, String opPackageName, long challenge) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(token);
                        try {
                            _data.writeInt(sensorId);
                            try {
                                _data.writeInt(userId);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
                try {
                    _data.writeString(opPackageName);
                    try {
                        _data.writeLong(challenge);
                        boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().revokeChallenge(token, sensorId, userId, opPackageName, challenge);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.face.IFaceService
            public boolean hasEnrolledFaces(int sensorId, int userId, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasEnrolledFaces(sensorId, userId, opPackageName);
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

            @Override // android.hardware.face.IFaceService
            public int getLockoutModeForUser(int sensorId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLockoutModeForUser(sensorId, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void invalidateAuthenticatorId(int sensorId, int userId, IInvalidationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateAuthenticatorId(sensorId, userId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public long getAuthenticatorId(int sensorId, int callingUserId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(callingUserId);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticatorId(sensorId, callingUserId);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void resetLockout(IBinder token, int sensorId, int userId, byte[] hardwareAuthToken, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeByteArray(hardwareAuthToken);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetLockout(token, sensorId, userId, hardwareAuthToken, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback callback, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addLockoutResetCallback(callback, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void setFeature(IBinder token, int userId, int feature, boolean enabled, byte[] hardwareAuthToken, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(token);
                        try {
                            _data.writeInt(userId);
                            try {
                                _data.writeInt(feature);
                                _data.writeInt(enabled ? 1 : 0);
                                try {
                                    _data.writeByteArray(hardwareAuthToken);
                                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                                    try {
                                        _data.writeString(opPackageName);
                                        boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            _reply.recycle();
                                            _data.recycle();
                                            return;
                                        }
                                        Stub.getDefaultImpl().setFeature(token, userId, feature, enabled, hardwareAuthToken, receiver, opPackageName);
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
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void getFeature(IBinder token, int userId, int feature, IFaceServiceReceiver receiver, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    _data.writeInt(feature);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getFeature(token, userId, feature, receiver, opPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void registerAuthenticators(List<FaceSensorPropertiesInternal> hidlSensors) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceService.DESCRIPTOR);
                    _data.writeTypedList(hidlSensors);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerAuthenticators(hidlSensors);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFaceService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IFaceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
