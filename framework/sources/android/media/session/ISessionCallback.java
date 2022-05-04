package android.media.session;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
/* loaded from: classes2.dex */
public interface ISessionCallback extends IInterface {
    void onAdjustVolume(String str, int i, int i2, int i3) throws RemoteException;

    void onCommand(String str, int i, int i2, String str2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException;

    void onCustomAction(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onFastForward(String str, int i, int i2) throws RemoteException;

    void onMediaButton(String str, int i, int i2, Intent intent, int i3, ResultReceiver resultReceiver) throws RemoteException;

    void onMediaButtonFromController(String str, int i, int i2, Intent intent) throws RemoteException;

    void onNext(String str, int i, int i2) throws RemoteException;

    void onPause(String str, int i, int i2) throws RemoteException;

    void onPlay(String str, int i, int i2) throws RemoteException;

    void onPlayFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPlayFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPlayFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException;

    void onPrepare(String str, int i, int i2) throws RemoteException;

    void onPrepareFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPrepareFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPrepareFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException;

    void onPrevious(String str, int i, int i2) throws RemoteException;

    void onRate(String str, int i, int i2, Rating rating) throws RemoteException;

    void onRewind(String str, int i, int i2) throws RemoteException;

    void onSeekTo(String str, int i, int i2, long j) throws RemoteException;

    void onSetPlaybackSpeed(String str, int i, int i2, float f) throws RemoteException;

    void onSetVolumeTo(String str, int i, int i2, int i3) throws RemoteException;

    void onSkipToTrack(String str, int i, int i2, long j) throws RemoteException;

    void onStop(String str, int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ISessionCallback {
        @Override // android.media.session.ISessionCallback
        public void onCommand(String packageName, int pid, int uid, String command, Bundle args, ResultReceiver cb) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onMediaButton(String packageName, int pid, int uid, Intent mediaButtonIntent, int sequenceNumber, ResultReceiver cb) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onMediaButtonFromController(String packageName, int pid, int uid, Intent mediaButtonIntent) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepare(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepareFromMediaId(String packageName, int pid, int uid, String mediaId, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepareFromSearch(String packageName, int pid, int uid, String query, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepareFromUri(String packageName, int pid, int uid, Uri uri, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlay(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlayFromMediaId(String packageName, int pid, int uid, String mediaId, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlayFromSearch(String packageName, int pid, int uid, String query, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlayFromUri(String packageName, int pid, int uid, Uri uri, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSkipToTrack(String packageName, int pid, int uid, long id) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPause(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onStop(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onNext(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrevious(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onFastForward(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onRewind(String packageName, int pid, int uid) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSeekTo(String packageName, int pid, int uid, long pos) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onRate(String packageName, int pid, int uid, Rating rating) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSetPlaybackSpeed(String packageName, int pid, int uid, float speed) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onCustomAction(String packageName, int pid, int uid, String action, Bundle args) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onAdjustVolume(String packageName, int pid, int uid, int direction) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSetVolumeTo(String packageName, int pid, int uid, int value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISessionCallback {
        public static final String DESCRIPTOR = "android.media.session.ISessionCallback";
        static final int TRANSACTION_onAdjustVolume = 23;
        static final int TRANSACTION_onCommand = 1;
        static final int TRANSACTION_onCustomAction = 22;
        static final int TRANSACTION_onFastForward = 17;
        static final int TRANSACTION_onMediaButton = 2;
        static final int TRANSACTION_onMediaButtonFromController = 3;
        static final int TRANSACTION_onNext = 15;
        static final int TRANSACTION_onPause = 13;
        static final int TRANSACTION_onPlay = 8;
        static final int TRANSACTION_onPlayFromMediaId = 9;
        static final int TRANSACTION_onPlayFromSearch = 10;
        static final int TRANSACTION_onPlayFromUri = 11;
        static final int TRANSACTION_onPrepare = 4;
        static final int TRANSACTION_onPrepareFromMediaId = 5;
        static final int TRANSACTION_onPrepareFromSearch = 6;
        static final int TRANSACTION_onPrepareFromUri = 7;
        static final int TRANSACTION_onPrevious = 16;
        static final int TRANSACTION_onRate = 20;
        static final int TRANSACTION_onRewind = 18;
        static final int TRANSACTION_onSeekTo = 19;
        static final int TRANSACTION_onSetPlaybackSpeed = 21;
        static final int TRANSACTION_onSetVolumeTo = 24;
        static final int TRANSACTION_onSkipToTrack = 12;
        static final int TRANSACTION_onStop = 14;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISessionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISessionCallback)) {
                return new Proxy(obj);
            }
            return (ISessionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCommand";
                case 2:
                    return "onMediaButton";
                case 3:
                    return "onMediaButtonFromController";
                case 4:
                    return "onPrepare";
                case 5:
                    return "onPrepareFromMediaId";
                case 6:
                    return "onPrepareFromSearch";
                case 7:
                    return "onPrepareFromUri";
                case 8:
                    return "onPlay";
                case 9:
                    return "onPlayFromMediaId";
                case 10:
                    return "onPlayFromSearch";
                case 11:
                    return "onPlayFromUri";
                case 12:
                    return "onSkipToTrack";
                case 13:
                    return "onPause";
                case 14:
                    return "onStop";
                case 15:
                    return "onNext";
                case 16:
                    return "onPrevious";
                case 17:
                    return "onFastForward";
                case 18:
                    return "onRewind";
                case 19:
                    return "onSeekTo";
                case 20:
                    return "onRate";
                case 21:
                    return "onSetPlaybackSpeed";
                case 22:
                    return "onCustomAction";
                case 23:
                    return "onAdjustVolume";
                case 24:
                    return "onSetVolumeTo";
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
            Bundle _arg4;
            ResultReceiver _arg5;
            Intent _arg3;
            ResultReceiver _arg52;
            Intent _arg32;
            Bundle _arg42;
            Bundle _arg43;
            Uri _arg33;
            Bundle _arg44;
            Bundle _arg45;
            Bundle _arg46;
            Uri _arg34;
            Bundle _arg47;
            Rating _arg35;
            Bundle _arg48;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            String _arg36 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            onCommand(_arg0, _arg1, _arg2, _arg36, _arg4, _arg5);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            int _arg22 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            int _arg49 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg52 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg52 = null;
                            }
                            onMediaButton(_arg02, _arg12, _arg22, _arg3, _arg49, _arg52);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg23 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg32 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            onMediaButtonFromController(_arg03, _arg13, _arg23, _arg32);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            int _arg14 = data.readInt();
                            int _arg24 = data.readInt();
                            onPrepare(_arg04, _arg14, _arg24);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg15 = data.readInt();
                            int _arg25 = data.readInt();
                            String _arg37 = data.readString();
                            if (data.readInt() != 0) {
                                _arg42 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            onPrepareFromMediaId(_arg05, _arg15, _arg25, _arg37, _arg42);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            int _arg16 = data.readInt();
                            int _arg26 = data.readInt();
                            String _arg38 = data.readString();
                            if (data.readInt() != 0) {
                                _arg43 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg43 = null;
                            }
                            onPrepareFromSearch(_arg06, _arg16, _arg26, _arg38, _arg43);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg17 = data.readInt();
                            int _arg27 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg33 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg44 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg44 = null;
                            }
                            onPrepareFromUri(_arg07, _arg17, _arg27, _arg33, _arg44);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            int _arg18 = data.readInt();
                            int _arg28 = data.readInt();
                            onPlay(_arg08, _arg18, _arg28);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _arg19 = data.readInt();
                            int _arg29 = data.readInt();
                            String _arg39 = data.readString();
                            if (data.readInt() != 0) {
                                _arg45 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg45 = null;
                            }
                            onPlayFromMediaId(_arg09, _arg19, _arg29, _arg39, _arg45);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            int _arg110 = data.readInt();
                            int _arg210 = data.readInt();
                            String _arg310 = data.readString();
                            if (data.readInt() != 0) {
                                _arg46 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg46 = null;
                            }
                            onPlayFromSearch(_arg010, _arg110, _arg210, _arg310, _arg46);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            int _arg111 = data.readInt();
                            int _arg211 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg34 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg47 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg47 = null;
                            }
                            onPlayFromUri(_arg011, _arg111, _arg211, _arg34, _arg47);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg112 = data.readInt();
                            int _arg212 = data.readInt();
                            long _arg311 = data.readLong();
                            onSkipToTrack(_arg012, _arg112, _arg212, _arg311);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            int _arg113 = data.readInt();
                            int _arg213 = data.readInt();
                            onPause(_arg013, _arg113, _arg213);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            int _arg114 = data.readInt();
                            int _arg214 = data.readInt();
                            onStop(_arg014, _arg114, _arg214);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            int _arg115 = data.readInt();
                            int _arg215 = data.readInt();
                            onNext(_arg015, _arg115, _arg215);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            int _arg116 = data.readInt();
                            int _arg216 = data.readInt();
                            onPrevious(_arg016, _arg116, _arg216);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            int _arg117 = data.readInt();
                            int _arg217 = data.readInt();
                            onFastForward(_arg017, _arg117, _arg217);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            int _arg118 = data.readInt();
                            int _arg218 = data.readInt();
                            onRewind(_arg018, _arg118, _arg218);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            int _arg119 = data.readInt();
                            int _arg219 = data.readInt();
                            long _arg312 = data.readLong();
                            onSeekTo(_arg019, _arg119, _arg219, _arg312);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            int _arg120 = data.readInt();
                            int _arg220 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg35 = Rating.CREATOR.createFromParcel(data);
                            } else {
                                _arg35 = null;
                            }
                            onRate(_arg020, _arg120, _arg220, _arg35);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            int _arg121 = data.readInt();
                            int _arg221 = data.readInt();
                            float _arg313 = data.readFloat();
                            onSetPlaybackSpeed(_arg021, _arg121, _arg221, _arg313);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            int _arg122 = data.readInt();
                            int _arg222 = data.readInt();
                            String _arg314 = data.readString();
                            if (data.readInt() != 0) {
                                _arg48 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg48 = null;
                            }
                            onCustomAction(_arg022, _arg122, _arg222, _arg314, _arg48);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            int _arg123 = data.readInt();
                            int _arg223 = data.readInt();
                            int _arg315 = data.readInt();
                            onAdjustVolume(_arg023, _arg123, _arg223, _arg315);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            int _arg124 = data.readInt();
                            int _arg224 = data.readInt();
                            int _arg316 = data.readInt();
                            onSetVolumeTo(_arg024, _arg124, _arg224, _arg316);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISessionCallback {
            public static ISessionCallback sDefaultImpl;
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

            @Override // android.media.session.ISessionCallback
            public void onCommand(String packageName, int pid, int uid, String command, Bundle args, ResultReceiver cb) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(packageName);
                        try {
                            _data.writeInt(pid);
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeInt(uid);
                    try {
                        _data.writeString(command);
                        if (args != null) {
                            _data.writeInt(1);
                            args.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (cb != null) {
                            _data.writeInt(1);
                            cb.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(1, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().onCommand(packageName, pid, uid, command, args, cb);
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onMediaButton(String packageName, int pid, int uid, Intent mediaButtonIntent, int sequenceNumber, ResultReceiver cb) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(packageName);
                        try {
                            _data.writeInt(pid);
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeInt(uid);
                    if (mediaButtonIntent != null) {
                        _data.writeInt(1);
                        mediaButtonIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(sequenceNumber);
                        if (cb != null) {
                            _data.writeInt(1);
                            cb.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(2, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().onMediaButton(packageName, pid, uid, mediaButtonIntent, sequenceNumber, cb);
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onMediaButtonFromController(String packageName, int pid, int uid, Intent mediaButtonIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    if (mediaButtonIntent != null) {
                        _data.writeInt(1);
                        mediaButtonIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaButtonFromController(packageName, pid, uid, mediaButtonIntent);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepare(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrepare(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepareFromMediaId(String packageName, int pid, int uid, String mediaId, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeString(mediaId);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrepareFromMediaId(packageName, pid, uid, mediaId, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepareFromSearch(String packageName, int pid, int uid, String query, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeString(query);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrepareFromSearch(packageName, pid, uid, query, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepareFromUri(String packageName, int pid, int uid, Uri uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrepareFromUri(packageName, pid, uid, uri, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlay(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlay(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlayFromMediaId(String packageName, int pid, int uid, String mediaId, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeString(mediaId);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayFromMediaId(packageName, pid, uid, mediaId, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlayFromSearch(String packageName, int pid, int uid, String query, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeString(query);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayFromSearch(packageName, pid, uid, query, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlayFromUri(String packageName, int pid, int uid, Uri uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayFromUri(packageName, pid, uid, uri, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSkipToTrack(String packageName, int pid, int uid, long id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeLong(id);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSkipToTrack(packageName, pid, uid, id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPause(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPause(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onStop(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStop(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onNext(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNext(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrevious(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrevious(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onFastForward(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFastForward(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onRewind(String packageName, int pid, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRewind(packageName, pid, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSeekTo(String packageName, int pid, int uid, long pos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeLong(pos);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSeekTo(packageName, pid, uid, pos);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onRate(String packageName, int pid, int uid, Rating rating) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    if (rating != null) {
                        _data.writeInt(1);
                        rating.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRate(packageName, pid, uid, rating);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSetPlaybackSpeed(String packageName, int pid, int uid, float speed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeFloat(speed);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetPlaybackSpeed(packageName, pid, uid, speed);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onCustomAction(String packageName, int pid, int uid, String action, Bundle args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeString(action);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCustomAction(packageName, pid, uid, action, args);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onAdjustVolume(String packageName, int pid, int uid, int direction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeInt(direction);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdjustVolume(packageName, pid, uid, direction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSetVolumeTo(String packageName, int pid, int uid, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(pid);
                    _data.writeInt(uid);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetVolumeTo(packageName, pid, uid, value);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISessionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISessionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
