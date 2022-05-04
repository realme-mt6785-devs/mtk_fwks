package android.hardware.soundtrigger;

import android.os.IBinder;
/* loaded from: classes2.dex */
public final /* synthetic */ class SoundTrigger$$ExternalSyntheticLambda0 implements IBinder.DeathRecipient {
    public static final /* synthetic */ SoundTrigger$$ExternalSyntheticLambda0 INSTANCE = new SoundTrigger$$ExternalSyntheticLambda0();

    private /* synthetic */ SoundTrigger$$ExternalSyntheticLambda0() {
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        SoundTrigger.lambda$getService$0();
    }
}
