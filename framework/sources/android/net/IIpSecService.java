package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IIpSecService extends IInterface {
    void addAddressToTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException;

    IpSecSpiResponse allocateSecurityParameterIndex(String str, int i, IBinder iBinder) throws RemoteException;

    void applyTransportModeTransform(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws RemoteException;

    void applyTunnelModeTransform(int i, int i2, int i3, String str) throws RemoteException;

    void closeUdpEncapsulationSocket(int i) throws RemoteException;

    IpSecTransformResponse createTransform(IpSecConfig ipSecConfig, IBinder iBinder, String str) throws RemoteException;

    IpSecTunnelInterfaceResponse createTunnelInterface(String str, String str2, Network network, IBinder iBinder, String str3) throws RemoteException;

    void deleteTransform(int i) throws RemoteException;

    void deleteTunnelInterface(int i, String str) throws RemoteException;

    IpSecUdpEncapResponse openUdpEncapsulationSocket(int i, IBinder iBinder) throws RemoteException;

    void releaseSecurityParameterIndex(int i) throws RemoteException;

    void removeAddressFromTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException;

    void removeTransportModeTransforms(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void setNetworkForTunnelInterface(int i, Network network, String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IIpSecService {
        @Override // android.net.IIpSecService
        public IpSecSpiResponse allocateSecurityParameterIndex(String destinationAddress, int requestedSpi, IBinder binder) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void releaseSecurityParameterIndex(int resourceId) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public IpSecUdpEncapResponse openUdpEncapsulationSocket(int port, IBinder binder) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void closeUdpEncapsulationSocket(int resourceId) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public IpSecTunnelInterfaceResponse createTunnelInterface(String localAddr, String remoteAddr, Network underlyingNetwork, IBinder binder, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void addAddressToTunnelInterface(int tunnelResourceId, LinkAddress localAddr, String callingPackage) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void removeAddressFromTunnelInterface(int tunnelResourceId, LinkAddress localAddr, String callingPackage) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void setNetworkForTunnelInterface(int tunnelResourceId, Network underlyingNetwork, String callingPackage) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void deleteTunnelInterface(int resourceId, String callingPackage) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public IpSecTransformResponse createTransform(IpSecConfig c, IBinder binder, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void deleteTransform(int transformId) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void applyTransportModeTransform(ParcelFileDescriptor socket, int direction, int transformId) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void applyTunnelModeTransform(int tunnelResourceId, int direction, int transformResourceId, String callingPackage) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void removeTransportModeTransforms(ParcelFileDescriptor socket) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IIpSecService {
        public static final String DESCRIPTOR = "android.net.IIpSecService";
        static final int TRANSACTION_addAddressToTunnelInterface = 6;
        static final int TRANSACTION_allocateSecurityParameterIndex = 1;
        static final int TRANSACTION_applyTransportModeTransform = 12;
        static final int TRANSACTION_applyTunnelModeTransform = 13;
        static final int TRANSACTION_closeUdpEncapsulationSocket = 4;
        static final int TRANSACTION_createTransform = 10;
        static final int TRANSACTION_createTunnelInterface = 5;
        static final int TRANSACTION_deleteTransform = 11;
        static final int TRANSACTION_deleteTunnelInterface = 9;
        static final int TRANSACTION_openUdpEncapsulationSocket = 3;
        static final int TRANSACTION_releaseSecurityParameterIndex = 2;
        static final int TRANSACTION_removeAddressFromTunnelInterface = 7;
        static final int TRANSACTION_removeTransportModeTransforms = 14;
        static final int TRANSACTION_setNetworkForTunnelInterface = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIpSecService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IIpSecService)) {
                return new Proxy(obj);
            }
            return (IIpSecService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "allocateSecurityParameterIndex";
                case 2:
                    return "releaseSecurityParameterIndex";
                case 3:
                    return "openUdpEncapsulationSocket";
                case 4:
                    return "closeUdpEncapsulationSocket";
                case 5:
                    return "createTunnelInterface";
                case 6:
                    return "addAddressToTunnelInterface";
                case 7:
                    return "removeAddressFromTunnelInterface";
                case 8:
                    return "setNetworkForTunnelInterface";
                case 9:
                    return "deleteTunnelInterface";
                case 10:
                    return "createTransform";
                case 11:
                    return "deleteTransform";
                case 12:
                    return "applyTransportModeTransform";
                case 13:
                    return "applyTunnelModeTransform";
                case 14:
                    return "removeTransportModeTransforms";
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
            Network _arg2;
            LinkAddress _arg1;
            LinkAddress _arg12;
            Network _arg13;
            IpSecConfig _arg0;
            ParcelFileDescriptor _arg02;
            ParcelFileDescriptor _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            int _arg14 = data.readInt();
                            IBinder _arg22 = data.readStrongBinder();
                            IpSecSpiResponse _result = allocateSecurityParameterIndex(_arg04, _arg14, _arg22);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            releaseSecurityParameterIndex(_arg05);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            IBinder _arg15 = data.readStrongBinder();
                            IpSecUdpEncapResponse _result2 = openUdpEncapsulationSocket(_arg06, _arg15);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            closeUdpEncapsulationSocket(_arg07);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            String _arg16 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = (Network) Network.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IBinder _arg3 = data.readStrongBinder();
                            String _arg4 = data.readString();
                            IpSecTunnelInterfaceResponse _result3 = createTunnelInterface(_arg08, _arg16, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = (LinkAddress) LinkAddress.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg23 = data.readString();
                            addAddressToTunnelInterface(_arg09, _arg1, _arg23);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = (LinkAddress) LinkAddress.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            String _arg24 = data.readString();
                            removeAddressFromTunnelInterface(_arg010, _arg12, _arg24);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = (Network) Network.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            String _arg25 = data.readString();
                            setNetworkForTunnelInterface(_arg011, _arg13, _arg25);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            String _arg17 = data.readString();
                            deleteTunnelInterface(_arg012, _arg17);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = IpSecConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IBinder _arg18 = data.readStrongBinder();
                            String _arg26 = data.readString();
                            IpSecTransformResponse _result4 = createTransform(_arg0, _arg18, _arg26);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            deleteTransform(_arg013);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg19 = data.readInt();
                            int _arg27 = data.readInt();
                            applyTransportModeTransform(_arg02, _arg19, _arg27);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int _arg110 = data.readInt();
                            int _arg28 = data.readInt();
                            String _arg32 = data.readString();
                            applyTunnelModeTransform(_arg014, _arg110, _arg28, _arg32);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            removeTransportModeTransforms(_arg03);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IIpSecService {
            public static IIpSecService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.net.IIpSecService
            public IpSecSpiResponse allocateSecurityParameterIndex(String destinationAddress, int requestedSpi, IBinder binder) throws RemoteException {
                IpSecSpiResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(destinationAddress);
                    _data.writeInt(requestedSpi);
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allocateSecurityParameterIndex(destinationAddress, requestedSpi, binder);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IpSecSpiResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void releaseSecurityParameterIndex(int resourceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resourceId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseSecurityParameterIndex(resourceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public IpSecUdpEncapResponse openUdpEncapsulationSocket(int port, IBinder binder) throws RemoteException {
                IpSecUdpEncapResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(port);
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openUdpEncapsulationSocket(port, binder);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IpSecUdpEncapResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void closeUdpEncapsulationSocket(int resourceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resourceId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeUdpEncapsulationSocket(resourceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public IpSecTunnelInterfaceResponse createTunnelInterface(String localAddr, String remoteAddr, Network underlyingNetwork, IBinder binder, String callingPackage) throws RemoteException {
                IpSecTunnelInterfaceResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(localAddr);
                    _data.writeString(remoteAddr);
                    if (underlyingNetwork != null) {
                        _data.writeInt(1);
                        underlyingNetwork.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(binder);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTunnelInterface(localAddr, remoteAddr, underlyingNetwork, binder, callingPackage);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IpSecTunnelInterfaceResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void addAddressToTunnelInterface(int tunnelResourceId, LinkAddress localAddr, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(tunnelResourceId);
                    if (localAddr != null) {
                        _data.writeInt(1);
                        localAddr.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addAddressToTunnelInterface(tunnelResourceId, localAddr, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void removeAddressFromTunnelInterface(int tunnelResourceId, LinkAddress localAddr, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(tunnelResourceId);
                    if (localAddr != null) {
                        _data.writeInt(1);
                        localAddr.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeAddressFromTunnelInterface(tunnelResourceId, localAddr, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void setNetworkForTunnelInterface(int tunnelResourceId, Network underlyingNetwork, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(tunnelResourceId);
                    if (underlyingNetwork != null) {
                        _data.writeInt(1);
                        underlyingNetwork.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNetworkForTunnelInterface(tunnelResourceId, underlyingNetwork, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void deleteTunnelInterface(int resourceId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resourceId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteTunnelInterface(resourceId, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public IpSecTransformResponse createTransform(IpSecConfig c, IBinder binder, String callingPackage) throws RemoteException {
                IpSecTransformResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (c != null) {
                        _data.writeInt(1);
                        c.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(binder);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTransform(c, binder, callingPackage);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IpSecTransformResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void deleteTransform(int transformId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(transformId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteTransform(transformId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void applyTransportModeTransform(ParcelFileDescriptor socket, int direction, int transformId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (socket != null) {
                        _data.writeInt(1);
                        socket.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(direction);
                    _data.writeInt(transformId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyTransportModeTransform(socket, direction, transformId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void applyTunnelModeTransform(int tunnelResourceId, int direction, int transformResourceId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(tunnelResourceId);
                    _data.writeInt(direction);
                    _data.writeInt(transformResourceId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyTunnelModeTransform(tunnelResourceId, direction, transformResourceId, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void removeTransportModeTransforms(ParcelFileDescriptor socket) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (socket != null) {
                        _data.writeInt(1);
                        socket.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeTransportModeTransforms(socket);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIpSecService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IIpSecService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
