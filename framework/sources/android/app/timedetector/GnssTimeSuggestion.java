package android.app.timedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.TimestampedValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class GnssTimeSuggestion implements Parcelable {
    public static final Parcelable.Creator<GnssTimeSuggestion> CREATOR = new Parcelable.Creator<GnssTimeSuggestion>() { // from class: android.app.timedetector.GnssTimeSuggestion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssTimeSuggestion createFromParcel(Parcel in) {
            return GnssTimeSuggestion.createFromParcel(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssTimeSuggestion[] newArray(int size) {
            return new GnssTimeSuggestion[size];
        }
    };
    private ArrayList<String> mDebugInfo;
    private final TimestampedValue<Long> mUtcTime;

    public GnssTimeSuggestion(TimestampedValue<Long> utcTime) {
        Objects.requireNonNull(utcTime);
        this.mUtcTime = utcTime;
        Objects.requireNonNull(utcTime.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GnssTimeSuggestion createFromParcel(Parcel in) {
        TimestampedValue<Long> utcTime = (TimestampedValue) in.readParcelable(null);
        GnssTimeSuggestion suggestion = new GnssTimeSuggestion(utcTime);
        ArrayList<String> debugInfo = in.readArrayList(null);
        suggestion.mDebugInfo = debugInfo;
        return suggestion;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mUtcTime, 0);
        dest.writeList(this.mDebugInfo);
    }

    public TimestampedValue<Long> getUtcTime() {
        return this.mUtcTime;
    }

    public List<String> getDebugInfo() {
        ArrayList<String> arrayList = this.mDebugInfo;
        return arrayList == null ? Collections.emptyList() : Collections.unmodifiableList(arrayList);
    }

    public void addDebugInfo(String... debugInfos) {
        if (this.mDebugInfo == null) {
            this.mDebugInfo = new ArrayList<>();
        }
        this.mDebugInfo.addAll(Arrays.asList(debugInfos));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GnssTimeSuggestion that = (GnssTimeSuggestion) o;
        return Objects.equals(this.mUtcTime, that.mUtcTime);
    }

    public int hashCode() {
        return Objects.hash(this.mUtcTime);
    }

    public String toString() {
        return "GnssTimeSuggestion{mUtcTime=" + this.mUtcTime + ", mDebugInfo=" + this.mDebugInfo + '}';
    }
}
