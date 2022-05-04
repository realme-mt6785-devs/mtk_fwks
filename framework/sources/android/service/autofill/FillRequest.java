package android.service.autofill;

import android.annotation.NonNull;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.inputmethod.InlineSuggestionsRequest;
import com.android.internal.org.bouncycastle.math.ec.Tnaf;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.BitUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public final class FillRequest implements Parcelable {
    public static final Parcelable.Creator<FillRequest> CREATOR = new Parcelable.Creator<FillRequest>() { // from class: android.service.autofill.FillRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FillRequest[] newArray(int size) {
            return new FillRequest[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FillRequest createFromParcel(Parcel in) {
            return new FillRequest(in);
        }
    };
    public static final int FLAG_COMPATIBILITY_MODE_REQUEST = 2;
    public static final int FLAG_MANUAL_REQUEST = 1;
    public static final int FLAG_PASSWORD_INPUT_TYPE = 4;
    public static final int FLAG_VIEW_NOT_FOCUSED = 16;
    public static final int INVALID_REQUEST_ID = Integer.MIN_VALUE;
    private final Bundle mClientState;
    private final List<FillContext> mFillContexts;
    private final int mFlags;
    private final int mId;
    private final InlineSuggestionsRequest mInlineSuggestionsRequest;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface RequestFlags {
    }

    private void onConstructed() {
        Preconditions.checkCollectionElementsNotNull(this.mFillContexts, "contexts");
    }

    public static String requestFlagsToString(int value) {
        return BitUtils.flagsToString(value, FillRequest$$ExternalSyntheticLambda0.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String singleRequestFlagsToString(int value) {
        switch (value) {
            case 1:
                return "FLAG_MANUAL_REQUEST";
            case 2:
                return "FLAG_COMPATIBILITY_MODE_REQUEST";
            case 4:
                return "FLAG_PASSWORD_INPUT_TYPE";
            case 16:
                return "FLAG_VIEW_NOT_FOCUSED";
            default:
                return Integer.toHexString(value);
        }
    }

    public FillRequest(int id, List<FillContext> fillContexts, Bundle clientState, int flags, InlineSuggestionsRequest inlineSuggestionsRequest) {
        this.mId = id;
        this.mFillContexts = fillContexts;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) fillContexts);
        this.mClientState = clientState;
        this.mFlags = flags;
        Preconditions.checkFlagsArgument(flags, 23);
        this.mInlineSuggestionsRequest = inlineSuggestionsRequest;
        onConstructed();
    }

    public int getId() {
        return this.mId;
    }

    public List<FillContext> getFillContexts() {
        return this.mFillContexts;
    }

    public Bundle getClientState() {
        return this.mClientState;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public InlineSuggestionsRequest getInlineSuggestionsRequest() {
        return this.mInlineSuggestionsRequest;
    }

    public String toString() {
        return "FillRequest { id = " + this.mId + ", fillContexts = " + this.mFillContexts + ", clientState = " + this.mClientState + ", flags = " + requestFlagsToString(this.mFlags) + ", inlineSuggestionsRequest = " + this.mInlineSuggestionsRequest + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte flg = 0;
        if (this.mClientState != null) {
            flg = (byte) (0 | 4);
        }
        if (this.mInlineSuggestionsRequest != null) {
            flg = (byte) (flg | Tnaf.POW_2_WIDTH);
        }
        dest.writeByte(flg);
        dest.writeInt(this.mId);
        dest.writeParcelableList(this.mFillContexts, flags);
        Bundle bundle = this.mClientState;
        if (bundle != null) {
            dest.writeBundle(bundle);
        }
        dest.writeInt(this.mFlags);
        InlineSuggestionsRequest inlineSuggestionsRequest = this.mInlineSuggestionsRequest;
        if (inlineSuggestionsRequest != null) {
            dest.writeTypedObject(inlineSuggestionsRequest, flags);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    FillRequest(Parcel in) {
        byte flg = in.readByte();
        int id = in.readInt();
        ArrayList arrayList = new ArrayList();
        in.readParcelableList(arrayList, FillContext.class.getClassLoader());
        Bundle clientState = (flg & 4) == 0 ? null : in.readBundle();
        int flags = in.readInt();
        InlineSuggestionsRequest inlineSuggestionsRequest = (flg & Tnaf.POW_2_WIDTH) == 0 ? null : (InlineSuggestionsRequest) in.readTypedObject(InlineSuggestionsRequest.CREATOR);
        this.mId = id;
        this.mFillContexts = arrayList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) arrayList);
        this.mClientState = clientState;
        this.mFlags = flags;
        Preconditions.checkFlagsArgument(flags, 23);
        this.mInlineSuggestionsRequest = inlineSuggestionsRequest;
        onConstructed();
    }

    @Deprecated
    private void __metadata() {
    }
}
