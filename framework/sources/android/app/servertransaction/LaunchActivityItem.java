package android.app.servertransaction;

import android.app.ActivityClient;
import android.app.ActivityOptions;
import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.app.IActivityClientController;
import android.app.ProfilerInfo;
import android.app.ResultInfo;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.IBinder;
import android.os.OplusJankMonitorExtPlugin;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.os.Trace;
import android.view.DisplayAdjustments;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class LaunchActivityItem extends ClientTransactionItem {
    public static final Parcelable.Creator<LaunchActivityItem> CREATOR = new Parcelable.Creator<LaunchActivityItem>() { // from class: android.app.servertransaction.LaunchActivityItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaunchActivityItem createFromParcel(Parcel in) {
            return new LaunchActivityItem(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaunchActivityItem[] newArray(int size) {
            return new LaunchActivityItem[size];
        }
    };
    private IActivityClientController mActivityClientController;
    private ActivityOptions mActivityOptions;
    private IBinder mAssistToken;
    private CompatibilityInfo mCompatInfo;
    private Configuration mCurConfig;
    private DisplayAdjustments.FixedRotationAdjustments mFixedRotationAdjustments;
    private int mIdent;
    private ActivityInfo mInfo;
    private Intent mIntent;
    private boolean mIsForward;
    private boolean mLaunchedFromBubble;
    private Configuration mOverrideConfig;
    private List<ReferrerIntent> mPendingNewIntents;
    private List<ResultInfo> mPendingResults;
    private PersistableBundle mPersistentState;
    private int mProcState;
    private ProfilerInfo mProfilerInfo;
    private String mReferrer;
    private IBinder mShareableActivityToken;
    private Bundle mState;
    private IVoiceInteractor mVoiceInteractor;

    @Override // android.app.servertransaction.BaseClientRequest
    public void preExecute(ClientTransactionHandler client, IBinder token) {
        ActivityThread.ActivityClientRecord r = new ActivityThread.ActivityClientRecord(token, this.mIntent, this.mIdent, this.mInfo, this.mOverrideConfig, this.mCompatInfo, this.mReferrer, this.mVoiceInteractor, this.mState, this.mPersistentState, this.mPendingResults, this.mPendingNewIntents, this.mActivityOptions, this.mIsForward, this.mProfilerInfo, client, this.mAssistToken, this.mFixedRotationAdjustments, this.mShareableActivityToken, this.mLaunchedFromBubble);
        client.addLaunchingActivity(token, r);
        client.updateProcessState(this.mProcState, false);
        client.updatePendingConfiguration(this.mCurConfig);
        IActivityClientController iActivityClientController = this.mActivityClientController;
        if (iActivityClientController != null) {
            ActivityClient.setActivityClientController(iActivityClientController);
        }
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler client, IBinder token, PendingTransactionActions pendingActions) {
        Trace.traceBegin(64L, "activityStart");
        long startTime = SystemClock.uptimeMillis();
        ActivityThread.ActivityClientRecord r = client.getLaunchingActivity(token);
        client.handleLaunchActivity(r, pendingActions, null);
        OplusJankMonitorExtPlugin.setLaunchStageTime.call(ActivityThread.currentProcessName(), "activityStart", Long.valueOf(startTime));
        Trace.traceEnd(64L);
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void postExecute(ClientTransactionHandler client, IBinder token, PendingTransactionActions pendingActions) {
        client.removeLaunchingActivity(token);
    }

    private LaunchActivityItem() {
    }

    public static LaunchActivityItem obtain(Intent intent, int ident, ActivityInfo info, Configuration curConfig, Configuration overrideConfig, CompatibilityInfo compatInfo, String referrer, IVoiceInteractor voiceInteractor, int procState, Bundle state, PersistableBundle persistentState, List<ResultInfo> pendingResults, List<ReferrerIntent> pendingNewIntents, ActivityOptions activityOptions, boolean isForward, ProfilerInfo profilerInfo, IBinder assistToken, IActivityClientController activityClientController, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments, IBinder shareableActivityToken, boolean launchedFromBubble) {
        LaunchActivityItem instance = (LaunchActivityItem) ObjectPool.obtain(LaunchActivityItem.class);
        if (instance == null) {
            instance = new LaunchActivityItem();
        }
        setValues(instance, intent, ident, info, curConfig, overrideConfig, compatInfo, referrer, voiceInteractor, procState, state, persistentState, pendingResults, pendingNewIntents, activityOptions, isForward, profilerInfo, assistToken, activityClientController, fixedRotationAdjustments, shareableActivityToken, launchedFromBubble);
        return instance;
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        setValues(this, null, 0, null, null, null, null, null, null, 0, null, null, null, null, null, false, null, null, null, null, null, false);
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedObject(this.mIntent, flags);
        dest.writeInt(this.mIdent);
        dest.writeTypedObject(this.mInfo, flags);
        dest.writeTypedObject(this.mCurConfig, flags);
        dest.writeTypedObject(this.mOverrideConfig, flags);
        dest.writeTypedObject(this.mCompatInfo, flags);
        dest.writeString(this.mReferrer);
        dest.writeStrongInterface(this.mVoiceInteractor);
        dest.writeInt(this.mProcState);
        dest.writeBundle(this.mState);
        dest.writePersistableBundle(this.mPersistentState);
        dest.writeTypedList(this.mPendingResults, flags);
        dest.writeTypedList(this.mPendingNewIntents, flags);
        ActivityOptions activityOptions = this.mActivityOptions;
        dest.writeBundle(activityOptions != null ? activityOptions.toBundle() : null);
        dest.writeBoolean(this.mIsForward);
        dest.writeTypedObject(this.mProfilerInfo, flags);
        dest.writeStrongBinder(this.mAssistToken);
        dest.writeStrongInterface(this.mActivityClientController);
        dest.writeTypedObject(this.mFixedRotationAdjustments, flags);
        dest.writeStrongBinder(this.mShareableActivityToken);
        dest.writeBoolean(this.mLaunchedFromBubble);
    }

    private LaunchActivityItem(Parcel in) {
        setValues(this, (Intent) in.readTypedObject(Intent.CREATOR), in.readInt(), (ActivityInfo) in.readTypedObject(ActivityInfo.CREATOR), (Configuration) in.readTypedObject(Configuration.CREATOR), (Configuration) in.readTypedObject(Configuration.CREATOR), (CompatibilityInfo) in.readTypedObject(CompatibilityInfo.CREATOR), in.readString(), IVoiceInteractor.Stub.asInterface(in.readStrongBinder()), in.readInt(), in.readBundle(getClass().getClassLoader()), in.readPersistableBundle(getClass().getClassLoader()), in.createTypedArrayList(ResultInfo.CREATOR), in.createTypedArrayList(ReferrerIntent.CREATOR), ActivityOptions.fromBundle(in.readBundle()), in.readBoolean(), (ProfilerInfo) in.readTypedObject(ProfilerInfo.CREATOR), in.readStrongBinder(), IActivityClientController.Stub.asInterface(in.readStrongBinder()), (DisplayAdjustments.FixedRotationAdjustments) in.readTypedObject(DisplayAdjustments.FixedRotationAdjustments.CREATOR), in.readStrongBinder(), in.readBoolean());
    }

    public boolean equals(Object o) {
        boolean intentsEqual;
        boolean z;
        boolean z2;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LaunchActivityItem other = (LaunchActivityItem) o;
        Intent intent = this.mIntent;
        if (!(intent == null && other.mIntent == null) && (intent == null || !intent.filterEquals(other.mIntent))) {
            intentsEqual = false;
        } else {
            intentsEqual = true;
        }
        if (intentsEqual && this.mIdent == other.mIdent && activityInfoEqual(other.mInfo) && Objects.equals(this.mCurConfig, other.mCurConfig) && Objects.equals(this.mOverrideConfig, other.mOverrideConfig) && Objects.equals(this.mCompatInfo, other.mCompatInfo) && Objects.equals(this.mReferrer, other.mReferrer) && this.mProcState == other.mProcState && areBundlesEqualRoughly(this.mState, other.mState) && areBundlesEqualRoughly(this.mPersistentState, other.mPersistentState) && Objects.equals(this.mPendingResults, other.mPendingResults) && Objects.equals(this.mPendingNewIntents, other.mPendingNewIntents)) {
            if (this.mActivityOptions == null) {
                z = true;
            } else {
                z = false;
            }
            if (other.mActivityOptions == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z == z2 && this.mIsForward == other.mIsForward && Objects.equals(this.mProfilerInfo, other.mProfilerInfo) && Objects.equals(this.mAssistToken, other.mAssistToken) && Objects.equals(this.mFixedRotationAdjustments, other.mFixedRotationAdjustments) && Objects.equals(this.mShareableActivityToken, other.mShareableActivityToken)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int result = (17 * 31) + this.mIntent.filterHashCode();
        return (((((((((((((((((((((((((((((((result * 31) + this.mIdent) * 31) + Objects.hashCode(this.mCurConfig)) * 31) + Objects.hashCode(this.mOverrideConfig)) * 31) + Objects.hashCode(this.mCompatInfo)) * 31) + Objects.hashCode(this.mReferrer)) * 31) + Objects.hashCode(Integer.valueOf(this.mProcState))) * 31) + getRoughBundleHashCode(this.mState)) * 31) + getRoughBundleHashCode(this.mPersistentState)) * 31) + Objects.hashCode(this.mPendingResults)) * 31) + Objects.hashCode(this.mPendingNewIntents)) * 31) + (this.mActivityOptions != null ? 1 : 0)) * 31) + (this.mIsForward ? 1 : 0)) * 31) + Objects.hashCode(this.mProfilerInfo)) * 31) + Objects.hashCode(this.mAssistToken)) * 31) + Objects.hashCode(this.mFixedRotationAdjustments)) * 31) + Objects.hashCode(this.mShareableActivityToken);
    }

    private boolean activityInfoEqual(ActivityInfo other) {
        ActivityInfo activityInfo = this.mInfo;
        return activityInfo == null ? other == null : other != null && activityInfo.flags == other.flags && this.mInfo.getMaxAspectRatio() == other.getMaxAspectRatio() && Objects.equals(this.mInfo.launchToken, other.launchToken) && Objects.equals(this.mInfo.getComponentName(), other.getComponentName());
    }

    private static int getRoughBundleHashCode(BaseBundle bundle) {
        return (bundle == null || bundle.isDefinitelyEmpty()) ? 0 : 1;
    }

    private static boolean areBundlesEqualRoughly(BaseBundle a, BaseBundle b) {
        return getRoughBundleHashCode(a) == getRoughBundleHashCode(b);
    }

    public String toString() {
        return "LaunchActivityItem{intent=" + this.mIntent + ",ident=" + this.mIdent + ",info=" + this.mInfo + ",curConfig=" + this.mCurConfig + ",overrideConfig=" + this.mOverrideConfig + ",referrer=" + this.mReferrer + ",procState=" + this.mProcState + ",state=" + this.mState + ",persistentState=" + this.mPersistentState + ",pendingResults=" + this.mPendingResults + ",pendingNewIntents=" + this.mPendingNewIntents + ",options=" + this.mActivityOptions + ",profilerInfo=" + this.mProfilerInfo + ",assistToken=" + this.mAssistToken + ",rotationAdj=" + this.mFixedRotationAdjustments + ",shareableActivityToken=" + this.mShareableActivityToken + "}";
    }

    private static void setValues(LaunchActivityItem instance, Intent intent, int ident, ActivityInfo info, Configuration curConfig, Configuration overrideConfig, CompatibilityInfo compatInfo, String referrer, IVoiceInteractor voiceInteractor, int procState, Bundle state, PersistableBundle persistentState, List<ResultInfo> pendingResults, List<ReferrerIntent> pendingNewIntents, ActivityOptions activityOptions, boolean isForward, ProfilerInfo profilerInfo, IBinder assistToken, IActivityClientController activityClientController, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments, IBinder shareableActivityToken, boolean launchedFromBubble) {
        instance.mIntent = intent;
        instance.mIdent = ident;
        instance.mInfo = info;
        instance.mCurConfig = curConfig;
        instance.mOverrideConfig = overrideConfig;
        instance.mCompatInfo = compatInfo;
        instance.mReferrer = referrer;
        instance.mVoiceInteractor = voiceInteractor;
        instance.mProcState = procState;
        instance.mState = state;
        instance.mPersistentState = persistentState;
        instance.mPendingResults = pendingResults;
        instance.mPendingNewIntents = pendingNewIntents;
        instance.mActivityOptions = activityOptions;
        instance.mIsForward = isForward;
        instance.mProfilerInfo = profilerInfo;
        instance.mAssistToken = assistToken;
        instance.mActivityClientController = activityClientController;
        instance.mFixedRotationAdjustments = fixedRotationAdjustments;
        instance.mShareableActivityToken = shareableActivityToken;
        instance.mLaunchedFromBubble = launchedFromBubble;
    }
}
