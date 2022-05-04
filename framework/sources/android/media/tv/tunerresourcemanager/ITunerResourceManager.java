package android.media.tv.tunerresourcemanager;

import android.media.tv.tuner.TunerFrontendInfo;
import android.media.tv.tunerresourcemanager.IResourcesReclaimListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ITunerResourceManager extends IInterface {
    public static final String DESCRIPTOR = "android$media$tv$tunerresourcemanager$ITunerResourceManager".replace('$', '.');

    boolean isHigherPriority(ResourceClientProfile resourceClientProfile, ResourceClientProfile resourceClientProfile2) throws RemoteException;

    void registerClientProfile(ResourceClientProfile resourceClientProfile, IResourcesReclaimListener iResourcesReclaimListener, int[] iArr) throws RemoteException;

    void releaseCasSession(int i, int i2) throws RemoteException;

    void releaseCiCam(int i, int i2) throws RemoteException;

    void releaseDemux(int i, int i2) throws RemoteException;

    void releaseDescrambler(int i, int i2) throws RemoteException;

    void releaseFrontend(int i, int i2) throws RemoteException;

    void releaseLnb(int i, int i2) throws RemoteException;

    boolean requestCasSession(CasSessionRequest casSessionRequest, int[] iArr) throws RemoteException;

    boolean requestCiCam(TunerCiCamRequest tunerCiCamRequest, int[] iArr) throws RemoteException;

    boolean requestDemux(TunerDemuxRequest tunerDemuxRequest, int[] iArr) throws RemoteException;

    boolean requestDescrambler(TunerDescramblerRequest tunerDescramblerRequest, int[] iArr) throws RemoteException;

    boolean requestFrontend(TunerFrontendRequest tunerFrontendRequest, int[] iArr) throws RemoteException;

    boolean requestLnb(TunerLnbRequest tunerLnbRequest, int[] iArr) throws RemoteException;

    void setFrontendInfoList(TunerFrontendInfo[] tunerFrontendInfoArr) throws RemoteException;

    void setLnbInfoList(int[] iArr) throws RemoteException;

    void shareFrontend(int i, int i2) throws RemoteException;

    void unregisterClientProfile(int i) throws RemoteException;

    void updateCasInfo(int i, int i2) throws RemoteException;

    boolean updateClientPriority(int i, int i2, int i3) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ITunerResourceManager {
        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void registerClientProfile(ResourceClientProfile profile, IResourcesReclaimListener listener, int[] clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void unregisterClientProfile(int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean updateClientPriority(int clientId, int priority, int niceValue) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void setFrontendInfoList(TunerFrontendInfo[] infos) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void updateCasInfo(int casSystemId, int maxSessionNum) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void setLnbInfoList(int[] lnbIds) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestFrontend(TunerFrontendRequest request, int[] frontendHandle) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void shareFrontend(int selfClientId, int targetClientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestDemux(TunerDemuxRequest request, int[] demuxHandle) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestDescrambler(TunerDescramblerRequest request, int[] descramblerHandle) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestCasSession(CasSessionRequest request, int[] casSessionHandle) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestCiCam(TunerCiCamRequest request, int[] ciCamHandle) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestLnb(TunerLnbRequest request, int[] lnbHandle) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseFrontend(int frontendHandle, int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseDemux(int demuxHandle, int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseDescrambler(int descramblerHandle, int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseCasSession(int casSessionHandle, int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseCiCam(int ciCamHandle, int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseLnb(int lnbHandle, int clientId) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean isHigherPriority(ResourceClientProfile challengerProfile, ResourceClientProfile holderProfile) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ITunerResourceManager {
        static final int TRANSACTION_isHigherPriority = 20;
        static final int TRANSACTION_registerClientProfile = 1;
        static final int TRANSACTION_releaseCasSession = 17;
        static final int TRANSACTION_releaseCiCam = 18;
        static final int TRANSACTION_releaseDemux = 15;
        static final int TRANSACTION_releaseDescrambler = 16;
        static final int TRANSACTION_releaseFrontend = 14;
        static final int TRANSACTION_releaseLnb = 19;
        static final int TRANSACTION_requestCasSession = 11;
        static final int TRANSACTION_requestCiCam = 12;
        static final int TRANSACTION_requestDemux = 9;
        static final int TRANSACTION_requestDescrambler = 10;
        static final int TRANSACTION_requestFrontend = 7;
        static final int TRANSACTION_requestLnb = 13;
        static final int TRANSACTION_setFrontendInfoList = 4;
        static final int TRANSACTION_setLnbInfoList = 6;
        static final int TRANSACTION_shareFrontend = 8;
        static final int TRANSACTION_unregisterClientProfile = 2;
        static final int TRANSACTION_updateCasInfo = 5;
        static final int TRANSACTION_updateClientPriority = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITunerResourceManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITunerResourceManager)) {
                return new Proxy(obj);
            }
            return (ITunerResourceManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ResourceClientProfile _arg0;
            int[] _arg2;
            TunerFrontendRequest _arg02;
            int[] _arg1;
            TunerDemuxRequest _arg03;
            int[] _arg12;
            TunerDescramblerRequest _arg04;
            int[] _arg13;
            CasSessionRequest _arg05;
            int[] _arg14;
            TunerCiCamRequest _arg06;
            int[] _arg15;
            TunerLnbRequest _arg07;
            int[] _arg16;
            ResourceClientProfile _arg08;
            ResourceClientProfile _arg17;
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
                                _arg0 = ResourceClientProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IResourcesReclaimListener _arg18 = IResourcesReclaimListener.Stub.asInterface(data.readStrongBinder());
                            int _arg2_length = data.readInt();
                            if (_arg2_length < 0) {
                                _arg2 = null;
                            } else {
                                _arg2 = new int[_arg2_length];
                            }
                            registerClientProfile(_arg0, _arg18, _arg2);
                            reply.writeNoException();
                            reply.writeIntArray(_arg2);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int _arg09 = data.readInt();
                            unregisterClientProfile(_arg09);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg010 = data.readInt();
                            int _arg19 = data.readInt();
                            boolean updateClientPriority = updateClientPriority(_arg010, _arg19, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(updateClientPriority ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            TunerFrontendInfo[] _arg011 = (TunerFrontendInfo[]) data.createTypedArray(TunerFrontendInfo.CREATOR);
                            setFrontendInfoList(_arg011);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            int _arg012 = data.readInt();
                            int _arg110 = data.readInt();
                            updateCasInfo(_arg012, _arg110);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            int[] _arg013 = data.createIntArray();
                            setLnbInfoList(_arg013);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg02 = TunerFrontendRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg1_length = data.readInt();
                            if (_arg1_length < 0) {
                                _arg1 = null;
                            } else {
                                _arg1 = new int[_arg1_length];
                            }
                            boolean requestFrontend = requestFrontend(_arg02, _arg1);
                            reply.writeNoException();
                            reply.writeInt(requestFrontend ? 1 : 0);
                            reply.writeIntArray(_arg1);
                            return true;
                        case 8:
                            data.enforceInterface(descriptor);
                            int _arg014 = data.readInt();
                            int _arg111 = data.readInt();
                            shareFrontend(_arg014, _arg111);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg03 = TunerDemuxRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _arg1_length2 = data.readInt();
                            if (_arg1_length2 < 0) {
                                _arg12 = null;
                            } else {
                                _arg12 = new int[_arg1_length2];
                            }
                            boolean requestDemux = requestDemux(_arg03, _arg12);
                            reply.writeNoException();
                            reply.writeInt(requestDemux ? 1 : 0);
                            reply.writeIntArray(_arg12);
                            return true;
                        case 10:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg04 = TunerDescramblerRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            int _arg1_length3 = data.readInt();
                            if (_arg1_length3 < 0) {
                                _arg13 = null;
                            } else {
                                _arg13 = new int[_arg1_length3];
                            }
                            boolean requestDescrambler = requestDescrambler(_arg04, _arg13);
                            reply.writeNoException();
                            reply.writeInt(requestDescrambler ? 1 : 0);
                            reply.writeIntArray(_arg13);
                            return true;
                        case 11:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg05 = CasSessionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            int _arg1_length4 = data.readInt();
                            if (_arg1_length4 < 0) {
                                _arg14 = null;
                            } else {
                                _arg14 = new int[_arg1_length4];
                            }
                            boolean requestCasSession = requestCasSession(_arg05, _arg14);
                            reply.writeNoException();
                            reply.writeInt(requestCasSession ? 1 : 0);
                            reply.writeIntArray(_arg14);
                            return true;
                        case 12:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg06 = TunerCiCamRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            int _arg1_length5 = data.readInt();
                            if (_arg1_length5 < 0) {
                                _arg15 = null;
                            } else {
                                _arg15 = new int[_arg1_length5];
                            }
                            boolean requestCiCam = requestCiCam(_arg06, _arg15);
                            reply.writeNoException();
                            reply.writeInt(requestCiCam ? 1 : 0);
                            reply.writeIntArray(_arg15);
                            return true;
                        case 13:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg07 = TunerLnbRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _arg1_length6 = data.readInt();
                            if (_arg1_length6 < 0) {
                                _arg16 = null;
                            } else {
                                _arg16 = new int[_arg1_length6];
                            }
                            boolean requestLnb = requestLnb(_arg07, _arg16);
                            reply.writeNoException();
                            reply.writeInt(requestLnb ? 1 : 0);
                            reply.writeIntArray(_arg16);
                            return true;
                        case 14:
                            data.enforceInterface(descriptor);
                            int _arg015 = data.readInt();
                            int _arg112 = data.readInt();
                            releaseFrontend(_arg015, _arg112);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(descriptor);
                            int _arg016 = data.readInt();
                            int _arg113 = data.readInt();
                            releaseDemux(_arg016, _arg113);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(descriptor);
                            int _arg017 = data.readInt();
                            int _arg114 = data.readInt();
                            releaseDescrambler(_arg017, _arg114);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(descriptor);
                            int _arg018 = data.readInt();
                            int _arg115 = data.readInt();
                            releaseCasSession(_arg018, _arg115);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(descriptor);
                            int _arg019 = data.readInt();
                            int _arg116 = data.readInt();
                            releaseCiCam(_arg019, _arg116);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(descriptor);
                            int _arg020 = data.readInt();
                            int _arg117 = data.readInt();
                            releaseLnb(_arg020, _arg117);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg08 = ResourceClientProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg17 = ResourceClientProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            boolean isHigherPriority = isHigherPriority(_arg08, _arg17);
                            reply.writeNoException();
                            reply.writeInt(isHigherPriority ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ITunerResourceManager {
            public static ITunerResourceManager sDefaultImpl;
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

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void registerClientProfile(ResourceClientProfile profile, IResourcesReclaimListener listener, int[] clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (clientId == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(clientId.length);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(clientId);
                        return;
                    }
                    Stub.getDefaultImpl().registerClientProfile(profile, listener, clientId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void unregisterClientProfile(int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterClientProfile(clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean updateClientPriority(int clientId, int priority, int niceValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(clientId);
                    _data.writeInt(priority);
                    _data.writeInt(niceValue);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateClientPriority(clientId, priority, niceValue);
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

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void setFrontendInfoList(TunerFrontendInfo[] infos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedArray(infos, 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setFrontendInfoList(infos);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void updateCasInfo(int casSystemId, int maxSessionNum) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(casSystemId);
                    _data.writeInt(maxSessionNum);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateCasInfo(casSystemId, maxSessionNum);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void setLnbInfoList(int[] lnbIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeIntArray(lnbIds);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLnbInfoList(lnbIds);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestFrontend(TunerFrontendRequest request, int[] frontendHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (frontendHandle == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(frontendHandle.length);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestFrontend(request, frontendHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.readIntArray(frontendHandle);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void shareFrontend(int selfClientId, int targetClientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(selfClientId);
                    _data.writeInt(targetClientId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().shareFrontend(selfClientId, targetClientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestDemux(TunerDemuxRequest request, int[] demuxHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (demuxHandle == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(demuxHandle.length);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestDemux(request, demuxHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.readIntArray(demuxHandle);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestDescrambler(TunerDescramblerRequest request, int[] descramblerHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (descramblerHandle == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(descramblerHandle.length);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestDescrambler(request, descramblerHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.readIntArray(descramblerHandle);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestCasSession(CasSessionRequest request, int[] casSessionHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (casSessionHandle == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(casSessionHandle.length);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestCasSession(request, casSessionHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.readIntArray(casSessionHandle);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestCiCam(TunerCiCamRequest request, int[] ciCamHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (ciCamHandle == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(ciCamHandle.length);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestCiCam(request, ciCamHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.readIntArray(ciCamHandle);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestLnb(TunerLnbRequest request, int[] lnbHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (lnbHandle == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(lnbHandle.length);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestLnb(request, lnbHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.readIntArray(lnbHandle);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseFrontend(int frontendHandle, int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(frontendHandle);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseFrontend(frontendHandle, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseDemux(int demuxHandle, int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(demuxHandle);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseDemux(demuxHandle, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseDescrambler(int descramblerHandle, int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(descramblerHandle);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseDescrambler(descramblerHandle, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseCasSession(int casSessionHandle, int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(casSessionHandle);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseCasSession(casSessionHandle, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseCiCam(int ciCamHandle, int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(ciCamHandle);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseCiCam(ciCamHandle, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseLnb(int lnbHandle, int clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(lnbHandle);
                    _data.writeInt(clientId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseLnb(lnbHandle, clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean isHigherPriority(ResourceClientProfile challengerProfile, ResourceClientProfile holderProfile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = true;
                    if (challengerProfile != null) {
                        _data.writeInt(1);
                        challengerProfile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (holderProfile != null) {
                        _data.writeInt(1);
                        holderProfile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHigherPriority(challengerProfile, holderProfile);
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
        }

        public static boolean setDefaultImpl(ITunerResourceManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITunerResourceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
