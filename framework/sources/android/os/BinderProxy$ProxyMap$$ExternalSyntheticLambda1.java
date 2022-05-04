package android.os;

import java.util.Comparator;
import java.util.Map;
/* loaded from: classes2.dex */
public final /* synthetic */ class BinderProxy$ProxyMap$$ExternalSyntheticLambda1 implements Comparator {
    public static final /* synthetic */ BinderProxy$ProxyMap$$ExternalSyntheticLambda1 INSTANCE = new BinderProxy$ProxyMap$$ExternalSyntheticLambda1();

    private /* synthetic */ BinderProxy$ProxyMap$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((Integer) ((Map.Entry) obj2).getValue()).compareTo((Integer) ((Map.Entry) obj).getValue());
        return compareTo;
    }
}
