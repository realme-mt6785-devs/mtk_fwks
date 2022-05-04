package com.mediatek.server.wm;

import android.os.Build;
import android.util.Slog;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.ClientWindowFrames;
import com.android.internal.protolog.ProtoLogImpl;
import com.android.server.policy.PhoneWindowManager;
import com.android.server.wm.WindowManagerDebugConfig;
import com.android.server.wm.WindowState;
import java.io.PrintWriter;
import java.lang.reflect.Field;
/* loaded from: classes.dex */
public class WindowManagerDebuggerImpl extends WindowManagerDebugger {
    private static final String TAG = "WindowManagerDebuggerImpl";

    public WindowManagerDebuggerImpl() {
        WMS_DEBUG_ENG = "eng".equals(Build.TYPE);
        WMS_DEBUG_USER_DEBUG = "userdebug".equals(Build.TYPE);
        WMS_DEBUG_USER = true;
    }

    public void runDebug(PrintWriter pw, String[] args, int opti) {
        int mode;
        String cmd;
        int opti2;
        int opti3 = opti;
        String cmd2 = "help";
        if (opti3 < args.length) {
            cmd2 = args[opti3];
            opti3++;
        }
        if ("help".equals(cmd2)) {
            mode = 0;
            pw.println("Window manager debug options:");
            pw.println("  -d enable <zone zone ...> : enable the debug zone");
            pw.println("  -d disable <zone zone ...> : disable the debug zone");
            pw.println("zone may be some of:");
            pw.println("  a[all]");
        } else if ("enable".equals(cmd2)) {
            mode = 1;
        } else if ("disable".equals(cmd2)) {
            mode = 2;
        } else {
            pw.println("Unknown debug argument: " + cmd2 + "; use \"-d help\" for help");
            return;
        }
        boolean setAll = false;
        Field[] fields = WindowManagerDebugConfig.class.getDeclaredFields();
        Field[] fieldsPolicy = PhoneWindowManager.class.getDeclaredFields();
        Field[] allFields = new Field[fields.length + fieldsPolicy.length];
        boolean z = false;
        System.arraycopy(fields, 0, allFields, 0, fields.length);
        System.arraycopy(fieldsPolicy, 0, allFields, fields.length, fieldsPolicy.length);
        while (true) {
            boolean z2 = true;
            if (setAll || (mode != 0 && opti3 >= args.length)) {
                break;
            }
            if (opti3 < args.length) {
                String cmd3 = args[opti3];
                cmd = cmd3;
                opti2 = opti3 + 1;
            } else {
                cmd = cmd2;
                opti2 = opti3;
            }
            setAll = mode == 0 || "all".equals(cmd) || "a".equals(cmd);
            int i = 0;
            while (i < allFields.length) {
                String name = allFields[i].getName();
                if (name != null && (name.startsWith("DEBUG") || name.startsWith("SHOW") || name.equals("localLOGV"))) {
                    if (!setAll) {
                        try {
                            if (name.equals(cmd)) {
                            }
                        } catch (IllegalAccessException e) {
                            Slog.e(TAG, name + " setBoolean failed", e);
                        }
                    }
                    if (mode != 0) {
                        allFields[i].setAccessible(z2);
                        Field field = allFields[i];
                        if (mode != z2) {
                            z2 = false;
                        }
                        field.setBoolean(null, z2);
                        name.equals("localLOGV");
                    }
                    pw.println(String.format("  %s = %b", name, Boolean.valueOf(allFields[i].getBoolean(null))));
                }
                i++;
                z2 = true;
            }
            opti3 = opti2;
            cmd2 = cmd;
        }
        ProtoLogImpl.getSingleInstance().startProtoLog(pw);
        ProtoLogImpl singleInstance = ProtoLogImpl.getSingleInstance();
        if (mode == 1) {
            z = true;
        }
        singleInstance.enableText(pw, z);
        pw.println(ProtoLogImpl.getSingleInstance().getStatus());
    }

    public void debugInterceptKeyBeforeQueueing(String tag, int keycode, boolean interactive, boolean keyguardActive, int policyFlags, boolean down, boolean canceled, boolean isWakeKey, int result, boolean useHapticFeedback, boolean isInjected) {
        Slog.d(tag, "interceptKeyTq keycode=" + keycode + " interactive=" + interactive + " keyguardActive=" + keyguardActive + " policyFlags=" + Integer.toHexString(policyFlags) + " down =" + down + " canceled = " + canceled + " isWakeKey=" + isWakeKey + " result = " + result + " useHapticFeedback = " + useHapticFeedback + " isInjected = " + isInjected);
    }

    public void debugApplyPostLayoutPolicyLw(String tag, WindowState win, WindowManager.LayoutParams attrs, WindowState mTopFullscreenOpaqueWindowState, WindowState attached, WindowState imeTarget, boolean dreamingLockscreen, boolean showingDream) {
        Slog.i(tag, "applyPostLayoutPolicyLw Win " + win + ": win.isVisible()=" + win.isVisible() + ", win.isDrawn()=" + win.isDrawn() + ", attrs.type=" + attrs.type + ", attrs.privateFlags=#" + Integer.toHexString(attrs.privateFlags) + ", mTopFullscreenOpaqueWindowState=" + mTopFullscreenOpaqueWindowState + ", win.isGoneForLayout()=" + win.isGoneForLayout() + ", attached=" + attached + ", imeTarget=" + imeTarget + ", isFullscreen=" + attrs.isFullscreen() + ", normallyFullscreenWindows=, mDreamingLockscreen=" + dreamingLockscreen + ", mShowingDream=" + showingDream);
    }

    public void debugLayoutWindowLw(String tag, int adjust, int type, int fl, boolean canHideNavigationBar, int sysUiFl) {
        Slog.v(tag, "layoutWindowLw : sim=#" + Integer.toHexString(adjust) + ", type=" + type + ", flag=" + fl + ", canHideNavigationBar=" + canHideNavigationBar + ", sysUiFl=" + sysUiFl);
    }

    public void debugGetOrientation(String tag, boolean displayFrozen, int lastWindowForcedOrientation, int lastKeyguardForcedOrientation) {
        Slog.v(tag, "Checking window orientation: mDisplayFrozen=" + displayFrozen + ", mLastWindowForcedOrientation=" + lastWindowForcedOrientation + ", mLastKeyguardForcedOrientation=" + lastKeyguardForcedOrientation);
    }

    public void debugGetOrientingWindow(String tag, WindowState w, WindowManager.LayoutParams attrs, boolean isVisible, boolean policyVisibilityAfterAnim, int policyVisibility, boolean destroying) {
        Slog.v(tag, w + " screenOrientation=" + attrs.screenOrientation + ", visibility=" + isVisible + ", mPolicyVisibilityAfterAnim=" + policyVisibilityAfterAnim + ", mPolicyVisibility=" + policyVisibility + ", destroying=" + destroying);
    }

    public void debugPrepareSurfaceLocked(String tag, boolean isWallpaper, WindowState win, boolean isParentWindowHidden, boolean isOnScreen, int policyVisibility, boolean hasSurface, boolean destroying, boolean lastHidden) {
        Slog.v(tag, win + " prepareSurfaceLocked , mIsWallpaper=" + isWallpaper + ", w.isParentWindowHidden=" + isParentWindowHidden + ", w.isOnScreen=" + isOnScreen + ", w.mPolicyVisibility=" + policyVisibility + ", w.mHasSurface=" + hasSurface + ", w.mDestroying=" + destroying + ", mLastHidden=" + lastHidden);
    }

    public void debugRelayoutWindow(String tag, WindowState win, int originType, int changeType) {
        Slog.e(tag, "Window : " + win + "changes the window type!!\nOriginal type : " + originType + "\nChanged type : " + changeType);
    }

    public void debugInputAttr(String tag, WindowManager.LayoutParams attrs) {
        Slog.v(tag, "Input attr :" + attrs);
    }

    public void debugViewVisibility(String tag, WindowState win, int viewVisibility, int oldVisibility, boolean focusMayChange, int requestedWidth, int requestedHeight, ClientWindowFrames outFrames, SurfaceControl outSurfaceControl) {
        if (viewVisibility == 0 && oldVisibility != 0) {
            Slog.i(tag, "Relayout " + win + ": oldVis=" + oldVisibility + " newVis=" + viewVisibility + " focusMayChange = " + focusMayChange + " requestedWidth = " + requestedWidth + " requestedHeight = " + requestedHeight + " outFrames = " + outFrames + " outSurfaceControl = " + outSurfaceControl);
        }
    }
}
