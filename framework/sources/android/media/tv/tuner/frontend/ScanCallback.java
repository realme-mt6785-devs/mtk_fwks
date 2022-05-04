package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
@SystemApi
/* loaded from: classes2.dex */
public interface ScanCallback {
    void onAnalogSifStandardReported(int i);

    void onAtsc3PlpInfosReported(Atsc3PlpInfo[] atsc3PlpInfoArr);

    void onDvbsStandardReported(int i);

    void onDvbtStandardReported(int i);

    void onFrequenciesReported(int[] iArr);

    void onGroupIdsReported(int[] iArr);

    void onHierarchyReported(int i);

    void onInputStreamIdsReported(int[] iArr);

    void onLocked();

    void onPlpIdsReported(int[] iArr);

    void onProgress(int i);

    void onScanStopped();

    void onSignalTypeReported(int i);

    void onSymbolRatesReported(int[] iArr);

    default void onModulationReported(int modulation) {
    }

    default void onPriorityReported(boolean isHighPriority) {
    }

    default void onDvbcAnnexReported(int dvbcAnnex) {
    }
}
