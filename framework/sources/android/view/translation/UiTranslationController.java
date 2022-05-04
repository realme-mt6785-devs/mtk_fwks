package android.view.translation;

import android.app.Activity;
import android.app.assist.ActivityId;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.IntArray;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManagerGlobal;
import android.view.autofill.AutofillId;
import android.view.translation.TranslationRequest;
import android.widget.TextView;
import android.widget.TextViewTranslationCallback;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public class UiTranslationController {
    private static final int ANIMATION_DURATION_MILLIS = 250;
    public static final boolean DEBUG = Log.isLoggable(UiTranslationManager.LOG_TAG, 3);
    private static final String TAG = "UiTranslationController";
    private final Activity mActivity;
    private final Context mContext;
    private int mCurrentState;
    private ArraySet<AutofillId> mLastRequestAutofillIds;
    private final Handler mWorkerHandler;
    private final HandlerThread mWorkerThread;
    private final Object mLock = new Object();
    private final ArrayMap<AutofillId, WeakReference<View>> mViews = new ArrayMap<>();
    private final ArrayMap<Pair<TranslationSpec, TranslationSpec>, Translator> mTranslators = new ArrayMap<>();
    private final ArraySet<AutofillId> mViewsToPadContent = new ArraySet<>();

    public UiTranslationController(Activity activity, Context context) {
        this.mActivity = activity;
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("UiTranslationController_" + activity.getComponentName(), -2);
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorkerHandler = handlerThread.getThreadHandler();
    }

    public void updateUiTranslationState(int state, TranslationSpec sourceSpec, TranslationSpec targetSpec, List<AutofillId> views, UiTranslationSpec uiTranslationSpec) {
        String str;
        if (this.mActivity.isResumed() || !(state == 0 || state == 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("updateUiTranslationState state: ");
            sb.append(stateToString(state));
            if (DEBUG) {
                str = ", views: " + views + ", spec: " + uiTranslationSpec;
            } else {
                str = "";
            }
            sb.append(str);
            Log.i(TAG, sb.toString());
            synchronized (this.mLock) {
                this.mCurrentState = state;
                if (views != null) {
                    setLastRequestAutofillIdsLocked(views);
                }
            }
            switch (state) {
                case 0:
                    if (uiTranslationSpec != null && uiTranslationSpec.shouldPadContentForCompat()) {
                        synchronized (this.mLock) {
                            this.mViewsToPadContent.addAll(views);
                        }
                    }
                    Pair<TranslationSpec, TranslationSpec> specs = new Pair<>(sourceSpec, targetSpec);
                    if (!this.mTranslators.containsKey(specs)) {
                        this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage(UiTranslationController$$ExternalSyntheticLambda0.INSTANCE, this, sourceSpec, targetSpec, views));
                        return;
                    } else {
                        onUiTranslationStarted(this.mTranslators.get(specs), views);
                        return;
                    }
                case 1:
                    runForEachView(UiTranslationController$$ExternalSyntheticLambda7.INSTANCE);
                    return;
                case 2:
                    runForEachView(UiTranslationController$$ExternalSyntheticLambda8.INSTANCE);
                    return;
                case 3:
                    destroyTranslators();
                    runForEachView(UiTranslationController$$ExternalSyntheticLambda9.INSTANCE);
                    notifyTranslationFinished(false);
                    synchronized (this.mLock) {
                        this.mViews.clear();
                    }
                    return;
                default:
                    Log.w(TAG, "onAutoTranslationStateChange(): unknown state: " + state);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateUiTranslationState$2(View view, ViewTranslationCallback callback) {
        callback.onClearTranslation(view);
        view.clearViewTranslationResponse();
        if (view.hasTranslationTransientState()) {
            view.setHasTransientState(false);
            view.setHasTranslationTransientState(false);
        }
    }

    public void onActivityDestroyed() {
        synchronized (this.mLock) {
            if (DEBUG) {
                Log.i(TAG, "onActivityDestroyed(): mCurrentState is " + stateToString(this.mCurrentState));
            }
            if (this.mCurrentState != 3) {
                notifyTranslationFinished(true);
            }
            this.mViews.clear();
            destroyTranslators();
            this.mWorkerThread.quitSafely();
        }
    }

    private void notifyTranslationFinished(boolean activityDestroyed) {
        UiTranslationManager manager = (UiTranslationManager) this.mContext.getSystemService(UiTranslationManager.class);
        if (manager != null) {
            manager.onTranslationFinished(activityDestroyed, new ActivityId(this.mActivity.getTaskId(), this.mActivity.getShareableActivityToken()), this.mActivity.getComponentName());
        }
    }

    private void setLastRequestAutofillIdsLocked(List<AutofillId> views) {
        if (this.mLastRequestAutofillIds == null) {
            this.mLastRequestAutofillIds = new ArraySet<>();
        }
        if (this.mLastRequestAutofillIds.size() > 0) {
            this.mLastRequestAutofillIds.clear();
        }
        this.mLastRequestAutofillIds.addAll(views);
    }

    public void dump(String outerPrefix, PrintWriter pw) {
        pw.print(outerPrefix);
        pw.println("UiTranslationController:");
        String pfx = outerPrefix + "  ";
        pw.print(pfx);
        pw.print("activity: ");
        pw.print(this.mActivity);
        pw.print(pfx);
        pw.print("resumed: ");
        pw.println(this.mActivity.isResumed());
        pw.print(pfx);
        pw.print("current state: ");
        pw.println(this.mCurrentState);
        int translatorSize = this.mTranslators.size();
        pw.print(outerPrefix);
        pw.print("number translator: ");
        pw.println(translatorSize);
        for (int i = 0; i < translatorSize; i++) {
            pw.print(outerPrefix);
            pw.print("#");
            pw.println(i);
            Translator translator = this.mTranslators.valueAt(i);
            translator.dump(outerPrefix, pw);
            pw.println();
        }
        synchronized (this.mLock) {
            int viewSize = this.mViews.size();
            pw.print(outerPrefix);
            pw.print("number views: ");
            pw.println(viewSize);
            for (int i2 = 0; i2 < viewSize; i2++) {
                pw.print(outerPrefix);
                pw.print("#");
                pw.println(i2);
                AutofillId autofillId = this.mViews.keyAt(i2);
                View view = this.mViews.valueAt(i2).get();
                pw.print(pfx);
                pw.print("autofillId: ");
                pw.println(autofillId);
                pw.print(pfx);
                pw.print("view:");
                pw.println(view);
            }
            pw.print(outerPrefix);
            pw.print("padded views: ");
            pw.println(this.mViewsToPadContent);
        }
        if (DEBUG) {
            dumpViewByTraversal(outerPrefix, pw);
        }
    }

    private void dumpViewByTraversal(String outerPrefix, PrintWriter pw) {
        ArrayList<ViewRootImpl> roots = WindowManagerGlobal.getInstance().getRootViews(this.mActivity.getActivityToken());
        pw.print(outerPrefix);
        pw.println("Dump views:");
        for (int rootNum = 0; rootNum < roots.size(); rootNum++) {
            View rootView = roots.get(rootNum).getView();
            if (rootView instanceof ViewGroup) {
                dumpChildren((ViewGroup) rootView, outerPrefix, pw);
            } else {
                dumpViewInfo(rootView, outerPrefix, pw);
            }
        }
    }

    private void dumpChildren(ViewGroup viewGroup, String outerPrefix, PrintWriter pw) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                pw.print(outerPrefix);
                pw.println("Children: ");
                pw.print(outerPrefix);
                pw.print(outerPrefix);
                pw.println(child);
                dumpChildren((ViewGroup) child, outerPrefix, pw);
            } else {
                pw.print(outerPrefix);
                pw.println("End Children: ");
                pw.print(outerPrefix);
                pw.print(outerPrefix);
                pw.print(child);
                dumpViewInfo(child, outerPrefix, pw);
            }
        }
    }

    private void dumpViewInfo(View view, String outerPrefix, PrintWriter pw) {
        AutofillId autofillId = view.getAutofillId();
        pw.print(outerPrefix);
        pw.print("autofillId: ");
        pw.print(autofillId);
        boolean isContainsView = false;
        boolean isRequestedView = false;
        synchronized (this.mLock) {
            if (this.mLastRequestAutofillIds.contains(autofillId)) {
                isRequestedView = true;
            }
            WeakReference<View> viewRef = this.mViews.get(autofillId);
            if (!(viewRef == null || viewRef.get() == null)) {
                isContainsView = true;
            }
        }
        pw.print(outerPrefix);
        pw.print("isContainsView: ");
        pw.print(isContainsView);
        pw.print(outerPrefix);
        pw.print("isRequestedView: ");
        pw.println(isRequestedView);
    }

    public void onTranslationCompleted(TranslationResponse response) {
        Object obj;
        if (response == null || response.getTranslationStatus() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Fail result from TranslationService, status=");
            if (response == null) {
                obj = "null";
            } else {
                obj = Integer.valueOf(response.getTranslationStatus());
            }
            sb.append(obj);
            Log.w(TAG, sb.toString());
            return;
        }
        SparseArray<ViewTranslationResponse> translatedResult = response.getViewTranslationResponses();
        SparseArray<ViewTranslationResponse> viewsResult = new SparseArray<>();
        SparseArray<LongSparseArray<ViewTranslationResponse>> virtualViewsResult = new SparseArray<>();
        IntArray viewIds = new IntArray(1);
        for (int i = 0; i < translatedResult.size(); i++) {
            ViewTranslationResponse result = translatedResult.valueAt(i);
            AutofillId autofillId = result.getAutofillId();
            if (viewIds.indexOf(autofillId.getViewId()) < 0) {
                viewIds.add(autofillId.getViewId());
            }
            if (autofillId.isNonVirtual()) {
                viewsResult.put(translatedResult.keyAt(i), result);
            } else {
                boolean isVirtualViewAdded = virtualViewsResult.indexOfKey(autofillId.getViewId()) >= 0;
                LongSparseArray<ViewTranslationResponse> childIds = isVirtualViewAdded ? virtualViewsResult.get(autofillId.getViewId()) : new LongSparseArray<>();
                childIds.put(autofillId.getVirtualChildLongId(), result);
                if (!isVirtualViewAdded) {
                    virtualViewsResult.put(autofillId.getViewId(), childIds);
                }
            }
        }
        findViewsTraversalByAutofillIds(viewIds);
        if (viewsResult.size() > 0) {
            onTranslationCompleted(viewsResult);
        }
        if (virtualViewsResult.size() > 0) {
            onVirtualViewTranslationCompleted(virtualViewsResult);
        }
    }

    private void onVirtualViewTranslationCompleted(SparseArray<LongSparseArray<ViewTranslationResponse>> translatedResult) {
        if (this.mActivity.isResumed()) {
            synchronized (this.mLock) {
                if (this.mCurrentState == 3) {
                    Log.w(TAG, "onTranslationCompleted: the translation state is finished now. Skip to show the translated text.");
                    return;
                }
                for (int i = 0; i < translatedResult.size(); i++) {
                    AutofillId autofillId = new AutofillId(translatedResult.keyAt(i));
                    final View view = this.mViews.get(autofillId).get();
                    if (view == null) {
                        Log.w(TAG, "onTranslationCompleted: the view for autofill id " + autofillId + " may be gone.");
                    } else {
                        final LongSparseArray<ViewTranslationResponse> virtualChildResponse = translatedResult.valueAt(i);
                        if (DEBUG) {
                            Log.v(TAG, "onVirtualViewTranslationCompleted: received response for AutofillId " + autofillId);
                        }
                        this.mActivity.runOnUiThread(new Runnable() { // from class: android.view.translation.UiTranslationController$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                UiTranslationController.lambda$onVirtualViewTranslationCompleted$3(View.this, virtualChildResponse);
                            }
                        });
                    }
                }
            }
        } else if (DEBUG) {
            Log.v(TAG, "onTranslationCompleted: Activity is not resumed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onVirtualViewTranslationCompleted$3(View view, LongSparseArray virtualChildResponse) {
        if (view.getViewTranslationCallback() != null) {
            view.onVirtualViewTranslationResponses(virtualChildResponse);
            if (view.getViewTranslationCallback() != null) {
                view.getViewTranslationCallback().onShowTranslation(view);
            }
        } else if (DEBUG) {
            Log.d(TAG, view + " doesn't support showing translation because of null ViewTranslationCallback.");
        }
    }

    private void onTranslationCompleted(SparseArray<ViewTranslationResponse> translatedResult) {
        if (this.mActivity.isResumed()) {
            int resultCount = translatedResult.size();
            if (DEBUG) {
                Log.v(TAG, "onTranslationCompleted: receive " + resultCount + " responses.");
            }
            synchronized (this.mLock) {
                if (this.mCurrentState == 3) {
                    Log.w(TAG, "onTranslationCompleted: the translation state is finished now. Skip to show the translated text.");
                    return;
                }
                for (int i = 0; i < resultCount; i++) {
                    final ViewTranslationResponse response = translatedResult.valueAt(i);
                    if (DEBUG) {
                        Log.v(TAG, "onTranslationCompleted: " + sanitizedViewTranslationResponse(response));
                    }
                    final AutofillId autofillId = response.getAutofillId();
                    if (autofillId == null) {
                        Log.w(TAG, "No AutofillId is set in ViewTranslationResponse");
                    } else {
                        final View view = this.mViews.get(autofillId).get();
                        if (view == null) {
                            Log.w(TAG, "onTranslationCompleted: the view for autofill id " + autofillId + " may be gone.");
                        } else {
                            this.mActivity.runOnUiThread(new Runnable() { // from class: android.view.translation.UiTranslationController$$ExternalSyntheticLambda4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    UiTranslationController.this.lambda$onTranslationCompleted$4$UiTranslationController(view, response, autofillId);
                                }
                            });
                        }
                    }
                }
            }
        } else if (DEBUG) {
            Log.v(TAG, "onTranslationCompleted: Activity is not resumed.");
        }
    }

    public /* synthetic */ void lambda$onTranslationCompleted$4$UiTranslationController(View view, ViewTranslationResponse response, AutofillId autofillId) {
        if (view.getViewTranslationResponse() == null || !view.getViewTranslationResponse().equals(response)) {
            ViewTranslationCallback callback = view.getViewTranslationCallback();
            if (callback == null) {
                if (view instanceof TextView) {
                    callback = new TextViewTranslationCallback();
                    view.setViewTranslationCallback(callback);
                } else if (DEBUG) {
                    Log.d(TAG, view + " doesn't support showing translation because of null ViewTranslationCallback.");
                    return;
                } else {
                    return;
                }
            }
            callback.setAnimationDurationMillis(250);
            if (this.mViewsToPadContent.contains(autofillId)) {
                callback.enableContentPadding();
            }
            view.onViewTranslationResponse(response);
            callback.onShowTranslation(view);
        } else if (DEBUG) {
            Log.d(TAG, "Duplicate ViewTranslationResponse for " + autofillId + ". Ignoring.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createTranslatorAndStart(TranslationSpec sourceSpec, TranslationSpec targetSpec, List<AutofillId> views) {
        Translator translator = createTranslatorIfNeeded(sourceSpec, targetSpec);
        if (translator == null) {
            Log.w(TAG, "Can not create Translator for sourceSpec:" + sourceSpec + " targetSpec:" + targetSpec);
            return;
        }
        onUiTranslationStarted(translator, views);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTranslationRequest(Translator translator, List<ViewTranslationRequest> requests) {
        if (requests.size() == 0) {
            Log.w(TAG, "No ViewTranslationRequest was collected.");
            return;
        }
        TranslationRequest request = new TranslationRequest.Builder().setViewTranslationRequests(requests).build();
        if (DEBUG) {
            StringBuilder msg = new StringBuilder("sendTranslationRequest:{requests=[");
            for (ViewTranslationRequest viewRequest : requests) {
                msg.append("{request=");
                msg.append(sanitizedViewTranslationRequest(viewRequest));
                msg.append("}, ");
            }
            Log.d(TAG, "sendTranslationRequest: " + msg.toString());
        }
        translator.requestUiTranslate(request, UiTranslationController$$ExternalSyntheticLambda6.INSTANCE, new Consumer() { // from class: android.view.translation.UiTranslationController$$ExternalSyntheticLambda10
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                UiTranslationController.this.onTranslationCompleted((TranslationResponse) obj);
            }
        });
    }

    private void onUiTranslationStarted(final Translator translator, List<AutofillId> views) {
        long[] childs;
        synchronized (this.mLock) {
            SparseIntArray virtualViewChildCount = getRequestVirtualViewChildCount(views);
            final Map<AutofillId, long[]> viewIds = new ArrayMap<>();
            Map<AutofillId, Integer> unusedIndices = null;
            for (int i = 0; i < views.size(); i++) {
                AutofillId autofillId = views.get(i);
                if (autofillId.isNonVirtual()) {
                    viewIds.put(autofillId, null);
                } else {
                    if (unusedIndices == null) {
                        unusedIndices = new ArrayMap<>();
                    }
                    AutofillId virtualViewAutofillId = new AutofillId(autofillId.getViewId());
                    int end = 0;
                    if (viewIds.containsKey(virtualViewAutofillId)) {
                        childs = viewIds.get(virtualViewAutofillId);
                        end = unusedIndices.get(virtualViewAutofillId).intValue();
                    } else {
                        int childCount = virtualViewChildCount.get(autofillId.getViewId());
                        long[] childs2 = new long[childCount];
                        viewIds.put(virtualViewAutofillId, childs2);
                        childs = childs2;
                    }
                    unusedIndices.put(virtualViewAutofillId, Integer.valueOf(end + 1));
                    childs[end] = autofillId.getVirtualChildLongId();
                }
            }
            final ArrayList<ViewTranslationRequest> requests = new ArrayList<>();
            final int[] supportedFormats = getSupportedFormatsLocked();
            final ArrayList<ViewRootImpl> roots = WindowManagerGlobal.getInstance().getRootViews(this.mActivity.getActivityToken());
            final TranslationCapability capability = getTranslationCapability(translator.getTranslationContext());
            this.mActivity.runOnUiThread(new Runnable() { // from class: android.view.translation.UiTranslationController$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    UiTranslationController.this.lambda$onUiTranslationStarted$6$UiTranslationController(roots, viewIds, supportedFormats, capability, requests, translator);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onUiTranslationStarted$6$UiTranslationController(ArrayList roots, Map viewIds, int[] supportedFormats, TranslationCapability capability, ArrayList requests, Translator translator) {
        for (int rootNum = 0; rootNum < roots.size(); rootNum++) {
            View rootView = ((ViewRootImpl) roots.get(rootNum)).getView();
            rootView.dispatchCreateViewTranslationRequest(viewIds, supportedFormats, capability, requests);
        }
        this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage(UiTranslationController$$ExternalSyntheticLambda1.INSTANCE, this, translator, requests));
    }

    private SparseIntArray getRequestVirtualViewChildCount(List<AutofillId> views) {
        SparseIntArray virtualViewCount = new SparseIntArray();
        for (int i = 0; i < views.size(); i++) {
            AutofillId autofillId = views.get(i);
            if (!autofillId.isNonVirtual()) {
                int virtualViewId = autofillId.getViewId();
                if (virtualViewCount.indexOfKey(virtualViewId) < 0) {
                    virtualViewCount.put(virtualViewId, 1);
                } else {
                    virtualViewCount.put(virtualViewId, virtualViewCount.get(virtualViewId) + 1);
                }
            }
        }
        return virtualViewCount;
    }

    private int[] getSupportedFormatsLocked() {
        return new int[]{1};
    }

    private TranslationCapability getTranslationCapability(TranslationContext translationContext) {
        return new TranslationCapability(3, translationContext.getSourceSpec(), translationContext.getTargetSpec(), true, 0);
    }

    private void findViewsTraversalByAutofillIds(IntArray sourceViewIds) {
        ArrayList<ViewRootImpl> roots = WindowManagerGlobal.getInstance().getRootViews(this.mActivity.getActivityToken());
        for (int rootNum = 0; rootNum < roots.size(); rootNum++) {
            View rootView = roots.get(rootNum).getView();
            if (rootView instanceof ViewGroup) {
                findViewsTraversalByAutofillIds((ViewGroup) rootView, sourceViewIds);
            } else {
                addViewIfNeeded(sourceViewIds, rootView);
            }
        }
    }

    private void findViewsTraversalByAutofillIds(ViewGroup viewGroup, IntArray sourceViewIds) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                findViewsTraversalByAutofillIds((ViewGroup) child, sourceViewIds);
            } else {
                addViewIfNeeded(sourceViewIds, child);
            }
        }
    }

    private void addViewIfNeeded(IntArray sourceViewIds, View view) {
        AutofillId autofillId = view.getAutofillId();
        if (sourceViewIds.indexOf(autofillId.getViewId()) >= 0 && !this.mViews.containsKey(autofillId)) {
            this.mViews.put(autofillId, new WeakReference<>(view));
        }
    }

    private void runForEachView(final BiConsumer<View, ViewTranslationCallback> action) {
        synchronized (this.mLock) {
            final ArrayMap<AutofillId, WeakReference<View>> views = new ArrayMap<>(this.mViews);
            if (views.size() == 0) {
                Log.w(TAG, "No views can be excuted for runForEachView.");
            }
            this.mActivity.runOnUiThread(new Runnable() { // from class: android.view.translation.UiTranslationController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    UiTranslationController.lambda$runForEachView$7(ArrayMap.this, action);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$runForEachView$7(ArrayMap views, BiConsumer action) {
        int viewCounts = views.size();
        for (int i = 0; i < viewCounts; i++) {
            View view = (View) ((WeakReference) views.valueAt(i)).get();
            boolean z = DEBUG;
            if (z) {
                StringBuilder sb = new StringBuilder();
                sb.append("runForEachView for autofillId = ");
                sb.append(view != null ? view.getAutofillId() : " null");
                Log.d(TAG, sb.toString());
            }
            if (view != null && view.getViewTranslationCallback() != null) {
                action.accept(view, view.getViewTranslationCallback());
            } else if (z) {
                Log.d(TAG, "View was gone or ViewTranslationCallback for autofillId = " + views.keyAt(i));
            }
        }
    }

    private Translator createTranslatorIfNeeded(TranslationSpec sourceSpec, TranslationSpec targetSpec) {
        TranslationManager tm = (TranslationManager) this.mContext.getSystemService(TranslationManager.class);
        if (tm == null) {
            Log.e(TAG, "Can not find TranslationManager when trying to create translator.");
            return null;
        }
        TranslationContext translationContext = new TranslationContext(sourceSpec, targetSpec, 0);
        Translator translator = tm.createTranslator(translationContext);
        if (translator != null) {
            Pair<TranslationSpec, TranslationSpec> specs = new Pair<>(sourceSpec, targetSpec);
            this.mTranslators.put(specs, translator);
        }
        return translator;
    }

    private void destroyTranslators() {
        synchronized (this.mLock) {
            int count = this.mTranslators.size();
            for (int i = 0; i < count; i++) {
                Translator translator = this.mTranslators.valueAt(i);
                translator.destroy();
            }
            this.mTranslators.clear();
        }
    }

    public static String stateToString(int state) {
        switch (state) {
            case 0:
                return "UI_TRANSLATION_STARTED";
            case 1:
                return "UI_TRANSLATION_PAUSED";
            case 2:
                return "UI_TRANSLATION_RESUMED";
            case 3:
                return "UI_TRANSLATION_FINISHED";
            default:
                return "Unknown state (" + state + ")";
        }
    }

    private static String sanitizedViewTranslationRequest(ViewTranslationRequest request) {
        StringBuilder msg = new StringBuilder("ViewTranslationRequest:{values=[");
        for (String key : request.getKeys()) {
            TranslationRequestValue value = request.getValue(key);
            msg.append("{text=");
            msg.append(value.getText() == null ? "null" : "string[" + value.getText().length() + "]}, ");
        }
        return msg.toString();
    }

    private static String sanitizedViewTranslationResponse(ViewTranslationResponse response) {
        StringBuilder msg = new StringBuilder("ViewTranslationResponse:{values=[");
        for (String key : response.getKeys()) {
            TranslationResponseValue value = response.getValue(key);
            msg.append("{status=");
            msg.append(value.getStatusCode());
            msg.append(", ");
            msg.append("text=");
            String str = "null";
            msg.append(value.getText() == null ? str : "string[" + value.getText().length() + "], ");
            msg.append("transliteration=");
            if (value.getTransliteration() != null) {
                str = "string[" + value.getTransliteration().length() + "]}, ";
            }
            msg.append(str);
        }
        return msg.toString();
    }
}
