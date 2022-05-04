package system.ext.loader.core;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes4.dex */
public class ExtRegistry {
    private static final Map<Class<?>, ExtCreator<?>> EXT_CREATORS = new HashMap();

    public static <T> void registerExt(Class<T> extClass, ExtCreator<T> extFetcher) {
        if (extClass != null && extFetcher != null) {
            EXT_CREATORS.put(extClass, extFetcher);
        }
    }

    public static <T> ExtCreator<T> getExt(Class<T> extClass) {
        if (extClass == null) {
            return null;
        }
        return (ExtCreator<T>) EXT_CREATORS.get(extClass);
    }
}
