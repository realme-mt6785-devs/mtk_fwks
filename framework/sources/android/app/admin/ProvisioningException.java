package android.app.admin;

import android.util.AndroidException;
/* loaded from: classes.dex */
public class ProvisioningException extends AndroidException {
    private final int mProvisioningResult;

    public ProvisioningException(Exception cause, int provisioningResult) {
        super(cause);
        this.mProvisioningResult = provisioningResult;
    }

    public int getProvisioningResult() {
        return this.mProvisioningResult;
    }
}
