package android.os;

import android.content.Context;
import android.os.BatteryConsumer;
import android.os.BatteryUsageStats;
import android.os.Parcelable;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import java.io.IOException;
import java.io.PrintWriter;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public final class AggregateBatteryConsumer extends BatteryConsumer implements Parcelable {
    public static final Parcelable.Creator<AggregateBatteryConsumer> CREATOR = new Parcelable.Creator<AggregateBatteryConsumer>() { // from class: android.os.AggregateBatteryConsumer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AggregateBatteryConsumer createFromParcel(Parcel source) {
            return new AggregateBatteryConsumer(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AggregateBatteryConsumer[] newArray(int size) {
            return new AggregateBatteryConsumer[size];
        }
    };
    private final double mConsumedPowerMah;

    public AggregateBatteryConsumer(Builder builder) {
        super(builder.mPowerComponentsBuilder.build());
        this.mConsumedPowerMah = builder.mConsumedPowerMah;
    }

    private AggregateBatteryConsumer(Parcel source) {
        super(new PowerComponents(source));
        this.mConsumedPowerMah = source.readDouble();
    }

    @Override // android.os.BatteryConsumer
    public void dump(PrintWriter pw, boolean skipEmptyComponents) {
        this.mPowerComponents.dump(pw, skipEmptyComponents);
    }

    @Override // android.os.BatteryConsumer, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(this.mConsumedPowerMah);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.BatteryConsumer
    public double getConsumedPower() {
        return this.mConsumedPowerMah;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToXml(TypedXmlSerializer serializer, int scope) throws IOException {
        serializer.startTag(null, "aggregate");
        serializer.attributeInt(null, "scope", scope);
        serializer.attributeDouble(null, Context.POWER_SERVICE, this.mConsumedPowerMah);
        this.mPowerComponents.writeToXml(serializer);
        serializer.endTag(null, "aggregate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void parseXml(TypedXmlPullParser parser, BatteryUsageStats.Builder builder) throws XmlPullParserException, IOException {
        int scope = parser.getAttributeInt(null, "scope");
        Builder consumerBuilder = builder.getAggregateBatteryConsumerBuilder(scope);
        int eventType = parser.getEventType();
        if (eventType != 2 || !parser.getName().equals("aggregate")) {
            throw new XmlPullParserException("Invalid XML parser state");
        }
        consumerBuilder.setConsumedPower(parser.getAttributeDouble(null, Context.POWER_SERVICE));
        while (true) {
            if ((eventType != 3 || !parser.getName().equals("aggregate")) && eventType != 1) {
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
        private double mConsumedPowerMah;

        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ double getTotalPower() {
            return super.getTotalPower();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.AggregateBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPower(int i, double d) {
            return super.setConsumedPower(i, d);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.AggregateBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPower(int i, double d, int i2) {
            return super.setConsumedPower(i, d, i2);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.AggregateBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPowerForCustomComponent(int i, double d) {
            return super.setConsumedPowerForCustomComponent(i, d);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.AggregateBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setUsageDurationForCustomComponentMillis(int i, long j) {
            return super.setUsageDurationForCustomComponentMillis(i, j);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.AggregateBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setUsageDurationMillis(int i, long j) {
            return super.setUsageDurationMillis(i, j);
        }

        public Builder(String[] customPowerComponentNames, boolean includePowerModels) {
            super(customPowerComponentNames, includePowerModels);
        }

        public Builder setConsumedPower(double consumedPowerMah) {
            this.mConsumedPowerMah = consumedPowerMah;
            return this;
        }

        public void add(AggregateBatteryConsumer aggregateBatteryConsumer) {
            this.mConsumedPowerMah += aggregateBatteryConsumer.mConsumedPowerMah;
            this.mPowerComponentsBuilder.addPowerAndDuration(aggregateBatteryConsumer.mPowerComponents);
        }

        public AggregateBatteryConsumer build() {
            return new AggregateBatteryConsumer(this);
        }
    }
}
