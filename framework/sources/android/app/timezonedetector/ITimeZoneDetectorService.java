package android.app.timezonedetector;

import android.app.time.ITimeZoneDetectorListener;
import android.app.time.TimeZoneCapabilitiesAndConfig;
import android.app.time.TimeZoneConfiguration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ITimeZoneDetectorService extends IInterface {
    public static final String DESCRIPTOR = "android.app.timezonedetector.ITimeZoneDetectorService";

    void addListener(ITimeZoneDetectorListener iTimeZoneDetectorListener) throws RemoteException;

    TimeZoneCapabilitiesAndConfig getCapabilitiesAndConfig() throws RemoteException;

    void removeListener(ITimeZoneDetectorListener iTimeZoneDetectorListener) throws RemoteException;

    boolean suggestManualTimeZone(ManualTimeZoneSuggestion manualTimeZoneSuggestion) throws RemoteException;

    void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion) throws RemoteException;

    boolean updateConfiguration(TimeZoneConfiguration timeZoneConfiguration) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ITimeZoneDetectorService {
        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public TimeZoneCapabilitiesAndConfig getCapabilitiesAndConfig() throws RemoteException {
            return null;
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public void addListener(ITimeZoneDetectorListener listener) throws RemoteException {
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public void removeListener(ITimeZoneDetectorListener listener) throws RemoteException {
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public boolean updateConfiguration(TimeZoneConfiguration configuration) throws RemoteException {
            return false;
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public boolean suggestManualTimeZone(ManualTimeZoneSuggestion timeZoneSuggestion) throws RemoteException {
            return false;
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion timeZoneSuggestion) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ITimeZoneDetectorService {
        static final int TRANSACTION_addListener = 2;
        static final int TRANSACTION_getCapabilitiesAndConfig = 1;
        static final int TRANSACTION_removeListener = 3;
        static final int TRANSACTION_suggestManualTimeZone = 5;
        static final int TRANSACTION_suggestTelephonyTimeZone = 6;
        static final int TRANSACTION_updateConfiguration = 4;

        public Stub() {
            attachInterface(this, ITimeZoneDetectorService.DESCRIPTOR);
        }

        public static ITimeZoneDetectorService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITimeZoneDetectorService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITimeZoneDetectorService)) {
                return new Proxy(obj);
            }
            return (ITimeZoneDetectorService) iin;
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
                    return "addListener";
                case 3:
                    return "removeListener";
                case 4:
                    return "updateConfiguration";
                case 5:
                    return "suggestManualTimeZone";
                case 6:
                    return "suggestTelephonyTimeZone";
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
            TimeZoneConfiguration _arg0;
            ManualTimeZoneSuggestion _arg02;
            TelephonyTimeZoneSuggestion _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITimeZoneDetectorService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITimeZoneDetectorService.DESCRIPTOR);
                            TimeZoneCapabilitiesAndConfig _result = getCapabilitiesAndConfig();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(ITimeZoneDetectorService.DESCRIPTOR);
                            ITimeZoneDetectorListener _arg04 = ITimeZoneDetectorListener.Stub.asInterface(data.readStrongBinder());
                            addListener(_arg04);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ITimeZoneDetectorService.DESCRIPTOR);
                            ITimeZoneDetectorListener _arg05 = ITimeZoneDetectorListener.Stub.asInterface(data.readStrongBinder());
                            removeListener(_arg05);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ITimeZoneDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TimeZoneConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean updateConfiguration = updateConfiguration(_arg0);
                            reply.writeNoException();
                            reply.writeInt(updateConfiguration ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(ITimeZoneDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ManualTimeZoneSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            boolean suggestManualTimeZone = suggestManualTimeZone(_arg02);
                            reply.writeNoException();
                            reply.writeInt(suggestManualTimeZone ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(ITimeZoneDetectorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = TelephonyTimeZoneSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            suggestTelephonyTimeZone(_arg03);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ITimeZoneDetectorService {
            public static ITimeZoneDetectorService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITimeZoneDetectorService.DESCRIPTOR;
            }

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public TimeZoneCapabilitiesAndConfig getCapabilitiesAndConfig() throws RemoteException {
                TimeZoneCapabilitiesAndConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneDetectorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCapabilitiesAndConfig();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TimeZoneCapabilitiesAndConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public void addListener(ITimeZoneDetectorListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneDetectorService.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public void removeListener(ITimeZoneDetectorListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneDetectorService.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public boolean updateConfiguration(TimeZoneConfiguration configuration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneDetectorService.DESCRIPTOR);
                    boolean _result = true;
                    if (configuration != null) {
                        _data.writeInt(1);
                        configuration.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateConfiguration(configuration);
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

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public boolean suggestManualTimeZone(ManualTimeZoneSuggestion timeZoneSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneDetectorService.DESCRIPTOR);
                    boolean _result = true;
                    if (timeZoneSuggestion != null) {
                        _data.writeInt(1);
                        timeZoneSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().suggestManualTimeZone(timeZoneSuggestion);
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

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion timeZoneSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneDetectorService.DESCRIPTOR);
                    if (timeZoneSuggestion != null) {
                        _data.writeInt(1);
                        timeZoneSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().suggestTelephonyTimeZone(timeZoneSuggestion);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeZoneDetectorService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITimeZoneDetectorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
