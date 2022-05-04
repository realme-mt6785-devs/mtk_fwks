package android.service.autofill;

import android.content.Context;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
/* loaded from: classes3.dex */
public class InlineSuggestionRoot extends FrameLayout {
    private static final String TAG = "InlineSuggestionRoot";
    private final IInlineSuggestionUiCallback mCallback;
    private float mDownX;
    private float mDownY;
    private final int mTouchSlop;

    public InlineSuggestionRoot(Context context, IInlineSuggestionUiCallback callback) {
        super(context);
        this.mCallback = callback;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setFocusable(false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            switch(r0) {
                case 0: goto L_0x0008;
                case 1: goto L_0x0007;
                case 2: goto L_0x0014;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0056
        L_0x0008:
            float r0 = r6.getX()
            r5.mDownX = r0
            float r0 = r6.getY()
            r5.mDownY = r0
        L_0x0014:
            float r0 = r5.mDownX
            float r1 = r5.mDownY
            float r2 = r6.getX()
            float r3 = r6.getY()
            float r0 = android.util.MathUtils.dist(r0, r1, r2, r3)
            int r1 = r6.getFlags()
            r1 = r1 & 2
            if (r1 != 0) goto L_0x002e
            r1 = 1
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            if (r1 == 0) goto L_0x0038
            int r2 = r5.mTouchSlop
            float r2 = (float) r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0056
        L_0x0038:
            android.service.autofill.IInlineSuggestionUiCallback r2 = r5.mCallback     // Catch: RemoteException -> 0x004e
            android.view.ViewRootImpl r3 = r5.getViewRootImpl()     // Catch: RemoteException -> 0x004e
            android.os.IBinder r3 = r3.getInputToken()     // Catch: RemoteException -> 0x004e
            android.content.Context r4 = r5.getContext()     // Catch: RemoteException -> 0x004e
            int r4 = r4.getDisplayId()     // Catch: RemoteException -> 0x004e
            r2.onTransferTouchFocusToImeWindow(r3, r4)     // Catch: RemoteException -> 0x004e
            goto L_0x0056
        L_0x004e:
            r2 = move-exception
            java.lang.String r3 = "InlineSuggestionRoot"
            java.lang.String r4 = "RemoteException transferring touch focus to IME"
            android.util.Log.w(r3, r4)
        L_0x0056:
            boolean r0 = super.dispatchTouchEvent(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.service.autofill.InlineSuggestionRoot.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }
}
