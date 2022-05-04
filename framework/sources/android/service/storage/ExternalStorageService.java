package android.service.storage;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.service.storage.ExternalStorageService;
import android.service.storage.IExternalStorageService;
import android.util.Slog;
import com.android.internal.os.BackgroundThread;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;
@SystemApi
/* loaded from: classes3.dex */
public abstract class ExternalStorageService extends Service {
    public static final String EXTRA_ERROR = "android.service.storage.extra.error";
    public static final String EXTRA_PACKAGE_NAME = "android.service.storage.extra.package_name";
    public static final String EXTRA_SESSION_ID = "android.service.storage.extra.session_id";
    public static final int FLAG_SESSION_ATTRIBUTE_INDEXABLE = 2;
    public static final int FLAG_SESSION_TYPE_FUSE = 1;
    public static final String SERVICE_INTERFACE = "android.service.storage.ExternalStorageService";
    public static final String TAG = "ExternalStorageService";
    private final ExternalStorageServiceWrapper mWrapper = new ExternalStorageServiceWrapper();
    private final Handler mHandler = BackgroundThread.getHandler();
    private final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SessionFlag {
    }

    public abstract void onEndSession(String str) throws IOException;

    public abstract void onStartSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, File file, File file2) throws IOException;

    public abstract void onVolumeStateChanged(StorageVolume storageVolume) throws IOException;

    public void onFreeCache(UUID volumeUuid, long bytes) throws IOException {
        throw new UnsupportedOperationException("onFreeCacheRequested not implemented");
    }

    public void onAnrDelayStarted(String packageName, int uid, int tid, int reason) {
        throw new UnsupportedOperationException("onAnrDelayStarted not implemented");
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.mWrapper;
    }

    /* loaded from: classes3.dex */
    private class ExternalStorageServiceWrapper extends IExternalStorageService.Stub {
        private ExternalStorageServiceWrapper() {
        }

        @Override // android.service.storage.IExternalStorageService
        public void startSession(final String sessionId, final int flag, final ParcelFileDescriptor deviceFd, final String upperPath, final String lowerPath, final RemoteCallback callback) throws RemoteException {
            if (ExternalStorageService.this.DEBUG) {
                Slog.i(ExternalStorageService.TAG, "startSession:" + sessionId + ",upperPath:" + upperPath + ", lowerPath:" + upperPath);
            }
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.ExternalStorageService$ExternalStorageServiceWrapper$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    ExternalStorageService.ExternalStorageServiceWrapper.this.lambda$startSession$0$ExternalStorageService$ExternalStorageServiceWrapper(sessionId, upperPath, flag, deviceFd, lowerPath, callback);
                }
            });
        }

        public /* synthetic */ void lambda$startSession$0$ExternalStorageService$ExternalStorageServiceWrapper(String sessionId, String upperPath, int flag, ParcelFileDescriptor deviceFd, String lowerPath, RemoteCallback callback) {
            Throwable t;
            try {
                if (ExternalStorageService.this.DEBUG) {
                    Slog.i(ExternalStorageService.TAG, "onStartSession before:" + sessionId + ",upperPath:" + upperPath + ", lowerPath:" + upperPath);
                }
                try {
                    ExternalStorageService.this.onStartSession(sessionId, flag, deviceFd, new File(upperPath), new File(lowerPath));
                    if (ExternalStorageService.this.DEBUG) {
                        Slog.i(ExternalStorageService.TAG, "onStartSession end:" + sessionId + ",upperPath:" + upperPath + ", lowerPath:" + upperPath);
                    }
                    sendResult(sessionId, null, callback);
                } catch (Throwable th) {
                    t = th;
                    sendResult(sessionId, t, callback);
                }
            } catch (Throwable th2) {
                t = th2;
            }
        }

        @Override // android.service.storage.IExternalStorageService
        public void notifyVolumeStateChanged(final String sessionId, final StorageVolume vol, final RemoteCallback callback) {
            if (ExternalStorageService.this.DEBUG) {
                Slog.i(ExternalStorageService.TAG, "notifyVolumeStateChanged:" + sessionId + ",vol:" + vol);
            }
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.ExternalStorageService$ExternalStorageServiceWrapper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    ExternalStorageService.ExternalStorageServiceWrapper.this.lambda$notifyVolumeStateChanged$1$ExternalStorageService$ExternalStorageServiceWrapper(sessionId, vol, callback);
                }
            });
        }

        public /* synthetic */ void lambda$notifyVolumeStateChanged$1$ExternalStorageService$ExternalStorageServiceWrapper(String sessionId, StorageVolume vol, RemoteCallback callback) {
            try {
                if (ExternalStorageService.this.DEBUG) {
                    Slog.i(ExternalStorageService.TAG, "onVolumeStateChanged before:" + sessionId + ",vol:" + vol);
                }
                ExternalStorageService.this.onVolumeStateChanged(vol);
                if (ExternalStorageService.this.DEBUG) {
                    Slog.i(ExternalStorageService.TAG, "onVolumeStateChanged end:" + sessionId + ",vol:" + vol);
                }
                sendResult(sessionId, null, callback);
            } catch (Throwable t) {
                sendResult(sessionId, t, callback);
            }
        }

        @Override // android.service.storage.IExternalStorageService
        public void freeCache(final String sessionId, final String volumeUuid, final long bytes, final RemoteCallback callback) {
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.ExternalStorageService$ExternalStorageServiceWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ExternalStorageService.ExternalStorageServiceWrapper.this.lambda$freeCache$2$ExternalStorageService$ExternalStorageServiceWrapper(volumeUuid, bytes, sessionId, callback);
                }
            });
        }

        public /* synthetic */ void lambda$freeCache$2$ExternalStorageService$ExternalStorageServiceWrapper(String volumeUuid, long bytes, String sessionId, RemoteCallback callback) {
            try {
                ExternalStorageService.this.onFreeCache(StorageManager.convert(volumeUuid), bytes);
                sendResult(sessionId, null, callback);
            } catch (Throwable t) {
                sendResult(sessionId, t, callback);
            }
        }

        @Override // android.service.storage.IExternalStorageService
        public void endSession(final String sessionId, final RemoteCallback callback) throws RemoteException {
            if (ExternalStorageService.this.DEBUG) {
                Slog.i(ExternalStorageService.TAG, "endSession:" + sessionId);
            }
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.ExternalStorageService$ExternalStorageServiceWrapper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ExternalStorageService.ExternalStorageServiceWrapper.this.lambda$endSession$3$ExternalStorageService$ExternalStorageServiceWrapper(sessionId, callback);
                }
            });
        }

        public /* synthetic */ void lambda$endSession$3$ExternalStorageService$ExternalStorageServiceWrapper(String sessionId, RemoteCallback callback) {
            try {
                if (ExternalStorageService.this.DEBUG) {
                    Slog.i(ExternalStorageService.TAG, "onEndSession before:" + sessionId);
                }
                ExternalStorageService.this.onEndSession(sessionId);
                if (ExternalStorageService.this.DEBUG) {
                    Slog.i(ExternalStorageService.TAG, "onEndSession end:" + sessionId);
                }
                sendResult(sessionId, null, callback);
            } catch (Throwable t) {
                sendResult(sessionId, t, callback);
            }
        }

        @Override // android.service.storage.IExternalStorageService
        public void notifyAnrDelayStarted(final String packageName, final int uid, final int tid, final int reason) throws RemoteException {
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.ExternalStorageService$ExternalStorageServiceWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ExternalStorageService.ExternalStorageServiceWrapper.this.lambda$notifyAnrDelayStarted$4$ExternalStorageService$ExternalStorageServiceWrapper(packageName, uid, tid, reason);
                }
            });
        }

        public /* synthetic */ void lambda$notifyAnrDelayStarted$4$ExternalStorageService$ExternalStorageServiceWrapper(String packageName, int uid, int tid, int reason) {
            try {
                ExternalStorageService.this.onAnrDelayStarted(packageName, uid, tid, reason);
            } catch (Throwable th) {
            }
        }

        private void sendResult(String sessionId, Throwable throwable, RemoteCallback callback) {
            Bundle bundle = new Bundle();
            bundle.putString(ExternalStorageService.EXTRA_SESSION_ID, sessionId);
            if (throwable != null) {
                bundle.putParcelable(ExternalStorageService.EXTRA_ERROR, new ParcelableException(throwable));
            }
            callback.sendResult(bundle);
            if (ExternalStorageService.this.DEBUG) {
                Slog.i(ExternalStorageService.TAG, "sendResult:" + sessionId + ",end");
            }
        }
    }
}
