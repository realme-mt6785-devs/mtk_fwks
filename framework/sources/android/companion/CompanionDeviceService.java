package android.companion;

import android.app.Service;
import android.companion.ICompanionDeviceService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class CompanionDeviceService extends Service {
    private static final String LOG_TAG = "CompanionDeviceService";
    public static final String SERVICE_INTERFACE = "android.companion.CompanionDeviceService";
    private final Stub mRemote = new Stub();

    public abstract void onDeviceAppeared(String str);

    public abstract void onDeviceDisappeared(String str);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (Objects.equals(intent.getAction(), SERVICE_INTERFACE)) {
            return this.mRemote;
        }
        Log.w(LOG_TAG, "Tried to bind to wrong intent (should be android.companion.CompanionDeviceService): " + intent);
        return null;
    }

    /* loaded from: classes.dex */
    class Stub extends ICompanionDeviceService.Stub {
        Stub() {
        }

        @Override // android.companion.ICompanionDeviceService
        public void onDeviceAppeared(String address) {
            Handler.getMain().sendMessage(PooledLambda.obtainMessage(CompanionDeviceService$Stub$$ExternalSyntheticLambda0.INSTANCE, CompanionDeviceService.this, address));
        }

        @Override // android.companion.ICompanionDeviceService
        public void onDeviceDisappeared(String address) {
            Handler.getMain().sendMessage(PooledLambda.obtainMessage(CompanionDeviceService$Stub$$ExternalSyntheticLambda1.INSTANCE, CompanionDeviceService.this, address));
        }
    }
}
