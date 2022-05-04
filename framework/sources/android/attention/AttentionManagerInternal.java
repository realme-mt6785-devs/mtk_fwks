package android.attention;
/* loaded from: classes.dex */
public abstract class AttentionManagerInternal {

    /* loaded from: classes.dex */
    public static abstract class AttentionCallbackInternal {
        public abstract void onFailure(int i);

        public abstract void onSuccess(int i, long j);
    }

    public abstract void cancelAttentionCheck(AttentionCallbackInternal attentionCallbackInternal);

    public abstract boolean checkAttention(long j, AttentionCallbackInternal attentionCallbackInternal);

    public abstract boolean isAttentionServiceSupported();
}
