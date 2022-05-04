package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.annotation.StringRes;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ParsedAttribution implements Parcelable {
    public static final Parcelable.Creator<ParsedAttribution> CREATOR = new Parcelable.Creator<ParsedAttribution>() { // from class: android.content.pm.parsing.component.ParsedAttribution.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedAttribution[] newArray(int size) {
            return new ParsedAttribution[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedAttribution createFromParcel(Parcel in) {
            return new ParsedAttribution(in);
        }
    };
    public static final int MAX_ATTRIBUTION_TAG_LEN = 50;
    private static final int MAX_NUM_ATTRIBUTIONS = 10000;
    public final List<String> inheritFrom;
    public final int label;
    public final String tag;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Max {
    }

    public static boolean isCombinationValid(List<ParsedAttribution> attributions) {
        if (attributions == null) {
            return true;
        }
        ArraySet<String> attributionTags = new ArraySet<>(attributions.size());
        ArraySet<String> inheritFromAttributionTags = new ArraySet<>();
        int numAttributions = attributions.size();
        if (numAttributions > 10000) {
            return false;
        }
        for (int attributionNum = 0; attributionNum < numAttributions; attributionNum++) {
            boolean wasAdded = attributionTags.add(attributions.get(attributionNum).tag);
            if (!wasAdded) {
                return false;
            }
        }
        for (int attributionNum2 = 0; attributionNum2 < numAttributions; attributionNum2++) {
            ParsedAttribution feature = attributions.get(attributionNum2);
            int numInheritFrom = feature.inheritFrom.size();
            for (int inheritFromNum = 0; inheritFromNum < numInheritFrom; inheritFromNum++) {
                String inheritFrom = feature.inheritFrom.get(inheritFromNum);
                if (attributionTags.contains(inheritFrom)) {
                    return false;
                }
                boolean wasAdded2 = inheritFromAttributionTags.add(inheritFrom);
                if (!wasAdded2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String maxToString(int value) {
        switch (value) {
            case 50:
                return "MAX_ATTRIBUTION_TAG_LEN";
            case 10000:
                return "MAX_NUM_ATTRIBUTIONS";
            default:
                return Integer.toHexString(value);
        }
    }

    public ParsedAttribution(String tag, int label, List<String> inheritFrom) {
        this.tag = tag;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) tag);
        this.label = label;
        AnnotationValidations.validate((Class<? extends Annotation>) StringRes.class, (Annotation) null, label);
        this.inheritFrom = inheritFrom;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) inheritFrom);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeInt(this.label);
        dest.writeStringList(this.inheritFrom);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ParsedAttribution(Parcel in) {
        String _tag = in.readString();
        int _label = in.readInt();
        List<String> _inheritFrom = new ArrayList<>();
        in.readStringList(_inheritFrom);
        this.tag = _tag;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) _tag);
        this.label = _label;
        AnnotationValidations.validate((Class<? extends Annotation>) StringRes.class, (Annotation) null, _label);
        this.inheritFrom = _inheritFrom;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) _inheritFrom);
    }

    @Deprecated
    private void __metadata() {
    }
}
