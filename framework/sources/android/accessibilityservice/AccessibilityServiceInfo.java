package android.accessibilityservice;

import android.accessibilityservice.util.AccessibilityUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import android.net.TrafficStats;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.R;
import com.android.internal.compat.IPlatformCompat;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class AccessibilityServiceInfo implements Parcelable {
    public static final int CAPABILITY_CAN_CONTROL_MAGNIFICATION = 16;
    public static final int CAPABILITY_CAN_PERFORM_GESTURES = 32;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES = 64;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int CAPABILITY_CAN_TAKE_SCREENSHOT = 128;
    public static final Parcelable.Creator<AccessibilityServiceInfo> CREATOR = new Parcelable.Creator<AccessibilityServiceInfo>() { // from class: android.accessibilityservice.AccessibilityServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccessibilityServiceInfo createFromParcel(Parcel parcel) {
            AccessibilityServiceInfo info = new AccessibilityServiceInfo();
            info.initFromParcel(parcel);
            return info;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccessibilityServiceInfo[] newArray(int size) {
            return new AccessibilityServiceInfo[size];
        }
    };
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_AUDIBLE = 4;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FEEDBACK_GENERIC = 16;
    public static final int FEEDBACK_HAPTIC = 2;
    public static final int FEEDBACK_SPOKEN = 1;
    public static final int FEEDBACK_VISUAL = 8;
    public static final int FLAG_ENABLE_ACCESSIBILITY_VOLUME = 128;
    public static final int FLAG_FORCE_DIRECT_BOOT_AWARE = 65536;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_2_FINGER_PASSTHROUGH = 8192;
    public static final int FLAG_REQUEST_ACCESSIBILITY_BUTTON = 256;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_FINGERPRINT_GESTURES = 512;
    public static final int FLAG_REQUEST_MULTI_FINGER_GESTURES = 4096;
    public static final int FLAG_REQUEST_SHORTCUT_WARNING_DIALOG_SPOKEN_FEEDBACK = 1024;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    public static final int FLAG_RETRIEVE_INTERACTIVE_WINDOWS = 64;
    public static final int FLAG_SEND_MOTION_EVENTS = 16384;
    public static final int FLAG_SERVICE_HANDLES_DOUBLE_TAP = 2048;
    private static final long REQUEST_ACCESSIBILITY_BUTTON_CHANGE = 136293963;
    private static final String TAG_ACCESSIBILITY_SERVICE = "accessibility-service";
    private static SparseArray<CapabilityInfo> sAvailableCapabilityInfos;
    public boolean crashed;
    public int eventTypes;
    public int feedbackType;
    public int flags;
    private int mAnimatedImageRes;
    private int mCapabilities;
    private ComponentName mComponentName;
    private int mDescriptionResId;
    private int mHtmlDescriptionRes;
    private int mInteractiveUiTimeout;
    private boolean mIsAccessibilityTool;
    private int mNonInteractiveUiTimeout;
    private String mNonLocalizedDescription;
    private String mNonLocalizedSummary;
    private ResolveInfo mResolveInfo;
    private String mSettingsActivityName;
    private int mSummaryResId;
    public long notificationTimeout;
    public String[] packageNames;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FeedbackType {
    }

    public AccessibilityServiceInfo() {
        this.mIsAccessibilityTool = false;
    }

    public AccessibilityServiceInfo(ResolveInfo resolveInfo, Context context) throws XmlPullParserException, IOException {
        TypedValue peekedValue;
        TypedValue peekedValue2;
        TypedValue peekedValue3;
        TypedValue peekedValue4;
        this.mIsAccessibilityTool = false;
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        this.mComponentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
        this.mResolveInfo = resolveInfo;
        XmlResourceParser parser = null;
        try {
            try {
                PackageManager packageManager = context.getPackageManager();
                parser = serviceInfo.loadXmlMetaData(packageManager, AccessibilityService.SERVICE_META_DATA);
                if (parser != null) {
                    for (int type = 0; type != 1 && type != 2; type = parser.next()) {
                    }
                    String nodeName = parser.getName();
                    if (TAG_ACCESSIBILITY_SERVICE.equals(nodeName)) {
                        AttributeSet allAttributes = Xml.asAttributeSet(parser);
                        Resources resources = packageManager.getResourcesForApplication(serviceInfo.applicationInfo);
                        TypedArray asAttributes = resources.obtainAttributes(allAttributes, R.styleable.AccessibilityService);
                        this.eventTypes = asAttributes.getInt(3, 0);
                        String packageNamez = asAttributes.getString(4);
                        if (packageNamez != null) {
                            this.packageNames = packageNamez.split("(\\s)*,(\\s)*");
                        }
                        this.feedbackType = asAttributes.getInt(5, 0);
                        this.notificationTimeout = asAttributes.getInt(6, 0);
                        this.mNonInteractiveUiTimeout = asAttributes.getInt(15, 0);
                        this.mInteractiveUiTimeout = asAttributes.getInt(16, 0);
                        this.flags = asAttributes.getInt(7, 0);
                        this.mSettingsActivityName = asAttributes.getString(2);
                        if (asAttributes.getBoolean(8, false)) {
                            this.mCapabilities |= 1;
                        }
                        if (asAttributes.getBoolean(9, false)) {
                            this.mCapabilities = 2 | this.mCapabilities;
                        }
                        if (asAttributes.getBoolean(11, false)) {
                            this.mCapabilities |= 8;
                        }
                        if (asAttributes.getBoolean(12, false)) {
                            this.mCapabilities |= 16;
                        }
                        if (asAttributes.getBoolean(13, false)) {
                            this.mCapabilities |= 32;
                        }
                        if (asAttributes.getBoolean(14, false)) {
                            this.mCapabilities |= 64;
                        }
                        if (asAttributes.getBoolean(19, false)) {
                            this.mCapabilities |= 128;
                        }
                        try {
                            peekedValue = asAttributes.peekValue(0);
                        } catch (Exception e) {
                            Log.e(TAG_ACCESSIBILITY_SERVICE, "App issue: failed to load app's AccessibilityService_description, will apply default one!", e);
                            peekedValue = null;
                        }
                        if (peekedValue != null) {
                            this.mDescriptionResId = peekedValue.resourceId;
                            CharSequence nonLocalizedDescription = peekedValue.coerceToString();
                            if (nonLocalizedDescription != null) {
                                this.mNonLocalizedDescription = nonLocalizedDescription.toString().trim();
                            }
                        } else {
                            this.mDescriptionResId = 0;
                            this.mNonLocalizedDescription = "AppIssue:Undefined";
                        }
                        try {
                            peekedValue2 = asAttributes.peekValue(1);
                        } catch (Exception e2) {
                            Log.e(TAG_ACCESSIBILITY_SERVICE, "App issue: failed to load app's AccessibilityService_summary, will apply default one!", e2);
                            peekedValue2 = null;
                        }
                        if (peekedValue2 != null) {
                            this.mSummaryResId = peekedValue2.resourceId;
                            CharSequence nonLocalizedSummary = peekedValue2.coerceToString();
                            if (nonLocalizedSummary != null) {
                                this.mNonLocalizedSummary = nonLocalizedSummary.toString().trim();
                            }
                        } else {
                            this.mSummaryResId = 0;
                            this.mNonLocalizedSummary = "AppIssue:Undefined";
                        }
                        try {
                            peekedValue3 = asAttributes.peekValue(17);
                        } catch (Exception e3) {
                            Log.e(TAG_ACCESSIBILITY_SERVICE, "App issue: failed to load app's AccessibilityService_animatedImageDrawable, will apply default one!", e3);
                            peekedValue3 = null;
                        }
                        if (peekedValue3 != null) {
                            this.mAnimatedImageRes = peekedValue3.resourceId;
                        } else {
                            this.mAnimatedImageRes = 0;
                        }
                        try {
                            peekedValue4 = asAttributes.peekValue(18);
                        } catch (Exception e4) {
                            Log.e(TAG_ACCESSIBILITY_SERVICE, "App issue: failed to load app's AccessibilityService_htmlDescription, will apply default one!", e4);
                            peekedValue4 = null;
                        }
                        if (peekedValue4 != null) {
                            this.mHtmlDescriptionRes = peekedValue4.resourceId;
                        } else {
                            this.mHtmlDescriptionRes = 0;
                            this.mNonLocalizedSummary = "AppIssue:Undefined";
                        }
                        this.mIsAccessibilityTool = asAttributes.getBoolean(20, false);
                        asAttributes.recycle();
                        if (parser != null) {
                            parser.close();
                            return;
                        }
                        return;
                    }
                    throw new XmlPullParserException("Meta-data does not start withaccessibility-service tag");
                }
            } finally {
                if (parser != null) {
                    parser.close();
                }
            }
        } catch (PackageManager.NameNotFoundException e5) {
            throw new XmlPullParserException("Unable to create context for: " + serviceInfo.packageName);
        }
    }

    public void updateDynamicallyConfigurableProperties(IPlatformCompat platformCompat, AccessibilityServiceInfo other) {
        if (isRequestAccessibilityButtonChangeEnabled(platformCompat)) {
            int i = other.flags & TrafficStats.TAG_NETWORK_STACK_RANGE_END;
            other.flags = i;
            other.flags = i | (this.flags & 256);
        }
        this.eventTypes = other.eventTypes;
        this.packageNames = other.packageNames;
        this.feedbackType = other.feedbackType;
        this.notificationTimeout = other.notificationTimeout;
        this.mNonInteractiveUiTimeout = other.mNonInteractiveUiTimeout;
        this.mInteractiveUiTimeout = other.mInteractiveUiTimeout;
        this.flags = other.flags;
    }

    private boolean isRequestAccessibilityButtonChangeEnabled(IPlatformCompat platformCompat) {
        ResolveInfo resolveInfo = this.mResolveInfo;
        if (resolveInfo == null) {
            return true;
        }
        if (platformCompat != null) {
            try {
                return platformCompat.isChangeEnabled(REQUEST_ACCESSIBILITY_BUTTON_CHANGE, resolveInfo.serviceInfo.applicationInfo);
            } catch (RemoteException e) {
            }
        }
        return this.mResolveInfo.serviceInfo.applicationInfo.targetSdkVersion > 29;
    }

    public void setComponentName(ComponentName component) {
        this.mComponentName = component;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public String getId() {
        return this.mComponentName.flattenToShortString();
    }

    public ResolveInfo getResolveInfo() {
        return this.mResolveInfo;
    }

    public String getSettingsActivityName() {
        return this.mSettingsActivityName;
    }

    public int getAnimatedImageRes() {
        return this.mAnimatedImageRes;
    }

    public Drawable loadAnimatedImage(Context context) {
        if (this.mAnimatedImageRes == 0) {
            return null;
        }
        return AccessibilityUtils.loadSafeAnimatedImage(context, this.mResolveInfo.serviceInfo.applicationInfo, this.mAnimatedImageRes);
    }

    public boolean getCanRetrieveWindowContent() {
        return (this.mCapabilities & 1) != 0;
    }

    public int getCapabilities() {
        return this.mCapabilities;
    }

    public void setCapabilities(int capabilities) {
        this.mCapabilities = capabilities;
    }

    public CharSequence loadSummary(PackageManager packageManager) {
        if (this.mSummaryResId == 0) {
            return this.mNonLocalizedSummary;
        }
        ServiceInfo serviceInfo = this.mResolveInfo.serviceInfo;
        CharSequence summary = packageManager.getText(serviceInfo.packageName, this.mSummaryResId, serviceInfo.applicationInfo);
        if (summary != null) {
            return summary.toString().trim();
        }
        return null;
    }

    public String getDescription() {
        return this.mNonLocalizedDescription;
    }

    public String loadDescription(PackageManager packageManager) {
        if (this.mDescriptionResId == 0) {
            return this.mNonLocalizedDescription;
        }
        ServiceInfo serviceInfo = this.mResolveInfo.serviceInfo;
        CharSequence description = packageManager.getText(serviceInfo.packageName, this.mDescriptionResId, serviceInfo.applicationInfo);
        if (description != null) {
            return description.toString().trim();
        }
        return null;
    }

    public String loadHtmlDescription(PackageManager packageManager) {
        if (this.mHtmlDescriptionRes == 0) {
            return null;
        }
        ServiceInfo serviceInfo = this.mResolveInfo.serviceInfo;
        CharSequence htmlDescription = packageManager.getText(serviceInfo.packageName, this.mHtmlDescriptionRes, serviceInfo.applicationInfo);
        if (htmlDescription != null) {
            return AccessibilityUtils.getFilteredHtmlText(htmlDescription.toString().trim());
        }
        return null;
    }

    public void setNonInteractiveUiTimeoutMillis(int timeout) {
        this.mNonInteractiveUiTimeout = timeout;
    }

    public int getNonInteractiveUiTimeoutMillis() {
        return this.mNonInteractiveUiTimeout;
    }

    public void setInteractiveUiTimeoutMillis(int timeout) {
        this.mInteractiveUiTimeout = timeout;
    }

    public int getInteractiveUiTimeoutMillis() {
        return this.mInteractiveUiTimeout;
    }

    public boolean isDirectBootAware() {
        return (this.flags & 65536) != 0 || this.mResolveInfo.serviceInfo.directBootAware;
    }

    public boolean isAccessibilityTool() {
        return this.mIsAccessibilityTool;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flagz) {
        parcel.writeInt(this.eventTypes);
        parcel.writeStringArray(this.packageNames);
        parcel.writeInt(this.feedbackType);
        parcel.writeLong(this.notificationTimeout);
        parcel.writeInt(this.mNonInteractiveUiTimeout);
        parcel.writeInt(this.mInteractiveUiTimeout);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.crashed ? 1 : 0);
        parcel.writeParcelable(this.mComponentName, flagz);
        parcel.writeParcelable(this.mResolveInfo, 0);
        parcel.writeString(this.mSettingsActivityName);
        parcel.writeInt(this.mCapabilities);
        parcel.writeInt(this.mSummaryResId);
        parcel.writeString(this.mNonLocalizedSummary);
        parcel.writeInt(this.mDescriptionResId);
        parcel.writeInt(this.mAnimatedImageRes);
        parcel.writeInt(this.mHtmlDescriptionRes);
        parcel.writeString(this.mNonLocalizedDescription);
        parcel.writeBoolean(this.mIsAccessibilityTool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFromParcel(Parcel parcel) {
        this.eventTypes = parcel.readInt();
        this.packageNames = parcel.readStringArray();
        this.feedbackType = parcel.readInt();
        this.notificationTimeout = parcel.readLong();
        this.mNonInteractiveUiTimeout = parcel.readInt();
        this.mInteractiveUiTimeout = parcel.readInt();
        this.flags = parcel.readInt();
        this.crashed = parcel.readInt() != 0;
        this.mComponentName = (ComponentName) parcel.readParcelable(getClass().getClassLoader());
        this.mResolveInfo = (ResolveInfo) parcel.readParcelable(null);
        this.mSettingsActivityName = parcel.readString();
        this.mCapabilities = parcel.readInt();
        this.mSummaryResId = parcel.readInt();
        this.mNonLocalizedSummary = parcel.readString();
        this.mDescriptionResId = parcel.readInt();
        this.mAnimatedImageRes = parcel.readInt();
        this.mHtmlDescriptionRes = parcel.readInt();
        this.mNonLocalizedDescription = parcel.readString();
        this.mIsAccessibilityTool = parcel.readBoolean();
    }

    public int hashCode() {
        ComponentName componentName = this.mComponentName;
        return (componentName == null ? 0 : componentName.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityServiceInfo other = (AccessibilityServiceInfo) obj;
        ComponentName componentName = this.mComponentName;
        if (componentName == null) {
            if (other.mComponentName != null) {
                return false;
            }
        } else if (!componentName.equals(other.mComponentName)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        appendEventTypes(stringBuilder, this.eventTypes);
        stringBuilder.append(", ");
        appendPackageNames(stringBuilder, this.packageNames);
        stringBuilder.append(", ");
        appendFeedbackTypes(stringBuilder, this.feedbackType);
        stringBuilder.append(", ");
        stringBuilder.append("notificationTimeout: ");
        stringBuilder.append(this.notificationTimeout);
        stringBuilder.append(", ");
        stringBuilder.append("nonInteractiveUiTimeout: ");
        stringBuilder.append(this.mNonInteractiveUiTimeout);
        stringBuilder.append(", ");
        stringBuilder.append("interactiveUiTimeout: ");
        stringBuilder.append(this.mInteractiveUiTimeout);
        stringBuilder.append(", ");
        appendFlags(stringBuilder, this.flags);
        stringBuilder.append(", ");
        stringBuilder.append("id: ");
        stringBuilder.append(getId());
        stringBuilder.append(", ");
        stringBuilder.append("resolveInfo: ");
        stringBuilder.append(this.mResolveInfo);
        stringBuilder.append(", ");
        stringBuilder.append("settingsActivityName: ");
        stringBuilder.append(this.mSettingsActivityName);
        stringBuilder.append(", ");
        stringBuilder.append("summary: ");
        stringBuilder.append(this.mNonLocalizedSummary);
        stringBuilder.append(", ");
        stringBuilder.append("isAccessibilityTool: ");
        stringBuilder.append(this.mIsAccessibilityTool);
        stringBuilder.append(", ");
        appendCapabilities(stringBuilder, this.mCapabilities);
        return stringBuilder.toString();
    }

    private static void appendFeedbackTypes(StringBuilder stringBuilder, int feedbackTypes) {
        stringBuilder.append("feedbackTypes:");
        stringBuilder.append("[");
        while (feedbackTypes != 0) {
            int feedbackTypeBit = 1 << Integer.numberOfTrailingZeros(feedbackTypes);
            stringBuilder.append(feedbackTypeToString(feedbackTypeBit));
            feedbackTypes &= ~feedbackTypeBit;
            if (feedbackTypes != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
    }

    private static void appendPackageNames(StringBuilder stringBuilder, String[] packageNames) {
        stringBuilder.append("packageNames:");
        stringBuilder.append("[");
        if (packageNames != null) {
            int packageNameCount = packageNames.length;
            for (int i = 0; i < packageNameCount; i++) {
                stringBuilder.append(packageNames[i]);
                if (i < packageNameCount - 1) {
                    stringBuilder.append(", ");
                }
            }
        }
        stringBuilder.append("]");
    }

    private static void appendEventTypes(StringBuilder stringBuilder, int eventTypes) {
        stringBuilder.append("eventTypes:");
        stringBuilder.append("[");
        while (eventTypes != 0) {
            int eventTypeBit = 1 << Integer.numberOfTrailingZeros(eventTypes);
            stringBuilder.append(AccessibilityEvent.eventTypeToString(eventTypeBit));
            eventTypes &= ~eventTypeBit;
            if (eventTypes != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
    }

    private static void appendFlags(StringBuilder stringBuilder, int flags) {
        stringBuilder.append("flags:");
        stringBuilder.append("[");
        while (flags != 0) {
            int flagBit = 1 << Integer.numberOfTrailingZeros(flags);
            stringBuilder.append(flagToString(flagBit));
            flags &= ~flagBit;
            if (flags != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
    }

    private static void appendCapabilities(StringBuilder stringBuilder, int capabilities) {
        stringBuilder.append("capabilities:");
        stringBuilder.append("[");
        while (capabilities != 0) {
            int capabilityBit = 1 << Integer.numberOfTrailingZeros(capabilities);
            stringBuilder.append(capabilityToString(capabilityBit));
            capabilities &= ~capabilityBit;
            if (capabilities != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
    }

    public static String feedbackTypeToString(int feedbackType) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (feedbackType != 0) {
            int feedbackTypeFlag = 1 << Integer.numberOfTrailingZeros(feedbackType);
            feedbackType &= ~feedbackTypeFlag;
            switch (feedbackTypeFlag) {
                case 1:
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append("FEEDBACK_SPOKEN");
                    break;
                case 2:
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append("FEEDBACK_HAPTIC");
                    break;
                case 4:
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append("FEEDBACK_AUDIBLE");
                    break;
                case 8:
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append("FEEDBACK_VISUAL");
                    break;
                case 16:
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append("FEEDBACK_GENERIC");
                    break;
                case 32:
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append("FEEDBACK_BRAILLE");
                    break;
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static String flagToString(int flag) {
        switch (flag) {
            case 1:
                return "DEFAULT";
            case 2:
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            case 4:
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            case 8:
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case 16:
                return "FLAG_REPORT_VIEW_IDS";
            case 32:
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
            case 64:
                return "FLAG_RETRIEVE_INTERACTIVE_WINDOWS";
            case 128:
                return "FLAG_ENABLE_ACCESSIBILITY_VOLUME";
            case 256:
                return "FLAG_REQUEST_ACCESSIBILITY_BUTTON";
            case 512:
                return "FLAG_REQUEST_FINGERPRINT_GESTURES";
            case 1024:
                return "FLAG_REQUEST_SHORTCUT_WARNING_DIALOG_SPOKEN_FEEDBACK";
            case 2048:
                return "FLAG_SERVICE_HANDLES_DOUBLE_TAP";
            case 4096:
                return "FLAG_REQUEST_MULTI_FINGER_GESTURES";
            case 8192:
                return "FLAG_REQUEST_2_FINGER_PASSTHROUGH";
            case 16384:
                return "FLAG_SEND_MOTION_EVENTS";
            default:
                return null;
        }
    }

    public static String capabilityToString(int capability) {
        switch (capability) {
            case 1:
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            case 2:
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            case 4:
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case 8:
                return "CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS";
            case 16:
                return "CAPABILITY_CAN_CONTROL_MAGNIFICATION";
            case 32:
                return "CAPABILITY_CAN_PERFORM_GESTURES";
            case 64:
                return "CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES";
            case 128:
                return "CAPABILITY_CAN_TAKE_SCREENSHOT";
            default:
                return "UNKNOWN";
        }
    }

    public List<CapabilityInfo> getCapabilityInfos() {
        return getCapabilityInfos(null);
    }

    public List<CapabilityInfo> getCapabilityInfos(Context context) {
        if (this.mCapabilities == 0) {
            return Collections.emptyList();
        }
        int capabilities = this.mCapabilities;
        List<CapabilityInfo> capabilityInfos = new ArrayList<>();
        SparseArray<CapabilityInfo> capabilityInfoSparseArray = getCapabilityInfoSparseArray(context);
        while (capabilities != 0) {
            int capabilityBit = 1 << Integer.numberOfTrailingZeros(capabilities);
            capabilities &= ~capabilityBit;
            CapabilityInfo capabilityInfo = capabilityInfoSparseArray.get(capabilityBit);
            if (capabilityInfo != null) {
                capabilityInfos.add(capabilityInfo);
            }
        }
        return capabilityInfos;
    }

    private static SparseArray<CapabilityInfo> getCapabilityInfoSparseArray(Context context) {
        if (sAvailableCapabilityInfos == null) {
            SparseArray<CapabilityInfo> sparseArray = new SparseArray<>();
            sAvailableCapabilityInfos = sparseArray;
            sparseArray.put(1, new CapabilityInfo(1, R.string.capability_title_canRetrieveWindowContent, R.string.capability_desc_canRetrieveWindowContent));
            sAvailableCapabilityInfos.put(2, new CapabilityInfo(2, R.string.capability_title_canRequestTouchExploration, R.string.capability_desc_canRequestTouchExploration));
            sAvailableCapabilityInfos.put(8, new CapabilityInfo(8, R.string.capability_title_canRequestFilterKeyEvents, R.string.capability_desc_canRequestFilterKeyEvents));
            sAvailableCapabilityInfos.put(16, new CapabilityInfo(16, R.string.capability_title_canControlMagnification, R.string.capability_desc_canControlMagnification));
            sAvailableCapabilityInfos.put(32, new CapabilityInfo(32, R.string.capability_title_canPerformGestures, R.string.capability_desc_canPerformGestures));
            sAvailableCapabilityInfos.put(128, new CapabilityInfo(128, R.string.capability_title_canTakeScreenshot, R.string.capability_desc_canTakeScreenshot));
            if (context == null || fingerprintAvailable(context)) {
                sAvailableCapabilityInfos.put(64, new CapabilityInfo(64, R.string.capability_title_canCaptureFingerprintGestures, R.string.capability_desc_canCaptureFingerprintGestures));
            }
        }
        return sAvailableCapabilityInfos;
    }

    private static boolean fingerprintAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT) && ((FingerprintManager) context.getSystemService(FingerprintManager.class)).isHardwareDetected();
    }

    /* loaded from: classes.dex */
    public static final class CapabilityInfo {
        public final int capability;
        public final int descResId;
        public final int titleResId;

        public CapabilityInfo(int capability, int titleResId, int descResId) {
            this.capability = capability;
            this.titleResId = titleResId;
            this.descResId = descResId;
        }
    }
}
