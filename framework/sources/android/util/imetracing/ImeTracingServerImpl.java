package android.util.imetracing;

import android.inputmethodservice.AbstractInputMethodService;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.util.TraceBuffer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ImeTracingServerImpl extends ImeTracing {
    private static final int BUFFER_CAPACITY = 4194304;
    private static final long MAGIC_NUMBER_CLIENTS_VALUE = 4990904633913462089L;
    private static final long MAGIC_NUMBER_IMMS_VALUE = 4990904633914117449L;
    private static final long MAGIC_NUMBER_IMS_VALUE = 4990904633914510665L;
    private static final String TRACE_DIRNAME = "/data/misc/wmtrace/";
    private static final String TRACE_FILENAME_CLIENTS = "ime_trace_clients.pb";
    private static final String TRACE_FILENAME_IMMS = "ime_trace_managerservice.pb";
    private static final String TRACE_FILENAME_IMS = "ime_trace_service.pb";
    private final Object mEnabledLock = new Object();
    private final TraceBuffer mBufferClients = new TraceBuffer(4194304);
    private final File mTraceFileClients = new File("/data/misc/wmtrace/ime_trace_clients.pb");
    private final TraceBuffer mBufferIms = new TraceBuffer(4194304);
    private final File mTraceFileIms = new File("/data/misc/wmtrace/ime_trace_service.pb");
    private final TraceBuffer mBufferImms = new TraceBuffer(4194304);
    private final File mTraceFileImms = new File("/data/misc/wmtrace/ime_trace_managerservice.pb");

    @Override // android.util.imetracing.ImeTracing
    public void addToBuffer(ProtoOutputStream proto, int source) {
        if (isAvailable() && isEnabled()) {
            switch (source) {
                case 0:
                    this.mBufferClients.add(proto);
                    return;
                case 1:
                    this.mBufferIms.add(proto);
                    return;
                case 2:
                    this.mBufferImms.add(proto);
                    return;
                default:
                    Log.w("imeTracing", "Request to add to buffer, but source not recognised.");
                    return;
            }
        }
    }

    @Override // android.util.imetracing.ImeTracing
    public void triggerClientDump(String where, InputMethodManager immInstance, ProtoOutputStream icProto) {
    }

    @Override // android.util.imetracing.ImeTracing
    public void triggerServiceDump(String where, AbstractInputMethodService service, ProtoOutputStream icProto) {
    }

    @Override // android.util.imetracing.ImeTracing
    public void triggerManagerServiceDump(String where) {
        if (isEnabled() && isAvailable()) {
            synchronized (this.mDumpInProgressLock) {
                if (!this.mDumpInProgress) {
                    this.mDumpInProgress = true;
                    try {
                        try {
                            sendToService(null, 2, where);
                        } catch (RemoteException e) {
                            Log.e("imeTracing", "Exception while sending ime-related manager service dump to server", e);
                        }
                    } finally {
                        this.mDumpInProgress = false;
                    }
                }
            }
        }
    }

    private void writeTracesToFilesLocked() {
        try {
            ProtoOutputStream clientsProto = new ProtoOutputStream();
            clientsProto.write(1125281431553L, MAGIC_NUMBER_CLIENTS_VALUE);
            this.mBufferClients.writeTraceToFile(this.mTraceFileClients, clientsProto);
            ProtoOutputStream imsProto = new ProtoOutputStream();
            imsProto.write(1125281431553L, MAGIC_NUMBER_IMS_VALUE);
            this.mBufferIms.writeTraceToFile(this.mTraceFileIms, imsProto);
            ProtoOutputStream immsProto = new ProtoOutputStream();
            immsProto.write(1125281431553L, MAGIC_NUMBER_IMMS_VALUE);
            this.mBufferImms.writeTraceToFile(this.mTraceFileImms, immsProto);
            resetBuffers();
        } catch (IOException e) {
            Log.e("imeTracing", "Unable to write buffer to file", e);
        }
    }

    @Override // android.util.imetracing.ImeTracing
    public void startTrace(PrintWriter pw) {
        if (Build.IS_USER) {
            Log.w("imeTracing", "Warn: Tracing is not supported on user builds.");
            return;
        }
        synchronized (this.mEnabledLock) {
            if (!isAvailable() || !isEnabled()) {
                logAndPrintln(pw, "Starting tracing in /data/misc/wmtrace/: ime_trace_clients.pb, ime_trace_service.pb, ime_trace_managerservice.pb");
                sEnabled = true;
                resetBuffers();
                return;
            }
            Log.w("imeTracing", "Warn: Tracing is already started.");
        }
    }

    @Override // android.util.imetracing.ImeTracing
    public void stopTrace(PrintWriter pw) {
        if (Build.IS_USER) {
            Log.w("imeTracing", "Warn: Tracing is not supported on user builds.");
            return;
        }
        synchronized (this.mEnabledLock) {
            if (isAvailable() && isEnabled()) {
                logAndPrintln(pw, "Stopping tracing and writing traces in /data/misc/wmtrace/: ime_trace_clients.pb, ime_trace_service.pb, ime_trace_managerservice.pb");
                sEnabled = false;
                writeTracesToFilesLocked();
                return;
            }
            Log.w("imeTracing", "Warn: Tracing is not available or not started.");
        }
    }

    @Override // android.util.imetracing.ImeTracing
    public void saveForBugreport(PrintWriter pw) {
        if (!Build.IS_USER) {
            synchronized (this.mEnabledLock) {
                if (isAvailable() && isEnabled()) {
                    sEnabled = false;
                    logAndPrintln(pw, "Writing traces in /data/misc/wmtrace/: ime_trace_clients.pb, ime_trace_service.pb, ime_trace_managerservice.pb");
                    writeTracesToFilesLocked();
                    sEnabled = true;
                }
            }
        }
    }

    private void resetBuffers() {
        this.mBufferClients.resetBuffer();
        this.mBufferIms.resetBuffer();
        this.mBufferImms.resetBuffer();
    }
}
