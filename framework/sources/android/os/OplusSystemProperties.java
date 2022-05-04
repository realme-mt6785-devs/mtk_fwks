package android.os;

import android.util.Log;
import com.oplus.annotation.OplusProperty;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class OplusSystemProperties {
    private static final String TAG = "OplusSystemProperties";
    private ArrayList<String> mOplusPropertyPersistList;
    private ArrayList<String> mOplusPropertyReadOnlyList;
    private ArrayList<String> mOplusPropertySysList;

    /* synthetic */ OplusSystemProperties(AnonymousClass1 x0) {
        this();
    }

    private OplusSystemProperties() {
        this.mOplusPropertyReadOnlyList = new ArrayList<>();
        this.mOplusPropertyPersistList = new ArrayList<>();
        this.mOplusPropertySysList = new ArrayList<>();
        if (Build.IS_DEBUGGABLE) {
            initOplusSystemPropertiesList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class InstanceHolder {
        static OplusSystemProperties INSTANCE = new OplusSystemProperties(null);

        private InstanceHolder() {
        }
    }

    public static OplusSystemProperties getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void initOplusSystemPropertiesList() {
        try {
            Field[] fields = OplusPropertyList.class.getDeclaredFields();
            for (Field field : fields) {
                boolean isAnnotation = field.isAnnotationPresent(OplusProperty.class);
                if (isAnnotation) {
                    String propertyName = (String) field.get(null);
                    Log.d(TAG, "load prop:" + propertyName);
                    OplusProperty oplusPropertyAno = (OplusProperty) field.getDeclaredAnnotation(OplusProperty.class);
                    if (oplusPropertyAno != null) {
                        switch (AnonymousClass1.$SwitchMap$com$oplus$annotation$OplusProperty$OplusPropertyType[oplusPropertyAno.value().ordinal()]) {
                            case 1:
                                this.mOplusPropertyReadOnlyList.add(propertyName);
                                continue;
                            case 2:
                                this.mOplusPropertyPersistList.add(propertyName);
                                continue;
                            case 3:
                                this.mOplusPropertySysList.add(propertyName);
                                continue;
                            default:
                                Log.w(TAG, "Unknown type:" + oplusPropertyAno.value());
                                continue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "initOplusSystemPropertiesList failed.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.os.OplusSystemProperties$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oplus$annotation$OplusProperty$OplusPropertyType;

        static {
            int[] iArr = new int[OplusProperty.OplusPropertyType.values().length];
            $SwitchMap$com$oplus$annotation$OplusProperty$OplusPropertyType = iArr;
            try {
                iArr[OplusProperty.OplusPropertyType.RO_PROPERTY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusProperty$OplusPropertyType[OplusProperty.OplusPropertyType.PERSIST_PROPERTY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusProperty$OplusPropertyType[OplusProperty.OplusPropertyType.SYS_PROPERTY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private boolean isPredefinedProperty(String propertyName) {
        if (propertyName == null) {
            return false;
        }
        if (this.mOplusPropertyReadOnlyList.contains(propertyName) || this.mOplusPropertyPersistList.contains(propertyName) || this.mOplusPropertySysList.contains(propertyName)) {
            return true;
        }
        return false;
    }

    private static boolean isPredefinedOplusProperty(String propertyName) {
        if (Build.IS_DEBUGGABLE) {
            return getInstance().isPredefinedProperty(propertyName);
        }
        return true;
    }

    public static String get(String key) {
        if (isPredefinedOplusProperty(key)) {
            return SystemProperties.get(key);
        }
        Log.e(TAG, "Warning: This property is not predefined, prop:" + key);
        throw new IllegalArgumentException("Warning: This property is not predefined, prop:" + key);
    }

    public static String get(String key, String def) {
        if (isPredefinedOplusProperty(key)) {
            return SystemProperties.get(key, def);
        }
        Log.e(TAG, "Warning: This property is not predefined, prop:" + key);
        throw new IllegalArgumentException("Warning: This property is not predefined, prop:" + key);
    }

    public static int getInt(String key, int def) {
        if (isPredefinedOplusProperty(key)) {
            return SystemProperties.getInt(key, def);
        }
        Log.e(TAG, "Warning: This property is not predefined, prop:" + key);
        throw new IllegalArgumentException("Warning: This property is not predefined, prop:" + key);
    }

    public static long getLong(String key, long def) {
        if (isPredefinedOplusProperty(key)) {
            return SystemProperties.getLong(key, def);
        }
        Log.e(TAG, "Warning: This property is not predefined, prop:" + key);
        throw new IllegalArgumentException("Warning: This property is not predefined, prop:" + key);
    }

    public static boolean getBoolean(String key, boolean def) {
        if (isPredefinedOplusProperty(key)) {
            return SystemProperties.getBoolean(key, def);
        }
        Log.e(TAG, "Warning: This property is not predefined, prop:" + key);
        throw new IllegalArgumentException("Warning: This property is not predefined, prop:" + key);
    }

    public static void set(String key, String val) {
        if (isPredefinedOplusProperty(key)) {
            SystemProperties.set(key, val);
            return;
        }
        Log.e(TAG, "Warning: This property is not predefined, prop:" + key);
        throw new IllegalArgumentException("Warning: This property is not predefined, prop:" + key);
    }

    public void dumpOplusProperty(FileDescriptor fd, PrintWriter pw, String[] args) {
        if (!Build.IS_DEBUGGABLE) {
            pw.println("release version, unsupported");
            return;
        }
        pw.println("RO properties:");
        Iterator<String> it = this.mOplusPropertyReadOnlyList.iterator();
        while (it.hasNext()) {
            String propRo = it.next();
            pw.println("Ro: " + propRo);
        }
        pw.println("Persist properties:");
        Iterator<String> it2 = this.mOplusPropertyPersistList.iterator();
        while (it2.hasNext()) {
            String propPersist = it2.next();
            pw.println("Persist: " + propPersist);
        }
        pw.println("Sys properties:");
        Iterator<String> it3 = this.mOplusPropertySysList.iterator();
        while (it3.hasNext()) {
            String propSys = it3.next();
            pw.println("Sys: " + propSys);
        }
    }
}
