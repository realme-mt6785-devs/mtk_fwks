package android.os;

import java.util.List;
/* loaded from: classes2.dex */
public interface IIdmap2 extends IInterface {
    public static final String DESCRIPTOR = "android.os.IIdmap2";

    void acquireFabricatedOverlayIterator() throws RemoteException;

    FabricatedOverlayInfo createFabricatedOverlay(FabricatedOverlayInternal fabricatedOverlayInternal) throws RemoteException;

    String createIdmap(String str, String str2, String str3, int i, boolean z, int i2) throws RemoteException;

    boolean deleteFabricatedOverlay(String str) throws RemoteException;

    String dumpIdmap(String str) throws RemoteException;

    String getIdmapPath(String str, int i) throws RemoteException;

    List<FabricatedOverlayInfo> nextFabricatedOverlayInfos() throws RemoteException;

    void releaseFabricatedOverlayIterator() throws RemoteException;

    boolean removeIdmap(String str, int i) throws RemoteException;

    boolean verifyIdmap(String str, String str2, String str3, int i, boolean z, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IIdmap2 {
        @Override // android.os.IIdmap2
        public String getIdmapPath(String overlayApkPath, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IIdmap2
        public boolean removeIdmap(String overlayApkPath, int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IIdmap2
        public boolean verifyIdmap(String targetApkPath, String overlayApkPath, String overlayName, int fulfilledPolicies, boolean enforceOverlayable, int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IIdmap2
        public String createIdmap(String targetApkPath, String overlayApkPath, String overlayName, int fulfilledPolicies, boolean enforceOverlayable, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IIdmap2
        public FabricatedOverlayInfo createFabricatedOverlay(FabricatedOverlayInternal overlay) throws RemoteException {
            return null;
        }

        @Override // android.os.IIdmap2
        public boolean deleteFabricatedOverlay(String path) throws RemoteException {
            return false;
        }

        @Override // android.os.IIdmap2
        public void acquireFabricatedOverlayIterator() throws RemoteException {
        }

        @Override // android.os.IIdmap2
        public void releaseFabricatedOverlayIterator() throws RemoteException {
        }

        @Override // android.os.IIdmap2
        public List<FabricatedOverlayInfo> nextFabricatedOverlayInfos() throws RemoteException {
            return null;
        }

        @Override // android.os.IIdmap2
        public String dumpIdmap(String overlayApkPath) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IIdmap2 {
        static final int TRANSACTION_acquireFabricatedOverlayIterator = 7;
        static final int TRANSACTION_createFabricatedOverlay = 5;
        static final int TRANSACTION_createIdmap = 4;
        static final int TRANSACTION_deleteFabricatedOverlay = 6;
        static final int TRANSACTION_dumpIdmap = 10;
        static final int TRANSACTION_getIdmapPath = 1;
        static final int TRANSACTION_nextFabricatedOverlayInfos = 9;
        static final int TRANSACTION_releaseFabricatedOverlayIterator = 8;
        static final int TRANSACTION_removeIdmap = 2;
        static final int TRANSACTION_verifyIdmap = 3;

        public Stub() {
            attachInterface(this, IIdmap2.DESCRIPTOR);
        }

        public static IIdmap2 asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IIdmap2.DESCRIPTOR);
            if (iin == null || !(iin instanceof IIdmap2)) {
                return new Proxy(obj);
            }
            return (IIdmap2) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getIdmapPath";
                case 2:
                    return "removeIdmap";
                case 3:
                    return "verifyIdmap";
                case 4:
                    return "createIdmap";
                case 5:
                    return "createFabricatedOverlay";
                case 6:
                    return "deleteFabricatedOverlay";
                case 7:
                    return "acquireFabricatedOverlayIterator";
                case 8:
                    return "releaseFabricatedOverlayIterator";
                case 9:
                    return "nextFabricatedOverlayInfos";
                case 10:
                    return "dumpIdmap";
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
            boolean _arg4;
            boolean _arg42;
            FabricatedOverlayInternal _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IIdmap2.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg1 = data.readInt();
                            String _result = getIdmapPath(_arg02, _arg1);
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg12 = data.readInt();
                            boolean removeIdmap = removeIdmap(_arg03, _arg12);
                            reply.writeNoException();
                            reply.writeInt(removeIdmap ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _arg13 = data.readString();
                            String _arg2 = data.readString();
                            int _arg3 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            int _arg5 = data.readInt();
                            boolean verifyIdmap = verifyIdmap(_arg04, _arg13, _arg2, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            reply.writeInt(verifyIdmap ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            String _arg05 = data.readString();
                            String _arg14 = data.readString();
                            String _arg22 = data.readString();
                            int _arg32 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg42 = true;
                            } else {
                                _arg42 = false;
                            }
                            int _arg52 = data.readInt();
                            String _result2 = createIdmap(_arg05, _arg14, _arg22, _arg32, _arg42, _arg52);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = FabricatedOverlayInternal.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            FabricatedOverlayInfo _result3 = createFabricatedOverlay(_arg0);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            String _arg06 = data.readString();
                            boolean deleteFabricatedOverlay = deleteFabricatedOverlay(_arg06);
                            reply.writeNoException();
                            reply.writeInt(deleteFabricatedOverlay ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            acquireFabricatedOverlayIterator();
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            releaseFabricatedOverlayIterator();
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            List<FabricatedOverlayInfo> _result4 = nextFabricatedOverlayInfos();
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        case 10:
                            data.enforceInterface(IIdmap2.DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _result5 = dumpIdmap(_arg07);
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IIdmap2 {
            public static IIdmap2 sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IIdmap2.DESCRIPTOR;
            }

            @Override // android.os.IIdmap2
            public String getIdmapPath(String overlayApkPath, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    _data.writeString(overlayApkPath);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIdmapPath(overlayApkPath, userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IIdmap2
            public boolean removeIdmap(String overlayApkPath, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    _data.writeString(overlayApkPath);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeIdmap(overlayApkPath, userId);
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

            @Override // android.os.IIdmap2
            public boolean verifyIdmap(String targetApkPath, String overlayApkPath, String overlayName, int fulfilledPolicies, boolean enforceOverlayable, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    try {
                        _data.writeString(targetApkPath);
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
                    _data.writeString(overlayApkPath);
                    try {
                        _data.writeString(overlayName);
                        try {
                            _data.writeInt(fulfilledPolicies);
                            boolean _result = true;
                            _data.writeInt(enforceOverlayable ? 1 : 0);
                            try {
                                _data.writeInt(userId);
                                try {
                                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() == 0) {
                                            _result = false;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean verifyIdmap = Stub.getDefaultImpl().verifyIdmap(targetApkPath, overlayApkPath, overlayName, fulfilledPolicies, enforceOverlayable, userId);
                                    _reply.recycle();
                                    _data.recycle();
                                    return verifyIdmap;
                                } catch (Throwable th4) {
                                    th = th4;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
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
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.IIdmap2
            public String createIdmap(String targetApkPath, String overlayApkPath, String overlayName, int fulfilledPolicies, boolean enforceOverlayable, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeString(targetApkPath);
                    try {
                        _data.writeString(overlayApkPath);
                        try {
                            _data.writeString(overlayName);
                            try {
                                _data.writeInt(fulfilledPolicies);
                                _data.writeInt(enforceOverlayable ? 1 : 0);
                                try {
                                    _data.writeInt(userId);
                                    try {
                                        boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            String _result = _reply.readString();
                                            _reply.recycle();
                                            _data.recycle();
                                            return _result;
                                        }
                                        String createIdmap = Stub.getDefaultImpl().createIdmap(targetApkPath, overlayApkPath, overlayName, fulfilledPolicies, enforceOverlayable, userId);
                                        _reply.recycle();
                                        _data.recycle();
                                        return createIdmap;
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
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.IIdmap2
            public FabricatedOverlayInfo createFabricatedOverlay(FabricatedOverlayInternal overlay) throws RemoteException {
                FabricatedOverlayInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    if (overlay != null) {
                        _data.writeInt(1);
                        overlay.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createFabricatedOverlay(overlay);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = FabricatedOverlayInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IIdmap2
            public boolean deleteFabricatedOverlay(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteFabricatedOverlay(path);
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

            @Override // android.os.IIdmap2
            public void acquireFabricatedOverlayIterator() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().acquireFabricatedOverlayIterator();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IIdmap2
            public void releaseFabricatedOverlayIterator() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseFabricatedOverlayIterator();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IIdmap2
            public List<FabricatedOverlayInfo> nextFabricatedOverlayInfos() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nextFabricatedOverlayInfos();
                    }
                    _reply.readException();
                    List<FabricatedOverlayInfo> _result = _reply.createTypedArrayList(FabricatedOverlayInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IIdmap2
            public String dumpIdmap(String overlayApkPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIdmap2.DESCRIPTOR);
                    _data.writeString(overlayApkPath);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpIdmap(overlayApkPath);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIdmap2 impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IIdmap2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
