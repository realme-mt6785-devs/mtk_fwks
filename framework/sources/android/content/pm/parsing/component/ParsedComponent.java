package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.parsing.ParsingPackageImpl;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class ParsedComponent implements Parcelable {
    private static ParsedIntentInfo.ListParceler sForIntentInfos = (ParsedIntentInfo.ListParceler) Parcelling.Cache.getOrCreate(ParsedIntentInfo.ListParceler.class);
    int banner;
    private ComponentName componentName;
    int descriptionRes;
    int flags;
    int icon;
    private List<ParsedIntentInfo> intents;
    int labelRes;
    int logo;
    private Map<String, PackageManager.Property> mProperties;
    protected Bundle metaData;
    private String name;
    CharSequence nonLocalizedLabel;
    private String packageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParsedComponent() {
        this.mProperties = Collections.emptyMap();
    }

    public ParsedComponent(ParsedComponent other) {
        this.mProperties = Collections.emptyMap();
        this.metaData = other.metaData;
        this.name = other.name;
        this.icon = other.getIcon();
        this.labelRes = other.getLabelRes();
        this.nonLocalizedLabel = other.getNonLocalizedLabel();
        this.logo = other.getLogo();
        this.banner = other.getBanner();
        this.descriptionRes = other.getDescriptionRes();
        this.flags = other.getFlags();
        setPackageName(other.packageName);
        this.intents = new ArrayList(other.getIntents());
    }

    public void addIntent(ParsedIntentInfo intent) {
        this.intents = CollectionUtils.add(this.intents, intent);
    }

    public void addProperty(PackageManager.Property property) {
        this.mProperties = CollectionUtils.add(this.mProperties, property.getName(), property);
    }

    public List<ParsedIntentInfo> getIntents() {
        List<ParsedIntentInfo> list = this.intents;
        return list != null ? list : Collections.emptyList();
    }

    public ParsedComponent setName(String name) {
        this.name = TextUtils.safeIntern(name);
        return this;
    }

    public void setPackageName(String packageName) {
        this.packageName = TextUtils.safeIntern(packageName);
        this.componentName = null;
    }

    public ComponentName getComponentName() {
        if (this.componentName == null) {
            this.componentName = new ComponentName(getPackageName(), getName());
        }
        return this.componentName;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(getIcon());
        dest.writeInt(getLabelRes());
        dest.writeCharSequence(getNonLocalizedLabel());
        dest.writeInt(getLogo());
        dest.writeInt(getBanner());
        dest.writeInt(getDescriptionRes());
        dest.writeInt(getFlags());
        ParsingPackageImpl.sForInternedString.parcel(this.packageName, dest, flags);
        sForIntentInfos.parcel(getIntents(), dest, flags);
        dest.writeBundle(this.metaData);
        dest.writeMap(this.mProperties);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParsedComponent(Parcel in) {
        this.mProperties = Collections.emptyMap();
        ClassLoader boot = Object.class.getClassLoader();
        this.name = in.readString();
        this.icon = in.readInt();
        this.labelRes = in.readInt();
        this.nonLocalizedLabel = in.readCharSequence();
        this.logo = in.readInt();
        this.banner = in.readInt();
        this.descriptionRes = in.readInt();
        this.flags = in.readInt();
        this.packageName = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.intents = sForIntentInfos.unparcel(in);
        this.metaData = in.readBundle(boot);
        this.mProperties = in.readHashMap(boot);
    }

    public String getName() {
        return this.name;
    }

    public int getIcon() {
        return this.icon;
    }

    public int getLabelRes() {
        return this.labelRes;
    }

    public CharSequence getNonLocalizedLabel() {
        return this.nonLocalizedLabel;
    }

    public int getLogo() {
        return this.logo;
    }

    public int getBanner() {
        return this.banner;
    }

    public int getDescriptionRes() {
        return this.descriptionRes;
    }

    public int getFlags() {
        return this.flags;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public Bundle getMetaData() {
        return this.metaData;
    }

    public Map<String, PackageManager.Property> getProperties() {
        return this.mProperties;
    }
}
