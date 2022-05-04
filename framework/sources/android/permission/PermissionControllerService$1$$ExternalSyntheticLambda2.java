package android.permission;

import java.util.concurrent.CountDownLatch;
/* loaded from: classes2.dex */
public final /* synthetic */ class PermissionControllerService$1$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ PermissionControllerService$1$$ExternalSyntheticLambda2(CountDownLatch countDownLatch) {
        this.f$0 = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.countDown();
    }
}
