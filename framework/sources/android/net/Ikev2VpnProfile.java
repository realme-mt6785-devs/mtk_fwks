package android.net;

import android.security.Credentials;
import android.security.keystore.KeyProperties;
import com.android.internal.net.VpnProfile;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class Ikev2VpnProfile extends PlatformVpnProfile {
    private static final String ANDROID_KEYSTORE_PROVIDER = "AndroidKeyStore";
    public static final List<String> DEFAULT_ALGORITHMS;
    private static final String EMPTY_CERT = "";
    private static final String MISSING_PARAM_MSG_TMPL = "Required parameter was not provided: %s";
    public static final String PREFIX_INLINE = "INLINE:";
    public static final String PREFIX_KEYSTORE_ALIAS = "KEYSTORE_ALIAS:";
    private final List<String> mAllowedAlgorithms;
    private final boolean mIsBypassable;
    private final boolean mIsMetered;
    private final boolean mIsRestrictedToTestNetworks;
    private final int mMaxMtu;
    private final String mPassword;
    private final byte[] mPresharedKey;
    private final ProxyInfo mProxyInfo;
    private final PrivateKey mRsaPrivateKey;
    private final String mServerAddr;
    private final X509Certificate mServerRootCaCert;
    private final X509Certificate mUserCert;
    private final String mUserIdentity;
    private final String mUsername;

    private static void addAlgorithmIfSupported(List<String> algorithms, String ipSecAlgoName) {
        if (IpSecAlgorithm.getSupportedAlgorithms().contains(ipSecAlgoName)) {
            algorithms.add(ipSecAlgoName);
        }
    }

    static {
        List<String> algorithms = new ArrayList<>();
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.CRYPT_AES_CBC);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.CRYPT_AES_CTR);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_HMAC_SHA256);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_HMAC_SHA384);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_HMAC_SHA512);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_AES_XCBC);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_AES_CMAC);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_CRYPT_AES_GCM);
        addAlgorithmIfSupported(algorithms, IpSecAlgorithm.AUTH_CRYPT_CHACHA20_POLY1305);
        DEFAULT_ALGORITHMS = Collections.unmodifiableList(algorithms);
    }

    private Ikev2VpnProfile(int type, String serverAddr, String userIdentity, byte[] presharedKey, X509Certificate serverRootCaCert, String username, String password, PrivateKey rsaPrivateKey, X509Certificate userCert, ProxyInfo proxyInfo, List<String> allowedAlgorithms, boolean isBypassable, boolean isMetered, int maxMtu, boolean restrictToTestNetworks) {
        super(type);
        checkNotNull(serverAddr, MISSING_PARAM_MSG_TMPL, "Server address");
        checkNotNull(userIdentity, MISSING_PARAM_MSG_TMPL, "User Identity");
        checkNotNull(allowedAlgorithms, MISSING_PARAM_MSG_TMPL, "Allowed Algorithms");
        this.mServerAddr = serverAddr;
        this.mUserIdentity = userIdentity;
        this.mPresharedKey = presharedKey == null ? null : Arrays.copyOf(presharedKey, presharedKey.length);
        this.mServerRootCaCert = serverRootCaCert;
        this.mUsername = username;
        this.mPassword = password;
        this.mRsaPrivateKey = rsaPrivateKey;
        this.mUserCert = userCert;
        this.mProxyInfo = new ProxyInfo(proxyInfo);
        this.mAllowedAlgorithms = Collections.unmodifiableList(new ArrayList(allowedAlgorithms));
        this.mIsBypassable = isBypassable;
        this.mIsMetered = isMetered;
        this.mMaxMtu = maxMtu;
        this.mIsRestrictedToTestNetworks = restrictToTestNetworks;
        validate();
    }

    private void validate() {
        Preconditions.checkStringNotEmpty(this.mServerAddr, MISSING_PARAM_MSG_TMPL, "Server Address");
        Preconditions.checkStringNotEmpty(this.mUserIdentity, MISSING_PARAM_MSG_TMPL, "User Identity");
        if (this.mMaxMtu >= 1280) {
            switch (this.mType) {
                case 6:
                    checkNotNull(this.mUsername, MISSING_PARAM_MSG_TMPL, "Username");
                    checkNotNull(this.mPassword, MISSING_PARAM_MSG_TMPL, "Password");
                    X509Certificate x509Certificate = this.mServerRootCaCert;
                    if (x509Certificate != null) {
                        checkCert(x509Certificate);
                        break;
                    }
                    break;
                case 7:
                    checkNotNull(this.mPresharedKey, MISSING_PARAM_MSG_TMPL, "Preshared Key");
                    break;
                case 8:
                    checkNotNull(this.mUserCert, MISSING_PARAM_MSG_TMPL, "User cert");
                    checkNotNull(this.mRsaPrivateKey, MISSING_PARAM_MSG_TMPL, "RSA Private key");
                    checkCert(this.mUserCert);
                    X509Certificate x509Certificate2 = this.mServerRootCaCert;
                    if (x509Certificate2 != null) {
                        checkCert(x509Certificate2);
                        break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid auth method set");
            }
            validateAllowedAlgorithms(this.mAllowedAlgorithms);
            return;
        }
        throw new IllegalArgumentException("Max MTU must be at least1280");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateAllowedAlgorithms(List<String> algorithmNames) {
        if (algorithmNames.contains(IpSecAlgorithm.AUTH_HMAC_MD5) || algorithmNames.contains(IpSecAlgorithm.AUTH_HMAC_SHA1)) {
            throw new IllegalArgumentException("Algorithm not supported for IKEv2 VPN profiles");
        } else if (!hasAeadAlgorithms(algorithmNames) && !hasNormalModeAlgorithms(algorithmNames)) {
            throw new IllegalArgumentException("Algorithm set missing support for Auth, Crypt or both");
        }
    }

    public static boolean hasAeadAlgorithms(List<String> algorithmNames) {
        return algorithmNames.contains(IpSecAlgorithm.AUTH_CRYPT_AES_GCM);
    }

    public static boolean hasNormalModeAlgorithms(List<String> algorithmNames) {
        boolean hasCrypt = algorithmNames.contains(IpSecAlgorithm.CRYPT_AES_CBC);
        boolean hasAuth = algorithmNames.contains(IpSecAlgorithm.AUTH_HMAC_SHA256) || algorithmNames.contains(IpSecAlgorithm.AUTH_HMAC_SHA384) || algorithmNames.contains(IpSecAlgorithm.AUTH_HMAC_SHA512);
        return hasCrypt && hasAuth;
    }

    public String getServerAddr() {
        return this.mServerAddr;
    }

    public String getUserIdentity() {
        return this.mUserIdentity;
    }

    public byte[] getPresharedKey() {
        byte[] bArr = this.mPresharedKey;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public X509Certificate getServerRootCaCert() {
        return this.mServerRootCaCert;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public PrivateKey getRsaPrivateKey() {
        return this.mRsaPrivateKey;
    }

    public X509Certificate getUserCert() {
        return this.mUserCert;
    }

    public ProxyInfo getProxyInfo() {
        return this.mProxyInfo;
    }

    public List<String> getAllowedAlgorithms() {
        return this.mAllowedAlgorithms;
    }

    public boolean isBypassable() {
        return this.mIsBypassable;
    }

    public boolean isMetered() {
        return this.mIsMetered;
    }

    public int getMaxMtu() {
        return this.mMaxMtu;
    }

    public boolean isRestrictedToTestNetworks() {
        return this.mIsRestrictedToTestNetworks;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), this.mServerAddr, this.mUserIdentity, Integer.valueOf(Arrays.hashCode(this.mPresharedKey)), this.mServerRootCaCert, this.mUsername, this.mPassword, this.mRsaPrivateKey, this.mUserCert, this.mProxyInfo, this.mAllowedAlgorithms, Boolean.valueOf(this.mIsBypassable), Boolean.valueOf(this.mIsMetered), Integer.valueOf(this.mMaxMtu), Boolean.valueOf(this.mIsRestrictedToTestNetworks));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Ikev2VpnProfile)) {
            return false;
        }
        Ikev2VpnProfile other = (Ikev2VpnProfile) obj;
        return this.mType == other.mType && Objects.equals(this.mServerAddr, other.mServerAddr) && Objects.equals(this.mUserIdentity, other.mUserIdentity) && Arrays.equals(this.mPresharedKey, other.mPresharedKey) && Objects.equals(this.mServerRootCaCert, other.mServerRootCaCert) && Objects.equals(this.mUsername, other.mUsername) && Objects.equals(this.mPassword, other.mPassword) && Objects.equals(this.mRsaPrivateKey, other.mRsaPrivateKey) && Objects.equals(this.mUserCert, other.mUserCert) && Objects.equals(this.mProxyInfo, other.mProxyInfo) && Objects.equals(this.mAllowedAlgorithms, other.mAllowedAlgorithms) && this.mIsBypassable == other.mIsBypassable && this.mIsMetered == other.mIsMetered && this.mMaxMtu == other.mMaxMtu && this.mIsRestrictedToTestNetworks == other.mIsRestrictedToTestNetworks;
    }

    @Override // android.net.PlatformVpnProfile
    public VpnProfile toVpnProfile() throws IOException, GeneralSecurityException {
        String str = "";
        VpnProfile profile = new VpnProfile(str, this.mIsRestrictedToTestNetworks);
        profile.type = this.mType;
        profile.server = this.mServerAddr;
        profile.ipsecIdentifier = this.mUserIdentity;
        profile.proxy = this.mProxyInfo;
        profile.setAllowedAlgorithms(this.mAllowedAlgorithms);
        profile.isBypassable = this.mIsBypassable;
        profile.isMetered = this.mIsMetered;
        profile.maxMtu = this.mMaxMtu;
        profile.areAuthParamsInline = true;
        profile.saveLogin = true;
        switch (this.mType) {
            case 6:
                profile.username = this.mUsername;
                profile.password = this.mPassword;
                X509Certificate x509Certificate = this.mServerRootCaCert;
                if (x509Certificate != null) {
                    str = certificateToPemString(x509Certificate);
                }
                profile.ipsecCaCert = str;
                break;
            case 7:
                profile.ipsecSecret = encodeForIpsecSecret(this.mPresharedKey);
                break;
            case 8:
                profile.ipsecUserCert = certificateToPemString(this.mUserCert);
                profile.ipsecSecret = PREFIX_INLINE + encodeForIpsecSecret(this.mRsaPrivateKey.getEncoded());
                X509Certificate x509Certificate2 = this.mServerRootCaCert;
                if (x509Certificate2 != null) {
                    str = certificateToPemString(x509Certificate2);
                }
                profile.ipsecCaCert = str;
                break;
            default:
                throw new IllegalArgumentException("Invalid auth method set");
        }
        return profile;
    }

    private static PrivateKey getPrivateKeyFromAndroidKeystore(String alias) {
        try {
            KeyStore keystore = KeyStore.getInstance("AndroidKeyStore");
            keystore.load(null);
            Key key = keystore.getKey(alias, null);
            if (key instanceof PrivateKey) {
                return (PrivateKey) key;
            }
            throw new IllegalStateException("Unexpected key type returned from android keystore.");
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load key from android keystore.", e);
        }
    }

    public static Ikev2VpnProfile fromVpnProfile(VpnProfile profile) throws GeneralSecurityException {
        PrivateKey key;
        Builder builder = new Builder(profile.server, profile.ipsecIdentifier);
        builder.setProxy(profile.proxy);
        builder.setAllowedAlgorithms(profile.getAllowedAlgorithms());
        builder.setBypassable(profile.isBypassable);
        builder.setMetered(profile.isMetered);
        builder.setMaxMtu(profile.maxMtu);
        if (profile.isRestrictedToTestNetworks) {
            builder.restrictToTestNetworks();
        }
        switch (profile.type) {
            case 6:
                builder.setAuthUsernamePassword(profile.username, profile.password, certificateFromPemString(profile.ipsecCaCert));
                break;
            case 7:
                builder.setAuthPsk(decodeFromIpsecSecret(profile.ipsecSecret));
                break;
            case 8:
                if (profile.ipsecSecret.startsWith(PREFIX_KEYSTORE_ALIAS)) {
                    String alias = profile.ipsecSecret.substring(PREFIX_KEYSTORE_ALIAS.length());
                    key = getPrivateKeyFromAndroidKeystore(alias);
                } else if (profile.ipsecSecret.startsWith(PREFIX_INLINE)) {
                    key = getPrivateKey(profile.ipsecSecret.substring(PREFIX_INLINE.length()));
                } else {
                    throw new IllegalArgumentException("Invalid RSA private key prefix");
                }
                X509Certificate userCert = certificateFromPemString(profile.ipsecUserCert);
                X509Certificate serverRootCa = certificateFromPemString(profile.ipsecCaCert);
                builder.setAuthDigitalSignature(userCert, key, serverRootCa);
                break;
            default:
                throw new IllegalArgumentException("Invalid auth method set");
        }
        return builder.build();
    }

    public static boolean isValidVpnProfile(VpnProfile profile) {
        if (profile.server.isEmpty() || profile.ipsecIdentifier.isEmpty()) {
            return false;
        }
        switch (profile.type) {
            case 6:
                return !profile.username.isEmpty() && !profile.password.isEmpty();
            case 7:
                return !profile.ipsecSecret.isEmpty();
            case 8:
                return !profile.ipsecSecret.isEmpty() && !profile.ipsecUserCert.isEmpty();
            default:
                return false;
        }
    }

    public static String certificateToPemString(X509Certificate cert) throws IOException, CertificateEncodingException {
        if (cert == null) {
            return "";
        }
        return new String(Credentials.convertToPem(cert), StandardCharsets.US_ASCII);
    }

    private static X509Certificate certificateFromPemString(String certStr) throws CertificateException {
        X509Certificate x509Certificate = null;
        if (certStr == null || "".equals(certStr)) {
            return null;
        }
        try {
            List<X509Certificate> certs = Credentials.convertFromPem(certStr.getBytes(StandardCharsets.US_ASCII));
            if (!certs.isEmpty()) {
                x509Certificate = certs.get(0);
            }
            return x509Certificate;
        } catch (IOException e) {
            throw new CertificateException(e);
        }
    }

    public static String encodeForIpsecSecret(byte[] secret) {
        checkNotNull(secret, MISSING_PARAM_MSG_TMPL, "secret");
        return Base64.getEncoder().encodeToString(secret);
    }

    private static byte[] decodeFromIpsecSecret(String encoded) {
        checkNotNull(encoded, MISSING_PARAM_MSG_TMPL, "encoded");
        return Base64.getDecoder().decode(encoded);
    }

    private static PrivateKey getPrivateKey(String keyStr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeFromIpsecSecret(keyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkCert(X509Certificate cert) {
        try {
            certificateToPemString(cert);
        } catch (IOException | GeneralSecurityException e) {
            throw new IllegalArgumentException("Certificate could not be encoded");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T checkNotNull(T reference, String messageTemplate, Object... messageArgs) {
        Objects.requireNonNull(reference, String.format(messageTemplate, messageArgs));
        return reference;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private String mPassword;
        private byte[] mPresharedKey;
        private ProxyInfo mProxyInfo;
        private PrivateKey mRsaPrivateKey;
        private final String mServerAddr;
        private X509Certificate mServerRootCaCert;
        private X509Certificate mUserCert;
        private final String mUserIdentity;
        private String mUsername;
        private int mType = -1;
        private List<String> mAllowedAlgorithms = Ikev2VpnProfile.DEFAULT_ALGORITHMS;
        private boolean mIsBypassable = false;
        private boolean mIsMetered = true;
        private int mMaxMtu = 1360;
        private boolean mIsRestrictedToTestNetworks = false;

        public Builder(String serverAddr, String identity) {
            Ikev2VpnProfile.checkNotNull(serverAddr, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "serverAddr");
            Ikev2VpnProfile.checkNotNull(identity, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "identity");
            this.mServerAddr = serverAddr;
            this.mUserIdentity = identity;
        }

        private void resetAuthParams() {
            this.mPresharedKey = null;
            this.mServerRootCaCert = null;
            this.mUsername = null;
            this.mPassword = null;
            this.mRsaPrivateKey = null;
            this.mUserCert = null;
        }

        public Builder setAuthUsernamePassword(String user, String pass, X509Certificate serverRootCa) {
            Ikev2VpnProfile.checkNotNull(user, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "user");
            Ikev2VpnProfile.checkNotNull(pass, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "pass");
            if (serverRootCa != null) {
                Ikev2VpnProfile.checkCert(serverRootCa);
            }
            resetAuthParams();
            this.mUsername = user;
            this.mPassword = pass;
            this.mServerRootCaCert = serverRootCa;
            this.mType = 6;
            return this;
        }

        public Builder setAuthDigitalSignature(X509Certificate userCert, PrivateKey key, X509Certificate serverRootCa) {
            Ikev2VpnProfile.checkNotNull(userCert, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "userCert");
            Ikev2VpnProfile.checkNotNull(key, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "key");
            Ikev2VpnProfile.checkCert(userCert);
            if (serverRootCa != null) {
                Ikev2VpnProfile.checkCert(serverRootCa);
            }
            resetAuthParams();
            this.mUserCert = userCert;
            this.mRsaPrivateKey = key;
            this.mServerRootCaCert = serverRootCa;
            this.mType = 8;
            return this;
        }

        public Builder setAuthPsk(byte[] psk) {
            Ikev2VpnProfile.checkNotNull(psk, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "psk");
            resetAuthParams();
            this.mPresharedKey = psk;
            this.mType = 7;
            return this;
        }

        public Builder setBypassable(boolean isBypassable) {
            this.mIsBypassable = isBypassable;
            return this;
        }

        public Builder setProxy(ProxyInfo proxy) {
            this.mProxyInfo = proxy;
            return this;
        }

        public Builder setMaxMtu(int mtu) {
            if (mtu >= 1280) {
                this.mMaxMtu = mtu;
                return this;
            }
            throw new IllegalArgumentException("Max MTU must be at least 1280");
        }

        public Builder setMetered(boolean isMetered) {
            this.mIsMetered = isMetered;
            return this;
        }

        public Builder setAllowedAlgorithms(List<String> algorithmNames) {
            Ikev2VpnProfile.checkNotNull(algorithmNames, Ikev2VpnProfile.MISSING_PARAM_MSG_TMPL, "algorithmNames");
            Ikev2VpnProfile.validateAllowedAlgorithms(algorithmNames);
            this.mAllowedAlgorithms = algorithmNames;
            return this;
        }

        public Builder restrictToTestNetworks() {
            this.mIsRestrictedToTestNetworks = true;
            return this;
        }

        public Ikev2VpnProfile build() {
            return new Ikev2VpnProfile(this.mType, this.mServerAddr, this.mUserIdentity, this.mPresharedKey, this.mServerRootCaCert, this.mUsername, this.mPassword, this.mRsaPrivateKey, this.mUserCert, this.mProxyInfo, this.mAllowedAlgorithms, this.mIsBypassable, this.mIsMetered, this.mMaxMtu, this.mIsRestrictedToTestNetworks);
        }
    }
}
