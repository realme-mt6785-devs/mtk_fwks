package android.service.autofill;

import android.annotation.SystemApi;
import android.content.ClipData;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.autofill.Helper;
import android.widget.RemoteViews;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public final class Dataset implements Parcelable {
    public static final Parcelable.Creator<Dataset> CREATOR = new Parcelable.Creator<Dataset>() { // from class: android.service.autofill.Dataset.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Dataset createFromParcel(Parcel parcel) {
            RemoteViews presentation = (RemoteViews) parcel.readParcelable(null);
            InlinePresentation inlinePresentation = (InlinePresentation) parcel.readParcelable(null);
            InlinePresentation inlineTooltipPresentation = (InlinePresentation) parcel.readParcelable(null);
            ArrayList<AutofillId> ids = parcel.createTypedArrayList(AutofillId.CREATOR);
            ArrayList<AutofillValue> values = parcel.createTypedArrayList(AutofillValue.CREATOR);
            ArrayList<RemoteViews> presentations = parcel.createTypedArrayList(RemoteViews.CREATOR);
            ArrayList<InlinePresentation> inlinePresentations = parcel.createTypedArrayList(InlinePresentation.CREATOR);
            ArrayList<InlinePresentation> inlineTooltipPresentations = parcel.createTypedArrayList(InlinePresentation.CREATOR);
            ArrayList<DatasetFieldFilter> filters = parcel.createTypedArrayList(DatasetFieldFilter.CREATOR);
            ClipData fieldContent = (ClipData) parcel.readParcelable(null);
            IntentSender authentication = (IntentSender) parcel.readParcelable(null);
            String datasetId = parcel.readString();
            Builder builder = presentation != null ? new Builder(presentation) : new Builder();
            if (inlinePresentation != null) {
                if (inlineTooltipPresentation != null) {
                    builder.setInlinePresentation(inlinePresentation, inlineTooltipPresentation);
                } else {
                    builder.setInlinePresentation(inlinePresentation);
                }
            }
            if (fieldContent != null) {
                builder.setContent(ids.get(0), fieldContent);
            }
            int inlinePresentationsSize = inlinePresentations.size();
            int i = 0;
            while (i < ids.size()) {
                AutofillId id = ids.get(i);
                AutofillValue value = values.get(i);
                RemoteViews fieldPresentation = presentations.get(i);
                InlinePresentation fieldInlinePresentation = i < inlinePresentationsSize ? inlinePresentations.get(i) : null;
                InlinePresentation fieldInlineTooltipPresentation = i < inlinePresentationsSize ? inlineTooltipPresentations.get(i) : null;
                DatasetFieldFilter filter = filters.get(i);
                builder.setLifeTheUniverseAndEverything(id, value, fieldPresentation, fieldInlinePresentation, fieldInlineTooltipPresentation, filter);
                i++;
                inlinePresentationsSize = inlinePresentationsSize;
            }
            builder.setAuthentication(authentication);
            builder.setId(datasetId);
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Dataset[] newArray(int size) {
            return new Dataset[size];
        }
    };
    private final IntentSender mAuthentication;
    private final ClipData mFieldContent;
    private final ArrayList<DatasetFieldFilter> mFieldFilters;
    private final ArrayList<AutofillId> mFieldIds;
    private final ArrayList<InlinePresentation> mFieldInlinePresentations;
    private final ArrayList<InlinePresentation> mFieldInlineTooltipPresentations;
    private final ArrayList<RemoteViews> mFieldPresentations;
    private final ArrayList<AutofillValue> mFieldValues;
    String mId;
    private final InlinePresentation mInlinePresentation;
    private final InlinePresentation mInlineTooltipPresentation;
    private final RemoteViews mPresentation;

    private Dataset(Builder builder) {
        this.mFieldIds = builder.mFieldIds;
        this.mFieldValues = builder.mFieldValues;
        this.mFieldPresentations = builder.mFieldPresentations;
        this.mFieldInlinePresentations = builder.mFieldInlinePresentations;
        this.mFieldInlineTooltipPresentations = builder.mFieldInlineTooltipPresentations;
        this.mFieldFilters = builder.mFieldFilters;
        this.mFieldContent = builder.mFieldContent;
        this.mPresentation = builder.mPresentation;
        this.mInlinePresentation = builder.mInlinePresentation;
        this.mInlineTooltipPresentation = builder.mInlineTooltipPresentation;
        this.mAuthentication = builder.mAuthentication;
        this.mId = builder.mId;
    }

    public ArrayList<AutofillId> getFieldIds() {
        return this.mFieldIds;
    }

    public ArrayList<AutofillValue> getFieldValues() {
        return this.mFieldValues;
    }

    public RemoteViews getFieldPresentation(int index) {
        RemoteViews customPresentation = this.mFieldPresentations.get(index);
        return customPresentation != null ? customPresentation : this.mPresentation;
    }

    public InlinePresentation getFieldInlinePresentation(int index) {
        InlinePresentation inlinePresentation = this.mFieldInlinePresentations.get(index);
        return inlinePresentation != null ? inlinePresentation : this.mInlinePresentation;
    }

    public InlinePresentation getFieldInlineTooltipPresentation(int index) {
        InlinePresentation inlineTooltipPresentation = this.mFieldInlineTooltipPresentations.get(index);
        return inlineTooltipPresentation != null ? inlineTooltipPresentation : this.mInlineTooltipPresentation;
    }

    public DatasetFieldFilter getFilter(int index) {
        return this.mFieldFilters.get(index);
    }

    public ClipData getFieldContent() {
        return this.mFieldContent;
    }

    public IntentSender getAuthentication() {
        return this.mAuthentication;
    }

    public boolean isEmpty() {
        ArrayList<AutofillId> arrayList = this.mFieldIds;
        return arrayList == null || arrayList.isEmpty();
    }

    public String toString() {
        if (!Helper.sDebug) {
            return super.toString();
        }
        StringBuilder builder = new StringBuilder("Dataset[");
        if (this.mId == null) {
            builder.append("noId");
        } else {
            builder.append("id=");
            builder.append(this.mId.length());
            builder.append("_chars");
        }
        if (this.mFieldIds != null) {
            builder.append(", fieldIds=");
            builder.append(this.mFieldIds);
        }
        if (this.mFieldValues != null) {
            builder.append(", fieldValues=");
            builder.append(this.mFieldValues);
        }
        if (this.mFieldContent != null) {
            builder.append(", fieldContent=");
            builder.append(this.mFieldContent);
        }
        if (this.mFieldPresentations != null) {
            builder.append(", fieldPresentations=");
            builder.append(this.mFieldPresentations.size());
        }
        if (this.mFieldInlinePresentations != null) {
            builder.append(", fieldInlinePresentations=");
            builder.append(this.mFieldInlinePresentations.size());
        }
        if (this.mFieldInlineTooltipPresentations != null) {
            builder.append(", fieldInlineTooltipInlinePresentations=");
            builder.append(this.mFieldInlineTooltipPresentations.size());
        }
        if (this.mFieldFilters != null) {
            builder.append(", fieldFilters=");
            builder.append(this.mFieldFilters.size());
        }
        if (this.mPresentation != null) {
            builder.append(", hasPresentation");
        }
        if (this.mInlinePresentation != null) {
            builder.append(", hasInlinePresentation");
        }
        if (this.mInlineTooltipPresentation != null) {
            builder.append(", hasInlineTooltipPresentation");
        }
        if (this.mAuthentication != null) {
            builder.append(", hasAuthentication");
        }
        builder.append(']');
        return builder.toString();
    }

    public String getId() {
        return this.mId;
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private IntentSender mAuthentication;
        private boolean mDestroyed;
        private ClipData mFieldContent;
        private ArrayList<DatasetFieldFilter> mFieldFilters;
        private ArrayList<AutofillId> mFieldIds;
        private ArrayList<InlinePresentation> mFieldInlinePresentations;
        private ArrayList<InlinePresentation> mFieldInlineTooltipPresentations;
        private ArrayList<RemoteViews> mFieldPresentations;
        private ArrayList<AutofillValue> mFieldValues;
        private String mId;
        private InlinePresentation mInlinePresentation;
        private InlinePresentation mInlineTooltipPresentation;
        private RemoteViews mPresentation;

        public Builder(RemoteViews presentation) {
            Preconditions.checkNotNull(presentation, "presentation must be non-null");
            this.mPresentation = presentation;
        }

        @SystemApi
        public Builder(InlinePresentation inlinePresentation) {
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation must be non-null");
            this.mInlinePresentation = inlinePresentation;
        }

        public Builder() {
        }

        public Builder setInlinePresentation(InlinePresentation inlinePresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation must be non-null");
            this.mInlinePresentation = inlinePresentation;
            return this;
        }

        public Builder setInlinePresentation(InlinePresentation inlinePresentation, InlinePresentation inlineTooltipPresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation must be non-null");
            Preconditions.checkNotNull(inlineTooltipPresentation, "inlineTooltipPresentation must be non-null");
            this.mInlinePresentation = inlinePresentation;
            this.mInlineTooltipPresentation = inlineTooltipPresentation;
            return this;
        }

        public Builder setAuthentication(IntentSender authentication) {
            throwIfDestroyed();
            this.mAuthentication = authentication;
            return this;
        }

        public Builder setId(String id) {
            throwIfDestroyed();
            this.mId = id;
            return this;
        }

        @SystemApi
        public Builder setContent(AutofillId id, ClipData content) {
            throwIfDestroyed();
            if (content != null) {
                for (int i = 0; i < content.getItemCount(); i++) {
                    boolean z = content.getItemAt(i).getIntent() == null;
                    Preconditions.checkArgument(z, "Content items cannot contain an Intent: content=" + content);
                }
            }
            setLifeTheUniverseAndEverything(id, null, null, null, null);
            this.mFieldContent = content;
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value) {
            throwIfDestroyed();
            setLifeTheUniverseAndEverything(id, value, null, null, null);
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, RemoteViews presentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(presentation, "presentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, presentation, null, null);
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, Pattern filter) {
            throwIfDestroyed();
            Preconditions.checkState(this.mPresentation != null, "Dataset presentation not set on constructor");
            setLifeTheUniverseAndEverything(id, value, null, null, new DatasetFieldFilter(filter));
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, Pattern filter, RemoteViews presentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(presentation, "presentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, presentation, null, new DatasetFieldFilter(filter));
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, RemoteViews presentation, InlinePresentation inlinePresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(presentation, "presentation cannot be null");
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, presentation, inlinePresentation, null);
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, RemoteViews presentation, InlinePresentation inlinePresentation, InlinePresentation inlineTooltipPresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(presentation, "presentation cannot be null");
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation cannot be null");
            Preconditions.checkNotNull(inlineTooltipPresentation, "inlineTooltipPresentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, presentation, inlinePresentation, inlineTooltipPresentation, null);
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, Pattern filter, RemoteViews presentation, InlinePresentation inlinePresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(presentation, "presentation cannot be null");
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, presentation, inlinePresentation, new DatasetFieldFilter(filter));
            return this;
        }

        public Builder setValue(AutofillId id, AutofillValue value, Pattern filter, RemoteViews presentation, InlinePresentation inlinePresentation, InlinePresentation inlineTooltipPresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(presentation, "presentation cannot be null");
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation cannot be null");
            Preconditions.checkNotNull(inlineTooltipPresentation, "inlineTooltipPresentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, presentation, inlinePresentation, inlineTooltipPresentation, new DatasetFieldFilter(filter));
            return this;
        }

        @SystemApi
        public Builder setFieldInlinePresentation(AutofillId id, AutofillValue value, Pattern filter, InlinePresentation inlinePresentation) {
            throwIfDestroyed();
            Preconditions.checkNotNull(inlinePresentation, "inlinePresentation cannot be null");
            setLifeTheUniverseAndEverything(id, value, null, inlinePresentation, new DatasetFieldFilter(filter));
            return this;
        }

        private void setLifeTheUniverseAndEverything(AutofillId id, AutofillValue value, RemoteViews presentation, InlinePresentation inlinePresentation, DatasetFieldFilter filter) {
            setLifeTheUniverseAndEverything(id, value, presentation, inlinePresentation, null, filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLifeTheUniverseAndEverything(AutofillId id, AutofillValue value, RemoteViews presentation, InlinePresentation inlinePresentation, InlinePresentation tooltip, DatasetFieldFilter filter) {
            Preconditions.checkNotNull(id, "id cannot be null");
            ArrayList<AutofillId> arrayList = this.mFieldIds;
            if (arrayList != null) {
                int existingIdx = arrayList.indexOf(id);
                if (existingIdx >= 0) {
                    this.mFieldValues.set(existingIdx, value);
                    this.mFieldPresentations.set(existingIdx, presentation);
                    this.mFieldInlinePresentations.set(existingIdx, inlinePresentation);
                    this.mFieldInlineTooltipPresentations.set(existingIdx, tooltip);
                    this.mFieldFilters.set(existingIdx, filter);
                    return;
                }
            } else {
                this.mFieldIds = new ArrayList<>();
                this.mFieldValues = new ArrayList<>();
                this.mFieldPresentations = new ArrayList<>();
                this.mFieldInlinePresentations = new ArrayList<>();
                this.mFieldInlineTooltipPresentations = new ArrayList<>();
                this.mFieldFilters = new ArrayList<>();
            }
            this.mFieldIds.add(id);
            this.mFieldValues.add(value);
            this.mFieldPresentations.add(presentation);
            this.mFieldInlinePresentations.add(inlinePresentation);
            this.mFieldInlineTooltipPresentations.add(tooltip);
            this.mFieldFilters.add(filter);
        }

        public Dataset build() {
            throwIfDestroyed();
            this.mDestroyed = true;
            ArrayList<AutofillId> arrayList = this.mFieldIds;
            if (arrayList != null) {
                if (this.mFieldContent != null) {
                    if (arrayList.size() > 1) {
                        throw new IllegalStateException("when filling content, only one field can be filled");
                    } else if (this.mFieldValues.get(0) != null) {
                        throw new IllegalStateException("cannot fill both content and values");
                    }
                }
                return new Dataset(this);
            }
            throw new IllegalStateException("at least one value must be set");
        }

        private void throwIfDestroyed() {
            if (this.mDestroyed) {
                throw new IllegalStateException("Already called #build()");
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPresentation, flags);
        parcel.writeParcelable(this.mInlinePresentation, flags);
        parcel.writeParcelable(this.mInlineTooltipPresentation, flags);
        parcel.writeTypedList(this.mFieldIds, flags);
        parcel.writeTypedList(this.mFieldValues, flags);
        parcel.writeTypedList(this.mFieldPresentations, flags);
        parcel.writeTypedList(this.mFieldInlinePresentations, flags);
        parcel.writeTypedList(this.mFieldInlineTooltipPresentations, flags);
        parcel.writeTypedList(this.mFieldFilters, flags);
        parcel.writeParcelable(this.mFieldContent, flags);
        parcel.writeParcelable(this.mAuthentication, flags);
        parcel.writeString(this.mId);
    }

    /* loaded from: classes3.dex */
    public static final class DatasetFieldFilter implements Parcelable {
        public static final Parcelable.Creator<DatasetFieldFilter> CREATOR = new Parcelable.Creator<DatasetFieldFilter>() { // from class: android.service.autofill.Dataset.DatasetFieldFilter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DatasetFieldFilter createFromParcel(Parcel parcel) {
                return new DatasetFieldFilter((Pattern) parcel.readSerializable());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DatasetFieldFilter[] newArray(int size) {
                return new DatasetFieldFilter[size];
            }
        };
        public final Pattern pattern;

        private DatasetFieldFilter(Pattern pattern) {
            this.pattern = pattern;
        }

        public String toString() {
            if (!Helper.sDebug) {
                return super.toString();
            }
            if (this.pattern == null) {
                return "null";
            }
            return this.pattern.pattern().length() + "_chars";
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeSerializable(this.pattern);
        }
    }
}
