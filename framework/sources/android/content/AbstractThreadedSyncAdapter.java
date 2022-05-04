package android.content;

import android.accounts.Account;
import android.content.ISyncAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class AbstractThreadedSyncAdapter {
    private static final boolean ENABLE_LOG;
    @Deprecated
    public static final int LOG_SYNC_DETAILS = 2743;
    private static final String TAG = "SyncAdapter";
    private boolean mAllowParallelSyncs;
    private final boolean mAutoInitialize;
    private final Context mContext;
    private final ISyncAdapterImpl mISyncAdapterImpl;
    private final AtomicInteger mNumSyncStarts;
    private final Object mSyncThreadLock;
    private final HashMap<Account, SyncThread> mSyncThreads;

    public abstract void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult);

    static {
        ENABLE_LOG = Build.IS_DEBUGGABLE && Log.isLoggable(TAG, 3);
    }

    public AbstractThreadedSyncAdapter(Context context, boolean autoInitialize) {
        this(context, autoInitialize, false);
    }

    public AbstractThreadedSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        this.mSyncThreads = new HashMap<>();
        this.mSyncThreadLock = new Object();
        this.mContext = context;
        this.mISyncAdapterImpl = new ISyncAdapterImpl();
        this.mNumSyncStarts = new AtomicInteger(0);
        this.mAutoInitialize = autoInitialize;
        this.mAllowParallelSyncs = allowParallelSyncs;
    }

    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Account toSyncKey(Account account) {
        if (this.mAllowParallelSyncs) {
            return account;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ISyncAdapterImpl extends ISyncAdapter.Stub {
        private ISyncAdapterImpl() {
        }

        @Override // android.content.ISyncAdapter
        public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback cb) {
            Handler.getMain().sendMessage(PooledLambda.obtainMessage(AbstractThreadedSyncAdapter$ISyncAdapterImpl$$ExternalSyntheticLambda0.INSTANCE, AbstractThreadedSyncAdapter.this, cb));
        }

        /* JADX WARN: Removed duplicated region for block: B:54:0x011f  */
        @Override // android.content.ISyncAdapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void startSync(android.content.ISyncContext r18, java.lang.String r19, android.accounts.Account r20, android.os.Bundle r21) {
            /*
                Method dump skipped, instructions count: 296
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.content.AbstractThreadedSyncAdapter.ISyncAdapterImpl.startSync(android.content.ISyncContext, java.lang.String, android.accounts.Account, android.os.Bundle):void");
        }

        @Override // android.content.ISyncAdapter
        public void cancelSync(ISyncContext syncContext) {
            SyncThread info = null;
            try {
                try {
                    synchronized (AbstractThreadedSyncAdapter.this.mSyncThreadLock) {
                        Iterator it = AbstractThreadedSyncAdapter.this.mSyncThreads.values().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            SyncThread current = (SyncThread) it.next();
                            if (current.mSyncContext.getSyncContextBinder() == syncContext.asBinder()) {
                                info = current;
                                break;
                            }
                        }
                    }
                    if (info != null) {
                        if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                            Log.d(AbstractThreadedSyncAdapter.TAG, "cancelSync() " + info.mAuthority + " " + info.mAccount);
                        }
                        if (AbstractThreadedSyncAdapter.this.mAllowParallelSyncs) {
                            AbstractThreadedSyncAdapter.this.onSyncCanceled(info);
                        } else {
                            AbstractThreadedSyncAdapter.this.onSyncCanceled();
                        }
                    } else if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                        Log.w(AbstractThreadedSyncAdapter.TAG, "cancelSync() unknown context");
                    }
                } catch (Error | RuntimeException th) {
                    if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                        Log.d(AbstractThreadedSyncAdapter.TAG, "cancelSync() caught exception", th);
                    }
                    throw th;
                }
            } finally {
                if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                    Log.d(AbstractThreadedSyncAdapter.TAG, "cancelSync() finishing");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SyncThread extends Thread {
        private final Account mAccount;
        private final String mAuthority;
        private final Bundle mExtras;
        private final SyncContext mSyncContext;
        private final Account mThreadsKey;

        private SyncThread(String name, SyncContext syncContext, String authority, Account account, Bundle extras) {
            super(name);
            this.mSyncContext = syncContext;
            this.mAuthority = authority;
            this.mAccount = account;
            this.mExtras = extras;
            this.mThreadsKey = AbstractThreadedSyncAdapter.this.toSyncKey(account);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            SecurityException e;
            Throwable th;
            Process.setThreadPriority(10);
            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                Log.d(AbstractThreadedSyncAdapter.TAG, "Thread started");
            }
            Trace.traceBegin(128L, this.mAuthority);
            SyncResult syncResult = new SyncResult();
            ContentProviderClient provider = null;
            try {
                try {
                    try {
                        if (isCanceled()) {
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "Already canceled");
                            }
                            Trace.traceEnd(128L);
                            if (0 != 0) {
                                provider.release();
                            }
                            if (!isCanceled()) {
                                this.mSyncContext.onFinished(syncResult);
                            }
                            synchronized (AbstractThreadedSyncAdapter.this.mSyncThreadLock) {
                                AbstractThreadedSyncAdapter.this.mSyncThreads.remove(this.mThreadsKey);
                            }
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "Thread finished");
                                return;
                            }
                            return;
                        }
                        if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                            Log.d(AbstractThreadedSyncAdapter.TAG, "Calling onPerformSync...");
                        }
                        ContentProviderClient provider2 = AbstractThreadedSyncAdapter.this.mContext.getContentResolver().acquireContentProviderClient(this.mAuthority);
                        try {
                            if (provider2 != null) {
                                AbstractThreadedSyncAdapter.this.onPerformSync(this.mAccount, this.mExtras, this.mAuthority, provider2, syncResult);
                            } else {
                                syncResult.databaseError = true;
                            }
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "onPerformSync done");
                            }
                            Trace.traceEnd(128L);
                            if (provider2 != null) {
                                provider2.release();
                            }
                            if (!isCanceled()) {
                                this.mSyncContext.onFinished(syncResult);
                            }
                            synchronized (AbstractThreadedSyncAdapter.this.mSyncThreadLock) {
                                AbstractThreadedSyncAdapter.this.mSyncThreads.remove(this.mThreadsKey);
                            }
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "Thread finished");
                            }
                        } catch (Error | RuntimeException e2) {
                            th = e2;
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "caught exception", th);
                            }
                            throw th;
                        } catch (SecurityException e3) {
                            e = e3;
                            provider = provider2;
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "SecurityException", e);
                            }
                            AbstractThreadedSyncAdapter.this.onSecurityException(this.mAccount, this.mExtras, this.mAuthority, syncResult);
                            syncResult.databaseError = true;
                            Trace.traceEnd(128L);
                            if (provider != null) {
                                provider.release();
                            }
                            if (!isCanceled()) {
                                this.mSyncContext.onFinished(syncResult);
                            }
                            synchronized (AbstractThreadedSyncAdapter.this.mSyncThreadLock) {
                                AbstractThreadedSyncAdapter.this.mSyncThreads.remove(this.mThreadsKey);
                            }
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "Thread finished");
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            provider = provider2;
                            Trace.traceEnd(128L);
                            if (provider != null) {
                                provider.release();
                            }
                            if (!isCanceled()) {
                                this.mSyncContext.onFinished(syncResult);
                            }
                            synchronized (AbstractThreadedSyncAdapter.this.mSyncThreadLock) {
                                AbstractThreadedSyncAdapter.this.mSyncThreads.remove(this.mThreadsKey);
                            }
                            if (AbstractThreadedSyncAdapter.ENABLE_LOG) {
                                Log.d(AbstractThreadedSyncAdapter.TAG, "Thread finished");
                            }
                            throw th;
                        }
                    } catch (Error | RuntimeException e4) {
                        th = e4;
                    }
                } catch (SecurityException e5) {
                    e = e5;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }

        private boolean isCanceled() {
            return Thread.currentThread().isInterrupted();
        }
    }

    public final IBinder getSyncAdapterBinder() {
        return this.mISyncAdapterImpl.asBinder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback cb) {
        boolean doSync;
        try {
            doSync = onUnsyncableAccount();
        } catch (RuntimeException e) {
            Log.e(TAG, "Exception while calling onUnsyncableAccount, assuming 'true'", e);
            doSync = true;
        }
        try {
            cb.onUnsyncableAccountDone(doSync);
        } catch (RemoteException e2) {
            Log.e(TAG, "Could not report result of onUnsyncableAccount", e2);
        }
    }

    public boolean onUnsyncableAccount() {
        return true;
    }

    public void onSecurityException(Account account, Bundle extras, String authority, SyncResult syncResult) {
    }

    public void onSyncCanceled() {
        SyncThread syncThread;
        synchronized (this.mSyncThreadLock) {
            syncThread = this.mSyncThreads.get(null);
        }
        if (syncThread != null) {
            syncThread.interrupt();
        }
    }

    public void onSyncCanceled(Thread thread) {
        thread.interrupt();
    }
}
