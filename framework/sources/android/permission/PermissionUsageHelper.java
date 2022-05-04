package android.permission;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.icu.text.ListFormatter;
import android.media.AudioManager;
import android.os.Process;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes2.dex */
public class PermissionUsageHelper implements AppOpsManager.OnOpActiveChangedListener, AppOpsManager.OnOpStartedListener {
    private static final long DEFAULT_RECENT_TIME_MS = 15000;
    private static final long DEFAULT_RUNNING_TIME_MS = 5000;
    private static final String PROPERTY_CAMERA_MIC_ICONS_ENABLED = "camera_mic_icons_enabled";
    private static final String PROPERTY_LOCATION_INDICATORS_ENABLED = "location_indicators_enabled";
    private static final String PROPERTY_PERMISSIONS_HUB_2_ENABLED = "permissions_hub_2_enabled";
    private static final String RECENT_ACCESS_TIME_MS = "recent_access_time_ms";
    private static final String RUNNING_ACCESS_TIME_MS = "running_access_time_ms";
    private static final String SYSTEM_PKG = "android";
    private AppOpsManager mAppOpsManager;
    private ArrayMap<Integer, ArrayList<AccessChainLink>> mAttributionChains = new ArrayMap<>();
    private Context mContext;
    private PackageManager mPkgManager;
    private ArrayMap<UserHandle, Context> mUserContexts;
    private static final List<String> LOCATION_OPS = List.of(AppOpsManager.OPSTR_COARSE_LOCATION, AppOpsManager.OPSTR_FINE_LOCATION);
    private static final List<String> MIC_OPS = List.of(AppOpsManager.OPSTR_PHONE_CALL_MICROPHONE, AppOpsManager.OPSTR_RECORD_AUDIO);
    private static final List<String> CAMERA_OPS = List.of(AppOpsManager.OPSTR_PHONE_CALL_CAMERA, AppOpsManager.OPSTR_CAMERA);

    private static boolean shouldShowPermissionsHub() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_PRIVACY, "permissions_hub_2_enabled", false);
    }

    private static boolean shouldShowIndicators() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_PRIVACY, "camera_mic_icons_enabled", true) || shouldShowPermissionsHub();
    }

    private static boolean shouldShowLocationIndicator() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_PRIVACY, "location_indicators_enabled", false);
    }

    private static long getRecentThreshold(Long now) {
        return now.longValue() - DeviceConfig.getLong(DeviceConfig.NAMESPACE_PRIVACY, RECENT_ACCESS_TIME_MS, DEFAULT_RECENT_TIME_MS);
    }

    private static long getRunningThreshold(Long now) {
        return now.longValue() - DeviceConfig.getLong(DeviceConfig.NAMESPACE_PRIVACY, RUNNING_ACCESS_TIME_MS, 5000L);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String getGroupForOp(String op) {
        char c;
        switch (op.hashCode()) {
            case -1671423430:
                if (op.equals(AppOpsManager.OPSTR_COARSE_LOCATION)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -210165041:
                if (op.equals(AppOpsManager.OPSTR_FINE_LOCATION)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 148526607:
                if (op.equals(AppOpsManager.OPSTR_PHONE_CALL_MICROPHONE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1191287187:
                if (op.equals(AppOpsManager.OPSTR_RECORD_AUDIO)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1390525066:
                if (op.equals(AppOpsManager.OPSTR_PHONE_CALL_CAMERA)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1528082064:
                if (op.equals(AppOpsManager.OPSTR_CAMERA)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return Manifest.permission_group.MICROPHONE;
            case 1:
                return Manifest.permission_group.CAMERA;
            case 2:
            case 3:
                return op;
            case 4:
            case 5:
                return Manifest.permission_group.LOCATION;
            default:
                throw new IllegalArgumentException("Unknown app op: " + op);
        }
    }

    public PermissionUsageHelper(Context context) {
        this.mContext = context;
        this.mPkgManager = context.getPackageManager();
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        ArrayMap<UserHandle, Context> arrayMap = new ArrayMap<>();
        this.mUserContexts = arrayMap;
        arrayMap.put(Process.myUserHandle(), this.mContext);
        String[] opStrs = {AppOpsManager.OPSTR_CAMERA, AppOpsManager.OPSTR_RECORD_AUDIO};
        this.mAppOpsManager.startWatchingActive(opStrs, context.getMainExecutor(), this);
        int[] ops = {26, 27};
        this.mAppOpsManager.startWatchingStarted(ops, this);
    }

    private Context getUserContext(UserHandle user) {
        if (!this.mUserContexts.containsKey(user)) {
            this.mUserContexts.put(user, this.mContext.createContextAsUser(user, 0));
        }
        return this.mUserContexts.get(user);
    }

    public void tearDown() {
        this.mAppOpsManager.stopWatchingActive(this);
        this.mAppOpsManager.stopWatchingStarted(this);
    }

    @Override // android.app.AppOpsManager.OnOpActiveChangedListener
    public void onOpActiveChanged(String op, int uid, String packageName, boolean active) {
    }

    @Override // android.app.AppOpsManager.OnOpActiveChangedListener
    public void onOpActiveChanged(String op, int uid, String packageName, String attributionTag, boolean active, int attributionFlags, int attributionChainId) {
        if (!active) {
            this.mAttributionChains.remove(Integer.valueOf(attributionChainId));
            int numChains = this.mAttributionChains.size();
            ArrayList<Integer> toRemove = new ArrayList<>();
            for (int i = 0; i < numChains; i++) {
                int chainId = this.mAttributionChains.keyAt(i).intValue();
                ArrayList<AccessChainLink> chain = this.mAttributionChains.valueAt(i);
                int chainSize = chain.size();
                int j = 0;
                while (true) {
                    if (j < chainSize) {
                        AccessChainLink link = chain.get(j);
                        if (link.packageAndOpEquals(op, packageName, attributionTag, uid)) {
                            toRemove.add(Integer.valueOf(chainId));
                            break;
                        }
                        j++;
                    }
                }
            }
            this.mAttributionChains.removeAll(toRemove);
        }
    }

    @Override // android.app.AppOpsManager.OnOpStartedListener
    public void onOpStarted(int op, int uid, String packageName, String attributionTag, int flags, int result) {
    }

    @Override // android.app.AppOpsManager.OnOpStartedListener
    public void onOpStarted(int op, int uid, String packageName, String attributionTag, int flags, int result, int startedType, int attributionFlags, int attributionChainId) {
        if (startedType != 0 && attributionChainId != -1 && attributionFlags != 0 && (attributionFlags & 8) != 0) {
            addLinkToChainIfNotPresent(AppOpsManager.opToPublicName(op), packageName, uid, attributionTag, attributionFlags, attributionChainId);
        }
    }

    private void addLinkToChainIfNotPresent(String op, String packageName, int uid, String attributionTag, int attributionFlags, int attributionChainId) {
        ArrayList<AccessChainLink> currentChain = this.mAttributionChains.computeIfAbsent(Integer.valueOf(attributionChainId), PermissionUsageHelper$$ExternalSyntheticLambda0.INSTANCE);
        AccessChainLink link = new AccessChainLink(op, packageName, attributionTag, uid, attributionFlags);
        if (!currentChain.contains(link)) {
            int currSize = currentChain.size();
            if (currSize == 0 || link.isEnd() || !currentChain.get(currSize - 1).isEnd()) {
                currentChain.add(link);
            } else if (link.isStart()) {
                currentChain.add(0, link);
            } else if (currentChain.get(currentChain.size() - 1).isEnd()) {
                currentChain.add(currSize - 1, link);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArrayList lambda$addLinkToChainIfNotPresent$0(Integer k) {
        return new ArrayList();
    }

    public List<PermGroupUsage> getOpUsageData(boolean isMicMuted) {
        PermissionUsageHelper permissionUsageHelper = this;
        List<PermGroupUsage> usages = new ArrayList<>();
        if (!shouldShowIndicators()) {
            return usages;
        }
        List<String> ops = new ArrayList<>(CAMERA_OPS);
        if (shouldShowLocationIndicator()) {
            ops.addAll(LOCATION_OPS);
        }
        if (!isMicMuted) {
            ops.addAll(MIC_OPS);
        }
        Map<String, List<OpUsage>> rawUsages = permissionUsageHelper.getOpUsages(ops);
        ArrayList<String> usedPermGroups = new ArrayList<>(rawUsages.keySet());
        AudioManager audioManager = (AudioManager) permissionUsageHelper.mContext.getSystemService(AudioManager.class);
        boolean hasPhoneCall = usedPermGroups.contains(AppOpsManager.OPSTR_PHONE_CALL_CAMERA) || usedPermGroups.contains(AppOpsManager.OPSTR_PHONE_CALL_MICROPHONE);
        if (hasPhoneCall && usedPermGroups.contains(Manifest.permission_group.MICROPHONE) && audioManager.getMode() == 3) {
            TelephonyManager telephonyManager = (TelephonyManager) permissionUsageHelper.mContext.getSystemService(TelephonyManager.class);
            List<OpUsage> permUsages = rawUsages.get(Manifest.permission_group.MICROPHONE);
            for (int usageNum = 0; usageNum < permUsages.size(); usageNum++) {
                if (telephonyManager.checkCarrierPrivilegesForPackage(permUsages.get(usageNum).packageName) == 1) {
                    usedPermGroups.remove(AppOpsManager.OPSTR_PHONE_CALL_CAMERA);
                    usedPermGroups.remove(AppOpsManager.OPSTR_PHONE_CALL_MICROPHONE);
                }
            }
        }
        int permGroupNum = 0;
        while (permGroupNum < usedPermGroups.size()) {
            boolean isPhone = false;
            String permGroup = usedPermGroups.get(permGroupNum);
            ArrayMap<OpUsage, CharSequence> usagesWithLabels = permissionUsageHelper.getUniqueUsagesWithLabels(rawUsages.get(permGroup));
            if (permGroup.equals(AppOpsManager.OPSTR_PHONE_CALL_MICROPHONE)) {
                isPhone = true;
                permGroup = Manifest.permission_group.MICROPHONE;
            } else if (permGroup.equals(AppOpsManager.OPSTR_PHONE_CALL_CAMERA)) {
                isPhone = true;
                permGroup = Manifest.permission_group.CAMERA;
            }
            int usageNum2 = 0;
            while (usageNum2 < usagesWithLabels.size()) {
                OpUsage usage = usagesWithLabels.keyAt(usageNum2);
                usages.add(new PermGroupUsage(usage.packageName, usage.uid, permGroup, usage.lastAccessTime, usage.isRunning, isPhone, usagesWithLabels.valueAt(usageNum2)));
                usageNum2++;
                ops = ops;
                rawUsages = rawUsages;
                usedPermGroups = usedPermGroups;
                audioManager = audioManager;
            }
            permGroupNum++;
            permissionUsageHelper = this;
        }
        return usages;
    }

    private Map<String, List<OpUsage>> getOpUsages(List<String> opNames) {
        long lastAccessTime;
        int numOpEntries;
        int opEntryNum;
        AppOpsManager.OpEntry opEntry;
        long now;
        int opFlags;
        OpUsage proxyUsage;
        try {
            List<AppOpsManager.PackageOps> ops = this.mAppOpsManager.getPackagesForOps((String[]) opNames.toArray(new String[opNames.size()]));
            long now2 = System.currentTimeMillis();
            long recentThreshold = getRecentThreshold(Long.valueOf(now2));
            long runningThreshold = getRunningThreshold(Long.valueOf(now2));
            int opFlags2 = 13;
            Map<String, Map<Integer, OpUsage>> usages = new ArrayMap<>();
            int numPkgOps = ops.size();
            for (int pkgOpNum = 0; pkgOpNum < numPkgOps; pkgOpNum++) {
                AppOpsManager.PackageOps pkgOps = ops.get(pkgOpNum);
                int uid = pkgOps.getUid();
                UserHandle.getUserHandleForUid(uid);
                String packageName = pkgOps.getPackageName();
                int numOpEntries2 = pkgOps.getOps().size();
                int opEntryNum2 = 0;
                while (opEntryNum2 < numOpEntries2) {
                    AppOpsManager.OpEntry opEntry2 = pkgOps.getOps().get(opEntryNum2);
                    String op = opEntry2.getOpStr();
                    List<String> attributionTags = new ArrayList<>(opEntry2.getAttributedOpEntries().keySet());
                    int numPkgOps2 = 0;
                    for (int numAttrEntries = opEntry2.getAttributedOpEntries().size(); numPkgOps2 < numAttrEntries; numAttrEntries = numAttrEntries) {
                        String attributionTag = attributionTags.get(numPkgOps2);
                        AppOpsManager.AttributedOpEntry attrOpEntry = opEntry2.getAttributedOpEntries().get(attributionTag);
                        long lastAccessTime2 = attrOpEntry.getLastAccessTime(opFlags2);
                        if (attrOpEntry.isRunning()) {
                            lastAccessTime = now2;
                        } else {
                            lastAccessTime = lastAccessTime2;
                        }
                        if (lastAccessTime >= recentThreshold || attrOpEntry.isRunning()) {
                            boolean isRunning = attrOpEntry.isRunning() || lastAccessTime >= runningThreshold;
                            AppOpsManager.OpEventProxyInfo proxy = attrOpEntry.getLastProxyInfo(opFlags2);
                            if (proxy == null || proxy.getPackageName() == null) {
                                proxyUsage = null;
                            } else {
                                OpUsage proxyUsage2 = new OpUsage(proxy.getPackageName(), proxy.getAttributionTag(), op, proxy.getUid(), lastAccessTime, isRunning, null);
                                proxyUsage = proxyUsage2;
                            }
                            opEntry = opEntry2;
                            String permGroupName = getGroupForOp(op);
                            opEntryNum = opEntryNum2;
                            numOpEntries = numOpEntries2;
                            OpUsage usage = new OpUsage(packageName, attributionTag, op, uid, lastAccessTime, isRunning, proxyUsage);
                            Integer packageAttr = Integer.valueOf(usage.getPackageIdHash());
                            if (!usages.containsKey(permGroupName)) {
                                ArrayMap<Integer, OpUsage> map = new ArrayMap<>();
                                map.put(packageAttr, usage);
                                usages.put(permGroupName, map);
                                opFlags = opFlags2;
                                now = now2;
                            } else {
                                Map<Integer, OpUsage> permGroupUsages = usages.get(permGroupName);
                                if (!permGroupUsages.containsKey(packageAttr)) {
                                    permGroupUsages.put(packageAttr, usage);
                                    opFlags = opFlags2;
                                    now = now2;
                                } else {
                                    opFlags = opFlags2;
                                    now = now2;
                                    if (usage.lastAccessTime > permGroupUsages.get(packageAttr).lastAccessTime) {
                                        permGroupUsages.put(packageAttr, usage);
                                    }
                                }
                            }
                        } else {
                            opEntry = opEntry2;
                            opFlags = opFlags2;
                            now = now2;
                            opEntryNum = opEntryNum2;
                            numOpEntries = numOpEntries2;
                        }
                        numPkgOps2++;
                        opFlags2 = opFlags;
                        now2 = now;
                        opEntry2 = opEntry;
                        opEntryNum2 = opEntryNum;
                        numOpEntries2 = numOpEntries;
                        attributionTags = attributionTags;
                    }
                    opEntryNum2++;
                    ops = ops;
                    numPkgOps = numPkgOps;
                }
            }
            Map<String, List<OpUsage>> flattenedUsages = new ArrayMap<>();
            List<String> permGroups = new ArrayList<>(usages.keySet());
            for (int i = 0; i < permGroups.size(); i++) {
                String permGroupName2 = permGroups.get(i);
                flattenedUsages.put(permGroupName2, new ArrayList<>(usages.get(permGroupName2).values()));
            }
            return flattenedUsages;
        } catch (NullPointerException e) {
            return Collections.emptyMap();
        }
    }

    private CharSequence formatLabelList(List<CharSequence> labels) {
        return ListFormatter.getInstance().format(labels);
    }

    private ArrayMap<OpUsage, CharSequence> getUniqueUsagesWithLabels(List<OpUsage> usages) {
        ArrayMap<OpUsage, CharSequence> usagesAndLabels;
        OpUsage proxy;
        OpUsage currentUsage;
        OpUsage currentUsage2;
        ArrayMap<OpUsage, CharSequence> usagesAndLabels2;
        PermissionUsageHelper permissionUsageHelper = this;
        List<OpUsage> list = usages;
        ArrayMap<OpUsage, CharSequence> usagesAndLabels3 = new ArrayMap<>();
        if (list == null) {
            return usagesAndLabels3;
        }
        if (usages.isEmpty()) {
            return usagesAndLabels3;
        }
        ArrayMap<Integer, OpUsage> allUsages = new ArrayMap<>();
        ArrayMap<Integer, OpUsage> mostRecentUsages = new ArrayMap<>();
        ArraySet<Integer> proxyPackages = new ArraySet<>();
        ArrayMap<OpUsage, ArrayList<CharSequence>> proxyLabels = new ArrayMap<>();
        ArrayMap<Integer, OpUsage> proxies = new ArrayMap<>();
        for (int i = 0; i < usages.size(); i++) {
            OpUsage usage = list.get(i);
            allUsages.put(Integer.valueOf(usage.getPackageIdHash()), usage);
            if (usage.proxy != null) {
                proxies.put(Integer.valueOf(usage.proxy.getPackageIdHash()), usage);
            }
        }
        int usageNum = 0;
        while (usageNum < usages.size()) {
            OpUsage usage2 = list.get(usageNum);
            if (usage2 == null) {
                usagesAndLabels2 = usagesAndLabels3;
            } else {
                int usageAttr = usage2.getPackageIdHash();
                if (!proxies.containsKey(Integer.valueOf(usageAttr)) && usage2.proxy != null && !usage2.op.equals(AppOpsManager.OPSTR_RECORD_AUDIO)) {
                    proxyLabels.put(usage2, new ArrayList<>());
                    proxyPackages.add(Integer.valueOf(usage2.getPackageIdHash()));
                }
                int usageId = usage2.getPackageIdHash();
                OpUsage lastMostRecent = mostRecentUsages.get(Integer.valueOf(usageId));
                if (permissionUsageHelper.shouldShowPackage(usage2.packageName)) {
                    if (lastMostRecent != null) {
                        usagesAndLabels2 = usagesAndLabels3;
                        if (usage2.lastAccessTime <= lastMostRecent.lastAccessTime) {
                        }
                    } else {
                        usagesAndLabels2 = usagesAndLabels3;
                    }
                    mostRecentUsages.put(Integer.valueOf(usageId), usage2);
                } else {
                    usagesAndLabels2 = usagesAndLabels3;
                }
            }
            usageNum++;
            list = usages;
            usagesAndLabels3 = usagesAndLabels2;
        }
        ArrayMap<OpUsage, CharSequence> usagesAndLabels4 = usagesAndLabels3;
        int numStart = 0;
        while (numStart < proxyLabels.size()) {
            OpUsage start = proxyLabels.keyAt(numStart);
            mostRecentUsages.remove(Integer.valueOf(start.getPackageIdHash()));
            OpUsage currentUsage3 = proxyLabels.keyAt(numStart);
            ArrayList<CharSequence> proxyLabelList = proxyLabels.get(currentUsage3);
            if (currentUsage3 == null) {
                usagesAndLabels = usagesAndLabels4;
            } else if (proxyLabelList == null) {
                usagesAndLabels = usagesAndLabels4;
            } else {
                int iterNum = 0;
                int maxUsages = allUsages.size();
                while (currentUsage3.proxy != null) {
                    if (!allUsages.containsKey(Integer.valueOf(currentUsage3.proxy.getPackageIdHash()))) {
                        proxy = currentUsage3.proxy;
                        if (!permissionUsageHelper.shouldShowPackage(proxy.packageName)) {
                            break;
                        }
                        maxUsages++;
                    } else {
                        proxy = allUsages.get(Integer.valueOf(currentUsage3.proxy.getPackageIdHash()));
                    }
                    if (proxy == null || iterNum == maxUsages) {
                        currentUsage = proxy;
                    } else if (proxy.getPackageIdHash() == start.getPackageIdHash()) {
                        currentUsage = proxy;
                    } else {
                        proxyPackages.add(Integer.valueOf(proxy.getPackageIdHash()));
                        if (proxy.packageName.equals(start.packageName)) {
                            currentUsage2 = proxy;
                        } else if (permissionUsageHelper.shouldShowPackage(proxy.packageName)) {
                            try {
                                PackageManager userPkgManager = permissionUsageHelper.getUserContext(proxy.getUser()).getPackageManager();
                                currentUsage2 = proxy;
                                try {
                                    ApplicationInfo appInfo = userPkgManager.getApplicationInfo(proxy.packageName, 0);
                                    CharSequence appLabel = appInfo.loadLabel(userPkgManager);
                                    if (!proxyLabelList.contains(appLabel)) {
                                        proxyLabelList.add(appLabel);
                                    }
                                } catch (PackageManager.NameNotFoundException e) {
                                }
                            } catch (PackageManager.NameNotFoundException e2) {
                                currentUsage2 = proxy;
                            }
                        } else {
                            currentUsage2 = proxy;
                        }
                        iterNum++;
                        currentUsage3 = currentUsage2;
                    }
                }
                if (!start.op.equals(AppOpsManager.OPSTR_RECORD_AUDIO)) {
                    usagesAndLabels = usagesAndLabels4;
                    usagesAndLabels.put(start, proxyLabelList.isEmpty() ? null : permissionUsageHelper.formatLabelList(proxyLabelList));
                } else {
                    usagesAndLabels = usagesAndLabels4;
                }
            }
            numStart++;
            usagesAndLabels4 = usagesAndLabels;
        }
        int i2 = 0;
        while (i2 < permissionUsageHelper.mAttributionChains.size()) {
            List<AccessChainLink> usageList = permissionUsageHelper.mAttributionChains.valueAt(i2);
            int lastVisible = usageList.size() - 1;
            if (!usageList.isEmpty() && usageList.get(lastVisible).isEnd()) {
                if (usageList.get(0).isStart() && usageList.get(lastVisible).usage.op.equals(AppOpsManager.OPSTR_RECORD_AUDIO)) {
                    for (AccessChainLink link : usageList) {
                        proxyPackages.add(Integer.valueOf(link.usage.getPackageIdHash()));
                    }
                    AccessChainLink start2 = usageList.get(0);
                    AccessChainLink lastVisibleLink = usageList.get(lastVisible);
                    int lastVisible2 = lastVisible;
                    while (lastVisible2 > 0 && !permissionUsageHelper.shouldShowPackage(lastVisibleLink.usage.packageName)) {
                        lastVisible2--;
                        AccessChainLink lastVisibleLink2 = usageList.get(lastVisible2);
                        lastVisibleLink = lastVisibleLink2;
                    }
                    String proxyLabel = null;
                    if (!lastVisibleLink.usage.packageName.equals(start2.usage.packageName)) {
                        try {
                            PackageManager userPkgManager2 = permissionUsageHelper.getUserContext(lastVisibleLink.usage.getUser()).getPackageManager();
                            try {
                                ApplicationInfo appInfo2 = userPkgManager2.getApplicationInfo(lastVisibleLink.usage.packageName, 0);
                                proxyLabel = appInfo2.loadLabel(userPkgManager2).toString();
                            } catch (PackageManager.NameNotFoundException e3) {
                            }
                        } catch (PackageManager.NameNotFoundException e4) {
                        }
                    }
                    usagesAndLabels4.put(start2.usage, proxyLabel);
                }
            }
            i2++;
            permissionUsageHelper = this;
        }
        for (Integer num : mostRecentUsages.keySet()) {
            int packageHash = num.intValue();
            if (!proxyPackages.contains(Integer.valueOf(packageHash))) {
                usagesAndLabels4.put(mostRecentUsages.get(Integer.valueOf(packageHash)), null);
            }
        }
        return usagesAndLabels4;
    }

    private boolean shouldShowPackage(String packageName) {
        return PermissionManager.shouldShowPackageForIndicatorCached(this.mContext, packageName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class OpUsage {
        public final String attributionTag;
        public final boolean isRunning;
        public final long lastAccessTime;
        public final String op;
        public final String packageName;
        public final OpUsage proxy;
        public final int uid;

        OpUsage(String packageName, String attributionTag, String op, int uid, long lastAccessTime, boolean isRunning, OpUsage proxy) {
            this.packageName = packageName;
            this.attributionTag = attributionTag;
            this.op = op;
            this.uid = uid;
            this.lastAccessTime = lastAccessTime;
            this.isRunning = isRunning;
            this.proxy = proxy;
        }

        public UserHandle getUser() {
            return UserHandle.getUserHandleForUid(this.uid);
        }

        public int getPackageIdHash() {
            return Objects.hash(this.packageName, Integer.valueOf(this.uid));
        }

        public int hashCode() {
            return Objects.hash(this.packageName, this.attributionTag, this.op, Integer.valueOf(this.uid), Long.valueOf(this.lastAccessTime), Boolean.valueOf(this.isRunning));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof OpUsage)) {
                return false;
            }
            OpUsage other = (OpUsage) obj;
            return Objects.equals(this.packageName, other.packageName) && Objects.equals(this.attributionTag, other.attributionTag) && Objects.equals(this.op, other.op) && this.uid == other.uid && this.lastAccessTime == other.lastAccessTime && this.isRunning == other.isRunning;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AccessChainLink {
        public final int flags;
        public final OpUsage usage;

        AccessChainLink(String op, String packageName, String attributionTag, int uid, int flags) {
            this.usage = new OpUsage(packageName, attributionTag, op, uid, System.currentTimeMillis(), true, null);
            this.flags = flags;
        }

        public boolean isEnd() {
            return (this.flags & 1) != 0;
        }

        public boolean isStart() {
            return (this.flags & 4) != 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AccessChainLink)) {
                return false;
            }
            AccessChainLink other = (AccessChainLink) obj;
            return other.flags == this.flags && packageAndOpEquals(other.usage.op, other.usage.packageName, other.usage.attributionTag, other.usage.uid);
        }

        public boolean packageAndOpEquals(String op, String packageName, String attributionTag, int uid) {
            return Objects.equals(op, this.usage.op) && Objects.equals(packageName, this.usage.packageName) && Objects.equals(attributionTag, this.usage.attributionTag) && uid == this.usage.uid;
        }
    }
}
