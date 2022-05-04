package android.hardware.radio;

import android.annotation.SystemApi;
import android.hardware.radio.ProgramList;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Collectors;
@SystemApi
/* loaded from: classes2.dex */
public final class ProgramList implements AutoCloseable {
    private OnCloseListener mOnCloseListener;
    private final Object mLock = new Object();
    private final Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> mPrograms = new HashMap();
    private final List<ListCallback> mListCallbacks = new ArrayList();
    private final List<OnCompleteListener> mOnCompleteListeners = new ArrayList();
    private boolean mIsClosed = false;
    private boolean mIsComplete = false;

    /* loaded from: classes2.dex */
    interface OnCloseListener {
        void onClose();
    }

    /* loaded from: classes2.dex */
    public interface OnCompleteListener {
        void onComplete();
    }

    /* loaded from: classes2.dex */
    public static abstract class ListCallback {
        public void onItemChanged(ProgramSelector.Identifier id) {
        }

        public void onItemRemoved(ProgramSelector.Identifier id) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.radio.ProgramList$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends ListCallback {
        final /* synthetic */ ListCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, ListCallback listCallback) {
            this.val$executor = executor;
            this.val$callback = listCallback;
        }

        @Override // android.hardware.radio.ProgramList.ListCallback
        public void onItemChanged(final ProgramSelector.Identifier id) {
            Executor executor = this.val$executor;
            final ListCallback listCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.hardware.radio.ProgramList$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ProgramList.ListCallback.this.onItemChanged(id);
                }
            });
        }

        @Override // android.hardware.radio.ProgramList.ListCallback
        public void onItemRemoved(final ProgramSelector.Identifier id) {
            Executor executor = this.val$executor;
            final ListCallback listCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.hardware.radio.ProgramList$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ProgramList.ListCallback.this.onItemRemoved(id);
                }
            });
        }
    }

    public void registerListCallback(Executor executor, ListCallback callback) {
        registerListCallback(new AnonymousClass1(executor, callback));
    }

    public void registerListCallback(ListCallback callback) {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                List<ListCallback> list = this.mListCallbacks;
                Objects.requireNonNull(callback);
                list.add(callback);
            }
        }
    }

    public void unregisterListCallback(ListCallback callback) {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                List<ListCallback> list = this.mListCallbacks;
                Objects.requireNonNull(callback);
                list.remove(callback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addOnCompleteListener$0(Executor executor, final OnCompleteListener listener) {
        Objects.requireNonNull(listener);
        executor.execute(new Runnable() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ProgramList.OnCompleteListener.this.onComplete();
            }
        });
    }

    public void addOnCompleteListener(final Executor executor, final OnCompleteListener listener) {
        addOnCompleteListener(new OnCompleteListener() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda0
            @Override // android.hardware.radio.ProgramList.OnCompleteListener
            public final void onComplete() {
                ProgramList.lambda$addOnCompleteListener$0(executor, listener);
            }
        });
    }

    public void addOnCompleteListener(OnCompleteListener listener) {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                List<OnCompleteListener> list = this.mOnCompleteListeners;
                Objects.requireNonNull(listener);
                list.add(listener);
                if (this.mIsComplete) {
                    listener.onComplete();
                }
            }
        }
    }

    public void removeOnCompleteListener(OnCompleteListener listener) {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                List<OnCompleteListener> list = this.mOnCompleteListeners;
                Objects.requireNonNull(listener);
                list.remove(listener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnCloseListener(OnCloseListener listener) {
        synchronized (this.mLock) {
            if (this.mOnCloseListener == null) {
                this.mOnCloseListener = listener;
            } else {
                throw new IllegalStateException("Close callback is already set");
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                this.mIsClosed = true;
                this.mPrograms.clear();
                this.mListCallbacks.clear();
                this.mOnCompleteListeners.clear();
                OnCloseListener onCloseListener = this.mOnCloseListener;
                if (onCloseListener != null) {
                    onCloseListener.onClose();
                    this.mOnCloseListener = null;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void apply(Chunk chunk) {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                this.mIsComplete = false;
                if (chunk.isPurge()) {
                    new HashSet(this.mPrograms.keySet()).stream().forEach(new Consumer() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda2
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ProgramList.this.lambda$apply$1$ProgramList((ProgramSelector.Identifier) obj);
                        }
                    });
                }
                chunk.getRemoved().stream().forEach(new Consumer() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ProgramList.this.lambda$apply$2$ProgramList((ProgramSelector.Identifier) obj);
                    }
                });
                chunk.getModified().stream().forEach(new Consumer() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ProgramList.this.lambda$apply$3$ProgramList((RadioManager.ProgramInfo) obj);
                    }
                });
                if (chunk.isComplete()) {
                    this.mIsComplete = true;
                    this.mOnCompleteListeners.forEach(ProgramList$$ExternalSyntheticLambda7.INSTANCE);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: putLocked */
    public void lambda$apply$3$ProgramList(RadioManager.ProgramInfo value) {
        ProgramSelector.Identifier key = value.getSelector().getPrimaryId();
        Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> map = this.mPrograms;
        Objects.requireNonNull(key);
        map.put(key, value);
        final ProgramSelector.Identifier sel = value.getSelector().getPrimaryId();
        this.mListCallbacks.forEach(new Consumer() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ProgramList.ListCallback) obj).onItemChanged(ProgramSelector.Identifier.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: removeLocked */
    public void lambda$apply$2$ProgramList(ProgramSelector.Identifier key) {
        Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> map = this.mPrograms;
        Objects.requireNonNull(key);
        RadioManager.ProgramInfo removed = map.remove(key);
        if (removed != null) {
            final ProgramSelector.Identifier sel = removed.getSelector().getPrimaryId();
            this.mListCallbacks.forEach(new Consumer() { // from class: android.hardware.radio.ProgramList$$ExternalSyntheticLambda6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ProgramList.ListCallback) obj).onItemRemoved(ProgramSelector.Identifier.this);
                }
            });
        }
    }

    public List<RadioManager.ProgramInfo> toList() {
        List<RadioManager.ProgramInfo> list;
        synchronized (this.mLock) {
            list = (List) this.mPrograms.values().stream().collect(Collectors.toList());
        }
        return list;
    }

    public RadioManager.ProgramInfo get(ProgramSelector.Identifier id) {
        RadioManager.ProgramInfo programInfo;
        synchronized (this.mLock) {
            Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> map = this.mPrograms;
            Objects.requireNonNull(id);
            programInfo = map.get(id);
        }
        return programInfo;
    }

    /* loaded from: classes2.dex */
    public static final class Filter implements Parcelable {
        public static final Parcelable.Creator<Filter> CREATOR = new Parcelable.Creator<Filter>() { // from class: android.hardware.radio.ProgramList.Filter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Filter createFromParcel(Parcel in) {
                return new Filter(in, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Filter[] newArray(int size) {
                return new Filter[size];
            }
        };
        private final boolean mExcludeModifications;
        private final Set<Integer> mIdentifierTypes;
        private final Set<ProgramSelector.Identifier> mIdentifiers;
        private final boolean mIncludeCategories;
        private final Map<String, String> mVendorFilter;

        /* synthetic */ Filter(Parcel x0, AnonymousClass1 x1) {
            this(x0);
        }

        public Filter(Set<Integer> identifierTypes, Set<ProgramSelector.Identifier> identifiers, boolean includeCategories, boolean excludeModifications) {
            Objects.requireNonNull(identifierTypes);
            this.mIdentifierTypes = identifierTypes;
            Objects.requireNonNull(identifiers);
            this.mIdentifiers = identifiers;
            this.mIncludeCategories = includeCategories;
            this.mExcludeModifications = excludeModifications;
            this.mVendorFilter = null;
        }

        public Filter() {
            this.mIdentifierTypes = Collections.emptySet();
            this.mIdentifiers = Collections.emptySet();
            this.mIncludeCategories = false;
            this.mExcludeModifications = false;
            this.mVendorFilter = null;
        }

        public Filter(Map<String, String> vendorFilter) {
            this.mIdentifierTypes = Collections.emptySet();
            this.mIdentifiers = Collections.emptySet();
            this.mIncludeCategories = false;
            this.mExcludeModifications = false;
            this.mVendorFilter = vendorFilter;
        }

        private Filter(Parcel in) {
            this.mIdentifierTypes = Utils.createIntSet(in);
            this.mIdentifiers = Utils.createSet(in, ProgramSelector.Identifier.CREATOR);
            boolean z = true;
            this.mIncludeCategories = in.readByte() != 0;
            this.mExcludeModifications = in.readByte() == 0 ? false : z;
            this.mVendorFilter = Utils.readStringMap(in);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            Utils.writeIntSet(dest, this.mIdentifierTypes);
            Utils.writeSet(dest, this.mIdentifiers);
            dest.writeByte(this.mIncludeCategories ? (byte) 1 : (byte) 0);
            dest.writeByte(this.mExcludeModifications ? (byte) 1 : (byte) 0);
            Utils.writeStringMap(dest, this.mVendorFilter);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Map<String, String> getVendorFilter() {
            return this.mVendorFilter;
        }

        public Set<Integer> getIdentifierTypes() {
            return this.mIdentifierTypes;
        }

        public Set<ProgramSelector.Identifier> getIdentifiers() {
            return this.mIdentifiers;
        }

        public boolean areCategoriesIncluded() {
            return this.mIncludeCategories;
        }

        public boolean areModificationsExcluded() {
            return this.mExcludeModifications;
        }

        public int hashCode() {
            return Objects.hash(this.mIdentifierTypes, this.mIdentifiers, Boolean.valueOf(this.mIncludeCategories), Boolean.valueOf(this.mExcludeModifications));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Filter)) {
                return false;
            }
            Filter other = (Filter) obj;
            return this.mIncludeCategories == other.mIncludeCategories && this.mExcludeModifications == other.mExcludeModifications && Objects.equals(this.mIdentifierTypes, other.mIdentifierTypes) && Objects.equals(this.mIdentifiers, other.mIdentifiers);
        }

        public String toString() {
            return "Filter [mIdentifierTypes=" + this.mIdentifierTypes + ", mIdentifiers=" + this.mIdentifiers + ", mIncludeCategories=" + this.mIncludeCategories + ", mExcludeModifications=" + this.mExcludeModifications + "]";
        }
    }

    /* loaded from: classes2.dex */
    public static final class Chunk implements Parcelable {
        public static final Parcelable.Creator<Chunk> CREATOR = new Parcelable.Creator<Chunk>() { // from class: android.hardware.radio.ProgramList.Chunk.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Chunk createFromParcel(Parcel in) {
                return new Chunk(in, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Chunk[] newArray(int size) {
                return new Chunk[size];
            }
        };
        private final boolean mComplete;
        private final Set<RadioManager.ProgramInfo> mModified;
        private final boolean mPurge;
        private final Set<ProgramSelector.Identifier> mRemoved;

        /* synthetic */ Chunk(Parcel x0, AnonymousClass1 x1) {
            this(x0);
        }

        public Chunk(boolean purge, boolean complete, Set<RadioManager.ProgramInfo> modified, Set<ProgramSelector.Identifier> removed) {
            this.mPurge = purge;
            this.mComplete = complete;
            this.mModified = modified != null ? modified : Collections.emptySet();
            this.mRemoved = removed != null ? removed : Collections.emptySet();
        }

        private Chunk(Parcel in) {
            boolean z = true;
            this.mPurge = in.readByte() != 0;
            this.mComplete = in.readByte() == 0 ? false : z;
            this.mModified = Utils.createSet(in, RadioManager.ProgramInfo.CREATOR);
            this.mRemoved = Utils.createSet(in, ProgramSelector.Identifier.CREATOR);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.mPurge ? (byte) 1 : (byte) 0);
            dest.writeByte(this.mComplete ? (byte) 1 : (byte) 0);
            Utils.writeSet(dest, this.mModified);
            Utils.writeSet(dest, this.mRemoved);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean isPurge() {
            return this.mPurge;
        }

        public boolean isComplete() {
            return this.mComplete;
        }

        public Set<RadioManager.ProgramInfo> getModified() {
            return this.mModified;
        }

        public Set<ProgramSelector.Identifier> getRemoved() {
            return this.mRemoved;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Chunk)) {
                return false;
            }
            Chunk other = (Chunk) obj;
            return this.mPurge == other.mPurge && this.mComplete == other.mComplete && Objects.equals(this.mModified, other.mModified) && Objects.equals(this.mRemoved, other.mRemoved);
        }

        public String toString() {
            return "Chunk [mPurge=" + this.mPurge + ", mComplete=" + this.mComplete + ", mModified=" + this.mModified + ", mRemoved=" + this.mRemoved + "]";
        }
    }
}
