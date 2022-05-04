package android.os;

import android.bluetooth.BluetoothUuid;
import android.net.MacAddress;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.HexDump;
import java.util.ArrayList;
import java.util.function.Predicate;
/* loaded from: classes2.dex */
public class BytesMatcher implements Predicate<byte[]> {
    private static final String TAG = "BytesMatcher";
    private static final char TYPE_EXACT_ACCEPT = '+';
    private static final char TYPE_EXACT_REJECT = '-';
    private static final char TYPE_PREFIX_ACCEPT = 8838;
    private static final char TYPE_PREFIX_REJECT = 8840;
    private final ArrayList<Rule> mRules = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Rule {
        public final byte[] mask;
        public final char type;
        public final byte[] value;

        public Rule(char type, byte[] value, byte[] mask) {
            if (mask == null || value.length == mask.length) {
                this.type = type;
                this.value = value;
                this.mask = mask;
                return;
            }
            throw new IllegalArgumentException("Expected length " + value.length + " but found " + mask.length);
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            encode(builder);
            return builder.toString();
        }

        public void encode(StringBuilder builder) {
            builder.append(this.type);
            builder.append(HexDump.toHexString(this.value));
            if (this.mask != null) {
                builder.append('/');
                builder.append(HexDump.toHexString(this.mask));
            }
        }

        public boolean test(byte[] value) {
            switch (this.type) {
                case '+':
                case '-':
                    if (value.length != this.value.length) {
                        return false;
                    }
                    break;
                case 8838:
                case 8840:
                    if (value.length < this.value.length) {
                        return false;
                    }
                    break;
            }
            int i = 0;
            while (true) {
                byte[] bArr = this.value;
                if (i >= bArr.length) {
                    return true;
                }
                byte local = bArr[i];
                byte remote = value[i];
                byte[] bArr2 = this.mask;
                if (bArr2 != null) {
                    local = (byte) (bArr2[i] & local);
                    remote = (byte) (bArr2[i] & remote);
                }
                if (local != remote) {
                    return false;
                }
                i++;
            }
        }
    }

    public void addExactAcceptRule(byte[] value, byte[] mask) {
        this.mRules.add(new Rule(TYPE_EXACT_ACCEPT, value, mask));
    }

    public void addExactRejectRule(byte[] value, byte[] mask) {
        this.mRules.add(new Rule(TYPE_EXACT_REJECT, value, mask));
    }

    public void addPrefixAcceptRule(byte[] value, byte[] mask) {
        this.mRules.add(new Rule(TYPE_PREFIX_ACCEPT, value, mask));
    }

    public void addPrefixRejectRule(byte[] value, byte[] mask) {
        this.mRules.add(new Rule(TYPE_PREFIX_REJECT, value, mask));
    }

    public boolean testBluetoothUuid(ParcelUuid value) {
        return test(BluetoothUuid.uuidToBytes(value));
    }

    public boolean testMacAddress(MacAddress value) {
        return test(value.toByteArray());
    }

    public boolean test(byte[] value) {
        return test(value, false);
    }

    public boolean test(byte[] value, boolean defaultValue) {
        int size = this.mRules.size();
        for (int i = 0; i < size; i++) {
            Rule rule = this.mRules.get(i);
            if (rule.test(value)) {
                switch (rule.type) {
                    case '+':
                    case 8838:
                        return true;
                    case '-':
                    case 8840:
                        return false;
                }
            }
        }
        return defaultValue;
    }

    public static String encode(BytesMatcher matcher) {
        StringBuilder builder = new StringBuilder();
        int size = matcher.mRules.size();
        for (int i = 0; i < size; i++) {
            Rule rule = matcher.mRules.get(i);
            rule.encode(builder);
            builder.append(',');
        }
        int i2 = builder.length();
        if (i2 > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static BytesMatcher decode(String value) {
        byte[] ruleMask;
        byte[] ruleValue;
        BytesMatcher matcher = new BytesMatcher();
        if (TextUtils.isEmpty(value)) {
            return matcher;
        }
        int length = value.length();
        int i = 0;
        while (i < length) {
            char type = value.charAt(i);
            int nextRule = value.indexOf(44, i);
            int nextMask = value.indexOf(47, i);
            if (nextRule == -1) {
                nextRule = length;
            }
            if (nextMask > nextRule) {
                nextMask = -1;
            }
            if (nextMask >= 0) {
                ruleValue = HexDump.hexStringToByteArray(value.substring(i + 1, nextMask));
                ruleMask = HexDump.hexStringToByteArray(value.substring(nextMask + 1, nextRule));
            } else {
                ruleValue = HexDump.hexStringToByteArray(value.substring(i + 1, nextRule));
                ruleMask = null;
            }
            switch (type) {
                case '+':
                    matcher.addExactAcceptRule(ruleValue, ruleMask);
                    break;
                case '-':
                    matcher.addExactRejectRule(ruleValue, ruleMask);
                    break;
                case 8838:
                    matcher.addPrefixAcceptRule(ruleValue, ruleMask);
                    break;
                case 8840:
                    matcher.addPrefixRejectRule(ruleValue, ruleMask);
                    break;
                default:
                    Log.w(TAG, "Ignoring unknown type " + type);
                    break;
            }
            i = nextRule + 1;
        }
        return matcher;
    }
}
