package com.oplus.uifirst;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.util.IntArray;
import java.util.Collection;
/* loaded from: classes4.dex */
public interface IOplusUIFirstManager extends IOplusCommonFeature {
    public static final String ANIMATION_END = "0";
    public static final int ANIMATION_SCENE = 5;
    public static final String APP_EXIT_ANIMATION = "2";
    public static final String APP_START_ANIMATION = "1";
    public static final int APP_STATUS_MOVE_TO_BG = 2;
    public static final int APP_STATUS_MOVE_TO_FG = 1;
    public static final int APP_STATUS_PROC_DIE = 3;
    public static final int APP_STATUS_RESUME_ACTIVITY = 4;
    public static final int APP_STATUS_START_ACTIVITY = 0;
    public static final int BOOST_ANIMATION_TIMEOUT = 207;
    public static final int BOOST_COMMIT_TIMEOUT = 210;
    public static final int BOOST_FRAME_END = 204;
    public static final int BOOST_FRAME_TIMEOUT = 212;
    public static final int BOOST_INPUT_TIMEOUT = 206;
    public static final int BOOST_INSETS_ANIMATION_TIMEOUT = 208;
    public static final int BOOST_OBTAIN_VIEW = 203;
    public static final int BOOST_SET_HWUITASK = 205;
    public static final int BOOST_TRAVERSAL_TIMEOUT = 209;
    public static final IOplusUIFirstManager DEFAULT = new IOplusUIFirstManager() { // from class: com.oplus.uifirst.IOplusUIFirstManager.1
    };
    public static final int FRAME_BOOST_END = 211;
    public static final int FRAME_BOOST_FRAME_START = 200;
    public static final int FRAME_BOOST_MOVE_BG = 202;
    public static final int FRAME_BOOST_MOVE_FG = 201;
    public static final int LAUNCHER_SCENE_SI = 4;
    public static final String LAUNCHER_SI_START = "4";
    public static final String NAME = "IOplusUIFirstManager";
    public static final int SYSTEMUI_SCENE = 6;
    public static final int UIFIRST_OPT_CLEAR = 0;
    public static final int UIFIRST_OPT_SET = 128;
    public static final int UIFIRST_SCENE_ANIM = 16;
    public static final int UIFIRST_SCENE_ANIM_START = 8;
    public static final int UIFIRST_SCENE_CAMERA = 4;
    public static final int UIFIRST_SCENE_INPUT = 32;
    public static final int UIFIRST_SCENE_LAUNCH = 1;
    public static final int UIFIRST_SCENE_OPT_CLEAR = 0;
    public static final int UIFIRST_SCENE_OPT_SET = 128;
    public static final int UIFIRST_SCENE_SLIDE = 2;
    public static final int UIFIRST_TYPE_ANIMATOR_TASK = 4;
    public static final int UIFIRST_TYPE_HEAVY_TASK = 2;
    public static final int UIFIRST_TYPE_LIGHT_TASK = 1;
    public static final int UIFIRST_TYPE_LISTPICK_TASK = 8;

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusUIFirstManager;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default void acquireLaunchBoost() {
    }

    default void acquireUxSceneBoost(String scene, int timeout) {
    }

    default int[] adjustUxProcess(String packageName, int status, int appPid, int renderThreadTid, IntArray hwuiTasks, Collection<Integer> glThreads, boolean isRemoteAnimation) {
        return null;
    }

    default void onAppStatusChanged(int status, String packageName, String activityName) {
    }

    default void setTaskAnimation(String packageName, int type, int pid, int renderTid, String uxValue, String flag) {
    }

    default void setUxThreadValue(int pid, int tid, String value) {
    }

    default void writeProcNode(String filePath, String val) {
    }

    default String readProcNode(String filePath) {
        return null;
    }

    default boolean checkUxRemoteAnimationList(String packageName) {
        return false;
    }

    default void setTaskAsRemoteAnimationUx(int appPid, int renderThreadTid, IntArray hwuiTasks, String packageName, boolean isRemoteAnimation) {
    }

    default void addApplicationGlThread(String packageName, int pid, int tid) {
    }

    default void removeApplicationGlThread(String packageName, int pid, int tid) {
    }

    default int getApplicationGlThreadValue(String packageName) {
        return 0;
    }

    default void handleProcessStart(String packageName, int uid, int pid, boolean isolated, String processName) {
    }

    default void handleProcessStop(String packageName, int uid, int pid) {
    }

    default void setfpsgoparamforperformance() {
    }

    default void getfpsgoparamforreserve() {
    }

    default void backfpsgoparam() {
    }

    default void ofbSetFps(int pid, int tid, long fpsNs, long vsyncNs) {
    }

    default void ofbBoostHint(int pid, int tid, int hwtid1, int hwtid2, int stage, int level, long fnumber, long sourceDelta, long targetDelta) {
    }

    default void ofbEndFrame(int pid, int tid, long fnumber) {
    }

    default void adjustTopApp(String packageName, int appPid, int renderThreadTid, IntArray hwuiTasks) {
    }

    default void setResumedApp(String pkgName, int uid, int pid) {
    }

    default void notifyUiSwitched(String uiInfo, int status) {
    }

    default void setProcessUxValue(int pid, int value) {
    }

    default void setRenderThreadTid(String packageName, int pid, int tid) {
    }
}
