package android.app;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.android.internal.util.ExponentiallyBucketedHistogram;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class QueuedWork {
    private static final boolean DEBUG = false;
    private static final long DELAY = 100;
    private static final long MAX_WAIT_TIME_MILLIS = 512;
    private static final String LOG_TAG = QueuedWork.class.getSimpleName();
    private static final Object sLock = new Object();
    private static Object sProcessingWork = new Object();
    private static final LinkedList<Runnable> sFinishers = new LinkedList<>();
    private static Handler sHandler = null;
    private static LinkedList<Runnable> sWork = new LinkedList<>();
    private static boolean sCanDelay = true;
    private static final ExponentiallyBucketedHistogram mWaitTimes = new ExponentiallyBucketedHistogram(16);
    private static int mNumWaits = 0;

    private static Handler getHandler() {
        Handler handler;
        synchronized (sLock) {
            if (sHandler == null) {
                HandlerThread handlerThread = new HandlerThread("queued-work-looper", -2);
                handlerThread.start();
                sHandler = new QueuedWorkHandler(handlerThread.getLooper());
            }
            handler = sHandler;
        }
        return handler;
    }

    public static void addFinisher(Runnable finisher) {
        synchronized (sLock) {
            sFinishers.add(finisher);
        }
    }

    public static void removeFinisher(Runnable finisher) {
        synchronized (sLock) {
            sFinishers.remove(finisher);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [boolean, int] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void waitToFinish() {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            android.os.Handler r3 = getHandler()
            java.lang.Object r4 = android.app.QueuedWork.sLock
            monitor-enter(r4)
            r5 = 1
            boolean r6 = r3.hasMessages(r5)     // Catch: all -> 0x007e
            if (r6 == 0) goto L_0x0016
            r3.removeMessages(r5)     // Catch: all -> 0x007e
        L_0x0016:
            r6 = 0
            android.app.QueuedWork.sCanDelay = r6     // Catch: all -> 0x007e
            monitor-exit(r4)     // Catch: all -> 0x007e
            android.os.StrictMode$ThreadPolicy r4 = android.os.StrictMode.allowThreadDiskWrites()
            processPendingWork()     // Catch: all -> 0x0079
            android.os.StrictMode.setThreadPolicy(r4)
        L_0x0025:
            java.lang.Object r6 = android.app.QueuedWork.sLock     // Catch: all -> 0x0075
            monitor-enter(r6)     // Catch: all -> 0x0075
            java.util.LinkedList<java.lang.Runnable> r7 = android.app.QueuedWork.sFinishers     // Catch: all -> 0x0072
            java.lang.Object r7 = r7.poll()     // Catch: all -> 0x0072
            java.lang.Runnable r7 = (java.lang.Runnable) r7     // Catch: all -> 0x0072
            monitor-exit(r6)     // Catch: all -> 0x0072
            if (r7 != 0) goto L_0x006e
            android.app.QueuedWork.sCanDelay = r5
            monitor-enter(r6)
            long r7 = java.lang.System.currentTimeMillis()     // Catch: all -> 0x006b
            long r7 = r7 - r0
            r9 = 0
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 > 0) goto L_0x0045
            if (r2 == 0) goto L_0x0069
        L_0x0045:
            com.android.internal.util.ExponentiallyBucketedHistogram r9 = android.app.QueuedWork.mWaitTimes     // Catch: all -> 0x006b
            java.lang.Long r10 = java.lang.Long.valueOf(r7)     // Catch: all -> 0x006b
            int r10 = r10.intValue()     // Catch: all -> 0x006b
            r9.add(r10)     // Catch: all -> 0x006b
            int r10 = android.app.QueuedWork.mNumWaits     // Catch: all -> 0x006b
            int r10 = r10 + r5
            android.app.QueuedWork.mNumWaits = r10     // Catch: all -> 0x006b
            int r10 = r10 % 1024
            if (r10 == 0) goto L_0x0061
            r10 = 512(0x200, double:2.53E-321)
            int r5 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x0069
        L_0x0061:
            java.lang.String r5 = android.app.QueuedWork.LOG_TAG     // Catch: all -> 0x006b
            java.lang.String r10 = "waited: "
            r9.log(r5, r10)     // Catch: all -> 0x006b
        L_0x0069:
            monitor-exit(r6)     // Catch: all -> 0x006b
            return
        L_0x006b:
            r5 = move-exception
            monitor-exit(r6)     // Catch: all -> 0x006b
            throw r5
        L_0x006e:
            r7.run()     // Catch: all -> 0x0075
            goto L_0x0025
        L_0x0072:
            r7 = move-exception
            monitor-exit(r6)     // Catch: all -> 0x0072
            throw r7     // Catch: all -> 0x0075
        L_0x0075:
            r6 = move-exception
            android.app.QueuedWork.sCanDelay = r5
            throw r6
        L_0x0079:
            r5 = move-exception
            android.os.StrictMode.setThreadPolicy(r4)
            throw r5
        L_0x007e:
            r5 = move-exception
            monitor-exit(r4)     // Catch: all -> 0x007e
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.QueuedWork.waitToFinish():void");
    }

    public static void queue(Runnable work, boolean shouldDelay) {
        Handler handler = getHandler();
        synchronized (sLock) {
            sWork.add(work);
            if (!shouldDelay || !sCanDelay) {
                handler.sendEmptyMessage(1);
            } else {
                handler.sendEmptyMessageDelayed(1, DELAY);
            }
        }
    }

    public static boolean hasPendingWork() {
        boolean z;
        synchronized (sLock) {
            z = !sWork.isEmpty();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processPendingWork() {
        LinkedList<Runnable> work;
        synchronized (sProcessingWork) {
            synchronized (sLock) {
                work = sWork;
                sWork = new LinkedList<>();
                getHandler().removeMessages(1);
            }
            if (work.size() > 0) {
                Iterator<Runnable> it = work.iterator();
                while (it.hasNext()) {
                    Runnable w = it.next();
                    w.run();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class QueuedWorkHandler extends Handler {
        static final int MSG_RUN = 1;

        QueuedWorkHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                QueuedWork.processPendingWork();
            }
        }
    }
}
