package android.print;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.print.IPrintSpoolerCallbacks;
import android.print.IPrintSpoolerClient;
import android.text.TextUtils;
import java.util.List;
/* loaded from: classes2.dex */
public interface IPrintSpooler extends IInterface {
    void clearCustomPrinterIconCache(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void createPrintJob(PrintJobInfo printJobInfo) throws RemoteException;

    void getCustomPrinterIcon(PrinterId printerId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void getPrintJobInfo(PrintJobId printJobId, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i, int i2) throws RemoteException;

    void getPrintJobInfos(IPrintSpoolerCallbacks iPrintSpoolerCallbacks, ComponentName componentName, int i, int i2, int i3) throws RemoteException;

    void onCustomPrinterIconLoaded(PrinterId printerId, Icon icon, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void pruneApprovedPrintServices(List<ComponentName> list) throws RemoteException;

    void removeObsoletePrintJobs() throws RemoteException;

    void setClient(IPrintSpoolerClient iPrintSpoolerClient) throws RemoteException;

    void setPrintJobCancelling(PrintJobId printJobId, boolean z) throws RemoteException;

    void setPrintJobState(PrintJobId printJobId, int i, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i2) throws RemoteException;

    void setPrintJobTag(PrintJobId printJobId, String str, IPrintSpoolerCallbacks iPrintSpoolerCallbacks, int i) throws RemoteException;

    void setProgress(PrintJobId printJobId, float f) throws RemoteException;

    void setStatus(PrintJobId printJobId, CharSequence charSequence) throws RemoteException;

    void setStatusRes(PrintJobId printJobId, int i, CharSequence charSequence) throws RemoteException;

    void writePrintJobData(ParcelFileDescriptor parcelFileDescriptor, PrintJobId printJobId) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IPrintSpooler {
        @Override // android.print.IPrintSpooler
        public void removeObsoletePrintJobs() throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void getPrintJobInfos(IPrintSpoolerCallbacks callback, ComponentName componentName, int state, int appId, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void getPrintJobInfo(PrintJobId printJobId, IPrintSpoolerCallbacks callback, int appId, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void createPrintJob(PrintJobInfo printJob) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setPrintJobState(PrintJobId printJobId, int status, String stateReason, IPrintSpoolerCallbacks callback, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setProgress(PrintJobId printJobId, float progress) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setStatus(PrintJobId printJobId, CharSequence status) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setStatusRes(PrintJobId printJobId, int status, CharSequence appPackageName) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void onCustomPrinterIconLoaded(PrinterId printerId, Icon icon, IPrintSpoolerCallbacks callbacks, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void getCustomPrinterIcon(PrinterId printerId, IPrintSpoolerCallbacks callbacks, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void clearCustomPrinterIconCache(IPrintSpoolerCallbacks callbacks, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setPrintJobTag(PrintJobId printJobId, String tag, IPrintSpoolerCallbacks callback, int sequence) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void writePrintJobData(ParcelFileDescriptor fd, PrintJobId printJobId) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setClient(IPrintSpoolerClient client) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void setPrintJobCancelling(PrintJobId printJobId, boolean cancelling) throws RemoteException {
        }

        @Override // android.print.IPrintSpooler
        public void pruneApprovedPrintServices(List<ComponentName> servicesToKeep) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPrintSpooler {
        public static final String DESCRIPTOR = "android.print.IPrintSpooler";
        static final int TRANSACTION_clearCustomPrinterIconCache = 11;
        static final int TRANSACTION_createPrintJob = 4;
        static final int TRANSACTION_getCustomPrinterIcon = 10;
        static final int TRANSACTION_getPrintJobInfo = 3;
        static final int TRANSACTION_getPrintJobInfos = 2;
        static final int TRANSACTION_onCustomPrinterIconLoaded = 9;
        static final int TRANSACTION_pruneApprovedPrintServices = 16;
        static final int TRANSACTION_removeObsoletePrintJobs = 1;
        static final int TRANSACTION_setClient = 14;
        static final int TRANSACTION_setPrintJobCancelling = 15;
        static final int TRANSACTION_setPrintJobState = 5;
        static final int TRANSACTION_setPrintJobTag = 12;
        static final int TRANSACTION_setProgress = 6;
        static final int TRANSACTION_setStatus = 7;
        static final int TRANSACTION_setStatusRes = 8;
        static final int TRANSACTION_writePrintJobData = 13;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrintSpooler asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPrintSpooler)) {
                return new Proxy(obj);
            }
            return (IPrintSpooler) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "removeObsoletePrintJobs";
                case 2:
                    return "getPrintJobInfos";
                case 3:
                    return "getPrintJobInfo";
                case 4:
                    return "createPrintJob";
                case 5:
                    return "setPrintJobState";
                case 6:
                    return "setProgress";
                case 7:
                    return "setStatus";
                case 8:
                    return "setStatusRes";
                case 9:
                    return "onCustomPrinterIconLoaded";
                case 10:
                    return "getCustomPrinterIcon";
                case 11:
                    return "clearCustomPrinterIconCache";
                case 12:
                    return "setPrintJobTag";
                case 13:
                    return "writePrintJobData";
                case 14:
                    return "setClient";
                case 15:
                    return "setPrintJobCancelling";
                case 16:
                    return "pruneApprovedPrintServices";
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
            ComponentName _arg1;
            PrintJobId _arg0;
            PrintJobInfo _arg02;
            PrintJobId _arg03;
            PrintJobId _arg04;
            PrintJobId _arg05;
            CharSequence _arg12;
            PrintJobId _arg06;
            CharSequence _arg2;
            PrinterId _arg07;
            Icon _arg13;
            PrinterId _arg08;
            PrintJobId _arg09;
            ParcelFileDescriptor _arg010;
            PrintJobId _arg14;
            PrintJobId _arg011;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            removeObsoletePrintJobs();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IPrintSpoolerCallbacks _arg012 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg22 = data.readInt();
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            getPrintJobInfos(_arg012, _arg1, _arg22, _arg3, _arg4);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IPrintSpoolerCallbacks _arg15 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            int _arg23 = data.readInt();
                            int _arg32 = data.readInt();
                            getPrintJobInfo(_arg0, _arg15, _arg23, _arg32);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = PrintJobInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            createPrintJob(_arg02);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _arg16 = data.readInt();
                            String _arg24 = data.readString();
                            IPrintSpoolerCallbacks _arg33 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            int _arg42 = data.readInt();
                            setPrintJobState(_arg03, _arg16, _arg24, _arg33, _arg42);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            float _arg17 = data.readFloat();
                            setProgress(_arg04, _arg17);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            setStatus(_arg05, _arg12);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            int _arg18 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            setStatusRes(_arg06, _arg18, _arg2);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = PrinterId.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = Icon.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            IPrintSpoolerCallbacks _arg25 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            int _arg34 = data.readInt();
                            onCustomPrinterIconLoaded(_arg07, _arg13, _arg25, _arg34);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = PrinterId.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            IPrintSpoolerCallbacks _arg19 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            int _arg26 = data.readInt();
                            getCustomPrinterIcon(_arg08, _arg19, _arg26);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            IPrintSpoolerCallbacks _arg013 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            int _arg110 = data.readInt();
                            clearCustomPrinterIconCache(_arg013, _arg110);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            String _arg111 = data.readString();
                            IPrintSpoolerCallbacks _arg27 = IPrintSpoolerCallbacks.Stub.asInterface(data.readStrongBinder());
                            int _arg35 = data.readInt();
                            setPrintJobTag(_arg09, _arg111, _arg27, _arg35);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg14 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            writePrintJobData(_arg010, _arg14);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            IPrintSpoolerClient _arg014 = IPrintSpoolerClient.Stub.asInterface(data.readStrongBinder());
                            setClient(_arg014);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = PrintJobId.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            boolean _arg112 = data.readInt() != 0;
                            setPrintJobCancelling(_arg011, _arg112);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            List<ComponentName> _arg015 = data.createTypedArrayList(ComponentName.CREATOR);
                            pruneApprovedPrintServices(_arg015);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPrintSpooler {
            public static IPrintSpooler sDefaultImpl;
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

            @Override // android.print.IPrintSpooler
            public void removeObsoletePrintJobs() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeObsoletePrintJobs();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void getPrintJobInfos(IPrintSpoolerCallbacks callback, ComponentName componentName, int state, int appId, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(state);
                    _data.writeInt(appId);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPrintJobInfos(callback, componentName, state, appId, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void getPrintJobInfo(PrintJobId printJobId, IPrintSpoolerCallbacks callback, int appId, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(appId);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPrintJobInfo(printJobId, callback, appId, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void createPrintJob(PrintJobInfo printJob) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJob != null) {
                        _data.writeInt(1);
                        printJob.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createPrintJob(printJob);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setPrintJobState(PrintJobId printJobId, int status, String stateReason, IPrintSpoolerCallbacks callback, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(status);
                    _data.writeString(stateReason);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPrintJobState(printJobId, status, stateReason, callback, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setProgress(PrintJobId printJobId, float progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeFloat(progress);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setProgress(printJobId, progress);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setStatus(PrintJobId printJobId, CharSequence status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (status != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(status, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStatus(printJobId, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setStatusRes(PrintJobId printJobId, int status, CharSequence appPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(status);
                    if (appPackageName != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(appPackageName, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStatusRes(printJobId, status, appPackageName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void onCustomPrinterIconLoaded(PrinterId printerId, Icon icon, IPrintSpoolerCallbacks callbacks, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        _data.writeInt(1);
                        printerId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (icon != null) {
                        _data.writeInt(1);
                        icon.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCustomPrinterIconLoaded(printerId, icon, callbacks, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void getCustomPrinterIcon(PrinterId printerId, IPrintSpoolerCallbacks callbacks, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printerId != null) {
                        _data.writeInt(1);
                        printerId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCustomPrinterIcon(printerId, callbacks, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void clearCustomPrinterIconCache(IPrintSpoolerCallbacks callbacks, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearCustomPrinterIconCache(callbacks, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setPrintJobTag(PrintJobId printJobId, String tag, IPrintSpoolerCallbacks callback, int sequence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(tag);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(sequence);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPrintJobTag(printJobId, tag, callback, sequence);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void writePrintJobData(ParcelFileDescriptor fd, PrintJobId printJobId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().writePrintJobData(fd, printJobId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setClient(IPrintSpoolerClient client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setClient(client);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void setPrintJobCancelling(PrintJobId printJobId, boolean cancelling) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (printJobId != null) {
                        _data.writeInt(1);
                        printJobId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (cancelling) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPrintJobCancelling(printJobId, cancelling);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.print.IPrintSpooler
            public void pruneApprovedPrintServices(List<ComponentName> servicesToKeep) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(servicesToKeep);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pruneApprovedPrintServices(servicesToKeep);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrintSpooler impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPrintSpooler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
