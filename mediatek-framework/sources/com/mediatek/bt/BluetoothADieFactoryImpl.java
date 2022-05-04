package com.mediatek.bt;

import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/* loaded from: classes.dex */
public class BluetoothADieFactoryImpl extends BluetoothADieFactory {
    private static final String COMMAND_BT_OFF = "4w2T8M65K5?2af+a OFF";
    private static final String COMMAND_BT_ON = "4w2T8M65K5?2af+a ON";
    private static final String COMMAND_READ_PIN = "0x12 0x00 0x00";
    private static final String DRIVER_NODE = "/proc/driver/bt_dbg";
    private static final String TAG = "BluetoothADieFactoryImpl";

    public boolean isAdieFail() {
        writeToNode(COMMAND_BT_ON);
        writeToNode(COMMAND_READ_PIN);
        String adieFlag = readFromNode();
        if (adieFlag != null) {
            writeToNode(COMMAND_BT_OFF);
            return "1".equals(adieFlag);
        }
        Log.e(TAG, "read exception happen return true");
        writeToNode(COMMAND_BT_OFF);
        return true;
    }

    private void writeToNode(String str) {
        try {
            File writeFile = new File(DRIVER_NODE);
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(writeFile);
                fileWriter.write(str);
            } catch (IOException e) {
                Log.e(TAG, "IOException");
                e.printStackTrace();
                if (fileWriter == null) {
                    return;
                }
            }
            fileWriter.close();
        } catch (Exception e2) {
            Log.e(TAG, "Exception");
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0045, code lost:
        if (r3 == null) goto L_0x0059;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String readFromNode() {
        /*
            r7 = this;
            java.lang.String r0 = "BluetoothADieFactoryImpl"
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: Exception -> 0x0050
            java.lang.String r3 = "/proc/driver/bt_dbg"
            r2.<init>(r3)     // Catch: Exception -> 0x0050
            r3 = 0
            r4 = 1
            char[] r5 = new char[r4]     // Catch: Exception -> 0x0050
            java.io.FileReader r6 = new java.io.FileReader     // Catch: all -> 0x003a, IOException -> 0x003c
            r6.<init>(r2)     // Catch: all -> 0x003a, IOException -> 0x003c
            r3 = r6
            r6 = 0
            int r4 = r3.read(r5, r6, r4)     // Catch: all -> 0x003a, IOException -> 0x003c
            if (r4 <= 0) goto L_0x0035
            java.lang.String r4 = new java.lang.String     // Catch: all -> 0x003a, IOException -> 0x003c
            r4.<init>(r5)     // Catch: all -> 0x003a, IOException -> 0x003c
            r1 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x003a, IOException -> 0x003c
            r4.<init>()     // Catch: all -> 0x003a, IOException -> 0x003c
            java.lang.String r6 = "readFromNode contents = "
            r4.append(r6)     // Catch: all -> 0x003a, IOException -> 0x003c
            r4.append(r1)     // Catch: all -> 0x003a, IOException -> 0x003c
            java.lang.String r4 = r4.toString()     // Catch: all -> 0x003a, IOException -> 0x003c
            android.util.Log.d(r0, r4)     // Catch: all -> 0x003a, IOException -> 0x003c
        L_0x0035:
        L_0x0036:
            r3.close()     // Catch: Exception -> 0x0050
            goto L_0x0048
        L_0x003a:
            r4 = move-exception
            goto L_0x0049
        L_0x003c:
            r4 = move-exception
            java.lang.String r6 = "IOException"
            android.util.Log.e(r0, r6)     // Catch: all -> 0x003a
            r4.printStackTrace()     // Catch: all -> 0x003a
            if (r3 == 0) goto L_0x0048
            goto L_0x0036
        L_0x0048:
            goto L_0x0059
        L_0x0049:
            if (r3 == 0) goto L_0x004e
            r3.close()     // Catch: Exception -> 0x0050
        L_0x004e:
            throw r4     // Catch: Exception -> 0x0050
        L_0x0050:
            r2 = move-exception
            java.lang.String r3 = "Exception"
            android.util.Log.e(r0, r3)
            r2.printStackTrace()
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.bt.BluetoothADieFactoryImpl.readFromNode():java.lang.String");
    }
}
