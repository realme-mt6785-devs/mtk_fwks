package android.os;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.SystemVibrator;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes2.dex */
public class SystemVibrator extends Vibrator {
    private static final String TAG = "Vibrator";
    private final Context mContext;
    private AllVibratorsInfo mVibratorInfo;
    private final VibratorManager mVibratorManager;
    private final Binder mToken = new Binder();
    private ISystemVibratorExt mSystemVibratorExt = (ISystemVibratorExt) ExtLoader.type(ISystemVibratorExt.class).base(this).create();
    private final ArrayList<AllVibratorsStateListener> mBrokenListeners = new ArrayList<>();
    private final ArrayMap<Vibrator.OnVibratorStateChangedListener, AllVibratorsStateListener> mRegisteredListeners = new ArrayMap<>();
    private final Object mLock = new Object();

    public SystemVibrator(Context context) {
        super(context);
        this.mContext = context;
        this.mVibratorManager = (VibratorManager) context.getSystemService(VibratorManager.class);
        this.mSystemVibratorExt.init(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.Vibrator
    public VibratorInfo getInfo() {
        synchronized (this.mLock) {
            AllVibratorsInfo allVibratorsInfo = this.mVibratorInfo;
            if (allVibratorsInfo != null) {
                return allVibratorsInfo;
            }
            VibratorManager vibratorManager = this.mVibratorManager;
            if (vibratorManager == null) {
                Log.w(TAG, "Failed to retrieve vibrator info; no vibrator manager.");
                return VibratorInfo.EMPTY_VIBRATOR_INFO;
            }
            int[] vibratorIds = vibratorManager.getVibratorIds();
            VibratorInfo[] vibratorInfos = new VibratorInfo[vibratorIds.length];
            for (int i = 0; i < vibratorIds.length; i++) {
                vibratorInfos[i] = this.mVibratorManager.getVibrator(vibratorIds[i]).getInfo();
            }
            AllVibratorsInfo allVibratorsInfo2 = new AllVibratorsInfo(vibratorInfos);
            this.mVibratorInfo = allVibratorsInfo2;
            return allVibratorsInfo2;
        }
    }

    @Override // android.os.Vibrator
    public boolean hasVibrator() {
        VibratorManager vibratorManager = this.mVibratorManager;
        if (vibratorManager != null) {
            return vibratorManager.getVibratorIds().length > 0;
        }
        Log.w(TAG, "Failed to check if vibrator exists; no vibrator manager.");
        return false;
    }

    @Override // android.os.Vibrator
    public boolean isVibrating() {
        int[] vibratorIds;
        VibratorManager vibratorManager = this.mVibratorManager;
        if (vibratorManager == null) {
            Log.w(TAG, "Failed to vibrate; no vibrator manager.");
            return false;
        }
        for (int vibratorId : vibratorManager.getVibratorIds()) {
            if (this.mVibratorManager.getVibrator(vibratorId).isVibrating()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Vibrator
    public void addVibratorStateListener(Vibrator.OnVibratorStateChangedListener listener) {
        Objects.requireNonNull(listener);
        Context context = this.mContext;
        if (context == null) {
            Log.w(TAG, "Failed to add vibrate state listener; no vibrator context.");
        } else {
            addVibratorStateListener(context.getMainExecutor(), listener);
        }
    }

    @Override // android.os.Vibrator
    public void addVibratorStateListener(Executor executor, Vibrator.OnVibratorStateChangedListener listener) {
        Objects.requireNonNull(listener);
        Objects.requireNonNull(executor);
        if (this.mVibratorManager == null) {
            Log.w(TAG, "Failed to add vibrate state listener; no vibrator manager.");
            return;
        }
        AllVibratorsStateListener delegate = null;
        try {
            synchronized (this.mRegisteredListeners) {
                if (this.mRegisteredListeners.containsKey(listener)) {
                    Log.w(TAG, "Listener already registered.");
                    if (0 != 0 && delegate.hasRegisteredListeners()) {
                        synchronized (this.mBrokenListeners) {
                            this.mBrokenListeners.add(null);
                        }
                    }
                    tryUnregisterBrokenListeners();
                    return;
                }
                AllVibratorsStateListener delegate2 = new AllVibratorsStateListener(executor, listener);
                delegate2.register(this.mVibratorManager);
                this.mRegisteredListeners.put(listener, delegate2);
                AllVibratorsStateListener delegate3 = null;
                if (0 != 0 && delegate3.hasRegisteredListeners()) {
                    synchronized (this.mBrokenListeners) {
                        this.mBrokenListeners.add(null);
                    }
                }
                tryUnregisterBrokenListeners();
            }
        } catch (Throwable th) {
            if (0 != 0 && delegate.hasRegisteredListeners()) {
                synchronized (this.mBrokenListeners) {
                    this.mBrokenListeners.add(null);
                }
            }
            tryUnregisterBrokenListeners();
            throw th;
        }
    }

    @Override // android.os.Vibrator
    public void removeVibratorStateListener(Vibrator.OnVibratorStateChangedListener listener) {
        Objects.requireNonNull(listener);
        if (this.mVibratorManager == null) {
            Log.w(TAG, "Failed to remove vibrate state listener; no vibrator manager.");
            return;
        }
        synchronized (this.mRegisteredListeners) {
            if (this.mRegisteredListeners.containsKey(listener)) {
                AllVibratorsStateListener delegate = this.mRegisteredListeners.get(listener);
                delegate.unregister(this.mVibratorManager);
                this.mRegisteredListeners.remove(listener);
            }
        }
        tryUnregisterBrokenListeners();
    }

    @Override // android.os.Vibrator
    public boolean hasAmplitudeControl() {
        return getInfo().hasAmplitudeControl();
    }

    @Override // android.os.Vibrator
    public boolean setAlwaysOnEffect(int uid, String opPkg, int alwaysOnId, VibrationEffect effect, AudioAttributes attributes) {
        if (this.mVibratorManager == null) {
            Log.w(TAG, "Failed to set always-on effect; no vibrator manager.");
            return false;
        }
        VibrationAttributes attr = new VibrationAttributes.Builder(attributes, effect).build();
        CombinedVibration combinedEffect = CombinedVibration.createParallel(effect);
        return this.mVibratorManager.setAlwaysOnEffect(uid, opPkg, alwaysOnId, combinedEffect, attr);
    }

    @Override // android.os.Vibrator
    public void vibrate(int uid, String opPkg, VibrationEffect effect, String reason, VibrationAttributes attributes) {
        if (this.mVibratorManager == null) {
            Log.w(TAG, "Failed to vibrate; no vibrator manager.");
        } else if (!this.mSystemVibratorExt.doVibrate(uid, opPkg, effect) && !this.mSystemVibratorExt.convertToLinearVibration(uid, opPkg, effect, this.mToken, attributes)) {
            CombinedVibration combinedEffect = CombinedVibration.createParallel(effect);
            this.mVibratorManager.vibrate(uid, opPkg, combinedEffect, reason, attributes);
        }
    }

    @Override // android.os.Vibrator
    public void cancel() {
        VibratorManager vibratorManager = this.mVibratorManager;
        if (vibratorManager == null) {
            Log.w(TAG, "Failed to cancel vibrate; no vibrator manager.");
        } else {
            vibratorManager.cancel();
        }
    }

    @Override // android.os.Vibrator
    public void cancel(int usageFilter) {
        VibratorManager vibratorManager = this.mVibratorManager;
        if (vibratorManager == null) {
            Log.w(TAG, "Failed to cancel vibrate; no vibrator manager.");
        } else {
            vibratorManager.cancel(usageFilter);
        }
    }

    private void tryUnregisterBrokenListeners() {
        synchronized (this.mBrokenListeners) {
            try {
                int i = this.mBrokenListeners.size();
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    this.mBrokenListeners.get(i).unregister(this.mVibratorManager);
                    this.mBrokenListeners.remove(i);
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "Failed to unregister broken listener", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SingleVibratorStateListener implements Vibrator.OnVibratorStateChangedListener {
        private final AllVibratorsStateListener mAllVibratorsListener;
        private final int mVibratorIdx;

        SingleVibratorStateListener(AllVibratorsStateListener listener, int vibratorIdx) {
            this.mAllVibratorsListener = listener;
            this.mVibratorIdx = vibratorIdx;
        }

        @Override // android.os.Vibrator.OnVibratorStateChangedListener
        public void onVibratorStateChanged(boolean isVibrating) {
            this.mAllVibratorsListener.onVibrating(this.mVibratorIdx, isVibrating);
        }
    }

    /* loaded from: classes2.dex */
    public static class AllVibratorsInfo extends VibratorInfo {
        private final VibratorInfo[] mVibratorInfos;

        public AllVibratorsInfo(VibratorInfo[] vibrators) {
            super(-1, capabilitiesIntersection(vibrators), vibrators.length > 0 ? vibrators[0] : VibratorInfo.EMPTY_VIBRATOR_INFO);
            this.mVibratorInfos = vibrators;
        }

        @Override // android.os.VibratorInfo
        public int isEffectSupported(int effectId) {
            VibratorInfo[] vibratorInfoArr = this.mVibratorInfos;
            if (vibratorInfoArr.length == 0) {
                return 2;
            }
            int supported = 1;
            for (VibratorInfo info : vibratorInfoArr) {
                int effectSupported = info.isEffectSupported(effectId);
                if (effectSupported == 2) {
                    return effectSupported;
                }
                if (effectSupported == 0) {
                    supported = effectSupported;
                }
            }
            return supported;
        }

        @Override // android.os.VibratorInfo
        public boolean isPrimitiveSupported(int primitiveId) {
            VibratorInfo[] vibratorInfoArr = this.mVibratorInfos;
            if (vibratorInfoArr.length == 0) {
                return false;
            }
            for (VibratorInfo info : vibratorInfoArr) {
                if (!info.isPrimitiveSupported(primitiveId)) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.os.VibratorInfo
        public int getPrimitiveDuration(int primitiveId) {
            VibratorInfo[] vibratorInfoArr;
            int maxDuration = 0;
            for (VibratorInfo info : this.mVibratorInfos) {
                int duration = info.getPrimitiveDuration(primitiveId);
                if (duration == 0) {
                    return 0;
                }
                maxDuration = Math.max(maxDuration, duration);
            }
            return maxDuration;
        }

        private static int capabilitiesIntersection(VibratorInfo[] infos) {
            if (infos.length == 0) {
                return 0;
            }
            int intersection = -1;
            for (VibratorInfo info : infos) {
                intersection = (int) (intersection & info.getCapabilities());
            }
            return intersection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AllVibratorsStateListener {
        private final Vibrator.OnVibratorStateChangedListener mDelegate;
        private final Executor mExecutor;
        private int mInitializedMask;
        private int mVibratingMask;
        private final Object mLock = new Object();
        private final SparseArray<SingleVibratorStateListener> mVibratorListeners = new SparseArray<>();

        AllVibratorsStateListener(Executor executor, Vibrator.OnVibratorStateChangedListener listener) {
            this.mExecutor = executor;
            this.mDelegate = listener;
        }

        boolean hasRegisteredListeners() {
            boolean z;
            synchronized (this.mLock) {
                z = this.mVibratorListeners.size() > 0;
            }
            return z;
        }

        void register(VibratorManager vibratorManager) {
            int[] vibratorIds = vibratorManager.getVibratorIds();
            synchronized (this.mLock) {
                for (int i = 0; i < vibratorIds.length; i++) {
                    int vibratorId = vibratorIds[i];
                    SingleVibratorStateListener listener = new SingleVibratorStateListener(this, i);
                    try {
                        vibratorManager.getVibrator(vibratorId).addVibratorStateListener(this.mExecutor, listener);
                        this.mVibratorListeners.put(vibratorId, listener);
                    } catch (RuntimeException e) {
                        try {
                            unregister(vibratorManager);
                        } catch (RuntimeException e1) {
                            Log.w(SystemVibrator.TAG, "Failed to unregister listener while recovering from a failed register call", e1);
                        }
                        throw e;
                    }
                }
            }
        }

        void unregister(VibratorManager vibratorManager) {
            synchronized (this.mLock) {
                int i = this.mVibratorListeners.size();
                while (true) {
                    i--;
                    if (i >= 0) {
                        int vibratorId = this.mVibratorListeners.keyAt(i);
                        SingleVibratorStateListener listener = this.mVibratorListeners.valueAt(i);
                        vibratorManager.getVibrator(vibratorId).removeVibratorStateListener(listener);
                        this.mVibratorListeners.removeAt(i);
                    }
                }
            }
        }

        void onVibrating(final int vibratorIdx, final boolean vibrating) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.SystemVibrator$AllVibratorsStateListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SystemVibrator.AllVibratorsStateListener.this.lambda$onVibrating$0$SystemVibrator$AllVibratorsStateListener(vibratorIdx, vibrating);
                }
            });
        }

        public /* synthetic */ void lambda$onVibrating$0$SystemVibrator$AllVibratorsStateListener(int vibratorIdx, boolean vibrating) {
            synchronized (this.mLock) {
                boolean anyVibrating = true;
                int allInitializedMask = 1 << (this.mVibratorListeners.size() - 1);
                int vibratorMask = 1 << vibratorIdx;
                int i = this.mInitializedMask;
                if ((i & vibratorMask) == 0) {
                    this.mInitializedMask = i | vibratorMask;
                    this.mVibratingMask |= vibrating ? vibratorMask : 0;
                } else {
                    int i2 = this.mVibratingMask;
                    boolean prevVibrating = (i2 & vibratorMask) != 0;
                    if (prevVibrating != vibrating) {
                        this.mVibratingMask = i2 ^ vibratorMask;
                    }
                }
                if (this.mInitializedMask == allInitializedMask) {
                    if (this.mVibratingMask == 0) {
                        anyVibrating = false;
                    }
                    this.mDelegate.onVibratorStateChanged(anyVibrating);
                }
            }
        }
    }

    @Override // android.os.Vibrator
    public void linearMotorVibrate(int uid, String opPkg, int[] waveformIds, long[] timings, int strength, int repeat, String reason, AudioAttributes attributes, IBinder token) {
        AudioAttributes attributes2;
        if (attributes == null) {
            attributes2 = new AudioAttributes.Builder().build();
        } else {
            attributes2 = attributes;
        }
        VibrationAttributes attr = new VibrationAttributes.Builder(attributes2, null).build();
        this.mSystemVibratorExt.linearMotorVibrate(uid, opPkg, waveformIds, timings, strength, repeat, reason, attr, token);
    }

    @Override // android.os.Vibrator
    public int getVibratorStatus() {
        return this.mSystemVibratorExt.getVibratorStatus();
    }

    @Override // android.os.Vibrator
    public void setVibratorStrength(int strength) {
        this.mSystemVibratorExt.setVibratorStrength(strength);
    }

    @Override // android.os.Vibrator
    public int getVibratorTouchStyle() {
        return this.mSystemVibratorExt.getVibratorTouchStyle();
    }

    @Override // android.os.Vibrator
    public void setVibratorTouchStyle(int style) {
        this.mSystemVibratorExt.setVibratorTouchStyle(style);
    }
}
