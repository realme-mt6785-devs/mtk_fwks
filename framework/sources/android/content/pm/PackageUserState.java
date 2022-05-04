package android.content.pm;

import android.content.ComponentName;
import android.content.pm.overlay.OverlayPaths;
import android.content.pm.parsing.ParsingPackageRead;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.os.BaseBundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Pair;
import android.util.Slog;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes.dex */
public class PackageUserState {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "PackageUserState";
    private OverlayPaths cachedOverlayPaths;
    public int categoryHint;
    public long ceDataInode;
    private ArrayMap<ComponentName, Pair<String, Integer>> componentLabelIconOverrideMap;
    public ArraySet<String> disabledComponents;
    public int distractionFlags;
    public int enabled;
    public ArraySet<String> enabledComponents;
    public String harmfulAppWarning;
    public boolean hidden;
    public int installReason;
    public boolean installed;
    public boolean instantApp;
    public String lastDisableAppCaller;
    public IPackageUserStateExt mPackageUserStateExt;
    public boolean notLaunched;
    private OverlayPaths overlayPaths;
    private ArrayMap<String, OverlayPaths> sharedLibraryOverlayPaths;
    public String splashScreenTheme;
    public boolean stopped;
    public ArrayMap<String, SuspendParams> suspendParams;
    public boolean suspended;
    public int uninstallReason;
    public boolean virtualPreload;

    public PackageUserState() {
        this.categoryHint = -1;
        this.mPackageUserStateExt = (IPackageUserStateExt) ExtLoader.type(IPackageUserStateExt.class).base(this).create();
        this.installed = true;
        this.hidden = false;
        this.suspended = false;
        this.enabled = 0;
        this.installReason = 0;
        this.uninstallReason = 0;
    }

    public PackageUserState(PackageUserState o) {
        this.categoryHint = -1;
        this.mPackageUserStateExt = (IPackageUserStateExt) ExtLoader.type(IPackageUserStateExt.class).base(this).create();
        this.ceDataInode = o.ceDataInode;
        this.installed = o.installed;
        this.stopped = o.stopped;
        this.notLaunched = o.notLaunched;
        this.hidden = o.hidden;
        this.distractionFlags = o.distractionFlags;
        this.suspended = o.suspended;
        this.suspendParams = new ArrayMap<>(o.suspendParams);
        this.instantApp = o.instantApp;
        this.virtualPreload = o.virtualPreload;
        this.enabled = o.enabled;
        this.lastDisableAppCaller = o.lastDisableAppCaller;
        this.categoryHint = o.categoryHint;
        this.installReason = o.installReason;
        this.uninstallReason = o.uninstallReason;
        this.disabledComponents = ArrayUtils.cloneOrNull(o.disabledComponents);
        this.enabledComponents = ArrayUtils.cloneOrNull(o.enabledComponents);
        this.overlayPaths = o.overlayPaths;
        if (o.sharedLibraryOverlayPaths != null) {
            this.sharedLibraryOverlayPaths = new ArrayMap<>(o.sharedLibraryOverlayPaths);
        }
        this.harmfulAppWarning = o.harmfulAppWarning;
        if (o.componentLabelIconOverrideMap != null) {
            this.componentLabelIconOverrideMap = new ArrayMap<>(o.componentLabelIconOverrideMap);
        }
        this.splashScreenTheme = o.splashScreenTheme;
        this.mPackageUserStateExt.setExtraData(o.mPackageUserStateExt);
    }

    public OverlayPaths getOverlayPaths() {
        return this.overlayPaths;
    }

    public Map<String, OverlayPaths> getSharedLibraryOverlayPaths() {
        return this.sharedLibraryOverlayPaths;
    }

    public boolean setOverlayPaths(OverlayPaths paths) {
        if (Objects.equals(paths, this.overlayPaths)) {
            return false;
        }
        if ((this.overlayPaths == null && paths.isEmpty()) || (paths == null && this.overlayPaths.isEmpty())) {
            return false;
        }
        this.overlayPaths = paths;
        this.cachedOverlayPaths = null;
        return true;
    }

    public boolean setSharedLibraryOverlayPaths(String library, OverlayPaths paths) {
        if (this.sharedLibraryOverlayPaths == null) {
            this.sharedLibraryOverlayPaths = new ArrayMap<>();
        }
        OverlayPaths currentPaths = this.sharedLibraryOverlayPaths.get(library);
        if (Objects.equals(paths, currentPaths)) {
            return false;
        }
        this.cachedOverlayPaths = null;
        if (paths == null || paths.isEmpty()) {
            return this.sharedLibraryOverlayPaths.remove(library) != null;
        }
        this.sharedLibraryOverlayPaths.put(library, paths);
        return true;
    }

    public boolean overrideLabelAndIcon(ComponentName component, String nonLocalizedLabel, Integer icon) {
        Pair<String, Integer> pair;
        String existingLabel = null;
        Integer existingIcon = null;
        ArrayMap<ComponentName, Pair<String, Integer>> arrayMap = this.componentLabelIconOverrideMap;
        if (!(arrayMap == null || (pair = arrayMap.get(component)) == null)) {
            existingLabel = pair.first;
            existingIcon = pair.second;
        }
        boolean changed = !TextUtils.equals(existingLabel, nonLocalizedLabel) || !Objects.equals(existingIcon, icon);
        if (changed) {
            if (nonLocalizedLabel == null && icon == null) {
                this.componentLabelIconOverrideMap.remove(component);
                if (this.componentLabelIconOverrideMap.isEmpty()) {
                    this.componentLabelIconOverrideMap = null;
                }
            } else {
                if (this.componentLabelIconOverrideMap == null) {
                    this.componentLabelIconOverrideMap = new ArrayMap<>(1);
                }
                this.componentLabelIconOverrideMap.put(component, Pair.create(nonLocalizedLabel, icon));
            }
        }
        return changed;
    }

    public void resetOverrideComponentLabelIcon() {
        this.componentLabelIconOverrideMap = null;
    }

    public Pair<String, Integer> getOverrideLabelIconForComponent(ComponentName componentName) {
        if (ArrayUtils.isEmpty(this.componentLabelIconOverrideMap)) {
            return null;
        }
        return this.componentLabelIconOverrideMap.get(componentName);
    }

    public boolean isAvailable(int flags) {
        boolean matchAnyUser = (4194304 & flags) != 0;
        boolean matchUninstalled = (flags & 8192) != 0;
        if (!matchAnyUser) {
            return this.installed && (!this.hidden || matchUninstalled);
        }
        return true;
    }

    public boolean isMatch(ComponentInfo componentInfo, int flags) {
        return isMatch(componentInfo.applicationInfo.isSystemApp(), componentInfo.applicationInfo.enabled, componentInfo.enabled, componentInfo.directBootAware, componentInfo.name, flags);
    }

    public boolean isMatch(boolean isSystem, boolean isPackageEnabled, ParsedMainComponent component, int flags) {
        return isMatch(isSystem, isPackageEnabled, component.isEnabled(), component.isDirectBootAware(), component.getName(), flags);
    }

    public boolean isMatch(boolean isSystem, boolean isPackageEnabled, boolean isComponentEnabled, boolean isComponentDirectBootAware, String componentName, int flags) {
        boolean z = true;
        boolean matchUninstalled = (4202496 & flags) != 0;
        if (!isAvailable(flags) && (!isSystem || !matchUninstalled)) {
            return reportIfDebug(false, flags);
        }
        if (!isEnabled(isPackageEnabled, isComponentEnabled, componentName, flags)) {
            return reportIfDebug(false, flags);
        }
        if ((1048576 & flags) != 0 && !isSystem) {
            return reportIfDebug(false, flags);
        }
        boolean matchesUnaware = (262144 & flags) != 0 && !isComponentDirectBootAware;
        boolean matchesAware = (524288 & flags) != 0 && isComponentDirectBootAware;
        if (!matchesUnaware && !matchesAware) {
            z = false;
        }
        return reportIfDebug(z, flags);
    }

    public boolean reportIfDebug(boolean result, int flags) {
        return result;
    }

    public boolean isPackageEnabled(ParsingPackageRead pkg) {
        switch (this.enabled) {
            case 1:
                return true;
            case 2:
            case 3:
            case 4:
                return false;
            default:
                return pkg.isEnabled();
        }
    }

    public boolean isEnabled(ComponentInfo componentInfo, int flags) {
        return isEnabled(componentInfo.applicationInfo.enabled, componentInfo.enabled, componentInfo.name, flags);
    }

    public boolean isEnabled(boolean isPackageEnabled, ParsedMainComponent parsedComponent, int flags) {
        return isEnabled(isPackageEnabled, parsedComponent.isEnabled(), parsedComponent.getName(), flags);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0028 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isEnabled(boolean r5, boolean r6, java.lang.String r7, int r8) {
        /*
            r4 = this;
            r0 = r8 & 512(0x200, float:7.175E-43)
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r4.enabled
            r2 = 0
            switch(r0) {
                case 0: goto L_0x001d;
                case 1: goto L_0x000c;
                case 2: goto L_0x0014;
                case 3: goto L_0x0014;
                case 4: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0020
        L_0x000d:
            r0 = 32768(0x8000, float:4.5918E-41)
            r0 = r0 & r8
            if (r0 != 0) goto L_0x001d
            return r2
        L_0x0014:
            android.content.pm.IPackageUserStateExt r3 = r4.mPackageUserStateExt
            boolean r0 = r3.ignorePackageDisabledInIsEnabled(r0, r8)
            if (r0 != 0) goto L_0x0020
            return r2
        L_0x001d:
            if (r5 != 0) goto L_0x0020
            return r2
        L_0x0020:
            android.util.ArraySet<java.lang.String> r0 = r4.enabledComponents
            boolean r0 = com.android.internal.util.ArrayUtils.contains(r0, r7)
            if (r0 == 0) goto L_0x0029
            return r1
        L_0x0029:
            android.util.ArraySet<java.lang.String> r0 = r4.disabledComponents
            boolean r0 = com.android.internal.util.ArrayUtils.contains(r0, r7)
            if (r0 == 0) goto L_0x0032
            return r2
        L_0x0032:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageUserState.isEnabled(boolean, boolean, java.lang.String, int):boolean");
    }

    public OverlayPaths getAllOverlayPaths() {
        if (this.overlayPaths == null && this.sharedLibraryOverlayPaths == null) {
            return null;
        }
        OverlayPaths overlayPaths = this.cachedOverlayPaths;
        if (overlayPaths != null) {
            return overlayPaths;
        }
        OverlayPaths.Builder newPaths = new OverlayPaths.Builder();
        newPaths.addAll(this.overlayPaths);
        ArrayMap<String, OverlayPaths> arrayMap = this.sharedLibraryOverlayPaths;
        if (arrayMap != null) {
            for (OverlayPaths libOverlayPaths : arrayMap.values()) {
                newPaths.addAll(libOverlayPaths);
            }
        }
        OverlayPaths build = newPaths.build();
        this.cachedOverlayPaths = build;
        return build;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof PackageUserState)) {
            return false;
        }
        PackageUserState oldState = (PackageUserState) obj;
        if (!(this.ceDataInode == oldState.ceDataInode && this.installed == oldState.installed && this.stopped == oldState.stopped && this.notLaunched == oldState.notLaunched && this.hidden == oldState.hidden && this.distractionFlags == oldState.distractionFlags && (z = this.suspended) == oldState.suspended)) {
            return false;
        }
        if (!((!z || Objects.equals(this.suspendParams, oldState.suspendParams)) && this.instantApp == oldState.instantApp && this.virtualPreload == oldState.virtualPreload && this.enabled == oldState.enabled)) {
            return false;
        }
        String str = this.lastDisableAppCaller;
        if ((str == null && oldState.lastDisableAppCaller != null) || !((str == null || str.equals(oldState.lastDisableAppCaller)) && this.categoryHint == oldState.categoryHint && this.installReason == oldState.installReason && this.uninstallReason == oldState.uninstallReason)) {
            return false;
        }
        ArraySet<String> arraySet = this.disabledComponents;
        if ((arraySet == null && oldState.disabledComponents != null) || (arraySet != null && oldState.disabledComponents == null)) {
            return false;
        }
        if (arraySet != null) {
            if (arraySet.size() != oldState.disabledComponents.size()) {
                return false;
            }
            for (int i = this.disabledComponents.size() - 1; i >= 0; i--) {
                if (!oldState.disabledComponents.contains(this.disabledComponents.valueAt(i))) {
                    return false;
                }
            }
        }
        ArraySet<String> arraySet2 = this.enabledComponents;
        if ((arraySet2 == null && oldState.enabledComponents != null) || (arraySet2 != null && oldState.enabledComponents == null)) {
            return false;
        }
        if (arraySet2 != null) {
            if (arraySet2.size() != oldState.enabledComponents.size()) {
                return false;
            }
            for (int i2 = this.enabledComponents.size() - 1; i2 >= 0; i2--) {
                if (!oldState.enabledComponents.contains(this.enabledComponents.valueAt(i2))) {
                    return false;
                }
            }
        }
        String str2 = this.harmfulAppWarning;
        return (str2 != null || oldState.harmfulAppWarning == null) && (str2 == null || str2.equals(oldState.harmfulAppWarning)) && Objects.equals(this.splashScreenTheme, oldState.splashScreenTheme);
    }

    public int hashCode() {
        int hashCode = Long.hashCode(this.ceDataInode);
        return (((((((((((((((((((((((((((((((((((hashCode * 31) + Boolean.hashCode(this.installed)) * 31) + Boolean.hashCode(this.stopped)) * 31) + Boolean.hashCode(this.notLaunched)) * 31) + Boolean.hashCode(this.hidden)) * 31) + this.distractionFlags) * 31) + Boolean.hashCode(this.suspended)) * 31) + Objects.hashCode(this.suspendParams)) * 31) + Boolean.hashCode(this.instantApp)) * 31) + Boolean.hashCode(this.virtualPreload)) * 31) + this.enabled) * 31) + Objects.hashCode(this.lastDisableAppCaller)) * 31) + this.categoryHint) * 31) + this.installReason) * 31) + this.uninstallReason) * 31) + Objects.hashCode(this.disabledComponents)) * 31) + Objects.hashCode(this.enabledComponents)) * 31) + Objects.hashCode(this.harmfulAppWarning)) * 31) + Objects.hashCode(this.splashScreenTheme);
    }

    /* loaded from: classes.dex */
    public static final class SuspendParams {
        private static final String TAG_APP_EXTRAS = "app-extras";
        private static final String TAG_DIALOG_INFO = "dialog-info";
        private static final String TAG_LAUNCHER_EXTRAS = "launcher-extras";
        public PersistableBundle appExtras;
        public SuspendDialogInfo dialogInfo;
        public PersistableBundle launcherExtras;

        private SuspendParams() {
        }

        public static SuspendParams getInstanceOrNull(SuspendDialogInfo dialogInfo, PersistableBundle appExtras, PersistableBundle launcherExtras) {
            if (dialogInfo == null && appExtras == null && launcherExtras == null) {
                return null;
            }
            SuspendParams instance = new SuspendParams();
            instance.dialogInfo = dialogInfo;
            instance.appExtras = appExtras;
            instance.launcherExtras = launcherExtras;
            return instance;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SuspendParams)) {
                return false;
            }
            SuspendParams other = (SuspendParams) obj;
            return Objects.equals(this.dialogInfo, other.dialogInfo) && BaseBundle.kindofEquals(this.appExtras, other.appExtras) && BaseBundle.kindofEquals(this.launcherExtras, other.launcherExtras);
        }

        public int hashCode() {
            int hashCode = Objects.hashCode(this.dialogInfo);
            int i = hashCode * 31;
            PersistableBundle persistableBundle = this.appExtras;
            int i2 = 0;
            int hashCode2 = i + (persistableBundle != null ? persistableBundle.size() : 0);
            int hashCode3 = hashCode2 * 31;
            PersistableBundle persistableBundle2 = this.launcherExtras;
            if (persistableBundle2 != null) {
                i2 = persistableBundle2.size();
            }
            return hashCode3 + i2;
        }

        public void saveToXml(TypedXmlSerializer out) throws IOException {
            if (this.dialogInfo != null) {
                out.startTag(null, TAG_DIALOG_INFO);
                this.dialogInfo.saveToXml(out);
                out.endTag(null, TAG_DIALOG_INFO);
            }
            if (this.appExtras != null) {
                out.startTag(null, TAG_APP_EXTRAS);
                try {
                    this.appExtras.saveToXml(out);
                } catch (XmlPullParserException e) {
                    Slog.e(PackageUserState.LOG_TAG, "Exception while trying to write appExtras. Will be lost on reboot", e);
                }
                out.endTag(null, TAG_APP_EXTRAS);
            }
            if (this.launcherExtras != null) {
                out.startTag(null, TAG_LAUNCHER_EXTRAS);
                try {
                    this.launcherExtras.saveToXml(out);
                } catch (XmlPullParserException e2) {
                    Slog.e(PackageUserState.LOG_TAG, "Exception while trying to write launcherExtras. Will be lost on reboot", e2);
                }
                out.endTag(null, TAG_LAUNCHER_EXTRAS);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static SuspendParams restoreFromXml(TypedXmlPullParser in) throws IOException {
            SuspendDialogInfo readDialogInfo = null;
            PersistableBundle readAppExtras = null;
            PersistableBundle readLauncherExtras = null;
            int currentDepth = in.getDepth();
            while (true) {
                try {
                    int type = in.next();
                    char c = 1;
                    if (type != 1 && (type != 3 || in.getDepth() > currentDepth)) {
                        if (!(type == 3 || type == 4)) {
                            String name = in.getName();
                            c = 65535;
                            switch (name.hashCode()) {
                                case -538220657:
                                    if (name.equals(TAG_APP_EXTRAS)) {
                                        break;
                                    }
                                    break;
                                case -22768109:
                                    if (name.equals(TAG_DIALOG_INFO)) {
                                        c = 0;
                                        break;
                                    }
                                    break;
                                case 1627485488:
                                    if (name.equals(TAG_LAUNCHER_EXTRAS)) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    readDialogInfo = SuspendDialogInfo.restoreFromXml(in);
                                    continue;
                                case 1:
                                    readAppExtras = PersistableBundle.restoreFromXml(in);
                                    continue;
                                case 2:
                                    readLauncherExtras = PersistableBundle.restoreFromXml(in);
                                    continue;
                                default:
                                    Slog.w(PackageUserState.LOG_TAG, "Unknown tag " + in.getName() + " in SuspendParams. Ignoring");
                                    continue;
                            }
                        }
                    }
                } catch (XmlPullParserException e) {
                    Slog.e(PackageUserState.LOG_TAG, "Exception while trying to parse SuspendParams, some fields may default", e);
                }
            }
            return getInstanceOrNull(readDialogInfo, readAppExtras, readLauncherExtras);
        }
    }
}
