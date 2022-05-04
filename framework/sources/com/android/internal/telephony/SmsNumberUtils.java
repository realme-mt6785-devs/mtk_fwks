package com.android.internal.telephony;

import android.app.blob.XmlTags;
import android.content.Context;
import android.os.Binder;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.security.keystore.KeyProperties;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes4.dex */
public class SmsNumberUtils {
    private static int[] ALL_COUNTRY_CODES = null;
    private static final int CDMA_HOME_NETWORK = 1;
    private static final int CDMA_ROAMING_NETWORK = 2;
    private static final boolean DBG;
    private static final int GSM_UMTS_NETWORK = 0;
    private static HashMap<String, ArrayList<String>> IDDS_MAPS = null;
    private static int MAX_COUNTRY_CODES_LENGTH = 0;
    private static final int MIN_COUNTRY_AREA_LOCAL_LENGTH = 10;
    private static final int NANP_CC = 1;
    private static final String NANP_IDD = "011";
    private static final int NANP_LONG_LENGTH = 11;
    private static final int NANP_MEDIUM_LENGTH = 10;
    private static final String NANP_NDD = "1";
    private static final int NANP_SHORT_LENGTH = 7;
    private static final int NP_CC_AREA_LOCAL = 104;
    private static final int NP_HOMEIDD_CC_AREA_LOCAL = 101;
    private static final int NP_INTERNATIONAL_BEGIN = 100;
    private static final int NP_LOCALIDD_CC_AREA_LOCAL = 103;
    private static final int NP_NANP_AREA_LOCAL = 2;
    private static final int NP_NANP_BEGIN = 1;
    private static final int NP_NANP_LOCAL = 1;
    private static final int NP_NANP_LOCALIDD_CC_AREA_LOCAL = 5;
    private static final int NP_NANP_NBPCD_CC_AREA_LOCAL = 4;
    private static final int NP_NANP_NBPCD_HOMEIDD_CC_AREA_LOCAL = 6;
    private static final int NP_NANP_NDD_AREA_LOCAL = 3;
    private static final int NP_NBPCD_CC_AREA_LOCAL = 102;
    private static final int NP_NBPCD_HOMEIDD_CC_AREA_LOCAL = 100;
    private static final int NP_NONE = 0;
    private static final String PLUS_SIGN = "+";
    private static final String TAG = "SmsNumberUtils";

    static {
        boolean z = false;
        if (SystemProperties.getInt("ro.debuggable", 0) == 1) {
            z = true;
        }
        DBG = z;
        ALL_COUNTRY_CODES = null;
        IDDS_MAPS = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class NumberEntry {
        public String IDD;
        public int countryCode;
        public String number;

        public NumberEntry(String number) {
            this.number = number;
        }
    }

    private static String formatNumber(Context context, String number, String activeMcc, int networkType) {
        if (number == null) {
            throw new IllegalArgumentException("number is null");
        } else if (activeMcc == null || activeMcc.trim().length() == 0) {
            throw new IllegalArgumentException("activeMcc is null or empty!");
        } else {
            String networkPortionNumber = PhoneNumberUtils.extractNetworkPortion(number);
            if (networkPortionNumber == null || networkPortionNumber.length() == 0) {
                throw new IllegalArgumentException("Number is invalid!");
            }
            NumberEntry numberEntry = new NumberEntry(networkPortionNumber);
            ArrayList<String> allIDDs = getAllIDDs(context, activeMcc);
            int nanpState = checkNANP(numberEntry, allIDDs);
            boolean z = DBG;
            if (z) {
                Log.d(TAG, "NANP type: " + getNumberPlanType(nanpState));
            }
            if (nanpState == 1 || nanpState == 2 || nanpState == 3) {
                return networkPortionNumber;
            }
            if (nanpState != 4) {
                int iddLength = 0;
                if (nanpState == 5) {
                    if (networkType == 1) {
                        return networkPortionNumber;
                    }
                    if (networkType == 0) {
                        if (numberEntry.IDD != null) {
                            iddLength = numberEntry.IDD.length();
                        }
                        return PLUS_SIGN + networkPortionNumber.substring(iddLength);
                    } else if (networkType == 2) {
                        if (numberEntry.IDD != null) {
                            iddLength = numberEntry.IDD.length();
                        }
                        return networkPortionNumber.substring(iddLength);
                    }
                }
                int internationalState = checkInternationalNumberPlan(context, numberEntry, allIDDs, NANP_IDD);
                if (z) {
                    Log.d(TAG, "International type: " + getNumberPlanType(internationalState));
                }
                String returnNumber = null;
                switch (internationalState) {
                    case 100:
                        if (networkType == 0) {
                            returnNumber = networkPortionNumber.substring(1);
                            break;
                        }
                        break;
                    case 101:
                        returnNumber = networkPortionNumber;
                        break;
                    case 102:
                        returnNumber = NANP_IDD + networkPortionNumber.substring(1);
                        break;
                    case 103:
                        if (networkType == 0 || networkType == 2) {
                            if (numberEntry.IDD != null) {
                                iddLength = numberEntry.IDD.length();
                            }
                            returnNumber = NANP_IDD + networkPortionNumber.substring(iddLength);
                            break;
                        }
                    case 104:
                        int countryCode = numberEntry.countryCode;
                        if (!inExceptionListForNpCcAreaLocal(numberEntry) && networkPortionNumber.length() >= 11 && countryCode != 1) {
                            returnNumber = NANP_IDD + networkPortionNumber;
                            break;
                        }
                        break;
                    default:
                        if (networkPortionNumber.startsWith(PLUS_SIGN) && (networkType == 1 || networkType == 2)) {
                            if (!networkPortionNumber.startsWith("+011")) {
                                returnNumber = NANP_IDD + networkPortionNumber.substring(1);
                                break;
                            } else {
                                returnNumber = networkPortionNumber.substring(1);
                                break;
                            }
                        }
                        break;
                }
                if (returnNumber == null) {
                    return networkPortionNumber;
                }
                return returnNumber;
            } else if (networkType == 1 || networkType == 2) {
                return networkPortionNumber.substring(1);
            } else {
                return networkPortionNumber;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
        if (r11 == null) goto L_0x0063;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0063, code lost:
        com.android.internal.telephony.SmsNumberUtils.IDDS_MAPS.put(r13, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006a, code lost:
        if (com.android.internal.telephony.SmsNumberUtils.DBG == false) goto L_0x0088;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
        android.util.Log.d(com.android.internal.telephony.SmsNumberUtils.TAG, "MCC = " + r13 + ", all IDDs = " + r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.ArrayList<java.lang.String> getAllIDDs(android.content.Context r12, java.lang.String r13) {
        /*
            java.lang.String r0 = "SmsNumberUtils"
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r1 = com.android.internal.telephony.SmsNumberUtils.IDDS_MAPS
            java.lang.Object r1 = r1.get(r13)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            if (r1 == 0) goto L_0x000d
            return r1
        L_0x000d:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1 = r2
            java.lang.String r2 = "IDD"
            java.lang.String r3 = "MCC"
            java.lang.String[] r6 = new java.lang.String[]{r2, r3}
            r2 = 0
            r3 = 0
            r10 = 0
            if (r13 == 0) goto L_0x0028
            java.lang.String r2 = "MCC=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r4[r10] = r13
            r3 = r4
        L_0x0028:
            r11 = 0
            android.content.ContentResolver r4 = r12.getContentResolver()     // Catch: all -> 0x0057, SQLException -> 0x0059
            android.net.Uri r5 = com.android.internal.telephony.HbpcdLookup.MccIdd.CONTENT_URI     // Catch: all -> 0x0057, SQLException -> 0x0059
            r9 = 0
            r7 = r2
            r8 = r3
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch: all -> 0x0057, SQLException -> 0x0059
            r11 = r4
            int r4 = r11.getCount()     // Catch: all -> 0x0057, SQLException -> 0x0059
            if (r4 <= 0) goto L_0x0051
        L_0x003d:
            boolean r4 = r11.moveToNext()     // Catch: all -> 0x0057, SQLException -> 0x0059
            if (r4 == 0) goto L_0x0051
            java.lang.String r4 = r11.getString(r10)     // Catch: all -> 0x0057, SQLException -> 0x0059
            boolean r5 = r1.contains(r4)     // Catch: all -> 0x0057, SQLException -> 0x0059
            if (r5 != 0) goto L_0x0050
            r1.add(r4)     // Catch: all -> 0x0057, SQLException -> 0x0059
        L_0x0050:
            goto L_0x003d
        L_0x0051:
            if (r11 == 0) goto L_0x0063
        L_0x0053:
            r11.close()
            goto L_0x0063
        L_0x0057:
            r0 = move-exception
            goto L_0x0089
        L_0x0059:
            r4 = move-exception
            java.lang.String r5 = "Can't access HbpcdLookup database"
            android.util.Log.e(r0, r5, r4)     // Catch: all -> 0x0057
            if (r11 == 0) goto L_0x0063
            goto L_0x0053
        L_0x0063:
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r4 = com.android.internal.telephony.SmsNumberUtils.IDDS_MAPS
            r4.put(r13, r1)
            boolean r4 = com.android.internal.telephony.SmsNumberUtils.DBG
            if (r4 == 0) goto L_0x0088
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "MCC = "
            r4.append(r5)
            r4.append(r13)
            java.lang.String r5 = ", all IDDs = "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r0, r4)
        L_0x0088:
            return r1
        L_0x0089:
            if (r11 == 0) goto L_0x008e
            r11.close()
        L_0x008e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.SmsNumberUtils.getAllIDDs(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    private static int checkNANP(NumberEntry numberEntry, ArrayList<String> allIDDs) {
        String number2;
        boolean isNANP = false;
        String number = numberEntry.number;
        if (number.length() == 7) {
            char firstChar = number.charAt(0);
            if (firstChar >= '2' && firstChar <= '9') {
                isNANP = true;
                int i = 1;
                while (true) {
                    if (i >= 7) {
                        break;
                    }
                    char c = number.charAt(i);
                    if (!PhoneNumberUtils.isISODigit(c)) {
                        isNANP = false;
                        break;
                    }
                    i++;
                }
            }
            if (isNANP) {
                return 1;
            }
        } else if (number.length() == 10) {
            if (isNANP(number)) {
                return 2;
            }
        } else if (number.length() == 11) {
            if (isNANP(number)) {
                return 3;
            }
        } else if (number.startsWith(PLUS_SIGN)) {
            String number3 = number.substring(1);
            if (number3.length() == 11) {
                if (isNANP(number3)) {
                    return 4;
                }
            } else if (number3.startsWith(NANP_IDD) && number3.length() == 14 && isNANP(number3.substring(3))) {
                return 6;
            }
        } else {
            Iterator<String> it = allIDDs.iterator();
            while (it.hasNext()) {
                String idd = it.next();
                if (number.startsWith(idd) && (number2 = number.substring(idd.length())) != null && number2.startsWith(String.valueOf(1)) && isNANP(number2)) {
                    numberEntry.IDD = idd;
                    return 5;
                }
            }
        }
        return 0;
    }

    private static boolean isNANP(String number) {
        if (!(number.length() == 10 || (number.length() == 11 && number.startsWith("1")))) {
            return false;
        }
        if (number.length() == 11) {
            number = number.substring(1);
        }
        if (!isTwoToNine(number.charAt(0)) || !isTwoToNine(number.charAt(3))) {
            return false;
        }
        for (int i = 1; i < 10; i++) {
            char c = number.charAt(i);
            if (!PhoneNumberUtils.isISODigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isTwoToNine(char c) {
        if (c < '2' || c > '9') {
            return false;
        }
        return true;
    }

    private static int checkInternationalNumberPlan(Context context, NumberEntry numberEntry, ArrayList<String> allIDDs, String homeIDD) {
        int countryCode;
        String number = numberEntry.number;
        if (number.startsWith(PLUS_SIGN)) {
            String numberNoNBPCD = number.substring(1);
            if (numberNoNBPCD.startsWith(homeIDD)) {
                String numberCountryAreaLocal = numberNoNBPCD.substring(homeIDD.length());
                int countryCode2 = getCountryCode(context, numberCountryAreaLocal);
                if (countryCode2 <= 0) {
                    return 0;
                }
                numberEntry.countryCode = countryCode2;
                return 100;
            }
            int countryCode3 = getCountryCode(context, numberNoNBPCD);
            if (countryCode3 <= 0) {
                return 0;
            }
            numberEntry.countryCode = countryCode3;
            return 102;
        } else if (number.startsWith(homeIDD)) {
            String numberCountryAreaLocal2 = number.substring(homeIDD.length());
            int countryCode4 = getCountryCode(context, numberCountryAreaLocal2);
            if (countryCode4 <= 0) {
                return 0;
            }
            numberEntry.countryCode = countryCode4;
            return 101;
        } else {
            Iterator<String> it = allIDDs.iterator();
            while (it.hasNext()) {
                String exitCode = it.next();
                if (number.startsWith(exitCode)) {
                    String numberNoIDD = number.substring(exitCode.length());
                    int countryCode5 = getCountryCode(context, numberNoIDD);
                    if (countryCode5 > 0) {
                        numberEntry.countryCode = countryCode5;
                        numberEntry.IDD = exitCode;
                        return 103;
                    }
                }
            }
            if (number.startsWith("0") || (countryCode = getCountryCode(context, number)) <= 0) {
                return 0;
            }
            numberEntry.countryCode = countryCode;
            return 104;
        }
    }

    private static int getCountryCode(Context context, String number) {
        int[] allCCs;
        if (number.length() < 10 || (allCCs = getAllCountryCodes(context)) == null) {
            return -1;
        }
        int[] ccArray = new int[MAX_COUNTRY_CODES_LENGTH];
        for (int i = 0; i < MAX_COUNTRY_CODES_LENGTH; i++) {
            ccArray[i] = Integer.parseInt(number.substring(0, i + 1));
        }
        for (int tempCC : allCCs) {
            for (int j = 0; j < MAX_COUNTRY_CODES_LENGTH; j++) {
                if (tempCC == ccArray[j]) {
                    if (DBG) {
                        Log.d(TAG, "Country code = " + tempCC);
                    }
                    return tempCC;
                }
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
        if (r0 == null) goto L_0x0062;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0064, code lost:
        return com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int[] getAllCountryCodes(android.content.Context r8) {
        /*
            int[] r0 = com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            r0 = 0
            java.lang.String r1 = "Country_Code"
            java.lang.String[] r4 = new java.lang.String[]{r1}     // Catch: all -> 0x0054, SQLException -> 0x0056
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: all -> 0x0054, SQLException -> 0x0056
            android.net.Uri r3 = com.android.internal.telephony.HbpcdLookup.MccLookup.CONTENT_URI     // Catch: all -> 0x0054, SQLException -> 0x0056
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: all -> 0x0054, SQLException -> 0x0056
            r0 = r1
            int r1 = r0.getCount()     // Catch: all -> 0x0054, SQLException -> 0x0056
            if (r1 <= 0) goto L_0x004e
            int r1 = r0.getCount()     // Catch: all -> 0x0054, SQLException -> 0x0056
            int[] r1 = new int[r1]     // Catch: all -> 0x0054, SQLException -> 0x0056
            com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES = r1     // Catch: all -> 0x0054, SQLException -> 0x0056
            r1 = 0
        L_0x0029:
            boolean r2 = r0.moveToNext()     // Catch: all -> 0x0054, SQLException -> 0x0056
            if (r2 == 0) goto L_0x004e
            r2 = 0
            int r2 = r0.getInt(r2)     // Catch: all -> 0x0054, SQLException -> 0x0056
            int[] r3 = com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES     // Catch: all -> 0x0054, SQLException -> 0x0056
            int r5 = r1 + 1
            r3[r1] = r2     // Catch: all -> 0x0054, SQLException -> 0x0056
            java.lang.String r1 = java.lang.String.valueOf(r2)     // Catch: all -> 0x0054, SQLException -> 0x0056
            java.lang.String r1 = r1.trim()     // Catch: all -> 0x0054, SQLException -> 0x0056
            int r1 = r1.length()     // Catch: all -> 0x0054, SQLException -> 0x0056
            int r3 = com.android.internal.telephony.SmsNumberUtils.MAX_COUNTRY_CODES_LENGTH     // Catch: all -> 0x0054, SQLException -> 0x0056
            if (r1 <= r3) goto L_0x004c
            com.android.internal.telephony.SmsNumberUtils.MAX_COUNTRY_CODES_LENGTH = r1     // Catch: all -> 0x0054, SQLException -> 0x0056
        L_0x004c:
            r1 = r5
            goto L_0x0029
        L_0x004e:
            if (r0 == 0) goto L_0x0062
        L_0x0050:
            r0.close()
            goto L_0x0062
        L_0x0054:
            r1 = move-exception
            goto L_0x0065
        L_0x0056:
            r1 = move-exception
            java.lang.String r2 = "SmsNumberUtils"
            java.lang.String r3 = "Can't access HbpcdLookup database"
            android.util.Log.e(r2, r3, r1)     // Catch: all -> 0x0054
            if (r0 == 0) goto L_0x0062
            goto L_0x0050
        L_0x0062:
            int[] r1 = com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES
            return r1
        L_0x0065:
            if (r0 == 0) goto L_0x006a
            r0.close()
        L_0x006a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.SmsNumberUtils.getAllCountryCodes(android.content.Context):int[]");
    }

    private static boolean inExceptionListForNpCcAreaLocal(NumberEntry numberEntry) {
        int countryCode = numberEntry.countryCode;
        return numberEntry.number.length() == 12 && (countryCode == 7 || countryCode == 20 || countryCode == 65 || countryCode == 90);
    }

    private static String getNumberPlanType(int state) {
        String str = "Number Plan type (" + state + "): ";
        if (state == 1) {
            return "NP_NANP_LOCAL";
        }
        if (state == 2) {
            return "NP_NANP_AREA_LOCAL";
        }
        if (state == 3) {
            return "NP_NANP_NDD_AREA_LOCAL";
        }
        if (state == 4) {
            return "NP_NANP_NBPCD_CC_AREA_LOCAL";
        }
        if (state == 5) {
            return "NP_NANP_LOCALIDD_CC_AREA_LOCAL";
        }
        if (state == 6) {
            return "NP_NANP_NBPCD_HOMEIDD_CC_AREA_LOCAL";
        }
        if (state == 100) {
            return "NP_NBPCD_HOMEIDD_CC_AREA_LOCAL";
        }
        if (state == 101) {
            return "NP_HOMEIDD_CC_AREA_LOCAL";
        }
        if (state == 102) {
            return "NP_NBPCD_CC_AREA_LOCAL";
        }
        if (state == 103) {
            return "NP_LOCALIDD_CC_AREA_LOCAL";
        }
        if (state == 104) {
            return "NP_CC_AREA_LOCAL";
        }
        return "Unknown type";
    }

    public static String filterDestAddr(Context context, int subId, String destAddr) {
        int networkType;
        String networkMcc;
        boolean z = DBG;
        if (z) {
            Log.d(TAG, "enter filterDestAddr. destAddr=\"" + pii(TAG, destAddr) + "\"");
        }
        if (destAddr == null || !PhoneNumberUtils.isGlobalPhoneNumber(destAddr)) {
            Log.w(TAG, "destAddr" + pii(TAG, destAddr) + " is not a global phone number! Nothing changed.");
            return destAddr;
        }
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(subId);
        String networkOperator = telephonyManager.getNetworkOperator();
        String result = null;
        if (needToConvert(context, subId) && (networkType = getNetworkType(telephonyManager)) != -1 && !TextUtils.isEmpty(networkOperator) && (networkMcc = networkOperator.substring(0, 3)) != null && networkMcc.trim().length() > 0) {
            result = formatNumber(context, destAddr, networkMcc, networkType);
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("destAddr is ");
            sb.append(result != null ? "formatted." : "not formatted.");
            Log.d(TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("leave filterDestAddr, new destAddr=\"");
            sb2.append(result != null ? pii(TAG, result) : pii(TAG, destAddr));
            sb2.append("\"");
            Log.d(TAG, sb2.toString());
        }
        return result != null ? result : destAddr;
    }

    private static int getNetworkType(TelephonyManager telephonyManager) {
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType == 1) {
            return 0;
        }
        if (phoneType == 2) {
            if (isInternationalRoaming(telephonyManager)) {
                return 2;
            }
            return 1;
        } else if (!DBG) {
            return -1;
        } else {
            Log.w(TAG, "warning! unknown mPhoneType value=" + phoneType);
            return -1;
        }
    }

    private static boolean isInternationalRoaming(TelephonyManager telephonyManager) {
        String operatorIsoCountry = telephonyManager.getNetworkCountryIso();
        String simIsoCountry = telephonyManager.getSimCountryIso();
        boolean internationalRoaming = !TextUtils.isEmpty(operatorIsoCountry) && !TextUtils.isEmpty(simIsoCountry) && !simIsoCountry.equals(operatorIsoCountry);
        if (!internationalRoaming) {
            return internationalRoaming;
        }
        if (XmlTags.ATTR_USER_ID.equals(simIsoCountry)) {
            return true ^ "vi".equals(operatorIsoCountry);
        }
        if ("vi".equals(simIsoCountry)) {
            return true ^ XmlTags.ATTR_USER_ID.equals(operatorIsoCountry);
        }
        return internationalRoaming;
    }

    private static boolean needToConvert(Context context, int subId) {
        PersistableBundle bundle;
        long identity = Binder.clearCallingIdentity();
        try {
            CarrierConfigManager configManager = (CarrierConfigManager) context.getSystemService("carrier_config");
            if (configManager != null && (bundle = configManager.getConfigForSubId(subId)) != null) {
                return bundle.getBoolean(CarrierConfigManager.KEY_SMS_REQUIRES_DESTINATION_NUMBER_CONVERSION_BOOL);
            }
            Binder.restoreCallingIdentity(identity);
            return false;
        } finally {
            Binder.restoreCallingIdentity(identity);
        }
    }

    private static String pii(String tag, Object pii) {
        String val = String.valueOf(pii);
        if (pii == null || TextUtils.isEmpty(val) || Log.isLoggable(tag, 2)) {
            return val;
        }
        return "[" + secureHash(val.getBytes()) + "]";
    }

    private static String secureHash(byte[] input) {
        if (TelephonyUtils.IS_USER) {
            return "****";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KeyProperties.DIGEST_SHA1);
            byte[] result = messageDigest.digest(input);
            return Base64.encodeToString(result, 11);
        } catch (NoSuchAlgorithmException e) {
            return "####";
        }
    }
}
