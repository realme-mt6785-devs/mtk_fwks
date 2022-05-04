package android.app;

import android.app.ILocalWallpaperColorConsumer;
import android.app.IWallpaperManagerCallback;
import android.content.ComponentName;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IWallpaperManager extends IInterface {
    void addOnLocalColorsChangedListener(ILocalWallpaperColorConsumer iLocalWallpaperColorConsumer, List<RectF> list, int i, int i2, int i3) throws RemoteException;

    void clearWallpaper(String str, int i, int i2) throws RemoteException;

    int getHeightHint(int i) throws RemoteException;

    String getName() throws RemoteException;

    @Deprecated
    ParcelFileDescriptor getWallpaper(String str, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException;

    WallpaperColors getWallpaperColors(int i, int i2, int i3) throws RemoteException;

    int getWallpaperIdForUser(int i, int i2) throws RemoteException;

    WallpaperInfo getWallpaperInfo(int i) throws RemoteException;

    ParcelFileDescriptor getWallpaperWithFeature(String str, String str2, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException;

    int getWidthHint(int i) throws RemoteException;

    boolean hasNamedWallpaper(String str) throws RemoteException;

    boolean isSetWallpaperAllowed(String str) throws RemoteException;

    boolean isWallpaperBackupEligible(int i, int i2) throws RemoteException;

    boolean isWallpaperSupported(String str) throws RemoteException;

    void notifyGoingToSleep(int i, int i2, Bundle bundle) throws RemoteException;

    void notifyWakingUp(int i, int i2, Bundle bundle) throws RemoteException;

    void registerWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException;

    void removeOnLocalColorsChangedListener(ILocalWallpaperColorConsumer iLocalWallpaperColorConsumer, List<RectF> list, int i, int i2, int i3) throws RemoteException;

    void setDimensionHints(int i, int i2, String str, int i3) throws RemoteException;

    void setDisplayPadding(Rect rect, String str, int i) throws RemoteException;

    void setInAmbientMode(boolean z, long j) throws RemoteException;

    boolean setLockWallpaperCallback(IWallpaperManagerCallback iWallpaperManagerCallback) throws RemoteException;

    ParcelFileDescriptor setWallpaper(String str, String str2, Rect rect, boolean z, Bundle bundle, int i, IWallpaperManagerCallback iWallpaperManagerCallback, int i2) throws RemoteException;

    void setWallpaperComponent(ComponentName componentName) throws RemoteException;

    void setWallpaperComponentChecked(ComponentName componentName, String str, int i) throws RemoteException;

    void settingsRestored() throws RemoteException;

    void unregisterWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IWallpaperManager {
        @Override // android.app.IWallpaperManager
        public ParcelFileDescriptor setWallpaper(String name, String callingPackage, Rect cropHint, boolean allowBackup, Bundle extras, int which, IWallpaperManagerCallback completion, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public void setWallpaperComponentChecked(ComponentName name, String callingPackage, int userId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void setWallpaperComponent(ComponentName name) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public ParcelFileDescriptor getWallpaper(String callingPkg, IWallpaperManagerCallback cb, int which, Bundle outParams, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public ParcelFileDescriptor getWallpaperWithFeature(String callingPkg, String callingFeatureId, IWallpaperManagerCallback cb, int which, Bundle outParams, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public int getWallpaperIdForUser(int which, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public WallpaperInfo getWallpaperInfo(int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public void clearWallpaper(String callingPackage, int which, int userId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public boolean hasNamedWallpaper(String name) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public void setDimensionHints(int width, int height, String callingPackage, int displayId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public int getWidthHint(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public int getHeightHint(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public void setDisplayPadding(Rect padding, String callingPackage, int displayId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public String getName() throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public void settingsRestored() throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public boolean isWallpaperSupported(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public boolean isSetWallpaperAllowed(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public boolean isWallpaperBackupEligible(int which, int userId) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public boolean setLockWallpaperCallback(IWallpaperManagerCallback cb) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public WallpaperColors getWallpaperColors(int which, int userId, int displayId) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public void removeOnLocalColorsChangedListener(ILocalWallpaperColorConsumer callback, List<RectF> area, int which, int userId, int displayId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void addOnLocalColorsChangedListener(ILocalWallpaperColorConsumer callback, List<RectF> regions, int which, int userId, int displayId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void registerWallpaperColorsCallback(IWallpaperManagerCallback cb, int userId, int displayId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback cb, int userId, int displayId) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void setInAmbientMode(boolean inAmbientMode, long animationDuration) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void notifyWakingUp(int x, int y, Bundle extras) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void notifyGoingToSleep(int x, int y, Bundle extras) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IWallpaperManager {
        public static final String DESCRIPTOR = "android.app.IWallpaperManager";
        static final int TRANSACTION_addOnLocalColorsChangedListener = 22;
        static final int TRANSACTION_clearWallpaper = 8;
        static final int TRANSACTION_getHeightHint = 12;
        static final int TRANSACTION_getName = 14;
        static final int TRANSACTION_getWallpaper = 4;
        static final int TRANSACTION_getWallpaperColors = 20;
        static final int TRANSACTION_getWallpaperIdForUser = 6;
        static final int TRANSACTION_getWallpaperInfo = 7;
        static final int TRANSACTION_getWallpaperWithFeature = 5;
        static final int TRANSACTION_getWidthHint = 11;
        static final int TRANSACTION_hasNamedWallpaper = 9;
        static final int TRANSACTION_isSetWallpaperAllowed = 17;
        static final int TRANSACTION_isWallpaperBackupEligible = 18;
        static final int TRANSACTION_isWallpaperSupported = 16;
        static final int TRANSACTION_notifyGoingToSleep = 27;
        static final int TRANSACTION_notifyWakingUp = 26;
        static final int TRANSACTION_registerWallpaperColorsCallback = 23;
        static final int TRANSACTION_removeOnLocalColorsChangedListener = 21;
        static final int TRANSACTION_setDimensionHints = 10;
        static final int TRANSACTION_setDisplayPadding = 13;
        static final int TRANSACTION_setInAmbientMode = 25;
        static final int TRANSACTION_setLockWallpaperCallback = 19;
        static final int TRANSACTION_setWallpaper = 1;
        static final int TRANSACTION_setWallpaperComponent = 3;
        static final int TRANSACTION_setWallpaperComponentChecked = 2;
        static final int TRANSACTION_settingsRestored = 15;
        static final int TRANSACTION_unregisterWallpaperColorsCallback = 24;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWallpaperManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWallpaperManager)) {
                return new Proxy(obj);
            }
            return (IWallpaperManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setWallpaper";
                case 2:
                    return "setWallpaperComponentChecked";
                case 3:
                    return "setWallpaperComponent";
                case 4:
                    return "getWallpaper";
                case 5:
                    return "getWallpaperWithFeature";
                case 6:
                    return "getWallpaperIdForUser";
                case 7:
                    return "getWallpaperInfo";
                case 8:
                    return "clearWallpaper";
                case 9:
                    return "hasNamedWallpaper";
                case 10:
                    return "setDimensionHints";
                case 11:
                    return "getWidthHint";
                case 12:
                    return "getHeightHint";
                case 13:
                    return "setDisplayPadding";
                case 14:
                    return "getName";
                case 15:
                    return "settingsRestored";
                case 16:
                    return "isWallpaperSupported";
                case 17:
                    return "isSetWallpaperAllowed";
                case 18:
                    return "isWallpaperBackupEligible";
                case 19:
                    return "setLockWallpaperCallback";
                case 20:
                    return "getWallpaperColors";
                case 21:
                    return "removeOnLocalColorsChangedListener";
                case 22:
                    return "addOnLocalColorsChangedListener";
                case 23:
                    return "registerWallpaperColorsCallback";
                case 24:
                    return "unregisterWallpaperColorsCallback";
                case 25:
                    return "setInAmbientMode";
                case 26:
                    return "notifyWakingUp";
                case 27:
                    return "notifyGoingToSleep";
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
            Rect _arg2;
            boolean _arg3;
            ComponentName _arg0;
            ComponentName _arg02;
            Rect _arg03;
            Bundle _arg22;
            Bundle _arg23;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg04 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            String _arg1 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            Bundle _arg4 = new Bundle();
                            int _arg5 = data.readInt();
                            IWallpaperManagerCallback _arg6 = IWallpaperManagerCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg7 = data.readInt();
                            ParcelFileDescriptor _result = setWallpaper(_arg05, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            reply.writeInt(1);
                            _arg4.writeToParcel(reply, 1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg12 = data.readString();
                            int _arg24 = data.readInt();
                            setWallpaperComponentChecked(_arg0, _arg12, _arg24);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            setWallpaperComponent(_arg02);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            IWallpaperManagerCallback _arg13 = IWallpaperManagerCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg25 = data.readInt();
                            Bundle _arg32 = new Bundle();
                            ParcelFileDescriptor _result2 = getWallpaper(_arg06, _arg13, _arg25, _arg32, data.readInt());
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            reply.writeInt(1);
                            _arg32.writeToParcel(reply, 1);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _arg14 = data.readString();
                            IWallpaperManagerCallback _arg26 = IWallpaperManagerCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg33 = data.readInt();
                            Bundle _arg42 = new Bundle();
                            int _arg52 = data.readInt();
                            ParcelFileDescriptor _result3 = getWallpaperWithFeature(_arg07, _arg14, _arg26, _arg33, _arg42, _arg52);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            reply.writeInt(1);
                            _arg42.writeToParcel(reply, 1);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg15 = data.readInt();
                            int _result4 = getWallpaperIdForUser(_arg08, _arg15);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            WallpaperInfo _result5 = getWallpaperInfo(_arg09);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            int _arg16 = data.readInt();
                            int _arg27 = data.readInt();
                            clearWallpaper(_arg010, _arg16, _arg27);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            boolean hasNamedWallpaper = hasNamedWallpaper(_arg011);
                            reply.writeNoException();
                            reply.writeInt(hasNamedWallpaper ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg17 = data.readInt();
                            String _arg28 = data.readString();
                            int _arg34 = data.readInt();
                            setDimensionHints(_arg012, _arg17, _arg28, _arg34);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _result6 = getWidthHint(_arg013);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int _result7 = getHeightHint(_arg014);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            String _arg18 = data.readString();
                            int _arg29 = data.readInt();
                            setDisplayPadding(_arg03, _arg18, _arg29);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _result8 = getName();
                            reply.writeNoException();
                            reply.writeString(_result8);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            settingsRestored();
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            boolean isWallpaperSupported = isWallpaperSupported(_arg015);
                            reply.writeNoException();
                            reply.writeInt(isWallpaperSupported ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            boolean isSetWallpaperAllowed = isSetWallpaperAllowed(_arg016);
                            reply.writeNoException();
                            reply.writeInt(isSetWallpaperAllowed ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            int _arg19 = data.readInt();
                            boolean isWallpaperBackupEligible = isWallpaperBackupEligible(_arg017, _arg19);
                            reply.writeNoException();
                            reply.writeInt(isWallpaperBackupEligible ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            IWallpaperManagerCallback _arg018 = IWallpaperManagerCallback.Stub.asInterface(data.readStrongBinder());
                            boolean lockWallpaperCallback = setLockWallpaperCallback(_arg018);
                            reply.writeNoException();
                            reply.writeInt(lockWallpaperCallback ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            int _arg110 = data.readInt();
                            int _arg210 = data.readInt();
                            WallpaperColors _result9 = getWallpaperColors(_arg019, _arg110, _arg210);
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            ILocalWallpaperColorConsumer _arg020 = ILocalWallpaperColorConsumer.Stub.asInterface(data.readStrongBinder());
                            List<RectF> _arg111 = data.createTypedArrayList(RectF.CREATOR);
                            int _arg211 = data.readInt();
                            int _arg35 = data.readInt();
                            removeOnLocalColorsChangedListener(_arg020, _arg111, _arg211, _arg35, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            ILocalWallpaperColorConsumer _arg021 = ILocalWallpaperColorConsumer.Stub.asInterface(data.readStrongBinder());
                            List<RectF> _arg112 = data.createTypedArrayList(RectF.CREATOR);
                            int _arg212 = data.readInt();
                            int _arg36 = data.readInt();
                            addOnLocalColorsChangedListener(_arg021, _arg112, _arg212, _arg36, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            IWallpaperManagerCallback _arg022 = IWallpaperManagerCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg113 = data.readInt();
                            int _arg213 = data.readInt();
                            registerWallpaperColorsCallback(_arg022, _arg113, _arg213);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            IWallpaperManagerCallback _arg023 = IWallpaperManagerCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg114 = data.readInt();
                            int _arg214 = data.readInt();
                            unregisterWallpaperColorsCallback(_arg023, _arg114, _arg214);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            long _arg115 = data.readLong();
                            setInAmbientMode(_arg04, _arg115);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            int _arg116 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            notifyWakingUp(_arg024, _arg116, _arg22);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            int _arg117 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg23 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            notifyGoingToSleep(_arg025, _arg117, _arg23);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IWallpaperManager {
            public static IWallpaperManager sDefaultImpl;
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

            @Override // android.app.IWallpaperManager
            public ParcelFileDescriptor setWallpaper(String name, String callingPackage, Rect cropHint, boolean allowBackup, Bundle extras, int which, IWallpaperManagerCallback completion, int userId) throws RemoteException {
                Throwable th;
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(name);
                        try {
                            _data.writeString(callingPackage);
                            if (cropHint != null) {
                                _data.writeInt(1);
                                cropHint.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            _data.writeInt(allowBackup ? 1 : 0);
                            try {
                                _data.writeInt(which);
                                _data.writeStrongBinder(completion != null ? completion.asBinder() : null);
                                _data.writeInt(userId);
                                boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() != 0) {
                                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                                    } else {
                                        _result = null;
                                    }
                                    if (_reply.readInt() != 0) {
                                        try {
                                            extras.readFromParcel(_reply);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            _reply.recycle();
                                            _data.recycle();
                                            throw th;
                                        }
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                ParcelFileDescriptor wallpaper = Stub.getDefaultImpl().setWallpaper(name, callingPackage, cropHint, allowBackup, extras, which, completion, userId);
                                _reply.recycle();
                                _data.recycle();
                                return wallpaper;
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.app.IWallpaperManager
            public void setWallpaperComponentChecked(ComponentName name, String callingPackage, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (name != null) {
                        _data.writeInt(1);
                        name.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setWallpaperComponentChecked(name, callingPackage, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setWallpaperComponent(ComponentName name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (name != null) {
                        _data.writeInt(1);
                        name.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setWallpaperComponent(name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public ParcelFileDescriptor getWallpaper(String callingPkg, IWallpaperManagerCallback cb, int which, Bundle outParams, int userId) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaper(callingPkg, cb, which, outParams, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    if (_reply.readInt() != 0) {
                        outParams.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public ParcelFileDescriptor getWallpaperWithFeature(String callingPkg, String callingFeatureId, IWallpaperManagerCallback cb, int which, Bundle outParams, int userId) throws RemoteException {
                Throwable th;
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPkg);
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
                    _data.writeString(callingFeatureId);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    try {
                        _data.writeInt(which);
                        try {
                            _data.writeInt(userId);
                            try {
                                boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() != 0) {
                                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                                    } else {
                                        _result = null;
                                    }
                                    if (_reply.readInt() != 0) {
                                        try {
                                            outParams.readFromParcel(_reply);
                                        } catch (Throwable th4) {
                                            th = th4;
                                            _reply.recycle();
                                            _data.recycle();
                                            throw th;
                                        }
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                ParcelFileDescriptor wallpaperWithFeature = Stub.getDefaultImpl().getWallpaperWithFeature(callingPkg, callingFeatureId, cb, which, outParams, userId);
                                _reply.recycle();
                                _data.recycle();
                                return wallpaperWithFeature;
                            } catch (Throwable th5) {
                                th = th5;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th6) {
                            th = th6;
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

            @Override // android.app.IWallpaperManager
            public int getWallpaperIdForUser(int which, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperIdForUser(which, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public WallpaperInfo getWallpaperInfo(int userId) throws RemoteException {
                WallpaperInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperInfo(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = WallpaperInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void clearWallpaper(String callingPackage, int which, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearWallpaper(callingPackage, which, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean hasNamedWallpaper(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasNamedWallpaper(name);
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

            @Override // android.app.IWallpaperManager
            public void setDimensionHints(int width, int height, String callingPackage, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(width);
                    _data.writeInt(height);
                    _data.writeString(callingPackage);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDimensionHints(width, height, callingPackage, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int getWidthHint(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWidthHint(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int getHeightHint(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHeightHint(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setDisplayPadding(Rect padding, String callingPackage, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (padding != null) {
                        _data.writeInt(1);
                        padding.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDisplayPadding(padding, callingPackage, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public String getName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void settingsRestored() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().settingsRestored();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean isWallpaperSupported(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWallpaperSupported(callingPackage);
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

            @Override // android.app.IWallpaperManager
            public boolean isSetWallpaperAllowed(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSetWallpaperAllowed(callingPackage);
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

            @Override // android.app.IWallpaperManager
            public boolean isWallpaperBackupEligible(int which, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWallpaperBackupEligible(which, userId);
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

            @Override // android.app.IWallpaperManager
            public boolean setLockWallpaperCallback(IWallpaperManagerCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLockWallpaperCallback(cb);
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

            @Override // android.app.IWallpaperManager
            public WallpaperColors getWallpaperColors(int which, int userId, int displayId) throws RemoteException {
                WallpaperColors _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperColors(which, userId, displayId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = WallpaperColors.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void removeOnLocalColorsChangedListener(ILocalWallpaperColorConsumer callback, List<RectF> area, int which, int userId, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeTypedList(area);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeOnLocalColorsChangedListener(callback, area, which, userId, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void addOnLocalColorsChangedListener(ILocalWallpaperColorConsumer callback, List<RectF> regions, int which, int userId, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeTypedList(regions);
                    _data.writeInt(which);
                    _data.writeInt(userId);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addOnLocalColorsChangedListener(callback, regions, which, userId, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void registerWallpaperColorsCallback(IWallpaperManagerCallback cb, int userId, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeInt(userId);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerWallpaperColorsCallback(cb, userId, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback cb, int userId, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeInt(userId);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterWallpaperColorsCallback(cb, userId, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setInAmbientMode(boolean inAmbientMode, long animationDuration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(inAmbientMode ? 1 : 0);
                    _data.writeLong(animationDuration);
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInAmbientMode(inAmbientMode, animationDuration);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void notifyWakingUp(int x, int y, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyWakingUp(x, y, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void notifyGoingToSleep(int x, int y, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyGoingToSleep(x, y, extras);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWallpaperManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWallpaperManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
