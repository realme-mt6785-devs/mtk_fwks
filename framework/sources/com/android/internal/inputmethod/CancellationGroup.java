package com.android.internal.inputmethod;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes4.dex */
public final class CancellationGroup {
    private final Object mLock = new Object();
    private ArrayList<CountDownLatch> mLatchList = null;
    private boolean mCanceled = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean registerLatch(CountDownLatch latch) {
        synchronized (this.mLock) {
            if (this.mCanceled) {
                return false;
            }
            if (this.mLatchList == null) {
                this.mLatchList = new ArrayList<>(1);
            }
            this.mLatchList.add(latch);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unregisterLatch(CountDownLatch latch) {
        synchronized (this.mLock) {
            ArrayList<CountDownLatch> arrayList = this.mLatchList;
            if (arrayList != null) {
                arrayList.remove(latch);
            }
        }
    }

    public void cancelAll() {
        synchronized (this.mLock) {
            if (!this.mCanceled) {
                this.mCanceled = true;
                ArrayList<CountDownLatch> arrayList = this.mLatchList;
                if (arrayList != null) {
                    arrayList.forEach(CancellationGroup$$ExternalSyntheticLambda0.INSTANCE);
                    this.mLatchList.clear();
                    this.mLatchList = null;
                }
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCanceled;
        }
        return z;
    }
}
