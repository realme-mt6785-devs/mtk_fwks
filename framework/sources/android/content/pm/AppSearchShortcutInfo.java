package android.content.pm;

import android.app.Person;
import android.app.appsearch.AppSearchSchema;
import android.app.appsearch.GenericDocument;
import android.content.ComponentName;
import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.internal.util.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
/* loaded from: classes.dex */
public class AppSearchShortcutInfo extends GenericDocument {
    private static final String ARE_STRINGS_RESOLVED = "Str";
    private static final String HAS_ADAPTIVE_BITMAP = "IcA";
    private static final String HAS_BITMAP_PATH = "hBiP";
    private static final String HAS_ICON_FILE = "IcF";
    private static final String HAS_ICON_FILE_PENDING_SAVE = "Pens";
    private static final String HAS_ICON_RES = "IcR";
    private static final String HAS_ICON_URI = "IcU";
    private static final String HAS_NON_ZERO_RANK = "hRan";
    private static final String HAS_STRING_RESOURCE = "hStr";
    private static final String IS_CACHED_BUBBLE = "CaB";
    private static final String IS_CACHED_NOTIFICATION = "CaN";
    private static final String IS_CACHED_PEOPLE_TITLE = "CaPT";
    private static final String IS_DISABLED = "Dis";
    private static final String IS_DYNAMIC = "Dyn";
    private static final String IS_IMMUTABLE = "Im";
    private static final String IS_KEY_FIELD_ONLY = "Key";
    private static final String IS_LONG_LIVED = "Liv";
    private static final String IS_MANIFEST = "Man";
    private static final String IS_PINNED = "Pin";
    private static final String IS_RETURNED_BY_SERVICE = "Rets";
    private static final String IS_SHADOW = "Sdw";
    public static final String KEY_ACTIVITY = "activity";
    public static final String KEY_FLAGS = "flags";
    public static final String KEY_ICON_RES_ID = "iconResId";
    public static final String KEY_PERSON = "person";
    public static final String KEY_RANK = "rank";
    private static final String NOT_CACHED_BUBBLE = "nCaB";
    private static final String NOT_CACHED_NOTIFICATION = "nCaN";
    private static final String NOT_CACHED_PEOPLE_TITLE = "nCaPT";
    private static final String NOT_DISABLED = "nDis";
    private static final String NOT_DYNAMIC = "nDyn";
    private static final String NOT_IMMUTABLE = "nIm";
    private static final String NOT_KEY_FIELD_ONLY = "nKey";
    private static final String NOT_LONG_LIVED = "nLiv";
    private static final String NOT_MANIFEST = "nMan";
    private static final String NOT_PINNED = "nPin";
    private static final String NOT_RETURNED_BY_SERVICE = "nRets";
    private static final String NOT_SHADOW = "nSdw";
    private static final String NOT_STRINGS_RESOLVED = "nStr";
    private static final String NO_ADAPTIVE_BITMAP = "nIcA";
    private static final String NO_ICON_FILE = "nIcF";
    private static final String NO_ICON_FILE_PENDING_SAVE = "nPens";
    private static final String NO_ICON_RES = "nIcR";
    private static final String NO_ICON_URI = "nIcU";
    public static final String QUERY_DISABLED_REASON_VERSION_LOWER = "disabledReason:100";
    public static final String QUERY_HAS_BITMAP_PATH = "flags:hBiP";
    public static final String QUERY_HAS_NON_ZERO_RANK = "flags:hRan";
    public static final String QUERY_HAS_STRING_RESOURCE = "flags:hStr";
    public static final String QUERY_IS_CACHED = "(flags:CaN OR flags:CaB OR flags:CaPT)";
    public static final String QUERY_IS_DYNAMIC = "flags:Dyn";
    public static final String QUERY_IS_FLOATING = "((Pin OR (flags:CaN OR flags:CaB OR flags:CaPT)) flags:nDyn flags:nMan)";
    public static final String QUERY_IS_FLOATING_AND_HAS_RANK = "(((Pin OR (flags:CaN OR flags:CaB OR flags:CaPT)) flags:nDyn flags:nMan) flags:hRan)";
    public static final String QUERY_IS_MANIFEST = "flags:Man";
    public static final String QUERY_IS_NON_MANIFEST_VISIBLE = "(flags:nMan (disabledReason:0 OR disabledReason:1 OR disabledReason:2 OR disabledReason:3) (flags:Pin OR (flags:CaN OR flags:CaB OR flags:CaPT) OR flags:Dyn))";
    public static final String QUERY_IS_NOT_CACHED = "(flags:nCaN flags:nCaB flags:nCaPT)";
    public static final String QUERY_IS_NOT_DYNAMIC = "flags:nDyn";
    public static final String QUERY_IS_NOT_FLOATING = "((flags:nPin (flags:nCaN flags:nCaB flags:nCaPT)) OR flags:Dyn OR flags:Man)";
    public static final String QUERY_IS_NOT_MANIFEST = "flags:nMan";
    public static final String QUERY_IS_NOT_PINNED = "flags:nPin";
    public static final String QUERY_IS_PINNED = "flags:Pin";
    public static final String QUERY_IS_PINNED_AND_ENABLED = "(flags:Pin flags:nDis)";
    public static final String QUERY_IS_VISIBLE_CACHED_OR_PINNED = "((disabledReason:0 OR disabledReason:1 OR disabledReason:2 OR disabledReason:3) flags:Dyn ((flags:CaN OR flags:CaB OR flags:CaPT) OR flags:Pin))";
    public static final String QUERY_IS_VISIBLE_PINNED_ONLY = "((disabledReason:0 OR disabledReason:1 OR disabledReason:2 OR disabledReason:3) flags:Pin (flags:nCaN flags:nCaB flags:nCaPT) flags:nDyn flags:nMan)";
    public static final String QUERY_IS_VISIBLE_TO_PUBLISHER = "(disabledReason:0 OR disabledReason:1 OR disabledReason:2 OR disabledReason:3)";
    public static final int SCHEMA_VERSION = 2;
    public static final String SCHEMA_TYPE = "Shortcut";
    public static final String KEY_SHORT_LABEL = "shortLabel";
    public static final String KEY_SHORT_LABEL_RES_ID = "shortLabelResId";
    public static final String KEY_SHORT_LABEL_RES_NAME = "shortLabelResName";
    public static final String KEY_LONG_LABEL = "longLabel";
    public static final String KEY_LONG_LABEL_RES_ID = "longLabelResId";
    public static final String KEY_LONG_LABEL_RES_NAME = "longLabelResName";
    public static final String KEY_DISABLED_MESSAGE = "disabledMessage";
    public static final String KEY_DISABLED_MESSAGE_RES_ID = "disabledMessageResId";
    public static final String KEY_DISABLED_MESSAGE_RES_NAME = "disabledMessageResName";
    public static final String KEY_CATEGORIES = "categories";
    public static final String KEY_INTENTS = "intents";
    public static final String KEY_INTENT_PERSISTABLE_EXTRAS = "intentPersistableExtras";
    public static final String KEY_LOCUS_ID = "locusId";
    public static final String KEY_IMPLICIT_RANK = "implicitRank";
    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_ICON_RES_NAME = "iconResName";
    public static final String KEY_ICON_URI = "iconUri";
    public static final String KEY_BITMAP_PATH = "bitmapPath";
    public static final String KEY_DISABLED_REASON = "disabledReason";
    public static final AppSearchSchema SCHEMA = new AppSearchSchema.Builder(SCHEMA_TYPE).addProperty(new AppSearchSchema.StringPropertyConfig.Builder("activity").setCardinality(2).setTokenizerType(1).setIndexingType(1).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_SHORT_LABEL).setCardinality(2).setTokenizerType(1).setIndexingType(2).build()).addProperty(new AppSearchSchema.LongPropertyConfig.Builder(KEY_SHORT_LABEL_RES_ID).setCardinality(2).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_SHORT_LABEL_RES_NAME).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_LONG_LABEL).setCardinality(2).setTokenizerType(1).setIndexingType(2).build()).addProperty(new AppSearchSchema.LongPropertyConfig.Builder(KEY_LONG_LABEL_RES_ID).setCardinality(2).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_LONG_LABEL_RES_NAME).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_DISABLED_MESSAGE).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.LongPropertyConfig.Builder(KEY_DISABLED_MESSAGE_RES_ID).setCardinality(2).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_DISABLED_MESSAGE_RES_NAME).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_CATEGORIES).setCardinality(1).setTokenizerType(1).setIndexingType(1).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_INTENTS).setCardinality(1).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.BytesPropertyConfig.Builder(KEY_INTENT_PERSISTABLE_EXTRAS).setCardinality(1).build()).addProperty(new AppSearchSchema.DocumentPropertyConfig.Builder("person", AppSearchPerson.SCHEMA_TYPE).setCardinality(1).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_LOCUS_ID).setCardinality(2).setTokenizerType(1).setIndexingType(1).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder("rank").setCardinality(2).setTokenizerType(1).setIndexingType(1).build()).addProperty(new AppSearchSchema.LongPropertyConfig.Builder(KEY_IMPLICIT_RANK).setCardinality(2).build()).addProperty(new AppSearchSchema.BytesPropertyConfig.Builder(KEY_EXTRAS).setCardinality(2).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder("flags").setCardinality(1).setTokenizerType(1).setIndexingType(1).build()).addProperty(new AppSearchSchema.LongPropertyConfig.Builder("iconResId").setCardinality(2).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_ICON_RES_NAME).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_ICON_URI).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_BITMAP_PATH).setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder(KEY_DISABLED_REASON).setCardinality(3).setTokenizerType(1).setIndexingType(1).build()).build();

    public AppSearchShortcutInfo(GenericDocument document) {
        super(document);
    }

    public static AppSearchShortcutInfo instance(ShortcutInfo shortcutInfo) {
        Objects.requireNonNull(shortcutInfo);
        return new Builder(shortcutInfo.getPackage(), shortcutInfo.getId()).setActivity(shortcutInfo.getActivity()).setShortLabel(shortcutInfo.getShortLabel()).setShortLabelResId(shortcutInfo.getShortLabelResourceId()).setShortLabelResName(shortcutInfo.getTitleResName()).setLongLabel(shortcutInfo.getLongLabel()).setLongLabelResId(shortcutInfo.getLongLabelResourceId()).setLongLabelResName(shortcutInfo.getTextResName()).setDisabledMessage(shortcutInfo.getDisabledMessage()).setDisabledMessageResId(shortcutInfo.getDisabledMessageResourceId()).setDisabledMessageResName(shortcutInfo.getDisabledMessageResName()).setCategories(shortcutInfo.getCategories()).setIntents(shortcutInfo.getIntents()).setRank(shortcutInfo.getRank()).setImplicitRank(shortcutInfo.getImplicitRank() | (shortcutInfo.isRankChanged() ? Integer.MIN_VALUE : 0)).setExtras(shortcutInfo.getExtras()).setCreationTimestampMillis(shortcutInfo.getLastChangedTimestamp()).setFlags(shortcutInfo.getFlags()).setIconResId(shortcutInfo.getIconResourceId()).setIconResName(shortcutInfo.getIconResName()).setBitmapPath(shortcutInfo.getBitmapPath()).setIconUri(shortcutInfo.getIconUri()).setDisabledReason(shortcutInfo.getDisabledReason()).setPersons(shortcutInfo.getPersons()).setLocusId(shortcutInfo.getLocusId()).build();
    }

    public ShortcutInfo toShortcutInfo(int userId) {
        Intent[] intents;
        Bundle[] intentExtrases;
        String packageName = getNamespace();
        String activityString = getPropertyString("activity");
        LocusId locusId = null;
        ComponentName activity = activityString == null ? null : ComponentName.unflattenFromString(activityString);
        String shortLabel = getPropertyString(KEY_SHORT_LABEL);
        int shortLabelResId = (int) getPropertyLong(KEY_SHORT_LABEL_RES_ID);
        String shortLabelResName = getPropertyString(KEY_SHORT_LABEL_RES_NAME);
        String longLabel = getPropertyString(KEY_LONG_LABEL);
        int longLabelResId = (int) getPropertyLong(KEY_LONG_LABEL_RES_ID);
        String longLabelResName = getPropertyString(KEY_LONG_LABEL_RES_NAME);
        String disabledMessage = getPropertyString(KEY_DISABLED_MESSAGE);
        int disabledMessageResId = (int) getPropertyLong(KEY_DISABLED_MESSAGE_RES_ID);
        String disabledMessageResName = getPropertyString(KEY_DISABLED_MESSAGE_RES_NAME);
        String[] categories = getPropertyStringArray(KEY_CATEGORIES);
        Set<String> categoriesSet = categories == null ? null : new ArraySet<>(Arrays.asList(categories));
        String[] intentsStrings = getPropertyStringArray(KEY_INTENTS);
        if (intentsStrings == null) {
            intents = new Intent[0];
        } else {
            intents = (Intent[]) Arrays.stream(intentsStrings).map(AppSearchShortcutInfo$$ExternalSyntheticLambda1.INSTANCE).toArray(AppSearchShortcutInfo$$ExternalSyntheticLambda2.INSTANCE);
        }
        byte[][] intentExtrasesBytes = getPropertyBytesArray(KEY_INTENT_PERSISTABLE_EXTRAS);
        if (intentExtrasesBytes == null) {
            intentExtrases = null;
        } else {
            intentExtrases = (Bundle[]) Arrays.stream(intentExtrasesBytes).map(new Function() { // from class: android.content.pm.AppSearchShortcutInfo$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Bundle transformToBundle;
                    transformToBundle = AppSearchShortcutInfo.this.transformToBundle((byte[]) obj);
                    return transformToBundle;
                }
            }).toArray(AppSearchShortcutInfo$$ExternalSyntheticLambda3.INSTANCE);
        }
        if (intents != null) {
            for (int i = 0; i < intents.length; i++) {
                Intent intent = intents[i];
                if (!(intent == null || intentExtrases == null || intentExtrases.length <= i || intentExtrases[i] == null || intentExtrases[i].size() == 0)) {
                    intent.replaceExtras(intentExtrases[i]);
                }
            }
        }
        Person[] persons = parsePerson(getPropertyDocumentArray("person"));
        String locusIdString = getPropertyString(KEY_LOCUS_ID);
        if (locusIdString != null) {
            locusId = new LocusId(locusIdString);
        }
        int rank = Integer.parseInt(getPropertyString("rank"));
        int implicitRank = (int) getPropertyLong(KEY_IMPLICIT_RANK);
        byte[] extrasByte = getPropertyBytes(KEY_EXTRAS);
        PersistableBundle extras = transformToPersistableBundle(extrasByte);
        int flags = parseFlags(getPropertyStringArray("flags"));
        int iconResId = (int) getPropertyLong("iconResId");
        String iconResName = getPropertyString(KEY_ICON_RES_NAME);
        String iconUri = getPropertyString(KEY_ICON_URI);
        String bitmapPath = getPropertyString(KEY_BITMAP_PATH);
        int disabledReason = Integer.parseInt(getPropertyString(KEY_DISABLED_REASON));
        ShortcutInfo si = new ShortcutInfo(userId, getId(), packageName, activity, null, shortLabel, shortLabelResId, shortLabelResName, longLabel, longLabelResId, longLabelResName, disabledMessage, disabledMessageResId, disabledMessageResName, categoriesSet, intents, rank, extras, getCreationTimestampMillis(), flags, iconResId, iconResName, bitmapPath, iconUri, disabledReason, persons, locusId, null);
        si.setImplicitRank(implicitRank);
        if ((Integer.MIN_VALUE & implicitRank) != 0) {
            si.setRankChanged();
        }
        return si;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Intent lambda$toShortcutInfo$0(String uri) {
        if (TextUtils.isEmpty(uri)) {
            return new Intent("android.intent.action.VIEW");
        }
        try {
            return Intent.parseUri(uri, 0);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Intent[] lambda$toShortcutInfo$1(int x$0) {
        return new Intent[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Bundle[] lambda$toShortcutInfo$2(int x$0) {
        return new Bundle[x$0];
    }

    public static List<GenericDocument> toGenericDocuments(Collection<ShortcutInfo> shortcuts) {
        List<GenericDocument> docs = new ArrayList<>(shortcuts.size());
        for (ShortcutInfo si : shortcuts) {
            docs.add(instance(si));
        }
        return docs;
    }

    /* loaded from: classes.dex */
    public static class Builder extends GenericDocument.Builder<Builder> {
        private List<String> mFlags = new ArrayList(1);
        private boolean mHasStringResource = false;

        public Builder(String packageName, String id) {
            super(packageName, id, AppSearchShortcutInfo.SCHEMA_TYPE);
        }

        public Builder setLocusId(LocusId locusId) {
            if (locusId != null) {
                setPropertyString(AppSearchShortcutInfo.KEY_LOCUS_ID, new String[]{locusId.getId()});
            }
            return this;
        }

        public Builder setActivity(ComponentName activity) {
            if (activity != null) {
                setPropertyString("activity", new String[]{activity.flattenToShortString()});
            }
            return this;
        }

        public Builder setShortLabel(CharSequence shortLabel) {
            if (!TextUtils.isEmpty(shortLabel)) {
                setPropertyString(AppSearchShortcutInfo.KEY_SHORT_LABEL, new String[]{Preconditions.checkStringNotEmpty(shortLabel, "shortLabel cannot be empty").toString()});
            }
            return this;
        }

        public Builder setShortLabelResId(int shortLabelResId) {
            setPropertyLong(AppSearchShortcutInfo.KEY_SHORT_LABEL_RES_ID, new long[]{shortLabelResId});
            if (shortLabelResId != 0) {
                this.mHasStringResource = true;
            }
            return this;
        }

        public Builder setShortLabelResName(String shortLabelResName) {
            if (!TextUtils.isEmpty(shortLabelResName)) {
                setPropertyString(AppSearchShortcutInfo.KEY_SHORT_LABEL_RES_NAME, new String[]{shortLabelResName});
            }
            return this;
        }

        public Builder setLongLabel(CharSequence longLabel) {
            if (!TextUtils.isEmpty(longLabel)) {
                setPropertyString(AppSearchShortcutInfo.KEY_LONG_LABEL, new String[]{Preconditions.checkStringNotEmpty(longLabel, "longLabel cannot be empty").toString()});
            }
            return this;
        }

        public Builder setLongLabelResId(int longLabelResId) {
            setPropertyLong(AppSearchShortcutInfo.KEY_LONG_LABEL_RES_ID, new long[]{longLabelResId});
            if (longLabelResId != 0) {
                this.mHasStringResource = true;
            }
            return this;
        }

        public Builder setLongLabelResName(String longLabelResName) {
            if (!TextUtils.isEmpty(longLabelResName)) {
                setPropertyString(AppSearchShortcutInfo.KEY_LONG_LABEL_RES_NAME, new String[]{longLabelResName});
            }
            return this;
        }

        public Builder setDisabledMessage(CharSequence disabledMessage) {
            if (!TextUtils.isEmpty(disabledMessage)) {
                setPropertyString(AppSearchShortcutInfo.KEY_DISABLED_MESSAGE, new String[]{Preconditions.checkStringNotEmpty(disabledMessage, "disabledMessage cannot be empty").toString()});
            }
            return this;
        }

        public Builder setDisabledMessageResId(int disabledMessageResId) {
            setPropertyLong(AppSearchShortcutInfo.KEY_DISABLED_MESSAGE_RES_ID, new long[]{disabledMessageResId});
            if (disabledMessageResId != 0) {
                this.mHasStringResource = true;
            }
            return this;
        }

        public Builder setDisabledMessageResName(String disabledMessageResName) {
            if (!TextUtils.isEmpty(disabledMessageResName)) {
                setPropertyString(AppSearchShortcutInfo.KEY_DISABLED_MESSAGE_RES_NAME, new String[]{disabledMessageResName});
            }
            return this;
        }

        public Builder setCategories(Set<String> categories) {
            if (categories != null && !categories.isEmpty()) {
                setPropertyString(AppSearchShortcutInfo.KEY_CATEGORIES, (String[]) categories.stream().toArray(AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda1.INSTANCE));
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ String[] lambda$setCategories$0(int x$0) {
            return new String[x$0];
        }

        public Builder setIntent(Intent intent) {
            return intent == null ? this : setIntents(new Intent[]{intent});
        }

        public Builder setIntents(Intent[] intents) {
            byte[] bArr;
            if (intents == null || intents.length == 0) {
                return this;
            }
            for (Intent intent : intents) {
                Objects.requireNonNull(intent, "intents cannot contain null");
                Objects.requireNonNull(intent.getAction(), "intent's action must be set");
            }
            byte[][] intentExtrases = new byte[intents.length];
            for (int i = 0; i < intents.length; i++) {
                Bundle extras = intents[i].getExtras();
                if (extras == null) {
                    bArr = new byte[0];
                } else {
                    bArr = AppSearchShortcutInfo.transformToByteArray(new PersistableBundle(extras));
                }
                intentExtrases[i] = bArr;
            }
            setPropertyString(AppSearchShortcutInfo.KEY_INTENTS, (String[]) Arrays.stream(intents).map(AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda0.INSTANCE).toArray(AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda2.INSTANCE));
            setPropertyBytes(AppSearchShortcutInfo.KEY_INTENT_PERSISTABLE_EXTRAS, intentExtrases);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ String[] lambda$setIntents$2(int x$0) {
            return new String[x$0];
        }

        public Builder setPerson(Person person) {
            return person == null ? this : setPersons(new Person[]{person});
        }

        public Builder setPersons(Person[] persons) {
            if (persons == null || persons.length == 0) {
                return this;
            }
            GenericDocument[] documents = new GenericDocument[persons.length];
            for (int i = 0; i < persons.length; i++) {
                Person person = persons[i];
                if (person != null) {
                    AppSearchPerson appSearchPerson = AppSearchPerson.instance(person);
                    documents[i] = appSearchPerson;
                }
            }
            setPropertyDocument("person", documents);
            return this;
        }

        public Builder setRank(int rank) {
            Preconditions.checkArgument(rank >= 0, "Rank cannot be negative");
            setPropertyString("rank", new String[]{String.valueOf(rank)});
            if (rank != 0) {
                this.mFlags.add(AppSearchShortcutInfo.HAS_NON_ZERO_RANK);
            }
            return this;
        }

        public Builder setImplicitRank(int rank) {
            setPropertyLong(AppSearchShortcutInfo.KEY_IMPLICIT_RANK, new long[]{rank});
            return this;
        }

        public Builder setExtras(PersistableBundle extras) {
            if (extras != null) {
                setPropertyBytes(AppSearchShortcutInfo.KEY_EXTRAS, new byte[][]{AppSearchShortcutInfo.transformToByteArray(extras)});
            }
            return this;
        }

        public Builder setFlags(int flags) {
            String[] flagArray = AppSearchShortcutInfo.flattenFlags(flags);
            if (flagArray != null && flagArray.length > 0) {
                this.mFlags.addAll(Arrays.asList(flagArray));
            }
            return this;
        }

        public Builder setIconResId(int iconResId) {
            setPropertyLong("iconResId", new long[]{iconResId});
            return this;
        }

        public Builder setIconResName(String iconResName) {
            if (!TextUtils.isEmpty(iconResName)) {
                setPropertyString(AppSearchShortcutInfo.KEY_ICON_RES_NAME, new String[]{iconResName});
            }
            return this;
        }

        public Builder setBitmapPath(String bitmapPath) {
            if (!TextUtils.isEmpty(bitmapPath)) {
                setPropertyString(AppSearchShortcutInfo.KEY_BITMAP_PATH, new String[]{bitmapPath});
                this.mFlags.add(AppSearchShortcutInfo.HAS_BITMAP_PATH);
            }
            return this;
        }

        public Builder setIconUri(String iconUri) {
            if (!TextUtils.isEmpty(iconUri)) {
                setPropertyString(AppSearchShortcutInfo.KEY_ICON_URI, new String[]{iconUri});
            }
            return this;
        }

        public Builder setDisabledReason(int disabledReason) {
            setPropertyString(AppSearchShortcutInfo.KEY_DISABLED_REASON, new String[]{String.valueOf(disabledReason)});
            return this;
        }

        public AppSearchShortcutInfo build() {
            if (this.mHasStringResource) {
                this.mFlags.add(AppSearchShortcutInfo.HAS_STRING_RESOURCE);
            }
            setPropertyString("flags", (String[]) this.mFlags.toArray(new String[0]));
            return new AppSearchShortcutInfo(AppSearchShortcutInfo.super.build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] transformToByteArray(PersistableBundle extras) {
        Objects.requireNonNull(extras);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new PersistableBundle(extras).writeToStream(baos);
            byte[] byteArray = baos.toByteArray();
            baos.close();
            return byteArray;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle transformToBundle(byte[] extras) {
        if (extras == null) {
            return null;
        }
        Objects.requireNonNull(extras);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(extras);
            Bundle ret = new Bundle();
            ret.putAll(PersistableBundle.readFromStream(bais));
            bais.close();
            return ret;
        } catch (IOException e) {
            return null;
        }
    }

    private PersistableBundle transformToPersistableBundle(byte[] extras) {
        if (extras == null) {
            return null;
        }
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(extras);
            PersistableBundle readFromStream = PersistableBundle.readFromStream(bais);
            bais.close();
            return readFromStream;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] flattenFlags(int flags) {
        List<String> flattenedFlags = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            int mask = 1 << i;
            String value = flagToString(flags, mask);
            if (value != null) {
                flattenedFlags.add(value);
            }
        }
        return (String[]) flattenedFlags.toArray(new String[0]);
    }

    private static String flagToString(int flags, int mask) {
        switch (mask) {
            case 1:
                return (flags & mask) != 0 ? IS_DYNAMIC : NOT_DYNAMIC;
            case 2:
                return (flags & mask) != 0 ? IS_PINNED : NOT_PINNED;
            case 4:
                return (flags & mask) != 0 ? HAS_ICON_RES : NO_ICON_RES;
            case 8:
                return (flags & mask) != 0 ? HAS_ICON_FILE : NO_ICON_FILE;
            case 16:
                return (flags & mask) != 0 ? IS_KEY_FIELD_ONLY : NOT_KEY_FIELD_ONLY;
            case 32:
                return (flags & mask) != 0 ? IS_MANIFEST : NOT_MANIFEST;
            case 64:
                return (flags & mask) != 0 ? IS_DISABLED : NOT_DISABLED;
            case 128:
                return (flags & mask) != 0 ? ARE_STRINGS_RESOLVED : NOT_STRINGS_RESOLVED;
            case 256:
                return (flags & mask) != 0 ? IS_IMMUTABLE : NOT_IMMUTABLE;
            case 512:
                return (flags & mask) != 0 ? HAS_ADAPTIVE_BITMAP : NO_ADAPTIVE_BITMAP;
            case 1024:
                return (flags & mask) != 0 ? IS_RETURNED_BY_SERVICE : NOT_RETURNED_BY_SERVICE;
            case 2048:
                return (flags & mask) != 0 ? HAS_ICON_FILE_PENDING_SAVE : NO_ICON_FILE_PENDING_SAVE;
            case 4096:
                return (flags & mask) != 0 ? IS_SHADOW : NOT_SHADOW;
            case 8192:
                return (flags & mask) != 0 ? IS_LONG_LIVED : NOT_LONG_LIVED;
            case 16384:
                return (flags & mask) != 0 ? IS_CACHED_NOTIFICATION : NOT_CACHED_NOTIFICATION;
            case 32768:
                return (flags & mask) != 0 ? HAS_ICON_URI : NO_ICON_URI;
            case 536870912:
                return (flags & mask) != 0 ? IS_CACHED_PEOPLE_TITLE : NOT_CACHED_PEOPLE_TITLE;
            case 1073741824:
                return (flags & mask) != 0 ? IS_CACHED_BUBBLE : NOT_CACHED_BUBBLE;
            default:
                return null;
        }
    }

    private static int parseFlags(String[] flags) {
        if (flags == null) {
            return 0;
        }
        int ret = 0;
        for (String str : flags) {
            ret |= parseFlag(str);
        }
        return ret;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int parseFlag(String value) {
        char c;
        switch (value.hashCode()) {
            case 2372:
                if (value.equals(IS_IMMUTABLE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 67460:
                if (value.equals(IS_CACHED_BUBBLE)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 67472:
                if (value.equals(IS_CACHED_NOTIFICATION)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 68718:
                if (value.equals(IS_DISABLED)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 69209:
                if (value.equals(IS_DYNAMIC)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 73287:
                if (value.equals(HAS_ADAPTIVE_BITMAP)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 73292:
                if (value.equals(HAS_ICON_FILE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 73304:
                if (value.equals(HAS_ICON_RES)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 73307:
                if (value.equals(HAS_ICON_URI)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 75327:
                if (value.equals(IS_KEY_FIELD_ONLY)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 76409:
                if (value.equals(IS_LONG_LIVED)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 77114:
                if (value.equals(IS_MANIFEST)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 80245:
                if (value.equals(IS_PINNED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 82982:
                if (value.equals(IS_SHADOW)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 83473:
                if (value.equals(ARE_STRINGS_RESOLVED)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2091778:
                if (value.equals(IS_CACHED_PEOPLE_TITLE)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 2483866:
                if (value.equals(HAS_ICON_FILE_PENDING_SAVE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 2543634:
                if (value.equals(IS_RETURNED_BY_SERVICE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case '\b':
                return 256;
            case '\t':
                return 512;
            case '\n':
                return 1024;
            case 11:
                return 2048;
            case '\f':
                return 4096;
            case '\r':
                return 8192;
            case 14:
                return 32768;
            case 15:
                return 16384;
            case 16:
                return 1073741824;
            case 17:
                return 536870912;
            default:
                return 0;
        }
    }

    private static Person[] parsePerson(GenericDocument[] persons) {
        if (persons == null) {
            return new Person[0];
        }
        Person[] ret = new Person[persons.length];
        for (int i = 0; i < persons.length; i++) {
            GenericDocument document = persons[i];
            if (document != null) {
                AppSearchPerson person = new AppSearchPerson(document);
                ret[i] = person.toPerson();
            }
        }
        return ret;
    }
}
