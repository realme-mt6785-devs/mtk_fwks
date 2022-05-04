package android.view;

import android.graphics.Point;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.view.IScrollCaptureResponseListener;
import android.window.ClientWindowFrames;
import com.android.internal.os.IResultReceiver;
/* loaded from: classes3.dex */
public interface IWindow extends IInterface {
    void closeSystemDialogs(String str) throws RemoteException;

    void dispatchAppVisibility(boolean z) throws RemoteException;

    void dispatchDragEvent(DragEvent dragEvent) throws RemoteException;

    void dispatchGetNewSurface() throws RemoteException;

    void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException;

    void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) throws RemoteException;

    void dispatchWindowShown() throws RemoteException;

    void executeCommand(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void hideInsets(int i, boolean z) throws RemoteException;

    void insetsChanged(InsetsState insetsState, boolean z, boolean z2) throws RemoteException;

    void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr, boolean z, boolean z2) throws RemoteException;

    void locationInParentDisplayChanged(Point point) throws RemoteException;

    void moved(int i, int i2) throws RemoteException;

    void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException;

    void requestScrollCapture(IScrollCaptureResponseListener iScrollCaptureResponseListener) throws RemoteException;

    void resized(ClientWindowFrames clientWindowFrames, boolean z, MergedConfiguration mergedConfiguration, boolean z2, boolean z3, int i) throws RemoteException;

    void showInsets(int i, boolean z) throws RemoteException;

    void updatePointerIcon(float f, float f2) throws RemoteException;

    void windowFocusChanged(boolean z, boolean z2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindow {
        @Override // android.view.IWindow
        public void executeCommand(String command, String parameters, ParcelFileDescriptor descriptor) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void resized(ClientWindowFrames frames, boolean reportDraw, MergedConfiguration newMergedConfiguration, boolean forceLayout, boolean alwaysConsumeSystemBars, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void locationInParentDisplayChanged(Point offset) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void insetsChanged(InsetsState insetsState, boolean willMove, boolean willResize) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls, boolean willMove, boolean willResize) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void showInsets(int types, boolean fromIme) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void hideInsets(int types, boolean fromIme) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void moved(int newX, int newY) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchAppVisibility(boolean visible) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchGetNewSurface() throws RemoteException {
        }

        @Override // android.view.IWindow
        public void windowFocusChanged(boolean hasFocus, boolean inTouchMode) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void closeSystemDialogs(String reason) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperOffsets(float x, float y, float xStep, float yStep, float zoom, boolean sync) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperCommand(String action, int x, int y, int z, Bundle extras, boolean sync) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchDragEvent(DragEvent event) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void updatePointerIcon(float x, float y) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchWindowShown() throws RemoteException {
        }

        @Override // android.view.IWindow
        public void requestAppKeyboardShortcuts(IResultReceiver receiver, int deviceId) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void requestScrollCapture(IScrollCaptureResponseListener callbacks) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindow {
        public static final String DESCRIPTOR = "android.view.IWindow";
        static final int TRANSACTION_closeSystemDialogs = 12;
        static final int TRANSACTION_dispatchAppVisibility = 9;
        static final int TRANSACTION_dispatchDragEvent = 15;
        static final int TRANSACTION_dispatchGetNewSurface = 10;
        static final int TRANSACTION_dispatchWallpaperCommand = 14;
        static final int TRANSACTION_dispatchWallpaperOffsets = 13;
        static final int TRANSACTION_dispatchWindowShown = 17;
        static final int TRANSACTION_executeCommand = 1;
        static final int TRANSACTION_hideInsets = 7;
        static final int TRANSACTION_insetsChanged = 4;
        static final int TRANSACTION_insetsControlChanged = 5;
        static final int TRANSACTION_locationInParentDisplayChanged = 3;
        static final int TRANSACTION_moved = 8;
        static final int TRANSACTION_requestAppKeyboardShortcuts = 18;
        static final int TRANSACTION_requestScrollCapture = 19;
        static final int TRANSACTION_resized = 2;
        static final int TRANSACTION_showInsets = 6;
        static final int TRANSACTION_updatePointerIcon = 16;
        static final int TRANSACTION_windowFocusChanged = 11;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindow asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindow)) {
                return new Proxy(obj);
            }
            return (IWindow) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "executeCommand";
                case 2:
                    return "resized";
                case 3:
                    return "locationInParentDisplayChanged";
                case 4:
                    return "insetsChanged";
                case 5:
                    return "insetsControlChanged";
                case 6:
                    return "showInsets";
                case 7:
                    return "hideInsets";
                case 8:
                    return "moved";
                case 9:
                    return "dispatchAppVisibility";
                case 10:
                    return "dispatchGetNewSurface";
                case 11:
                    return "windowFocusChanged";
                case 12:
                    return "closeSystemDialogs";
                case 13:
                    return "dispatchWallpaperOffsets";
                case 14:
                    return "dispatchWallpaperCommand";
                case 15:
                    return "dispatchDragEvent";
                case 16:
                    return "updatePointerIcon";
                case 17:
                    return "dispatchWindowShown";
                case 18:
                    return "requestAppKeyboardShortcuts";
                case 19:
                    return "requestScrollCapture";
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
            ParcelFileDescriptor _arg2;
            ClientWindowFrames _arg0;
            MergedConfiguration _arg22;
            Point _arg02;
            InsetsState _arg03;
            InsetsState _arg04;
            Bundle _arg4;
            DragEvent _arg05;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            String _arg12 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            executeCommand(_arg06, _arg12, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ClientWindowFrames.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean _arg13 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg22 = MergedConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            boolean _arg3 = data.readInt() != 0;
                            boolean _arg42 = data.readInt() != 0;
                            int _arg5 = data.readInt();
                            resized(_arg0, _arg13, _arg22, _arg3, _arg42, _arg5);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Point.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            locationInParentDisplayChanged(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            boolean _arg14 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            insetsChanged(_arg03, _arg14, _arg1);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            InsetsSourceControl[] _arg15 = (InsetsSourceControl[]) data.createTypedArray(InsetsSourceControl.CREATOR);
                            boolean _arg23 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            insetsControlChanged(_arg04, _arg15, _arg23, _arg1);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            showInsets(_arg07, _arg1);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            hideInsets(_arg08, _arg1);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            moved(_arg09, data.readInt());
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            dispatchAppVisibility(_arg1);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            dispatchGetNewSurface();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            boolean _arg010 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            windowFocusChanged(_arg010, _arg1);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            closeSystemDialogs(_arg011);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            float _arg012 = data.readFloat();
                            float _arg16 = data.readFloat();
                            float _arg24 = data.readFloat();
                            float _arg32 = data.readFloat();
                            float _arg43 = data.readFloat();
                            boolean _arg52 = data.readInt() != 0;
                            dispatchWallpaperOffsets(_arg012, _arg16, _arg24, _arg32, _arg43, _arg52);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            int _arg17 = data.readInt();
                            int _arg25 = data.readInt();
                            int _arg33 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            boolean _arg53 = data.readInt() != 0;
                            dispatchWallpaperCommand(_arg013, _arg17, _arg25, _arg33, _arg4, _arg53);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = DragEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            dispatchDragEvent(_arg05);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            float _arg014 = data.readFloat();
                            updatePointerIcon(_arg014, data.readFloat());
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            dispatchWindowShown();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg015 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            requestAppKeyboardShortcuts(_arg015, data.readInt());
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            IScrollCaptureResponseListener _arg016 = IScrollCaptureResponseListener.Stub.asInterface(data.readStrongBinder());
                            requestScrollCapture(_arg016);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindow {
            public static IWindow sDefaultImpl;
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

            @Override // android.view.IWindow
            public void executeCommand(String command, String parameters, ParcelFileDescriptor descriptor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(command);
                    _data.writeString(parameters);
                    if (descriptor != null) {
                        _data.writeInt(1);
                        descriptor.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeCommand(command, parameters, descriptor);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void resized(ClientWindowFrames frames, boolean reportDraw, MergedConfiguration newMergedConfiguration, boolean forceLayout, boolean alwaysConsumeSystemBars, int displayId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (frames != null) {
                        _data.writeInt(1);
                        frames.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reportDraw ? 1 : 0);
                    if (newMergedConfiguration != null) {
                        _data.writeInt(1);
                        newMergedConfiguration.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(forceLayout ? 1 : 0);
                    if (alwaysConsumeSystemBars) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    try {
                        _data.writeInt(displayId);
                        try {
                            boolean _status = this.mRemote.transact(2, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().resized(frames, reportDraw, newMergedConfiguration, forceLayout, alwaysConsumeSystemBars, displayId);
                            _data.recycle();
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.view.IWindow
            public void locationInParentDisplayChanged(Point offset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (offset != null) {
                        _data.writeInt(1);
                        offset.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().locationInParentDisplayChanged(offset);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void insetsChanged(InsetsState insetsState, boolean willMove, boolean willResize) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (insetsState != null) {
                        _data.writeInt(1);
                        insetsState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(willMove ? 1 : 0);
                    if (willResize) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().insetsChanged(insetsState, willMove, willResize);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls, boolean willMove, boolean willResize) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (insetsState != null) {
                        _data.writeInt(1);
                        insetsState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(activeControls, 0);
                    _data.writeInt(willMove ? 1 : 0);
                    if (willResize) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().insetsControlChanged(insetsState, activeControls, willMove, willResize);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void showInsets(int types, boolean fromIme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(types);
                    _data.writeInt(fromIme ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInsets(types, fromIme);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void hideInsets(int types, boolean fromIme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(types);
                    _data.writeInt(fromIme ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideInsets(types, fromIme);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void moved(int newX, int newY) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(newX);
                    _data.writeInt(newY);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().moved(newX, newY);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchAppVisibility(boolean visible) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(visible ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchAppVisibility(visible);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchGetNewSurface() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchGetNewSurface();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void windowFocusChanged(boolean hasFocus, boolean inTouchMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    _data.writeInt(hasFocus ? 1 : 0);
                    if (inTouchMode) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().windowFocusChanged(hasFocus, inTouchMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void closeSystemDialogs(String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeSystemDialogs(reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchWallpaperOffsets(float x, float y, float xStep, float yStep, float zoom, boolean sync) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeFloat(x);
                        try {
                            _data.writeFloat(y);
                            try {
                                _data.writeFloat(xStep);
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
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
                try {
                    _data.writeFloat(yStep);
                    try {
                        _data.writeFloat(zoom);
                        _data.writeInt(sync ? 1 : 0);
                        try {
                            boolean _status = this.mRemote.transact(13, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().dispatchWallpaperOffsets(x, y, xStep, yStep, zoom, sync);
                            _data.recycle();
                        } catch (Throwable th6) {
                            th = th6;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindow
            public void dispatchWallpaperCommand(String action, int x, int y, int z, Bundle extras, boolean sync) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(action);
                        try {
                            _data.writeInt(x);
                            try {
                                _data.writeInt(y);
                                try {
                                    _data.writeInt(z);
                                    int i = 0;
                                    if (extras != null) {
                                        _data.writeInt(1);
                                        extras.writeToParcel(_data, 0);
                                    } else {
                                        _data.writeInt(0);
                                    }
                                    if (sync) {
                                        i = 1;
                                    }
                                    _data.writeInt(i);
                                    try {
                                        boolean _status = this.mRemote.transact(14, _data, null, 1);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _data.recycle();
                                            return;
                                        }
                                        Stub.getDefaultImpl().dispatchWallpaperCommand(action, x, y, z, extras, sync);
                                        _data.recycle();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        _data.recycle();
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.view.IWindow
            public void dispatchDragEvent(DragEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchDragEvent(event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void updatePointerIcon(float x, float y) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(x);
                    _data.writeFloat(y);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePointerIcon(x, y);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchWindowShown() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchWindowShown();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void requestAppKeyboardShortcuts(IResultReceiver receiver, int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestAppKeyboardShortcuts(receiver, deviceId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindow
            public void requestScrollCapture(IScrollCaptureResponseListener callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestScrollCapture(callbacks);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindow impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindow getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
