package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import java.util.Objects;
/* loaded from: classes.dex */
public class ContentProviderOperation implements Parcelable {
    public static final Parcelable.Creator<ContentProviderOperation> CREATOR = new Parcelable.Creator<ContentProviderOperation>() { // from class: android.content.ContentProviderOperation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentProviderOperation createFromParcel(Parcel source) {
            return new ContentProviderOperation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentProviderOperation[] newArray(int size) {
            return new ContentProviderOperation[size];
        }
    };
    private static final String TAG = "ContentProviderOperation";
    public static final int TYPE_ASSERT = 4;
    public static final int TYPE_CALL = 5;
    public static final int TYPE_DELETE = 3;
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_UPDATE = 2;
    private final String mArg;
    private final boolean mExceptionAllowed;
    private final Integer mExpectedCount;
    private final ArrayMap<String, Object> mExtras;
    private final String mMethod;
    private final String mSelection;
    private final SparseArray<Object> mSelectionArgs;
    private final int mType;
    private final Uri mUri;
    private final ArrayMap<String, Object> mValues;
    private final boolean mYieldAllowed;

    private ContentProviderOperation(Builder builder) {
        this.mType = builder.mType;
        this.mUri = builder.mUri;
        this.mMethod = builder.mMethod;
        this.mArg = builder.mArg;
        this.mValues = builder.mValues;
        this.mExtras = builder.mExtras;
        this.mSelection = builder.mSelection;
        this.mSelectionArgs = builder.mSelectionArgs;
        this.mExpectedCount = builder.mExpectedCount;
        this.mYieldAllowed = builder.mYieldAllowed;
        this.mExceptionAllowed = builder.mExceptionAllowed;
    }

    private ContentProviderOperation(Parcel source) {
        this.mType = source.readInt();
        this.mUri = Uri.CREATOR.createFromParcel(source);
        Integer num = null;
        this.mMethod = source.readInt() != 0 ? source.readString8() : null;
        this.mArg = source.readInt() != 0 ? source.readString8() : null;
        int valuesSize = source.readInt();
        if (valuesSize != -1) {
            ArrayMap<String, Object> arrayMap = new ArrayMap<>(valuesSize);
            this.mValues = arrayMap;
            source.readArrayMap(arrayMap, null);
        } else {
            this.mValues = null;
        }
        int extrasSize = source.readInt();
        if (extrasSize != -1) {
            ArrayMap<String, Object> arrayMap2 = new ArrayMap<>(extrasSize);
            this.mExtras = arrayMap2;
            source.readArrayMap(arrayMap2, null);
        } else {
            this.mExtras = null;
        }
        this.mSelection = source.readInt() != 0 ? source.readString8() : null;
        this.mSelectionArgs = source.readSparseArray(null);
        this.mExpectedCount = source.readInt() != 0 ? Integer.valueOf(source.readInt()) : num;
        boolean z = true;
        this.mYieldAllowed = source.readInt() != 0;
        this.mExceptionAllowed = source.readInt() == 0 ? false : z;
    }

    public ContentProviderOperation(ContentProviderOperation cpo, Uri withUri) {
        this.mType = cpo.mType;
        this.mUri = withUri;
        this.mMethod = cpo.mMethod;
        this.mArg = cpo.mArg;
        this.mValues = cpo.mValues;
        this.mExtras = cpo.mExtras;
        this.mSelection = cpo.mSelection;
        this.mSelectionArgs = cpo.mSelectionArgs;
        this.mExpectedCount = cpo.mExpectedCount;
        this.mYieldAllowed = cpo.mYieldAllowed;
        this.mExceptionAllowed = cpo.mExceptionAllowed;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mType);
        Uri.writeToParcel(dest, this.mUri);
        if (this.mMethod != null) {
            dest.writeInt(1);
            dest.writeString8(this.mMethod);
        } else {
            dest.writeInt(0);
        }
        if (this.mArg != null) {
            dest.writeInt(1);
            dest.writeString8(this.mArg);
        } else {
            dest.writeInt(0);
        }
        ArrayMap<String, Object> arrayMap = this.mValues;
        if (arrayMap != null) {
            dest.writeInt(arrayMap.size());
            dest.writeArrayMap(this.mValues);
        } else {
            dest.writeInt(-1);
        }
        ArrayMap<String, Object> arrayMap2 = this.mExtras;
        if (arrayMap2 != null) {
            dest.writeInt(arrayMap2.size());
            dest.writeArrayMap(this.mExtras);
        } else {
            dest.writeInt(-1);
        }
        if (this.mSelection != null) {
            dest.writeInt(1);
            dest.writeString8(this.mSelection);
        } else {
            dest.writeInt(0);
        }
        dest.writeSparseArray(this.mSelectionArgs);
        if (this.mExpectedCount != null) {
            dest.writeInt(1);
            dest.writeInt(this.mExpectedCount.intValue());
        } else {
            dest.writeInt(0);
        }
        dest.writeInt(this.mYieldAllowed ? 1 : 0);
        dest.writeInt(this.mExceptionAllowed ? 1 : 0);
    }

    public static Builder newInsert(Uri uri) {
        return new Builder(1, uri);
    }

    public static Builder newUpdate(Uri uri) {
        return new Builder(2, uri);
    }

    public static Builder newDelete(Uri uri) {
        return new Builder(3, uri);
    }

    public static Builder newAssertQuery(Uri uri) {
        return new Builder(4, uri);
    }

    public static Builder newCall(Uri uri, String method, String arg) {
        return new Builder(5, uri, method, arg);
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean isYieldAllowed() {
        return this.mYieldAllowed;
    }

    public boolean isExceptionAllowed() {
        return this.mExceptionAllowed;
    }

    public int getType() {
        return this.mType;
    }

    public boolean isInsert() {
        return this.mType == 1;
    }

    public boolean isDelete() {
        return this.mType == 3;
    }

    public boolean isUpdate() {
        return this.mType == 2;
    }

    public boolean isAssertQuery() {
        return this.mType == 4;
    }

    public boolean isCall() {
        return this.mType == 5;
    }

    public boolean isWriteOperation() {
        int i = this.mType;
        return i == 3 || i == 1 || i == 2;
    }

    public boolean isReadOperation() {
        return this.mType == 4;
    }

    public ContentProviderResult apply(ContentProvider provider, ContentProviderResult[] backRefs, int numBackRefs) throws OperationApplicationException {
        if (!this.mExceptionAllowed) {
            return applyInternal(provider, backRefs, numBackRefs);
        }
        try {
            return applyInternal(provider, backRefs, numBackRefs);
        } catch (Exception e) {
            return new ContentProviderResult(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d4, code lost:
        if (r2 != null) goto L_0x00d6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00da, code lost:
        if (r3.moveToNext() == false) goto L_0x011d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00dc, code lost:
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00de, code lost:
        if (r5 >= r2.length) goto L_0x00d6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e0, code lost:
        r6 = r3.getString(r5);
        r7 = r0.getAsString(r2[r5]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ee, code lost:
        if (android.text.TextUtils.equals(r6, r7) == false) goto L_0x00f3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f0, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x011b, code lost:
        throw new android.content.OperationApplicationException("Found value " + r6 + " when expected " + r7 + " for column " + r2[r5]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x011d, code lost:
        r3.close();
        r2 = r4;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.ContentProviderResult applyInternal(android.content.ContentProvider r12, android.content.ContentProviderResult[] r13, int r14) throws android.content.OperationApplicationException {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.ContentProviderOperation.applyInternal(android.content.ContentProvider, android.content.ContentProviderResult[], int):android.content.ContentProviderResult");
    }

    public ContentValues resolveValueBackReferences(ContentProviderResult[] backRefs, int numBackRefs) {
        Object resolved;
        if (this.mValues == null) {
            return null;
        }
        ContentValues values = new ContentValues();
        for (int i = 0; i < this.mValues.size(); i++) {
            Object value = this.mValues.valueAt(i);
            if (value instanceof BackReference) {
                resolved = ((BackReference) value).resolve(backRefs, numBackRefs);
            } else {
                resolved = value;
            }
            values.putObject(this.mValues.keyAt(i), resolved);
        }
        return values;
    }

    public Bundle resolveExtrasBackReferences(ContentProviderResult[] backRefs, int numBackRefs) {
        Object resolved;
        if (this.mExtras == null) {
            return null;
        }
        Bundle extras = new Bundle();
        for (int i = 0; i < this.mExtras.size(); i++) {
            Object value = this.mExtras.valueAt(i);
            if (value instanceof BackReference) {
                resolved = ((BackReference) value).resolve(backRefs, numBackRefs);
            } else {
                resolved = value;
            }
            extras.putObject(this.mExtras.keyAt(i), resolved);
        }
        return extras;
    }

    public String[] resolveSelectionArgsBackReferences(ContentProviderResult[] backRefs, int numBackRefs) {
        Object resolved;
        if (this.mSelectionArgs == null) {
            return null;
        }
        int max = -1;
        for (int i = 0; i < this.mSelectionArgs.size(); i++) {
            max = Math.max(max, this.mSelectionArgs.keyAt(i));
        }
        int i2 = max + 1;
        String[] selectionArgs = new String[i2];
        for (int i3 = 0; i3 < this.mSelectionArgs.size(); i3++) {
            Object value = this.mSelectionArgs.valueAt(i3);
            if (value instanceof BackReference) {
                resolved = ((BackReference) value).resolve(backRefs, numBackRefs);
            } else {
                resolved = value;
            }
            selectionArgs[this.mSelectionArgs.keyAt(i3)] = String.valueOf(resolved);
        }
        return selectionArgs;
    }

    public static String typeToString(int type) {
        switch (type) {
            case 1:
                return "insert";
            case 2:
                return "update";
            case 3:
                return "delete";
            case 4:
                return "assert";
            case 5:
                return "call";
            default:
                return Integer.toString(type);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ContentProviderOperation(");
        sb.append("type=");
        sb.append(typeToString(this.mType));
        sb.append(' ');
        if (this.mUri != null) {
            sb.append("uri=");
            sb.append(this.mUri);
            sb.append(' ');
        }
        if (this.mValues != null) {
            sb.append("values=");
            sb.append(this.mValues);
            sb.append(' ');
        }
        if (this.mSelection != null) {
            sb.append("selection=");
            sb.append(this.mSelection);
            sb.append(' ');
        }
        if (this.mSelectionArgs != null) {
            sb.append("selectionArgs=");
            sb.append(this.mSelectionArgs);
            sb.append(' ');
        }
        if (this.mExpectedCount != null) {
            sb.append("expectedCount=");
            sb.append(this.mExpectedCount);
            sb.append(' ');
        }
        if (this.mYieldAllowed) {
            sb.append("yieldAllowed ");
        }
        if (this.mExceptionAllowed) {
            sb.append("exceptionAllowed ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* loaded from: classes.dex */
    public static class BackReference implements Parcelable {
        public static final Parcelable.Creator<BackReference> CREATOR = new Parcelable.Creator<BackReference>() { // from class: android.content.ContentProviderOperation.BackReference.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BackReference createFromParcel(Parcel source) {
                return new BackReference(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BackReference[] newArray(int size) {
                return new BackReference[size];
            }
        };
        private final int fromIndex;
        private final String fromKey;

        private BackReference(int fromIndex, String fromKey) {
            this.fromIndex = fromIndex;
            this.fromKey = fromKey;
        }

        public BackReference(Parcel src) {
            this.fromIndex = src.readInt();
            if (src.readInt() != 0) {
                this.fromKey = src.readString8();
            } else {
                this.fromKey = null;
            }
        }

        public Object resolve(ContentProviderResult[] backRefs, int numBackRefs) {
            int i = this.fromIndex;
            if (i < numBackRefs) {
                ContentProviderResult backRef = backRefs[i];
                if (backRef.extras != null) {
                    Object backRefValue = backRef.extras.get(this.fromKey);
                    return backRefValue;
                }
                Object backRefValue2 = backRef.uri;
                if (backRefValue2 != null) {
                    Object backRefValue3 = Long.valueOf(ContentUris.parseId(backRef.uri));
                    return backRefValue3;
                }
                Object backRefValue4 = Long.valueOf(backRef.count.intValue());
                return backRefValue4;
            }
            Log.e(ContentProviderOperation.TAG, toString());
            throw new ArrayIndexOutOfBoundsException("asked for back ref " + this.fromIndex + " but there are only " + numBackRefs + " back refs");
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.fromIndex);
            if (this.fromKey != null) {
                dest.writeInt(1);
                dest.writeString8(this.fromKey);
                return;
            }
            dest.writeInt(0);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private final String mArg;
        private boolean mExceptionAllowed;
        private Integer mExpectedCount;
        private ArrayMap<String, Object> mExtras;
        private final String mMethod;
        private String mSelection;
        private SparseArray<Object> mSelectionArgs;
        private final int mType;
        private final Uri mUri;
        private ArrayMap<String, Object> mValues;
        private boolean mYieldAllowed;

        private Builder(int type, Uri uri) {
            this(type, uri, null, null);
        }

        private Builder(int type, Uri uri, String method, String arg) {
            this.mType = type;
            Objects.requireNonNull(uri);
            this.mUri = uri;
            this.mMethod = method;
            this.mArg = arg;
        }

        public ContentProviderOperation build() {
            ArrayMap<String, Object> arrayMap;
            ArrayMap<String, Object> arrayMap2;
            if (this.mType == 2 && ((arrayMap2 = this.mValues) == null || arrayMap2.isEmpty())) {
                throw new IllegalArgumentException("Empty values");
            } else if (this.mType != 4 || (((arrayMap = this.mValues) != null && !arrayMap.isEmpty()) || this.mExpectedCount != null)) {
                return new ContentProviderOperation(this);
            } else {
                throw new IllegalArgumentException("Empty values");
            }
        }

        private void ensureValues() {
            if (this.mValues == null) {
                this.mValues = new ArrayMap<>();
            }
        }

        private void ensureExtras() {
            if (this.mExtras == null) {
                this.mExtras = new ArrayMap<>();
            }
        }

        private void ensureSelectionArgs() {
            if (this.mSelectionArgs == null) {
                this.mSelectionArgs = new SparseArray<>();
            }
        }

        private void setValue(String key, Object value) {
            ensureValues();
            boolean oldReference = this.mValues.get(key) instanceof BackReference;
            boolean newReference = value instanceof BackReference;
            if (!oldReference || newReference) {
                this.mValues.put(key, value);
            }
        }

        private void setExtra(String key, Object value) {
            ensureExtras();
            boolean oldReference = this.mExtras.get(key) instanceof BackReference;
            boolean newReference = value instanceof BackReference;
            if (!oldReference || newReference) {
                this.mExtras.put(key, value);
            }
        }

        private void setSelectionArg(int index, Object value) {
            ensureSelectionArgs();
            boolean oldReference = this.mSelectionArgs.get(index) instanceof BackReference;
            boolean newReference = value instanceof BackReference;
            if (!oldReference || newReference) {
                this.mSelectionArgs.put(index, value);
            }
        }

        public Builder withValues(ContentValues values) {
            assertValuesAllowed();
            ensureValues();
            ArrayMap<String, Object> rawValues = values.getValues();
            for (int i = 0; i < rawValues.size(); i++) {
                setValue(rawValues.keyAt(i), rawValues.valueAt(i));
            }
            return this;
        }

        public Builder withValue(String key, Object value) {
            assertValuesAllowed();
            if (ContentValues.isSupportedValue(value)) {
                setValue(key, value);
                return this;
            }
            throw new IllegalArgumentException("bad value type: " + value.getClass().getName());
        }

        public Builder withValueBackReferences(ContentValues backReferences) {
            assertValuesAllowed();
            ArrayMap<String, Object> rawValues = backReferences.getValues();
            for (int i = 0; i < rawValues.size(); i++) {
                setValue(rawValues.keyAt(i), new BackReference(((Integer) rawValues.valueAt(i)).intValue(), null));
            }
            return this;
        }

        public Builder withValueBackReference(String key, int fromIndex) {
            assertValuesAllowed();
            setValue(key, new BackReference(fromIndex, null));
            return this;
        }

        public Builder withValueBackReference(String key, int fromIndex, String fromKey) {
            assertValuesAllowed();
            setValue(key, new BackReference(fromIndex, fromKey));
            return this;
        }

        public Builder withExtras(Bundle extras) {
            assertExtrasAllowed();
            ensureExtras();
            for (String key : extras.keySet()) {
                setExtra(key, extras.get(key));
            }
            return this;
        }

        public Builder withExtra(String key, Object value) {
            assertExtrasAllowed();
            setExtra(key, value);
            return this;
        }

        public Builder withExtraBackReference(String key, int fromIndex) {
            assertExtrasAllowed();
            setExtra(key, new BackReference(fromIndex, null));
            return this;
        }

        public Builder withExtraBackReference(String key, int fromIndex, String fromKey) {
            assertExtrasAllowed();
            setExtra(key, new BackReference(fromIndex, fromKey));
            return this;
        }

        public Builder withSelection(String selection, String[] selectionArgs) {
            assertSelectionAllowed();
            this.mSelection = selection;
            if (selectionArgs != null) {
                ensureSelectionArgs();
                for (int i = 0; i < selectionArgs.length; i++) {
                    setSelectionArg(i, selectionArgs[i]);
                }
            }
            return this;
        }

        public Builder withSelectionBackReference(int index, int fromIndex) {
            assertSelectionAllowed();
            setSelectionArg(index, new BackReference(fromIndex, null));
            return this;
        }

        public Builder withSelectionBackReference(int index, int fromIndex, String fromKey) {
            assertSelectionAllowed();
            setSelectionArg(index, new BackReference(fromIndex, fromKey));
            return this;
        }

        public Builder withExpectedCount(int count) {
            int i = this.mType;
            if (i == 2 || i == 3 || i == 4) {
                this.mExpectedCount = Integer.valueOf(count);
                return this;
            }
            throw new IllegalArgumentException("only updates, deletes, and asserts can have expected counts");
        }

        public Builder withYieldAllowed(boolean yieldAllowed) {
            this.mYieldAllowed = yieldAllowed;
            return this;
        }

        public Builder withExceptionAllowed(boolean exceptionAllowed) {
            this.mExceptionAllowed = exceptionAllowed;
            return this;
        }

        public Builder withFailureAllowed(boolean failureAllowed) {
            return withExceptionAllowed(failureAllowed);
        }

        private void assertValuesAllowed() {
            switch (this.mType) {
                case 1:
                case 2:
                case 4:
                    return;
                case 3:
                default:
                    throw new IllegalArgumentException("Values not supported for " + ContentProviderOperation.typeToString(this.mType));
            }
        }

        private void assertSelectionAllowed() {
            switch (this.mType) {
                case 2:
                case 3:
                case 4:
                    return;
                default:
                    throw new IllegalArgumentException("Selection not supported for " + ContentProviderOperation.typeToString(this.mType));
            }
        }

        private void assertExtrasAllowed() {
            switch (this.mType) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return;
                default:
                    throw new IllegalArgumentException("Extras not supported for " + ContentProviderOperation.typeToString(this.mType));
            }
        }
    }
}
