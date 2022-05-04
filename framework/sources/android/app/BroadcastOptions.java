package android.app;

import android.annotation.SystemApi;
import android.os.Bundle;
@SystemApi
/* loaded from: classes.dex */
public class BroadcastOptions {
    private static final String KEY_ALLOW_BACKGROUND_ACTIVITY_STARTS = "android:broadcast.allowBackgroundActivityStarts";
    private static final String KEY_DONT_SEND_TO_RESTRICTED_APPS = "android:broadcast.dontSendToRestrictedApps";
    private static final String KEY_MAX_MANIFEST_RECEIVER_API_LEVEL = "android:broadcast.maxManifestReceiverApiLevel";
    private static final String KEY_MIN_MANIFEST_RECEIVER_API_LEVEL = "android:broadcast.minManifestReceiverApiLevel";
    private static final String KEY_TEMPORARY_APP_ALLOWLIST_DURATION = "android:broadcast.temporaryAppAllowlistDuration";
    private static final String KEY_TEMPORARY_APP_ALLOWLIST_REASON = "android:broadcast.temporaryAppAllowlistReason";
    private static final String KEY_TEMPORARY_APP_ALLOWLIST_REASON_CODE = "android:broadcast.temporaryAppAllowlistReasonCode";
    private static final String KEY_TEMPORARY_APP_ALLOWLIST_TYPE = "android:broadcast.temporaryAppAllowlistType";
    @Deprecated
    public static final int TEMPORARY_WHITELIST_TYPE_FOREGROUND_SERVICE_ALLOWED = 0;
    @Deprecated
    public static final int TEMPORARY_WHITELIST_TYPE_FOREGROUND_SERVICE_NOT_ALLOWED = 1;
    private boolean mAllowBackgroundActivityStarts;
    private boolean mDontSendToRestrictedApps;
    private int mMaxManifestReceiverApiLevel;
    private int mMinManifestReceiverApiLevel;
    private long mTemporaryAppAllowlistDuration;
    private String mTemporaryAppAllowlistReason;
    private int mTemporaryAppAllowlistReasonCode;
    private int mTemporaryAppAllowlistType;

    public static BroadcastOptions makeBasic() {
        BroadcastOptions opts = new BroadcastOptions();
        return opts;
    }

    private BroadcastOptions() {
        this.mMinManifestReceiverApiLevel = 0;
        this.mMaxManifestReceiverApiLevel = 10000;
        this.mDontSendToRestrictedApps = false;
        resetTemporaryAppAllowlist();
    }

    public BroadcastOptions(Bundle opts) {
        this.mMinManifestReceiverApiLevel = 0;
        this.mMaxManifestReceiverApiLevel = 10000;
        this.mDontSendToRestrictedApps = false;
        if (opts.containsKey(KEY_TEMPORARY_APP_ALLOWLIST_DURATION)) {
            this.mTemporaryAppAllowlistDuration = opts.getLong(KEY_TEMPORARY_APP_ALLOWLIST_DURATION);
            this.mTemporaryAppAllowlistType = opts.getInt(KEY_TEMPORARY_APP_ALLOWLIST_TYPE);
            this.mTemporaryAppAllowlistReasonCode = opts.getInt(KEY_TEMPORARY_APP_ALLOWLIST_REASON_CODE, 0);
            this.mTemporaryAppAllowlistReason = opts.getString(KEY_TEMPORARY_APP_ALLOWLIST_REASON);
        } else {
            resetTemporaryAppAllowlist();
        }
        this.mMinManifestReceiverApiLevel = opts.getInt(KEY_MIN_MANIFEST_RECEIVER_API_LEVEL, 0);
        this.mMaxManifestReceiverApiLevel = opts.getInt(KEY_MAX_MANIFEST_RECEIVER_API_LEVEL, 10000);
        this.mDontSendToRestrictedApps = opts.getBoolean(KEY_DONT_SEND_TO_RESTRICTED_APPS, false);
        this.mAllowBackgroundActivityStarts = opts.getBoolean(KEY_ALLOW_BACKGROUND_ACTIVITY_STARTS, false);
    }

    @Deprecated
    public void setTemporaryAppWhitelistDuration(long duration) {
        setTemporaryAppAllowlist(duration, 0, 0, null);
    }

    public void setTemporaryAppAllowlist(long duration, int type, int reasonCode, String reason) {
        this.mTemporaryAppAllowlistDuration = duration;
        this.mTemporaryAppAllowlistType = type;
        this.mTemporaryAppAllowlistReasonCode = reasonCode;
        this.mTemporaryAppAllowlistReason = reason;
        if (!isTemporaryAppAllowlistSet()) {
            resetTemporaryAppAllowlist();
        }
    }

    private boolean isTemporaryAppAllowlistSet() {
        return this.mTemporaryAppAllowlistDuration > 0 && this.mTemporaryAppAllowlistType != -1;
    }

    private void resetTemporaryAppAllowlist() {
        this.mTemporaryAppAllowlistDuration = 0L;
        this.mTemporaryAppAllowlistType = -1;
        this.mTemporaryAppAllowlistReasonCode = 0;
        this.mTemporaryAppAllowlistReason = null;
    }

    public long getTemporaryAppAllowlistDuration() {
        return this.mTemporaryAppAllowlistDuration;
    }

    public int getTemporaryAppAllowlistType() {
        return this.mTemporaryAppAllowlistType;
    }

    public int getTemporaryAppAllowlistReasonCode() {
        return this.mTemporaryAppAllowlistReasonCode;
    }

    public String getTemporaryAppAllowlistReason() {
        return this.mTemporaryAppAllowlistReason;
    }

    public void setMinManifestReceiverApiLevel(int apiLevel) {
        this.mMinManifestReceiverApiLevel = apiLevel;
    }

    public int getMinManifestReceiverApiLevel() {
        return this.mMinManifestReceiverApiLevel;
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public void setMaxManifestReceiverApiLevel(int apiLevel) {
        this.mMaxManifestReceiverApiLevel = apiLevel;
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public int getMaxManifestReceiverApiLevel() {
        return this.mMaxManifestReceiverApiLevel;
    }

    public void setDontSendToRestrictedApps(boolean dontSendToRestrictedApps) {
        this.mDontSendToRestrictedApps = dontSendToRestrictedApps;
    }

    public boolean isDontSendToRestrictedApps() {
        return this.mDontSendToRestrictedApps;
    }

    public void setBackgroundActivityStartsAllowed(boolean allowBackgroundActivityStarts) {
        this.mAllowBackgroundActivityStarts = allowBackgroundActivityStarts;
    }

    public boolean allowsBackgroundActivityStarts() {
        return this.mAllowBackgroundActivityStarts;
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        if (isTemporaryAppAllowlistSet()) {
            b.putLong(KEY_TEMPORARY_APP_ALLOWLIST_DURATION, this.mTemporaryAppAllowlistDuration);
            b.putInt(KEY_TEMPORARY_APP_ALLOWLIST_TYPE, this.mTemporaryAppAllowlistType);
            b.putInt(KEY_TEMPORARY_APP_ALLOWLIST_REASON_CODE, this.mTemporaryAppAllowlistReasonCode);
            b.putString(KEY_TEMPORARY_APP_ALLOWLIST_REASON, this.mTemporaryAppAllowlistReason);
        }
        int i = this.mMinManifestReceiverApiLevel;
        if (i != 0) {
            b.putInt(KEY_MIN_MANIFEST_RECEIVER_API_LEVEL, i);
        }
        int i2 = this.mMaxManifestReceiverApiLevel;
        if (i2 != 10000) {
            b.putInt(KEY_MAX_MANIFEST_RECEIVER_API_LEVEL, i2);
        }
        if (this.mDontSendToRestrictedApps) {
            b.putBoolean(KEY_DONT_SEND_TO_RESTRICTED_APPS, true);
        }
        if (this.mAllowBackgroundActivityStarts) {
            b.putBoolean(KEY_ALLOW_BACKGROUND_ACTIVITY_STARTS, true);
        }
        if (b.isEmpty()) {
            return null;
        }
        return b;
    }
}
