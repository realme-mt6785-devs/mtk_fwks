package android.service.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.IStatusBarNotificationHolder;
import android.text.TextUtils;
import java.util.List;
/* loaded from: classes3.dex */
public interface INotificationListener extends IInterface {
    void onActionClicked(String str, Notification.Action action, int i) throws RemoteException;

    void onAllowedAdjustmentsChanged() throws RemoteException;

    void onInterruptionFilterChanged(int i) throws RemoteException;

    void onListenerConnected(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onListenerHintsChanged(int i) throws RemoteException;

    void onNotificationChannelGroupModification(String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup, int i) throws RemoteException;

    void onNotificationChannelModification(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) throws RemoteException;

    void onNotificationClicked(String str) throws RemoteException;

    void onNotificationDirectReply(String str) throws RemoteException;

    void onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationChannel notificationChannel, NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onNotificationExpansionChanged(String str, boolean z, boolean z2) throws RemoteException;

    void onNotificationFeedbackReceived(String str, NotificationRankingUpdate notificationRankingUpdate, Bundle bundle) throws RemoteException;

    void onNotificationPosted(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onNotificationRankingUpdate(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onNotificationRemoved(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate, NotificationStats notificationStats, int i) throws RemoteException;

    void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder iStatusBarNotificationHolder, String str) throws RemoteException;

    void onNotificationVisibilityChanged(String str, boolean z) throws RemoteException;

    void onNotificationsSeen(List<String> list) throws RemoteException;

    void onPanelHidden() throws RemoteException;

    void onPanelRevealed(int i) throws RemoteException;

    void onStatusBarIconsBehaviorChanged(boolean z) throws RemoteException;

    void onSuggestedReplySent(String str, CharSequence charSequence, int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements INotificationListener {
        @Override // android.service.notification.INotificationListener
        public void onListenerConnected(NotificationRankingUpdate update) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationPosted(IStatusBarNotificationHolder notificationHolder, NotificationRankingUpdate update) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onStatusBarIconsBehaviorChanged(boolean hideSilentStatusIcons) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationRemoved(IStatusBarNotificationHolder notificationHolder, NotificationRankingUpdate update, NotificationStats stats, int reason) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationRankingUpdate(NotificationRankingUpdate update) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onListenerHintsChanged(int hints) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onInterruptionFilterChanged(int interruptionFilter) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationChannelModification(String pkgName, UserHandle user, NotificationChannel channel, int modificationType) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationChannelGroupModification(String pkgName, UserHandle user, NotificationChannelGroup group, int modificationType) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder notificationHolder, NotificationChannel channel, NotificationRankingUpdate update) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder notificationHolder, String snoozeCriterionId) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationsSeen(List<String> keys) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onPanelRevealed(int items) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onPanelHidden() throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationVisibilityChanged(String key, boolean isVisible) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationExpansionChanged(String key, boolean userAction, boolean expanded) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationDirectReply(String key) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onSuggestedReplySent(String key, CharSequence reply, int source) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onActionClicked(String key, Notification.Action action, int source) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationClicked(String key) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onAllowedAdjustmentsChanged() throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationFeedbackReceived(String key, NotificationRankingUpdate update, Bundle feedback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements INotificationListener {
        public static final String DESCRIPTOR = "android.service.notification.INotificationListener";
        static final int TRANSACTION_onActionClicked = 19;
        static final int TRANSACTION_onAllowedAdjustmentsChanged = 21;
        static final int TRANSACTION_onInterruptionFilterChanged = 7;
        static final int TRANSACTION_onListenerConnected = 1;
        static final int TRANSACTION_onListenerHintsChanged = 6;
        static final int TRANSACTION_onNotificationChannelGroupModification = 9;
        static final int TRANSACTION_onNotificationChannelModification = 8;
        static final int TRANSACTION_onNotificationClicked = 20;
        static final int TRANSACTION_onNotificationDirectReply = 17;
        static final int TRANSACTION_onNotificationEnqueuedWithChannel = 10;
        static final int TRANSACTION_onNotificationExpansionChanged = 16;
        static final int TRANSACTION_onNotificationFeedbackReceived = 22;
        static final int TRANSACTION_onNotificationPosted = 2;
        static final int TRANSACTION_onNotificationRankingUpdate = 5;
        static final int TRANSACTION_onNotificationRemoved = 4;
        static final int TRANSACTION_onNotificationSnoozedUntilContext = 11;
        static final int TRANSACTION_onNotificationVisibilityChanged = 15;
        static final int TRANSACTION_onNotificationsSeen = 12;
        static final int TRANSACTION_onPanelHidden = 14;
        static final int TRANSACTION_onPanelRevealed = 13;
        static final int TRANSACTION_onStatusBarIconsBehaviorChanged = 3;
        static final int TRANSACTION_onSuggestedReplySent = 18;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INotificationListener)) {
                return new Proxy(obj);
            }
            return (INotificationListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onListenerConnected";
                case 2:
                    return "onNotificationPosted";
                case 3:
                    return "onStatusBarIconsBehaviorChanged";
                case 4:
                    return "onNotificationRemoved";
                case 5:
                    return "onNotificationRankingUpdate";
                case 6:
                    return "onListenerHintsChanged";
                case 7:
                    return "onInterruptionFilterChanged";
                case 8:
                    return "onNotificationChannelModification";
                case 9:
                    return "onNotificationChannelGroupModification";
                case 10:
                    return "onNotificationEnqueuedWithChannel";
                case 11:
                    return "onNotificationSnoozedUntilContext";
                case 12:
                    return "onNotificationsSeen";
                case 13:
                    return "onPanelRevealed";
                case 14:
                    return "onPanelHidden";
                case 15:
                    return "onNotificationVisibilityChanged";
                case 16:
                    return "onNotificationExpansionChanged";
                case 17:
                    return "onNotificationDirectReply";
                case 18:
                    return "onSuggestedReplySent";
                case 19:
                    return "onActionClicked";
                case 20:
                    return "onNotificationClicked";
                case 21:
                    return "onAllowedAdjustmentsChanged";
                case 22:
                    return "onNotificationFeedbackReceived";
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
            NotificationRankingUpdate _arg0;
            NotificationRankingUpdate _arg1;
            NotificationRankingUpdate _arg12;
            NotificationStats _arg2;
            NotificationRankingUpdate _arg02;
            UserHandle _arg13;
            NotificationChannel _arg22;
            UserHandle _arg14;
            NotificationChannelGroup _arg23;
            NotificationChannel _arg15;
            NotificationRankingUpdate _arg24;
            boolean _arg16;
            CharSequence _arg17;
            Notification.Action _arg18;
            NotificationRankingUpdate _arg19;
            Bundle _arg25;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg26 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = NotificationRankingUpdate.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onListenerConnected(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IStatusBarNotificationHolder _arg03 = IStatusBarNotificationHolder.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = NotificationRankingUpdate.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onNotificationPosted(_arg03, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg26 = true;
                            }
                            onStatusBarIconsBehaviorChanged(_arg26);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            IStatusBarNotificationHolder _arg04 = IStatusBarNotificationHolder.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = NotificationRankingUpdate.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = NotificationStats.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _arg3 = data.readInt();
                            onNotificationRemoved(_arg04, _arg12, _arg2, _arg3);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = NotificationRankingUpdate.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onNotificationRankingUpdate(_arg02);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onListenerHintsChanged(_arg05);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            onInterruptionFilterChanged(_arg06);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = NotificationChannel.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            int _arg32 = data.readInt();
                            onNotificationChannelModification(_arg07, _arg13, _arg22, _arg32);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = NotificationChannelGroup.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            int _arg33 = data.readInt();
                            onNotificationChannelGroupModification(_arg08, _arg14, _arg23, _arg33);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            IStatusBarNotificationHolder _arg09 = IStatusBarNotificationHolder.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg15 = NotificationChannel.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg24 = NotificationRankingUpdate.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            onNotificationEnqueuedWithChannel(_arg09, _arg15, _arg24);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            IStatusBarNotificationHolder _arg010 = IStatusBarNotificationHolder.Stub.asInterface(data.readStrongBinder());
                            String _arg110 = data.readString();
                            onNotificationSnoozedUntilContext(_arg010, _arg110);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _arg011 = data.createStringArrayList();
                            onNotificationsSeen(_arg011);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            onPanelRevealed(_arg012);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            onPanelHidden();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            if (data.readInt() != 0) {
                                _arg26 = true;
                            }
                            onNotificationVisibilityChanged(_arg013, _arg26);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            if (data.readInt() != 0) {
                                _arg16 = true;
                            } else {
                                _arg16 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg26 = true;
                            }
                            onNotificationExpansionChanged(_arg014, _arg16, _arg26);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            onNotificationDirectReply(_arg015);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            onSuggestedReplySent(_arg016, _arg17, data.readInt());
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = Notification.Action.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            onActionClicked(_arg017, _arg18, data.readInt());
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            onNotificationClicked(_arg018);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            onAllowedAdjustmentsChanged();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            if (data.readInt() != 0) {
                                _arg19 = NotificationRankingUpdate.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg25 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            onNotificationFeedbackReceived(_arg019, _arg19, _arg25);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements INotificationListener {
            public static INotificationListener sDefaultImpl;
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

            @Override // android.service.notification.INotificationListener
            public void onListenerConnected(NotificationRankingUpdate update) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (update != null) {
                        _data.writeInt(1);
                        update.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onListenerConnected(update);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationPosted(IStatusBarNotificationHolder notificationHolder, NotificationRankingUpdate update) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(notificationHolder != null ? notificationHolder.asBinder() : null);
                    if (update != null) {
                        _data.writeInt(1);
                        update.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationPosted(notificationHolder, update);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onStatusBarIconsBehaviorChanged(boolean hideSilentStatusIcons) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(hideSilentStatusIcons ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStatusBarIconsBehaviorChanged(hideSilentStatusIcons);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationRemoved(IStatusBarNotificationHolder notificationHolder, NotificationRankingUpdate update, NotificationStats stats, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(notificationHolder != null ? notificationHolder.asBinder() : null);
                    if (update != null) {
                        _data.writeInt(1);
                        update.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (stats != null) {
                        _data.writeInt(1);
                        stats.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationRemoved(notificationHolder, update, stats, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationRankingUpdate(NotificationRankingUpdate update) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (update != null) {
                        _data.writeInt(1);
                        update.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationRankingUpdate(update);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onListenerHintsChanged(int hints) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(hints);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onListenerHintsChanged(hints);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onInterruptionFilterChanged(int interruptionFilter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(interruptionFilter);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInterruptionFilterChanged(interruptionFilter);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationChannelModification(String pkgName, UserHandle user, NotificationChannel channel, int modificationType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (channel != null) {
                        _data.writeInt(1);
                        channel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(modificationType);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationChannelModification(pkgName, user, channel, modificationType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationChannelGroupModification(String pkgName, UserHandle user, NotificationChannelGroup group, int modificationType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (group != null) {
                        _data.writeInt(1);
                        group.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(modificationType);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationChannelGroupModification(pkgName, user, group, modificationType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder notificationHolder, NotificationChannel channel, NotificationRankingUpdate update) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(notificationHolder != null ? notificationHolder.asBinder() : null);
                    if (channel != null) {
                        _data.writeInt(1);
                        channel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (update != null) {
                        _data.writeInt(1);
                        update.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationEnqueuedWithChannel(notificationHolder, channel, update);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder notificationHolder, String snoozeCriterionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(notificationHolder != null ? notificationHolder.asBinder() : null);
                    _data.writeString(snoozeCriterionId);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationSnoozedUntilContext(notificationHolder, snoozeCriterionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationsSeen(List<String> keys) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringList(keys);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationsSeen(keys);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onPanelRevealed(int items) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(items);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanelRevealed(items);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onPanelHidden() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanelHidden();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationVisibilityChanged(String key, boolean isVisible) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeInt(isVisible ? 1 : 0);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationVisibilityChanged(key, isVisible);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationExpansionChanged(String key, boolean userAction, boolean expanded) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    int i = 0;
                    _data.writeInt(userAction ? 1 : 0);
                    if (expanded) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationExpansionChanged(key, userAction, expanded);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationDirectReply(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationDirectReply(key);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onSuggestedReplySent(String key, CharSequence reply, int source) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (reply != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(reply, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(source);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuggestedReplySent(key, reply, source);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onActionClicked(String key, Notification.Action action, int source) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (action != null) {
                        _data.writeInt(1);
                        action.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(source);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActionClicked(key, action, source);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationClicked(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationClicked(key);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onAllowedAdjustmentsChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAllowedAdjustmentsChanged();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationFeedbackReceived(String key, NotificationRankingUpdate update, Bundle feedback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (update != null) {
                        _data.writeInt(1);
                        update.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (feedback != null) {
                        _data.writeInt(1);
                        feedback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationFeedbackReceived(key, update, feedback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INotificationListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INotificationListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
