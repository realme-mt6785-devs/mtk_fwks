package android.os;

import android.content.Context;
import android.nfc.cardemulation.CardEmulation;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import android.util.proto.ProtoOutputStream;
import com.android.internal.os.PowerCalculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
class PowerComponents {
    private static final int CUSTOM_POWER_COMPONENT_OFFSET = -982;
    private final double mConsumedPowerMah;
    private final int mCustomPowerComponentCount;
    private String[] mCustomPowerComponentNames;
    private final double[] mPowerComponentsMah;
    private final byte[] mPowerModels;
    private final long[] mUsageDurationsMs;

    PowerComponents(Builder builder) {
        String[] strArr = builder.mCustomPowerComponentNames;
        this.mCustomPowerComponentNames = strArr;
        this.mCustomPowerComponentCount = strArr.length;
        this.mPowerComponentsMah = builder.mPowerComponentsMah;
        this.mUsageDurationsMs = builder.mUsageDurationsMs;
        this.mConsumedPowerMah = builder.getTotalPower();
        this.mPowerModels = builder.getPowerModels();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PowerComponents(Parcel source) {
        this.mConsumedPowerMah = source.readDouble();
        this.mCustomPowerComponentCount = source.readInt();
        this.mPowerComponentsMah = source.createDoubleArray();
        this.mUsageDurationsMs = source.createLongArray();
        if (source.readBoolean()) {
            byte[] bArr = new byte[18];
            this.mPowerModels = bArr;
            source.readByteArray(bArr);
            return;
        }
        this.mPowerModels = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mConsumedPowerMah);
        dest.writeInt(this.mCustomPowerComponentCount);
        dest.writeDoubleArray(this.mPowerComponentsMah);
        dest.writeLongArray(this.mUsageDurationsMs);
        if (this.mPowerModels != null) {
            dest.writeBoolean(true);
            dest.writeByteArray(this.mPowerModels);
            return;
        }
        dest.writeBoolean(false);
    }

    public double getConsumedPower() {
        return this.mConsumedPowerMah;
    }

    public double getConsumedPower(int componentId) {
        if (componentId < 18) {
            try {
                return this.mPowerComponentsMah[componentId];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
            }
        } else {
            throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
        }
    }

    public double getConsumedPowerForCustomComponent(int componentId) {
        if (componentId < 1000 || componentId >= 9999) {
            throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
        }
        try {
            return this.mPowerComponentsMah[componentId + CUSTOM_POWER_COMPONENT_OFFSET];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCustomPowerComponentNames(String[] customPowerComponentNames) {
        this.mCustomPowerComponentNames = customPowerComponentNames;
    }

    public String getCustomPowerComponentName(int componentId) {
        if (componentId < 1000 || componentId >= 9999) {
            throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
        }
        try {
            return this.mCustomPowerComponentNames[componentId - 1000];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
        }
    }

    public boolean hasPowerModels() {
        return this.mPowerModels != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPowerModel(int component) {
        if (hasPowerModels()) {
            return this.mPowerModels[component];
        }
        throw new IllegalStateException("Power model IDs were not requested in the BatteryUsageStatsQuery");
    }

    public long getUsageDurationMillis(int componentId) {
        if (componentId < 18) {
            try {
                return this.mUsageDurationsMs[componentId];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
            }
        } else {
            throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
        }
    }

    public long getUsageDurationForCustomComponentMillis(int componentId) {
        if (componentId >= 1000) {
            try {
                return this.mUsageDurationsMs[componentId + CUSTOM_POWER_COMPONENT_OFFSET];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
            }
        } else {
            throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
        }
    }

    public int getCustomPowerComponentCount() {
        return this.mCustomPowerComponentCount;
    }

    public long getMaxComponentUsageDurationMillis() {
        long max = 0;
        for (int i = this.mUsageDurationsMs.length - 1; i >= 0; i--) {
            long[] jArr = this.mUsageDurationsMs;
            if (jArr[i] > max) {
                max = jArr[i];
            }
        }
        return max;
    }

    public void dump(PrintWriter pw, boolean skipEmptyComponents) {
        String separator = "";
        for (int componentId = 0; componentId < 18; componentId++) {
            double componentPower = getConsumedPower(componentId);
            if (!skipEmptyComponents || componentPower != 0.0d) {
                pw.print(separator);
                separator = " ";
                pw.print(BatteryConsumer.powerComponentIdToString(componentId));
                pw.print("=");
                PowerCalculator.printPowerMah(pw, componentPower);
            }
        }
        int customComponentCount = getCustomPowerComponentCount();
        for (int customComponentId = 1000; customComponentId < customComponentCount + 1000; customComponentId++) {
            double customComponentPower = getConsumedPowerForCustomComponent(customComponentId);
            if (!skipEmptyComponents || customComponentPower != 0.0d) {
                pw.print(separator);
                separator = " ";
                pw.print(getCustomPowerComponentName(customComponentId));
                pw.print("=");
                PowerCalculator.printPowerMah(pw, customComponentPower);
            }
        }
    }

    boolean hasStatsProtoData() {
        return writeStatsProtoImpl(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeStatsProto(ProtoOutputStream proto) {
        writeStatsProtoImpl(proto);
    }

    private boolean writeStatsProtoImpl(ProtoOutputStream proto) {
        boolean interestingData = false;
        int idx = 0;
        while (true) {
            double[] dArr = this.mPowerComponentsMah;
            if (idx >= dArr.length) {
                return interestingData;
            }
            int componentId = idx < 18 ? idx : idx + 982;
            long powerDeciCoulombs = BatteryConsumer.convertMahToDeciCoulombs(dArr[idx]);
            long durationMs = this.mUsageDurationsMs[idx];
            if (powerDeciCoulombs != 0 || durationMs != 0) {
                interestingData = true;
                if (proto == null) {
                    return true;
                }
                long token = proto.start(2246267895810L);
                proto.write(1120986464257L, componentId);
                proto.write(1112396529666L, powerDeciCoulombs);
                proto.write(1112396529667L, durationMs);
                proto.end(token);
            }
            idx++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToXml(TypedXmlSerializer serializer) throws IOException {
        String str = "power_components";
        serializer.startTag(null, str);
        int componentId = 0;
        while (componentId < 18) {
            double powerMah = getConsumedPower(componentId);
            long durationMs = getUsageDurationMillis(componentId);
            if (powerMah != 0.0d || durationMs != 0) {
                serializer.startTag(null, CardEmulation.EXTRA_SERVICE_COMPONENT);
                serializer.attributeInt(null, "id", componentId);
                if (powerMah != 0.0d) {
                    serializer.attributeDouble(null, Context.POWER_SERVICE, powerMah);
                }
                if (durationMs != 0) {
                    serializer.attributeLong(null, "duration", durationMs);
                }
                byte[] bArr = this.mPowerModels;
                if (bArr != null) {
                    serializer.attributeInt(null, "model", bArr[componentId]);
                }
                serializer.endTag(null, CardEmulation.EXTRA_SERVICE_COMPONENT);
            }
            componentId++;
            str = str;
        }
        int customComponentEnd = this.mCustomPowerComponentCount + 1000;
        for (int componentId2 = 1000; componentId2 < customComponentEnd; componentId2++) {
            double powerMah2 = getConsumedPowerForCustomComponent(componentId2);
            long durationMs2 = getUsageDurationForCustomComponentMillis(componentId2);
            if (powerMah2 != 0.0d || durationMs2 != 0) {
                serializer.startTag(null, "custom_component");
                serializer.attributeInt(null, "id", componentId2);
                if (powerMah2 != 0.0d) {
                    serializer.attributeDouble(null, Context.POWER_SERVICE, powerMah2);
                }
                if (durationMs2 != 0) {
                    serializer.attributeLong(null, "duration", durationMs2);
                }
                serializer.endTag(null, "custom_component");
            }
        }
        serializer.endTag(null, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void parseXml(TypedXmlPullParser parser, Builder builder) throws XmlPullParserException, IOException {
        char c;
        char c2;
        char c3;
        int eventType = parser.getEventType();
        int i = 2;
        if (eventType != 2 || !parser.getName().equals("power_components")) {
            throw new XmlPullParserException("Invalid XML parser state");
        }
        while (true) {
            if ((eventType != 3 || !parser.getName().equals("power_components")) && eventType != 1) {
                if (eventType == i) {
                    String name = parser.getName();
                    switch (name.hashCode()) {
                        case -1399907075:
                            if (name.equals(CardEmulation.EXTRA_SERVICE_COMPONENT)) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -437794385:
                            if (name.equals("custom_component")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            int componentId = -1;
                            double powerMah = 0.0d;
                            long durationMs = 0;
                            int model = 0;
                            for (int i2 = 0; i2 < parser.getAttributeCount(); i2++) {
                                String attributeName = parser.getAttributeName(i2);
                                switch (attributeName.hashCode()) {
                                    case -1992012396:
                                        if (attributeName.equals("duration")) {
                                            c2 = 2;
                                            break;
                                        }
                                        c2 = 65535;
                                        break;
                                    case 3355:
                                        if (attributeName.equals("id")) {
                                            c2 = 0;
                                            break;
                                        }
                                        c2 = 65535;
                                        break;
                                    case 104069929:
                                        if (attributeName.equals("model")) {
                                            c2 = 3;
                                            break;
                                        }
                                        c2 = 65535;
                                        break;
                                    case 106858757:
                                        if (attributeName.equals(Context.POWER_SERVICE)) {
                                            c2 = 1;
                                            break;
                                        }
                                        c2 = 65535;
                                        break;
                                    default:
                                        c2 = 65535;
                                        break;
                                }
                                switch (c2) {
                                    case 0:
                                        componentId = parser.getAttributeInt(i2);
                                        break;
                                    case 1:
                                        powerMah = parser.getAttributeDouble(i2);
                                        break;
                                    case 2:
                                        durationMs = parser.getAttributeLong(i2);
                                        break;
                                    case 3:
                                        model = parser.getAttributeInt(i2);
                                        break;
                                }
                            }
                            builder.setConsumedPower(componentId, powerMah, model);
                            builder.setUsageDurationMillis(componentId, durationMs);
                            continue;
                        case 1:
                            int componentId2 = -1;
                            double powerMah2 = 0.0d;
                            long durationMs2 = 0;
                            for (int i3 = 0; i3 < parser.getAttributeCount(); i3++) {
                                String attributeName2 = parser.getAttributeName(i3);
                                switch (attributeName2.hashCode()) {
                                    case -1992012396:
                                        if (attributeName2.equals("duration")) {
                                            c3 = 2;
                                            break;
                                        }
                                        c3 = 65535;
                                        break;
                                    case 3355:
                                        if (attributeName2.equals("id")) {
                                            c3 = 0;
                                            break;
                                        }
                                        c3 = 65535;
                                        break;
                                    case 106858757:
                                        if (attributeName2.equals(Context.POWER_SERVICE)) {
                                            c3 = 1;
                                            break;
                                        }
                                        c3 = 65535;
                                        break;
                                    default:
                                        c3 = 65535;
                                        break;
                                }
                                switch (c3) {
                                    case 0:
                                        int componentId3 = parser.getAttributeInt(i3);
                                        componentId2 = componentId3;
                                        break;
                                    case 1:
                                        powerMah2 = parser.getAttributeDouble(i3);
                                        break;
                                    case 2:
                                        durationMs2 = parser.getAttributeLong(i3);
                                        break;
                                }
                            }
                            builder.setConsumedPowerForCustomComponent(componentId2, powerMah2);
                            builder.setUsageDurationForCustomComponentMillis(componentId2, durationMs2);
                            continue;
                    }
                }
                eventType = parser.next();
                i = 2;
            } else {
                return;
            }
        }
    }

    /* loaded from: classes2.dex */
    static final class Builder {
        private static final byte POWER_MODEL_UNINITIALIZED = -1;
        private final String[] mCustomPowerComponentNames;
        private final double[] mPowerComponentsMah;
        private final byte[] mPowerModels;
        private final long[] mUsageDurationsMs;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(String[] customPowerComponentNames, boolean includePowerModels) {
            this.mCustomPowerComponentNames = customPowerComponentNames;
            int powerComponentCount = customPowerComponentNames.length + 18;
            this.mPowerComponentsMah = new double[powerComponentCount];
            this.mUsageDurationsMs = new long[powerComponentCount];
            if (includePowerModels) {
                byte[] bArr = new byte[18];
                this.mPowerModels = bArr;
                Arrays.fill(bArr, (byte) -1);
                return;
            }
            this.mPowerModels = null;
        }

        public Builder setConsumedPower(int componentId, double componentPower, int powerModel) {
            if (componentId < 18) {
                try {
                    this.mPowerComponentsMah[componentId] = componentPower;
                    byte[] bArr = this.mPowerModels;
                    if (bArr != null) {
                        bArr[componentId] = (byte) powerModel;
                    }
                    return this;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
                }
            } else {
                throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
            }
        }

        public Builder setConsumedPowerForCustomComponent(int componentId, double componentPower) {
            if (componentId < 1000 || componentId >= 9999) {
                throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
            }
            try {
                this.mPowerComponentsMah[componentId + PowerComponents.CUSTOM_POWER_COMPONENT_OFFSET] = componentPower;
                return this;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
            }
        }

        public Builder setUsageDurationMillis(int componentId, long componentUsageDurationMillis) {
            if (componentId < 18) {
                try {
                    this.mUsageDurationsMs[componentId] = componentUsageDurationMillis;
                    return this;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
                }
            } else {
                throw new IllegalArgumentException("Unsupported power component ID: " + componentId);
            }
        }

        public Builder setUsageDurationForCustomComponentMillis(int componentId, long componentUsageDurationMillis) {
            if (componentId >= 1000) {
                try {
                    this.mUsageDurationsMs[componentId + PowerComponents.CUSTOM_POWER_COMPONENT_OFFSET] = componentUsageDurationMillis;
                    return this;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
                }
            } else {
                throw new IllegalArgumentException("Unsupported custom power component ID: " + componentId);
            }
        }

        public void addPowerAndDuration(Builder other) {
            addPowerAndDuration(other.mPowerComponentsMah, other.mUsageDurationsMs, other.mPowerModels);
        }

        public void addPowerAndDuration(PowerComponents other) {
            addPowerAndDuration(other.mPowerComponentsMah, other.mUsageDurationsMs, other.mPowerModels);
        }

        private void addPowerAndDuration(double[] powerComponentsMah, long[] usageDurationsMs, byte[] powerModels) {
            double[] dArr = this.mPowerComponentsMah;
            if (dArr.length == powerComponentsMah.length) {
                for (int i = dArr.length - 1; i >= 0; i--) {
                    double[] dArr2 = this.mPowerComponentsMah;
                    dArr2[i] = dArr2[i] + powerComponentsMah[i];
                }
                for (int i2 = this.mUsageDurationsMs.length - 1; i2 >= 0; i2--) {
                    long[] jArr = this.mUsageDurationsMs;
                    jArr[i2] = jArr[i2] + usageDurationsMs[i2];
                }
                byte[] bArr = this.mPowerModels;
                if (!(bArr == null || powerModels == null)) {
                    for (int i3 = bArr.length - 1; i3 >= 0; i3--) {
                        byte[] bArr2 = this.mPowerModels;
                        if (bArr2[i3] == -1) {
                            bArr2[i3] = powerModels[i3];
                        } else if (!(bArr2[i3] == powerModels[i3] || powerModels[i3] == -1)) {
                            bArr2[i3] = 0;
                        }
                    }
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Number of power components does not match: " + powerComponentsMah.length + ", expected: " + this.mPowerComponentsMah.length);
        }

        public double getTotalPower() {
            double totalPowerMah = 0.0d;
            for (int i = this.mPowerComponentsMah.length - 1; i >= 0; i--) {
                totalPowerMah += this.mPowerComponentsMah[i];
            }
            return totalPowerMah;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getPowerModels() {
            byte[] bArr = this.mPowerModels;
            if (bArr == null) {
                return null;
            }
            byte[] powerModels = new byte[bArr.length];
            for (int i = bArr.length - 1; i >= 0; i--) {
                byte[] bArr2 = this.mPowerModels;
                powerModels[i] = bArr2[i] != -1 ? bArr2[i] : (byte) 0;
            }
            return powerModels;
        }

        public PowerComponents build() {
            return new PowerComponents(this);
        }
    }
}
