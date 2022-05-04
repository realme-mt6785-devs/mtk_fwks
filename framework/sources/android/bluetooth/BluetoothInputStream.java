package android.bluetooth;

import java.io.IOException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothInputStream extends InputStream {
    private BluetoothSocket mSocket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothInputStream(BluetoothSocket s) {
        this.mSocket = s;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.mSocket.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mSocket.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] b = new byte[1];
        int ret = this.mSocket.read(b, 0, 1);
        if (ret == 1) {
            return b[0] & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int offset, int length) throws IOException {
        if (b == null) {
            throw new NullPointerException("byte array is null");
        } else if ((offset | length) >= 0 && length <= b.length - offset) {
            return this.mSocket.read(b, offset, length);
        } else {
            throw new ArrayIndexOutOfBoundsException("invalid offset or length");
        }
    }
}
