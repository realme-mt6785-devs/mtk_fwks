package android.net;

import android.content.res.Resources;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.R;
import com.android.internal.util.HexDump;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public final class IpSecAlgorithm implements Parcelable {
    public static final Map<String, Integer> ALGO_TO_REQUIRED_FIRST_SDK;
    public static final String AUTH_AES_CMAC = "cmac(aes)";
    public static final String AUTH_AES_XCBC = "xcbc(aes)";
    public static final String AUTH_CRYPT_AES_GCM = "rfc4106(gcm(aes))";
    public static final String AUTH_CRYPT_CHACHA20_POLY1305 = "rfc7539esp(chacha20,poly1305)";
    public static final String AUTH_HMAC_MD5 = "hmac(md5)";
    public static final String AUTH_HMAC_SHA1 = "hmac(sha1)";
    public static final String AUTH_HMAC_SHA256 = "hmac(sha256)";
    public static final String AUTH_HMAC_SHA384 = "hmac(sha384)";
    public static final String AUTH_HMAC_SHA512 = "hmac(sha512)";
    public static final String CRYPT_AES_CBC = "cbc(aes)";
    public static final String CRYPT_AES_CTR = "rfc3686(ctr(aes))";
    public static final String CRYPT_NULL = "ecb(cipher_null)";
    private static final int SDK_VERSION_ZERO = 0;
    private static final String TAG = "IpSecAlgorithm";
    private final byte[] mKey;
    private final String mName;
    private final int mTruncLenBits;
    private static final Set<String> ENABLED_ALGOS = Collections.unmodifiableSet(loadAlgos(Resources.getSystem()));
    public static final Parcelable.Creator<IpSecAlgorithm> CREATOR = new Parcelable.Creator<IpSecAlgorithm>() { // from class: android.net.IpSecAlgorithm.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecAlgorithm createFromParcel(Parcel in) {
            String name = in.readString();
            byte[] key = in.createByteArray();
            int truncLenBits = in.readInt();
            return new IpSecAlgorithm(name, key, truncLenBits);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecAlgorithm[] newArray(int size) {
            return new IpSecAlgorithm[size];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AlgorithmName {
    }

    static {
        HashMap hashMap = new HashMap();
        ALGO_TO_REQUIRED_FIRST_SDK = hashMap;
        hashMap.put(CRYPT_AES_CBC, 0);
        hashMap.put(AUTH_HMAC_MD5, 0);
        hashMap.put(AUTH_HMAC_SHA1, 0);
        hashMap.put(AUTH_HMAC_SHA256, 0);
        hashMap.put(AUTH_HMAC_SHA384, 0);
        hashMap.put(AUTH_HMAC_SHA512, 0);
        hashMap.put(AUTH_CRYPT_AES_GCM, 0);
        hashMap.put(CRYPT_AES_CTR, 31);
        hashMap.put(AUTH_AES_XCBC, 31);
        hashMap.put(AUTH_AES_CMAC, 31);
        hashMap.put(AUTH_CRYPT_CHACHA20_POLY1305, 31);
    }

    public IpSecAlgorithm(String algorithm, byte[] key) {
        this(algorithm, key, 0);
    }

    public IpSecAlgorithm(String algorithm, byte[] key, int truncLenBits) {
        this.mName = algorithm;
        byte[] bArr = (byte[]) key.clone();
        this.mKey = bArr;
        this.mTruncLenBits = truncLenBits;
        checkValidOrThrow(algorithm, bArr.length * 8, truncLenBits);
    }

    public String getName() {
        return this.mName;
    }

    public byte[] getKey() {
        return (byte[]) this.mKey.clone();
    }

    public int getTruncationLengthBits() {
        return this.mTruncLenBits;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mName);
        out.writeByteArray(this.mKey);
        out.writeInt(this.mTruncLenBits);
    }

    public static Set<String> getSupportedAlgorithms() {
        return ENABLED_ALGOS;
    }

    public static Set<String> loadAlgos(Resources systemResources) {
        Set<String> enabledAlgos = new HashSet<>();
        String[] resourceAlgos = systemResources.getStringArray(R.array.config_optionalIpSecAlgorithms);
        for (String str : resourceAlgos) {
            if (!ALGO_TO_REQUIRED_FIRST_SDK.containsKey(str) || !enabledAlgos.add(str)) {
                throw new IllegalArgumentException("Invalid or repeated algorithm " + str);
            }
        }
        for (Map.Entry<String, Integer> entry : ALGO_TO_REQUIRED_FIRST_SDK.entrySet()) {
            if (Build.VERSION.DEVICE_INITIAL_SDK_INT >= entry.getValue().intValue()) {
                enabledAlgos.add(entry.getKey());
            }
        }
        return enabledAlgos;
    }

    private static void checkValidOrThrow(String name, int keyLen, int truncLen) {
        if (getSupportedAlgorithms().contains(name)) {
            char c = 65535;
            boolean isValidLen = true;
            switch (name.hashCode()) {
                case -1751374730:
                    if (name.equals(AUTH_AES_XCBC)) {
                        c = 7;
                        break;
                    }
                    break;
                case -1137603038:
                    if (name.equals(AUTH_CRYPT_AES_GCM)) {
                        c = '\t';
                        break;
                    }
                    break;
                case -878787135:
                    if (name.equals(AUTH_CRYPT_CHACHA20_POLY1305)) {
                        c = '\n';
                        break;
                    }
                    break;
                case 394796030:
                    if (name.equals(CRYPT_AES_CBC)) {
                        c = 0;
                        break;
                    }
                    break;
                case 559425185:
                    if (name.equals(AUTH_HMAC_SHA256)) {
                        c = 4;
                        break;
                    }
                    break;
                case 559457797:
                    if (name.equals(AUTH_HMAC_SHA384)) {
                        c = 5;
                        break;
                    }
                    break;
                case 559510590:
                    if (name.equals(AUTH_HMAC_SHA512)) {
                        c = 6;
                        break;
                    }
                    break;
                case 759177996:
                    if (name.equals(AUTH_HMAC_MD5)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1206161110:
                    if (name.equals(AUTH_AES_CMAC)) {
                        c = '\b';
                        break;
                    }
                    break;
                case 1574008592:
                    if (name.equals(CRYPT_AES_CTR)) {
                        c = 1;
                        break;
                    }
                    break;
                case 2065384259:
                    if (name.equals(AUTH_HMAC_SHA1)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    if (!(keyLen == 128 || keyLen == 192 || keyLen == 256)) {
                        isValidLen = false;
                    }
                    isValidLen = true;
                    break;
                case 1:
                    if (!(keyLen == 160 || keyLen == 224 || keyLen == 288)) {
                        isValidLen = false;
                    }
                    isValidLen = true;
                    break;
                case 2:
                    isValidLen = keyLen == 128;
                    if (truncLen < 96 || truncLen > 128) {
                        isValidLen = false;
                    }
                    break;
                case 3:
                    isValidLen = keyLen == 160;
                    if (truncLen < 96 || truncLen > 160) {
                        isValidLen = false;
                    }
                    break;
                case 4:
                    isValidLen = keyLen == 256;
                    if (truncLen < 96 || truncLen > 256) {
                        isValidLen = false;
                    }
                    break;
                case 5:
                    boolean isValidLen2 = keyLen == 384;
                    if (truncLen < 192 || truncLen > 384) {
                        isValidLen = false;
                    }
                    isValidLen = isValidLen;
                    isValidLen = isValidLen2;
                    break;
                case 6:
                    boolean isValidLen3 = keyLen == 512;
                    if (truncLen < 256 || truncLen > 512) {
                        isValidLen = false;
                    }
                    isValidLen = isValidLen;
                    isValidLen = isValidLen3;
                    break;
                case 7:
                    isValidLen = keyLen == 128;
                    if (truncLen != 96) {
                        isValidLen = false;
                    }
                    break;
                case '\b':
                    isValidLen = keyLen == 128;
                    if (truncLen != 96) {
                        isValidLen = false;
                    }
                    break;
                case '\t':
                    isValidLen = keyLen == 160 || keyLen == 224 || keyLen == 288;
                    if (!(truncLen == 64 || truncLen == 96 || truncLen == 128)) {
                        isValidLen = false;
                    }
                    break;
                case '\n':
                    isValidLen = keyLen == 288;
                    if (truncLen != 128) {
                        isValidLen = false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Couldn't find an algorithm: " + name);
            }
            if (!isValidLen) {
                throw new IllegalArgumentException("Invalid key material keyLength: " + keyLen);
            } else if (!isValidLen) {
                throw new IllegalArgumentException("Invalid truncation keyLength: " + truncLen);
            }
        } else {
            throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean isAuthentication() {
        char c;
        String name = getName();
        switch (name.hashCode()) {
            case -1751374730:
                if (name.equals(AUTH_AES_XCBC)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 559425185:
                if (name.equals(AUTH_HMAC_SHA256)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 559457797:
                if (name.equals(AUTH_HMAC_SHA384)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 559510590:
                if (name.equals(AUTH_HMAC_SHA512)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 759177996:
                if (name.equals(AUTH_HMAC_MD5)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1206161110:
                if (name.equals(AUTH_AES_CMAC)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2065384259:
                if (name.equals(AUTH_HMAC_SHA1)) {
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
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean isEncryption() {
        char c;
        String name = getName();
        switch (name.hashCode()) {
            case 394796030:
                if (name.equals(CRYPT_AES_CBC)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1574008592:
                if (name.equals(CRYPT_AES_CTR)) {
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
            case 1:
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean isAead() {
        char c;
        String name = getName();
        switch (name.hashCode()) {
            case -1137603038:
                if (name.equals(AUTH_CRYPT_AES_GCM)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -878787135:
                if (name.equals(AUTH_CRYPT_CHACHA20_POLY1305)) {
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
            case 1:
                return true;
            default:
                return false;
        }
    }

    private static boolean isUnsafeBuild() {
        return Build.IS_DEBUGGABLE && Build.IS_ENG;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{mName=");
        sb.append(this.mName);
        sb.append(", mKey=");
        sb.append(isUnsafeBuild() ? HexDump.toHexString(this.mKey) : "<hidden>");
        sb.append(", mTruncLenBits=");
        sb.append(this.mTruncLenBits);
        sb.append("}");
        return sb.toString();
    }

    public static boolean equals(IpSecAlgorithm lhs, IpSecAlgorithm rhs) {
        return (lhs == null || rhs == null) ? lhs == rhs : lhs.mName.equals(rhs.mName) && Arrays.equals(lhs.mKey, rhs.mKey) && lhs.mTruncLenBits == rhs.mTruncLenBits;
    }
}
