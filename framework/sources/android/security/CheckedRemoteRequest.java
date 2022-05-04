package android.security;

import android.os.RemoteException;
@FunctionalInterface
/* loaded from: classes2.dex */
interface CheckedRemoteRequest<R> {
    R execute() throws RemoteException;
}
