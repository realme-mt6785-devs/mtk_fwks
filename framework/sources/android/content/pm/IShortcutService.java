package android.content.pm;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.internal.infra.AndroidFuture;
import java.util.List;
/* loaded from: classes.dex */
public interface IShortcutService extends IInterface {
    AndroidFuture addDynamicShortcuts(String str, ParceledListSlice parceledListSlice, int i) throws RemoteException;

    AndroidFuture applyRestore(byte[] bArr, int i) throws RemoteException;

    AndroidFuture<Intent> createShortcutResultIntent(String str, ShortcutInfo shortcutInfo, int i) throws RemoteException;

    AndroidFuture disableShortcuts(String str, List list, CharSequence charSequence, int i, int i2) throws RemoteException;

    AndroidFuture enableShortcuts(String str, List list, int i) throws RemoteException;

    byte[] getBackupPayload(int i) throws RemoteException;

    int getIconMaxDimensions(String str, int i) throws RemoteException;

    int getMaxShortcutCountPerActivity(String str, int i) throws RemoteException;

    long getRateLimitResetTime(String str, int i) throws RemoteException;

    int getRemainingCallCount(String str, int i) throws RemoteException;

    AndroidFuture<ParceledListSlice> getShareTargets(String str, IntentFilter intentFilter, int i) throws RemoteException;

    AndroidFuture<ParceledListSlice> getShortcuts(String str, int i, int i2) throws RemoteException;

    boolean hasShareTargets(String str, String str2, int i) throws RemoteException;

    boolean isRequestPinItemSupported(int i, int i2) throws RemoteException;

    AndroidFuture onApplicationActive(String str, int i) throws RemoteException;

    AndroidFuture pushDynamicShortcut(String str, ShortcutInfo shortcutInfo, int i) throws RemoteException;

    AndroidFuture removeAllDynamicShortcuts(String str, int i) throws RemoteException;

    AndroidFuture removeDynamicShortcuts(String str, List list, int i) throws RemoteException;

    AndroidFuture removeLongLivedShortcuts(String str, List list, int i) throws RemoteException;

    AndroidFuture reportShortcutUsed(String str, String str2, int i) throws RemoteException;

    AndroidFuture requestPinShortcut(String str, ShortcutInfo shortcutInfo, IntentSender intentSender, int i) throws RemoteException;

    void resetThrottling() throws RemoteException;

    AndroidFuture setDynamicShortcuts(String str, ParceledListSlice parceledListSlice, int i) throws RemoteException;

    AndroidFuture updateShortcuts(String str, ParceledListSlice parceledListSlice, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IShortcutService {
        @Override // android.content.pm.IShortcutService
        public AndroidFuture setDynamicShortcuts(String packageName, ParceledListSlice shortcutInfoList, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture addDynamicShortcuts(String packageName, ParceledListSlice shortcutInfoList, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture removeDynamicShortcuts(String packageName, List shortcutIds, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture removeAllDynamicShortcuts(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture updateShortcuts(String packageName, ParceledListSlice shortcuts, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture requestPinShortcut(String packageName, ShortcutInfo shortcut, IntentSender resultIntent, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture<Intent> createShortcutResultIntent(String packageName, ShortcutInfo shortcut, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture disableShortcuts(String packageName, List shortcutIds, CharSequence disabledMessage, int disabledMessageResId, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture enableShortcuts(String packageName, List shortcutIds, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public int getMaxShortcutCountPerActivity(String packageName, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IShortcutService
        public int getRemainingCallCount(String packageName, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IShortcutService
        public long getRateLimitResetTime(String packageName, int userId) throws RemoteException {
            return 0L;
        }

        @Override // android.content.pm.IShortcutService
        public int getIconMaxDimensions(String packageName, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture reportShortcutUsed(String packageName, String shortcutId, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public void resetThrottling() throws RemoteException {
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture onApplicationActive(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public byte[] getBackupPayload(int user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture applyRestore(byte[] payload, int user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public boolean isRequestPinItemSupported(int user, int requestType) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture<ParceledListSlice> getShareTargets(String packageName, IntentFilter filter, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public boolean hasShareTargets(String packageName, String packageToCheck, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture removeLongLivedShortcuts(String packageName, List shortcutIds, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture<ParceledListSlice> getShortcuts(String packageName, int matchFlags, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IShortcutService
        public AndroidFuture pushDynamicShortcut(String packageName, ShortcutInfo shortcut, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IShortcutService {
        public static final String DESCRIPTOR = "android.content.pm.IShortcutService";
        static final int TRANSACTION_addDynamicShortcuts = 2;
        static final int TRANSACTION_applyRestore = 18;
        static final int TRANSACTION_createShortcutResultIntent = 7;
        static final int TRANSACTION_disableShortcuts = 8;
        static final int TRANSACTION_enableShortcuts = 9;
        static final int TRANSACTION_getBackupPayload = 17;
        static final int TRANSACTION_getIconMaxDimensions = 13;
        static final int TRANSACTION_getMaxShortcutCountPerActivity = 10;
        static final int TRANSACTION_getRateLimitResetTime = 12;
        static final int TRANSACTION_getRemainingCallCount = 11;
        static final int TRANSACTION_getShareTargets = 20;
        static final int TRANSACTION_getShortcuts = 23;
        static final int TRANSACTION_hasShareTargets = 21;
        static final int TRANSACTION_isRequestPinItemSupported = 19;
        static final int TRANSACTION_onApplicationActive = 16;
        static final int TRANSACTION_pushDynamicShortcut = 24;
        static final int TRANSACTION_removeAllDynamicShortcuts = 4;
        static final int TRANSACTION_removeDynamicShortcuts = 3;
        static final int TRANSACTION_removeLongLivedShortcuts = 22;
        static final int TRANSACTION_reportShortcutUsed = 14;
        static final int TRANSACTION_requestPinShortcut = 6;
        static final int TRANSACTION_resetThrottling = 15;
        static final int TRANSACTION_setDynamicShortcuts = 1;
        static final int TRANSACTION_updateShortcuts = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IShortcutService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IShortcutService)) {
                return new Proxy(obj);
            }
            return (IShortcutService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setDynamicShortcuts";
                case 2:
                    return "addDynamicShortcuts";
                case 3:
                    return "removeDynamicShortcuts";
                case 4:
                    return "removeAllDynamicShortcuts";
                case 5:
                    return "updateShortcuts";
                case 6:
                    return "requestPinShortcut";
                case 7:
                    return "createShortcutResultIntent";
                case 8:
                    return "disableShortcuts";
                case 9:
                    return "enableShortcuts";
                case 10:
                    return "getMaxShortcutCountPerActivity";
                case 11:
                    return "getRemainingCallCount";
                case 12:
                    return "getRateLimitResetTime";
                case 13:
                    return "getIconMaxDimensions";
                case 14:
                    return "reportShortcutUsed";
                case 15:
                    return "resetThrottling";
                case 16:
                    return "onApplicationActive";
                case 17:
                    return "getBackupPayload";
                case 18:
                    return "applyRestore";
                case 19:
                    return "isRequestPinItemSupported";
                case 20:
                    return "getShareTargets";
                case 21:
                    return "hasShareTargets";
                case 22:
                    return "removeLongLivedShortcuts";
                case 23:
                    return "getShortcuts";
                case 24:
                    return "pushDynamicShortcut";
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
            ParceledListSlice _arg1;
            ParceledListSlice _arg12;
            ParceledListSlice _arg13;
            ShortcutInfo _arg14;
            IntentSender _arg2;
            ShortcutInfo _arg15;
            CharSequence _arg22;
            IntentFilter _arg16;
            ShortcutInfo _arg17;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg23 = data.readInt();
                            AndroidFuture _result = setDynamicShortcuts(_arg0, _arg1, _arg23);
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
                            String _arg02 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            int _arg24 = data.readInt();
                            AndroidFuture _result2 = addDynamicShortcuts(_arg02, _arg12, _arg24);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            ClassLoader cl = getClass().getClassLoader();
                            List _arg18 = data.readArrayList(cl);
                            int _arg25 = data.readInt();
                            AndroidFuture _result3 = removeDynamicShortcuts(_arg03, _arg18, _arg25);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            int _arg19 = data.readInt();
                            AndroidFuture _result4 = removeAllDynamicShortcuts(_arg04, _arg19);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            int _arg26 = data.readInt();
                            AndroidFuture _result5 = updateShortcuts(_arg05, _arg13, _arg26);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = ShortcutInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _arg3 = data.readInt();
                            AndroidFuture _result6 = requestPinShortcut(_arg06, _arg14, _arg2, _arg3);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = ShortcutInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            int _arg27 = data.readInt();
                            AndroidFuture<Intent> _result7 = createShortcutResultIntent(_arg07, _arg15, _arg27);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            ClassLoader cl2 = getClass().getClassLoader();
                            List _arg110 = data.readArrayList(cl2);
                            if (data.readInt() != 0) {
                                _arg22 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            int _arg32 = data.readInt();
                            int _arg4 = data.readInt();
                            AndroidFuture _result8 = disableShortcuts(_arg08, _arg110, _arg22, _arg32, _arg4);
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                _result8.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            ClassLoader cl3 = getClass().getClassLoader();
                            List _arg111 = data.readArrayList(cl3);
                            int _arg28 = data.readInt();
                            AndroidFuture _result9 = enableShortcuts(_arg09, _arg111, _arg28);
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            int _arg112 = data.readInt();
                            int _result10 = getMaxShortcutCountPerActivity(_arg010, _arg112);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            int _arg113 = data.readInt();
                            int _result11 = getRemainingCallCount(_arg011, _arg113);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg114 = data.readInt();
                            long _result12 = getRateLimitResetTime(_arg012, _arg114);
                            reply.writeNoException();
                            reply.writeLong(_result12);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            int _arg115 = data.readInt();
                            int _result13 = getIconMaxDimensions(_arg013, _arg115);
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            String _arg116 = data.readString();
                            int _arg29 = data.readInt();
                            AndroidFuture _result14 = reportShortcutUsed(_arg014, _arg116, _arg29);
                            reply.writeNoException();
                            if (_result14 != null) {
                                reply.writeInt(1);
                                _result14.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            resetThrottling();
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            int _arg117 = data.readInt();
                            AndroidFuture _result15 = onApplicationActive(_arg015, _arg117);
                            reply.writeNoException();
                            if (_result15 != null) {
                                reply.writeInt(1);
                                _result15.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            byte[] _result16 = getBackupPayload(_arg016);
                            reply.writeNoException();
                            reply.writeByteArray(_result16);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg017 = data.createByteArray();
                            int _arg118 = data.readInt();
                            AndroidFuture _result17 = applyRestore(_arg017, _arg118);
                            reply.writeNoException();
                            if (_result17 != null) {
                                reply.writeInt(1);
                                _result17.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            int _arg119 = data.readInt();
                            boolean isRequestPinItemSupported = isRequestPinItemSupported(_arg018, _arg119);
                            reply.writeNoException();
                            reply.writeInt(isRequestPinItemSupported ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            if (data.readInt() != 0) {
                                _arg16 = IntentFilter.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            int _arg210 = data.readInt();
                            AndroidFuture<ParceledListSlice> _result18 = getShareTargets(_arg019, _arg16, _arg210);
                            reply.writeNoException();
                            if (_result18 != null) {
                                reply.writeInt(1);
                                _result18.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            String _arg120 = data.readString();
                            int _arg211 = data.readInt();
                            boolean hasShareTargets = hasShareTargets(_arg020, _arg120, _arg211);
                            reply.writeNoException();
                            reply.writeInt(hasShareTargets ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            ClassLoader cl4 = getClass().getClassLoader();
                            List _arg121 = data.readArrayList(cl4);
                            int _arg212 = data.readInt();
                            AndroidFuture _result19 = removeLongLivedShortcuts(_arg021, _arg121, _arg212);
                            reply.writeNoException();
                            if (_result19 != null) {
                                reply.writeInt(1);
                                _result19.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            int _arg122 = data.readInt();
                            int _arg213 = data.readInt();
                            AndroidFuture<ParceledListSlice> _result20 = getShortcuts(_arg022, _arg122, _arg213);
                            reply.writeNoException();
                            if (_result20 != null) {
                                reply.writeInt(1);
                                _result20.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = ShortcutInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            int _arg214 = data.readInt();
                            AndroidFuture _result21 = pushDynamicShortcut(_arg023, _arg17, _arg214);
                            reply.writeNoException();
                            if (_result21 != null) {
                                reply.writeInt(1);
                                _result21.writeToParcel(reply, 1);
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
        public static class Proxy implements IShortcutService {
            public static IShortcutService sDefaultImpl;
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

            @Override // android.content.pm.IShortcutService
            public AndroidFuture setDynamicShortcuts(String packageName, ParceledListSlice shortcutInfoList, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (shortcutInfoList != null) {
                        _data.writeInt(1);
                        shortcutInfoList.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDynamicShortcuts(packageName, shortcutInfoList, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture addDynamicShortcuts(String packageName, ParceledListSlice shortcutInfoList, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (shortcutInfoList != null) {
                        _data.writeInt(1);
                        shortcutInfoList.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addDynamicShortcuts(packageName, shortcutInfoList, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture removeDynamicShortcuts(String packageName, List shortcutIds, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeList(shortcutIds);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDynamicShortcuts(packageName, shortcutIds, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture removeAllDynamicShortcuts(String packageName, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAllDynamicShortcuts(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture updateShortcuts(String packageName, ParceledListSlice shortcuts, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (shortcuts != null) {
                        _data.writeInt(1);
                        shortcuts.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateShortcuts(packageName, shortcuts, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture requestPinShortcut(String packageName, ShortcutInfo shortcut, IntentSender resultIntent, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (shortcut != null) {
                        _data.writeInt(1);
                        shortcut.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (resultIntent != null) {
                        _data.writeInt(1);
                        resultIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestPinShortcut(packageName, shortcut, resultIntent, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture<Intent> createShortcutResultIntent(String packageName, ShortcutInfo shortcut, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (shortcut != null) {
                        _data.writeInt(1);
                        shortcut.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createShortcutResultIntent(packageName, shortcut, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture disableShortcuts(String packageName, List shortcutIds, CharSequence disabledMessage, int disabledMessageResId, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeList(shortcutIds);
                    if (disabledMessage != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(disabledMessage, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(disabledMessageResId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableShortcuts(packageName, shortcutIds, disabledMessage, disabledMessageResId, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture enableShortcuts(String packageName, List shortcutIds, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeList(shortcutIds);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableShortcuts(packageName, shortcutIds, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public int getMaxShortcutCountPerActivity(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaxShortcutCountPerActivity(packageName, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public int getRemainingCallCount(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemainingCallCount(packageName, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public long getRateLimitResetTime(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRateLimitResetTime(packageName, userId);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public int getIconMaxDimensions(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIconMaxDimensions(packageName, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture reportShortcutUsed(String packageName, String shortcutId, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(shortcutId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reportShortcutUsed(packageName, shortcutId, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public void resetThrottling() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetThrottling();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture onApplicationActive(String packageName, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onApplicationActive(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public byte[] getBackupPayload(int user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(user);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackupPayload(user);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture applyRestore(byte[] payload, int user) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(payload);
                    _data.writeInt(user);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().applyRestore(payload, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public boolean isRequestPinItemSupported(int user, int requestType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(user);
                    _data.writeInt(requestType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRequestPinItemSupported(user, requestType);
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

            @Override // android.content.pm.IShortcutService
            public AndroidFuture<ParceledListSlice> getShareTargets(String packageName, IntentFilter filter, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (filter != null) {
                        _data.writeInt(1);
                        filter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShareTargets(packageName, filter, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public boolean hasShareTargets(String packageName, String packageToCheck, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(packageToCheck);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasShareTargets(packageName, packageToCheck, userId);
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

            @Override // android.content.pm.IShortcutService
            public AndroidFuture removeLongLivedShortcuts(String packageName, List shortcutIds, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeList(shortcutIds);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeLongLivedShortcuts(packageName, shortcutIds, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture<ParceledListSlice> getShortcuts(String packageName, int matchFlags, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(matchFlags);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcuts(packageName, matchFlags, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutService
            public AndroidFuture pushDynamicShortcut(String packageName, ShortcutInfo shortcut, int userId) throws RemoteException {
                AndroidFuture _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (shortcut != null) {
                        _data.writeInt(1);
                        shortcut.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pushDynamicShortcut(packageName, shortcut, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidFuture.CREATOR.createFromParcel(_reply);
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

        public static boolean setDefaultImpl(IShortcutService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IShortcutService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
