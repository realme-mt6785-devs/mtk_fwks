package android.view;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.style.AccessibilityClickableSpan;
import android.text.style.ClickableSpan;
import android.util.LongSparseArray;
import android.util.Slog;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeIdManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityRequestPreparer;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import com.android.internal.os.SomeArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Predicate;
/* loaded from: classes3.dex */
public final class AccessibilityInteractionController {
    private static final boolean CONSIDER_REQUEST_PREPARERS = false;
    private static final boolean ENFORCE_NODE_TREE_CONSISTENT = false;
    private static final int FLAGS_AFFECTING_REPORTED_DATA = 24;
    private static final boolean IGNORE_REQUEST_PREPARERS = true;
    private static final String LOG_TAG = "AccessibilityInteractionController";
    private static final long REQUEST_PREPARER_TIMEOUT_MS = 500;
    private final AccessibilityManager mA11yManager;
    private int mActiveRequestPreparerId;
    private AddNodeInfosForViewId mAddNodeInfosForViewId;
    private final PrivateHandler mHandler;
    private List<MessageHolder> mMessagesWaitingForRequestPreparer;
    private final long mMyLooperThreadId;
    private int mNumActiveRequestPreparers;
    private final ViewRootImpl mViewRootImpl;
    private final ArrayList<AccessibilityNodeInfo> mTempAccessibilityNodeInfoList = new ArrayList<>();
    private final Object mLock = new Object();
    private final ArrayList<View> mTempArrayList = new ArrayList<>();
    private final Point mTempPoint = new Point();
    private final Rect mTempRect = new Rect();
    private final Rect mTempRect1 = new Rect();
    private final Rect mTempRect2 = new Rect();
    private final RectF mTempRectF = new RectF();
    private final int mMyProcessId = Process.myPid();
    private final AccessibilityNodePrefetcher mPrefetcher = new AccessibilityNodePrefetcher();
    private ArrayList<Message> mPendingFindNodeByIdMessages = new ArrayList<>();

    public AccessibilityInteractionController(ViewRootImpl viewRootImpl) {
        Looper looper = viewRootImpl.mHandler.getLooper();
        this.mMyLooperThreadId = looper.getThread().getId();
        this.mHandler = new PrivateHandler(looper);
        this.mViewRootImpl = viewRootImpl;
        this.mA11yManager = (AccessibilityManager) viewRootImpl.mContext.getSystemService(AccessibilityManager.class);
    }

    private void scheduleMessage(Message message, int interrogatingPid, long interrogatingTid, boolean ignoreRequestPreparers) {
        if (!ignoreRequestPreparers && holdOffMessageIfNeeded(message, interrogatingPid, interrogatingTid)) {
            return;
        }
        if (interrogatingPid == this.mMyProcessId && interrogatingTid == this.mMyLooperThreadId && this.mHandler.hasAccessibilityCallback(message)) {
            AccessibilityInteractionClient.getInstanceForThread(interrogatingTid).setSameThreadMessage(message);
        } else if (this.mHandler.hasAccessibilityCallback(message) || Thread.currentThread().getId() != this.mMyLooperThreadId) {
            this.mHandler.sendMessage(message);
        } else {
            this.mHandler.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isShown(View view) {
        return view != null && view.getWindowVisibility() == 0 && view.isShown();
    }

    public void findAccessibilityNodeInfoByAccessibilityIdClientThread(long accessibilityNodeId, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec, Bundle arguments) {
        Message message = this.mHandler.obtainMessage();
        message.what = 2;
        message.arg1 = flags;
        SomeArgs args = SomeArgs.obtain();
        args.argi1 = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeId);
        args.argi2 = AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeId);
        args.argi3 = interactionId;
        args.arg1 = callback;
        args.arg2 = spec;
        args.arg3 = interactiveRegion;
        args.arg4 = arguments;
        message.obj = args;
        synchronized (this.mLock) {
            try {
                try {
                    this.mPendingFindNodeByIdMessages.add(message);
                    scheduleMessage(message, interrogatingPid, interrogatingTid, false);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private boolean holdOffMessageIfNeeded(Message originalMessage, int callingPid, long callingTid) {
        synchronized (this.mLock) {
            if (this.mNumActiveRequestPreparers != 0) {
                queueMessageToHandleOncePrepared(originalMessage, callingPid, callingTid);
                return true;
            } else if (originalMessage.what != 2) {
                return false;
            } else {
                SomeArgs originalMessageArgs = (SomeArgs) originalMessage.obj;
                Bundle requestArguments = (Bundle) originalMessageArgs.arg4;
                if (requestArguments == null) {
                    return false;
                }
                int accessibilityViewId = originalMessageArgs.argi1;
                List<AccessibilityRequestPreparer> preparers = this.mA11yManager.getRequestPreparersForAccessibilityId(accessibilityViewId);
                if (preparers == null) {
                    return false;
                }
                String extraDataKey = requestArguments.getString(AccessibilityNodeInfo.EXTRA_DATA_REQUESTED_KEY);
                if (extraDataKey == null) {
                    return false;
                }
                this.mNumActiveRequestPreparers = preparers.size();
                for (int i = 0; i < preparers.size(); i++) {
                    Message requestPreparerMessage = this.mHandler.obtainMessage(7);
                    SomeArgs requestPreparerArgs = SomeArgs.obtain();
                    requestPreparerArgs.argi1 = originalMessageArgs.argi2 == Integer.MAX_VALUE ? -1 : originalMessageArgs.argi2;
                    requestPreparerArgs.arg1 = preparers.get(i);
                    requestPreparerArgs.arg2 = extraDataKey;
                    requestPreparerArgs.arg3 = requestArguments;
                    Message preparationFinishedMessage = this.mHandler.obtainMessage(8);
                    int i2 = this.mActiveRequestPreparerId + 1;
                    this.mActiveRequestPreparerId = i2;
                    preparationFinishedMessage.arg1 = i2;
                    requestPreparerArgs.arg4 = preparationFinishedMessage;
                    requestPreparerMessage.obj = requestPreparerArgs;
                    scheduleMessage(requestPreparerMessage, callingPid, callingTid, true);
                    this.mHandler.obtainMessage(9);
                    this.mHandler.sendEmptyMessageDelayed(9, REQUEST_PREPARER_TIMEOUT_MS);
                }
                queueMessageToHandleOncePrepared(originalMessage, callingPid, callingTid);
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareForExtraDataRequestUiThread(Message message) {
        SomeArgs args = (SomeArgs) message.obj;
        int virtualDescendantId = args.argi1;
        AccessibilityRequestPreparer preparer = (AccessibilityRequestPreparer) args.arg1;
        String extraDataKey = (String) args.arg2;
        Bundle requestArguments = (Bundle) args.arg3;
        Message preparationFinishedMessage = (Message) args.arg4;
        preparer.onPrepareExtraData(virtualDescendantId, extraDataKey, requestArguments, preparationFinishedMessage);
    }

    private void queueMessageToHandleOncePrepared(Message message, int interrogatingPid, long interrogatingTid) {
        if (this.mMessagesWaitingForRequestPreparer == null) {
            this.mMessagesWaitingForRequestPreparer = new ArrayList(1);
        }
        MessageHolder messageHolder = new MessageHolder(message, interrogatingPid, interrogatingTid);
        this.mMessagesWaitingForRequestPreparer.add(messageHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPreparerDoneUiThread(Message message) {
        synchronized (this.mLock) {
            if (message.arg1 != this.mActiveRequestPreparerId) {
                Slog.e(LOG_TAG, "Surprising AccessibilityRequestPreparer callback (likely late)");
                return;
            }
            int i = this.mNumActiveRequestPreparers - 1;
            this.mNumActiveRequestPreparers = i;
            if (i <= 0) {
                this.mHandler.removeMessages(9);
                scheduleAllMessagesWaitingForRequestPreparerLocked();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPreparerTimeoutUiThread() {
        synchronized (this.mLock) {
            Slog.e(LOG_TAG, "AccessibilityRequestPreparer timed out");
            scheduleAllMessagesWaitingForRequestPreparerLocked();
        }
    }

    private void scheduleAllMessagesWaitingForRequestPreparerLocked() {
        int numMessages = this.mMessagesWaitingForRequestPreparer.size();
        int i = 0;
        while (true) {
            boolean z = false;
            if (i < numMessages) {
                MessageHolder request = this.mMessagesWaitingForRequestPreparer.get(i);
                Message message = request.mMessage;
                int i2 = request.mInterrogatingPid;
                long j = request.mInterrogatingTid;
                if (i == 0) {
                    z = true;
                }
                scheduleMessage(message, i2, j, z);
                i++;
            } else {
                this.mMessagesWaitingForRequestPreparer.clear();
                this.mNumActiveRequestPreparers = 0;
                this.mActiveRequestPreparerId = -1;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void findAccessibilityNodeInfoByAccessibilityIdUiThread(android.os.Message r26) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.AccessibilityInteractionController.findAccessibilityNodeInfoByAccessibilityIdUiThread(android.os.Message):void");
    }

    private AccessibilityNodeInfo populateAccessibilityNodeInfoForView(View view, Bundle arguments, int virtualViewId) {
        AccessibilityNodeInfo root;
        AccessibilityNodeProvider provider = view.getAccessibilityNodeProvider();
        String extraDataRequested = arguments == null ? null : arguments.getString(AccessibilityNodeInfo.EXTRA_DATA_REQUESTED_KEY);
        if (provider == null) {
            root = view.createAccessibilityNodeInfo();
            if (!(root == null || extraDataRequested == null)) {
                view.addExtraDataToAccessibilityNodeInfo(root, extraDataRequested, arguments);
            }
        } else {
            root = provider.createAccessibilityNodeInfo(virtualViewId);
            if (!(root == null || extraDataRequested == null)) {
                provider.addExtraDataToAccessibilityNodeInfo(virtualViewId, root, extraDataRequested, arguments);
            }
        }
        return root;
    }

    public void findAccessibilityNodeInfosByViewIdClientThread(long accessibilityNodeId, String viewId, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
        Message message = this.mHandler.obtainMessage();
        message.what = 3;
        message.arg1 = flags;
        message.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeId);
        SomeArgs args = SomeArgs.obtain();
        args.argi1 = interactionId;
        args.arg1 = callback;
        args.arg2 = spec;
        args.arg3 = viewId;
        args.arg4 = interactiveRegion;
        message.obj = args;
        scheduleMessage(message, interrogatingPid, interrogatingTid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findAccessibilityNodeInfosByViewIdUiThread(Message message) {
        List<AccessibilityNodeInfo> infos;
        Throwable th;
        List<AccessibilityNodeInfo> infos2;
        int flags = message.arg1;
        int accessibilityViewId = message.arg2;
        SomeArgs args = (SomeArgs) message.obj;
        int interactionId = args.argi1;
        IAccessibilityInteractionConnectionCallback callback = (IAccessibilityInteractionConnectionCallback) args.arg1;
        MagnificationSpec spec = (MagnificationSpec) args.arg2;
        String viewId = (String) args.arg3;
        Region interactiveRegion = (Region) args.arg4;
        args.recycle();
        List<AccessibilityNodeInfo> infos3 = this.mTempAccessibilityNodeInfoList;
        infos3.clear();
        try {
            if (this.mViewRootImpl.mView == null || this.mViewRootImpl.mAttachInfo == null) {
                infos2 = infos3;
            } else if (viewId == null) {
                infos2 = infos3;
            } else {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = flags;
                View root = findViewByAccessibilityId(accessibilityViewId);
                if (root != null) {
                    int resolvedViewId = root.getContext().getResources().getIdentifier(viewId, null, null);
                    if (resolvedViewId <= 0) {
                        this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
                        updateInfosForViewportAndReturnFindNodeResult(infos3, callback, interactionId, spec, interactiveRegion);
                        return;
                    }
                    infos = infos3;
                    try {
                        if (this.mAddNodeInfosForViewId == null) {
                            this.mAddNodeInfosForViewId = new AddNodeInfosForViewId();
                        }
                        this.mAddNodeInfosForViewId.init(resolvedViewId, infos);
                        root.findViewByPredicate(this.mAddNodeInfosForViewId);
                        this.mAddNodeInfosForViewId.reset();
                    } catch (Throwable th2) {
                        th = th2;
                        this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
                        updateInfosForViewportAndReturnFindNodeResult(infos, callback, interactionId, spec, interactiveRegion);
                        throw th;
                    }
                } else {
                    infos = infos3;
                }
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
                updateInfosForViewportAndReturnFindNodeResult(infos, callback, interactionId, spec, interactiveRegion);
                return;
            }
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            updateInfosForViewportAndReturnFindNodeResult(infos2, callback, interactionId, spec, interactiveRegion);
        } catch (Throwable th3) {
            th = th3;
            infos = infos3;
        }
    }

    public void findAccessibilityNodeInfosByTextClientThread(long accessibilityNodeId, String text, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
        Message message = this.mHandler.obtainMessage();
        message.what = 4;
        message.arg1 = flags;
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = text;
        args.arg2 = callback;
        args.arg3 = spec;
        args.argi1 = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeId);
        args.argi2 = AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeId);
        args.argi3 = interactionId;
        args.arg4 = interactiveRegion;
        message.obj = args;
        scheduleMessage(message, interrogatingPid, interrogatingTid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r21v0, types: [android.view.AccessibilityInteractionController] */
    /* JADX WARN: Type inference failed for: r6v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.graphics.Region] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void findAccessibilityNodeInfosByTextUiThread(android.os.Message r22) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.AccessibilityInteractionController.findAccessibilityNodeInfosByTextUiThread(android.os.Message):void");
    }

    public void findFocusClientThread(long accessibilityNodeId, int focusType, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
        Message message = this.mHandler.obtainMessage();
        message.what = 5;
        message.arg1 = flags;
        message.arg2 = focusType;
        SomeArgs args = SomeArgs.obtain();
        args.argi1 = interactionId;
        args.argi2 = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeId);
        args.argi3 = AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeId);
        args.arg1 = callback;
        args.arg2 = spec;
        args.arg3 = interactiveRegion;
        message.obj = args;
        scheduleMessage(message, interrogatingPid, interrogatingTid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findFocusUiThread(Message message) {
        int flags = message.arg1;
        int focusType = message.arg2;
        SomeArgs args = (SomeArgs) message.obj;
        int interactionId = args.argi1;
        int accessibilityViewId = args.argi2;
        int virtualDescendantId = args.argi3;
        IAccessibilityInteractionConnectionCallback callback = (IAccessibilityInteractionConnectionCallback) args.arg1;
        MagnificationSpec spec = (MagnificationSpec) args.arg2;
        Region interactiveRegion = (Region) args.arg3;
        args.recycle();
        AccessibilityNodeInfo focused = null;
        try {
            if (!(this.mViewRootImpl.mView == null || this.mViewRootImpl.mAttachInfo == null)) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = flags;
                View root = findViewByAccessibilityId(accessibilityViewId);
                if (root != null && isShown(root)) {
                    switch (focusType) {
                        case 1:
                            View target = root.findFocus();
                            if (!isShown(target)) {
                                break;
                            } else {
                                AccessibilityNodeProvider provider = target.getAccessibilityNodeProvider();
                                if (provider != null) {
                                    focused = provider.findFocus(focusType);
                                }
                                if (focused == null) {
                                    focused = target.createAccessibilityNodeInfo();
                                }
                                break;
                            }
                        case 2:
                            View host = this.mViewRootImpl.mAccessibilityFocusedHost;
                            if (host != null && ViewRootImpl.isViewDescendantOf(host, root) && isShown(host)) {
                                if (host.getAccessibilityNodeProvider() != null) {
                                    if (this.mViewRootImpl.mAccessibilityFocusedVirtualView != null) {
                                        focused = AccessibilityNodeInfo.obtain(this.mViewRootImpl.mAccessibilityFocusedVirtualView);
                                    }
                                } else if (virtualDescendantId == -1) {
                                    focused = host.createAccessibilityNodeInfo();
                                }
                                break;
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown focus type: " + focusType);
                    }
                }
            }
        } finally {
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            updateInfoForViewportAndReturnFindNodeResult(null, callback, interactionId, spec, interactiveRegion);
        }
    }

    public void focusSearchClientThread(long accessibilityNodeId, int direction, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
        Message message = this.mHandler.obtainMessage();
        message.what = 6;
        message.arg1 = flags;
        message.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeId);
        SomeArgs args = SomeArgs.obtain();
        args.argi2 = direction;
        args.argi3 = interactionId;
        args.arg1 = callback;
        args.arg2 = spec;
        args.arg3 = interactiveRegion;
        message.obj = args;
        scheduleMessage(message, interrogatingPid, interrogatingTid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void focusSearchUiThread(Message message) {
        View nextView;
        int flags = message.arg1;
        int accessibilityViewId = message.arg2;
        SomeArgs args = (SomeArgs) message.obj;
        int direction = args.argi2;
        int interactionId = args.argi3;
        IAccessibilityInteractionConnectionCallback callback = (IAccessibilityInteractionConnectionCallback) args.arg1;
        MagnificationSpec spec = (MagnificationSpec) args.arg2;
        Region interactiveRegion = (Region) args.arg3;
        args.recycle();
        AccessibilityNodeInfo next = null;
        try {
            if (!(this.mViewRootImpl.mView == null || this.mViewRootImpl.mAttachInfo == null)) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = flags;
                View root = findViewByAccessibilityId(accessibilityViewId);
                if (!(root == null || !isShown(root) || (nextView = root.focusSearch(direction)) == null)) {
                    next = nextView.createAccessibilityNodeInfo();
                }
            }
        } finally {
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            updateInfoForViewportAndReturnFindNodeResult(null, callback, interactionId, spec, interactiveRegion);
        }
    }

    public void performAccessibilityActionClientThread(long accessibilityNodeId, int action, Bundle arguments, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid) {
        Message message = this.mHandler.obtainMessage();
        message.what = 1;
        message.arg1 = flags;
        message.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeId);
        SomeArgs args = SomeArgs.obtain();
        args.argi1 = AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeId);
        args.argi2 = action;
        args.argi3 = interactionId;
        args.arg1 = callback;
        args.arg2 = arguments;
        message.obj = args;
        scheduleMessage(message, interrogatingPid, interrogatingTid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performAccessibilityActionUiThread(Message message) {
        int flags = message.arg1;
        int accessibilityViewId = message.arg2;
        SomeArgs args = (SomeArgs) message.obj;
        int virtualDescendantId = args.argi1;
        int action = args.argi2;
        int interactionId = args.argi3;
        IAccessibilityInteractionConnectionCallback callback = (IAccessibilityInteractionConnectionCallback) args.arg1;
        Bundle arguments = (Bundle) args.arg2;
        args.recycle();
        boolean succeeded = false;
        try {
            if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null && !this.mViewRootImpl.mStopped && !this.mViewRootImpl.mPausedForTransition) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = flags;
                View target = findViewByAccessibilityId(accessibilityViewId);
                if (target != null && isShown(target)) {
                    this.mA11yManager.notifyPerformingAction(action);
                    if (action == 16908670) {
                        succeeded = handleClickableSpanActionUiThread(target, virtualDescendantId, arguments);
                    } else {
                        AccessibilityNodeProvider provider = target.getAccessibilityNodeProvider();
                        if (provider != null) {
                            succeeded = provider.performAction(virtualDescendantId, action, arguments);
                        } else if (virtualDescendantId == -1) {
                            succeeded = target.performAccessibilityAction(action, arguments);
                        }
                    }
                    this.mA11yManager.notifyPerformingAction(0);
                }
                try {
                    this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
                    callback.setPerformAccessibilityActionResult(succeeded, interactionId);
                    return;
                } catch (RemoteException e) {
                    return;
                }
            }
            try {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
                callback.setPerformAccessibilityActionResult(false, interactionId);
            } catch (RemoteException e2) {
            }
        } catch (Throwable th) {
            try {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
                callback.setPerformAccessibilityActionResult(succeeded, interactionId);
            } catch (RemoteException e3) {
            }
            throw th;
        }
    }

    public void clearAccessibilityFocusClientThread() {
        Message message = this.mHandler.obtainMessage();
        message.what = 101;
        scheduleMessage(message, 0, 0L, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAccessibilityFocusUiThread() {
        if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null) {
            try {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 8;
                View root = this.mViewRootImpl.mView;
                if (root != null && isShown(root)) {
                    View host = this.mViewRootImpl.mAccessibilityFocusedHost;
                    if (host != null && ViewRootImpl.isViewDescendantOf(host, root)) {
                        AccessibilityNodeProvider provider = host.getAccessibilityNodeProvider();
                        AccessibilityNodeInfo focusNode = this.mViewRootImpl.mAccessibilityFocusedVirtualView;
                        if (provider == null || focusNode == null) {
                            host.performAccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
                        } else {
                            int virtualNodeId = AccessibilityNodeInfo.getVirtualDescendantId(focusNode.getSourceNodeId());
                            provider.performAction(virtualNodeId, AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
                        }
                    }
                }
            } finally {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            }
        }
    }

    public void notifyOutsideTouchClientThread() {
        Message message = this.mHandler.obtainMessage();
        message.what = 102;
        scheduleMessage(message, 0, 0L, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOutsideTouchUiThread() {
        View root;
        if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null && !this.mViewRootImpl.mStopped && !this.mViewRootImpl.mPausedForTransition && (root = this.mViewRootImpl.mView) != null && isShown(root)) {
            long now = SystemClock.uptimeMillis();
            MotionEvent event = MotionEvent.obtain(now, now, 4, 0.0f, 0.0f, 0);
            event.setSource(4098);
            this.mViewRootImpl.dispatchInputEvent(event);
        }
    }

    private View findViewByAccessibilityId(int accessibilityId) {
        if (accessibilityId == 2147483646) {
            return this.mViewRootImpl.mView;
        }
        return AccessibilityNodeIdManager.getInstance().findView(accessibilityId);
    }

    private void adjustIsVisibleToUserIfNeeded(AccessibilityNodeInfo info, Region interactiveRegion) {
        if (interactiveRegion != null && info != null) {
            Rect boundsInScreen = this.mTempRect;
            info.getBoundsInScreen(boundsInScreen);
            if (interactiveRegion.quickReject(boundsInScreen) && !shouldBypassAdjustIsVisible()) {
                info.setVisibleToUser(false);
            }
        }
    }

    private boolean shouldBypassAdjustIsVisible() {
        int windowType = this.mViewRootImpl.mOrigWindowType;
        if (windowType == 2011) {
            return true;
        }
        return false;
    }

    private void adjustBoundsInScreenIfNeeded(AccessibilityNodeInfo info) {
        if (info != null && !shouldBypassAdjustBoundsInScreen()) {
            Rect boundsInScreen = this.mTempRect;
            info.getBoundsInScreen(boundsInScreen);
            boundsInScreen.offset(this.mViewRootImpl.mAttachInfo.mLocationInParentDisplay.x, this.mViewRootImpl.mAttachInfo.mLocationInParentDisplay.y);
            info.setBoundsInScreen(boundsInScreen);
        }
    }

    private boolean shouldBypassAdjustBoundsInScreen() {
        return this.mViewRootImpl.mAttachInfo.mLocationInParentDisplay.equals(0, 0);
    }

    private void applyScreenMatrixIfNeeded(List<AccessibilityNodeInfo> infos) {
        if (!(infos == null || shouldBypassApplyScreenMatrix())) {
            int infoCount = infos.size();
            for (int i = 0; i < infoCount; i++) {
                AccessibilityNodeInfo info = infos.get(i);
                applyScreenMatrixIfNeeded(info);
            }
        }
    }

    private void applyScreenMatrixIfNeeded(AccessibilityNodeInfo info) {
        if (info != null && !shouldBypassApplyScreenMatrix()) {
            Rect boundsInScreen = this.mTempRect;
            RectF transformedBounds = this.mTempRectF;
            Matrix screenMatrix = this.mViewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy;
            info.getBoundsInScreen(boundsInScreen);
            transformedBounds.set(boundsInScreen);
            screenMatrix.mapRect(transformedBounds);
            boundsInScreen.set((int) transformedBounds.left, (int) transformedBounds.top, (int) transformedBounds.right, (int) transformedBounds.bottom);
            info.setBoundsInScreen(boundsInScreen);
        }
    }

    private boolean shouldBypassApplyScreenMatrix() {
        Matrix screenMatrix = this.mViewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy;
        return screenMatrix == null || screenMatrix.isIdentity();
    }

    private void associateLeashedParentIfNeeded(AccessibilityNodeInfo info) {
        if (info != null && !shouldBypassAssociateLeashedParent() && this.mViewRootImpl.mView.getAccessibilityViewId() == AccessibilityNodeInfo.getAccessibilityViewId(info.getSourceNodeId())) {
            info.setLeashedParent(this.mViewRootImpl.mAttachInfo.mLeashedParentToken, this.mViewRootImpl.mAttachInfo.mLeashedParentAccessibilityViewId);
        }
    }

    private boolean shouldBypassAssociateLeashedParent() {
        return this.mViewRootImpl.mAttachInfo.mLeashedParentToken == null && this.mViewRootImpl.mAttachInfo.mLeashedParentAccessibilityViewId == -1;
    }

    private void applyAppScaleAndMagnificationSpecIfNeeded(AccessibilityNodeInfo info, MagnificationSpec spec) {
        if (info != null) {
            float applicationScale = this.mViewRootImpl.mAttachInfo.mApplicationScale;
            if (shouldApplyAppScaleAndMagnificationSpec(applicationScale, spec)) {
                Rect boundsInParent = this.mTempRect;
                Rect boundsInScreen = this.mTempRect1;
                info.getBoundsInParent(boundsInParent);
                info.getBoundsInScreen(boundsInScreen);
                if (applicationScale != 1.0f) {
                    boundsInParent.scale(applicationScale);
                    boundsInScreen.scale(applicationScale);
                }
                if (spec != null) {
                    boundsInParent.scale(spec.scale);
                    boundsInScreen.scale(spec.scale);
                    boundsInScreen.offset((int) spec.offsetX, (int) spec.offsetY);
                }
                info.setBoundsInParent(boundsInParent);
                info.setBoundsInScreen(boundsInScreen);
                if (info.hasExtras()) {
                    Bundle extras = info.getExtras();
                    Parcelable[] textLocations = extras.getParcelableArray(AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY);
                    if (textLocations != null) {
                        for (Parcelable parcelable : textLocations) {
                            RectF textLocation = (RectF) parcelable;
                            textLocation.scale(applicationScale);
                            if (spec != null) {
                                textLocation.scale(spec.scale);
                                textLocation.offset(spec.offsetX, spec.offsetY);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean shouldApplyAppScaleAndMagnificationSpec(float appScale, MagnificationSpec spec) {
        return appScale != 1.0f || (spec != null && !spec.isNop());
    }

    private void updateInfosForViewPort(List<AccessibilityNodeInfo> infos, MagnificationSpec spec, Region interactiveRegion) {
        for (int i = 0; i < infos.size(); i++) {
            updateInfoForViewPort(infos.get(i), spec, interactiveRegion);
        }
    }

    private void updateInfoForViewPort(AccessibilityNodeInfo info, MagnificationSpec spec, Region interactiveRegion) {
        associateLeashedParentIfNeeded(info);
        applyScreenMatrixIfNeeded(info);
        adjustBoundsInScreenIfNeeded(info);
        adjustIsVisibleToUserIfNeeded(info, interactiveRegion);
        applyAppScaleAndMagnificationSpecIfNeeded(info, spec);
    }

    private void updateInfosForViewportAndReturnFindNodeResult(List<AccessibilityNodeInfo> infos, IAccessibilityInteractionConnectionCallback callback, int interactionId, MagnificationSpec spec, Region interactiveRegion) {
        if (infos != null) {
            updateInfosForViewPort(infos, spec, interactiveRegion);
        }
        returnFindNodesResult(infos, callback, interactionId);
    }

    private void returnFindNodeResult(AccessibilityNodeInfo info, IAccessibilityInteractionConnectionCallback callback, int interactionId) {
        try {
            callback.setFindAccessibilityNodeInfoResult(info, interactionId);
        } catch (RemoteException e) {
        }
    }

    private void returnFindNodeResult(SatisfiedFindAccessibilityNodeByAccessibilityIdRequest satisfiedRequest) {
        try {
            AccessibilityNodeInfo info = satisfiedRequest.mSatisfiedRequestNode;
            IAccessibilityInteractionConnectionCallback callback = satisfiedRequest.mSatisfiedRequestCallback;
            int interactionId = satisfiedRequest.mSatisfiedRequestInteractionId;
            callback.setFindAccessibilityNodeInfoResult(info, interactionId);
        } catch (RemoteException e) {
        }
    }

    private void returnFindNodesResult(List<AccessibilityNodeInfo> infos, IAccessibilityInteractionConnectionCallback callback, int interactionId) {
        try {
            callback.setFindAccessibilityNodeInfosResult(infos, interactionId);
            if (infos != null) {
                infos.clear();
            }
        } catch (RemoteException e) {
        }
    }

    private SatisfiedFindAccessibilityNodeByAccessibilityIdRequest getSatisfiedRequestInPrefetch(AccessibilityNodeInfo requestedNode, List<AccessibilityNodeInfo> infos, int flags) {
        SatisfiedFindAccessibilityNodeByAccessibilityIdRequest satisfiedRequest = null;
        synchronized (this.mLock) {
            int i = 0;
            while (true) {
                if (i >= this.mPendingFindNodeByIdMessages.size()) {
                    break;
                }
                Message pendingMessage = this.mPendingFindNodeByIdMessages.get(i);
                int pendingFlags = pendingMessage.arg1;
                if ((pendingFlags & 24) == (flags & 24)) {
                    SomeArgs args = (SomeArgs) pendingMessage.obj;
                    int accessibilityViewId = args.argi1;
                    int virtualDescendantId = args.argi2;
                    AccessibilityNodeInfo satisfiedRequestNode = nodeWithIdFromList(requestedNode, infos, AccessibilityNodeInfo.makeNodeId(accessibilityViewId, virtualDescendantId));
                    if (satisfiedRequestNode != null) {
                        this.mHandler.removeMessages(2, pendingMessage.obj);
                        IAccessibilityInteractionConnectionCallback satisfiedRequestCallback = (IAccessibilityInteractionConnectionCallback) args.arg1;
                        int satisfiedRequestInteractionId = args.argi3;
                        satisfiedRequest = new SatisfiedFindAccessibilityNodeByAccessibilityIdRequest(satisfiedRequestNode, satisfiedRequestCallback, satisfiedRequestInteractionId);
                        args.recycle();
                        break;
                    }
                }
                i++;
            }
            this.mPendingFindNodeByIdMessages.clear();
        }
        return satisfiedRequest;
    }

    private AccessibilityNodeInfo nodeWithIdFromList(AccessibilityNodeInfo requestedNode, List<AccessibilityNodeInfo> infos, long nodeId) {
        if (requestedNode != null && requestedNode.getSourceNodeId() == nodeId) {
            return requestedNode;
        }
        for (int j = 0; j < infos.size(); j++) {
            AccessibilityNodeInfo info = infos.get(j);
            if (info.getSourceNodeId() == nodeId) {
                return info;
            }
        }
        return null;
    }

    private void returnPrefetchResult(int interactionId, List<AccessibilityNodeInfo> infos, IAccessibilityInteractionConnectionCallback callback) {
        if (infos.size() > 0) {
            try {
                callback.setPrefetchAccessibilityNodeInfoResult(infos, interactionId);
            } catch (RemoteException e) {
            }
        }
    }

    private void updateInfoForViewportAndReturnFindNodeResult(AccessibilityNodeInfo info, IAccessibilityInteractionConnectionCallback callback, int interactionId, MagnificationSpec spec, Region interactiveRegion) {
        updateInfoForViewPort(info, spec, interactiveRegion);
        returnFindNodeResult(info, callback, interactionId);
    }

    private boolean handleClickableSpanActionUiThread(View view, int virtualDescendantId, Bundle arguments) {
        ClickableSpan clickableSpan;
        Parcelable span = arguments.getParcelable(AccessibilityNodeInfo.ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN);
        if (!(span instanceof AccessibilityClickableSpan)) {
            return false;
        }
        AccessibilityNodeInfo infoWithSpan = null;
        AccessibilityNodeProvider provider = view.getAccessibilityNodeProvider();
        if (provider != null) {
            infoWithSpan = provider.createAccessibilityNodeInfo(virtualDescendantId);
        } else if (virtualDescendantId == -1) {
            infoWithSpan = view.createAccessibilityNodeInfo();
        }
        if (infoWithSpan == null || (clickableSpan = ((AccessibilityClickableSpan) span).findClickableSpan(infoWithSpan.getOriginalText())) == null) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class AccessibilityNodePrefetcher {
        private static final int MAX_ACCESSIBILITY_NODE_INFO_BATCH_SIZE = 50;
        private final ArrayList<View> mTempViewList;

        private AccessibilityNodePrefetcher() {
            this.mTempViewList = new ArrayList<>();
        }

        public void prefetchAccessibilityNodeInfos(View view, AccessibilityNodeInfo root, int virtualViewId, int fetchFlags, List<AccessibilityNodeInfo> outInfos) {
            if (root != null) {
                AccessibilityNodeProvider provider = view.getAccessibilityNodeProvider();
                if (provider == null) {
                    if ((fetchFlags & 1) != 0) {
                        prefetchPredecessorsOfRealNode(view, outInfos);
                    }
                    if ((fetchFlags & 2) != 0) {
                        prefetchSiblingsOfRealNode(view, outInfos);
                    }
                    if ((fetchFlags & 4) != 0) {
                        prefetchDescendantsOfRealNode(view, outInfos);
                        return;
                    }
                    return;
                }
                if ((fetchFlags & 1) != 0) {
                    prefetchPredecessorsOfVirtualNode(root, view, provider, outInfos);
                }
                if ((fetchFlags & 2) != 0) {
                    prefetchSiblingsOfVirtualNode(root, view, provider, outInfos);
                }
                if ((fetchFlags & 4) != 0) {
                    prefetchDescendantsOfVirtualNode(root, provider, outInfos);
                }
            }
        }

        private boolean shouldStopPrefetching(List prefetchededInfos) {
            return AccessibilityInteractionController.this.mHandler.hasUserInteractiveMessagesWaiting() || prefetchededInfos.size() >= 50;
        }

        private void enforceNodeTreeConsistent(AccessibilityNodeInfo root, List<AccessibilityNodeInfo> nodes) {
            LongSparseArray<AccessibilityNodeInfo> nodeMap = new LongSparseArray<>();
            int nodeCount = nodes.size();
            for (int i = 0; i < nodeCount; i++) {
                AccessibilityNodeInfo node = nodes.get(i);
                nodeMap.put(node.getSourceNodeId(), node);
            }
            for (AccessibilityNodeInfo parent = root; parent != null; parent = nodeMap.get(parent.getParentNodeId())) {
                root = parent;
            }
            AccessibilityNodeInfo accessFocus = null;
            AccessibilityNodeInfo inputFocus = null;
            HashSet<AccessibilityNodeInfo> seen = new HashSet<>();
            Queue<AccessibilityNodeInfo> fringe = new LinkedList<>();
            fringe.add(root);
            while (!fringe.isEmpty()) {
                AccessibilityNodeInfo current = fringe.poll();
                if (seen.add(current)) {
                    if (current.isAccessibilityFocused()) {
                        if (accessFocus == null) {
                            accessFocus = current;
                        } else {
                            throw new IllegalStateException("Duplicate accessibility focus:" + current + " in window:" + AccessibilityInteractionController.this.mViewRootImpl.mAttachInfo.mAccessibilityWindowId);
                        }
                    }
                    if (current.isFocused()) {
                        if (inputFocus == null) {
                            inputFocus = current;
                        } else {
                            throw new IllegalStateException("Duplicate input focus: " + current + " in window:" + AccessibilityInteractionController.this.mViewRootImpl.mAttachInfo.mAccessibilityWindowId);
                        }
                    }
                    int childCount = current.getChildCount();
                    for (int j = 0; j < childCount; j++) {
                        long childId = current.getChildId(j);
                        AccessibilityNodeInfo child = nodeMap.get(childId);
                        if (child != null) {
                            fringe.add(child);
                        }
                    }
                } else {
                    throw new IllegalStateException("Duplicate node: " + current + " in window:" + AccessibilityInteractionController.this.mViewRootImpl.mAttachInfo.mAccessibilityWindowId);
                }
            }
            for (int j2 = nodeMap.size() - 1; j2 >= 0; j2--) {
                AccessibilityNodeInfo info = nodeMap.valueAt(j2);
                if (!seen.contains(info)) {
                    throw new IllegalStateException("Disconnected node: " + info);
                }
            }
        }

        private void prefetchPredecessorsOfRealNode(View view, List<AccessibilityNodeInfo> outInfos) {
            if (!shouldStopPrefetching(outInfos)) {
                for (ViewParent parent = view.getParentForAccessibility(); (parent instanceof View) && !shouldStopPrefetching(outInfos); parent = parent.getParentForAccessibility()) {
                    View parentView = (View) parent;
                    AccessibilityNodeInfo info = parentView.createAccessibilityNodeInfo();
                    if (info != null) {
                        outInfos.add(info);
                    }
                }
            }
        }

        private void prefetchSiblingsOfRealNode(View current, List<AccessibilityNodeInfo> outInfos) {
            AccessibilityNodeInfo info;
            if (!shouldStopPrefetching(outInfos)) {
                ViewParent parent = current.getParentForAccessibility();
                if (parent instanceof ViewGroup) {
                    ViewGroup parentGroup = (ViewGroup) parent;
                    ArrayList<View> children = this.mTempViewList;
                    children.clear();
                    try {
                        parentGroup.addChildrenForAccessibility(children);
                        int childCount = children.size();
                        for (int i = 0; i < childCount; i++) {
                            if (!shouldStopPrefetching(outInfos)) {
                                View child = children.get(i);
                                if (child.getAccessibilityViewId() != current.getAccessibilityViewId() && AccessibilityInteractionController.this.isShown(child)) {
                                    AccessibilityNodeProvider provider = child.getAccessibilityNodeProvider();
                                    if (provider == null) {
                                        info = child.createAccessibilityNodeInfo();
                                    } else {
                                        info = provider.createAccessibilityNodeInfo(-1);
                                    }
                                    if (info != null) {
                                        outInfos.add(info);
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    } finally {
                        children.clear();
                    }
                }
            }
        }

        private void prefetchDescendantsOfRealNode(View root, List<AccessibilityNodeInfo> outInfos) {
            if (!shouldStopPrefetching(outInfos) && (root instanceof ViewGroup)) {
                HashMap<View, AccessibilityNodeInfo> addedChildren = new HashMap<>();
                ArrayList<View> children = this.mTempViewList;
                children.clear();
                try {
                    root.addChildrenForAccessibility(children);
                    int childCount = children.size();
                    for (int i = 0; i < childCount; i++) {
                        if (!shouldStopPrefetching(outInfos)) {
                            View child = children.get(i);
                            if (AccessibilityInteractionController.this.isShown(child)) {
                                AccessibilityNodeProvider provider = child.getAccessibilityNodeProvider();
                                if (provider == null) {
                                    AccessibilityNodeInfo info = child.createAccessibilityNodeInfo();
                                    if (info != null) {
                                        outInfos.add(info);
                                        addedChildren.put(child, null);
                                    }
                                } else {
                                    AccessibilityNodeInfo info2 = provider.createAccessibilityNodeInfo(-1);
                                    if (info2 != null) {
                                        outInfos.add(info2);
                                        addedChildren.put(child, info2);
                                    }
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    children.clear();
                    if (!shouldStopPrefetching(outInfos)) {
                        for (Map.Entry<View, AccessibilityNodeInfo> entry : addedChildren.entrySet()) {
                            View addedChild = entry.getKey();
                            AccessibilityNodeInfo virtualRoot = entry.getValue();
                            if (virtualRoot == null) {
                                prefetchDescendantsOfRealNode(addedChild, outInfos);
                            } else {
                                prefetchDescendantsOfVirtualNode(virtualRoot, addedChild.getAccessibilityNodeProvider(), outInfos);
                            }
                        }
                    }
                } finally {
                    children.clear();
                }
            }
        }

        private void prefetchPredecessorsOfVirtualNode(AccessibilityNodeInfo root, View providerHost, AccessibilityNodeProvider provider, List<AccessibilityNodeInfo> outInfos) {
            int initialResultSize = outInfos.size();
            long parentNodeId = root.getParentNodeId();
            int accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(parentNodeId);
            while (accessibilityViewId != Integer.MAX_VALUE && !shouldStopPrefetching(outInfos)) {
                int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(parentNodeId);
                if (virtualDescendantId != -1 || accessibilityViewId == providerHost.getAccessibilityViewId()) {
                    AccessibilityNodeInfo parent = provider.createAccessibilityNodeInfo(virtualDescendantId);
                    if (parent == null) {
                        int currentResultSize = outInfos.size();
                        for (int i = currentResultSize - 1; i >= initialResultSize; i--) {
                            outInfos.remove(i);
                        }
                        return;
                    }
                    outInfos.add(parent);
                    parentNodeId = parent.getParentNodeId();
                    accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(parentNodeId);
                } else {
                    prefetchPredecessorsOfRealNode(providerHost, outInfos);
                    return;
                }
            }
        }

        private void prefetchSiblingsOfVirtualNode(AccessibilityNodeInfo current, View providerHost, AccessibilityNodeProvider provider, List<AccessibilityNodeInfo> outInfos) {
            long parentNodeId = current.getParentNodeId();
            int parentAccessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(parentNodeId);
            int parentVirtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(parentNodeId);
            if (parentVirtualDescendantId != -1 || parentAccessibilityViewId == providerHost.getAccessibilityViewId()) {
                AccessibilityNodeInfo parent = provider.createAccessibilityNodeInfo(parentVirtualDescendantId);
                if (parent != null) {
                    int childCount = parent.getChildCount();
                    for (int i = 0; i < childCount && !shouldStopPrefetching(outInfos); i++) {
                        long childNodeId = parent.getChildId(i);
                        if (childNodeId != current.getSourceNodeId()) {
                            int childVirtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(childNodeId);
                            AccessibilityNodeInfo child = provider.createAccessibilityNodeInfo(childVirtualDescendantId);
                            if (child != null) {
                                outInfos.add(child);
                            }
                        }
                    }
                    return;
                }
                return;
            }
            prefetchSiblingsOfRealNode(providerHost, outInfos);
        }

        private void prefetchDescendantsOfVirtualNode(AccessibilityNodeInfo root, AccessibilityNodeProvider provider, List<AccessibilityNodeInfo> outInfos) {
            int initialOutInfosSize = outInfos.size();
            int childCount = root.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (!shouldStopPrefetching(outInfos)) {
                    long childNodeId = root.getChildId(i);
                    AccessibilityNodeInfo child = provider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(childNodeId));
                    if (child != null) {
                        outInfos.add(child);
                    }
                } else {
                    return;
                }
            }
            if (!shouldStopPrefetching(outInfos)) {
                int addedChildCount = outInfos.size() - initialOutInfosSize;
                for (int i2 = 0; i2 < addedChildCount; i2++) {
                    prefetchDescendantsOfVirtualNode(outInfos.get(initialOutInfosSize + i2), provider, outInfos);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class PrivateHandler extends Handler {
        private static final int FIRST_NO_ACCESSIBILITY_CALLBACK_MSG = 100;
        private static final int MSG_APP_PREPARATION_FINISHED = 8;
        private static final int MSG_APP_PREPARATION_TIMEOUT = 9;
        private static final int MSG_CLEAR_ACCESSIBILITY_FOCUS = 101;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFOS_BY_VIEW_ID = 3;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_ACCESSIBILITY_ID = 2;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_TEXT = 4;
        private static final int MSG_FIND_FOCUS = 5;
        private static final int MSG_FOCUS_SEARCH = 6;
        private static final int MSG_NOTIFY_OUTSIDE_TOUCH = 102;
        private static final int MSG_PERFORM_ACCESSIBILITY_ACTION = 1;
        private static final int MSG_PREPARE_FOR_EXTRA_DATA_REQUEST = 7;

        public PrivateHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public String getMessageName(Message message) {
            int type = message.what;
            switch (type) {
                case 1:
                    return "MSG_PERFORM_ACCESSIBILITY_ACTION";
                case 2:
                    return "MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_ACCESSIBILITY_ID";
                case 3:
                    return "MSG_FIND_ACCESSIBILITY_NODE_INFOS_BY_VIEW_ID";
                case 4:
                    return "MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_TEXT";
                case 5:
                    return "MSG_FIND_FOCUS";
                case 6:
                    return "MSG_FOCUS_SEARCH";
                case 7:
                    return "MSG_PREPARE_FOR_EXTRA_DATA_REQUEST";
                case 8:
                    return "MSG_APP_PREPARATION_FINISHED";
                case 9:
                    return "MSG_APP_PREPARATION_TIMEOUT";
                case 101:
                    return "MSG_CLEAR_ACCESSIBILITY_FOCUS";
                case 102:
                    return "MSG_NOTIFY_OUTSIDE_TOUCH";
                default:
                    throw new IllegalArgumentException("Unknown message type: " + type);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int type = message.what;
            switch (type) {
                case 1:
                    AccessibilityInteractionController.this.performAccessibilityActionUiThread(message);
                    return;
                case 2:
                    AccessibilityInteractionController.this.findAccessibilityNodeInfoByAccessibilityIdUiThread(message);
                    return;
                case 3:
                    AccessibilityInteractionController.this.findAccessibilityNodeInfosByViewIdUiThread(message);
                    return;
                case 4:
                    AccessibilityInteractionController.this.findAccessibilityNodeInfosByTextUiThread(message);
                    return;
                case 5:
                    AccessibilityInteractionController.this.findFocusUiThread(message);
                    return;
                case 6:
                    AccessibilityInteractionController.this.focusSearchUiThread(message);
                    return;
                case 7:
                    AccessibilityInteractionController.this.prepareForExtraDataRequestUiThread(message);
                    return;
                case 8:
                    AccessibilityInteractionController.this.requestPreparerDoneUiThread(message);
                    return;
                case 9:
                    AccessibilityInteractionController.this.requestPreparerTimeoutUiThread();
                    return;
                case 101:
                    AccessibilityInteractionController.this.clearAccessibilityFocusUiThread();
                    return;
                case 102:
                    AccessibilityInteractionController.this.notifyOutsideTouchUiThread();
                    return;
                default:
                    throw new IllegalArgumentException("Unknown message type: " + type);
            }
        }

        boolean hasAccessibilityCallback(Message message) {
            return message.what < 100;
        }

        boolean hasUserInteractiveMessagesWaiting() {
            return hasMessagesOrCallbacks();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class AddNodeInfosForViewId implements Predicate<View> {
        private List<AccessibilityNodeInfo> mInfos;
        private int mViewId;

        private AddNodeInfosForViewId() {
            this.mViewId = -1;
        }

        public void init(int viewId, List<AccessibilityNodeInfo> infos) {
            this.mViewId = viewId;
            this.mInfos = infos;
        }

        public void reset() {
            this.mViewId = -1;
            this.mInfos = null;
        }

        public boolean test(View view) {
            if (view.getId() != this.mViewId || !AccessibilityInteractionController.this.isShown(view)) {
                return false;
            }
            this.mInfos.add(view.createAccessibilityNodeInfo());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class MessageHolder {
        final int mInterrogatingPid;
        final long mInterrogatingTid;
        final Message mMessage;

        MessageHolder(Message message, int interrogatingPid, long interrogatingTid) {
            this.mMessage = message;
            this.mInterrogatingPid = interrogatingPid;
            this.mInterrogatingTid = interrogatingTid;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class SatisfiedFindAccessibilityNodeByAccessibilityIdRequest {
        final IAccessibilityInteractionConnectionCallback mSatisfiedRequestCallback;
        final int mSatisfiedRequestInteractionId;
        final AccessibilityNodeInfo mSatisfiedRequestNode;

        SatisfiedFindAccessibilityNodeByAccessibilityIdRequest(AccessibilityNodeInfo satisfiedRequestNode, IAccessibilityInteractionConnectionCallback satisfiedRequestCallback, int satisfiedRequestInteractionId) {
            this.mSatisfiedRequestNode = satisfiedRequestNode;
            this.mSatisfiedRequestCallback = satisfiedRequestCallback;
            this.mSatisfiedRequestInteractionId = satisfiedRequestInteractionId;
        }
    }
}
