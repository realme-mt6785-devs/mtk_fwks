package android.security;

import android.os.Environment;
import android.os.FileUtils;
import com.android.internal.logging.nano.MetricsProto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import libcore.io.IoUtils;
/* loaded from: classes2.dex */
public class SystemKeyStore {
    private static final String KEY_FILE_EXTENSION = ".sks";
    private static final String SYSTEM_KEYSTORE_DIRECTORY = "misc/systemkeys";
    private static SystemKeyStore mInstance = new SystemKeyStore();

    private SystemKeyStore() {
    }

    public static SystemKeyStore getInstance() {
        return mInstance;
    }

    public static String toHexString(byte[] keyData) {
        if (keyData == null) {
            return null;
        }
        int length = keyData.length;
        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);
        for (byte b : keyData) {
            String hexStr = Integer.toString(b & 255, 16);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }

    public String generateNewKeyHexString(int numBits, String algName, String keyName) throws NoSuchAlgorithmException {
        return toHexString(generateNewKey(numBits, algName, keyName));
    }

    public byte[] generateNewKey(int numBits, String algName, String keyName) throws NoSuchAlgorithmException {
        File keyFile = getKeyFile(keyName);
        if (!keyFile.exists()) {
            KeyGenerator skg = KeyGenerator.getInstance(algName);
            SecureRandom srng = SecureRandom.getInstance("SHA1PRNG");
            skg.init(numBits, srng);
            SecretKey sk = skg.generateKey();
            byte[] retKey = sk.getEncoded();
            try {
                if (keyFile.createNewFile()) {
                    FileOutputStream fos = new FileOutputStream(keyFile);
                    fos.write(retKey);
                    fos.flush();
                    FileUtils.sync(fos);
                    fos.close();
                    FileUtils.setPermissions(keyFile.getName(), (int) MetricsProto.MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION, -1, -1);
                    return retKey;
                }
                throw new IllegalArgumentException();
            } catch (IOException e) {
                return null;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private File getKeyFile(String keyName) {
        File sysKeystoreDir = new File(Environment.getDataDirectory(), SYSTEM_KEYSTORE_DIRECTORY);
        File keyFile = new File(sysKeystoreDir, keyName + KEY_FILE_EXTENSION);
        return keyFile;
    }

    public String retrieveKeyHexString(String keyName) throws IOException {
        return toHexString(retrieveKey(keyName));
    }

    public byte[] retrieveKey(String keyName) throws IOException {
        File keyFile = getKeyFile(keyName);
        if (!keyFile.exists()) {
            return null;
        }
        return IoUtils.readFileAsByteArray(keyFile.toString());
    }

    public void deleteKey(String keyName) {
        File keyFile = getKeyFile(keyName);
        if (keyFile.exists()) {
            keyFile.delete();
            return;
        }
        throw new IllegalArgumentException();
    }
}
