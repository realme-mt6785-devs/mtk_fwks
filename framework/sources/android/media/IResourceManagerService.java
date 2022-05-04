package android.media;

import android.media.IResourceManagerClient;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IResourceManagerService extends IInterface {
    public static final String DESCRIPTOR = "android.media.IResourceManagerService";
    public static final String kPolicySupportsMultipleSecureCodecs = "supports-multiple-secure-codecs";
    public static final String kPolicySupportsSecureWithNonSecureCodec = "supports-secure-with-non-secure-codec";

    void addResource(int i, int i2, long j, IResourceManagerClient iResourceManagerClient, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException;

    void config(MediaResourcePolicyParcel[] mediaResourcePolicyParcelArr) throws RemoteException;

    void markClientForPendingRemoval(int i, long j) throws RemoteException;

    void overridePid(int i, int i2) throws RemoteException;

    void overrideProcessInfo(IResourceManagerClient iResourceManagerClient, int i, int i2, int i3) throws RemoteException;

    boolean reclaimResource(int i, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException;

    void reclaimResourcesFromClientsPendingRemoval(int i) throws RemoteException;

    void removeClient(int i, long j) throws RemoteException;

    void removeResource(int i, long j, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IResourceManagerService {
        @Override // android.media.IResourceManagerService
        public void config(MediaResourcePolicyParcel[] policies) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void addResource(int pid, int uid, long clientId, IResourceManagerClient client, MediaResourceParcel[] resources) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void removeResource(int pid, long clientId, MediaResourceParcel[] resources) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void removeClient(int pid, long clientId) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public boolean reclaimResource(int callingPid, MediaResourceParcel[] resources) throws RemoteException {
            return false;
        }

        @Override // android.media.IResourceManagerService
        public void overridePid(int originalPid, int newPid) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void overrideProcessInfo(IResourceManagerClient client, int pid, int procState, int oomScore) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void markClientForPendingRemoval(int pid, long clientId) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void reclaimResourcesFromClientsPendingRemoval(int pid) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IResourceManagerService {
        static final int TRANSACTION_addResource = 2;
        static final int TRANSACTION_config = 1;
        static final int TRANSACTION_markClientForPendingRemoval = 8;
        static final int TRANSACTION_overridePid = 6;
        static final int TRANSACTION_overrideProcessInfo = 7;
        static final int TRANSACTION_reclaimResource = 5;
        static final int TRANSACTION_reclaimResourcesFromClientsPendingRemoval = 9;
        static final int TRANSACTION_removeClient = 4;
        static final int TRANSACTION_removeResource = 3;

        public Stub() {
            attachInterface(this, IResourceManagerService.DESCRIPTOR);
        }

        public static IResourceManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IResourceManagerService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IResourceManagerService)) {
                return new Proxy(obj);
            }
            return (IResourceManagerService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "config";
                case 2:
                    return "addResource";
                case 3:
                    return "removeResource";
                case 4:
                    return "removeClient";
                case 5:
                    return "reclaimResource";
                case 6:
                    return "overridePid";
                case 7:
                    return "overrideProcessInfo";
                case 8:
                    return "markClientForPendingRemoval";
                case 9:
                    return "reclaimResourcesFromClientsPendingRemoval";
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
                    reply.writeString(IResourceManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            MediaResourcePolicyParcel[] _arg0 = (MediaResourcePolicyParcel[]) data.createTypedArray(MediaResourcePolicyParcel.CREATOR);
                            config(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg1 = data.readInt();
                            long _arg2 = data.readLong();
                            IResourceManagerClient _arg3 = IResourceManagerClient.Stub.asInterface(data.readStrongBinder());
                            MediaResourceParcel[] _arg4 = (MediaResourceParcel[]) data.createTypedArray(MediaResourceParcel.CREATOR);
                            addResource(_arg02, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            long _arg12 = data.readLong();
                            MediaResourceParcel[] _arg22 = (MediaResourceParcel[]) data.createTypedArray(MediaResourceParcel.CREATOR);
                            removeResource(_arg03, _arg12, _arg22);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            long _arg13 = data.readLong();
                            removeClient(_arg04, _arg13);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            MediaResourceParcel[] _arg14 = (MediaResourceParcel[]) data.createTypedArray(MediaResourceParcel.CREATOR);
                            boolean reclaimResource = reclaimResource(_arg05, _arg14);
                            reply.writeNoException();
                            reply.writeInt(reclaimResource ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg15 = data.readInt();
                            overridePid(_arg06, _arg15);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            IResourceManagerClient _arg07 = IResourceManagerClient.Stub.asInterface(data.readStrongBinder());
                            int _arg16 = data.readInt();
                            int _arg23 = data.readInt();
                            int _arg32 = data.readInt();
                            overrideProcessInfo(_arg07, _arg16, _arg23, _arg32);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            long _arg17 = data.readLong();
                            markClientForPendingRemoval(_arg08, _arg17);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IResourceManagerService.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            reclaimResourcesFromClientsPendingRemoval(_arg09);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IResourceManagerService {
            public static IResourceManagerService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IResourceManagerService.DESCRIPTOR;
            }

            @Override // android.media.IResourceManagerService
            public void config(MediaResourcePolicyParcel[] policies) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeTypedArray(policies, 0);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().config(policies);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void addResource(int pid, int uid, long clientId, IResourceManagerClient client, MediaResourceParcel[] resources) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeInt(pid);
                    try {
                        _data.writeInt(uid);
                        try {
                            _data.writeLong(clientId);
                            _data.writeStrongBinder(client != null ? client.asBinder() : null);
                            try {
                                _data.writeTypedArray(resources, 0);
                                try {
                                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().addResource(pid, uid, clientId, client, resources);
                                    _reply.recycle();
                                    _data.recycle();
                                } catch (Throwable th3) {
                                    th = th3;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
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
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.media.IResourceManagerService
            public void removeResource(int pid, long clientId, MediaResourceParcel[] resources) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeLong(clientId);
                    _data.writeTypedArray(resources, 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeResource(pid, clientId, resources);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void removeClient(int pid, long clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeLong(clientId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeClient(pid, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public boolean reclaimResource(int callingPid, MediaResourceParcel[] resources) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeInt(callingPid);
                    boolean _result = false;
                    _data.writeTypedArray(resources, 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reclaimResource(callingPid, resources);
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

            @Override // android.media.IResourceManagerService
            public void overridePid(int originalPid, int newPid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeInt(originalPid);
                    _data.writeInt(newPid);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().overridePid(originalPid, newPid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void overrideProcessInfo(IResourceManagerClient client, int pid, int procState, int oomScore) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeInt(pid);
                    _data.writeInt(procState);
                    _data.writeInt(oomScore);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().overrideProcessInfo(client, pid, procState, oomScore);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void markClientForPendingRemoval(int pid, long clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeLong(clientId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().markClientForPendingRemoval(pid, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void reclaimResourcesFromClientsPendingRemoval(int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerService.DESCRIPTOR);
                    _data.writeInt(pid);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reclaimResourcesFromClientsPendingRemoval(pid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IResourceManagerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IResourceManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
