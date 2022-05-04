package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Utils {
    private static final String TAG = "BroadcastRadio.utils";

    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeStringMap(Parcel dest, Map<String, String> map) {
        if (map == null) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> readStringMap(Parcel in) {
        int size = in.readInt();
        Map<String, String> map = new HashMap<>(size);
        while (true) {
            int size2 = size - 1;
            if (size <= 0) {
                return map;
            }
            String key = in.readString();
            String value = in.readString();
            map.put(key, value);
            size = size2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeStringIntMap(Parcel dest, Map<String, Integer> map) {
        if (map == null) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(map.size());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue().intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Integer> readStringIntMap(Parcel in) {
        int size = in.readInt();
        Map<String, Integer> map = new HashMap<>(size);
        while (true) {
            int size2 = size - 1;
            if (size <= 0) {
                return map;
            }
            String key = in.readString();
            int value = in.readInt();
            map.put(key, Integer.valueOf(value));
            size = size2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Parcelable> void writeSet(final Parcel dest, Set<T> set) {
        if (set == null) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(set.size());
        set.stream().forEach(new Consumer() { // from class: android.hardware.radio.Utils$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Parcel.this.writeTypedObject((Parcelable) obj, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Set<T> createSet(Parcel in, Parcelable.Creator<T> c) {
        int size = in.readInt();
        HashSet hashSet = new HashSet(size);
        while (true) {
            int size2 = size - 1;
            if (size <= 0) {
                return hashSet;
            }
            hashSet.add(in.readTypedObject(c));
            size = size2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeIntSet(final Parcel dest, Set<Integer> set) {
        if (set == null) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(set.size());
        set.stream().forEach(new Consumer() { // from class: android.hardware.radio.Utils$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Utils.lambda$writeIntSet$1(Parcel.this, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$writeIntSet$1(Parcel dest, Integer elem) {
        Objects.requireNonNull(elem);
        dest.writeInt(elem.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set<Integer> createIntSet(Parcel in) {
        int size = in.readInt();
        Set<Integer> set = new HashSet<>(size);
        while (true) {
            int size2 = size - 1;
            if (size <= 0) {
                return set;
            }
            set.add(Integer.valueOf(in.readInt()));
            size = size2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Parcelable> void writeTypedCollection(Parcel dest, Collection<T> coll) {
        ArrayList<T> list = null;
        if (coll != null) {
            if (coll instanceof ArrayList) {
                list = (ArrayList) coll;
            } else {
                list = new ArrayList<>(coll);
            }
        }
        dest.writeTypedList(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void close(ICloseHandle handle) {
        try {
            handle.close();
        } catch (RemoteException ex) {
            ex.rethrowFromSystemServer();
        }
    }
}
