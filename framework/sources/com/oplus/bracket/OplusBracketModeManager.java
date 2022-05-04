package com.oplus.bracket;

import android.app.OplusActivityManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.MotionEvent;
import android.view.View;
import com.android.internal.policy.DecorView;
import com.oplus.bracket.IOplusBracketModeChangedListener;
/* loaded from: classes4.dex */
public class OplusBracketModeManager {
    public static final int CLIENT_DISABLE_TOUCH = 4;
    public static final int CLIENT_ENABLE_TOUCH = 3;
    public static final int CLIENT_ENTER_BRACKET = 1;
    public static final int CLIENT_EXIT_BRACKET = 2;
    public static final int CLIENT_INVISIBLE_TOUCH = 6;
    public static final int CLIENT_VISIBLE_TOUCH = 5;
    public static final int SEVER_QUEST_DCS_TOUCH = 4;
    public static final int SEVER_QUEST_TOUCH = 3;
    public static final String TAG = "OplusBracketModeManager";
    public static final int WINDOWING_MODE_BRACKET = 115;
    public static final int WINDOW_TYPE_MODE_BRACKET = 2115;
    private static volatile OplusBracketModeManager sInstance;
    private boolean mInBracketMode;
    private IOplusBracketWindowObserver mObserver;
    private final Object mlock = new Object();
    private Rect mBracketRect = new Rect();
    private boolean mTouchModeEnable = false;
    private boolean mHasRegister = false;
    private boolean mUpdateDCS = false;
    private InnerListener mInnerListener = new InnerListener();
    private OplusActivityManager mOAms = new OplusActivityManager();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class InnerListener extends IOplusBracketModeChangedListener.Stub {
        private InnerListener() {
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onBracketModeChanged(int type) {
            OplusBracketLog.d(OplusBracketModeManager.TAG, "onBracketModeChanged: " + OplusBracketModeManager.modeChangeReason(type));
            switch (type) {
                case 3:
                    OplusBracketModeManager.this.mTouchModeEnable = true;
                    return;
                case 4:
                    OplusBracketModeManager.this.mTouchModeEnable = false;
                    return;
                default:
                    return;
            }
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onBracketRegionChange(Rect rect) {
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onBindService(IOplusBracketWindowObserver oplusBracketWindowObserver) {
            OplusBracketModeManager.this.mObserver = oplusBracketWindowObserver;
            OplusBracketModeManager.this.onSurfaceViewShow(3);
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onUnBindService(int reason) {
            OplusBracketModeManager.this.mObserver = null;
            OplusBracketModeManager.this.mUpdateDCS = false;
        }
    }

    public static OplusBracketModeManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusBracketModeManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusBracketModeManager();
                }
            }
        }
        return sInstance;
    }

    public boolean addOnConfigChangedListener(IOplusBracketModeChangedListener listener) {
        OplusActivityManager oplusActivityManager;
        OplusBracketLog.i(TAG, "addOnConfigChangedListener listener = " + listener);
        synchronized (this.mlock) {
            try {
                try {
                    oplusActivityManager = this.mOAms;
                } catch (RemoteException e) {
                    OplusBracketLog.e(TAG, "addOnConfigChangedListener remoteException ");
                    e.printStackTrace();
                }
                if (oplusActivityManager == null) {
                    return false;
                }
                return oplusActivityManager.addBracketWindowConfigChangedListener(listener);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean removeOnConfigChangedListener(IOplusBracketModeChangedListener listener) {
        OplusActivityManager oplusActivityManager;
        OplusBracketLog.i(TAG, "removeOnConfigChangedListener listener = " + listener);
        synchronized (this.mlock) {
            try {
                try {
                    oplusActivityManager = this.mOAms;
                } catch (RemoteException e) {
                    OplusBracketLog.e(TAG, "removeOnConfigChangedListener remoteException ");
                    e.printStackTrace();
                }
                if (oplusActivityManager == null) {
                    return false;
                }
                return oplusActivityManager.removeBracketWindowConfigChangedListener(listener);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean updateInputEventInTouchPanel(View view, InputEvent event, InputEventReceiver inputEventReceiver) {
        if (view != null && this.mTouchModeEnable && this.mInBracketMode && (view instanceof DecorView) && (event instanceof MotionEvent)) {
            MotionEvent ev = (MotionEvent) event;
            if (ev.getY() > view.getHeight()) {
                ev.setLocation(ev.getX(), ev.getY() - view.getHeight());
                ev.setTainted(true);
                updateDCSTouch();
                return false;
            }
        }
        return false;
    }

    public boolean disableOnClick(View view, InputEvent event) {
        if (view != null && this.mTouchModeEnable && this.mInBracketMode && (event instanceof MotionEvent)) {
            return event.isTainted();
        }
        return false;
    }

    public void onConfigChange(View view, Configuration configuration) {
        if (view != null && (view instanceof DecorView)) {
            boolean z = configuration.windowConfiguration.getWindowingMode() == 115;
            this.mInBracketMode = z;
            if (z && !this.mHasRegister) {
                this.mBracketRect.set(configuration.windowConfiguration.getAppBounds());
            }
            if (this.mInBracketMode && !this.mHasRegister) {
                addOnConfigChangedListener(this.mInnerListener);
                this.mHasRegister = true;
            }
            if (this.mHasRegister && !this.mInBracketMode) {
                removeOnConfigChangedListener(this.mInnerListener);
                this.mHasRegister = false;
            }
        }
    }

    public void onSurfaceViewShow(int msg) {
        IOplusBracketWindowObserver iOplusBracketWindowObserver = this.mObserver;
        if (iOplusBracketWindowObserver != null) {
            try {
                iOplusBracketWindowObserver.onSurfaceViewShow(this.mInnerListener, msg);
            } catch (Exception e) {
                OplusBracketLog.d(TAG, "setSurfaceViewFlag: ");
                e.printStackTrace();
            }
        }
    }

    public void updateDCSTouch() {
        if (!this.mUpdateDCS) {
            onSurfaceViewShow(4);
            this.mUpdateDCS = true;
        }
    }

    public static String modeChangeReason(int reason) {
        switch (reason) {
            case 1:
                return "CLIENT_ENTER_BRACKET";
            case 2:
                return "CLIENT_EXIT_BRACKET";
            case 3:
                return "CLIENT_ENABLE_TOUCH";
            case 4:
                return "CLIENT_DISABLE_TOUCH";
            case 5:
                return "CLIENT_VISIBLE_TOUCH";
            case 6:
                return "CLIENT_INVISIBLE_TOUCH";
            default:
                return String.valueOf(reason);
        }
    }
}
