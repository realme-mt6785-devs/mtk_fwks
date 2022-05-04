package android.app.smartspace;

import android.annotation.SystemApi;
import android.content.Context;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class SmartspaceManager {
    private final Context mContext;

    public SmartspaceManager(Context context) {
        Objects.requireNonNull(context);
        this.mContext = context;
    }

    public SmartspaceSession createSmartspaceSession(SmartspaceConfig smartspaceConfig) {
        return new SmartspaceSession(this.mContext, smartspaceConfig);
    }
}
