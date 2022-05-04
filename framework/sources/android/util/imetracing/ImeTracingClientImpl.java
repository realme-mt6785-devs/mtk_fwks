package android.util.imetracing;

import android.inputmethodservice.AbstractInputMethodService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.inputmethod.InputMethodManager;
import java.io.PrintWriter;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ImeTracingClientImpl extends ImeTracing {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ImeTracingClientImpl() throws ServiceManager.ServiceNotFoundException, RemoteException {
        sEnabled = this.mService.isImeTraceEnabled();
    }

    @Override // android.util.imetracing.ImeTracing
    public void addToBuffer(ProtoOutputStream proto, int source) {
    }

    @Override // android.util.imetracing.ImeTracing
    public void triggerClientDump(String where, InputMethodManager immInstance, ProtoOutputStream icProto) {
        if (isEnabled() && isAvailable()) {
            synchronized (this.mDumpInProgressLock) {
                if (!this.mDumpInProgress) {
                    this.mDumpInProgress = true;
                    try {
                        try {
                            ProtoOutputStream proto = new ProtoOutputStream();
                            immInstance.dumpDebug(proto, icProto);
                            sendToService(proto.getBytes(), 0, where);
                        } catch (RemoteException e) {
                            Log.e("imeTracing", "Exception while sending ime-related client dump to server", e);
                        }
                    } finally {
                        this.mDumpInProgress = false;
                    }
                }
            }
        }
    }

    @Override // android.util.imetracing.ImeTracing
    public void triggerServiceDump(String where, AbstractInputMethodService service, ProtoOutputStream icProto) {
        if (isEnabled() && isAvailable()) {
            synchronized (this.mDumpInProgressLock) {
                if (!this.mDumpInProgress) {
                    this.mDumpInProgress = true;
                    try {
                        try {
                            ProtoOutputStream proto = new ProtoOutputStream();
                            service.dumpProtoInternal(proto, icProto);
                            sendToService(proto.getBytes(), 1, where);
                        } catch (RemoteException e) {
                            Log.e("imeTracing", "Exception while sending ime-related service dump to server", e);
                        }
                    } finally {
                        this.mDumpInProgress = false;
                    }
                }
            }
        }
    }

    @Override // android.util.imetracing.ImeTracing
    public void triggerManagerServiceDump(String where) {
    }

    @Override // android.util.imetracing.ImeTracing
    public void startTrace(PrintWriter pw) {
    }

    @Override // android.util.imetracing.ImeTracing
    public void stopTrace(PrintWriter pw) {
    }
}
