package android.app;

import android.os.RemoteException;
import com.oplus.favorite.IOplusFavoriteQueryCallback;
/* loaded from: classes.dex */
public interface IOplusDirectActivityManager {
    void favoriteQueryRule(String str, IOplusFavoriteQueryCallback iOplusFavoriteQueryCallback) throws RemoteException;
}
