package android.view;

import android.content.res.CompatibilityInfo;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.SparseSetArray;
import android.util.proto.ProtoOutputStream;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public class InsetsAnimationControlImpl implements WindowInsetsAnimationController, InsetsAnimationControlRunner {
    private static final String TAG = "InsetsAnimationCtrlImpl";
    private final WindowInsetsAnimation mAnimation;
    private final int mAnimationType;
    private boolean mCancelled;
    private final InsetsAnimationControlCallbacks mController;
    private int mControllingTypes;
    private final SparseArray<InsetsSourceControl> mControls;
    private Insets mCurrentInsets;
    private boolean mFinished;
    private final boolean mHasZeroInsetsIme;
    private final Insets mHiddenInsets;
    private final InsetsState mInitialInsetsState;
    private final int mLayoutInsetsDuringAnimation;
    private final WindowInsetsAnimationControlListener mListener;
    private float mPendingFraction;
    private Insets mPendingInsets;
    private Boolean mPerceptible;
    public boolean mReadyDispatched;
    private final Insets mShownInsets;
    private boolean mShownOnFinish;
    private final SparseSetArray<InsetsSourceControl> mSideControlsMap;
    private final CompatibilityInfo.Translator mTranslator;
    private final int mTypes;
    private final Rect mTmpFrame = new Rect();
    private final Matrix mTmpMatrix = new Matrix();
    private float mCurrentAlpha = 1.0f;
    private float mPendingAlpha = 1.0f;

    public InsetsAnimationControlImpl(SparseArray<InsetsSourceControl> controls, Rect frame, InsetsState state, WindowInsetsAnimationControlListener listener, int types, InsetsAnimationControlCallbacks controller, long durationMs, Interpolator interpolator, int animationType, int layoutInsetsDuringAnimation, CompatibilityInfo.Translator translator) {
        SparseIntArray typeSideMap;
        SparseSetArray<InsetsSourceControl> sparseSetArray = new SparseSetArray<>();
        this.mSideControlsMap = sparseSetArray;
        this.mControls = controls;
        this.mListener = listener;
        this.mTypes = types;
        this.mControllingTypes = types;
        this.mController = controller;
        InsetsState insetsState = new InsetsState(state, true, true);
        this.mInitialInsetsState = insetsState;
        if (frame != null) {
            SparseIntArray typeSideMap2 = new SparseIntArray();
            this.mCurrentInsets = getInsetsFromState(insetsState, frame, null);
            this.mHiddenInsets = calculateInsets(insetsState, frame, controls, false, null);
            Insets calculateInsets = calculateInsets(insetsState, frame, controls, true, typeSideMap2);
            this.mShownInsets = calculateInsets;
            boolean z = calculateInsets.bottom == 0 && controlsInternalType(19);
            this.mHasZeroInsetsIme = z;
            if (z) {
                typeSideMap = typeSideMap2;
                typeSideMap.put(19, 3);
            } else {
                typeSideMap = typeSideMap2;
            }
            buildSideControlsMap(typeSideMap, sparseSetArray, controls);
        } else {
            this.mCurrentInsets = calculateInsets(insetsState, controls, true);
            this.mHiddenInsets = calculateInsets(null, controls, false);
            Insets calculateInsets2 = calculateInsets(null, controls, true);
            this.mShownInsets = calculateInsets2;
            this.mHasZeroInsetsIme = calculateInsets2.bottom == 0 && controlsInternalType(19);
            buildSideControlsMap(sparseSetArray, controls);
        }
        this.mPendingInsets = this.mCurrentInsets;
        WindowInsetsAnimation windowInsetsAnimation = new WindowInsetsAnimation(types, interpolator, durationMs);
        this.mAnimation = windowInsetsAnimation;
        windowInsetsAnimation.setAlpha(getCurrentAlpha());
        this.mAnimationType = animationType;
        this.mLayoutInsetsDuringAnimation = layoutInsetsDuringAnimation;
        this.mTranslator = translator;
        controller.startAnimation(this, listener, types, windowInsetsAnimation, new WindowInsetsAnimation.Bounds(this.mHiddenInsets, this.mShownInsets));
    }

    private boolean calculatePerceptible(Insets currentInsets, float currentAlpha) {
        return currentInsets.left * 100 >= (this.mShownInsets.left - this.mHiddenInsets.left) * 5 && currentInsets.top * 100 >= (this.mShownInsets.top - this.mHiddenInsets.top) * 5 && currentInsets.right * 100 >= (this.mShownInsets.right - this.mHiddenInsets.right) * 5 && currentInsets.bottom * 100 >= (this.mShownInsets.bottom - this.mHiddenInsets.bottom) * 5 && currentAlpha >= 0.5f;
    }

    @Override // android.view.WindowInsetsAnimationController
    public boolean hasZeroInsetsIme() {
        return this.mHasZeroInsetsIme;
    }

    @Override // android.view.WindowInsetsAnimationController
    public Insets getHiddenStateInsets() {
        return this.mHiddenInsets;
    }

    @Override // android.view.WindowInsetsAnimationController
    public Insets getShownStateInsets() {
        return this.mShownInsets;
    }

    @Override // android.view.WindowInsetsAnimationController
    public Insets getCurrentInsets() {
        return this.mCurrentInsets;
    }

    @Override // android.view.WindowInsetsAnimationController
    public float getCurrentAlpha() {
        return this.mCurrentAlpha;
    }

    @Override // android.view.WindowInsetsAnimationController, android.view.InsetsAnimationControlRunner
    public int getTypes() {
        return this.mTypes;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public int getControllingTypes() {
        return this.mControllingTypes;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public void notifyControlRevoked(int types) {
        this.mControllingTypes &= ~types;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public void updateSurfacePosition(SparseArray<InsetsSourceControl> controls) {
        for (int i = controls.size() - 1; i >= 0; i--) {
            InsetsSourceControl control = controls.valueAt(i);
            InsetsSourceControl c = this.mControls.get(control.getType());
            if (c != null) {
                Point position = control.getSurfacePosition();
                c.setSurfacePosition(position.x, position.y);
            }
        }
    }

    @Override // android.view.InsetsAnimationControlRunner
    public int getAnimationType() {
        return this.mAnimationType;
    }

    @Override // android.view.WindowInsetsAnimationController
    public void setInsetsAndAlpha(Insets insets, float alpha, float fraction) {
        setInsetsAndAlpha(insets, alpha, fraction, false);
    }

    private void setInsetsAndAlpha(Insets insets, float alpha, float fraction, boolean allowWhenFinished) {
        if (!allowWhenFinished && this.mFinished) {
            throw new IllegalStateException("Can't change insets on an animation that is finished.");
        } else if (!this.mCancelled) {
            this.mPendingFraction = sanitize(fraction);
            this.mPendingInsets = sanitize(insets);
            this.mPendingAlpha = sanitize(alpha);
            this.mController.scheduleApplyChangeInsets(this);
            boolean perceptible = calculatePerceptible(this.mPendingInsets, this.mPendingAlpha);
            Boolean bool = this.mPerceptible;
            if (bool == null || perceptible != bool.booleanValue()) {
                this.mController.reportPerceptible(this.mTypes, perceptible);
                this.mPerceptible = Boolean.valueOf(perceptible);
            }
        } else {
            throw new IllegalStateException("Can't change insets on an animation that is cancelled.");
        }
    }

    public boolean applyChangeInsets(InsetsState outState) {
        if (this.mCancelled) {
            if (InsetsController.DEBUG) {
                Log.d(TAG, "applyChangeInsets canceled");
            }
            return false;
        }
        Insets offset = Insets.subtract(this.mShownInsets, this.mPendingInsets);
        ArrayList<SyncRtSurfaceTransactionApplier.SurfaceParams> params = new ArrayList<>();
        updateLeashesForSide(0, offset.left, this.mPendingInsets.left, params, outState, this.mPendingAlpha);
        updateLeashesForSide(1, offset.top, this.mPendingInsets.top, params, outState, this.mPendingAlpha);
        updateLeashesForSide(2, offset.right, this.mPendingInsets.right, params, outState, this.mPendingAlpha);
        updateLeashesForSide(3, offset.bottom, this.mPendingInsets.bottom, params, outState, this.mPendingAlpha);
        this.mController.applySurfaceParams((SyncRtSurfaceTransactionApplier.SurfaceParams[]) params.toArray(new SyncRtSurfaceTransactionApplier.SurfaceParams[params.size()]));
        this.mCurrentInsets = this.mPendingInsets;
        this.mAnimation.setFraction(this.mPendingFraction);
        float f = this.mPendingAlpha;
        this.mCurrentAlpha = f;
        this.mAnimation.setAlpha(f);
        if (this.mFinished) {
            if (InsetsController.DEBUG) {
                Log.d(TAG, String.format("notifyFinished shown: %s, currentAlpha: %f, currentInsets: %s", Boolean.valueOf(this.mShownOnFinish), Float.valueOf(this.mCurrentAlpha), this.mCurrentInsets));
            }
            this.mController.notifyFinished(this, this.mShownOnFinish);
            releaseLeashes();
        }
        if (InsetsController.DEBUG) {
            Log.d(TAG, "Animation finished abruptly.");
        }
        return this.mFinished;
    }

    private void releaseLeashes() {
        for (int i = this.mControls.size() - 1; i >= 0; i--) {
            InsetsSourceControl c = this.mControls.valueAt(i);
            if (c != null) {
                final InsetsAnimationControlCallbacks insetsAnimationControlCallbacks = this.mController;
                Objects.requireNonNull(insetsAnimationControlCallbacks);
                c.release(new Consumer() { // from class: android.view.InsetsAnimationControlImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        InsetsAnimationControlCallbacks.this.releaseSurfaceControlFromRt((SurfaceControl) obj);
                    }
                });
            }
        }
    }

    @Override // android.view.WindowInsetsAnimationController
    public void finish(boolean shown) {
        if (!this.mCancelled && !this.mFinished) {
            this.mShownOnFinish = shown;
            this.mFinished = true;
            setInsetsAndAlpha(shown ? this.mShownInsets : this.mHiddenInsets, this.mPendingAlpha, 1.0f, true);
            if (InsetsController.DEBUG) {
                Log.d(TAG, "notify control request finished for types: " + this.mTypes);
            }
            this.mListener.onFinished(this);
        } else if (InsetsController.DEBUG) {
            Log.d(TAG, "Animation already canceled or finished, not notifying.");
        }
    }

    @Override // android.view.WindowInsetsAnimationController
    public float getCurrentFraction() {
        return this.mAnimation.getFraction();
    }

    @Override // android.view.InsetsAnimationControlRunner
    public void cancel() {
        if (!this.mFinished) {
            this.mPendingInsets = this.mLayoutInsetsDuringAnimation == 0 ? this.mShownInsets : this.mHiddenInsets;
            this.mPendingAlpha = 1.0f;
            InsetsAnimationControlImpl insetsAnimationControlImpl = null;
            applyChangeInsets(null);
            this.mCancelled = true;
            WindowInsetsAnimationControlListener windowInsetsAnimationControlListener = this.mListener;
            if (this.mReadyDispatched) {
                insetsAnimationControlImpl = this;
            }
            windowInsetsAnimationControlListener.onCancelled(insetsAnimationControlImpl);
            if (InsetsController.DEBUG) {
                Log.d(TAG, "notify Control request cancelled for types: " + this.mTypes);
            }
            releaseLeashes();
        }
    }

    @Override // android.view.WindowInsetsAnimationController
    public boolean isFinished() {
        return this.mFinished;
    }

    @Override // android.view.WindowInsetsAnimationController
    public boolean isCancelled() {
        return this.mCancelled;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public WindowInsetsAnimation getAnimation() {
        return this.mAnimation;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        long token = proto.start(fieldId);
        proto.write(1133871366145L, this.mCancelled);
        proto.write(1133871366146L, this.mFinished);
        proto.write(1138166333443L, Objects.toString(this.mTmpMatrix));
        proto.write(1138166333444L, Objects.toString(this.mPendingInsets));
        proto.write(1108101562373L, this.mPendingFraction);
        proto.write(1133871366150L, this.mShownOnFinish);
        proto.write(1108101562375L, this.mCurrentAlpha);
        proto.write(1108101562376L, this.mPendingAlpha);
        proto.end(token);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SparseArray<InsetsSourceControl> getControls() {
        return this.mControls;
    }

    private Insets getInsetsFromState(InsetsState state, Rect frame, SparseIntArray typeSideMap) {
        return state.calculateInsets(frame, null, false, false, 16, 0, 0, 2, 0, typeSideMap).getInsets(this.mTypes);
    }

    private Insets calculateInsets(InsetsState state, Rect frame, SparseArray<InsetsSourceControl> controls, boolean shown, SparseIntArray typeSideMap) {
        for (int i = controls.size() - 1; i >= 0; i--) {
            InsetsSourceControl control = controls.valueAt(i);
            if (control != null) {
                state.getSource(control.getType()).setVisible(shown);
            }
        }
        return getInsetsFromState(state, frame, typeSideMap);
    }

    private Insets calculateInsets(InsetsState state, SparseArray<InsetsSourceControl> controls, boolean shownOrCurrent) {
        Insets insets = Insets.NONE;
        if (!shownOrCurrent) {
            return insets;
        }
        for (int i = controls.size() - 1; i >= 0; i--) {
            InsetsSourceControl control = controls.valueAt(i);
            if (control != null && (state == null || state.getSource(control.getType()).isVisible())) {
                insets = Insets.max(insets, control.getInsetsHint());
            }
        }
        return insets;
    }

    private Insets sanitize(Insets insets) {
        if (insets == null) {
            insets = getCurrentInsets();
        }
        if (hasZeroInsetsIme()) {
            return insets;
        }
        return Insets.max(Insets.min(insets, this.mShownInsets), this.mHiddenInsets);
    }

    private static float sanitize(float alpha) {
        if (alpha >= 1.0f) {
            return 1.0f;
        }
        if (alpha <= 0.0f) {
            return 0.0f;
        }
        return alpha;
    }

    private void updateLeashesForSide(int side, int offset, int inset, ArrayList<SyncRtSurfaceTransactionApplier.SurfaceParams> surfaceParams, InsetsState outState, float alpha) {
        ArraySet<InsetsSourceControl> controls = this.mSideControlsMap.get(side);
        if (controls != null) {
            for (int i = controls.size() - 1; i >= 0; i--) {
                InsetsSourceControl control = controls.valueAt(i);
                InsetsSource source = this.mInitialInsetsState.getSource(control.getType());
                SurfaceControl leash = control.getLeash();
                this.mTmpMatrix.setTranslate(control.getSurfacePosition().x, control.getSurfacePosition().y);
                this.mTmpFrame.set(source.getFrame());
                addTranslationToMatrix(side, offset, this.mTmpMatrix, this.mTmpFrame);
                boolean visible = false;
                if (!this.mHasZeroInsetsIme || side != 3) {
                    if (inset != 0) {
                        visible = true;
                    }
                } else if (this.mAnimationType == 0 || !this.mFinished) {
                    visible = true;
                }
                if (outState != null) {
                    outState.getSource(source.getType()).setVisible(visible);
                    outState.getSource(source.getType()).setFrame(this.mTmpFrame);
                }
                if (leash != null) {
                    SyncRtSurfaceTransactionApplier.SurfaceParams params = new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(leash).withAlpha(alpha).withMatrix(this.mTmpMatrix).withVisibility(visible).build();
                    surfaceParams.add(params);
                }
            }
        }
    }

    private void addTranslationToMatrix(int side, int offset, Matrix m, Rect frame) {
        CompatibilityInfo.Translator translator = this.mTranslator;
        float surfaceOffset = translator != null ? translator.translateLengthInAppWindowToScreen(offset) : offset;
        switch (side) {
            case 0:
                m.postTranslate(-surfaceOffset, 0.0f);
                frame.offset(-offset, 0);
                return;
            case 1:
                m.postTranslate(0.0f, -surfaceOffset);
                frame.offset(0, -offset);
                return;
            case 2:
                m.postTranslate(surfaceOffset, 0.0f);
                frame.offset(offset, 0);
                return;
            case 3:
                m.postTranslate(0.0f, surfaceOffset);
                frame.offset(0, offset);
                return;
            default:
                return;
        }
    }

    private static void buildSideControlsMap(SparseIntArray typeSideMap, SparseSetArray<InsetsSourceControl> sideControlsMap, SparseArray<InsetsSourceControl> controls) {
        for (int i = typeSideMap.size() - 1; i >= 0; i--) {
            int type = typeSideMap.keyAt(i);
            int side = typeSideMap.valueAt(i);
            InsetsSourceControl control = controls.get(type);
            if (control != null) {
                sideControlsMap.add(side, control);
            }
        }
    }

    private static void buildSideControlsMap(SparseSetArray<InsetsSourceControl> sideControlsMap, SparseArray<InsetsSourceControl> controls) {
        for (int i = controls.size() - 1; i >= 0; i--) {
            InsetsSourceControl control = controls.valueAt(i);
            if (control != null) {
                int side = InsetsState.getInsetSide(control.getInsetsHint());
                if (side == 4 && control.getType() == 19) {
                    side = 3;
                }
                sideControlsMap.add(side, control);
            }
        }
    }
}
