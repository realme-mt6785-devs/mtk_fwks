package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes.dex */
public class ParsedMainComponent extends ParsedComponent {
    public static final Parcelable.Creator<ParsedMainComponent> CREATOR = new Parcelable.Creator<ParsedMainComponent>() { // from class: android.content.pm.parsing.component.ParsedMainComponent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedMainComponent createFromParcel(Parcel source) {
            return new ParsedMainComponent(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedMainComponent[] newArray(int size) {
            return new ParsedMainComponent[size];
        }
    };
    String[] attributionTags;
    boolean directBootAware;
    boolean enabled;
    boolean exported;
    public final IParsedMainComponentExt mParsedMainComponentExt;
    int order;
    private String processName;
    String splitName;

    public ParsedMainComponent() {
        this.enabled = true;
        this.mParsedMainComponentExt = (IParsedMainComponentExt) ExtLoader.type(IParsedMainComponentExt.class).base(this).create();
    }

    public ParsedMainComponent(ParsedMainComponent other) {
        super(other);
        this.enabled = true;
        IParsedMainComponentExt iParsedMainComponentExt = (IParsedMainComponentExt) ExtLoader.type(IParsedMainComponentExt.class).base(this).create();
        this.mParsedMainComponentExt = iParsedMainComponentExt;
        this.processName = other.processName;
        this.directBootAware = other.directBootAware;
        this.enabled = other.enabled;
        this.exported = other.exported;
        this.order = other.order;
        this.splitName = other.splitName;
        this.attributionTags = other.attributionTags;
        iParsedMainComponentExt.init(other.mParsedMainComponentExt);
    }

    public ParsedMainComponent setProcessName(String processName) {
        this.processName = TextUtils.safeIntern(processName);
        return this;
    }

    public ParsedMainComponent setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getClassName() {
        return getName();
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        ParsingPackageImpl.sForInternedString.parcel(this.processName, dest, flags);
        dest.writeBoolean(this.directBootAware);
        dest.writeBoolean(this.enabled);
        dest.writeBoolean(this.exported);
        dest.writeInt(this.order);
        dest.writeString(this.splitName);
        dest.writeString8Array(this.attributionTags);
        this.mParsedMainComponentExt.writeToParcel(dest, flags);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParsedMainComponent(Parcel in) {
        super(in);
        this.enabled = true;
        IParsedMainComponentExt iParsedMainComponentExt = (IParsedMainComponentExt) ExtLoader.type(IParsedMainComponentExt.class).base(this).create();
        this.mParsedMainComponentExt = iParsedMainComponentExt;
        this.processName = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.directBootAware = in.readBoolean();
        this.enabled = in.readBoolean();
        this.exported = in.readBoolean();
        this.order = in.readInt();
        this.splitName = in.readString();
        this.attributionTags = in.createString8Array();
        iParsedMainComponentExt.init(in);
    }

    public String getProcessName() {
        return this.processName;
    }

    public boolean isDirectBootAware() {
        return this.directBootAware;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isExported() {
        return this.exported;
    }

    public int getOrder() {
        return this.order;
    }

    public String getSplitName() {
        return this.splitName;
    }

    public String[] getAttributionTags() {
        return this.attributionTags;
    }

    public ParsedMainComponent setDirectBootAware(boolean value) {
        this.directBootAware = value;
        return this;
    }

    public ParsedMainComponent setExported(boolean value) {
        this.exported = value;
        return this;
    }

    public ParsedMainComponent setSplitName(String value) {
        this.splitName = value;
        return this;
    }

    public ParsedMainComponent setAttributionTags(String[] value) {
        this.attributionTags = value;
        return this;
    }
}
