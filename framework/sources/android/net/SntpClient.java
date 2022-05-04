package android.net;

import android.os.SystemClock;
import android.util.Log;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.util.TrafficStatsConstants;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class SntpClient {
    private static final boolean DBG = true;
    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 2208988800L;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int REFERENCE_TIME_OFFSET = 16;
    private static final String TAG = "SntpClient";
    private static final int TRANSMIT_TIME_OFFSET = 40;
    private long mNtpTime;
    private long mNtpTimeReference;
    private long mRoundTripTime;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class InvalidServerReplyException extends Exception {
        public InvalidServerReplyException(String message) {
            super(message);
        }
    }

    public boolean requestTime(String host, int timeout, Network network) {
        Network networkForResolv = network.getPrivateDnsBypassingCopy();
        try {
            InetAddress[] addresses = networkForResolv.getAllByName(host);
            for (InetAddress inetAddress : addresses) {
                if (requestTime(inetAddress, 123, timeout, networkForResolv)) {
                    return true;
                }
            }
        } catch (UnknownHostException e) {
            Log.w(TAG, "Unknown host: " + host);
            EventLogTags.writeNtpFailure(host, e.toString());
        }
        Log.d(TAG, "request time failed");
        return false;
    }

    public boolean requestTime(InetAddress address, int port, int timeout, Network network) {
        DatagramSocket socket = null;
        int oldTag = TrafficStats.getAndSetThreadStatsTag(TrafficStatsConstants.TAG_SYSTEM_NTP);
        try {
            try {
                socket = new DatagramSocket();
                network.bindSocket(socket);
                socket.setSoTimeout(timeout);
                byte[] buffer = new byte[48];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
                buffer[0] = GsmAlphabet.GSM_EXTENDED_ESCAPE;
                long requestTime = System.currentTimeMillis();
                long requestTicks = SystemClock.elapsedRealtime();
                writeTimeStamp(buffer, 40, requestTime);
                socket.send(request);
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                long responseTicks = SystemClock.elapsedRealtime();
                long responseTime = requestTime + (responseTicks - requestTicks);
                byte leap = (byte) ((buffer[0] >> 6) & 3);
                byte mode = (byte) (buffer[0] & 7);
                int stratum = buffer[1] & 255;
                long originateTime = readTimeStamp(buffer, 24);
                long receiveTime = readTimeStamp(buffer, 32);
                long transmitTime = readTimeStamp(buffer, 40);
                long referenceTime = readTimeStamp(buffer, 16);
                checkValidServerReply(leap, mode, stratum, transmitTime, referenceTime);
                long roundTripTime = (responseTicks - requestTicks) - (transmitTime - receiveTime);
                long clockOffset = ((receiveTime - originateTime) + (transmitTime - responseTime)) / 2;
                EventLogTags.writeNtpSuccess(address.toString(), roundTripTime, clockOffset);
                Log.d(TAG, "round trip: " + roundTripTime + "ms, clock offset: " + clockOffset + "ms");
                this.mNtpTime = responseTime + clockOffset;
                this.mNtpTimeReference = responseTicks;
                this.mRoundTripTime = roundTripTime;
                socket.close();
                TrafficStats.setThreadStatsTag(oldTag);
                return true;
            } catch (Exception e) {
                EventLogTags.writeNtpFailure(address.toString(), e.toString());
                Log.d(TAG, "request time failed: " + e);
                if (socket != null) {
                    socket.close();
                }
                TrafficStats.setThreadStatsTag(oldTag);
                return false;
            }
        } catch (Throwable th) {
            if (socket != null) {
                socket.close();
            }
            TrafficStats.setThreadStatsTag(oldTag);
            throw th;
        }
    }

    @Deprecated
    public boolean requestTime(String host, int timeout) {
        Log.w(TAG, "Shame on you for calling the hidden API requestTime()!");
        return false;
    }

    public long getNtpTime() {
        return this.mNtpTime;
    }

    public long getNtpTimeReference() {
        return this.mNtpTimeReference;
    }

    public long getRoundTripTime() {
        return this.mRoundTripTime;
    }

    private static void checkValidServerReply(byte leap, byte mode, int stratum, long transmitTime, long referenceTime) throws InvalidServerReplyException {
        if (leap == 3) {
            throw new InvalidServerReplyException("unsynchronized server");
        } else if (mode != 4 && mode != 5) {
            throw new InvalidServerReplyException("untrusted mode: " + ((int) mode));
        } else if (stratum == 0 || stratum > 15) {
            throw new InvalidServerReplyException("untrusted stratum: " + stratum);
        } else if (transmitTime == 0) {
            throw new InvalidServerReplyException("zero transmitTime");
        } else if (referenceTime == 0) {
            throw new InvalidServerReplyException("zero reference timestamp");
        }
    }

    private long read32(byte[] buffer, int offset) {
        byte b0 = buffer[offset];
        byte b1 = buffer[offset + 1];
        byte b2 = buffer[offset + 2];
        byte b3 = buffer[offset + 3];
        int i0 = (b0 & 128) == 128 ? (b0 & Byte.MAX_VALUE) + 128 : b0;
        int i1 = (b1 & 128) == 128 ? (b1 & Byte.MAX_VALUE) + 128 : b1;
        int i2 = (b2 & 128) == 128 ? (b2 & Byte.MAX_VALUE) + 128 : b2;
        int i3 = (b3 & 128) == 128 ? (b3 & Byte.MAX_VALUE) + 128 : b3;
        return (i0 << 24) + (i1 << 16) + (i2 << 8) + i3;
    }

    private long readTimeStamp(byte[] buffer, int offset) {
        long seconds = read32(buffer, offset);
        long fraction = read32(buffer, offset + 4);
        if (seconds == 0 && fraction == 0) {
            return 0L;
        }
        return ((seconds - OFFSET_1900_TO_1970) * 1000) + ((1000 * fraction) / 4294967296L);
    }

    private void writeTimeStamp(byte[] buffer, int offset, long time) {
        if (time == 0) {
            Arrays.fill(buffer, offset, offset + 8, (byte) 0);
            return;
        }
        long seconds = time / 1000;
        long milliseconds = time - (seconds * 1000);
        long seconds2 = seconds + OFFSET_1900_TO_1970;
        int offset2 = offset + 1;
        buffer[offset] = (byte) (seconds2 >> 24);
        int offset3 = offset2 + 1;
        buffer[offset2] = (byte) (seconds2 >> 16);
        int offset4 = offset3 + 1;
        buffer[offset3] = (byte) (seconds2 >> 8);
        int offset5 = offset4 + 1;
        buffer[offset4] = (byte) (seconds2 >> 0);
        long fraction = (4294967296L * milliseconds) / 1000;
        int offset6 = offset5 + 1;
        buffer[offset5] = (byte) (fraction >> 24);
        int offset7 = offset6 + 1;
        buffer[offset6] = (byte) (fraction >> 16);
        int offset8 = offset7 + 1;
        buffer[offset7] = (byte) (fraction >> 8);
        int i = offset8 + 1;
        buffer[offset8] = (byte) (Math.random() * 255.0d);
    }
}
