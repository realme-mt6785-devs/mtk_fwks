package com.android.internal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.media.MediaMetrics;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.Log;
import android.util.Slog;
import android.view.ISystemGestureExclusionListener;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.view.WindowManagerGlobal;
import android.view.WindowManagerPolicyConstants;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.widget.PointerLocationView;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class PointerLocationView extends View implements InputManager.InputDeviceListener, WindowManagerPolicyConstants.PointerEventListener {
    private static final String ALT_STRATEGY_PROPERY_KEY = "debug.velocitytracker.alt";
    private static final String GESTURE_EXCLUSION_PROP = "debug.pointerlocation.showexclusion";
    private static final String TAG = "Pointer";
    private final VelocityTracker mAltVelocity;
    private boolean mCurDown;
    private int mCurNumPointers;
    private final Paint mCurrentPointPaint;
    private int mHeaderBottom;
    private final InputManager mIm;
    private int mMaxNumPointers;
    private final Paint mPaint;
    private final Paint mPathPaint;
    private final ArrayList<PointerState> mPointers;
    private final Paint mSystemGestureExclusionPaint;
    private final Paint mSystemGestureExclusionRejectedPaint;
    private final Paint mTargetPaint;
    private final Paint mTextBackgroundPaint;
    private final Paint mTextLevelPaint;
    private final Paint mTextPaint;
    private final ViewConfiguration mVC;
    private final Paint.FontMetricsInt mTextMetrics = new Paint.FontMetricsInt();
    private int mHeaderPaddingTop = 0;
    private final MotionEvent.PointerCoords mTempCoords = new MotionEvent.PointerCoords();
    private final Region mSystemGestureExclusion = new Region();
    private final Region mSystemGestureExclusionRejected = new Region();
    private final Path mSystemGestureExclusionPath = new Path();
    private final FasterStringBuilder mText = new FasterStringBuilder();
    private boolean mPrintCoords = true;
    private IPointerLocationViewExt mPLVExt = PointerLocationViewExtPlugin.constructor.newInstance(new Object[0]);
    private RectF mReusableOvalRect = new RectF();
    private ISystemGestureExclusionListener mSystemGestureExclusionListener = new AnonymousClass1();
    private int mActivePointerId = 0;
    private final VelocityTracker mVelocity = VelocityTracker.obtain();

    /* loaded from: classes4.dex */
    public static class PointerState {
        private float mAltXVelocity;
        private float mAltYVelocity;
        private float mBoundingBottom;
        private float mBoundingLeft;
        private float mBoundingRight;
        private float mBoundingTop;
        private boolean mCurDown;
        private boolean mHasBoundingBox;
        private int mToolType;
        private int mTraceCount;
        private float mXVelocity;
        private float mYVelocity;
        private float[] mTraceX = new float[32];
        private float[] mTraceY = new float[32];
        private boolean[] mTraceCurrent = new boolean[32];
        private MotionEvent.PointerCoords mCoords = new MotionEvent.PointerCoords();
        private VelocityTracker.Estimator mEstimator = new VelocityTracker.Estimator();
        private VelocityTracker.Estimator mAltEstimator = new VelocityTracker.Estimator();

        public void clearTrace() {
            this.mTraceCount = 0;
        }

        public void addTrace(float x, float y, boolean current) {
            float[] fArr = this.mTraceX;
            int traceCapacity = fArr.length;
            int i = this.mTraceCount;
            if (i == traceCapacity) {
                int traceCapacity2 = traceCapacity * 2;
                float[] newTraceX = new float[traceCapacity2];
                System.arraycopy(fArr, 0, newTraceX, 0, i);
                this.mTraceX = newTraceX;
                float[] newTraceY = new float[traceCapacity2];
                System.arraycopy(this.mTraceY, 0, newTraceY, 0, this.mTraceCount);
                this.mTraceY = newTraceY;
                boolean[] newTraceCurrent = new boolean[traceCapacity2];
                System.arraycopy(this.mTraceCurrent, 0, newTraceCurrent, 0, this.mTraceCount);
                this.mTraceCurrent = newTraceCurrent;
            }
            float[] newTraceY2 = this.mTraceX;
            int i2 = this.mTraceCount;
            newTraceY2[i2] = x;
            this.mTraceY[i2] = y;
            this.mTraceCurrent[i2] = current;
            this.mTraceCount = i2 + 1;
        }
    }

    public PointerLocationView(Context c) {
        super(c);
        ArrayList<PointerState> arrayList = new ArrayList<>();
        this.mPointers = arrayList;
        setFocusableInTouchMode(true);
        this.mIm = (InputManager) c.getSystemService(InputManager.class);
        this.mVC = ViewConfiguration.get(c);
        Paint paint = new Paint();
        this.mTextPaint = paint;
        paint.setAntiAlias(true);
        paint.setTextSize(getResources().getDisplayMetrics().density * 10.0f);
        paint.setARGB(255, 0, 0, 0);
        Paint paint2 = new Paint();
        this.mTextBackgroundPaint = paint2;
        paint2.setAntiAlias(false);
        paint2.setARGB(128, 255, 255, 255);
        Paint paint3 = new Paint();
        this.mTextLevelPaint = paint3;
        paint3.setAntiAlias(false);
        paint3.setARGB(192, 255, 0, 0);
        Paint paint4 = new Paint();
        this.mPaint = paint4;
        paint4.setAntiAlias(true);
        paint4.setARGB(255, 255, 255, 255);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(2.0f);
        Paint paint5 = new Paint();
        this.mCurrentPointPaint = paint5;
        paint5.setAntiAlias(true);
        paint5.setARGB(255, 255, 0, 0);
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeWidth(2.0f);
        Paint paint6 = new Paint();
        this.mTargetPaint = paint6;
        paint6.setAntiAlias(false);
        paint6.setARGB(255, 0, 0, 192);
        Paint paint7 = new Paint();
        this.mPathPaint = paint7;
        paint7.setAntiAlias(false);
        paint7.setARGB(255, 0, 96, 255);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(1.0f);
        Paint paint8 = new Paint();
        this.mSystemGestureExclusionPaint = paint8;
        paint8.setARGB(25, 255, 0, 0);
        paint8.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint9 = new Paint();
        this.mSystemGestureExclusionRejectedPaint = paint9;
        paint9.setARGB(25, 0, 0, 255);
        paint9.setStyle(Paint.Style.FILL_AND_STROKE);
        PointerState ps = new PointerState();
        arrayList.add(ps);
        String altStrategy = SystemProperties.get(ALT_STRATEGY_PROPERY_KEY);
        if (altStrategy.length() != 0) {
            Log.d(TAG, "Comparing default velocity tracker strategy with " + altStrategy);
            this.mAltVelocity = VelocityTracker.obtain(altStrategy);
            return;
        }
        this.mAltVelocity = null;
    }

    public void setPrintCoords(boolean state) {
        if (this.mPLVExt.inputLogd(TAG, "force print coords!")) {
            this.mPrintCoords = true;
        } else {
            this.mPrintCoords = state;
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (insets.getDisplayCutout() != null) {
            this.mHeaderPaddingTop = insets.getDisplayCutout().getSafeInsetTop();
        } else {
            this.mHeaderPaddingTop = 0;
        }
        return super.onApplyWindowInsets(insets);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mTextPaint.getFontMetricsInt(this.mTextMetrics);
        this.mHeaderBottom = (this.mHeaderPaddingTop - this.mTextMetrics.ascent) + this.mTextMetrics.descent + 2;
    }

    private void drawOval(Canvas canvas, float x, float y, float major, float minor, float angle, Paint paint) {
        canvas.save(1);
        canvas.rotate((float) ((180.0f * angle) / 3.141592653589793d), x, y);
        this.mReusableOvalRect.left = x - (minor / 2.0f);
        this.mReusableOvalRect.right = (minor / 2.0f) + x;
        this.mReusableOvalRect.top = y - (major / 2.0f);
        this.mReusableOvalRect.bottom = (major / 2.0f) + y;
        canvas.drawOval(this.mReusableOvalRect, paint);
        canvas.restore();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:70:0x05b1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x05e3 A[SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDraw(android.graphics.Canvas r31) {
        /*
            Method dump skipped, instructions count: 1519
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.PointerLocationView.onDraw(android.graphics.Canvas):void");
    }

    private void logMotionEvent(String type, MotionEvent event) {
        int action = event.getAction();
        int N = event.getHistorySize();
        int NI = event.getPointerCount();
        for (int historyPos = 0; historyPos < N; historyPos++) {
            for (int i = 0; i < NI; i++) {
                int id = event.getPointerId(i);
                event.getHistoricalPointerCoords(i, historyPos, this.mTempCoords);
                logCoords(type, action, i, this.mTempCoords, id, event);
            }
        }
        for (int i2 = 0; i2 < NI; i2++) {
            int id2 = event.getPointerId(i2);
            event.getPointerCoords(i2, this.mTempCoords);
            logCoords(type, action, i2, this.mTempCoords, id2, event);
        }
    }

    private void logCoords(String type, int action, int index, MotionEvent.PointerCoords coords, int id, MotionEvent event) {
        String prefix;
        int toolType = event.getToolType(index);
        int buttonState = event.getButtonState();
        switch (action & 255) {
            case 0:
                prefix = "DOWN";
                break;
            case 1:
                prefix = "UP";
                break;
            case 2:
                prefix = "MOVE";
                break;
            case 3:
                prefix = "CANCEL";
                break;
            case 4:
                prefix = "OUTSIDE";
                break;
            case 5:
                if (index != ((action & 65280) >> 8)) {
                    prefix = "MOVE";
                    break;
                } else {
                    prefix = "DOWN";
                    break;
                }
            case 6:
                if (index != ((action & 65280) >> 8)) {
                    prefix = "MOVE";
                    break;
                } else {
                    prefix = "UP";
                    break;
                }
            case 7:
                prefix = "HOVER MOVE";
                break;
            case 8:
                prefix = "SCROLL";
                break;
            case 9:
                prefix = "HOVER ENTER";
                break;
            case 10:
                prefix = "HOVER EXIT";
                break;
            default:
                prefix = Integer.toString(action);
                break;
        }
        Log.i(TAG, this.mText.clear().append(type).append(" id ").append(id + 1).append(": ").append(prefix).append(" (").append(coords.x, 3).append(", ").append(coords.y, 3).append(") Pressure=").append(coords.pressure, 3).append(" Size=").append(coords.size, 3).append(" TouchMajor=").append(coords.touchMajor, 3).append(" TouchMinor=").append(coords.touchMinor, 3).append(" ToolMajor=").append(coords.toolMajor, 3).append(" ToolMinor=").append(coords.toolMinor, 3).append(" Orientation=").append((float) ((coords.orientation * 180.0f) / 3.141592653589793d), 1).append("deg").append(" Tilt=").append((float) ((coords.getAxisValue(25) * 180.0f) / 3.141592653589793d), 1).append("deg").append(" Distance=").append(coords.getAxisValue(24), 1).append(" VScroll=").append(coords.getAxisValue(9), 1).append(" HScroll=").append(coords.getAxisValue(10), 1).append(" BoundingBox=[(").append(event.getAxisValue(32), 3).append(", ").append(event.getAxisValue(33), 3).append(")").append(", (").append(event.getAxisValue(34), 3).append(", ").append(event.getAxisValue(35), 3).append(")]").append(" ToolType=").append(MotionEvent.toolTypeToString(toolType)).append(" ButtonState=").append(MotionEvent.buttonStateToString(buttonState)).toString());
    }

    @Override // android.view.WindowManagerPolicyConstants.PointerEventListener
    public void onPointerEvent(MotionEvent event) {
        int NP;
        boolean z;
        PointerState ps;
        MotionEvent.PointerCoords coords;
        MotionEvent.PointerCoords coords2;
        PointerState ps2;
        MotionEvent.PointerCoords coords3;
        int N;
        int historyPos;
        int NP2;
        int NI;
        PointerState ps3;
        int i;
        if (this.mPLVExt.enableInputLogV()) {
            Trace.traceBegin(8L, "PointerLocationView#onPointerEvent,x:" + event.getRawX() + "y:" + event.getRawY());
        }
        int action = event.getAction();
        int NP3 = this.mPointers.size();
        if (action == 0 || (action & 255) == 5) {
            int index = (action & 65280) >> 8;
            if (action == 0) {
                for (int p = 0; p < NP3; p++) {
                    PointerState ps4 = this.mPointers.get(p);
                    ps4.clearTrace();
                    ps4.mCurDown = false;
                }
                this.mCurDown = true;
                this.mCurNumPointers = 0;
                this.mMaxNumPointers = 0;
                this.mVelocity.clear();
                VelocityTracker velocityTracker = this.mAltVelocity;
                if (velocityTracker != null) {
                    velocityTracker.clear();
                }
            }
            int i2 = this.mCurNumPointers + 1;
            this.mCurNumPointers = i2;
            if (this.mMaxNumPointers < i2) {
                this.mMaxNumPointers = i2;
            }
            int id = event.getPointerId(index);
            while (NP3 <= id) {
                this.mPointers.add(new PointerState());
                NP3++;
            }
            int i3 = this.mActivePointerId;
            if (i3 < 0 || (i3 < NP3 && !this.mPointers.get(i3).mCurDown)) {
                this.mActivePointerId = id;
            }
            PointerState ps5 = this.mPointers.get(id);
            ps5.mCurDown = true;
            InputDevice device = InputDevice.getDevice(event.getDeviceId());
            ps5.mHasBoundingBox = (device == null || device.getMotionRange(32) == null) ? false : true;
            NP = NP3;
        } else {
            NP = NP3;
        }
        int NI2 = event.getPointerCount();
        this.mVelocity.addMovement(event);
        this.mVelocity.computeCurrentVelocity(1);
        VelocityTracker velocityTracker2 = this.mAltVelocity;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(event);
            this.mAltVelocity.computeCurrentVelocity(1);
        }
        int N2 = event.getHistorySize();
        int historyPos2 = 0;
        while (historyPos2 < N2) {
            int i4 = 0;
            while (i4 < NI2) {
                int id2 = event.getPointerId(i4);
                if (!this.mCurDown || id2 >= NP) {
                    ps2 = null;
                } else {
                    ps2 = this.mPointers.get(id2);
                }
                if (ps2 != null) {
                    coords3 = ps2.mCoords;
                } else {
                    coords3 = this.mTempCoords;
                }
                event.getHistoricalPointerCoords(i4, historyPos2, coords3);
                if (this.mPrintCoords) {
                    ps3 = ps2;
                    i = i4;
                    historyPos = historyPos2;
                    N = N2;
                    NI = NI2;
                    NP2 = NP;
                    logCoords(TAG, action, i, coords3, id2, event);
                } else {
                    ps3 = ps2;
                    i = i4;
                    historyPos = historyPos2;
                    N = N2;
                    NI = NI2;
                    NP2 = NP;
                }
                if (ps3 != null) {
                    ps3.addTrace(coords3.x, coords3.y, false);
                }
                i4 = i + 1;
                NI2 = NI;
                NP = NP2;
                historyPos2 = historyPos;
                N2 = N;
            }
            historyPos2++;
        }
        for (int i5 = 0; i5 < NI2; i5++) {
            int id3 = event.getPointerId(i5);
            if (!this.mCurDown || id3 >= NP) {
                ps = null;
            } else {
                ps = this.mPointers.get(id3);
            }
            if (ps != null) {
                coords = ps.mCoords;
            } else {
                coords = this.mTempCoords;
            }
            event.getPointerCoords(i5, coords);
            if (this.mPrintCoords) {
                coords2 = coords;
                logCoords(TAG, action, i5, coords, id3, event);
            } else {
                coords2 = coords;
            }
            if (ps != null) {
                ps.addTrace(coords2.x, coords2.y, true);
                ps.mXVelocity = this.mVelocity.getXVelocity(id3);
                ps.mYVelocity = this.mVelocity.getYVelocity(id3);
                this.mVelocity.getEstimator(id3, ps.mEstimator);
                VelocityTracker velocityTracker3 = this.mAltVelocity;
                if (velocityTracker3 != null) {
                    ps.mAltXVelocity = velocityTracker3.getXVelocity(id3);
                    ps.mAltYVelocity = this.mAltVelocity.getYVelocity(id3);
                    this.mAltVelocity.getEstimator(id3, ps.mAltEstimator);
                }
                ps.mToolType = event.getToolType(i5);
                if (ps.mHasBoundingBox) {
                    ps.mBoundingLeft = event.getAxisValue(32, i5);
                    ps.mBoundingTop = event.getAxisValue(33, i5);
                    ps.mBoundingRight = event.getAxisValue(34, i5);
                    ps.mBoundingBottom = event.getAxisValue(35, i5);
                }
            }
        }
        if (this.mPLVExt.enableInputLogV()) {
            Trace.traceEnd(8L);
        }
        if (action == 1 || action == 3 || (action & 255) == 6) {
            int index2 = (65280 & action) >> 8;
            int id4 = event.getPointerId(index2);
            if (id4 >= NP) {
                Slog.wtf(TAG, "Got pointer ID out of bounds: id=" + id4 + " arraysize=" + NP + " pointerindex=" + index2 + " action=0x" + Integer.toHexString(action));
                return;
            }
            PointerState ps6 = this.mPointers.get(id4);
            ps6.mCurDown = false;
            int i6 = 1;
            if (action == 1) {
                z = false;
            } else if (action == 3) {
                z = false;
            } else {
                this.mCurNumPointers--;
                if (this.mActivePointerId == id4) {
                    if (index2 != 0) {
                        i6 = 0;
                    }
                    this.mActivePointerId = event.getPointerId(i6);
                }
                ps6.addTrace(Float.NaN, Float.NaN, false);
            }
            this.mCurDown = z;
            int i7 = z ? 1 : 0;
            int i8 = z ? 1 : 0;
            this.mCurNumPointers = i7;
        }
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        onPointerEvent(event);
        if (event.getAction() != 0 || isFocused()) {
            return true;
        }
        requestFocus();
        return true;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent event) {
        int source = event.getSource();
        if ((source & 2) != 0) {
            onPointerEvent(event);
            return true;
        } else if ((source & 16) != 0) {
            logMotionEvent("Joystick", event);
            return true;
        } else if ((source & 8) != 0) {
            logMotionEvent("Position", event);
            return true;
        } else {
            logMotionEvent("Generic", event);
            return true;
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!shouldLogKey(keyCode)) {
            return super.onKeyDown(keyCode, event);
        }
        int repeatCount = event.getRepeatCount();
        if (repeatCount == 0) {
            Log.i(TAG, "Key Down: " + event);
            return true;
        }
        Log.i(TAG, "Key Repeat #" + repeatCount + ": " + event);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!shouldLogKey(keyCode)) {
            return super.onKeyUp(keyCode, event);
        }
        Log.i(TAG, "Key Up: " + event);
        return true;
    }

    private static boolean shouldLogKey(int keyCode) {
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                return true;
            default:
                if (KeyEvent.isGamepadButton(keyCode) || KeyEvent.isModifierKey(keyCode)) {
                    return true;
                }
                return false;
        }
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent event) {
        logMotionEvent("Trackball", event);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIm.registerInputDeviceListener(this, getHandler());
        if (shouldShowSystemGestureExclusion()) {
            try {
                WindowManagerGlobal.getWindowManagerService().registerSystemGestureExclusionListener(this.mSystemGestureExclusionListener, this.mContext.getDisplayId());
                int alpha = systemGestureExclusionOpacity();
                this.mSystemGestureExclusionPaint.setAlpha(alpha);
                this.mSystemGestureExclusionRejectedPaint.setAlpha(alpha);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            this.mSystemGestureExclusion.setEmpty();
        }
        logInputDevices();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIm.unregisterInputDeviceListener(this);
        try {
            WindowManagerGlobal.getWindowManagerService().unregisterSystemGestureExclusionListener(this.mSystemGestureExclusionListener, this.mContext.getDisplayId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceAdded(int deviceId) {
        logInputDeviceState(deviceId, "Device Added");
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceChanged(int deviceId) {
        logInputDeviceState(deviceId, "Device Changed");
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceRemoved(int deviceId) {
        logInputDeviceState(deviceId, "Device Removed");
    }

    private void logInputDevices() {
        int[] deviceIds = InputDevice.getDeviceIds();
        for (int i : deviceIds) {
            logInputDeviceState(i, "Device Enumerated");
        }
    }

    private void logInputDeviceState(int deviceId, String state) {
        InputDevice device = this.mIm.getInputDevice(deviceId);
        if (device != null) {
            Log.i(TAG, state + ": " + device);
            return;
        }
        Log.i(TAG, state + ": " + deviceId);
    }

    private static boolean shouldShowSystemGestureExclusion() {
        return systemGestureExclusionOpacity() > 0;
    }

    private static int systemGestureExclusionOpacity() {
        int x = SystemProperties.getInt(GESTURE_EXCLUSION_PROP, 0);
        if (x < 0 || x > 255) {
            return 0;
        }
        return x;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class FasterStringBuilder {
        private char[] mChars = new char[64];
        private int mLength;

        public FasterStringBuilder clear() {
            this.mLength = 0;
            return this;
        }

        public FasterStringBuilder append(String value) {
            int valueLength = value.length();
            int index = reserve(valueLength);
            value.getChars(0, valueLength, this.mChars, index);
            this.mLength += valueLength;
            return this;
        }

        public FasterStringBuilder append(int value) {
            return append(value, 0);
        }

        public FasterStringBuilder append(int value, int zeroPadWidth) {
            boolean negative = value < 0;
            if (!negative || (value = -value) >= 0) {
                int index = reserve(11);
                char[] chars = this.mChars;
                if (value == 0) {
                    int i = index + 1;
                    chars[index] = '0';
                    this.mLength++;
                    return this;
                }
                if (negative) {
                    chars[index] = '-';
                    index++;
                }
                int divisor = 1000000000;
                int numberWidth = 10;
                while (value < divisor) {
                    divisor /= 10;
                    numberWidth--;
                    if (numberWidth < zeroPadWidth) {
                        chars[index] = '0';
                        index++;
                    }
                }
                while (true) {
                    int digit = value / divisor;
                    value -= digit * divisor;
                    divisor /= 10;
                    int index2 = index + 1;
                    chars[index] = (char) (digit + 48);
                    if (divisor == 0) {
                        this.mLength = index2;
                        return this;
                    }
                    index = index2;
                }
            } else {
                append("-2147483648");
                return this;
            }
        }

        public FasterStringBuilder append(float value, int precision) {
            int scale = 1;
            for (int i = 0; i < precision; i++) {
                scale *= 10;
            }
            float value2 = (float) (Math.rint(scale * value) / scale);
            if (((int) value2) == 0 && value2 < 0.0f) {
                append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            }
            append((int) value2);
            if (precision != 0) {
                append(MediaMetrics.SEPARATOR);
                float value3 = Math.abs(value2);
                append((int) (scale * ((float) (value3 - Math.floor(value3)))), precision);
            }
            return this;
        }

        public String toString() {
            return new String(this.mChars, 0, this.mLength);
        }

        private int reserve(int length) {
            int oldLength = this.mLength;
            int newLength = this.mLength + length;
            char[] oldChars = this.mChars;
            int oldCapacity = oldChars.length;
            if (newLength > oldCapacity) {
                int newCapacity = oldCapacity * 2;
                char[] newChars = new char[newCapacity];
                System.arraycopy(oldChars, 0, newChars, 0, oldLength);
                this.mChars = newChars;
            }
            return oldLength;
        }
    }

    /* renamed from: com.android.internal.widget.PointerLocationView$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    class AnonymousClass1 extends ISystemGestureExclusionListener.Stub {
        AnonymousClass1() {
        }

        @Override // android.view.ISystemGestureExclusionListener
        public void onSystemGestureExclusionChanged(int displayId, Region systemGestureExclusion, Region systemGestureExclusionUnrestricted) {
            final Region exclusion = Region.obtain(systemGestureExclusion);
            final Region rejected = Region.obtain();
            if (systemGestureExclusionUnrestricted != null) {
                rejected.set(systemGestureExclusionUnrestricted);
                rejected.op(exclusion, Region.Op.DIFFERENCE);
            }
            Handler handler = PointerLocationView.this.getHandler();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.android.internal.widget.PointerLocationView$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PointerLocationView.AnonymousClass1.this.lambda$onSystemGestureExclusionChanged$0$PointerLocationView$1(exclusion, rejected);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSystemGestureExclusionChanged$0$PointerLocationView$1(Region exclusion, Region rejected) {
            PointerLocationView.this.mSystemGestureExclusion.set(exclusion);
            PointerLocationView.this.mSystemGestureExclusionRejected.set(rejected);
            exclusion.recycle();
            PointerLocationView.this.invalidate();
        }
    }
}
