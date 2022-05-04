package android.location;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes2.dex */
public class GeocoderParams implements Parcelable {
    public static final Parcelable.Creator<GeocoderParams> CREATOR = new Parcelable.Creator<GeocoderParams>() { // from class: android.location.GeocoderParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeocoderParams createFromParcel(Parcel in) {
            int uid = in.readInt();
            String packageName = in.readString();
            String attributionTag = in.readString();
            String language = in.readString();
            String country = in.readString();
            String variant = in.readString();
            return new GeocoderParams(uid, packageName, attributionTag, new Locale(language, country, variant));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeocoderParams[] newArray(int size) {
            return new GeocoderParams[size];
        }
    };
    private final String mAttributionTag;
    private final Locale mLocale;
    private final String mPackageName;
    private final int mUid;

    public GeocoderParams(Context context) {
        this(context, Locale.getDefault());
    }

    public GeocoderParams(Context context, Locale locale) {
        this(Process.myUid(), context.getPackageName(), context.getAttributionTag(), locale);
    }

    private GeocoderParams(int uid, String packageName, String attributionTag, Locale locale) {
        this.mUid = uid;
        Objects.requireNonNull(packageName);
        this.mPackageName = packageName;
        this.mAttributionTag = attributionTag;
        Objects.requireNonNull(locale);
        this.mLocale = locale;
    }

    public int getClientUid() {
        return this.mUid;
    }

    public String getClientPackage() {
        return this.mPackageName;
    }

    public String getClientAttributionTag() {
        return this.mAttributionTag;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mUid);
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.mAttributionTag);
        parcel.writeString(this.mLocale.getLanguage());
        parcel.writeString(this.mLocale.getCountry());
        parcel.writeString(this.mLocale.getVariant());
    }
}
