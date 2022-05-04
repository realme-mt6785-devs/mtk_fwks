package com.oplus.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class Alog {
    private static final List<Logger> FOREST = new ArrayList();
    private static final Logger SOULS = new Logger() { // from class: com.oplus.utils.Alog.1
        @Override // com.oplus.utils.Alog.Logger
        public void i(String message) {
            for (Logger logger : Alog.FOREST) {
                logger.i(message);
            }
        }

        @Override // com.oplus.utils.Alog.Logger
        public void i(String message, Throwable t) {
            for (Logger logger : Alog.FOREST) {
                logger.i(message, t);
            }
        }

        @Override // com.oplus.utils.Alog.Logger
        public void d(String message) {
            for (Logger logger : Alog.FOREST) {
                logger.d(message);
            }
        }

        @Override // com.oplus.utils.Alog.Logger
        public void d(String message, Throwable t) {
            for (Logger logger : Alog.FOREST) {
                logger.d(message, t);
            }
        }

        @Override // com.oplus.utils.Alog.Logger
        public void e(String message) {
            for (Logger logger : Alog.FOREST) {
                logger.e(message);
            }
        }

        @Override // com.oplus.utils.Alog.Logger
        public void e(String message, Throwable t) {
            for (Logger logger : Alog.FOREST) {
                logger.e(message, t);
            }
        }

        @Override // com.oplus.utils.Alog.Logger
        protected void log(int priority, String tag, String message, Throwable t) {
            throw new AssertionError("Should not handle log method by myself");
        }
    };

    public static void i(String message) {
        SOULS.i(message);
    }

    public static void i(String message, Throwable t) {
        SOULS.i(message, t);
    }

    public static void d(String message) {
        SOULS.d(message);
    }

    public static void d(String message, Throwable t) {
        SOULS.d(message, t);
    }

    public static void e(String message) {
        SOULS.e(message);
    }

    public static void e(String message, Throwable t) {
        SOULS.e(message, t);
    }

    public static void addLogger(Logger logger) {
        List<Logger> list = FOREST;
        synchronized (list) {
            list.add(logger);
        }
    }

    public static void clearLogger() {
        List<Logger> list = FOREST;
        synchronized (list) {
            list.clear();
        }
    }

    public static void tag(String tag) {
        List<Logger> list = FOREST;
        synchronized (list) {
            for (Logger logger : list) {
                logger.explicitTag.set(tag);
            }
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Logger {
        public static final int ASSERT = 7;
        public static final int DEBUG = 3;
        public static final int ERROR = 6;
        public static final int INFO = 4;
        public static final int VERBOSE = 2;
        public static final int WARN = 5;
        final ThreadLocal<String> explicitTag = new ThreadLocal<>();

        protected abstract void log(int i, String str, String str2, Throwable th);

        public void i(String message) {
            prepareLog(4, null, message, new Object[0]);
        }

        public void i(String message, Throwable t) {
            prepareLog(4, t, message, new Object[0]);
        }

        public void d(String message) {
            prepareLog(3, null, message, new Object[0]);
        }

        public void d(String message, Throwable t) {
            prepareLog(3, t, message, new Object[0]);
        }

        public void e(String message) {
            prepareLog(6, null, message, new Object[0]);
        }

        public void e(String message, Throwable t) {
            prepareLog(6, t, message, new Object[0]);
        }

        String getTag() {
            String tag = this.explicitTag.get();
            if (tag != null) {
                this.explicitTag.remove();
            }
            return tag;
        }

        void prepareLog(int priority, Throwable t, String message, Object... args) {
            String tag = getTag();
            if (message != null && message.length() == 0) {
                message = null;
            }
            if (message != null) {
                if (args != null && args.length > 0) {
                    message = formatMessage(message, args);
                }
                if (t != null) {
                    message = message + "\n" + getStackTraceString(t);
                }
            } else if (t != null) {
                message = getStackTraceString(t);
            } else {
                return;
            }
            log(priority, tag, message, t);
        }

        private String getStackTraceString(Throwable t) {
            StringWriter sw = new StringWriter(256);
            PrintWriter pw = new PrintWriter((Writer) sw, false);
            t.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        }

        protected String formatMessage(String message, Object[] args) {
            return String.format(message, args);
        }
    }
}
