package android.os;
/* loaded from: classes2.dex */
public final class ThreadLocalWorkSource {
    public static final int UID_NONE = -1;
    private static final ThreadLocal<Integer> sWorkSourceUid = ThreadLocal.withInitial(ThreadLocalWorkSource$$ExternalSyntheticLambda0.INSTANCE);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$static$0() {
        return -1;
    }

    public static int getUid() {
        return sWorkSourceUid.get().intValue();
    }

    public static long setUid(int uid) {
        long token = getToken();
        sWorkSourceUid.set(Integer.valueOf(uid));
        return token;
    }

    public static void restore(long token) {
        sWorkSourceUid.set(Integer.valueOf(parseUidFromToken(token)));
    }

    public static long clear() {
        return setUid(-1);
    }

    private static int parseUidFromToken(long token) {
        return (int) token;
    }

    private static long getToken() {
        return sWorkSourceUid.get().intValue();
    }

    private ThreadLocalWorkSource() {
    }
}
