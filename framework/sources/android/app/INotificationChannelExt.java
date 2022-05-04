package android.app;

import android.os.Parcel;
import android.telephony.DataFailCause;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
/* loaded from: classes.dex */
public interface INotificationChannelExt {
    static int[] getLockableFields(int[] originalLockableFields) {
        int[] addedLockableFields = {65537, 65538, 65600, 65540, DataFailCause.NO_DEFAULT_DATA, 65552, 65568, 65664, 65792};
        int originalLockableFieldsLength = originalLockableFields.length;
        int addedLockableFieldsLength = addedLockableFields.length;
        int[] newLockableFields = new int[originalLockableFieldsLength + addedLockableFieldsLength];
        System.arraycopy(originalLockableFields, 0, newLockableFields, 0, originalLockableFieldsLength);
        System.arraycopy(addedLockableFields, 0, newLockableFields, originalLockableFieldsLength, addedLockableFieldsLength);
        return newLockableFields;
    }

    default void init(String id, CharSequence name, int importance) {
    }

    default void readFromParcel(Parcel in) {
    }

    default void writeToParcel(Parcel dest, int flags) {
    }

    default boolean canShowBanner() {
        return false;
    }

    default void setShowBanner(boolean show) {
    }

    default boolean isUnimportant() {
        return false;
    }

    default void setUnimportant(boolean isUnimportant) {
    }

    default boolean isFold() {
        return false;
    }

    default void setFold(boolean fold) {
    }

    default void setOpush(boolean isOpush) {
    }

    default boolean isOpush() {
        return false;
    }

    default void setMaxMessage(int max) {
    }

    default int getMaxMessages() {
        return -1;
    }

    default void setShowIcon(boolean show) {
    }

    default boolean canShowIcon() {
        return false;
    }

    default void setSupportNumBadge(boolean supportNumBadge) {
    }

    default boolean isSupportNumBadge() {
        return false;
    }

    default void setBadgeOption(int badgeOption) {
    }

    default int getBadgeOption() {
        return 0;
    }

    default boolean isChangeableFold() {
        return false;
    }

    default void setChangeableFold(boolean changeable) {
    }

    default boolean isChangeableShowIcon() {
        return false;
    }

    default void setChangeableShowIcon(boolean changeable) {
    }

    default void populateFromXml(XmlPullParser parser) {
    }

    default void writeXml(XmlSerializer out) throws IOException {
    }

    default void toJson(JSONObject record) throws JSONException {
    }

    default boolean equals(INotificationChannelExt that) {
        return true;
    }

    default int hashCode(int result) {
        return 0;
    }
}
