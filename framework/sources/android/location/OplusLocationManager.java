package android.location;

import android.content.Context;
import android.location.IOplusLocationManager;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.oplus.compatibility.OplusCompatibilityManager;
import java.util.List;
/* loaded from: classes2.dex */
public class OplusLocationManager {
    private static final int OPLUS_GPS_STATUS = 32;
    private final String TAG = "OplusLocationManager";

    public List<String> getInUsePackagesList() {
        try {
            IOplusLocationManager oplusLocMgr = IOplusLocationManager.Stub.asInterface(ServiceManager.getService("OplusLocationManager"));
            if (oplusLocMgr == null) {
                return null;
            }
            List<String> result = oplusLocMgr.getInUsePackagesList();
            return result;
        } catch (RemoteException e) {
            Log.e("OplusLocationManager", "Running getPackageList error!");
            return null;
        }
    }

    public void setDebugOn() {
        try {
            IOplusLocationManager oplusLocMgr = IOplusLocationManager.Stub.asInterface(ServiceManager.getService("OplusLocationManager"));
            if (oplusLocMgr != null) {
                oplusLocMgr.setDebugOn();
            }
        } catch (RemoteException e) {
            Log.e("OplusLocationManager", "Running setDebugOn error!");
        }
    }

    public void setDebugOff() {
        try {
            IOplusLocationManager oplusLocMgr = IOplusLocationManager.Stub.asInterface(ServiceManager.getService("OplusLocationManager"));
            if (oplusLocMgr != null) {
                oplusLocMgr.setDebugOff();
            }
        } catch (RemoteException e) {
            Log.e("OplusLocationManager", "Running setDebugOff error!");
        }
    }

    public void setDebugDump() {
        try {
            IOplusLocationManager oplusLocMgr = IOplusLocationManager.Stub.asInterface(ServiceManager.getService("OplusLocationManager"));
            if (oplusLocMgr != null) {
                oplusLocMgr.setDebugDump();
            }
        } catch (RemoteException e) {
            Log.e("OplusLocationManager", "Running setDebugDump error!");
        }
    }

    public void handleCompatibilityException(Context context) {
        String packageName = getPackageName(context);
        OplusCompatibilityManager compatibilityManager = new OplusCompatibilityManager();
        compatibilityManager.handleCompatibilityException(32, packageName);
    }

    private String getPackageName(Context context) {
        int uid = Binder.getCallingUid();
        String[] packages = context.getPackageManager().getPackagesForUid(uid);
        if (packages == null || 1 != packages.length) {
            Log.d("OplusLocationManager", "packages is invalid!!");
            return "";
        }
        String packageName = packages[0];
        Log.d("OplusLocationManager", "only one name : " + packageName);
        return packageName;
    }
}
