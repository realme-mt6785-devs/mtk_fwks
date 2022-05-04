package android.os;

import com.android.internal.os.BinderInternal;
/* loaded from: classes2.dex */
public final /* synthetic */ class Binder$$ExternalSyntheticLambda0 implements BinderInternal.WorkSourceProvider {
    public static final /* synthetic */ Binder$$ExternalSyntheticLambda0 INSTANCE = new Binder$$ExternalSyntheticLambda0();

    private /* synthetic */ Binder$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.os.BinderInternal.WorkSourceProvider
    public final int resolveWorkSourceUid(int i) {
        int callingUid;
        callingUid = Binder.getCallingUid();
        return callingUid;
    }
}
