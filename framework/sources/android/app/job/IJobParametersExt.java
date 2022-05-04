package android.app.job;
/* loaded from: classes.dex */
public interface IJobParametersExt {
    public static final String TAG = "JobParametersExt";

    default void setIntValue(String type, int value) {
    }

    default void setStringValue(String type, String value) {
    }

    default int getIntValue(String type, int defValue) {
        return defValue;
    }

    default String getStringValue(String type, String defValue) {
        return defValue;
    }

    default void initJobParameters(Object in) {
    }

    default void writeToParcelJobParameters(Object dest) {
    }
}
