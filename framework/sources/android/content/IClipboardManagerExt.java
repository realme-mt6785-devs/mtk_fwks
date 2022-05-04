package android.content;

import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IClipboardManagerExt {
    default boolean adjustBeforeGetPrimaryClip(Context context, String pkg, int userId) throws RemoteException {
        return false;
    }

    default ClipData hookGetPrimaryClip(String pkg, int userId) throws RemoteException {
        return null;
    }
}
