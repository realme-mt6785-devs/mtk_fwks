package android.operator;

import android.operator.IOplusOperatorManager;
import android.os.Bundle;
import android.os.Debug;
import android.os.OplusPropertyList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Slog;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class OplusOperatorManager implements IOplusOperator {
    @Deprecated
    public static final boolean SERVICE_ENABLED = true;
    public static final String SERVICE_NAME = "operator";
    private static final String TAG = "OplusOperatorManager";
    private IOplusOperatorManager mService;
    private static volatile OplusOperatorManager sManager = null;
    private static final Map mEmptyMap = Collections.emptyMap();

    private OplusOperatorManager(IOplusOperatorManager service) {
        this.mService = service;
    }

    public static OplusOperatorManager getInstance() {
        if (sManager == null) {
            synchronized (OplusOperatorManager.class) {
                if (sManager == null) {
                    IOplusOperatorManager mService = IOplusOperatorManager.Stub.asInterface(ServiceManager.getService(SERVICE_NAME));
                    if (mService != null) {
                        sManager = new OplusOperatorManager(mService);
                    } else {
                        Slog.e(TAG, "Whoops, service not initiated yet , print caller stack " + Debug.getCallers(9));
                        return null;
                    }
                }
            }
        }
        return sManager;
    }

    @Override // android.operator.IOplusOperator
    public void testAidl() {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager != null) {
                iOplusOperatorManager.testAidl();
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "testAidl ", ex);
        }
    }

    @Override // android.operator.IOplusOperator
    public Map getConfigMap(String config) {
        Map configMap = null;
        try {
            try {
                if (this.mService == null) {
                    return null;
                }
                long startTime = System.currentTimeMillis();
                Bundle bundle = new Bundle();
                bundle.putString("config", config);
                configMap = this.mService.getConfigMap(bundle);
                long consumingTime = System.currentTimeMillis() - startTime;
                Log.i(TAG, "getConfigMap " + config + " took " + consumingTime + "ms");
                return configMap;
            } catch (RemoteException ex) {
                Log.e(TAG, "getConfigMap ", ex);
                return configMap;
            }
        } catch (Throwable th) {
            return configMap;
        }
    }

    @Override // android.operator.IOplusOperator
    public void grantCustomizedRuntimePermissions() {
        try {
            if (this.mService != null) {
                long startTime = System.currentTimeMillis();
                this.mService.grantCustomizedRuntimePermissions();
                long consumingTime = System.currentTimeMillis() - startTime;
                Log.i(TAG, "grantCustomizedPermissions  took " + consumingTime + "ms");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "grantCustomizedPermissions ", ex);
        }
    }

    @Override // android.operator.IOplusOperator
    public void notifySmartCustomizationStart() {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager != null) {
                iOplusOperatorManager.notifySmartCustomizationStart();
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "notifySmartCustomizationStart ", ex);
        }
    }

    @Override // android.operator.IOplusOperator
    public boolean isInSimTriggeredSystemBlackList(String pkgName) {
        try {
            try {
                IOplusOperatorManager iOplusOperatorManager = this.mService;
                if (iOplusOperatorManager == null) {
                    return false;
                }
                boolean result = iOplusOperatorManager.isInSimTriggeredSystemBlackList(pkgName);
                return result;
            } catch (RemoteException ex) {
                Log.e(TAG, "isInSimTriggeredSystemBlackList " + pkgName, ex);
                return false;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    @Override // android.operator.IOplusOperator
    public String getActiveSimOperator() {
        return SystemProperties.get(OplusPropertyList.PROPERTY_OPERATOR_SERVICE_OPTA);
    }

    @Override // android.operator.IOplusOperator
    public String getActiveSimRegion() {
        return SystemProperties.get(OplusPropertyList.PROPERTY_OPERATOR_SERVICE_OPTB);
    }

    @Override // android.operator.IOplusOperator
    public void notifySimSwitch(Bundle data) {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager != null) {
                iOplusOperatorManager.notifySimSwitch(data);
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "notifiySimSwitch ", ex);
        }
    }

    @Override // android.operator.IOplusOperator
    public void notifyRegionSwitch(Bundle data) {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager != null) {
                iOplusOperatorManager.notifyRegionSwitch(data);
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "notifyRegionSwitch ", ex);
        }
    }

    /* loaded from: classes2.dex */
    private static class OplusOperatorDummyManager extends OplusOperatorManager implements IOplusOperator {
        private OplusOperatorDummyManager(IOplusOperatorManager service) {
            super(service);
        }

        @Override // android.operator.OplusOperatorManager, android.operator.IOplusOperator
        public void testAidl() {
            Log.i(OplusOperatorManager.TAG, "dummy testAidl");
        }

        @Override // android.operator.OplusOperatorManager, android.operator.IOplusOperator
        public Map getConfigMap(String config) {
            return OplusOperatorManager.mEmptyMap;
        }

        @Override // android.operator.OplusOperatorManager, android.operator.IOplusOperator
        public void grantCustomizedRuntimePermissions() {
            Log.i(OplusOperatorManager.TAG, "dummy grantCustomizedPermissions");
        }

        @Override // android.operator.OplusOperatorManager, android.operator.IOplusOperator
        public void notifySmartCustomizationStart() {
            Log.i(OplusOperatorManager.TAG, "dummy notifySmartCustomizationStart");
        }

        @Override // android.operator.OplusOperatorManager, android.operator.IOplusOperator
        public boolean isInSimTriggeredSystemBlackList(String pkgName) {
            return false;
        }
    }

    public void mountCotaImage(Bundle data) {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager != null) {
                iOplusOperatorManager.mountCotaImage(data);
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "mountCotaImage ", ex);
        }
    }

    public void notifyCotaMounted() {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager != null) {
                iOplusOperatorManager.notifyCotaMounted();
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "notifyCotaMounted ", ex);
        }
    }

    public int getCotaMountState(String imagePath) {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager == null) {
                return -1;
            }
            int ret = iOplusOperatorManager.getCotaMountState(imagePath);
            return ret;
        } catch (RemoteException ex) {
            Log.e(TAG, "getCotaMountState ", ex);
            return -1;
        }
    }

    public List<String> getCotaAppPackageNameList() {
        try {
            IOplusOperatorManager iOplusOperatorManager = this.mService;
            if (iOplusOperatorManager == null) {
                return null;
            }
            List<String> apkNameList = iOplusOperatorManager.getCotaAppPackageNameList();
            return apkNameList;
        } catch (RemoteException ex) {
            Log.e(TAG, "getCotaAppPackageNameList ", ex);
            return null;
        }
    }
}
