package com.oplus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.app.OplusAlarmInfo;
import com.oplus.app.OplusWakeLockInfo;
import java.util.List;
/* loaded from: classes4.dex */
public interface IOplusPowerMonitor extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.os.IOplusPowerMonitor";

    void acquireSuspendBlocker(String str) throws RemoteException;

    List<OplusAlarmInfo> getAlarmWakeUpInfo(long j, long j2) throws RemoteException;

    String getRpmMasterStatsFilePath() throws RemoteException;

    String getRpmStatsFilePath() throws RemoteException;

    List<OplusWakeLockInfo> getWakeLockInfo(long j, long j2) throws RemoteException;

    void recordAlarmWakeupEvent() throws RemoteException;

    void recordAppWakeupEvent(int i, String str) throws RemoteException;

    void recordAppWakeupInfoEvent(OplusAlarmInfo oplusAlarmInfo) throws RemoteException;

    void recordWakeLockAcquireEvent(OplusWakeLockInfo oplusWakeLockInfo) throws RemoteException;

    void recordWakeLockReleaseEvent(OplusWakeLockInfo oplusWakeLockInfo) throws RemoteException;

    void releaseSuspendBlocker(String str) throws RemoteException;

    void resetWakeupEventRecords() throws RemoteException;

    void scheduleRpmUpdate(boolean z) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusPowerMonitor {
        @Override // com.oplus.os.IOplusPowerMonitor
        public void recordAlarmWakeupEvent() throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void recordAppWakeupEvent(int alarmType, String alarmPackageName) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void resetWakeupEventRecords() throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void acquireSuspendBlocker(String name) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void releaseSuspendBlocker(String name) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public String getRpmStatsFilePath() throws RemoteException {
            return null;
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public String getRpmMasterStatsFilePath() throws RemoteException {
            return null;
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void scheduleRpmUpdate(boolean calDelta) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void recordAppWakeupInfoEvent(OplusAlarmInfo alarmInfo) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public List<OplusAlarmInfo> getAlarmWakeUpInfo(long starttime, long endtime) throws RemoteException {
            return null;
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void recordWakeLockAcquireEvent(OplusWakeLockInfo wakeLockInfo) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public void recordWakeLockReleaseEvent(OplusWakeLockInfo wakeLockInfo) throws RemoteException {
        }

        @Override // com.oplus.os.IOplusPowerMonitor
        public List<OplusWakeLockInfo> getWakeLockInfo(long starttime, long endtime) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusPowerMonitor {
        static final int TRANSACTION_acquireSuspendBlocker = 4;
        static final int TRANSACTION_getAlarmWakeUpInfo = 10;
        static final int TRANSACTION_getRpmMasterStatsFilePath = 7;
        static final int TRANSACTION_getRpmStatsFilePath = 6;
        static final int TRANSACTION_getWakeLockInfo = 13;
        static final int TRANSACTION_recordAlarmWakeupEvent = 1;
        static final int TRANSACTION_recordAppWakeupEvent = 2;
        static final int TRANSACTION_recordAppWakeupInfoEvent = 9;
        static final int TRANSACTION_recordWakeLockAcquireEvent = 11;
        static final int TRANSACTION_recordWakeLockReleaseEvent = 12;
        static final int TRANSACTION_releaseSuspendBlocker = 5;
        static final int TRANSACTION_resetWakeupEventRecords = 3;
        static final int TRANSACTION_scheduleRpmUpdate = 8;

        public Stub() {
            attachInterface(this, IOplusPowerMonitor.DESCRIPTOR);
        }

        public static IOplusPowerMonitor asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusPowerMonitor.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusPowerMonitor)) {
                return new Proxy(obj);
            }
            return (IOplusPowerMonitor) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "recordAlarmWakeupEvent";
                case 2:
                    return "recordAppWakeupEvent";
                case 3:
                    return "resetWakeupEventRecords";
                case 4:
                    return "acquireSuspendBlocker";
                case 5:
                    return "releaseSuspendBlocker";
                case 6:
                    return "getRpmStatsFilePath";
                case 7:
                    return "getRpmMasterStatsFilePath";
                case 8:
                    return "scheduleRpmUpdate";
                case 9:
                    return "recordAppWakeupInfoEvent";
                case 10:
                    return "getAlarmWakeUpInfo";
                case 11:
                    return "recordWakeLockAcquireEvent";
                case 12:
                    return "recordWakeLockReleaseEvent";
                case 13:
                    return "getWakeLockInfo";
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
            OplusAlarmInfo _arg0;
            OplusWakeLockInfo _arg02;
            OplusWakeLockInfo _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusPowerMonitor.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            recordAlarmWakeupEvent();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            String _arg1 = data.readString();
                            recordAppWakeupEvent(_arg04, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            resetWakeupEventRecords();
                            return true;
                        case 4:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            String _arg05 = data.readString();
                            acquireSuspendBlocker(_arg05);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            String _arg06 = data.readString();
                            releaseSuspendBlocker(_arg06);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            String _result = getRpmStatsFilePath();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 7:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            String _result2 = getRpmMasterStatsFilePath();
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 8:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            boolean _arg07 = data.readInt() != 0;
                            scheduleRpmUpdate(_arg07);
                            return true;
                        case 9:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusAlarmInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            recordAppWakeupInfoEvent(_arg0);
                            return true;
                        case 10:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            long _arg08 = data.readLong();
                            long _arg12 = data.readLong();
                            List<OplusAlarmInfo> _result3 = getAlarmWakeUpInfo(_arg08, _arg12);
                            reply.writeNoException();
                            reply.writeTypedList(_result3);
                            return true;
                        case 11:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OplusWakeLockInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            recordWakeLockAcquireEvent(_arg02);
                            return true;
                        case 12:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = OplusWakeLockInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            recordWakeLockReleaseEvent(_arg03);
                            return true;
                        case 13:
                            data.enforceInterface(IOplusPowerMonitor.DESCRIPTOR);
                            long _arg09 = data.readLong();
                            long _arg13 = data.readLong();
                            List<OplusWakeLockInfo> _result4 = getWakeLockInfo(_arg09, _arg13);
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusPowerMonitor {
            public static IOplusPowerMonitor sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusPowerMonitor.DESCRIPTOR;
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void recordAlarmWakeupEvent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordAlarmWakeupEvent();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void recordAppWakeupEvent(int alarmType, String alarmPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    _data.writeInt(alarmType);
                    _data.writeString(alarmPackageName);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordAppWakeupEvent(alarmType, alarmPackageName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void resetWakeupEventRecords() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetWakeupEventRecords();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void acquireSuspendBlocker(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acquireSuspendBlocker(name);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void releaseSuspendBlocker(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseSuspendBlocker(name);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public String getRpmStatsFilePath() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRpmStatsFilePath();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public String getRpmMasterStatsFilePath() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRpmMasterStatsFilePath();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void scheduleRpmUpdate(boolean calDelta) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    _data.writeInt(calDelta ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scheduleRpmUpdate(calDelta);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void recordAppWakeupInfoEvent(OplusAlarmInfo alarmInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    if (alarmInfo != null) {
                        _data.writeInt(1);
                        alarmInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordAppWakeupInfoEvent(alarmInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public List<OplusAlarmInfo> getAlarmWakeUpInfo(long starttime, long endtime) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    _data.writeLong(starttime);
                    _data.writeLong(endtime);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlarmWakeUpInfo(starttime, endtime);
                    }
                    _reply.readException();
                    List<OplusAlarmInfo> _result = _reply.createTypedArrayList(OplusAlarmInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void recordWakeLockAcquireEvent(OplusWakeLockInfo wakeLockInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    if (wakeLockInfo != null) {
                        _data.writeInt(1);
                        wakeLockInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordWakeLockAcquireEvent(wakeLockInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public void recordWakeLockReleaseEvent(OplusWakeLockInfo wakeLockInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    if (wakeLockInfo != null) {
                        _data.writeInt(1);
                        wakeLockInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordWakeLockReleaseEvent(wakeLockInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.IOplusPowerMonitor
            public List<OplusWakeLockInfo> getWakeLockInfo(long starttime, long endtime) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusPowerMonitor.DESCRIPTOR);
                    _data.writeLong(starttime);
                    _data.writeLong(endtime);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWakeLockInfo(starttime, endtime);
                    }
                    _reply.readException();
                    List<OplusWakeLockInfo> _result = _reply.createTypedArrayList(OplusWakeLockInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusPowerMonitor impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusPowerMonitor getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
