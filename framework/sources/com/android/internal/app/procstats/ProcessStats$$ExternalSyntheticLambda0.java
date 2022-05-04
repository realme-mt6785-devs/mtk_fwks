package com.android.internal.app.procstats;

import com.android.internal.app.procstats.ProcessStats;
import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class ProcessStats$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ ProcessStats$$ExternalSyntheticLambda0 INSTANCE = new ProcessStats$$ExternalSyntheticLambda0();

    private /* synthetic */ ProcessStats$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ProcessStats.lambda$static$0((ProcessStats.AssociationDumpContainer) obj, (ProcessStats.AssociationDumpContainer) obj2);
    }
}
