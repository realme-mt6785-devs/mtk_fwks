package android.view;

import android.view.inspector.IntFlagMapping;
import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class View$InspectionCompanion$$ExternalSyntheticLambda1 implements IntFunction {
    public final /* synthetic */ IntFlagMapping f$0;

    public /* synthetic */ View$InspectionCompanion$$ExternalSyntheticLambda1(IntFlagMapping intFlagMapping) {
        this.f$0 = intFlagMapping;
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        return this.f$0.get(i);
    }
}
