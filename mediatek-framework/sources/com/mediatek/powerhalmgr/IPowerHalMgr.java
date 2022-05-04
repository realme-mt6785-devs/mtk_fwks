package com.mediatek.powerhalmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPowerHalMgr extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.powerhalmgr.IPowerHalMgr";

    void UpdateManagementPkt(int i, String str) throws RemoteException;

    boolean flushPriorityRules(int i) throws RemoteException;

    void getCpuCap() throws RemoteException;

    void getCpuRTInfo() throws RemoteException;

    void getGpuCap() throws RemoteException;

    void getGpuRTInfo() throws RemoteException;

    boolean isDupPacketPredictionStarted() throws RemoteException;

    void mtkCusPowerHint(int i, int i2) throws RemoteException;

    void mtkPowerHint(int i, int i2) throws RemoteException;

    int perfCusLockHint(int i, int i2) throws RemoteException;

    int perfLockAcquire(int i, int i2, int[] iArr) throws RemoteException;

    void perfLockRelease(int i) throws RemoteException;

    int querySysInfo(int i, int i2) throws RemoteException;

    boolean registerDuplicatePacketPredictionEvent(IRemoteCallback iRemoteCallback) throws RemoteException;

    void scnConfig(int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException;

    void scnDisable(int i) throws RemoteException;

    void scnEnable(int i, int i2) throws RemoteException;

    int scnReg() throws RemoteException;

    void scnUltraCfg(int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException;

    void scnUnreg(int i) throws RemoteException;

    void setForegroundSports() throws RemoteException;

    void setPredictInfo(String str, int i) throws RemoteException;

    boolean setPriorityByLinkinfo(int i, DupLinkInfo dupLinkInfo) throws RemoteException;

    boolean setPriorityByUid(int i, int i2) throws RemoteException;

    void setSysInfo(int i, String str) throws RemoteException;

    int setSysInfoSync(int i, String str) throws RemoteException;

    boolean startDuplicatePacketPrediction() throws RemoteException;

    boolean stopDuplicatePacketPrediction() throws RemoteException;

    boolean unregisterDuplicatePacketPredictionEvent(IRemoteCallback iRemoteCallback) throws RemoteException;

    boolean updateMultiDuplicatePacketLink(DupLinkInfo[] dupLinkInfoArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPowerHalMgr {
        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public int scnReg() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void scnConfig(int handle, int cmd, int param_1, int param_2, int param_3, int param_4) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void scnUnreg(int handle) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void scnEnable(int handle, int timeout) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void scnDisable(int handle) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void scnUltraCfg(int handle, int ultracmd, int param_1, int param_2, int param_3, int param_4) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void mtkCusPowerHint(int hint, int data) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void getCpuCap() throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void getGpuCap() throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void getGpuRTInfo() throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void getCpuRTInfo() throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void UpdateManagementPkt(int type, String packet) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void setForegroundSports() throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void setSysInfo(int type, String data) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean startDuplicatePacketPrediction() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean stopDuplicatePacketPrediction() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean isDupPacketPredictionStarted() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean registerDuplicatePacketPredictionEvent(IRemoteCallback listener) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean unregisterDuplicatePacketPredictionEvent(IRemoteCallback listener) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean updateMultiDuplicatePacketLink(DupLinkInfo[] linkList) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void setPredictInfo(String pack_name, int uid) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public int perfLockAcquire(int handle, int duration, int[] list) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void perfLockRelease(int handle) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public int querySysInfo(int cmd, int param) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public void mtkPowerHint(int hint, int data) throws RemoteException {
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public int perfCusLockHint(int hint, int duration) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public int setSysInfoSync(int type, String data) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean setPriorityByUid(int action, int uid) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean setPriorityByLinkinfo(int action, DupLinkInfo linkinfo) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.powerhalmgr.IPowerHalMgr
        public boolean flushPriorityRules(int type) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPowerHalMgr {
        static final int TRANSACTION_UpdateManagementPkt = 12;
        static final int TRANSACTION_flushPriorityRules = 30;
        static final int TRANSACTION_getCpuCap = 8;
        static final int TRANSACTION_getCpuRTInfo = 11;
        static final int TRANSACTION_getGpuCap = 9;
        static final int TRANSACTION_getGpuRTInfo = 10;
        static final int TRANSACTION_isDupPacketPredictionStarted = 17;
        static final int TRANSACTION_mtkCusPowerHint = 7;
        static final int TRANSACTION_mtkPowerHint = 25;
        static final int TRANSACTION_perfCusLockHint = 26;
        static final int TRANSACTION_perfLockAcquire = 22;
        static final int TRANSACTION_perfLockRelease = 23;
        static final int TRANSACTION_querySysInfo = 24;
        static final int TRANSACTION_registerDuplicatePacketPredictionEvent = 18;
        static final int TRANSACTION_scnConfig = 2;
        static final int TRANSACTION_scnDisable = 5;
        static final int TRANSACTION_scnEnable = 4;
        static final int TRANSACTION_scnReg = 1;
        static final int TRANSACTION_scnUltraCfg = 6;
        static final int TRANSACTION_scnUnreg = 3;
        static final int TRANSACTION_setForegroundSports = 13;
        static final int TRANSACTION_setPredictInfo = 21;
        static final int TRANSACTION_setPriorityByLinkinfo = 29;
        static final int TRANSACTION_setPriorityByUid = 28;
        static final int TRANSACTION_setSysInfo = 14;
        static final int TRANSACTION_setSysInfoSync = 27;
        static final int TRANSACTION_startDuplicatePacketPrediction = 15;
        static final int TRANSACTION_stopDuplicatePacketPrediction = 16;
        static final int TRANSACTION_unregisterDuplicatePacketPredictionEvent = 19;
        static final int TRANSACTION_updateMultiDuplicatePacketLink = 20;

        public Stub() {
            attachInterface(this, IPowerHalMgr.DESCRIPTOR);
        }

        public static IPowerHalMgr asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPowerHalMgr.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPowerHalMgr)) {
                return new Proxy(obj);
            }
            return (IPowerHalMgr) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            DupLinkInfo _arg1;
            switch (code) {
                case 1598968902:
                    reply.writeString(IPowerHalMgr.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _result = scnReg();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg12 = data.readInt();
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            int _arg5 = data.readInt();
                            scnConfig(_arg0, _arg12, _arg2, _arg3, _arg4, _arg5);
                            return true;
                        case 3:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            scnUnreg(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg13 = data.readInt();
                            scnEnable(_arg03, _arg13);
                            return true;
                        case 5:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            scnDisable(_arg04);
                            return true;
                        case 6:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg14 = data.readInt();
                            int _arg22 = data.readInt();
                            int _arg32 = data.readInt();
                            int _arg42 = data.readInt();
                            int _arg52 = data.readInt();
                            scnUltraCfg(_arg05, _arg14, _arg22, _arg32, _arg42, _arg52);
                            return true;
                        case 7:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg15 = data.readInt();
                            mtkCusPowerHint(_arg06, _arg15);
                            return true;
                        case 8:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            getCpuCap();
                            return true;
                        case 9:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            getGpuCap();
                            return true;
                        case 10:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            getGpuRTInfo();
                            return true;
                        case 11:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            getCpuRTInfo();
                            return true;
                        case 12:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            String _arg16 = data.readString();
                            UpdateManagementPkt(_arg07, _arg16);
                            return true;
                        case 13:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            setForegroundSports();
                            return true;
                        case 14:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            String _arg17 = data.readString();
                            setSysInfo(_arg08, _arg17);
                            return true;
                        case 15:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            boolean startDuplicatePacketPrediction = startDuplicatePacketPrediction();
                            reply.writeNoException();
                            reply.writeInt(startDuplicatePacketPrediction ? 1 : 0);
                            return true;
                        case 16:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            boolean stopDuplicatePacketPrediction = stopDuplicatePacketPrediction();
                            reply.writeNoException();
                            reply.writeInt(stopDuplicatePacketPrediction ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            boolean isDupPacketPredictionStarted = isDupPacketPredictionStarted();
                            reply.writeNoException();
                            reply.writeInt(isDupPacketPredictionStarted ? 1 : 0);
                            return true;
                        case TRANSACTION_registerDuplicatePacketPredictionEvent /* 18 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            IRemoteCallback _arg09 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            boolean registerDuplicatePacketPredictionEvent = registerDuplicatePacketPredictionEvent(_arg09);
                            reply.writeNoException();
                            reply.writeInt(registerDuplicatePacketPredictionEvent ? 1 : 0);
                            return true;
                        case TRANSACTION_unregisterDuplicatePacketPredictionEvent /* 19 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            IRemoteCallback _arg010 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            boolean unregisterDuplicatePacketPredictionEvent = unregisterDuplicatePacketPredictionEvent(_arg010);
                            reply.writeNoException();
                            reply.writeInt(unregisterDuplicatePacketPredictionEvent ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            DupLinkInfo[] _arg011 = (DupLinkInfo[]) data.createTypedArray(DupLinkInfo.CREATOR);
                            boolean updateMultiDuplicatePacketLink = updateMultiDuplicatePacketLink(_arg011);
                            reply.writeNoException();
                            reply.writeInt(updateMultiDuplicatePacketLink ? 1 : 0);
                            return true;
                        case TRANSACTION_setPredictInfo /* 21 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg18 = data.readInt();
                            setPredictInfo(_arg012, _arg18);
                            return true;
                        case 22:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _arg19 = data.readInt();
                            int[] _arg23 = data.createIntArray();
                            int _result2 = perfLockAcquire(_arg013, _arg19, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 23:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            perfLockRelease(_arg014);
                            return true;
                        case 24:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _arg110 = data.readInt();
                            int _result3 = querySysInfo(_arg015, _arg110);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 25:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            int _arg111 = data.readInt();
                            mtkPowerHint(_arg016, _arg111);
                            return true;
                        case 26:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            int _arg112 = data.readInt();
                            int _result4 = perfCusLockHint(_arg017, _arg112);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case TRANSACTION_setSysInfoSync /* 27 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            String _arg113 = data.readString();
                            int _result5 = setSysInfoSync(_arg018, _arg113);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case TRANSACTION_setPriorityByUid /* 28 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg019 = data.readInt();
                            int _arg114 = data.readInt();
                            boolean priorityByUid = setPriorityByUid(_arg019, _arg114);
                            reply.writeNoException();
                            reply.writeInt(priorityByUid ? 1 : 0);
                            return true;
                        case TRANSACTION_setPriorityByLinkinfo /* 29 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg020 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = DupLinkInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            boolean priorityByLinkinfo = setPriorityByLinkinfo(_arg020, _arg1);
                            reply.writeNoException();
                            reply.writeInt(priorityByLinkinfo ? 1 : 0);
                            return true;
                        case TRANSACTION_flushPriorityRules /* 30 */:
                            data.enforceInterface(IPowerHalMgr.DESCRIPTOR);
                            int _arg021 = data.readInt();
                            boolean flushPriorityRules = flushPriorityRules(_arg021);
                            reply.writeNoException();
                            reply.writeInt(flushPriorityRules ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPowerHalMgr {
            public static IPowerHalMgr sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPowerHalMgr.DESCRIPTOR;
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public int scnReg() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().scnReg();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void scnConfig(int handle, int cmd, int param_1, int param_2, int param_3, int param_4) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeInt(handle);
                    try {
                        _data.writeInt(cmd);
                        try {
                            _data.writeInt(param_1);
                            try {
                                _data.writeInt(param_2);
                            } catch (Throwable th3) {
                                th = th3;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(param_3);
                        try {
                            _data.writeInt(param_4);
                            try {
                                boolean _status = this.mRemote.transact(2, _data, null, 1);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().scnConfig(handle, cmd, param_1, param_2, param_3, param_4);
                                _data.recycle();
                            } catch (Throwable th6) {
                                th = th6;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void scnUnreg(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(handle);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scnUnreg(handle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void scnEnable(int handle, int timeout) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(handle);
                    _data.writeInt(timeout);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scnEnable(handle, timeout);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void scnDisable(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(handle);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scnDisable(handle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void scnUltraCfg(int handle, int ultracmd, int param_1, int param_2, int param_3, int param_4) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeInt(handle);
                    try {
                        _data.writeInt(ultracmd);
                        try {
                            _data.writeInt(param_1);
                            try {
                                _data.writeInt(param_2);
                            } catch (Throwable th3) {
                                th = th3;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(param_3);
                        try {
                            _data.writeInt(param_4);
                            try {
                                boolean _status = this.mRemote.transact(6, _data, null, 1);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().scnUltraCfg(handle, ultracmd, param_1, param_2, param_3, param_4);
                                _data.recycle();
                            } catch (Throwable th6) {
                                th = th6;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void mtkCusPowerHint(int hint, int data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(hint);
                    _data.writeInt(data);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mtkCusPowerHint(hint, data);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void getCpuCap() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCpuCap();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void getGpuCap() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getGpuCap();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void getGpuRTInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getGpuRTInfo();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void getCpuRTInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCpuRTInfo();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void UpdateManagementPkt(int type, String packet) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeString(packet);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().UpdateManagementPkt(type, packet);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void setForegroundSports() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForegroundSports();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void setSysInfo(int type, String data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSysInfo(type, data);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean startDuplicatePacketPrediction() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDuplicatePacketPrediction();
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean stopDuplicatePacketPrediction() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopDuplicatePacketPrediction();
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean isDupPacketPredictionStarted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDupPacketPredictionStarted();
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean registerDuplicatePacketPredictionEvent(IRemoteCallback listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerDuplicatePacketPredictionEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerDuplicatePacketPredictionEvent(listener);
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean unregisterDuplicatePacketPredictionEvent(IRemoteCallback listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterDuplicatePacketPredictionEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterDuplicatePacketPredictionEvent(listener);
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean updateMultiDuplicatePacketLink(DupLinkInfo[] linkList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    boolean _result = false;
                    _data.writeTypedArray(linkList, 0);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateMultiDuplicatePacketLink(linkList);
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void setPredictInfo(String pack_name, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeString(pack_name);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPredictInfo, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPredictInfo(pack_name, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public int perfLockAcquire(int handle, int duration, int[] list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(handle);
                    _data.writeInt(duration);
                    _data.writeIntArray(list);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().perfLockAcquire(handle, duration, list);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void perfLockRelease(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(handle);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().perfLockRelease(handle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public int querySysInfo(int cmd, int param) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(cmd);
                    _data.writeInt(param);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().querySysInfo(cmd, param);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public void mtkPowerHint(int hint, int data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(hint);
                    _data.writeInt(data);
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mtkPowerHint(hint, data);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public int perfCusLockHint(int hint, int duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(hint);
                    _data.writeInt(duration);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().perfCusLockHint(hint, duration);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public int setSysInfoSync(int type, String data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setSysInfoSync, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSysInfoSync(type, data);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean setPriorityByUid(int action, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(action);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPriorityByUid, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPriorityByUid(action, uid);
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

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean setPriorityByLinkinfo(int action, DupLinkInfo linkinfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(action);
                    boolean _result = true;
                    if (linkinfo != null) {
                        _data.writeInt(1);
                        linkinfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPriorityByLinkinfo, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPriorityByLinkinfo(action, linkinfo);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.powerhalmgr.IPowerHalMgr
            public boolean flushPriorityRules(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPowerHalMgr.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_flushPriorityRules, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().flushPriorityRules(type);
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
        }

        public static boolean setDefaultImpl(IPowerHalMgr impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPowerHalMgr getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
