package android.content.pm.parsing.component;

import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class ParsedActivity extends ParsedMainComponent {
    public static final Parcelable.Creator<ParsedActivity> CREATOR = new Parcelable.Creator<ParsedActivity>() { // from class: android.content.pm.parsing.component.ParsedActivity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedActivity createFromParcel(Parcel source) {
            return new ParsedActivity(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedActivity[] newArray(int size) {
            return new ParsedActivity[size];
        }
    };
    int colorMode;
    int configChanges;
    int documentLaunchMode;
    int launchMode;
    int lockTaskLaunchMode;
    private Float maxAspectRatio;
    int maxRecents;
    private Float minAspectRatio;
    private String parentActivityName;
    private String permission;
    int persistableMode;
    int privateFlags;
    String requestedVrComponent;
    int resizeMode;
    int rotationAnimation;
    int screenOrientation;
    int softInputMode;
    private boolean supportsSizeChanges;
    private String targetActivity;
    String taskAffinity;
    int theme;
    int uiOptions;
    ActivityInfo.WindowLayout windowLayout;

    public ParsedActivity(ParsedActivity other) {
        super(other);
        this.screenOrientation = -1;
        this.resizeMode = 2;
        this.rotationAnimation = -1;
        this.theme = other.theme;
        this.uiOptions = other.uiOptions;
        this.targetActivity = other.targetActivity;
        this.parentActivityName = other.parentActivityName;
        this.taskAffinity = other.taskAffinity;
        this.privateFlags = other.privateFlags;
        this.permission = other.permission;
        this.launchMode = other.launchMode;
        this.documentLaunchMode = other.documentLaunchMode;
        this.maxRecents = other.maxRecents;
        this.configChanges = other.configChanges;
        this.softInputMode = other.softInputMode;
        this.persistableMode = other.persistableMode;
        this.lockTaskLaunchMode = other.lockTaskLaunchMode;
        this.screenOrientation = other.screenOrientation;
        this.resizeMode = other.resizeMode;
        this.maxAspectRatio = other.maxAspectRatio;
        this.minAspectRatio = other.minAspectRatio;
        this.supportsSizeChanges = other.supportsSizeChanges;
        this.requestedVrComponent = other.requestedVrComponent;
        this.rotationAnimation = other.rotationAnimation;
        this.colorMode = other.colorMode;
        this.windowLayout = other.windowLayout;
    }

    public static ParsedActivity makeAppDetailsActivity(String packageName, String processName, int uiOptions, String taskAffinity, boolean hardwareAccelerated) {
        ParsedActivity activity = new ParsedActivity();
        activity.setPackageName(packageName);
        activity.theme = 16973909;
        activity.exported = true;
        activity.setName(PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME);
        activity.setProcessName(processName);
        activity.uiOptions = uiOptions;
        activity.taskAffinity = taskAffinity;
        activity.launchMode = 0;
        activity.documentLaunchMode = 0;
        activity.maxRecents = ActivityTaskManager.getDefaultAppRecentsLimitStatic();
        activity.configChanges = ParsedActivityUtils.getActivityConfigChanges(0, 0);
        activity.softInputMode = 0;
        activity.persistableMode = 1;
        activity.screenOrientation = -1;
        activity.resizeMode = 4;
        activity.lockTaskLaunchMode = 0;
        activity.setDirectBootAware(false);
        activity.rotationAnimation = -1;
        activity.colorMode = 0;
        if (hardwareAccelerated) {
            activity.setFlags(activity.getFlags() | 512);
        }
        return activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParsedActivity makeAlias(String targetActivityName, ParsedActivity target) {
        ParsedActivity alias = new ParsedActivity();
        alias.setPackageName(target.getPackageName());
        alias.setTargetActivity(targetActivityName);
        alias.configChanges = target.configChanges;
        alias.flags = target.flags;
        alias.privateFlags = target.privateFlags;
        alias.icon = target.icon;
        alias.logo = target.logo;
        alias.banner = target.banner;
        alias.labelRes = target.labelRes;
        alias.nonLocalizedLabel = target.nonLocalizedLabel;
        alias.launchMode = target.launchMode;
        alias.lockTaskLaunchMode = target.lockTaskLaunchMode;
        alias.documentLaunchMode = target.documentLaunchMode;
        alias.descriptionRes = target.descriptionRes;
        alias.screenOrientation = target.screenOrientation;
        alias.taskAffinity = target.taskAffinity;
        alias.theme = target.theme;
        alias.softInputMode = target.softInputMode;
        alias.uiOptions = target.uiOptions;
        alias.parentActivityName = target.parentActivityName;
        alias.maxRecents = target.maxRecents;
        alias.windowLayout = target.windowLayout;
        alias.resizeMode = target.resizeMode;
        alias.maxAspectRatio = target.maxAspectRatio;
        alias.minAspectRatio = target.minAspectRatio;
        alias.supportsSizeChanges = target.supportsSizeChanges;
        alias.requestedVrComponent = target.requestedVrComponent;
        alias.directBootAware = target.directBootAware;
        alias.setProcessName(target.getProcessName());
        return alias;
    }

    public ParsedActivity setMaxAspectRatio(int resizeMode, float maxAspectRatio) {
        if (resizeMode == 2 || resizeMode == 1) {
            return this;
        }
        if (maxAspectRatio < 1.0f && maxAspectRatio != 0.0f) {
            return this;
        }
        this.maxAspectRatio = Float.valueOf(maxAspectRatio);
        return this;
    }

    public ParsedActivity setMinAspectRatio(int resizeMode, float minAspectRatio) {
        if (resizeMode == 2 || resizeMode == 1) {
            return this;
        }
        if (minAspectRatio < 1.0f && minAspectRatio != 0.0f) {
            return this;
        }
        this.minAspectRatio = Float.valueOf(minAspectRatio);
        return this;
    }

    public ParsedActivity setSupportsSizeChanges(boolean supportsSizeChanges) {
        this.supportsSizeChanges = supportsSizeChanges;
        return this;
    }

    public ParsedActivity setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public ParsedActivity setResizeMode(int resizeMode) {
        this.resizeMode = resizeMode;
        return this;
    }

    public ParsedActivity setTargetActivity(String targetActivity) {
        this.targetActivity = TextUtils.safeIntern(targetActivity);
        return this;
    }

    public ParsedActivity setParentActivity(String parentActivity) {
        this.parentActivityName = TextUtils.safeIntern(parentActivity);
        return this;
    }

    public ParsedActivity setPermission(String permission) {
        this.permission = TextUtils.isEmpty(permission) ? null : permission.intern();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Activity{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.theme);
        dest.writeInt(this.uiOptions);
        dest.writeString(this.targetActivity);
        dest.writeString(this.parentActivityName);
        dest.writeString(this.taskAffinity);
        dest.writeInt(this.privateFlags);
        ParsingPackageImpl.sForInternedString.parcel(this.permission, dest, flags);
        dest.writeInt(this.launchMode);
        dest.writeInt(this.documentLaunchMode);
        dest.writeInt(this.maxRecents);
        dest.writeInt(this.configChanges);
        dest.writeInt(this.softInputMode);
        dest.writeInt(this.persistableMode);
        dest.writeInt(this.lockTaskLaunchMode);
        dest.writeInt(this.screenOrientation);
        dest.writeInt(this.resizeMode);
        dest.writeValue(this.maxAspectRatio);
        dest.writeValue(this.minAspectRatio);
        dest.writeBoolean(this.supportsSizeChanges);
        dest.writeString(this.requestedVrComponent);
        dest.writeInt(this.rotationAnimation);
        dest.writeInt(this.colorMode);
        dest.writeBundle(this.metaData);
        if (this.windowLayout != null) {
            dest.writeInt(1);
            this.windowLayout.writeToParcel(dest);
            return;
        }
        dest.writeBoolean(false);
    }

    public ParsedActivity() {
        this.screenOrientation = -1;
        this.resizeMode = 2;
        this.rotationAnimation = -1;
    }

    protected ParsedActivity(Parcel in) {
        super(in);
        this.screenOrientation = -1;
        this.resizeMode = 2;
        this.rotationAnimation = -1;
        this.theme = in.readInt();
        this.uiOptions = in.readInt();
        this.targetActivity = in.readString();
        this.parentActivityName = in.readString();
        this.taskAffinity = in.readString();
        this.privateFlags = in.readInt();
        this.permission = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.launchMode = in.readInt();
        this.documentLaunchMode = in.readInt();
        this.maxRecents = in.readInt();
        this.configChanges = in.readInt();
        this.softInputMode = in.readInt();
        this.persistableMode = in.readInt();
        this.lockTaskLaunchMode = in.readInt();
        this.screenOrientation = in.readInt();
        this.resizeMode = in.readInt();
        this.maxAspectRatio = (Float) in.readValue(Float.class.getClassLoader());
        this.minAspectRatio = (Float) in.readValue(Float.class.getClassLoader());
        this.supportsSizeChanges = in.readBoolean();
        this.requestedVrComponent = in.readString();
        this.rotationAnimation = in.readInt();
        this.colorMode = in.readInt();
        this.metaData = in.readBundle();
        if (in.readBoolean()) {
            this.windowLayout = new ActivityInfo.WindowLayout(in);
        }
    }

    public int getTheme() {
        return this.theme;
    }

    public int getUiOptions() {
        return this.uiOptions;
    }

    public String getTargetActivity() {
        return this.targetActivity;
    }

    public String getParentActivityName() {
        return this.parentActivityName;
    }

    public String getTaskAffinity() {
        return this.taskAffinity;
    }

    public int getPrivateFlags() {
        return this.privateFlags;
    }

    public String getPermission() {
        return this.permission;
    }

    public int getLaunchMode() {
        return this.launchMode;
    }

    public int getDocumentLaunchMode() {
        return this.documentLaunchMode;
    }

    public int getMaxRecents() {
        return this.maxRecents;
    }

    public int getConfigChanges() {
        return this.configChanges;
    }

    public int getSoftInputMode() {
        return this.softInputMode;
    }

    public int getPersistableMode() {
        return this.persistableMode;
    }

    public int getLockTaskLaunchMode() {
        return this.lockTaskLaunchMode;
    }

    public int getScreenOrientation() {
        return this.screenOrientation;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public Float getMaxAspectRatio() {
        return this.maxAspectRatio;
    }

    public Float getMinAspectRatio() {
        return this.minAspectRatio;
    }

    public boolean getSupportsSizeChanges() {
        return this.supportsSizeChanges;
    }

    public String getRequestedVrComponent() {
        return this.requestedVrComponent;
    }

    public int getRotationAnimation() {
        return this.rotationAnimation;
    }

    public int getColorMode() {
        return this.colorMode;
    }

    public ActivityInfo.WindowLayout getWindowLayout() {
        return this.windowLayout;
    }
}
