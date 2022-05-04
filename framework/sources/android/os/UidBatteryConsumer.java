package android.os;

import android.os.BatteryConsumer;
import android.os.BatteryStats;
import android.os.BatteryUsageStats;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import com.android.internal.os.PowerCalculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public final class UidBatteryConsumer extends BatteryConsumer implements Parcelable {
    public static final Parcelable.Creator<UidBatteryConsumer> CREATOR = new Parcelable.Creator<UidBatteryConsumer>() { // from class: android.os.UidBatteryConsumer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UidBatteryConsumer createFromParcel(Parcel source) {
            return new UidBatteryConsumer(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UidBatteryConsumer[] newArray(int size) {
            return new UidBatteryConsumer[size];
        }
    };
    public static final int STATE_BACKGROUND = 1;
    public static final int STATE_FOREGROUND = 0;
    private final String mPackageWithHighestDrain;
    private final long mTimeInBackgroundMs;
    private final long mTimeInForegroundMs;
    private final int mUid;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
    }

    public int getUid() {
        return this.mUid;
    }

    public String getPackageWithHighestDrain() {
        return this.mPackageWithHighestDrain;
    }

    public long getTimeInStateMs(int state) {
        switch (state) {
            case 0:
                return this.mTimeInForegroundMs;
            case 1:
                return this.mTimeInBackgroundMs;
            default:
                return 0L;
        }
    }

    private UidBatteryConsumer(Builder builder) {
        super(builder.mPowerComponentsBuilder.build());
        this.mUid = builder.mUid;
        this.mPackageWithHighestDrain = builder.mPackageWithHighestDrain;
        this.mTimeInForegroundMs = builder.mTimeInForegroundMs;
        this.mTimeInBackgroundMs = builder.mTimeInBackgroundMs;
    }

    private UidBatteryConsumer(Parcel source) {
        super(new PowerComponents(source));
        this.mUid = source.readInt();
        this.mPackageWithHighestDrain = source.readString();
        this.mTimeInForegroundMs = source.readLong();
        this.mTimeInBackgroundMs = source.readLong();
    }

    @Override // android.os.BatteryConsumer, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mUid);
        dest.writeString(this.mPackageWithHighestDrain);
        dest.writeLong(this.mTimeInForegroundMs);
        dest.writeLong(this.mTimeInBackgroundMs);
    }

    @Override // android.os.BatteryConsumer
    public void dump(PrintWriter pw, boolean skipEmptyComponents) {
        double consumedPower = getConsumedPower();
        pw.print("UID ");
        UserHandle.formatUid(pw, getUid());
        pw.print(": ");
        PowerCalculator.printPowerMah(pw, consumedPower);
        pw.print(" ( ");
        this.mPowerComponents.dump(pw, skipEmptyComponents);
        pw.print(" ) ");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToXml(TypedXmlSerializer serializer) throws IOException {
        if (getConsumedPower() != 0.0d) {
            serializer.startTag(null, "uid");
            serializer.attributeInt(null, "uid", getUid());
            if (!TextUtils.isEmpty(this.mPackageWithHighestDrain)) {
                serializer.attribute(null, "highest_drain_package", this.mPackageWithHighestDrain);
            }
            serializer.attributeLong(null, "time_in_foreground", this.mTimeInForegroundMs);
            serializer.attributeLong(null, "time_in_background", this.mTimeInBackgroundMs);
            this.mPowerComponents.writeToXml(serializer);
            serializer.endTag(null, "uid");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void createFromXml(TypedXmlPullParser parser, BatteryUsageStats.Builder builder) throws XmlPullParserException, IOException {
        int uid = parser.getAttributeInt(null, "uid");
        Builder consumerBuilder = builder.getOrCreateUidBatteryConsumerBuilder(uid);
        int eventType = parser.getEventType();
        if (eventType != 2 || !parser.getName().equals("uid")) {
            throw new XmlPullParserException("Invalid XML parser state");
        }
        consumerBuilder.setPackageWithHighestDrain(parser.getAttributeValue(null, "highest_drain_package"));
        consumerBuilder.setTimeInStateMs(0, parser.getAttributeLong(null, "time_in_foreground"));
        consumerBuilder.setTimeInStateMs(1, parser.getAttributeLong(null, "time_in_background"));
        while (true) {
            if ((eventType != 3 || !parser.getName().equals("uid")) && eventType != 1) {
                if (eventType == 2 && parser.getName().equals("power_components")) {
                    PowerComponents.parseXml(parser, consumerBuilder.mPowerComponentsBuilder);
                }
                eventType = parser.next();
            } else {
                return;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder extends BatteryConsumer.BaseBuilder<Builder> {
        private static final String PACKAGE_NAME_UNINITIALIZED = "";
        private final BatteryStats.Uid mBatteryStatsUid;
        private boolean mExcludeFromBatteryUsageStats;
        private String mPackageWithHighestDrain;
        public long mTimeInBackgroundMs;
        public long mTimeInForegroundMs;
        private final int mUid;

        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ double getTotalPower() {
            return super.getTotalPower();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UidBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPower(int i, double d) {
            return super.setConsumedPower(i, d);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UidBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPower(int i, double d, int i2) {
            return super.setConsumedPower(i, d, i2);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UidBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPowerForCustomComponent(int i, double d) {
            return super.setConsumedPowerForCustomComponent(i, d);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UidBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setUsageDurationForCustomComponentMillis(int i, long j) {
            return super.setUsageDurationForCustomComponentMillis(i, j);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UidBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setUsageDurationMillis(int i, long j) {
            return super.setUsageDurationMillis(i, j);
        }

        public Builder(String[] customPowerComponentNames, boolean includePowerModels, BatteryStats.Uid batteryStatsUid) {
            super(customPowerComponentNames, includePowerModels);
            this.mPackageWithHighestDrain = "";
            this.mBatteryStatsUid = batteryStatsUid;
            this.mUid = batteryStatsUid.getUid();
        }

        public Builder(String[] customPowerComponentNames, boolean includePowerModels, int uid) {
            super(customPowerComponentNames, includePowerModels);
            this.mPackageWithHighestDrain = "";
            this.mBatteryStatsUid = null;
            this.mUid = uid;
        }

        public BatteryStats.Uid getBatteryStatsUid() {
            BatteryStats.Uid uid = this.mBatteryStatsUid;
            if (uid != null) {
                return uid;
            }
            throw new IllegalStateException("UidBatteryConsumer.Builder was initialized without a BatteryStats.Uid");
        }

        public int getUid() {
            return this.mUid;
        }

        public Builder setPackageWithHighestDrain(String packageName) {
            this.mPackageWithHighestDrain = TextUtils.nullIfEmpty(packageName);
            return this;
        }

        public Builder setTimeInStateMs(int state, long timeInStateMs) {
            switch (state) {
                case 0:
                    this.mTimeInForegroundMs = timeInStateMs;
                    break;
                case 1:
                    this.mTimeInBackgroundMs = timeInStateMs;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported state: " + state);
            }
            return this;
        }

        public Builder excludeFromBatteryUsageStats() {
            this.mExcludeFromBatteryUsageStats = true;
            return this;
        }

        public Builder add(UidBatteryConsumer consumer) {
            this.mPowerComponentsBuilder.addPowerAndDuration(consumer.mPowerComponents);
            this.mTimeInBackgroundMs += consumer.mTimeInBackgroundMs;
            this.mTimeInForegroundMs += consumer.mTimeInForegroundMs;
            String str = this.mPackageWithHighestDrain;
            if (str == "") {
                this.mPackageWithHighestDrain = consumer.mPackageWithHighestDrain;
            } else if (!TextUtils.equals(str, consumer.mPackageWithHighestDrain)) {
                this.mPackageWithHighestDrain = null;
            }
            return this;
        }

        public boolean isExcludedFromBatteryUsageStats() {
            return this.mExcludeFromBatteryUsageStats;
        }

        public UidBatteryConsumer build() {
            if (this.mPackageWithHighestDrain == "") {
                this.mPackageWithHighestDrain = null;
            }
            return new UidBatteryConsumer(this);
        }
    }
}
