package android.speech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.speech.IRecognitionListener;
import android.speech.IRecognitionServiceManager;
import android.speech.IRecognitionServiceManagerCallback;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.R;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes3.dex */
public class SpeechRecognizer {
    public static final String CONFIDENCE_SCORES = "confidence_scores";
    private static final boolean DBG = false;
    public static final int ERROR_AUDIO = 3;
    public static final int ERROR_CLIENT = 5;
    public static final int ERROR_INSUFFICIENT_PERMISSIONS = 9;
    public static final int ERROR_LANGUAGE_NOT_SUPPORTED = 12;
    public static final int ERROR_LANGUAGE_UNAVAILABLE = 13;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_NETWORK_TIMEOUT = 1;
    public static final int ERROR_NO_MATCH = 7;
    public static final int ERROR_RECOGNIZER_BUSY = 8;
    public static final int ERROR_SERVER = 4;
    public static final int ERROR_SERVER_DISCONNECTED = 11;
    public static final int ERROR_SPEECH_TIMEOUT = 6;
    public static final int ERROR_TOO_MANY_REQUESTS = 10;
    private static final int MSG_CANCEL = 3;
    private static final int MSG_CHANGE_LISTENER = 4;
    private static final int MSG_SET_TEMPORARY_ON_DEVICE_COMPONENT = 5;
    private static final int MSG_START = 1;
    private static final int MSG_STOP = 2;
    public static final String RESULTS_RECOGNITION = "results_recognition";
    private static final String TAG = "SpeechRecognizer";
    private final IBinder mClientToken;
    private final Context mContext;
    private Handler mHandler;
    private final InternalListener mListener;
    private IRecognitionServiceManager mManagerService;
    private final boolean mOnDevice;
    private final Queue<Message> mPendingTasks;
    private IRecognitionService mService;
    private final ComponentName mServiceComponent;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface RecognitionError {
    }

    private SpeechRecognizer(Context context, ComponentName serviceComponent) {
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: android.speech.SpeechRecognizer.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        SpeechRecognizer.this.handleStartListening((Intent) msg.obj);
                        return;
                    case 2:
                        SpeechRecognizer.this.handleStopMessage();
                        return;
                    case 3:
                        SpeechRecognizer.this.handleCancelMessage();
                        return;
                    case 4:
                        SpeechRecognizer.this.handleChangeListener((RecognitionListener) msg.obj);
                        return;
                    case 5:
                        SpeechRecognizer.this.handleSetTemporaryComponent((ComponentName) msg.obj);
                        return;
                    default:
                        return;
                }
            }
        };
        this.mPendingTasks = new LinkedBlockingQueue();
        this.mListener = new InternalListener();
        this.mClientToken = new Binder();
        this.mContext = context;
        this.mServiceComponent = serviceComponent;
        this.mOnDevice = false;
    }

    private SpeechRecognizer(Context context, boolean onDevice) {
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: android.speech.SpeechRecognizer.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        SpeechRecognizer.this.handleStartListening((Intent) msg.obj);
                        return;
                    case 2:
                        SpeechRecognizer.this.handleStopMessage();
                        return;
                    case 3:
                        SpeechRecognizer.this.handleCancelMessage();
                        return;
                    case 4:
                        SpeechRecognizer.this.handleChangeListener((RecognitionListener) msg.obj);
                        return;
                    case 5:
                        SpeechRecognizer.this.handleSetTemporaryComponent((ComponentName) msg.obj);
                        return;
                    default:
                        return;
                }
            }
        };
        this.mPendingTasks = new LinkedBlockingQueue();
        this.mListener = new InternalListener();
        this.mClientToken = new Binder();
        this.mContext = context;
        this.mServiceComponent = null;
        this.mOnDevice = onDevice;
    }

    public static boolean isRecognitionAvailable(Context context) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentServices(new Intent(RecognitionService.SERVICE_INTERFACE), 0);
        return (list == null || list.size() == 0) ? false : true;
    }

    public static boolean isOnDeviceRecognitionAvailable(Context context) {
        ComponentName componentName = ComponentName.unflattenFromString(context.getString(R.string.config_defaultOnDeviceSpeechRecognitionService));
        return componentName != null;
    }

    public static SpeechRecognizer createSpeechRecognizer(Context context) {
        return createSpeechRecognizer(context, null);
    }

    public static SpeechRecognizer createSpeechRecognizer(Context context, ComponentName serviceComponent) {
        if (context != null) {
            checkIsCalledFromMainThread();
            return new SpeechRecognizer(context, serviceComponent);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static SpeechRecognizer createOnDeviceSpeechRecognizer(Context context) {
        if (isOnDeviceRecognitionAvailable(context)) {
            return lenientlyCreateOnDeviceSpeechRecognizer(context);
        }
        throw new UnsupportedOperationException("On-device recognition is not available");
    }

    public static SpeechRecognizer createOnDeviceTestingSpeechRecognizer(Context context) {
        return lenientlyCreateOnDeviceSpeechRecognizer(context);
    }

    private static SpeechRecognizer lenientlyCreateOnDeviceSpeechRecognizer(Context context) {
        if (context != null) {
            checkIsCalledFromMainThread();
            return new SpeechRecognizer(context, true);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public void setRecognitionListener(RecognitionListener listener) {
        checkIsCalledFromMainThread();
        putMessage(Message.obtain(this.mHandler, 4, listener));
    }

    public void startListening(Intent recognizerIntent) {
        if (recognizerIntent != null) {
            checkIsCalledFromMainThread();
            if (this.mService == null) {
                connectToSystemService();
            }
            putMessage(Message.obtain(this.mHandler, 1, recognizerIntent));
            return;
        }
        throw new IllegalArgumentException("intent must not be null");
    }

    public void stopListening() {
        checkIsCalledFromMainThread();
        putMessage(Message.obtain(this.mHandler, 2));
    }

    public void cancel() {
        checkIsCalledFromMainThread();
        putMessage(Message.obtain(this.mHandler, 3));
    }

    public void setTemporaryOnDeviceRecognizer(ComponentName componentName) {
        Handler handler = this.mHandler;
        handler.sendMessage(Message.obtain(handler, 5, componentName));
    }

    private static void checkIsCalledFromMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("SpeechRecognizer should be used only from the application's main thread");
        }
    }

    private void putMessage(Message msg) {
        if (this.mService == null) {
            this.mPendingTasks.offer(msg);
        } else {
            this.mHandler.sendMessage(msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStartListening(Intent recognizerIntent) {
        if (checkOpenConnection()) {
            try {
                this.mService.startListening(recognizerIntent, this.mListener, this.mContext.getAttributionSource());
            } catch (RemoteException e) {
                Log.e(TAG, "startListening() failed", e);
                this.mListener.onError(5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStopMessage() {
        if (checkOpenConnection()) {
            try {
                this.mService.stopListening(this.mListener);
            } catch (RemoteException e) {
                Log.e(TAG, "stopListening() failed", e);
                this.mListener.onError(5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCancelMessage() {
        if (checkOpenConnection()) {
            try {
                this.mService.cancel(this.mListener, false);
            } catch (RemoteException e) {
                Log.e(TAG, "cancel() failed", e);
                this.mListener.onError(5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetTemporaryComponent(ComponentName componentName) {
        if (maybeInitializeManagerService()) {
            try {
                this.mManagerService.setTemporaryComponent(componentName);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    private boolean checkOpenConnection() {
        if (this.mService != null) {
            return true;
        }
        this.mListener.onError(5);
        Log.e(TAG, "not connected to the recognition service");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleChangeListener(RecognitionListener listener) {
        this.mListener.mInternalListener = listener;
    }

    public void destroy() {
        IRecognitionService iRecognitionService = this.mService;
        if (iRecognitionService != null) {
            try {
                iRecognitionService.cancel(this.mListener, true);
            } catch (RemoteException e) {
            }
        }
        this.mService = null;
        this.mPendingTasks.clear();
        this.mListener.mInternalListener = null;
    }

    private void connectToSystemService() {
        if (maybeInitializeManagerService()) {
            ComponentName componentName = getSpeechRecognizerComponentName();
            boolean z = this.mOnDevice;
            if (z || componentName != null) {
                try {
                    this.mManagerService.createSession(componentName, this.mClientToken, z, new IRecognitionServiceManagerCallback.Stub() { // from class: android.speech.SpeechRecognizer.2
                        @Override // android.speech.IRecognitionServiceManagerCallback
                        public void onSuccess(IRecognitionService service) throws RemoteException {
                            SpeechRecognizer.this.mService = service;
                            while (!SpeechRecognizer.this.mPendingTasks.isEmpty()) {
                                SpeechRecognizer.this.mHandler.sendMessage((Message) SpeechRecognizer.this.mPendingTasks.poll());
                            }
                        }

                        @Override // android.speech.IRecognitionServiceManagerCallback
                        public void onError(int errorCode) throws RemoteException {
                            Log.e(SpeechRecognizer.TAG, "Bind to system recognition service failed with error " + errorCode);
                            SpeechRecognizer.this.mListener.onError(errorCode);
                        }
                    });
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
            } else {
                this.mListener.onError(5);
            }
        }
    }

    private boolean maybeInitializeManagerService() {
        InternalListener internalListener;
        if (this.mManagerService != null) {
            return true;
        }
        IRecognitionServiceManager asInterface = IRecognitionServiceManager.Stub.asInterface(ServiceManager.getService(Context.SPEECH_RECOGNITION_SERVICE));
        this.mManagerService = asInterface;
        if (asInterface != null || (internalListener = this.mListener) == null) {
            return true;
        }
        internalListener.onError(5);
        return false;
    }

    private ComponentName getSpeechRecognizerComponentName() {
        if (this.mOnDevice) {
            return null;
        }
        ComponentName componentName = this.mServiceComponent;
        if (componentName != null) {
            return componentName;
        }
        String serviceComponent = Settings.Secure.getString(this.mContext.getContentResolver(), Settings.Secure.VOICE_RECOGNITION_SERVICE);
        if (!TextUtils.isEmpty(serviceComponent)) {
            return ComponentName.unflattenFromString(serviceComponent);
        }
        Log.e(TAG, "no selected voice recognition service");
        this.mListener.onError(5);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class InternalListener extends IRecognitionListener.Stub {
        private static final int MSG_BEGINNING_OF_SPEECH = 1;
        private static final int MSG_BUFFER_RECEIVED = 2;
        private static final int MSG_END_OF_SPEECH = 3;
        private static final int MSG_ERROR = 4;
        private static final int MSG_ON_EVENT = 9;
        private static final int MSG_PARTIAL_RESULTS = 7;
        private static final int MSG_READY_FOR_SPEECH = 5;
        private static final int MSG_RESULTS = 6;
        private static final int MSG_RMS_CHANGED = 8;
        private final Handler mInternalHandler;
        private RecognitionListener mInternalListener;

        private InternalListener() {
            this.mInternalHandler = new Handler(Looper.getMainLooper()) { // from class: android.speech.SpeechRecognizer.InternalListener.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    if (InternalListener.this.mInternalListener != null) {
                        switch (msg.what) {
                            case 1:
                                InternalListener.this.mInternalListener.onBeginningOfSpeech();
                                return;
                            case 2:
                                InternalListener.this.mInternalListener.onBufferReceived((byte[]) msg.obj);
                                return;
                            case 3:
                                InternalListener.this.mInternalListener.onEndOfSpeech();
                                return;
                            case 4:
                                InternalListener.this.mInternalListener.onError(((Integer) msg.obj).intValue());
                                return;
                            case 5:
                                InternalListener.this.mInternalListener.onReadyForSpeech((Bundle) msg.obj);
                                return;
                            case 6:
                                InternalListener.this.mInternalListener.onResults((Bundle) msg.obj);
                                return;
                            case 7:
                                InternalListener.this.mInternalListener.onPartialResults((Bundle) msg.obj);
                                return;
                            case 8:
                                InternalListener.this.mInternalListener.onRmsChanged(((Float) msg.obj).floatValue());
                                return;
                            case 9:
                                InternalListener.this.mInternalListener.onEvent(msg.arg1, (Bundle) msg.obj);
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }

        @Override // android.speech.IRecognitionListener
        public void onBeginningOfSpeech() {
            Message.obtain(this.mInternalHandler, 1).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onBufferReceived(byte[] buffer) {
            Message.obtain(this.mInternalHandler, 2, buffer).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onEndOfSpeech() {
            Message.obtain(this.mInternalHandler, 3).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onError(int error) {
            Message.obtain(this.mInternalHandler, 4, Integer.valueOf(error)).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onReadyForSpeech(Bundle noiseParams) {
            Message.obtain(this.mInternalHandler, 5, noiseParams).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onResults(Bundle results) {
            Message.obtain(this.mInternalHandler, 6, results).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onPartialResults(Bundle results) {
            Message.obtain(this.mInternalHandler, 7, results).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onRmsChanged(float rmsdB) {
            Message.obtain(this.mInternalHandler, 8, Float.valueOf(rmsdB)).sendToTarget();
        }

        @Override // android.speech.IRecognitionListener
        public void onEvent(int eventType, Bundle params) {
            Message.obtain(this.mInternalHandler, 9, eventType, eventType, params).sendToTarget();
        }
    }
}
