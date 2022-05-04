package android.content.pm;

import android.app.Person;
import android.app.appsearch.AppSearchSchema;
import android.app.appsearch.GenericDocument;
import android.net.UriCodec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes.dex */
public class AppSearchPerson extends GenericDocument {
    public static final String KEY_KEY = "key";
    public static final String KEY_NAME = "name";
    public static final String SCHEMA_TYPE = "Person";
    public static final String KEY_IS_BOT = "isBot";
    public static final String KEY_IS_IMPORTANT = "isImportant";
    public static final AppSearchSchema SCHEMA = new AppSearchSchema.Builder(SCHEMA_TYPE).addProperty(new AppSearchSchema.StringPropertyConfig.Builder("name").setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.StringPropertyConfig.Builder("key").setCardinality(2).setTokenizerType(0).setIndexingType(0).build()).addProperty(new AppSearchSchema.BooleanPropertyConfig.Builder(KEY_IS_BOT).setCardinality(3).build()).addProperty(new AppSearchSchema.BooleanPropertyConfig.Builder(KEY_IS_IMPORTANT).setCardinality(3).build()).build();

    public AppSearchPerson(GenericDocument document) {
        super(document);
    }

    public static AppSearchPerson instance(Person person) {
        String id;
        Objects.requireNonNull(person);
        if (person.getUri() != null) {
            id = person.getUri();
        } else {
            id = UUID.randomUUID().toString();
        }
        return new Builder(id).setName(person.getName()).setKey(person.getKey()).setIsBot(person.isBot()).setIsImportant(person.isImportant()).build();
    }

    public Person toPerson() {
        String uri;
        try {
            uri = UriCodec.decode(getId(), false, StandardCharsets.UTF_8, true);
        } catch (IllegalArgumentException e) {
            uri = null;
        }
        return new Person.Builder().setName(getPropertyString("name")).setUri(uri).setKey(getPropertyString("key")).setBot(getPropertyBoolean(KEY_IS_BOT)).setImportant(getPropertyBoolean(KEY_IS_IMPORTANT)).build();
    }

    /* loaded from: classes.dex */
    public static class Builder extends GenericDocument.Builder<Builder> {
        public Builder(String id) {
            super("", id, AppSearchPerson.SCHEMA_TYPE);
        }

        public Builder setName(CharSequence name) {
            if (name != null) {
                setPropertyString("name", new String[]{name.toString()});
            }
            return this;
        }

        public Builder setKey(String key) {
            if (key != null) {
                setPropertyString("key", new String[]{key});
            }
            return this;
        }

        public Builder setIsBot(boolean isBot) {
            setPropertyBoolean(AppSearchPerson.KEY_IS_BOT, new boolean[]{isBot});
            return this;
        }

        public Builder setIsImportant(boolean isImportant) {
            setPropertyBoolean(AppSearchPerson.KEY_IS_IMPORTANT, new boolean[]{isImportant});
            return this;
        }

        public AppSearchPerson build() {
            return new AppSearchPerson(AppSearchPerson.super.build());
        }
    }
}
