package com.android.server.vcn.repackaged.util;

import android.os.ParcelUuid;
import android.os.PersistableBundle;
import com.android.internal.util.HexDump;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes4.dex */
public class PersistableBundleUtils {
    private static final String BYTE_ARRAY_KEY = "BYTE_ARRAY_KEY";
    private static final String COLLECTION_SIZE_KEY = "COLLECTION_LENGTH";
    private static final String INTEGER_KEY = "INTEGER_KEY";
    private static final String LIST_KEY_FORMAT = "LIST_ITEM_%d";
    private static final String MAP_KEY_FORMAT = "MAP_KEY_%d";
    private static final String MAP_VALUE_FORMAT = "MAP_VALUE_%d";
    private static final String PARCEL_UUID_KEY = "PARCEL_UUID";
    public static final Serializer<Integer> INTEGER_SERIALIZER = PersistableBundleUtils$$ExternalSyntheticLambda1.INSTANCE;
    public static final Deserializer<Integer> INTEGER_DESERIALIZER = PersistableBundleUtils$$ExternalSyntheticLambda0.INSTANCE;

    /* loaded from: classes4.dex */
    public interface Deserializer<T> {
        T fromPersistableBundle(PersistableBundle persistableBundle);
    }

    /* loaded from: classes4.dex */
    public interface Serializer<T> {
        PersistableBundle toPersistableBundle(T t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PersistableBundle lambda$static$0(Integer i) {
        PersistableBundle result = new PersistableBundle();
        result.putInt(INTEGER_KEY, i.intValue());
        return result;
    }

    public static PersistableBundle fromParcelUuid(ParcelUuid uuid) {
        PersistableBundle result = new PersistableBundle();
        result.putString(PARCEL_UUID_KEY, uuid.toString());
        return result;
    }

    public static ParcelUuid toParcelUuid(PersistableBundle bundle) {
        return ParcelUuid.fromString(bundle.getString(PARCEL_UUID_KEY));
    }

    public static <T> PersistableBundle fromList(List<T> in, Serializer<T> serializer) {
        PersistableBundle result = new PersistableBundle();
        result.putInt(COLLECTION_SIZE_KEY, in.size());
        for (int i = 0; i < in.size(); i++) {
            String key = String.format(LIST_KEY_FORMAT, Integer.valueOf(i));
            result.putPersistableBundle(key, serializer.toPersistableBundle(in.get(i)));
        }
        return result;
    }

    public static <T> List<T> toList(PersistableBundle in, Deserializer<T> deserializer) {
        int listLength = in.getInt(COLLECTION_SIZE_KEY);
        ArrayList<T> result = new ArrayList<>(listLength);
        for (int i = 0; i < listLength; i++) {
            String key = String.format(LIST_KEY_FORMAT, Integer.valueOf(i));
            PersistableBundle item = in.getPersistableBundle(key);
            result.add(deserializer.fromPersistableBundle(item));
        }
        return result;
    }

    public static PersistableBundle fromByteArray(byte[] array) {
        PersistableBundle result = new PersistableBundle();
        result.putString(BYTE_ARRAY_KEY, HexDump.toHexString(array));
        return result;
    }

    public static byte[] toByteArray(PersistableBundle bundle) {
        Objects.requireNonNull(bundle, "PersistableBundle is null");
        String hex = bundle.getString(BYTE_ARRAY_KEY);
        if (hex != null && hex.length() % 2 == 0) {
            return HexDump.hexStringToByteArray(hex);
        }
        throw new IllegalArgumentException("PersistableBundle contains invalid byte array");
    }

    public static <K, V> PersistableBundle fromMap(Map<K, V> in, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        PersistableBundle result = new PersistableBundle();
        result.putInt(COLLECTION_SIZE_KEY, in.size());
        int i = 0;
        for (Map.Entry<K, V> entry : in.entrySet()) {
            String keyKey = String.format(MAP_KEY_FORMAT, Integer.valueOf(i));
            String valueKey = String.format(MAP_VALUE_FORMAT, Integer.valueOf(i));
            result.putPersistableBundle(keyKey, keySerializer.toPersistableBundle(entry.getKey()));
            result.putPersistableBundle(valueKey, valueSerializer.toPersistableBundle(entry.getValue()));
            i++;
        }
        return result;
    }

    public static <K, V> LinkedHashMap<K, V> toMap(PersistableBundle in, Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        int mapSize = in.getInt(COLLECTION_SIZE_KEY);
        LinkedHashMap<K, V> result = new LinkedHashMap<>(mapSize);
        for (int i = 0; i < mapSize; i++) {
            String keyKey = String.format(MAP_KEY_FORMAT, Integer.valueOf(i));
            String valueKey = String.format(MAP_VALUE_FORMAT, Integer.valueOf(i));
            PersistableBundle keyBundle = in.getPersistableBundle(keyKey);
            PersistableBundle valueBundle = in.getPersistableBundle(valueKey);
            K key = keyDeserializer.fromPersistableBundle(keyBundle);
            V value = valueDeserializer.fromPersistableBundle(valueBundle);
            result.put(key, value);
        }
        return result;
    }

    /* loaded from: classes4.dex */
    public static class LockingReadWriteHelper {
        private final ReadWriteLock mDiskLock = new ReentrantReadWriteLock();
        private final String mPath;

        public LockingReadWriteHelper(String path) {
            Objects.requireNonNull(path, "fileName was null");
            this.mPath = path;
        }

        public PersistableBundle readFromDisk() throws IOException {
            try {
                this.mDiskLock.readLock().lock();
                File file = new File(this.mPath);
                if (!file.exists()) {
                    return null;
                }
                FileInputStream fis = new FileInputStream(file);
                PersistableBundle readFromStream = PersistableBundle.readFromStream(fis);
                fis.close();
                return readFromStream;
            } finally {
                this.mDiskLock.readLock().unlock();
            }
        }

        public void writeToDisk(PersistableBundle bundle) throws IOException {
            Objects.requireNonNull(bundle, "bundle was null");
            try {
                this.mDiskLock.writeLock().lock();
                File file = new File(this.mPath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                bundle.writeToStream(fos);
                fos.close();
            } finally {
                this.mDiskLock.writeLock().unlock();
            }
        }
    }
}
