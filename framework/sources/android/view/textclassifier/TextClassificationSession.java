package android.view.textclassifier;

import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextLanguage;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;
import com.android.internal.util.Preconditions;
import java.util.Objects;
import java.util.function.Supplier;
import sun.misc.Cleaner;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class TextClassificationSession implements TextClassifier {
    private static final String LOG_TAG = "TextClassificationSession";
    private final TextClassificationContext mClassificationContext;
    private final Cleaner mCleaner;
    private final TextClassifier mDelegate;
    private boolean mDestroyed;
    private final SelectionEventHelper mEventHelper;
    private final Object mLock = new Object();
    private final TextClassificationSessionId mSessionId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextClassificationSession(TextClassificationContext context, TextClassifier delegate) {
        Objects.requireNonNull(context);
        TextClassificationContext textClassificationContext = context;
        this.mClassificationContext = textClassificationContext;
        Objects.requireNonNull(delegate);
        TextClassifier textClassifier = delegate;
        this.mDelegate = textClassifier;
        TextClassificationSessionId textClassificationSessionId = new TextClassificationSessionId();
        this.mSessionId = textClassificationSessionId;
        SelectionEventHelper selectionEventHelper = new SelectionEventHelper(textClassificationSessionId, textClassificationContext);
        this.mEventHelper = selectionEventHelper;
        initializeRemoteSession();
        this.mCleaner = Cleaner.create(this, new CleanerRunnable(selectionEventHelper, textClassifier));
    }

    public /* synthetic */ TextSelection lambda$suggestSelection$0$TextClassificationSession(TextSelection.Request request) {
        return this.mDelegate.suggestSelection(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextSelection suggestSelection(final TextSelection.Request request) {
        return (TextSelection) checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$suggestSelection$0$TextClassificationSession(request);
            }
        });
    }

    private void initializeRemoteSession() {
        TextClassifier textClassifier = this.mDelegate;
        if (textClassifier instanceof SystemTextClassifier) {
            ((SystemTextClassifier) textClassifier).initializeRemoteSession(this.mClassificationContext, this.mSessionId);
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextClassification classifyText(final TextClassification.Request request) {
        return (TextClassification) checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$classifyText$1$TextClassificationSession(request);
            }
        });
    }

    public /* synthetic */ TextClassification lambda$classifyText$1$TextClassificationSession(TextClassification.Request request) {
        return this.mDelegate.classifyText(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextLinks generateLinks(final TextLinks.Request request) {
        return (TextLinks) checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$generateLinks$2$TextClassificationSession(request);
            }
        });
    }

    public /* synthetic */ TextLinks lambda$generateLinks$2$TextClassificationSession(TextLinks.Request request) {
        return this.mDelegate.generateLinks(request);
    }

    public /* synthetic */ ConversationActions lambda$suggestConversationActions$3$TextClassificationSession(ConversationActions.Request request) {
        return this.mDelegate.suggestConversationActions(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public ConversationActions suggestConversationActions(final ConversationActions.Request request) {
        return (ConversationActions) checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$suggestConversationActions$3$TextClassificationSession(request);
            }
        });
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextLanguage detectLanguage(final TextLanguage.Request request) {
        return (TextLanguage) checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$detectLanguage$4$TextClassificationSession(request);
            }
        });
    }

    public /* synthetic */ TextLanguage lambda$detectLanguage$4$TextClassificationSession(TextLanguage.Request request) {
        return this.mDelegate.detectLanguage(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public int getMaxGenerateLinksTextLength() {
        final TextClassifier textClassifier = this.mDelegate;
        Objects.requireNonNull(textClassifier);
        return ((Integer) checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(TextClassifier.this.getMaxGenerateLinksTextLength());
            }
        })).intValue();
    }

    @Override // android.view.textclassifier.TextClassifier
    public void onSelectionEvent(final SelectionEvent event) {
        checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$onSelectionEvent$5$TextClassificationSession(event);
            }
        });
    }

    public /* synthetic */ Object lambda$onSelectionEvent$5$TextClassificationSession(SelectionEvent event) {
        try {
            if (!this.mEventHelper.sanitizeEvent(event)) {
                return null;
            }
            this.mDelegate.onSelectionEvent(event);
            return null;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error reporting text classifier selection event", e);
            return null;
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public void onTextClassifierEvent(final TextClassifierEvent event) {
        checkDestroyedAndRun(new Supplier() { // from class: android.view.textclassifier.TextClassificationSession$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextClassificationSession.this.lambda$onTextClassifierEvent$6$TextClassificationSession(event);
            }
        });
    }

    public /* synthetic */ Object lambda$onTextClassifierEvent$6$TextClassificationSession(TextClassifierEvent event) {
        try {
            event.mHiddenTempSessionId = this.mSessionId;
            this.mDelegate.onTextClassifierEvent(event);
            return null;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error reporting text classifier event", e);
            return null;
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public void destroy() {
        synchronized (this.mLock) {
            if (!this.mDestroyed) {
                this.mCleaner.clean();
                this.mDestroyed = true;
            }
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public boolean isDestroyed() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mDestroyed;
        }
        return z;
    }

    private <T> T checkDestroyedAndRun(Supplier<T> responseSupplier) {
        if (!isDestroyed()) {
            T response = responseSupplier.get();
            synchronized (this.mLock) {
                if (!this.mDestroyed) {
                    return response;
                }
            }
        }
        throw new IllegalStateException("This TextClassification session has been destroyed");
    }

    /* loaded from: classes3.dex */
    private static final class SelectionEventHelper {
        private final TextClassificationContext mContext;
        private int mInvocationMethod = 0;
        private SelectionEvent mPrevEvent;
        private final TextClassificationSessionId mSessionId;
        private SelectionEvent mSmartEvent;
        private SelectionEvent mStartEvent;

        SelectionEventHelper(TextClassificationSessionId sessionId, TextClassificationContext context) {
            Objects.requireNonNull(sessionId);
            this.mSessionId = sessionId;
            Objects.requireNonNull(context);
            this.mContext = context;
        }

        boolean sanitizeEvent(SelectionEvent event) {
            updateInvocationMethod(event);
            modifyAutoSelectionEventType(event);
            boolean z = false;
            if (event.getEventType() == 1 || this.mStartEvent != null) {
                long now = System.currentTimeMillis();
                switch (event.getEventType()) {
                    case 1:
                        if (event.getAbsoluteEnd() == event.getAbsoluteStart() + 1) {
                            z = true;
                        }
                        Preconditions.checkArgument(z);
                        event.setSessionId(this.mSessionId);
                        this.mStartEvent = event;
                        break;
                    case 2:
                        SelectionEvent selectionEvent = this.mPrevEvent;
                        if (selectionEvent != null && selectionEvent.getAbsoluteStart() == event.getAbsoluteStart() && this.mPrevEvent.getAbsoluteEnd() == event.getAbsoluteEnd()) {
                            return false;
                        }
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.mSmartEvent = event;
                        break;
                    case 100:
                    case 107:
                        SelectionEvent selectionEvent2 = this.mPrevEvent;
                        if (selectionEvent2 != null) {
                            event.setEntityType(selectionEvent2.getEntityType());
                            break;
                        }
                        break;
                }
                event.setEventTime(now);
                SelectionEvent selectionEvent3 = this.mStartEvent;
                if (selectionEvent3 != null) {
                    event.setSessionId(selectionEvent3.getSessionId()).setDurationSinceSessionStart(now - this.mStartEvent.getEventTime()).setStart(event.getAbsoluteStart() - this.mStartEvent.getAbsoluteStart()).setEnd(event.getAbsoluteEnd() - this.mStartEvent.getAbsoluteStart());
                }
                SelectionEvent selectionEvent4 = this.mSmartEvent;
                if (selectionEvent4 != null) {
                    event.setResultId(selectionEvent4.getResultId()).setSmartStart(this.mSmartEvent.getAbsoluteStart() - this.mStartEvent.getAbsoluteStart()).setSmartEnd(this.mSmartEvent.getAbsoluteEnd() - this.mStartEvent.getAbsoluteStart());
                }
                SelectionEvent selectionEvent5 = this.mPrevEvent;
                if (selectionEvent5 != null) {
                    event.setDurationSincePreviousEvent(now - selectionEvent5.getEventTime()).setEventIndex(this.mPrevEvent.getEventIndex() + 1);
                }
                this.mPrevEvent = event;
                return true;
            }
            Log.d(TextClassificationSession.LOG_TAG, "Selection session not yet started. Ignoring event");
            return false;
        }

        void endSession() {
            this.mPrevEvent = null;
            this.mSmartEvent = null;
            this.mStartEvent = null;
        }

        private void updateInvocationMethod(SelectionEvent event) {
            event.setTextClassificationSessionContext(this.mContext);
            if (event.getInvocationMethod() == 0) {
                event.setInvocationMethod(this.mInvocationMethod);
            } else {
                this.mInvocationMethod = event.getInvocationMethod();
            }
        }

        private void modifyAutoSelectionEventType(SelectionEvent event) {
            switch (event.getEventType()) {
                case 3:
                case 4:
                case 5:
                    if (!SelectionSessionLogger.isPlatformLocalTextClassifierSmartSelection(event.getResultId())) {
                        event.setEventType(5);
                        return;
                    } else if (event.getAbsoluteEnd() - event.getAbsoluteStart() > 1) {
                        event.setEventType(4);
                        return;
                    } else {
                        event.setEventType(3);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* loaded from: classes3.dex */
    private static class CleanerRunnable implements Runnable {
        private final TextClassifier mDelegate;
        private final SelectionEventHelper mEventHelper;

        CleanerRunnable(SelectionEventHelper eventHelper, TextClassifier delegate) {
            Objects.requireNonNull(eventHelper);
            this.mEventHelper = eventHelper;
            Objects.requireNonNull(delegate);
            this.mDelegate = delegate;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mEventHelper.endSession();
            this.mDelegate.destroy();
        }
    }
}
