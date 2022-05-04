package android.os;

import android.content.Context;
import android.os.PowerComponents;
import android.util.proto.ProtoOutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public abstract class BatteryConsumer {
    public static final int FIRST_CUSTOM_POWER_COMPONENT_ID = 1000;
    public static final int LAST_CUSTOM_POWER_COMPONENT_ID = 9999;
    public static final int POWER_COMPONENT_AMBIENT_DISPLAY = 15;
    public static final int POWER_COMPONENT_AUDIO = 4;
    public static final int POWER_COMPONENT_BLUETOOTH = 2;
    public static final int POWER_COMPONENT_CAMERA = 3;
    public static final int POWER_COMPONENT_COUNT = 18;
    public static final int POWER_COMPONENT_CPU = 1;
    public static final int POWER_COMPONENT_FLASHLIGHT = 6;
    public static final int POWER_COMPONENT_GNSS = 10;
    public static final int POWER_COMPONENT_IDLE = 16;
    public static final int POWER_COMPONENT_MEMORY = 13;
    public static final int POWER_COMPONENT_MOBILE_RADIO = 8;
    public static final int POWER_COMPONENT_PHONE = 14;
    public static final int POWER_COMPONENT_REATTRIBUTED_TO_OTHER_CONSUMERS = 17;
    public static final int POWER_COMPONENT_SCREEN = 0;
    public static final int POWER_COMPONENT_SENSORS = 9;
    public static final int POWER_COMPONENT_SYSTEM_SERVICES = 7;
    public static final int POWER_COMPONENT_VIDEO = 5;
    public static final int POWER_COMPONENT_WAKELOCK = 12;
    public static final int POWER_COMPONENT_WIFI = 11;
    public static final int POWER_MODEL_MEASURED_ENERGY = 2;
    public static final int POWER_MODEL_POWER_PROFILE = 1;
    public static final int POWER_MODEL_UNDEFINED = 0;
    private static final String[] sPowerComponentNames;
    protected final PowerComponents mPowerComponents;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PowerComponent {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PowerModel {
    }

    public abstract void dump(PrintWriter printWriter, boolean z);

    static {
        sPowerComponentNames = r0;
        String[] strArr = {"screen", "cpu", "bluetooth", Context.CAMERA_SERVICE, "audio", "video", "flashlight", "system_services", "mobile_radio", "sensors", "gnss", "wifi", "wakelock", "memory", "phone", "ambient_display", "idle", "reattributed"};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BatteryConsumer(PowerComponents powerComponents) {
        this.mPowerComponents = powerComponents;
    }

    public double getConsumedPower() {
        return this.mPowerComponents.getConsumedPower();
    }

    public double getConsumedPower(int componentId) {
        return this.mPowerComponents.getConsumedPower(componentId);
    }

    public int getPowerModel(int componentId) {
        return this.mPowerComponents.getPowerModel(componentId);
    }

    public double getConsumedPowerForCustomComponent(int componentId) {
        return this.mPowerComponents.getConsumedPowerForCustomComponent(componentId);
    }

    public int getCustomPowerComponentCount() {
        return this.mPowerComponents.getCustomPowerComponentCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCustomPowerComponentNames(String[] customPowerComponentNames) {
        this.mPowerComponents.setCustomPowerComponentNames(customPowerComponentNames);
    }

    public String getCustomPowerComponentName(int componentId) {
        return this.mPowerComponents.getCustomPowerComponentName(componentId);
    }

    public long getUsageDurationMillis(int componentId) {
        return this.mPowerComponents.getUsageDurationMillis(componentId);
    }

    public long getUsageDurationForCustomComponentMillis(int componentId) {
        return this.mPowerComponents.getUsageDurationForCustomComponentMillis(componentId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeToParcel(Parcel dest, int flags) {
        this.mPowerComponents.writeToParcel(dest, flags);
    }

    public static String powerComponentIdToString(int componentId) {
        return sPowerComponentNames[componentId];
    }

    public static String powerModelToString(int powerModel) {
        switch (powerModel) {
            case 1:
                return "power profile";
            case 2:
                return "measured energy";
            default:
                return "";
        }
    }

    public void dump(PrintWriter pw) {
        dump(pw, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasStatsProtoData() {
        return writeStatsProtoImpl(null, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeStatsProto(ProtoOutputStream proto, long fieldId) {
        writeStatsProtoImpl(proto, fieldId);
    }

    private boolean writeStatsProtoImpl(ProtoOutputStream proto, long fieldId) {
        long totalConsumedPowerDeciCoulombs = convertMahToDeciCoulombs(getConsumedPower());
        if (totalConsumedPowerDeciCoulombs == 0) {
            return false;
        }
        if (proto == null) {
            return true;
        }
        long token = proto.start(fieldId);
        proto.write(1112396529665L, totalConsumedPowerDeciCoulombs);
        this.mPowerComponents.writeStatsProto(proto);
        proto.end(token);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long convertMahToDeciCoulombs(double powerMah) {
        return (long) ((36.0d * powerMah) + 0.5d);
    }

    /* loaded from: classes2.dex */
    protected static abstract class BaseBuilder<T extends BaseBuilder<?>> {
        final PowerComponents.Builder mPowerComponentsBuilder;

        public BaseBuilder(String[] customPowerComponentNames, boolean includePowerModels) {
            this.mPowerComponentsBuilder = new PowerComponents.Builder(customPowerComponentNames, includePowerModels);
        }

        public T setConsumedPower(int componentId, double componentPower) {
            return setConsumedPower(componentId, componentPower, 1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T setConsumedPower(int componentId, double componentPower, int powerModel) {
            this.mPowerComponentsBuilder.setConsumedPower(componentId, componentPower, powerModel);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T setConsumedPowerForCustomComponent(int componentId, double componentPower) {
            this.mPowerComponentsBuilder.setConsumedPowerForCustomComponent(componentId, componentPower);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T setUsageDurationMillis(int componentId, long componentUsageTimeMillis) {
            this.mPowerComponentsBuilder.setUsageDurationMillis(componentId, componentUsageTimeMillis);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T setUsageDurationForCustomComponentMillis(int componentId, long componentUsageTimeMillis) {
            this.mPowerComponentsBuilder.setUsageDurationForCustomComponentMillis(componentId, componentUsageTimeMillis);
            return this;
        }

        public double getTotalPower() {
            return this.mPowerComponentsBuilder.getTotalPower();
        }
    }
}
