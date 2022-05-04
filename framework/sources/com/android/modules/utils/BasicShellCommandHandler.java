package com.android.modules.utils;

import android.os.Binder;
import com.android.internal.content.NativeLibraryHelper;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
/* loaded from: classes4.dex */
public abstract class BasicShellCommandHandler {
    protected static final boolean DEBUG = false;
    protected static final String TAG = "ShellCommand";
    private int mArgPos;
    private String[] mArgs;
    private String mCmd;
    private String mCurArgData;
    private FileDescriptor mErr;
    private PrintWriter mErrPrintWriter;
    private FileOutputStream mFileErr;
    private FileInputStream mFileIn;
    private FileOutputStream mFileOut;
    private FileDescriptor mIn;
    private InputStream mInputStream;
    private FileDescriptor mOut;
    private PrintWriter mOutPrintWriter;
    private Binder mTarget;

    public abstract int onCommand(String str);

    public abstract void onHelp();

    public void init(Binder target, FileDescriptor in, FileDescriptor out, FileDescriptor err, String[] args, int firstArgPos) {
        this.mTarget = target;
        this.mIn = in;
        this.mOut = out;
        this.mErr = err;
        this.mArgs = args;
        this.mCmd = null;
        this.mArgPos = firstArgPos;
        this.mCurArgData = null;
        this.mFileIn = null;
        this.mFileOut = null;
        this.mFileErr = null;
        this.mOutPrintWriter = null;
        this.mErrPrintWriter = null;
        this.mInputStream = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x005e, code lost:
        if (r2 == null) goto L_0x0061;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0061, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int exec(android.os.Binder r10, java.io.FileDescriptor r11, java.io.FileDescriptor r12, java.io.FileDescriptor r13, java.lang.String[] r14) {
        /*
            r9 = this;
            if (r14 == 0) goto L_0x000b
            int r0 = r14.length
            if (r0 <= 0) goto L_0x000b
            r0 = 0
            r0 = r14[r0]
            r1 = 1
            r8 = r1
            goto L_0x000e
        L_0x000b:
            r0 = 0
            r1 = 0
            r8 = r1
        L_0x000e:
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r8
            r1.init(r2, r3, r4, r5, r6, r7)
            r9.mCmd = r0
            r1 = -1
            int r2 = r9.onCommand(r0)     // Catch: all -> 0x002f
            r1 = r2
            java.io.PrintWriter r2 = r9.mOutPrintWriter
            if (r2 == 0) goto L_0x0027
            r2.flush()
        L_0x0027:
            java.io.PrintWriter r2 = r9.mErrPrintWriter
            if (r2 == 0) goto L_0x0061
        L_0x002b:
            r2.flush()
            goto L_0x0061
        L_0x002f:
            r2 = move-exception
            java.io.PrintWriter r3 = r9.getErrPrintWriter()     // Catch: all -> 0x0062
            r3.println()     // Catch: all -> 0x0062
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x0062
            r4.<init>()     // Catch: all -> 0x0062
            java.lang.String r5 = "Exception occurred while executing '"
            r4.append(r5)     // Catch: all -> 0x0062
            java.lang.String r5 = r9.mCmd     // Catch: all -> 0x0062
            r4.append(r5)     // Catch: all -> 0x0062
            java.lang.String r5 = "':"
            r4.append(r5)     // Catch: all -> 0x0062
            java.lang.String r4 = r4.toString()     // Catch: all -> 0x0062
            r3.println(r4)     // Catch: all -> 0x0062
            r2.printStackTrace(r3)     // Catch: all -> 0x0062
            java.io.PrintWriter r2 = r9.mOutPrintWriter
            if (r2 == 0) goto L_0x005c
            r2.flush()
        L_0x005c:
            java.io.PrintWriter r2 = r9.mErrPrintWriter
            if (r2 == 0) goto L_0x0061
            goto L_0x002b
        L_0x0061:
            return r1
        L_0x0062:
            r2 = move-exception
            java.io.PrintWriter r3 = r9.mOutPrintWriter
            if (r3 == 0) goto L_0x006a
            r3.flush()
        L_0x006a:
            java.io.PrintWriter r3 = r9.mErrPrintWriter
            if (r3 == 0) goto L_0x0071
            r3.flush()
        L_0x0071:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.modules.utils.BasicShellCommandHandler.exec(android.os.Binder, java.io.FileDescriptor, java.io.FileDescriptor, java.io.FileDescriptor, java.lang.String[]):int");
    }

    public FileDescriptor getOutFileDescriptor() {
        return this.mOut;
    }

    public OutputStream getRawOutputStream() {
        if (this.mFileOut == null) {
            this.mFileOut = new FileOutputStream(this.mOut);
        }
        return this.mFileOut;
    }

    public PrintWriter getOutPrintWriter() {
        if (this.mOutPrintWriter == null) {
            this.mOutPrintWriter = new PrintWriter(getRawOutputStream());
        }
        return this.mOutPrintWriter;
    }

    public FileDescriptor getErrFileDescriptor() {
        return this.mErr;
    }

    public OutputStream getRawErrorStream() {
        if (this.mFileErr == null) {
            this.mFileErr = new FileOutputStream(this.mErr);
        }
        return this.mFileErr;
    }

    public PrintWriter getErrPrintWriter() {
        if (this.mErr == null) {
            return getOutPrintWriter();
        }
        if (this.mErrPrintWriter == null) {
            this.mErrPrintWriter = new PrintWriter(getRawErrorStream());
        }
        return this.mErrPrintWriter;
    }

    public FileDescriptor getInFileDescriptor() {
        return this.mIn;
    }

    public InputStream getRawInputStream() {
        if (this.mFileIn == null) {
            this.mFileIn = new FileInputStream(this.mIn);
        }
        return this.mFileIn;
    }

    public InputStream getBufferedInputStream() {
        if (this.mInputStream == null) {
            this.mInputStream = new BufferedInputStream(getRawInputStream());
        }
        return this.mInputStream;
    }

    public String getNextOption() {
        if (this.mCurArgData == null) {
            int i = this.mArgPos;
            String[] strArr = this.mArgs;
            if (i >= strArr.length) {
                return null;
            }
            String arg = strArr[i];
            if (!arg.startsWith(NativeLibraryHelper.CLEAR_ABI_OVERRIDE)) {
                return null;
            }
            this.mArgPos++;
            if (arg.equals("--")) {
                return null;
            }
            if (arg.length() <= 1 || arg.charAt(1) == '-') {
                this.mCurArgData = null;
                return arg;
            } else if (arg.length() > 2) {
                this.mCurArgData = arg.substring(2);
                return arg.substring(0, 2);
            } else {
                this.mCurArgData = null;
                return arg;
            }
        } else {
            String prev = this.mArgs[this.mArgPos - 1];
            throw new IllegalArgumentException("No argument expected after \"" + prev + "\"");
        }
    }

    public String getNextArg() {
        if (this.mCurArgData != null) {
            String arg = this.mCurArgData;
            this.mCurArgData = null;
            return arg;
        }
        int i = this.mArgPos;
        String[] strArr = this.mArgs;
        if (i >= strArr.length) {
            return null;
        }
        this.mArgPos = i + 1;
        return strArr[i];
    }

    public String peekNextArg() {
        String str = this.mCurArgData;
        if (str != null) {
            return str;
        }
        int i = this.mArgPos;
        String[] strArr = this.mArgs;
        if (i < strArr.length) {
            return strArr[i];
        }
        return null;
    }

    public String[] peekRemainingArgs() {
        int remaining = getRemainingArgsCount();
        String[] args = new String[remaining];
        int pos = this.mArgPos;
        while (true) {
            String[] strArr = this.mArgs;
            if (pos >= strArr.length) {
                return args;
            }
            args[pos - this.mArgPos] = strArr[pos];
            pos++;
        }
    }

    public int getRemainingArgsCount() {
        int i = this.mArgPos;
        String[] strArr = this.mArgs;
        if (i >= strArr.length) {
            return 0;
        }
        return strArr.length - i;
    }

    public String getNextArgRequired() {
        String arg = getNextArg();
        if (arg != null) {
            return arg;
        }
        String prev = this.mArgs[this.mArgPos - 1];
        throw new IllegalArgumentException("Argument expected after \"" + prev + "\"");
    }

    public int handleDefaultCommands(String cmd) {
        if (cmd == null || "help".equals(cmd) || "-h".equals(cmd)) {
            onHelp();
            return -1;
        }
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("Unknown command: " + cmd);
        return -1;
    }

    public Binder getTarget() {
        return this.mTarget;
    }

    public String[] getAllArgs() {
        return this.mArgs;
    }
}
