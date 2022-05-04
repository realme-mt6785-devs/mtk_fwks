package com.android.internal.app;

import android.app.AppOpsManager;
import android.app.AsyncNotedAppOp;
import android.app.RuntimeAppOpAccessMessage;
import android.app.SyncNotedAppOp;
import android.content.AttributionSource;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.PackageTagsList;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import com.android.internal.app.IAppOpsActiveCallback;
import com.android.internal.app.IAppOpsAsyncNotedCallback;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.app.IAppOpsNotedCallback;
import com.android.internal.app.IAppOpsStartedCallback;
import java.util.List;
/* loaded from: classes4.dex */
public interface IAppOpsService extends IInterface {
    void addHistoricalOps(AppOpsManager.HistoricalOps historicalOps) throws RemoteException;

    int checkAudioOperation(int i, int i2, int i3, String str) throws RemoteException;

    int checkOperation(int i, int i2, String str) throws RemoteException;

    int checkOperationRaw(int i, int i2, String str, String str2) throws RemoteException;

    int checkPackage(int i, String str) throws RemoteException;

    void clearHistory() throws RemoteException;

    void collectNoteOpCallsForValidation(String str, int i, String str2, long j) throws RemoteException;

    RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() throws RemoteException;

    List<AsyncNotedAppOp> extractAsyncOps(String str) throws RemoteException;

    void finishOperation(IBinder iBinder, int i, int i2, String str, String str2) throws RemoteException;

    void finishProxyOperation(int i, AttributionSource attributionSource, boolean z) throws RemoteException;

    void getHistoricalOps(int i, String str, String str2, List<String> list, int i2, int i3, long j, long j2, int i4, RemoteCallback remoteCallback) throws RemoteException;

    void getHistoricalOpsFromDiskRaw(int i, String str, String str2, List<String> list, int i2, int i3, long j, long j2, int i4, RemoteCallback remoteCallback) throws RemoteException;

    List<AppOpsManager.PackageOps> getOpsForPackage(int i, String str, int[] iArr) throws RemoteException;

    List<AppOpsManager.PackageOps> getPackagesForOps(int[] iArr) throws RemoteException;

    List<AppOpsManager.PackageOps> getUidOps(int i, int[] iArr) throws RemoteException;

    boolean isOperationActive(int i, int i2, String str) throws RemoteException;

    boolean isProxying(int i, String str, String str2, int i2, String str3) throws RemoteException;

    SyncNotedAppOp noteOperation(int i, int i2, String str, String str2, boolean z, String str3, boolean z2) throws RemoteException;

    SyncNotedAppOp noteProxyOperation(int i, AttributionSource attributionSource, boolean z, String str, boolean z2, boolean z3) throws RemoteException;

    void offsetHistory(long j) throws RemoteException;

    int permissionToOpCode(String str) throws RemoteException;

    void rebootHistory(long j) throws RemoteException;

    void reloadNonHistoricalState() throws RemoteException;

    void removeUser(int i) throws RemoteException;

    MessageSamplingConfig reportRuntimeAppOpAccessMessageAndGetConfig(String str, SyncNotedAppOp syncNotedAppOp, String str2) throws RemoteException;

    void resetAllModes(int i, String str) throws RemoteException;

    void resetHistoryParameters() throws RemoteException;

    void resetPackageOpsNoHistory(String str) throws RemoteException;

    void setAudioRestriction(int i, int i2, int i3, int i4, String[] strArr) throws RemoteException;

    void setCameraAudioRestriction(int i) throws RemoteException;

    void setHistoryParameters(int i, long j, int i2) throws RemoteException;

    void setMode(int i, int i2, String str, int i3) throws RemoteException;

    void setUidMode(int i, int i2, int i3) throws RemoteException;

    void setUserRestriction(int i, boolean z, IBinder iBinder, int i2, PackageTagsList packageTagsList) throws RemoteException;

    void setUserRestrictions(Bundle bundle, IBinder iBinder, int i) throws RemoteException;

    boolean shouldCollectNotes(int i) throws RemoteException;

    SyncNotedAppOp startOperation(IBinder iBinder, int i, int i2, String str, String str2, boolean z, boolean z2, String str3, boolean z3, int i3, int i4) throws RemoteException;

    SyncNotedAppOp startProxyOperation(int i, AttributionSource attributionSource, boolean z, boolean z2, String str, boolean z3, boolean z4, int i2, int i3, int i4) throws RemoteException;

    void startWatchingActive(int[] iArr, IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException;

    void startWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException;

    void startWatchingMode(int i, String str, IAppOpsCallback iAppOpsCallback) throws RemoteException;

    void startWatchingModeWithFlags(int i, String str, int i2, IAppOpsCallback iAppOpsCallback) throws RemoteException;

    void startWatchingNoted(int[] iArr, IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException;

    void startWatchingStarted(int[] iArr, IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException;

    void stopWatchingActive(IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException;

    void stopWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException;

    void stopWatchingMode(IAppOpsCallback iAppOpsCallback) throws RemoteException;

    void stopWatchingNoted(IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException;

    void stopWatchingStarted(IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IAppOpsService {
        @Override // com.android.internal.app.IAppOpsService
        public int checkOperation(int code, int uid, String packageName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public SyncNotedAppOp noteOperation(int code, int uid, String packageName, String attributionTag, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public SyncNotedAppOp startOperation(IBinder clientId, int code, int uid, String packageName, String attributionTag, boolean startIfModeDefault, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage, int attributionFlags, int attributionChainId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void finishOperation(IBinder clientId, int code, int uid, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingMode(int op, String packageName, IAppOpsCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingMode(IAppOpsCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public int permissionToOpCode(String permission) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkAudioOperation(int code, int usage, int uid, String packageName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public boolean shouldCollectNotes(int opCode) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setCameraAudioRestriction(int mode) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public SyncNotedAppOp noteProxyOperation(int code, AttributionSource attributionSource, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage, boolean skipProxyOperation) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public SyncNotedAppOp startProxyOperation(int code, AttributionSource attributionSource, boolean startIfModeDefault, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage, boolean skipProxyOperation, int proxyAttributionFlags, int proxiedAttributionFlags, int attributionChainId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void finishProxyOperation(int code, AttributionSource attributionSource, boolean skipProxyOperation) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkPackage(int uid, String packageName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public MessageSamplingConfig reportRuntimeAppOpAccessMessageAndGetConfig(String packageName, SyncNotedAppOp appOp, String message) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AppOpsManager.PackageOps> getPackagesForOps(int[] ops) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AppOpsManager.PackageOps> getOpsForPackage(int uid, String packageName, int[] ops) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void getHistoricalOps(int uid, String packageName, String attributionTag, List<String> ops, int historyFlags, int filter, long beginTimeMillis, long endTimeMillis, int flags, RemoteCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void getHistoricalOpsFromDiskRaw(int uid, String packageName, String attributionTag, List<String> ops, int historyFlags, int filter, long beginTimeMillis, long endTimeMillis, int flags, RemoteCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void offsetHistory(long duration) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setHistoryParameters(int mode, long baseSnapshotInterval, int compressionStep) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void addHistoricalOps(AppOpsManager.HistoricalOps ops) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void resetHistoryParameters() throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void resetPackageOpsNoHistory(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void clearHistory() throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void rebootHistory(long offlineDurationMillis) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AppOpsManager.PackageOps> getUidOps(int uid, int[] ops) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setUidMode(int code, int uid, int mode) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setMode(int code, int uid, String packageName, int mode) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void resetAllModes(int reqUserId, String reqPackageName) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setAudioRestriction(int code, int usage, int uid, int mode, String[] exceptionPackages) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setUserRestrictions(Bundle restrictions, IBinder token, int userHandle) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setUserRestriction(int code, boolean restricted, IBinder token, int userHandle, PackageTagsList excludedPackageTags) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void removeUser(int userHandle) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingActive(int[] ops, IAppOpsActiveCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingActive(IAppOpsActiveCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public boolean isOperationActive(int code, int uid, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IAppOpsService
        public boolean isProxying(int op, String proxyPackageName, String proxyAttributionTag, int proxiedUid, String proxiedPackageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingStarted(int[] ops, IAppOpsStartedCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingStarted(IAppOpsStartedCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingModeWithFlags(int op, String packageName, int flags, IAppOpsCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingNoted(int[] ops, IAppOpsNotedCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingNoted(IAppOpsNotedCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingAsyncNoted(String packageName, IAppOpsAsyncNotedCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingAsyncNoted(String packageName, IAppOpsAsyncNotedCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AsyncNotedAppOp> extractAsyncOps(String packageName) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkOperationRaw(int code, int uid, String packageName, String attributionTag) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void reloadNonHistoricalState() throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void collectNoteOpCallsForValidation(String stackTrace, int op, String packageName, long version) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IAppOpsService {
        public static final String DESCRIPTOR = "com.android.internal.app.IAppOpsService";
        static final int TRANSACTION_addHistoricalOps = 23;
        static final int TRANSACTION_checkAudioOperation = 8;
        static final int TRANSACTION_checkOperation = 1;
        static final int TRANSACTION_checkOperationRaw = 48;
        static final int TRANSACTION_checkPackage = 14;
        static final int TRANSACTION_clearHistory = 26;
        static final int TRANSACTION_collectNoteOpCallsForValidation = 50;
        static final int TRANSACTION_collectRuntimeAppOpAccessMessage = 15;
        static final int TRANSACTION_extractAsyncOps = 47;
        static final int TRANSACTION_finishOperation = 4;
        static final int TRANSACTION_finishProxyOperation = 13;
        static final int TRANSACTION_getHistoricalOps = 19;
        static final int TRANSACTION_getHistoricalOpsFromDiskRaw = 20;
        static final int TRANSACTION_getOpsForPackage = 18;
        static final int TRANSACTION_getPackagesForOps = 17;
        static final int TRANSACTION_getUidOps = 28;
        static final int TRANSACTION_isOperationActive = 38;
        static final int TRANSACTION_isProxying = 39;
        static final int TRANSACTION_noteOperation = 2;
        static final int TRANSACTION_noteProxyOperation = 11;
        static final int TRANSACTION_offsetHistory = 21;
        static final int TRANSACTION_permissionToOpCode = 7;
        static final int TRANSACTION_rebootHistory = 27;
        static final int TRANSACTION_reloadNonHistoricalState = 49;
        static final int TRANSACTION_removeUser = 35;
        static final int TRANSACTION_reportRuntimeAppOpAccessMessageAndGetConfig = 16;
        static final int TRANSACTION_resetAllModes = 31;
        static final int TRANSACTION_resetHistoryParameters = 24;
        static final int TRANSACTION_resetPackageOpsNoHistory = 25;
        static final int TRANSACTION_setAudioRestriction = 32;
        static final int TRANSACTION_setCameraAudioRestriction = 10;
        static final int TRANSACTION_setHistoryParameters = 22;
        static final int TRANSACTION_setMode = 30;
        static final int TRANSACTION_setUidMode = 29;
        static final int TRANSACTION_setUserRestriction = 34;
        static final int TRANSACTION_setUserRestrictions = 33;
        static final int TRANSACTION_shouldCollectNotes = 9;
        static final int TRANSACTION_startOperation = 3;
        static final int TRANSACTION_startProxyOperation = 12;
        static final int TRANSACTION_startWatchingActive = 36;
        static final int TRANSACTION_startWatchingAsyncNoted = 45;
        static final int TRANSACTION_startWatchingMode = 5;
        static final int TRANSACTION_startWatchingModeWithFlags = 42;
        static final int TRANSACTION_startWatchingNoted = 43;
        static final int TRANSACTION_startWatchingStarted = 40;
        static final int TRANSACTION_stopWatchingActive = 37;
        static final int TRANSACTION_stopWatchingAsyncNoted = 46;
        static final int TRANSACTION_stopWatchingMode = 6;
        static final int TRANSACTION_stopWatchingNoted = 44;
        static final int TRANSACTION_stopWatchingStarted = 41;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppOpsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppOpsService)) {
                return new Proxy(obj);
            }
            return (IAppOpsService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "checkOperation";
                case 2:
                    return "noteOperation";
                case 3:
                    return "startOperation";
                case 4:
                    return "finishOperation";
                case 5:
                    return "startWatchingMode";
                case 6:
                    return "stopWatchingMode";
                case 7:
                    return "permissionToOpCode";
                case 8:
                    return "checkAudioOperation";
                case 9:
                    return "shouldCollectNotes";
                case 10:
                    return "setCameraAudioRestriction";
                case 11:
                    return "noteProxyOperation";
                case 12:
                    return "startProxyOperation";
                case 13:
                    return "finishProxyOperation";
                case 14:
                    return "checkPackage";
                case 15:
                    return "collectRuntimeAppOpAccessMessage";
                case 16:
                    return "reportRuntimeAppOpAccessMessageAndGetConfig";
                case 17:
                    return "getPackagesForOps";
                case 18:
                    return "getOpsForPackage";
                case 19:
                    return "getHistoricalOps";
                case 20:
                    return "getHistoricalOpsFromDiskRaw";
                case 21:
                    return "offsetHistory";
                case 22:
                    return "setHistoryParameters";
                case 23:
                    return "addHistoricalOps";
                case 24:
                    return "resetHistoryParameters";
                case 25:
                    return "resetPackageOpsNoHistory";
                case 26:
                    return "clearHistory";
                case 27:
                    return "rebootHistory";
                case 28:
                    return "getUidOps";
                case 29:
                    return "setUidMode";
                case 30:
                    return "setMode";
                case 31:
                    return "resetAllModes";
                case 32:
                    return "setAudioRestriction";
                case 33:
                    return "setUserRestrictions";
                case 34:
                    return "setUserRestriction";
                case 35:
                    return "removeUser";
                case 36:
                    return "startWatchingActive";
                case 37:
                    return "stopWatchingActive";
                case 38:
                    return "isOperationActive";
                case 39:
                    return "isProxying";
                case 40:
                    return "startWatchingStarted";
                case 41:
                    return "stopWatchingStarted";
                case 42:
                    return "startWatchingModeWithFlags";
                case 43:
                    return "startWatchingNoted";
                case 44:
                    return "stopWatchingNoted";
                case 45:
                    return "startWatchingAsyncNoted";
                case 46:
                    return "stopWatchingAsyncNoted";
                case 47:
                    return "extractAsyncOps";
                case 48:
                    return "checkOperationRaw";
                case 49:
                    return "reloadNonHistoricalState";
                case 50:
                    return "collectNoteOpCallsForValidation";
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
            boolean _arg5;
            boolean _arg6;
            boolean _arg8;
            AttributionSource _arg1;
            boolean _arg2;
            boolean _arg4;
            boolean _arg52;
            AttributionSource _arg12;
            boolean _arg22;
            boolean _arg3;
            boolean _arg53;
            boolean _arg62;
            AttributionSource _arg13;
            SyncNotedAppOp _arg14;
            RemoteCallback _arg9;
            RemoteCallback _arg92;
            AppOpsManager.HistoricalOps _arg0;
            Bundle _arg02;
            boolean _arg15;
            PackageTagsList _arg42;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg23 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg16 = data.readInt();
                            String _arg24 = data.readString();
                            int _result = checkOperation(_arg03, _arg16, _arg24);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg17 = data.readInt();
                            String _arg25 = data.readString();
                            String _arg32 = data.readString();
                            boolean _arg43 = data.readInt() != 0;
                            String _arg54 = data.readString();
                            boolean _arg63 = data.readInt() != 0;
                            SyncNotedAppOp _result2 = noteOperation(_arg04, _arg17, _arg25, _arg32, _arg43, _arg54, _arg63);
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
                            IBinder _arg05 = data.readStrongBinder();
                            int _arg18 = data.readInt();
                            int _arg26 = data.readInt();
                            String _arg33 = data.readString();
                            String _arg44 = data.readString();
                            if (data.readInt() != 0) {
                                _arg5 = true;
                            } else {
                                _arg5 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg6 = true;
                            } else {
                                _arg6 = false;
                            }
                            String _arg7 = data.readString();
                            if (data.readInt() != 0) {
                                _arg8 = true;
                            } else {
                                _arg8 = false;
                            }
                            int _arg93 = data.readInt();
                            int _arg10 = data.readInt();
                            SyncNotedAppOp _result3 = startOperation(_arg05, _arg18, _arg26, _arg33, _arg44, _arg5, _arg6, _arg7, _arg8, _arg93, _arg10);
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
                            IBinder _arg06 = data.readStrongBinder();
                            int _arg19 = data.readInt();
                            int _arg27 = data.readInt();
                            String _arg34 = data.readString();
                            String _arg45 = data.readString();
                            finishOperation(_arg06, _arg19, _arg27, _arg34, _arg45);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            String _arg110 = data.readString();
                            IAppOpsCallback _arg28 = IAppOpsCallback.Stub.asInterface(data.readStrongBinder());
                            startWatchingMode(_arg07, _arg110, _arg28);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            IAppOpsCallback _arg08 = IAppOpsCallback.Stub.asInterface(data.readStrongBinder());
                            stopWatchingMode(_arg08);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _result4 = permissionToOpCode(_arg09);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg111 = data.readInt();
                            int _arg29 = data.readInt();
                            String _arg35 = data.readString();
                            int _result5 = checkAudioOperation(_arg010, _arg111, _arg29, _arg35);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            boolean shouldCollectNotes = shouldCollectNotes(_arg011);
                            reply.writeNoException();
                            reply.writeInt(shouldCollectNotes ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            setCameraAudioRestriction(_arg012);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            } else {
                                _arg2 = false;
                            }
                            String _arg36 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg52 = true;
                            } else {
                                _arg52 = false;
                            }
                            SyncNotedAppOp _result6 = noteProxyOperation(_arg013, _arg1, _arg2, _arg36, _arg4, _arg52);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = true;
                            } else {
                                _arg22 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            String _arg46 = data.readString();
                            if (data.readInt() != 0) {
                                _arg53 = true;
                            } else {
                                _arg53 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg62 = true;
                            } else {
                                _arg62 = false;
                            }
                            int _arg72 = data.readInt();
                            int _arg82 = data.readInt();
                            int _arg94 = data.readInt();
                            SyncNotedAppOp _result7 = startProxyOperation(_arg014, _arg12, _arg22, _arg3, _arg46, _arg53, _arg62, _arg72, _arg82, _arg94);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = true;
                            }
                            finishProxyOperation(_arg015, _arg13, _arg23);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            String _arg112 = data.readString();
                            int _result8 = checkPackage(_arg016, _arg112);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            RuntimeAppOpAccessMessage _result9 = collectRuntimeAppOpAccessMessage();
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = SyncNotedAppOp.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            String _arg210 = data.readString();
                            MessageSamplingConfig _result10 = reportRuntimeAppOpAccessMessageAndGetConfig(_arg017, _arg14, _arg210);
                            reply.writeNoException();
                            if (_result10 != null) {
                                reply.writeInt(1);
                                _result10.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg018 = data.createIntArray();
                            List<AppOpsManager.PackageOps> _result11 = getPackagesForOps(_arg018);
                            reply.writeNoException();
                            reply.writeTypedList(_result11);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            String _arg113 = data.readString();
                            int[] _arg211 = data.createIntArray();
                            List<AppOpsManager.PackageOps> _result12 = getOpsForPackage(_arg019, _arg113, _arg211);
                            reply.writeNoException();
                            reply.writeTypedList(_result12);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            String _arg114 = data.readString();
                            String _arg212 = data.readString();
                            List<String> _arg37 = data.createStringArrayList();
                            int _arg47 = data.readInt();
                            int _arg55 = data.readInt();
                            long _arg64 = data.readLong();
                            long _arg73 = data.readLong();
                            int _arg83 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg9 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg9 = null;
                            }
                            getHistoricalOps(_arg020, _arg114, _arg212, _arg37, _arg47, _arg55, _arg64, _arg73, _arg83, _arg9);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            String _arg115 = data.readString();
                            String _arg213 = data.readString();
                            List<String> _arg38 = data.createStringArrayList();
                            int _arg48 = data.readInt();
                            int _arg56 = data.readInt();
                            long _arg65 = data.readLong();
                            long _arg74 = data.readLong();
                            int _arg84 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg92 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg92 = null;
                            }
                            getHistoricalOpsFromDiskRaw(_arg021, _arg115, _arg213, _arg38, _arg48, _arg56, _arg65, _arg74, _arg84, _arg92);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg022 = data.readLong();
                            offsetHistory(_arg022);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            long _arg116 = data.readLong();
                            int _arg214 = data.readInt();
                            setHistoryParameters(_arg023, _arg116, _arg214);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = AppOpsManager.HistoricalOps.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            addHistoricalOps(_arg0);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            resetHistoryParameters();
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            resetPackageOpsNoHistory(_arg024);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            clearHistory();
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg025 = data.readLong();
                            rebootHistory(_arg025);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            int[] _arg117 = data.createIntArray();
                            List<AppOpsManager.PackageOps> _result13 = getUidOps(_arg026, _arg117);
                            reply.writeNoException();
                            reply.writeTypedList(_result13);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            int _arg118 = data.readInt();
                            int _arg215 = data.readInt();
                            setUidMode(_arg027, _arg118, _arg215);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg028 = data.readInt();
                            int _arg119 = data.readInt();
                            String _arg216 = data.readString();
                            int _arg39 = data.readInt();
                            setMode(_arg028, _arg119, _arg216, _arg39);
                            reply.writeNoException();
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            String _arg120 = data.readString();
                            resetAllModes(_arg029, _arg120);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            int _arg121 = data.readInt();
                            int _arg217 = data.readInt();
                            int _arg310 = data.readInt();
                            String[] _arg49 = data.createStringArray();
                            setAudioRestriction(_arg030, _arg121, _arg217, _arg310, _arg49);
                            reply.writeNoException();
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            IBinder _arg122 = data.readStrongBinder();
                            int _arg218 = data.readInt();
                            setUserRestrictions(_arg02, _arg122, _arg218);
                            reply.writeNoException();
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg031 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            } else {
                                _arg15 = false;
                            }
                            IBinder _arg219 = data.readStrongBinder();
                            int _arg311 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg42 = PackageTagsList.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            setUserRestriction(_arg031, _arg15, _arg219, _arg311, _arg42);
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg032 = data.readInt();
                            removeUser(_arg032);
                            reply.writeNoException();
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg033 = data.createIntArray();
                            IAppOpsActiveCallback _arg123 = IAppOpsActiveCallback.Stub.asInterface(data.readStrongBinder());
                            startWatchingActive(_arg033, _arg123);
                            reply.writeNoException();
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            IAppOpsActiveCallback _arg034 = IAppOpsActiveCallback.Stub.asInterface(data.readStrongBinder());
                            stopWatchingActive(_arg034);
                            reply.writeNoException();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg035 = data.readInt();
                            int _arg124 = data.readInt();
                            String _arg220 = data.readString();
                            boolean isOperationActive = isOperationActive(_arg035, _arg124, _arg220);
                            reply.writeNoException();
                            reply.writeInt(isOperationActive ? 1 : 0);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg036 = data.readInt();
                            String _arg125 = data.readString();
                            String _arg221 = data.readString();
                            int _arg312 = data.readInt();
                            String _arg410 = data.readString();
                            boolean isProxying = isProxying(_arg036, _arg125, _arg221, _arg312, _arg410);
                            reply.writeNoException();
                            reply.writeInt(isProxying ? 1 : 0);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg037 = data.createIntArray();
                            IAppOpsStartedCallback _arg126 = IAppOpsStartedCallback.Stub.asInterface(data.readStrongBinder());
                            startWatchingStarted(_arg037, _arg126);
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            IAppOpsStartedCallback _arg038 = IAppOpsStartedCallback.Stub.asInterface(data.readStrongBinder());
                            stopWatchingStarted(_arg038);
                            reply.writeNoException();
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg039 = data.readInt();
                            String _arg127 = data.readString();
                            int _arg222 = data.readInt();
                            IAppOpsCallback _arg313 = IAppOpsCallback.Stub.asInterface(data.readStrongBinder());
                            startWatchingModeWithFlags(_arg039, _arg127, _arg222, _arg313);
                            reply.writeNoException();
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg040 = data.createIntArray();
                            IAppOpsNotedCallback _arg128 = IAppOpsNotedCallback.Stub.asInterface(data.readStrongBinder());
                            startWatchingNoted(_arg040, _arg128);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            IAppOpsNotedCallback _arg041 = IAppOpsNotedCallback.Stub.asInterface(data.readStrongBinder());
                            stopWatchingNoted(_arg041);
                            reply.writeNoException();
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            IAppOpsAsyncNotedCallback _arg129 = IAppOpsAsyncNotedCallback.Stub.asInterface(data.readStrongBinder());
                            startWatchingAsyncNoted(_arg042, _arg129);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            IAppOpsAsyncNotedCallback _arg130 = IAppOpsAsyncNotedCallback.Stub.asInterface(data.readStrongBinder());
                            stopWatchingAsyncNoted(_arg043, _arg130);
                            reply.writeNoException();
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg044 = data.readString();
                            List<AsyncNotedAppOp> _result14 = extractAsyncOps(_arg044);
                            reply.writeNoException();
                            reply.writeTypedList(_result14);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg045 = data.readInt();
                            int _arg131 = data.readInt();
                            String _arg223 = data.readString();
                            String _arg314 = data.readString();
                            int _result15 = checkOperationRaw(_arg045, _arg131, _arg223, _arg314);
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            reloadNonHistoricalState();
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg046 = data.readString();
                            int _arg132 = data.readInt();
                            String _arg224 = data.readString();
                            long _arg315 = data.readLong();
                            collectNoteOpCallsForValidation(_arg046, _arg132, _arg224, _arg315);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IAppOpsService {
            public static IAppOpsService sDefaultImpl;
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

            @Override // com.android.internal.app.IAppOpsService
            public int checkOperation(int code, int uid, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkOperation(code, uid, packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public SyncNotedAppOp noteOperation(int code, int uid, String packageName, String attributionTag, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage) throws RemoteException {
                Throwable th;
                SyncNotedAppOp _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(code);
                        try {
                            _data.writeInt(uid);
                            try {
                                _data.writeString(packageName);
                                try {
                                    _data.writeString(attributionTag);
                                    int i = 1;
                                    _data.writeInt(shouldCollectAsyncNotedOp ? 1 : 0);
                                    try {
                                        _data.writeString(message);
                                        if (!shouldCollectMessage) {
                                            i = 0;
                                        }
                                        _data.writeInt(i);
                                        boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            if (_reply.readInt() != 0) {
                                                _result = SyncNotedAppOp.CREATOR.createFromParcel(_reply);
                                            } else {
                                                _result = null;
                                            }
                                            _reply.recycle();
                                            _data.recycle();
                                            return _result;
                                        }
                                        SyncNotedAppOp noteOperation = Stub.getDefaultImpl().noteOperation(code, uid, packageName, attributionTag, shouldCollectAsyncNotedOp, message, shouldCollectMessage);
                                        _reply.recycle();
                                        _data.recycle();
                                        return noteOperation;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        _reply.recycle();
                                        _data.recycle();
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public SyncNotedAppOp startOperation(IBinder clientId, int code, int uid, String packageName, String attributionTag, boolean startIfModeDefault, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage, int attributionFlags, int attributionChainId) throws RemoteException {
                Throwable th;
                SyncNotedAppOp _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeStrongBinder(clientId);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    int i = 1;
                    _data.writeInt(startIfModeDefault ? 1 : 0);
                    _data.writeInt(shouldCollectAsyncNotedOp ? 1 : 0);
                    _data.writeString(message);
                    if (!shouldCollectMessage) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeInt(attributionFlags);
                    _data.writeInt(attributionChainId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = SyncNotedAppOp.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        _reply.recycle();
                        _data.recycle();
                        return _result;
                    }
                    SyncNotedAppOp startOperation = Stub.getDefaultImpl().startOperation(clientId, code, uid, packageName, attributionTag, startIfModeDefault, shouldCollectAsyncNotedOp, message, shouldCollectMessage, attributionFlags, attributionChainId);
                    _reply.recycle();
                    _data.recycle();
                    return startOperation;
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void finishOperation(IBinder clientId, int code, int uid, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(clientId);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishOperation(clientId, code, uid, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingMode(int op, String packageName, IAppOpsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWatchingMode(op, packageName, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingMode(IAppOpsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopWatchingMode(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int permissionToOpCode(String permission) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(permission);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().permissionToOpCode(permission);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int checkAudioOperation(int code, int usage, int uid, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(usage);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkAudioOperation(code, usage, uid, packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public boolean shouldCollectNotes(int opCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(opCode);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldCollectNotes(opCode);
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

            @Override // com.android.internal.app.IAppOpsService
            public void setCameraAudioRestriction(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCameraAudioRestriction(mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public SyncNotedAppOp noteProxyOperation(int code, AttributionSource attributionSource, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage, boolean skipProxyOperation) throws RemoteException {
                Throwable th;
                SyncNotedAppOp _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(code);
                        int i = 1;
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeInt(shouldCollectAsyncNotedOp ? 1 : 0);
                        try {
                            _data.writeString(message);
                            _data.writeInt(shouldCollectMessage ? 1 : 0);
                            if (!skipProxyOperation) {
                                i = 0;
                            }
                            _data.writeInt(i);
                            try {
                                boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() != 0) {
                                        _result = SyncNotedAppOp.CREATOR.createFromParcel(_reply);
                                    } else {
                                        _result = null;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                SyncNotedAppOp noteProxyOperation = Stub.getDefaultImpl().noteProxyOperation(code, attributionSource, shouldCollectAsyncNotedOp, message, shouldCollectMessage, skipProxyOperation);
                                _reply.recycle();
                                _data.recycle();
                                return noteProxyOperation;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
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
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public SyncNotedAppOp startProxyOperation(int code, AttributionSource attributionSource, boolean startIfModeDefault, boolean shouldCollectAsyncNotedOp, String message, boolean shouldCollectMessage, boolean skipProxyOperation, int proxyAttributionFlags, int proxiedAttributionFlags, int attributionChainId) throws RemoteException {
                Throwable th;
                SyncNotedAppOp _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(code);
                        int i = 1;
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeInt(startIfModeDefault ? 1 : 0);
                        _data.writeInt(shouldCollectAsyncNotedOp ? 1 : 0);
                        _data.writeString(message);
                        _data.writeInt(shouldCollectMessage ? 1 : 0);
                        if (!skipProxyOperation) {
                            i = 0;
                        }
                        _data.writeInt(i);
                        _data.writeInt(proxyAttributionFlags);
                        _data.writeInt(proxiedAttributionFlags);
                        _data.writeInt(attributionChainId);
                        boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            if (_reply.readInt() != 0) {
                                _result = SyncNotedAppOp.CREATOR.createFromParcel(_reply);
                            } else {
                                _result = null;
                            }
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        SyncNotedAppOp startProxyOperation = Stub.getDefaultImpl().startProxyOperation(code, attributionSource, startIfModeDefault, shouldCollectAsyncNotedOp, message, shouldCollectMessage, skipProxyOperation, proxyAttributionFlags, proxiedAttributionFlags, attributionChainId);
                        _reply.recycle();
                        _data.recycle();
                        return startProxyOperation;
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void finishProxyOperation(int code, AttributionSource attributionSource, boolean skipProxyOperation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    int i = 1;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!skipProxyOperation) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishProxyOperation(code, attributionSource, skipProxyOperation);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int checkPackage(int uid, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkPackage(uid, packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() throws RemoteException {
                RuntimeAppOpAccessMessage _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().collectRuntimeAppOpAccessMessage();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RuntimeAppOpAccessMessage.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public MessageSamplingConfig reportRuntimeAppOpAccessMessageAndGetConfig(String packageName, SyncNotedAppOp appOp, String message) throws RemoteException {
                MessageSamplingConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (appOp != null) {
                        _data.writeInt(1);
                        appOp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(message);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reportRuntimeAppOpAccessMessageAndGetConfig(packageName, appOp, message);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = MessageSamplingConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AppOpsManager.PackageOps> getPackagesForOps(int[] ops) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(ops);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesForOps(ops);
                    }
                    _reply.readException();
                    List<AppOpsManager.PackageOps> _result = _reply.createTypedArrayList(AppOpsManager.PackageOps.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AppOpsManager.PackageOps> getOpsForPackage(int uid, String packageName, int[] ops) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeIntArray(ops);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOpsForPackage(uid, packageName, ops);
                    }
                    _reply.readException();
                    List<AppOpsManager.PackageOps> _result = _reply.createTypedArrayList(AppOpsManager.PackageOps.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void getHistoricalOps(int uid, String packageName, String attributionTag, List<String> ops, int historyFlags, int filter, long beginTimeMillis, long endTimeMillis, int flags, RemoteCallback callback) throws RemoteException {
                Parcel _reply;
                Throwable th;
                Parcel _reply2;
                Parcel _data = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeStringList(ops);
                    _data.writeInt(historyFlags);
                    _data.writeInt(filter);
                    _data.writeLong(beginTimeMillis);
                    _data.writeLong(endTimeMillis);
                    _data.writeInt(flags);
                    if (callback != null) {
                        try {
                            _data.writeInt(1);
                            callback.writeToParcel(_data, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, _reply3, 0);
                    try {
                        if (_status) {
                            _reply2 = _reply3;
                        } else if (Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().getHistoricalOps(uid, packageName, attributionTag, ops, historyFlags, filter, beginTimeMillis, endTimeMillis, flags, callback);
                            _reply3.recycle();
                            _data.recycle();
                            return;
                        } else {
                            _reply2 = _reply3;
                        }
                        _reply2.readException();
                        _reply2.recycle();
                        _data.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply = _reply3;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void getHistoricalOpsFromDiskRaw(int uid, String packageName, String attributionTag, List<String> ops, int historyFlags, int filter, long beginTimeMillis, long endTimeMillis, int flags, RemoteCallback callback) throws RemoteException {
                Parcel _reply;
                Throwable th;
                Parcel _reply2;
                Parcel _data = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeStringList(ops);
                    _data.writeInt(historyFlags);
                    _data.writeInt(filter);
                    _data.writeLong(beginTimeMillis);
                    _data.writeLong(endTimeMillis);
                    _data.writeInt(flags);
                    if (callback != null) {
                        try {
                            _data.writeInt(1);
                            callback.writeToParcel(_data, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply3, 0);
                    try {
                        if (_status) {
                            _reply2 = _reply3;
                        } else if (Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().getHistoricalOpsFromDiskRaw(uid, packageName, attributionTag, ops, historyFlags, filter, beginTimeMillis, endTimeMillis, flags, callback);
                            _reply3.recycle();
                            _data.recycle();
                            return;
                        } else {
                            _reply2 = _reply3;
                        }
                        _reply2.readException();
                        _reply2.recycle();
                        _data.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply = _reply3;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void offsetHistory(long duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(duration);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().offsetHistory(duration);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setHistoryParameters(int mode, long baseSnapshotInterval, int compressionStep) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    _data.writeLong(baseSnapshotInterval);
                    _data.writeInt(compressionStep);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setHistoryParameters(mode, baseSnapshotInterval, compressionStep);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void addHistoricalOps(AppOpsManager.HistoricalOps ops) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ops != null) {
                        _data.writeInt(1);
                        ops.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addHistoricalOps(ops);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void resetHistoryParameters() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetHistoryParameters();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void resetPackageOpsNoHistory(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetPackageOpsNoHistory(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void clearHistory() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearHistory();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void rebootHistory(long offlineDurationMillis) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(offlineDurationMillis);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().rebootHistory(offlineDurationMillis);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AppOpsManager.PackageOps> getUidOps(int uid, int[] ops) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeIntArray(ops);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUidOps(uid, ops);
                    }
                    _reply.readException();
                    List<AppOpsManager.PackageOps> _result = _reply.createTypedArrayList(AppOpsManager.PackageOps.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setUidMode(int code, int uid, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUidMode(code, uid, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setMode(int code, int uid, String packageName, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setMode(code, uid, packageName, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void resetAllModes(int reqUserId, String reqPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(reqUserId);
                    _data.writeString(reqPackageName);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetAllModes(reqUserId, reqPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setAudioRestriction(int code, int usage, int uid, int mode, String[] exceptionPackages) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(usage);
                    _data.writeInt(uid);
                    _data.writeInt(mode);
                    _data.writeStringArray(exceptionPackages);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAudioRestriction(code, usage, uid, mode, exceptionPackages);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setUserRestrictions(Bundle restrictions, IBinder token, int userHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (restrictions != null) {
                        _data.writeInt(1);
                        restrictions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    _data.writeInt(userHandle);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserRestrictions(restrictions, token, userHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setUserRestriction(int code, boolean restricted, IBinder token, int userHandle, PackageTagsList excludedPackageTags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(restricted ? 1 : 0);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userHandle);
                    if (excludedPackageTags != null) {
                        _data.writeInt(1);
                        excludedPackageTags.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserRestriction(code, restricted, token, userHandle, excludedPackageTags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void removeUser(int userHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeUser(userHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingActive(int[] ops, IAppOpsActiveCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(ops);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWatchingActive(ops, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingActive(IAppOpsActiveCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopWatchingActive(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public boolean isOperationActive(int code, int uid, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOperationActive(code, uid, packageName);
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

            @Override // com.android.internal.app.IAppOpsService
            public boolean isProxying(int op, String proxyPackageName, String proxyAttributionTag, int proxiedUid, String proxiedPackageName) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(op);
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
                    _data.writeString(proxyPackageName);
                    try {
                        _data.writeString(proxyAttributionTag);
                        try {
                            _data.writeInt(proxiedUid);
                            try {
                                _data.writeString(proxiedPackageName);
                                try {
                                    boolean _result = false;
                                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = true;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean isProxying = Stub.getDefaultImpl().isProxying(op, proxyPackageName, proxyAttributionTag, proxiedUid, proxiedPackageName);
                                    _reply.recycle();
                                    _data.recycle();
                                    return isProxying;
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

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingStarted(int[] ops, IAppOpsStartedCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(ops);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWatchingStarted(ops, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingStarted(IAppOpsStartedCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopWatchingStarted(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingModeWithFlags(int op, String packageName, int flags, IAppOpsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWatchingModeWithFlags(op, packageName, flags, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingNoted(int[] ops, IAppOpsNotedCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(ops);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWatchingNoted(ops, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingNoted(IAppOpsNotedCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopWatchingNoted(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingAsyncNoted(String packageName, IAppOpsAsyncNotedCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWatchingAsyncNoted(packageName, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingAsyncNoted(String packageName, IAppOpsAsyncNotedCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopWatchingAsyncNoted(packageName, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AsyncNotedAppOp> extractAsyncOps(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().extractAsyncOps(packageName);
                    }
                    _reply.readException();
                    List<AsyncNotedAppOp> _result = _reply.createTypedArrayList(AsyncNotedAppOp.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int checkOperationRaw(int code, int uid, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkOperationRaw(code, uid, packageName, attributionTag);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void reloadNonHistoricalState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reloadNonHistoricalState();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void collectNoteOpCallsForValidation(String stackTrace, int op, String packageName, long version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(stackTrace);
                    _data.writeInt(op);
                    _data.writeString(packageName);
                    _data.writeLong(version);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().collectNoteOpCallsForValidation(stackTrace, op, packageName, version);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppOpsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAppOpsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
