package com.android.modules.utils;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
abstract class BaseParceledListSlice<T> implements Parcelable {
    private int mInlineCountLimit = Integer.MAX_VALUE;
    private final List<T> mList;
    private static String TAG = "ParceledListSlice";
    private static boolean DEBUG = false;
    private static final int MAX_IPC_SIZE = IBinder.getSuggestedMaxIpcSizeBytes();

    protected abstract Parcelable.Creator<?> readParcelableCreator(Parcel parcel, ClassLoader classLoader);

    protected abstract void writeElement(T t, Parcel parcel, int i);

    protected abstract void writeParcelableCreator(T t, Parcel parcel);

    public BaseParceledListSlice(List<T> list) {
        this.mList = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseParceledListSlice(Parcel p, ClassLoader loader) {
        List<T> list;
        int N = p.readInt();
        this.mList = new ArrayList(N);
        if (DEBUG) {
            Log.d(TAG, "Retrieving " + N + " items");
        }
        if (N > 0) {
            Parcelable.Creator<?> creator = readParcelableCreator(p, loader);
            Class<?> listElementClass = null;
            int i = 0;
            while (i < N && p.readInt() != 0) {
                T parcelable = readCreator(creator, p, loader);
                if (listElementClass == null) {
                    listElementClass = parcelable.getClass();
                } else {
                    verifySameType(listElementClass, parcelable.getClass());
                }
                this.mList.add(parcelable);
                if (DEBUG) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Read inline #");
                    sb.append(i);
                    sb.append(": ");
                    List<T> list2 = this.mList;
                    sb.append(list2.get(list2.size() - 1));
                    Log.d(str, sb.toString());
                }
                i++;
            }
            if (i < N) {
                IBinder retriever = p.readStrongBinder();
                int i2 = i;
                while (i2 < N) {
                    if (DEBUG) {
                        Log.d(TAG, "Reading more @" + i2 + " of " + N + ": retriever=" + retriever);
                    }
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    data.writeInt(i2);
                    try {
                        retriever.transact(1, data, reply, 0);
                        while (i2 < N && reply.readInt() != 0) {
                            T parcelable2 = readCreator(creator, reply, loader);
                            verifySameType(listElementClass, parcelable2.getClass());
                            this.mList.add(parcelable2);
                            if (DEBUG) {
                                String str2 = TAG;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Read extra #");
                                sb2.append(i2);
                                sb2.append(": ");
                                sb2.append(this.mList.get(list.size() - 1));
                                Log.d(str2, sb2.toString());
                            }
                            i2++;
                        }
                        reply.recycle();
                        data.recycle();
                    } catch (RemoteException e) {
                        Log.w(TAG, "Failure retrieving array; only received " + i2 + " of " + N, e);
                        return;
                    }
                }
            }
        }
    }

    private T readCreator(Parcelable.Creator<?> creator, Parcel p, ClassLoader loader) {
        if (!(creator instanceof Parcelable.ClassLoaderCreator)) {
            return (T) creator.createFromParcel(p);
        }
        Parcelable.ClassLoaderCreator<?> classLoaderCreator = (Parcelable.ClassLoaderCreator) creator;
        return (T) classLoaderCreator.createFromParcel(p, loader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void verifySameType(Class<?> expected, Class<?> actual) {
        if (!actual.equals(expected)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't unparcel type ");
            String str = null;
            sb.append(actual == null ? null : actual.getName());
            sb.append(" in list of type ");
            if (expected != null) {
                str = expected.getName();
            }
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public List<T> getList() {
        return this.mList;
    }

    public void setInlineCountLimit(int maxCount) {
        this.mInlineCountLimit = maxCount;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0090, code lost:
        r10.writeInt(0);
        r3 = new com.android.modules.utils.BaseParceledListSlice.AnonymousClass1(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x009a, code lost:
        if (com.android.modules.utils.BaseParceledListSlice.DEBUG == false) goto L_0x00c2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x009c, code lost:
        r5 = com.android.modules.utils.BaseParceledListSlice.TAG;
        android.util.Log.d(r5, "Breaking @" + r4 + " of " + r0 + ": retriever=" + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00c2, code lost:
        r10.writeStrongBinder(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00c5, code lost:
        return;
     */
    @Override // android.os.Parcelable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeToParcel(android.os.Parcel r10, final int r11) {
        /*
            r9 = this;
            java.util.List<T> r0 = r9.mList
            int r0 = r0.size()
            r1 = r11
            r10.writeInt(r0)
            boolean r2 = com.android.modules.utils.BaseParceledListSlice.DEBUG
            if (r2 == 0) goto L_0x0029
            java.lang.String r2 = com.android.modules.utils.BaseParceledListSlice.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Writing "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r4 = " items"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
        L_0x0029:
            if (r0 <= 0) goto L_0x00c5
            java.util.List<T> r2 = r9.mList
            r3 = 0
            java.lang.Object r2 = r2.get(r3)
            java.lang.Class r2 = r2.getClass()
            java.util.List<T> r4 = r9.mList
            java.lang.Object r4 = r4.get(r3)
            r9.writeParcelableCreator(r4, r10)
            r4 = 0
        L_0x0040:
            if (r4 >= r0) goto L_0x008e
            int r5 = r9.mInlineCountLimit
            if (r4 >= r5) goto L_0x008e
            int r5 = r10.dataSize()
            int r6 = com.android.modules.utils.BaseParceledListSlice.MAX_IPC_SIZE
            if (r5 >= r6) goto L_0x008e
            r5 = 1
            r10.writeInt(r5)
            java.util.List<T> r5 = r9.mList
            java.lang.Object r5 = r5.get(r4)
            java.lang.Class r6 = r5.getClass()
            verifySameType(r2, r6)
            r9.writeElement(r5, r10, r1)
            boolean r6 = com.android.modules.utils.BaseParceledListSlice.DEBUG
            if (r6 == 0) goto L_0x008a
            java.lang.String r6 = com.android.modules.utils.BaseParceledListSlice.TAG
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Wrote inline #"
            r7.append(r8)
            r7.append(r4)
            java.lang.String r8 = ": "
            r7.append(r8)
            java.util.List<T> r8 = r9.mList
            java.lang.Object r8 = r8.get(r4)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r6, r7)
        L_0x008a:
            int r4 = r4 + 1
            goto L_0x0040
        L_0x008e:
            if (r4 >= r0) goto L_0x00c5
            r10.writeInt(r3)
            com.android.modules.utils.BaseParceledListSlice$1 r3 = new com.android.modules.utils.BaseParceledListSlice$1
            r3.<init>()
            boolean r5 = com.android.modules.utils.BaseParceledListSlice.DEBUG
            if (r5 == 0) goto L_0x00c2
            java.lang.String r5 = com.android.modules.utils.BaseParceledListSlice.TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Breaking @"
            r6.append(r7)
            r6.append(r4)
            java.lang.String r7 = " of "
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = ": retriever="
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r5, r6)
        L_0x00c2:
            r10.writeStrongBinder(r3)
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.modules.utils.BaseParceledListSlice.writeToParcel(android.os.Parcel, int):void");
    }
}
