package android.view;

import android.os.Looper;
/* loaded from: classes3.dex */
public class BatchedInputEventReceiver extends InputEventReceiver {
    private boolean mBatchedInputScheduled;
    private Choreographer mChoreographer;
    private final BatchedInputRunnable mBatchedInputRunnable = new BatchedInputRunnable();
    private boolean mBatchingEnabled = true;

    public BatchedInputEventReceiver(InputChannel inputChannel, Looper looper, Choreographer choreographer) {
        super(inputChannel, looper);
        this.mChoreographer = choreographer;
    }

    @Override // android.view.InputEventReceiver
    public void onBatchedInputEventPending(int source) {
        if (this.mBatchingEnabled) {
            scheduleBatchedInput();
        } else {
            consumeBatchedInputEvents(-1L);
        }
    }

    @Override // android.view.InputEventReceiver
    public void dispose() {
        unscheduleBatchedInput();
        consumeBatchedInputEvents(-1L);
        super.dispose();
    }

    public void setBatchingEnabled(boolean batchingEnabled) {
        this.mBatchingEnabled = batchingEnabled;
        if (!batchingEnabled) {
            unscheduleBatchedInput();
            consumeBatchedInputEvents(-1L);
        }
    }

    void doConsumeBatchedInput(long frameTimeNanos) {
        if (this.mBatchedInputScheduled) {
            this.mBatchedInputScheduled = false;
            if (consumeBatchedInputEvents(frameTimeNanos) && frameTimeNanos != -1) {
                scheduleBatchedInput();
            }
        }
    }

    private void scheduleBatchedInput() {
        if (!this.mBatchedInputScheduled) {
            this.mBatchedInputScheduled = true;
            this.mChoreographer.postCallback(0, this.mBatchedInputRunnable, null);
        }
    }

    private void unscheduleBatchedInput() {
        if (this.mBatchedInputScheduled) {
            this.mBatchedInputScheduled = false;
            this.mChoreographer.removeCallbacks(0, this.mBatchedInputRunnable, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class BatchedInputRunnable implements Runnable {
        private BatchedInputRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BatchedInputEventReceiver batchedInputEventReceiver = BatchedInputEventReceiver.this;
            batchedInputEventReceiver.doConsumeBatchedInput(batchedInputEventReceiver.mChoreographer.getFrameTimeNanos());
        }
    }
}
