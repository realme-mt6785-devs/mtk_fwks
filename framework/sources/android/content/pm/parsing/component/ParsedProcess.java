package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.content.pm.ApplicationInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes.dex */
public class ParsedProcess implements Parcelable {
    public static final Parcelable.Creator<ParsedProcess> CREATOR;
    static Parcelling<Set<String>> sParcellingForDeniedPermissions;
    protected Set<String> deniedPermissions;
    protected int gwpAsanMode;
    protected int memtagMode;
    protected String name;
    protected int nativeHeapZeroInitialized;

    public ParsedProcess() {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        this.memtagMode = -1;
        this.nativeHeapZeroInitialized = -1;
    }

    public ParsedProcess(ParsedProcess other) {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        this.memtagMode = -1;
        this.nativeHeapZeroInitialized = -1;
        this.name = other.name;
        this.deniedPermissions = new ArraySet(other.deniedPermissions);
    }

    public void addStateFrom(ParsedProcess other) {
        this.deniedPermissions = CollectionUtils.addAll(this.deniedPermissions, other.deniedPermissions);
    }

    public ParsedProcess(String name, Set<String> deniedPermissions, int gwpAsanMode, int memtagMode, int nativeHeapZeroInitialized) {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        this.memtagMode = -1;
        this.nativeHeapZeroInitialized = -1;
        this.name = name;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) name);
        this.deniedPermissions = deniedPermissions;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) deniedPermissions);
        this.gwpAsanMode = gwpAsanMode;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.GwpAsanMode.class, (Annotation) null, gwpAsanMode);
        this.memtagMode = memtagMode;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.MemtagMode.class, (Annotation) null, memtagMode);
        this.nativeHeapZeroInitialized = nativeHeapZeroInitialized;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.NativeHeapZeroInitialized.class, (Annotation) null, nativeHeapZeroInitialized);
    }

    public String getName() {
        return this.name;
    }

    public Set<String> getDeniedPermissions() {
        return this.deniedPermissions;
    }

    public int getGwpAsanMode() {
        return this.gwpAsanMode;
    }

    public int getMemtagMode() {
        return this.memtagMode;
    }

    public int getNativeHeapZeroInitialized() {
        return this.nativeHeapZeroInitialized;
    }

    static {
        Parcelling<Set<String>> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForInternedStringSet.class);
        sParcellingForDeniedPermissions = parcelling;
        if (parcelling == null) {
            sParcellingForDeniedPermissions = Parcelling.Cache.put(new Parcelling.BuiltIn.ForInternedStringSet());
        }
        CREATOR = new Parcelable.Creator<ParsedProcess>() { // from class: android.content.pm.parsing.component.ParsedProcess.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ParsedProcess[] newArray(int size) {
                return new ParsedProcess[size];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ParsedProcess createFromParcel(Parcel in) {
                return new ParsedProcess(in);
            }
        };
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        sParcellingForDeniedPermissions.parcel(this.deniedPermissions, dest, flags);
        dest.writeInt(this.gwpAsanMode);
        dest.writeInt(this.memtagMode);
        dest.writeInt(this.nativeHeapZeroInitialized);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ParsedProcess(Parcel in) {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        this.memtagMode = -1;
        this.nativeHeapZeroInitialized = -1;
        String _name = in.readString();
        Set<String> _deniedPermissions = sParcellingForDeniedPermissions.unparcel(in);
        int _gwpAsanMode = in.readInt();
        int _memtagMode = in.readInt();
        int _nativeHeapZeroInitialized = in.readInt();
        this.name = _name;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) _name);
        this.deniedPermissions = _deniedPermissions;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) _deniedPermissions);
        this.gwpAsanMode = _gwpAsanMode;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.GwpAsanMode.class, (Annotation) null, _gwpAsanMode);
        this.memtagMode = _memtagMode;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.MemtagMode.class, (Annotation) null, _memtagMode);
        this.nativeHeapZeroInitialized = _nativeHeapZeroInitialized;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.NativeHeapZeroInitialized.class, (Annotation) null, _nativeHeapZeroInitialized);
    }

    @Deprecated
    private void __metadata() {
    }
}
