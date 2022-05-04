package android.os;

import android.os.AggregateBatteryConsumer;
import android.os.BatteryStats;
import android.os.Parcelable;
import android.os.UidBatteryConsumer;
import android.os.UserBatteryConsumer;
import android.util.Range;
import android.util.SparseArray;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import android.util.proto.ProtoOutputStream;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.os.BatteryStatsHistory;
import com.android.internal.os.BatteryStatsHistoryIterator;
import com.android.internal.os.PowerCalculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public final class BatteryUsageStats implements Parcelable {
    public static final int AGGREGATE_BATTERY_CONSUMER_SCOPE_ALL_APPS = 1;
    public static final int AGGREGATE_BATTERY_CONSUMER_SCOPE_COUNT = 2;
    public static final int AGGREGATE_BATTERY_CONSUMER_SCOPE_DEVICE = 0;
    public static final Parcelable.Creator<BatteryUsageStats> CREATOR = new Parcelable.Creator<BatteryUsageStats>() { // from class: android.os.BatteryUsageStats.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatteryUsageStats createFromParcel(Parcel source) {
            return new BatteryUsageStats(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatteryUsageStats[] newArray(int size) {
            return new BatteryUsageStats[size];
        }
    };
    private static final int STATSD_PULL_ATOM_MAX_BYTES = 45000;
    private static final double WEIGHT_BACKGROUND_STATE = 8.333333333333333E-5d;
    private static final double WEIGHT_CONSUMED_POWER = 1.0d;
    private static final double WEIGHT_FOREGROUND_STATE = 2.777777777777778E-5d;
    static final String XML_ATTR_BATTERY_CAPACITY = "battery_capacity";
    static final String XML_ATTR_BATTERY_REMAINING = "battery_remaining";
    static final String XML_ATTR_CHARGE_REMAINING = "charge_remaining";
    static final String XML_ATTR_DISCHARGE_LOWER = "discharge_lower";
    static final String XML_ATTR_DISCHARGE_PERCENT = "discharge_pct";
    static final String XML_ATTR_DISCHARGE_UPPER = "discharge_upper";
    static final String XML_ATTR_DURATION = "duration";
    static final String XML_ATTR_END_TIMESTAMP = "end_timestamp";
    static final String XML_ATTR_HIGHEST_DRAIN_PACKAGE = "highest_drain_package";
    static final String XML_ATTR_ID = "id";
    static final String XML_ATTR_MODEL = "model";
    static final String XML_ATTR_POWER = "power";
    static final String XML_ATTR_PREFIX_CUSTOM_COMPONENT = "custom_component_";
    static final String XML_ATTR_SCOPE = "scope";
    static final String XML_ATTR_START_TIMESTAMP = "start_timestamp";
    static final String XML_ATTR_TIME_IN_BACKGROUND = "time_in_background";
    static final String XML_ATTR_TIME_IN_FOREGROUND = "time_in_foreground";
    static final String XML_ATTR_UID = "uid";
    static final String XML_ATTR_USER_ID = "user_id";
    static final String XML_TAG_AGGREGATE = "aggregate";
    static final String XML_TAG_BATTERY_USAGE_STATS = "battery_usage_stats";
    static final String XML_TAG_COMPONENT = "component";
    static final String XML_TAG_CUSTOM_COMPONENT = "custom_component";
    static final String XML_TAG_POWER_COMPONENTS = "power_components";
    static final String XML_TAG_UID = "uid";
    static final String XML_TAG_USER = "user";
    private final AggregateBatteryConsumer[] mAggregateBatteryConsumers;
    private final double mBatteryCapacityMah;
    private final long mBatteryTimeRemainingMs;
    private final long mChargeTimeRemainingMs;
    private final String[] mCustomPowerComponentNames;
    private final int mDischargePercentage;
    private final double mDischargedPowerLowerBound;
    private final double mDischargedPowerUpperBound;
    private final Parcel mHistoryBuffer;
    private final List<BatteryStats.HistoryTag> mHistoryTagPool;
    private final long mStatsDurationMs;
    private final long mStatsEndTimestampMs;
    private final long mStatsStartTimestampMs;
    private final List<UidBatteryConsumer> mUidBatteryConsumers;
    private final List<UserBatteryConsumer> mUserBatteryConsumers;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AggregateBatteryConsumerScope {
    }

    private BatteryUsageStats(Builder builder) {
        this.mStatsStartTimestampMs = builder.mStatsStartTimestampMs;
        this.mStatsEndTimestampMs = builder.mStatsEndTimestampMs;
        this.mStatsDurationMs = builder.getStatsDuration();
        this.mBatteryCapacityMah = builder.mBatteryCapacityMah;
        this.mDischargePercentage = builder.mDischargePercentage;
        this.mDischargedPowerLowerBound = builder.mDischargedPowerLowerBoundMah;
        this.mDischargedPowerUpperBound = builder.mDischargedPowerUpperBoundMah;
        this.mHistoryBuffer = builder.mHistoryBuffer;
        this.mHistoryTagPool = builder.mHistoryTagPool;
        this.mBatteryTimeRemainingMs = builder.mBatteryTimeRemainingMs;
        this.mChargeTimeRemainingMs = builder.mChargeTimeRemainingMs;
        this.mCustomPowerComponentNames = builder.mCustomPowerComponentNames;
        double totalPowerMah = 0.0d;
        int uidBatteryConsumerCount = builder.mUidBatteryConsumerBuilders.size();
        this.mUidBatteryConsumers = new ArrayList(uidBatteryConsumerCount);
        for (int i = 0; i < uidBatteryConsumerCount; i++) {
            UidBatteryConsumer.Builder uidBatteryConsumerBuilder = (UidBatteryConsumer.Builder) builder.mUidBatteryConsumerBuilders.valueAt(i);
            if (!uidBatteryConsumerBuilder.isExcludedFromBatteryUsageStats()) {
                UidBatteryConsumer consumer = uidBatteryConsumerBuilder.build();
                totalPowerMah += consumer.getConsumedPower();
                this.mUidBatteryConsumers.add(consumer);
            }
        }
        int userBatteryConsumerCount = builder.mUserBatteryConsumerBuilders.size();
        this.mUserBatteryConsumers = new ArrayList(userBatteryConsumerCount);
        for (int i2 = 0; i2 < userBatteryConsumerCount; i2++) {
            UserBatteryConsumer consumer2 = ((UserBatteryConsumer.Builder) builder.mUserBatteryConsumerBuilders.valueAt(i2)).build();
            totalPowerMah += consumer2.getConsumedPower();
            this.mUserBatteryConsumers.add(consumer2);
        }
        builder.getAggregateBatteryConsumerBuilder(1).setConsumedPower(totalPowerMah);
        this.mAggregateBatteryConsumers = new AggregateBatteryConsumer[2];
        for (int i3 = 0; i3 < 2; i3++) {
            this.mAggregateBatteryConsumers[i3] = builder.mAggregateBatteryConsumersBuilders[i3].build();
        }
    }

    public long getStatsStartTimestamp() {
        return this.mStatsStartTimestampMs;
    }

    public long getStatsEndTimestamp() {
        return this.mStatsEndTimestampMs;
    }

    public long getStatsDuration() {
        return this.mStatsDurationMs;
    }

    public double getConsumedPower() {
        return this.mAggregateBatteryConsumers[0].getConsumedPower();
    }

    public double getBatteryCapacity() {
        return this.mBatteryCapacityMah;
    }

    public int getDischargePercentage() {
        return this.mDischargePercentage;
    }

    public Range<Double> getDischargedPowerRange() {
        return Range.create(Double.valueOf(this.mDischargedPowerLowerBound), Double.valueOf(this.mDischargedPowerUpperBound));
    }

    public long getBatteryTimeRemainingMs() {
        return this.mBatteryTimeRemainingMs;
    }

    public long getChargeTimeRemainingMs() {
        return this.mChargeTimeRemainingMs;
    }

    public BatteryConsumer getAggregateBatteryConsumer(int scope) {
        return this.mAggregateBatteryConsumers[scope];
    }

    public List<UidBatteryConsumer> getUidBatteryConsumers() {
        return this.mUidBatteryConsumers;
    }

    public List<UserBatteryConsumer> getUserBatteryConsumers() {
        return this.mUserBatteryConsumers;
    }

    public BatteryStatsHistoryIterator iterateBatteryStatsHistory() {
        Parcel parcel = this.mHistoryBuffer;
        if (parcel != null) {
            return new BatteryStatsHistoryIterator(new BatteryStatsHistory(parcel), this.mHistoryTagPool);
        }
        throw new IllegalStateException("Battery history was not requested in the BatteryUsageStatsQuery");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private BatteryUsageStats(Parcel source) {
        this.mStatsStartTimestampMs = source.readLong();
        this.mStatsEndTimestampMs = source.readLong();
        this.mStatsDurationMs = source.readLong();
        this.mBatteryCapacityMah = source.readDouble();
        this.mDischargePercentage = source.readInt();
        this.mDischargedPowerLowerBound = source.readDouble();
        this.mDischargedPowerUpperBound = source.readDouble();
        this.mBatteryTimeRemainingMs = source.readLong();
        this.mChargeTimeRemainingMs = source.readLong();
        this.mCustomPowerComponentNames = source.readStringArray();
        this.mAggregateBatteryConsumers = new AggregateBatteryConsumer[2];
        for (int i = 0; i < 2; i++) {
            this.mAggregateBatteryConsumers[i] = AggregateBatteryConsumer.CREATOR.createFromParcel(source);
            this.mAggregateBatteryConsumers[i].setCustomPowerComponentNames(this.mCustomPowerComponentNames);
        }
        Parcel blob = Parcel.obtain();
        byte[] bytes = source.readBlob();
        blob.unmarshall(bytes, 0, bytes.length);
        blob.setDataPosition(0);
        int uidCount = blob.readInt();
        this.mUidBatteryConsumers = new ArrayList(uidCount);
        for (int i2 = 0; i2 < uidCount; i2++) {
            UidBatteryConsumer consumer = UidBatteryConsumer.CREATOR.createFromParcel(blob);
            consumer.setCustomPowerComponentNames(this.mCustomPowerComponentNames);
            this.mUidBatteryConsumers.add(consumer);
        }
        blob.recycle();
        int userCount = source.readInt();
        this.mUserBatteryConsumers = new ArrayList(userCount);
        for (int i3 = 0; i3 < userCount; i3++) {
            UserBatteryConsumer consumer2 = UserBatteryConsumer.CREATOR.createFromParcel(source);
            consumer2.setCustomPowerComponentNames(this.mCustomPowerComponentNames);
            this.mUserBatteryConsumers.add(consumer2);
        }
        if (source.readBoolean()) {
            byte[] historyBlob = source.readBlob();
            Parcel obtain = Parcel.obtain();
            this.mHistoryBuffer = obtain;
            obtain.unmarshall(historyBlob, 0, historyBlob.length);
            int historyTagCount = source.readInt();
            this.mHistoryTagPool = new ArrayList(historyTagCount);
            for (int i4 = 0; i4 < historyTagCount; i4++) {
                BatteryStats.HistoryTag tag = new BatteryStats.HistoryTag();
                tag.string = source.readString();
                tag.uid = source.readInt();
                tag.poolIdx = source.readInt();
                this.mHistoryTagPool.add(tag);
            }
            return;
        }
        this.mHistoryBuffer = null;
        this.mHistoryTagPool = null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mStatsStartTimestampMs);
        dest.writeLong(this.mStatsEndTimestampMs);
        dest.writeLong(this.mStatsDurationMs);
        dest.writeDouble(this.mBatteryCapacityMah);
        dest.writeInt(this.mDischargePercentage);
        dest.writeDouble(this.mDischargedPowerLowerBound);
        dest.writeDouble(this.mDischargedPowerUpperBound);
        dest.writeLong(this.mBatteryTimeRemainingMs);
        dest.writeLong(this.mChargeTimeRemainingMs);
        dest.writeStringArray(this.mCustomPowerComponentNames);
        for (int i = 0; i < 2; i++) {
            this.mAggregateBatteryConsumers[i].writeToParcel(dest, flags);
        }
        Parcel blob = Parcel.obtain();
        blob.writeInt(this.mUidBatteryConsumers.size());
        for (int i2 = this.mUidBatteryConsumers.size() - 1; i2 >= 0; i2--) {
            this.mUidBatteryConsumers.get(i2).writeToParcel(blob, flags);
        }
        dest.writeBlob(blob.marshall());
        blob.recycle();
        dest.writeInt(this.mUserBatteryConsumers.size());
        for (int i3 = this.mUserBatteryConsumers.size() - 1; i3 >= 0; i3--) {
            this.mUserBatteryConsumers.get(i3).writeToParcel(dest, flags);
        }
        if (this.mHistoryBuffer != null) {
            dest.writeBoolean(true);
            dest.writeBlob(this.mHistoryBuffer.marshall());
            dest.writeInt(this.mHistoryTagPool.size());
            for (int i4 = this.mHistoryTagPool.size() - 1; i4 >= 0; i4--) {
                BatteryStats.HistoryTag tag = this.mHistoryTagPool.get(i4);
                dest.writeString(tag.string);
                dest.writeInt(tag.uid);
                dest.writeInt(tag.poolIdx);
            }
            return;
        }
        dest.writeBoolean(false);
    }

    public byte[] getStatsProto() {
        int maxRawSize = 78750;
        for (int i = 0; i < 3; i++) {
            ProtoOutputStream proto = new ProtoOutputStream();
            writeStatsProto(proto, maxRawSize);
            int rawSize = proto.getRawSize();
            byte[] protoOutput = proto.getBytes();
            if (protoOutput.length <= STATSD_PULL_ATOM_MAX_BYTES) {
                return protoOutput;
            }
            maxRawSize = (int) (((rawSize * 45000) / protoOutput.length) - 1024);
        }
        ProtoOutputStream proto2 = new ProtoOutputStream();
        writeStatsProto(proto2, STATSD_PULL_ATOM_MAX_BYTES);
        return proto2.getBytes();
    }

    private void writeStatsProto(ProtoOutputStream proto, int maxRawSize) {
        BatteryConsumer deviceBatteryConsumer = getAggregateBatteryConsumer(0);
        proto.write(1112396529665L, getStatsStartTimestamp());
        proto.write(1112396529666L, getStatsEndTimestamp());
        proto.write(1112396529667L, getStatsDuration());
        proto.write(1120986464262L, getDischargePercentage());
        deviceBatteryConsumer.writeStatsProto(proto, 1146756268036L);
        writeUidBatteryConsumersProto(proto, maxRawSize);
    }

    private void writeUidBatteryConsumersProto(ProtoOutputStream proto, int maxRawSize) {
        List<UidBatteryConsumer> consumers = getUidBatteryConsumers();
        consumers.sort(Comparator.comparingDouble(new ToDoubleFunction() { // from class: android.os.BatteryUsageStats$$ExternalSyntheticLambda0
            @Override // java.util.function.ToDoubleFunction
            public final double applyAsDouble(Object obj) {
                double uidBatteryConsumerWeight;
                uidBatteryConsumerWeight = BatteryUsageStats.this.getUidBatteryConsumerWeight((UidBatteryConsumer) obj);
                return uidBatteryConsumerWeight;
            }
        }).reversed());
        int size = consumers.size();
        for (int i = 0; i < size; i++) {
            UidBatteryConsumer consumer = consumers.get(i);
            long fgMs = consumer.getTimeInStateMs(0);
            long bgMs = consumer.getTimeInStateMs(1);
            boolean hasBaseData = consumer.hasStatsProtoData();
            if (fgMs != 0 || bgMs != 0 || hasBaseData) {
                long token = proto.start(2246267895813L);
                proto.write(1120986464257L, consumer.getUid());
                if (hasBaseData) {
                    consumer.writeStatsProto(proto, 1146756268034L);
                }
                proto.write(1112396529667L, fgMs);
                proto.write(1112396529668L, bgMs);
                proto.end(token);
                if (proto.getRawSize() >= maxRawSize) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getUidBatteryConsumerWeight(UidBatteryConsumer uidBatteryConsumer) {
        double consumedPower = uidBatteryConsumer.getConsumedPower();
        long timeInForeground = uidBatteryConsumer.getTimeInStateMs(0);
        long timeInBackground = uidBatteryConsumer.getTimeInStateMs(1);
        return (WEIGHT_CONSUMED_POWER * consumedPower) + (timeInForeground * WEIGHT_FOREGROUND_STATE) + (timeInBackground * WEIGHT_BACKGROUND_STATE);
    }

    public void dump(PrintWriter pw, String prefix) {
        int componentId;
        int componentId2;
        Range<Double> dischargedPowerRange;
        BatteryConsumer appsConsumer;
        pw.print(prefix);
        pw.println("  Estimated power use (mAh):");
        pw.print(prefix);
        pw.print("    Capacity: ");
        PowerCalculator.printPowerMah(pw, getBatteryCapacity());
        pw.print(", Computed drain: ");
        PowerCalculator.printPowerMah(pw, getConsumedPower());
        Range<Double> dischargedPowerRange2 = getDischargedPowerRange();
        pw.print(", actual drain: ");
        PowerCalculator.printPowerMah(pw, dischargedPowerRange2.getLower().doubleValue());
        if (!dischargedPowerRange2.getLower().equals(dischargedPowerRange2.getUpper())) {
            pw.print(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            PowerCalculator.printPowerMah(pw, dischargedPowerRange2.getUpper().doubleValue());
        }
        pw.println();
        pw.println("    Global");
        BatteryConsumer deviceConsumer = getAggregateBatteryConsumer(0);
        BatteryConsumer appsConsumer2 = getAggregateBatteryConsumer(1);
        int componentId3 = 0;
        while (componentId3 < 18) {
            double devicePowerMah = deviceConsumer.getConsumedPower(componentId3);
            double appsPowerMah = appsConsumer2.getConsumedPower(componentId3);
            if (devicePowerMah == 0.0d && appsPowerMah == 0.0d) {
                componentId2 = componentId3;
                dischargedPowerRange = dischargedPowerRange2;
                appsConsumer = appsConsumer2;
            } else {
                String componentName = BatteryConsumer.powerComponentIdToString(componentId3);
                componentId2 = componentId3;
                dischargedPowerRange = dischargedPowerRange2;
                appsConsumer = appsConsumer2;
                printPowerComponent(pw, prefix, componentName, devicePowerMah, appsPowerMah, deviceConsumer.getPowerModel(componentId3), deviceConsumer.getUsageDurationMillis(componentId3));
            }
            componentId3 = componentId2 + 1;
            appsConsumer2 = appsConsumer;
            dischargedPowerRange2 = dischargedPowerRange;
        }
        int componentId4 = 1000;
        while (componentId4 < this.mCustomPowerComponentNames.length + 1000) {
            double devicePowerMah2 = deviceConsumer.getConsumedPowerForCustomComponent(componentId4);
            double appsPowerMah2 = appsConsumer2.getConsumedPowerForCustomComponent(componentId4);
            if (devicePowerMah2 == 0.0d && appsPowerMah2 == 0.0d) {
                componentId = componentId4;
            } else {
                componentId = componentId4;
                printPowerComponent(pw, prefix, deviceConsumer.getCustomPowerComponentName(componentId4), devicePowerMah2, appsPowerMah2, 0, deviceConsumer.getUsageDurationForCustomComponentMillis(componentId4));
            }
            componentId4 = componentId + 1;
        }
        dumpSortedBatteryConsumers(pw, prefix, getUidBatteryConsumers());
        dumpSortedBatteryConsumers(pw, prefix, getUserBatteryConsumers());
    }

    private void printPowerComponent(PrintWriter pw, String prefix, String componentName, double devicePowerMah, double appsPowerMah, int powerModel, long durationMs) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append("      ");
        sb.append(componentName);
        sb.append(": ");
        sb.append(PowerCalculator.formatCharge(devicePowerMah));
        if (!(powerModel == 0 || powerModel == 1)) {
            sb.append(" [");
            sb.append(BatteryConsumer.powerModelToString(powerModel));
            sb.append("]");
        }
        sb.append(" apps: ");
        sb.append(PowerCalculator.formatCharge(appsPowerMah));
        if (durationMs != 0) {
            sb.append(" duration: ");
            BatteryStats.formatTimeMs(sb, durationMs);
        }
        pw.println(sb.toString());
    }

    private void dumpSortedBatteryConsumers(PrintWriter pw, String prefix, List<? extends BatteryConsumer> batteryConsumers) {
        batteryConsumers.sort(Comparator.comparingDouble(BatteryUsageStats$$ExternalSyntheticLambda1.INSTANCE).reversed());
        for (BatteryConsumer consumer : batteryConsumers) {
            if (consumer.getConsumedPower() != 0.0d) {
                pw.print(prefix);
                pw.print("    ");
                consumer.dump(pw);
                pw.println();
            }
        }
    }

    public void writeXml(TypedXmlSerializer serializer) throws IOException {
        serializer.startTag(null, XML_TAG_BATTERY_USAGE_STATS);
        for (int i = 0; i < this.mCustomPowerComponentNames.length; i++) {
            serializer.attribute(null, XML_ATTR_PREFIX_CUSTOM_COMPONENT + i, this.mCustomPowerComponentNames[i]);
        }
        serializer.attributeLong(null, XML_ATTR_START_TIMESTAMP, this.mStatsStartTimestampMs);
        serializer.attributeLong(null, XML_ATTR_END_TIMESTAMP, this.mStatsEndTimestampMs);
        serializer.attributeLong(null, "duration", this.mStatsDurationMs);
        serializer.attributeDouble(null, XML_ATTR_BATTERY_CAPACITY, this.mBatteryCapacityMah);
        serializer.attributeInt(null, XML_ATTR_DISCHARGE_PERCENT, this.mDischargePercentage);
        serializer.attributeDouble(null, XML_ATTR_DISCHARGE_LOWER, this.mDischargedPowerLowerBound);
        serializer.attributeDouble(null, XML_ATTR_DISCHARGE_UPPER, this.mDischargedPowerUpperBound);
        serializer.attributeLong(null, XML_ATTR_BATTERY_REMAINING, this.mBatteryTimeRemainingMs);
        serializer.attributeLong(null, XML_ATTR_CHARGE_REMAINING, this.mChargeTimeRemainingMs);
        for (int scope = 0; scope < 2; scope++) {
            this.mAggregateBatteryConsumers[scope].writeToXml(serializer, scope);
        }
        for (UidBatteryConsumer consumer : this.mUidBatteryConsumers) {
            consumer.writeToXml(serializer);
        }
        for (UserBatteryConsumer consumer2 : this.mUserBatteryConsumers) {
            consumer2.writeToXml(serializer);
        }
        serializer.endTag(null, XML_TAG_BATTERY_USAGE_STATS);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static BatteryUsageStats createFromXml(TypedXmlPullParser parser) throws XmlPullParserException, IOException {
        boolean z;
        Builder builder = null;
        int eventType = parser.getEventType();
        while (true) {
            if (eventType == 1) {
                break;
            } else if (eventType != 2 || !parser.getName().equals(XML_TAG_BATTERY_USAGE_STATS)) {
                eventType = parser.next();
            } else {
                List<String> customComponentNames = new ArrayList<>();
                int i = 0;
                while (true) {
                    int index = parser.getAttributeIndex(null, XML_ATTR_PREFIX_CUSTOM_COMPONENT + i);
                    if (index == -1) {
                        break;
                    }
                    customComponentNames.add(parser.getAttributeValue(index));
                    i++;
                }
                builder = new Builder((String[]) customComponentNames.toArray(new String[0]), true);
                builder.setStatsStartTimestamp(parser.getAttributeLong(null, XML_ATTR_START_TIMESTAMP));
                builder.setStatsEndTimestamp(parser.getAttributeLong(null, XML_ATTR_END_TIMESTAMP));
                builder.setStatsDuration(parser.getAttributeLong(null, "duration"));
                builder.setBatteryCapacity(parser.getAttributeDouble(null, XML_ATTR_BATTERY_CAPACITY));
                builder.setDischargePercentage(parser.getAttributeInt(null, XML_ATTR_DISCHARGE_PERCENT));
                builder.setDischargedPowerRange(parser.getAttributeDouble(null, XML_ATTR_DISCHARGE_LOWER), parser.getAttributeDouble(null, XML_ATTR_DISCHARGE_UPPER));
                builder.setBatteryTimeRemainingMs(parser.getAttributeLong(null, XML_ATTR_BATTERY_REMAINING));
                builder.setChargeTimeRemainingMs(parser.getAttributeLong(null, XML_ATTR_CHARGE_REMAINING));
                eventType = parser.next();
            }
        }
        if (builder != null) {
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = parser.getName();
                    switch (name.hashCode()) {
                        case 115792:
                            if (name.equals("uid")) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 3599307:
                            if (name.equals("user")) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 175177151:
                            if (name.equals(XML_TAG_AGGREGATE)) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        default:
                            z = true;
                            break;
                    }
                    switch (z) {
                        case false:
                            AggregateBatteryConsumer.parseXml(parser, builder);
                            continue;
                        case true:
                            UidBatteryConsumer.createFromXml(parser, builder);
                            continue;
                        case true:
                            UserBatteryConsumer.createFromXml(parser, builder);
                            continue;
                    }
                }
                eventType = parser.next();
            }
            return builder.build();
        }
        throw new XmlPullParserException("No root element");
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private final AggregateBatteryConsumer.Builder[] mAggregateBatteryConsumersBuilders;
        private double mBatteryCapacityMah;
        private long mBatteryTimeRemainingMs;
        private long mChargeTimeRemainingMs;
        private final String[] mCustomPowerComponentNames;
        private int mDischargePercentage;
        private double mDischargedPowerLowerBoundMah;
        private double mDischargedPowerUpperBoundMah;
        private Parcel mHistoryBuffer;
        private List<BatteryStats.HistoryTag> mHistoryTagPool;
        private final boolean mIncludePowerModels;
        private long mStatsDurationMs;
        private long mStatsEndTimestampMs;
        private long mStatsStartTimestampMs;
        private final SparseArray<UidBatteryConsumer.Builder> mUidBatteryConsumerBuilders;
        private final SparseArray<UserBatteryConsumer.Builder> mUserBatteryConsumerBuilders;

        public Builder(String[] customPowerComponentNames) {
            this(customPowerComponentNames, false);
        }

        public Builder(String[] customPowerComponentNames, boolean includePowerModels) {
            this.mStatsDurationMs = -1L;
            this.mBatteryTimeRemainingMs = -1L;
            this.mChargeTimeRemainingMs = -1L;
            this.mAggregateBatteryConsumersBuilders = new AggregateBatteryConsumer.Builder[2];
            this.mUidBatteryConsumerBuilders = new SparseArray<>();
            this.mUserBatteryConsumerBuilders = new SparseArray<>();
            this.mCustomPowerComponentNames = customPowerComponentNames;
            this.mIncludePowerModels = includePowerModels;
            for (int i = 0; i < 2; i++) {
                this.mAggregateBatteryConsumersBuilders[i] = new AggregateBatteryConsumer.Builder(customPowerComponentNames, includePowerModels);
            }
        }

        public BatteryUsageStats build() {
            return new BatteryUsageStats(this);
        }

        public Builder setBatteryCapacity(double batteryCapacityMah) {
            this.mBatteryCapacityMah = batteryCapacityMah;
            return this;
        }

        public Builder setStatsStartTimestamp(long statsStartTimestampMs) {
            this.mStatsStartTimestampMs = statsStartTimestampMs;
            return this;
        }

        public Builder setStatsEndTimestamp(long statsEndTimestampMs) {
            this.mStatsEndTimestampMs = statsEndTimestampMs;
            return this;
        }

        public Builder setStatsDuration(long statsDurationMs) {
            this.mStatsDurationMs = statsDurationMs;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getStatsDuration() {
            long j = this.mStatsDurationMs;
            if (j != -1) {
                return j;
            }
            return this.mStatsEndTimestampMs - this.mStatsStartTimestampMs;
        }

        public Builder setDischargePercentage(int dischargePercentage) {
            this.mDischargePercentage = dischargePercentage;
            return this;
        }

        public Builder setDischargedPowerRange(double dischargedPowerLowerBoundMah, double dischargedPowerUpperBoundMah) {
            this.mDischargedPowerLowerBoundMah = dischargedPowerLowerBoundMah;
            this.mDischargedPowerUpperBoundMah = dischargedPowerUpperBoundMah;
            return this;
        }

        public Builder setBatteryTimeRemainingMs(long batteryTimeRemainingMs) {
            this.mBatteryTimeRemainingMs = batteryTimeRemainingMs;
            return this;
        }

        public Builder setChargeTimeRemainingMs(long chargeTimeRemainingMs) {
            this.mChargeTimeRemainingMs = chargeTimeRemainingMs;
            return this;
        }

        public Builder setBatteryHistory(Parcel historyBuffer, List<BatteryStats.HistoryTag> historyTagPool) {
            this.mHistoryBuffer = historyBuffer;
            this.mHistoryTagPool = historyTagPool;
            return this;
        }

        public AggregateBatteryConsumer.Builder getAggregateBatteryConsumerBuilder(int scope) {
            return this.mAggregateBatteryConsumersBuilders[scope];
        }

        public UidBatteryConsumer.Builder getOrCreateUidBatteryConsumerBuilder(BatteryStats.Uid batteryStatsUid) {
            int uid = batteryStatsUid.getUid();
            UidBatteryConsumer.Builder builder = this.mUidBatteryConsumerBuilders.get(uid);
            if (builder != null) {
                return builder;
            }
            UidBatteryConsumer.Builder builder2 = new UidBatteryConsumer.Builder(this.mCustomPowerComponentNames, this.mIncludePowerModels, batteryStatsUid);
            this.mUidBatteryConsumerBuilders.put(uid, builder2);
            return builder2;
        }

        public UidBatteryConsumer.Builder getOrCreateUidBatteryConsumerBuilder(int uid) {
            UidBatteryConsumer.Builder builder = this.mUidBatteryConsumerBuilders.get(uid);
            if (builder != null) {
                return builder;
            }
            UidBatteryConsumer.Builder builder2 = new UidBatteryConsumer.Builder(this.mCustomPowerComponentNames, this.mIncludePowerModels, uid);
            this.mUidBatteryConsumerBuilders.put(uid, builder2);
            return builder2;
        }

        public UserBatteryConsumer.Builder getOrCreateUserBatteryConsumerBuilder(int userId) {
            UserBatteryConsumer.Builder builder = this.mUserBatteryConsumerBuilders.get(userId);
            if (builder != null) {
                return builder;
            }
            UserBatteryConsumer.Builder builder2 = new UserBatteryConsumer.Builder(this.mCustomPowerComponentNames, this.mIncludePowerModels, userId);
            this.mUserBatteryConsumerBuilders.put(userId, builder2);
            return builder2;
        }

        public SparseArray<UidBatteryConsumer.Builder> getUidBatteryConsumerBuilders() {
            return this.mUidBatteryConsumerBuilders;
        }

        public Builder add(BatteryUsageStats stats) {
            if (!Arrays.equals(this.mCustomPowerComponentNames, stats.mCustomPowerComponentNames)) {
                throw new IllegalArgumentException("BatteryUsageStats have different custom power components");
            } else if (this.mUserBatteryConsumerBuilders.size() != 0 || !stats.getUserBatteryConsumers().isEmpty()) {
                throw new UnsupportedOperationException("Combining UserBatteryConsumers is not supported");
            } else {
                this.mDischargedPowerLowerBoundMah += stats.mDischargedPowerLowerBound;
                this.mDischargedPowerUpperBoundMah += stats.mDischargedPowerUpperBound;
                this.mDischargePercentage += stats.mDischargePercentage;
                this.mStatsDurationMs = getStatsDuration() + stats.getStatsDuration();
                if (this.mStatsStartTimestampMs == 0 || stats.mStatsStartTimestampMs < this.mStatsStartTimestampMs) {
                    this.mStatsStartTimestampMs = stats.mStatsStartTimestampMs;
                }
                boolean addingLaterSnapshot = stats.mStatsEndTimestampMs > this.mStatsEndTimestampMs;
                if (addingLaterSnapshot) {
                    this.mStatsEndTimestampMs = stats.mStatsEndTimestampMs;
                }
                for (int scope = 0; scope < 2; scope++) {
                    getAggregateBatteryConsumerBuilder(scope).add(stats.mAggregateBatteryConsumers[scope]);
                }
                for (UidBatteryConsumer consumer : stats.getUidBatteryConsumers()) {
                    getOrCreateUidBatteryConsumerBuilder(consumer.getUid()).add(consumer);
                }
                if (addingLaterSnapshot) {
                    this.mBatteryCapacityMah = stats.mBatteryCapacityMah;
                    this.mBatteryTimeRemainingMs = stats.mBatteryTimeRemainingMs;
                    this.mChargeTimeRemainingMs = stats.mChargeTimeRemainingMs;
                }
                return this;
            }
        }
    }
}
