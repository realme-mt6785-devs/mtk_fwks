package android.app.time;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.TimestampedValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class ExternalTimeSuggestion implements Parcelable {
    public static final Parcelable.Creator<ExternalTimeSuggestion> CREATOR = new Parcelable.Creator<ExternalTimeSuggestion>() { // from class: android.app.time.ExternalTimeSuggestion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExternalTimeSuggestion createFromParcel(Parcel in) {
            return ExternalTimeSuggestion.createFromParcel(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExternalTimeSuggestion[] newArray(int size) {
            return new ExternalTimeSuggestion[size];
        }
    };
    private ArrayList<String> mDebugInfo;
    private final TimestampedValue<Long> mUtcTime;

    public ExternalTimeSuggestion(long elapsedRealtimeMillis, long suggestionMillis) {
        this.mUtcTime = new TimestampedValue<>(elapsedRealtimeMillis, Long.valueOf(suggestionMillis));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ExternalTimeSuggestion createFromParcel(Parcel in) {
        TimestampedValue<Long> utcTime = (TimestampedValue) in.readParcelable(null);
        ExternalTimeSuggestion suggestion = new ExternalTimeSuggestion(utcTime.getReferenceTimeMillis(), utcTime.getValue().longValue());
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
        if (arrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(arrayList);
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
        ExternalTimeSuggestion that = (ExternalTimeSuggestion) o;
        return Objects.equals(this.mUtcTime, that.mUtcTime);
    }

    public int hashCode() {
        return Objects.hash(this.mUtcTime);
    }

    public String toString() {
        return "ExternalTimeSuggestion{mUtcTime=" + this.mUtcTime + ", mDebugInfo=" + this.mDebugInfo + '}';
    }
}
