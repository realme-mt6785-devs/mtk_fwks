package android.view;

import android.graphics.Rect;
import android.os.CancellationSignal;
import android.util.IndentingPrintWriter;
import android.view.ScrollCaptureSearchResults;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final class ScrollCaptureSearchResults {
    private static final int AFTER = 1;
    private static final int BEFORE = -1;
    private static final int EQUAL = 0;
    static final Comparator<ScrollCaptureTarget> PRIORITY_ORDER = ScrollCaptureSearchResults$$ExternalSyntheticLambda1.INSTANCE;
    private int mCompleted;
    private final Executor mExecutor;
    private Runnable mOnCompleteListener;
    private boolean mComplete = true;
    private final List<ScrollCaptureTarget> mTargets = new ArrayList();
    private final CancellationSignal mCancel = new CancellationSignal();

    static /* synthetic */ int access$308(ScrollCaptureSearchResults x0) {
        int i = x0.mCompleted;
        x0.mCompleted = i + 1;
        return i;
    }

    public ScrollCaptureSearchResults(Executor executor) {
        this.mExecutor = executor;
    }

    public void addTarget(ScrollCaptureTarget target) {
        Objects.requireNonNull(target);
        this.mTargets.add(target);
        this.mComplete = false;
        final ScrollCaptureCallback callback = target.getCallback();
        final Consumer<Rect> consumer = new SearchRequest(target);
        this.mExecutor.execute(new Runnable() { // from class: android.view.ScrollCaptureSearchResults$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ScrollCaptureSearchResults.this.lambda$addTarget$0$ScrollCaptureSearchResults(callback, consumer);
            }
        });
    }

    public /* synthetic */ void lambda$addTarget$0$ScrollCaptureSearchResults(ScrollCaptureCallback callback, Consumer consumer) {
        callback.onScrollCaptureSearch(this.mCancel, consumer);
    }

    public boolean isComplete() {
        return this.mComplete;
    }

    public void setOnCompleteListener(Runnable onComplete) {
        if (this.mComplete) {
            onComplete.run();
        } else {
            this.mOnCompleteListener = onComplete;
        }
    }

    public boolean isEmpty() {
        return this.mTargets.isEmpty();
    }

    public void finish() {
        if (!this.mComplete) {
            this.mCancel.cancel();
            signalComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signalComplete() {
        this.mComplete = true;
        this.mTargets.sort(PRIORITY_ORDER);
        Runnable runnable = this.mOnCompleteListener;
        if (runnable != null) {
            runnable.run();
            this.mOnCompleteListener = null;
        }
    }

    public List<ScrollCaptureTarget> getTargets() {
        return new ArrayList(this.mTargets);
    }

    public ScrollCaptureTarget getTopResult() {
        ScrollCaptureTarget target = this.mTargets.isEmpty() ? null : this.mTargets.get(0);
        if (target == null || target.getScrollBounds() == null) {
            return null;
        }
        return target;
    }

    /* loaded from: classes3.dex */
    private class SearchRequest implements Consumer<Rect> {
        private ScrollCaptureTarget mTarget;

        SearchRequest(ScrollCaptureTarget target) {
            this.mTarget = target;
        }

        public void accept(final Rect scrollBounds) {
            if (this.mTarget != null && !ScrollCaptureSearchResults.this.mCancel.isCanceled()) {
                ScrollCaptureSearchResults.this.mExecutor.execute(new Runnable() { // from class: android.view.ScrollCaptureSearchResults$SearchRequest$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ScrollCaptureSearchResults.SearchRequest.this.lambda$accept$0$ScrollCaptureSearchResults$SearchRequest(scrollBounds);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: consume */
        public void lambda$accept$0$ScrollCaptureSearchResults$SearchRequest(Rect scrollBounds) {
            if (this.mTarget != null && !ScrollCaptureSearchResults.this.mCancel.isCanceled()) {
                if (!ScrollCaptureSearchResults.nullOrEmpty(scrollBounds)) {
                    this.mTarget.setScrollBounds(scrollBounds);
                    this.mTarget.updatePositionInWindow();
                }
                ScrollCaptureSearchResults.access$308(ScrollCaptureSearchResults.this);
                this.mTarget = null;
                if (ScrollCaptureSearchResults.this.mCompleted == ScrollCaptureSearchResults.this.mTargets.size()) {
                    ScrollCaptureSearchResults.this.signalComplete();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$1(ScrollCaptureTarget a, ScrollCaptureTarget b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null || b == null) {
            return a == null ? 1 : -1;
        }
        boolean emptyScrollBoundsA = nullOrEmpty(a.getScrollBounds());
        boolean emptyScrollBoundsB = nullOrEmpty(b.getScrollBounds());
        if (!emptyScrollBoundsA && !emptyScrollBoundsB) {
            View viewA = a.getContainingView();
            View viewB = b.getContainingView();
            boolean hintIncludeA = hasIncludeHint(viewA);
            boolean hintIncludeB = hasIncludeHint(viewB);
            if (hintIncludeA != hintIncludeB) {
                return hintIncludeA ? -1 : 1;
            }
            if (isDescendant(viewA, viewB)) {
                return -1;
            }
            if (isDescendant(viewB, viewA)) {
                return 1;
            }
            int scrollAreaA = area(a.getScrollBounds());
            int scrollAreaB = area(b.getScrollBounds());
            return scrollAreaA >= scrollAreaB ? -1 : 1;
        } else if (!emptyScrollBoundsA || !emptyScrollBoundsB) {
            return emptyScrollBoundsA ? 1 : -1;
        } else {
            return 0;
        }
    }

    private static int area(Rect r) {
        return r.width() * r.height();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean nullOrEmpty(Rect r) {
        return r == null || r.isEmpty();
    }

    private static boolean hasIncludeHint(View view) {
        return (view.getScrollCaptureHint() & 2) != 0;
    }

    private static boolean isDescendant(View view, View otherView) {
        if (view == otherView) {
            return false;
        }
        ViewParent otherParent = otherView.getParent();
        while (otherParent != view && otherParent != null) {
            otherParent = otherParent.getParent();
        }
        return otherParent == view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dump(IndentingPrintWriter writer) {
        writer.println("results:");
        writer.increaseIndent();
        writer.println("complete: " + isComplete());
        writer.println("cancelled: " + this.mCancel.isCanceled());
        writer.println("targets:");
        writer.increaseIndent();
        if (isEmpty()) {
            writer.println("None");
        } else {
            for (int i = 0; i < this.mTargets.size(); i++) {
                writer.println("[" + i + "]");
                writer.increaseIndent();
                this.mTargets.get(i).dump(writer);
                writer.decreaseIndent();
            }
            writer.decreaseIndent();
        }
        writer.decreaseIndent();
    }
}
