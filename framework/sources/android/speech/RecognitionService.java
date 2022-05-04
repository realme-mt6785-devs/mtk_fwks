package android.speech;

import android.Manifest;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.AttributionSource;
import android.content.Context;
import android.content.ContextParams;
import android.content.Intent;
import android.content.PermissionChecker;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.speech.IRecognitionService;
import android.util.Log;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public abstract class RecognitionService extends Service {
    private static final boolean DBG = false;
    private static final int MSG_CANCEL = 3;
    private static final int MSG_RESET = 4;
    private static final int MSG_START_LISTENING = 1;
    private static final int MSG_STOP_LISTENING = 2;
    public static final String SERVICE_INTERFACE = "android.speech.RecognitionService";
    public static final String SERVICE_META_DATA = "android.speech";
    private static final String TAG = "RecognitionService";
    private RecognitionServiceBinder mBinder = new RecognitionServiceBinder(this);
    private Callback mCurrentCallback = null;
    private final Handler mHandler = new Handler() { // from class: android.speech.RecognitionService.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    StartListeningArgs args = (StartListeningArgs) msg.obj;
                    RecognitionService.this.dispatchStartListening(args.mIntent, args.mListener, args.mAttributionSource);
                    return;
                case 2:
                    RecognitionService.this.dispatchStopListening((IRecognitionListener) msg.obj);
                    return;
                case 3:
                    RecognitionService.this.dispatchCancel((IRecognitionListener) msg.obj);
                    return;
                case 4:
                    RecognitionService.this.dispatchClearCallback();
                    return;
                default:
                    return;
            }
        }
    };
    private boolean mStartedDataDelivery;

    protected abstract void onCancel(Callback callback);

    protected abstract void onStartListening(Intent intent, Callback callback);

    protected abstract void onStopListening(Callback callback);

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchStartListening(Intent intent, IRecognitionListener listener, AttributionSource attributionSource) {
        try {
            if (this.mCurrentCallback == null) {
                boolean preflightPermissionCheckPassed = checkPermissionForPreflight(attributionSource);
                if (preflightPermissionCheckPassed) {
                    Callback callback = new Callback(listener, attributionSource);
                    this.mCurrentCallback = callback;
                    onStartListening(intent, callback);
                }
                if (!preflightPermissionCheckPassed || !checkPermissionAndStartDataDelivery()) {
                    listener.onError(9);
                    if (preflightPermissionCheckPassed) {
                        onCancel(this.mCurrentCallback);
                        dispatchClearCallback();
                    }
                    Log.i(TAG, "caller doesn't have permission:android.permission.RECORD_AUDIO");
                }
                return;
            }
            listener.onError(8);
            Log.i(TAG, "concurrent startListening received - ignoring this call");
        } catch (RemoteException e) {
            Log.d(TAG, "onError call from startListening failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchStopListening(IRecognitionListener listener) {
        try {
            Callback callback = this.mCurrentCallback;
            if (callback == null) {
                listener.onError(5);
                Log.w(TAG, "stopListening called with no preceding startListening - ignoring");
            } else if (callback.mListener.asBinder() != listener.asBinder()) {
                listener.onError(8);
                Log.w(TAG, "stopListening called by other caller than startListening - ignoring");
            } else {
                onStopListening(this.mCurrentCallback);
            }
        } catch (RemoteException e) {
            Log.d(TAG, "onError call from stopListening failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCancel(IRecognitionListener listener) {
        Callback callback = this.mCurrentCallback;
        if (callback != null) {
            if (callback.mListener.asBinder() != listener.asBinder()) {
                Log.w(TAG, "cancel called by client who did not call startListening - ignoring");
                return;
            }
            onCancel(this.mCurrentCallback);
            dispatchClearCallback();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchClearCallback() {
        finishDataDelivery();
        this.mCurrentCallback = null;
        this.mStartedDataDelivery = false;
    }

    /* loaded from: classes3.dex */
    private class StartListeningArgs {
        public final AttributionSource mAttributionSource;
        public final Intent mIntent;
        public final IRecognitionListener mListener;

        public StartListeningArgs(Intent intent, IRecognitionListener listener, AttributionSource attributionSource) {
            this.mIntent = intent;
            this.mListener = listener;
            this.mAttributionSource = attributionSource;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createContext(ContextParams contextParams) {
        if (contextParams.getNextAttributionSource() != null) {
            if (this.mHandler.getLooper().equals(Looper.myLooper())) {
                handleAttributionContextCreation(contextParams.getNextAttributionSource());
            } else {
                this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.speech.RecognitionService$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RecognitionService.this.handleAttributionContextCreation((AttributionSource) obj);
                    }
                }, contextParams.getNextAttributionSource()));
            }
        }
        return super.createContext(contextParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAttributionContextCreation(AttributionSource attributionSource) {
        Callback callback = this.mCurrentCallback;
        if (callback != null && callback.mCallingAttributionSource.equals(attributionSource)) {
            this.mCurrentCallback.mAttributionContextCreated = true;
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        finishDataDelivery();
        this.mCurrentCallback = null;
        this.mBinder.clearReference();
        super.onDestroy();
    }

    /* loaded from: classes3.dex */
    public class Callback {
        private Context mAttributionContext;
        private boolean mAttributionContextCreated;
        private final AttributionSource mCallingAttributionSource;
        private final IRecognitionListener mListener;

        private Callback(IRecognitionListener listener, AttributionSource attributionSource) {
            this.mListener = listener;
            this.mCallingAttributionSource = attributionSource;
        }

        public void beginningOfSpeech() throws RemoteException {
            this.mListener.onBeginningOfSpeech();
        }

        public void bufferReceived(byte[] buffer) throws RemoteException {
            this.mListener.onBufferReceived(buffer);
        }

        public void endOfSpeech() throws RemoteException {
            this.mListener.onEndOfSpeech();
        }

        public void error(int error) throws RemoteException {
            Message.obtain(RecognitionService.this.mHandler, 4).sendToTarget();
            this.mListener.onError(error);
        }

        public void partialResults(Bundle partialResults) throws RemoteException {
            this.mListener.onPartialResults(partialResults);
        }

        public void readyForSpeech(Bundle params) throws RemoteException {
            this.mListener.onReadyForSpeech(params);
        }

        public void results(Bundle results) throws RemoteException {
            Message.obtain(RecognitionService.this.mHandler, 4).sendToTarget();
            this.mListener.onResults(results);
        }

        public void rmsChanged(float rmsdB) throws RemoteException {
            this.mListener.onRmsChanged(rmsdB);
        }

        public int getCallingUid() {
            return this.mCallingAttributionSource.getUid();
        }

        public AttributionSource getCallingAttributionSource() {
            return this.mCallingAttributionSource;
        }

        Context getAttributionContextForCaller() {
            if (this.mAttributionContext == null) {
                this.mAttributionContext = RecognitionService.this.createContext(new ContextParams.Builder().setNextAttributionSource(this.mCallingAttributionSource).build());
            }
            return this.mAttributionContext;
        }
    }

    /* loaded from: classes3.dex */
    private static final class RecognitionServiceBinder extends IRecognitionService.Stub {
        private final WeakReference<RecognitionService> mServiceRef;

        public RecognitionServiceBinder(RecognitionService service) {
            this.mServiceRef = new WeakReference<>(service);
        }

        @Override // android.speech.IRecognitionService
        public void startListening(Intent recognizerIntent, IRecognitionListener listener, AttributionSource attributionSource) {
            Objects.requireNonNull(attributionSource);
            attributionSource.enforceCallingUid();
            RecognitionService service = this.mServiceRef.get();
            if (service != null) {
                Handler handler = service.mHandler;
                Handler handler2 = service.mHandler;
                Objects.requireNonNull(service);
                handler.sendMessage(Message.obtain(handler2, 1, new StartListeningArgs(recognizerIntent, listener, attributionSource)));
            }
        }

        @Override // android.speech.IRecognitionService
        public void stopListening(IRecognitionListener listener) {
            RecognitionService service = this.mServiceRef.get();
            if (service != null) {
                service.mHandler.sendMessage(Message.obtain(service.mHandler, 2, listener));
            }
        }

        @Override // android.speech.IRecognitionService
        public void cancel(IRecognitionListener listener, boolean isShutdown) {
            RecognitionService service = this.mServiceRef.get();
            if (service != null) {
                service.mHandler.sendMessage(Message.obtain(service.mHandler, 3, listener));
            }
        }

        public void clearReference() {
            this.mServiceRef.clear();
        }
    }

    private boolean checkPermissionAndStartDataDelivery() {
        if (this.mCurrentCallback.mAttributionContextCreated) {
            return true;
        }
        if (PermissionChecker.checkPermissionAndStartDataDelivery(this, Manifest.permission.RECORD_AUDIO, this.mCurrentCallback.getAttributionContextForCaller().getAttributionSource(), null) == 0) {
            this.mStartedDataDelivery = true;
        }
        return this.mStartedDataDelivery;
    }

    private boolean checkPermissionForPreflight(AttributionSource attributionSource) {
        return PermissionChecker.checkPermissionForPreflight(this, Manifest.permission.RECORD_AUDIO, attributionSource) == 0;
    }

    void finishDataDelivery() {
        if (this.mStartedDataDelivery) {
            this.mStartedDataDelivery = false;
            String op = AppOpsManager.permissionToOp(Manifest.permission.RECORD_AUDIO);
            PermissionChecker.finishDataDelivery(this, op, this.mCurrentCallback.getAttributionContextForCaller().getAttributionSource());
        }
    }
}
