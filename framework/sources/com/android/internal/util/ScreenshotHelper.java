package com.android.internal.util;

import android.app.job.JobInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Insets;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.R;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public class ScreenshotHelper {
    public static final int SCREENSHOT_MSG_PROCESS_COMPLETE = 2;
    public static final int SCREENSHOT_MSG_URI = 1;
    private static final String TAG = "ScreenshotHelper";
    private final BroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private final int SCREENSHOT_TIMEOUT_MS = 10000;
    private final Object mScreenshotLock = new Object();
    private IBinder mScreenshotService = null;
    private ServiceConnection mScreenshotConnection = null;
    private IScreenshotHelperExt mSHExt = ScreenshotHelperExtPlugin.constructor.newInstance(new Object[0]);

    /* loaded from: classes4.dex */
    public static class ScreenshotRequest implements Parcelable {
        public static final Parcelable.Creator<ScreenshotRequest> CREATOR = new Parcelable.Creator<ScreenshotRequest>() { // from class: com.android.internal.util.ScreenshotHelper.ScreenshotRequest.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ScreenshotRequest createFromParcel(Parcel source) {
                return new ScreenshotRequest(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ScreenshotRequest[] newArray(int size) {
                return new ScreenshotRequest[size];
            }
        };
        private Bundle mBitmapBundle;
        private Rect mBoundsInScreen;
        private boolean mHasNavBar;
        private boolean mHasStatusBar;
        private Insets mInsets;
        private int mSource;
        private int mTaskId;
        private ComponentName mTopComponent;
        private int mUserId;

        ScreenshotRequest(int source, boolean hasStatus, boolean hasNav) {
            this.mSource = source;
            this.mHasStatusBar = hasStatus;
            this.mHasNavBar = hasNav;
        }

        ScreenshotRequest(int source, Bundle bitmapBundle, Rect boundsInScreen, Insets insets, int taskId, int userId, ComponentName topComponent) {
            this.mSource = source;
            this.mBitmapBundle = bitmapBundle;
            this.mBoundsInScreen = boundsInScreen;
            this.mInsets = insets;
            this.mTaskId = taskId;
            this.mUserId = userId;
            this.mTopComponent = topComponent;
        }

        ScreenshotRequest(Parcel in) {
            this.mSource = in.readInt();
            this.mHasStatusBar = in.readBoolean();
            this.mHasNavBar = in.readBoolean();
            if (in.readInt() == 1) {
                this.mBitmapBundle = in.readBundle(getClass().getClassLoader());
                this.mBoundsInScreen = (Rect) in.readParcelable(Rect.class.getClassLoader());
                this.mInsets = (Insets) in.readParcelable(Insets.class.getClassLoader());
                this.mTaskId = in.readInt();
                this.mUserId = in.readInt();
                this.mTopComponent = (ComponentName) in.readParcelable(ComponentName.class.getClassLoader());
            }
        }

        public int getSource() {
            return this.mSource;
        }

        public boolean getHasStatusBar() {
            return this.mHasStatusBar;
        }

        public boolean getHasNavBar() {
            return this.mHasNavBar;
        }

        public Bundle getBitmapBundle() {
            return this.mBitmapBundle;
        }

        public Rect getBoundsInScreen() {
            return this.mBoundsInScreen;
        }

        public Insets getInsets() {
            return this.mInsets;
        }

        public int getTaskId() {
            return this.mTaskId;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public ComponentName getTopComponent() {
            return this.mTopComponent;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mSource);
            dest.writeBoolean(this.mHasStatusBar);
            dest.writeBoolean(this.mHasNavBar);
            if (this.mBitmapBundle == null) {
                dest.writeInt(0);
                return;
            }
            dest.writeInt(1);
            dest.writeBundle(this.mBitmapBundle);
            dest.writeParcelable(this.mBoundsInScreen, 0);
            dest.writeParcelable(this.mInsets, 0);
            dest.writeInt(this.mTaskId);
            dest.writeInt(this.mUserId);
            dest.writeParcelable(this.mTopComponent, 0);
        }
    }

    public ScreenshotHelper(Context context) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.internal.util.ScreenshotHelper.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                synchronized (ScreenshotHelper.this.mScreenshotLock) {
                    if (Intent.ACTION_USER_SWITCHED.equals(intent.getAction())) {
                        ScreenshotHelper.this.resetConnection();
                    }
                }
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        this.mContext = context;
        IntentFilter filter = new IntentFilter(Intent.ACTION_USER_SWITCHED);
        context.registerReceiver(broadcastReceiver, filter);
    }

    public void takeScreenshot(int screenshotType, boolean hasStatus, boolean hasNav, int source, Handler handler, Consumer<Uri> completionConsumer) {
        ScreenshotRequest screenshotRequest = new ScreenshotRequest(source, hasStatus, hasNav);
        takeScreenshot(screenshotType, JobInfo.MIN_BACKOFF_MILLIS, handler, screenshotRequest, completionConsumer);
    }

    public void takeScreenshot(int screenshotType, boolean hasStatus, boolean hasNav, Handler handler, Consumer<Uri> completionConsumer) {
        takeScreenshot(screenshotType, hasStatus, hasNav, 10000, handler, completionConsumer);
    }

    public void takeScreenshot(int screenshotType, boolean hasStatus, boolean hasNav, long timeoutMs, Handler handler, Consumer<Uri> completionConsumer) {
        ScreenshotRequest screenshotRequest = new ScreenshotRequest(5, hasStatus, hasNav);
        takeScreenshot(screenshotType, timeoutMs, handler, screenshotRequest, completionConsumer);
    }

    public void provideScreenshot(Bundle screenshotBundle, Rect boundsInScreen, Insets insets, int taskId, int userId, ComponentName topComponent, int source, Handler handler, Consumer<Uri> completionConsumer) {
        ScreenshotRequest screenshotRequest = new ScreenshotRequest(source, screenshotBundle, boundsInScreen, insets, taskId, userId, topComponent);
        takeScreenshot(3, JobInfo.MIN_BACKOFF_MILLIS, handler, screenshotRequest, completionConsumer);
    }

    private void takeScreenshot(int screenshotType, long timeoutMs, final Handler handler, ScreenshotRequest screenshotRequest, final Consumer<Uri> completionConsumer) {
        synchronized (this.mScreenshotLock) {
            if (!this.mSHExt.takeScreenshotNeedReturn(this.mContext, screenshotType, screenshotRequest, handler)) {
                final Runnable mScreenshotTimeout = new Runnable() { // from class: com.android.internal.util.ScreenshotHelper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ScreenshotHelper.this.lambda$takeScreenshot$0$ScreenshotHelper(completionConsumer);
                    }
                };
                final Message msg = Message.obtain(null, screenshotType, screenshotRequest);
                Handler h = new Handler(handler.getLooper()) { // from class: com.android.internal.util.ScreenshotHelper.2
                    @Override // android.os.Handler
                    public void handleMessage(Message msg2) {
                        switch (msg2.what) {
                            case 1:
                                Consumer consumer = completionConsumer;
                                if (consumer != null) {
                                    consumer.accept((Uri) msg2.obj);
                                }
                                handler.removeCallbacks(mScreenshotTimeout);
                                return;
                            case 2:
                                synchronized (ScreenshotHelper.this.mScreenshotLock) {
                                    ScreenshotHelper.this.resetConnection();
                                }
                                return;
                            default:
                                return;
                        }
                    }
                };
                msg.replyTo = new Messenger(h);
                if (!(this.mScreenshotConnection == null || this.mScreenshotService == null)) {
                    Messenger messenger = new Messenger(this.mScreenshotService);
                    try {
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        Log.e(TAG, "Couldn't take screenshot: " + e);
                        if (completionConsumer != null) {
                            completionConsumer.accept(null);
                        }
                    }
                    handler.postDelayed(mScreenshotTimeout, timeoutMs);
                }
                ComponentName serviceComponent = ComponentName.unflattenFromString(this.mContext.getResources().getString(R.string.config_screenshotServiceComponent));
                Intent serviceIntent = new Intent();
                serviceIntent.setComponent(serviceComponent);
                ServiceConnection conn = new ServiceConnection() { // from class: com.android.internal.util.ScreenshotHelper.3
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        synchronized (ScreenshotHelper.this.mScreenshotLock) {
                            if (ScreenshotHelper.this.mScreenshotConnection == this) {
                                ScreenshotHelper.this.mScreenshotService = service;
                                Messenger messenger2 = new Messenger(ScreenshotHelper.this.mScreenshotService);
                                try {
                                    messenger2.send(msg);
                                } catch (RemoteException e2) {
                                    Log.e(ScreenshotHelper.TAG, "Couldn't take screenshot: " + e2);
                                    Consumer consumer = completionConsumer;
                                    if (consumer != null) {
                                        consumer.accept(null);
                                    }
                                }
                            }
                        }
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName name) {
                        synchronized (ScreenshotHelper.this.mScreenshotLock) {
                            if (ScreenshotHelper.this.mScreenshotConnection != null) {
                                ScreenshotHelper.this.resetConnection();
                                if (handler.hasCallbacks(mScreenshotTimeout)) {
                                    Log.e(ScreenshotHelper.TAG, "Screenshot service disconnected");
                                    handler.removeCallbacks(mScreenshotTimeout);
                                    ScreenshotHelper.this.notifyScreenshotError();
                                }
                            }
                        }
                    }
                };
                if (this.mContext.bindServiceAsUser(serviceIntent, conn, 67108865, UserHandle.CURRENT)) {
                    this.mScreenshotConnection = conn;
                    handler.postDelayed(mScreenshotTimeout, timeoutMs);
                }
            }
        }
    }

    public /* synthetic */ void lambda$takeScreenshot$0$ScreenshotHelper(Consumer completionConsumer) {
        synchronized (this.mScreenshotLock) {
            if (this.mScreenshotConnection != null) {
                Log.e(TAG, "Timed out before getting screenshot capture response");
                resetConnection();
                notifyScreenshotError();
            }
        }
        if (completionConsumer != null) {
            completionConsumer.accept(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetConnection() {
        ServiceConnection serviceConnection = this.mScreenshotConnection;
        if (serviceConnection != null) {
            this.mContext.unbindService(serviceConnection);
            this.mScreenshotConnection = null;
            this.mScreenshotService = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyScreenshotError() {
        ComponentName errorComponent = ComponentName.unflattenFromString(this.mContext.getResources().getString(R.string.config_screenshotErrorReceiverComponent));
        Intent errorIntent = new Intent(Intent.ACTION_USER_PRESENT);
        errorIntent.setComponent(errorComponent);
        errorIntent.addFlags(335544320);
        this.mContext.sendBroadcastAsUser(errorIntent, UserHandle.CURRENT);
    }
}
