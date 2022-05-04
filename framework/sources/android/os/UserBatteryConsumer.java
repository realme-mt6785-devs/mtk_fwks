package android.os;

import android.os.BatteryConsumer;
import android.os.BatteryUsageStats;
import android.os.Parcelable;
import android.os.UidBatteryConsumer;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import com.android.internal.os.PowerCalculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public class UserBatteryConsumer extends BatteryConsumer implements Parcelable {
    public static final Parcelable.Creator<UserBatteryConsumer> CREATOR = new Parcelable.Creator<UserBatteryConsumer>() { // from class: android.os.UserBatteryConsumer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserBatteryConsumer createFromParcel(Parcel in) {
            return new UserBatteryConsumer(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserBatteryConsumer[] newArray(int size) {
            return new UserBatteryConsumer[size];
        }
    };
    private final int mUserId;

    public int getUserId() {
        return this.mUserId;
    }

    private UserBatteryConsumer(Builder builder) {
        super(builder.mPowerComponentsBuilder.build());
        this.mUserId = builder.mUserId;
    }

    private UserBatteryConsumer(Parcel in) {
        super(new PowerComponents(in));
        this.mUserId = in.readInt();
    }

    @Override // android.os.BatteryConsumer, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mUserId);
    }

    @Override // android.os.BatteryConsumer
    public void dump(PrintWriter pw, boolean skipEmptyComponents) {
        double consumedPower = getConsumedPower();
        pw.print("User ");
        pw.print(this.mUserId);
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
            serializer.startTag(null, "user");
            serializer.attributeInt(null, "user_id", getUserId());
            this.mPowerComponents.writeToXml(serializer);
            serializer.endTag(null, "user");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void createFromXml(TypedXmlPullParser parser, BatteryUsageStats.Builder builder) throws XmlPullParserException, IOException {
        int userId = parser.getAttributeInt(null, "user_id");
        Builder consumerBuilder = builder.getOrCreateUserBatteryConsumerBuilder(userId);
        int eventType = parser.getEventType();
        if (eventType != 2 || !parser.getName().equals("user")) {
            throw new XmlPullParserException("Invalid XML parser state");
        }
        while (true) {
            if ((eventType != 3 || !parser.getName().equals("user")) && eventType != 1) {
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
        private List<UidBatteryConsumer.Builder> mUidBatteryConsumers;
        private final int mUserId;

        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ double getTotalPower() {
            return super.getTotalPower();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UserBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPower(int i, double d) {
            return super.setConsumedPower(i, d);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UserBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPower(int i, double d, int i2) {
            return super.setConsumedPower(i, d, i2);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UserBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setConsumedPowerForCustomComponent(int i, double d) {
            return super.setConsumedPowerForCustomComponent(i, d);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UserBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setUsageDurationForCustomComponentMillis(int i, long j) {
            return super.setUsageDurationForCustomComponentMillis(i, j);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.UserBatteryConsumer$Builder, android.os.BatteryConsumer$BaseBuilder] */
        @Override // android.os.BatteryConsumer.BaseBuilder
        public /* bridge */ /* synthetic */ Builder setUsageDurationMillis(int i, long j) {
            return super.setUsageDurationMillis(i, j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(String[] customPowerComponentNames, boolean includePowerModels, int userId) {
            super(customPowerComponentNames, includePowerModels);
            this.mUserId = userId;
        }

        public void addUidBatteryConsumer(UidBatteryConsumer.Builder uidBatteryConsumerBuilder) {
            if (this.mUidBatteryConsumers == null) {
                this.mUidBatteryConsumers = new ArrayList();
            }
            this.mUidBatteryConsumers.add(uidBatteryConsumerBuilder);
        }

        public UserBatteryConsumer build() {
            List<UidBatteryConsumer.Builder> list = this.mUidBatteryConsumers;
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    UidBatteryConsumer.Builder uidBatteryConsumer = this.mUidBatteryConsumers.get(i);
                    this.mPowerComponentsBuilder.addPowerAndDuration(uidBatteryConsumer.mPowerComponentsBuilder);
                }
            }
            return new UserBatteryConsumer(this);
        }
    }
}
