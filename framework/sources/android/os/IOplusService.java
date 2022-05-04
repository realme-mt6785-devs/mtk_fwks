package android.os;

import java.util.Map;
/* loaded from: classes2.dex */
public interface IOplusService extends IInterface {
    public static final String DESCRIPTOR = "android.os.IOplusService";

    void StartLogCoreService() throws RemoteException;

    void assertKernelPanic() throws RemoteException;

    boolean closeFlashLight() throws RemoteException;

    boolean copyFile(String str, String str2) throws RemoteException;

    boolean copyFileForDcs(String str, String str2) throws RemoteException;

    boolean deleteFile(String str) throws RemoteException;

    void deleteFileForDcs(String str) throws RemoteException;

    void deleteSystemLogFile() throws RemoteException;

    String getFlashLightState() throws RemoteException;

    String getOplusLogInfoString(int i) throws RemoteException;

    boolean iScoreLogServiceRunning() throws RemoteException;

    boolean openFlashLight() throws RemoteException;

    void sendDeleteStampId(String str) throws RemoteException;

    void sendOnStampEvent(String str, Map map) throws RemoteException;

    void sendQualityDCSEvent(String str, Map map) throws RemoteException;

    void startOplusFileEncodeHelperService() throws RemoteException;

    void startSensorLog(boolean z) throws RemoteException;

    void stopSensorLog() throws RemoteException;

    void unbindCoreLogService() throws RemoteException;

    void unbindOplusFileEncodeHelperService() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOplusService {
        @Override // android.os.IOplusService
        public void startSensorLog(boolean isOutPutFile) throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void stopSensorLog() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public boolean openFlashLight() throws RemoteException {
            return false;
        }

        @Override // android.os.IOplusService
        public boolean closeFlashLight() throws RemoteException {
            return false;
        }

        @Override // android.os.IOplusService
        public String getFlashLightState() throws RemoteException {
            return null;
        }

        @Override // android.os.IOplusService
        public boolean iScoreLogServiceRunning() throws RemoteException {
            return false;
        }

        @Override // android.os.IOplusService
        public void StartLogCoreService() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void unbindCoreLogService() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void assertKernelPanic() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public String getOplusLogInfoString(int index) throws RemoteException {
            return null;
        }

        @Override // android.os.IOplusService
        public void deleteSystemLogFile() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public boolean copyFileForDcs(String srcPath, String destPath) throws RemoteException {
            return false;
        }

        @Override // android.os.IOplusService
        public void deleteFileForDcs(String srcPath) throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void startOplusFileEncodeHelperService() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void unbindOplusFileEncodeHelperService() throws RemoteException {
        }

        @Override // android.os.IOplusService
        public boolean copyFile(String destPath, String srcPath) throws RemoteException {
            return false;
        }

        @Override // android.os.IOplusService
        public boolean deleteFile(String path) throws RemoteException {
            return false;
        }

        @Override // android.os.IOplusService
        public void sendOnStampEvent(String eventId, Map map) throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void sendDeleteStampId(String eventId) throws RemoteException {
        }

        @Override // android.os.IOplusService
        public void sendQualityDCSEvent(String eventId, Map map) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOplusService {
        static final int TRANSACTION_StartLogCoreService = 7;
        static final int TRANSACTION_assertKernelPanic = 9;
        static final int TRANSACTION_closeFlashLight = 4;
        static final int TRANSACTION_copyFile = 16;
        static final int TRANSACTION_copyFileForDcs = 12;
        static final int TRANSACTION_deleteFile = 17;
        static final int TRANSACTION_deleteFileForDcs = 13;
        static final int TRANSACTION_deleteSystemLogFile = 11;
        static final int TRANSACTION_getFlashLightState = 5;
        static final int TRANSACTION_getOplusLogInfoString = 10;
        static final int TRANSACTION_iScoreLogServiceRunning = 6;
        static final int TRANSACTION_openFlashLight = 3;
        static final int TRANSACTION_sendDeleteStampId = 19;
        static final int TRANSACTION_sendOnStampEvent = 18;
        static final int TRANSACTION_sendQualityDCSEvent = 20;
        static final int TRANSACTION_startOplusFileEncodeHelperService = 14;
        static final int TRANSACTION_startSensorLog = 1;
        static final int TRANSACTION_stopSensorLog = 2;
        static final int TRANSACTION_unbindCoreLogService = 8;
        static final int TRANSACTION_unbindOplusFileEncodeHelperService = 15;

        public Stub() {
            attachInterface(this, IOplusService.DESCRIPTOR);
        }

        public static IOplusService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusService)) {
                return new Proxy(obj);
            }
            return (IOplusService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startSensorLog";
                case 2:
                    return "stopSensorLog";
                case 3:
                    return "openFlashLight";
                case 4:
                    return "closeFlashLight";
                case 5:
                    return "getFlashLightState";
                case 6:
                    return "iScoreLogServiceRunning";
                case 7:
                    return "StartLogCoreService";
                case 8:
                    return "unbindCoreLogService";
                case 9:
                    return "assertKernelPanic";
                case 10:
                    return "getOplusLogInfoString";
                case 11:
                    return "deleteSystemLogFile";
                case 12:
                    return "copyFileForDcs";
                case 13:
                    return "deleteFileForDcs";
                case 14:
                    return "startOplusFileEncodeHelperService";
                case 15:
                    return "unbindOplusFileEncodeHelperService";
                case 16:
                    return "copyFile";
                case 17:
                    return "deleteFile";
                case 18:
                    return "sendOnStampEvent";
                case 19:
                    return "sendDeleteStampId";
                case 20:
                    return "sendQualityDCSEvent";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            startSensorLog(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            stopSensorLog();
                            return true;
                        case 3:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            boolean openFlashLight = openFlashLight();
                            reply.writeNoException();
                            reply.writeInt(openFlashLight ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            boolean closeFlashLight = closeFlashLight();
                            reply.writeNoException();
                            reply.writeInt(closeFlashLight ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _result = getFlashLightState();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            boolean iScoreLogServiceRunning = iScoreLogServiceRunning();
                            reply.writeNoException();
                            reply.writeInt(iScoreLogServiceRunning ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            StartLogCoreService();
                            return true;
                        case 8:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            unbindCoreLogService();
                            return true;
                        case 9:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            assertKernelPanic();
                            return true;
                        case 10:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _result2 = getOplusLogInfoString(_arg02);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 11:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            deleteSystemLogFile();
                            return true;
                        case 12:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            String _arg1 = data.readString();
                            boolean copyFileForDcs = copyFileForDcs(_arg03, _arg1);
                            reply.writeNoException();
                            reply.writeInt(copyFileForDcs ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg04 = data.readString();
                            deleteFileForDcs(_arg04);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            startOplusFileEncodeHelperService();
                            return true;
                        case 15:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            unbindOplusFileEncodeHelperService();
                            return true;
                        case 16:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg05 = data.readString();
                            String _arg12 = data.readString();
                            boolean copyFile = copyFile(_arg05, _arg12);
                            reply.writeNoException();
                            reply.writeInt(copyFile ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg06 = data.readString();
                            boolean deleteFile = deleteFile(_arg06);
                            reply.writeNoException();
                            reply.writeInt(deleteFile ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg07 = data.readString();
                            ClassLoader cl = getClass().getClassLoader();
                            Map _arg13 = data.readHashMap(cl);
                            sendOnStampEvent(_arg07, _arg13);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg08 = data.readString();
                            sendDeleteStampId(_arg08);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(IOplusService.DESCRIPTOR);
                            String _arg09 = data.readString();
                            ClassLoader cl2 = getClass().getClassLoader();
                            Map _arg14 = data.readHashMap(cl2);
                            sendQualityDCSEvent(_arg09, _arg14);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOplusService {
            public static IOplusService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusService.DESCRIPTOR;
            }

            @Override // android.os.IOplusService
            public void startSensorLog(boolean isOutPutFile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeInt(isOutPutFile ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startSensorLog(isOutPutFile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void stopSensorLog() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopSensorLog();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public boolean openFlashLight() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openFlashLight();
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

            @Override // android.os.IOplusService
            public boolean closeFlashLight() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closeFlashLight();
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

            @Override // android.os.IOplusService
            public String getFlashLightState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFlashLightState();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public boolean iScoreLogServiceRunning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iScoreLogServiceRunning();
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

            @Override // android.os.IOplusService
            public void StartLogCoreService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().StartLogCoreService();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void unbindCoreLogService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unbindCoreLogService();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void assertKernelPanic() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().assertKernelPanic();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public String getOplusLogInfoString(int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeInt(index);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOplusLogInfoString(index);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void deleteSystemLogFile() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteSystemLogFile();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public boolean copyFileForDcs(String srcPath, String destPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(srcPath);
                    _data.writeString(destPath);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().copyFileForDcs(srcPath, destPath);
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

            @Override // android.os.IOplusService
            public void deleteFileForDcs(String srcPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(srcPath);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteFileForDcs(srcPath);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void startOplusFileEncodeHelperService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startOplusFileEncodeHelperService();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void unbindOplusFileEncodeHelperService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unbindOplusFileEncodeHelperService();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public boolean copyFile(String destPath, String srcPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(destPath);
                    _data.writeString(srcPath);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().copyFile(destPath, srcPath);
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

            @Override // android.os.IOplusService
            public boolean deleteFile(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteFile(path);
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

            @Override // android.os.IOplusService
            public void sendOnStampEvent(String eventId, Map map) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(eventId);
                    _data.writeMap(map);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendOnStampEvent(eventId, map);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void sendDeleteStampId(String eventId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(eventId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendDeleteStampId(eventId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IOplusService
            public void sendQualityDCSEvent(String eventId, Map map) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusService.DESCRIPTOR);
                    _data.writeString(eventId);
                    _data.writeMap(map);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendQualityDCSEvent(eventId, map);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
