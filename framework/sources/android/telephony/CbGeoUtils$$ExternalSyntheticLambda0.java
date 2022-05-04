package android.telephony;

import android.telephony.CbGeoUtils;
import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class CbGeoUtils$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ CbGeoUtils$$ExternalSyntheticLambda0 INSTANCE = new CbGeoUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ CbGeoUtils$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String encodeGeometryToString;
        encodeGeometryToString = CbGeoUtils.encodeGeometryToString((CbGeoUtils.Geometry) obj);
        return encodeGeometryToString;
    }
}
