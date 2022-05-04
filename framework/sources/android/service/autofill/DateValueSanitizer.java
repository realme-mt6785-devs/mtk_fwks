package android.service.autofill;

import android.icu.text.DateFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.autofill.AutofillValue;
import android.view.autofill.Helper;
import com.android.internal.util.Preconditions;
import java.util.Date;
/* loaded from: classes3.dex */
public final class DateValueSanitizer extends InternalSanitizer implements Sanitizer, Parcelable {
    public static final Parcelable.Creator<DateValueSanitizer> CREATOR = new Parcelable.Creator<DateValueSanitizer>() { // from class: android.service.autofill.DateValueSanitizer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DateValueSanitizer createFromParcel(Parcel parcel) {
            return new DateValueSanitizer((DateFormat) parcel.readSerializable());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DateValueSanitizer[] newArray(int size) {
            return new DateValueSanitizer[size];
        }
    };
    private static final String TAG = "DateValueSanitizer";
    private final DateFormat mDateFormat;

    public DateValueSanitizer(DateFormat dateFormat) {
        this.mDateFormat = (DateFormat) Preconditions.checkNotNull(dateFormat);
    }

    @Override // android.service.autofill.InternalSanitizer
    public AutofillValue sanitize(AutofillValue value) {
        if (value == null) {
            Log.w(TAG, "sanitize() called with null value");
            return null;
        } else if (!value.isDate()) {
            if (Helper.sDebug) {
                Log.d(TAG, value + " is not a date");
            }
            return null;
        } else {
            try {
                Date date = new Date(value.getDateValue());
                String converted = this.mDateFormat.format(date);
                if (Helper.sDebug) {
                    Log.d(TAG, "Transformed " + date + " to " + converted);
                }
                Date sanitized = this.mDateFormat.parse(converted);
                if (Helper.sDebug) {
                    Log.d(TAG, "Sanitized to " + sanitized);
                }
                return AutofillValue.forDate(sanitized.getTime());
            } catch (Exception e) {
                Log.w(TAG, "Could not apply " + this.mDateFormat + " to " + value + ": " + e);
                return null;
            }
        }
    }

    public String toString() {
        if (!Helper.sDebug) {
            return super.toString();
        }
        return "DateValueSanitizer: [dateFormat=" + this.mDateFormat + "]";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(this.mDateFormat);
    }
}
