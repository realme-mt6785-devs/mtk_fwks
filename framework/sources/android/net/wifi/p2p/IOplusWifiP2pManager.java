package android.net.wifi.p2p;

import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IOplusWifiP2pManager {
    public static final String DESCRIPTOR = "android.net.wifi.IOplusWifiP2pManager";
    public static final int OPLUS_CALL_TRANSACTION_INDEX = 11000;
    public static final int OPLUS_FIRST_CALL_TRANSACTION = 11001;
    public static final int TRANSACTION_saveExternalPeerAddress = 11004;
    public static final int TRANSACTION_setNfcTriggered = 11003;
    public static final int TRANSACTION_setOshareEnabled = 11007;
    public static final int TRANSACTION_setPcAutonomousGo = 11005;
    public static final int TRANSACTION_setPcAutonomousGoV2 = 11006;

    void saveExternalPeerAddress(String str) throws RemoteException;

    boolean setNfcTriggered(boolean z) throws RemoteException;

    boolean setPcAutonomousGo(boolean z) throws RemoteException;
}
