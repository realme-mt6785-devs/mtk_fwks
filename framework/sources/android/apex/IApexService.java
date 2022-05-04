package android.apex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IApexService extends IInterface {
    public static final String DESCRIPTOR = "android$apex$IApexService".replace('$', '.');

    void abortStagedSession(int i) throws RemoteException;

    void activatePackage(String str) throws RemoteException;

    long calculateSizeForCompressedApex(CompressedApexInfoList compressedApexInfoList) throws RemoteException;

    void deactivatePackage(String str) throws RemoteException;

    void destroyCeSnapshots(int i, int i2) throws RemoteException;

    void destroyCeSnapshotsNotSpecified(int i, int[] iArr) throws RemoteException;

    void destroyDeSnapshots(int i) throws RemoteException;

    ApexInfo getActivePackage(String str) throws RemoteException;

    ApexInfo[] getActivePackages() throws RemoteException;

    ApexInfo[] getAllPackages() throws RemoteException;

    ApexSessionInfo[] getSessions() throws RemoteException;

    ApexSessionInfo getStagedSessionInfo(int i) throws RemoteException;

    ApexInfo installAndActivatePackage(String str) throws RemoteException;

    void markBootCompleted() throws RemoteException;

    void markStagedSessionReady(int i) throws RemoteException;

    void markStagedSessionSuccessful(int i) throws RemoteException;

    void postinstallPackages(List<String> list) throws RemoteException;

    void preinstallPackages(List<String> list) throws RemoteException;

    void recollectDataApex(String str, String str2) throws RemoteException;

    void recollectPreinstalledData(List<String> list) throws RemoteException;

    void remountPackages() throws RemoteException;

    void reserveSpaceForCompressedApex(CompressedApexInfoList compressedApexInfoList) throws RemoteException;

    void restoreCeData(int i, int i2, String str) throws RemoteException;

    void resumeRevertIfNeeded() throws RemoteException;

    void revertActiveSessions() throws RemoteException;

    void snapshotCeData(int i, int i2, String str) throws RemoteException;

    void stagePackages(List<String> list) throws RemoteException;

    void submitStagedSession(ApexSessionParams apexSessionParams, ApexInfoList apexInfoList) throws RemoteException;

    void unstagePackages(List<String> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IApexService {
        @Override // android.apex.IApexService
        public void submitStagedSession(ApexSessionParams params, ApexInfoList packages) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void markStagedSessionReady(int session_id) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void markStagedSessionSuccessful(int session_id) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public ApexSessionInfo[] getSessions() throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexSessionInfo getStagedSessionInfo(int session_id) throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexInfo[] getActivePackages() throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexInfo[] getAllPackages() throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public void abortStagedSession(int session_id) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void revertActiveSessions() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void snapshotCeData(int user_id, int rollback_id, String apex_name) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void restoreCeData(int user_id, int rollback_id, String apex_name) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void destroyDeSnapshots(int rollback_id) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void destroyCeSnapshots(int user_id, int rollback_id) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void destroyCeSnapshotsNotSpecified(int user_id, int[] retain_rollback_ids) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void unstagePackages(List<String> active_package_paths) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public ApexInfo getActivePackage(String package_name) throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public void activatePackage(String package_path) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void deactivatePackage(String package_path) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void preinstallPackages(List<String> package_tmp_paths) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void postinstallPackages(List<String> package_tmp_paths) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void stagePackages(List<String> package_tmp_paths) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void resumeRevertIfNeeded() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void remountPackages() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void recollectPreinstalledData(List<String> paths) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void recollectDataApex(String path, String decompression_dir) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void markBootCompleted() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public long calculateSizeForCompressedApex(CompressedApexInfoList compressed_apex_info_list) throws RemoteException {
            return 0L;
        }

        @Override // android.apex.IApexService
        public void reserveSpaceForCompressedApex(CompressedApexInfoList compressed_apex_info_list) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public ApexInfo installAndActivatePackage(String packagePath) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IApexService {
        static final int TRANSACTION_abortStagedSession = 8;
        static final int TRANSACTION_activatePackage = 17;
        static final int TRANSACTION_calculateSizeForCompressedApex = 27;
        static final int TRANSACTION_deactivatePackage = 18;
        static final int TRANSACTION_destroyCeSnapshots = 13;
        static final int TRANSACTION_destroyCeSnapshotsNotSpecified = 14;
        static final int TRANSACTION_destroyDeSnapshots = 12;
        static final int TRANSACTION_getActivePackage = 16;
        static final int TRANSACTION_getActivePackages = 6;
        static final int TRANSACTION_getAllPackages = 7;
        static final int TRANSACTION_getSessions = 4;
        static final int TRANSACTION_getStagedSessionInfo = 5;
        static final int TRANSACTION_installAndActivatePackage = 29;
        static final int TRANSACTION_markBootCompleted = 26;
        static final int TRANSACTION_markStagedSessionReady = 2;
        static final int TRANSACTION_markStagedSessionSuccessful = 3;
        static final int TRANSACTION_postinstallPackages = 20;
        static final int TRANSACTION_preinstallPackages = 19;
        static final int TRANSACTION_recollectDataApex = 25;
        static final int TRANSACTION_recollectPreinstalledData = 24;
        static final int TRANSACTION_remountPackages = 23;
        static final int TRANSACTION_reserveSpaceForCompressedApex = 28;
        static final int TRANSACTION_restoreCeData = 11;
        static final int TRANSACTION_resumeRevertIfNeeded = 22;
        static final int TRANSACTION_revertActiveSessions = 9;
        static final int TRANSACTION_snapshotCeData = 10;
        static final int TRANSACTION_stagePackages = 21;
        static final int TRANSACTION_submitStagedSession = 1;
        static final int TRANSACTION_unstagePackages = 15;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IApexService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IApexService)) {
                return new Proxy(obj);
            }
            return (IApexService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ApexSessionParams _arg0;
            CompressedApexInfoList _arg02;
            CompressedApexInfoList _arg03;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = ApexSessionParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            ApexInfoList _arg1 = new ApexInfoList();
                            submitStagedSession(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg1.writeToParcel(reply, 1);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int _arg04 = data.readInt();
                            markStagedSessionReady(_arg04);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg05 = data.readInt();
                            markStagedSessionSuccessful(_arg05);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            ApexSessionInfo[] _result = getSessions();
                            reply.writeNoException();
                            reply.writeTypedArray(_result, 1);
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            int _arg06 = data.readInt();
                            ApexSessionInfo _result2 = getStagedSessionInfo(_arg06);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            ApexInfo[] _result3 = getActivePackages();
                            reply.writeNoException();
                            reply.writeTypedArray(_result3, 1);
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            ApexInfo[] _result4 = getAllPackages();
                            reply.writeNoException();
                            reply.writeTypedArray(_result4, 1);
                            return true;
                        case 8:
                            data.enforceInterface(descriptor);
                            int _arg07 = data.readInt();
                            abortStagedSession(_arg07);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(descriptor);
                            revertActiveSessions();
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(descriptor);
                            int _arg08 = data.readInt();
                            int _arg12 = data.readInt();
                            String _arg2 = data.readString();
                            snapshotCeData(_arg08, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(descriptor);
                            int _arg09 = data.readInt();
                            int _arg13 = data.readInt();
                            String _arg22 = data.readString();
                            restoreCeData(_arg09, _arg13, _arg22);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(descriptor);
                            int _arg010 = data.readInt();
                            destroyDeSnapshots(_arg010);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(descriptor);
                            int _arg011 = data.readInt();
                            destroyCeSnapshots(_arg011, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(descriptor);
                            int _arg012 = data.readInt();
                            destroyCeSnapshotsNotSpecified(_arg012, data.createIntArray());
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(descriptor);
                            List<String> _arg013 = data.createStringArrayList();
                            unstagePackages(_arg013);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(descriptor);
                            String _arg014 = data.readString();
                            ApexInfo _result5 = getActivePackage(_arg014);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 17:
                            data.enforceInterface(descriptor);
                            String _arg015 = data.readString();
                            activatePackage(_arg015);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(descriptor);
                            String _arg016 = data.readString();
                            deactivatePackage(_arg016);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(descriptor);
                            List<String> _arg017 = data.createStringArrayList();
                            preinstallPackages(_arg017);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(descriptor);
                            List<String> _arg018 = data.createStringArrayList();
                            postinstallPackages(_arg018);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(descriptor);
                            List<String> _arg019 = data.createStringArrayList();
                            stagePackages(_arg019);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(descriptor);
                            resumeRevertIfNeeded();
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(descriptor);
                            remountPackages();
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(descriptor);
                            List<String> _arg020 = data.createStringArrayList();
                            recollectPreinstalledData(_arg020);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(descriptor);
                            String _arg021 = data.readString();
                            recollectDataApex(_arg021, data.readString());
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(descriptor);
                            markBootCompleted();
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg02 = CompressedApexInfoList.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            long _result6 = calculateSizeForCompressedApex(_arg02);
                            reply.writeNoException();
                            reply.writeLong(_result6);
                            return true;
                        case 28:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg03 = CompressedApexInfoList.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            reserveSpaceForCompressedApex(_arg03);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(descriptor);
                            String _arg022 = data.readString();
                            ApexInfo _result7 = installAndActivatePackage(_arg022);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
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
        public static class Proxy implements IApexService {
            public static IApexService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.apex.IApexService
            public void submitStagedSession(ApexSessionParams params, ApexInfoList packages) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            packages.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().submitStagedSession(params, packages);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void markStagedSessionReady(int session_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(session_id);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().markStagedSessionReady(session_id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void markStagedSessionSuccessful(int session_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(session_id);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().markStagedSessionSuccessful(session_id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexSessionInfo[] getSessions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSessions();
                    }
                    _reply.readException();
                    ApexSessionInfo[] _result = (ApexSessionInfo[]) _reply.createTypedArray(ApexSessionInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexSessionInfo getStagedSessionInfo(int session_id) throws RemoteException {
                ApexSessionInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(session_id);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStagedSessionInfo(session_id);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ApexSessionInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo[] getActivePackages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePackages();
                    }
                    _reply.readException();
                    ApexInfo[] _result = (ApexInfo[]) _reply.createTypedArray(ApexInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo[] getAllPackages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPackages();
                    }
                    _reply.readException();
                    ApexInfo[] _result = (ApexInfo[]) _reply.createTypedArray(ApexInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void abortStagedSession(int session_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(session_id);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abortStagedSession(session_id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void revertActiveSessions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().revertActiveSessions();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void snapshotCeData(int user_id, int rollback_id, String apex_name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(user_id);
                    _data.writeInt(rollback_id);
                    _data.writeString(apex_name);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().snapshotCeData(user_id, rollback_id, apex_name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void restoreCeData(int user_id, int rollback_id, String apex_name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(user_id);
                    _data.writeInt(rollback_id);
                    _data.writeString(apex_name);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().restoreCeData(user_id, rollback_id, apex_name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void destroyDeSnapshots(int rollback_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(rollback_id);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyDeSnapshots(rollback_id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void destroyCeSnapshots(int user_id, int rollback_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(user_id);
                    _data.writeInt(rollback_id);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyCeSnapshots(user_id, rollback_id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void destroyCeSnapshotsNotSpecified(int user_id, int[] retain_rollback_ids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(user_id);
                    _data.writeIntArray(retain_rollback_ids);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroyCeSnapshotsNotSpecified(user_id, retain_rollback_ids);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void unstagePackages(List<String> active_package_paths) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringList(active_package_paths);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unstagePackages(active_package_paths);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo getActivePackage(String package_name) throws RemoteException {
                ApexInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(package_name);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePackage(package_name);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ApexInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void activatePackage(String package_path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(package_path);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().activatePackage(package_path);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void deactivatePackage(String package_path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(package_path);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deactivatePackage(package_path);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void preinstallPackages(List<String> package_tmp_paths) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringList(package_tmp_paths);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().preinstallPackages(package_tmp_paths);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void postinstallPackages(List<String> package_tmp_paths) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringList(package_tmp_paths);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().postinstallPackages(package_tmp_paths);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void stagePackages(List<String> package_tmp_paths) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringList(package_tmp_paths);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stagePackages(package_tmp_paths);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void resumeRevertIfNeeded() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resumeRevertIfNeeded();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void remountPackages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().remountPackages();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void recollectPreinstalledData(List<String> paths) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringList(paths);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().recollectPreinstalledData(paths);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void recollectDataApex(String path, String decompression_dir) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(path);
                    _data.writeString(decompression_dir);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().recollectDataApex(path, decompression_dir);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void markBootCompleted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().markBootCompleted();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public long calculateSizeForCompressedApex(CompressedApexInfoList compressed_apex_info_list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (compressed_apex_info_list != null) {
                        _data.writeInt(1);
                        compressed_apex_info_list.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().calculateSizeForCompressedApex(compressed_apex_info_list);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void reserveSpaceForCompressedApex(CompressedApexInfoList compressed_apex_info_list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (compressed_apex_info_list != null) {
                        _data.writeInt(1);
                        compressed_apex_info_list.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reserveSpaceForCompressedApex(compressed_apex_info_list);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo installAndActivatePackage(String packagePath) throws RemoteException {
                ApexInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(packagePath);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().installAndActivatePackage(packagePath);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ApexInfo.CREATOR.createFromParcel(_reply);
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

        public static boolean setDefaultImpl(IApexService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IApexService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
