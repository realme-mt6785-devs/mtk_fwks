package com.android.internal.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.nio.charset.StandardCharsets;
/* loaded from: classes4.dex */
public class ProcFileReader implements Closeable {
    private final byte[] mBuffer;
    private boolean mLineFinished;
    private final InputStream mStream;
    private int mTail;

    public ProcFileReader(InputStream stream) throws IOException {
        this(stream, 4096);
    }

    public ProcFileReader(InputStream stream, int bufferSize) throws IOException {
        this.mStream = stream;
        this.mBuffer = new byte[bufferSize];
        fillBuf();
    }

    private int fillBuf() throws IOException {
        byte[] bArr = this.mBuffer;
        int length = bArr.length;
        int i = this.mTail;
        int length2 = length - i;
        if (length2 != 0) {
            int read = this.mStream.read(bArr, i, length2);
            if (read != -1) {
                this.mTail += read;
            }
            return read;
        }
        throw new IOException("attempting to fill already-full buffer");
    }

    private void consumeBuf(int count) throws IOException {
        int i;
        while (true) {
            i = this.mTail;
            if (count >= i || this.mBuffer[count] != 32) {
                break;
            }
            count++;
        }
        byte[] bArr = this.mBuffer;
        System.arraycopy(bArr, count, bArr, 0, i - count);
        int i2 = this.mTail - count;
        this.mTail = i2;
        if (i2 == 0) {
            fillBuf();
        }
    }

    private int nextTokenIndex() throws IOException {
        if (this.mLineFinished) {
            return -1;
        }
        int i = 0;
        while (true) {
            if (i < this.mTail) {
                byte b = this.mBuffer[i];
                if (b == 10) {
                    this.mLineFinished = true;
                    return i;
                } else if (b == 32) {
                    return i;
                } else {
                    i++;
                }
            } else if (fillBuf() <= 0) {
                throw new ProtocolException("End of stream while looking for token boundary");
            }
        }
    }

    public boolean hasMoreData() {
        return this.mTail > 0;
    }

    public void finishLine() throws IOException {
        if (this.mLineFinished) {
            this.mLineFinished = false;
            return;
        }
        int i = 0;
        while (true) {
            if (i < this.mTail) {
                if (this.mBuffer[i] == 10) {
                    consumeBuf(i + 1);
                    return;
                }
                i++;
            } else if (fillBuf() <= 0) {
                throw new ProtocolException("End of stream while looking for line boundary");
            }
        }
    }

    public String nextString() throws IOException {
        int tokenIndex = nextTokenIndex();
        if (tokenIndex != -1) {
            return parseAndConsumeString(tokenIndex);
        }
        throw new ProtocolException("Missing required string");
    }

    public long nextLong() throws IOException {
        return nextLong(false);
    }

    public long nextLong(boolean stopAtInvalid) throws IOException {
        int tokenIndex = nextTokenIndex();
        if (tokenIndex != -1) {
            return parseAndConsumeLong(tokenIndex, stopAtInvalid);
        }
        throw new ProtocolException("Missing required long");
    }

    public long nextOptionalLong(long def) throws IOException {
        int tokenIndex = nextTokenIndex();
        if (tokenIndex == -1) {
            return def;
        }
        return parseAndConsumeLong(tokenIndex, false);
    }

    private String parseAndConsumeString(int tokenIndex) throws IOException {
        String s = new String(this.mBuffer, 0, tokenIndex, StandardCharsets.US_ASCII);
        consumeBuf(tokenIndex + 1);
        return s;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long parseAndConsumeLong(int r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            byte[] r0 = r9.mBuffer
            r1 = 0
            r0 = r0[r1]
            r2 = 1
            r3 = 45
            if (r0 != r3) goto L_0x000c
            r0 = r2
            goto L_0x000d
        L_0x000c:
            r0 = r1
        L_0x000d:
            r3 = 0
            if (r0 == 0) goto L_0x0012
            r1 = r2
        L_0x0012:
            if (r1 >= r10) goto L_0x003b
            byte[] r2 = r9.mBuffer
            r2 = r2[r1]
            int r2 = r2 + (-48)
            if (r2 < 0) goto L_0x0033
            r5 = 9
            if (r2 <= r5) goto L_0x0021
            goto L_0x0033
        L_0x0021:
            r5 = 10
            long r5 = r5 * r3
            long r7 = (long) r2
            long r5 = r5 - r7
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 > 0) goto L_0x002e
            r3 = r5
            int r1 = r1 + 1
            goto L_0x0012
        L_0x002e:
            java.lang.NumberFormatException r7 = r9.invalidLong(r10)
            throw r7
        L_0x0033:
            if (r11 == 0) goto L_0x0036
            goto L_0x003b
        L_0x0036:
            java.lang.NumberFormatException r5 = r9.invalidLong(r10)
            throw r5
        L_0x003b:
            int r1 = r10 + 1
            r9.consumeBuf(r1)
            if (r0 == 0) goto L_0x0044
            r1 = r3
            goto L_0x0045
        L_0x0044:
            long r1 = -r3
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.util.ProcFileReader.parseAndConsumeLong(int, boolean):long");
    }

    private NumberFormatException invalidLong(int tokenIndex) {
        return new NumberFormatException("invalid long: " + new String(this.mBuffer, 0, tokenIndex, StandardCharsets.US_ASCII));
    }

    public int nextInt() throws IOException {
        long value = nextLong();
        if (value <= 2147483647L && value >= -2147483648L) {
            return (int) value;
        }
        throw new NumberFormatException("parsed value larger than integer");
    }

    public void nextIgnored() throws IOException {
        int tokenIndex = nextTokenIndex();
        if (tokenIndex != -1) {
            consumeBuf(tokenIndex + 1);
            return;
        }
        throw new ProtocolException("Missing required token");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mStream.close();
    }
}
