package com.android.internal.app;

import android.security.keystore.KeyProperties;
import com.android.internal.midi.MidiConstants;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes4.dex */
public class ChooserUtil {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            md.update(input.getBytes(UTF_8));
            return convertBytesToHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String convertBytesToHexString(byte[] input) {
        char[] chars = new char[input.length * 2];
        for (int i = 0; i < input.length; i++) {
            byte b = input[i];
            chars[i * 2] = Character.forDigit((b >> 4) & 15, 16);
            chars[(i * 2) + 1] = Character.forDigit(b & MidiConstants.STATUS_CHANNEL_MASK, 16);
        }
        return new String(chars);
    }

    private ChooserUtil() {
    }
}
