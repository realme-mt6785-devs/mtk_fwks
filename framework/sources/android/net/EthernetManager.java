package android.net;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.EthernetManager;
import android.net.IEthernetServiceListener;
import android.net.ITetheredInterfaceCallback;
import android.os.RemoteException;
import com.android.internal.os.BackgroundThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
@SystemApi
/* loaded from: classes2.dex */
public class EthernetManager {
    private static final String TAG = "EthernetManager";
    private final IEthernetManager mService;
    private final ArrayList<ListenerInfo> mListeners = new ArrayList<>();
    private final IEthernetServiceListener.Stub mServiceListener = new AnonymousClass1();

    /* loaded from: classes2.dex */
    public interface Listener {
        void onAvailabilityChanged(String str, boolean z);
    }

    /* loaded from: classes2.dex */
    public interface TetheredInterfaceCallback {
        void onAvailable(String str);

        void onUnavailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.net.EthernetManager$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends IEthernetServiceListener.Stub {
        AnonymousClass1() {
        }

        @Override // android.net.IEthernetServiceListener
        public void onAvailabilityChanged(final String iface, final boolean isAvailable) {
            synchronized (EthernetManager.this.mListeners) {
                Iterator it = EthernetManager.this.mListeners.iterator();
                while (it.hasNext()) {
                    final ListenerInfo li = (ListenerInfo) it.next();
                    li.executor.execute(new Runnable() { // from class: android.net.EthernetManager$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            EthernetManager.ListenerInfo.this.listener.onAvailabilityChanged(iface, isAvailable);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ListenerInfo {
        public final Executor executor;
        public final Listener listener;

        /* synthetic */ ListenerInfo(Executor x0, Listener x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        private ListenerInfo(Executor executor, Listener listener) {
            this.executor = executor;
            this.listener = listener;
        }
    }

    public EthernetManager(Context context, IEthernetManager service) {
        this.mService = service;
    }

    public IpConfiguration getConfiguration(String iface) {
        try {
            return this.mService.getConfiguration(iface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setConfiguration(String iface, IpConfiguration config) {
        try {
            this.mService.setConfiguration(iface, config);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAvailable() {
        return getAvailableInterfaces().length > 0;
    }

    public boolean isAvailable(String iface) {
        try {
            return this.mService.isAvailable(iface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addListener(Listener listener) {
        addListener(listener, BackgroundThread.getExecutor());
    }

    public void addListener(Listener listener, Executor executor) {
        if (listener == null || executor == null) {
            throw new NullPointerException("listener and executor must not be null");
        }
        synchronized (this.mListeners) {
            this.mListeners.add(new ListenerInfo(executor, listener, null));
            if (this.mListeners.size() == 1) {
                try {
                    this.mService.addListener(this.mServiceListener);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    public String[] getAvailableInterfaces() {
        try {
            return this.mService.getAvailableInterfaces();
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public void removeListener(final Listener listener) {
        if (listener != null) {
            synchronized (this.mListeners) {
                this.mListeners.removeIf(new Predicate() { // from class: android.net.EthernetManager$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return EthernetManager.lambda$removeListener$0(EthernetManager.Listener.this, (EthernetManager.ListenerInfo) obj);
                    }
                });
                if (this.mListeners.isEmpty()) {
                    try {
                        this.mService.removeListener(this.mServiceListener);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
            return;
        }
        throw new IllegalArgumentException("listener must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$removeListener$0(Listener listener, ListenerInfo l) {
        return l.listener == listener;
    }

    public void setIncludeTestInterfaces(boolean include) {
        try {
            this.mService.setIncludeTestInterfaces(include);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* loaded from: classes2.dex */
    public static class TetheredInterfaceRequest {
        private final ITetheredInterfaceCallback mCb;
        private final IEthernetManager mService;

        /* synthetic */ TetheredInterfaceRequest(IEthernetManager x0, ITetheredInterfaceCallback x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        private TetheredInterfaceRequest(IEthernetManager service, ITetheredInterfaceCallback cb) {
            this.mService = service;
            this.mCb = cb;
        }

        public void release() {
            try {
                this.mService.releaseTetheredInterface(this.mCb);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    public TetheredInterfaceRequest requestTetheredInterface(Executor executor, TetheredInterfaceCallback callback) {
        Objects.requireNonNull(callback, "Callback must be non-null");
        Objects.requireNonNull(executor, "Executor must be non-null");
        ITetheredInterfaceCallback cbInternal = new AnonymousClass2(executor, callback);
        try {
            this.mService.requestTetheredInterface(cbInternal);
            return new TetheredInterfaceRequest(this.mService, cbInternal, null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* renamed from: android.net.EthernetManager$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    class AnonymousClass2 extends ITetheredInterfaceCallback.Stub {
        final /* synthetic */ TetheredInterfaceCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass2(Executor executor, TetheredInterfaceCallback tetheredInterfaceCallback) {
            this.val$executor = executor;
            this.val$callback = tetheredInterfaceCallback;
        }

        @Override // android.net.ITetheredInterfaceCallback
        public void onAvailable(final String iface) {
            Executor executor = this.val$executor;
            final TetheredInterfaceCallback tetheredInterfaceCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.net.EthernetManager$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EthernetManager.TetheredInterfaceCallback.this.onAvailable(iface);
                }
            });
        }

        @Override // android.net.ITetheredInterfaceCallback
        public void onUnavailable() {
            Executor executor = this.val$executor;
            final TetheredInterfaceCallback tetheredInterfaceCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.net.EthernetManager$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EthernetManager.TetheredInterfaceCallback.this.onUnavailable();
                }
            });
        }
    }
}
