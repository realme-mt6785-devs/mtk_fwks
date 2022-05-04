package android.security.keymaster;

import android.os.Parcel;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class KeymasterBooleanArgument extends KeymasterArgument {
    public final boolean value = true;

    public KeymasterBooleanArgument(int tag) {
        super(tag);
        switch (KeymasterDefs.getTagType(tag)) {
            case 1879048192:
                return;
            default:
                throw new IllegalArgumentException("Bad bool tag " + tag);
        }
    }

    public KeymasterBooleanArgument(int tag, Parcel in) {
        super(tag);
    }

    @Override // android.security.keymaster.KeymasterArgument
    public void writeValue(Parcel out) {
    }
}
