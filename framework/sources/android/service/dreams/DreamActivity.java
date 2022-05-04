package android.service.dreams;

import android.app.Activity;
import android.os.Bundle;
import android.service.dreams.DreamService;
import com.android.internal.R;
/* loaded from: classes3.dex */
public class DreamActivity extends Activity {
    static final String EXTRA_CALLBACK = "binder";

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DreamService.DreamServiceWrapper callback = (DreamService.DreamServiceWrapper) getIntent().getIBinderExtra("binder");
        if (callback != null) {
            callback.onActivityCreated(this);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.dream_activity_open_enter, R.anim.dream_activity_open_exit);
    }

    @Override // android.app.Activity
    public void finishAndRemoveTask() {
        super.finishAndRemoveTask();
        overridePendingTransition(0, R.anim.dream_activity_close_exit);
    }
}
