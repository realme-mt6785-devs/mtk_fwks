package com.android.internal.listeners;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public interface ListenerTransport<TListener> {
    TListener getListener();

    void unregister();

    default void execute(Executor executor, final Consumer<TListener> operation) {
        Objects.requireNonNull(operation);
        if (getListener() != null) {
            executor.execute(new Runnable() { // from class: com.android.internal.listeners.ListenerTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ListenerTransport.lambda$execute$0(ListenerTransport.this, operation);
                }
            });
        }
    }

    static /* synthetic */ void lambda$execute$0(ListenerTransport _this, Consumer operation) {
        Object listener = _this.getListener();
        if (listener != null) {
            operation.accept(listener);
        }
    }
}
