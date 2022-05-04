package android.hardware.biometrics;

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
import android.text.TextUtils;
import java.util.List;
/* loaded from: classes.dex */
public interface IAuthService extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.biometrics.IAuthService";

    void authenticate(IBinder iBinder, long j, int i, IBiometricServiceReceiver iBiometricServiceReceiver, String str, PromptInfo promptInfo) throws RemoteException;

    int canAuthenticate(String str, int i, int i2) throws RemoteException;

    void cancelAuthentication(IBinder iBinder, String str) throws RemoteException;

    ITestSession createTestSession(int i, ITestSessionCallback iTestSessionCallback, String str) throws RemoteException;

    long[] getAuthenticatorIds(int i) throws RemoteException;

    CharSequence getButtonLabel(int i, String str, int i2) throws RemoteException;

    CharSequence getPromptMessage(int i, String str, int i2) throws RemoteException;

    List<SensorPropertiesInternal> getSensorProperties(String str) throws RemoteException;

    CharSequence getSettingName(int i, String str, int i2) throws RemoteException;

    String getUiPackage() throws RemoteException;

    boolean hasEnrolledBiometrics(int i, String str) throws RemoteException;

    void invalidateAuthenticatorIds(int i, int i2, IInvalidationCallback iInvalidationCallback) throws RemoteException;

    void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback) throws RemoteException;

    void resetLockoutTimeBound(IBinder iBinder, String str, int i, int i2, byte[] bArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAuthService {
        @Override // android.hardware.biometrics.IAuthService
        public ITestSession createTestSession(int sensorId, ITestSessionCallback callback, String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IAuthService
        public List<SensorPropertiesInternal> getSensorProperties(String opPackageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IAuthService
        public String getUiPackage() throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IAuthService
        public void authenticate(IBinder token, long sessionId, int userId, IBiometricServiceReceiver receiver, String opPackageName, PromptInfo promptInfo) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IAuthService
        public void cancelAuthentication(IBinder token, String opPackageName) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IAuthService
        public int canAuthenticate(String opPackageName, int userId, int authenticators) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.biometrics.IAuthService
        public boolean hasEnrolledBiometrics(int userId, String opPackageName) throws RemoteException {
            return false;
        }

        @Override // android.hardware.biometrics.IAuthService
        public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback callback) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IAuthService
        public void invalidateAuthenticatorIds(int userId, int fromSensorId, IInvalidationCallback callback) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IAuthService
        public long[] getAuthenticatorIds(int userId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IAuthService
        public void resetLockoutTimeBound(IBinder token, String opPackageName, int fromSensorId, int userId, byte[] hardwareAuthToken) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IAuthService
        public CharSequence getButtonLabel(int userId, String opPackageName, int authenticators) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IAuthService
        public CharSequence getPromptMessage(int userId, String opPackageName, int authenticators) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IAuthService
        public CharSequence getSettingName(int userId, String opPackageName, int authenticators) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAuthService {
        static final int TRANSACTION_authenticate = 4;
        static final int TRANSACTION_canAuthenticate = 6;
        static final int TRANSACTION_cancelAuthentication = 5;
        static final int TRANSACTION_createTestSession = 1;
        static final int TRANSACTION_getAuthenticatorIds = 10;
        static final int TRANSACTION_getButtonLabel = 12;
        static final int TRANSACTION_getPromptMessage = 13;
        static final int TRANSACTION_getSensorProperties = 2;
        static final int TRANSACTION_getSettingName = 14;
        static final int TRANSACTION_getUiPackage = 3;
        static final int TRANSACTION_hasEnrolledBiometrics = 7;
        static final int TRANSACTION_invalidateAuthenticatorIds = 9;
        static final int TRANSACTION_registerEnabledOnKeyguardCallback = 8;
        static final int TRANSACTION_resetLockoutTimeBound = 11;

        public Stub() {
            attachInterface(this, IAuthService.DESCRIPTOR);
        }

        public static IAuthService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAuthService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAuthService)) {
                return new Proxy(obj);
            }
            return (IAuthService) iin;
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
                    return "getUiPackage";
                case 4:
                    return "authenticate";
                case 5:
                    return "cancelAuthentication";
                case 6:
                    return "canAuthenticate";
                case 7:
                    return "hasEnrolledBiometrics";
                case 8:
                    return "registerEnabledOnKeyguardCallback";
                case 9:
                    return "invalidateAuthenticatorIds";
                case 10:
                    return "getAuthenticatorIds";
                case 11:
                    return "resetLockoutTimeBound";
                case 12:
                    return "getButtonLabel";
                case 13:
                    return "getPromptMessage";
                case 14:
                    return "getSettingName";
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
                    reply.writeString(IAuthService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            ITestSessionCallback _arg1 = ITestSessionCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg2 = data.readString();
                            ITestSession _result = createTestSession(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        case 2:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            List<SensorPropertiesInternal> _result2 = getSensorProperties(_arg02);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            String _result3 = getUiPackage();
                            reply.writeNoException();
                            reply.writeString(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
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
                        case 5:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            String _arg13 = data.readString();
                            cancelAuthentication(_arg04, _arg13);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg14 = data.readInt();
                            int _arg23 = data.readInt();
                            int _result4 = canAuthenticate(_arg05, _arg14, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _arg15 = data.readString();
                            boolean hasEnrolledBiometrics = hasEnrolledBiometrics(_arg06, _arg15);
                            reply.writeNoException();
                            reply.writeInt(hasEnrolledBiometrics ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            IBiometricEnabledOnKeyguardCallback _arg07 = IBiometricEnabledOnKeyguardCallback.Stub.asInterface(data.readStrongBinder());
                            registerEnabledOnKeyguardCallback(_arg07);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg16 = data.readInt();
                            IInvalidationCallback _arg24 = IInvalidationCallback.Stub.asInterface(data.readStrongBinder());
                            invalidateAuthenticatorIds(_arg08, _arg16, _arg24);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            long[] _result5 = getAuthenticatorIds(_arg09);
                            reply.writeNoException();
                            reply.writeLongArray(_result5);
                            return true;
                        case 11:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            IBinder _arg010 = data.readStrongBinder();
                            String _arg17 = data.readString();
                            int _arg25 = data.readInt();
                            int _arg32 = data.readInt();
                            byte[] _arg42 = data.createByteArray();
                            resetLockoutTimeBound(_arg010, _arg17, _arg25, _arg32, _arg42);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            String _arg18 = data.readString();
                            int _arg26 = data.readInt();
                            CharSequence _result6 = getButtonLabel(_arg011, _arg18, _arg26);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                TextUtils.writeToParcel(_result6, reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 13:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            String _arg19 = data.readString();
                            int _arg27 = data.readInt();
                            CharSequence _result7 = getPromptMessage(_arg012, _arg19, _arg27);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                TextUtils.writeToParcel(_result7, reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 14:
                            data.enforceInterface(IAuthService.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            String _arg110 = data.readString();
                            int _arg28 = data.readInt();
                            CharSequence _result8 = getSettingName(_arg013, _arg110, _arg28);
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                TextUtils.writeToParcel(_result8, reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAuthService {
            public static IAuthService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAuthService.DESCRIPTOR;
            }

            @Override // android.hardware.biometrics.IAuthService
            public ITestSession createTestSession(int sensorId, ITestSessionCallback callback, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
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

            @Override // android.hardware.biometrics.IAuthService
            public List<SensorPropertiesInternal> getSensorProperties(String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
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

            @Override // android.hardware.biometrics.IAuthService
            public String getUiPackage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUiPackage();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IAuthService
            public void authenticate(IBinder token, long sessionId, int userId, IBiometricServiceReceiver receiver, String opPackageName, PromptInfo promptInfo) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(token);
                        try {
                            _data.writeLong(sessionId);
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
                                boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().authenticate(token, sessionId, userId, receiver, opPackageName, promptInfo);
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

            @Override // android.hardware.biometrics.IAuthService
            public void cancelAuthentication(IBinder token, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // android.hardware.biometrics.IAuthService
            public int canAuthenticate(String opPackageName, int userId, int authenticators) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeString(opPackageName);
                    _data.writeInt(userId);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAuthenticate(opPackageName, userId, authenticators);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IAuthService
            public boolean hasEnrolledBiometrics(int userId, String opPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // android.hardware.biometrics.IAuthService
            public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IAuthService
            public void invalidateAuthenticatorIds(int userId, int fromSensorId, IInvalidationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(fromSensorId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // android.hardware.biometrics.IAuthService
            public long[] getAuthenticatorIds(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticatorIds(userId);
                    }
                    _reply.readException();
                    long[] _result = _reply.createLongArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IAuthService
            public void resetLockoutTimeBound(IBinder token, String opPackageName, int fromSensorId, int userId, byte[] hardwareAuthToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(opPackageName);
                    _data.writeInt(fromSensorId);
                    _data.writeInt(userId);
                    _data.writeByteArray(hardwareAuthToken);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // android.hardware.biometrics.IAuthService
            public CharSequence getButtonLabel(int userId, String opPackageName, int authenticators) throws RemoteException {
                CharSequence _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getButtonLabel(userId, opPackageName, authenticators);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IAuthService
            public CharSequence getPromptMessage(int userId, String opPackageName, int authenticators) throws RemoteException {
                CharSequence _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPromptMessage(userId, opPackageName, authenticators);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IAuthService
            public CharSequence getSettingName(int userId, String opPackageName, int authenticators) throws RemoteException {
                CharSequence _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAuthService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(opPackageName);
                    _data.writeInt(authenticators);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingName(userId, opPackageName, authenticators);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAuthService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAuthService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
