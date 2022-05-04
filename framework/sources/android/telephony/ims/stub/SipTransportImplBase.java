package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.ims.DelegateMessageCallback;
import android.telephony.ims.DelegateRequest;
import android.telephony.ims.DelegateStateCallback;
import android.telephony.ims.aidl.ISipDelegate;
import android.telephony.ims.aidl.ISipDelegateMessageCallback;
import android.telephony.ims.aidl.ISipDelegateStateCallback;
import android.telephony.ims.aidl.ISipTransport;
import android.telephony.ims.aidl.SipDelegateAidlWrapper;
import android.telephony.ims.stub.SipTransportImplBase;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes3.dex */
public class SipTransportImplBase {
    private static final String LOG_TAG = "SipTransportIB";
    private final Executor mBinderExecutor;
    private final IBinder.DeathRecipient mDeathRecipient = new AnonymousClass1();
    private final ISipTransport.Stub mSipTransportImpl = new AnonymousClass2();
    private final ArrayList<SipDelegateAidlWrapper> mDelegates = new ArrayList<>();

    /* renamed from: android.telephony.ims.stub.SipTransportImplBase$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 implements IBinder.DeathRecipient {
        AnonymousClass1() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            SipTransportImplBase.this.mBinderExecutor.execute(new Runnable() { // from class: android.telephony.ims.stub.SipTransportImplBase$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SipTransportImplBase.AnonymousClass1.this.lambda$binderDied$0$SipTransportImplBase$1();
                }
            });
        }

        public /* synthetic */ void lambda$binderDied$0$SipTransportImplBase$1() {
            SipTransportImplBase.this.binderDiedInternal(null);
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied(final IBinder who) {
            SipTransportImplBase.this.mBinderExecutor.execute(new Runnable() { // from class: android.telephony.ims.stub.SipTransportImplBase$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SipTransportImplBase.AnonymousClass1.this.lambda$binderDied$1$SipTransportImplBase$1(who);
                }
            });
        }

        public /* synthetic */ void lambda$binderDied$1$SipTransportImplBase$1(IBinder who) {
            SipTransportImplBase.this.binderDiedInternal(who);
        }
    }

    /* renamed from: android.telephony.ims.stub.SipTransportImplBase$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass2 extends ISipTransport.Stub {
        AnonymousClass2() {
        }

        @Override // android.telephony.ims.aidl.ISipTransport
        public void createSipDelegate(final int subId, final DelegateRequest request, final ISipDelegateStateCallback dc, final ISipDelegateMessageCallback mc) {
            long token = Binder.clearCallingIdentity();
            try {
                SipTransportImplBase.this.mBinderExecutor.execute(new Runnable() { // from class: android.telephony.ims.stub.SipTransportImplBase$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        SipTransportImplBase.AnonymousClass2.this.lambda$createSipDelegate$0$SipTransportImplBase$2(subId, request, dc, mc);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$createSipDelegate$0$SipTransportImplBase$2(int subId, DelegateRequest request, ISipDelegateStateCallback dc, ISipDelegateMessageCallback mc) {
            SipTransportImplBase.this.createSipDelegateInternal(subId, request, dc, mc);
        }

        @Override // android.telephony.ims.aidl.ISipTransport
        public void destroySipDelegate(final ISipDelegate delegate, final int reason) {
            long token = Binder.clearCallingIdentity();
            try {
                SipTransportImplBase.this.mBinderExecutor.execute(new Runnable() { // from class: android.telephony.ims.stub.SipTransportImplBase$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SipTransportImplBase.AnonymousClass2.this.lambda$destroySipDelegate$1$SipTransportImplBase$2(delegate, reason);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$destroySipDelegate$1$SipTransportImplBase$2(ISipDelegate delegate, int reason) {
            SipTransportImplBase.this.destroySipDelegateInternal(delegate, reason);
        }
    }

    public SipTransportImplBase(Executor executor) {
        if (executor != null) {
            this.mBinderExecutor = executor;
            return;
        }
        throw new IllegalArgumentException("executor must not be null");
    }

    public void createSipDelegate(int subscriptionId, DelegateRequest request, DelegateStateCallback dc, DelegateMessageCallback mc) {
        throw new UnsupportedOperationException("createSipDelegate not implemented!");
    }

    public void destroySipDelegate(SipDelegate delegate, int reason) {
        throw new UnsupportedOperationException("destroySipDelegate not implemented!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSipDelegateInternal(int subId, DelegateRequest r, ISipDelegateStateCallback cb, ISipDelegateMessageCallback mc) {
        SipDelegateAidlWrapper wrapper = new SipDelegateAidlWrapper(this.mBinderExecutor, cb, mc);
        this.mDelegates.add(wrapper);
        linkDeathRecipient(wrapper);
        createSipDelegate(subId, r, wrapper, wrapper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroySipDelegateInternal(ISipDelegate d, int reason) {
        SipDelegateAidlWrapper result = null;
        Iterator<SipDelegateAidlWrapper> it = this.mDelegates.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SipDelegateAidlWrapper w = it.next();
            if (Objects.equals(d, w.getDelegateBinder())) {
                result = w;
                break;
            }
        }
        if (result != null) {
            unlinkDeathRecipient(result);
            this.mDelegates.remove(result);
            destroySipDelegate(result.getDelegate(), reason);
            return;
        }
        Log.w(LOG_TAG, "destroySipDelegateInternal, could not findSipDelegate corresponding to " + d);
    }

    private void linkDeathRecipient(SipDelegateAidlWrapper w) {
        try {
            w.getStateCallbackBinder().asBinder().linkToDeath(this.mDeathRecipient, 0);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, "linkDeathRecipient, remote process already died, cleaning up.");
            this.mDeathRecipient.binderDied(w.getStateCallbackBinder().asBinder());
        }
    }

    private void unlinkDeathRecipient(SipDelegateAidlWrapper w) {
        try {
            w.getStateCallbackBinder().asBinder().unlinkToDeath(this.mDeathRecipient, 0);
        } catch (NoSuchElementException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:5:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void binderDiedInternal(android.os.IBinder r5) {
        /*
            r4 = this;
            java.util.ArrayList<android.telephony.ims.aidl.SipDelegateAidlWrapper> r0 = r4.mDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            java.lang.String r2 = "SipTransportIB"
            if (r1 == 0) goto L_0x004d
            java.lang.Object r1 = r0.next()
            android.telephony.ims.aidl.SipDelegateAidlWrapper r1 = (android.telephony.ims.aidl.SipDelegateAidlWrapper) r1
            if (r5 == 0) goto L_0x0026
            android.telephony.ims.aidl.ISipDelegateStateCallback r3 = r1.getStateCallbackBinder()
            android.os.IBinder r3 = r3.asBinder()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            goto L_0x0006
        L_0x0026:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "Binder death detected for "
            r0.append(r3)
            r0.append(r1)
            java.lang.String r3 = ", calling destroy and removing."
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r2, r0)
            java.util.ArrayList<android.telephony.ims.aidl.SipDelegateAidlWrapper> r0 = r4.mDelegates
            r0.remove(r1)
            android.telephony.ims.stub.SipDelegate r0 = r1.getDelegate()
            r2 = 1
            r4.destroySipDelegate(r0, r2)
            return
        L_0x004d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Binder death detected for IBinder "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ", but couldn't find matching SipDelegate"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.ims.stub.SipTransportImplBase.binderDiedInternal(android.os.IBinder):void");
    }

    public ISipTransport getBinder() {
        return this.mSipTransportImpl;
    }
}
