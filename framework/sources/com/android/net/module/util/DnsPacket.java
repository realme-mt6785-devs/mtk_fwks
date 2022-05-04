package com.android.net.module.util;

import android.media.MediaMetrics;
import android.text.TextUtils;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public abstract class DnsPacket {
    public static final int ANSECTION = 1;
    public static final int ARSECTION = 3;
    public static final int NSSECTION = 2;
    private static final int NUM_SECTIONS = 4;
    public static final int QDSECTION = 0;
    private static final String TAG = DnsPacket.class.getSimpleName();
    protected final DnsHeader mHeader;
    protected final List<DnsRecord>[] mRecords;

    /* loaded from: classes4.dex */
    public static class ParseException extends RuntimeException {
        public String reason;

        public ParseException(String reason) {
            super(reason);
            this.reason = reason;
        }

        public ParseException(String reason, Throwable cause) {
            super(reason, cause);
            this.reason = reason;
        }
    }

    /* loaded from: classes4.dex */
    public class DnsHeader {
        private static final String TAG = "DnsHeader";
        public final int flags;
        public final int id;
        private final int[] mRecordCount = new int[4];
        public final int rcode;

        DnsHeader(ByteBuffer buf) throws BufferUnderflowException {
            this.id = Short.toUnsignedInt(buf.getShort());
            int unsignedInt = Short.toUnsignedInt(buf.getShort());
            this.flags = unsignedInt;
            this.rcode = unsignedInt & 15;
            for (int i = 0; i < 4; i++) {
                this.mRecordCount[i] = Short.toUnsignedInt(buf.getShort());
            }
        }

        public int getRecordCount(int type) {
            return this.mRecordCount[type];
        }
    }

    /* loaded from: classes4.dex */
    public class DnsRecord {
        private static final int MAXLABELCOUNT = 128;
        private static final int MAXLABELSIZE = 63;
        private static final int MAXNAMESIZE = 255;
        public static final int NAME_COMPRESSION = 192;
        public static final int NAME_NORMAL = 0;
        private static final String TAG = "DnsRecord";
        public final String dName;
        private final DecimalFormat mByteFormat = new DecimalFormat();
        private final FieldPosition mPos = new FieldPosition(0);
        private final byte[] mRdata;
        public final int nsClass;
        public final int nsType;
        public final long ttl;

        DnsRecord(int recordType, ByteBuffer buf) throws BufferUnderflowException, ParseException {
            String parseName = parseName(buf, 0);
            this.dName = parseName;
            if (parseName.length() <= 255) {
                this.nsType = Short.toUnsignedInt(buf.getShort());
                this.nsClass = Short.toUnsignedInt(buf.getShort());
                if (recordType != 0) {
                    this.ttl = Integer.toUnsignedLong(buf.getInt());
                    int length = Short.toUnsignedInt(buf.getShort());
                    byte[] bArr = new byte[length];
                    this.mRdata = bArr;
                    buf.get(bArr);
                    return;
                }
                this.ttl = 0L;
                this.mRdata = null;
                return;
            }
            throw new ParseException("Parse name fail, name size is too long: " + parseName.length());
        }

        public byte[] getRR() {
            byte[] bArr = this.mRdata;
            if (bArr == null) {
                return null;
            }
            return (byte[]) bArr.clone();
        }

        private String labelToString(byte[] label) {
            StringBuffer sb = new StringBuffer();
            for (byte b : label) {
                int b2 = Byte.toUnsignedInt(b);
                if (b2 <= 32 || b2 >= 127) {
                    sb.append('\\');
                    this.mByteFormat.format(b2, sb, this.mPos);
                } else if (b2 == 34 || b2 == 46 || b2 == 59 || b2 == 92 || b2 == 40 || b2 == 41 || b2 == 64 || b2 == 36) {
                    sb.append('\\');
                    sb.append((char) b2);
                } else {
                    sb.append((char) b2);
                }
            }
            return sb.toString();
        }

        private String parseName(ByteBuffer buf, int depth) throws BufferUnderflowException, ParseException {
            if (depth <= 128) {
                int len = Byte.toUnsignedInt(buf.get());
                int mask = len & 192;
                if (len == 0) {
                    return "";
                }
                if (mask != 0 && mask != 192) {
                    throw new ParseException("Parse name fail, bad label type");
                } else if (mask == 192) {
                    int offset = ((len & (-193)) << 8) + Byte.toUnsignedInt(buf.get());
                    int oldPos = buf.position();
                    if (offset < oldPos - 2) {
                        buf.position(offset);
                        String pointed = parseName(buf, depth + 1);
                        buf.position(oldPos);
                        return pointed;
                    }
                    throw new ParseException("Parse compression name fail, invalid compression");
                } else {
                    byte[] label = new byte[len];
                    buf.get(label);
                    String head = labelToString(label);
                    if (head.length() <= 63) {
                        String tail = parseName(buf, depth + 1);
                        if (TextUtils.isEmpty(tail)) {
                            return head;
                        }
                        return head + MediaMetrics.SEPARATOR + tail;
                    }
                    throw new ParseException("Parse name fail, invalid label length");
                }
            } else {
                throw new ParseException("Failed to parse name, too many labels");
            }
        }
    }

    protected DnsPacket(byte[] data) throws ParseException {
        if (data != null) {
            try {
                ByteBuffer buffer = ByteBuffer.wrap(data);
                this.mHeader = new DnsHeader(buffer);
                this.mRecords = new ArrayList[4];
                for (int i = 0; i < 4; i++) {
                    int count = this.mHeader.getRecordCount(i);
                    if (count > 0) {
                        this.mRecords[i] = new ArrayList(count);
                    }
                    for (int j = 0; j < count; j++) {
                        try {
                            this.mRecords[i].add(new DnsRecord(i, buffer));
                        } catch (BufferUnderflowException e) {
                            throw new ParseException("Parse record fail", e);
                        }
                    }
                }
            } catch (BufferUnderflowException e2) {
                throw new ParseException("Parse Header fail, bad input data", e2);
            }
        } else {
            throw new ParseException("Parse header failed, null input data");
        }
    }
}
