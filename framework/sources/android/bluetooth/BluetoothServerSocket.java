package android.bluetooth;

import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes.dex */
public final class BluetoothServerSocket implements Closeable {
    private static final boolean DBG = false;
    private static final String TAG = "BluetoothServerSocket";
    private int mChannel;
    private Handler mHandler;
    private int mMessage;
    final BluetoothSocket mSocket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothServerSocket(int type, boolean auth, boolean encrypt, int port) throws IOException {
        this.mChannel = port;
        BluetoothSocket bluetoothSocket = new BluetoothSocket(type, -1, auth, encrypt, null, port, null);
        this.mSocket = bluetoothSocket;
        if (port == -2) {
            bluetoothSocket.setExcludeSdp(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothServerSocket(int type, boolean auth, boolean encrypt, int port, boolean mitm, boolean min16DigitPin) throws IOException {
        this.mChannel = port;
        BluetoothSocket bluetoothSocket = new BluetoothSocket(type, -1, auth, encrypt, null, port, null, mitm, min16DigitPin);
        this.mSocket = bluetoothSocket;
        if (port == -2) {
            bluetoothSocket.setExcludeSdp(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothServerSocket(int type, boolean auth, boolean encrypt, ParcelUuid uuid) throws IOException {
        BluetoothSocket bluetoothSocket = new BluetoothSocket(type, -1, auth, encrypt, null, -1, uuid);
        this.mSocket = bluetoothSocket;
        this.mChannel = bluetoothSocket.getPort();
    }

    public BluetoothSocket accept() throws IOException {
        return accept(-1);
    }

    public BluetoothSocket accept(int timeout) throws IOException {
        return this.mSocket.accept(timeout);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.obtainMessage(this.mMessage).sendToTarget();
            }
        }
        this.mSocket.close();
    }

    synchronized void setCloseHandler(Handler handler, int message) {
        this.mHandler = handler;
        this.mMessage = message;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setServiceName(String serviceName) {
        this.mSocket.setServiceName(serviceName);
    }

    public int getChannel() {
        return this.mChannel;
    }

    public int getPsm() {
        return this.mChannel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setChannel(int newChannel) {
        BluetoothSocket bluetoothSocket = this.mSocket;
        if (!(bluetoothSocket == null || bluetoothSocket.getPort() == newChannel)) {
            Log.w(TAG, "The port set is different that the underlying port. mSocket.getPort(): " + this.mSocket.getPort() + " requested newChannel: " + newChannel);
        }
        this.mChannel = newChannel;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServerSocket: Type: ");
        switch (this.mSocket.getConnectionType()) {
            case 1:
                sb.append("TYPE_RFCOMM");
                break;
            case 2:
                sb.append("TYPE_SCO");
                break;
            case 3:
                sb.append("TYPE_L2CAP");
                break;
            case 4:
                sb.append("TYPE_L2CAP_LE");
                break;
        }
        sb.append(" Channel: ");
        sb.append(this.mChannel);
        return sb.toString();
    }
}
