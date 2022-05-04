package android.operator;

import android.os.Bundle;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IOplusOperator {
    String getActiveSimOperator();

    String getActiveSimRegion();

    Map getConfigMap(String str);

    void grantCustomizedRuntimePermissions();

    boolean isInSimTriggeredSystemBlackList(String str);

    void notifyRegionSwitch(Bundle bundle);

    void notifySimSwitch(Bundle bundle);

    void notifySmartCustomizationStart();

    void testAidl();
}
