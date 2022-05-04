package android.service.contentsuggestions;

import android.annotation.SystemApi;
import android.app.Service;
import android.app.contentsuggestions.ClassificationsRequest;
import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.IClassificationsCallback;
import android.app.contentsuggestions.ISelectionsCallback;
import android.app.contentsuggestions.SelectionsRequest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.hardware.HardwareBuffer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.contentsuggestions.IContentSuggestionsService;
import android.util.Log;
import android.util.Slog;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.List;
@SystemApi
/* loaded from: classes3.dex */
public abstract class ContentSuggestionsService extends Service {
    public static final String SERVICE_INTERFACE = "android.service.contentsuggestions.ContentSuggestionsService";
    private static final String TAG = ContentSuggestionsService.class.getSimpleName();
    private Handler mHandler;
    private final IContentSuggestionsService mInterface = new IContentSuggestionsService.Stub() { // from class: android.service.contentsuggestions.ContentSuggestionsService.1
        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void provideContextImage(int taskId, HardwareBuffer contextImage, int colorSpaceId, Bundle imageContextRequestExtras) {
            if (!imageContextRequestExtras.containsKey(ContentSuggestionsManager.EXTRA_BITMAP) || contextImage == null) {
                Bitmap wrappedBuffer = null;
                if (imageContextRequestExtras.containsKey(ContentSuggestionsManager.EXTRA_BITMAP)) {
                    wrappedBuffer = (Bitmap) imageContextRequestExtras.getParcelable(ContentSuggestionsManager.EXTRA_BITMAP);
                } else if (contextImage != null) {
                    ColorSpace colorSpace = null;
                    if (colorSpaceId >= 0 && colorSpaceId < ColorSpace.Named.values().length) {
                        colorSpace = ColorSpace.get(ColorSpace.Named.values()[colorSpaceId]);
                    }
                    wrappedBuffer = Bitmap.wrapHardwareBuffer(contextImage, colorSpace);
                }
                ContentSuggestionsService.this.mHandler.sendMessage(PooledLambda.obtainMessage(ContentSuggestionsService$1$$ExternalSyntheticLambda0.INSTANCE, ContentSuggestionsService.this, Integer.valueOf(taskId), wrappedBuffer, imageContextRequestExtras));
                return;
            }
            throw new IllegalArgumentException("Two bitmaps provided; expected one.");
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void suggestContentSelections(SelectionsRequest request, ISelectionsCallback callback) {
            Handler handler = ContentSuggestionsService.this.mHandler;
            ContentSuggestionsService$1$$ExternalSyntheticLambda2 contentSuggestionsService$1$$ExternalSyntheticLambda2 = ContentSuggestionsService$1$$ExternalSyntheticLambda2.INSTANCE;
            ContentSuggestionsService contentSuggestionsService = ContentSuggestionsService.this;
            handler.sendMessage(PooledLambda.obtainMessage(contentSuggestionsService$1$$ExternalSyntheticLambda2, contentSuggestionsService, request, contentSuggestionsService.wrapSelectionsCallback(callback)));
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void classifyContentSelections(ClassificationsRequest request, IClassificationsCallback callback) {
            Handler handler = ContentSuggestionsService.this.mHandler;
            ContentSuggestionsService$1$$ExternalSyntheticLambda1 contentSuggestionsService$1$$ExternalSyntheticLambda1 = ContentSuggestionsService$1$$ExternalSyntheticLambda1.INSTANCE;
            ContentSuggestionsService contentSuggestionsService = ContentSuggestionsService.this;
            handler.sendMessage(PooledLambda.obtainMessage(contentSuggestionsService$1$$ExternalSyntheticLambda1, contentSuggestionsService, request, contentSuggestionsService.wrapClassificationCallback(callback)));
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void notifyInteraction(String requestId, Bundle interaction) {
            ContentSuggestionsService.this.mHandler.sendMessage(PooledLambda.obtainMessage(ContentSuggestionsService$1$$ExternalSyntheticLambda3.INSTANCE, ContentSuggestionsService.this, requestId, interaction));
        }
    };

    public abstract void onClassifyContentSelections(ClassificationsRequest classificationsRequest, ContentSuggestionsManager.ClassificationsCallback classificationsCallback);

    public abstract void onNotifyInteraction(String str, Bundle bundle);

    public abstract void onProcessContextImage(int i, Bitmap bitmap, Bundle bundle);

    public abstract void onSuggestContentSelections(SelectionsRequest selectionsRequest, ContentSuggestionsManager.SelectionsCallback selectionsCallback);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mHandler = new Handler(Looper.getMainLooper(), null, true);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return this.mInterface.asBinder();
        }
        String str = TAG;
        Log.w(str, "Tried to bind to wrong intent (should be android.service.contentsuggestions.ContentSuggestionsService: " + intent);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentSuggestionsManager.SelectionsCallback wrapSelectionsCallback(final ISelectionsCallback callback) {
        return new ContentSuggestionsManager.SelectionsCallback() { // from class: android.service.contentsuggestions.ContentSuggestionsService$$ExternalSyntheticLambda1
            @Override // android.app.contentsuggestions.ContentSuggestionsManager.SelectionsCallback
            public final void onContentSelectionsAvailable(int i, List list) {
                ContentSuggestionsService.lambda$wrapSelectionsCallback$0(ISelectionsCallback.this, i, list);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$wrapSelectionsCallback$0(ISelectionsCallback callback, int statusCode, List selections) {
        try {
            callback.onContentSelectionsAvailable(statusCode, selections);
        } catch (RemoteException e) {
            String str = TAG;
            Slog.e(str, "Error sending result: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentSuggestionsManager.ClassificationsCallback wrapClassificationCallback(final IClassificationsCallback callback) {
        return new ContentSuggestionsManager.ClassificationsCallback() { // from class: android.service.contentsuggestions.ContentSuggestionsService$$ExternalSyntheticLambda0
            @Override // android.app.contentsuggestions.ContentSuggestionsManager.ClassificationsCallback
            public final void onContentClassificationsAvailable(int i, List list) {
                ContentSuggestionsService.lambda$wrapClassificationCallback$1(IClassificationsCallback.this, i, list);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$wrapClassificationCallback$1(IClassificationsCallback callback, int statusCode, List classifications) {
        try {
            callback.onContentClassificationsAvailable(statusCode, classifications);
        } catch (RemoteException e) {
            String str = TAG;
            Slog.e(str, "Error sending result: " + e);
        }
    }
}
