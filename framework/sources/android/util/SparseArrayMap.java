package android.util;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public class SparseArrayMap<K, V> {
    private final SparseArray<ArrayMap<K, V>> mData = new SparseArray<>();

    public void add(int key, K mapKey, V obj) {
        ArrayMap<K, V> data = this.mData.get(key);
        if (data == null) {
            data = new ArrayMap<>();
            this.mData.put(key, data);
        }
        data.put(mapKey, obj);
    }

    public void clear() {
        for (int i = 0; i < this.mData.size(); i++) {
            this.mData.valueAt(i).clear();
        }
    }

    public boolean contains(int key, K mapKey) {
        return this.mData.contains(key) && this.mData.get(key).containsKey(mapKey);
    }

    public void delete(int key) {
        this.mData.delete(key);
    }

    public V delete(int key, K mapKey) {
        ArrayMap<K, V> data = this.mData.get(key);
        if (data != null) {
            return data.remove(mapKey);
        }
        return null;
    }

    public V get(int key, K mapKey) {
        ArrayMap<K, V> data = this.mData.get(key);
        if (data != null) {
            return data.get(mapKey);
        }
        return null;
    }

    public V getOrDefault(int key, K mapKey, V defaultValue) {
        ArrayMap<K, V> data;
        if (!this.mData.contains(key) || (data = this.mData.get(key)) == null || !data.containsKey(mapKey)) {
            return defaultValue;
        }
        return data.get(mapKey);
    }

    public int indexOfKey(int key) {
        return this.mData.indexOfKey(key);
    }

    public int indexOfKey(int key, K mapKey) {
        ArrayMap<K, V> data = this.mData.get(key);
        if (data != null) {
            return data.indexOfKey(mapKey);
        }
        return -1;
    }

    public int keyAt(int index) {
        return this.mData.keyAt(index);
    }

    public K keyAt(int keyIndex, int mapIndex) {
        return this.mData.valueAt(keyIndex).keyAt(mapIndex);
    }

    public int numMaps() {
        return this.mData.size();
    }

    public int numElementsForKey(int key) {
        ArrayMap<K, V> data = this.mData.get(key);
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public V valueAt(int keyIndex, int mapIndex) {
        return this.mData.valueAt(keyIndex).valueAt(mapIndex);
    }

    public void forEach(Consumer<V> consumer) {
        for (int i = numMaps() - 1; i >= 0; i--) {
            ArrayMap<K, V> data = this.mData.valueAt(i);
            for (int j = data.size() - 1; j >= 0; j--) {
                consumer.accept(data.valueAt(j));
            }
        }
    }
}
