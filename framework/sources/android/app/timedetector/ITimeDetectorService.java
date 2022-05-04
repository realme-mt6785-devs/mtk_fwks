package android.app.timedetector;

import android.app.time.ExternalTimeSuggestion;
import android.app.time.TimeCapabilitiesAndConfig;
import android.app.time.TimeConfiguration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ITimeDetectorService extends IInterface {
    public static final String DESCRIPTOR = "android.app.timedetector.ITimeDetectorService";

    TimeCapabilitiesAndConfig getCapabilitiesAndConfig() throws RemoteException;

    void suggestExternalTime(ExternalTimeSuggestion externalTimeSuggestion) throws RemoteException;

    void suggestGnssTime(GnssTimeSuggestion gnssTimeSuggestion) throws RemoteException;

    boolean suggestManualTime(ManualTimeSuggestion manualTimeSuggestion) throws RemoteException;

    void suggestNetworkTime(NetworkTimeSuggestion networkTimeSuggestion) throws RemoteException;

    void suggestTelephonyTime(TelephonyTimeSuggestion telephonyTimeSuggestion) throws RemoteException;

    boolean updateConfiguration(TimeConfiguration timeConfiguration) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ITimeDetectorService {
        @Override // android.app.timedetector.ITimeDetectorService
        public TimeCapabilitiesAndConfig getCapabilitiesAndConfig() throws RemoteException {
            return null;
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public boolean updateConfiguration(TimeConfiguration timeConfiguration) throws RemoteException {
            return false;
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestExternalTime(ExternalTimeSuggestion timeSuggestion) throws RemoteException {
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestGnssTime(GnssTimeSuggestion timeSuggestion) throws RemoteException {
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public boolean suggestManualTime(ManualTimeSuggestion timeSuggestion) throws RemoteException {
            return false;
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestNetworkTime(NetworkTimeSuggestion timeSuggestion) throws RemoteException {
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestTelephonyTime(TelephonyTimeSuggestion timeSuggestion) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ITimeDetectorService {
        static final int TRANSACTION_getCapabilitiesAndConfig = 1;
        static final int TRANSACTION_suggestExternalTime = 3;
        static final int TRANSACTION_suggestGnssTime = 4;
        static final int TRANSACTION_suggestManualTime = 5;
        static final int TRANSACTION_suggestNetworkTime = 6;
        static final int TRANSACTION_suggestTelephonyTime = 7;
        static final int TRANSACTION_updateConfiguration = 2;

        public Stub() {
            attachInterface(this, ITimeDetectorService.DESCRIPTOR);
        }

        public static ITimeDetectorService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITimeDetectorService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITimeDetectorService)) {
                return new Proxy(obj);
            }
            return (ITimeDetectorService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getCapabilitiesAndConfig";
                case 2:
                    return "updateConfiguration";
                case 3:
                    return "suggestExternalTime";
                case 4:
                    return "suggestGnssTime";
                case 5:
                    return "suggestManualTime";
                case 6:
                    return "suggestNetworkTime";
                case 7:
                    return "suggestTelephonyTime";
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
            TimeConfiguration _arg0;
            ExternalTimeSuggestion _arg02;
            GnssTimeSuggestion _arg03;
            ManualTimeSuggestion _arg04;
            NetworkTimeSuggestion _arg05;
            TelephonyTimeSuggestion _arg06;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITimeDetectorService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            TimeCapabilitiesAndConfig _result = getCapabilitiesAndConfig();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TimeConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean updateConfiguration = updateConfiguration(_arg0);
                            reply.writeNoException();
                            reply.writeInt(updateConfiguration ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ExternalTimeSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            suggestExternalTime(_arg02);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = GnssTimeSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            suggestGnssTime(_arg03);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ManualTimeSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            boolean suggestManualTime = suggestManualTime(_arg04);
                            reply.writeNoException();
                            reply.writeInt(suggestManualTime ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = NetworkTimeSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            suggestNetworkTime(_arg05);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(ITimeDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = TelephonyTimeSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            suggestTelephonyTime(_arg06);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ITimeDetectorService {
            public static ITimeDetectorService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITimeDetectorService.DESCRIPTOR;
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public TimeCapabilitiesAndConfig getCapabilitiesAndConfig() throws RemoteException {
                TimeCapabilitiesAndConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCapabilitiesAndConfig();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TimeCapabilitiesAndConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public boolean updateConfiguration(TimeConfiguration timeConfiguration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    boolean _result = true;
                    if (timeConfiguration != null) {
                        _data.writeInt(1);
                        timeConfiguration.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateConfiguration(timeConfiguration);
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

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestExternalTime(ExternalTimeSuggestion timeSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    if (timeSuggestion != null) {
                        _data.writeInt(1);
                        timeSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().suggestExternalTime(timeSuggestion);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestGnssTime(GnssTimeSuggestion timeSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    if (timeSuggestion != null) {
                        _data.writeInt(1);
                        timeSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().suggestGnssTime(timeSuggestion);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public boolean suggestManualTime(ManualTimeSuggestion timeSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    boolean _result = true;
                    if (timeSuggestion != null) {
                        _data.writeInt(1);
                        timeSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().suggestManualTime(timeSuggestion);
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

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestNetworkTime(NetworkTimeSuggestion timeSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    if (timeSuggestion != null) {
                        _data.writeInt(1);
                        timeSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().suggestNetworkTime(timeSuggestion);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestTelephonyTime(TelephonyTimeSuggestion timeSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeDetectorService.DESCRIPTOR);
                    if (timeSuggestion != null) {
                        _data.writeInt(1);
                        timeSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().suggestTelephonyTime(timeSuggestion);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeDetectorService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITimeDetectorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
