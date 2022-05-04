package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.mms.ContentType;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.util.PduCache;
import com.google.android.mms.util.PduCacheEntry;
import com.google.android.mms.util.SqliteWrapper;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes4.dex */
public class PduPersister {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final HashMap<Integer, Integer> CHARSET_COLUMN_INDEX_MAP;
    protected static final HashMap<Integer, String> CHARSET_COLUMN_NAME_MAP;
    private static final boolean DEBUG = false;
    private static final HashMap<Integer, Integer> ENCODED_STRING_COLUMN_INDEX_MAP;
    protected static final HashMap<Integer, String> ENCODED_STRING_COLUMN_NAME_MAP;
    protected static final boolean LOCAL_LOGV = false;
    protected static final HashMap<Integer, Integer> LONG_COLUMN_INDEX_MAP;
    protected static final HashMap<Integer, String> LONG_COLUMN_NAME_MAP;
    protected static final HashMap<Uri, Integer> MESSAGE_BOX_MAP;
    private static final HashMap<Integer, Integer> OCTET_COLUMN_INDEX_MAP;
    protected static final HashMap<Integer, String> OCTET_COLUMN_NAME_MAP;
    protected static final int PART_COLUMN_CHARSET = 1;
    protected static final int PART_COLUMN_CONTENT_DISPOSITION = 2;
    protected static final int PART_COLUMN_CONTENT_ID = 3;
    protected static final int PART_COLUMN_CONTENT_LOCATION = 4;
    protected static final int PART_COLUMN_CONTENT_TYPE = 5;
    protected static final int PART_COLUMN_FILENAME = 6;
    protected static final int PART_COLUMN_ID = 0;
    protected static final int PART_COLUMN_NAME = 7;
    protected static final int PART_COLUMN_TEXT = 8;
    private static final int PDU_COLUMN_CONTENT_CLASS = 11;
    private static final int PDU_COLUMN_CONTENT_LOCATION = 5;
    private static final int PDU_COLUMN_CONTENT_TYPE = 6;
    private static final int PDU_COLUMN_DATE = 21;
    private static final int PDU_COLUMN_DELIVERY_REPORT = 12;
    private static final int PDU_COLUMN_DELIVERY_TIME = 22;
    private static final int PDU_COLUMN_EXPIRY = 23;
    private static final int PDU_COLUMN_ID = 0;
    private static final int PDU_COLUMN_MESSAGE_BOX = 1;
    private static final int PDU_COLUMN_MESSAGE_CLASS = 7;
    private static final int PDU_COLUMN_MESSAGE_ID = 8;
    private static final int PDU_COLUMN_MESSAGE_SIZE = 24;
    private static final int PDU_COLUMN_MESSAGE_TYPE = 13;
    private static final int PDU_COLUMN_MMS_VERSION = 14;
    private static final int PDU_COLUMN_PRIORITY = 15;
    private static final int PDU_COLUMN_READ_REPORT = 16;
    private static final int PDU_COLUMN_READ_STATUS = 17;
    private static final int PDU_COLUMN_REPORT_ALLOWED = 18;
    private static final int PDU_COLUMN_RESPONSE_TEXT = 9;
    private static final int PDU_COLUMN_RETRIEVE_STATUS = 19;
    private static final int PDU_COLUMN_RETRIEVE_TEXT = 3;
    private static final int PDU_COLUMN_RETRIEVE_TEXT_CHARSET = 26;
    private static final int PDU_COLUMN_STATUS = 20;
    private static final int PDU_COLUMN_SUBJECT = 4;
    private static final int PDU_COLUMN_SUBJECT_CHARSET = 25;
    private static final int PDU_COLUMN_THREAD_ID = 2;
    private static final int PDU_COLUMN_TRANSACTION_ID = 10;
    private static final long PLACEHOLDER_THREAD_ID = Long.MAX_VALUE;
    public static final int PROC_STATUS_COMPLETED = 3;
    public static final int PROC_STATUS_PERMANENTLY_FAILURE = 2;
    public static final int PROC_STATUS_TRANSIENT_FAILURE = 1;
    private static final String TAG = "PduPersister";
    public static final String TEMPORARY_DRM_OBJECT_URI = "content://mms/9223372036854775807/part";
    private static final HashMap<Integer, Integer> TEXT_STRING_COLUMN_INDEX_MAP;
    protected static final HashMap<Integer, String> TEXT_STRING_COLUMN_NAME_MAP;
    protected static PduPersister sPersister;
    protected final ContentResolver mContentResolver;
    public final Context mContext;
    private final DrmManagerClient mDrmManagerClient;
    protected static final int[] ADDRESS_FIELDS = {129, 130, 137, 151};
    private static final String[] PDU_PROJECTION = {"_id", Telephony.BaseMmsColumns.MESSAGE_BOX, "thread_id", Telephony.BaseMmsColumns.RETRIEVE_TEXT, Telephony.BaseMmsColumns.SUBJECT, Telephony.BaseMmsColumns.CONTENT_LOCATION, Telephony.BaseMmsColumns.CONTENT_TYPE, Telephony.BaseMmsColumns.MESSAGE_CLASS, Telephony.BaseMmsColumns.MESSAGE_ID, Telephony.BaseMmsColumns.RESPONSE_TEXT, Telephony.BaseMmsColumns.TRANSACTION_ID, Telephony.BaseMmsColumns.CONTENT_CLASS, Telephony.BaseMmsColumns.DELIVERY_REPORT, Telephony.BaseMmsColumns.MESSAGE_TYPE, "v", Telephony.BaseMmsColumns.PRIORITY, Telephony.BaseMmsColumns.READ_REPORT, Telephony.BaseMmsColumns.READ_STATUS, Telephony.BaseMmsColumns.REPORT_ALLOWED, Telephony.BaseMmsColumns.RETRIEVE_STATUS, Telephony.BaseMmsColumns.STATUS, "date", Telephony.BaseMmsColumns.DELIVERY_TIME, Telephony.BaseMmsColumns.EXPIRY, Telephony.BaseMmsColumns.MESSAGE_SIZE, Telephony.BaseMmsColumns.SUBJECT_CHARSET, Telephony.BaseMmsColumns.RETRIEVE_TEXT_CHARSET};
    protected static final String[] PART_PROJECTION = {"_id", Telephony.Mms.Part.CHARSET, Telephony.Mms.Part.CONTENT_DISPOSITION, "cid", Telephony.Mms.Part.CONTENT_LOCATION, "ct", Telephony.Mms.Part.FILENAME, "name", "text"};
    protected static final PduCache PDU_CACHE_INSTANCE = PduCache.getInstance();

    static {
        HashMap<Uri, Integer> hashMap = new HashMap<>();
        MESSAGE_BOX_MAP = hashMap;
        hashMap.put(Telephony.Mms.Inbox.CONTENT_URI, 1);
        hashMap.put(Telephony.Mms.Sent.CONTENT_URI, 2);
        hashMap.put(Telephony.Mms.Draft.CONTENT_URI, 3);
        hashMap.put(Telephony.Mms.Outbox.CONTENT_URI, 4);
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        CHARSET_COLUMN_INDEX_MAP = hashMap2;
        hashMap2.put(150, 25);
        hashMap2.put(154, 26);
        HashMap<Integer, String> hashMap3 = new HashMap<>();
        CHARSET_COLUMN_NAME_MAP = hashMap3;
        hashMap3.put(150, Telephony.BaseMmsColumns.SUBJECT_CHARSET);
        hashMap3.put(154, Telephony.BaseMmsColumns.RETRIEVE_TEXT_CHARSET);
        HashMap<Integer, Integer> hashMap4 = new HashMap<>();
        ENCODED_STRING_COLUMN_INDEX_MAP = hashMap4;
        hashMap4.put(154, 3);
        hashMap4.put(150, 4);
        HashMap<Integer, String> hashMap5 = new HashMap<>();
        ENCODED_STRING_COLUMN_NAME_MAP = hashMap5;
        hashMap5.put(154, Telephony.BaseMmsColumns.RETRIEVE_TEXT);
        hashMap5.put(150, Telephony.BaseMmsColumns.SUBJECT);
        HashMap<Integer, Integer> hashMap6 = new HashMap<>();
        TEXT_STRING_COLUMN_INDEX_MAP = hashMap6;
        hashMap6.put(131, 5);
        hashMap6.put(132, 6);
        hashMap6.put(138, 7);
        hashMap6.put(139, 8);
        hashMap6.put(147, 9);
        hashMap6.put(152, 10);
        HashMap<Integer, String> hashMap7 = new HashMap<>();
        TEXT_STRING_COLUMN_NAME_MAP = hashMap7;
        hashMap7.put(131, Telephony.BaseMmsColumns.CONTENT_LOCATION);
        hashMap7.put(132, Telephony.BaseMmsColumns.CONTENT_TYPE);
        hashMap7.put(138, Telephony.BaseMmsColumns.MESSAGE_CLASS);
        hashMap7.put(139, Telephony.BaseMmsColumns.MESSAGE_ID);
        hashMap7.put(147, Telephony.BaseMmsColumns.RESPONSE_TEXT);
        hashMap7.put(152, Telephony.BaseMmsColumns.TRANSACTION_ID);
        HashMap<Integer, Integer> hashMap8 = new HashMap<>();
        OCTET_COLUMN_INDEX_MAP = hashMap8;
        hashMap8.put(186, 11);
        hashMap8.put(134, 12);
        hashMap8.put(140, 13);
        hashMap8.put(141, 14);
        hashMap8.put(143, 15);
        hashMap8.put(144, 16);
        hashMap8.put(155, 17);
        hashMap8.put(145, 18);
        hashMap8.put(153, 19);
        hashMap8.put(149, 20);
        HashMap<Integer, String> hashMap9 = new HashMap<>();
        OCTET_COLUMN_NAME_MAP = hashMap9;
        hashMap9.put(186, Telephony.BaseMmsColumns.CONTENT_CLASS);
        hashMap9.put(134, Telephony.BaseMmsColumns.DELIVERY_REPORT);
        hashMap9.put(140, Telephony.BaseMmsColumns.MESSAGE_TYPE);
        hashMap9.put(141, "v");
        hashMap9.put(143, Telephony.BaseMmsColumns.PRIORITY);
        hashMap9.put(144, Telephony.BaseMmsColumns.READ_REPORT);
        hashMap9.put(155, Telephony.BaseMmsColumns.READ_STATUS);
        hashMap9.put(145, Telephony.BaseMmsColumns.REPORT_ALLOWED);
        hashMap9.put(153, Telephony.BaseMmsColumns.RETRIEVE_STATUS);
        hashMap9.put(149, Telephony.BaseMmsColumns.STATUS);
        HashMap<Integer, Integer> hashMap10 = new HashMap<>();
        LONG_COLUMN_INDEX_MAP = hashMap10;
        hashMap10.put(133, 21);
        hashMap10.put(135, 22);
        hashMap10.put(136, 23);
        hashMap10.put(142, 24);
        HashMap<Integer, String> hashMap11 = new HashMap<>();
        LONG_COLUMN_NAME_MAP = hashMap11;
        hashMap11.put(133, "date");
        hashMap11.put(135, Telephony.BaseMmsColumns.DELIVERY_TIME);
        hashMap11.put(136, Telephony.BaseMmsColumns.EXPIRY);
        hashMap11.put(142, Telephony.BaseMmsColumns.MESSAGE_SIZE);
    }

    private PduPersister(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mDrmManagerClient = new DrmManagerClient(context);
    }

    public static PduPersister getPduPersister(Context context) {
        PduPersister pduPersister = sPersister;
        if (pduPersister == null) {
            sPersister = new PduPersister(context);
        } else if (!context.equals(pduPersister.mContext)) {
            sPersister.release();
            sPersister = new PduPersister(context);
        }
        return sPersister;
    }

    private void setEncodedStringValueToHeaders(Cursor c, int columnIndex, PduHeaders headers, int mapColumn) {
        String s = c.getString(columnIndex);
        if (s != null && s.length() > 0) {
            int charsetColumnIndex = CHARSET_COLUMN_INDEX_MAP.get(Integer.valueOf(mapColumn)).intValue();
            int charset = c.getInt(charsetColumnIndex);
            EncodedStringValue value = new EncodedStringValue(charset, getBytes(s));
            headers.setEncodedStringValue(value, mapColumn);
        }
    }

    private void setTextStringToHeaders(Cursor c, int columnIndex, PduHeaders headers, int mapColumn) {
        String s = c.getString(columnIndex);
        if (s != null) {
            headers.setTextString(getBytes(s), mapColumn);
        }
    }

    private void setOctetToHeaders(Cursor c, int columnIndex, PduHeaders headers, int mapColumn) throws InvalidHeaderValueException {
        if (!c.isNull(columnIndex)) {
            int b = c.getInt(columnIndex);
            headers.setOctet(b, mapColumn);
        }
    }

    private void setLongToHeaders(Cursor c, int columnIndex, PduHeaders headers, int mapColumn) {
        if (!c.isNull(columnIndex)) {
            long l = c.getLong(columnIndex);
            headers.setLongInteger(l, mapColumn);
        }
    }

    protected Integer getIntegerFromPartColumn(Cursor c, int columnIndex) {
        if (!c.isNull(columnIndex)) {
            return Integer.valueOf(c.getInt(columnIndex));
        }
        return null;
    }

    protected byte[] getByteArrayFromPartColumn(Cursor c, int columnIndex) {
        if (!c.isNull(columnIndex)) {
            return getBytes(c.getString(columnIndex));
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x019e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.google.android.mms.pdu.PduPart[] loadParts(long r28) throws com.google.android.mms.MmsException {
        /*
            Method dump skipped, instructions count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.mms.pdu.PduPersister.loadParts(long):com.google.android.mms.pdu.PduPart[]");
    }

    private void loadAddress(long msgId, PduHeaders headers) {
        Context context = this.mContext;
        ContentResolver contentResolver = this.mContentResolver;
        Cursor c = SqliteWrapper.query(context, contentResolver, Uri.parse("content://mms/" + msgId + "/addr"), new String[]{"address", Telephony.Mms.Addr.CHARSET, "type"}, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    String addr = c.getString(0);
                    if (!TextUtils.isEmpty(addr)) {
                        int addrType = c.getInt(2);
                        switch (addrType) {
                            case 129:
                            case 130:
                            case 151:
                                headers.appendEncodedStringValue(new EncodedStringValue(c.getInt(1), getBytes(addr)), addrType);
                                continue;
                            case 137:
                                headers.setEncodedStringValue(new EncodedStringValue(c.getInt(1), getBytes(addr)), addrType);
                                continue;
                            default:
                                Log.e(TAG, "Unknown address type: " + addrType);
                                continue;
                        }
                    }
                } finally {
                    c.close();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x01e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018a A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x018e A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0195 A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019c A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a3 A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01aa A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b1 A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b8 A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01bf A[Catch: all -> 0x023d, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01da A[Catch: all -> 0x023d, TRY_LEAVE, TryCatch #6 {all -> 0x023d, blocks: (B:28:0x0053, B:51:0x0149, B:54:0x0152, B:60:0x016c, B:62:0x0172, B:64:0x017b, B:66:0x0187, B:67:0x018a, B:68:0x018e, B:69:0x0195, B:70:0x019c, B:71:0x01a3, B:72:0x01aa, B:73:0x01b1, B:74:0x01b8, B:75:0x01bf, B:76:0x01d9, B:77:0x01da, B:87:0x01fb, B:88:0x0213, B:89:0x0214, B:90:0x021d, B:30:0x0071, B:32:0x0077, B:34:0x007d, B:35:0x0092, B:37:0x0098, B:38:0x00b7, B:39:0x00c2, B:41:0x00c8, B:42:0x00e7, B:43:0x00f2, B:45:0x00f8, B:46:0x0117, B:47:0x0122, B:49:0x0128, B:91:0x021e, B:92:0x0234), top: B:120:0x0053 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.mms.pdu.GenericPdu load(android.net.Uri r19) throws com.google.android.mms.MmsException {
        /*
            Method dump skipped, instructions count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.mms.pdu.PduPersister.load(android.net.Uri):com.google.android.mms.pdu.GenericPdu");
    }

    protected void persistAddress(long msgId, int type, EncodedStringValue[] array) {
        ContentValues values = new ContentValues(3);
        for (EncodedStringValue addr : array) {
            values.clear();
            values.put("address", toIsoString(addr.getTextString()));
            values.put(Telephony.Mms.Addr.CHARSET, Integer.valueOf(addr.getCharacterSet()));
            values.put("type", Integer.valueOf(type));
            Uri uri = Uri.parse("content://mms/" + msgId + "/addr");
            SqliteWrapper.insert(this.mContext, this.mContentResolver, uri, values);
        }
    }

    protected static String getPartContentType(PduPart part) {
        if (part.getContentType() == null) {
            return null;
        }
        return toIsoString(part.getContentType());
    }

    public Uri persistPart(PduPart part, long msgId, HashMap<Uri, InputStream> preOpenedFiles) throws MmsException {
        Uri uri = Uri.parse("content://mms/" + msgId + "/part");
        ContentValues values = new ContentValues(8);
        int charset = part.getCharset();
        if (charset != 0) {
            values.put(Telephony.Mms.Part.CHARSET, Integer.valueOf(charset));
        }
        String contentType = getPartContentType(part);
        if (contentType != null) {
            if (ContentType.IMAGE_JPG.equals(contentType)) {
                contentType = ContentType.IMAGE_JPEG;
            }
            values.put("ct", contentType);
            if (ContentType.APP_SMIL.equals(contentType)) {
                values.put("seq", (Integer) (-1));
            }
            if (part.getFilename() != null) {
                String fileName = new String(part.getFilename());
                values.put(Telephony.Mms.Part.FILENAME, fileName);
            }
            if (part.getName() != null) {
                String name = new String(part.getName());
                values.put("name", name);
            }
            if (part.getContentDisposition() != null) {
                Object value = toIsoString(part.getContentDisposition());
                values.put(Telephony.Mms.Part.CONTENT_DISPOSITION, (String) value);
            }
            if (part.getContentId() != null) {
                Object value2 = toIsoString(part.getContentId());
                values.put("cid", (String) value2);
            }
            if (part.getContentLocation() != null) {
                Object value3 = toIsoString(part.getContentLocation());
                values.put(Telephony.Mms.Part.CONTENT_LOCATION, (String) value3);
            }
            Uri res = SqliteWrapper.insert(this.mContext, this.mContentResolver, uri, values);
            if (res != null) {
                persistData(part, res, contentType, preOpenedFiles);
                part.setDataUri(res);
                return res;
            }
            throw new MmsException("Failed to persist part, return null.");
        }
        throw new MmsException("MIME type of the part must be set.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:94:0x01b4, code lost:
        r17 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01bd, code lost:
        throw new com.google.android.mms.MmsException("Error converting drm data.");
     */
    /* JADX WARN: Not initialized variable reg: 16, insn: 0x0354: MOVE  (r11 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY] A[D('dataUri' android.net.Uri)]), block:B:161:0x0353 */
    /* JADX WARN: Not initialized variable reg: 16, insn: 0x035a: MOVE  (r11 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY] A[D('dataUri' android.net.Uri)]), block:B:163:0x035a */
    /* JADX WARN: Not initialized variable reg: 16, insn: 0x0360: MOVE  (r11 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY] A[D('dataUri' android.net.Uri)]), block:B:165:0x0360 */
    /* JADX WARN: Not initialized variable reg: 17, insn: 0x0356: MOVE  (r8 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY] A[D('os' java.io.OutputStream)]), block:B:161:0x0353 */
    /* JADX WARN: Not initialized variable reg: 17, insn: 0x035c: MOVE  (r8 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY] A[D('os' java.io.OutputStream)]), block:B:163:0x035a */
    /* JADX WARN: Not initialized variable reg: 17, insn: 0x0362: MOVE  (r8 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY] A[D('os' java.io.OutputStream)]), block:B:165:0x0360 */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x03ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0390 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:234:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v12 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void persistData(com.google.android.mms.pdu.PduPart r24, android.net.Uri r25, java.lang.String r26, java.util.HashMap<android.net.Uri, java.io.InputStream> r27) throws com.google.android.mms.MmsException {
        /*
            Method dump skipped, instructions count: 1021
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.mms.pdu.PduPersister.persistData(com.google.android.mms.pdu.PduPart, android.net.Uri, java.lang.String, java.util.HashMap):void");
    }

    private void updateAddress(long msgId, int type, EncodedStringValue[] array) {
        Context context = this.mContext;
        ContentResolver contentResolver = this.mContentResolver;
        Uri parse = Uri.parse("content://mms/" + msgId + "/addr");
        StringBuilder sb = new StringBuilder();
        sb.append("type=");
        sb.append(type);
        SqliteWrapper.delete(context, contentResolver, parse, sb.toString(), null);
        persistAddress(msgId, type, array);
    }

    public void updateHeaders(Uri uri, SendReq sendReq) {
        EncodedStringValue[] array;
        PduHeaders headers;
        int addrType;
        EncodedStringValue[] array2;
        PduCache pduCache = PDU_CACHE_INSTANCE;
        synchronized (pduCache) {
            if (pduCache.isUpdating(uri)) {
                try {
                    pduCache.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "updateHeaders: ", e);
                }
            }
        }
        PDU_CACHE_INSTANCE.purge(uri);
        ContentValues values = new ContentValues(10);
        byte[] contentType = sendReq.getContentType();
        if (contentType != null) {
            values.put(Telephony.BaseMmsColumns.CONTENT_TYPE, toIsoString(contentType));
        }
        long date = sendReq.getDate();
        if (date != -1) {
            values.put("date", Long.valueOf(date));
        }
        int deliveryReport = sendReq.getDeliveryReport();
        if (deliveryReport != 0) {
            values.put(Telephony.BaseMmsColumns.DELIVERY_REPORT, Integer.valueOf(deliveryReport));
        }
        long expiry = sendReq.getExpiry();
        if (expiry != -1) {
            values.put(Telephony.BaseMmsColumns.EXPIRY, Long.valueOf(expiry));
        }
        byte[] msgClass = sendReq.getMessageClass();
        if (msgClass != null) {
            values.put(Telephony.BaseMmsColumns.MESSAGE_CLASS, toIsoString(msgClass));
        }
        int priority = sendReq.getPriority();
        if (priority != 0) {
            values.put(Telephony.BaseMmsColumns.PRIORITY, Integer.valueOf(priority));
        }
        int readReport = sendReq.getReadReport();
        if (readReport != 0) {
            values.put(Telephony.BaseMmsColumns.READ_REPORT, Integer.valueOf(readReport));
        }
        byte[] transId = sendReq.getTransactionId();
        if (transId != null) {
            values.put(Telephony.BaseMmsColumns.TRANSACTION_ID, toIsoString(transId));
        }
        EncodedStringValue subject = sendReq.getSubject();
        if (subject != null) {
            values.put(Telephony.BaseMmsColumns.SUBJECT, toIsoString(subject.getTextString()));
            values.put(Telephony.BaseMmsColumns.SUBJECT_CHARSET, Integer.valueOf(subject.getCharacterSet()));
        } else {
            values.put(Telephony.BaseMmsColumns.SUBJECT, "");
        }
        long messageSize = sendReq.getMessageSize();
        if (messageSize > 0) {
            values.put(Telephony.BaseMmsColumns.MESSAGE_SIZE, Long.valueOf(messageSize));
        }
        PduHeaders headers2 = sendReq.getPduHeaders();
        HashSet<String> recipients = new HashSet<>();
        int[] iArr = ADDRESS_FIELDS;
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int addrType2 = iArr[i];
            EncodedStringValue[] array3 = null;
            if (addrType2 == 137) {
                EncodedStringValue v = headers2.getEncodedStringValue(addrType2);
                if (v != null) {
                    length = length;
                    addrType = 0;
                    EncodedStringValue[] array4 = {v};
                    array3 = array4;
                } else {
                    length = length;
                    addrType = 0;
                }
                array = array3;
            } else {
                length = length;
                addrType = 0;
                EncodedStringValue[] array5 = headers2.getEncodedStringValues(addrType2);
                array = array5;
            }
            if (array != null) {
                headers = headers2;
                long msgId = ContentUris.parseId(uri);
                updateAddress(msgId, addrType2, array);
                if (addrType2 == 151) {
                    int length2 = array.length;
                    while (addrType < length2) {
                        EncodedStringValue v2 = array[addrType];
                        if (v2 != null) {
                            array2 = array;
                            recipients.add(v2.getString());
                        } else {
                            array2 = array;
                        }
                        addrType++;
                        array = array2;
                    }
                }
            } else {
                headers = headers2;
            }
            i++;
            headers2 = headers;
            iArr = iArr;
        }
        if (!recipients.isEmpty()) {
            long threadId = Telephony.Threads.getOrCreateThreadId(this.mContext, recipients);
            values.put("thread_id", Long.valueOf(threadId));
        }
        SqliteWrapper.update(this.mContext, this.mContentResolver, uri, values, null, null);
    }

    private void updatePart(Uri uri, PduPart part, HashMap<Uri, InputStream> preOpenedFiles) throws MmsException {
        ContentValues values = new ContentValues(7);
        int charset = part.getCharset();
        if (charset != 0) {
            values.put(Telephony.Mms.Part.CHARSET, Integer.valueOf(charset));
        }
        if (part.getContentType() != null) {
            String contentType = toIsoString(part.getContentType());
            values.put("ct", contentType);
            if (part.getFilename() != null) {
                String fileName = new String(part.getFilename());
                values.put(Telephony.Mms.Part.FILENAME, fileName);
            }
            if (part.getName() != null) {
                String name = new String(part.getName());
                values.put("name", name);
            }
            Object value = null;
            if (part.getContentDisposition() != null) {
                value = toIsoString(part.getContentDisposition());
                values.put(Telephony.Mms.Part.CONTENT_DISPOSITION, (String) value);
            }
            if (part.getContentId() != null) {
                value = toIsoString(part.getContentId());
                values.put("cid", (String) value);
            }
            if (part.getContentLocation() != null) {
                Object value2 = toIsoString(part.getContentLocation());
                values.put(Telephony.Mms.Part.CONTENT_LOCATION, (String) value2);
            }
            SqliteWrapper.update(this.mContext, this.mContentResolver, uri, values, null, null);
            if (part.getData() != null || !uri.equals(part.getDataUri())) {
                persistData(part, uri, contentType, preOpenedFiles);
                return;
            }
            return;
        }
        throw new MmsException("MIME type of the part must be set.");
    }

    public void updateParts(Uri uri, PduBody body, HashMap<Uri, InputStream> preOpenedFiles) throws MmsException {
        try {
            PduCache pduCache = PDU_CACHE_INSTANCE;
            synchronized (pduCache) {
                if (pduCache.isUpdating(uri)) {
                    try {
                        pduCache.wait();
                    } catch (InterruptedException e) {
                        Log.e(TAG, "updateParts: ", e);
                    }
                    PduCacheEntry cacheEntry = PDU_CACHE_INSTANCE.get(uri);
                    if (cacheEntry != null) {
                        ((MultimediaMessagePdu) cacheEntry.getPdu()).setBody(body);
                    }
                }
                PDU_CACHE_INSTANCE.setUpdating(uri, true);
            }
            ArrayList<PduPart> toBeCreated = new ArrayList<>();
            HashMap<Uri, PduPart> toBeUpdated = new HashMap<>();
            int partsNum = body.getPartsNum();
            StringBuilder filter = new StringBuilder();
            filter.append('(');
            for (int i = 0; i < partsNum; i++) {
                PduPart part = body.getPart(i);
                Uri partUri = part.getDataUri();
                if (partUri != null && !TextUtils.isEmpty(partUri.getAuthority()) && partUri.getAuthority().startsWith("mms")) {
                    toBeUpdated.put(partUri, part);
                    if (filter.length() > 1) {
                        filter.append(" AND ");
                    }
                    filter.append("_id");
                    filter.append("!=");
                    DatabaseUtils.appendEscapedSQLString(filter, partUri.getLastPathSegment());
                }
                toBeCreated.add(part);
            }
            filter.append(')');
            long msgId = ContentUris.parseId(uri);
            Context context = this.mContext;
            ContentResolver contentResolver = this.mContentResolver;
            SqliteWrapper.delete(context, contentResolver, Uri.parse(Telephony.Mms.CONTENT_URI + "/" + msgId + "/part"), filter.length() > 2 ? filter.toString() : null, null);
            Iterator<PduPart> it = toBeCreated.iterator();
            while (it.hasNext()) {
                persistPart(it.next(), msgId, preOpenedFiles);
            }
            for (Map.Entry<Uri, PduPart> e2 : toBeUpdated.entrySet()) {
                updatePart(e2.getKey(), e2.getValue(), preOpenedFiles);
            }
            PduCache pduCache2 = PDU_CACHE_INSTANCE;
            synchronized (pduCache2) {
                pduCache2.setUpdating(uri, false);
                pduCache2.notifyAll();
            }
        } catch (Throwable th) {
            PduCache pduCache3 = PDU_CACHE_INSTANCE;
            synchronized (pduCache3) {
                pduCache3.setUpdating(uri, false);
                pduCache3.notifyAll();
                throw th;
            }
        }
    }

    public Uri persist(GenericPdu pdu, Uri uri, boolean createThreadId, boolean groupMmsEnabled, HashMap<Uri, InputStream> preOpenedFiles) throws MmsException {
        long msgId;
        Throwable th;
        int messageSize;
        int i;
        long placeholderId;
        Uri uri2;
        long msgId2;
        Uri res;
        int i2;
        if (uri != null) {
            try {
                msgId = ContentUris.parseId(uri);
            } catch (NumberFormatException e) {
                msgId = -1;
            }
            boolean existingUri = msgId != -1;
            if (existingUri || MESSAGE_BOX_MAP.get(uri) != null) {
                PduCache pduCache = PDU_CACHE_INSTANCE;
                synchronized (pduCache) {
                    try {
                        if (pduCache.isUpdating(uri)) {
                            try {
                                try {
                                    pduCache.wait();
                                } catch (InterruptedException e2) {
                                    Log.e(TAG, "persist1: ", e2);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                while (true) {
                                    try {
                                        break;
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                }
                                throw th;
                            }
                        }
                        PDU_CACHE_INSTANCE.purge(uri);
                        PduHeaders header = pdu.getPduHeaders();
                        ContentValues values = new ContentValues();
                        Set<Map.Entry<Integer, String>> set = ENCODED_STRING_COLUMN_NAME_MAP.entrySet();
                        for (Map.Entry<Integer, String> e3 : set) {
                            int field = e3.getKey().intValue();
                            EncodedStringValue encodedString = header.getEncodedStringValue(field);
                            if (encodedString != null) {
                                String charsetColumn = CHARSET_COLUMN_NAME_MAP.get(Integer.valueOf(field));
                                values.put(e3.getValue(), toIsoString(encodedString.getTextString()));
                                values.put(charsetColumn, Integer.valueOf(encodedString.getCharacterSet()));
                            }
                        }
                        Set<Map.Entry<Integer, String>> set2 = TEXT_STRING_COLUMN_NAME_MAP.entrySet();
                        for (Map.Entry<Integer, String> e4 : set2) {
                            byte[] text = header.getTextString(e4.getKey().intValue());
                            if (text != null) {
                                values.put(e4.getValue(), toIsoString(text));
                            }
                        }
                        Set<Map.Entry<Integer, String>> set3 = OCTET_COLUMN_NAME_MAP.entrySet();
                        for (Map.Entry<Integer, String> e5 : set3) {
                            int b = header.getOctet(e5.getKey().intValue());
                            if (b != 0) {
                                values.put(e5.getValue(), Integer.valueOf(b));
                            }
                        }
                        Set<Map.Entry<Integer, String>> set4 = LONG_COLUMN_NAME_MAP.entrySet();
                        for (Map.Entry<Integer, String> e6 : set4) {
                            long l = header.getLongInteger(e6.getKey().intValue());
                            if (l != -1) {
                                values.put(e6.getValue(), Long.valueOf(l));
                            }
                        }
                        int[] iArr = ADDRESS_FIELDS;
                        HashMap<Integer, EncodedStringValue[]> addressMap = new HashMap<>(iArr.length);
                        int length = iArr.length;
                        for (int i3 = 0; i3 < length; i3++) {
                            int addrType = iArr[i3];
                            EncodedStringValue[] array = null;
                            if (addrType == 137) {
                                EncodedStringValue v = header.getEncodedStringValue(addrType);
                                if (v != null) {
                                    length = length;
                                    iArr = iArr;
                                    array = new EncodedStringValue[]{v};
                                } else {
                                    length = length;
                                    iArr = iArr;
                                }
                            } else {
                                length = length;
                                iArr = iArr;
                                array = header.getEncodedStringValues(addrType);
                            }
                            addressMap.put(Integer.valueOf(addrType), array);
                        }
                        HashSet<String> recipients = new HashSet<>();
                        int msgType = pdu.getMessageType();
                        if (msgType == 130 || msgType == 132 || msgType == 128) {
                            switch (msgType) {
                                case 128:
                                    loadRecipients(151, recipients, addressMap, false);
                                    break;
                                case 130:
                                case 132:
                                    loadRecipients(137, recipients, addressMap, false);
                                    if (groupMmsEnabled) {
                                        loadRecipients(151, recipients, addressMap, true);
                                        loadRecipients(130, recipients, addressMap, true);
                                        break;
                                    }
                                    break;
                            }
                            long threadId = 0;
                            if (createThreadId && !recipients.isEmpty()) {
                                threadId = Telephony.Threads.getOrCreateThreadId(this.mContext, recipients);
                            }
                            values.put("thread_id", Long.valueOf(threadId));
                        }
                        long placeholderId2 = System.currentTimeMillis();
                        int i4 = 1;
                        int messageSize2 = 0;
                        if (pdu instanceof MultimediaMessagePdu) {
                            PduBody body = ((MultimediaMessagePdu) pdu).getBody();
                            if (body != null) {
                                int partsNum = body.getPartsNum();
                                if (partsNum > 2) {
                                    i4 = 0;
                                }
                                int i5 = 0;
                                while (i5 < partsNum) {
                                    PduPart part = body.getPart(i5);
                                    int messageSize3 = messageSize2 + part.getDataLength();
                                    persistPart(part, placeholderId2, preOpenedFiles);
                                    String contentType = getPartContentType(part);
                                    if (contentType != null && !ContentType.APP_SMIL.equals(contentType) && !"text/plain".equals(contentType)) {
                                        i4 = 0;
                                    }
                                    i5++;
                                    messageSize2 = messageSize3;
                                    partsNum = partsNum;
                                }
                                messageSize = messageSize2;
                                i = i4;
                            } else {
                                messageSize = 0;
                                i = 1;
                            }
                        } else {
                            messageSize = 0;
                            i = 1;
                        }
                        values.put(Telephony.BaseMmsColumns.TEXT_ONLY, Integer.valueOf(i));
                        if (values.getAsInteger(Telephony.BaseMmsColumns.MESSAGE_SIZE) == null) {
                            values.put(Telephony.BaseMmsColumns.MESSAGE_SIZE, Integer.valueOf(messageSize));
                        }
                        if (existingUri) {
                            i2 = 0;
                            placeholderId = placeholderId2;
                            SqliteWrapper.update(this.mContext, this.mContentResolver, uri, values, null, null);
                            uri2 = uri;
                            res = uri;
                            msgId2 = msgId;
                        } else {
                            i2 = 0;
                            placeholderId = placeholderId2;
                            uri2 = uri;
                            res = SqliteWrapper.insert(this.mContext, this.mContentResolver, uri2, values);
                            if (res != null) {
                                msgId2 = ContentUris.parseId(res);
                            } else {
                                throw new MmsException("persist() failed: return null.");
                            }
                        }
                        ContentValues values2 = new ContentValues(1);
                        values2.put(Telephony.Mms.Part.MSG_ID, Long.valueOf(msgId2));
                        SqliteWrapper.update(this.mContext, this.mContentResolver, Uri.parse("content://mms/" + placeholderId + "/part"), values2, null, null);
                        if (!existingUri) {
                            res = Uri.parse(uri2 + "/" + msgId2);
                        }
                        int[] iArr2 = ADDRESS_FIELDS;
                        int length2 = iArr2.length;
                        while (i2 < length2) {
                            int addrType2 = iArr2[i2];
                            EncodedStringValue[] array2 = addressMap.get(Integer.valueOf(addrType2));
                            if (array2 != null) {
                                persistAddress(msgId2, addrType2, array2);
                            }
                            i2++;
                            iArr2 = iArr2;
                        }
                        return res;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } else {
                throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
            }
        } else {
            throw new MmsException("Uri may not be null.");
        }
    }

    protected void loadRecipients(int addressType, HashSet<String> recipients, HashMap<Integer, EncodedStringValue[]> addressMap, boolean excludeMyNumber) {
        EncodedStringValue[] array = addressMap.get(Integer.valueOf(addressType));
        if (array != null) {
            if (!excludeMyNumber || array.length != 1) {
                SubscriptionManager subscriptionManager = SubscriptionManager.from(this.mContext);
                Set<String> myPhoneNumbers = new HashSet<>();
                if (excludeMyNumber) {
                    for (SubscriptionInfo subInfo : subscriptionManager.getActiveSubscriptionInfoList()) {
                        String myNumber = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).createForSubscriptionId(subInfo.getSubscriptionId()).getLine1Number();
                        if (myNumber != null) {
                            myPhoneNumbers.add(myNumber);
                        }
                    }
                }
                for (EncodedStringValue v : array) {
                    if (v != null) {
                        String number = v.getString();
                        if (excludeMyNumber) {
                            Iterator<String> it = myPhoneNumbers.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                if (!(PhoneNumberUtils.compare(number, it.next()) || recipients.contains(number))) {
                                    recipients.add(number);
                                    break;
                                }
                            }
                        } else if (!recipients.contains(number)) {
                            recipients.add(number);
                        }
                    }
                }
            }
        }
    }

    public Uri move(Uri from, Uri to) throws MmsException {
        long msgId = ContentUris.parseId(from);
        if (msgId != -1) {
            Integer msgBox = MESSAGE_BOX_MAP.get(to);
            if (msgBox != null) {
                ContentValues values = new ContentValues(1);
                values.put(Telephony.BaseMmsColumns.MESSAGE_BOX, msgBox);
                SqliteWrapper.update(this.mContext, this.mContentResolver, from, values, null, null);
                return ContentUris.withAppendedId(to, msgId);
            }
            throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
        }
        throw new MmsException("Error! ID of the message: -1.");
    }

    public static String toIsoString(byte[] bytes) {
        try {
            return new String(bytes, CharacterSets.MIMENAME_ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "ISO_8859_1 must be supported!", e);
            return "";
        }
    }

    public static byte[] getBytes(String data) {
        try {
            return data.getBytes(CharacterSets.MIMENAME_ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "ISO_8859_1 must be supported!", e);
            return new byte[0];
        }
    }

    public void release() {
        Uri uri = Uri.parse(TEMPORARY_DRM_OBJECT_URI);
        SqliteWrapper.delete(this.mContext, this.mContentResolver, uri, null, null);
        this.mDrmManagerClient.release();
    }

    public Cursor getPendingMessages(long dueTime) {
        Uri.Builder uriBuilder = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
        uriBuilder.appendQueryParameter("protocol", "mms");
        String[] selectionArgs = {String.valueOf(10), String.valueOf(dueTime)};
        return SqliteWrapper.query(this.mContext, this.mContentResolver, uriBuilder.build(), null, "err_type < ? AND due_time <= ?", selectionArgs, Telephony.MmsSms.PendingMessages.DUE_TIME);
    }
}
