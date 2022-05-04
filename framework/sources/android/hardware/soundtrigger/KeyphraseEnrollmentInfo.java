package android.hardware.soundtrigger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Slog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes2.dex */
public class KeyphraseEnrollmentInfo {
    public static final String ACTION_MANAGE_VOICE_KEYPHRASES = "com.android.intent.action.MANAGE_VOICE_KEYPHRASES";
    public static final String EXTRA_VOICE_KEYPHRASE_ACTION = "com.android.intent.extra.VOICE_KEYPHRASE_ACTION";
    public static final String EXTRA_VOICE_KEYPHRASE_HINT_TEXT = "com.android.intent.extra.VOICE_KEYPHRASE_HINT_TEXT";
    public static final String EXTRA_VOICE_KEYPHRASE_LOCALE = "com.android.intent.extra.VOICE_KEYPHRASE_LOCALE";
    public static final int MANAGE_ACTION_ENROLL = 0;
    public static final int MANAGE_ACTION_RE_ENROLL = 1;
    public static final int MANAGE_ACTION_UN_ENROLL = 2;
    private static final String TAG = "KeyphraseEnrollmentInfo";
    private static final String VOICE_KEYPHRASE_META_DATA = "android.voice_enrollment";
    private final Map<KeyphraseMetadata, String> mKeyphrasePackageMap;
    private final KeyphraseMetadata[] mKeyphrases;
    private String mParseError;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ManageActions {
    }

    public KeyphraseEnrollmentInfo(PackageManager pm) {
        Objects.requireNonNull(pm);
        List<ResolveInfo> ris = pm.queryIntentServices(new Intent(ACTION_MANAGE_VOICE_KEYPHRASES), 65536);
        if (ris == null || ris.isEmpty()) {
            this.mParseError = "No enrollment applications found";
            this.mKeyphrasePackageMap = Collections.emptyMap();
            this.mKeyphrases = null;
            return;
        }
        List<String> parseErrors = new LinkedList<>();
        this.mKeyphrasePackageMap = new HashMap();
        for (ResolveInfo ri : ris) {
            try {
                ApplicationInfo ai = pm.getApplicationInfo(ri.serviceInfo.packageName, 128);
                if ((ai.privateFlags & 8) == 0) {
                    Slog.w(TAG, ai.packageName + " is not a privileged system app");
                } else if (!Manifest.permission.MANAGE_VOICE_KEYPHRASES.equals(ai.permission)) {
                    Slog.w(TAG, ai.packageName + " does not require MANAGE_VOICE_KEYPHRASES");
                } else {
                    KeyphraseMetadata metadata = getKeyphraseMetadataFromApplicationInfo(pm, ai, parseErrors);
                    if (metadata != null) {
                        this.mKeyphrasePackageMap.put(metadata, ai.packageName);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                String error = "error parsing voice enrollment meta-data for " + ri.serviceInfo.packageName;
                parseErrors.add(error + ": " + e);
                Slog.w(TAG, error, e);
            }
        }
        if (this.mKeyphrasePackageMap.isEmpty()) {
            parseErrors.add("No suitable enrollment application found");
            Slog.w(TAG, "No suitable enrollment application found");
            this.mKeyphrases = null;
        } else {
            this.mKeyphrases = (KeyphraseMetadata[]) this.mKeyphrasePackageMap.keySet().toArray(new KeyphraseMetadata[0]);
        }
        if (!parseErrors.isEmpty()) {
            this.mParseError = TextUtils.join("\n", parseErrors);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007b, code lost:
        if (r1 != null) goto L_0x007d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007d, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b0, code lost:
        if (0 == 0) goto L_0x00b3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b3, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.hardware.soundtrigger.KeyphraseMetadata getKeyphraseMetadataFromApplicationInfo(android.content.pm.PackageManager r12, android.content.pm.ApplicationInfo r13, java.util.List<java.lang.String> r14) {
        /*
            r11 = this;
            java.lang.String r0 = "KeyphraseEnrollmentInfo"
            r1 = 0
            java.lang.String r2 = r13.packageName
            r3 = 0
            java.lang.String r4 = "android.voice_enrollment"
            android.content.res.XmlResourceParser r4 = r13.loadXmlMetaData(r12, r4)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r1 = r4
            r4 = 0
            if (r1 != 0) goto L_0x002e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r5.<init>()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            java.lang.String r6 = "No android.voice_enrollment meta-data for "
            r5.append(r6)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r5.append(r2)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r14.add(r5)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            android.util.Slog.w(r0, r5)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            return r4
        L_0x002e:
            android.content.res.Resources r5 = r12.getResourcesForApplication(r13)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            android.util.AttributeSet r6 = android.util.Xml.asAttributeSet(r1)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
        L_0x0036:
            int r7 = r1.next()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r8 = r7
            r9 = 1
            if (r7 == r9) goto L_0x0042
            r7 = 2
            if (r8 == r7) goto L_0x0042
            goto L_0x0036
        L_0x0042:
            java.lang.String r7 = r1.getName()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            java.lang.String r9 = "voice-enrollment-application"
            boolean r9 = r9.equals(r7)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            if (r9 != 0) goto L_0x006d
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r9.<init>()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            java.lang.String r10 = "Meta-data does not start with voice-enrollment-application tag for "
            r9.append(r10)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r9.append(r2)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            java.lang.String r9 = r9.toString()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r14.add(r9)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            android.util.Slog.w(r0, r9)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            if (r1 == 0) goto L_0x006c
            r1.close()
        L_0x006c:
            return r4
        L_0x006d:
            int[] r4 = com.android.internal.R.styleable.VoiceEnrollmentApplication     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            android.content.res.TypedArray r4 = r5.obtainAttributes(r6, r4)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            android.hardware.soundtrigger.KeyphraseMetadata r9 = r11.getKeyphraseFromTypedArray(r4, r2, r14)     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            r3 = r9
            r4.recycle()     // Catch: all -> 0x0081, XmlPullParserException | NameNotFoundException | IOException -> 0x0083
            if (r1 == 0) goto L_0x00b3
        L_0x007d:
            r1.close()
            goto L_0x00b3
        L_0x0081:
            r0 = move-exception
            goto L_0x00b4
        L_0x0083:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0081
            r5.<init>()     // Catch: all -> 0x0081
            java.lang.String r6 = "Error parsing keyphrase enrollment meta-data for "
            r5.append(r6)     // Catch: all -> 0x0081
            r5.append(r2)     // Catch: all -> 0x0081
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0081
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x0081
            r6.<init>()     // Catch: all -> 0x0081
            r6.append(r5)     // Catch: all -> 0x0081
            java.lang.String r7 = ": "
            r6.append(r7)     // Catch: all -> 0x0081
            r6.append(r4)     // Catch: all -> 0x0081
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x0081
            r14.add(r6)     // Catch: all -> 0x0081
            android.util.Slog.w(r0, r5, r4)     // Catch: all -> 0x0081
            if (r1 == 0) goto L_0x00b3
            goto L_0x007d
        L_0x00b3:
            return r3
        L_0x00b4:
            if (r1 == 0) goto L_0x00b9
            r1.close()
        L_0x00b9:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.soundtrigger.KeyphraseEnrollmentInfo.getKeyphraseMetadataFromApplicationInfo(android.content.pm.PackageManager, android.content.pm.ApplicationInfo, java.util.List):android.hardware.soundtrigger.KeyphraseMetadata");
    }

    private KeyphraseMetadata getKeyphraseFromTypedArray(TypedArray array, String packageName, List<String> parseErrors) {
        int searchKeyphraseId = array.getInt(0, -1);
        if (searchKeyphraseId <= 0) {
            String error = "No valid searchKeyphraseId specified in meta-data for " + packageName;
            parseErrors.add(error);
            Slog.w(TAG, error);
            return null;
        }
        String searchKeyphrase = array.getString(1);
        if (searchKeyphrase == null) {
            String error2 = "No valid searchKeyphrase specified in meta-data for " + packageName;
            parseErrors.add(error2);
            Slog.w(TAG, error2);
            return null;
        }
        String searchKeyphraseSupportedLocales = array.getString(2);
        if (searchKeyphraseSupportedLocales == null) {
            String error3 = "No valid searchKeyphraseSupportedLocales specified in meta-data for " + packageName;
            parseErrors.add(error3);
            Slog.w(TAG, error3);
            return null;
        }
        ArraySet<Locale> locales = new ArraySet<>();
        if (!TextUtils.isEmpty(searchKeyphraseSupportedLocales)) {
            try {
                String[] supportedLocalesDelimited = searchKeyphraseSupportedLocales.split(",");
                for (String s : supportedLocalesDelimited) {
                    locales.add(Locale.forLanguageTag(s));
                }
            } catch (Exception e) {
                String error4 = "Error reading searchKeyphraseSupportedLocales from meta-data for " + packageName;
                parseErrors.add(error4);
                Slog.w(TAG, error4);
                return null;
            }
        }
        int recognitionModes = array.getInt(3, -1);
        if (recognitionModes >= 0) {
            return new KeyphraseMetadata(searchKeyphraseId, searchKeyphrase, locales, recognitionModes);
        }
        String error5 = "No valid searchKeyphraseRecognitionFlags specified in meta-data for " + packageName;
        parseErrors.add(error5);
        Slog.w(TAG, error5);
        return null;
    }

    public String getParseError() {
        return this.mParseError;
    }

    public Collection<KeyphraseMetadata> listKeyphraseMetadata() {
        return Arrays.asList(this.mKeyphrases);
    }

    public Intent getManageKeyphraseIntent(int action, String keyphrase, Locale locale) {
        Objects.requireNonNull(keyphrase);
        Objects.requireNonNull(locale);
        Map<KeyphraseMetadata, String> map = this.mKeyphrasePackageMap;
        if (map == null || map.isEmpty()) {
            Slog.w(TAG, "No enrollment application exists");
            return null;
        }
        KeyphraseMetadata keyphraseMetadata = getKeyphraseMetadata(keyphrase, locale);
        if (keyphraseMetadata != null) {
            return new Intent(ACTION_MANAGE_VOICE_KEYPHRASES).setPackage(this.mKeyphrasePackageMap.get(keyphraseMetadata)).putExtra(EXTRA_VOICE_KEYPHRASE_HINT_TEXT, keyphrase).putExtra(EXTRA_VOICE_KEYPHRASE_LOCALE, locale.toLanguageTag()).putExtra(EXTRA_VOICE_KEYPHRASE_ACTION, action);
        }
        return null;
    }

    public KeyphraseMetadata getKeyphraseMetadata(String keyphrase, Locale locale) {
        Objects.requireNonNull(keyphrase);
        Objects.requireNonNull(locale);
        KeyphraseMetadata[] keyphraseMetadataArr = this.mKeyphrases;
        if (keyphraseMetadataArr != null && keyphraseMetadataArr.length > 0) {
            for (KeyphraseMetadata keyphraseMetadata : keyphraseMetadataArr) {
                if (keyphraseMetadata.supportsPhrase(keyphrase) && keyphraseMetadata.supportsLocale(locale)) {
                    return keyphraseMetadata;
                }
            }
        }
        Slog.w(TAG, "No enrollment application supports the given keyphrase/locale: '" + keyphrase + "'/" + locale);
        return null;
    }

    public String toString() {
        return "KeyphraseEnrollmentInfo [KeyphrasePackageMap=" + this.mKeyphrasePackageMap.toString() + ", ParseError=" + this.mParseError + "]";
    }
}
