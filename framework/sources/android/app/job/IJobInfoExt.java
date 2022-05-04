package android.app.job;
/* loaded from: classes.dex */
public interface IJobInfoExt {
    public static final int LEVEL_CPU_ABNORMAL_HEAVY = 3;
    public static final int LEVEL_CPU_ABNORMAL_MIDDLE = 2;
    public static final int LEVEL_CPU_ABNORMAL_SLIGHT = 1;
    public static final int LEVEL_CPU_NORMAL = 0;
    public static final int SCENE_MODE_GAME = 4;
    public static final int SCENE_MODE_VIDEO = 1;
    public static final int SCENE_MODE_VIDEO_CALL = 2;
    public static final String TAG = "JobInfoExt";
    public static final int TYPE_PROTECT_FORE_FRAME = 0;
    public static final int TYPE_PROTECT_FORE_NET = 1;

    default boolean getBooleanConstraint(String type, boolean defValue) {
        return defValue;
    }

    default String getStringConstraint(String type, String defValue) {
        return defValue;
    }

    default int getIntConstraint(String type, int defValue) {
        return defValue;
    }

    default long getLongConstraint(String type, long defValue) {
        return defValue;
    }

    default void setBooleanConstraint(String type, boolean value) {
    }

    default void setStringConstraint(String type, String value) {
    }

    default void setIntConstraint(String type, int value) {
    }

    default void setLongConstraint(JobInfo job, String type, long value) {
    }

    default void initJobInfo(Object in) {
    }

    default void initJobInfoPure(JobBuilderExt jobBuilderExt) {
    }

    default void writeToParcelJobInfo(Object out, int flags) {
    }

    /* loaded from: classes.dex */
    public static class JobBuilderExt {
        public boolean mHasCpuConstraint;
        public boolean mHasProtectSceneConstraint;
        public boolean mHasTemperatureConstraint;
        public boolean mIsFastIdle;
        public boolean mIsOplusJob;
        public String mOplusExtraStr;
        public int mProtectForeType;
        public int mProtectScene;
        public boolean mRequiresBattIdle;
        public boolean mRequiresProtectFore;

        public JobBuilderExt setRequiresBattIdle(boolean requiresBattIdle, int extra) {
            this.mRequiresBattIdle = requiresBattIdle;
            return this;
        }

        public JobBuilderExt setFastIdle(boolean isFastIdle) {
            this.mIsFastIdle = isFastIdle;
            return this;
        }

        public JobBuilderExt setOplusJob(boolean isOplusJob) {
            this.mIsOplusJob = isOplusJob;
            return this;
        }

        public JobBuilderExt setRequiresProtectFore(boolean requiresProtectFore) {
            setRequiresProtectFore(requiresProtectFore, 0);
            return this;
        }

        public JobBuilderExt setRequiresProtectFore(boolean requiresProtectFore, int type) {
            this.mRequiresProtectFore = requiresProtectFore;
            this.mProtectForeType = type;
            return this;
        }

        public JobBuilderExt setHasCpuConstraint(boolean hasCpuConstraint) {
            this.mHasCpuConstraint = hasCpuConstraint;
            return this;
        }

        public JobBuilderExt setOplusExtraStr(String str) {
            this.mOplusExtraStr = str;
            return this;
        }

        public JobBuilderExt setHasTemperatureConstraint(boolean hasTemperatureConstraint) {
            this.mHasTemperatureConstraint = hasTemperatureConstraint;
            return this;
        }

        public JobBuilderExt setRequiresProtectScene(boolean requiresProtectScene, int protectScene) {
            this.mHasProtectSceneConstraint = requiresProtectScene;
            this.mProtectScene = protectScene;
            return this;
        }
    }
}
