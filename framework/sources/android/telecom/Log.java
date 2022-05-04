package android.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.telecom.Logging.EventManager;
import android.telecom.Logging.Session;
import android.telecom.Logging.SessionManager;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.internal.util.IndentingPrintWriter;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Locale;
import java.util.stream.Collectors;
/* loaded from: classes3.dex */
public class Log {
    public static boolean DEBUG = false;
    public static boolean ERROR = false;
    private static final int EVENTS_TO_CACHE = 10;
    private static final int EVENTS_TO_CACHE_DEBUG = 20;
    private static final long EXTENDED_LOGGING_DURATION_MILLIS = 1800000;
    private static final boolean FORCE_LOGGING = false;
    public static boolean INFO;
    private static final int NUM_DIALABLE_DIGITS_TO_LOG;
    public static boolean OPLUS_DEBUG;
    public static boolean OPLUS_PANIC;
    public static boolean OPLUS_PHONE_LOG_SWITCH;
    public static String TAG;
    private static final boolean USER_BUILD;
    public static boolean VERBOSE;
    public static boolean WARN;
    private static EventManager sEventManager;
    private static boolean sIsUserExtendedLoggingEnabled;
    private static SessionManager sSessionManager;
    private static final Object sSingletonSync;
    private static long sUserExtendedLoggingStopTime;

    static {
        NUM_DIALABLE_DIGITS_TO_LOG = Build.IS_USER ? 0 : 2;
        TAG = "TelecomFramework";
        OPLUS_PHONE_LOG_SWITCH = false;
        boolean z = SystemProperties.getBoolean("persist.sys.assert.panic", false);
        OPLUS_PANIC = z;
        DEBUG = z;
        INFO = z;
        VERBOSE = false;
        OPLUS_DEBUG = false;
        WARN = isLoggable(5);
        ERROR = isLoggable(6);
        USER_BUILD = Build.IS_USER;
        sSingletonSync = new Object();
        sIsUserExtendedLoggingEnabled = false;
        sUserExtendedLoggingStopTime = 0L;
    }

    private Log() {
    }

    public static void oplusRefreshLogSwitch(Context context) {
        if (context != null) {
            boolean z = false;
            OPLUS_PANIC = SystemProperties.getBoolean("persist.sys.assert.panic", false);
            OPLUS_PHONE_LOG_SWITCH = Settings.System.getInt(context.getContentResolver(), "phone.log.switch", 0) == 1;
            android.util.Log.i(TAG, buildMessage("TelecomFramework", "OPLUS_PANIC = " + OPLUS_PANIC + ", OPLUS_PHONE_LOG_SWITCH = " + OPLUS_PHONE_LOG_SWITCH, new Object[0]));
            boolean z2 = OPLUS_PHONE_LOG_SWITCH;
            if (z2 || OPLUS_PANIC) {
                z = true;
            }
            DEBUG = z;
            INFO = z;
            OPLUS_DEBUG = z2;
        }
    }

    public static void d(String prefix, String format, Object... args) {
        if (sIsUserExtendedLoggingEnabled) {
            maybeDisableLogging();
            android.util.Log.i(TAG, buildMessage(prefix, format, args));
        } else if (DEBUG) {
            android.util.Log.d(TAG, buildMessage(prefix, format, args));
        }
    }

    public static void d(Object objectPrefix, String format, Object... args) {
        if (sIsUserExtendedLoggingEnabled) {
            maybeDisableLogging();
            android.util.Log.i(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args));
        } else if (DEBUG) {
            android.util.Log.d(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args));
        }
    }

    public static void i(String prefix, String format, Object... args) {
        if (INFO) {
            android.util.Log.i(TAG, buildMessage(prefix, format, args));
        }
    }

    public static void i(Object objectPrefix, String format, Object... args) {
        if (INFO) {
            android.util.Log.i(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args));
        }
    }

    public static void v(String prefix, String format, Object... args) {
        if (sIsUserExtendedLoggingEnabled) {
            maybeDisableLogging();
            android.util.Log.i(TAG, buildMessage(prefix, format, args));
        } else if (VERBOSE) {
            android.util.Log.v(TAG, buildMessage(prefix, format, args));
        }
    }

    public static void v(Object objectPrefix, String format, Object... args) {
        if (sIsUserExtendedLoggingEnabled) {
            maybeDisableLogging();
            android.util.Log.i(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args));
        } else if (VERBOSE) {
            android.util.Log.v(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args));
        }
    }

    public static void w(String prefix, String format, Object... args) {
        if (WARN) {
            android.util.Log.w(TAG, buildMessage(prefix, format, args));
        }
    }

    public static void w(Object objectPrefix, String format, Object... args) {
        if (WARN) {
            android.util.Log.w(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args));
        }
    }

    public static void e(String prefix, Throwable tr, String format, Object... args) {
        if (ERROR) {
            android.util.Log.e(TAG, buildMessage(prefix, format, args), tr);
        }
    }

    public static void e(Object objectPrefix, Throwable tr, String format, Object... args) {
        if (ERROR) {
            android.util.Log.e(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args), tr);
        }
    }

    public static void wtf(String prefix, Throwable tr, String format, Object... args) {
        android.util.Log.wtf(TAG, buildMessage(prefix, format, args), tr);
    }

    public static void wtf(Object objectPrefix, Throwable tr, String format, Object... args) {
        android.util.Log.wtf(TAG, buildMessage(getPrefixFromObject(objectPrefix), format, args), tr);
    }

    public static void wtf(String prefix, String format, Object... args) {
        String msg = buildMessage(prefix, format, args);
        android.util.Log.wtf(TAG, msg, new IllegalStateException(msg));
    }

    public static void wtf(Object objectPrefix, String format, Object... args) {
        String msg = buildMessage(getPrefixFromObject(objectPrefix), format, args);
        android.util.Log.wtf(TAG, msg, new IllegalStateException(msg));
    }

    public static void setSessionContext(Context context) {
        getSessionManager().setContext(context);
    }

    public static void startSession(String shortMethodName) {
        getSessionManager().startSession(shortMethodName, null);
    }

    public static void startSession(Session.Info info, String shortMethodName) {
        getSessionManager().startSession(info, shortMethodName, null);
    }

    public static void startSession(String shortMethodName, String callerIdentification) {
        getSessionManager().startSession(shortMethodName, callerIdentification);
    }

    public static void startSession(Session.Info info, String shortMethodName, String callerIdentification) {
        getSessionManager().startSession(info, shortMethodName, callerIdentification);
    }

    public static Session createSubsession() {
        return getSessionManager().createSubsession();
    }

    public static Session.Info getExternalSession() {
        return getSessionManager().getExternalSession();
    }

    public static Session.Info getExternalSession(String ownerInfo) {
        return getSessionManager().getExternalSession(ownerInfo);
    }

    public static void cancelSubsession(Session subsession) {
        getSessionManager().cancelSubsession(subsession);
    }

    public static void continueSession(Session subsession, String shortMethodName) {
        getSessionManager().continueSession(subsession, shortMethodName);
    }

    public static void endSession() {
        getSessionManager().endSession();
    }

    public static void registerSessionListener(SessionManager.ISessionListener l) {
        getSessionManager().registerSessionListener(l);
    }

    public static String getSessionId() {
        synchronized (sSingletonSync) {
            if (sSessionManager == null) {
                return "";
            }
            return getSessionManager().getSessionId();
        }
    }

    public static void addEvent(EventManager.Loggable recordEntry, String event) {
        getEventManager().event(recordEntry, event, null);
    }

    public static void addEvent(EventManager.Loggable recordEntry, String event, Object data) {
        getEventManager().event(recordEntry, event, data);
    }

    public static void addEvent(EventManager.Loggable recordEntry, String event, String format, Object... args) {
        getEventManager().event(recordEntry, event, format, args);
    }

    public static void registerEventListener(EventManager.EventListener e) {
        getEventManager().registerEventListener(e);
    }

    public static void addRequestResponsePair(EventManager.TimedEventPair p) {
        getEventManager().addRequestResponsePair(p);
    }

    public static void dumpEvents(IndentingPrintWriter pw) {
        synchronized (sSingletonSync) {
            if (sEventManager != null) {
                getEventManager().dumpEvents(pw);
            } else {
                pw.println("No Historical Events Logged.");
            }
        }
    }

    public static void dumpEventsTimeline(IndentingPrintWriter pw) {
        synchronized (sSingletonSync) {
            if (sEventManager != null) {
                getEventManager().dumpEventsTimeline(pw);
            } else {
                pw.println("No Historical Events Logged.");
            }
        }
    }

    public static void setIsExtendedLoggingEnabled(boolean isExtendedLoggingEnabled) {
        if (sIsUserExtendedLoggingEnabled != isExtendedLoggingEnabled) {
            EventManager eventManager = sEventManager;
            if (eventManager != null) {
                eventManager.changeEventCacheSize(isExtendedLoggingEnabled ? 20 : 10);
            }
            sIsUserExtendedLoggingEnabled = isExtendedLoggingEnabled;
            if (isExtendedLoggingEnabled) {
                sUserExtendedLoggingStopTime = System.currentTimeMillis() + 1800000;
            } else {
                sUserExtendedLoggingStopTime = 0L;
            }
        }
    }

    private static EventManager getEventManager() {
        if (sEventManager == null) {
            synchronized (sSingletonSync) {
                if (sEventManager == null) {
                    EventManager eventManager = new EventManager(Log$$ExternalSyntheticLambda0.INSTANCE);
                    sEventManager = eventManager;
                    return eventManager;
                }
            }
        }
        return sEventManager;
    }

    public static SessionManager getSessionManager() {
        if (sSessionManager == null) {
            synchronized (sSingletonSync) {
                if (sSessionManager == null) {
                    SessionManager sessionManager = new SessionManager();
                    sSessionManager = sessionManager;
                    return sessionManager;
                }
            }
        }
        return sSessionManager;
    }

    public static void setTag(String tag) {
        TAG = tag;
        WARN = isLoggable(5);
        ERROR = isLoggable(6);
    }

    private static void maybeDisableLogging() {
        if (sIsUserExtendedLoggingEnabled && sUserExtendedLoggingStopTime < System.currentTimeMillis()) {
            sUserExtendedLoggingStopTime = 0L;
            sIsUserExtendedLoggingEnabled = false;
        }
    }

    public static boolean isLoggable(int level) {
        return android.util.Log.isLoggable(TAG, level);
    }

    public static String piiHandle(Object pii) {
        if (pii == null || VERBOSE) {
            return String.valueOf(pii);
        }
        StringBuilder sb = new StringBuilder();
        if (pii instanceof Uri) {
            Uri uri = (Uri) pii;
            String scheme = uri.getScheme();
            if (!TextUtils.isEmpty(scheme)) {
                sb.append(scheme);
                sb.append(SettingsStringUtil.DELIMITER);
            }
            String textToObfuscate = uri.getSchemeSpecificPart();
            if (PhoneAccount.SCHEME_TEL.equals(scheme)) {
                obfuscatePhoneNumber(sb, textToObfuscate);
            } else if ("sip".equals(scheme)) {
                for (int i = 0; i < textToObfuscate.length(); i++) {
                    char c = textToObfuscate.charAt(i);
                    if (!(c == '@' || c == '.')) {
                        c = '*';
                    }
                    sb.append(c);
                }
            } else {
                sb.append(pii(pii));
            }
        } else if (pii instanceof String) {
            String number = (String) pii;
            obfuscatePhoneNumber(sb, number);
        }
        return sb.toString();
    }

    private static void obfuscatePhoneNumber(StringBuilder sb, String phoneNumber) {
        int numDigitsToObfuscate = getDialableCount(phoneNumber) - NUM_DIALABLE_DIGITS_TO_LOG;
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            boolean isDialable = PhoneNumberUtils.isDialable(c);
            if (isDialable) {
                numDigitsToObfuscate--;
            }
            sb.append((!isDialable || numDigitsToObfuscate < 0) ? Character.valueOf(c) : "*");
        }
    }

    private static int getDialableCount(String toCount) {
        char[] charArray;
        int numDialable = 0;
        for (char c : toCount.toCharArray()) {
            if (PhoneNumberUtils.isDialable(c)) {
                numDialable++;
            }
        }
        return numDialable;
    }

    public static String pii(Object pii) {
        if (pii == null || VERBOSE) {
            return String.valueOf(pii);
        }
        return "***";
    }

    private static String getPrefixFromObject(Object obj) {
        return obj == null ? "<null>" : obj.getClass().getSimpleName();
    }

    private static String buildMessage(String prefix, String format, Object... args) {
        String sessionPostfix;
        String msg;
        String format2;
        String sessionName = null;
        if (OPLUS_DEBUG) {
            sessionName = getSessionId();
        }
        if (TextUtils.isEmpty(sessionName)) {
            sessionPostfix = "";
        } else {
            sessionPostfix = ": " + sessionName;
        }
        if (args != null) {
            try {
            } catch (IllegalFormatException ife) {
                e(TAG, (Throwable) ife, "Log: IllegalFormatException: formatString='%s' numArgs=%d", format, Integer.valueOf(args.length));
                msg = format + " (An error occurred while formatting the message.)";
            }
            if (args.length != 0) {
                format2 = String.format(Locale.US, format, args);
                msg = format2;
                return String.format(Locale.US, "%s: %s%s", prefix, msg, sessionPostfix);
            }
        }
        format2 = format;
        msg = format2;
        return String.format(Locale.US, "%s: %s%s", prefix, msg, sessionPostfix);
    }

    public static String getPackageAbbreviation(ComponentName componentName) {
        if (componentName == null) {
            return "";
        }
        return getPackageAbbreviation(componentName.getPackageName());
    }

    public static String getPackageAbbreviation(String packageName) {
        if (packageName == null) {
            return "";
        }
        return (String) Arrays.stream(packageName.split("\\.")).map(Log$$ExternalSyntheticLambda1.INSTANCE).collect(Collectors.joining(""));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getPackageAbbreviation$0(String s) {
        return s.length() == 0 ? "" : s.substring(0, 1);
    }

    public static String garbleMiddle(String input) {
        if (TextUtils.isEmpty(input) || input.length() < 2) {
            return input;
        }
        int len = input.length();
        StringBuilder rtn = new StringBuilder();
        if (len > 7) {
            rtn.append(input.substring(0, 3));
            rtn.append("****");
            rtn.append(input.substring(len - 4));
            return rtn.toString();
        } else if (len > 2) {
            rtn.append(input.substring(0, 1));
            rtn.append("****");
            rtn.append(input.substring(len - 1));
            return rtn.toString();
        } else {
            rtn.append(input.substring(0, 1));
            rtn.append("*");
            return rtn.toString();
        }
    }
}
