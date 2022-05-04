package android.security;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.security.IGenerateRkpKeyService;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class GenerateRkpKey {
    private static final int NOTIFY_EMPTY = 0;
    private static final int NOTIFY_KEY_GENERATED = 1;
    private static final String TAG = "GenerateRkpKey";
    private static final int TIMEOUT_MS = 1000;
    private IGenerateRkpKeyService mBinder;
    private ServiceConnection mConnection = new ServiceConnection() { // from class: android.security.GenerateRkpKey.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            GenerateRkpKey.this.mBinder = IGenerateRkpKeyService.Stub.asInterface(service);
            GenerateRkpKey.this.mCountDownLatch.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName className) {
            GenerateRkpKey.this.mCountDownLatch.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            GenerateRkpKey.this.mBinder = null;
        }
    };
    private Context mContext;
    private CountDownLatch mCountDownLatch;

    public GenerateRkpKey(Context context) {
        this.mContext = context;
    }

    private void bindAndSendCommand(int command, int securityLevel) throws RemoteException {
        Intent intent = new Intent(IGenerateRkpKeyService.class.getName());
        ComponentName comp = intent.resolveSystemService(this.mContext.getPackageManager(), 0);
        if (comp != null) {
            intent.setComponent(comp);
            this.mCountDownLatch = new CountDownLatch(1);
            if (this.mContext.bindService(intent, this.mConnection, 1)) {
                try {
                    this.mCountDownLatch.await(1000L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Log.e(TAG, "Interrupted: ", e);
                }
                IGenerateRkpKeyService iGenerateRkpKeyService = this.mBinder;
                if (iGenerateRkpKeyService != null) {
                    switch (command) {
                        case 0:
                            iGenerateRkpKeyService.generateKey(securityLevel);
                            break;
                        case 1:
                            iGenerateRkpKeyService.notifyKeyGenerated(securityLevel);
                            break;
                        default:
                            Log.e(TAG, "Invalid case for command");
                            break;
                    }
                } else {
                    Log.e(TAG, "Binder object is null; failed to bind to GenerateRkpKeyService.");
                }
                this.mContext.unbindService(this.mConnection);
                return;
            }
            throw new RemoteException("Failed to bind to GenerateRkpKeyService");
        }
    }

    public void notifyEmpty(int securityLevel) throws RemoteException {
        bindAndSendCommand(0, securityLevel);
    }

    public void notifyKeyGenerated(int securityLevel) throws RemoteException {
        bindAndSendCommand(1, securityLevel);
    }
}
