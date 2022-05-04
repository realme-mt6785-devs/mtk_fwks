package com.android.internal.content.om;

import android.content.pm.PackagePartitions;
import android.content.pm.parsing.ParsingPackageRead;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.content.om.OverlayConfigParser;
import com.android.internal.content.om.OverlayScanner;
import com.android.internal.util.Preconditions;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
/* loaded from: classes4.dex */
public class OverlayConfig {
    public static final int DEFAULT_PRIORITY = Integer.MAX_VALUE;
    static final String TAG = "OverlayConfig";
    private static OverlayConfig sInstance;
    private static final Comparator<OverlayConfigParser.ParsedConfiguration> sStaticOverlayComparator = OverlayConfig$$ExternalSyntheticLambda0.INSTANCE;
    private final ArrayMap<String, Configuration> mConfigurations = new ArrayMap<>();

    /* loaded from: classes4.dex */
    public interface PackageProvider {
        void forEachPackage(BiConsumer<ParsingPackageRead, Boolean> biConsumer);
    }

    private static native String[] createIdmap(String str, String[] strArr, String[] strArr2, boolean z);

    /* loaded from: classes4.dex */
    public static final class Configuration {
        public final int configIndex;
        public final OverlayConfigParser.ParsedConfiguration parsedConfig;

        public Configuration(OverlayConfigParser.ParsedConfiguration parsedConfig, int configIndex) {
            this.parsedConfig = parsedConfig;
            this.configIndex = configIndex;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(OverlayConfigParser.ParsedConfiguration c1, OverlayConfigParser.ParsedConfiguration c2) {
        OverlayScanner.ParsedOverlayInfo o1 = c1.parsedInfo;
        OverlayScanner.ParsedOverlayInfo o2 = c2.parsedInfo;
        Preconditions.checkArgument(o1.isStatic && o2.isStatic, "attempted to sort non-static overlay");
        if (!o1.targetPackageName.equals(o2.targetPackageName)) {
            return o1.targetPackageName.compareTo(o2.targetPackageName);
        }
        int comparedPriority = o1.priority - o2.priority;
        return comparedPriority == 0 ? o1.path.compareTo(o2.path) : comparedPriority;
    }

    public OverlayConfig(final File rootDirectory, Supplier<OverlayScanner> scannerFactory, PackageProvider packageProvider) {
        ArrayList<OverlayConfigParser.OverlayPartition> partitions;
        ArrayList<OverlayConfigParser.OverlayPartition> partitions2;
        ArrayList<OverlayScanner.ParsedOverlayInfo> partitionOverlayInfos;
        ArrayList<OverlayScanner.ParsedOverlayInfo> packageManagerOverlayInfos;
        ArrayList<OverlayConfigParser.OverlayPartition> partitions3;
        int i = 1;
        Preconditions.checkArgument((scannerFactory == null) != (packageProvider == null), "scannerFactory and packageProvider cannot be both null or both non-null");
        if (rootDirectory == null) {
            partitions = new ArrayList<>(PackagePartitions.getOrderedPartitions(OverlayConfig$$ExternalSyntheticLambda3.INSTANCE));
        } else {
            partitions = new ArrayList<>(PackagePartitions.getOrderedPartitions(new Function() { // from class: com.android.internal.content.om.OverlayConfig$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return OverlayConfig.lambda$new$1(rootDirectory, (PackagePartitions.SystemPartition) obj);
                }
            }));
        }
        boolean foundConfigFile = false;
        ArrayList<OverlayScanner.ParsedOverlayInfo> packageManagerOverlayInfos2 = null;
        ArrayList<OverlayConfigParser.ParsedConfiguration> overlays = new ArrayList<>();
        int i2 = 0;
        int n = partitions.size();
        while (i2 < n) {
            OverlayConfigParser.OverlayPartition partition = partitions.get(i2);
            OverlayScanner scanner = scannerFactory == null ? null : scannerFactory.get();
            ArrayList<OverlayConfigParser.ParsedConfiguration> partitionOverlays = OverlayConfigParser.getConfigurations(partition, scanner);
            if (partitionOverlays != null) {
                foundConfigFile = true;
                overlays.addAll(partitionOverlays);
                partitions2 = partitions;
            } else {
                if (scannerFactory != null) {
                    partitionOverlayInfos = new ArrayList<>(scanner.getAllParsedInfos());
                } else {
                    packageManagerOverlayInfos2 = packageManagerOverlayInfos2 == null ? getOverlayPackageInfos(packageProvider) : packageManagerOverlayInfos2;
                    partitionOverlayInfos = new ArrayList<>(packageManagerOverlayInfos2);
                    for (int j = partitionOverlayInfos.size() - i; j >= 0; j--) {
                        if (!partition.containsFile(partitionOverlayInfos.get(j).path)) {
                            partitionOverlayInfos.remove(j);
                        }
                    }
                }
                ArrayList<OverlayConfigParser.ParsedConfiguration> partitionConfigs = new ArrayList<>();
                int j2 = 0;
                int m = partitionOverlayInfos.size();
                while (j2 < m) {
                    OverlayScanner.ParsedOverlayInfo p = partitionOverlayInfos.get(j2);
                    if (p.isStatic) {
                        partitions3 = partitions;
                        packageManagerOverlayInfos = packageManagerOverlayInfos2;
                        partitionConfigs.add(new OverlayConfigParser.ParsedConfiguration(p.packageName, true, false, partition.policy, p));
                    } else {
                        partitions3 = partitions;
                        packageManagerOverlayInfos = packageManagerOverlayInfos2;
                    }
                    j2++;
                    partitions = partitions3;
                    packageManagerOverlayInfos2 = packageManagerOverlayInfos;
                }
                partitions2 = partitions;
                partitionConfigs.sort(sStaticOverlayComparator);
                overlays.addAll(partitionConfigs);
            }
            i2++;
            partitions = partitions2;
            i = 1;
        }
        if (!foundConfigFile) {
            overlays.sort(sStaticOverlayComparator);
        }
        int n2 = overlays.size();
        for (int i3 = 0; i3 < n2; i3++) {
            OverlayConfigParser.ParsedConfiguration config = overlays.get(i3);
            this.mConfigurations.put(config.packageName, new Configuration(config, i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ OverlayConfigParser.OverlayPartition lambda$new$1(File rootDirectory, PackagePartitions.SystemPartition p) {
        return new OverlayConfigParser.OverlayPartition(new File(rootDirectory, p.getNonConicalFolder().getPath()), p);
    }

    public static OverlayConfig getZygoteInstance() {
        Trace.traceBegin(67108864L, "OverlayConfig#getZygoteInstance");
        try {
            return new OverlayConfig(null, OverlayConfig$$ExternalSyntheticLambda4.INSTANCE, null);
        } finally {
            Trace.traceEnd(67108864L);
        }
    }

    /* JADX WARN: Finally extract failed */
    public static OverlayConfig initializeSystemInstance(PackageProvider packageProvider) {
        Trace.traceBegin(67108864L, "OverlayConfig#initializeSystemInstance");
        try {
            sInstance = new OverlayConfig(null, null, packageProvider);
            Trace.traceEnd(67108864L);
            return sInstance;
        } catch (Throwable th) {
            Trace.traceEnd(67108864L);
            throw th;
        }
    }

    public static OverlayConfig getSystemInstance() {
        OverlayConfig overlayConfig = sInstance;
        if (overlayConfig != null) {
            return overlayConfig;
        }
        throw new IllegalStateException("System instance not initialized");
    }

    public Configuration getConfiguration(String packageName) {
        return this.mConfigurations.get(packageName);
    }

    public boolean isEnabled(String packageName) {
        Configuration config = this.mConfigurations.get(packageName);
        if (config == null) {
            return false;
        }
        return config.parsedConfig.enabled;
    }

    public boolean isMutable(String packageName) {
        Configuration config = this.mConfigurations.get(packageName);
        if (config == null) {
            return true;
        }
        return config.parsedConfig.mutable;
    }

    public int getPriority(String packageName) {
        Configuration config = this.mConfigurations.get(packageName);
        if (config == null) {
            return Integer.MAX_VALUE;
        }
        return config.configIndex;
    }

    private ArrayList<Configuration> getSortedOverlays() {
        ArrayList<Configuration> sortedOverlays = new ArrayList<>();
        int n = this.mConfigurations.size();
        for (int i = 0; i < n; i++) {
            sortedOverlays.add(this.mConfigurations.valueAt(i));
        }
        sortedOverlays.sort(Comparator.comparingInt(OverlayConfig$$ExternalSyntheticLambda5.INSTANCE));
        return sortedOverlays;
    }

    private static ArrayList<OverlayScanner.ParsedOverlayInfo> getOverlayPackageInfos(PackageProvider packageManager) {
        final ArrayList<OverlayScanner.ParsedOverlayInfo> overlays = new ArrayList<>();
        packageManager.forEachPackage(new BiConsumer() { // from class: com.android.internal.content.om.OverlayConfig$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                OverlayConfig.lambda$getOverlayPackageInfos$3(overlays, (ParsingPackageRead) obj, (Boolean) obj2);
            }
        });
        return overlays;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getOverlayPackageInfos$3(ArrayList overlays, ParsingPackageRead p, Boolean isSystem) {
        if (p.getOverlayTarget() != null && isSystem.booleanValue()) {
            overlays.add(new OverlayScanner.ParsedOverlayInfo(p.getPackageName(), p.getOverlayTarget(), p.getTargetSdkVersion(), p.isOverlayIsStatic(), p.getOverlayPriority(), new File(p.getBaseApkPath())));
        }
    }

    /* loaded from: classes4.dex */
    public static class IdmapInvocation {
        public final boolean enforceOverlayable;
        public final ArrayList<String> overlayPaths = new ArrayList<>();
        public final String policy;

        IdmapInvocation(boolean enforceOverlayable, String policy) {
            this.enforceOverlayable = enforceOverlayable;
            this.policy = policy;
        }

        public String toString() {
            return getClass().getSimpleName() + String.format("{enforceOverlayable=%s, policy=%s, overlayPaths=[%s]}", Boolean.valueOf(this.enforceOverlayable), this.policy, String.join(", ", this.overlayPaths));
        }
    }

    public ArrayList<IdmapInvocation> getImmutableFrameworkOverlayIdmapInvocations() {
        ArrayList<IdmapInvocation> idmapInvocations = new ArrayList<>();
        ArrayList<Configuration> sortedConfigs = getSortedOverlays();
        int n = sortedConfigs.size();
        for (int i = 0; i < n; i++) {
            Configuration overlay = sortedConfigs.get(i);
            if (!overlay.parsedConfig.mutable && overlay.parsedConfig.enabled && "android".equals(overlay.parsedConfig.parsedInfo.targetPackageName)) {
                boolean enforceOverlayable = overlay.parsedConfig.parsedInfo.targetSdkVersion >= 29;
                IdmapInvocation invocation = null;
                if (!idmapInvocations.isEmpty()) {
                    IdmapInvocation last = idmapInvocations.get(idmapInvocations.size() - 1);
                    if (last.enforceOverlayable == enforceOverlayable && last.policy.equals(overlay.parsedConfig.policy)) {
                        invocation = last;
                    }
                }
                if (invocation == null) {
                    invocation = new IdmapInvocation(enforceOverlayable, overlay.parsedConfig.policy);
                    idmapInvocations.add(invocation);
                }
                invocation.overlayPaths.add(overlay.parsedConfig.parsedInfo.path.getAbsolutePath());
            }
        }
        return idmapInvocations;
    }

    public String[] createImmutableFrameworkIdmapsInZygote() {
        ArrayList<String> idmapPaths = new ArrayList<>();
        ArrayList<IdmapInvocation> idmapInvocations = getImmutableFrameworkOverlayIdmapInvocations();
        int n = idmapInvocations.size();
        for (int i = 0; i < n; i++) {
            IdmapInvocation invocation = idmapInvocations.get(i);
            String[] idmaps = createIdmap("/system/framework/framework-res.apk", (String[]) invocation.overlayPaths.toArray(new String[0]), new String[]{"public", invocation.policy}, invocation.enforceOverlayable);
            if (idmaps == null) {
                Log.w(TAG, "'idmap2 create-multiple' failed: no mutable=\"false\" overlays targeting \"android\" will be loaded");
                return new String[0];
            }
            idmapPaths.addAll(Arrays.asList(idmaps));
        }
        return (String[]) idmapPaths.toArray(new String[0]);
    }

    public ArrayList<IdmapInvocation> getImmutableOplusFrameworkOverlayIdmapInvocations() {
        ArrayList<IdmapInvocation> idmapInvocations = new ArrayList<>();
        ArrayList<Configuration> sortedConfigs = getSortedOverlays();
        int n = sortedConfigs.size();
        for (int i = 0; i < n; i++) {
            Configuration overlay = sortedConfigs.get(i);
            if (!overlay.parsedConfig.mutable && overlay.parsedConfig.enabled && "oplus".equals(overlay.parsedConfig.parsedInfo.targetPackageName)) {
                boolean enforceOverlayable = overlay.parsedConfig.parsedInfo.targetSdkVersion >= 29;
                IdmapInvocation invocation = null;
                if (!idmapInvocations.isEmpty()) {
                    IdmapInvocation last = idmapInvocations.get(idmapInvocations.size() - 1);
                    if (last.enforceOverlayable == enforceOverlayable && last.policy.equals(overlay.parsedConfig.policy)) {
                        invocation = last;
                    }
                }
                if (invocation == null) {
                    invocation = new IdmapInvocation(enforceOverlayable, overlay.parsedConfig.policy);
                    idmapInvocations.add(invocation);
                }
                invocation.overlayPaths.add(overlay.parsedConfig.parsedInfo.path.getAbsolutePath());
            }
        }
        return idmapInvocations;
    }

    public String[] createImmutableOplusFrameworkIdmapsInZygote() {
        ArrayList<String> idmapPaths = new ArrayList<>();
        ArrayList<IdmapInvocation> idmapInvocations = getImmutableOplusFrameworkOverlayIdmapInvocations();
        int n = idmapInvocations.size();
        for (int i = 0; i < n; i++) {
            IdmapInvocation invocation = idmapInvocations.get(i);
            String[] idmaps = createIdmap("/system_ext/framework/oplus-framework-res.apk", (String[]) invocation.overlayPaths.toArray(new String[0]), new String[]{"public", invocation.policy}, invocation.enforceOverlayable);
            if (idmaps == null) {
                Log.w(TAG, "'idmap2 create-multiple' failed: no mutable=\"false\" overlays targeting \"oplus\" will be loaded");
                return new String[0];
            }
            idmapPaths.addAll(Arrays.asList(idmaps));
        }
        return (String[]) idmapPaths.toArray(new String[0]);
    }
}
