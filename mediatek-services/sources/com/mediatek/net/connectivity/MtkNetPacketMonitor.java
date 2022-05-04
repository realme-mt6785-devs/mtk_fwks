package com.mediatek.net.connectivity;

import android.net.util.SocketUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructTimeval;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class MtkNetPacketMonitor {
    private static final int MSG_CREATE_QUEUE = 11;
    private static final int MSG_DESTROY_QUEUE = 12;
    private static final int MSG_NOTIFY_PACKET = 21;
    private static final int MSG_SEND_VERDICT = 13;
    private static final int MSG_START_CONNECTION = 1;
    private static final String NAME_CONTROL_THREAD = "[MDC]MtkNetPacketMonitor-control";
    private static final String NAME_NOTIFY_THREAD = "[MDC]MtkNetPacketMonitor-notify";
    private static final String NAME_RECEIVE_THREAD = "[MDC]MtkNetPacketMonitor-receive";
    private static final String TAG = "[MDC]MtkNetPacketMonitor";
    private Handler mControlHandler;
    private HandlerThread mControlHandlerThread;
    private Handler mNotifyHandler;
    private HandlerThread mNotifyHandlerThread;
    private PacketCallback mPacketCallback;
    private Handler mReceiveHandler;
    private HandlerThread mReceiveHandlerThread;
    private SocketWrapper mSocketWrapper = new SocketWrapper();

    public MtkNetPacketMonitor() {
        initHandler();
        this.mReceiveHandler.sendEmptyMessage(1);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(NAME_CONTROL_THREAD);
        this.mControlHandlerThread = handlerThread;
        handlerThread.start();
        this.mControlHandler = new Handler(this.mControlHandlerThread.getLooper()) { // from class: com.mediatek.net.connectivity.MtkNetPacketMonitor.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MtkNetPacketMonitor.MSG_CREATE_QUEUE /* 11 */:
                        MtkNetPacketMonitor.this.doCreateQueue(msg.arg1);
                        return;
                    case MtkNetPacketMonitor.MSG_DESTROY_QUEUE /* 12 */:
                        MtkNetPacketMonitor.this.doDestroyQueue(msg.arg1);
                        return;
                    case MtkNetPacketMonitor.MSG_SEND_VERDICT /* 13 */:
                        MtkNetPacketMonitor.this.doSendVerdict(msg.arg1, msg.arg2);
                        return;
                    default:
                        return;
                }
            }
        };
        HandlerThread handlerThread2 = new HandlerThread(NAME_RECEIVE_THREAD);
        this.mReceiveHandlerThread = handlerThread2;
        handlerThread2.start();
        this.mReceiveHandler = new Handler(this.mReceiveHandlerThread.getLooper()) { // from class: com.mediatek.net.connectivity.MtkNetPacketMonitor.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        MtkNetPacketMonitor.this.doStartConnection();
                        return;
                    default:
                        return;
                }
            }
        };
        HandlerThread handlerThread3 = new HandlerThread(NAME_NOTIFY_THREAD);
        this.mNotifyHandlerThread = handlerThread3;
        handlerThread3.start();
        this.mNotifyHandler = new Handler(this.mNotifyHandlerThread.getLooper()) { // from class: com.mediatek.net.connectivity.MtkNetPacketMonitor.3
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MtkNetPacketMonitor.MSG_NOTIFY_PACKET /* 21 */:
                        MtkNetPacketMonitor.this.doNotifyPacket(msg.arg1);
                        return;
                    default:
                        Log.i(MtkNetPacketMonitor.TAG, "Do nothing");
                        return;
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStartConnection() {
        this.mSocketWrapper.connectToKernel();
        this.mSocketWrapper.blockToReceive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCreateQueue(int queueNumber) {
        if (queueNumber <= 0) {
            Log.i(TAG, "doCreateQueue: invalid " + queueNumber);
            return;
        }
        Log.i(TAG, "send create queue message " + queueNumber);
        byte[] bindMessage = MtkPacketMessage.getBindQueueMessage(queueNumber);
        this.mSocketWrapper.sendMessage(bindMessage);
        Log.i(TAG, "send mode message " + queueNumber);
        byte[] modeMessage = MtkPacketMessage.getSetModeMessage(queueNumber);
        this.mSocketWrapper.sendMessage(modeMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDestroyQueue(int queueNumber) {
        if (queueNumber <= 0) {
            Log.i(TAG, "doDestroyQueue: invalid " + queueNumber);
            return;
        }
        Log.i(TAG, "doDestroyQueue " + queueNumber);
        byte[] message = MtkPacketMessage.getUnbindQueueMessage(queueNumber);
        this.mSocketWrapper.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSendVerdict(int queueNumber, int packetId) {
        Log.i(TAG, "doSendVerdict queue " + queueNumber + ", packetId " + packetId);
        byte[] message = MtkPacketMessage.getVerdictMessage(queueNumber, packetId, 0);
        this.mSocketWrapper.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doNotifyPacket(int uid) {
        PacketCallback packetCallback = this.mPacketCallback;
        if (packetCallback != null) {
            packetCallback.onPacketEvent(uid);
        } else {
            Log.e(TAG, "doNotifyPacket mPacketCallback is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processReceiveBytes(byte[] bytes) {
        if (bytes == null || bytes.length < 1) {
            Log.e(TAG, "processReceiveBytes invalid bytes");
            return;
        }
        MtkPacketMessage packetMessage = new MtkPacketMessage();
        packetMessage.parseFromBytes(bytes);
        int queueNumber = packetMessage.getQueueNumber();
        int packetId = packetMessage.getPacketId();
        if (queueNumber <= 0 || packetId <= 0) {
            Log.i(TAG, "processReceiveBytes not notify");
            return;
        }
        Log.i(TAG, "processReceiveBytes " + queueNumber + ", packet " + packetId);
        Message notifyMessage = this.mNotifyHandler.obtainMessage(MSG_NOTIFY_PACKET);
        notifyMessage.arg1 = queueNumber;
        this.mNotifyHandler.sendMessage(notifyMessage);
        Message verdictMessage = this.mControlHandler.obtainMessage(MSG_SEND_VERDICT);
        verdictMessage.arg1 = queueNumber;
        verdictMessage.arg2 = packetId;
        this.mControlHandler.sendMessage(verdictMessage);
    }

    public void startMonitorProcessWithUid(int uid) {
        Log.i(TAG, "startMonitorProcessWithUid " + uid);
        Message message = this.mControlHandler.obtainMessage(MSG_CREATE_QUEUE);
        message.arg1 = uid;
        this.mControlHandler.sendMessage(message);
    }

    public void stopMonitorProcessWithUid(int uid) {
        Log.i(TAG, "stopMonitorProcessWithUid " + uid);
        Message message = this.mControlHandler.obtainMessage(MSG_DESTROY_QUEUE);
        message.arg1 = uid;
        this.mControlHandler.sendMessage(message);
    }

    public void setPacketCallback(PacketCallback callback) {
        Log.i(TAG, "setPacketCallback");
        this.mPacketCallback = callback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PacketCallback {
        public void onPacketEvent(int uid) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SocketWrapper {
        private static final long IO_TIMEOUT = 300;
        private static final int SOCKET_RECV_BUFSIZE = 4096;
        private FileDescriptor mSocket;

        public SocketWrapper() {
        }

        public void connectToKernel() {
            try {
                FileDescriptor socket = Os.socket(OsConstants.AF_NETLINK, OsConstants.SOCK_DGRAM, OsConstants.NETLINK_NETFILTER);
                this.mSocket = socket;
                Os.setsockoptInt(socket, OsConstants.SOL_SOCKET, OsConstants.SO_RCVBUF, SOCKET_RECV_BUFSIZE);
                Os.connect(this.mSocket, SocketUtils.makeNetlinkSocketAddress(0, 0));
            } catch (ErrnoException | SocketException e) {
                e.printStackTrace();
            }
        }

        public void blockToReceive() {
            byte[] socketBuffer = new byte[SOCKET_RECV_BUFSIZE];
            InetSocketAddress inetSocketAddress = new InetSocketAddress(0);
            while (isSocketValid()) {
                try {
                    int rval = Os.recvfrom(this.mSocket, socketBuffer, 0, socketBuffer.length, 0, inetSocketAddress);
                    if (rval > 0) {
                        byte[] array = Arrays.copyOf(socketBuffer, rval);
                        MtkNetPacketMonitor.this.processReceiveBytes(array);
                    } else {
                        Log.e(MtkNetPacketMonitor.TAG, "blockToReceive error " + rval);
                    }
                } catch (ErrnoException | SocketException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(byte[] bytes) {
            if (bytes == null || bytes.length < 1) {
                Log.e(MtkNetPacketMonitor.TAG, "sendMessage invalid bytes");
            } else {
                sendMessage(bytes, 0, bytes.length);
            }
        }

        private void sendMessage(byte[] bytes, int offset, int count) {
            if (!isSocketValid()) {
                Log.e(MtkNetPacketMonitor.TAG, "socket is not valid");
                return;
            }
            try {
                Os.setsockoptTimeval(this.mSocket, OsConstants.SOL_SOCKET, OsConstants.SO_SNDTIMEO, StructTimeval.fromMillis(IO_TIMEOUT));
                int ret = Os.write(this.mSocket, bytes, offset, count);
                if (ret <= 0) {
                    Log.e(MtkNetPacketMonitor.TAG, "sendMessage error " + ret);
                }
            } catch (ErrnoException | InterruptedIOException e) {
                e.printStackTrace();
            }
        }

        private boolean isSocketValid() {
            FileDescriptor fileDescriptor = this.mSocket;
            return fileDescriptor != null && fileDescriptor.valid();
        }
    }
}
