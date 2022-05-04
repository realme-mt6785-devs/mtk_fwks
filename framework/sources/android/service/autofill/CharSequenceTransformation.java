package android.service.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.Logging.Session;
import android.util.Log;
import android.util.Pair;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import android.widget.RemoteViews;
import com.android.internal.util.Preconditions;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public final class CharSequenceTransformation extends InternalTransformation implements Transformation, Parcelable {
    public static final Parcelable.Creator<CharSequenceTransformation> CREATOR = new Parcelable.Creator<CharSequenceTransformation>() { // from class: android.service.autofill.CharSequenceTransformation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CharSequenceTransformation createFromParcel(Parcel parcel) {
            AutofillId[] ids = (AutofillId[]) parcel.readParcelableArray(null, AutofillId.class);
            Pattern[] regexs = (Pattern[]) parcel.readSerializable();
            String[] substs = parcel.createStringArray();
            Builder builder = new Builder(ids[0], regexs[0], substs[0]);
            int size = ids.length;
            for (int i = 1; i < size; i++) {
                builder.addField(ids[i], regexs[i], substs[i]);
            }
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CharSequenceTransformation[] newArray(int size) {
            return new CharSequenceTransformation[size];
        }
    };
    private static final String TAG = "CharSequenceTransformation";
    private final LinkedHashMap<AutofillId, Pair<Pattern, String>> mFields;

    private CharSequenceTransformation(Builder builder) {
        this.mFields = builder.mFields;
    }

    @Override // android.service.autofill.InternalTransformation
    public void apply(ValueFinder finder, RemoteViews parentTemplate, int childViewId) throws Exception {
        StringBuilder converted = new StringBuilder();
        int size = this.mFields.size();
        if (Helper.sDebug) {
            Log.d(TAG, size + " fields on id " + childViewId);
        }
        for (Map.Entry<AutofillId, Pair<Pattern, String>> entry : this.mFields.entrySet()) {
            AutofillId id = entry.getKey();
            Pair<Pattern, String> field = entry.getValue();
            String value = finder.findByAutofillId(id);
            if (value == null) {
                Log.w(TAG, "No value for id " + id);
                return;
            }
            try {
                Matcher matcher = field.first.matcher(value);
                if (matcher.find()) {
                    String convertedValue = matcher.replaceAll(field.second);
                    converted.append(convertedValue);
                } else if (Helper.sDebug) {
                    Log.d(TAG, "Match for " + field.first + " failed on id " + id);
                    return;
                } else {
                    return;
                }
            } catch (Exception e) {
                Log.w(TAG, "Cannot apply " + field.first.pattern() + Session.SUBSESSION_SEPARATION_CHAR + field.second + " to field with autofill id" + id + ": " + e.getClass());
                throw e;
            }
        }
        Log.d(TAG, "Converting text on child " + childViewId + " to " + converted.length() + "_chars");
        parentTemplate.setCharSequence(childViewId, "setText", converted);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private boolean mDestroyed;
        private final LinkedHashMap<AutofillId, Pair<Pattern, String>> mFields = new LinkedHashMap<>();

        public Builder(AutofillId id, Pattern regex, String subst) {
            addField(id, regex, subst);
        }

        public Builder addField(AutofillId id, Pattern regex, String subst) {
            throwIfDestroyed();
            Preconditions.checkNotNull(id);
            Preconditions.checkNotNull(regex);
            Preconditions.checkNotNull(subst);
            this.mFields.put(id, new Pair<>(regex, subst));
            return this;
        }

        public CharSequenceTransformation build() {
            throwIfDestroyed();
            this.mDestroyed = true;
            return new CharSequenceTransformation(this);
        }

        private void throwIfDestroyed() {
            Preconditions.checkState(!this.mDestroyed, "Already called build()");
        }
    }

    public String toString() {
        if (!Helper.sDebug) {
            return super.toString();
        }
        return "MultipleViewsCharSequenceTransformation: [fields=" + this.mFields + "]";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.regex.Pattern[], java.io.Serializable] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // android.os.Parcelable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeToParcel(android.os.Parcel r10, int r11) {
        /*
            r9 = this;
            java.util.LinkedHashMap<android.view.autofill.AutofillId, android.util.Pair<java.util.regex.Pattern, java.lang.String>> r0 = r9.mFields
            int r0 = r0.size()
            android.view.autofill.AutofillId[] r1 = new android.view.autofill.AutofillId[r0]
            java.util.regex.Pattern[] r2 = new java.util.regex.Pattern[r0]
            java.lang.String[] r3 = new java.lang.String[r0]
            r4 = 0
            java.util.LinkedHashMap<android.view.autofill.AutofillId, android.util.Pair<java.util.regex.Pattern, java.lang.String>> r5 = r9.mFields
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0017:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0041
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            android.view.autofill.AutofillId r7 = (android.view.autofill.AutofillId) r7
            r1[r4] = r7
            java.lang.Object r7 = r6.getValue()
            android.util.Pair r7 = (android.util.Pair) r7
            F r8 = r7.first
            java.util.regex.Pattern r8 = (java.util.regex.Pattern) r8
            r2[r4] = r8
            S r8 = r7.second
            java.lang.String r8 = (java.lang.String) r8
            r3[r4] = r8
            int r4 = r4 + 1
            goto L_0x0017
        L_0x0041:
            r10.writeParcelableArray(r1, r11)
            r10.writeSerializable(r2)
            r10.writeStringArray(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.service.autofill.CharSequenceTransformation.writeToParcel(android.os.Parcel, int):void");
    }
}
