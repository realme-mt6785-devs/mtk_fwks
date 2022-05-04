package android.hardware.biometrics;

import android.hardware.biometrics.IBiometricAuthenticator;
import android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback;
import android.hardware.biometrics.IBiometricServiceReceiver;
import android.hardware.biometrics.IInvalidationCallback;
import android.hardware.biometrics.ITestSession;
import android.hardware.biometrics.ITestSessionCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IBiometricService extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricService";

    void authenticate(IBinder iBinder, long j, int i, IBiometricServiceReceiver iBiometricServiceReceiver, String str, PromptInfo promptInfo) throws RemoteException;

    int canAuthenticate(String str, int i, int i2, int i3) throws RemoteException;

    void cancelAuthentication(IBinder iBinder, String str) throws RemoteException;

    ITestSession createTestSession(int i, ITestSessionCallback iTestSessionCallback, String str) throws RemoteException;

    long[] getAuthenticatorIds(int i) throws RemoteException;

    int getCurrentModality(String str, int i, int i2, int i3) throws RemoteException;

    int getCurrentStrength(int i) throws RemoteException;

    List<SensorPropertiesInternal> getSensorProperties(String str) throws RemoteException;

    int getSupportedModalities(int i) throws RemoteException;

    boolean hasEnrolledBiometrics(int i, String str) throws RemoteException;

    void invalidateAuthenticatorIds(int i, int i2, IInvalidationCallback iInvalidationCallback) throws RemoteException;

    void onReadyForAuthentication(int i) throws RemoteException;

    void registerAuthenticator(int i, int i2, int i3, IBiometricAuthenticator iBiometricAuthenticator) throws RemoteException;

    void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback, int i) throws RemoteException;

    void resetLockoutTimeBound(IBinder iBinder, String str, int i, int i2, byte[] bArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBiometricService {
        @Override // android.hardware.biometrics.IBiometricService
        public ITestSession createTestSession(int sensorId, ITestSessionCallback callback, String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public List<SensorPropertiesInternal> getSensorProperties(String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void authenticate(IBinder token, long operationId, int userId, IBiometricServiceReceiver receiver, String opPackageName, PromptInfo promptInfo) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void cancelAuthentication(IBinder token, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public int canAuthenticate(String opPackageName, int userId, int callingUserId, int authenticators) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public boolean hasEnrolledBiometrics(int userId, String opPackageName) throws RemoteException {
            return false;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void registerAuthenticator(int id, int modality, int strength, IBiometricAuthenticator authenticator) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback callback, int callingUserId) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void onReadyForAuthentication(int cookie) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void invalidateAuthenticatorIds(int userId, int fromSensorId, IInvalidationCallback callback) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public long[] getAuthenticatorIds(int callingUserId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void resetLockoutTimeBound(IBinder token, String opPackageName, int fromSensorId, int userId, byte[] hardwareAuthToken) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public int getCurrentStrength(int sensorId) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public int getCurrentModality(String opPackageName, int userId, int callingUserId, int authenticators) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public int getSupportedModalities(int authenticators) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBiometricService {
        static final int TRANSACTION_authenticate = 3;
        static final int TRANSACTION_canAuthenticate = 5;
        static final int TRANSACTION_cancelAuthentication = 4;
        static final int TRANSACTION_createTestSession = 1;
        static final int TRANSACTION_getAuthenticatorIds = 11;
        static final int TRANSACTION_getCurrentModality = 14;
        static final int TRANSACTION_getCurrentStrength = 13;
        static final int TRANSACTION_getSensorProperties = 2;
        static final int TRANSACTION_getSupportedModalities = 15;
        static final int TRANSACTION_hasEnrolledBiometrics = 6;
        static final int TRANSACTION_invalidateAuthenticatorIds = 10;
        static final int TRANSACTION_onReadyForAuthentication = 9;
        static final int TRANSACTION_registerAuthenticator = 7;
        static final int TRANSACTION_registerEnabledOnKeyguardCallback = 8;
        static final int TRANSACTION_resetLockoutTimeBound = 12;

        public Stub() {
            attachInterface(this, IBiometricService.DESCRIPTOR);
        }

        public static IBiometricService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBiometricService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBiometricService)) {
                return new Proxy(obj);
            }
            return (IBiometricService) iin;
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
                    return "getSensorProperties";
                case 3:
                    return "authenticate";
                case 4:
                    return "cancelAuthentication";
                case 5:
                    return "canAuthenticate";
                case 6:
                    return "hasEnrolledBiometrics";
                case 7:
                    return "registerAuthenticator";
                case 8:
                    return "registerEnabledOnKeyguardCallback";
                case 9:
                    return "onReadyForAuthentication";
                case 10:
                    return "invalidateAuthenticatorIds";
                case 11:
                    return "getAuthenticatorIds";
                case 12:
                    return "resetLockoutTimeBound";
                case 13:
                    return "getCurrentStrength";
                case 14:
                    return "getCurrentModality";
                case 15:
                    return "getSupportedModalities";
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
            PromptInfo _arg5;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBiometricService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            ITestSessionCallback _arg1 = ITestSessionCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg2 = data.readString();
                            ITestSession _result = createTestSession(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        case 2:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            List<SensorPropertiesInternal> _result2 = getSensorProperties(_arg02);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            long _arg12 = data.readLong();
                            int _arg22 = data.readInt();
                            IBiometricServiceReceiver _arg3 = IBiometricServiceReceiver.Stub.asInterface(data.readStrongBinder());
                            String _arg4 = data.readString();
                            if (data.readInt() != 0) {
                                _arg5 = PromptInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            authenticate(_arg03, _arg12, _arg22, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            String _arg13 = data.readString();
                            cancelAuthentication(_arg04, _arg13);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg14 = data.readInt();
                            int _arg23 = data.readInt();
                            int _arg32 = data.readInt();
                            int _result3 = canAuthenticate(_arg05, _arg14, _arg23, _arg32);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _arg15 = data.readString();
                            boolean hasEnrolledBiometrics = hasEnrolledBiometrics(_arg06, _arg15);
                            reply.writeNoException();
                            reply.writeInt(hasEnrolledBiometrics ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg16 = data.readInt();
                            int _arg24 = data.readInt();
                            IBiometricAuthenticator _arg33 = IBiometricAuthenticator.Stub.asInterface(data.readStrongBinder());
                            registerAuthenticator(_arg07, _arg16, _arg24, _arg33);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            IBiometricEnabledOnKeyguardCallback _arg08 = IBiometricEnabledOnKeyguardCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg17 = data.readInt();
                            registerEnabledOnKeyguardCallback(_arg08, _arg17);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            onReadyForAuthentication(_arg09);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg18 = data.readInt();
                            IInvalidationCallback _arg25 = IInvalidationCallback.Stub.asInterface(data.readStrongBinder());
                            invalidateAuthenticatorIds(_arg010, _arg18, _arg25);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            long[] _result4 = getAuthenticatorIds(_arg011);
                            reply.writeNoException();
                            reply.writeLongArray(_result4);
                            return true;
                        case 12:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            IBinder _arg012 = data.readStrongBinder();
                            String _arg19 = data.readString();
                            int _arg26 = data.readInt();
                            int _arg34 = data.readInt();
                            byte[] _arg42 = data.createByteArray();
                            resetLockoutTimeBound(_arg012, _arg19, _arg26, _arg34, _arg42);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _result5 = getCurrentStrength(_arg013);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 14:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            String _arg014 = data.readString();
                            int _arg110 = data.readInt();
                            int _arg27 = data.readInt();
                            int _arg35 = data.readInt();
                            int _result6 = getCurrentModality(_arg014, _arg110, _arg27, _arg35);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 15:
                            data.enforceInterface(IBiometricService.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _result7 = getSupportedModalities(_arg015);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBiometricService {
            public static IBiometricService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBiometricService.DESCRIPTOR;
            }

            @Override // android.hardware.biometrics.IBiometricService
            public ITestSession createTestSession(int sensorId, ITestSessionCallback callback, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
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

            @Override // android.hardware.biometrics.IBiometricService
            public List<SensorPropertiesInternal> getSensorProperties(String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorProperties(opPackageName);
                    }
                    _reply.readException();
                    List<SensorPropertiesInternal> _result = _reply.createTypedArrayList(SensorPropertiesInternal.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void authenticate(IBinder token, long operationId, int userId, IBiometricServiceReceiver receiver, String opPackageName, PromptInfo promptInfo) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(token);
                        try {
                            _data.writeLong(operationId);
                            try {
                                _data.writeInt(userId);
                                _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                                _data.writeString(opPackageName);
                                if (promptInfo != null) {
                                    _data.writeInt(1);
                                    promptInfo.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().authenticate(token, operationId, userId, receiver, opPackageName, promptInfo);
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

            @Override // android.hardware.biometrics.IBiometricService
            public void cancelAuthentication(IBinder token, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // android.hardware.biometrics.IBiometricService
            public int canAuthenticate(String opPackageName, int userId, int callingUserId, int authenticators) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeString(opPackageName);
                    _data.writeInt(userId);
                    _data.writeInt(callingUserId);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAuthenticate(opPackageName, userId, callingUserId, authenticators);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public boolean hasEnrolledBiometrics(int userId, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasEnrolledBiometrics(userId, opPackageName);
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

            @Override // android.hardware.biometrics.IBiometricService
            public void registerAuthenticator(int id, int modality, int strength, IBiometricAuthenticator authenticator) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(id);
                    _data.writeInt(modality);
                    _data.writeInt(strength);
                    _data.writeStrongBinder(authenticator != null ? authenticator.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerAuthenticator(id, modality, strength, authenticator);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback callback, int callingUserId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(callingUserId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(callback, callingUserId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void onReadyForAuthentication(int cookie) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(cookie);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onReadyForAuthentication(cookie);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void invalidateAuthenticatorIds(int userId, int fromSensorId, IInvalidationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(fromSensorId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateAuthenticatorIds(userId, fromSensorId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public long[] getAuthenticatorIds(int callingUserId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(callingUserId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticatorIds(callingUserId);
                    }
                    _reply.readException();
                    long[] _result = _reply.createLongArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void resetLockoutTimeBound(IBinder token, String opPackageName, int fromSensorId, int userId, byte[] hardwareAuthToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    _data.writeInt(fromSensorId);
                    _data.writeInt(userId);
                    _data.writeByteArray(hardwareAuthToken);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetLockoutTimeBound(token, opPackageName, fromSensorId, userId, hardwareAuthToken);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public int getCurrentStrength(int sensorId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentStrength(sensorId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public int getCurrentModality(String opPackageName, int userId, int callingUserId, int authenticators) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeString(opPackageName);
                    _data.writeInt(userId);
                    _data.writeInt(callingUserId);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentModality(opPackageName, userId, callingUserId, authenticators);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public int getSupportedModalities(int authenticators) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricService.DESCRIPTOR);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedModalities(authenticators);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBiometricService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBiometricService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
