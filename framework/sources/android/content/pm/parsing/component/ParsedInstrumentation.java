package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class ParsedInstrumentation extends ParsedComponent {
    public static final Parcelable.Creator<ParsedInstrumentation> CREATOR = new Parcelable.Creator<ParsedInstrumentation>() { // from class: android.content.pm.parsing.component.ParsedInstrumentation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedInstrumentation createFromParcel(Parcel source) {
            return new ParsedInstrumentation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedInstrumentation[] newArray(int size) {
            return new ParsedInstrumentation[size];
        }
    };
    boolean functionalTest;
    boolean handleProfiling;
    private String targetPackage;
    private String targetProcesses;

    public ParsedInstrumentation() {
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = TextUtils.safeIntern(targetPackage);
    }

    public void setTargetProcesses(String targetProcesses) {
        this.targetProcesses = TextUtils.safeIntern(targetProcesses);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Instrumentation{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        ParsingPackageImpl.sForInternedString.parcel(this.targetPackage, dest, flags);
        ParsingPackageImpl.sForInternedString.parcel(this.targetProcesses, dest, flags);
        dest.writeBoolean(this.handleProfiling);
        dest.writeBoolean(this.functionalTest);
    }

    protected ParsedInstrumentation(Parcel in) {
        super(in);
        this.targetPackage = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.targetProcesses = ParsingPackageImpl.sForInternedString.unparcel(in);
        boolean z = true;
        this.handleProfiling = in.readByte() != 0;
        this.functionalTest = in.readByte() == 0 ? false : z;
    }

    public String getTargetPackage() {
        return this.targetPackage;
    }

    public String getTargetProcesses() {
        return this.targetProcesses;
    }

    public boolean isHandleProfiling() {
        return this.handleProfiling;
    }

    public boolean isFunctionalTest() {
        return this.functionalTest;
    }
}
